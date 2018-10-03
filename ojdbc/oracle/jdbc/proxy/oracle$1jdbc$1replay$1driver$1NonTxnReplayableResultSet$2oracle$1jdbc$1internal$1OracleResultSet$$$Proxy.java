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
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1internal$1OracleResultSet$$$Proxy
  extends NonTxnReplayableResultSet
  implements oracle.jdbc.internal.OracleResultSet, _Proxy_
{
  private oracle.jdbc.internal.OracleResultSet delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject28244;
  private static Method methodObject28474;
  private static Method methodObject28288;
  private static Method methodObject28424;
  private static Method methodObject28234;
  private static Method methodObject28223;
  private static Method methodObject28462;
  private static Method methodObject28354;
  private static Method methodObject28228;
  private static Method methodObject28376;
  private static Method methodObject28489;
  private static Method methodObject28366;
  private static Method methodObject28368;
  private static Method methodObject28444;
  private static Method methodObject28336;
  private static Method methodObject28300;
  private static Method methodObject28320;
  private static Method methodObject28224;
  private static Method methodObject28315;
  private static Method methodObject28286;
  private static Method methodObject28433;
  private static Method methodObject28309;
  private static Method methodObject28238;
  private static Method methodObject28481;
  private static Method methodObject28467;
  private static Method methodObject28466;
  private static Method methodObject28314;
  private static Method methodObject28275;
  private static Method methodObject28267;
  private static Method methodObject28427;
  private static Method methodObject28257;
  private static Method methodObject28270;
  private static Method methodObject28251;
  private static Method methodObject28284;
  private static Method methodObject28253;
  private static Method methodObject28361;
  private static Method methodObject28468;
  private static Method methodObject28219;
  private static Method methodObject28479;
  private static Method methodObject28382;
  private static Method methodObject28411;
  private static Method methodObject28357;
  private static Method methodObject28364;
  private static Method methodObject28419;
  private static Method methodObject28266;
  private static Method methodObject28421;
  private static Method methodObject28494;
  private static Method methodObject28391;
  private static Method methodObject28379;
  private static Method methodObject28459;
  private static Method methodObject28324;
  private static Method methodObject28295;
  private static Method methodObject28344;
  private static Method methodObject28452;
  private static Method methodObject28225;
  private static Method methodObject28423;
  private static Method methodObject28222;
  private static Method methodObject28310;
  private static Method methodObject28236;
  private static Method methodObject28260;
  private static Method methodObject28289;
  private static Method methodObject28483;
  private static Method methodObject28441;
  private static Method methodObject28374;
  private static Method methodObject28380;
  private static Method methodObject28431;
  private static Method methodObject28378;
  private static Method methodObject28436;
  private static Method methodObject28311;
  private static Method methodObject28451;
  private static Method methodObject28271;
  private static Method methodObject28350;
  private static Method methodObject28402;
  private static Method methodObject28339;
  private static Method methodObject28448;
  private static Method methodObject28413;
  private static Method methodObject28334;
  private static Method methodObject28345;
  private static Method methodObject28471;
  private static Method methodObject28405;
  private static Method methodObject28418;
  private static Method methodObject28486;
  private static Method methodObject28465;
  private static Method methodObject28453;
  private static Method methodObject28304;
  private static Method methodObject28283;
  private static Method methodObject28473;
  private static Method methodObject28329;
  private static Method methodObject28321;
  private static Method methodObject28333;
  private static Method methodObject28351;
  private static Method methodObject28362;
  private static Method methodObject28337;
  private static Method methodObject28246;
  private static Method methodObject28490;
  private static Method methodObject28478;
  private static Method methodObject28256;
  private static Method methodObject28250;
  private static Method methodObject28325;
  private static Method methodObject28330;
  private static Method methodObject28394;
  private static Method methodObject28297;
  private static Method methodObject28348;
  private static Method methodObject28487;
  private static Method methodObject28397;
  private static Method methodObject28407;
  private static Method methodObject28460;
  private static Method methodObject28372;
  private static Method methodObject28373;
  private static Method methodObject28294;
  private static Method methodObject28408;
  private static Method methodObject28480;
  private static Method methodObject28389;
  private static Method methodObject28312;
  private static Method methodObject28398;
  private static Method methodObject28292;
  private static Method methodObject28369;
  private static Method methodObject28417;
  private static Method methodObject28445;
  private static Method methodObject28439;
  private static Method methodObject28347;
  private static Method methodObject28353;
  private static Method methodObject28221;
  private static Method methodObject28326;
  private static Method methodObject28259;
  private static Method methodObject28428;
  private static Method methodObject28268;
  private static Method methodObject28355;
  private static Method methodObject28385;
  private static Method methodObject28255;
  private static Method methodObject28488;
  private static Method methodObject28359;
  private static Method methodObject28482;
  private static Method methodObject28276;
  private static Method methodObject28477;
  private static Method methodObject28393;
  private static Method methodObject28464;
  private static Method methodObject28274;
  private static Method methodObject28279;
  private static Method methodObject28303;
  private static Method methodObject28248;
  private static Method methodObject28277;
  private static Method methodObject28371;
  private static Method methodObject28375;
  private static Method methodObject28281;
  private static Method methodObject28491;
  private static Method methodObject28395;
  private static Method methodObject28363;
  private static Method methodObject28447;
  private static Method methodObject28426;
  private static Method methodObject28365;
  private static Method methodObject28278;
  private static Method methodObject28229;
  private static Method methodObject28390;
  private static Method methodObject28412;
  private static Method methodObject28404;
  private static Method methodObject28338;
  private static Method methodObject28242;
  private static Method methodObject28323;
  private static Method methodObject28264;
  private static Method methodObject28469;
  private static Method methodObject28456;
  private static Method methodObject28273;
  private static Method methodObject28358;
  private static Method methodObject28434;
  private static Method methodObject28475;
  private static Method methodObject28319;
  private static Method methodObject28287;
  private static Method methodObject28239;
  private static Method methodObject28218;
  private static Method methodObject28383;
  private static Method methodObject28458;
  private static Method methodObject28442;
  private static Method methodObject28399;
  private static Method methodObject28220;
  private static Method methodObject28461;
  private static Method methodObject28262;
  private static Method methodObject28302;
  private static Method methodObject28280;
  private static Method methodObject28282;
  private static Method methodObject28235;
  private static Method methodObject28386;
  private static Method methodObject28346;
  private static Method methodObject28306;
  private static Method methodObject28232;
  private static Method methodObject28241;
  private static Method methodObject28258;
  private static Method methodObject28495;
  private static Method methodObject28429;
  private static Method methodObject28237;
  private static Method methodObject28388;
  private static Method methodObject28457;
  private static Method methodObject28245;
  private static Method methodObject28432;
  private static Method methodObject28381;
  private static Method methodObject28440;
  private static Method methodObject28420;
  private static Method methodObject28435;
  private static Method methodObject28454;
  private static Method methodObject28227;
  private static Method methodObject28240;
  private static Method methodObject28367;
  private static Method methodObject28437;
  private static Method methodObject28360;
  private static Method methodObject28401;
  private static Method methodObject28335;
  private static Method methodObject28332;
  private static Method methodObject28322;
  private static Method methodObject28291;
  private static Method methodObject28299;
  private static Method methodObject28406;
  private static Method methodObject28272;
  private static Method methodObject28342;
  private static Method methodObject28252;
  private static Method methodObject28285;
  private static Method methodObject28396;
  private static Method methodObject28443;
  private static Method methodObject28307;
  private static Method methodObject28293;
  private static Method methodObject28263;
  private static Method methodObject28377;
  private static Method methodObject28446;
  private static Method methodObject28472;
  private static Method methodObject28484;
  private static Method methodObject28243;
  private static Method methodObject28261;
  private static Method methodObject28265;
  private static Method methodObject28331;
  private static Method methodObject28492;
  private static Method methodObject28485;
  private static Method methodObject28301;
  private static Method methodObject28384;
  private static Method methodObject28409;
  private static Method methodObject28254;
  private static Method methodObject28463;
  private static Method methodObject28416;
  private static Method methodObject28356;
  private static Method methodObject28305;
  private static Method methodObject28387;
  private static Method methodObject28298;
  private static Method methodObject28403;
  private static Method methodObject28296;
  private static Method methodObject28476;
  private static Method methodObject28269;
  private static Method methodObject28425;
  private static Method methodObject28493;
  private static Method methodObject28308;
  private static Method methodObject28318;
  private static Method methodObject28343;
  private static Method methodObject28290;
  private static Method methodObject28414;
  private static Method methodObject28231;
  private static Method methodObject28230;
  private static Method methodObject28392;
  private static Method methodObject28470;
  private static Method methodObject28328;
  private static Method methodObject28438;
  private static Method methodObject28349;
  private static Method methodObject28352;
  private static Method methodObject28317;
  private static Method methodObject28370;
  private static Method methodObject28247;
  private static Method methodObject28422;
  private static Method methodObject28400;
  private static Method methodObject28449;
  private static Method methodObject28313;
  private static Method methodObject28226;
  private static Method methodObject28327;
  private static Method methodObject28450;
  private static Method methodObject28233;
  private static Method methodObject28430;
  private static Method methodObject28340;
  private static Method methodObject28249;
  private static Method methodObject28341;
  private static Method methodObject28410;
  private static Method methodObject28415;
  private static Method methodObject28455;
  private static Method methodObject28316;
  
  public NUMBER getNUMBER(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28244, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject28244, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject28244, onErrorForAll(methodObject28244, e));
    }
  }
  
  public void updateNull(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28474, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.updateNull(arg0);
      postForAll(methodObject28474);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28474, e);
    }
  }
  
  public void updateTIMESTAMP(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28288, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMP(arg0, arg1);
      postForAll(methodObject28288);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28288, e);
    }
  }
  
  public boolean isLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28424, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28424, Boolean.valueOf(this.delegate.isLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28424, onErrorForAll(methodObject28424, e))).booleanValue();
    }
  }
  
  public CHAR getCHAR(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28234, this, new Object[] { arg0 });
      return (CHAR)postForAll(methodObject28234, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject28234, onErrorForAll(methodObject28234, e));
    }
  }
  
  public ResultSet getCursor(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28223, this, new Object[] { Integer.valueOf(arg0) });
      return (ResultSet)postForAll(methodObject28223, this.proxyFactory.proxyForCreate((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject28223));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject28223, onErrorForAll(methodObject28223, e));
    }
  }
  
  public void updateInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28462, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject28462);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28462, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28354, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject28354, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject28354));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject28354, onErrorForAll(methodObject28354, e));
    }
  }
  
  public BFILE getBFILE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28228, this, new Object[] { arg0 });
      return (BFILE)postForAll(methodObject28228, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject28228, onErrorForAll(methodObject28228, e));
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28376, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28376);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28376, e);
    }
  }
  
  public void updateString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28489, this, new Object[] { arg0, arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject28489);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28489, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28366, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28366);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28366, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28368, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject28368);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28368, e);
    }
  }
  
  public void updateBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28444, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject28444);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28444, e);
    }
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28336, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject28336, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject28336));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject28336, onErrorForAll(methodObject28336, e));
    }
  }
  
  public void updateREF(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28300, this, new Object[] { arg0, arg1 });
      this.delegate.updateREF(arg0, arg1);
      postForAll(methodObject28300);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28300, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28320, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject28320, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject28320, onErrorForAll(methodObject28320, e))).longValue();
    }
  }
  
  public ResultSet getCursor(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28224, this, new Object[] { arg0 });
      return (ResultSet)postForAll(methodObject28224, this.proxyFactory.proxyForCreate((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject28224));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject28224, onErrorForAll(methodObject28224, e));
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28315, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject28315, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject28315, onErrorForAll(methodObject28315, e))).shortValue();
    }
  }
  
  public void updateINTERVALDS(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28286, this, new Object[] { arg0, arg1 });
      this.delegate.updateINTERVALDS(arg0, arg1);
      postForAll(methodObject28286);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28286, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28433, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      postForAll(methodObject28433);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28433, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28309, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject28309, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28309));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28309, onErrorForAll(methodObject28309, e));
    }
  }
  
  public CustomDatum getCustomDatum(String arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28238, this, new Object[] { arg0, arg1 });
      return (CustomDatum)postForAll(methodObject28238, (Object)this.delegate.getCustomDatum(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (CustomDatum)postForAll(methodObject28238, onErrorForAll(methodObject28238, e));
    }
  }
  
  public void updateRef(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28481, this, new Object[] { arg0, arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28481);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28481, e);
    }
  }
  
  public void updateNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28467, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28467);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28467, e);
    }
  }
  
  public void updateNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28466, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28466);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28466, e);
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28314, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject28314, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject28314, onErrorForAll(methodObject28314, e))).byteValue();
    }
  }
  
  public void updateCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28275, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCLOB(arg0, arg1);
      postForAll(methodObject28275);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28275, e);
    }
  }
  
  public void updateBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28267, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBfile(arg0, arg1);
      postForAll(methodObject28267);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28267, e);
    }
  }
  
  public void refreshRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28427, this, zeroLengthObjectArray);
      this.delegate.refreshRow();
      postForAll(methodObject28427);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28427, e);
    }
  }
  
  public INTERVALDS getINTERVALDS(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28257, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALDS)postForAll(methodObject28257, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject28257, onErrorForAll(methodObject28257, e));
    }
  }
  
  public void updateBFILE(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28270, this, new Object[] { arg0, arg1 });
      this.delegate.updateBFILE(arg0, arg1);
      postForAll(methodObject28270);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28270, e);
    }
  }
  
  public ROWID getROWID(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28251, this, new Object[] { Integer.valueOf(arg0) });
      return (ROWID)postForAll(methodObject28251, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject28251, onErrorForAll(methodObject28251, e));
    }
  }
  
  public void updateINTERVALYM(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28284, this, new Object[] { arg0, arg1 });
      this.delegate.updateINTERVALYM(arg0, arg1);
      postForAll(methodObject28284);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28284, e);
    }
  }
  
  public STRUCT getSTRUCT(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28253, this, new Object[] { Integer.valueOf(arg0) });
      return (STRUCT)postForAll(methodObject28253, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject28253, onErrorForAll(methodObject28253, e));
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28361, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28361);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28361, e);
    }
  }
  
  public void updateNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28468, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject28468);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28468, e);
    }
  }
  
  public Object getObject(int arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28219, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject28219, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28219));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28219, onErrorForAll(methodObject28219, e));
    }
  }
  
  public void updateObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28479, this, new Object[] { arg0, arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28479);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28479, e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28382, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28382, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28382, onErrorForAll(methodObject28382, e))).booleanValue();
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28411, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject28411, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28411, onErrorForAll(methodObject28411, e));
    }
  }
  
  public void updateRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28357, this, zeroLengthObjectArray);
      this.delegate.updateRow();
      postForAll(methodObject28357);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28357, e);
    }
  }
  
  public void updateBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28364, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28364);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28364, e);
    }
  }
  
  public InputStream getUnicodeStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28419, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28419, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28419, onErrorForAll(methodObject28419, e));
    }
  }
  
  public void updateARRAY(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28266, this, new Object[] { arg0, arg1 });
      this.delegate.updateARRAY(arg0, arg1);
      postForAll(methodObject28266);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28266, e);
    }
  }
  
  public boolean isAfterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28421, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28421, Boolean.valueOf(this.delegate.isAfterLast()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28421, onErrorForAll(methodObject28421, e))).booleanValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28494, this, new Object[] { arg0 });
      return postForAll(methodObject28494, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28494, onErrorForAll(methodObject28494, e));
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28391, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject28391);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28391, e);
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28379, this, new Object[] { arg0, arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject28379);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28379, e);
    }
  }
  
  public void updateDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28459, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject28459);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28459, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28324, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject28324, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject28324, onErrorForAll(methodObject28324, e))).doubleValue();
    }
  }
  
  public void updateOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28295, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateOracleObject(arg0, arg1);
      postForAll(methodObject28295);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28295, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28344, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject28344, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject28344));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject28344, onErrorForAll(methodObject28344, e));
    }
  }
  
  public void updateClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28452, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject28452);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28452, e);
    }
  }
  
  public ARRAY getARRAY(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28225, this, new Object[] { Integer.valueOf(arg0) });
      return (ARRAY)postForAll(methodObject28225, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject28225, onErrorForAll(methodObject28225, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28423, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28423, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28423, onErrorForAll(methodObject28423, e))).booleanValue();
    }
  }
  
  public Datum getOracleObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28222, this, new Object[] { arg0 });
      return (Datum)postForAll(methodObject28222, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject28222, onErrorForAll(methodObject28222, e));
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28310, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject28310, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28310));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28310, onErrorForAll(methodObject28310, e));
    }
  }
  
  public CLOB getCLOB(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28236, this, new Object[] { arg0 });
      return (CLOB)postForAll(methodObject28236, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject28236, onErrorForAll(methodObject28236, e));
    }
  }
  
  public TIMESTAMP getTIMESTAMP(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28260, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject28260, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject28260, onErrorForAll(methodObject28260, e));
    }
  }
  
  public void updateTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28289, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMPTZ(arg0, arg1);
      postForAll(methodObject28289);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28289, e);
    }
  }
  
  public void updateRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28483, this, new Object[] { arg0, arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28483);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28483, e);
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28441, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject28441);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28441, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28374, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject28374);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28374, e);
    }
  }
  
  public boolean last()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28380, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28380, Boolean.valueOf(this.delegate.last()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28380, Boolean.valueOf(onErrorForLast(methodObject28380, e)))).booleanValue();
    }
  }
  
  public boolean rowUpdated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28431, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28431, Boolean.valueOf(this.delegate.rowUpdated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28431, onErrorForAll(methodObject28431, e))).booleanValue();
    }
  }
  
  public void updateNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28378, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNCharacterStream(arg0, arg1);
      postForAll(methodObject28378);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28378, e);
    }
  }
  
  public void updateBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28436, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject28436);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28436, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28311, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28311, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28311, onErrorForAll(methodObject28311, e))).booleanValue();
    }
  }
  
  public void updateClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28451, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28451);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28451, e);
    }
  }
  
  public void updateBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28271, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBLOB(arg0, arg1);
      postForAll(methodObject28271);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28271, e);
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28350, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject28350, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject28350));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject28350, onErrorForAll(methodObject28350, e));
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28402, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28402, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28402, onErrorForAll(methodObject28402, e))).intValue();
    }
  }
  
  public boolean isFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28339, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28339, Boolean.valueOf(this.delegate.isFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28339, onErrorForAll(methodObject28339, e))).booleanValue();
    }
  }
  
  public void updateBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28448, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject28448);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28448, e);
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28413, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject28413, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28413, onErrorForAll(methodObject28413, e));
    }
  }
  
  public boolean previous()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28334, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28334, Boolean.valueOf(this.delegate.previous()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28334, onErrorForAll(methodObject28334, e))).booleanValue();
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28345, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject28345, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject28345));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject28345, onErrorForAll(methodObject28345, e));
    }
  }
  
  public void updateNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28471, this, new Object[] { arg0, arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject28471);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28471, e);
    }
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28405, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject28405, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28405, onErrorForAll(methodObject28405, e));
    }
  }
  
  public InputStream getUnicodeStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28418, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28418, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28418, onErrorForAll(methodObject28418, e));
    }
  }
  
  public void updateShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28486, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject28486);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28486, e);
    }
  }
  
  public void updateLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28465, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject28465);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28465, e);
    }
  }
  
  public void updateClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28453, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateClob(arg0, arg1, arg2);
      postForAll(methodObject28453);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28453, e);
    }
  }
  
  public void updateSTRUCT(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28304, this, new Object[] { arg0, arg1 });
      this.delegate.updateSTRUCT(arg0, arg1);
      postForAll(methodObject28304);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28304, e);
    }
  }
  
  public void updateINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28283, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateINTERVALYM(arg0, arg1);
      postForAll(methodObject28283);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28283, e);
    }
  }
  
  public void updateNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28473, this, new Object[] { arg0, arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject28473);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28473, e);
    }
  }
  
  public boolean next()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28329, this, zeroLengthObjectArray);
      return postForNext(methodObject28329, this.delegate.next());
    }
    catch (SQLException e)
    {
      return postForNext(methodObject28329, ((Boolean)onErrorForAll(methodObject28329, e)).booleanValue());
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28321, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject28321, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject28321, onErrorForAll(methodObject28321, e))).floatValue();
    }
  }
  
  public int getType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28333, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28333, Integer.valueOf(this.delegate.getType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28333, onErrorForAll(methodObject28333, e))).intValue();
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28351, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject28351, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject28351));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject28351, onErrorForAll(methodObject28351, e));
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28362, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject28362);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28362, e);
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28337, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject28337, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28337, onErrorForAll(methodObject28337, e));
    }
  }
  
  public OPAQUE getOPAQUE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28246, this, new Object[] { arg0 });
      return (OPAQUE)postForAll(methodObject28246, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject28246, onErrorForAll(methodObject28246, e));
    }
  }
  
  public void updateTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28490, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject28490);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28490, e);
    }
  }
  
  public void updateObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28478, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject28478);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28478, e);
    }
  }
  
  public INTERVALYM getINTERVALYM(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28256, this, new Object[] { arg0 });
      return (INTERVALYM)postForAll(methodObject28256, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject28256, onErrorForAll(methodObject28256, e));
    }
  }
  
  public REF getREF(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28250, this, new Object[] { arg0 });
      return (REF)postForAll(methodObject28250, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject28250, onErrorForAll(methodObject28250, e));
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28325, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject28325, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject28325, onErrorForAll(methodObject28325, e));
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28330, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject28330, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject28330, onErrorForAll(methodObject28330, e));
    }
  }
  
  public InputStream getAsciiStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28394, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28394, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28394, onErrorForAll(methodObject28394, e));
    }
  }
  
  public void updateRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28297, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRAW(arg0, arg1);
      postForAll(methodObject28297);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28297, e);
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28348, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject28348, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject28348));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject28348, onErrorForAll(methodObject28348, e));
    }
  }
  
  public void updateShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28487, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.updateShort(arg0, arg1);
      postForAll(methodObject28487);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28487, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28397, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject28397, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28397, onErrorForAll(methodObject28397, e));
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28407, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject28407, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28407, onErrorForAll(methodObject28407, e));
    }
  }
  
  public void updateFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28460, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject28460);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28460, e);
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28372, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28372);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28372, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28373, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28373);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28373, e);
    }
  }
  
  public void updateNUMBER(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28294, this, new Object[] { arg0, arg1 });
      this.delegate.updateNUMBER(arg0, arg1);
      postForAll(methodObject28294);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28294, e);
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28408, this, new Object[] { arg0 });
      return (String)postForAll(methodObject28408, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28408, onErrorForAll(methodObject28408, e));
    }
  }
  
  public void updateRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28480, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28480);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28480, e);
    }
  }
  
  public void beforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28389, this, zeroLengthObjectArray);
      this.delegate.beforeFirst();
      postForAll(methodObject28389);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28389, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28312, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject28312, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28312, onErrorForAll(methodObject28312, e))).booleanValue();
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28398, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject28398, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28398, onErrorForAll(methodObject28398, e));
    }
  }
  
  public void updateTIMESTAMPLTZ(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28292, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMPLTZ(arg0, arg1);
      postForAll(methodObject28292);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28292, e);
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28369, this, new Object[] { arg0, arg1 });
      this.delegate.updateBinaryStream(arg0, arg1);
      postForAll(methodObject28369);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28369, e);
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28417, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject28417, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28417, onErrorForAll(methodObject28417, e));
    }
  }
  
  public void updateBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28445, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.updateBoolean(arg0, arg1);
      postForAll(methodObject28445);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28445, e);
    }
  }
  
  public void updateBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28439, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28439);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28439, e);
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28347, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject28347, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject28347));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject28347, onErrorForAll(methodObject28347, e));
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28353, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject28353, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject28353));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject28353, onErrorForAll(methodObject28353, e));
    }
  }
  
  public Datum getOracleObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28221, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum)postForAll(methodObject28221, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject28221, onErrorForAll(methodObject28221, e));
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28326, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject28326, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject28326, onErrorForAll(methodObject28326, e));
    }
  }
  
  public TIMESTAMP getTIMESTAMP(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28259, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMP)postForAll(methodObject28259, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject28259, onErrorForAll(methodObject28259, e));
    }
  }
  
  public boolean relative(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28428, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28428, Boolean.valueOf(this.delegate.relative(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28428, onErrorForAll(methodObject28428, e))).booleanValue();
    }
  }
  
  public void updateBfile(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28268, this, new Object[] { arg0, arg1 });
      this.delegate.updateBfile(arg0, arg1);
      postForAll(methodObject28268);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28268, e);
    }
  }
  
  public void deleteRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28355, this, zeroLengthObjectArray);
      this.delegate.deleteRow();
      postForAll(methodObject28355);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28355, e);
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28385, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject28385, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28385, onErrorForAll(methodObject28385, e));
    }
  }
  
  public INTERVALYM getINTERVALYM(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28255, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALYM)postForAll(methodObject28255, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject28255, onErrorForAll(methodObject28255, e));
    }
  }
  
  public void updateString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28488, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateString(arg0, arg1);
      postForAll(methodObject28488);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28488, e);
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28359, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28359);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28359, e);
    }
  }
  
  public void updateRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28482, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28482);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28482, e);
    }
  }
  
  public void updateCLOB(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28276, this, new Object[] { arg0, arg1 });
      this.delegate.updateCLOB(arg0, arg1);
      postForAll(methodObject28276);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28276, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28477, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28477);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28477, e);
    }
  }
  
  public boolean first()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28393, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28393, Boolean.valueOf(this.delegate.first()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28393, onErrorForAll(methodObject28393, e))).booleanValue();
    }
  }
  
  public void updateLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28464, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.updateLong(arg0, arg1);
      postForAll(methodObject28464);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28464, e);
    }
  }
  
  public void updateCHAR(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28274, this, new Object[] { arg0, arg1 });
      this.delegate.updateCHAR(arg0, arg1);
      postForAll(methodObject28274);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28274, e);
    }
  }
  
  public void updateORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28279, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateORAData(arg0, arg1);
      postForAll(methodObject28279);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28279, e);
    }
  }
  
  public void updateSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28303, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateSTRUCT(arg0, arg1);
      postForAll(methodObject28303);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28303, e);
    }
  }
  
  public RAW getRAW(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28248, this, new Object[] { arg0 });
      return (RAW)postForAll(methodObject28248, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject28248, onErrorForAll(methodObject28248, e));
    }
  }
  
  public void updateCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28277, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCustomDatum(arg0, arg1);
      postForAll(methodObject28277);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28277, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28371, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28371);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28371, e);
    }
  }
  
  public void updateCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28375, this, new Object[] { arg0, arg1 });
      this.delegate.updateCharacterStream(arg0, arg1);
      postForAll(methodObject28375);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28375, e);
    }
  }
  
  public void updateDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28281, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateDATE(arg0, arg1);
      postForAll(methodObject28281);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28281, e);
    }
  }
  
  public void updateTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28491, this, new Object[] { arg0, arg1 });
      this.delegate.updateTime(arg0, arg1);
      postForAll(methodObject28491);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28491, e);
    }
  }
  
  public InputStream getAsciiStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28395, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28395, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28395, onErrorForAll(methodObject28395, e));
    }
  }
  
  public void updateAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28363, this, new Object[] { arg0, arg1 });
      this.delegate.updateAsciiStream(arg0, arg1);
      postForAll(methodObject28363);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28363, e);
    }
  }
  
  public void updateByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28447, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject28447);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28447, e);
    }
  }
  
  public void moveToInsertRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28426, this, zeroLengthObjectArray);
      this.delegate.moveToInsertRow();
      postForAll(methodObject28426);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28426, e);
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28365, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28365);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28365, e);
    }
  }
  
  public void updateCustomDatum(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28278, this, new Object[] { arg0, arg1 });
      this.delegate.updateCustomDatum(arg0, arg1);
      postForAll(methodObject28278);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28278, e);
    }
  }
  
  public BFILE getBfile(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28229, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject28229, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject28229, onErrorForAll(methodObject28229, e));
    }
  }
  
  public void cancelRowUpdates()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28390, this, zeroLengthObjectArray);
      this.delegate.cancelRowUpdates();
      postForAll(methodObject28390);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28390, e);
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28412, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject28412, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28412, onErrorForAll(methodObject28412, e));
    }
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28404, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28404, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28404, onErrorForAll(methodObject28404, e))).intValue();
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28338, this, new Object[] { arg0 });
      return (String)postForAll(methodObject28338, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28338, onErrorForAll(methodObject28338, e));
    }
  }
  
  public DATE getDATE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28242, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject28242, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject28242, onErrorForAll(methodObject28242, e));
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28323, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject28323, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject28323, onErrorForAll(methodObject28323, e))).doubleValue();
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28264, this, new Object[] { arg0 });
      return (TIMESTAMPLTZ)postForAll(methodObject28264, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject28264, onErrorForAll(methodObject28264, e));
    }
  }
  
  public void updateNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28469, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNClob(arg0, arg1, arg2);
      postForAll(methodObject28469);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28469, e);
    }
  }
  
  public void updateDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28456, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject28456);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28456, e);
    }
  }
  
  public void updateCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28273, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateCHAR(arg0, arg1);
      postForAll(methodObject28273);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28273, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28358, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28358);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28358, e);
    }
  }
  
  public void updateArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28434, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28434);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28434, e);
    }
  }
  
  public void updateNull(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28475, this, new Object[] { arg0 });
      this.delegate.updateNull(arg0);
      postForAll(methodObject28475);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28475, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28319, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject28319, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject28319, onErrorForAll(methodObject28319, e))).longValue();
    }
  }
  
  public void updateTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28287, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMP(arg0, arg1);
      postForAll(methodObject28287);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28287, e);
    }
  }
  
  public ORAData getORAData(int arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28239, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (ORAData)postForAll(methodObject28239, (Object)this.delegate.getORAData(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (ORAData)postForAll(methodObject28239, onErrorForAll(methodObject28239, e));
    }
  }
  
  public void closeStatementOnClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28218, this, zeroLengthObjectArray);
      this.delegate.closeStatementOnClose();
      postForAll(methodObject28218);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28218, e);
    }
  }
  
  public InputStream getBinaryStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28383, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject28383, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28383, onErrorForAll(methodObject28383, e));
    }
  }
  
  public void updateDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28458, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.updateDouble(arg0, arg1);
      postForAll(methodObject28458);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28458, e);
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28442, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject28442);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28442, e);
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28399, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject28399, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28399, onErrorForAll(methodObject28399, e));
    }
  }
  
  public Object getObject(String arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28220, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject28220, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject28220));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28220, onErrorForAll(methodObject28220, e));
    }
  }
  
  public void updateFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28461, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.updateFloat(arg0, arg1);
      postForAll(methodObject28461);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28461, e);
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28262, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject28262, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject28262, onErrorForAll(methodObject28262, e));
    }
  }
  
  public void updateROWID(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28302, this, new Object[] { arg0, arg1 });
      this.delegate.updateROWID(arg0, arg1);
      postForAll(methodObject28302);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28302, e);
    }
  }
  
  public void updateORAData(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28280, this, new Object[] { arg0, arg1 });
      this.delegate.updateORAData(arg0, arg1);
      postForAll(methodObject28280);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28280, e);
    }
  }
  
  public void updateDATE(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28282, this, new Object[] { arg0, arg1 });
      this.delegate.updateDATE(arg0, arg1);
      postForAll(methodObject28282);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28282, e);
    }
  }
  
  public CLOB getCLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28235, this, new Object[] { Integer.valueOf(arg0) });
      return (CLOB)postForAll(methodObject28235, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject28235, onErrorForAll(methodObject28235, e));
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28386, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject28386, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28386, onErrorForAll(methodObject28386, e));
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28346, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject28346, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject28346));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject28346, onErrorForAll(methodObject28346, e));
    }
  }
  
  public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28306, this, new Object[] { arg0 });
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28306, (Object)this.delegate.getAuthorizationIndicator(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28306, onErrorForAll(methodObject28306, e));
    }
  }
  
  public BLOB getBLOB(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28232, this, new Object[] { arg0 });
      return (BLOB)postForAll(methodObject28232, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject28232, onErrorForAll(methodObject28232, e));
    }
  }
  
  public DATE getDATE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28241, this, new Object[] { Integer.valueOf(arg0) });
      return (DATE)postForAll(methodObject28241, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject28241, onErrorForAll(methodObject28241, e));
    }
  }
  
  public INTERVALDS getINTERVALDS(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28258, this, new Object[] { arg0 });
      return (INTERVALDS)postForAll(methodObject28258, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject28258, onErrorForAll(methodObject28258, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28495, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject28495, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28495, onErrorForAll(methodObject28495, e))).booleanValue();
    }
  }
  
  public boolean rowDeleted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28429, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28429, Boolean.valueOf(this.delegate.rowDeleted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28429, onErrorForAll(methodObject28429, e))).booleanValue();
    }
  }
  
  public CustomDatum getCustomDatum(int arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28237, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (CustomDatum)postForAll(methodObject28237, (Object)this.delegate.getCustomDatum(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (CustomDatum)postForAll(methodObject28237, onErrorForAll(methodObject28237, e));
    }
  }
  
  public void afterLast()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28388, this, zeroLengthObjectArray);
      this.delegate.afterLast();
      postForAll(methodObject28388);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28388, e);
    }
  }
  
  public void updateDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28457, this, new Object[] { arg0, arg1 });
      this.delegate.updateDate(arg0, arg1);
      postForAll(methodObject28457);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28457, e);
    }
  }
  
  public OPAQUE getOPAQUE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28245, this, new Object[] { Integer.valueOf(arg0) });
      return (OPAQUE)postForAll(methodObject28245, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject28245, onErrorForAll(methodObject28245, e));
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28432, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      postForAll(methodObject28432);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28432, e);
    }
  }
  
  public Statement getStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28381, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject28381, (Object)super.getStatement());
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject28381, onErrorForAll(methodObject28381, e));
    }
  }
  
  public void updateBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28440, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateBlob(arg0, arg1, arg2);
      postForAll(methodObject28440);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28440, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28420, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject28420, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject28420, onErrorForAll(methodObject28420, e));
    }
  }
  
  public void updateArray(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28435, this, new Object[] { arg0, arg1 });
      this.delegate.updateArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28435);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28435, e);
    }
  }
  
  public void updateClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28454, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject28454);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28454, e);
    }
  }
  
  public BFILE getBFILE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28227, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject28227, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject28227, onErrorForAll(methodObject28227, e));
    }
  }
  
  public ORAData getORAData(String arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28240, this, new Object[] { arg0, arg1 });
      return (ORAData)postForAll(methodObject28240, (Object)this.delegate.getORAData(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (ORAData)postForAll(methodObject28240, onErrorForAll(methodObject28240, e));
    }
  }
  
  public void updateBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28367, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateBinaryStream(arg0, arg1, arg2);
      postForAll(methodObject28367);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28367, e);
    }
  }
  
  public void updateBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28437, this, new Object[] { arg0, arg1 });
      this.delegate.updateBigDecimal(arg0, arg1);
      postForAll(methodObject28437);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28437, e);
    }
  }
  
  public void updateAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28360, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.updateAsciiStream(arg0, arg1, arg2);
      postForAll(methodObject28360);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28360, e);
    }
  }
  
  public String getCursorName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28401, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject28401, (Object)this.delegate.getCursorName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject28401, onErrorForAll(methodObject28401, e));
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28335, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject28335, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject28335));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject28335, onErrorForAll(methodObject28335, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28332, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject28332);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28332, e);
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28322, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject28322, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject28322, onErrorForAll(methodObject28322, e))).floatValue();
    }
  }
  
  public void updateTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28291, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTIMESTAMPLTZ(arg0, arg1);
      postForAll(methodObject28291);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28291, e);
    }
  }
  
  public void updateREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28299, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateREF(arg0, arg1);
      postForAll(methodObject28299);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28299, e);
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28406, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject28406, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject28406, onErrorForAll(methodObject28406, e));
    }
  }
  
  public void updateBLOB(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28272, this, new Object[] { arg0, arg1 });
      this.delegate.updateBLOB(arg0, arg1);
      postForAll(methodObject28272);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28272, e);
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28342, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject28342, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28342, onErrorForAll(methodObject28342, e));
    }
  }
  
  public ROWID getROWID(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28252, this, new Object[] { arg0 });
      return (ROWID)postForAll(methodObject28252, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject28252, onErrorForAll(methodObject28252, e));
    }
  }
  
  public void updateINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28285, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateINTERVALDS(arg0, arg1);
      postForAll(methodObject28285);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28285, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28396, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject28396, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject28396, onErrorForAll(methodObject28396, e));
    }
  }
  
  public void updateBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28443, this, new Object[] { arg0, arg1 });
      this.delegate.updateBlob(arg0, arg1);
      postForAll(methodObject28443);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28443, e);
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28307, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject28307, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject28307));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28307, onErrorForAll(methodObject28307, e));
    }
  }
  
  public void updateNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28293, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNUMBER(arg0, arg1);
      postForAll(methodObject28293);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28293, e);
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28263, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPLTZ)postForAll(methodObject28263, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject28263, onErrorForAll(methodObject28263, e));
    }
  }
  
  public void updateNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28377, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.updateNCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28377);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28377, e);
    }
  }
  
  public void updateByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28446, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.updateByte(arg0, arg1);
      postForAll(methodObject28446);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28446, e);
    }
  }
  
  public void updateNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28472, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNString(arg0, arg1);
      postForAll(methodObject28472);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28472, e);
    }
  }
  
  public void updateSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28484, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28484);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28484, e);
    }
  }
  
  public NUMBER getNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28243, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject28243, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject28243, onErrorForAll(methodObject28243, e));
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28261, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPTZ)postForAll(methodObject28261, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject28261, onErrorForAll(methodObject28261, e));
    }
  }
  
  public void updateARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28265, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateARRAY(arg0, arg1);
      postForAll(methodObject28265);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28265, e);
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28331, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject28331, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject28331, onErrorForAll(methodObject28331, e));
    }
  }
  
  public void updateTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28492, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject28492);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28492, e);
    }
  }
  
  public void updateSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28485, this, new Object[] { arg0, arg1 });
      this.delegate.updateSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28485);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28485, e);
    }
  }
  
  public void updateROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28301, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateROWID(arg0, arg1);
      postForAll(methodObject28301);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28301, e);
    }
  }
  
  public InputStream getBinaryStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28384, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject28384, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject28384, onErrorForAll(methodObject28384, e));
    }
  }
  
  public int getRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28409, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28409, Integer.valueOf(this.delegate.getRow()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28409, onErrorForAll(methodObject28409, e))).intValue();
    }
  }
  
  public STRUCT getSTRUCT(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28254, this, new Object[] { arg0 });
      return (STRUCT)postForAll(methodObject28254, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject28254, onErrorForAll(methodObject28254, e));
    }
  }
  
  public void updateInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28463, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.updateInt(arg0, arg1);
      postForAll(methodObject28463);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28463, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28416, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject28416, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28416, onErrorForAll(methodObject28416, e));
    }
  }
  
  public void insertRow()
    throws SQLException
  {
    try
    {
      super.preForRowUpdates(methodObject28356, this, zeroLengthObjectArray);
      this.delegate.insertRow();
      postForAll(methodObject28356);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28356, e);
    }
  }
  
  public OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28305, this, new Object[] { Integer.valueOf(arg0) });
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28305, (Object)this.delegate.getAuthorizationIndicator(arg0));
    }
    catch (SQLException e)
    {
      return (OracleResultSet.AuthorizationIndicator)postForAll(methodObject28305, onErrorForAll(methodObject28305, e));
    }
  }
  
  public boolean absolute(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28387, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject28387, Boolean.valueOf(this.delegate.absolute(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28387, onErrorForAll(methodObject28387, e))).booleanValue();
    }
  }
  
  public void updateRAW(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28298, this, new Object[] { arg0, arg1 });
      this.delegate.updateRAW(arg0, arg1);
      postForAll(methodObject28298);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28298, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28403, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28403, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28403, onErrorForAll(methodObject28403, e))).intValue();
    }
  }
  
  public void updateOracleObject(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28296, this, new Object[] { arg0, arg1 });
      this.delegate.updateOracleObject(arg0, arg1);
      postForAll(methodObject28296);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28296, e);
    }
  }
  
  public void updateObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28476, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject28476);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28476, e);
    }
  }
  
  public void updateBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28269, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBFILE(arg0, arg1);
      postForAll(methodObject28269);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28269, e);
    }
  }
  
  public void moveToCurrentRow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28425, this, zeroLengthObjectArray);
      this.delegate.moveToCurrentRow();
      postForAll(methodObject28425);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28425, e);
    }
  }
  
  public void updateTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28493, this, new Object[] { arg0, arg1 });
      this.delegate.updateTimestamp(arg0, arg1);
      postForAll(methodObject28493);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28493, e);
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28308, this, new Object[] { arg0 });
      return postForAll(methodObject28308, this.proxyFactory.proxyForCreate(this.delegate.getObject(arg0), this, this.proxyCache, methodObject28308));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject28308, onErrorForAll(methodObject28308, e));
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28318, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject28318, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28318, onErrorForAll(methodObject28318, e))).intValue();
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28343, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject28343, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28343, onErrorForAll(methodObject28343, e));
    }
  }
  
  public void updateTIMESTAMPTZ(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28290, this, new Object[] { arg0, arg1 });
      this.delegate.updateTIMESTAMPTZ(arg0, arg1);
      postForAll(methodObject28290);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28290, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28414, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject28414, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28414, onErrorForAll(methodObject28414, e));
    }
  }
  
  public BLOB getBLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28231, this, new Object[] { Integer.valueOf(arg0) });
      return (BLOB)postForAll(methodObject28231, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject28231, onErrorForAll(methodObject28231, e));
    }
  }
  
  public BFILE getBfile(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28230, this, new Object[] { arg0 });
      return (BFILE)postForAll(methodObject28230, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject28230, onErrorForAll(methodObject28230, e));
    }
  }
  
  public int findColumn(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28392, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject28392, Integer.valueOf(this.delegate.findColumn(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28392, onErrorForAll(methodObject28392, e))).intValue();
    }
  }
  
  public void updateNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28470, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateNClob(arg0, arg1);
      postForAll(methodObject28470);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28470, e);
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28328, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject28328, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject28328));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject28328, onErrorForAll(methodObject28328, e));
    }
  }
  
  public void updateBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28438, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28438);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28438, e);
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28349, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject28349, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject28349));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject28349, onErrorForAll(methodObject28349, e));
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28352, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject28352, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject28352));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject28352, onErrorForAll(methodObject28352, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28317, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject28317, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28317, onErrorForAll(methodObject28317, e))).intValue();
    }
  }
  
  public void updateCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForUpdateStreams(methodObject28370, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.updateCharacterStream(arg0, arg1, arg2);
      postForAll(methodObject28370);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28370, e);
    }
  }
  
  public RAW getRAW(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28247, this, new Object[] { Integer.valueOf(arg0) });
      return (RAW)postForAll(methodObject28247, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject28247, onErrorForAll(methodObject28247, e));
    }
  }
  
  public boolean isBeforeFirst()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28422, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28422, Boolean.valueOf(this.delegate.isBeforeFirst()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28422, onErrorForAll(methodObject28422, e))).booleanValue();
    }
  }
  
  public int getConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28400, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject28400, Integer.valueOf(this.delegate.getConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject28400, onErrorForAll(methodObject28400, e))).intValue();
    }
  }
  
  public void updateBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28449, this, new Object[] { arg0, arg1 });
      this.delegate.updateBytes(arg0, arg1);
      postForAll(methodObject28449);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28449, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28313, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject28313, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject28313, onErrorForAll(methodObject28313, e))).byteValue();
    }
  }
  
  public ARRAY getARRAY(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28226, this, new Object[] { arg0 });
      return (ARRAY)postForAll(methodObject28226, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject28226, onErrorForAll(methodObject28226, e));
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28327, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject28327, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject28327));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject28327, onErrorForAll(methodObject28327, e));
    }
  }
  
  public void updateClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28450, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.updateClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject28450);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28450, e);
    }
  }
  
  public CHAR getCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28233, this, new Object[] { Integer.valueOf(arg0) });
      return (CHAR)postForAll(methodObject28233, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject28233, onErrorForAll(methodObject28233, e));
    }
  }
  
  public boolean rowInserted()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28430, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject28430, Boolean.valueOf(this.delegate.rowInserted()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject28430, onErrorForAll(methodObject28430, e))).booleanValue();
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28340, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject28340, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28340, onErrorForAll(methodObject28340, e));
    }
  }
  
  public REF getREF(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28249, this, new Object[] { Integer.valueOf(arg0) });
      return (REF)postForAll(methodObject28249, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject28249, onErrorForAll(methodObject28249, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28341, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject28341, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject28341, onErrorForAll(methodObject28341, e));
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28410, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject28410, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject28410, onErrorForAll(methodObject28410, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28415, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject28415, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject28415, onErrorForAll(methodObject28415, e));
    }
  }
  
  public void updateClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28455, this, new Object[] { arg0, arg1 });
      this.delegate.updateClob(arg0, arg1);
      postForAll(methodObject28455);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject28455, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject28316, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject28316, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject28316, onErrorForAll(methodObject28316, e))).shortValue();
    }
  }
  
  public oracle.jdbc.internal.OracleResultSet _getDelegate_()
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
      methodObject28244 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getNUMBER", new Class[] { String.class });
      methodObject28474 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { Integer.TYPE });
      methodObject28288 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMP", new Class[] { String.class, TIMESTAMP.class });
      methodObject28424 = ResultSet.class.getDeclaredMethod("isLast", new Class[0]);
      methodObject28234 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCHAR", new Class[] { String.class });
      methodObject28223 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCursor", new Class[] { Integer.TYPE });
      methodObject28462 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject28354 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject28228 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBFILE", new Class[] { String.class });
      methodObject28376 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28489 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { String.class, String.class });
      methodObject28366 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28368 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28444 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject28336 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject28300 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateREF", new Class[] { String.class, REF.class });
      methodObject28320 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject28224 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCursor", new Class[] { String.class });
      methodObject28315 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject28286 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateINTERVALDS", new Class[] { String.class, INTERVALDS.class });
      methodObject28433 = ResultSet.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject28309 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject28238 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCustomDatum", new Class[] { String.class, CustomDatumFactory.class });
      methodObject28481 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { String.class, Ref.class });
      methodObject28467 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, NClob.class });
      methodObject28466 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject28314 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject28275 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject28267 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject28427 = ResultSet.class.getDeclaredMethod("refreshRow", new Class[0]);
      methodObject28257 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getINTERVALDS", new Class[] { Integer.TYPE });
      methodObject28270 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBFILE", new Class[] { String.class, BFILE.class });
      methodObject28251 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getROWID", new Class[] { Integer.TYPE });
      methodObject28284 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateINTERVALYM", new Class[] { String.class, INTERVALYM.class });
      methodObject28253 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getSTRUCT", new Class[] { Integer.TYPE });
      methodObject28361 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28468 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28219 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, OracleDataFactory.class });
      methodObject28479 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class });
      methodObject28382 = ResultSet.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject28411 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject28357 = ResultSet.class.getDeclaredMethod("updateRow", new Class[0]);
      methodObject28364 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject28419 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { String.class });
      methodObject28266 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateARRAY", new Class[] { String.class, ARRAY.class });
      methodObject28421 = ResultSet.class.getDeclaredMethod("isAfterLast", new Class[0]);
      methodObject28494 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject28391 = ResultSet.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject28379 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject28459 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { String.class, Double.TYPE });
      methodObject28324 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject28295 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject28344 = ResultSet.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject28452 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28225 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getARRAY", new Class[] { Integer.TYPE });
      methodObject28423 = ResultSet.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject28222 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getOracleObject", new Class[] { String.class });
      methodObject28310 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject28236 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCLOB", new Class[] { String.class });
      methodObject28260 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMP", new Class[] { String.class });
      methodObject28289 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject28483 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { String.class, RowId.class });
      methodObject28441 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28374 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject28380 = ResultSet.class.getDeclaredMethod("last", new Class[0]);
      methodObject28431 = ResultSet.class.getDeclaredMethod("rowUpdated", new Class[0]);
      methodObject28378 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject28436 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject28311 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject28451 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Clob.class });
      methodObject28271 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject28350 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject28402 = ResultSet.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject28339 = ResultSet.class.getDeclaredMethod("isFirst", new Class[0]);
      methodObject28448 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject28413 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject28334 = ResultSet.class.getDeclaredMethod("previous", new Class[0]);
      methodObject28345 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject28471 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class });
      methodObject28405 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject28418 = ResultSet.class.getDeclaredMethod("getUnicodeStream", new Class[] { Integer.TYPE });
      methodObject28486 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject28465 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { String.class, Long.TYPE });
      methodObject28453 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28304 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateSTRUCT", new Class[] { String.class, STRUCT.class });
      methodObject28283 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject28473 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { String.class, String.class });
      methodObject28329 = ResultSet.class.getDeclaredMethod("next", new Class[0]);
      methodObject28321 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject28333 = ResultSet.class.getDeclaredMethod("getType", new Class[0]);
      methodObject28351 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject28362 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28337 = ResultSet.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject28246 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getOPAQUE", new Class[] { String.class });
      methodObject28490 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { Integer.TYPE, Time.class });
      methodObject28478 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject28256 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getINTERVALYM", new Class[] { String.class });
      methodObject28250 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getREF", new Class[] { String.class });
      methodObject28325 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject28330 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject28394 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { Integer.TYPE });
      methodObject28297 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject28348 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject28487 = ResultSet.class.getDeclaredMethod("updateShort", new Class[] { String.class, Short.TYPE });
      methodObject28397 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class, Integer.TYPE });
      methodObject28407 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject28460 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject28372 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject28373 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28294 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateNUMBER", new Class[] { String.class, NUMBER.class });
      methodObject28408 = ResultSet.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject28480 = ResultSet.class.getDeclaredMethod("updateRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject28389 = ResultSet.class.getDeclaredMethod("beforeFirst", new Class[0]);
      methodObject28312 = ResultSet.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject28398 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject28292 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPLTZ", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject28369 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject28417 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject28445 = ResultSet.class.getDeclaredMethod("updateBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject28439 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, Blob.class });
      methodObject28347 = ResultSet.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject28353 = ResultSet.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject28221 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getOracleObject", new Class[] { Integer.TYPE });
      methodObject28326 = ResultSet.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject28259 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMP", new Class[] { Integer.TYPE });
      methodObject28428 = ResultSet.class.getDeclaredMethod("relative", new Class[] { Integer.TYPE });
      methodObject28268 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBfile", new Class[] { String.class, BFILE.class });
      methodObject28355 = ResultSet.class.getDeclaredMethod("deleteRow", new Class[0]);
      methodObject28385 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject28255 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getINTERVALYM", new Class[] { Integer.TYPE });
      methodObject28488 = ResultSet.class.getDeclaredMethod("updateString", new Class[] { Integer.TYPE, String.class });
      methodObject28359 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject28482 = ResultSet.class.getDeclaredMethod("updateRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject28276 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCLOB", new Class[] { String.class, CLOB.class });
      methodObject28477 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class });
      methodObject28393 = ResultSet.class.getDeclaredMethod("first", new Class[0]);
      methodObject28464 = ResultSet.class.getDeclaredMethod("updateLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject28274 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCHAR", new Class[] { String.class, CHAR.class });
      methodObject28279 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject28303 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject28248 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getRAW", new Class[] { String.class });
      methodObject28277 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject28371 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject28375 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { String.class, Reader.class });
      methodObject28281 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject28491 = ResultSet.class.getDeclaredMethod("updateTime", new Class[] { String.class, Time.class });
      methodObject28395 = ResultSet.class.getDeclaredMethod("getAsciiStream", new Class[] { String.class });
      methodObject28363 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject28447 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { String.class, Byte.TYPE });
      methodObject28426 = ResultSet.class.getDeclaredMethod("moveToInsertRow", new Class[0]);
      methodObject28365 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject28278 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCustomDatum", new Class[] { String.class, CustomDatum.class });
      methodObject28229 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBfile", new Class[] { Integer.TYPE });
      methodObject28390 = ResultSet.class.getDeclaredMethod("cancelRowUpdates", new Class[0]);
      methodObject28412 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject28404 = ResultSet.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject28338 = ResultSet.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject28242 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getDATE", new Class[] { String.class });
      methodObject28323 = ResultSet.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject28264 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { String.class });
      methodObject28469 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28456 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { Integer.TYPE, Date.class });
      methodObject28273 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject28358 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject28434 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { Integer.TYPE, Array.class });
      methodObject28475 = ResultSet.class.getDeclaredMethod("updateNull", new Class[] { String.class });
      methodObject28319 = ResultSet.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject28287 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject28239 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getORAData", new Class[] { Integer.TYPE, ORADataFactory.class });
      methodObject28218 = oracle.jdbc.internal.OracleResultSet.class.getDeclaredMethod("closeStatementOnClose", new Class[0]);
      methodObject28383 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { Integer.TYPE });
      methodObject28458 = ResultSet.class.getDeclaredMethod("updateDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject28442 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject28399 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject28220 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class, OracleDataFactory.class });
      methodObject28461 = ResultSet.class.getDeclaredMethod("updateFloat", new Class[] { String.class, Float.TYPE });
      methodObject28262 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { String.class });
      methodObject28302 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateROWID", new Class[] { String.class, ROWID.class });
      methodObject28280 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateORAData", new Class[] { String.class, ORAData.class });
      methodObject28282 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateDATE", new Class[] { String.class, DATE.class });
      methodObject28235 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCLOB", new Class[] { Integer.TYPE });
      methodObject28386 = ResultSet.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject28346 = ResultSet.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject28306 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getAuthorizationIndicator", new Class[] { String.class });
      methodObject28232 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBLOB", new Class[] { String.class });
      methodObject28241 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getDATE", new Class[] { Integer.TYPE });
      methodObject28258 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getINTERVALDS", new Class[] { String.class });
      methodObject28495 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject28429 = ResultSet.class.getDeclaredMethod("rowDeleted", new Class[0]);
      methodObject28237 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCustomDatum", new Class[] { Integer.TYPE, CustomDatumFactory.class });
      methodObject28388 = ResultSet.class.getDeclaredMethod("afterLast", new Class[0]);
      methodObject28457 = ResultSet.class.getDeclaredMethod("updateDate", new Class[] { String.class, Date.class });
      methodObject28245 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getOPAQUE", new Class[] { Integer.TYPE });
      methodObject28432 = ResultSet.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject28381 = ResultSet.class.getDeclaredMethod("getStatement", new Class[0]);
      methodObject28440 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28420 = ResultSet.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject28435 = ResultSet.class.getDeclaredMethod("updateArray", new Class[] { String.class, Array.class });
      methodObject28454 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject28227 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBFILE", new Class[] { Integer.TYPE });
      methodObject28240 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getORAData", new Class[] { String.class, ORADataFactory.class });
      methodObject28367 = ResultSet.class.getDeclaredMethod("updateBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject28437 = ResultSet.class.getDeclaredMethod("updateBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject28360 = ResultSet.class.getDeclaredMethod("updateAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject28401 = ResultSet.class.getDeclaredMethod("getCursorName", new Class[0]);
      methodObject28335 = ResultSet.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject28332 = ResultSet.class.getDeclaredMethod("close", new Class[0]);
      methodObject28322 = ResultSet.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject28291 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject28299 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateREF", new Class[] { Integer.TYPE, REF.class });
      methodObject28406 = ResultSet.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject28272 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBLOB", new Class[] { String.class, BLOB.class });
      methodObject28342 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject28252 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getROWID", new Class[] { String.class });
      methodObject28285 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject28396 = ResultSet.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject28443 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { String.class, InputStream.class });
      methodObject28307 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject28293 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject28263 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { Integer.TYPE });
      methodObject28377 = ResultSet.class.getDeclaredMethod("updateNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject28446 = ResultSet.class.getDeclaredMethod("updateByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject28472 = ResultSet.class.getDeclaredMethod("updateNString", new Class[] { Integer.TYPE, String.class });
      methodObject28484 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject28243 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getNUMBER", new Class[] { Integer.TYPE });
      methodObject28261 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { Integer.TYPE });
      methodObject28265 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject28331 = ResultSet.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject28492 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject28485 = ResultSet.class.getDeclaredMethod("updateSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject28301 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject28384 = ResultSet.class.getDeclaredMethod("getBinaryStream", new Class[] { String.class });
      methodObject28409 = ResultSet.class.getDeclaredMethod("getRow", new Class[0]);
      methodObject28254 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getSTRUCT", new Class[] { String.class });
      methodObject28463 = ResultSet.class.getDeclaredMethod("updateInt", new Class[] { String.class, Integer.TYPE });
      methodObject28416 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject28356 = ResultSet.class.getDeclaredMethod("insertRow", new Class[0]);
      methodObject28305 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getAuthorizationIndicator", new Class[] { Integer.TYPE });
      methodObject28387 = ResultSet.class.getDeclaredMethod("absolute", new Class[] { Integer.TYPE });
      methodObject28298 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateRAW", new Class[] { String.class, RAW.class });
      methodObject28403 = ResultSet.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject28296 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateOracleObject", new Class[] { String.class, Datum.class });
      methodObject28476 = ResultSet.class.getDeclaredMethod("updateObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject28269 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject28425 = ResultSet.class.getDeclaredMethod("moveToCurrentRow", new Class[0]);
      methodObject28493 = ResultSet.class.getDeclaredMethod("updateTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject28308 = ResultSet.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject28318 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject28343 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject28290 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("updateTIMESTAMPTZ", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject28414 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject28231 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBLOB", new Class[] { Integer.TYPE });
      methodObject28230 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getBfile", new Class[] { String.class });
      methodObject28392 = ResultSet.class.getDeclaredMethod("findColumn", new Class[] { String.class });
      methodObject28470 = ResultSet.class.getDeclaredMethod("updateNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject28328 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject28438 = ResultSet.class.getDeclaredMethod("updateBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject28349 = ResultSet.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject28352 = ResultSet.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject28317 = ResultSet.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject28370 = ResultSet.class.getDeclaredMethod("updateCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject28247 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getRAW", new Class[] { Integer.TYPE });
      methodObject28422 = ResultSet.class.getDeclaredMethod("isBeforeFirst", new Class[0]);
      methodObject28400 = ResultSet.class.getDeclaredMethod("getConcurrency", new Class[0]);
      methodObject28449 = ResultSet.class.getDeclaredMethod("updateBytes", new Class[] { String.class, byte[].class });
      methodObject28313 = ResultSet.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject28226 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getARRAY", new Class[] { String.class });
      methodObject28327 = ResultSet.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject28450 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject28233 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getCHAR", new Class[] { Integer.TYPE });
      methodObject28430 = ResultSet.class.getDeclaredMethod("rowInserted", new Class[0]);
      methodObject28340 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject28249 = oracle.jdbc.OracleResultSet.class.getDeclaredMethod("getREF", new Class[] { Integer.TYPE });
      methodObject28341 = ResultSet.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject28410 = ResultSet.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject28415 = ResultSet.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
      methodObject28455 = ResultSet.class.getDeclaredMethod("updateClob", new Class[] { String.class, Reader.class });
      methodObject28316 = ResultSet.class.getDeclaredMethod("getShort", new Class[] { String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1internal$1OracleResultSet$$$Proxy(oracle.jdbc.internal.OracleResultSet paramOracleResultSet, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleResultSet;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableResultSet$2oracle$1jdbc$1internal$1OracleResultSet$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */