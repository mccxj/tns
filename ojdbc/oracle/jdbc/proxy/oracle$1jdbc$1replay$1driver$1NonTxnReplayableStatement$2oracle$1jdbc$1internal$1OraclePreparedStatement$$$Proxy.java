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
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.OPAQUE;
import oracle.sql.ORAData;
import oracle.sql.RAW;
import oracle.sql.REF;
import oracle.sql.ROWID;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OraclePreparedStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements oracle.jdbc.internal.OraclePreparedStatement, _Proxy_
{
  private oracle.jdbc.internal.OraclePreparedStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25582;
  private static Method methodObject25563;
  private static Method methodObject25609;
  private static Method methodObject25646;
  private static Method methodObject25496;
  private static Method methodObject25532;
  private static Method methodObject25693;
  private static Method methodObject25552;
  private static Method methodObject25616;
  private static Method methodObject25750;
  private static Method methodObject25498;
  private static Method methodObject25499;
  private static Method methodObject25732;
  private static Method methodObject25511;
  private static Method methodObject25533;
  private static Method methodObject25668;
  private static Method methodObject25579;
  private static Method methodObject25583;
  private static Method methodObject25535;
  private static Method methodObject25534;
  private static Method methodObject25705;
  private static Method methodObject25610;
  private static Method methodObject25568;
  private static Method methodObject25717;
  private static Method methodObject25593;
  private static Method methodObject25509;
  private static Method methodObject25721;
  private static Method methodObject25596;
  private static Method methodObject25718;
  private static Method methodObject25716;
  private static Method methodObject25548;
  private static Method methodObject25637;
  private static Method methodObject25629;
  private static Method methodObject25688;
  private static Method methodObject25521;
  private static Method methodObject25643;
  private static Method methodObject25645;
  private static Method methodObject25692;
  private static Method methodObject25615;
  private static Method methodObject25695;
  private static Method methodObject25715;
  private static Method methodObject25608;
  private static Method methodObject25624;
  private static Method methodObject25698;
  private static Method methodObject25546;
  private static Method methodObject25741;
  private static Method methodObject25638;
  private static Method methodObject25660;
  private static Method methodObject25724;
  private static Method methodObject25663;
  private static Method methodObject25558;
  private static Method methodObject25699;
  private static Method methodObject25617;
  private static Method methodObject25669;
  private static Method methodObject25560;
  private static Method methodObject25553;
  private static Method methodObject25618;
  private static Method methodObject25575;
  private static Method methodObject25564;
  private static Method methodObject25738;
  private static Method methodObject25577;
  private static Method methodObject25653;
  private static Method methodObject25703;
  private static Method methodObject25603;
  private static Method methodObject25619;
  private static Method methodObject25735;
  private static Method methodObject25590;
  private static Method methodObject25589;
  private static Method methodObject25749;
  private static Method methodObject25623;
  private static Method methodObject25573;
  private static Method methodObject25530;
  private static Method methodObject25549;
  private static Method methodObject25708;
  private static Method methodObject25734;
  private static Method methodObject25700;
  private static Method methodObject25625;
  private static Method methodObject25748;
  private static Method methodObject25554;
  private static Method methodObject25667;
  private static Method methodObject25745;
  private static Method methodObject25651;
  private static Method methodObject25747;
  private static Method methodObject25517;
  private static Method methodObject25602;
  private static Method methodObject25681;
  private static Method methodObject25520;
  private static Method methodObject25529;
  private static Method methodObject25585;
  private static Method methodObject25657;
  private static Method methodObject25726;
  private static Method methodObject25545;
  private static Method methodObject25597;
  private static Method methodObject25514;
  private static Method methodObject25658;
  private static Method methodObject25587;
  private static Method methodObject25557;
  private static Method methodObject25628;
  private static Method methodObject25727;
  private static Method methodObject25622;
  private static Method methodObject25586;
  private static Method methodObject25621;
  private static Method methodObject25547;
  private static Method methodObject25537;
  private static Method methodObject25723;
  private static Method methodObject25714;
  private static Method methodObject25559;
  private static Method methodObject25611;
  private static Method methodObject25594;
  private static Method methodObject25500;
  private static Method methodObject25502;
  private static Method methodObject25613;
  private static Method methodObject25728;
  private static Method methodObject25691;
  private static Method methodObject25526;
  private static Method methodObject25655;
  private static Method methodObject25706;
  private static Method methodObject25687;
  private static Method methodObject25736;
  private static Method methodObject25562;
  private static Method methodObject25689;
  private static Method methodObject25580;
  private static Method methodObject25649;
  private static Method methodObject25690;
  private static Method methodObject25523;
  private static Method methodObject25567;
  private static Method methodObject25636;
  private static Method methodObject25662;
  private static Method methodObject25556;
  private static Method methodObject25739;
  private static Method methodObject25607;
  private static Method methodObject25743;
  private static Method methodObject25730;
  private static Method methodObject25541;
  private static Method methodObject25720;
  private static Method methodObject25600;
  private static Method methodObject25665;
  private static Method methodObject25729;
  private static Method methodObject25650;
  private static Method methodObject25731;
  private static Method methodObject25682;
  private static Method methodObject25677;
  private static Method methodObject25565;
  private static Method methodObject25711;
  private static Method methodObject25632;
  private static Method methodObject25522;
  private static Method methodObject25672;
  private static Method methodObject25515;
  private static Method methodObject25572;
  private static Method methodObject25576;
  private static Method methodObject25601;
  private static Method methodObject25581;
  private static Method methodObject25570;
  private static Method methodObject25710;
  private static Method methodObject25641;
  private static Method methodObject25664;
  private static Method methodObject25604;
  private static Method methodObject25647;
  private static Method methodObject25656;
  private static Method methodObject25666;
  private static Method methodObject25506;
  private static Method methodObject25712;
  private static Method methodObject25713;
  private static Method methodObject25670;
  private static Method methodObject25752;
  private static Method methodObject25574;
  private static Method methodObject25501;
  private static Method methodObject25538;
  private static Method methodObject25627;
  private static Method methodObject25644;
  private static Method methodObject25527;
  private static Method methodObject25652;
  private static Method methodObject25725;
  private static Method methodObject25504;
  private static Method methodObject25679;
  private static Method methodObject25550;
  private static Method methodObject25642;
  private static Method methodObject25543;
  private static Method methodObject25544;
  private static Method methodObject25518;
  private static Method methodObject25561;
  private static Method methodObject25528;
  private static Method methodObject25648;
  private static Method methodObject25704;
  private static Method methodObject25702;
  private static Method methodObject25671;
  private static Method methodObject25696;
  private static Method methodObject25551;
  private static Method methodObject25685;
  private static Method methodObject25697;
  private static Method methodObject25620;
  private static Method methodObject25524;
  private static Method methodObject25740;
  private static Method methodObject25634;
  private static Method methodObject25571;
  private static Method methodObject25595;
  private static Method methodObject25614;
  private static Method methodObject25686;
  private static Method methodObject25540;
  private static Method methodObject25605;
  private static Method methodObject25684;
  private static Method methodObject25519;
  private static Method methodObject25675;
  private static Method methodObject25626;
  private static Method methodObject25539;
  private static Method methodObject25606;
  private static Method methodObject25566;
  private static Method methodObject25525;
  private static Method methodObject25746;
  private static Method methodObject25510;
  private static Method methodObject25633;
  private static Method methodObject25592;
  private static Method methodObject25635;
  private static Method methodObject25497;
  private static Method methodObject25536;
  private static Method methodObject25719;
  private static Method methodObject25503;
  private static Method methodObject25599;
  private static Method methodObject25661;
  private static Method methodObject25744;
  private static Method methodObject25707;
  private static Method methodObject25733;
  private static Method methodObject25640;
  private static Method methodObject25630;
  private static Method methodObject25512;
  private static Method methodObject25591;
  private static Method methodObject25751;
  private static Method methodObject25588;
  private static Method methodObject25676;
  private static Method methodObject25507;
  private static Method methodObject25701;
  private static Method methodObject25578;
  private static Method methodObject25505;
  private static Method methodObject25673;
  private static Method methodObject25513;
  private static Method methodObject25742;
  private static Method methodObject25680;
  private static Method methodObject25694;
  private static Method methodObject25639;
  private static Method methodObject25737;
  private static Method methodObject25584;
  private static Method methodObject25654;
  private static Method methodObject25722;
  private static Method methodObject25531;
  private static Method methodObject25678;
  private static Method methodObject25683;
  private static Method methodObject25569;
  private static Method methodObject25659;
  private static Method methodObject25555;
  private static Method methodObject25508;
  private static Method methodObject25598;
  private static Method methodObject25631;
  private static Method methodObject25709;
  private static Method methodObject25674;
  private static Method methodObject25542;
  private static Method methodObject25612;
  
  public void setBlobAtName(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25582, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25582, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25563, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25563, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25609, this, new Object[] { arg0, arg1 });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25609, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25646, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25646, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25496, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25496, e);
    }
  }
  
  public void setCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25532, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25532, e);
    }
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25693, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25693, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25693, ((Integer)onErrorForAll(methodObject25693, e)).intValue());
    }
  }
  
  public int getExecuteBatch()
  {
    super.preForAll(methodObject25552, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25552, Integer.valueOf(this.delegate.getExecuteBatch()))).intValue();
  }
  
  public void setFormOfUse(int arg0, short arg1)
  {
    super.preForAll(methodObject25616, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
    this.delegate.setFormOfUse(arg0, arg1);
  }
  
  public boolean getserverCursor()
  {
    super.preForAll(methodObject25750, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25750, Boolean.valueOf(this.delegate.getserverCursor()))).booleanValue();
  }
  
  public void setInternalBytes(int arg0, byte[] arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25498, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setInternalBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25498, e);
    }
  }
  
  public void enterImplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25499, this, zeroLengthObjectArray);
      this.delegate.enterImplicitCache();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25499, e);
    }
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25732, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25732, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25511, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25511, e);
    }
  }
  
  public void setBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25533, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25533, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25668, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25668, e);
    }
  }
  
  public void setDATEAtName(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25579, this, new Object[] { arg0, arg1 });
      this.delegate.setDATEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25579, e);
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25583, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25583, e);
    }
  }
  
  public void setINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25535, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25535, e);
    }
  }
  
  public void setBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25534, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25534, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25705, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25705, e);
    }
  }
  
  public void setRefTypeAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25610, this, new Object[] { arg0, arg1 });
      this.delegate.setRefTypeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25610, e);
    }
  }
  
  public void setStringForClobAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25568, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25568, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25717, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25717, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25717, onErrorForAll(methodObject25717, e))).booleanValue();
    }
  }
  
  public void setDateAtName(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25593, this, new Object[] { arg0, arg1 });
      this.delegate.setDateAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25593, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25509, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25509, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25721, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25721, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25596, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimeAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25596, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25718, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25718, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25716, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25716, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25716, onErrorForAll(methodObject25716, e))).intValue();
    }
  }
  
  public void setOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25548, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25548, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25637, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25637, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25629, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25629, e);
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25688, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25688, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25688, onErrorForExecute(methodObject25688, e));
    }
  }
  
  public void setBinaryDouble(int arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25521, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25521, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25643, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25643, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25645, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25645, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25692, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject25692, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25692, ((Integer)onErrorForAll(methodObject25692, e)).intValue());
    }
  }
  
  public void setPlsqlIndexTable(int arg0, Object arg1, int arg2, int arg3, int arg4, int arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25615, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3), Integer.valueOf(arg4), Integer.valueOf(arg5) });
      this.delegate.setPlsqlIndexTable(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3, arg4, arg5);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25615, e);
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject25695, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject25695, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject25695, onErrorForAll(methodObject25695, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25715, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25715, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25715, onErrorForAll(methodObject25715, e))).intValue();
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25608, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25608, e);
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25624, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25624, e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25698, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25698, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25698, onErrorForAll(methodObject25698, e))).intValue();
    }
  }
  
  public void setRefType(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25546, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25546, e);
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25741, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25741, e);
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25638, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25638, e);
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25660, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25660, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25724, this, new Object[] { arg0 });
      return postForAll(methodObject25724, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject25724, onErrorForAll(methodObject25724, e));
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25663, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25663, e);
    }
  }
  
  public void setIntAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25558, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setIntAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25558, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25699, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25699, e);
    }
  }
  
  public void setDisableStmtCaching(boolean arg0)
  {
    super.preForAll(methodObject25617, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setDisableStmtCaching(arg0);
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25669, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25669, e);
    }
  }
  
  public void setFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25560, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25560, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25553, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNullAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25553, e);
    }
  }
  
  public OracleParameterMetaData OracleGetParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25618, this, zeroLengthObjectArray);
      return (OracleParameterMetaData)postForAll(methodObject25618, this.proxyFactory.proxyForCache((Object)this.delegate.OracleGetParameterMetaData(), this, this.proxyCache, methodObject25618));
    }
    catch (SQLException e)
    {
      return (OracleParameterMetaData)postForAll(methodObject25618, onErrorForAll(methodObject25618, e));
    }
  }
  
  public void setStructDescriptorAtName(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25575, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptorAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25575, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25564, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25564, e);
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25738, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25738, e);
    }
  }
  
  public void setRAWAtName(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25577, this, new Object[] { arg0, arg1 });
      this.delegate.setRAWAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25577, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25653, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject25653, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject25653));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject25653, onErrorForAll(methodObject25653, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25703, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25703, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25703, onErrorForAll(methodObject25703, e))).booleanValue();
    }
  }
  
  public void setTIMESTAMPLTZAtName(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25603, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25603, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25619, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerReturnParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25619, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25735, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25735, e);
    }
  }
  
  public void setBfileAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25590, this, new Object[] { arg0, arg1 });
      this.delegate.setBfileAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25590, e);
    }
  }
  
  public void setBFILEAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25589, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25589, e);
    }
  }
  
  public int sendBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25749, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25749, Integer.valueOf(this.delegate.sendBatch()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25749, onErrorForAll(methodObject25749, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25623, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25623, e);
    }
  }
  
  public void setARRAYAtName(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25573, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAYAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25573, e);
    }
  }
  
  public void setNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25530, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25530, e);
    }
  }
  
  public void defineParameterTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25549, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25549, e);
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25708, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25708, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject25708));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25708, onErrorForAll(methodObject25708, e));
    }
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject25734, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25734, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25700, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25700, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25700, onErrorForAll(methodObject25700, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25625, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25625, e);
    }
  }
  
  public boolean getFixedString()
  {
    super.preForAll(methodObject25748, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject25748, Boolean.valueOf(this.delegate.getFixedString()))).booleanValue();
  }
  
  public void setNullAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25554, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNullAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25554, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25667, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25667, e);
    }
  }
  
  public long getChecksum()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25745, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25745, Long.valueOf(this.delegate.getChecksum()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25745, onErrorForAll(methodObject25745, e))).longValue();
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25651, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25651, e);
    }
  }
  
  public void setFixedString(boolean arg0)
  {
    super.preForAll(methodObject25747, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setFixedString(arg0);
  }
  
  public void setExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25517, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setExecuteBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25517, e);
    }
  }
  
  public void setTIMESTAMPTZAtName(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25602, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25602, e);
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25681, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25681, e);
    }
  }
  
  public void setBinaryDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25520, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25520, e);
    }
  }
  
  public void setDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25529, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25529, e);
    }
  }
  
  public void setCLOBAtName(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25585, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25585, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25657, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25657, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25726, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25726, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25726, onErrorForAll(methodObject25726, e))).booleanValue();
    }
  }
  
  public void setORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25545, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25545, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25597, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestampAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25597, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25514, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25514, e);
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25658, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25658, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25587, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25587, e);
    }
  }
  
  public void setShortAtName(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25557, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShortAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25557, e);
    }
  }
  
  public void setSQLXMLAtName(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25628, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXMLAtName(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25628, e);
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25727, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25727, e);
    }
  }
  
  public ResultSet getReturnResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25622, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25622, this.proxyFactory.proxyForCache((Object)this.delegate.getReturnResultSet(), this, this.proxyCache, methodObject25622));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25622, onErrorForAll(methodObject25622, e));
    }
  }
  
  public void setClobAtName(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25586, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25586, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25621, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25621, e);
    }
  }
  
  public void setREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25547, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25547, e);
    }
  }
  
  public void setTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25537, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25537, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25723, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25723, e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25714, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25714, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25714, onErrorForAll(methodObject25714, e))).intValue();
    }
  }
  
  public void setLongAtName(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25559, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLongAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25559, e);
    }
  }
  
  public void setRefAtName(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25611, this, new Object[] { arg0, arg1 });
      this.delegate.setRefAtName(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25611, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25594, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDateAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25594, e);
    }
  }
  
  public void enterExplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25500, this, zeroLengthObjectArray);
      this.delegate.enterExplicitCache();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25500, e);
    }
  }
  
  public void exitExplicitCacheToActive()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25502, this, zeroLengthObjectArray);
      this.delegate.exitExplicitCacheToActive();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25502, e);
    }
  }
  
  public void setOracleObjectAtName(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25613, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObjectAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25613, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25728, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25728, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25691, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject25691, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25691, ((Integer)onErrorForAll(methodObject25691, e)).intValue());
    }
  }
  
  public void setROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25526, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25526, e);
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25655, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25655, e);
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25706, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25706, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25687, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject25687, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25687, onErrorForExecute(methodObject25687, e));
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25736, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25736, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25562, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25562, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25689, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25689, e);
    }
  }
  
  public void setNUMBERAtName(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25580, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBERAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25580, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25649, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25649, e);
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25690, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject25690, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject25690));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject25690, onErrorForAll(methodObject25690, e));
    }
  }
  
  public void setBytesForBlob(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25523, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25523, e);
    }
  }
  
  public void setStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25567, this, new Object[] { arg0, arg1 });
      this.delegate.setStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25567, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25636, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25636, e);
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25662, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25662, e);
    }
  }
  
  public void setByteAtName(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25556, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByteAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25556, e);
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25739, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25739, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25607, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25607, e);
    }
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25743, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject25743, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject25743, onErrorForAll(methodObject25743, e))).longValue();
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25730, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25730, e);
    }
  }
  
  public void setOPAQUE(int arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25541, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25541, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25720, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25720, e);
    }
  }
  
  public void setINTERVALDSAtName(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25600, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDSAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25600, e);
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25665, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25665, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25729, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25729, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25650, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25650, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25731, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25731, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25682, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25682, e);
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25677, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25677, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25565, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25565, e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25711, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject25711, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25711, onErrorForAll(methodObject25711, e))).booleanValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25632, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25632, e);
    }
  }
  
  public void setStringForClob(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25522, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25522, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25672, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25672, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25515, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25515, e);
    }
  }
  
  public void setArrayAtName(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25572, this, new Object[] { arg0, arg1 });
      this.delegate.setArrayAtName(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25572, e);
    }
  }
  
  public void setSTRUCTAtName(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25576, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCTAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25576, e);
    }
  }
  
  public void setTIMESTAMPAtName(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25601, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25601, e);
    }
  }
  
  public void setBLOBAtName(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25581, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25581, e);
    }
  }
  
  public void setCursorAtName(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25570, this, new Object[] { arg0, arg1 });
      this.delegate.setCursorAtName(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25570, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25710, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25710, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25710, onErrorForAll(methodObject25710, e))).intValue();
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25641, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25641, e);
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25664, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25664, e);
    }
  }
  
  public void setUnicodeStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25604, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25604, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25647, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25647, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25656, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25656, e);
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25666, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25666, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25506, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25506, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25712, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject25712, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25712, onErrorForAll(methodObject25712, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25713, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25713, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25713, onErrorForAll(methodObject25713, e))).intValue();
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25670, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25670, e);
    }
  }
  
  public int getstatementType()
  {
    super.preForAll(methodObject25752, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25752, Integer.valueOf(this.delegate.getstatementType()))).intValue();
  }
  
  public void setOPAQUEAtName(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25574, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25574, e);
    }
  }
  
  public void exitImplicitCacheToActive()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25501, this, zeroLengthObjectArray);
      this.delegate.exitImplicitCacheToActive();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25501, e);
    }
  }
  
  public void setTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25538, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25538, e);
    }
  }
  
  public void setRowIdAtName(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25627, this, new Object[] { arg0, arg1 });
      this.delegate.setRowIdAtName(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25627, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25644, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25644, e);
    }
  }
  
  public void setRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25527, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25527, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25652, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject25652, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject25652));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25652, (ResultSet)onErrorForAll(methodObject25652, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25725, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject25725, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject25725, onErrorForAll(methodObject25725, e))).booleanValue();
    }
  }
  
  public void exitExplicitCacheToClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25504, this, zeroLengthObjectArray);
      this.delegate.exitExplicitCacheToClose();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25504, e);
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25679, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25679, e);
    }
  }
  
  public void defineParameterTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25550, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25550, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25642, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25642, e);
    }
  }
  
  public void setSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25543, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25543, e);
    }
  }
  
  public void setCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25544, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25544, e);
    }
  }
  
  public void setBinaryFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25518, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25518, e);
    }
  }
  
  public void setDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25561, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25561, e);
    }
  }
  
  public void setCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25528, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25528, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25648, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25648, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25704, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25704, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25702, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject25702, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject25702, onErrorForAll(methodObject25702, e));
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25671, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25671, e);
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25696, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject25696, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject25696));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject25696, (ResultSet)onErrorForAll(methodObject25696, e));
    }
  }
  
  public void defineParameterType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25551, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25551, e);
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25685, this, new Object[] { arg0 });
      return postForExecute(methodObject25685, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25685, onErrorForExecute(methodObject25685, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25697, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject25697, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject25697, onErrorForAll(methodObject25697, e));
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25620, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25620, e);
    }
  }
  
  public void setFixedCHAR(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25524, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25524, e);
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject25740, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25740, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25634, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25634, e);
    }
  }
  
  public void setROWIDAtName(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25571, this, new Object[] { arg0, arg1 });
      this.delegate.setROWIDAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25571, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25595, this, new Object[] { arg0, arg1 });
      this.delegate.setTimeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25595, e);
    }
  }
  
  public void setURLAtName(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25614, this, new Object[] { arg0, arg1 });
      this.delegate.setURLAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25614, e);
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25686, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject25686, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25686, onErrorForExecute(methodObject25686, e));
    }
  }
  
  public void setARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25540, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25540, e);
    }
  }
  
  public void setCustomDatumAtName(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25605, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatumAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25605, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25684, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject25684);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject25684, e);
    }
  }
  
  public void setBinaryFloat(int arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25519, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25519, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25675, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25675, e);
    }
  }
  
  public void setNStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25626, this, new Object[] { arg0, arg1 });
      this.delegate.setNStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25626, e);
    }
  }
  
  public void setTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25539, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25539, e);
    }
  }
  
  public void setORADataAtName(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25606, this, new Object[] { arg0, arg1 });
      this.delegate.setORADataAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25606, e);
    }
  }
  
  public void setBigDecimalAtName(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25566, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimalAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25566, e);
    }
  }
  
  public void setCursor(int arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25525, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25525, e);
    }
  }
  
  public void setSnapshotSCN(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25746, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setSnapshotSCN(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25746, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25510, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25510, e);
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25633, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25633, e);
    }
  }
  
  public void setBytesForBlobAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25592, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25592, e);
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25635, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25635, e);
    }
  }
  
  public void setCheckBindTypes(boolean arg0)
  {
    super.preForAll(methodObject25497, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCheckBindTypes(arg0);
  }
  
  public void setINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25536, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25536, e);
    }
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25719, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25719, e);
    }
  }
  
  public void exitImplicitCacheToClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25503, this, zeroLengthObjectArray);
      this.delegate.exitImplicitCacheToClose();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25503, e);
    }
  }
  
  public void setINTERVALYMAtName(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25599, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYMAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25599, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25661, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25661, e);
    }
  }
  
  public OracleStatement.SqlKind getSqlKind()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25744, this, zeroLengthObjectArray);
      return (OracleStatement.SqlKind)postForAll(methodObject25744, (Object)this.delegate.getSqlKind());
    }
    catch (SQLException e)
    {
      return (OracleStatement.SqlKind)postForAll(methodObject25744, onErrorForAll(methodObject25744, e));
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25707, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25707, e);
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25733, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25733, e);
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25640, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject25640, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25640, ((Integer)onErrorForAll(methodObject25640, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25630, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25630, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25512, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25512, e);
    }
  }
  
  public void setBytesAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25591, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25591, e);
    }
  }
  
  public int getcacheState()
  {
    super.preForAll(methodObject25751, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25751, Integer.valueOf(this.delegate.getcacheState()))).intValue();
  }
  
  public void setClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25588, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25588, e);
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25676, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25676, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25507, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25507, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25701, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25701, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25701, onErrorForAll(methodObject25701, e))).intValue();
    }
  }
  
  public void setCHARAtName(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25578, this, new Object[] { arg0, arg1 });
      this.delegate.setCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25578, e);
    }
  }
  
  public String getOriginalSql()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25505, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject25505, (Object)this.delegate.getOriginalSql());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject25505, onErrorForAll(methodObject25505, e));
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25673, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25673, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25513, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25513, e);
    }
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25742, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject25742, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject25742, onErrorForAll(methodObject25742, e));
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25680, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25680, e);
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject25694, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject25694, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject25694, ((Integer)onErrorForAll(methodObject25694, e)).intValue());
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject25639, this, zeroLengthObjectArray);
      return postForExecute(methodObject25639, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject25639, onErrorForExecute(methodObject25639, e));
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject25737, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject25737, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public void setBlobAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25584, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25584, e);
    }
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25654, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject25654, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject25654));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject25654, onErrorForAll(methodObject25654, e));
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25722, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25722, e);
    }
  }
  
  public void setBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25531, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25531, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25678, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25678, e);
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25683, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25683, e);
    }
  }
  
  public void setFixedCHARAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25569, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25569, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25659, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25659, e);
    }
  }
  
  public void setBooleanAtName(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25555, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBooleanAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25555, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject25508, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25508, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25598, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestampAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25598, e);
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25631, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25631, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25709, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject25709, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject25709, onErrorForAll(methodObject25709, e))).intValue();
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25674, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25674, e);
    }
  }
  
  public void setStructDescriptor(int arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25542, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25542, e);
    }
  }
  
  public void setREFAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25612, this, new Object[] { arg0, arg1 });
      this.delegate.setREFAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25612, e);
    }
  }
  
  public oracle.jdbc.internal.OraclePreparedStatement _getDelegate_()
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
      methodObject25582 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, Blob.class });
      methodObject25563 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject25609 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class });
      methodObject25646 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25496 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Integer.TYPE });
      methodObject25532 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject25693 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject25552 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("getExecuteBatch", new Class[0]);
      methodObject25616 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFormOfUse", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject25750 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getserverCursor", new Class[0]);
      methodObject25498 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setInternalBytes", new Class[] { Integer.TYPE, byte[].class, Integer.TYPE });
      methodObject25499 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("enterImplicitCache", new Class[0]);
      methodObject25732 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25511 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject25533 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject25668 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25579 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDATEAtName", new Class[] { String.class, DATE.class });
      methodObject25583 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25535 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject25534 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject25705 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject25610 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefTypeAtName", new Class[] { String.class, REF.class });
      methodObject25568 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringForClobAtName", new Class[] { String.class, String.class });
      methodObject25717 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject25593 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class });
      methodObject25509 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25721 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject25596 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class, Calendar.class });
      methodObject25718 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject25716 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject25548 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject25637 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject25629 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject25688 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject25521 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, BINARY_DOUBLE.class });
      methodObject25643 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject25645 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25692 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject25615 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setPlsqlIndexTable", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25695 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject25715 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject25608 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject25624 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25698 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject25546 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefType", new Class[] { Integer.TYPE, REF.class });
      methodObject25741 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject25638 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject25660 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject25724 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject25663 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject25558 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setIntAtName", new Class[] { String.class, Integer.TYPE });
      methodObject25699 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject25617 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDisableStmtCaching", new Class[] { Boolean.TYPE });
      methodObject25669 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject25560 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject25553 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject25618 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("OracleGetParameterMetaData", new Class[0]);
      methodObject25575 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptorAtName", new Class[] { String.class, StructDescriptor.class });
      methodObject25564 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject25738 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject25577 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRAWAtName", new Class[] { String.class, RAW.class });
      methodObject25653 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject25703 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject25603 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZAtName", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject25619 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25735 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject25590 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBfileAtName", new Class[] { String.class, BFILE.class });
      methodObject25589 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBFILEAtName", new Class[] { String.class, BFILE.class });
      methodObject25749 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("sendBatch", new Class[0]);
      methodObject25623 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, NClob.class });
      methodObject25573 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setARRAYAtName", new Class[] { String.class, ARRAY.class });
      methodObject25530 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject25549 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25708 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject25734 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject25700 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject25625 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class });
      methodObject25748 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getFixedString", new Class[0]);
      methodObject25554 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE });
      methodObject25667 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25745 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getChecksum", new Class[0]);
      methodObject25651 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject25747 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setFixedString", new Class[] { Boolean.TYPE });
      methodObject25517 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setExecuteBatch", new Class[] { Integer.TYPE });
      methodObject25602 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZAtName", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject25681 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject25520 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject25529 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject25585 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCLOBAtName", new Class[] { String.class, CLOB.class });
      methodObject25657 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject25726 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject25545 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject25597 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class });
      methodObject25514 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25658 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject25587 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25557 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setShortAtName", new Class[] { String.class, Short.TYPE });
      methodObject25628 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSQLXMLAtName", new Class[] { String.class, SQLXML.class });
      methodObject25727 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject25622 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("getReturnResultSet", new Class[0]);
      methodObject25586 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Clob.class });
      methodObject25621 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25547 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setREF", new Class[] { Integer.TYPE, REF.class });
      methodObject25537 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject25723 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject25714 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject25559 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setLongAtName", new Class[] { String.class, Long.TYPE });
      methodObject25611 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRefAtName", new Class[] { String.class, Ref.class });
      methodObject25594 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class, Calendar.class });
      methodObject25500 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("enterExplicitCache", new Class[0]);
      methodObject25502 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitExplicitCacheToActive", new Class[0]);
      methodObject25613 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOracleObjectAtName", new Class[] { String.class, Datum.class });
      methodObject25728 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25691 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject25526 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject25655 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject25706 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject25687 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject25736 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject25562 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject25689 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject25580 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNUMBERAtName", new Class[] { String.class, NUMBER.class });
      methodObject25649 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject25690 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject25523 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { Integer.TYPE, byte[].class });
      methodObject25567 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringAtName", new Class[] { String.class, String.class });
      methodObject25636 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject25662 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject25556 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setByteAtName", new Class[] { String.class, Byte.TYPE });
      methodObject25739 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject25607 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject25743 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject25730 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject25541 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { Integer.TYPE, OPAQUE.class });
      methodObject25720 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject25600 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDSAtName", new Class[] { String.class, INTERVALDS.class });
      methodObject25665 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject25729 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25650 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25731 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25682 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject25677 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject25565 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject25711 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject25632 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject25522 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStringForClob", new Class[] { Integer.TYPE, String.class });
      methodObject25672 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject25515 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject25572 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setArrayAtName", new Class[] { String.class, Array.class });
      methodObject25576 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSTRUCTAtName", new Class[] { String.class, STRUCT.class });
      methodObject25601 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPAtName", new Class[] { String.class, TIMESTAMP.class });
      methodObject25581 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBLOBAtName", new Class[] { String.class, BLOB.class });
      methodObject25570 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCursorAtName", new Class[] { String.class, ResultSet.class });
      methodObject25710 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject25641 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25664 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject25604 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setUnicodeStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25647 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject25656 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject25666 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject25506 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject25712 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject25713 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject25670 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25752 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getstatementType", new Class[0]);
      methodObject25574 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setOPAQUEAtName", new Class[] { String.class, OPAQUE.class });
      methodObject25501 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitImplicitCacheToActive", new Class[0]);
      methodObject25538 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject25627 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRowIdAtName", new Class[] { String.class, RowId.class });
      methodObject25644 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25527 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject25652 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject25725 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject25504 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitExplicitCacheToClose", new Class[0]);
      methodObject25679 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject25550 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25642 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject25543 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject25544 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject25518 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject25561 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject25528 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject25648 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25704 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject25702 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject25671 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject25696 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject25551 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("defineParameterType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25685 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject25697 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject25620 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25524 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { Integer.TYPE, String.class });
      methodObject25740 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject25634 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject25571 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setROWIDAtName", new Class[] { String.class, ROWID.class });
      methodObject25595 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class });
      methodObject25614 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setURLAtName", new Class[] { String.class, URL.class });
      methodObject25686 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject25540 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject25605 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCustomDatumAtName", new Class[] { String.class, CustomDatum.class });
      methodObject25684 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject25519 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, BINARY_FLOAT.class });
      methodObject25675 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject25626 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setNStringAtName", new Class[] { String.class, String.class });
      methodObject25539 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject25606 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setORADataAtName", new Class[] { String.class, ORAData.class });
      methodObject25566 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBigDecimalAtName", new Class[] { String.class, BigDecimal.class });
      methodObject25525 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCursor", new Class[] { Integer.TYPE, ResultSet.class });
      methodObject25746 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("setSnapshotSCN", new Class[] { Long.TYPE });
      methodObject25510 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25633 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25592 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlobAtName", new Class[] { String.class, byte[].class });
      methodObject25635 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject25497 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("setCheckBindTypes", new Class[] { Boolean.TYPE });
      methodObject25536 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject25719 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject25503 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("exitImplicitCacheToClose", new Class[0]);
      methodObject25599 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYMAtName", new Class[] { String.class, INTERVALYM.class });
      methodObject25661 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject25744 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getSqlKind", new Class[0]);
      methodObject25707 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject25733 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject25640 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject25630 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject25512 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject25591 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBytesAtName", new Class[] { String.class, byte[].class });
      methodObject25751 = oracle.jdbc.internal.OracleStatement.class.getDeclaredMethod("getcacheState", new Class[0]);
      methodObject25588 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class });
      methodObject25676 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject25507 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject25701 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject25578 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCHARAtName", new Class[] { String.class, CHAR.class });
      methodObject25505 = oracle.jdbc.internal.OraclePreparedStatement.class.getDeclaredMethod("getOriginalSql", new Class[0]);
      methodObject25673 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject25513 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject25742 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject25680 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject25694 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject25639 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject25737 = oracle.jdbc.OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject25584 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class });
      methodObject25654 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject25722 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject25531 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject25678 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject25683 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject25569 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setFixedCHARAtName", new Class[] { String.class, String.class });
      methodObject25659 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject25555 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setBooleanAtName", new Class[] { String.class, Boolean.TYPE });
      methodObject25508 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject25598 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject25631 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject25709 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject25674 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject25542 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { Integer.TYPE, StructDescriptor.class });
      methodObject25612 = oracle.jdbc.OraclePreparedStatement.class.getDeclaredMethod("setREFAtName", new Class[] { String.class, REF.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OraclePreparedStatement$$$Proxy(oracle.jdbc.internal.OraclePreparedStatement paramOraclePreparedStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOraclePreparedStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1internal$1OraclePreparedStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */