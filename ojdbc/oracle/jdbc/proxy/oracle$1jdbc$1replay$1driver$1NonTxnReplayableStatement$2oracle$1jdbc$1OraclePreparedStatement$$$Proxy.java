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
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OraclePreparedStatement$$$Proxy
  extends NonTxnReplayableStatement
  implements OraclePreparedStatement, _Proxy_
{
  private OraclePreparedStatement delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject24828;
  private static Method methodObject24809;
  private static Method methodObject24855;
  private static Method methodObject24893;
  private static Method methodObject24778;
  private static Method methodObject24798;
  private static Method methodObject24940;
  private static Method methodObject24863;
  private static Method methodObject24979;
  private static Method methodObject24757;
  private static Method methodObject24779;
  private static Method methodObject24915;
  private static Method methodObject24825;
  private static Method methodObject24829;
  private static Method methodObject24781;
  private static Method methodObject24780;
  private static Method methodObject24952;
  private static Method methodObject24856;
  private static Method methodObject24814;
  private static Method methodObject24964;
  private static Method methodObject24839;
  private static Method methodObject24755;
  private static Method methodObject24968;
  private static Method methodObject24842;
  private static Method methodObject24965;
  private static Method methodObject24963;
  private static Method methodObject24794;
  private static Method methodObject24884;
  private static Method methodObject24876;
  private static Method methodObject24935;
  private static Method methodObject24767;
  private static Method methodObject24890;
  private static Method methodObject24892;
  private static Method methodObject24862;
  private static Method methodObject24939;
  private static Method methodObject24942;
  private static Method methodObject24962;
  private static Method methodObject24854;
  private static Method methodObject24871;
  private static Method methodObject24945;
  private static Method methodObject24792;
  private static Method methodObject24988;
  private static Method methodObject24885;
  private static Method methodObject24907;
  private static Method methodObject24971;
  private static Method methodObject24910;
  private static Method methodObject24804;
  private static Method methodObject24946;
  private static Method methodObject24864;
  private static Method methodObject24916;
  private static Method methodObject24806;
  private static Method methodObject24799;
  private static Method methodObject24865;
  private static Method methodObject24821;
  private static Method methodObject24810;
  private static Method methodObject24985;
  private static Method methodObject24823;
  private static Method methodObject24900;
  private static Method methodObject24950;
  private static Method methodObject24849;
  private static Method methodObject24866;
  private static Method methodObject24982;
  private static Method methodObject24836;
  private static Method methodObject24835;
  private static Method methodObject24762;
  private static Method methodObject24870;
  private static Method methodObject24819;
  private static Method methodObject24776;
  private static Method methodObject24795;
  private static Method methodObject24955;
  private static Method methodObject24981;
  private static Method methodObject24947;
  private static Method methodObject24872;
  private static Method methodObject24800;
  private static Method methodObject24914;
  private static Method methodObject24898;
  private static Method methodObject24848;
  private static Method methodObject24763;
  private static Method methodObject24928;
  private static Method methodObject24766;
  private static Method methodObject24775;
  private static Method methodObject24831;
  private static Method methodObject24904;
  private static Method methodObject24973;
  private static Method methodObject24791;
  private static Method methodObject24843;
  private static Method methodObject24760;
  private static Method methodObject24905;
  private static Method methodObject24833;
  private static Method methodObject24803;
  private static Method methodObject24875;
  private static Method methodObject24974;
  private static Method methodObject24869;
  private static Method methodObject24832;
  private static Method methodObject24868;
  private static Method methodObject24783;
  private static Method methodObject24793;
  private static Method methodObject24970;
  private static Method methodObject24961;
  private static Method methodObject24805;
  private static Method methodObject24857;
  private static Method methodObject24840;
  private static Method methodObject24859;
  private static Method methodObject24975;
  private static Method methodObject24772;
  private static Method methodObject24902;
  private static Method methodObject24938;
  private static Method methodObject24953;
  private static Method methodObject24934;
  private static Method methodObject24983;
  private static Method methodObject24808;
  private static Method methodObject24936;
  private static Method methodObject24826;
  private static Method methodObject24896;
  private static Method methodObject24937;
  private static Method methodObject24769;
  private static Method methodObject24813;
  private static Method methodObject24883;
  private static Method methodObject24909;
  private static Method methodObject24802;
  private static Method methodObject24986;
  private static Method methodObject24853;
  private static Method methodObject24990;
  private static Method methodObject24977;
  private static Method methodObject24787;
  private static Method methodObject24967;
  private static Method methodObject24846;
  private static Method methodObject24912;
  private static Method methodObject24976;
  private static Method methodObject24897;
  private static Method methodObject24978;
  private static Method methodObject24929;
  private static Method methodObject24924;
  private static Method methodObject24811;
  private static Method methodObject24958;
  private static Method methodObject24879;
  private static Method methodObject24768;
  private static Method methodObject24919;
  private static Method methodObject24761;
  private static Method methodObject24818;
  private static Method methodObject24847;
  private static Method methodObject24822;
  private static Method methodObject24827;
  private static Method methodObject24816;
  private static Method methodObject24957;
  private static Method methodObject24888;
  private static Method methodObject24911;
  private static Method methodObject24850;
  private static Method methodObject24894;
  private static Method methodObject24903;
  private static Method methodObject24913;
  private static Method methodObject24752;
  private static Method methodObject24959;
  private static Method methodObject24960;
  private static Method methodObject24917;
  private static Method methodObject24820;
  private static Method methodObject24784;
  private static Method methodObject24874;
  private static Method methodObject24891;
  private static Method methodObject24773;
  private static Method methodObject24899;
  private static Method methodObject24972;
  private static Method methodObject24926;
  private static Method methodObject24796;
  private static Method methodObject24889;
  private static Method methodObject24789;
  private static Method methodObject24790;
  private static Method methodObject24764;
  private static Method methodObject24807;
  private static Method methodObject24774;
  private static Method methodObject24895;
  private static Method methodObject24951;
  private static Method methodObject24949;
  private static Method methodObject24918;
  private static Method methodObject24797;
  private static Method methodObject24932;
  private static Method methodObject24943;
  private static Method methodObject24944;
  private static Method methodObject24867;
  private static Method methodObject24770;
  private static Method methodObject24987;
  private static Method methodObject24881;
  private static Method methodObject24817;
  private static Method methodObject24841;
  private static Method methodObject24860;
  private static Method methodObject24933;
  private static Method methodObject24786;
  private static Method methodObject24851;
  private static Method methodObject24931;
  private static Method methodObject24765;
  private static Method methodObject24922;
  private static Method methodObject24873;
  private static Method methodObject24785;
  private static Method methodObject24852;
  private static Method methodObject24812;
  private static Method methodObject24771;
  private static Method methodObject24756;
  private static Method methodObject24880;
  private static Method methodObject24838;
  private static Method methodObject24882;
  private static Method methodObject24782;
  private static Method methodObject24861;
  private static Method methodObject24966;
  private static Method methodObject24845;
  private static Method methodObject24908;
  private static Method methodObject24954;
  private static Method methodObject24980;
  private static Method methodObject24887;
  private static Method methodObject24877;
  private static Method methodObject24758;
  private static Method methodObject24837;
  private static Method methodObject24834;
  private static Method methodObject24923;
  private static Method methodObject24753;
  private static Method methodObject24948;
  private static Method methodObject24824;
  private static Method methodObject24920;
  private static Method methodObject24759;
  private static Method methodObject24989;
  private static Method methodObject24927;
  private static Method methodObject24941;
  private static Method methodObject24886;
  private static Method methodObject24984;
  private static Method methodObject24830;
  private static Method methodObject24901;
  private static Method methodObject24969;
  private static Method methodObject24777;
  private static Method methodObject24925;
  private static Method methodObject24930;
  private static Method methodObject24815;
  private static Method methodObject24906;
  private static Method methodObject24801;
  private static Method methodObject24754;
  private static Method methodObject24844;
  private static Method methodObject24878;
  private static Method methodObject24956;
  private static Method methodObject24921;
  private static Method methodObject24788;
  private static Method methodObject24858;
  
  public void setBlobAtName(String arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24828, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24828, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24809, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24809, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24855, this, new Object[] { arg0, arg1 });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24855, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24893, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24893, e);
    }
  }
  
  public void setCLOB(int arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24778, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24778, e);
    }
  }
  
  public int getExecuteBatch()
  {
    super.preForAll(methodObject24798, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24798, Integer.valueOf(this.delegate.getExecuteBatch()))).intValue();
  }
  
  public int executeUpdate(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24940, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24940, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24940, ((Integer)onErrorForAll(methodObject24940, e)).intValue());
    }
  }
  
  public void setFormOfUse(int arg0, short arg1)
  {
    super.preForAll(methodObject24863, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
    this.delegate.setFormOfUse(arg0, arg1);
  }
  
  public void defineColumnTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24979, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24979, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24757, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24757, e);
    }
  }
  
  public void setBFILE(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24779, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBFILE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24779, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24915, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24915, e);
    }
  }
  
  public void setDATEAtName(String arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24825, this, new Object[] { arg0, arg1 });
      this.delegate.setDATEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24825, e);
    }
  }
  
  public void setBlobAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24829, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBlobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24829, e);
    }
  }
  
  public void setINTERVALYM(int arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24781, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALYM(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24781, e);
    }
  }
  
  public void setBfile(int arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24780, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBfile(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24780, e);
    }
  }
  
  public void setFetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24952, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24952, e);
    }
  }
  
  public void setRefTypeAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24856, this, new Object[] { arg0, arg1 });
      this.delegate.setRefTypeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24856, e);
    }
  }
  
  public void setStringForClobAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24814, this, new Object[] { arg0, arg1 });
      this.delegate.setStringForClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24814, e);
    }
  }
  
  public boolean isPoolable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24964, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24964, Boolean.valueOf(this.delegate.isPoolable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24964, onErrorForAll(methodObject24964, e))).booleanValue();
    }
  }
  
  public void setDateAtName(String arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24839, this, new Object[] { arg0, arg1 });
      this.delegate.setDateAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24839, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24755, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24755, e);
    }
  }
  
  public void setMaxRows(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24968, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxRows(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24968, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24842, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimeAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24842, e);
    }
  }
  
  public void setCursorName(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24965, this, new Object[] { arg0 });
      this.delegate.setCursorName(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24965, e);
    }
  }
  
  public int getUpdateCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24963, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24963, Integer.valueOf(this.delegate.getUpdateCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24963, onErrorForAll(methodObject24963, e))).intValue();
    }
  }
  
  public void setOracleObject(int arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24794, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOracleObject(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24794, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24884, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTimestamp(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24884, e);
    }
  }
  
  public void setBoolean(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24876, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setBoolean(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24876, e);
    }
  }
  
  public boolean execute(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24935, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24935, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24935, onErrorForExecute(methodObject24935, e));
    }
  }
  
  public void setBinaryDouble(int arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24767, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24767, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24890, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setAsciiStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24890, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24892, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24892, e);
    }
  }
  
  public void setPlsqlIndexTable(int arg0, Object arg1, int arg2, int arg3, int arg4, int arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24862, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3), Integer.valueOf(arg4), Integer.valueOf(arg5) });
      this.delegate.setPlsqlIndexTable(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3, arg4, arg5);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24862, e);
    }
  }
  
  public int executeUpdate(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24939, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecuteUpdate(methodObject24939, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24939, ((Integer)onErrorForAll(methodObject24939, e)).intValue());
    }
  }
  
  public int[] executeBatch()
    throws SQLException
  {
    try
    {
      super.preForExecuteBatch(methodObject24942, this, zeroLengthObjectArray);
      return (int[])postForAll(methodObject24942, (Object)this.delegate.executeBatch());
    }
    catch (SQLException e)
    {
      return (int[])postForAll(methodObject24942, onErrorForAll(methodObject24942, e));
    }
  }
  
  public int getResultSetType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24962, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24962, Integer.valueOf(this.delegate.getResultSetType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24962, onErrorForAll(methodObject24962, e))).intValue();
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24854, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24854, e);
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24871, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24871, e);
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24945, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24945, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24945, onErrorForAll(methodObject24945, e))).intValue();
    }
  }
  
  public void setRefType(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24792, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRefType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24792, e);
    }
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24988, this, new Object[] { arg0 });
      this.delegate.setDatabaseChangeRegistration(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24988, e);
    }
  }
  
  public void setURL(int arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24885, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setURL(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24885, e);
    }
  }
  
  public void setDate(int arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24907, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setDate(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24907, e);
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24971, this, new Object[] { arg0 });
      return postForAll(methodObject24971, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject24971, onErrorForAll(methodObject24971, e));
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24910, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24910, e);
    }
  }
  
  public void setIntAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24804, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setIntAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24804, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24946, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24946, e);
    }
  }
  
  public void setDisableStmtCaching(boolean arg0)
  {
    super.preForAll(methodObject24864, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setDisableStmtCaching(arg0);
  }
  
  public void setClob(int arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24916, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24916, e);
    }
  }
  
  public void setFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24806, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24806, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24799, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      this.delegate.setNullAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24799, e);
    }
  }
  
  public OracleParameterMetaData OracleGetParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24865, this, zeroLengthObjectArray);
      return (OracleParameterMetaData)postForAll(methodObject24865, this.proxyFactory.proxyForCache((Object)this.delegate.OracleGetParameterMetaData(), this, this.proxyCache, methodObject24865));
    }
    catch (SQLException e)
    {
      return (OracleParameterMetaData)postForAll(methodObject24865, onErrorForAll(methodObject24865, e));
    }
  }
  
  public void setStructDescriptorAtName(String arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24821, this, new Object[] { arg0, arg1 });
      this.delegate.setStructDescriptorAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24821, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24810, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24810, e);
    }
  }
  
  public void setLobPrefetchSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24985, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setLobPrefetchSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24985, e);
    }
  }
  
  public void setRAWAtName(String arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24823, this, new Object[] { arg0, arg1 });
      this.delegate.setRAWAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24823, e);
    }
  }
  
  public ResultSetMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24900, this, zeroLengthObjectArray);
      return (ResultSetMetaData)postForAll(methodObject24900, this.proxyFactory.proxyForCreate((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject24900));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject24900, onErrorForAll(methodObject24900, e));
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24950, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24950, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24950, onErrorForAll(methodObject24950, e))).booleanValue();
    }
  }
  
  public void setTIMESTAMPLTZAtName(String arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24849, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPLTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24849, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24866, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.registerReturnParameter(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24866, e);
    }
  }
  
  public void setResultSetCache(OracleResultSetCache arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24982, this, new Object[] { arg0 });
      this.delegate.setResultSetCache((arg0 instanceof _Proxy_) ? (OracleResultSetCache)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24982, e);
    }
  }
  
  public void setBfileAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24836, this, new Object[] { arg0, arg1 });
      this.delegate.setBfileAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24836, e);
    }
  }
  
  public void setBFILEAtName(String arg0, BFILE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24835, this, new Object[] { arg0, arg1 });
      this.delegate.setBFILEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24835, e);
    }
  }
  
  public int sendBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24762, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24762, Integer.valueOf(this.delegate.sendBatch()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24762, onErrorForAll(methodObject24762, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24870, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24870, e);
    }
  }
  
  public void setARRAYAtName(String arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24819, this, new Object[] { arg0, arg1 });
      this.delegate.setARRAYAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24819, e);
    }
  }
  
  public void setNUMBER(int arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24776, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNUMBER(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24776, e);
    }
  }
  
  public void defineParameterTypeBytes(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24795, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeBytes(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24795, e);
    }
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24955, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24955, this.proxyFactory.proxyForCache((Object)this.delegate.getGeneratedKeys(), this, this.proxyCache, methodObject24955));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24955, onErrorForAll(methodObject24955, e));
    }
  }
  
  public int getRowPrefetch()
  {
    super.preForAll(methodObject24981, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24981, Integer.valueOf(this.delegate.getRowPrefetch()))).intValue();
  }
  
  public int getFetchDirection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24947, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24947, Integer.valueOf(this.delegate.getFetchDirection()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24947, onErrorForAll(methodObject24947, e))).intValue();
    }
  }
  
  public void setNClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24872, this, new Object[] { arg0, arg1 });
      this.delegate.setNClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24872, e);
    }
  }
  
  public void setNullAtName(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24800, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.setNullAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24800, e);
    }
  }
  
  public void setBlob(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24914, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setBlob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24914, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24898, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24898, e);
    }
  }
  
  public void setTIMESTAMPTZAtName(String arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24848, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPTZAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24848, e);
    }
  }
  
  public void setExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24763, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setExecuteBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24763, e);
    }
  }
  
  public void setArray(int arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24928, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setArray(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24928, e);
    }
  }
  
  public void setBinaryDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24766, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setBinaryDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24766, e);
    }
  }
  
  public void setDATE(int arg0, DATE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24775, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDATE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24775, e);
    }
  }
  
  public void setCLOBAtName(String arg0, CLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24831, this, new Object[] { arg0, arg1 });
      this.delegate.setCLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24831, e);
    }
  }
  
  public void setTime(int arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24904, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTime(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24904, e);
    }
  }
  
  public boolean isNCHAR(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24973, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24973, Boolean.valueOf(this.delegate.isNCHAR(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24973, onErrorForAll(methodObject24973, e))).booleanValue();
    }
  }
  
  public void setORAData(int arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24791, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setORAData(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24791, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24843, this, new Object[] { arg0, arg1 });
      this.delegate.setTimestampAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24843, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24760, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24760, e);
    }
  }
  
  public void setTime(int arg0, Time arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24905, this, new Object[] { Integer.valueOf(arg0), arg1, arg2 });
      this.delegate.setTime(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24905, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24833, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setClobAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24833, e);
    }
  }
  
  public void setShortAtName(String arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24803, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setShortAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24803, e);
    }
  }
  
  public void setSQLXMLAtName(String arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24875, this, new Object[] { arg0, arg1 });
      this.delegate.setSQLXMLAtName(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24875, e);
    }
  }
  
  public void clearDefines()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24974, this, zeroLengthObjectArray);
      this.delegate.clearDefines();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24974, e);
    }
  }
  
  public ResultSet getReturnResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24869, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24869, this.proxyFactory.proxyForCache((Object)this.delegate.getReturnResultSet(), this, this.proxyCache, methodObject24869));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24869, onErrorForAll(methodObject24869, e));
    }
  }
  
  public void setClobAtName(String arg0, Clob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24832, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, (arg1 instanceof _Proxy_) ? (Clob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24832, e);
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24868, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24868, e);
    }
  }
  
  public void setTIMESTAMP(int arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24783, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMP(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24783, e);
    }
  }
  
  public void setREF(int arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24793, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setREF(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24793, e);
    }
  }
  
  public void setQueryTimeout(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24970, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setQueryTimeout(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24970, e);
    }
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24961, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24961, Integer.valueOf(this.delegate.getResultSetConcurrency()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24961, onErrorForAll(methodObject24961, e))).intValue();
    }
  }
  
  public void setLongAtName(String arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24805, this, new Object[] { arg0, Long.valueOf(arg1) });
      this.delegate.setLongAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24805, e);
    }
  }
  
  public void setRefAtName(String arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24857, this, new Object[] { arg0, arg1 });
      this.delegate.setRefAtName(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24857, e);
    }
  }
  
  public void setDateAtName(String arg0, Date arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24840, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setDateAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24840, e);
    }
  }
  
  public void setOracleObjectAtName(String arg0, Datum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24859, this, new Object[] { arg0, arg1 });
      this.delegate.setOracleObjectAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24859, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24975, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.defineColumnType(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24975, e);
    }
  }
  
  public void setROWID(int arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24772, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setROWID(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24772, e);
    }
  }
  
  public void setBytes(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24902, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytes(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24902, e);
    }
  }
  
  public int executeUpdate(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24938, this, new Object[] { arg0 });
      return postForExecuteUpdate(methodObject24938, this.delegate.executeUpdate(arg0));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24938, ((Integer)onErrorForAll(methodObject24938, e)).intValue());
    }
  }
  
  public void addBatch(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24953, this, new Object[] { arg0 });
      this.delegate.addBatch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24953, e);
    }
  }
  
  public boolean execute(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24934, this, new Object[] { arg0, arg1 });
      return postForExecute(methodObject24934, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24934, onErrorForExecute(methodObject24934, e));
    }
  }
  
  public void setRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24983, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setRowPrefetch(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24983, e);
    }
  }
  
  public void setBinaryFloatAtName(String arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24808, this, new Object[] { arg0, Float.valueOf(arg1) });
      this.delegate.setBinaryFloatAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24808, e);
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24936, this, zeroLengthObjectArray);
      this.delegate.cancel();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24936, e);
    }
  }
  
  public void setNUMBERAtName(String arg0, NUMBER arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24826, this, new Object[] { arg0, arg1 });
      this.delegate.setNUMBERAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24826, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24896, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCharacterStream(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24896, e);
    }
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24937, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject24937, this.proxyFactory.proxyForCache((Object)this.delegate.getResultSet(), this, this.proxyCache, methodObject24937));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject24937, onErrorForAll(methodObject24937, e));
    }
  }
  
  public void setBytesForBlob(int arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24769, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBytesForBlob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24769, e);
    }
  }
  
  public void setStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24813, this, new Object[] { arg0, arg1 });
      this.delegate.setStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24813, e);
    }
  }
  
  public void setTimestamp(int arg0, Timestamp arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24883, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTimestamp(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24883, e);
    }
  }
  
  public void setObject(int arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24909, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24909, e);
    }
  }
  
  public void setByteAtName(String arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24802, this, new Object[] { arg0, Byte.valueOf(arg1) });
      this.delegate.setByteAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24802, e);
    }
  }
  
  public void closeWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24986, this, new Object[] { arg0 });
      this.delegate.closeWithKey(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24986, e);
    }
  }
  
  public void setObjectAtName(String arg0, Object arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24853, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), Integer.valueOf(arg3) });
      this.delegate.setObjectAtName(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24853, e);
    }
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24990, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject24990, Long.valueOf(this.delegate.getRegisteredQueryId()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject24990, onErrorForAll(methodObject24990, e))).longValue();
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2, short arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24977, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2), Short.valueOf(arg3) });
      this.delegate.defineColumnType(arg0, arg1, arg2, arg3);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24977, e);
    }
  }
  
  public void setOPAQUE(int arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24787, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setOPAQUE(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24787, e);
    }
  }
  
  public void setMaxFieldSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24967, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setMaxFieldSize(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24967, e);
    }
  }
  
  public void setINTERVALDSAtName(String arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24846, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALDSAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24846, e);
    }
  }
  
  public void setBigDecimal(int arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24912, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBigDecimal(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24912, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24976, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24976, e);
    }
  }
  
  public void setNCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24897, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24897, e);
    }
  }
  
  public void defineColumnType(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24978, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.defineColumnType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24978, e);
    }
  }
  
  public void setRef(int arg0, Ref arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24929, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRef(arg0, (arg1 instanceof _Proxy_) ? (Ref)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24929, e);
    }
  }
  
  public void setNull(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24924, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.setNull(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24924, e);
    }
  }
  
  public void setBinaryDoubleAtName(String arg0, BINARY_DOUBLE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24811, this, new Object[] { arg0, arg1 });
      this.delegate.setBinaryDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24811, e);
    }
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24958, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject24958, Boolean.valueOf(this.delegate.getMoreResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24958, onErrorForAll(methodObject24958, e))).booleanValue();
    }
  }
  
  public void setFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24879, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24879, e);
    }
  }
  
  public void setStringForClob(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24768, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStringForClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24768, e);
    }
  }
  
  public void setNClob(int arg0, NClob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24919, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, (arg1 instanceof _Proxy_) ? (NClob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24919, e);
    }
  }
  
  public void setNCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24761, this, new Object[] { arg0, arg1 });
      this.delegate.setNCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24761, e);
    }
  }
  
  public void setArrayAtName(String arg0, Array arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24818, this, new Object[] { arg0, arg1 });
      this.delegate.setArrayAtName(arg0, (arg1 instanceof _Proxy_) ? (Array)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24818, e);
    }
  }
  
  public void setTIMESTAMPAtName(String arg0, TIMESTAMP arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24847, this, new Object[] { arg0, arg1 });
      this.delegate.setTIMESTAMPAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24847, e);
    }
  }
  
  public void setSTRUCTAtName(String arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24822, this, new Object[] { arg0, arg1 });
      this.delegate.setSTRUCTAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24822, e);
    }
  }
  
  public void setBLOBAtName(String arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24827, this, new Object[] { arg0, arg1 });
      this.delegate.setBLOBAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24827, e);
    }
  }
  
  public void setCursorAtName(String arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24816, this, new Object[] { arg0, arg1 });
      this.delegate.setCursorAtName(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24816, e);
    }
  }
  
  public int getMaxRows()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24957, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24957, Integer.valueOf(this.delegate.getMaxRows()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24957, onErrorForAll(methodObject24957, e))).intValue();
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24888, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24888, e);
    }
  }
  
  public void addBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24911, this, zeroLengthObjectArray);
      this.delegate.addBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24911, e);
    }
  }
  
  public void setUnicodeStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24850, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24850, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24894, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24894, e);
    }
  }
  
  public void setString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24903, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24903, e);
    }
  }
  
  public void setBlob(int arg0, Blob arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24913, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBlob(arg0, (arg1 instanceof _Proxy_) ? (Blob)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24913, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24752, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24752, e);
    }
  }
  
  public boolean getMoreResults(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24959, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject24959, Boolean.valueOf(this.delegate.getMoreResults(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24959, onErrorForAll(methodObject24959, e))).booleanValue();
    }
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24960, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24960, Integer.valueOf(this.delegate.getQueryTimeout()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24960, onErrorForAll(methodObject24960, e))).intValue();
    }
  }
  
  public void setClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24917, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24917, e);
    }
  }
  
  public void setOPAQUEAtName(String arg0, OPAQUE arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24820, this, new Object[] { arg0, arg1 });
      this.delegate.setOPAQUEAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24820, e);
    }
  }
  
  public void setTIMESTAMPTZ(int arg0, TIMESTAMPTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24784, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24784, e);
    }
  }
  
  public void setRowIdAtName(String arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24874, this, new Object[] { arg0, arg1 });
      this.delegate.setRowIdAtName(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24874, e);
    }
  }
  
  public void setBinaryStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24891, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setBinaryStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24891, e);
    }
  }
  
  public void setRAW(int arg0, RAW arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24773, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRAW(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24773, e);
    }
  }
  
  public ResultSet executeQuery()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24899, this, zeroLengthObjectArray);
      return postForExecuteQuery(methodObject24899, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(), this, this.proxyCache, methodObject24899));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24899, (ResultSet)onErrorForAll(methodObject24899, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24972, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject24972, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject24972, onErrorForAll(methodObject24972, e))).booleanValue();
    }
  }
  
  public void setSQLXML(int arg0, SQLXML arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24926, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSQLXML(arg0, (arg1 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24926, e);
    }
  }
  
  public void defineParameterTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24796, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24796, e);
    }
  }
  
  public void setAsciiStream(int arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24889, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24889, e);
    }
  }
  
  public void setSTRUCT(int arg0, STRUCT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24789, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setSTRUCT(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24789, e);
    }
  }
  
  public void setCustomDatum(int arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24790, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCustomDatum(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24790, e);
    }
  }
  
  public void setBinaryFloat(int arg0, float arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24764, this, new Object[] { Integer.valueOf(arg0), Float.valueOf(arg1) });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24764, e);
    }
  }
  
  public void setDoubleAtName(String arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24807, this, new Object[] { arg0, Double.valueOf(arg1) });
      this.delegate.setDoubleAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24807, e);
    }
  }
  
  public void setCHAR(int arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24774, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24774, e);
    }
  }
  
  public void setCharacterStream(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24895, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24895, e);
    }
  }
  
  public void setFetchDirection(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24951, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setFetchDirection(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24951, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24949, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject24949, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject24949, onErrorForAll(methodObject24949, e));
    }
  }
  
  public void setClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24918, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24918, e);
    }
  }
  
  public void defineParameterType(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24797, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineParameterType(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24797, e);
    }
  }
  
  public boolean execute(String arg0)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24932, this, new Object[] { arg0 });
      return postForExecute(methodObject24932, this.delegate.execute(arg0));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24932, onErrorForExecute(methodObject24932, e));
    }
  }
  
  public ResultSet executeQuery(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24943, this, new Object[] { arg0 });
      return postForExecuteQuery(methodObject24943, (ResultSet)this.proxyFactory.proxyForCreateCache((Object)this.delegate.executeQuery(arg0), this, this.proxyCache, methodObject24943));
    }
    catch (SQLException e)
    {
      return postForExecuteQuery(methodObject24943, (ResultSet)onErrorForAll(methodObject24943, e));
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24944, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject24944, (Object)super.getConnection());
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject24944, onErrorForAll(methodObject24944, e));
    }
  }
  
  public void registerReturnParameter(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24867, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.registerReturnParameter(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24867, e);
    }
  }
  
  public void setFixedCHAR(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24770, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setFixedCHAR(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24770, e);
    }
  }
  
  public int creationState()
  {
    super.preForAll(methodObject24987, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24987, Integer.valueOf(this.delegate.creationState()))).intValue();
  }
  
  public void setLong(int arg0, long arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24881, this, new Object[] { Integer.valueOf(arg0), Long.valueOf(arg1) });
      this.delegate.setLong(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24881, e);
    }
  }
  
  public void setROWIDAtName(String arg0, ROWID arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24817, this, new Object[] { arg0, arg1 });
      this.delegate.setROWIDAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24817, e);
    }
  }
  
  public void setTimeAtName(String arg0, Time arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24841, this, new Object[] { arg0, arg1 });
      this.delegate.setTimeAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24841, e);
    }
  }
  
  public void setURLAtName(String arg0, URL arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24860, this, new Object[] { arg0, arg1 });
      this.delegate.setURLAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24860, e);
    }
  }
  
  public boolean execute(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24933, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return postForExecute(methodObject24933, this.delegate.execute(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24933, onErrorForExecute(methodObject24933, e));
    }
  }
  
  public void setARRAY(int arg0, ARRAY arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24786, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setARRAY(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24786, e);
    }
  }
  
  public void setCustomDatumAtName(String arg0, CustomDatum arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24851, this, new Object[] { arg0, arg1 });
      this.delegate.setCustomDatumAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24851, e);
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24931, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClose(methodObject24931);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForClose(methodObject24931, e);
    }
  }
  
  public void setBinaryFloat(int arg0, BINARY_FLOAT arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24765, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBinaryFloat(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24765, e);
    }
  }
  
  public void setNString(int arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24922, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNString(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24922, e);
    }
  }
  
  public void setNStringAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24873, this, new Object[] { arg0, arg1 });
      this.delegate.setNStringAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24873, e);
    }
  }
  
  public void setTIMESTAMPLTZ(int arg0, TIMESTAMPLTZ arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24785, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setTIMESTAMPLTZ(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24785, e);
    }
  }
  
  public void setORADataAtName(String arg0, ORAData arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24852, this, new Object[] { arg0, arg1 });
      this.delegate.setORADataAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24852, e);
    }
  }
  
  public void setBigDecimalAtName(String arg0, BigDecimal arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24812, this, new Object[] { arg0, arg1 });
      this.delegate.setBigDecimalAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24812, e);
    }
  }
  
  public void setCursor(int arg0, ResultSet arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24771, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setCursor(arg0, (arg1 instanceof _Proxy_) ? (ResultSet)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24771, e);
    }
  }
  
  public void setBinaryStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24756, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setBinaryStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24756, e);
    }
  }
  
  public void setInt(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24880, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setInt(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24880, e);
    }
  }
  
  public void setBytesForBlobAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24838, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesForBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24838, e);
    }
  }
  
  public void setShort(int arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24882, this, new Object[] { Integer.valueOf(arg0), Short.valueOf(arg1) });
      this.delegate.setShort(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24882, e);
    }
  }
  
  public void setINTERVALDS(int arg0, INTERVALDS arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24782, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setINTERVALDS(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24782, e);
    }
  }
  
  public void setCheckBindTypes(boolean arg0)
  {
    super.preForAll(methodObject24861, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCheckBindTypes(arg0);
  }
  
  public void setEscapeProcessing(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24966, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setEscapeProcessing(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24966, e);
    }
  }
  
  public void setINTERVALYMAtName(String arg0, INTERVALYM arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24845, this, new Object[] { arg0, arg1 });
      this.delegate.setINTERVALYMAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24845, e);
    }
  }
  
  public void setObject(int arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24908, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setObject(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24908, e);
    }
  }
  
  public void clearBatch()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24954, this, zeroLengthObjectArray);
      this.delegate.clearBatch();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24954, e);
    }
  }
  
  public void defineColumnTypeChars(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24980, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      this.delegate.defineColumnTypeChars(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24980, e);
    }
  }
  
  public int executeUpdate()
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24887, this, zeroLengthObjectArray);
      return postForExecuteUpdate(methodObject24887, this.delegate.executeUpdate());
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24887, ((Integer)onErrorForAll(methodObject24887, e)).intValue());
    }
  }
  
  public void setByte(int arg0, byte arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24877, this, new Object[] { Integer.valueOf(arg0), Byte.valueOf(arg1) });
      this.delegate.setByte(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24877, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24758, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setCharacterStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24758, e);
    }
  }
  
  public void setBytesAtName(String arg0, byte[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24837, this, new Object[] { arg0, arg1 });
      this.delegate.setBytesAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24837, e);
    }
  }
  
  public void setClobAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24834, this, new Object[] { arg0, arg1 });
      this.delegate.setClobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24834, e);
    }
  }
  
  public void setNull(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24923, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      this.delegate.setNull(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24923, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24753, this, new Object[] { arg0, arg1, Long.valueOf(arg2) });
      this.delegate.setAsciiStreamAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24753, e);
    }
  }
  
  public int getFetchSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24948, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24948, Integer.valueOf(this.delegate.getFetchSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24948, onErrorForAll(methodObject24948, e))).intValue();
    }
  }
  
  public void setCHARAtName(String arg0, CHAR arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24824, this, new Object[] { arg0, arg1 });
      this.delegate.setCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24824, e);
    }
  }
  
  public void setNClob(int arg0, Reader arg1, long arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24920, this, new Object[] { Integer.valueOf(arg0), arg1, Long.valueOf(arg2) });
      this.delegate.setNClob(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24920, e);
    }
  }
  
  public void setCharacterStreamAtName(String arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24759, this, new Object[] { arg0, arg1 });
      this.delegate.setCharacterStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24759, e);
    }
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24989, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject24989, (Object)this.delegate.getRegisteredTableNames());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject24989, onErrorForAll(methodObject24989, e));
    }
  }
  
  public void clearParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24927, this, zeroLengthObjectArray);
      this.delegate.clearParameters();
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24927, e);
    }
  }
  
  public int executeUpdate(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForExecuteUpdate(methodObject24941, this, new Object[] { arg0, arg1 });
      return postForExecuteUpdate(methodObject24941, this.delegate.executeUpdate(arg0, arg1));
    }
    catch (SQLException e)
    {
      return postForExecuteUpdate(methodObject24941, ((Integer)onErrorForAll(methodObject24941, e)).intValue());
    }
  }
  
  public boolean execute()
    throws SQLException
  {
    try
    {
      super.preForExecute(methodObject24886, this, zeroLengthObjectArray);
      return postForExecute(methodObject24886, this.delegate.execute());
    }
    catch (SQLException e)
    {
      return postForExecute(methodObject24886, onErrorForExecute(methodObject24886, e));
    }
  }
  
  public int getLobPrefetchSize()
  {
    super.preForAll(methodObject24984, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject24984, Integer.valueOf(this.delegate.getLobPrefetchSize()))).intValue();
  }
  
  public void setBlobAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24830, this, new Object[] { arg0, arg1 });
      this.delegate.setBlobAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24830, e);
    }
  }
  
  public ParameterMetaData getParameterMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24901, this, zeroLengthObjectArray);
      return (ParameterMetaData)postForAll(methodObject24901, this.proxyFactory.proxyForCreate((Object)this.delegate.getParameterMetaData(), this, this.proxyCache, methodObject24901));
    }
    catch (SQLException e)
    {
      return (ParameterMetaData)postForAll(methodObject24901, onErrorForAll(methodObject24901, e));
    }
  }
  
  public void setPoolable(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24969, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setPoolable(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24969, e);
    }
  }
  
  public void setBLOB(int arg0, BLOB arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24777, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setBLOB(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24777, e);
    }
  }
  
  public void setRowId(int arg0, RowId arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24925, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setRowId(arg0, (arg1 instanceof _Proxy_) ? (RowId)((_Proxy_)arg1)._getDelegate_() : arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24925, e);
    }
  }
  
  public void setUnicodeStream(int arg0, InputStream arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24930, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.setUnicodeStream(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24930, e);
    }
  }
  
  public void setFixedCHARAtName(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24815, this, new Object[] { arg0, arg1 });
      this.delegate.setFixedCHARAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24815, e);
    }
  }
  
  public void setDate(int arg0, Date arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24906, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setDate(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24906, e);
    }
  }
  
  public void setBooleanAtName(String arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24801, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      this.delegate.setBooleanAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24801, e);
    }
  }
  
  public void setAsciiStreamAtName(String arg0, InputStream arg1)
    throws SQLException
  {
    try
    {
      super.preForSetStreams(methodObject24754, this, new Object[] { arg0, arg1 });
      this.delegate.setAsciiStreamAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24754, e);
    }
  }
  
  public void setTimestampAtName(String arg0, Timestamp arg1, Calendar arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24844, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setTimestampAtName(arg0, arg1, arg2);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24844, e);
    }
  }
  
  public void setDouble(int arg0, double arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24878, this, new Object[] { Integer.valueOf(arg0), Double.valueOf(arg1) });
      this.delegate.setDouble(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24878, e);
    }
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24956, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject24956, Integer.valueOf(this.delegate.getMaxFieldSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject24956, onErrorForAll(methodObject24956, e))).intValue();
    }
  }
  
  public void setNClob(int arg0, Reader arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24921, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setNClob(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24921, e);
    }
  }
  
  public void setStructDescriptor(int arg0, StructDescriptor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24788, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.setStructDescriptor(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24788, e);
    }
  }
  
  public void setREFAtName(String arg0, REF arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject24858, this, new Object[] { arg0, arg1 });
      this.delegate.setREFAtName(arg0, arg1);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject24858, e);
    }
  }
  
  public OraclePreparedStatement _getDelegate_()
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
      methodObject24828 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, Blob.class });
      methodObject24809 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, BINARY_FLOAT.class });
      methodObject24855 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class });
      methodObject24893 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24778 = OraclePreparedStatement.class.getDeclaredMethod("setCLOB", new Class[] { Integer.TYPE, CLOB.class });
      methodObject24798 = OraclePreparedStatement.class.getDeclaredMethod("getExecuteBatch", new Class[0]);
      methodObject24940 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, int[].class });
      methodObject24863 = OraclePreparedStatement.class.getDeclaredMethod("setFormOfUse", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24979 = OracleStatement.class.getDeclaredMethod("defineColumnTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24757 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject24779 = OraclePreparedStatement.class.getDeclaredMethod("setBFILE", new Class[] { Integer.TYPE, BFILE.class });
      methodObject24915 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24825 = OraclePreparedStatement.class.getDeclaredMethod("setDATEAtName", new Class[] { String.class, DATE.class });
      methodObject24829 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24781 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYM", new Class[] { Integer.TYPE, INTERVALYM.class });
      methodObject24780 = OraclePreparedStatement.class.getDeclaredMethod("setBfile", new Class[] { Integer.TYPE, BFILE.class });
      methodObject24952 = Statement.class.getDeclaredMethod("setFetchSize", new Class[] { Integer.TYPE });
      methodObject24856 = OraclePreparedStatement.class.getDeclaredMethod("setRefTypeAtName", new Class[] { String.class, REF.class });
      methodObject24814 = OraclePreparedStatement.class.getDeclaredMethod("setStringForClobAtName", new Class[] { String.class, String.class });
      methodObject24964 = Statement.class.getDeclaredMethod("isPoolable", new Class[0]);
      methodObject24839 = OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class });
      methodObject24755 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24968 = Statement.class.getDeclaredMethod("setMaxRows", new Class[] { Integer.TYPE });
      methodObject24842 = OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class, Calendar.class });
      methodObject24965 = Statement.class.getDeclaredMethod("setCursorName", new Class[] { String.class });
      methodObject24963 = Statement.class.getDeclaredMethod("getUpdateCount", new Class[0]);
      methodObject24794 = OraclePreparedStatement.class.getDeclaredMethod("setOracleObject", new Class[] { Integer.TYPE, Datum.class });
      methodObject24884 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class, Calendar.class });
      methodObject24876 = PreparedStatement.class.getDeclaredMethod("setBoolean", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject24935 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, String[].class });
      methodObject24767 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, BINARY_DOUBLE.class });
      methodObject24890 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class });
      methodObject24892 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24862 = OraclePreparedStatement.class.getDeclaredMethod("setPlsqlIndexTable", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24939 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, Integer.TYPE });
      methodObject24942 = Statement.class.getDeclaredMethod("executeBatch", new Class[0]);
      methodObject24962 = Statement.class.getDeclaredMethod("getResultSetType", new Class[0]);
      methodObject24854 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE });
      methodObject24871 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24945 = Statement.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject24792 = OraclePreparedStatement.class.getDeclaredMethod("setRefType", new Class[] { Integer.TYPE, REF.class });
      methodObject24988 = OracleStatement.class.getDeclaredMethod("setDatabaseChangeRegistration", new Class[] { DatabaseChangeRegistration.class });
      methodObject24885 = PreparedStatement.class.getDeclaredMethod("setURL", new Class[] { Integer.TYPE, URL.class });
      methodObject24907 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class, Calendar.class });
      methodObject24971 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject24910 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24804 = OraclePreparedStatement.class.getDeclaredMethod("setIntAtName", new Class[] { String.class, Integer.TYPE });
      methodObject24946 = Statement.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject24864 = OraclePreparedStatement.class.getDeclaredMethod("setDisableStmtCaching", new Class[] { Boolean.TYPE });
      methodObject24916 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Clob.class });
      methodObject24806 = OraclePreparedStatement.class.getDeclaredMethod("setFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject24799 = OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE, String.class });
      methodObject24865 = OraclePreparedStatement.class.getDeclaredMethod("OracleGetParameterMetaData", new Class[0]);
      methodObject24821 = OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptorAtName", new Class[] { String.class, StructDescriptor.class });
      methodObject24810 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject24985 = OracleStatement.class.getDeclaredMethod("setLobPrefetchSize", new Class[] { Integer.TYPE });
      methodObject24823 = OraclePreparedStatement.class.getDeclaredMethod("setRAWAtName", new Class[] { String.class, RAW.class });
      methodObject24900 = PreparedStatement.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject24950 = Statement.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject24849 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZAtName", new Class[] { String.class, TIMESTAMPLTZ.class });
      methodObject24866 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24982 = OracleStatement.class.getDeclaredMethod("setResultSetCache", new Class[] { OracleResultSetCache.class });
      methodObject24836 = OraclePreparedStatement.class.getDeclaredMethod("setBfileAtName", new Class[] { String.class, BFILE.class });
      methodObject24835 = OraclePreparedStatement.class.getDeclaredMethod("setBFILEAtName", new Class[] { String.class, BFILE.class });
      methodObject24762 = OraclePreparedStatement.class.getDeclaredMethod("sendBatch", new Class[0]);
      methodObject24870 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, NClob.class });
      methodObject24819 = OraclePreparedStatement.class.getDeclaredMethod("setARRAYAtName", new Class[] { String.class, ARRAY.class });
      methodObject24776 = OraclePreparedStatement.class.getDeclaredMethod("setNUMBER", new Class[] { Integer.TYPE, NUMBER.class });
      methodObject24795 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeBytes", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24955 = Statement.class.getDeclaredMethod("getGeneratedKeys", new Class[0]);
      methodObject24981 = OracleStatement.class.getDeclaredMethod("getRowPrefetch", new Class[0]);
      methodObject24947 = Statement.class.getDeclaredMethod("getFetchDirection", new Class[0]);
      methodObject24872 = OraclePreparedStatement.class.getDeclaredMethod("setNClobAtName", new Class[] { String.class, Reader.class });
      methodObject24800 = OraclePreparedStatement.class.getDeclaredMethod("setNullAtName", new Class[] { String.class, Integer.TYPE });
      methodObject24914 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24898 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24848 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZAtName", new Class[] { String.class, TIMESTAMPTZ.class });
      methodObject24763 = OraclePreparedStatement.class.getDeclaredMethod("setExecuteBatch", new Class[] { Integer.TYPE });
      methodObject24928 = PreparedStatement.class.getDeclaredMethod("setArray", new Class[] { Integer.TYPE, Array.class });
      methodObject24766 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24775 = OraclePreparedStatement.class.getDeclaredMethod("setDATE", new Class[] { Integer.TYPE, DATE.class });
      methodObject24831 = OraclePreparedStatement.class.getDeclaredMethod("setCLOBAtName", new Class[] { String.class, CLOB.class });
      methodObject24904 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class });
      methodObject24973 = OracleStatement.class.getDeclaredMethod("isNCHAR", new Class[] { Integer.TYPE });
      methodObject24791 = OraclePreparedStatement.class.getDeclaredMethod("setORAData", new Class[] { Integer.TYPE, ORAData.class });
      methodObject24843 = OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class });
      methodObject24760 = OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24905 = PreparedStatement.class.getDeclaredMethod("setTime", new Class[] { Integer.TYPE, Time.class, Calendar.class });
      methodObject24833 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24803 = OraclePreparedStatement.class.getDeclaredMethod("setShortAtName", new Class[] { String.class, Short.TYPE });
      methodObject24875 = OraclePreparedStatement.class.getDeclaredMethod("setSQLXMLAtName", new Class[] { String.class, SQLXML.class });
      methodObject24974 = OracleStatement.class.getDeclaredMethod("clearDefines", new Class[0]);
      methodObject24869 = OraclePreparedStatement.class.getDeclaredMethod("getReturnResultSet", new Class[0]);
      methodObject24832 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Clob.class });
      methodObject24868 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24783 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMP", new Class[] { Integer.TYPE, TIMESTAMP.class });
      methodObject24793 = OraclePreparedStatement.class.getDeclaredMethod("setREF", new Class[] { Integer.TYPE, REF.class });
      methodObject24970 = Statement.class.getDeclaredMethod("setQueryTimeout", new Class[] { Integer.TYPE });
      methodObject24961 = Statement.class.getDeclaredMethod("getResultSetConcurrency", new Class[0]);
      methodObject24805 = OraclePreparedStatement.class.getDeclaredMethod("setLongAtName", new Class[] { String.class, Long.TYPE });
      methodObject24857 = OraclePreparedStatement.class.getDeclaredMethod("setRefAtName", new Class[] { String.class, Ref.class });
      methodObject24840 = OraclePreparedStatement.class.getDeclaredMethod("setDateAtName", new Class[] { String.class, Date.class, Calendar.class });
      methodObject24859 = OraclePreparedStatement.class.getDeclaredMethod("setOracleObjectAtName", new Class[] { String.class, Datum.class });
      methodObject24975 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24772 = OraclePreparedStatement.class.getDeclaredMethod("setROWID", new Class[] { Integer.TYPE, ROWID.class });
      methodObject24902 = PreparedStatement.class.getDeclaredMethod("setBytes", new Class[] { Integer.TYPE, byte[].class });
      methodObject24938 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class });
      methodObject24953 = Statement.class.getDeclaredMethod("addBatch", new Class[] { String.class });
      methodObject24934 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, int[].class });
      methodObject24983 = OracleStatement.class.getDeclaredMethod("setRowPrefetch", new Class[] { Integer.TYPE });
      methodObject24808 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloatAtName", new Class[] { String.class, Float.TYPE });
      methodObject24936 = Statement.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject24826 = OraclePreparedStatement.class.getDeclaredMethod("setNUMBERAtName", new Class[] { String.class, NUMBER.class });
      methodObject24896 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class });
      methodObject24937 = Statement.class.getDeclaredMethod("getResultSet", new Class[0]);
      methodObject24769 = OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlob", new Class[] { Integer.TYPE, byte[].class });
      methodObject24813 = OraclePreparedStatement.class.getDeclaredMethod("setStringAtName", new Class[] { String.class, String.class });
      methodObject24883 = PreparedStatement.class.getDeclaredMethod("setTimestamp", new Class[] { Integer.TYPE, Timestamp.class });
      methodObject24909 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class });
      methodObject24802 = OraclePreparedStatement.class.getDeclaredMethod("setByteAtName", new Class[] { String.class, Byte.TYPE });
      methodObject24986 = OracleStatement.class.getDeclaredMethod("closeWithKey", new Class[] { String.class });
      methodObject24853 = OraclePreparedStatement.class.getDeclaredMethod("setObjectAtName", new Class[] { String.class, Object.class, Integer.TYPE, Integer.TYPE });
      methodObject24990 = OracleStatement.class.getDeclaredMethod("getRegisteredQueryId", new Class[0]);
      methodObject24977 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE, Short.TYPE });
      methodObject24787 = OraclePreparedStatement.class.getDeclaredMethod("setOPAQUE", new Class[] { Integer.TYPE, OPAQUE.class });
      methodObject24967 = Statement.class.getDeclaredMethod("setMaxFieldSize", new Class[] { Integer.TYPE });
      methodObject24846 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDSAtName", new Class[] { String.class, INTERVALDS.class });
      methodObject24912 = PreparedStatement.class.getDeclaredMethod("setBigDecimal", new Class[] { Integer.TYPE, BigDecimal.class });
      methodObject24976 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24897 = PreparedStatement.class.getDeclaredMethod("setNCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24978 = OracleStatement.class.getDeclaredMethod("defineColumnType", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24929 = PreparedStatement.class.getDeclaredMethod("setRef", new Class[] { Integer.TYPE, Ref.class });
      methodObject24924 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject24811 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryDoubleAtName", new Class[] { String.class, BINARY_DOUBLE.class });
      methodObject24958 = Statement.class.getDeclaredMethod("getMoreResults", new Class[0]);
      methodObject24879 = PreparedStatement.class.getDeclaredMethod("setFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24768 = OraclePreparedStatement.class.getDeclaredMethod("setStringForClob", new Class[] { Integer.TYPE, String.class });
      methodObject24919 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, NClob.class });
      methodObject24761 = OraclePreparedStatement.class.getDeclaredMethod("setNCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject24818 = OraclePreparedStatement.class.getDeclaredMethod("setArrayAtName", new Class[] { String.class, Array.class });
      methodObject24847 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPAtName", new Class[] { String.class, TIMESTAMP.class });
      methodObject24822 = OraclePreparedStatement.class.getDeclaredMethod("setSTRUCTAtName", new Class[] { String.class, STRUCT.class });
      methodObject24827 = OraclePreparedStatement.class.getDeclaredMethod("setBLOBAtName", new Class[] { String.class, BLOB.class });
      methodObject24816 = OraclePreparedStatement.class.getDeclaredMethod("setCursorAtName", new Class[] { String.class, ResultSet.class });
      methodObject24957 = Statement.class.getDeclaredMethod("getMaxRows", new Class[0]);
      methodObject24888 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24911 = PreparedStatement.class.getDeclaredMethod("addBatch", new Class[0]);
      methodObject24850 = OraclePreparedStatement.class.getDeclaredMethod("setUnicodeStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24894 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Integer.TYPE });
      methodObject24903 = PreparedStatement.class.getDeclaredMethod("setString", new Class[] { Integer.TYPE, String.class });
      methodObject24913 = PreparedStatement.class.getDeclaredMethod("setBlob", new Class[] { Integer.TYPE, Blob.class });
      methodObject24752 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Integer.TYPE });
      methodObject24959 = Statement.class.getDeclaredMethod("getMoreResults", new Class[] { Integer.TYPE });
      methodObject24960 = Statement.class.getDeclaredMethod("getQueryTimeout", new Class[0]);
      methodObject24917 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24820 = OraclePreparedStatement.class.getDeclaredMethod("setOPAQUEAtName", new Class[] { String.class, OPAQUE.class });
      methodObject24784 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPTZ", new Class[] { Integer.TYPE, TIMESTAMPTZ.class });
      methodObject24874 = OraclePreparedStatement.class.getDeclaredMethod("setRowIdAtName", new Class[] { String.class, RowId.class });
      methodObject24891 = PreparedStatement.class.getDeclaredMethod("setBinaryStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24773 = OraclePreparedStatement.class.getDeclaredMethod("setRAW", new Class[] { Integer.TYPE, RAW.class });
      methodObject24899 = PreparedStatement.class.getDeclaredMethod("executeQuery", new Class[0]);
      methodObject24972 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject24926 = PreparedStatement.class.getDeclaredMethod("setSQLXML", new Class[] { Integer.TYPE, SQLXML.class });
      methodObject24796 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24889 = PreparedStatement.class.getDeclaredMethod("setAsciiStream", new Class[] { Integer.TYPE, InputStream.class, Long.TYPE });
      methodObject24789 = OraclePreparedStatement.class.getDeclaredMethod("setSTRUCT", new Class[] { Integer.TYPE, STRUCT.class });
      methodObject24790 = OraclePreparedStatement.class.getDeclaredMethod("setCustomDatum", new Class[] { Integer.TYPE, CustomDatum.class });
      methodObject24764 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, Float.TYPE });
      methodObject24807 = OraclePreparedStatement.class.getDeclaredMethod("setDoubleAtName", new Class[] { String.class, Double.TYPE });
      methodObject24774 = OraclePreparedStatement.class.getDeclaredMethod("setCHAR", new Class[] { Integer.TYPE, CHAR.class });
      methodObject24895 = PreparedStatement.class.getDeclaredMethod("setCharacterStream", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24951 = Statement.class.getDeclaredMethod("setFetchDirection", new Class[] { Integer.TYPE });
      methodObject24949 = Statement.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject24918 = PreparedStatement.class.getDeclaredMethod("setClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24797 = OraclePreparedStatement.class.getDeclaredMethod("defineParameterType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24932 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class });
      methodObject24943 = Statement.class.getDeclaredMethod("executeQuery", new Class[] { String.class });
      methodObject24944 = Statement.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject24867 = OraclePreparedStatement.class.getDeclaredMethod("registerReturnParameter", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24770 = OraclePreparedStatement.class.getDeclaredMethod("setFixedCHAR", new Class[] { Integer.TYPE, String.class });
      methodObject24987 = OracleStatement.class.getDeclaredMethod("creationState", new Class[0]);
      methodObject24881 = PreparedStatement.class.getDeclaredMethod("setLong", new Class[] { Integer.TYPE, Long.TYPE });
      methodObject24817 = OraclePreparedStatement.class.getDeclaredMethod("setROWIDAtName", new Class[] { String.class, ROWID.class });
      methodObject24841 = OraclePreparedStatement.class.getDeclaredMethod("setTimeAtName", new Class[] { String.class, Time.class });
      methodObject24860 = OraclePreparedStatement.class.getDeclaredMethod("setURLAtName", new Class[] { String.class, URL.class });
      methodObject24933 = Statement.class.getDeclaredMethod("execute", new Class[] { String.class, Integer.TYPE });
      methodObject24786 = OraclePreparedStatement.class.getDeclaredMethod("setARRAY", new Class[] { Integer.TYPE, ARRAY.class });
      methodObject24851 = OraclePreparedStatement.class.getDeclaredMethod("setCustomDatumAtName", new Class[] { String.class, CustomDatum.class });
      methodObject24931 = Statement.class.getDeclaredMethod("close", new Class[0]);
      methodObject24765 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryFloat", new Class[] { Integer.TYPE, BINARY_FLOAT.class });
      methodObject24922 = PreparedStatement.class.getDeclaredMethod("setNString", new Class[] { Integer.TYPE, String.class });
      methodObject24873 = OraclePreparedStatement.class.getDeclaredMethod("setNStringAtName", new Class[] { String.class, String.class });
      methodObject24785 = OraclePreparedStatement.class.getDeclaredMethod("setTIMESTAMPLTZ", new Class[] { Integer.TYPE, TIMESTAMPLTZ.class });
      methodObject24852 = OraclePreparedStatement.class.getDeclaredMethod("setORADataAtName", new Class[] { String.class, ORAData.class });
      methodObject24812 = OraclePreparedStatement.class.getDeclaredMethod("setBigDecimalAtName", new Class[] { String.class, BigDecimal.class });
      methodObject24771 = OraclePreparedStatement.class.getDeclaredMethod("setCursor", new Class[] { Integer.TYPE, ResultSet.class });
      methodObject24756 = OraclePreparedStatement.class.getDeclaredMethod("setBinaryStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24880 = PreparedStatement.class.getDeclaredMethod("setInt", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24838 = OraclePreparedStatement.class.getDeclaredMethod("setBytesForBlobAtName", new Class[] { String.class, byte[].class });
      methodObject24882 = PreparedStatement.class.getDeclaredMethod("setShort", new Class[] { Integer.TYPE, Short.TYPE });
      methodObject24782 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALDS", new Class[] { Integer.TYPE, INTERVALDS.class });
      methodObject24861 = OraclePreparedStatement.class.getDeclaredMethod("setCheckBindTypes", new Class[] { Boolean.TYPE });
      methodObject24966 = Statement.class.getDeclaredMethod("setEscapeProcessing", new Class[] { Boolean.TYPE });
      methodObject24845 = OraclePreparedStatement.class.getDeclaredMethod("setINTERVALYMAtName", new Class[] { String.class, INTERVALYM.class });
      methodObject24908 = PreparedStatement.class.getDeclaredMethod("setObject", new Class[] { Integer.TYPE, Object.class, Integer.TYPE });
      methodObject24954 = Statement.class.getDeclaredMethod("clearBatch", new Class[0]);
      methodObject24980 = OracleStatement.class.getDeclaredMethod("defineColumnTypeChars", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject24887 = PreparedStatement.class.getDeclaredMethod("executeUpdate", new Class[0]);
      methodObject24877 = PreparedStatement.class.getDeclaredMethod("setByte", new Class[] { Integer.TYPE, Byte.TYPE });
      methodObject24758 = OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class, Long.TYPE });
      methodObject24837 = OraclePreparedStatement.class.getDeclaredMethod("setBytesAtName", new Class[] { String.class, byte[].class });
      methodObject24834 = OraclePreparedStatement.class.getDeclaredMethod("setClobAtName", new Class[] { String.class, Reader.class });
      methodObject24923 = PreparedStatement.class.getDeclaredMethod("setNull", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject24753 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class, Long.TYPE });
      methodObject24948 = Statement.class.getDeclaredMethod("getFetchSize", new Class[0]);
      methodObject24824 = OraclePreparedStatement.class.getDeclaredMethod("setCHARAtName", new Class[] { String.class, CHAR.class });
      methodObject24920 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class, Long.TYPE });
      methodObject24759 = OraclePreparedStatement.class.getDeclaredMethod("setCharacterStreamAtName", new Class[] { String.class, Reader.class });
      methodObject24989 = OracleStatement.class.getDeclaredMethod("getRegisteredTableNames", new Class[0]);
      methodObject24927 = PreparedStatement.class.getDeclaredMethod("clearParameters", new Class[0]);
      methodObject24941 = Statement.class.getDeclaredMethod("executeUpdate", new Class[] { String.class, String[].class });
      methodObject24886 = PreparedStatement.class.getDeclaredMethod("execute", new Class[0]);
      methodObject24984 = OracleStatement.class.getDeclaredMethod("getLobPrefetchSize", new Class[0]);
      methodObject24830 = OraclePreparedStatement.class.getDeclaredMethod("setBlobAtName", new Class[] { String.class, InputStream.class });
      methodObject24901 = PreparedStatement.class.getDeclaredMethod("getParameterMetaData", new Class[0]);
      methodObject24969 = Statement.class.getDeclaredMethod("setPoolable", new Class[] { Boolean.TYPE });
      methodObject24777 = OraclePreparedStatement.class.getDeclaredMethod("setBLOB", new Class[] { Integer.TYPE, BLOB.class });
      methodObject24925 = PreparedStatement.class.getDeclaredMethod("setRowId", new Class[] { Integer.TYPE, RowId.class });
      methodObject24930 = PreparedStatement.class.getDeclaredMethod("setUnicodeStream", new Class[] { Integer.TYPE, InputStream.class, Integer.TYPE });
      methodObject24815 = OraclePreparedStatement.class.getDeclaredMethod("setFixedCHARAtName", new Class[] { String.class, String.class });
      methodObject24906 = PreparedStatement.class.getDeclaredMethod("setDate", new Class[] { Integer.TYPE, Date.class });
      methodObject24801 = OraclePreparedStatement.class.getDeclaredMethod("setBooleanAtName", new Class[] { String.class, Boolean.TYPE });
      methodObject24754 = OraclePreparedStatement.class.getDeclaredMethod("setAsciiStreamAtName", new Class[] { String.class, InputStream.class });
      methodObject24844 = OraclePreparedStatement.class.getDeclaredMethod("setTimestampAtName", new Class[] { String.class, Timestamp.class, Calendar.class });
      methodObject24878 = PreparedStatement.class.getDeclaredMethod("setDouble", new Class[] { Integer.TYPE, Double.TYPE });
      methodObject24956 = Statement.class.getDeclaredMethod("getMaxFieldSize", new Class[0]);
      methodObject24921 = PreparedStatement.class.getDeclaredMethod("setNClob", new Class[] { Integer.TYPE, Reader.class });
      methodObject24788 = OraclePreparedStatement.class.getDeclaredMethod("setStructDescriptor", new Class[] { Integer.TYPE, StructDescriptor.class });
      methodObject24858 = OraclePreparedStatement.class.getDeclaredMethod("setREFAtName", new Class[] { String.class, REF.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OraclePreparedStatement$$$Proxy(OraclePreparedStatement paramOraclePreparedStatement, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOraclePreparedStatement;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableStatement$2oracle$1jdbc$1OraclePreparedStatement$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */