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
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
import oracle.sql.ARRAY;
import oracle.sql.BFILE;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
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
import oracle.sql.StructDescriptor;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleCallableStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements oracle.jdbc.internal.OracleCallableStatement, _Proxy_
{
  private oracle.jdbc.internal.OracleCallableStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25409;
  private static Method methodObject25255;
  private static Method methodObject25095;
  private static Method methodObject25359;
  private static Method methodObject25082;
  private static Method methodObject25077;
  private static Method methodObject25478;
  private static Method methodObject25463;
  private static Method methodObject25169;
  private static Method methodObject25340;
  private static Method methodObject25362;
  private static Method methodObject25157;
  private static Method methodObject25195;
  private static Method methodObject25395;
  private static Method methodObject25130;
  private static Method methodObject25420;
  private static Method methodObject25338;
  private static Method methodObject25330;
  private static Method methodObject25070;
  private static Method methodObject25423;
  private static Method methodObject25114;
  private static Method methodObject25111;
  private static Method methodObject25112;
  private static Method methodObject25297;
  private static Method methodObject25252;
  private static Method methodObject25254;
  private static Method methodObject25085;
  private static Method methodObject25206;
  private static Method methodObject25216;
  private static Method methodObject25324;
  private static Method methodObject25452;
  private static Method methodObject25307;
  private static Method methodObject25373;
  private static Method methodObject25472;
  private static Method methodObject25333;
  private static Method methodObject25385;
  private static Method methodObject25308;
  private static Method methodObject25278;
  private static Method methodObject25173;
  private static Method methodObject25402;
  private static Method methodObject25089;
  private static Method methodObject25134;
  private static Method methodObject25469;
  private static Method methodObject25262;
  private static Method methodObject25078;
  private static Method methodObject25222;
  private static Method methodObject25159;
  private static Method methodObject25430;
  private static Method methodObject25084;
  private static Method methodObject25466;
  private static Method methodObject25417;
  private static Method methodObject25400;
  private static Method methodObject25376;
  private static Method methodObject25317;
  private static Method methodObject25063;
  private static Method methodObject25123;
  private static Method methodObject25309;
  private static Method methodObject25154;
  private static Method methodObject25491;
  private static Method methodObject25230;
  private static Method methodObject25276;
  private static Method methodObject25088;
  private static Method methodObject25196;
  private static Method methodObject25488;
  private static Method methodObject25260;
  private static Method methodObject25266;
  private static Method methodObject25412;
  private static Method methodObject25372;
  private static Method methodObject25202;
  private static Method methodObject25424;
  private static Method methodObject25182;
  private static Method methodObject25343;
  private static Method methodObject25458;
  private static Method methodObject25413;
  private static Method methodObject25449;
  private static Method methodObject25199;
  private static Method methodObject25053;
  private static Method methodObject25056;
  private static Method methodObject25364;
  private static Method methodObject25374;
  private static Method methodObject25332;
  private static Method methodObject25213;
  private static Method methodObject25438;
  private static Method methodObject25225;
  private static Method methodObject25161;
  private static Method methodObject25231;
  private static Method methodObject25315;
  private static Method methodObject25296;
  private static Method methodObject25204;
  private static Method methodObject25198;
  private static Method methodObject25142;
  private static Method methodObject25175;
  private static Method methodObject25094;
  private static Method methodObject25126;
  private static Method methodObject25298;
  private static Method methodObject25066;
  private static Method methodObject25189;
  private static Method methodObject25146;
  private static Method methodObject25394;
  private static Method methodObject25064;
  private static Method methodObject25245;
  private static Method methodObject25271;
  private static Method methodObject25383;
  private static Method methodObject25190;
  private static Method methodObject25228;
  private static Method methodObject25434;
  private static Method methodObject25461;
  private static Method methodObject25368;
  private static Method methodObject25122;
  private static Method methodObject25286;
  private static Method methodObject25099;
  private static Method methodObject25215;
  private static Method methodObject25150;
  private static Method methodObject25183;
  private static Method methodObject25151;
  private static Method methodObject25104;
  private static Method methodObject25397;
  private static Method methodObject25140;
  private static Method methodObject25319;
  private static Method methodObject25250;
  private static Method methodObject25273;
  private static Method methodObject25256;
  private static Method methodObject25265;
  private static Method methodObject25321;
  private static Method methodObject25322;
  private static Method methodObject25495;
  private static Method methodObject25102;
  private static Method methodObject25365;
  private static Method methodObject25093;
  private static Method methodObject25455;
  private static Method methodObject25253;
  private static Method methodObject25106;
  private static Method methodObject25483;
  private static Method methodObject25288;
  private static Method methodObject25103;
  private static Method methodObject25377;
  private static Method methodObject25191;
  private static Method methodObject25251;
  private static Method methodObject25371;
  private static Method methodObject25223;
  private static Method methodObject25345;
  private static Method methodObject25108;
  private static Method methodObject25257;
  private static Method methodObject25313;
  private static Method methodObject25378;
  private static Method methodObject25448;
  private static Method methodObject25351;
  private static Method methodObject25471;
  private static Method methodObject25398;
  private static Method methodObject25295;
  private static Method methodObject25367;
  private static Method methodObject25432;
  private static Method methodObject25346;
  private static Method methodObject25194;
  private static Method methodObject25171;
  private static Method methodObject25211;
  private static Method methodObject25185;
  private static Method methodObject25366;
  private static Method methodObject25207;
  private static Method methodObject25339;
  private static Method methodObject25242;
  private static Method methodObject25156;
  private static Method methodObject25244;
  private static Method methodObject25476;
  private static Method methodObject25363;
  private static Method methodObject25482;
  private static Method methodObject25426;
  private static Method methodObject25083;
  private static Method methodObject25487;
  private static Method methodObject25193;
  private static Method methodObject25316;
  private static Method methodObject25464;
  private static Method methodObject25107;
  private static Method methodObject25116;
  private static Method methodObject25179;
  private static Method methodObject25092;
  private static Method methodObject25137;
  private static Method methodObject25138;
  private static Method methodObject25494;
  private static Method methodObject25133;
  private static Method methodObject25415;
  private static Method methodObject25310;
  private static Method methodObject25484;
  private static Method methodObject25282;
  private static Method methodObject25167;
  private static Method methodObject25342;
  private static Method methodObject25473;
  private static Method methodObject25289;
  private static Method methodObject25100;
  private static Method methodObject25127;
  private static Method methodObject25188;
  private static Method methodObject25200;
  private static Method methodObject25248;
  private static Method methodObject25166;
  private static Method methodObject25292;
  private static Method methodObject25268;
  private static Method methodObject25337;
  private static Method methodObject25162;
  private static Method methodObject25176;
  private static Method methodObject25184;
  private static Method methodObject25110;
  private static Method methodObject25226;
  private static Method methodObject25165;
  private static Method methodObject25390;
  private static Method methodObject25436;
  private static Method methodObject25475;
  private static Method methodObject25205;
  private static Method methodObject25302;
  private static Method methodObject25379;
  private static Method methodObject25139;
  private static Method methodObject25444;
  private static Method methodObject25181;
  private static Method methodObject25493;
  private static Method methodObject25477;
  private static Method methodObject25360;
  private static Method methodObject25277;
  private static Method methodObject25069;
  private static Method methodObject25406;
  private static Method methodObject25164;
  private static Method methodObject25410;
  private static Method methodObject25361;
  private static Method methodObject25314;
  private static Method methodObject25437;
  private static Method methodObject25326;
  private static Method methodObject25163;
  private static Method methodObject25236;
  private static Method methodObject25327;
  private static Method methodObject25325;
  private static Method methodObject25234;
  private static Method methodObject25246;
  private static Method methodObject25375;
  private static Method methodObject25238;
  private static Method methodObject25348;
  private static Method methodObject25443;
  private static Method methodObject25301;
  private static Method methodObject25055;
  private static Method methodObject25304;
  private static Method methodObject25435;
  private static Method methodObject25076;
  private static Method methodObject25247;
  private static Method methodObject25269;
  private static Method methodObject25272;
  private static Method methodObject25445;
  private static Method methodObject25387;
  private static Method methodObject25380;
  private static Method methodObject25446;
  private static Method methodObject25131;
  private static Method methodObject25391;
  private static Method methodObject25404;
  private static Method methodObject25097;
  private static Method methodObject25312;
  private static Method methodObject25447;
  private static Method methodObject25416;
  private static Method methodObject25492;
  private static Method methodObject25451;
  private static Method methodObject25357;
  private static Method methodObject25465;
  private static Method methodObject25079;
  private static Method methodObject25160;
  private static Method methodObject25201;
  private static Method methodObject25453;
  private static Method methodObject25065;
  private static Method methodObject25381;
  private static Method methodObject25217;
  private static Method methodObject25490;
  private static Method methodObject25210;
  private static Method methodObject25486;
  private static Method methodObject25290;
  private static Method methodObject25429;
  private static Method methodObject25075;
  private static Method methodObject25347;
  private static Method methodObject25147;
  private static Method methodObject25057;
  private static Method methodObject25125;
  private static Method methodObject25356;
  private static Method methodObject25457;
  private static Method methodObject25170;
  private static Method methodObject25267;
  private static Method methodObject25174;
  private static Method methodObject25124;
  private static Method methodObject25384;
  private static Method methodObject25414;
  private static Method methodObject25456;
  private static Method methodObject25178;
  private static Method methodObject25129;
  private static Method methodObject25074;
  private static Method methodObject25450;
  private static Method methodObject25119;
  private static Method methodObject25212;
  private static Method methodObject25105;
  private static Method methodObject25144;
  private static Method methodObject25132;
  private static Method methodObject25080;
  private static Method methodObject25067;
  private static Method methodObject25081;
  private static Method methodObject25062;
  private static Method methodObject25136;
  private static Method methodObject25323;
  private static Method methodObject25386;
  private static Method methodObject25068;
  private static Method methodObject25421;
  private static Method methodObject25481;
  private static Method methodObject25479;
  private static Method methodObject25440;
  private static Method methodObject25208;
  private static Method methodObject25459;
  private static Method methodObject25264;
  private static Method methodObject25300;
  private static Method methodObject25353;
  private static Method methodObject25221;
  private static Method methodObject25467;
  private static Method methodObject25389;
  private static Method methodObject25115;
  private static Method methodObject25073;
  private static Method methodObject25258;
  private static Method methodObject25407;
  private static Method methodObject25113;
  private static Method methodObject25299;
  private static Method methodObject25350;
  private static Method methodObject25470;
  private static Method methodObject25474;
  private static Method methodObject25152;
  private static Method methodObject25059;
  private static Method methodObject25141;
  private static Method methodObject25052;
  private static Method methodObject25329;
  private static Method methodObject25121;
  private static Method methodObject25274;
  private static Method methodObject25427;
  private static Method methodObject25460;
  private static Method methodObject25229;
  private static Method methodObject25259;
  private static Method methodObject25462;
  private static Method methodObject25291;
  private static Method methodObject25392;
  private static Method methodObject25320;
  private static Method methodObject25172;
  private static Method methodObject25241;
  private static Method methodObject25349;
  private static Method methodObject25192;
  private static Method methodObject25281;
  private static Method methodObject25344;
  private static Method methodObject25399;
  private static Method methodObject25153;
  private static Method methodObject25403;
  private static Method methodObject25428;
  private static Method methodObject25168;
  private static Method methodObject25408;
  private static Method methodObject25061;
  private static Method methodObject25071;
  private static Method methodObject25209;
  private static Method methodObject25431;
  private static Method methodObject25128;
  private static Method methodObject25275;
  private static Method methodObject25335;
  private static Method methodObject25279;
  private static Method methodObject25233;
  private static Method methodObject25480;
  private static Method methodObject25401;
  private static Method methodObject25051;
  private static Method methodObject25090;
  private static Method methodObject25197;
  private static Method methodObject25354;
  private static Method methodObject25261;
  private static Method methodObject25334;
  private static Method methodObject25370;
  private static Method methodObject25143;
  private static Method methodObject25355;
  private static Method methodObject25388;
  private static Method methodObject25311;
  private static Method methodObject25135;
  private static Method methodObject25280;
  private static Method methodObject25098;
  private static Method methodObject25294;
  private static Method methodObject25305;
  private static Method methodObject25306;
  private static Method methodObject25243;
  private static Method methodObject25086;
  private static Method methodObject25118;
  private static Method methodObject25441;
  private static Method methodObject25180;
  private static Method methodObject25422;
  private static Method methodObject25155;
  private static Method methodObject25293;
  private static Method methodObject25149;
  private static Method methodObject25058;
  private static Method methodObject25284;
  private static Method methodObject25454;
  private static Method methodObject25433;
  private static Method methodObject25352;
  private static Method methodObject25393;
  private static Method methodObject25489;
  private static Method methodObject25091;
  private static Method methodObject25419;
  private static Method methodObject25117;
  private static Method methodObject25328;
  private static Method methodObject25270;
  private static Method methodObject25249;
  private static Method methodObject25239;
  private static Method methodObject25072;
  private static Method methodObject25227;
  private static Method methodObject25341;
  private static Method methodObject25418;
  private static Method methodObject25219;
  private static Method methodObject25285;
  private static Method methodObject25336;
  private static Method methodObject25224;
  private static Method methodObject25405;
  private static Method methodObject25145;
  private static Method methodObject25060;
  private static Method methodObject25187;
  private static Method methodObject25158;
  private static Method methodObject25218;
  private static Method methodObject25303;
  private static Method methodObject25177;
  private static Method methodObject25148;
  private static Method methodObject25087;
  private static Method methodObject25203;
  private static Method methodObject25468;
  private static Method methodObject25263;
  private static Method methodObject25411;
  private static Method methodObject25331;
  private static Method methodObject25109;
  private static Method methodObject25358;
  private static Method methodObject25287;
  private static Method methodObject25396;
  private static Method methodObject25237;
  private static Method methodObject25232;
  private static Method methodObject25382;
  private static Method methodObject25425;
  private static Method methodObject25240;
  private static Method methodObject25054;
  private static Method methodObject25283;
  private static Method methodObject25318;
  private static Method methodObject25235;
  private static Method methodObject25369;
  private static Method methodObject25101;
  private static Method methodObject25439;
  private static Method methodObject25214;
  private static Method methodObject25186;
  private static Method methodObject25220;
  
  public void setBlobAtName(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25409, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25409, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25255, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25255, e);
    }
  }
  
  public ResultSet getCursor(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25095, this, new Object[] { Integer.valueOf(arg0) });
      return (ResultSet)postForAll(methodObject25095, this.proxyFactory.proxyForCache((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject25095));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25095, onErrorForAll(methodObject25095, e));
    }
  }
  
  public void setCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25359, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25359, e);
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25082, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25082, e);
    }
  }
  
  public void setTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25077, this, new Object[] { arg0, arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25077, e);
    }
  }
  
  public void enterImplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25478, this, zeroLengthObjectArray);
      this.delegate.enterImplicitCache();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25478, e);
    }
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25463, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25463, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25169, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject25169, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25169, onErrorForAll(methodObject25169, e))).longValue();
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25340, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25340, e);
    }
  }
  
  public void setINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25362, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25362, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25157, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject25157, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject25157));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25157, onErrorForAll(methodObject25157, e));
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25195, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25195, e);
    }
  }
  
  public void setStringForClobAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25395, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25395, e);
    }
  }
  
  public void setBytesForBlob(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25130, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25130, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25420, this, new Object[] { arg0, arg1 });
      this.delegate.setDateAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25420, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25338, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25338, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25330, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25330, e);
    }
  }
  
  public void setString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25070, this, new Object[] { arg0, arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25070, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25423, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimeAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25423, e);
    }
  }
  
  public INTERVALDS getINTERVALDS(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25114, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALDS)postForAll(methodObject25114, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject25114, onErrorForAll(methodObject25114, e));
    }
  }
  
  public ROWID getROWID(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25111, this, new Object[] { Integer.valueOf(arg0) });
      return (ROWID)postForAll(methodObject25111, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject25111, onErrorForAll(methodObject25111, e));
    }
  }
  
  public STRUCT getSTRUCT(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25112, this, new Object[] { Integer.valueOf(arg0) });
      return (STRUCT)postForAll(methodObject25112, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject25112, onErrorForAll(methodObject25112, e));
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25297, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25297, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25297, onErrorForExecute(methodObject25297, e));
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25252, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25252, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25254, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25254, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25085, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25085, e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25206, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25206, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25206, onErrorForAll(methodObject25206, e))).booleanValue();
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25216, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject25216, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject25216, onErrorForAll(methodObject25216, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25324, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25324, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25324, onErrorForAll(methodObject25324, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25452, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25452, e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25307, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25307, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25307, onErrorForAll(methodObject25307, e))).intValue();
    }
  }
  
  public void setRefType(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25373, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25373, e);
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25472, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25472, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25333, this, new Object[] { arg0 });
      return postForAll(methodObject25333, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25333, onErrorForAll(methodObject25333, e));
    }
  }
  
  public void setIntAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25385, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setIntAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25385, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25308, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25308, e);
    }
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25278, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25278, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25173, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject25173, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject25173, onErrorForAll(methodObject25173, e))).doubleValue();
    }
  }
  
  public void setStructDescriptorAtName(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25402, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptorAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25402, e);
    }
  }
  
  public void setNull(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25089, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25089, e);
    }
  }
  
  public void setRAW(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25134, this, new Object[] { arg0, arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25134, e);
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25469, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25469, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25262, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject25262, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject25262));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject25262, onErrorForAll(methodObject25262, e));
    }
  }
  
  public void setTime(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25078, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25078, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25222, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25222, e);
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25159, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject25159, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject25159));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25159, onErrorForAll(methodObject25159, e));
    }
  }
  
  public void setTIMESTAMPLTZAtName(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25430, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25430, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25084, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25084, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25466, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25466, e);
    }
  }
  
  public void setBfileAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25417, this, new Object[] { arg0, arg1 });
      this.delegate.setBfileAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25417, e);
    }
  }
  
  public void setARRAYAtName(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25400, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAYAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25400, e);
    }
  }
  
  public void defineParameterTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25376, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25376, e);
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25317, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25317, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject25317));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25317, onErrorForAll(methodObject25317, e));
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25063, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25063, e);
    }
  }
  
  public Datum[] getOraclePlsqlIndexTable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25123, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum[])postForAll(methodObject25123, (Object)this.delegate.getOraclePlsqlIndexTable(arg0));
    }
    catch (SQLException e)
    {
      return (Datum[])postForAll(methodObject25123, onErrorForAll(methodObject25123, e));
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25309, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25309, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25309, onErrorForAll(methodObject25309, e))).intValue();
    }
  }
  
  public void setREF(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25154, this, new Object[] { arg0, arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25154, e);
    }
  }
  
  public boolean getFixedString()
  {
    super.preForAll(methodObject25491, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25491, Boolean.valueOf(this.delegate.getFixedString()))).booleanValue();
  }
  
  public void setClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25230, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25230, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25276, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25276, e);
    }
  }
  
  public void setClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25088, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25088, e);
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25196, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject25196, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject25196));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject25196, onErrorForAll(methodObject25196, e));
    }
  }
  
  public long getChecksum()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25488, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25488, Long.valueOf(this.delegate.getChecksum()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25488, onErrorForAll(methodObject25488, e))).longValue();
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25260, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25260, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25266, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25266, e);
    }
  }
  
  public void setCLOBAtName(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25412, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25412, e);
    }
  }
  
  public void setORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25372, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25372, e);
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25202, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject25202, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject25202));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject25202, onErrorForAll(methodObject25202, e));
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25424, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestampAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25424, e);
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25182, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject25182, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25182, onErrorForAll(methodObject25182, e));
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25343, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25343, e);
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25458, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25458, e);
    }
  }
  
  public void setClobAtName(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25413, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25413, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25449, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25449, e);
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25199, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject25199, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject25199));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject25199, onErrorForAll(methodObject25199, e));
    }
  }
  
  public BigDecimal getBigDecimal(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25053, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject25053, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject25053, onErrorForAll(methodObject25053, e));
    }
  }
  
  public void setBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25056, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25056, e);
    }
  }
  
  public void setTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25364, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25364, e);
    }
  }
  
  public void setREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25374, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25374, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25332, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25332, e);
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25213, this, new Object[] { arg0 });
      return (String)postForAll(methodObject25213, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25213, onErrorForAll(methodObject25213, e));
    }
  }
  
  public void setRefAtName(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25438, this, new Object[] { arg0, arg1 });
      this.delegate.setRefAtName(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25438, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25225, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25225, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25161, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject25161, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25161, onErrorForAll(methodObject25161, e))).booleanValue();
    }
  }
  
  public void setClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25231, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25231, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25315, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25315, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25296, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25296, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25296, onErrorForExecute(methodObject25296, e));
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25204, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject25204, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject25204));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject25204, onErrorForAll(methodObject25204, e));
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25198, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject25198, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject25198));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject25198, onErrorForAll(methodObject25198, e));
    }
  }
  
  public void setINTERVALYM(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25142, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25142, e);
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25175, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject25175, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject25175, onErrorForAll(methodObject25175, e));
    }
  }
  
  public Datum getOracleObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25094, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum)postForAll(methodObject25094, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject25094, onErrorForAll(methodObject25094, e));
    }
  }
  
  public void setBinaryFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25126, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25126, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25298, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25298, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25066, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25066, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25189, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25189, e);
    }
  }
  
  public void setTIMESTAMPLTZ(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25146, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25146, e);
    }
  }
  
  public void setStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25394, this, new Object[] { arg0, arg1 });
      this.delegate.setStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25394, e);
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25064, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25064, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25245, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25245, e);
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25271, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25271, e);
    }
  }
  
  public void setByteAtName(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25383, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByteAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25383, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25190, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25190, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25228, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25228, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25434, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25434, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25461, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25461, e);
    }
  }
  
  public void setOPAQUE(int arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25368, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25368, e);
    }
  }
  
  public Object getPlsqlIndexTable(int arg0, Class arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25122, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject25122, this.proxyFactory.proxyForCache(this.delegate.getPlsqlIndexTable(arg0, arg1), this, this.proxyCache, methodObject25122));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25122, onErrorForAll(methodObject25122, e));
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25286, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25286, e);
    }
  }
  
  public BFILE getBfile(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25099, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject25099, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject25099, onErrorForAll(methodObject25099, e));
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25215, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject25215, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject25215, onErrorForAll(methodObject25215, e));
    }
  }
  
  public void setSTRUCT(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25150, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25150, e);
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25183, this, new Object[] { arg0 });
      return (String)postForAll(methodObject25183, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25183, onErrorForAll(methodObject25183, e));
    }
  }
  
  public void setCustomDatum(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25151, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25151, e);
    }
  }
  
  public Object getORAData(int arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25104, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject25104, this.proxyFactory.proxyForCache(this.delegate.getORAData(arg0, arg1), this, this.proxyCache, methodObject25104));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25104, onErrorForAll(methodObject25104, e));
    }
  }
  
  public void setCursorAtName(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25397, this, new Object[] { arg0, arg1 });
      this.delegate.setCursorAtName(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25397, e);
    }
  }
  
  public void setBFILE(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25140, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25140, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25319, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25319, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25319, onErrorForAll(methodObject25319, e))).intValue();
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25250, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25250, e);
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25273, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25273, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25256, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25256, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25265, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25265, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25321, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25321, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25321, onErrorForAll(methodObject25321, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25322, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25322, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25322, onErrorForAll(methodObject25322, e))).intValue();
    }
  }
  
  public int getstatementType()
  {
    super.preForAll(methodObject25495, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25495, Integer.valueOf(this.delegate.getstatementType()))).intValue();
  }
  
  public CLOB getCLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25102, this, new Object[] { Integer.valueOf(arg0) });
      return (CLOB)postForAll(methodObject25102, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject25102, onErrorForAll(methodObject25102, e));
    }
  }
  
  public void setTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25365, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25365, e);
    }
  }
  
  public void setUnicodeStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25093, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25093, e);
    }
  }
  
  public void setRowIdAtName(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25455, this, new Object[] { arg0, arg1 });
      this.delegate.setRowIdAtName(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25455, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25253, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25253, e);
    }
  }
  
  public DATE getDATE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25106, this, new Object[] { Integer.valueOf(arg0) });
      return (DATE)postForAll(methodObject25106, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject25106, onErrorForAll(methodObject25106, e));
    }
  }
  
  public void exitExplicitCacheToClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25483, this, zeroLengthObjectArray);
      this.delegate.exitExplicitCacheToClose();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25483, e);
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25288, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25288, e);
    }
  }
  
  public Object getCustomDatum(int arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25103, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject25103, this.proxyFactory.proxyForCache(this.delegate.getCustomDatum(arg0, arg1), this, this.proxyCache, methodObject25103));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25103, onErrorForAll(methodObject25103, e));
    }
  }
  
  public void defineParameterTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25377, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25377, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25191, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25191, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25251, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25251, e);
    }
  }
  
  public void setCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25371, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25371, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25223, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25223, e);
    }
  }
  
  public void setBinaryFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25345, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25345, e);
    }
  }
  
  public OPAQUE getOPAQUE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25108, this, new Object[] { Integer.valueOf(arg0) });
      return (OPAQUE)postForAll(methodObject25108, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject25108, onErrorForAll(methodObject25108, e));
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25257, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25257, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25313, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25313, e);
    }
  }
  
  public void defineParameterType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25378, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25378, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25448, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25448, e);
    }
  }
  
  public void setFixedCHAR(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25351, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25351, e);
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject25471, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25471, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public void setROWIDAtName(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25398, this, new Object[] { arg0, arg1 });
      this.delegate.setROWIDAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25398, e);
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25295, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject25295, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25295, onErrorForExecute(methodObject25295, e));
    }
  }
  
  public void setARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25367, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25367, e);
    }
  }
  
  public void setCustomDatumAtName(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25432, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatumAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25432, e);
    }
  }
  
  public void setBinaryFloat(int arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25346, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25346, e);
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25194, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25194, e);
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25171, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject25171, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject25171, onErrorForAll(methodObject25171, e))).floatValue();
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25211, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject25211, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject25211, onErrorForAll(methodObject25211, e));
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25185, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject25185, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject25185, onErrorForAll(methodObject25185, e));
    }
  }
  
  public void setTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25366, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25366, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25207, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject25207, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject25207, onErrorForAll(methodObject25207, e));
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25339, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25339, e);
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25242, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25242, e);
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25156, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject25156, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject25156));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25156, onErrorForAll(methodObject25156, e));
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25244, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25244, e);
    }
  }
  
  public void setCheckBindTypes(boolean arg0)
  {
    super.preForAll(methodObject25476, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCheckBindTypes(arg0);
  }
  
  public void setINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25363, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25363, e);
    }
  }
  
  public void exitImplicitCacheToClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25482, this, zeroLengthObjectArray);
      this.delegate.exitImplicitCacheToClose();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25482, e);
    }
  }
  
  public void setINTERVALYMAtName(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25426, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYMAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25426, e);
    }
  }
  
  public void setObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25083, this, new Object[] { arg0, arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25083, e);
    }
  }
  
  public OracleStatement.SqlKind getSqlKind()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25487, this, zeroLengthObjectArray);
      return (OracleStatement.SqlKind)postForAll(methodObject25487, (Object)this.delegate.getSqlKind());
    }
    catch (SQLException e)
    {
      return (OracleStatement.SqlKind)postForAll(methodObject25487, onErrorForAll(methodObject25487, e));
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25193, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25193, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25316, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25316, e);
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25464, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25464, e);
    }
  }
  
  public NUMBER getNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25107, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject25107, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject25107, onErrorForAll(methodObject25107, e));
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25116, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPTZ)postForAll(methodObject25116, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject25116, onErrorForAll(methodObject25116, e));
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25179, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject25179, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject25179, onErrorForAll(methodObject25179, e));
    }
  }
  
  public void setRef(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25092, this, new Object[] { arg0, arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25092, e);
    }
  }
  
  public void setNUMBER(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25137, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25137, e);
    }
  }
  
  public void setBLOB(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25138, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25138, e);
    }
  }
  
  public int getcacheState()
  {
    super.preForAll(methodObject25494, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25494, Integer.valueOf(this.delegate.getcacheState()))).intValue();
  }
  
  public void setROWID(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25133, this, new Object[] { arg0, arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25133, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25415, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25415, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25310, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25310, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25310, onErrorForAll(methodObject25310, e))).intValue();
    }
  }
  
  public String getOriginalSql()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25484, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject25484, (Object)this.delegate.getOriginalSql());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25484, onErrorForAll(methodObject25484, e));
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25282, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25282, e);
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25167, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject25167, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25167, onErrorForAll(methodObject25167, e))).intValue();
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25342, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25342, e);
    }
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25473, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject25473, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject25473, onErrorForAll(methodObject25473, e));
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25289, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25289, e);
    }
  }
  
  public BLOB getBLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25100, this, new Object[] { Integer.valueOf(arg0) });
      return (BLOB)postForAll(methodObject25100, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject25100, onErrorForAll(methodObject25100, e));
    }
  }
  
  public void setBinaryDouble(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25127, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25127, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25188, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25188, e);
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25200, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject25200, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject25200));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject25200, onErrorForAll(methodObject25200, e));
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25248, this, zeroLengthObjectArray);
      return postForExecute(methodObject25248, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25248, onErrorForExecute(methodObject25248, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25166, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject25166, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25166, onErrorForAll(methodObject25166, e))).intValue();
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25292, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25292, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25268, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25268, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25337, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25337, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25162, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject25162, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject25162, onErrorForAll(methodObject25162, e))).byteValue();
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25176, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject25176, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject25176));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject25176, onErrorForAll(methodObject25176, e));
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25184, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject25184, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject25184, onErrorForAll(methodObject25184, e));
    }
  }
  
  public REF getREF(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25110, this, new Object[] { Integer.valueOf(arg0) });
      return (REF)postForAll(methodObject25110, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject25110, onErrorForAll(methodObject25110, e));
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25226, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25226, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25165, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject25165, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject25165, onErrorForAll(methodObject25165, e))).shortValue();
    }
  }
  
  public void setBinaryFloatAtName(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25390, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25390, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25436, this, new Object[] { arg0, arg1 });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25436, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25475, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25475, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25205, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject25205, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject25205));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject25205, onErrorForAll(methodObject25205, e));
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25302, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25302, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25302, ((Integer)onErrorForAll(methodObject25302, e)).intValue());
    }
  }
  
  public int getExecuteBatch()
  {
    super.preForAll(methodObject25379, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25379, Integer.valueOf(this.delegate.getExecuteBatch()))).intValue();
  }
  
  public void setCLOB(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25139, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25139, e);
    }
  }
  
  public void setFormOfUse(int arg0, short arg1)
  {
    super.preForAll(methodObject25444, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
    this.delegate.setFormOfUse(arg0, arg1);
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25181, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject25181, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject25181));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject25181, onErrorForAll(methodObject25181, e));
    }
  }
  
  public boolean getserverCursor()
  {
    super.preForAll(methodObject25493, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25493, Boolean.valueOf(this.delegate.getserverCursor()))).booleanValue();
  }
  
  public void setInternalBytes(int arg0, byte[] arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25477, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setInternalBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25477, e);
    }
  }
  
  public void setBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25360, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25360, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25277, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25277, e);
    }
  }
  
  public void setBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25069, this, new Object[] { arg0, arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25069, e);
    }
  }
  
  public void setDATEAtName(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25406, this, new Object[] { arg0, arg1 });
      this.delegate.setDATEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25406, e);
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25164, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject25164, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject25164, onErrorForAll(methodObject25164, e))).shortValue();
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25410, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25410, e);
    }
  }
  
  public void setBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25361, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25361, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25314, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25314, e);
    }
  }
  
  public void setRefTypeAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25437, this, new Object[] { arg0, arg1 });
      this.delegate.setRefTypeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25437, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25326, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25326, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25326, onErrorForAll(methodObject25326, e))).booleanValue();
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25163, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject25163, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject25163, onErrorForAll(methodObject25163, e))).byteValue();
    }
  }
  
  public void setRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25236, this, new Object[] { arg0, arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25236, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25327, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25327, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25325, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25325, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25325, onErrorForAll(methodObject25325, e))).intValue();
    }
  }
  
  public void setNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25234, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25234, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25246, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25246, e);
    }
  }
  
  public void setOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25375, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25375, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25238, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25238, e);
    }
  }
  
  public void setBinaryDouble(int arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25348, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25348, e);
    }
  }
  
  public void setPlsqlIndexTable(int arg0, Object arg1, int arg2, int arg3, int arg4, int arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25443, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3), Integer.valueOf(arg4), Integer.valueOf(arg5) });
      this.delegate.setPlsqlIndexTable(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3, arg4, arg5);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25443, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25301, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject25301, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25301, ((Integer)onErrorForAll(methodObject25301, e)).intValue());
    }
  }
  
  public Object getObject(int arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25055, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject25055, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject25055));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25055, onErrorForAll(methodObject25055, e));
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject25304, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject25304, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject25304, onErrorForAll(methodObject25304, e));
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25435, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25435, e);
    }
  }
  
  public InputStream getUnicodeStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25076, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject25076, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25076, onErrorForAll(methodObject25076, e));
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25247, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25247, e);
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25269, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25269, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25272, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25272, e);
    }
  }
  
  public void setDisableStmtCaching(boolean arg0)
  {
    super.preForAll(methodObject25445, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setDisableStmtCaching(arg0);
  }
  
  public void setFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25387, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25387, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25380, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNullAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25380, e);
    }
  }
  
  public OracleParameterMetaData OracleGetParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25446, this, zeroLengthObjectArray);
      return (OracleParameterMetaData)postForAll(methodObject25446, this.proxyFactory.proxyForCache((Object)this.delegate.OracleGetParameterMetaData(), this, this.proxyCache, methodObject25446));
    }
    catch (SQLException e)
    {
      return (OracleParameterMetaData)postForAll(methodObject25446, onErrorForAll(methodObject25446, e));
    }
  }
  
  public void setFixedCHAR(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25131, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25131, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25391, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25391, e);
    }
  }
  
  public void setRAWAtName(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25404, this, new Object[] { arg0, arg1 });
      this.delegate.setRAWAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25404, e);
    }
  }
  
  public ARRAY getARRAY(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25097, this, new Object[] { Integer.valueOf(arg0) });
      return (ARRAY)postForAll(methodObject25097, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject25097, onErrorForAll(methodObject25097, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25312, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25312, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25312, onErrorForAll(methodObject25312, e))).booleanValue();
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25447, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerReturnParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25447, e);
    }
  }
  
  public void setBFILEAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25416, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25416, e);
    }
  }
  
  public int sendBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25492, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25492, Integer.valueOf(this.delegate.sendBatch()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25492, onErrorForAll(methodObject25492, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25451, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25451, e);
    }
  }
  
  public void setNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25357, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25357, e);
    }
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject25465, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25465, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public void setDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25079, this, new Object[] { arg0, arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25079, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25160, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25160, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25160, onErrorForAll(methodObject25160, e))).booleanValue();
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25201, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject25201, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject25201));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject25201, onErrorForAll(methodObject25201, e));
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25453, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25453, e);
    }
  }
  
  public void setURL(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25065, this, new Object[] { arg0, arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25065, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25381, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNullAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25381, e);
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25217, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject25217, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject25217, onErrorForAll(methodObject25217, e));
    }
  }
  
  public void setFixedString(boolean arg0)
  {
    super.preForAll(methodObject25490, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setFixedString(arg0);
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25210, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject25210, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject25210, onErrorForAll(methodObject25210, e));
    }
  }
  
  public void setExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25486, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setExecuteBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25486, e);
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25290, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25290, e);
    }
  }
  
  public void setTIMESTAMPTZAtName(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25429, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25429, e);
    }
  }
  
  public InputStream getUnicodeStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25075, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject25075, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25075, onErrorForAll(methodObject25075, e));
    }
  }
  
  public void setBinaryDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25347, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25347, e);
    }
  }
  
  public void setARRAY(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25147, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25147, e);
    }
  }
  
  public void setByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25057, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25057, e);
    }
  }
  
  public void setBinaryFloat(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25125, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25125, e);
    }
  }
  
  public void setDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25356, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25356, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25457, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25457, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25457, onErrorForAll(methodObject25457, e))).booleanValue();
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25170, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject25170, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject25170, onErrorForAll(methodObject25170, e))).floatValue();
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25267, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25267, e);
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25174, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject25174, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject25174, onErrorForAll(methodObject25174, e));
    }
  }
  
  public void registerIndexTableOutParameter(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25124, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerIndexTableOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25124, e);
    }
  }
  
  public void setShortAtName(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25384, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShortAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25384, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25414, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25414, e);
    }
  }
  
  public void setSQLXMLAtName(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25456, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXMLAtName(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25456, e);
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25178, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject25178, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject25178, onErrorForAll(methodObject25178, e));
    }
  }
  
  public void setStringForClob(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25129, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25129, e);
    }
  }
  
  public InputStream getAsciiStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25074, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject25074, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25074, onErrorForAll(methodObject25074, e));
    }
  }
  
  public ResultSet getReturnResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25450, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25450, this.proxyFactory.proxyForCache((Object)this.delegate.getReturnResultSet(), this, this.proxyCache, methodObject25450));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25450, onErrorForAll(methodObject25450, e));
    }
  }
  
  public void registerOutParameterChars(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25119, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameterChars(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25119, e);
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25212, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject25212, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25212, onErrorForAll(methodObject25212, e));
    }
  }
  
  public Object getAnyDataEmbeddedObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25105, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject25105, this.proxyFactory.proxyForCache(this.delegate.getAnyDataEmbeddedObject(arg0), this, this.proxyCache, methodObject25105));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25105, onErrorForAll(methodObject25105, e));
    }
  }
  
  public void setTIMESTAMP(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25144, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25144, e);
    }
  }
  
  public void setCursor(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25132, this, new Object[] { arg0, arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25132, e);
    }
  }
  
  public void setDate(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25080, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25080, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25067, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25067, e);
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25081, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25081, e);
    }
  }
  
  public void setShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25062, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25062, e);
    }
  }
  
  public void setDATE(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25136, this, new Object[] { arg0, arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25136, e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25323, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25323, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25323, onErrorForAll(methodObject25323, e))).intValue();
    }
  }
  
  public void setLongAtName(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25386, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLongAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25386, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25068, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25068, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25421, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDateAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25421, e);
    }
  }
  
  public void exitExplicitCacheToActive()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25481, this, zeroLengthObjectArray);
      this.delegate.exitExplicitCacheToActive();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25481, e);
    }
  }
  
  public void enterExplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25479, this, zeroLengthObjectArray);
      this.delegate.enterExplicitCache();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25479, e);
    }
  }
  
  public void setOracleObjectAtName(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25440, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObjectAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25440, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25208, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject25208, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject25208, onErrorForAll(methodObject25208, e));
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25459, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25459, e);
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25264, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25264, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25300, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject25300, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25300, ((Integer)onErrorForAll(methodObject25300, e)).intValue());
    }
  }
  
  public void setROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25353, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25353, e);
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25221, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject25221, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject25221, onErrorForAll(methodObject25221, e));
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25467, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25467, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25389, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25389, e);
    }
  }
  
  public TIMESTAMP getTIMESTAMP(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25115, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMP)postForAll(methodObject25115, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject25115, onErrorForAll(methodObject25115, e));
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25073, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject25073, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject25073, onErrorForAll(methodObject25073, e));
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25258, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25258, e);
    }
  }
  
  public void setNUMBERAtName(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25407, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBERAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25407, e);
    }
  }
  
  public INTERVALYM getINTERVALYM(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25113, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALYM)postForAll(methodObject25113, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject25113, onErrorForAll(methodObject25113, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25299, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25299, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject25299));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25299, onErrorForAll(methodObject25299, e));
    }
  }
  
  public void setBytesForBlob(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25350, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25350, e);
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25470, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25470, e);
    }
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25474, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25474, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25474, onErrorForAll(methodObject25474, e))).longValue();
    }
  }
  
  public void setORAData(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25152, this, new Object[] { arg0, arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25152, e);
    }
  }
  
  public void setFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25059, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25059, e);
    }
  }
  
  public void setBfile(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25141, this, new Object[] { arg0, arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25141, e);
    }
  }
  
  public InputStream getAsciiStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25052, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject25052, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25052, onErrorForAll(methodObject25052, e));
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25329, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25329, e);
    }
  }
  
  public Object getPlsqlIndexTable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25121, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject25121, this.proxyFactory.proxyForCache(this.delegate.getPlsqlIndexTable(arg0), this, this.proxyCache, methodObject25121));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25121, onErrorForAll(methodObject25121, e));
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25274, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25274, e);
    }
  }
  
  public void setINTERVALDSAtName(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25427, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDSAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25427, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25460, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25460, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25229, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25229, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25259, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25259, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25462, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25462, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25291, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25291, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25392, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25392, e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25320, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25320, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25320, onErrorForAll(methodObject25320, e))).booleanValue();
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25172, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject25172, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject25172, onErrorForAll(methodObject25172, e))).doubleValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25241, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25241, e);
    }
  }
  
  public void setStringForClob(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25349, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25349, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25192, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25192, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25281, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25281, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25344, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25344, e);
    }
  }
  
  public void setArrayAtName(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25399, this, new Object[] { arg0, arg1 });
      this.delegate.setArrayAtName(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25399, e);
    }
  }
  
  public void setRefType(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25153, this, new Object[] { arg0, arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25153, e);
    }
  }
  
  public void setSTRUCTAtName(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25403, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCTAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25403, e);
    }
  }
  
  public void setTIMESTAMPAtName(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25428, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25428, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25168, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject25168, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25168, onErrorForAll(methodObject25168, e))).longValue();
    }
  }
  
  public void setBLOBAtName(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25408, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25408, e);
    }
  }
  
  public void setLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25061, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25061, e);
    }
  }
  
  public InputStream getBinaryStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25071, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject25071, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25071, onErrorForAll(methodObject25071, e));
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25209, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject25209, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject25209, onErrorForAll(methodObject25209, e));
    }
  }
  
  public void setUnicodeStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25431, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25431, e);
    }
  }
  
  public void setBinaryDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25128, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25128, e);
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25275, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25275, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25335, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25335, e);
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25279, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25279, e);
    }
  }
  
  public void setNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25233, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25233, e);
    }
  }
  
  public void exitImplicitCacheToActive()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25480, this, zeroLengthObjectArray);
      this.delegate.exitImplicitCacheToActive();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25480, e);
    }
  }
  
  public void setOPAQUEAtName(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25401, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25401, e);
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25051, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject25051, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject25051, onErrorForAll(methodObject25051, e));
    }
  }
  
  public void setNull(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25090, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25090, e);
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25197, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject25197, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject25197));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject25197, onErrorForAll(methodObject25197, e));
    }
  }
  
  public void setRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25354, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25354, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25261, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject25261, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject25261));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25261, (ResultSet)onErrorForAll(methodObject25261, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25334, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject25334, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25334, onErrorForAll(methodObject25334, e))).booleanValue();
    }
  }
  
  public void setSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25370, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25370, e);
    }
  }
  
  public void setINTERVALDS(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25143, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25143, e);
    }
  }
  
  public void setCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25355, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25355, e);
    }
  }
  
  public void setDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25388, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25388, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25311, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject25311, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject25311, onErrorForAll(methodObject25311, e));
    }
  }
  
  public void setCHAR(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25135, this, new Object[] { arg0, arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25135, e);
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25280, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25280, e);
    }
  }
  
  public BFILE getBFILE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25098, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject25098, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject25098, onErrorForAll(methodObject25098, e));
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25294, this, new Object[] { arg0 });
      return postForExecute(methodObject25294, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25294, onErrorForExecute(methodObject25294, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25305, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject25305, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject25305));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25305, (ResultSet)onErrorForAll(methodObject25305, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25306, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject25306, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject25306, onErrorForAll(methodObject25306, e));
    }
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25243, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25243, e);
    }
  }
  
  public void setBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25086, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25086, e);
    }
  }
  
  public void registerOutParameterBytes(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25118, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameterBytes(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25118, e);
    }
  }
  
  public void setURLAtName(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25441, this, new Object[] { arg0, arg1 });
      this.delegate.setURLAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25441, e);
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25180, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject25180, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject25180));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject25180, onErrorForAll(methodObject25180, e));
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25422, this, new Object[] { arg0, arg1 });
      this.delegate.setTimeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25422, e);
    }
  }
  
  public void setOracleObject(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25155, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25155, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25293, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject25293);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject25293, e);
    }
  }
  
  public void setStructDescriptor(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25149, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25149, e);
    }
  }
  
  public void setDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25058, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25058, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25284, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25284, e);
    }
  }
  
  public void setNStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25454, this, new Object[] { arg0, arg1 });
      this.delegate.setNStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25454, e);
    }
  }
  
  public void setORADataAtName(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25433, this, new Object[] { arg0, arg1 });
      this.delegate.setORADataAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25433, e);
    }
  }
  
  public void setCursor(int arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25352, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25352, e);
    }
  }
  
  public void setBigDecimalAtName(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25393, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimalAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25393, e);
    }
  }
  
  public void setSnapshotSCN(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25489, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setSnapshotSCN(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25489, e);
    }
  }
  
  public void setArray(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25091, this, new Object[] { arg0, arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25091, e);
    }
  }
  
  public void setBytesForBlobAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25419, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25419, e);
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25117, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPLTZ)postForAll(methodObject25117, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject25117, onErrorForAll(methodObject25117, e));
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25328, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25328, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25270, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25270, e);
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25249, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject25249, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25249, ((Integer)onErrorForAll(methodObject25249, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25239, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25239, e);
    }
  }
  
  public InputStream getBinaryStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25072, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject25072, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject25072, onErrorForAll(methodObject25072, e));
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25227, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25227, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25341, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25341, e);
    }
  }
  
  public void setBytesAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25418, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25418, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25219, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject25219, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject25219, onErrorForAll(methodObject25219, e));
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25285, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25285, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25336, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25336, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25224, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25224, e);
    }
  }
  
  public void setCHARAtName(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25405, this, new Object[] { arg0, arg1 });
      this.delegate.setCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25405, e);
    }
  }
  
  public void setTIMESTAMPTZ(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25145, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25145, e);
    }
  }
  
  public void setInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25060, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25060, e);
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25187, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject25187, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject25187, onErrorForAll(methodObject25187, e));
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25158, this, new Object[] { arg0 });
      return postForAll(methodObject25158, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject25158));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25158, onErrorForAll(methodObject25158, e));
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25218, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject25218, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject25218, onErrorForAll(methodObject25218, e));
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25303, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25303, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25303, ((Integer)onErrorForAll(methodObject25303, e)).intValue());
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25177, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject25177, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject25177));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject25177, onErrorForAll(methodObject25177, e));
    }
  }
  
  public void setOPAQUE(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25148, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25148, e);
    }
  }
  
  public void setBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25087, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25087, e);
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25203, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject25203, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject25203));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject25203, onErrorForAll(methodObject25203, e));
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject25468, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25468, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25263, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject25263, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject25263));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject25263, onErrorForAll(methodObject25263, e));
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25411, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25411, e);
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25331, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25331, e);
    }
  }
  
  public RAW getRAW(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25109, this, new Object[] { Integer.valueOf(arg0) });
      return (RAW)postForAll(methodObject25109, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject25109, onErrorForAll(methodObject25109, e));
    }
  }
  
  public void setBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25358, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25358, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25287, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25287, e);
    }
  }
  
  public void setFixedCHARAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25396, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25396, e);
    }
  }
  
  public void setSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25237, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25237, e);
    }
  }
  
  public void setNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25232, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25232, e);
    }
  }
  
  public void setBooleanAtName(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25382, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBooleanAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25382, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25425, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestampAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25425, e);
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25240, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25240, e);
    }
  }
  
  public byte[] privateGetBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25054, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject25054, (Object)this.delegate.privateGetBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject25054, onErrorForAll(methodObject25054, e));
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25283, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25283, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25318, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25318, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25318, onErrorForAll(methodObject25318, e))).intValue();
    }
  }
  
  public void setNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25235, this, new Object[] { arg0, arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25235, e);
    }
  }
  
  public void setStructDescriptor(int arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25369, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25369, e);
    }
  }
  
  public CHAR getCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25101, this, new Object[] { Integer.valueOf(arg0) });
      return (CHAR)postForAll(methodObject25101, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject25101, onErrorForAll(methodObject25101, e));
    }
  }
  
  public void setREFAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25439, this, new Object[] { arg0, arg1 });
      this.delegate.setREFAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25439, e);
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25214, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject25214, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject25214, onErrorForAll(methodObject25214, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25186, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject25186, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject25186, onErrorForAll(methodObject25186, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25220, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject25220, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject25220, onErrorForAll(methodObject25220, e));
    }
  }
  
  public oracle.jdbc.internal.OracleCallableStatement _getDelegate_()
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
      methodObject25409 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, Blob.class });
      methodObject25255 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25095 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getCursor", new Class[] { Integer.TYPE });
      methodObject25359 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject25082 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject25077 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class });
      methodObject25478 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("enterImplicitCache", new Class[0]);
      methodObject25463 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25169 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject25340 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject25362 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject25157 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject25195 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject25395 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringForClobAtName", new Class[] { String.class, String.class });
      methodObject25130 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { String.class, byte[].class });
      methodObject25420 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class });
      methodObject25338 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25330 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject25070 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setString", new Class[] { String.class, String.class });
      methodObject25423 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class, Calendar.class });
      methodObject25114 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getINTERVALDS", new Class[] { Integer.TYPE });
      methodObject25111 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getROWID", new Class[] { Integer.TYPE });
      methodObject25112 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getSTRUCT", new Class[] { Integer.TYPE });
      methodObject25297 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject25252 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25254 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25085 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25206 = CallableStatement.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject25216 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject25324 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject25452 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25307 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject25373 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefType", new Class[] { Integer.TYPE, REF.class });
      methodObject25472 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject25333 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject25385 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setIntAtName", new Class[] { String.class, Integer.TYPE });
      methodObject25308 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject25278 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject25173 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject25402 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptorAtName", new Class[] { String.class, StructDescriptor.class });
      methodObject25089 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject25134 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setRAW", new Class[] { String.class, RAW.class });
      methodObject25469 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject25262 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject25078 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class, Calendar.class });
      methodObject25222 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25159 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject25430 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZAtName", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject25084 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25466 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject25417 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBfileAtName", new Class[] { String.class, BFILE.class });
      methodObject25400 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setARRAYAtName", new Class[] { String.class, ARRAY.class });
      methodObject25376 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25317 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject25063 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject25123 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getOraclePlsqlIndexTable", new Class[] { Integer.TYPE });
      methodObject25309 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject25154 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setREF", new Class[] { String.class, REF.class });
      methodObject25491 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getFixedString", new Class[0]);
      methodObject25230 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25276 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25088 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Clob.class });
      methodObject25196 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject25488 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getChecksum", new Class[0]);
      methodObject25260 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject25266 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject25412 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCLOBAtName", new Class[] { String.class, CLOB.class });
      methodObject25372 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject25202 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject25424 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class });
      methodObject25182 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject25343 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25458 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject25413 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Clob.class });
      methodObject25449 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25199 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject25053 = oracle.jdbc.internal.OracleCallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class, Integer.TYPE });
      methodObject25056 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject25364 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject25374 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setREF", new Class[] { Integer.TYPE, REF.class });
      methodObject25332 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject25213 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject25438 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefAtName", new Class[] { String.class, Ref.class });
      methodObject25225 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE });
      methodObject25161 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject25231 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class });
      methodObject25315 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject25296 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject25204 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject25198 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject25142 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { String.class, INTERVALYM.class });
      methodObject25175 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject25094 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getOracleObject", new Class[] { Integer.TYPE });
      methodObject25126 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { String.class, Float.TYPE });
      methodObject25298 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject25066 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25189 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject25146 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject25394 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringAtName", new Class[] { String.class, String.class });
      methodObject25064 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject25245 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject25271 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject25383 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setByteAtName", new Class[] { String.class, Byte.TYPE });
      methodObject25190 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25228 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25434 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject25461 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject25368 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { Integer.TYPE, OPAQUE.class });
      methodObject25122 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getPlsqlIndexTable", new Class[] { Integer.TYPE, Class.class });
      methodObject25286 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25099 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getBfile", new Class[] { Integer.TYPE });
      methodObject25215 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject25150 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { String.class, STRUCT.class });
      methodObject25183 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject25151 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { String.class, CustomDatum.class });
      methodObject25104 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getORAData", new Class[] { Integer.TYPE, ORADataFactory.class });
      methodObject25397 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCursorAtName", new Class[] { String.class, ResultSet.class });
      methodObject25140 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBFILE", new Class[] { String.class, BFILE.class });
      methodObject25319 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject25250 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25273 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject25256 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject25265 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject25321 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject25322 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject25495 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getstatementType", new Class[0]);
      methodObject25102 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getCLOB", new Class[] { Integer.TYPE });
      methodObject25365 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject25093 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25455 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRowIdAtName", new Class[] { String.class, RowId.class });
      methodObject25253 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25106 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getDATE", new Class[] { Integer.TYPE });
      methodObject25483 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitExplicitCacheToClose", new Class[0]);
      methodObject25288 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject25103 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getCustomDatum", new Class[] { Integer.TYPE, CustomDatumFactory.class });
      methodObject25377 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25191 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject25251 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25371 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject25223 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25345 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject25108 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getOPAQUE", new Class[] { Integer.TYPE });
      methodObject25257 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25313 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject25378 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25448 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25351 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { Integer.TYPE, String.class });
      methodObject25471 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject25398 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setROWIDAtName", new Class[] { String.class, ROWID.class });
      methodObject25295 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject25367 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject25432 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCustomDatumAtName", new Class[] { String.class, CustomDatum.class });
      methodObject25346 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, BINARY_FLOAT.class });
      methodObject25194 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25171 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject25211 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject25185 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject25366 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject25207 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25339 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25242 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25156 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject25244 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject25476 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setCheckBindTypes", new Class[] { Boolean.TYPE });
      methodObject25363 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject25482 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitImplicitCacheToClose", new Class[0]);
      methodObject25426 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYMAtName", new Class[] { String.class, INTERVALYM.class });
      methodObject25083 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class });
      methodObject25487 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getSqlKind", new Class[0]);
      methodObject25193 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class });
      methodObject25316 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject25464 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25107 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getNUMBER", new Class[] { Integer.TYPE });
      methodObject25116 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { Integer.TYPE });
      methodObject25179 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject25092 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setRef", new Class[] { String.class, Ref.class });
      methodObject25137 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setNUMBER", new Class[] { String.class, NUMBER.class });
      methodObject25138 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBLOB", new Class[] { String.class, BLOB.class });
      methodObject25494 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getcacheState", new Class[0]);
      methodObject25133 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setROWID", new Class[] { String.class, ROWID.class });
      methodObject25415 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class });
      methodObject25310 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject25484 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("getOriginalSql", new Class[0]);
      methodObject25282 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25167 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject25342 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject25473 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject25289 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject25100 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getBLOB", new Class[] { Integer.TYPE });
      methodObject25127 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject25188 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25200 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject25248 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject25166 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject25292 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25268 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject25337 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject25162 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject25176 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject25184 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject25110 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getREF", new Class[] { Integer.TYPE });
      methodObject25226 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject25165 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { String.class });
      methodObject25390 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject25436 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class });
      methodObject25475 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject25205 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject25302 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject25379 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("getExecuteBatch", new Class[0]);
      methodObject25139 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setCLOB", new Class[] { String.class, CLOB.class });
      methodObject25444 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFormOfUse", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject25181 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject25493 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getserverCursor", new Class[0]);
      methodObject25477 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setInternalBytes", new Class[] { Integer.TYPE, byte[].class, Integer.TYPE });
      methodObject25360 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject25277 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25069 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBytes", new Class[] { String.class, byte[].class });
      methodObject25406 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDATEAtName", new Class[] { String.class, DATE.class });
      methodObject25164 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject25410 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25361 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject25314 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject25437 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefTypeAtName", new Class[] { String.class, REF.class });
      methodObject25326 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject25163 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject25236 = CallableStatement.class.getDeclaredMethod("setRowId", new Class[] { String.class, RowId.class });
      methodObject25327 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject25325 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject25234 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class });
      methodObject25246 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject25375 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject25238 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject25348 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, BINARY_DOUBLE.class });
      methodObject25443 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setPlsqlIndexTable", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25301 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject25055 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, OracleDataFactory.class });
      methodObject25304 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject25435 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject25076 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getUnicodeStream", new Class[] { String.class });
      methodObject25247 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject25269 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject25272 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject25445 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDisableStmtCaching", new Class[] { Boolean.TYPE });
      methodObject25387 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject25380 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject25446 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("OracleGetParameterMetaData", new Class[0]);
      methodObject25131 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { String.class, String.class });
      methodObject25391 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject25404 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRAWAtName", new Class[] { String.class, RAW.class });
      methodObject25097 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getARRAY", new Class[] { Integer.TYPE });
      methodObject25312 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject25447 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25416 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBFILEAtName", new Class[] { String.class, BFILE.class });
      methodObject25492 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("sendBatch", new Class[0]);
      methodObject25451 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, NClob.class });
      methodObject25357 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject25465 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject25079 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class });
      methodObject25160 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject25201 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject25453 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class });
      methodObject25065 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setURL", new Class[] { String.class, URL.class });
      methodObject25381 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE });
      methodObject25217 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject25490 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setFixedString", new Class[] { Boolean.TYPE });
      methodObject25210 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject25486 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setExecuteBatch", new Class[] { Integer.TYPE });
      methodObject25290 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject25429 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZAtName", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject25075 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getUnicodeStream", new Class[] { Integer.TYPE });
      methodObject25347 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject25147 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setARRAY", new Class[] { String.class, ARRAY.class });
      methodObject25057 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setByte", new Class[] { String.class, Byte.TYPE });
      methodObject25125 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject25356 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject25457 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject25170 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject25267 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject25174 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject25124 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("registerIndexTableOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25384 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setShortAtName", new Class[] { String.class, Short.TYPE });
      methodObject25414 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25456 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSQLXMLAtName", new Class[] { String.class, SQLXML.class });
      methodObject25178 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject25129 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setStringForClob", new Class[] { String.class, String.class });
      methodObject25074 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getAsciiStream", new Class[] { Integer.TYPE });
      methodObject25450 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("getReturnResultSet", new Class[0]);
      methodObject25119 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("registerOutParameterChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25212 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject25105 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getAnyDataEmbeddedObject", new Class[] { Integer.TYPE });
      methodObject25144 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { String.class, TIMESTAMP.class });
      methodObject25132 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setCursor", new Class[] { String.class, ResultSet.class });
      methodObject25080 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class, Calendar.class });
      methodObject25067 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25081 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject25062 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setShort", new Class[] { String.class, Short.TYPE });
      methodObject25136 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setDATE", new Class[] { String.class, DATE.class });
      methodObject25323 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject25386 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setLongAtName", new Class[] { String.class, Long.TYPE });
      methodObject25068 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject25421 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class, Calendar.class });
      methodObject25481 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitExplicitCacheToActive", new Class[0]);
      methodObject25479 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("enterExplicitCache", new Class[0]);
      methodObject25440 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOracleObjectAtName", new Class[] { String.class, Datum.class });
      methodObject25208 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject25459 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25264 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject25300 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject25353 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject25221 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject25467 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject25389 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject25115 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMP", new Class[] { Integer.TYPE });
      methodObject25073 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject25258 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject25407 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNUMBERAtName", new Class[] { String.class, NUMBER.class });
      methodObject25113 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getINTERVALYM", new Class[] { Integer.TYPE });
      methodObject25299 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject25350 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { Integer.TYPE, byte[].class });
      methodObject25470 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject25474 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject25152 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setORAData", new Class[] { String.class, ORAData.class });
      methodObject25059 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setFloat", new Class[] { String.class, Float.TYPE });
      methodObject25141 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBfile", new Class[] { String.class, BFILE.class });
      methodObject25052 = oracle.jdbc.internal.OracleCallableStatement.class.getDeclaredMethod("getAsciiStream", new Class[] { String.class });
      methodObject25329 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject25121 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getPlsqlIndexTable", new Class[] { Integer.TYPE });
      methodObject25274 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject25427 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDSAtName", new Class[] { String.class, INTERVALDS.class });
      methodObject25460 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25229 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class });
      methodObject25259 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25462 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25291 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject25392 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject25320 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject25172 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject25241 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject25349 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringForClob", new Class[] { Integer.TYPE, String.class });
      methodObject25192 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25281 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject25344 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject25399 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setArrayAtName", new Class[] { String.class, Array.class });
      methodObject25153 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setRefType", new Class[] { String.class, REF.class });
      methodObject25403 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSTRUCTAtName", new Class[] { String.class, STRUCT.class });
      methodObject25428 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPAtName", new Class[] { String.class, TIMESTAMP.class });
      methodObject25168 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject25408 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBLOBAtName", new Class[] { String.class, BLOB.class });
      methodObject25061 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setLong", new Class[] { String.class, Long.TYPE });
      methodObject25071 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getBinaryStream", new Class[] { Integer.TYPE });
      methodObject25209 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject25431 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setUnicodeStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25128 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { String.class, Double.TYPE });
      methodObject25275 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject25335 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25279 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25233 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25480 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitImplicitCacheToActive", new Class[0]);
      methodObject25401 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOPAQUEAtName", new Class[] { String.class, OPAQUE.class });
      methodObject25051 = oracle.jdbc.internal.OracleCallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject25090 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE });
      methodObject25197 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject25354 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject25261 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject25334 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject25370 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject25143 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { String.class, INTERVALDS.class });
      methodObject25355 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject25388 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject25311 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject25135 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setCHAR", new Class[] { String.class, CHAR.class });
      methodObject25280 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject25098 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getBFILE", new Class[] { Integer.TYPE });
      methodObject25294 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject25305 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject25306 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject25243 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject25086 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject25118 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("registerOutParameterBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25441 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setURLAtName", new Class[] { String.class, URL.class });
      methodObject25180 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject25422 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class });
      methodObject25155 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setOracleObject", new Class[] { String.class, Datum.class });
      methodObject25293 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject25149 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { String.class, StructDescriptor.class });
      methodObject25058 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setDouble", new Class[] { String.class, Double.TYPE });
      methodObject25284 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject25454 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNStringAtName", new Class[] { String.class, String.class });
      methodObject25433 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setORADataAtName", new Class[] { String.class, ORAData.class });
      methodObject25352 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCursor", new Class[] { Integer.TYPE, ResultSet.class });
      methodObject25393 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBigDecimalAtName", new Class[] { String.class, BigDecimal.class });
      methodObject25489 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setSnapshotSCN", new Class[] { Long.TYPE });
      methodObject25091 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setArray", new Class[] { String.class, Array.class });
      methodObject25419 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlobAtName", new Class[] { String.class, byte[].class });
      methodObject25117 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { Integer.TYPE });
      methodObject25328 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject25270 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject25249 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject25239 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject25072 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getBinaryStream", new Class[] { String.class });
      methodObject25227 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject25341 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25418 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesAtName", new Class[] { String.class, byte[].class });
      methodObject25219 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject25285 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25336 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25224 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25405 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCHARAtName", new Class[] { String.class, CHAR.class });
      methodObject25145 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject25060 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setInt", new Class[] { String.class, Integer.TYPE });
      methodObject25187 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject25158 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject25218 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject25303 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject25177 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject25148 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { String.class, OPAQUE.class });
      methodObject25087 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, Blob.class });
      methodObject25203 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject25468 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject25263 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject25411 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class });
      methodObject25331 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject25109 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getRAW", new Class[] { Integer.TYPE });
      methodObject25358 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject25287 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject25396 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFixedCHARAtName", new Class[] { String.class, String.class });
      methodObject25237 = CallableStatement.class.getDeclaredMethod("setSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject25232 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, NClob.class });
      methodObject25382 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBooleanAtName", new Class[] { String.class, Boolean.TYPE });
      methodObject25425 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject25240 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject25054 = oracle.jdbc.internal.OracleCallableStatement.class.getDeclaredMethod("privateGetBytes", new Class[] { Integer.TYPE });
      methodObject25283 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject25318 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject25235 = CallableStatement.class.getDeclaredMethod("setNString", new Class[] { String.class, String.class });
      methodObject25369 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { Integer.TYPE, StructDescriptor.class });
      methodObject25101 = oracle.jdbc.OracleCallableStatement.class.getDeclaredMethod("getCHAR", new Class[] { Integer.TYPE });
      methodObject25439 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setREFAtName", new Class[] { String.class, REF.class });
      methodObject25214 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject25186 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject25220 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleCallableStatement$$$Proxy(oracle.jdbc.internal.OracleCallableStatement paramOracleCallableStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleCallableStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OracleCallableStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */