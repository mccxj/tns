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
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleParameterMetaData;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
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
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleCallableStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements OracleCallableStatement, _Proxy_
{
  private OracleCallableStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject24686;
  private static Method methodObject24532;
  private static Method methodObject24371;
  private static Method methodObject24636;
  private static Method methodObject24358;
  private static Method methodObject24353;
  private static Method methodObject24740;
  private static Method methodObject24445;
  private static Method methodObject24617;
  private static Method methodObject24639;
  private static Method methodObject24433;
  private static Method methodObject24471;
  private static Method methodObject24672;
  private static Method methodObject24406;
  private static Method methodObject24697;
  private static Method methodObject24615;
  private static Method methodObject24607;
  private static Method methodObject24346;
  private static Method methodObject24700;
  private static Method methodObject24390;
  private static Method methodObject24387;
  private static Method methodObject24388;
  private static Method methodObject24574;
  private static Method methodObject24529;
  private static Method methodObject24531;
  private static Method methodObject24361;
  private static Method methodObject24482;
  private static Method methodObject24493;
  private static Method methodObject24601;
  private static Method methodObject24729;
  private static Method methodObject24584;
  private static Method methodObject24650;
  private static Method methodObject24749;
  private static Method methodObject24610;
  private static Method methodObject24662;
  private static Method methodObject24585;
  private static Method methodObject24555;
  private static Method methodObject24449;
  private static Method methodObject24679;
  private static Method methodObject24365;
  private static Method methodObject24410;
  private static Method methodObject24746;
  private static Method methodObject24539;
  private static Method methodObject24354;
  private static Method methodObject24499;
  private static Method methodObject24435;
  private static Method methodObject24707;
  private static Method methodObject24360;
  private static Method methodObject24743;
  private static Method methodObject24694;
  private static Method methodObject24677;
  private static Method methodObject24653;
  private static Method methodObject24594;
  private static Method methodObject24339;
  private static Method methodObject24399;
  private static Method methodObject24586;
  private static Method methodObject24430;
  private static Method methodObject24507;
  private static Method methodObject24553;
  private static Method methodObject24364;
  private static Method methodObject24472;
  private static Method methodObject24537;
  private static Method methodObject24543;
  private static Method methodObject24689;
  private static Method methodObject24649;
  private static Method methodObject24478;
  private static Method methodObject24701;
  private static Method methodObject24458;
  private static Method methodObject24620;
  private static Method methodObject24735;
  private static Method methodObject24690;
  private static Method methodObject24726;
  private static Method methodObject24475;
  private static Method methodObject24332;
  private static Method methodObject24641;
  private static Method methodObject24651;
  private static Method methodObject24609;
  private static Method methodObject24490;
  private static Method methodObject24715;
  private static Method methodObject24502;
  private static Method methodObject24437;
  private static Method methodObject24508;
  private static Method methodObject24592;
  private static Method methodObject24573;
  private static Method methodObject24480;
  private static Method methodObject24474;
  private static Method methodObject24418;
  private static Method methodObject24451;
  private static Method methodObject24370;
  private static Method methodObject24402;
  private static Method methodObject24575;
  private static Method methodObject24342;
  private static Method methodObject24465;
  private static Method methodObject24422;
  private static Method methodObject24671;
  private static Method methodObject24340;
  private static Method methodObject24522;
  private static Method methodObject24548;
  private static Method methodObject24660;
  private static Method methodObject24466;
  private static Method methodObject24505;
  private static Method methodObject24711;
  private static Method methodObject24738;
  private static Method methodObject24645;
  private static Method methodObject24398;
  private static Method methodObject24563;
  private static Method methodObject24375;
  private static Method methodObject24492;
  private static Method methodObject24426;
  private static Method methodObject24459;
  private static Method methodObject24427;
  private static Method methodObject24380;
  private static Method methodObject24674;
  private static Method methodObject24416;
  private static Method methodObject24596;
  private static Method methodObject24527;
  private static Method methodObject24550;
  private static Method methodObject24533;
  private static Method methodObject24542;
  private static Method methodObject24598;
  private static Method methodObject24599;
  private static Method methodObject24378;
  private static Method methodObject24642;
  private static Method methodObject24369;
  private static Method methodObject24732;
  private static Method methodObject24530;
  private static Method methodObject24382;
  private static Method methodObject24565;
  private static Method methodObject24379;
  private static Method methodObject24654;
  private static Method methodObject24467;
  private static Method methodObject24528;
  private static Method methodObject24648;
  private static Method methodObject24500;
  private static Method methodObject24622;
  private static Method methodObject24384;
  private static Method methodObject24534;
  private static Method methodObject24590;
  private static Method methodObject24655;
  private static Method methodObject24725;
  private static Method methodObject24628;
  private static Method methodObject24748;
  private static Method methodObject24675;
  private static Method methodObject24572;
  private static Method methodObject24644;
  private static Method methodObject24709;
  private static Method methodObject24623;
  private static Method methodObject24470;
  private static Method methodObject24447;
  private static Method methodObject24488;
  private static Method methodObject24461;
  private static Method methodObject24643;
  private static Method methodObject24484;
  private static Method methodObject24616;
  private static Method methodObject24519;
  private static Method methodObject24432;
  private static Method methodObject24521;
  private static Method methodObject24719;
  private static Method methodObject24640;
  private static Method methodObject24703;
  private static Method methodObject24359;
  private static Method methodObject24469;
  private static Method methodObject24593;
  private static Method methodObject24741;
  private static Method methodObject24383;
  private static Method methodObject24392;
  private static Method methodObject24455;
  private static Method methodObject24368;
  private static Method methodObject24413;
  private static Method methodObject24414;
  private static Method methodObject24409;
  private static Method methodObject24692;
  private static Method methodObject24587;
  private static Method methodObject24559;
  private static Method methodObject24443;
  private static Method methodObject24619;
  private static Method methodObject24750;
  private static Method methodObject24566;
  private static Method methodObject24376;
  private static Method methodObject24403;
  private static Method methodObject24464;
  private static Method methodObject24476;
  private static Method methodObject24525;
  private static Method methodObject24442;
  private static Method methodObject24569;
  private static Method methodObject24545;
  private static Method methodObject24614;
  private static Method methodObject24438;
  private static Method methodObject24452;
  private static Method methodObject24460;
  private static Method methodObject24386;
  private static Method methodObject24503;
  private static Method methodObject24441;
  private static Method methodObject24667;
  private static Method methodObject24713;
  private static Method methodObject24481;
  private static Method methodObject24579;
  private static Method methodObject24656;
  private static Method methodObject24415;
  private static Method methodObject24721;
  private static Method methodObject24457;
  private static Method methodObject24637;
  private static Method methodObject24554;
  private static Method methodObject24345;
  private static Method methodObject24683;
  private static Method methodObject24440;
  private static Method methodObject24687;
  private static Method methodObject24638;
  private static Method methodObject24591;
  private static Method methodObject24714;
  private static Method methodObject24603;
  private static Method methodObject24439;
  private static Method methodObject24513;
  private static Method methodObject24604;
  private static Method methodObject24602;
  private static Method methodObject24523;
  private static Method methodObject24511;
  private static Method methodObject24652;
  private static Method methodObject24515;
  private static Method methodObject24625;
  private static Method methodObject24720;
  private static Method methodObject24578;
  private static Method methodObject24331;
  private static Method methodObject24581;
  private static Method methodObject24712;
  private static Method methodObject24352;
  private static Method methodObject24524;
  private static Method methodObject24546;
  private static Method methodObject24549;
  private static Method methodObject24722;
  private static Method methodObject24664;
  private static Method methodObject24657;
  private static Method methodObject24723;
  private static Method methodObject24407;
  private static Method methodObject24668;
  private static Method methodObject24681;
  private static Method methodObject24373;
  private static Method methodObject24589;
  private static Method methodObject24724;
  private static Method methodObject24693;
  private static Method methodObject24372;
  private static Method methodObject24728;
  private static Method methodObject24634;
  private static Method methodObject24742;
  private static Method methodObject24355;
  private static Method methodObject24436;
  private static Method methodObject24477;
  private static Method methodObject24730;
  private static Method methodObject24341;
  private static Method methodObject24658;
  private static Method methodObject24494;
  private static Method methodObject24487;
  private static Method methodObject24396;
  private static Method methodObject24567;
  private static Method methodObject24706;
  private static Method methodObject24351;
  private static Method methodObject24624;
  private static Method methodObject24423;
  private static Method methodObject24333;
  private static Method methodObject24401;
  private static Method methodObject24633;
  private static Method methodObject24734;
  private static Method methodObject24446;
  private static Method methodObject24544;
  private static Method methodObject24450;
  private static Method methodObject24400;
  private static Method methodObject24661;
  private static Method methodObject24691;
  private static Method methodObject24733;
  private static Method methodObject24454;
  private static Method methodObject24405;
  private static Method methodObject24350;
  private static Method methodObject24727;
  private static Method methodObject24395;
  private static Method methodObject24489;
  private static Method methodObject24381;
  private static Method methodObject24420;
  private static Method methodObject24408;
  private static Method methodObject24356;
  private static Method methodObject24343;
  private static Method methodObject24357;
  private static Method methodObject24338;
  private static Method methodObject24412;
  private static Method methodObject24600;
  private static Method methodObject24663;
  private static Method methodObject24344;
  private static Method methodObject24698;
  private static Method methodObject24717;
  private static Method methodObject24485;
  private static Method methodObject24736;
  private static Method methodObject24541;
  private static Method methodObject24577;
  private static Method methodObject24630;
  private static Method methodObject24498;
  private static Method methodObject24744;
  private static Method methodObject24666;
  private static Method methodObject24391;
  private static Method methodObject24349;
  private static Method methodObject24535;
  private static Method methodObject24684;
  private static Method methodObject24389;
  private static Method methodObject24576;
  private static Method methodObject24627;
  private static Method methodObject24747;
  private static Method methodObject24751;
  private static Method methodObject24428;
  private static Method methodObject24335;
  private static Method methodObject24417;
  private static Method methodObject24606;
  private static Method methodObject24397;
  private static Method methodObject24551;
  private static Method methodObject24704;
  private static Method methodObject24737;
  private static Method methodObject24506;
  private static Method methodObject24536;
  private static Method methodObject24739;
  private static Method methodObject24568;
  private static Method methodObject24669;
  private static Method methodObject24597;
  private static Method methodObject24448;
  private static Method methodObject24518;
  private static Method methodObject24626;
  private static Method methodObject24468;
  private static Method methodObject24558;
  private static Method methodObject24621;
  private static Method methodObject24676;
  private static Method methodObject24429;
  private static Method methodObject24680;
  private static Method methodObject24705;
  private static Method methodObject24444;
  private static Method methodObject24685;
  private static Method methodObject24337;
  private static Method methodObject24347;
  private static Method methodObject24486;
  private static Method methodObject24708;
  private static Method methodObject24404;
  private static Method methodObject24552;
  private static Method methodObject24612;
  private static Method methodObject24556;
  private static Method methodObject24510;
  private static Method methodObject24678;
  private static Method methodObject24483;
  private static Method methodObject24366;
  private static Method methodObject24473;
  private static Method methodObject24631;
  private static Method methodObject24538;
  private static Method methodObject24611;
  private static Method methodObject24647;
  private static Method methodObject24419;
  private static Method methodObject24632;
  private static Method methodObject24665;
  private static Method methodObject24588;
  private static Method methodObject24411;
  private static Method methodObject24557;
  private static Method methodObject24374;
  private static Method methodObject24571;
  private static Method methodObject24582;
  private static Method methodObject24583;
  private static Method methodObject24520;
  private static Method methodObject24362;
  private static Method methodObject24394;
  private static Method methodObject24718;
  private static Method methodObject24456;
  private static Method methodObject24699;
  private static Method methodObject24431;
  private static Method methodObject24570;
  private static Method methodObject24425;
  private static Method methodObject24334;
  private static Method methodObject24561;
  private static Method methodObject24731;
  private static Method methodObject24710;
  private static Method methodObject24629;
  private static Method methodObject24670;
  private static Method methodObject24367;
  private static Method methodObject24696;
  private static Method methodObject24393;
  private static Method methodObject24605;
  private static Method methodObject24547;
  private static Method methodObject24526;
  private static Method methodObject24516;
  private static Method methodObject24348;
  private static Method methodObject24504;
  private static Method methodObject24618;
  private static Method methodObject24695;
  private static Method methodObject24496;
  private static Method methodObject24562;
  private static Method methodObject24613;
  private static Method methodObject24501;
  private static Method methodObject24682;
  private static Method methodObject24421;
  private static Method methodObject24336;
  private static Method methodObject24463;
  private static Method methodObject24434;
  private static Method methodObject24495;
  private static Method methodObject24580;
  private static Method methodObject24453;
  private static Method methodObject24424;
  private static Method methodObject24363;
  private static Method methodObject24479;
  private static Method methodObject24745;
  private static Method methodObject24540;
  private static Method methodObject24688;
  private static Method methodObject24608;
  private static Method methodObject24385;
  private static Method methodObject24635;
  private static Method methodObject24564;
  private static Method methodObject24673;
  private static Method methodObject24514;
  private static Method methodObject24509;
  private static Method methodObject24659;
  private static Method methodObject24702;
  private static Method methodObject24517;
  private static Method methodObject24560;
  private static Method methodObject24595;
  private static Method methodObject24512;
  private static Method methodObject24646;
  private static Method methodObject24377;
  private static Method methodObject24716;
  private static Method methodObject24491;
  private static Method methodObject24462;
  private static Method methodObject24497;
  
  public void setBlobAtName(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24686, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24686, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24532, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24532, e);
    }
  }
  
  public ResultSet getCursor(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24371, this, new Object[] { Integer.valueOf(arg0) });
      return (ResultSet)postForAll(methodObject24371, this.proxyFactory.proxyForCache((Object)this.delegate.getCursor(arg0), this, this.proxyCache, methodObject24371));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24371, onErrorForAll(methodObject24371, e));
    }
  }
  
  public void setCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24636, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24636, e);
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24358, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24358, e);
    }
  }
  
  public void setTime(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24353, this, new Object[] { arg0, arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24353, e);
    }
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24740, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24740, e);
    }
  }
  
  public long getLong(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24445, this, new Object[] { arg0 });
      return ((Long)postForAll(methodObject24445, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject24445, onErrorForAll(methodObject24445, e))).longValue();
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24617, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24617, e);
    }
  }
  
  public void setINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24639, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24639, e);
    }
  }
  
  public Object getObject(int arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24433, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject24433, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject24433));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24433, onErrorForAll(methodObject24433, e));
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24471, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24471, e);
    }
  }
  
  public void setStringForClobAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24672, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24672, e);
    }
  }
  
  public void setBytesForBlob(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24406, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24406, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24697, this, new Object[] { arg0, arg1 });
      this.delegate.setDateAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24697, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24615, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24615, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24607, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24607, e);
    }
  }
  
  public void setString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24346, this, new Object[] { arg0, arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24346, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24700, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimeAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24700, e);
    }
  }
  
  public INTERVALDS getINTERVALDS(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24390, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALDS)postForAll(methodObject24390, (Object)this.delegate.getINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject24390, onErrorForAll(methodObject24390, e));
    }
  }
  
  public ROWID getROWID(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24387, this, new Object[] { Integer.valueOf(arg0) });
      return (ROWID)postForAll(methodObject24387, (Object)this.delegate.getROWID(arg0));
    }
    catch (SQLException e)
    {
      return (ROWID)postForAll(methodObject24387, onErrorForAll(methodObject24387, e));
    }
  }
  
  public STRUCT getSTRUCT(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24388, this, new Object[] { Integer.valueOf(arg0) });
      return (STRUCT)postForAll(methodObject24388, (Object)this.delegate.getSTRUCT(arg0));
    }
    catch (SQLException e)
    {
      return (STRUCT)postForAll(methodObject24388, onErrorForAll(methodObject24388, e));
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24574, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24574, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24574, onErrorForExecute(methodObject24574, e));
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24529, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24529, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24531, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24531, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24361, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24361, e);
    }
  }
  
  public boolean wasNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24482, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24482, Boolean.valueOf(this.delegate.wasNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24482, onErrorForAll(methodObject24482, e))).booleanValue();
    }
  }
  
  public Time getTime(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24493, this, new Object[] { arg0 });
      return (Time)postForAll(methodObject24493, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24493, onErrorForAll(methodObject24493, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24601, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24601, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24601, onErrorForAll(methodObject24601, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24729, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24729, e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24584, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24584, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24584, onErrorForAll(methodObject24584, e))).intValue();
    }
  }
  
  public void setRefType(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24650, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24650, e);
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24749, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24749, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24610, this, new Object[] { arg0 });
      return postForAll(methodObject24610, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24610, onErrorForAll(methodObject24610, e));
    }
  }
  
  public void setIntAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24662, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setIntAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24662, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24585, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24585, e);
    }
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24555, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24555, e);
    }
  }
  
  public double getDouble(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24449, this, new Object[] { arg0 });
      return ((Double)postForAll(methodObject24449, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject24449, onErrorForAll(methodObject24449, e))).doubleValue();
    }
  }
  
  public void setStructDescriptorAtName(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24679, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptorAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24679, e);
    }
  }
  
  public void setNull(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24365, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24365, e);
    }
  }
  
  public void setRAW(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24410, this, new Object[] { arg0, arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24410, e);
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24746, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24746, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24539, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject24539, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject24539));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject24539, onErrorForAll(methodObject24539, e));
    }
  }
  
  public void setTime(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24354, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24354, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24499, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24499, e);
    }
  }
  
  public Object getObject(String arg0, Map arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24435, this, new Object[] { arg0, arg1 });
      return postForAll(methodObject24435, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject24435));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24435, onErrorForAll(methodObject24435, e));
    }
  }
  
  public void setTIMESTAMPLTZAtName(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24707, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24707, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24360, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24360, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24743, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24743, e);
    }
  }
  
  public void setBfileAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24694, this, new Object[] { arg0, arg1 });
      this.delegate.setBfileAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24694, e);
    }
  }
  
  public void setARRAYAtName(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24677, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAYAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24677, e);
    }
  }
  
  public void defineParameterTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24653, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24653, e);
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24594, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24594, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject24594));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24594, onErrorForAll(methodObject24594, e));
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24339, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24339, e);
    }
  }
  
  public Datum[] getOraclePlsqlIndexTable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24399, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum[])postForAll(methodObject24399, (Object)this.delegate.getOraclePlsqlIndexTable(arg0));
    }
    catch (SQLException e)
    {
      return (Datum[])postForAll(methodObject24399, onErrorForAll(methodObject24399, e));
    }
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24586, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24586, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24586, onErrorForAll(methodObject24586, e))).intValue();
    }
  }
  
  public void setREF(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24430, this, new Object[] { arg0, arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24430, e);
    }
  }
  
  public void setClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24507, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24507, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24553, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24553, e);
    }
  }
  
  public void setClob(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24364, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24364, e);
    }
  }
  
  public Blob getBlob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24472, this, new Object[] { Integer.valueOf(arg0) });
      return (Blob)postForAll(methodObject24472, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject24472));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject24472, onErrorForAll(methodObject24472, e));
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24537, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24537, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24543, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24543, e);
    }
  }
  
  public void setCLOBAtName(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24689, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24689, e);
    }
  }
  
  public void setORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24649, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24649, e);
    }
  }
  
  public RowId getRowId(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24478, this, new Object[] { Integer.valueOf(arg0) });
      return (RowId)postForAll(methodObject24478, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject24478));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject24478, onErrorForAll(methodObject24478, e));
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24701, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestampAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24701, e);
    }
  }
  
  public String getString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24458, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject24458, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24458, onErrorForAll(methodObject24458, e));
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24620, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24620, e);
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24735, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24735, e);
    }
  }
  
  public void setClobAtName(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24690, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24690, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24726, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24726, e);
    }
  }
  
  public Clob getClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24475, this, new Object[] { arg0 });
      return (Clob)postForAll(methodObject24475, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject24475));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject24475, onErrorForAll(methodObject24475, e));
    }
  }
  
  public void setBoolean(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24332, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24332, e);
    }
  }
  
  public void setTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24641, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24641, e);
    }
  }
  
  public void setREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24651, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24651, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24609, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24609, e);
    }
  }
  
  public String getNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24490, this, new Object[] { arg0 });
      return (String)postForAll(methodObject24490, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24490, onErrorForAll(methodObject24490, e));
    }
  }
  
  public void setRefAtName(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24715, this, new Object[] { arg0, arg1 });
      this.delegate.setRefAtName(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24715, e);
    }
  }
  
  public void registerOutParameter(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24502, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.registerOutParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24502, e);
    }
  }
  
  public boolean getBoolean(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24437, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24437, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24437, onErrorForAll(methodObject24437, e))).booleanValue();
    }
  }
  
  public void setClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24508, this, new Object[] { arg0, arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24508, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24592, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24592, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24573, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24573, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24573, onErrorForExecute(methodObject24573, e));
    }
  }
  
  public SQLXML getSQLXML(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24480, this, new Object[] { Integer.valueOf(arg0) });
      return (SQLXML)postForAll(methodObject24480, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject24480));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject24480, onErrorForAll(methodObject24480, e));
    }
  }
  
  public Clob getClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24474, this, new Object[] { Integer.valueOf(arg0) });
      return (Clob)postForAll(methodObject24474, this.proxyFactory.proxyForCreate((Object)this.delegate.getClob(arg0), this, this.proxyCache, methodObject24474));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject24474, onErrorForAll(methodObject24474, e));
    }
  }
  
  public void setINTERVALYM(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24418, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24418, e);
    }
  }
  
  public byte[] getBytes(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24451, this, new Object[] { arg0 });
      return (byte[])postForAll(methodObject24451, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject24451, onErrorForAll(methodObject24451, e));
    }
  }
  
  public Datum getOracleObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24370, this, new Object[] { Integer.valueOf(arg0) });
      return (Datum)postForAll(methodObject24370, (Object)this.delegate.getOracleObject(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject24370, onErrorForAll(methodObject24370, e));
    }
  }
  
  public void setBinaryFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24402, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24402, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24575, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24575, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24342, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24342, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24465, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24465, e);
    }
  }
  
  public void setTIMESTAMPLTZ(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24422, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24422, e);
    }
  }
  
  public void setStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24671, this, new Object[] { arg0, arg1 });
      this.delegate.setStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24671, e);
    }
  }
  
  public void setTimestamp(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24340, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24340, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24522, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24522, e);
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24548, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24548, e);
    }
  }
  
  public void setByteAtName(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24660, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByteAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24660, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24466, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24466, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24505, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24505, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24711, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24711, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24738, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24738, e);
    }
  }
  
  public void setOPAQUE(int arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24645, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24645, e);
    }
  }
  
  public Object getPlsqlIndexTable(int arg0, Class arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24398, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject24398, this.proxyFactory.proxyForCache(this.delegate.getPlsqlIndexTable(arg0, arg1), this, this.proxyCache, methodObject24398));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24398, onErrorForAll(methodObject24398, e));
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24563, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24563, e);
    }
  }
  
  public BFILE getBfile(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24375, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject24375, (Object)this.delegate.getBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject24375, onErrorForAll(methodObject24375, e));
    }
  }
  
  public Time getTime(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24492, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Time)postForAll(methodObject24492, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24492, onErrorForAll(methodObject24492, e));
    }
  }
  
  public void setSTRUCT(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24426, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24426, e);
    }
  }
  
  public String getString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24459, this, new Object[] { arg0 });
      return (String)postForAll(methodObject24459, (Object)this.delegate.getString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24459, onErrorForAll(methodObject24459, e));
    }
  }
  
  public void setCustomDatum(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24427, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24427, e);
    }
  }
  
  public Object getORAData(int arg0, ORADataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24380, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject24380, this.proxyFactory.proxyForCache(this.delegate.getORAData(arg0, arg1), this, this.proxyCache, methodObject24380));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24380, onErrorForAll(methodObject24380, e));
    }
  }
  
  public void setCursorAtName(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24674, this, new Object[] { arg0, arg1 });
      this.delegate.setCursorAtName(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24674, e);
    }
  }
  
  public void setBFILE(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24416, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24416, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24596, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24596, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24596, onErrorForAll(methodObject24596, e))).intValue();
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24527, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24527, e);
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24550, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24550, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24533, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24533, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24542, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24542, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24598, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24598, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24598, onErrorForAll(methodObject24598, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24599, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24599, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24599, onErrorForAll(methodObject24599, e))).intValue();
    }
  }
  
  public CLOB getCLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24378, this, new Object[] { Integer.valueOf(arg0) });
      return (CLOB)postForAll(methodObject24378, (Object)this.delegate.getCLOB(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject24378, onErrorForAll(methodObject24378, e));
    }
  }
  
  public void setTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24642, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24642, e);
    }
  }
  
  public void setUnicodeStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24369, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24369, e);
    }
  }
  
  public void setRowIdAtName(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24732, this, new Object[] { arg0, arg1 });
      this.delegate.setRowIdAtName(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24732, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24530, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24530, e);
    }
  }
  
  public DATE getDATE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24382, this, new Object[] { Integer.valueOf(arg0) });
      return (DATE)postForAll(methodObject24382, (Object)this.delegate.getDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject24382, onErrorForAll(methodObject24382, e));
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24565, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24565, e);
    }
  }
  
  public Object getCustomDatum(int arg0, CustomDatumFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24379, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject24379, this.proxyFactory.proxyForCache(this.delegate.getCustomDatum(arg0, arg1), this, this.proxyCache, methodObject24379));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24379, onErrorForAll(methodObject24379, e));
    }
  }
  
  public void defineParameterTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24654, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24654, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24467, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24467, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24528, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24528, e);
    }
  }
  
  public void setCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24648, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24648, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24500, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24500, e);
    }
  }
  
  public void setBinaryFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24622, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24622, e);
    }
  }
  
  public OPAQUE getOPAQUE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24384, this, new Object[] { Integer.valueOf(arg0) });
      return (OPAQUE)postForAll(methodObject24384, (Object)this.delegate.getOPAQUE(arg0));
    }
    catch (SQLException e)
    {
      return (OPAQUE)postForAll(methodObject24384, onErrorForAll(methodObject24384, e));
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24534, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24534, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24590, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24590, e);
    }
  }
  
  public void defineParameterType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24655, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24655, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24725, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24725, e);
    }
  }
  
  public void setFixedCHAR(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24628, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24628, e);
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject24748, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24748, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public void setROWIDAtName(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24675, this, new Object[] { arg0, arg1 });
      this.delegate.setROWIDAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24675, e);
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24572, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject24572, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24572, onErrorForExecute(methodObject24572, e));
    }
  }
  
  public void setARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24644, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24644, e);
    }
  }
  
  public void setCustomDatumAtName(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24709, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatumAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24709, e);
    }
  }
  
  public void setBinaryFloat(int arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24623, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24623, e);
    }
  }
  
  public void setNCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24470, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24470, e);
    }
  }
  
  public float getFloat(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24447, this, new Object[] { arg0 });
      return ((Float)postForAll(methodObject24447, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject24447, onErrorForAll(methodObject24447, e))).floatValue();
    }
  }
  
  public Reader getNCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24488, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject24488, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24488, onErrorForAll(methodObject24488, e));
    }
  }
  
  public Date getDate(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24461, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Date)postForAll(methodObject24461, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24461, onErrorForAll(methodObject24461, e));
    }
  }
  
  public void setTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24643, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24643, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24484, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (BigDecimal)postForAll(methodObject24484, (Object)this.delegate.getBigDecimal(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24484, onErrorForAll(methodObject24484, e));
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24616, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24616, e);
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24519, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24519, e);
    }
  }
  
  public Object getObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24432, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject24432, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject24432));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24432, onErrorForAll(methodObject24432, e));
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24521, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24521, e);
    }
  }
  
  public void setCheckBindTypes(boolean arg0)
  {
    super.preForAll(methodObject24719, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCheckBindTypes(arg0);
  }
  
  public void setINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24640, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24640, e);
    }
  }
  
  public void setINTERVALYMAtName(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24703, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYMAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24703, e);
    }
  }
  
  public void setObject(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24359, this, new Object[] { arg0, arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24359, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24469, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24469, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24593, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24593, e);
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24741, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24741, e);
    }
  }
  
  public NUMBER getNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24383, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject24383, (Object)this.delegate.getNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject24383, onErrorForAll(methodObject24383, e));
    }
  }
  
  public TIMESTAMPTZ getTIMESTAMPTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24392, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPTZ)postForAll(methodObject24392, (Object)this.delegate.getTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject24392, onErrorForAll(methodObject24392, e));
    }
  }
  
  public URL getURL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24455, this, new Object[] { arg0 });
      return (URL)postForAll(methodObject24455, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject24455, onErrorForAll(methodObject24455, e));
    }
  }
  
  public void setRef(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24368, this, new Object[] { arg0, arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24368, e);
    }
  }
  
  public void setNUMBER(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24413, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24413, e);
    }
  }
  
  public void setBLOB(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24414, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24414, e);
    }
  }
  
  public void setROWID(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24409, this, new Object[] { arg0, arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24409, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24692, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24692, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24587, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24587, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24587, onErrorForAll(methodObject24587, e))).intValue();
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24559, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24559, e);
    }
  }
  
  public int getInt(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24443, this, new Object[] { arg0 });
      return ((Integer)postForAll(methodObject24443, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24443, onErrorForAll(methodObject24443, e))).intValue();
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24619, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24619, e);
    }
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24750, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject24750, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject24750, onErrorForAll(methodObject24750, e));
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24566, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24566, e);
    }
  }
  
  public BLOB getBLOB(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24376, this, new Object[] { Integer.valueOf(arg0) });
      return (BLOB)postForAll(methodObject24376, (Object)this.delegate.getBLOB(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject24376, onErrorForAll(methodObject24376, e));
    }
  }
  
  public void setBinaryDouble(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24403, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24403, e);
    }
  }
  
  public void setAsciiStream(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24464, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24464, e);
    }
  }
  
  public NClob getNClob(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24476, this, new Object[] { Integer.valueOf(arg0) });
      return (NClob)postForAll(methodObject24476, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject24476));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject24476, onErrorForAll(methodObject24476, e));
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24525, this, zeroLengthObjectArray);
      return postForExecute(methodObject24525, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24525, onErrorForExecute(methodObject24525, e));
    }
  }
  
  public int getInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24442, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject24442, Integer.valueOf(this.delegate.getInt(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24442, onErrorForAll(methodObject24442, e))).intValue();
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24569, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24569, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24545, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24545, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24614, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24614, e);
    }
  }
  
  public byte getByte(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24438, this, new Object[] { Integer.valueOf(arg0) });
      return ((Byte)postForAll(methodObject24438, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject24438, onErrorForAll(methodObject24438, e))).byteValue();
    }
  }
  
  public Array getArray(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24452, this, new Object[] { Integer.valueOf(arg0) });
      return (Array)postForAll(methodObject24452, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject24452));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject24452, onErrorForAll(methodObject24452, e));
    }
  }
  
  public Date getDate(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24460, this, new Object[] { Integer.valueOf(arg0) });
      return (Date)postForAll(methodObject24460, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24460, onErrorForAll(methodObject24460, e));
    }
  }
  
  public REF getREF(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24386, this, new Object[] { Integer.valueOf(arg0) });
      return (REF)postForAll(methodObject24386, (Object)this.delegate.getREF(arg0));
    }
    catch (SQLException e)
    {
      return (REF)postForAll(methodObject24386, onErrorForAll(methodObject24386, e));
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24503, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24503, e);
    }
  }
  
  public short getShort(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24441, this, new Object[] { arg0 });
      return ((Short)postForAll(methodObject24441, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject24441, onErrorForAll(methodObject24441, e))).shortValue();
    }
  }
  
  public void setBinaryFloatAtName(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24667, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24667, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24713, this, new Object[] { arg0, arg1 });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24713, e);
    }
  }
  
  public SQLXML getSQLXML(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24481, this, new Object[] { arg0 });
      return (SQLXML)postForAll(methodObject24481, this.proxyFactory.proxyForCreate((Object)this.delegate.getSQLXML(arg0), this, this.proxyCache, methodObject24481));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject24481, onErrorForAll(methodObject24481, e));
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24579, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24579, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24579, ((Integer)onErrorForAll(methodObject24579, e)).intValue());
    }
  }
  
  public int getExecuteBatch()
  {
    super.preForAll(methodObject24656, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24656, Integer.valueOf(this.delegate.getExecuteBatch()))).intValue();
  }
  
  public void setCLOB(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24415, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24415, e);
    }
  }
  
  public void setFormOfUse(int arg0, short arg1)
  {
    super.preForAll(methodObject24721, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
    this.delegate.setFormOfUse(arg0, arg1);
  }
  
  public Ref getRef(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24457, this, new Object[] { arg0 });
      return (Ref)postForAll(methodObject24457, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject24457));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject24457, onErrorForAll(methodObject24457, e));
    }
  }
  
  public void setBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24637, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24637, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24554, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24554, e);
    }
  }
  
  public void setBytes(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24345, this, new Object[] { arg0, arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24345, e);
    }
  }
  
  public void setDATEAtName(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24683, this, new Object[] { arg0, arg1 });
      this.delegate.setDATEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24683, e);
    }
  }
  
  public short getShort(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24440, this, new Object[] { Integer.valueOf(arg0) });
      return ((Short)postForAll(methodObject24440, Short.valueOf(this.delegate.getShort(arg0)))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject24440, onErrorForAll(methodObject24440, e))).shortValue();
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24687, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24687, e);
    }
  }
  
  public void setBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24638, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24638, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24591, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24591, e);
    }
  }
  
  public void setRefTypeAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24714, this, new Object[] { arg0, arg1 });
      this.delegate.setRefTypeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24714, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24603, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24603, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24603, onErrorForAll(methodObject24603, e))).booleanValue();
    }
  }
  
  public byte getByte(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24439, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject24439, Byte.valueOf(this.delegate.getByte(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject24439, onErrorForAll(methodObject24439, e))).byteValue();
    }
  }
  
  public void setRowId(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24513, this, new Object[] { arg0, arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24513, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24604, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24604, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24602, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24602, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24602, onErrorForAll(methodObject24602, e))).intValue();
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24523, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24523, e);
    }
  }
  
  public void setNClob(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24511, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24511, e);
    }
  }
  
  public void setOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24652, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24652, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24515, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24515, e);
    }
  }
  
  public void setBinaryDouble(int arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24625, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24625, e);
    }
  }
  
  public void setPlsqlIndexTable(int arg0, Object arg1, int arg2, int arg3, int arg4, int arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24720, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3), Integer.valueOf(arg4), Integer.valueOf(arg5) });
      this.delegate.setPlsqlIndexTable(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3, arg4, arg5);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24720, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24578, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject24578, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24578, ((Integer)onErrorForAll(methodObject24578, e)).intValue());
    }
  }
  
  public Object getObject(int arg0, OracleDataFactory arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24331, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return postForAll(methodObject24331, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0, arg1), this, this.proxyCache, methodObject24331));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24331, onErrorForAll(methodObject24331, e));
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject24581, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject24581, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject24581, onErrorForAll(methodObject24581, e));
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24712, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24712, e);
    }
  }
  
  public InputStream getUnicodeStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24352, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject24352, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject24352, onErrorForAll(methodObject24352, e));
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24524, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24524, e);
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24546, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24546, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24549, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24549, e);
    }
  }
  
  public void setDisableStmtCaching(boolean arg0)
  {
    super.preForAll(methodObject24722, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setDisableStmtCaching(arg0);
  }
  
  public void setFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24664, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24664, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24657, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNullAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24657, e);
    }
  }
  
  public OracleParameterMetaData OracleGetParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24723, this, zeroLengthObjectArray);
      return (OracleParameterMetaData)postForAll(methodObject24723, this.proxyFactory.proxyForCache((Object)this.delegate.OracleGetParameterMetaData(), this, this.proxyCache, methodObject24723));
    }
    catch (SQLException e)
    {
      return (OracleParameterMetaData)postForAll(methodObject24723, onErrorForAll(methodObject24723, e));
    }
  }
  
  public void setFixedCHAR(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24407, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24407, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24668, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24668, e);
    }
  }
  
  public void setRAWAtName(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24681, this, new Object[] { arg0, arg1 });
      this.delegate.setRAWAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24681, e);
    }
  }
  
  public ARRAY getARRAY(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24373, this, new Object[] { Integer.valueOf(arg0) });
      return (ARRAY)postForAll(methodObject24373, (Object)this.delegate.getARRAY(arg0));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject24373, onErrorForAll(methodObject24373, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24589, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24589, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24589, onErrorForAll(methodObject24589, e))).booleanValue();
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24724, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerReturnParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24724, e);
    }
  }
  
  public void setBFILEAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24693, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24693, e);
    }
  }
  
  public int sendBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24372, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24372, Integer.valueOf(this.delegate.sendBatch()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24372, onErrorForAll(methodObject24372, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24728, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24728, e);
    }
  }
  
  public void setNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24634, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24634, e);
    }
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject24742, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24742, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public void setDate(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24355, this, new Object[] { arg0, arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24355, e);
    }
  }
  
  public boolean getBoolean(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24436, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24436, Boolean.valueOf(this.delegate.getBoolean(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24436, onErrorForAll(methodObject24436, e))).booleanValue();
    }
  }
  
  public NClob getNClob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24477, this, new Object[] { arg0 });
      return (NClob)postForAll(methodObject24477, this.proxyFactory.proxyForCreate((Object)this.delegate.getNClob(arg0), this, this.proxyCache, methodObject24477));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject24477, onErrorForAll(methodObject24477, e));
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24730, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24730, e);
    }
  }
  
  public void setURL(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24341, this, new Object[] { arg0, arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24341, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24658, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNullAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24658, e);
    }
  }
  
  public Time getTime(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24494, this, new Object[] { arg0, arg1 });
      return (Time)postForAll(methodObject24494, (Object)this.delegate.getTime(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24494, onErrorForAll(methodObject24494, e));
    }
  }
  
  public Reader getNCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24487, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject24487, (Object)this.delegate.getNCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24487, onErrorForAll(methodObject24487, e));
    }
  }
  
  public void setExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24396, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setExecuteBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24396, e);
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24567, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24567, e);
    }
  }
  
  public void setTIMESTAMPTZAtName(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24706, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24706, e);
    }
  }
  
  public InputStream getUnicodeStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24351, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject24351, (Object)this.delegate.getUnicodeStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject24351, onErrorForAll(methodObject24351, e));
    }
  }
  
  public void setBinaryDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24624, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24624, e);
    }
  }
  
  public void setARRAY(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24423, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24423, e);
    }
  }
  
  public void setByte(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24333, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24333, e);
    }
  }
  
  public void setBinaryFloat(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24401, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24401, e);
    }
  }
  
  public void setDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24633, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24633, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24734, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24734, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24734, onErrorForAll(methodObject24734, e))).booleanValue();
    }
  }
  
  public float getFloat(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24446, this, new Object[] { Integer.valueOf(arg0) });
      return ((Float)postForAll(methodObject24446, Float.valueOf(this.delegate.getFloat(arg0)))).floatValue();
    }
    catch (SQLException e)
    {
      return ((Float)postForAll(methodObject24446, onErrorForAll(methodObject24446, e))).floatValue();
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24544, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24544, e);
    }
  }
  
  public byte[] getBytes(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24450, this, new Object[] { Integer.valueOf(arg0) });
      return (byte[])postForAll(methodObject24450, (Object)this.delegate.getBytes(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject24450, onErrorForAll(methodObject24450, e));
    }
  }
  
  public void registerIndexTableOutParameter(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24400, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerIndexTableOutParameter(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24400, e);
    }
  }
  
  public void setShortAtName(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24661, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShortAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24661, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24691, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24691, e);
    }
  }
  
  public void setSQLXMLAtName(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24733, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXMLAtName(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24733, e);
    }
  }
  
  public URL getURL(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24454, this, new Object[] { Integer.valueOf(arg0) });
      return (URL)postForAll(methodObject24454, (Object)this.delegate.getURL(arg0));
    }
    catch (SQLException e)
    {
      return (URL)postForAll(methodObject24454, onErrorForAll(methodObject24454, e));
    }
  }
  
  public void setStringForClob(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24405, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24405, e);
    }
  }
  
  public InputStream getAsciiStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24350, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject24350, (Object)this.delegate.getAsciiStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject24350, onErrorForAll(methodObject24350, e));
    }
  }
  
  public ResultSet getReturnResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24727, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24727, this.proxyFactory.proxyForCache((Object)this.delegate.getReturnResultSet(), this, this.proxyCache, methodObject24727));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24727, onErrorForAll(methodObject24727, e));
    }
  }
  
  public void registerOutParameterChars(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24395, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameterChars(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24395, e);
    }
  }
  
  public String getNString(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24489, this, new Object[] { Integer.valueOf(arg0) });
      return (String)postForAll(methodObject24489, (Object)this.delegate.getNString(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject24489, onErrorForAll(methodObject24489, e));
    }
  }
  
  public Object getAnyDataEmbeddedObject(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24381, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject24381, this.proxyFactory.proxyForCache(this.delegate.getAnyDataEmbeddedObject(arg0), this, this.proxyCache, methodObject24381));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24381, onErrorForAll(methodObject24381, e));
    }
  }
  
  public void setTIMESTAMP(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24420, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24420, e);
    }
  }
  
  public void setCursor(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24408, this, new Object[] { arg0, arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24408, e);
    }
  }
  
  public void setDate(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24356, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24356, e);
    }
  }
  
  public void setBinaryStream(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24343, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24343, e);
    }
  }
  
  public void setObject(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24357, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24357, e);
    }
  }
  
  public void setShort(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24338, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24338, e);
    }
  }
  
  public void setDATE(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24412, this, new Object[] { arg0, arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24412, e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24600, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24600, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24600, onErrorForAll(methodObject24600, e))).intValue();
    }
  }
  
  public void setLongAtName(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24663, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLongAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24663, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24344, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24344, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24698, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDateAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24698, e);
    }
  }
  
  public void setOracleObjectAtName(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24717, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObjectAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24717, e);
    }
  }
  
  public BigDecimal getBigDecimal(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24485, this, new Object[] { Integer.valueOf(arg0) });
      return (BigDecimal)postForAll(methodObject24485, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24485, onErrorForAll(methodObject24485, e));
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24736, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24736, e);
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24541, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24541, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24577, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject24577, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24577, ((Integer)onErrorForAll(methodObject24577, e)).intValue());
    }
  }
  
  public void setROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24630, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24630, e);
    }
  }
  
  public Timestamp getTimestamp(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24498, this, new Object[] { arg0, arg1 });
      return (Timestamp)postForAll(methodObject24498, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24498, onErrorForAll(methodObject24498, e));
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24744, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24744, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24666, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24666, e);
    }
  }
  
  public TIMESTAMP getTIMESTAMP(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24391, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMP)postForAll(methodObject24391, (Object)this.delegate.getTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject24391, onErrorForAll(methodObject24391, e));
    }
  }
  
  public Reader getCharacterStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24349, this, new Object[] { Integer.valueOf(arg0) });
      return (Reader)postForAll(methodObject24349, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24349, onErrorForAll(methodObject24349, e));
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24535, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24535, e);
    }
  }
  
  public void setNUMBERAtName(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24684, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBERAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24684, e);
    }
  }
  
  public INTERVALYM getINTERVALYM(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24389, this, new Object[] { Integer.valueOf(arg0) });
      return (INTERVALYM)postForAll(methodObject24389, (Object)this.delegate.getINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject24389, onErrorForAll(methodObject24389, e));
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24576, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24576, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject24576));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24576, onErrorForAll(methodObject24576, e));
    }
  }
  
  public void setBytesForBlob(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24627, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24627, e);
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24747, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24747, e);
    }
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24751, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject24751, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject24751, onErrorForAll(methodObject24751, e))).longValue();
    }
  }
  
  public void setORAData(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24428, this, new Object[] { arg0, arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24428, e);
    }
  }
  
  public void setFloat(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24335, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24335, e);
    }
  }
  
  public void setBfile(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24417, this, new Object[] { arg0, arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24417, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24606, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24606, e);
    }
  }
  
  public Object getPlsqlIndexTable(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24397, this, new Object[] { Integer.valueOf(arg0) });
      return postForAll(methodObject24397, this.proxyFactory.proxyForCache(this.delegate.getPlsqlIndexTable(arg0), this, this.proxyCache, methodObject24397));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24397, onErrorForAll(methodObject24397, e));
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24551, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24551, e);
    }
  }
  
  public void setINTERVALDSAtName(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24704, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDSAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24704, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24737, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24737, e);
    }
  }
  
  public void setBlob(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24506, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24506, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24536, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24536, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24739, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24739, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24568, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24568, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24669, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24669, e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24597, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24597, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24597, onErrorForAll(methodObject24597, e))).booleanValue();
    }
  }
  
  public double getDouble(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24448, this, new Object[] { Integer.valueOf(arg0) });
      return ((Double)postForAll(methodObject24448, Double.valueOf(this.delegate.getDouble(arg0)))).doubleValue();
    }
    catch (SQLException e)
    {
      return ((Double)postForAll(methodObject24448, onErrorForAll(methodObject24448, e))).doubleValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24518, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24518, e);
    }
  }
  
  public void setStringForClob(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24626, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24626, e);
    }
  }
  
  public void setCharacterStream(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24468, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24468, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24558, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24558, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24621, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24621, e);
    }
  }
  
  public void setArrayAtName(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24676, this, new Object[] { arg0, arg1 });
      this.delegate.setArrayAtName(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24676, e);
    }
  }
  
  public void setRefType(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24429, this, new Object[] { arg0, arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24429, e);
    }
  }
  
  public void setSTRUCTAtName(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24680, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCTAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24680, e);
    }
  }
  
  public void setTIMESTAMPAtName(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24705, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24705, e);
    }
  }
  
  public long getLong(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24444, this, new Object[] { Integer.valueOf(arg0) });
      return ((Long)postForAll(methodObject24444, Long.valueOf(this.delegate.getLong(arg0)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject24444, onErrorForAll(methodObject24444, e))).longValue();
    }
  }
  
  public void setBLOBAtName(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24685, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24685, e);
    }
  }
  
  public void setLong(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24337, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24337, e);
    }
  }
  
  public InputStream getBinaryStream(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24347, this, new Object[] { Integer.valueOf(arg0) });
      return (InputStream)postForAll(methodObject24347, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject24347, onErrorForAll(methodObject24347, e));
    }
  }
  
  public BigDecimal getBigDecimal(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24486, this, new Object[] { arg0 });
      return (BigDecimal)postForAll(methodObject24486, (Object)this.delegate.getBigDecimal(arg0));
    }
    catch (SQLException e)
    {
      return (BigDecimal)postForAll(methodObject24486, onErrorForAll(methodObject24486, e));
    }
  }
  
  public void setUnicodeStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24708, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24708, e);
    }
  }
  
  public void setBinaryDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24404, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24404, e);
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24552, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24552, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24612, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24612, e);
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24556, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24556, e);
    }
  }
  
  public void setNClob(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24510, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24510, e);
    }
  }
  
  public void setOPAQUEAtName(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24678, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24678, e);
    }
  }
  
  public Reader getCharacterStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24483, this, new Object[] { arg0 });
      return (Reader)postForAll(methodObject24483, (Object)this.delegate.getCharacterStream(arg0));
    }
    catch (SQLException e)
    {
      return (Reader)postForAll(methodObject24483, onErrorForAll(methodObject24483, e));
    }
  }
  
  public void setNull(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24366, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24366, e);
    }
  }
  
  public Blob getBlob(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24473, this, new Object[] { arg0 });
      return (Blob)postForAll(methodObject24473, this.proxyFactory.proxyForCreate((Object)this.delegate.getBlob(arg0), this, this.proxyCache, methodObject24473));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject24473, onErrorForAll(methodObject24473, e));
    }
  }
  
  public void setRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24631, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24631, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24538, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject24538, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject24538));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24538, (ResultSet)onErrorForAll(methodObject24538, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24611, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24611, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24611, onErrorForAll(methodObject24611, e))).booleanValue();
    }
  }
  
  public void setSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24647, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24647, e);
    }
  }
  
  public void setINTERVALDS(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24419, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24419, e);
    }
  }
  
  public void setCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24632, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24632, e);
    }
  }
  
  public void setDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24665, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24665, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24588, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject24588, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject24588, onErrorForAll(methodObject24588, e));
    }
  }
  
  public void setCHAR(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24411, this, new Object[] { arg0, arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24411, e);
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24557, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24557, e);
    }
  }
  
  public BFILE getBFILE(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24374, this, new Object[] { Integer.valueOf(arg0) });
      return (BFILE)postForAll(methodObject24374, (Object)this.delegate.getBFILE(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject24374, onErrorForAll(methodObject24374, e));
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24571, this, new Object[] { arg0 });
      return postForExecute(methodObject24571, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24571, onErrorForExecute(methodObject24571, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24582, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject24582, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject24582));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24582, (ResultSet)onErrorForAll(methodObject24582, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24583, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject24583, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject24583, onErrorForAll(methodObject24583, e));
    }
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24520, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24520, e);
    }
  }
  
  public void setBigDecimal(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24362, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24362, e);
    }
  }
  
  public void registerOutParameterBytes(int arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24394, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.registerOutParameterBytes(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24394, e);
    }
  }
  
  public void setURLAtName(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24718, this, new Object[] { arg0, arg1 });
      this.delegate.setURLAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24718, e);
    }
  }
  
  public Ref getRef(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24456, this, new Object[] { Integer.valueOf(arg0) });
      return (Ref)postForAll(methodObject24456, this.proxyFactory.proxyForCreate((Object)this.delegate.getRef(arg0), this, this.proxyCache, methodObject24456));
    }
    catch (SQLException e)
    {
      return (Ref)postForAll(methodObject24456, onErrorForAll(methodObject24456, e));
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24699, this, new Object[] { arg0, arg1 });
      this.delegate.setTimeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24699, e);
    }
  }
  
  public void setOracleObject(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24431, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24431, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24570, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject24570);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject24570, e);
    }
  }
  
  public void setStructDescriptor(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24425, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24425, e);
    }
  }
  
  public void setDouble(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24334, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24334, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24561, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24561, e);
    }
  }
  
  public void setNStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24731, this, new Object[] { arg0, arg1 });
      this.delegate.setNStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24731, e);
    }
  }
  
  public void setORADataAtName(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24710, this, new Object[] { arg0, arg1 });
      this.delegate.setORADataAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24710, e);
    }
  }
  
  public void setCursor(int arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24629, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24629, e);
    }
  }
  
  public void setBigDecimalAtName(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24670, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimalAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24670, e);
    }
  }
  
  public void setArray(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24367, this, new Object[] { arg0, arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24367, e);
    }
  }
  
  public void setBytesForBlobAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24696, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24696, e);
    }
  }
  
  public TIMESTAMPLTZ getTIMESTAMPLTZ(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24393, this, new Object[] { Integer.valueOf(arg0) });
      return (TIMESTAMPLTZ)postForAll(methodObject24393, (Object)this.delegate.getTIMESTAMPLTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject24393, onErrorForAll(methodObject24393, e));
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24605, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24605, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24547, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24547, e);
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24526, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject24526, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24526, ((Integer)onErrorForAll(methodObject24526, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24516, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24516, e);
    }
  }
  
  public InputStream getBinaryStream(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24348, this, new Object[] { arg0 });
      return (InputStream)postForAll(methodObject24348, (Object)this.delegate.getBinaryStream(arg0));
    }
    catch (SQLException e)
    {
      return (InputStream)postForAll(methodObject24348, onErrorForAll(methodObject24348, e));
    }
  }
  
  public void registerOutParameter(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24504, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24504, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24618, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24618, e);
    }
  }
  
  public void setBytesAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24695, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24695, e);
    }
  }
  
  public Timestamp getTimestamp(int arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24496, this, new Object[] { Integer.valueOf(arg0), arg1 });
      return (Timestamp)postForAll(methodObject24496, (Object)this.delegate.getTimestamp(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24496, onErrorForAll(methodObject24496, e));
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24562, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24562, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24613, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24613, e);
    }
  }
  
  public void registerOutParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24501, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerOutParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24501, e);
    }
  }
  
  public void setCHARAtName(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24682, this, new Object[] { arg0, arg1 });
      this.delegate.setCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24682, e);
    }
  }
  
  public void setTIMESTAMPTZ(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24421, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24421, e);
    }
  }
  
  public void setInt(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24336, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24336, e);
    }
  }
  
  public Date getDate(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24463, this, new Object[] { arg0, arg1 });
      return (Date)postForAll(methodObject24463, (Object)this.delegate.getDate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24463, onErrorForAll(methodObject24463, e));
    }
  }
  
  public Object getObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24434, this, new Object[] { arg0 });
      return postForAll(methodObject24434, this.proxyFactory.proxyForCache(this.delegate.getObject(arg0), this, this.proxyCache, methodObject24434));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24434, onErrorForAll(methodObject24434, e));
    }
  }
  
  public Timestamp getTimestamp(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24495, this, new Object[] { Integer.valueOf(arg0) });
      return (Timestamp)postForAll(methodObject24495, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24495, onErrorForAll(methodObject24495, e));
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24580, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24580, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24580, ((Integer)onErrorForAll(methodObject24580, e)).intValue());
    }
  }
  
  public Array getArray(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24453, this, new Object[] { arg0 });
      return (Array)postForAll(methodObject24453, this.proxyFactory.proxyForCreate((Object)this.delegate.getArray(arg0), this, this.proxyCache, methodObject24453));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject24453, onErrorForAll(methodObject24453, e));
    }
  }
  
  public void setOPAQUE(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24424, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24424, e);
    }
  }
  
  public void setBlob(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24363, this, new Object[] { arg0, arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24363, e);
    }
  }
  
  public RowId getRowId(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24479, this, new Object[] { arg0 });
      return (RowId)postForAll(methodObject24479, this.proxyFactory.proxyForCreate((Object)this.delegate.getRowId(arg0), this, this.proxyCache, methodObject24479));
    }
    catch (SQLException e)
    {
      return (RowId)postForAll(methodObject24479, onErrorForAll(methodObject24479, e));
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject24745, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24745, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24540, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject24540, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject24540));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject24540, onErrorForAll(methodObject24540, e));
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24688, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24688, e);
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24608, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24608, e);
    }
  }
  
  public RAW getRAW(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24385, this, new Object[] { Integer.valueOf(arg0) });
      return (RAW)postForAll(methodObject24385, (Object)this.delegate.getRAW(arg0));
    }
    catch (SQLException e)
    {
      return (RAW)postForAll(methodObject24385, onErrorForAll(methodObject24385, e));
    }
  }
  
  public void setBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24635, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24635, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24564, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24564, e);
    }
  }
  
  public void setFixedCHARAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24673, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24673, e);
    }
  }
  
  public void setSQLXML(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24514, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24514, e);
    }
  }
  
  public void setNClob(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24509, this, new Object[] { arg0, arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24509, e);
    }
  }
  
  public void setBooleanAtName(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24659, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBooleanAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24659, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24702, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestampAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24702, e);
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24517, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24517, e);
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24560, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24560, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24595, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24595, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24595, onErrorForAll(methodObject24595, e))).intValue();
    }
  }
  
  public void setNString(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24512, this, new Object[] { arg0, arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24512, e);
    }
  }
  
  public void setStructDescriptor(int arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24646, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24646, e);
    }
  }
  
  public CHAR getCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24377, this, new Object[] { Integer.valueOf(arg0) });
      return (CHAR)postForAll(methodObject24377, (Object)this.delegate.getCHAR(arg0));
    }
    catch (SQLException e)
    {
      return (CHAR)postForAll(methodObject24377, onErrorForAll(methodObject24377, e));
    }
  }
  
  public void setREFAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24716, this, new Object[] { arg0, arg1 });
      this.delegate.setREFAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24716, e);
    }
  }
  
  public Time getTime(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24491, this, new Object[] { Integer.valueOf(arg0) });
      return (Time)postForAll(methodObject24491, (Object)this.delegate.getTime(arg0));
    }
    catch (SQLException e)
    {
      return (Time)postForAll(methodObject24491, onErrorForAll(methodObject24491, e));
    }
  }
  
  public Date getDate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24462, this, new Object[] { arg0 });
      return (Date)postForAll(methodObject24462, (Object)this.delegate.getDate(arg0));
    }
    catch (SQLException e)
    {
      return (Date)postForAll(methodObject24462, onErrorForAll(methodObject24462, e));
    }
  }
  
  public Timestamp getTimestamp(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24497, this, new Object[] { arg0 });
      return (Timestamp)postForAll(methodObject24497, (Object)this.delegate.getTimestamp(arg0));
    }
    catch (SQLException e)
    {
      return (Timestamp)postForAll(methodObject24497, onErrorForAll(methodObject24497, e));
    }
  }
  
  public OracleCallableStatement _getDelegate_()
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
      methodObject24686 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, Blob.class });
      methodObject24532 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24371 = OracleCallableStatement.class.getDeclaredMethod("getCursor", new Class[] { Integer.TYPE });
      methodObject24636 = OraclePreparedStatement.class.getDeclaredMethod("setCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject24358 = OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject24353 = OracleCallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class });
      methodObject24740 = OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24445 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { String.class });
      methodObject24617 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject24639 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject24433 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, Map.class });
      methodObject24471 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class });
      methodObject24672 = OraclePreparedStatement.class.getDeclaredMethod("setStringForClobAtName", new Class[] { String.class, String.class });
      methodObject24406 = OracleCallableStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { String.class, byte[].class });
      methodObject24697 = OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class });
      methodObject24615 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24607 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24346 = OracleCallableStatement.class.getDeclaredMethod("setString", new Class[] { String.class, String.class });
      methodObject24700 = OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class, Calendar.class });
      methodObject24390 = OracleCallableStatement.class.getDeclaredMethod("getINTERVALDS", new Class[] { Integer.TYPE });
      methodObject24387 = OracleCallableStatement.class.getDeclaredMethod("getROWID", new Class[] { Integer.TYPE });
      methodObject24388 = OracleCallableStatement.class.getDeclaredMethod("getSTRUCT", new Class[] { Integer.TYPE });
      methodObject24574 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject24529 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24531 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24361 = OracleCallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24482 = CallableStatement.class.getDeclaredMethod("wasNull", new Class[0]);
      methodObject24493 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class });
      methodObject24601 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject24729 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24584 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject24650 = OraclePreparedStatement.class.getDeclaredMethod("setRefType", new Class[] { Integer.TYPE, REF.class });
      methodObject24749 = OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject24610 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject24662 = OraclePreparedStatement.class.getDeclaredMethod("setIntAtName", new Class[] { String.class, Integer.TYPE });
      methodObject24585 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject24555 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject24449 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { String.class });
      methodObject24679 = OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptorAtName", new Class[] { String.class, StructDescriptor.class });
      methodObject24365 = OracleCallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24410 = OracleCallableStatement.class.getDeclaredMethod("setRAW", new Class[] { String.class, RAW.class });
      methodObject24746 = OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject24539 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject24354 = OracleCallableStatement.class.getDeclaredMethod("setTime", new Class[] { String.class, Time.class, Calendar.class });
      methodObject24499 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24435 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class, Map.class });
      methodObject24707 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZAtName", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject24360 = OracleCallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24743 = OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject24694 = OraclePreparedStatement.class.getDeclaredMethod("setBfileAtName", new Class[] { String.class, BFILE.class });
      methodObject24677 = OraclePreparedStatement.class.getDeclaredMethod("setARRAYAtName", new Class[] { String.class, ARRAY.class });
      methodObject24653 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24594 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject24339 = OracleCallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class });
      methodObject24399 = OracleCallableStatement.class.getDeclaredMethod("getOraclePlsqlIndexTable", new Class[] { Integer.TYPE });
      methodObject24586 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject24430 = OracleCallableStatement.class.getDeclaredMethod("setREF", new Class[] { String.class, REF.class });
      methodObject24507 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24553 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24364 = OracleCallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Clob.class });
      methodObject24472 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { Integer.TYPE });
      methodObject24537 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24543 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject24689 = OraclePreparedStatement.class.getDeclaredMethod("setCLOBAtName", new Class[] { String.class, CLOB.class });
      methodObject24649 = OraclePreparedStatement.class.getDeclaredMethod("setORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject24478 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { Integer.TYPE });
      methodObject24701 = OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class });
      methodObject24458 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { Integer.TYPE });
      methodObject24620 = OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24735 = OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject24690 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Clob.class });
      methodObject24726 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24475 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { String.class });
      methodObject24332 = OracleCallableStatement.class.getDeclaredMethod("setBoolean", new Class[] { String.class, Boolean.TYPE });
      methodObject24641 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject24651 = OraclePreparedStatement.class.getDeclaredMethod("setREF", new Class[] { Integer.TYPE, REF.class });
      methodObject24609 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject24490 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { String.class });
      methodObject24715 = OraclePreparedStatement.class.getDeclaredMethod("setRefAtName", new Class[] { String.class, Ref.class });
      methodObject24502 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE });
      methodObject24437 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { String.class });
      methodObject24508 = CallableStatement.class.getDeclaredMethod("setClob", new Class[] { String.class, Reader.class });
      methodObject24592 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject24573 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject24480 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { Integer.TYPE });
      methodObject24474 = CallableStatement.class.getDeclaredMethod("getClob", new Class[] { Integer.TYPE });
      methodObject24418 = OracleCallableStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { String.class, INTERVALYM.class });
      methodObject24451 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { String.class });
      methodObject24370 = OracleCallableStatement.class.getDeclaredMethod("getOracleObject", new Class[] { Integer.TYPE });
      methodObject24402 = OracleCallableStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { String.class, Float.TYPE });
      methodObject24575 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject24342 = OracleCallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24465 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class });
      methodObject24422 = OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject24671 = OraclePreparedStatement.class.getDeclaredMethod("setStringAtName", new Class[] { String.class, String.class });
      methodObject24340 = OracleCallableStatement.class.getDeclaredMethod("setTimestamp", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject24522 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject24548 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject24660 = OraclePreparedStatement.class.getDeclaredMethod("setByteAtName", new Class[] { String.class, Byte.TYPE });
      methodObject24466 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24505 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24711 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24738 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject24645 = OraclePreparedStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { Integer.TYPE, OPAQUE.class });
      methodObject24398 = OracleCallableStatement.class.getDeclaredMethod("getPlsqlIndexTable", new Class[] { Integer.TYPE, Class.class });
      methodObject24563 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24375 = OracleCallableStatement.class.getDeclaredMethod("getBfile", new Class[] { Integer.TYPE });
      methodObject24492 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24426 = OracleCallableStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { String.class, STRUCT.class });
      methodObject24459 = CallableStatement.class.getDeclaredMethod("getString", new Class[] { String.class });
      methodObject24427 = OracleCallableStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { String.class, CustomDatum.class });
      methodObject24380 = OracleCallableStatement.class.getDeclaredMethod("getORAData", new Class[] { Integer.TYPE, ORADataFactory.class });
      methodObject24674 = OraclePreparedStatement.class.getDeclaredMethod("setCursorAtName", new Class[] { String.class, ResultSet.class });
      methodObject24416 = OracleCallableStatement.class.getDeclaredMethod("setBFILE", new Class[] { String.class, BFILE.class });
      methodObject24596 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject24527 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24550 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject24533 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject24542 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject24598 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject24599 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject24378 = OracleCallableStatement.class.getDeclaredMethod("getCLOB", new Class[] { Integer.TYPE });
      methodObject24642 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject24369 = OracleCallableStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24732 = OraclePreparedStatement.class.getDeclaredMethod("setRowIdAtName", new Class[] { String.class, RowId.class });
      methodObject24530 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24382 = OracleCallableStatement.class.getDeclaredMethod("getDATE", new Class[] { Integer.TYPE });
      methodObject24565 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject24379 = OracleCallableStatement.class.getDeclaredMethod("getCustomDatum", new Class[] { Integer.TYPE, CustomDatumFactory.class });
      methodObject24654 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24467 = CallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class });
      methodObject24528 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24648 = OraclePreparedStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject24500 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24622 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24384 = OracleCallableStatement.class.getDeclaredMethod("getOPAQUE", new Class[] { Integer.TYPE });
      methodObject24534 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24590 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject24655 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24725 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24628 = OraclePreparedStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { Integer.TYPE, String.class });
      methodObject24748 = OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject24675 = OraclePreparedStatement.class.getDeclaredMethod("setROWIDAtName", new Class[] { String.class, ROWID.class });
      methodObject24572 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject24644 = OraclePreparedStatement.class.getDeclaredMethod("setARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject24709 = OraclePreparedStatement.class.getDeclaredMethod("setCustomDatumAtName", new Class[] { String.class, CustomDatum.class });
      methodObject24623 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, BINARY_FLOAT.class });
      methodObject24470 = CallableStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24447 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { String.class });
      methodObject24488 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { String.class });
      methodObject24461 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24643 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject24484 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24616 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24519 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24432 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE });
      methodObject24521 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24719 = OraclePreparedStatement.class.getDeclaredMethod("setCheckBindTypes", new Class[] { Boolean.TYPE });
      methodObject24640 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject24703 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYMAtName", new Class[] { String.class, INTERVALYM.class });
      methodObject24359 = OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class });
      methodObject24469 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class });
      methodObject24593 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject24741 = OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24383 = OracleCallableStatement.class.getDeclaredMethod("getNUMBER", new Class[] { Integer.TYPE });
      methodObject24392 = OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMPTZ", new Class[] { Integer.TYPE });
      methodObject24455 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { String.class });
      methodObject24368 = OracleCallableStatement.class.getDeclaredMethod("setRef", new Class[] { String.class, Ref.class });
      methodObject24413 = OracleCallableStatement.class.getDeclaredMethod("setNUMBER", new Class[] { String.class, NUMBER.class });
      methodObject24414 = OracleCallableStatement.class.getDeclaredMethod("setBLOB", new Class[] { String.class, BLOB.class });
      methodObject24409 = OracleCallableStatement.class.getDeclaredMethod("setROWID", new Class[] { String.class, ROWID.class });
      methodObject24692 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class });
      methodObject24587 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject24559 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24443 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { String.class });
      methodObject24619 = OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject24750 = OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject24566 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject24376 = OracleCallableStatement.class.getDeclaredMethod("getBLOB", new Class[] { Integer.TYPE });
      methodObject24403 = OracleCallableStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject24464 = CallableStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24476 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { Integer.TYPE });
      methodObject24525 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject24442 = CallableStatement.class.getDeclaredMethod("getInt", new Class[] { Integer.TYPE });
      methodObject24569 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24545 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject24614 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject24438 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { Integer.TYPE });
      methodObject24452 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { Integer.TYPE });
      methodObject24460 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { Integer.TYPE });
      methodObject24386 = OracleCallableStatement.class.getDeclaredMethod("getREF", new Class[] { Integer.TYPE });
      methodObject24503 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject24441 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { String.class });
      methodObject24667 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject24713 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class });
      methodObject24481 = CallableStatement.class.getDeclaredMethod("getSQLXML", new Class[] { String.class });
      methodObject24579 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject24656 = OraclePreparedStatement.class.getDeclaredMethod("getExecuteBatch", new Class[0]);
      methodObject24415 = OracleCallableStatement.class.getDeclaredMethod("setCLOB", new Class[] { String.class, CLOB.class });
      methodObject24721 = OraclePreparedStatement.class.getDeclaredMethod("setFormOfUse", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24457 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { String.class });
      methodObject24637 = OraclePreparedStatement.class.getDeclaredMethod("setBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject24554 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24345 = OracleCallableStatement.class.getDeclaredMethod("setBytes", new Class[] { String.class, byte[].class });
      methodObject24683 = OraclePreparedStatement.class.getDeclaredMethod("setDATEAtName", new Class[] { String.class, DATE.class });
      methodObject24440 = CallableStatement.class.getDeclaredMethod("getShort", new Class[] { Integer.TYPE });
      methodObject24687 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24638 = OraclePreparedStatement.class.getDeclaredMethod("setBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject24591 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject24714 = OraclePreparedStatement.class.getDeclaredMethod("setRefTypeAtName", new Class[] { String.class, REF.class });
      methodObject24603 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject24439 = CallableStatement.class.getDeclaredMethod("getByte", new Class[] { String.class });
      methodObject24513 = CallableStatement.class.getDeclaredMethod("setRowId", new Class[] { String.class, RowId.class });
      methodObject24604 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject24602 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24523 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject24511 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class });
      methodObject24652 = OraclePreparedStatement.class.getDeclaredMethod("setOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject24515 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject24625 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, BINARY_DOUBLE.class });
      methodObject24720 = OraclePreparedStatement.class.getDeclaredMethod("setPlsqlIndexTable", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24578 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject24331 = OracleCallableStatement.class.getDeclaredMethod("getObject", new Class[] { Integer.TYPE, OracleDataFactory.class });
      methodObject24581 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject24712 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject24352 = OracleCallableStatement.class.getDeclaredMethod("getUnicodeStream", new Class[] { String.class });
      methodObject24524 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject24546 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject24549 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24722 = OraclePreparedStatement.class.getDeclaredMethod("setDisableStmtCaching", new Class[] { Boolean.TYPE });
      methodObject24664 = OraclePreparedStatement.class.getDeclaredMethod("setFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject24657 = OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24723 = OraclePreparedStatement.class.getDeclaredMethod("OracleGetParameterMetaData", new Class[0]);
      methodObject24407 = OracleCallableStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { String.class, String.class });
      methodObject24668 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject24681 = OraclePreparedStatement.class.getDeclaredMethod("setRAWAtName", new Class[] { String.class, RAW.class });
      methodObject24373 = OracleCallableStatement.class.getDeclaredMethod("getARRAY", new Class[] { Integer.TYPE });
      methodObject24589 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject24724 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24693 = OraclePreparedStatement.class.getDeclaredMethod("setBFILEAtName", new Class[] { String.class, BFILE.class });
      methodObject24372 = OracleCallableStatement.class.getDeclaredMethod("sendBatch", new Class[0]);
      methodObject24728 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, NClob.class });
      methodObject24634 = OraclePreparedStatement.class.getDeclaredMethod("setNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject24742 = OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject24355 = OracleCallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class });
      methodObject24436 = CallableStatement.class.getDeclaredMethod("getBoolean", new Class[] { Integer.TYPE });
      methodObject24477 = CallableStatement.class.getDeclaredMethod("getNClob", new Class[] { String.class });
      methodObject24730 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class });
      methodObject24341 = OracleCallableStatement.class.getDeclaredMethod("setURL", new Class[] { String.class, URL.class });
      methodObject24658 = OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE });
      methodObject24494 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { String.class, Calendar.class });
      methodObject24487 = CallableStatement.class.getDeclaredMethod("getNCharacterStream", new Class[] { Integer.TYPE });
      methodObject24396 = OracleCallableStatement.class.getDeclaredMethod("setExecuteBatch", new Class[] { Integer.TYPE });
      methodObject24567 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject24706 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZAtName", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject24351 = OracleCallableStatement.class.getDeclaredMethod("getUnicodeStream", new Class[] { Integer.TYPE });
      methodObject24624 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24423 = OracleCallableStatement.class.getDeclaredMethod("setARRAY", new Class[] { String.class, ARRAY.class });
      methodObject24333 = OracleCallableStatement.class.getDeclaredMethod("setByte", new Class[] { String.class, Byte.TYPE });
      methodObject24401 = OracleCallableStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject24633 = OraclePreparedStatement.class.getDeclaredMethod("setDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject24734 = OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject24446 = CallableStatement.class.getDeclaredMethod("getFloat", new Class[] { Integer.TYPE });
      methodObject24544 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject24450 = CallableStatement.class.getDeclaredMethod("getBytes", new Class[] { Integer.TYPE });
      methodObject24400 = OracleCallableStatement.class.getDeclaredMethod("registerIndexTableOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24661 = OraclePreparedStatement.class.getDeclaredMethod("setShortAtName", new Class[] { String.class, Short.TYPE });
      methodObject24691 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24733 = OraclePreparedStatement.class.getDeclaredMethod("setSQLXMLAtName", new Class[] { String.class, SQLXML.class });
      methodObject24454 = CallableStatement.class.getDeclaredMethod("getURL", new Class[] { Integer.TYPE });
      methodObject24405 = OracleCallableStatement.class.getDeclaredMethod("setStringForClob", new Class[] { String.class, String.class });
      methodObject24350 = OracleCallableStatement.class.getDeclaredMethod("getAsciiStream", new Class[] { Integer.TYPE });
      methodObject24727 = OraclePreparedStatement.class.getDeclaredMethod("getReturnResultSet", new Class[0]);
      methodObject24395 = OracleCallableStatement.class.getDeclaredMethod("registerOutParameterChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24489 = CallableStatement.class.getDeclaredMethod("getNString", new Class[] { Integer.TYPE });
      methodObject24381 = OracleCallableStatement.class.getDeclaredMethod("getAnyDataEmbeddedObject", new Class[] { Integer.TYPE });
      methodObject24420 = OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { String.class, TIMESTAMP.class });
      methodObject24408 = OracleCallableStatement.class.getDeclaredMethod("setCursor", new Class[] { String.class, ResultSet.class });
      methodObject24356 = OracleCallableStatement.class.getDeclaredMethod("setDate", new Class[] { String.class, Date.class, Calendar.class });
      methodObject24343 = OracleCallableStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24357 = OracleCallableStatement.class.getDeclaredMethod("setObject", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24338 = OracleCallableStatement.class.getDeclaredMethod("setShort", new Class[] { String.class, Short.TYPE });
      methodObject24412 = OracleCallableStatement.class.getDeclaredMethod("setDATE", new Class[] { String.class, DATE.class });
      methodObject24600 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject24663 = OraclePreparedStatement.class.getDeclaredMethod("setLongAtName", new Class[] { String.class, Long.TYPE });
      methodObject24344 = OracleCallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject24698 = OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class, Calendar.class });
      methodObject24717 = OraclePreparedStatement.class.getDeclaredMethod("setOracleObjectAtName", new Class[] { String.class, Datum.class });
      methodObject24485 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { Integer.TYPE });
      methodObject24736 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24541 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject24577 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject24630 = OraclePreparedStatement.class.getDeclaredMethod("setROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject24498 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class, Calendar.class });
      methodObject24744 = OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject24666 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject24391 = OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMP", new Class[] { Integer.TYPE });
      methodObject24349 = OracleCallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { Integer.TYPE });
      methodObject24535 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24684 = OraclePreparedStatement.class.getDeclaredMethod("setNUMBERAtName", new Class[] { String.class, NUMBER.class });
      methodObject24389 = OracleCallableStatement.class.getDeclaredMethod("getINTERVALYM", new Class[] { Integer.TYPE });
      methodObject24576 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject24627 = OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { Integer.TYPE, byte[].class });
      methodObject24747 = OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject24751 = OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject24428 = OracleCallableStatement.class.getDeclaredMethod("setORAData", new Class[] { String.class, ORAData.class });
      methodObject24335 = OracleCallableStatement.class.getDeclaredMethod("setFloat", new Class[] { String.class, Float.TYPE });
      methodObject24417 = OracleCallableStatement.class.getDeclaredMethod("setBfile", new Class[] { String.class, BFILE.class });
      methodObject24606 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24397 = OracleCallableStatement.class.getDeclaredMethod("getPlsqlIndexTable", new Class[] { Integer.TYPE });
      methodObject24551 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject24704 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDSAtName", new Class[] { String.class, INTERVALDS.class });
      methodObject24737 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24506 = CallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, InputStream.class });
      methodObject24536 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24739 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24568 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject24669 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject24597 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject24448 = CallableStatement.class.getDeclaredMethod("getDouble", new Class[] { Integer.TYPE });
      methodObject24518 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24626 = OraclePreparedStatement.class.getDeclaredMethod("setStringForClob", new Class[] { Integer.TYPE, String.class });
      methodObject24468 = CallableStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24558 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject24621 = OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject24676 = OraclePreparedStatement.class.getDeclaredMethod("setArrayAtName", new Class[] { String.class, Array.class });
      methodObject24429 = OracleCallableStatement.class.getDeclaredMethod("setRefType", new Class[] { String.class, REF.class });
      methodObject24680 = OraclePreparedStatement.class.getDeclaredMethod("setSTRUCTAtName", new Class[] { String.class, STRUCT.class });
      methodObject24705 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPAtName", new Class[] { String.class, TIMESTAMP.class });
      methodObject24444 = CallableStatement.class.getDeclaredMethod("getLong", new Class[] { Integer.TYPE });
      methodObject24685 = OraclePreparedStatement.class.getDeclaredMethod("setBLOBAtName", new Class[] { String.class, BLOB.class });
      methodObject24337 = OracleCallableStatement.class.getDeclaredMethod("setLong", new Class[] { String.class, Long.TYPE });
      methodObject24347 = OracleCallableStatement.class.getDeclaredMethod("getBinaryStream", new Class[] { Integer.TYPE });
      methodObject24486 = CallableStatement.class.getDeclaredMethod("getBigDecimal", new Class[] { String.class });
      methodObject24708 = OraclePreparedStatement.class.getDeclaredMethod("setUnicodeStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24404 = OracleCallableStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { String.class, Double.TYPE });
      methodObject24552 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject24612 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24556 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24510 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24678 = OraclePreparedStatement.class.getDeclaredMethod("setOPAQUEAtName", new Class[] { String.class, OPAQUE.class });
      methodObject24483 = CallableStatement.class.getDeclaredMethod("getCharacterStream", new Class[] { String.class });
      methodObject24366 = OracleCallableStatement.class.getDeclaredMethod("setNull", new Class[] { String.class, Integer.TYPE });
      methodObject24473 = CallableStatement.class.getDeclaredMethod("getBlob", new Class[] { String.class });
      methodObject24631 = OraclePreparedStatement.class.getDeclaredMethod("setRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject24538 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject24611 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24647 = OraclePreparedStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject24419 = OracleCallableStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { String.class, INTERVALDS.class });
      methodObject24632 = OraclePreparedStatement.class.getDeclaredMethod("setCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject24665 = OraclePreparedStatement.class.getDeclaredMethod("setDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject24588 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject24411 = OracleCallableStatement.class.getDeclaredMethod("setCHAR", new Class[] { String.class, CHAR.class });
      methodObject24557 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24374 = OracleCallableStatement.class.getDeclaredMethod("getBFILE", new Class[] { Integer.TYPE });
      methodObject24571 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject24582 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject24583 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject24520 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject24362 = OracleCallableStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { String.class, BigDecimal.class });
      methodObject24394 = OracleCallableStatement.class.getDeclaredMethod("registerOutParameterBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24718 = OraclePreparedStatement.class.getDeclaredMethod("setURLAtName", new Class[] { String.class, URL.class });
      methodObject24456 = CallableStatement.class.getDeclaredMethod("getRef", new Class[] { Integer.TYPE });
      methodObject24699 = OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class });
      methodObject24431 = OracleCallableStatement.class.getDeclaredMethod("setOracleObject", new Class[] { String.class, Datum.class });
      methodObject24570 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject24425 = OracleCallableStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { String.class, StructDescriptor.class });
      methodObject24334 = OracleCallableStatement.class.getDeclaredMethod("setDouble", new Class[] { String.class, Double.TYPE });
      methodObject24561 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject24731 = OraclePreparedStatement.class.getDeclaredMethod("setNStringAtName", new Class[] { String.class, String.class });
      methodObject24710 = OraclePreparedStatement.class.getDeclaredMethod("setORADataAtName", new Class[] { String.class, ORAData.class });
      methodObject24629 = OraclePreparedStatement.class.getDeclaredMethod("setCursor", new Class[] { Integer.TYPE, ResultSet.class });
      methodObject24670 = OraclePreparedStatement.class.getDeclaredMethod("setBigDecimalAtName", new Class[] { String.class, BigDecimal.class });
      methodObject24367 = OracleCallableStatement.class.getDeclaredMethod("setArray", new Class[] { String.class, Array.class });
      methodObject24696 = OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlobAtName", new Class[] { String.class, byte[].class });
      methodObject24393 = OracleCallableStatement.class.getDeclaredMethod("getTIMESTAMPLTZ", new Class[] { Integer.TYPE });
      methodObject24605 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject24547 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject24526 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject24516 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject24348 = OracleCallableStatement.class.getDeclaredMethod("getBinaryStream", new Class[] { String.class });
      methodObject24504 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24618 = OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24695 = OraclePreparedStatement.class.getDeclaredMethod("setBytesAtName", new Class[] { String.class, byte[].class });
      methodObject24496 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE, Calendar.class });
      methodObject24562 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24613 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24501 = CallableStatement.class.getDeclaredMethod("registerOutParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24682 = OraclePreparedStatement.class.getDeclaredMethod("setCHARAtName", new Class[] { String.class, CHAR.class });
      methodObject24421 = OracleCallableStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject24336 = OracleCallableStatement.class.getDeclaredMethod("setInt", new Class[] { String.class, Integer.TYPE });
      methodObject24463 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class, Calendar.class });
      methodObject24434 = CallableStatement.class.getDeclaredMethod("getObject", new Class[] { String.class });
      methodObject24495 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { Integer.TYPE });
      methodObject24580 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject24453 = CallableStatement.class.getDeclaredMethod("getArray", new Class[] { String.class });
      methodObject24424 = OracleCallableStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { String.class, OPAQUE.class });
      methodObject24363 = OracleCallableStatement.class.getDeclaredMethod("setBlob", new Class[] { String.class, Blob.class });
      methodObject24479 = CallableStatement.class.getDeclaredMethod("getRowId", new Class[] { String.class });
      methodObject24745 = OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject24540 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject24688 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class });
      methodObject24608 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject24385 = OracleCallableStatement.class.getDeclaredMethod("getRAW", new Class[] { Integer.TYPE });
      methodObject24635 = OraclePreparedStatement.class.getDeclaredMethod("setBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject24564 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject24673 = OraclePreparedStatement.class.getDeclaredMethod("setFixedCHARAtName", new Class[] { String.class, String.class });
      methodObject24514 = CallableStatement.class.getDeclaredMethod("setSQLXML", new Class[] { String.class, SQLXML.class });
      methodObject24509 = CallableStatement.class.getDeclaredMethod("setNClob", new Class[] { String.class, NClob.class });
      methodObject24659 = OraclePreparedStatement.class.getDeclaredMethod("setBooleanAtName", new Class[] { String.class, Boolean.TYPE });
      methodObject24702 = OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject24517 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24560 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24595 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject24512 = CallableStatement.class.getDeclaredMethod("setNString", new Class[] { String.class, String.class });
      methodObject24646 = OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { Integer.TYPE, StructDescriptor.class });
      methodObject24377 = OracleCallableStatement.class.getDeclaredMethod("getCHAR", new Class[] { Integer.TYPE });
      methodObject24716 = OraclePreparedStatement.class.getDeclaredMethod("setREFAtName", new Class[] { String.class, REF.class });
      methodObject24491 = CallableStatement.class.getDeclaredMethod("getTime", new Class[] { Integer.TYPE });
      methodObject24462 = CallableStatement.class.getDeclaredMethod("getDate", new Class[] { String.class });
      methodObject24497 = CallableStatement.class.getDeclaredMethod("getTimestamp", new Class[] { String.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleCallableStatement$$$Proxy(OracleCallableStatement paramOracleCallableStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleCallableStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OracleCallableStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */