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
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleResultSet.AuthorizationIndicator;
import oracle.jdbc.replay.driver.NonTxnReplayableResultSet;
import oracle.sql.ARRAY;
import oracle.sql.BFILE;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.OPAQUE;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.RAW;
import oracle.sql.REF;
import oracle.sql.ROWID;
import oracle.sql.STRUCT;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1OracleResultSet$$$Proxy
  extends NonTxnReplayableResultSet
  implements OracleResultSet, _Proxy_
{
  private OracleResultSet delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject27966;
  private static Method methodObject28196;
  private static Method methodObject28010;
  private static Method methodObject28146;
  private static Method methodObject27956;
  private static Method methodObject27945;
  private static Method methodObject28184;
  private static Method methodObject28076;
  private static Method methodObject27950;
  private static Method methodObject28098;
  private static Method methodObject28211;
  private static Method methodObject28088;
  private static Method methodObject28090;
  private static Method methodObject28166;
  private static Method methodObject28058;
  private static Method methodObject28022;
  private static Method methodObject28042;
  private static Method methodObject27946;
  private static Method methodObject28037;
  private static Method methodObject28008;
  private static Method methodObject28155;
  private static Method methodObject28031;
  private static Method methodObject27960;
  private static Method methodObject28203;
  private static Method methodObject28189;
  private static Method methodObject28188;
  private static Method methodObject28036;
  private static Method methodObject27997;
  private static Method methodObject27989;
  private static Method methodObject28149;
  private static Method methodObject27979;
  private static Method methodObject27992;
  private static Method methodObject27973;
  private static Method methodObject28006;
  private static Method methodObject27975;
  private static Method methodObject28083;
  private static Method methodObject28190;
  private static Method methodObject27941;
  private static Method methodObject28201;
  private static Method methodObject28104;
  private static Method methodObject28133;
  private static Method methodObject28079;
  private static Method methodObject28086;
  private static Method methodObject28141;
  private static Method methodObject27988;
  private static Method methodObject28143;
  private static Method methodObject28216;
  private static Method methodObject28113;
  private static Method methodObject28101;
  private static Method methodObject28181;
  private static Method methodObject28046;
  private static Method methodObject28017;
  private static Method methodObject28066;
  private static Method methodObject28174;
  private static Method methodObject27947;
  private static Method methodObject28145;
  private static Method methodObject27944;
  private static Method methodObject28032;
  private static Method methodObject27958;
  private static Method methodObject27982;
  private static Method methodObject28011;
  private static Method methodObject28205;
  private static Method methodObject28163;
  private static Method methodObject28096;
  private static Method methodObject28102;
  private static Method methodObject28153;
  private static Method methodObject28100;
  private static Method methodObject28158;
  private static Method methodObject28033;
  private static Method methodObject28173;
  private static Method methodObject27993;
  private static Method methodObject28072;
  private static Method methodObject28124;
  private static Method methodObject28061;
  private static Method methodObject28170;
  private static Method methodObject28135;
  private static Method methodObject28056;
  private static Method methodObject28067;
  private static Method methodObject28193;
  private static Method methodObject28127;
  private static Method methodObject28140;
  private static Method methodObject28208;
  private static Method methodObject28187;
  private static Method methodObject28175;
  private static Method methodObject28026;
  private static Method methodObject28005;
  private static Method methodObject28195;
  private static Method methodObject28051;
  private static Method methodObject28043;
  private static Method methodObject28055;
  private static Method methodObject28073;
  private static Method methodObject28084;
  private static Method methodObject28059;
  private static Method methodObject27968;
  private static Method methodObject28212;
  private static Method methodObject28200;
  private static Method methodObject27978;
  private static Method methodObject27972;
  private static Method methodObject28047;
  private static Method methodObject28052;
  private static Method methodObject28116;
  private static Method methodObject28019;
  private static Method methodObject28070;
  private static Method methodObject28209;
  private static Method methodObject28119;
  private static Method methodObject28129;
  private static Method methodObject28182;
  private static Method methodObject28094;
  private static Method methodObject28095;
  private static Method methodObject28016;
  private static Method methodObject28130;
  private static Method methodObject28202;
  private static Method methodObject28111;
  private static Method methodObject28034;
  private static Method methodObject28120;
  private static Method methodObject28014;
  private static Method methodObject28091;
  private static Method methodObject28139;
  private static Method methodObject28167;
  private static Method methodObject28161;
  private static Method methodObject28069;
  private static Method methodObject28075;
  private static Method methodObject27943;
  private static Method methodObject28048;
  private static Method methodObject27981;
  private static Method methodObject28150;
  private static Method methodObject27990;
  private static Method methodObject28077;
  private static Method methodObject28107;
  private static Method methodObject27977;
  private static Method methodObject28210;
  private static Method methodObject28081;
  private static Method methodObject28204;
  private static Method methodObject27998;
  private static Method methodObject28199;
  private static Method methodObject28115;
  private static Method methodObject28186;
  private static Method methodObject27996;
  private static Method methodObject28001;
  private static Method methodObject28025;
  private static Method methodObject27970;
  private static Method methodObject27999;
  private static Method methodObject28093;
  private static Method methodObject28097;
  private static Method methodObject28003;
  private static Method methodObject28213;
  private static Method methodObject28117;
  private static Method methodObject28085;
  private static Method methodObject28169;
  private static Method methodObject28148;
  private static Method methodObject28087;
  private static Method methodObject28000;
  private static Method methodObject27951;
  private static Method methodObject28112;
  private static Method methodObject28134;
  private static Method methodObject28126;
  private static Method methodObject28060;
  private static Method methodObject27964;
  private static Method methodObject28045;
  private static Method methodObject27986;
  private static Method methodObject28191;
  private static Method methodObject28178;
  private static Method methodObject27995;
  private static Method methodObject28080;
  private static Method methodObject28156;
  private static Method methodObject28197;
  private static Method methodObject28041;
  private static Method methodObject28009;
  private static Method methodObject27961;
  private static Method methodObject28105;
  private static Method methodObject28180;
  private static Method methodObject28164;
  private static Method methodObject28121;
  private static Method methodObject27942;
  private static Method methodObject28183;
  private static Method methodObject27984;
  private static Method methodObject28024;
  private static Method methodObject28002;
  private static Method methodObject28004;
  private static Method methodObject27957;
  private static Method methodObject28108;
  private static Method methodObject28068;
  private static Method methodObject28028;
  private static Method methodObject27954;
  private static Method methodObject27963;
  private static Method methodObject27980;
  private static Method methodObject28217;
  private static Method methodObject28151;
  private static Method methodObject27959;
  private static Method methodObject28110;
  private static Method methodObject28179;
  private static Method methodObject27967;
  private static Method methodObject28154;
  private static Method methodObject28103;
  private static Method methodObject28162;
  private static Method methodObject28142;
  private static Method methodObject28157;
  private static Method methodObject28176;
  private static Method methodObject27949;
  private static Method methodObject27962;
  private static Method methodObject28089;
  private static Method methodObject28159;
  private static Method methodObject28082;
  private static Method methodObject28123;
  private static Method methodObject28057;
  private static Method methodObject28054;
  private static Method methodObject28044;
  private static Method methodObject28013;
  private static Method methodObject28021;
  private static Method methodObject28128;
  private static Method methodObject27994;
  private static Method methodObject28064;
  private static Method methodObject27974;
  private static Method methodObject28007;
  private static Method methodObject28118;
  private static Method methodObject28165;
  private static Method methodObject28029;
  private static Method methodObject28015;
  private static Method methodObject27985;
  private static Method methodObject28099;
  private static Method methodObject28168;
  private static Method methodObject28194;
  private static Method methodObject28206;
  private static Method methodObject27965;
  private static Method methodObject27983;
  private static Method methodObject27987;
  private static Method methodObject28053;
  private static Method methodObject28214;
  private static Method methodObject28207;
  private static Method methodObject28023;
  private static Method methodObject28106;
  private static Method methodObject28131;
  private static Method methodObject27976;
  private static Method methodObject28185;
  private static Method methodObject28138;
  private static Method methodObject28078;
  private static Method methodObject28027;
  private static Method methodObject28109;
  private static Method methodObject28020;
  private static Method methodObject28125;
  private static Method methodObject28018;
  private static Method methodObject28198;
  private static Method methodObject27991;
  private static Method methodObject28147;
  private static Method methodObject28215;
  private static Method methodObject28030;
  private static Method methodObject28040;
  private static Method methodObject28065;
  private static Method methodObject28012;
  private static Method methodObject28136;
  private static Method methodObject27953;
  private static Method methodObject27952;
  private static Method methodObject28114;
  private static Method methodObject28192;
  private static Method methodObject28050;
  private static Method methodObject28160;
  private static Method methodObject28071;
  private static Method methodObject28074;
  private static Method methodObject28039;
  private static Method methodObject28092;
  private static Method methodObject27969;
  private static Method methodObject28144;
  private static Method methodObject28122;
  private static Method methodObject28171;
  private static Method methodObject28035;
  private static Method methodObject27948;
  private static Method methodObject28049;
  private static Method methodObject28172;
  private static Method methodObject27955;
  private static Method methodObject28152;
  private static Method methodObject28062;
  private static Method methodObject27971;
  private static Method methodObject28063;
  private static Method methodObject28132;
  private static Method methodObject28137;
  private static Method methodObject28177;
  private static Method methodObject28038;
  
  public NUMBER getNUMBER(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27966, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject27966, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27966, onErrorForAll(methodObject27966, e));
    }
  }
  
  public void updateNull(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28196, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.updateNull(arg0);
      postForAll(methodObject28196);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28196, e);
    }
  }
  
  public void updateTIMESTAMP(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28010, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMP(arg0, arg1);
      postForAll(methodObject28010);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28010, e);
    }
  }
  
  public boolean isLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28146, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28146, Boolean.valueOf(this.delegate.isLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28146, onErrorForAll(methodObject28146, e))).booleanValue();
    }
  }
  
  public CHAR getCHAR(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27956, this, new Object[] { arg0 });
      return (CHAR)postForAll(methodObject27956, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject27956, onErrorForAll(methodObject27956, e));
    }
  }
  
  public ResultSet getCursor(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27945, this, new Object[] { Integer.valueOf(arg0) });
      return (ResultSet)postForAll(methodObject27945, this.proxyFactory.proxyForCreate((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject27945));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject27945, onErrorForAll(methodObject27945, e));
    }
  }
  
  public void updateInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28184, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject28184);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28184, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28076, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject28076, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject28076));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject28076, onErrorForAll(methodObject28076, e));
    }
  }
  
  public BFILE getBFILE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27950, this, new Object[] { arg0 });
      return (BFILE)postForAll(methodObject27950, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject27950, onErrorForAll(methodObject27950, e));
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28098, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28098);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28098, e);
    }
  }
  
  public void updateString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28211, this, new Object[] { arg0, arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject28211);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28211, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28088, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28088);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28088, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28090, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject28090);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28090, e);
    }
  }
  
  public void updateBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28166, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject28166);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28166, e);
    }
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28058, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject28058, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject28058));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject28058, onErrorForAll(methodObject28058, e));
    }
  }
  
  public void updateREF(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28022, this, new Object[] { arg0, arg1 });
      this.delegate.updateREF(arg0, arg1);
      postForAll(methodObject28022);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28022, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28042, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject28042, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject28042, onErrorForAll(methodObject28042, e))).longValue();
    }
  }
  
  public ResultSet getCursor(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27946, this, new Object[] { arg0 });
      return (ResultSet)postForAll(methodObject27946, this.proxyFactory.proxyForCreate((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject27946));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject27946, onErrorForAll(methodObject27946, e));
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28037, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject28037, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject28037, onErrorForAll(methodObject28037, e))).shortValue();
    }
  }
  
  public void updateINTERVALDS(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28008, this, new Object[] { arg0, arg1 });
      this.delegate.updateINTERVALDS(arg0, arg1);
      postForAll(methodObject28008);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28008, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28155, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      postForAll(methodObject28155);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28155, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28031, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject28031, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28031));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28031, onErrorForAll(methodObject28031, e));
    }
  }
  
  public CustomDatum getCustomDatum(String arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27960, this, new Object[] { arg0, arg1 });
      return (CustomDatum)postForAll(methodObject27960, (Object)this.delegate.getCustomDatum(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (CustomDatum)postForAll(methodObject27960, onErrorForAll(methodObject27960, e));
    }
  }
  
  public void updateRef(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28203, this, new Object[] { arg0, arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28203);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28203, e);
    }
  }
  
  public void updateNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28189, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28189);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28189, e);
    }
  }
  
  public void updateNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28188, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28188);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28188, e);
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28036, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject28036, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject28036, onErrorForAll(methodObject28036, e))).byteValue();
    }
  }
  
  public void updateCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27997, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCLOB(arg0, arg1);
      postForAll(methodObject27997);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27997, e);
    }
  }
  
  public void updateBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27989, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBfile(arg0, arg1);
      postForAll(methodObject27989);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27989, e);
    }
  }
  
  public void refreshRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28149, this, zeroLengthObjectArray);
      this.delegate.refreshRow();
      postForAll(methodObject28149);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28149, e);
    }
  }
  
  public INTERVALDS getINTERVALDS(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27979, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALDS)postForAll(methodObject27979, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject27979, onErrorForAll(methodObject27979, e));
    }
  }
  
  public void updateBFILE(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27992, this, new Object[] { arg0, arg1 });
      this.delegate.updateBFILE(arg0, arg1);
      postForAll(methodObject27992);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27992, e);
    }
  }
  
  public ROWID getROWID(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27973, this, new Object[] { Integer.valueOf(arg0) });
      return (ROWID)postForAll(methodObject27973, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject27973, onErrorForAll(methodObject27973, e));
    }
  }
  
  public void updateINTERVALYM(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28006, this, new Object[] { arg0, arg1 });
      this.delegate.updateINTERVALYM(arg0, arg1);
      postForAll(methodObject28006);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28006, e);
    }
  }
  
  public STRUCT getSTRUCT(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27975, this, new Object[] { Integer.valueOf(arg0) });
      return (STRUCT)postForAll(methodObject27975, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject27975, onErrorForAll(methodObject27975, e));
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28083, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28083);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28083, e);
    }
  }
  
  public void updateNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28190, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject28190);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28190, e);
    }
  }
  
  public Object getObject(int arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27941, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject27941, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject27941));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27941, onErrorForAll(methodObject27941, e));
    }
  }
  
  public void updateObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28201, this, new Object[] { arg0, arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28201);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28201, e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28104, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28104, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28104, onErrorForAll(methodObject28104, e))).booleanValue();
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28133, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject28133, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28133, onErrorForAll(methodObject28133, e));
    }
  }
  
  public void updateRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28079, this, zeroLengthObjectArray);
      this.delegate.updateRow();
      postForAll(methodObject28079);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28079, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28086, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28086);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28086, e);
    }
  }
  
  public InputStream getUnicodeStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28141, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28141, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28141, onErrorForAll(methodObject28141, e));
    }
  }
  
  public void updateARRAY(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27988, this, new Object[] { arg0, arg1 });
      this.delegate.updateARRAY(arg0, arg1);
      postForAll(methodObject27988);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27988, e);
    }
  }
  
  public boolean isAfterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28143, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28143, Boolean.valueOf(this.delegate.isAfterLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28143, onErrorForAll(methodObject28143, e))).booleanValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28216, this, new Object[] { arg0 });
      return postForAll(methodObject28216, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28216, onErrorForAll(methodObject28216, e));
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28113, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject28113);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28113, e);
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28101, this, new Object[] { arg0, arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject28101);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28101, e);
    }
  }
  
  public void updateDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28181, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject28181);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28181, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28046, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject28046, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject28046, onErrorForAll(methodObject28046, e))).doubleValue();
    }
  }
  
  public void updateOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28017, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateOracleObject(arg0, arg1);
      postForAll(methodObject28017);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28017, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28066, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject28066, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject28066));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject28066, onErrorForAll(methodObject28066, e));
    }
  }
  
  public void updateClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28174, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject28174);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28174, e);
    }
  }
  
  public ARRAY getARRAY(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27947, this, new Object[] { Integer.valueOf(arg0) });
      return (ARRAY)postForAll(methodObject27947, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject27947, onErrorForAll(methodObject27947, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28145, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28145, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28145, onErrorForAll(methodObject28145, e))).booleanValue();
    }
  }
  
  public Datum getOracleObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27944, this, new Object[] { arg0 });
      return (Datum)postForAll(methodObject27944, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject27944, onErrorForAll(methodObject27944, e));
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28032, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject28032, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28032));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28032, onErrorForAll(methodObject28032, e));
    }
  }
  
  public CLOB getCLOB(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27958, this, new Object[] { arg0 });
      return (CLOB)postForAll(methodObject27958, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject27958, onErrorForAll(methodObject27958, e));
    }
  }
  
  public TIMESTAMP getTIMESTAMP(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27982, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27982, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27982, onErrorForAll(methodObject27982, e));
    }
  }
  
  public void updateTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28011, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMPTZ(arg0, arg1);
      postForAll(methodObject28011);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28011, e);
    }
  }
  
  public void updateRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28205, this, new Object[] { arg0, arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28205);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28205, e);
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28163, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject28163);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28163, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28096, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject28096);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28096, e);
    }
  }
  
  public boolean last()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28102, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28102, Boolean.valueOf(this.delegate.last()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28102, Boolean.valueOf(onErrorForLast(methodObject28102, e)))).booleanValue();
    }
  }
  
  public boolean rowUpdated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28153, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28153, Boolean.valueOf(this.delegate.rowUpdated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28153, onErrorForAll(methodObject28153, e))).booleanValue();
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28100, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject28100);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28100, e);
    }
  }
  
  public void updateBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28158, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject28158);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28158, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28033, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28033, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28033, onErrorForAll(methodObject28033, e))).booleanValue();
    }
  }
  
  public void updateClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28173, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28173);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28173, e);
    }
  }
  
  public void updateBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27993, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBLOB(arg0, arg1);
      postForAll(methodObject27993);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27993, e);
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28072, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject28072, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject28072));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject28072, onErrorForAll(methodObject28072, e));
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28124, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28124, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28124, onErrorForAll(methodObject28124, e))).intValue();
    }
  }
  
  public boolean isFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28061, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28061, Boolean.valueOf(this.delegate.isFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28061, onErrorForAll(methodObject28061, e))).booleanValue();
    }
  }
  
  public void updateBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28170, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject28170);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28170, e);
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28135, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject28135, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28135, onErrorForAll(methodObject28135, e));
    }
  }
  
  public boolean previous()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28056, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28056, Boolean.valueOf(this.delegate.previous()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28056, onErrorForAll(methodObject28056, e))).booleanValue();
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28067, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject28067, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject28067));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject28067, onErrorForAll(methodObject28067, e));
    }
  }
  
  public void updateNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28193, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject28193);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28193, e);
    }
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28127, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject28127, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28127, onErrorForAll(methodObject28127, e));
    }
  }
  
  public InputStream getUnicodeStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28140, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28140, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28140, onErrorForAll(methodObject28140, e));
    }
  }
  
  public void updateShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28208, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject28208);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28208, e);
    }
  }
  
  public void updateLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28187, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject28187);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28187, e);
    }
  }
  
  public void updateClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28175, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject28175);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28175, e);
    }
  }
  
  public void updateSTRUCT(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28026, this, new Object[] { arg0, arg1 });
      this.delegate.updateSTRUCT(arg0, arg1);
      postForAll(methodObject28026);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28026, e);
    }
  }
  
  public void updateINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28005, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateINTERVALYM(arg0, arg1);
      postForAll(methodObject28005);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28005, e);
    }
  }
  
  public void updateNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28195, this, new Object[] { arg0, arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject28195);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28195, e);
    }
  }
  
  public boolean next()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28051, this, zeroLengthObjectArray);
      return postForNext(methodObject28051, this.delegate.next());
    }
    catch (SQLException e)
    {
      return postForNext(methodObject28051, ((Boolean)onErrorForAll(methodObject28051, e)).booleanValue());
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28043, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject28043, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject28043, onErrorForAll(methodObject28043, e))).floatValue();
    }
  }
  
  public int getType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28055, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28055, Integer.valueOf(this.delegate.getType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28055, onErrorForAll(methodObject28055, e))).intValue();
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28073, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject28073, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject28073));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject28073, onErrorForAll(methodObject28073, e));
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28084, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject28084);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28084, e);
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28059, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject28059, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28059, onErrorForAll(methodObject28059, e));
    }
  }
  
  public OPAQUE getOPAQUE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27968, this, new Object[] { arg0 });
      return (OPAQUE)postForAll(methodObject27968, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject27968, onErrorForAll(methodObject27968, e));
    }
  }
  
  public void updateTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28212, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject28212);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28212, e);
    }
  }
  
  public void updateObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28200, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject28200);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28200, e);
    }
  }
  
  public INTERVALYM getINTERVALYM(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27978, this, new Object[] { arg0 });
      return (INTERVALYM)postForAll(methodObject27978, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject27978, onErrorForAll(methodObject27978, e));
    }
  }
  
  public REF getREF(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27972, this, new Object[] { arg0 });
      return (REF)postForAll(methodObject27972, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject27972, onErrorForAll(methodObject27972, e));
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28047, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject28047, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject28047, onErrorForAll(methodObject28047, e));
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28052, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject28052, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject28052, onErrorForAll(methodObject28052, e));
    }
  }
  
  public InputStream getAsciiStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28116, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28116, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28116, onErrorForAll(methodObject28116, e));
    }
  }
  
  public void updateRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28019, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRAW(arg0, arg1);
      postForAll(methodObject28019);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28019, e);
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28070, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject28070, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject28070));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject28070, onErrorForAll(methodObject28070, e));
    }
  }
  
  public void updateShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28209, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject28209);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28209, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28119, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject28119, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28119, onErrorForAll(methodObject28119, e));
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28129, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject28129, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28129, onErrorForAll(methodObject28129, e));
    }
  }
  
  public void updateFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28182, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject28182);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28182, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28094, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28094);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28094, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28095, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28095);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28095, e);
    }
  }
  
  public void updateNUMBER(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28016, this, new Object[] { arg0, arg1 });
      this.delegate.updateNUMBER(arg0, arg1);
      postForAll(methodObject28016);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28016, e);
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28130, this, new Object[] { arg0 });
      return (String)postForAll(methodObject28130, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28130, onErrorForAll(methodObject28130, e));
    }
  }
  
  public void updateRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28202, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28202);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28202, e);
    }
  }
  
  public void beforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28111, this, zeroLengthObjectArray);
      this.delegate.beforeFirst();
      postForAll(methodObject28111);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28111, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28034, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject28034, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28034, onErrorForAll(methodObject28034, e))).booleanValue();
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28120, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject28120, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28120, onErrorForAll(methodObject28120, e));
    }
  }
  
  public void updateTIMESTAMPLTZ(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28014, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMPLTZ(arg0, arg1);
      postForAll(methodObject28014);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28014, e);
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28091, this, new Object[] { arg0, arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject28091);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28091, e);
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28139, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject28139, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28139, onErrorForAll(methodObject28139, e));
    }
  }
  
  public void updateBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28167, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject28167);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28167, e);
    }
  }
  
  public void updateBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28161, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28161);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28161, e);
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28069, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject28069, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject28069));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject28069, onErrorForAll(methodObject28069, e));
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28075, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject28075, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject28075));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject28075, onErrorForAll(methodObject28075, e));
    }
  }
  
  public Datum getOracleObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27943, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum)postForAll(methodObject27943, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject27943, onErrorForAll(methodObject27943, e));
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28048, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject28048, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject28048, onErrorForAll(methodObject28048, e));
    }
  }
  
  public TIMESTAMP getTIMESTAMP(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27981, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMP)postForAll(methodObject27981, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27981, onErrorForAll(methodObject27981, e));
    }
  }
  
  public boolean relative(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28150, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28150, Boolean.valueOf(this.delegate.relative(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28150, onErrorForAll(methodObject28150, e))).booleanValue();
    }
  }
  
  public void updateBfile(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27990, this, new Object[] { arg0, arg1 });
      this.delegate.updateBfile(arg0, arg1);
      postForAll(methodObject27990);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27990, e);
    }
  }
  
  public void deleteRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28077, this, zeroLengthObjectArray);
      this.delegate.deleteRow();
      postForAll(methodObject28077);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28077, e);
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28107, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject28107, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28107, onErrorForAll(methodObject28107, e));
    }
  }
  
  public INTERVALYM getINTERVALYM(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27977, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALYM)postForAll(methodObject27977, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject27977, onErrorForAll(methodObject27977, e));
    }
  }
  
  public void updateString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28210, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject28210);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28210, e);
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28081, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28081);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28081, e);
    }
  }
  
  public void updateRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28204, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28204);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28204, e);
    }
  }
  
  public void updateCLOB(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27998, this, new Object[] { arg0, arg1 });
      this.delegate.updateCLOB(arg0, arg1);
      postForAll(methodObject27998);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27998, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28199, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28199);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28199, e);
    }
  }
  
  public boolean first()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28115, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28115, Boolean.valueOf(this.delegate.first()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28115, onErrorForAll(methodObject28115, e))).booleanValue();
    }
  }
  
  public void updateLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28186, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject28186);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28186, e);
    }
  }
  
  public void updateCHAR(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27996, this, new Object[] { arg0, arg1 });
      this.delegate.updateCHAR(arg0, arg1);
      postForAll(methodObject27996);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27996, e);
    }
  }
  
  public void updateORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28001, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateORAData(arg0, arg1);
      postForAll(methodObject28001);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28001, e);
    }
  }
  
  public void updateSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28025, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateSTRUCT(arg0, arg1);
      postForAll(methodObject28025);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28025, e);
    }
  }
  
  public RAW getRAW(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27970, this, new Object[] { arg0 });
      return (RAW)postForAll(methodObject27970, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject27970, onErrorForAll(methodObject27970, e));
    }
  }
  
  public void updateCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27999, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCustomDatum(arg0, arg1);
      postForAll(methodObject27999);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27999, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28093, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28093);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28093, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28097, this, new Object[] { arg0, arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject28097);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28097, e);
    }
  }
  
  public void updateDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28003, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateDATE(arg0, arg1);
      postForAll(methodObject28003);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28003, e);
    }
  }
  
  public void updateTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28213, this, new Object[] { arg0, arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject28213);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28213, e);
    }
  }
  
  public InputStream getAsciiStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28117, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28117, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28117, onErrorForAll(methodObject28117, e));
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28085, this, new Object[] { arg0, arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject28085);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28085, e);
    }
  }
  
  public void updateByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28169, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject28169);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28169, e);
    }
  }
  
  public void moveToInsertRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28148, this, zeroLengthObjectArray);
      this.delegate.moveToInsertRow();
      postForAll(methodObject28148);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28148, e);
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28087, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28087);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28087, e);
    }
  }
  
  public void updateCustomDatum(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28000, this, new Object[] { arg0, arg1 });
      this.delegate.updateCustomDatum(arg0, arg1);
      postForAll(methodObject28000);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28000, e);
    }
  }
  
  public BFILE getBfile(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27951, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject27951, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject27951, onErrorForAll(methodObject27951, e));
    }
  }
  
  public void cancelRowUpdates()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28112, this, zeroLengthObjectArray);
      this.delegate.cancelRowUpdates();
      postForAll(methodObject28112);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28112, e);
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28134, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject28134, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28134, onErrorForAll(methodObject28134, e));
    }
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28126, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28126, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28126, onErrorForAll(methodObject28126, e))).intValue();
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28060, this, new Object[] { arg0 });
      return (String)postForAll(methodObject28060, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28060, onErrorForAll(methodObject28060, e));
    }
  }
  
  public DATE getDATE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27964, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27964, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27964, onErrorForAll(methodObject27964, e));
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28045, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject28045, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject28045, onErrorForAll(methodObject28045, e))).doubleValue();
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27986, this, new Object[] { arg0 });
      return (TIMESTAMPLTZ)postForAll(methodObject27986, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27986, onErrorForAll(methodObject27986, e));
    }
  }
  
  public void updateNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28191, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject28191);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28191, e);
    }
  }
  
  public void updateDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28178, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject28178);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28178, e);
    }
  }
  
  public void updateCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27995, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCHAR(arg0, arg1);
      postForAll(methodObject27995);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27995, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28080, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28080);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28080, e);
    }
  }
  
  public void updateArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28156, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28156);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28156, e);
    }
  }
  
  public void updateNull(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28197, this, new Object[] { arg0 });
      this.delegate.updateNull(arg0);
      postForAll(methodObject28197);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28197, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28041, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject28041, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject28041, onErrorForAll(methodObject28041, e))).longValue();
    }
  }
  
  public void updateTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28009, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMP(arg0, arg1);
      postForAll(methodObject28009);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28009, e);
    }
  }
  
  public ORAData getORAData(int arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27961, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (ORAData)postForAll(methodObject27961, (Object)this.delegate.getORAData(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (ORAData)postForAll(methodObject27961, onErrorForAll(methodObject27961, e));
    }
  }
  
  public InputStream getBinaryStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28105, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28105, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28105, onErrorForAll(methodObject28105, e));
    }
  }
  
  public void updateDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28180, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject28180);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28180, e);
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28164, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject28164);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28164, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28121, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject28121, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28121, onErrorForAll(methodObject28121, e));
    }
  }
  
  public Object getObject(String arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27942, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject27942, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject27942));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27942, onErrorForAll(methodObject27942, e));
    }
  }
  
  public void updateFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28183, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject28183);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28183, e);
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27984, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27984, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27984, onErrorForAll(methodObject27984, e));
    }
  }
  
  public void updateROWID(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28024, this, new Object[] { arg0, arg1 });
      this.delegate.updateROWID(arg0, arg1);
      postForAll(methodObject28024);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28024, e);
    }
  }
  
  public void updateORAData(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28002, this, new Object[] { arg0, arg1 });
      this.delegate.updateORAData(arg0, arg1);
      postForAll(methodObject28002);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28002, e);
    }
  }
  
  public void updateDATE(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28004, this, new Object[] { arg0, arg1 });
      this.delegate.updateDATE(arg0, arg1);
      postForAll(methodObject28004);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28004, e);
    }
  }
  
  public CLOB getCLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27957, this, new Object[] { Integer.valueOf(arg0) });
      return (CLOB)postForAll(methodObject27957, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject27957, onErrorForAll(methodObject27957, e));
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28108, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject28108, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28108, onErrorForAll(methodObject28108, e));
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28068, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject28068, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject28068));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject28068, onErrorForAll(methodObject28068, e));
    }
  }
  
  public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28028, this, new Object[] { arg0 });
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28028, (Object)this.delegate.getAuthorizationIndicator(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28028, onErrorForAll(methodObject28028, e));
    }
  }
  
  public BLOB getBLOB(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27954, this, new Object[] { arg0 });
      return (BLOB)postForAll(methodObject27954, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject27954, onErrorForAll(methodObject27954, e));
    }
  }
  
  public DATE getDATE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27963, this, new Object[] { Integer.valueOf(arg0) });
      return (DATE)postForAll(methodObject27963, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27963, onErrorForAll(methodObject27963, e));
    }
  }
  
  public INTERVALDS getINTERVALDS(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27980, this, new Object[] { arg0 });
      return (INTERVALDS)postForAll(methodObject27980, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject27980, onErrorForAll(methodObject27980, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28217, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject28217, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28217, onErrorForAll(methodObject28217, e))).booleanValue();
    }
  }
  
  public boolean rowDeleted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28151, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28151, Boolean.valueOf(this.delegate.rowDeleted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28151, onErrorForAll(methodObject28151, e))).booleanValue();
    }
  }
  
  public CustomDatum getCustomDatum(int arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27959, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (CustomDatum)postForAll(methodObject27959, (Object)this.delegate.getCustomDatum(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (CustomDatum)postForAll(methodObject27959, onErrorForAll(methodObject27959, e));
    }
  }
  
  public void afterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28110, this, zeroLengthObjectArray);
      this.delegate.afterLast();
      postForAll(methodObject28110);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28110, e);
    }
  }
  
  public void updateDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28179, this, new Object[] { arg0, arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject28179);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28179, e);
    }
  }
  
  public OPAQUE getOPAQUE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27967, this, new Object[] { Integer.valueOf(arg0) });
      return (OPAQUE)postForAll(methodObject27967, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject27967, onErrorForAll(methodObject27967, e));
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28154, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      postForAll(methodObject28154);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28154, e);
    }
  }
  
  public Statement getStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28103, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject28103, (Object)super.getStatement());
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject28103, onErrorForAll(methodObject28103, e));
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28162, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject28162);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28162, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28142, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject28142, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject28142, onErrorForAll(methodObject28142, e));
    }
  }
  
  public void updateArray(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28157, this, new Object[] { arg0, arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28157);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28157, e);
    }
  }
  
  public void updateClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28176, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject28176);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28176, e);
    }
  }
  
  public BFILE getBFILE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27949, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject27949, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject27949, onErrorForAll(methodObject27949, e));
    }
  }
  
  public ORAData getORAData(String arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27962, this, new Object[] { arg0, arg1 });
      return (ORAData)postForAll(methodObject27962, (Object)this.delegate.getORAData(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (ORAData)postForAll(methodObject27962, onErrorForAll(methodObject27962, e));
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28089, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28089);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28089, e);
    }
  }
  
  public void updateBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28159, this, new Object[] { arg0, arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject28159);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28159, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28082, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28082);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28082, e);
    }
  }
  
  public String getCursorName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28123, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject28123, (Object)this.delegate.getCursorName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28123, onErrorForAll(methodObject28123, e));
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28057, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject28057, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject28057));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject28057, onErrorForAll(methodObject28057, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28054, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject28054);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28054, e);
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28044, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject28044, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject28044, onErrorForAll(methodObject28044, e))).floatValue();
    }
  }
  
  public void updateTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28013, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMPLTZ(arg0, arg1);
      postForAll(methodObject28013);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28013, e);
    }
  }
  
  public void updateREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28021, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateREF(arg0, arg1);
      postForAll(methodObject28021);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28021, e);
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28128, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject28128, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28128, onErrorForAll(methodObject28128, e));
    }
  }
  
  public void updateBLOB(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27994, this, new Object[] { arg0, arg1 });
      this.delegate.updateBLOB(arg0, arg1);
      postForAll(methodObject27994);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27994, e);
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28064, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject28064, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28064, onErrorForAll(methodObject28064, e));
    }
  }
  
  public ROWID getROWID(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27974, this, new Object[] { arg0 });
      return (ROWID)postForAll(methodObject27974, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject27974, onErrorForAll(methodObject27974, e));
    }
  }
  
  public void updateINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28007, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateINTERVALDS(arg0, arg1);
      postForAll(methodObject28007);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28007, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28118, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject28118, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28118, onErrorForAll(methodObject28118, e));
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28165, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject28165);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28165, e);
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28029, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject28029, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject28029));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28029, onErrorForAll(methodObject28029, e));
    }
  }
  
  public void updateNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28015, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNUMBER(arg0, arg1);
      postForAll(methodObject28015);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28015, e);
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27985, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPLTZ)postForAll(methodObject27985, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27985, onErrorForAll(methodObject27985, e));
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28099, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28099);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28099, e);
    }
  }
  
  public void updateByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28168, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject28168);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28168, e);
    }
  }
  
  public void updateNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28194, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject28194);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28194, e);
    }
  }
  
  public void updateSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28206, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28206);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28206, e);
    }
  }
  
  public NUMBER getNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27965, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27965, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27965, onErrorForAll(methodObject27965, e));
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27983, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPTZ)postForAll(methodObject27983, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27983, onErrorForAll(methodObject27983, e));
    }
  }
  
  public void updateARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27987, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateARRAY(arg0, arg1);
      postForAll(methodObject27987);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27987, e);
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28053, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject28053, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject28053, onErrorForAll(methodObject28053, e));
    }
  }
  
  public void updateTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28214, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject28214);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28214, e);
    }
  }
  
  public void updateSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28207, this, new Object[] { arg0, arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28207);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28207, e);
    }
  }
  
  public void updateROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28023, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateROWID(arg0, arg1);
      postForAll(methodObject28023);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28023, e);
    }
  }
  
  public InputStream getBinaryStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28106, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28106, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28106, onErrorForAll(methodObject28106, e));
    }
  }
  
  public int getRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28131, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28131, Integer.valueOf(this.delegate.getRow()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28131, onErrorForAll(methodObject28131, e))).intValue();
    }
  }
  
  public STRUCT getSTRUCT(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27976, this, new Object[] { arg0 });
      return (STRUCT)postForAll(methodObject27976, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject27976, onErrorForAll(methodObject27976, e));
    }
  }
  
  public void updateInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28185, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject28185);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28185, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28138, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject28138, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28138, onErrorForAll(methodObject28138, e));
    }
  }
  
  public void insertRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28078, this, zeroLengthObjectArray);
      this.delegate.insertRow();
      postForAll(methodObject28078);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28078, e);
    }
  }
  
  public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28027, this, new Object[] { Integer.valueOf(arg0) });
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28027, (Object)this.delegate.getAuthorizationIndicator(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28027, onErrorForAll(methodObject28027, e));
    }
  }
  
  public boolean absolute(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28109, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28109, Boolean.valueOf(this.delegate.absolute(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28109, onErrorForAll(methodObject28109, e))).booleanValue();
    }
  }
  
  public void updateRAW(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28020, this, new Object[] { arg0, arg1 });
      this.delegate.updateRAW(arg0, arg1);
      postForAll(methodObject28020);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28020, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28125, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28125, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28125, onErrorForAll(methodObject28125, e))).intValue();
    }
  }
  
  public void updateOracleObject(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28018, this, new Object[] { arg0, arg1 });
      this.delegate.updateOracleObject(arg0, arg1);
      postForAll(methodObject28018);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28018, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28198, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject28198);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28198, e);
    }
  }
  
  public void updateBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27991, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBFILE(arg0, arg1);
      postForAll(methodObject27991);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27991, e);
    }
  }
  
  public void moveToCurrentRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28147, this, zeroLengthObjectArray);
      this.delegate.moveToCurrentRow();
      postForAll(methodObject28147);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28147, e);
    }
  }
  
  public void updateTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28215, this, new Object[] { arg0, arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject28215);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28215, e);
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28030, this, new Object[] { arg0 });
      return postForAll(methodObject28030, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject28030));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28030, onErrorForAll(methodObject28030, e));
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28040, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject28040, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28040, onErrorForAll(methodObject28040, e))).intValue();
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28065, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject28065, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28065, onErrorForAll(methodObject28065, e));
    }
  }
  
  public void updateTIMESTAMPTZ(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28012, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMPTZ(arg0, arg1);
      postForAll(methodObject28012);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28012, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28136, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject28136, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28136, onErrorForAll(methodObject28136, e));
    }
  }
  
  public BLOB getBLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27953, this, new Object[] { Integer.valueOf(arg0) });
      return (BLOB)postForAll(methodObject27953, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject27953, onErrorForAll(methodObject27953, e));
    }
  }
  
  public BFILE getBfile(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27952, this, new Object[] { arg0 });
      return (BFILE)postForAll(methodObject27952, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject27952, onErrorForAll(methodObject27952, e));
    }
  }
  
  public int findColumn(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28114, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject28114, Integer.valueOf(this.delegate.findColumn(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28114, onErrorForAll(methodObject28114, e))).intValue();
    }
  }
  
  public void updateNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28192, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject28192);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28192, e);
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28050, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject28050, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject28050));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject28050, onErrorForAll(methodObject28050, e));
    }
  }
  
  public void updateBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28160, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28160);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28160, e);
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28071, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject28071, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject28071));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject28071, onErrorForAll(methodObject28071, e));
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28074, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject28074, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject28074));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject28074, onErrorForAll(methodObject28074, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28039, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject28039, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28039, onErrorForAll(methodObject28039, e))).intValue();
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28092, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28092);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28092, e);
    }
  }
  
  public RAW getRAW(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27969, this, new Object[] { Integer.valueOf(arg0) });
      return (RAW)postForAll(methodObject27969, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject27969, onErrorForAll(methodObject27969, e));
    }
  }
  
  public boolean isBeforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28144, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28144, Boolean.valueOf(this.delegate.isBeforeFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28144, onErrorForAll(methodObject28144, e))).booleanValue();
    }
  }
  
  public int getConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28122, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28122, Integer.valueOf(this.delegate.getConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28122, onErrorForAll(methodObject28122, e))).intValue();
    }
  }
  
  public void updateBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28171, this, new Object[] { arg0, arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject28171);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28171, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28035, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject28035, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject28035, onErrorForAll(methodObject28035, e))).byteValue();
    }
  }
  
  public ARRAY getARRAY(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27948, this, new Object[] { arg0 });
      return (ARRAY)postForAll(methodObject27948, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject27948, onErrorForAll(methodObject27948, e));
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28049, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject28049, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject28049));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject28049, onErrorForAll(methodObject28049, e));
    }
  }
  
  public void updateClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28172, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28172);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28172, e);
    }
  }
  
  public CHAR getCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27955, this, new Object[] { Integer.valueOf(arg0) });
      return (CHAR)postForAll(methodObject27955, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject27955, onErrorForAll(methodObject27955, e));
    }
  }
  
  public boolean rowInserted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28152, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28152, Boolean.valueOf(this.delegate.rowInserted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28152, onErrorForAll(methodObject28152, e))).booleanValue();
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28062, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject28062, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28062, onErrorForAll(methodObject28062, e));
    }
  }
  
  public REF getREF(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27971, this, new Object[] { Integer.valueOf(arg0) });
      return (REF)postForAll(methodObject27971, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject27971, onErrorForAll(methodObject27971, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28063, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject28063, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28063, onErrorForAll(methodObject28063, e));
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28132, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject28132, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28132, onErrorForAll(methodObject28132, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28137, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject28137, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28137, onErrorForAll(methodObject28137, e));
    }
  }
  
  public void updateClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28177, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject28177);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28177, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28038, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject28038, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject28038, onErrorForAll(methodObject28038, e))).shortValue();
    }
  }
  
  public OracleResultSet _getDelegate_()
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
      methodObject27966 = OracleResultSet.class.getDeclaredMethod("getNUMBER", new Class[] { String.class });
      methodObject28196 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { Integer.TYPE });
      methodObject28010 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMP", new Class[] { String.class, TIMESTAMP.class });
      methodObject28146 = ResultSet.class.getDeclaredMethod("isLast", new Class[0]);
      methodObject27956 = OracleResultSet.class.getDeclaredMethod("getCHAR", new Class[] { String.class });
      methodObject27945 = OracleResultSet.class.getDeclaredMethod("getCursor", new Class[] { Integer.TYPE });
      methodObject28184 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject28076 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject27950 = OracleResultSet.class.getDeclaredMethod("getBFILE", new Class[] { String.class });
      methodObject28098 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28211 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { String.class, String.class });
      methodObject28088 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28090 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28166 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject28058 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject28022 = OracleResultSet.class.getDeclaredMethod("updateREF", new Class[] { String.class, REF.class });
      methodObject28042 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject27946 = OracleResultSet.class.getDeclaredMethod("getCursor", new Class[] { String.class });
      methodObject28037 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject28008 = OracleResultSet.class.getDeclaredMethod("updateINTERVALDS", new Class[] { String.class, INTERVALDS.class });
      methodObject28155 = ResultSet.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject28031 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject27960 = OracleResultSet.class.getDeclaredMethod("getCustomDatum", new Class[] { String.class, CustomDatumFactory.class });
      methodObject28203 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { String.class, Ref.class });
      methodObject28189 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, NClob.class });
      methodObject28188 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject28036 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject27997 = OracleResultSet.class.getDeclaredMethod("updateCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject27989 = OracleResultSet.class.getDeclaredMethod("updateBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject28149 = ResultSet.class.getDeclaredMethod("refreshRow", new Class[0]);
      methodObject27979 = OracleResultSet.class.getDeclaredMethod("getINTERVALDS", new Class[] { Integer.TYPE });
      methodObject27992 = OracleResultSet.class.getDeclaredMethod("updateBFILE", new Class[] { String.class, BFILE.class });
      methodObject27973 = OracleResultSet.class.getDeclaredMethod("getROWID", new Class[] { Integer.TYPE });
      methodObject28006 = OracleResultSet.class.getDeclaredMethod("updateINTERVALYM", new Class[] { String.class, INTERVALYM.class });
      methodObject27975 = OracleResultSet.class.getDeclaredMethod("getSTRUCT", new Class[] { Integer.TYPE });
      methodObject28083 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28190 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27941 = OracleResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, OracleDataFactory.class });
      methodObject28201 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class });
      methodObject28104 = ResultSet.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject28133 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject28079 = ResultSet.class.getDeclaredMethod("updateRow", new Class[0]);
      methodObject28086 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject28141 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { String.class });
      methodObject27988 = OracleResultSet.class.getDeclaredMethod("updateARRAY", new Class[] { String.class, ARRAY.class });
      methodObject28143 = ResultSet.class.getDeclaredMethod("isAfterLast", new Class[0]);
      methodObject28216 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject28113 = ResultSet.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject28101 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject28181 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { String.class, Double.TYPE });
      methodObject28046 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject28017 = OracleResultSet.class.getDeclaredMethod("updateOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject28066 = ResultSet.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject28174 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject27947 = OracleResultSet.class.getDeclaredMethod("getARRAY", new Class[] { Integer.TYPE });
      methodObject28145 = ResultSet.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject27944 = OracleResultSet.class.getDeclaredMethod("getOracleObject", new Class[] { String.class });
      methodObject28032 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject27958 = OracleResultSet.class.getDeclaredMethod("getCLOB", new Class[] { String.class });
      methodObject27982 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMP", new Class[] { String.class });
      methodObject28011 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject28205 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { String.class, RowId.class });
      methodObject28163 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28096 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject28102 = ResultSet.class.getDeclaredMethod("last", new Class[0]);
      methodObject28153 = ResultSet.class.getDeclaredMethod("rowUpdated", new Class[0]);
      methodObject28100 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject28158 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject28033 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject28173 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Clob.class });
      methodObject27993 = OracleResultSet.class.getDeclaredMethod("updateBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject28072 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject28124 = ResultSet.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject28061 = ResultSet.class.getDeclaredMethod("isFirst", new Class[0]);
      methodObject28170 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject28135 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject28056 = ResultSet.class.getDeclaredMethod("previous", new Class[0]);
      methodObject28067 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject28193 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class });
      methodObject28127 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject28140 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { Integer.TYPE });
      methodObject28208 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject28187 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { String.class, Long.TYPE });
      methodObject28175 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28026 = OracleResultSet.class.getDeclaredMethod("updateSTRUCT", new Class[] { String.class, STRUCT.class });
      methodObject28005 = OracleResultSet.class.getDeclaredMethod("updateINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject28195 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { String.class, String.class });
      methodObject28051 = ResultSet.class.getDeclaredMethod("next", new Class[0]);
      methodObject28043 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject28055 = ResultSet.class.getDeclaredMethod("getType", new Class[0]);
      methodObject28073 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject28084 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28059 = ResultSet.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject27968 = OracleResultSet.class.getDeclaredMethod("getOPAQUE", new Class[] { String.class });
      methodObject28212 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { Integer.TYPE, Time.class });
      methodObject28200 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject27978 = OracleResultSet.class.getDeclaredMethod("getINTERVALYM", new Class[] { String.class });
      methodObject27972 = OracleResultSet.class.getDeclaredMethod("getREF", new Class[] { String.class });
      methodObject28047 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject28052 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject28116 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { Integer.TYPE });
      methodObject28019 = OracleResultSet.class.getDeclaredMethod("updateRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject28070 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject28209 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { String.class, Short.TYPE });
      methodObject28119 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class, Integer.TYPE });
      methodObject28129 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject28182 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject28094 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28095 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28016 = OracleResultSet.class.getDeclaredMethod("updateNUMBER", new Class[] { String.class, NUMBER.class });
      methodObject28130 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject28202 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject28111 = ResultSet.class.getDeclaredMethod("beforeFirst", new Class[0]);
      methodObject28034 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject28120 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject28014 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPLTZ", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject28091 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject28139 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject28167 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject28161 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, Blob.class });
      methodObject28069 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject28075 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject27943 = OracleResultSet.class.getDeclaredMethod("getOracleObject", new Class[] { Integer.TYPE });
      methodObject28048 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject27981 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMP", new Class[] { Integer.TYPE });
      methodObject28150 = ResultSet.class.getDeclaredMethod("relative", new Class[] { Integer.TYPE });
      methodObject27990 = OracleResultSet.class.getDeclaredMethod("updateBfile", new Class[] { String.class, BFILE.class });
      methodObject28077 = ResultSet.class.getDeclaredMethod("deleteRow", new Class[0]);
      methodObject28107 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject27977 = OracleResultSet.class.getDeclaredMethod("getINTERVALYM", new Class[] { Integer.TYPE });
      methodObject28210 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { Integer.TYPE, String.class });
      methodObject28081 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject28204 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject27998 = OracleResultSet.class.getDeclaredMethod("updateCLOB", new Class[] { String.class, CLOB.class });
      methodObject28199 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class });
      methodObject28115 = ResultSet.class.getDeclaredMethod("first", new Class[0]);
      methodObject28186 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject27996 = OracleResultSet.class.getDeclaredMethod("updateCHAR", new Class[] { String.class, CHAR.class });
      methodObject28001 = OracleResultSet.class.getDeclaredMethod("updateORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject28025 = OracleResultSet.class.getDeclaredMethod("updateSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject27970 = OracleResultSet.class.getDeclaredMethod("getRAW", new Class[] { String.class });
      methodObject27999 = OracleResultSet.class.getDeclaredMethod("updateCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject28093 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject28097 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class });
      methodObject28003 = OracleResultSet.class.getDeclaredMethod("updateDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject28213 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { String.class, Time.class });
      methodObject28117 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { String.class });
      methodObject28085 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject28169 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { String.class, Byte.TYPE });
      methodObject28148 = ResultSet.class.getDeclaredMethod("moveToInsertRow", new Class[0]);
      methodObject28087 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject28000 = OracleResultSet.class.getDeclaredMethod("updateCustomDatum", new Class[] { String.class, CustomDatum.class });
      methodObject27951 = OracleResultSet.class.getDeclaredMethod("getBfile", new Class[] { Integer.TYPE });
      methodObject28112 = ResultSet.class.getDeclaredMethod("cancelRowUpdates", new Class[0]);
      methodObject28134 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject28126 = ResultSet.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject28060 = ResultSet.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject27964 = OracleResultSet.class.getDeclaredMethod("getDATE", new Class[] { String.class });
      methodObject28045 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject27986 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { String.class });
      methodObject28191 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28178 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { Integer.TYPE, Date.class });
      methodObject27995 = OracleResultSet.class.getDeclaredMethod("updateCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject28080 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject28156 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { Integer.TYPE, Array.class });
      methodObject28197 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { String.class });
      methodObject28041 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject28009 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject27961 = OracleResultSet.class.getDeclaredMethod("getORAData", new Class[] { Integer.TYPE, ORADataFactory.class });
      methodObject28105 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { Integer.TYPE });
      methodObject28180 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject28164 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28121 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject27942 = OracleResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class, OracleDataFactory.class });
      methodObject28183 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { String.class, Float.TYPE });
      methodObject27984 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { String.class });
      methodObject28024 = OracleResultSet.class.getDeclaredMethod("updateROWID", new Class[] { String.class, ROWID.class });
      methodObject28002 = OracleResultSet.class.getDeclaredMethod("updateORAData", new Class[] { String.class, ORAData.class });
      methodObject28004 = OracleResultSet.class.getDeclaredMethod("updateDATE", new Class[] { String.class, DATE.class });
      methodObject27957 = OracleResultSet.class.getDeclaredMethod("getCLOB", new Class[] { Integer.TYPE });
      methodObject28108 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject28068 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject28028 = OracleResultSet.class.getDeclaredMethod("getAuthorizationIndicator", new Class[] { String.class });
      methodObject27954 = OracleResultSet.class.getDeclaredMethod("getBLOB", new Class[] { String.class });
      methodObject27963 = OracleResultSet.class.getDeclaredMethod("getDATE", new Class[] { Integer.TYPE });
      methodObject27980 = OracleResultSet.class.getDeclaredMethod("getINTERVALDS", new Class[] { String.class });
      methodObject28217 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject28151 = ResultSet.class.getDeclaredMethod("rowDeleted", new Class[0]);
      methodObject27959 = OracleResultSet.class.getDeclaredMethod("getCustomDatum", new Class[] { Integer.TYPE, CustomDatumFactory.class });
      methodObject28110 = ResultSet.class.getDeclaredMethod("afterLast", new Class[0]);
      methodObject28179 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { String.class, Date.class });
      methodObject27967 = OracleResultSet.class.getDeclaredMethod("getOPAQUE", new Class[] { Integer.TYPE });
      methodObject28154 = ResultSet.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject28103 = ResultSet.class.getDeclaredMethod("getStatement", new Class[0]);
      methodObject28162 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28142 = ResultSet.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject28157 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { String.class, Array.class });
      methodObject28176 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject27949 = OracleResultSet.class.getDeclaredMethod("getBFILE", new Class[] { Integer.TYPE });
      methodObject27962 = OracleResultSet.class.getDeclaredMethod("getORAData", new Class[] { String.class, ORADataFactory.class });
      methodObject28089 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28159 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject28082 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28123 = ResultSet.class.getDeclaredMethod("getCursorName", new Class[0]);
      methodObject28057 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject28054 = ResultSet.class.getDeclaredMethod("close", new Class[0]);
      methodObject28044 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject28013 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject28021 = OracleResultSet.class.getDeclaredMethod("updateREF", new Class[] { Integer.TYPE, REF.class });
      methodObject28128 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject27994 = OracleResultSet.class.getDeclaredMethod("updateBLOB", new Class[] { String.class, BLOB.class });
      methodObject28064 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject27974 = OracleResultSet.class.getDeclaredMethod("getROWID", new Class[] { String.class });
      methodObject28007 = OracleResultSet.class.getDeclaredMethod("updateINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject28118 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject28165 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class });
      methodObject28029 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject28015 = OracleResultSet.class.getDeclaredMethod("updateNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject27985 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { Integer.TYPE });
      methodObject28099 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28168 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject28194 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { Integer.TYPE, String.class });
      methodObject28206 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject27965 = OracleResultSet.class.getDeclaredMethod("getNUMBER", new Class[] { Integer.TYPE });
      methodObject27983 = OracleResultSet.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { Integer.TYPE });
      methodObject27987 = OracleResultSet.class.getDeclaredMethod("updateARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject28053 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject28214 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject28207 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject28023 = OracleResultSet.class.getDeclaredMethod("updateROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject28106 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { String.class });
      methodObject28131 = ResultSet.class.getDeclaredMethod("getRow", new Class[0]);
      methodObject27976 = OracleResultSet.class.getDeclaredMethod("getSTRUCT", new Class[] { String.class });
      methodObject28185 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { String.class, Integer.TYPE });
      methodObject28138 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject28078 = ResultSet.class.getDeclaredMethod("insertRow", new Class[0]);
      methodObject28027 = OracleResultSet.class.getDeclaredMethod("getAuthorizationIndicator", new Class[] { Integer.TYPE });
      methodObject28109 = ResultSet.class.getDeclaredMethod("absolute", new Class[] { Integer.TYPE });
      methodObject28020 = OracleResultSet.class.getDeclaredMethod("updateRAW", new Class[] { String.class, RAW.class });
      methodObject28125 = ResultSet.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject28018 = OracleResultSet.class.getDeclaredMethod("updateOracleObject", new Class[] { String.class, Datum.class });
      methodObject28198 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject27991 = OracleResultSet.class.getDeclaredMethod("updateBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject28147 = ResultSet.class.getDeclaredMethod("moveToCurrentRow", new Class[0]);
      methodObject28215 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject28030 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject28040 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject28065 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject28012 = OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPTZ", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject28136 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject27953 = OracleResultSet.class.getDeclaredMethod("getBLOB", new Class[] { Integer.TYPE });
      methodObject27952 = OracleResultSet.class.getDeclaredMethod("getBfile", new Class[] { String.class });
      methodObject28114 = ResultSet.class.getDeclaredMethod("findColumn", new Class[] { String.class });
      methodObject28192 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject28050 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject28160 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject28071 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject28074 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject28039 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject28092 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject27969 = OracleResultSet.class.getDeclaredMethod("getRAW", new Class[] { Integer.TYPE });
      methodObject28144 = ResultSet.class.getDeclaredMethod("isBeforeFirst", new Class[0]);
      methodObject28122 = ResultSet.class.getDeclaredMethod("getConcurrency", new Class[0]);
      methodObject28171 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { String.class, byte[].class });
      methodObject28035 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject27948 = OracleResultSet.class.getDeclaredMethod("getARRAY", new Class[] { String.class });
      methodObject28049 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject28172 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject27955 = OracleResultSet.class.getDeclaredMethod("getCHAR", new Class[] { Integer.TYPE });
      methodObject28152 = ResultSet.class.getDeclaredMethod("rowInserted", new Class[0]);
      methodObject28062 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject27971 = OracleResultSet.class.getDeclaredMethod("getREF", new Class[] { Integer.TYPE });
      methodObject28063 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject28132 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject28137 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
      methodObject28177 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class });
      methodObject28038 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1OracleResultSet$$$Proxy(OracleResultSet paramOracleResultSet, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleResultSet;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1OracleResultSet$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */