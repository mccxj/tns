package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.SocketException;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Wrapper;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import javax.transaction.xa.XAResource;
import oracle.jdbc.OracleConnection.DatabaseShutdownMode;
import oracle.jdbc.OracleConnection.DatabaseStartupMode;
import oracle.jdbc.OracleOCIFailover;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.aq.AQDequeueOptions;
import oracle.jdbc.aq.AQEnqueueOptions;
import oracle.jdbc.aq.AQMessage;
import oracle.jdbc.aq.AQNotificationRegistration;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.internal.KeywordValueLong;
import oracle.jdbc.internal.OracleConnection.BufferCacheStatistics;
import oracle.jdbc.internal.OracleConnection.InstanceProperty;
import oracle.jdbc.internal.OracleConnection.XSOperationCode;
import oracle.jdbc.internal.OracleStatement;
import oracle.jdbc.internal.XSEventListener;
import oracle.jdbc.internal.XSNamespace;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.jdbc.oracore.OracleTypeCLOB;
import oracle.jdbc.pool.OracleConnectionCacheCallback;
import oracle.jdbc.pool.OraclePooledConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.BFILE;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.BLOB;
import oracle.sql.BfileDBAccess;
import oracle.sql.BlobDBAccess;
import oracle.sql.CLOB;
import oracle.sql.ClobDBAccess;
import oracle.sql.CustomDatum;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.StructDescriptor;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
import oracle.sql.TIMEZONETAB;
import oracle.sql.TypeDescriptor;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1internal$1OracleConnection$$$Proxy
  extends NonTxnReplayableConnection
  implements oracle.jdbc.internal.OracleConnection, _Proxy_
{
  private oracle.jdbc.internal.OracleConnection delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject27616;
  private static Method methodObject27647;
  private static Method methodObject27533;
  private static Method methodObject27649;
  private static Method methodObject27562;
  private static Method methodObject27729;
  private static Method methodObject27567;
  private static Method methodObject27661;
  private static Method methodObject27487;
  private static Method methodObject27465;
  private static Method methodObject27668;
  private static Method methodObject27675;
  private static Method methodObject27575;
  private static Method methodObject27711;
  private static Method methodObject27483;
  private static Method methodObject27586;
  private static Method methodObject27705;
  private static Method methodObject27689;
  private static Method methodObject27532;
  private static Method methodObject27659;
  private static Method methodObject27577;
  private static Method methodObject27620;
  private static Method methodObject27682;
  private static Method methodObject27741;
  private static Method methodObject27653;
  private static Method methodObject27658;
  private static Method methodObject27633;
  private static Method methodObject27488;
  private static Method methodObject27507;
  private static Method methodObject27607;
  private static Method methodObject27719;
  private static Method methodObject27519;
  private static Method methodObject27650;
  private static Method methodObject27698;
  private static Method methodObject27694;
  private static Method methodObject27554;
  private static Method methodObject27547;
  private static Method methodObject27693;
  private static Method methodObject27612;
  private static Method methodObject27632;
  private static Method methodObject27740;
  private static Method methodObject27473;
  private static Method methodObject27493;
  private static Method methodObject27599;
  private static Method methodObject27643;
  private static Method methodObject27580;
  private static Method methodObject27750;
  private static Method methodObject27550;
  private static Method methodObject27485;
  private static Method methodObject27472;
  private static Method methodObject27733;
  private static Method methodObject27515;
  private static Method methodObject27626;
  private static Method methodObject27727;
  private static Method methodObject27495;
  private static Method methodObject27648;
  private static Method methodObject27724;
  private static Method methodObject27652;
  private static Method methodObject27720;
  private static Method methodObject27478;
  private static Method methodObject27732;
  private static Method methodObject27543;
  private static Method methodObject27736;
  private static Method methodObject27524;
  private static Method methodObject27461;
  private static Method methodObject27690;
  private static Method methodObject27582;
  private static Method methodObject27606;
  private static Method methodObject27462;
  private static Method methodObject27667;
  private static Method methodObject27555;
  private static Method methodObject27627;
  private static Method methodObject27527;
  private static Method methodObject27644;
  private static Method methodObject27664;
  private static Method methodObject27518;
  private static Method methodObject27654;
  private static Method methodObject27673;
  private static Method methodObject27646;
  private static Method methodObject27597;
  private static Method methodObject27641;
  private static Method methodObject27676;
  private static Method methodObject27592;
  private static Method methodObject27639;
  private static Method methodObject27622;
  private static Method methodObject27656;
  private static Method methodObject27712;
  private static Method methodObject27726;
  private static Method methodObject27499;
  private static Method methodObject27615;
  private static Method methodObject27619;
  private static Method methodObject27681;
  private static Method methodObject27674;
  private static Method methodObject27618;
  private static Method methodObject27540;
  private static Method methodObject27482;
  private static Method methodObject27692;
  private static Method methodObject27486;
  private static Method methodObject27669;
  private static Method methodObject27628;
  private static Method methodObject27564;
  private static Method methodObject27484;
  private static Method methodObject27504;
  private static Method methodObject27696;
  private static Method methodObject27635;
  private static Method methodObject27491;
  private static Method methodObject27714;
  private static Method methodObject27602;
  private static Method methodObject27593;
  private static Method methodObject27494;
  private static Method methodObject27629;
  private static Method methodObject27621;
  private static Method methodObject27728;
  private static Method methodObject27517;
  private static Method methodObject27601;
  private static Method methodObject27548;
  private static Method methodObject27680;
  private static Method methodObject27520;
  private static Method methodObject27479;
  private static Method methodObject27560;
  private static Method methodObject27546;
  private static Method methodObject27542;
  private static Method methodObject27569;
  private static Method methodObject27743;
  private static Method methodObject27585;
  private static Method methodObject27686;
  private static Method methodObject27672;
  private static Method methodObject27730;
  private static Method methodObject27687;
  private static Method methodObject27715;
  private static Method methodObject27625;
  private static Method methodObject27464;
  private static Method methodObject27576;
  private static Method methodObject27688;
  private static Method methodObject27640;
  private static Method methodObject27613;
  private static Method methodObject27739;
  private static Method methodObject27469;
  private static Method methodObject27505;
  private static Method methodObject27474;
  private static Method methodObject27574;
  private static Method methodObject27670;
  private static Method methodObject27589;
  private static Method methodObject27513;
  private static Method methodObject27496;
  private static Method methodObject27702;
  private static Method methodObject27511;
  private static Method methodObject27502;
  private static Method methodObject27725;
  private static Method methodObject27557;
  private static Method methodObject27584;
  private static Method methodObject27677;
  private static Method methodObject27572;
  private static Method methodObject27595;
  private static Method methodObject27561;
  private static Method methodObject27663;
  private static Method methodObject27721;
  private static Method methodObject27594;
  private static Method methodObject27581;
  private static Method methodObject27691;
  private static Method methodObject27642;
  private static Method methodObject27662;
  private static Method methodObject27600;
  private static Method methodObject27624;
  private static Method methodObject27604;
  private static Method methodObject27634;
  private static Method methodObject27471;
  private static Method methodObject27526;
  private static Method methodObject27579;
  private static Method methodObject27538;
  private static Method methodObject27603;
  private static Method methodObject27718;
  private static Method methodObject27660;
  private static Method methodObject27636;
  private static Method methodObject27665;
  private static Method methodObject27749;
  private static Method methodObject27737;
  private static Method methodObject27551;
  private static Method methodObject27501;
  private static Method methodObject27734;
  private static Method methodObject27713;
  private static Method methodObject27531;
  private static Method methodObject27529;
  private static Method methodObject27508;
  private static Method methodObject27703;
  private static Method methodObject27498;
  private static Method methodObject27709;
  private static Method methodObject27583;
  private static Method methodObject27506;
  private static Method methodObject27537;
  private static Method methodObject27559;
  private static Method methodObject27591;
  private static Method methodObject27528;
  private static Method methodObject27587;
  private static Method methodObject27701;
  private static Method methodObject27481;
  private static Method methodObject27685;
  private static Method methodObject27536;
  private static Method methodObject27545;
  private static Method methodObject27530;
  private static Method methodObject27707;
  private static Method methodObject27588;
  private static Method methodObject27722;
  private static Method methodObject27614;
  private static Method methodObject27679;
  private static Method methodObject27563;
  private static Method methodObject27723;
  private static Method methodObject27684;
  private static Method methodObject27571;
  private static Method methodObject27697;
  private static Method methodObject27512;
  private static Method methodObject27751;
  private static Method methodObject27748;
  private static Method methodObject27731;
  private static Method methodObject27525;
  private static Method methodObject27503;
  private static Method methodObject27590;
  private static Method methodObject27480;
  private static Method methodObject27596;
  private static Method methodObject27608;
  private static Method methodObject27695;
  private static Method methodObject27708;
  private static Method methodObject27735;
  private static Method methodObject27492;
  private static Method methodObject27463;
  private static Method methodObject27477;
  private static Method methodObject27558;
  private static Method methodObject27470;
  private static Method methodObject27609;
  private static Method methodObject27578;
  private static Method methodObject27704;
  private static Method methodObject27745;
  private static Method methodObject27683;
  private static Method methodObject27706;
  private static Method methodObject27657;
  private static Method methodObject27556;
  private static Method methodObject27710;
  private static Method methodObject27717;
  private static Method methodObject27573;
  private static Method methodObject27534;
  private static Method methodObject27699;
  private static Method methodObject27489;
  private static Method methodObject27655;
  private static Method methodObject27611;
  private static Method methodObject27747;
  private static Method methodObject27638;
  private static Method methodObject27516;
  private static Method methodObject27610;
  private static Method methodObject27490;
  private static Method methodObject27738;
  private static Method methodObject27466;
  private static Method methodObject27468;
  private static Method methodObject27553;
  private static Method methodObject27637;
  private static Method methodObject27671;
  private static Method methodObject27605;
  private static Method methodObject27617;
  private static Method methodObject27568;
  private static Method methodObject27598;
  private static Method methodObject27522;
  private static Method methodObject27566;
  private static Method methodObject27570;
  private static Method methodObject27651;
  private static Method methodObject27500;
  private static Method methodObject27509;
  private static Method methodObject27521;
  private static Method methodObject27544;
  private static Method methodObject27716;
  private static Method methodObject27514;
  private static Method methodObject27549;
  private static Method methodObject27476;
  private static Method methodObject27497;
  private static Method methodObject27475;
  private static Method methodObject27510;
  private static Method methodObject27523;
  private static Method methodObject27744;
  private static Method methodObject27746;
  private static Method methodObject27539;
  private static Method methodObject27742;
  private static Method methodObject27678;
  private static Method methodObject27631;
  private static Method methodObject27700;
  private static Method methodObject27666;
  private static Method methodObject27623;
  private static Method methodObject27552;
  private static Method methodObject27645;
  private static Method methodObject27467;
  private static Method methodObject27535;
  private static Method methodObject27630;
  private static Method methodObject27541;
  private static Method methodObject27565;
  
  public void setExplicitCachingEnabled(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27616, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setExplicitCachingEnabled(arg0);
      postForAll(methodObject27616);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27616, e);
    }
  }
  
  public AQMessage dequeue(String arg0, AQDequeueOptions arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27647, this, new Object[] { arg0, arg1, arg2 });
      return (AQMessage)postForAll(methodObject27647, (Object)this.delegate.dequeue(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQMessage)postForAll(methodObject27647, onErrorForAll(methodObject27647, e));
    }
  }
  
  public OracleConnectionCacheCallback getConnectionCacheCallbackObj()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27533, this, zeroLengthObjectArray);
      return (OracleConnectionCacheCallback)postForAll(methodObject27533, (Object)this.delegate.getConnectionCacheCallbackObj());
    }
    catch (SQLException e)
    {
      return (OracleConnectionCacheCallback)postForAll(methodObject27533, onErrorForAll(methodObject27533, e));
    }
  }
  
  public DatabaseChangeRegistration getDatabaseChangeRegistration(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27649, this, new Object[] { Integer.valueOf(arg0) });
      return (DatabaseChangeRegistration)postForAll(methodObject27649, (Object)this.delegate.getDatabaseChangeRegistration(arg0));
    }
    catch (SQLException e)
    {
      return (DatabaseChangeRegistration)postForAll(methodObject27649, onErrorForAll(methodObject27649, e));
    }
  }
  
  public void shutdown(OracleConnection.DatabaseShutdownMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27562, this, new Object[] { arg0 });
      this.delegate.shutdown(arg0);
      postForAll(methodObject27562);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27562, e);
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27729, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (PreparedStatement)postForAll(methodObject27729, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27729));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27729, onErrorForAll(methodObject27729, e));
    }
  }
  
  public void close(int arg0)
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27567, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.close(arg0);
      postForClosure(methodObject27567);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27567, e);
    }
  }
  
  public DATE createDATE(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27661, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27661, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27661, onErrorForAll(methodObject27661, e));
    }
  }
  
  public void setFDO(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27487, this, new Object[] { arg0 });
      this.delegate.setFDO(arg0);
      postForAll(methodObject27487);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27487, e);
    }
  }
  
  public CLOB createClob(byte[] arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27465, this, new Object[] { arg0, Short.valueOf(arg1) });
      return (CLOB)postForAll(methodObject27465, (Object)this.delegate.createClob(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject27465, onErrorForAll(methodObject27465, e));
    }
  }
  
  public NUMBER createNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27668, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27668, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27668, onErrorForAll(methodObject27668, e));
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27675, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27675, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27675, onErrorForAll(methodObject27675, e));
    }
  }
  
  public ARRAY createARRAY(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27575, this, new Object[] { arg0, arg1 });
      return (ARRAY)postForAll(methodObject27575, (Object)super.createARRAY(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject27575, onErrorForAll(methodObject27575, e));
    }
  }
  
  public Savepoint setSavepoint()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27711, this, zeroLengthObjectArray);
      return (Savepoint)postForAll(methodObject27711, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(), this, this.proxyCache, methodObject27711));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27711, onErrorForAll(methodObject27711, e));
    }
  }
  
  public void setDefaultFixedString(boolean arg0)
  {
    super.preForAll(methodObject27483, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setDefaultFixedString(arg0);
    postForAll(methodObject27483);
  }
  
  public boolean getAutoClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27586, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27586, Boolean.valueOf(this.delegate.getAutoClose()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27586, onErrorForAll(methodObject27586, e))).booleanValue();
    }
  }
  
  public void setReadOnly(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27705, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setReadOnly(arg0);
      postForAll(methodObject27705);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27705, e);
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27689, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27689, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27689, onErrorForAll(methodObject27689, e));
    }
  }
  
  public void cleanupAndClose(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27532, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.cleanupAndClose(arg0);
      postForAll(methodObject27532);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27532, e);
    }
  }
  
  public DATE createDATE(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27659, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27659, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27659, onErrorForAll(methodObject27659, e));
    }
  }
  
  public oracle.jdbc.OracleConnection unwrap()
  {
    super.preForAll(methodObject27577, this, zeroLengthObjectArray);
    return (oracle.jdbc.OracleConnection)postForAll(methodObject27577, (Object)super.unwrap());
  }
  
  public PreparedStatement getStatementWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27620, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27620, this.proxyFactory.proxyForCache((Object)this.delegate.getStatementWithKey(arg0), this, this.proxyCache, methodObject27620));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27620, onErrorForAll(methodObject27620, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27682, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27682, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27682, onErrorForAll(methodObject27682, e));
    }
  }
  
  public int getTransactionIsolation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27741, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27741, Integer.valueOf(this.delegate.getTransactionIsolation()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27741, onErrorForAll(methodObject27741, e))).intValue();
    }
  }
  
  public void unregisterDatabaseChangeNotification(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27653, this, new Object[] { Long.valueOf(arg0), arg1 });
      this.delegate.unregisterDatabaseChangeNotification(arg0, arg1);
      postForAll(methodObject27653);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27653, e);
    }
  }
  
  public DATE createDATE(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27658, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27658, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27658, onErrorForAll(methodObject27658, e));
    }
  }
  
  public boolean isLogicalConnection()
  {
    super.preForAll(methodObject27633, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27633, Boolean.valueOf(this.delegate.isLogicalConnection()))).booleanValue();
  }
  
  public byte[] getFDO(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27488, this, new Object[] { Boolean.valueOf(arg0) });
      return (byte[])postForAll(methodObject27488, (Object)this.delegate.getFDO(arg0));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject27488, onErrorForAll(methodObject27488, e));
    }
  }
  
  public void getForm(OracleTypeADT arg0, OracleTypeCLOB arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27507, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.getForm(arg0, arg1, arg2);
      postForAll(methodObject27507);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27507, e);
    }
  }
  
  public void setIncludeSynonyms(boolean arg0)
  {
    super.preForAll(methodObject27607, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setIncludeSynonyms(arg0);
    postForAll(methodObject27607);
  }
  
  public SQLXML createSQLXML()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27719, this, zeroLengthObjectArray);
      return (SQLXML)postForAll(methodObject27719, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createSQLXML(), this, this.proxyCache, methodObject27719));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject27719, onErrorForAll(methodObject27719, e));
    }
  }
  
  public void setStartTime(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27519, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.setStartTime(arg0);
      postForAll(methodObject27519);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27519, e);
    }
  }
  
  public void unregisterDatabaseChangeNotification(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27650, this, new Object[] { arg0 });
      this.delegate.unregisterDatabaseChangeNotification(arg0);
      postForAll(methodObject27650);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27650, e);
    }
  }
  
  public String getEncryptionAlgorithmName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27698, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27698, (Object)this.delegate.getEncryptionAlgorithmName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27698, onErrorForAll(methodObject27698, e));
    }
  }
  
  public TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27694, this, zeroLengthObjectArray);
      return (TypeDescriptor[])postForAll(methodObject27694, (Object)this.delegate.getAllTypeDescriptorsInCurrentSchema());
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27694, onErrorForAll(methodObject27694, e));
    }
  }
  
  public int getTimezoneVersionNumber()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27554, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27554, Integer.valueOf(this.delegate.getTimezoneVersionNumber()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27554, onErrorForAll(methodObject27554, e))).intValue();
    }
  }
  
  public void executeLightweightSessionPiggyback(int arg0, byte[] arg1, KeywordValueLong[] arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27547, this, new Object[] { Integer.valueOf(arg0), arg1, arg2, Integer.valueOf(arg3) });
      this.delegate.executeLightweightSessionPiggyback(arg0, arg1, arg2, arg3);
      postForAll(methodObject27547);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27547, e);
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(DATE arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27693, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27693, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27693, onErrorForAll(methodObject27693, e));
    }
  }
  
  public void setStatementCacheSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27612, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setStatementCacheSize(arg0);
      postForAll(methodObject27612);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27612, e);
    }
  }
  
  public String getSessionTimeZoneOffset()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27632, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27632, (Object)this.delegate.getSessionTimeZoneOffset());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27632, onErrorForAll(methodObject27632, e));
    }
  }
  
  public Properties getClientInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27740, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27740, (Object)this.delegate.getClientInfo());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27740, onErrorForAll(methodObject27740, e));
    }
  }
  
  public short getStructAttrNCsId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27473, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27473, Short.valueOf(this.delegate.getStructAttrNCsId()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27473, onErrorForAll(methodObject27473, e))).shortValue();
    }
  }
  
  public void removeAllDescriptor()
  {
    super.preForAll(methodObject27493, this, zeroLengthObjectArray);
    this.delegate.removeAllDescriptor();
    postForAll(methodObject27493);
  }
  
  public boolean getUsingXAFlag()
  {
    super.preForAll(methodObject27599, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27599, Boolean.valueOf(this.delegate.getUsingXAFlag()))).booleanValue();
  }
  
  public void setPlsqlWarnings(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27643, this, new Object[] { arg0 });
      this.delegate.setPlsqlWarnings(arg0);
      postForAll(methodObject27643);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27643, e);
    }
  }
  
  public oracle.jdbc.internal.OracleConnection physicalConnectionWithin()
  {
    super.preForAll(methodObject27580, this, zeroLengthObjectArray);
    return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject27580, this.proxyFactory.proxyForCache((Object)this.delegate.physicalConnectionWithin(), this, this.proxyCache, methodObject27580));
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27750, this, new Object[] { arg0 });
      return postForAll(methodObject27750, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27750, onErrorForAll(methodObject27750, e));
    }
  }
  
  public void setUsable(boolean arg0)
  {
    super.preForAll(methodObject27550, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setUsable(arg0);
    postForAll(methodObject27550);
  }
  
  public oracle.jdbc.OracleConnection getWrapper()
  {
    super.preForAll(methodObject27485, this, zeroLengthObjectArray);
    return (oracle.jdbc.OracleConnection)postForAll(methodObject27485, this.proxyFactory.proxyForCache((Object)this.delegate.getWrapper(), this, this.proxyCache, methodObject27485));
  }
  
  public Class getClassForType(String arg0, Map arg1)
  {
    super.preForAll(methodObject27472, this, new Object[] { arg0, arg1 });
    return (Class)postForAll(methodObject27472, (Object)this.delegate.getClassForType(arg0, arg1));
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27733, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject27733);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27733, e);
    }
  }
  
  public int getMaxNCharbyteSize()
  {
    super.preForAll(methodObject27515, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27515, Integer.valueOf(this.delegate.getMaxNCharbyteSize()))).intValue();
  }
  
  public PreparedStatement prepareStatementWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27626, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27626, this.proxyFactory.proxyForCache((Object)this.delegate.prepareStatementWithKey(arg0), this, this.proxyCache, methodObject27626));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27626, onErrorForAll(methodObject27626, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27727, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (PreparedStatement)postForAll(methodObject27727, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27727));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27727, onErrorForAll(methodObject27727, e));
    }
  }
  
  public Enumeration descriptorCacheKeys()
  {
    super.preForAll(methodObject27495, this, zeroLengthObjectArray);
    return (Enumeration)postForAll(methodObject27495, (Object)this.delegate.descriptorCacheKeys());
  }
  
  public DatabaseChangeRegistration registerDatabaseChangeNotification(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27648, this, new Object[] { arg0 });
      return (DatabaseChangeRegistration)postForAll(methodObject27648, (Object)this.delegate.registerDatabaseChangeNotification(arg0));
    }
    catch (SQLException e)
    {
      return (DatabaseChangeRegistration)postForAll(methodObject27648, onErrorForAll(methodObject27648, e));
    }
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27724, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (CallableStatement)postForAll(methodObject27724, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2), this, this.proxyCache, methodObject27724));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27724, onErrorForAll(methodObject27724, e));
    }
  }
  
  public void unregisterDatabaseChangeNotification(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27652, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.unregisterDatabaseChangeNotification(arg0);
      postForAll(methodObject27652);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27652, e);
    }
  }
  
  public Statement createStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27720, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject27720, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(), this, this.proxyCache, methodObject27720));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27720, onErrorForAll(methodObject27720, e));
    }
  }
  
  public void setJavaObjectTypeMap(Map arg0)
  {
    super.preForAll(methodObject27478, this, new Object[] { arg0 });
    this.delegate.setJavaObjectTypeMap(arg0);
    postForAll(methodObject27478);
  }
  
  public DatabaseMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27732, this, zeroLengthObjectArray);
      return (DatabaseMetaData)postForAll(methodObject27732, this.proxyFactory.proxyForCache((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject27732));
    }
    catch (SQLException e)
    {
      return (DatabaseMetaData)postForAll(methodObject27732, onErrorForAll(methodObject27732, e));
    }
  }
  
  public boolean isV8Compatible()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27543, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27543, Boolean.valueOf(this.delegate.isV8Compatible()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27543, onErrorForAll(methodObject27543, e))).booleanValue();
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27736, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27736, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27736, onErrorForAll(methodObject27736, e))).booleanValue();
    }
  }
  
  public Connection getLogicalConnection(OraclePooledConnection arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27524, this, new Object[] { arg0, Boolean.valueOf(arg1) });
      return (Connection)postForAll(methodObject27524, this.proxyFactory.proxyForCache((Object)this.delegate.getLogicalConnection(arg0, arg1), this, this.proxyCache, methodObject27524));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject27524, onErrorForAll(methodObject27524, e));
    }
  }
  
  public Object getDescriptor(byte[] arg0)
  {
    super.preForAll(methodObject27461, this, new Object[] { arg0 });
    return postForAll(methodObject27461, this.proxyFactory.proxyForCache(this.delegate.getDescriptor(arg0), this, this.proxyCache, methodObject27461));
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27690, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27690, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27690, onErrorForAll(methodObject27690, e));
    }
  }
  
  public int pingDatabase(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27582, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject27582, Integer.valueOf(this.delegate.pingDatabase(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27582, onErrorForAll(methodObject27582, e))).intValue();
    }
  }
  
  public void setEndToEndMetrics(String[] arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27606, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setEndToEndMetrics(arg0, arg1);
      postForAll(methodObject27606);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27606, e);
    }
  }
  
  public String getURL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27462, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27462, (Object)this.delegate.getURL());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27462, onErrorForAll(methodObject27462, e));
    }
  }
  
  public NUMBER createNUMBER(short arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27667, this, new Object[] { Short.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27667, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27667, onErrorForAll(methodObject27667, e));
    }
  }
  
  public TIMEZONETAB getTIMEZONETAB()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27555, this, zeroLengthObjectArray);
      return (TIMEZONETAB)postForAll(methodObject27555, (Object)this.delegate.getTIMEZONETAB());
    }
    catch (SQLException e)
    {
      return (TIMEZONETAB)postForAll(methodObject27555, onErrorForAll(methodObject27555, e));
    }
  }
  
  public CallableStatement prepareCallWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27627, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27627, this.proxyFactory.proxyForCache((Object)this.delegate.prepareCallWithKey(arg0), this, this.proxyCache, methodObject27627));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27627, onErrorForAll(methodObject27627, e));
    }
  }
  
  public int getHeapAllocSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27527, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27527, Integer.valueOf(this.delegate.getHeapAllocSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27527, onErrorForAll(methodObject27527, e))).intValue();
    }
  }
  
  public AQNotificationRegistration[] registerAQNotification(String[] arg0, Properties[] arg1, Properties arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27644, this, new Object[] { arg0, arg1, arg2 });
      return (AQNotificationRegistration[])postForAll(methodObject27644, (Object)this.delegate.registerAQNotification(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQNotificationRegistration[])postForAll(methodObject27644, onErrorForAll(methodObject27644, e));
    }
  }
  
  public INTERVALYM createINTERVALYM(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27664, this, new Object[] { arg0 });
      return (INTERVALYM)postForAll(methodObject27664, (Object)this.delegate.createINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject27664, onErrorForAll(methodObject27664, e));
    }
  }
  
  public int javaCharsToNCHARBytes(char[] arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27518, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject27518, Integer.valueOf(this.delegate.javaCharsToNCHARBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27518, onErrorForAll(methodObject27518, e))).intValue();
    }
  }
  
  public BINARY_DOUBLE createBINARY_DOUBLE(double arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27654, this, new Object[] { Double.valueOf(arg0) });
      return (BINARY_DOUBLE)postForAll(methodObject27654, (Object)this.delegate.createBINARY_DOUBLE(arg0));
    }
    catch (SQLException e)
    {
      return (BINARY_DOUBLE)postForAll(methodObject27654, onErrorForAll(methodObject27654, e));
    }
  }
  
  public NUMBER createNUMBER(BigInteger arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27673, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject27673, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27673, onErrorForAll(methodObject27673, e));
    }
  }
  
  public AQMessage dequeue(String arg0, AQDequeueOptions arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27646, this, new Object[] { arg0, arg1, arg2 });
      return (AQMessage)postForAll(methodObject27646, (Object)this.delegate.dequeue(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQMessage)postForAll(methodObject27646, onErrorForAll(methodObject27646, e));
    }
  }
  
  public short getStructAttrCsId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27597, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27597, Short.valueOf(this.delegate.getStructAttrCsId()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27597, onErrorForAll(methodObject27597, e))).shortValue();
    }
  }
  
  public void setConnectionReleasePriority(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27641, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setConnectionReleasePriority(arg0);
      postForAll(methodObject27641);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27641, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(DATE arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27676, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27676, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27676, onErrorForAll(methodObject27676, e));
    }
  }
  
  public boolean getRestrictGetTables()
  {
    super.preForAll(methodObject27592, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27592, Boolean.valueOf(this.delegate.getRestrictGetTables()))).booleanValue();
  }
  
  public Properties getUnMatchedConnectionAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27639, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27639, (Object)this.delegate.getUnMatchedConnectionAttributes());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27639, onErrorForAll(methodObject27639, e));
    }
  }
  
  public void setUsingXAFlag(boolean arg0)
  {
    super.preForAll(methodObject27622, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setUsingXAFlag(arg0);
    postForAll(methodObject27622);
  }
  
  public DATE createDATE(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27656, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27656, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27656, onErrorForAll(methodObject27656, e));
    }
  }
  
  public Savepoint setSavepoint(String arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27712, this, new Object[] { arg0 });
      return (Savepoint)postForAll(methodObject27712, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(arg0), this, this.proxyCache, methodObject27712));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27712, onErrorForAll(methodObject27712, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27726, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27726, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0), this, this.proxyCache, methodObject27726));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27726, onErrorForAll(methodObject27726, e));
    }
  }
  
  public Datum toDatum(CustomDatum arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27499, this, new Object[] { arg0 });
      return (Datum)postForAll(methodObject27499, (Object)this.delegate.toDatum(arg0));
    }
    catch (SQLException e)
    {
      return (Datum)postForAll(methodObject27499, onErrorForAll(methodObject27499, e));
    }
  }
  
  public boolean getImplicitCachingEnabled()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27615, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27615, Boolean.valueOf(this.delegate.getImplicitCachingEnabled()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27615, onErrorForAll(methodObject27615, e))).booleanValue();
    }
  }
  
  public void purgeExplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27619, this, zeroLengthObjectArray);
      this.delegate.purgeExplicitCache();
      postForAll(methodObject27619);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27619, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27681, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27681, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27681, onErrorForAll(methodObject27681, e));
    }
  }
  
  public NUMBER createNUMBER(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27674, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (NUMBER)postForAll(methodObject27674, (Object)this.delegate.createNUMBER(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27674, onErrorForAll(methodObject27674, e));
    }
  }
  
  public void purgeImplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27618, this, zeroLengthObjectArray);
      this.delegate.purgeImplicitCache();
      postForAll(methodObject27618);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27618, e);
    }
  }
  
  public boolean isDescriptorSharable(oracle.jdbc.internal.OracleConnection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27540, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27540, Boolean.valueOf(this.delegate.isDescriptorSharable((arg0 instanceof _Proxy_) ? (oracle.jdbc.internal.OracleConnection)((_Proxy_)arg0)._getDelegate_() : arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27540, onErrorForAll(methodObject27540, e))).booleanValue();
    }
  }
  
  public ClobDBAccess createClobDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27482, this, zeroLengthObjectArray);
      return (ClobDBAccess)postForAll(methodObject27482, (Object)this.delegate.createClobDBAccess());
    }
    catch (SQLException e)
    {
      return (ClobDBAccess)postForAll(methodObject27482, onErrorForAll(methodObject27482, e));
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27692, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27692, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27692, onErrorForAll(methodObject27692, e));
    }
  }
  
  public Class classForNameAndSchema(String arg0, String arg1)
    throws ClassNotFoundException
  {
    super.preForAll(methodObject27486, this, new Object[] { arg0, arg1 });
    return (Class)postForAll(methodObject27486, (Object)this.delegate.classForNameAndSchema(arg0, arg1));
  }
  
  public NUMBER createNUMBER(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27669, this, new Object[] { Long.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27669, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27669, onErrorForAll(methodObject27669, e));
    }
  }
  
  public void setCreateStatementAsRefCursor(boolean arg0)
  {
    super.preForAll(methodObject27628, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCreateStatementAsRefCursor(arg0);
    postForAll(methodObject27628);
  }
  
  public Properties getProperties()
  {
    super.preForAll(methodObject27564, this, zeroLengthObjectArray);
    return (Properties)postForAll(methodObject27564, (Object)this.delegate.getProperties());
  }
  
  public boolean getDefaultFixedString()
  {
    super.preForAll(methodObject27484, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27484, Boolean.valueOf(this.delegate.getDefaultFixedString()))).booleanValue();
  }
  
  public ResultSet newArrayDataResultSet(ARRAY arg0, long arg1, int arg2, Map arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27504, this, new Object[] { arg0, Long.valueOf(arg1), Integer.valueOf(arg2), arg3 });
      return (ResultSet)postForAll(methodObject27504, this.proxyFactory.proxyForCache((Object)this.delegate.newArrayDataResultSet(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27504));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject27504, onErrorForAll(methodObject27504, e));
    }
  }
  
  public TypeDescriptor[] getTypeDescriptorsFromList(String[][] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27696, this, new Object[] { arg0 });
      return (TypeDescriptor[])postForAll(methodObject27696, (Object)this.delegate.getTypeDescriptorsFromList(arg0));
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27696, onErrorForAll(methodObject27696, e));
    }
  }
  
  public void setWrapper(oracle.jdbc.OracleConnection arg0)
  {
    super.preForAll(methodObject27635, this, new Object[] { arg0 });
    this.delegate.setWrapper((arg0 instanceof _Proxy_) ? (oracle.jdbc.OracleConnection)((_Proxy_)arg0)._getDelegate_() : arg0);
    postForAll(methodObject27635);
  }
  
  public oracle.jdbc.internal.OracleConnection getPhysicalConnection()
  {
    super.preForAll(methodObject27491, this, zeroLengthObjectArray);
    return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject27491, this.proxyFactory.proxyForCache((Object)this.delegate.getPhysicalConnection(), this, this.proxyCache, methodObject27491));
  }
  
  public Array createArrayOf(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27714, this, new Object[] { arg0, arg1 });
      return (Array)postForAll(methodObject27714, this.proxyFactory.proxyForCreateCache((Object)super.createArrayOf(arg0, arg1), this, this.proxyCache, methodObject27714));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27714, onErrorForAll(methodObject27714, e));
    }
  }
  
  public void registerSQLType(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27602, this, new Object[] { arg0, arg1 });
      this.delegate.registerSQLType(arg0, arg1);
      postForAll(methodObject27602);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27602, e);
    }
  }
  
  public Object getJavaObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27593, this, new Object[] { arg0 });
      return postForAll(methodObject27593, this.proxyFactory.proxyForCache(this.delegate.getJavaObject(arg0), this, this.proxyCache, methodObject27593));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27593, onErrorForAll(methodObject27593, e));
    }
  }
  
  public int numberOfDescriptorCacheEntries()
  {
    super.preForAll(methodObject27494, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27494, Integer.valueOf(this.delegate.numberOfDescriptorCacheEntries()))).intValue();
  }
  
  public boolean getCreateStatementAsRefCursor()
  {
    super.preForAll(methodObject27629, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27629, Boolean.valueOf(this.delegate.getCreateStatementAsRefCursor()))).booleanValue();
  }
  
  public CallableStatement getCallWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27621, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27621, this.proxyFactory.proxyForCache((Object)this.delegate.getCallWithKey(arg0), this, this.proxyCache, methodObject27621));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27621, onErrorForAll(methodObject27621, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27728, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (PreparedStatement)postForAll(methodObject27728, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27728));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27728, onErrorForAll(methodObject27728, e));
    }
  }
  
  public int javaCharsToCHARBytes(char[] arg0, int arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27517, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject27517, Integer.valueOf(this.delegate.javaCharsToCHARBytes(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27517, onErrorForAll(methodObject27517, e))).intValue();
    }
  }
  
  public void registerSQLType(String arg0, Class arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27601, this, new Object[] { arg0, arg1 });
      this.delegate.registerSQLType(arg0, arg1);
      postForAll(methodObject27601);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27601, e);
    }
  }
  
  public void doXSNamespaceOp(OracleConnection.XSOperationCode arg0, byte[] arg1, XSNamespace[] arg2, XSNamespace[][] arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27548, this, new Object[] { arg0, arg1, arg2, arg3 });
      this.delegate.doXSNamespaceOp(arg0, arg1, arg2, arg3);
      postForAll(methodObject27548);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27548, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27680, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27680, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27680, onErrorForAll(methodObject27680, e));
    }
  }
  
  public long getStartTime()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27520, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject27520, Long.valueOf(this.delegate.getStartTime()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject27520, onErrorForAll(methodObject27520, e))).longValue();
    }
  }
  
  public byte getInstanceProperty(OracleConnection.InstanceProperty arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27479, this, new Object[] { arg0 });
      return ((Byte)postForAll(methodObject27479, Byte.valueOf(this.delegate.getInstanceProperty(arg0)))).byteValue();
    }
    catch (SQLException e)
    {
      return ((Byte)postForAll(methodObject27479, onErrorForAll(methodObject27479, e))).byteValue();
    }
  }
  
  public boolean isLobStreamPosStandardCompliant()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27560, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27560, Boolean.valueOf(this.delegate.isLobStreamPosStandardCompliant()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27560, onErrorForAll(methodObject27560, e))).booleanValue();
    }
  }
  
  public void executeLightweightSessionRoundtrip(int arg0, byte[] arg1, KeywordValueLong[] arg2, int arg3, KeywordValueLong[][] arg4, int[] arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27546, this, new Object[] { Integer.valueOf(arg0), arg1, arg2, Integer.valueOf(arg3), arg4, arg5 });
      this.delegate.executeLightweightSessionRoundtrip(arg0, arg1, arg2, arg3, arg4, arg5);
      postForAll(methodObject27546);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27546, e);
    }
  }
  
  public XAResource getXAResource()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27542, this, zeroLengthObjectArray);
      return (XAResource)postForAll(methodObject27542, (Object)this.delegate.getXAResource());
    }
    catch (SQLException e)
    {
      return (XAResource)postForAll(methodObject27542, onErrorForAll(methodObject27542, e));
    }
  }
  
  public void oracleRollback(OracleSavepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27569, this, new Object[] { arg0 });
      this.delegate.oracleRollback((arg0 instanceof _Proxy_) ? (OracleSavepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27569);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27569, e);
    }
  }
  
  public String nativeSQL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27743, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27743, (Object)this.delegate.nativeSQL(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27743, onErrorForAll(methodObject27743, e));
    }
  }
  
  public void openProxySession(int arg0, Properties arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27585, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.openProxySession(arg0, arg1);
      postForAll(methodObject27585);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27585, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27686, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27686, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27686, onErrorForAll(methodObject27686, e));
    }
  }
  
  public NUMBER createNUMBER(BigDecimal arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27672, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject27672, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27672, onErrorForAll(methodObject27672, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27730, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27730, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27730));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27730, onErrorForAll(methodObject27730, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27687, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27687, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27687, onErrorForAll(methodObject27687, e));
    }
  }
  
  public Struct createStruct(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27715, this, new Object[] { arg0, arg1 });
      return (Struct)postForAll(methodObject27715, this.proxyFactory.proxyForCreateCache((Object)super.createStruct(arg0, arg1), this, this.proxyCache, methodObject27715));
    }
    catch (SQLException e)
    {
      return (Struct)postForAll(methodObject27715, onErrorForAll(methodObject27715, e));
    }
  }
  
  public void startup(OracleConnection.DatabaseStartupMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27625, this, new Object[] { arg0 });
      this.delegate.startup(arg0);
      postForAll(methodObject27625);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27625, e);
    }
  }
  
  public CLOB createClob(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27464, this, new Object[] { arg0 });
      return (CLOB)postForAll(methodObject27464, (Object)this.delegate.createClob(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject27464, onErrorForAll(methodObject27464, e));
    }
  }
  
  public Array createOracleArray(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27576, this, new Object[] { arg0, arg1 });
      return (Array)postForAll(methodObject27576, this.proxyFactory.proxyForCreateCache((Object)super.createOracleArray(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1), this, this.proxyCache, methodObject27576));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27576, onErrorForAll(methodObject27576, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(DATE arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27688, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27688, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27688, onErrorForAll(methodObject27688, e));
    }
  }
  
  public void registerConnectionCacheCallback(OracleConnectionCacheCallback arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27640, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.registerConnectionCacheCallback(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject27640);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27640, e);
    }
  }
  
  public int getStatementCacheSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27613, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27613, Integer.valueOf(this.delegate.getStatementCacheSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27613, onErrorForAll(methodObject27613, e))).intValue();
    }
  }
  
  public String getClientInfo(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27739, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27739, (Object)this.delegate.getClientInfo(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27739, onErrorForAll(methodObject27739, e));
    }
  }
  
  public Map getTypeMap()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27469, this, zeroLengthObjectArray);
      return (Map)postForAll(methodObject27469, (Object)this.delegate.getTypeMap());
    }
    catch (SQLException e)
    {
      return (Map)postForAll(methodObject27469, onErrorForAll(methodObject27469, e));
    }
  }
  
  public ResultSet newArrayLocatorResultSet(ArrayDescriptor arg0, byte[] arg1, long arg2, int arg3, Map arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27505, this, new Object[] { arg0, arg1, Long.valueOf(arg2), Integer.valueOf(arg3), arg4 });
      return (ResultSet)postForAll(methodObject27505, this.proxyFactory.proxyForCache((Object)this.delegate.newArrayLocatorResultSet(arg0, arg1, arg2, arg3, arg4), this, this.proxyCache, methodObject27505));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject27505, onErrorForAll(methodObject27505, e));
    }
  }
  
  public Properties getDBAccessProperties()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27474, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27474, (Object)this.delegate.getDBAccessProperties());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27474, onErrorForAll(methodObject27474, e));
    }
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForCancel(methodObject27574, this, zeroLengthObjectArray);
      this.delegate.cancel();
      postForCancel(methodObject27574);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27574, e);
    }
  }
  
  public NUMBER createNUMBER(float arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27670, this, new Object[] { Float.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27670, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27670, onErrorForAll(methodObject27670, e));
    }
  }
  
  public String[] getEndToEndMetrics()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27589, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject27589, (Object)this.delegate.getEndToEndMetrics());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject27589, onErrorForAll(methodObject27589, e));
    }
  }
  
  public int getMaxCharSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27513, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27513, Integer.valueOf(this.delegate.getMaxCharSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27513, onErrorForAll(methodObject27513, e))).intValue();
    }
  }
  
  public long getTdoCState(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27496, this, new Object[] { arg0, arg1 });
      return ((Long)postForAll(methodObject27496, Long.valueOf(this.delegate.getTdoCState(arg0, arg1)))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject27496, onErrorForAll(methodObject27496, e))).longValue();
    }
  }
  
  public TimeZone getDefaultTimeZone()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27702, this, zeroLengthObjectArray);
      return (TimeZone)postForAll(methodObject27702, (Object)this.delegate.getDefaultTimeZone());
    }
    catch (SQLException e)
    {
      return (TimeZone)postForAll(methodObject27702, onErrorForAll(methodObject27702, e));
    }
  }
  
  public short getDriverCharSet()
  {
    super.preForAll(methodObject27511, this, zeroLengthObjectArray);
    return ((Short)postForAll(methodObject27511, Short.valueOf(this.delegate.getDriverCharSet()))).shortValue();
  }
  
  public short getNCharSet()
  {
    super.preForAll(methodObject27502, this, zeroLengthObjectArray);
    return ((Short)postForAll(methodObject27502, Short.valueOf(this.delegate.getNCharSet()))).shortValue();
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27725, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (CallableStatement)postForAll(methodObject27725, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27725));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27725, onErrorForAll(methodObject27725, e));
    }
  }
  
  public boolean getTimestamptzInGmt()
  {
    super.preForAll(methodObject27557, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27557, Boolean.valueOf(this.delegate.getTimestamptzInGmt()))).booleanValue();
  }
  
  public void archive(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27584, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.archive(arg0, arg1, arg2);
      postForAll(methodObject27584);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27584, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27677, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27677, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27677, onErrorForAll(methodObject27677, e));
    }
  }
  
  public void oracleReleaseSavepoint(OracleSavepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27572, this, new Object[] { arg0 });
      this.delegate.oracleReleaseSavepoint((arg0 instanceof _Proxy_) ? (OracleSavepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27572);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27572, e);
    }
  }
  
  public String getSQLType(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27595, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27595, (Object)this.delegate.getSQLType((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27595, onErrorForAll(methodObject27595, e));
    }
  }
  
  public boolean isConnectionSocketKeepAlive()
    throws SocketException, SQLException
  {
    try
    {
      super.preForAll(methodObject27561, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27561, Boolean.valueOf(this.delegate.isConnectionSocketKeepAlive()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27561, onErrorForAll(methodObject27561, e))).booleanValue();
    }
  }
  
  public INTERVALDS createINTERVALDS(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27663, this, new Object[] { arg0 });
      return (INTERVALDS)postForAll(methodObject27663, (Object)this.delegate.createINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject27663, onErrorForAll(methodObject27663, e));
    }
  }
  
  public Statement createStatement(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27721, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (Statement)postForAll(methodObject27721, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1), this, this.proxyCache, methodObject27721));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27721, onErrorForAll(methodObject27721, e));
    }
  }
  
  public boolean getRemarksReporting()
  {
    super.preForAll(methodObject27594, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27594, Boolean.valueOf(this.delegate.getRemarksReporting()))).booleanValue();
  }
  
  public int pingDatabase()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27581, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27581, Integer.valueOf(this.delegate.pingDatabase()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27581, onErrorForAll(methodObject27581, e))).intValue();
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27691, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27691, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27691, onErrorForAll(methodObject27691, e));
    }
  }
  
  public int getConnectionReleasePriority()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27642, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27642, Integer.valueOf(this.delegate.getConnectionReleasePriority()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27642, onErrorForAll(methodObject27642, e))).intValue();
    }
  }
  
  public DATE createDATE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27662, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27662, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27662, onErrorForAll(methodObject27662, e));
    }
  }
  
  public boolean getXAErrorFlag()
  {
    super.preForAll(methodObject27600, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27600, Boolean.valueOf(this.delegate.getXAErrorFlag()))).booleanValue();
  }
  
  public void startup(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27624, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.startup(arg0, arg1);
      postForAll(methodObject27624);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27624, e);
    }
  }
  
  public void setDefaultExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27604, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setDefaultExecuteBatch(arg0);
      postForAll(methodObject27604);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27604, e);
    }
  }
  
  public void registerTAFCallback(OracleOCIFailover arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27634, this, new Object[] { arg0, arg1 });
      this.delegate.registerTAFCallback(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27634);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27634, e);
    }
  }
  
  public String getDefaultSchemaNameForNamedTypes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27471, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27471, (Object)this.delegate.getDefaultSchemaNameForNamedTypes());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27471, onErrorForAll(methodObject27471, e));
    }
  }
  
  public int getTxnMode()
  {
    super.preForAll(methodObject27526, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27526, Integer.valueOf(this.delegate.getTxnMode()))).intValue();
  }
  
  public String getUserName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27579, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27579, (Object)this.delegate.getUserName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27579, onErrorForAll(methodObject27579, e));
    }
  }
  
  public BLOB createBlobWithUnpickledBytes(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27538, this, new Object[] { arg0 });
      return (BLOB)postForAll(methodObject27538, (Object)this.delegate.createBlobWithUnpickledBytes(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject27538, onErrorForAll(methodObject27538, e));
    }
  }
  
  public void setAutoClose(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27603, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoClose(arg0);
      postForAll(methodObject27603);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27603, e);
    }
  }
  
  public NClob createNClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27718, this, zeroLengthObjectArray);
      return (NClob)postForAll(methodObject27718, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createNClob(), this, this.proxyCache, methodObject27718));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject27718, onErrorForAll(methodObject27718, e));
    }
  }
  
  public DATE createDATE(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27660, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27660, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27660, onErrorForAll(methodObject27660, e));
    }
  }
  
  public boolean isProxySession()
  {
    super.preForAll(methodObject27636, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27636, Boolean.valueOf(this.delegate.isProxySession()))).booleanValue();
  }
  
  public NUMBER createNUMBER(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27665, this, new Object[] { Boolean.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27665, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27665, onErrorForAll(methodObject27665, e));
    }
  }
  
  public void setTransactionIsolation(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27749, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setTransactionIsolation(arg0);
      postForAll(methodObject27749);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27749, e);
    }
  }
  
  public boolean getAutoCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27737, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27737, Boolean.valueOf(this.delegate.getAutoCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27737, onErrorForAll(methodObject27737, e))).booleanValue();
    }
  }
  
  public void addXSEventListener(XSEventListener arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27551, this, new Object[] { arg0 });
      this.delegate.addXSEventListener(arg0);
      postForAll(methodObject27551);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27551, e);
    }
  }
  
  public short getJdbcCsId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27501, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27501, Short.valueOf(this.delegate.getJdbcCsId()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27501, onErrorForAll(methodObject27501, e))).shortValue();
    }
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27734, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27734, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27734, onErrorForAll(methodObject27734, e))).intValue();
    }
  }
  
  public void releaseSavepoint(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27713, this, new Object[] { arg0 });
      this.delegate.releaseSavepoint((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27713);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27713, e);
    }
  }
  
  public void closeInternal(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27531, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.closeInternal(arg0);
      postForAll(methodObject27531);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27531, e);
    }
  }
  
  public void setAbandonedTimeoutEnabled(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27529, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAbandonedTimeoutEnabled(arg0);
      postForAll(methodObject27529);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27529, e);
    }
  }
  
  public int CHARBytesToJavaChars(byte[] arg0, int arg1, char[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27508, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject27508, Integer.valueOf(this.delegate.CHARBytesToJavaChars(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27508, onErrorForAll(methodObject27508, e))).intValue();
    }
  }
  
  public void setApplicationContext(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27703, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setApplicationContext(arg0, arg1, arg2);
      postForAll(methodObject27703);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27703, e);
    }
  }
  
  public OracleConnection.BufferCacheStatistics getCharBufferCacheStatistics()
  {
    super.preForAll(methodObject27498, this, zeroLengthObjectArray);
    return (OracleConnection.BufferCacheStatistics)postForAll(methodObject27498, (Object)this.delegate.getCharBufferCacheStatistics());
  }
  
  public void rollback()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27709, this, zeroLengthObjectArray);
      this.delegate.rollback();
      postForAll(methodObject27709);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27709, e);
    }
  }
  
  public void putDescriptor(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27583, this, new Object[] { arg0, arg1 });
      this.delegate.putDescriptor(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27583);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27583, e);
    }
  }
  
  public ResultSetMetaData newStructMetaData(StructDescriptor arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27506, this, new Object[] { arg0 });
      return (ResultSetMetaData)postForAll(methodObject27506, this.proxyFactory.proxyForCache((Object)this.delegate.newStructMetaData(arg0), this, this.proxyCache, methodObject27506));
    }
    catch (SQLException e)
    {
      return (ResultSetMetaData)postForAll(methodObject27506, onErrorForAll(methodObject27506, e));
    }
  }
  
  public CLOB createClobWithUnpickledBytes(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27537, this, new Object[] { arg0 });
      return (CLOB)postForAll(methodObject27537, (Object)this.delegate.createClobWithUnpickledBytes(arg0));
    }
    catch (SQLException e)
    {
      return (CLOB)postForAll(methodObject27537, onErrorForAll(methodObject27537, e));
    }
  }
  
  public boolean isDataInLocatorEnabled()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27559, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27559, Boolean.valueOf(this.delegate.isDataInLocatorEnabled()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27559, onErrorForAll(methodObject27559, e))).booleanValue();
    }
  }
  
  public boolean getIncludeSynonyms()
  {
    super.preForAll(methodObject27591, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27591, Boolean.valueOf(this.delegate.getIncludeSynonyms()))).booleanValue();
  }
  
  public int getOCIEnvHeapAllocSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27528, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27528, Integer.valueOf(this.delegate.getOCIEnvHeapAllocSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27528, onErrorForAll(methodObject27528, e))).intValue();
    }
  }
  
  public int getDefaultExecuteBatch()
  {
    super.preForAll(methodObject27587, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27587, Integer.valueOf(this.delegate.getDefaultExecuteBatch()))).intValue();
  }
  
  public void setDefaultTimeZone(TimeZone arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27701, this, new Object[] { arg0 });
      this.delegate.setDefaultTimeZone(arg0);
      postForAll(methodObject27701);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27701, e);
    }
  }
  
  public BlobDBAccess createBlobDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27481, this, zeroLengthObjectArray);
      return (BlobDBAccess)postForAll(methodObject27481, (Object)this.delegate.createBlobDBAccess());
    }
    catch (SQLException e)
    {
      return (BlobDBAccess)postForAll(methodObject27481, onErrorForAll(methodObject27481, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27685, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27685, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27685, onErrorForAll(methodObject27685, e));
    }
  }
  
  public Properties getServerSessionInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27536, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27536, (Object)this.delegate.getServerSessionInfo());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27536, onErrorForAll(methodObject27536, e));
    }
  }
  
  public byte[] createLightweightSession(String arg0, KeywordValueLong[] arg1, int arg2, KeywordValueLong[][] arg3, int[] arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27545, this, new Object[] { arg0, arg1, Integer.valueOf(arg2), arg3, arg4 });
      return (byte[])postForAll(methodObject27545, (Object)this.delegate.createLightweightSession(arg0, arg1, arg2, arg3, arg4));
    }
    catch (SQLException e)
    {
      return (byte[])postForAll(methodObject27545, onErrorForAll(methodObject27545, e));
    }
  }
  
  public int getHeartbeatNoChangeCount()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27530, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27530, Integer.valueOf(this.delegate.getHeartbeatNoChangeCount()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27530, onErrorForAll(methodObject27530, e))).intValue();
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27707, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27707, Boolean.valueOf(this.delegate.isReadOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27707, onErrorForAll(methodObject27707, e))).booleanValue();
    }
  }
  
  public int getDefaultRowPrefetch()
  {
    super.preForAll(methodObject27588, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27588, Integer.valueOf(this.delegate.getDefaultRowPrefetch()))).intValue();
  }
  
  public Statement createStatement(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27722, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (Statement)postForAll(methodObject27722, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27722));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27722, onErrorForAll(methodObject27722, e));
    }
  }
  
  public void setImplicitCachingEnabled(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27614, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setImplicitCachingEnabled(arg0);
      postForAll(methodObject27614);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27614, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27679, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27679, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27679, onErrorForAll(methodObject27679, e));
    }
  }
  
  public Object getDescriptor(String arg0)
  {
    super.preForAll(methodObject27563, this, new Object[] { arg0 });
    return postForAll(methodObject27563, this.proxyFactory.proxyForCache(this.delegate.getDescriptor(arg0), this, this.proxyCache, methodObject27563));
  }
  
  public CallableStatement prepareCall(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27723, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27723, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0), this, this.proxyCache, methodObject27723));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27723, onErrorForAll(methodObject27723, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27684, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27684, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27684, onErrorForAll(methodObject27684, e));
    }
  }
  
  public OracleSavepoint oracleSetSavepoint(String arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27571, this, new Object[] { arg0 });
      return (OracleSavepoint)postForAll(methodObject27571, this.proxyFactory.proxyForCreate((Object)this.delegate.oracleSetSavepoint(arg0), this, this.proxyCache, methodObject27571));
    }
    catch (SQLException e)
    {
      return (OracleSavepoint)postForAll(methodObject27571, onErrorForAll(methodObject27571, e));
    }
  }
  
  public String getDataIntegrityAlgorithmName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27697, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27697, (Object)this.delegate.getDataIntegrityAlgorithmName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27697, onErrorForAll(methodObject27697, e));
    }
  }
  
  public int getC2SNlsRatio()
  {
    super.preForAll(methodObject27512, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27512, Integer.valueOf(this.delegate.getC2SNlsRatio()))).intValue();
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27751, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27751, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27751, onErrorForAll(methodObject27751, e))).booleanValue();
    }
  }
  
  public void setHoldability(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27748, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setHoldability(arg0);
      postForAll(methodObject27748);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27748, e);
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27731, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27731, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27731));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27731, onErrorForAll(methodObject27731, e));
    }
  }
  
  public void setTxnMode(int arg0)
  {
    super.preForAll(methodObject27525, this, new Object[] { Integer.valueOf(arg0) });
    this.delegate.setTxnMode(arg0);
    postForAll(methodObject27525);
  }
  
  public ResultSet newArrayDataResultSet(Datum[] arg0, long arg1, int arg2, Map arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27503, this, new Object[] { arg0, Long.valueOf(arg1), Integer.valueOf(arg2), arg3 });
      return (ResultSet)postForAll(methodObject27503, this.proxyFactory.proxyForCache((Object)this.delegate.newArrayDataResultSet(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27503));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject27503, onErrorForAll(methodObject27503, e));
    }
  }
  
  public short getEndToEndECIDSequenceNumber()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27590, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27590, Short.valueOf(this.delegate.getEndToEndECIDSequenceNumber()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27590, onErrorForAll(methodObject27590, e))).shortValue();
    }
  }
  
  public BfileDBAccess createBfileDBAccess()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27480, this, zeroLengthObjectArray);
      return (BfileDBAccess)postForAll(methodObject27480, (Object)this.delegate.createBfileDBAccess());
    }
    catch (SQLException e)
    {
      return (BfileDBAccess)postForAll(methodObject27480, onErrorForAll(methodObject27480, e));
    }
  }
  
  public int getStmtCacheSize()
  {
    super.preForAll(methodObject27596, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27596, Integer.valueOf(this.delegate.getStmtCacheSize()))).intValue();
  }
  
  public void setRemarksReporting(boolean arg0)
  {
    super.preForAll(methodObject27608, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setRemarksReporting(arg0);
    postForAll(methodObject27608);
  }
  
  public TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27695, this, new Object[] { arg0 });
      return (TypeDescriptor[])postForAll(methodObject27695, (Object)this.delegate.getTypeDescriptorsFromListInCurrentSchema(arg0));
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27695, onErrorForAll(methodObject27695, e));
    }
  }
  
  public void commit()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27708, this, zeroLengthObjectArray);
      this.delegate.commit();
      postForAll(methodObject27708);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27708, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27735, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject27735, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject27735, onErrorForAll(methodObject27735, e));
    }
  }
  
  public void removeDescriptor(String arg0)
  {
    super.preForAll(methodObject27492, this, new Object[] { arg0 });
    this.delegate.removeDescriptor(arg0);
    postForAll(methodObject27492);
  }
  
  public BLOB createBlob(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27463, this, new Object[] { arg0 });
      return (BLOB)postForAll(methodObject27463, (Object)this.delegate.createBlob(arg0));
    }
    catch (SQLException e)
    {
      return (BLOB)postForAll(methodObject27463, onErrorForAll(methodObject27463, e));
    }
  }
  
  public Map getJavaObjectTypeMap()
  {
    super.preForAll(methodObject27477, this, zeroLengthObjectArray);
    return (Map)postForAll(methodObject27477, (Object)this.delegate.getJavaObjectTypeMap());
  }
  
  public boolean getUse1900AsYearForTime()
  {
    super.preForAll(methodObject27558, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27558, Boolean.valueOf(this.delegate.getUse1900AsYearForTime()))).booleanValue();
  }
  
  public void setTypeMap(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27470, this, new Object[] { arg0 });
      this.delegate.setTypeMap(arg0);
      postForAll(methodObject27470);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27470, e);
    }
  }
  
  public void setRestrictGetTables(boolean arg0)
  {
    super.preForAll(methodObject27609, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setRestrictGetTables(arg0);
    postForAll(methodObject27609);
  }
  
  public Connection _getPC()
  {
    super.preForAll(methodObject27578, this, zeroLengthObjectArray);
    return (Connection)postForAll(methodObject27578, (Object)this.delegate._getPC());
  }
  
  public void clearAllApplicationContext(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27704, this, new Object[] { arg0 });
      this.delegate.clearAllApplicationContext(arg0);
      postForAll(methodObject27704);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27704, e);
    }
  }
  
  public void setCatalog(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27745, this, new Object[] { arg0 });
      this.delegate.setCatalog(arg0);
      postForAll(methodObject27745);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27745, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27683, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27683, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27683, onErrorForAll(methodObject27683, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27706, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClosure(methodObject27706);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27706, e);
    }
  }
  
  public DATE createDATE(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27657, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27657, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27657, onErrorForAll(methodObject27657, e));
    }
  }
  
  public String getDatabaseTimeZone()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27556, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27556, (Object)this.delegate.getDatabaseTimeZone());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27556, onErrorForAll(methodObject27556, e));
    }
  }
  
  public void rollback(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27710, this, new Object[] { arg0 });
      this.delegate.rollback((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27710);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27710, e);
    }
  }
  
  public Clob createClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27717, this, zeroLengthObjectArray);
      return (Clob)postForAll(methodObject27717, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createClob(), this, this.proxyCache, methodObject27717));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject27717, onErrorForAll(methodObject27717, e));
    }
  }
  
  public void abort()
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27573, this, zeroLengthObjectArray);
      this.delegate.abort();
      postForClosure(methodObject27573);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27573, e);
    }
  }
  
  public Object getConnectionCacheCallbackPrivObj()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27534, this, zeroLengthObjectArray);
      return postForAll(methodObject27534, this.proxyFactory.proxyForCache(this.delegate.getConnectionCacheCallbackPrivObj(), this, this.proxyCache, methodObject27534));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27534, onErrorForAll(methodObject27534, e));
    }
  }
  
  public String getAuthenticationAdaptorName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27699, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27699, (Object)this.delegate.getAuthenticationAdaptorName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27699, onErrorForAll(methodObject27699, e));
    }
  }
  
  public boolean getBigEndian()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27489, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27489, Boolean.valueOf(this.delegate.getBigEndian()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27489, onErrorForAll(methodObject27489, e))).booleanValue();
    }
  }
  
  public BINARY_FLOAT createBINARY_FLOAT(float arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27655, this, new Object[] { Float.valueOf(arg0) });
      return (BINARY_FLOAT)postForAll(methodObject27655, (Object)this.delegate.createBINARY_FLOAT(arg0));
    }
    catch (SQLException e)
    {
      return (BINARY_FLOAT)postForAll(methodObject27655, onErrorForAll(methodObject27655, e));
    }
  }
  
  public void setStmtCacheSize(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27611, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setStmtCacheSize(arg0, arg1);
      postForAll(methodObject27611);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27611, e);
    }
  }
  
  public void setClientInfo(Properties arg0)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27747, this, new Object[] { arg0 });
    this.delegate.setClientInfo(arg0);
    postForAll(methodObject27747);
  }
  
  public Properties getConnectionAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27638, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27638, (Object)this.delegate.getConnectionAttributes());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27638, onErrorForAll(methodObject27638, e));
    }
  }
  
  public boolean isCharSetMultibyte(short arg0)
  {
    super.preForAll(methodObject27516, this, new Object[] { Short.valueOf(arg0) });
    return ((Boolean)postForAll(methodObject27516, Boolean.valueOf(this.delegate.isCharSetMultibyte(arg0)))).booleanValue();
  }
  
  public void setStmtCacheSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27610, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setStmtCacheSize(arg0);
      postForAll(methodObject27610);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27610, e);
    }
  }
  
  public void putDescriptor(byte[] arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27490, this, new Object[] { arg0, arg1 });
      this.delegate.putDescriptor(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27490);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27490, e);
    }
  }
  
  public String getCatalog()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27738, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27738, (Object)this.delegate.getCatalog());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27738, onErrorForAll(methodObject27738, e));
    }
  }
  
  public EnumSet getTransactionState()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27466, this, zeroLengthObjectArray);
      return (EnumSet)postForAll(methodObject27466, (Object)this.delegate.getTransactionState());
    }
    catch (SQLException e)
    {
      return (EnumSet)postForAll(methodObject27466, onErrorForAll(methodObject27466, e));
    }
  }
  
  public String getDatabaseProductVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27468, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27468, (Object)this.delegate.getDatabaseProductVersion());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27468, onErrorForAll(methodObject27468, e));
    }
  }
  
  public void removeXSEventListener(XSEventListener arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27553, this, new Object[] { arg0 });
      this.delegate.removeXSEventListener(arg0);
      postForAll(methodObject27553);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27553, e);
    }
  }
  
  public void applyConnectionAttributes(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27637, this, new Object[] { arg0 });
      this.delegate.applyConnectionAttributes(arg0);
      postForAll(methodObject27637);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27637, e);
    }
  }
  
  public NUMBER createNUMBER(double arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27671, this, new Object[] { Double.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27671, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27671, onErrorForAll(methodObject27671, e));
    }
  }
  
  public void setDefaultRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27605, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setDefaultRowPrefetch(arg0);
      postForAll(methodObject27605);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27605, e);
    }
  }
  
  public boolean getExplicitCachingEnabled()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27617, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27617, Boolean.valueOf(this.delegate.getExplicitCachingEnabled()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27617, onErrorForAll(methodObject27617, e))).booleanValue();
    }
  }
  
  public void commit(EnumSet arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27568, this, new Object[] { arg0 });
      this.delegate.commit(arg0);
      postForAll(methodObject27568);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27568, e);
    }
  }
  
  public String getCurrentSchema()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27598, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27598, (Object)this.delegate.getCurrentSchema());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27598, onErrorForAll(methodObject27598, e));
    }
  }
  
  public void getPropertyForPooledConnection(OraclePooledConnection arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27522, this, new Object[] { arg0 });
      this.delegate.getPropertyForPooledConnection(arg0);
      postForAll(methodObject27522);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27522, e);
    }
  }
  
  public void close(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27566, this, new Object[] { arg0 });
      this.delegate.close(arg0);
      postForClosure(methodObject27566);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27566, e);
    }
  }
  
  public OracleSavepoint oracleSetSavepoint()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27570, this, zeroLengthObjectArray);
      return (OracleSavepoint)postForAll(methodObject27570, this.proxyFactory.proxyForCreate((Object)this.delegate.oracleSetSavepoint(), this, this.proxyCache, methodObject27570));
    }
    catch (SQLException e)
    {
      return (OracleSavepoint)postForAll(methodObject27570, onErrorForAll(methodObject27570, e));
    }
  }
  
  public void unregisterDatabaseChangeNotification(int arg0, String arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27651, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.unregisterDatabaseChangeNotification(arg0, arg1, arg2);
      postForAll(methodObject27651);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27651, e);
    }
  }
  
  public short getDbCsId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27500, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27500, Short.valueOf(this.delegate.getDbCsId()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27500, onErrorForAll(methodObject27500, e))).shortValue();
    }
  }
  
  public int NCHARBytesToJavaChars(byte[] arg0, int arg1, char[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27509, this, new Object[] { arg0, Integer.valueOf(arg1), arg2 });
      return ((Integer)postForAll(methodObject27509, Integer.valueOf(this.delegate.NCHARBytesToJavaChars(arg0, arg1, arg2)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27509, onErrorForAll(methodObject27509, e))).intValue();
    }
  }
  
  public boolean isStatementCacheInitialized()
  {
    super.preForAll(methodObject27521, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27521, Boolean.valueOf(this.delegate.isStatementCacheInitialized()))).booleanValue();
  }
  
  public boolean getMapDateToTimestamp()
  {
    super.preForAll(methodObject27544, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27544, Boolean.valueOf(this.delegate.getMapDateToTimestamp()))).booleanValue();
  }
  
  public Blob createBlob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27716, this, zeroLengthObjectArray);
      return (Blob)postForAll(methodObject27716, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createBlob(), this, this.proxyCache, methodObject27716));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject27716, onErrorForAll(methodObject27716, e));
    }
  }
  
  public int getMaxCharbyteSize()
  {
    super.preForAll(methodObject27514, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27514, Integer.valueOf(this.delegate.getMaxCharbyteSize()))).intValue();
  }
  
  public void doXSNamespaceOp(OracleConnection.XSOperationCode arg0, byte[] arg1, XSNamespace[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27549, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.doXSNamespaceOp(arg0, arg1, arg2);
      postForAll(methodObject27549);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27549, e);
    }
  }
  
  public short getVersionNumber()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27476, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27476, Short.valueOf(this.delegate.getVersionNumber()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27476, onErrorForAll(methodObject27476, e))).shortValue();
    }
  }
  
  public OracleConnection.BufferCacheStatistics getByteBufferCacheStatistics()
  {
    super.preForAll(methodObject27497, this, zeroLengthObjectArray);
    return (OracleConnection.BufferCacheStatistics)postForAll(methodObject27497, (Object)this.delegate.getByteBufferCacheStatistics());
  }
  
  public Properties getOCIHandles()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27475, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27475, (Object)this.delegate.getOCIHandles());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27475, onErrorForAll(methodObject27475, e));
    }
  }
  
  public boolean IsNCharFixedWith()
  {
    super.preForAll(methodObject27510, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27510, Boolean.valueOf(this.delegate.IsNCharFixedWith()))).booleanValue();
  }
  
  public String getProtocolType()
  {
    super.preForAll(methodObject27523, this, zeroLengthObjectArray);
    return (String)postForAll(methodObject27523, (Object)this.delegate.getProtocolType());
  }
  
  public void setAutoCommit(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27744, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoCommit(arg0);
      postForAll(methodObject27744);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27744, e);
    }
  }
  
  public void setClientInfo(String arg0, String arg1)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27746, this, new Object[] { arg0, arg1 });
    this.delegate.setClientInfo(arg0, arg1);
    postForAll(methodObject27746);
  }
  
  public BFILE createBfile(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27539, this, new Object[] { arg0 });
      return (BFILE)postForAll(methodObject27539, (Object)this.delegate.createBfile(arg0));
    }
    catch (SQLException e)
    {
      return (BFILE)postForAll(methodObject27539, onErrorForAll(methodObject27539, e));
    }
  }
  
  public boolean isValid(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27742, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27742, Boolean.valueOf(this.delegate.isValid(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27742, onErrorForAll(methodObject27742, e))).booleanValue();
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27678, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27678, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27678, onErrorForAll(methodObject27678, e));
    }
  }
  
  public String getSessionTimeZone()
  {
    super.preForAll(methodObject27631, this, zeroLengthObjectArray);
    return (String)postForAll(methodObject27631, (Object)this.delegate.getSessionTimeZone());
  }
  
  public boolean isUsable()
  {
    super.preForAll(methodObject27700, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27700, Boolean.valueOf(this.delegate.isUsable()))).booleanValue();
  }
  
  public NUMBER createNUMBER(byte arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27666, this, new Object[] { Byte.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27666, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27666, onErrorForAll(methodObject27666, e));
    }
  }
  
  public void setXAErrorFlag(boolean arg0)
  {
    super.preForAll(methodObject27623, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setXAErrorFlag(arg0);
    postForAll(methodObject27623);
  }
  
  public void addXSEventListener(XSEventListener arg0, Executor arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27552, this, new Object[] { arg0, arg1 });
      this.delegate.addXSEventListener(arg0, arg1);
      postForAll(methodObject27552);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27552, e);
    }
  }
  
  public void unregisterAQNotification(AQNotificationRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27645, this, new Object[] { arg0 });
      this.delegate.unregisterAQNotification(arg0);
      postForAll(methodObject27645);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27645, e);
    }
  }
  
  public long getCurrentSCN()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27467, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject27467, Long.valueOf(this.delegate.getCurrentSCN()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject27467, onErrorForAll(methodObject27467, e))).longValue();
    }
  }
  
  public int getConnectionCacheCallbackFlag()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27535, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27535, Integer.valueOf(this.delegate.getConnectionCacheCallbackFlag()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27535, onErrorForAll(methodObject27535, e))).intValue();
    }
  }
  
  public void setSessionTimeZone(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27630, this, new Object[] { arg0 });
      this.delegate.setSessionTimeZone(arg0);
      postForAll(methodObject27630);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27630, e);
    }
  }
  
  public OracleStatement refCursorCursorToStatement(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27541, this, new Object[] { Integer.valueOf(arg0) });
      return (OracleStatement)postForAll(methodObject27541, this.proxyFactory.proxyForCache((Object)this.delegate.refCursorCursorToStatement(arg0), this, this.proxyCache, methodObject27541));
    }
    catch (SQLException e)
    {
      return (OracleStatement)postForAll(methodObject27541, onErrorForAll(methodObject27541, e));
    }
  }
  
  public void enqueue(String arg0, AQEnqueueOptions arg1, AQMessage arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27565, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.enqueue(arg0, arg1, arg2);
      postForAll(methodObject27565);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27565, e);
    }
  }
  
  public oracle.jdbc.internal.OracleConnection _getDelegate_()
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
      methodObject27616 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setExplicitCachingEnabled", new Class[] { Boolean.TYPE });
      methodObject27647 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("dequeue", new Class[] { String.class, AQDequeueOptions.class, String.class });
      methodObject27533 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getConnectionCacheCallbackObj", new Class[0]);
      methodObject27649 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDatabaseChangeRegistration", new Class[] { Integer.TYPE });
      methodObject27562 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("shutdown", new Class[] { OracleConnection.DatabaseShutdownMode.class });
      methodObject27729 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE });
      methodObject27567 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("close", new Class[] { Integer.TYPE });
      methodObject27661 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Timestamp.class, Calendar.class });
      methodObject27487 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setFDO", new Class[] { byte[].class });
      methodObject27465 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createClob", new Class[] { byte[].class, Short.TYPE });
      methodObject27668 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Integer.TYPE });
      methodObject27675 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Date.class });
      methodObject27575 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createARRAY", new Class[] { String.class, Object.class });
      methodObject27711 = Connection.class.getDeclaredMethod("setSavepoint", new Class[0]);
      methodObject27483 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setDefaultFixedString", new Class[] { Boolean.TYPE });
      methodObject27586 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAutoClose", new Class[0]);
      methodObject27705 = Connection.class.getDeclaredMethod("setReadOnly", new Class[] { Boolean.TYPE });
      methodObject27689 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Date.class, Calendar.class });
      methodObject27532 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("cleanupAndClose", new Class[] { Boolean.TYPE });
      methodObject27659 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Date.class, Calendar.class });
      methodObject27577 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unwrap", new Class[0]);
      methodObject27620 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStatementWithKey", new Class[] { String.class });
      methodObject27682 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Time.class });
      methodObject27741 = Connection.class.getDeclaredMethod("getTransactionIsolation", new Class[0]);
      methodObject27653 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Long.TYPE, String.class });
      methodObject27658 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Timestamp.class });
      methodObject27633 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isLogicalConnection", new Class[0]);
      methodObject27488 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getFDO", new Class[] { Boolean.TYPE });
      methodObject27507 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getForm", new Class[] { OracleTypeADT.class, OracleTypeCLOB.class, Integer.TYPE });
      methodObject27607 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setIncludeSynonyms", new Class[] { Boolean.TYPE });
      methodObject27719 = Connection.class.getDeclaredMethod("createSQLXML", new Class[0]);
      methodObject27519 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setStartTime", new Class[] { Long.TYPE });
      methodObject27650 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { DatabaseChangeRegistration.class });
      methodObject27698 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEncryptionAlgorithmName", new Class[0]);
      methodObject27694 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAllTypeDescriptorsInCurrentSchema", new Class[0]);
      methodObject27554 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTimezoneVersionNumber", new Class[0]);
      methodObject27547 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("executeLightweightSessionPiggyback", new Class[] { Integer.TYPE, byte[].class, KeywordValueLong[].class, Integer.TYPE });
      methodObject27693 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { DATE.class, Calendar.class });
      methodObject27612 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStatementCacheSize", new Class[] { Integer.TYPE });
      methodObject27632 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSessionTimeZoneOffset", new Class[0]);
      methodObject27740 = Connection.class.getDeclaredMethod("getClientInfo", new Class[0]);
      methodObject27473 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getStructAttrNCsId", new Class[0]);
      methodObject27493 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("removeAllDescriptor", new Class[0]);
      methodObject27599 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUsingXAFlag", new Class[0]);
      methodObject27643 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setPlsqlWarnings", new Class[] { String.class });
      methodObject27580 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("physicalConnectionWithin", new Class[0]);
      methodObject27750 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject27550 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setUsable", new Class[] { Boolean.TYPE });
      methodObject27485 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getWrapper", new Class[0]);
      methodObject27472 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getClassForType", new Class[] { String.class, Map.class });
      methodObject27733 = Connection.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject27515 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getMaxNCharbyteSize", new Class[0]);
      methodObject27626 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("prepareStatementWithKey", new Class[] { String.class });
      methodObject27727 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27495 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("descriptorCacheKeys", new Class[0]);
      methodObject27648 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerDatabaseChangeNotification", new Class[] { Properties.class });
      methodObject27724 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27652 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Integer.TYPE });
      methodObject27720 = Connection.class.getDeclaredMethod("createStatement", new Class[0]);
      methodObject27478 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setJavaObjectTypeMap", new Class[] { Map.class });
      methodObject27732 = Connection.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject27543 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isV8Compatible", new Class[0]);
      methodObject27736 = Connection.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject27524 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getLogicalConnection", new Class[] { OraclePooledConnection.class, Boolean.TYPE });
      methodObject27461 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDescriptor", new Class[] { byte[].class });
      methodObject27690 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Time.class, Calendar.class });
      methodObject27582 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("pingDatabase", new Class[] { Integer.TYPE });
      methodObject27606 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setEndToEndMetrics", new Class[] { String[].class, Short.TYPE });
      methodObject27462 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getURL", new Class[0]);
      methodObject27667 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Short.TYPE });
      methodObject27555 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTIMEZONETAB", new Class[0]);
      methodObject27627 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("prepareCallWithKey", new Class[] { String.class });
      methodObject27527 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getHeapAllocSize", new Class[0]);
      methodObject27644 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerAQNotification", new Class[] { String[].class, Properties[].class, Properties.class });
      methodObject27664 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createINTERVALYM", new Class[] { String.class });
      methodObject27518 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("javaCharsToNCHARBytes", new Class[] { char[].class, Integer.TYPE, byte[].class });
      methodObject27654 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createBINARY_DOUBLE", new Class[] { Double.TYPE });
      methodObject27673 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { BigInteger.class });
      methodObject27646 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("dequeue", new Class[] { String.class, AQDequeueOptions.class, byte[].class });
      methodObject27597 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStructAttrCsId", new Class[0]);
      methodObject27641 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setConnectionReleasePriority", new Class[] { Integer.TYPE });
      methodObject27676 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { DATE.class });
      methodObject27592 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getRestrictGetTables", new Class[0]);
      methodObject27639 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUnMatchedConnectionAttributes", new Class[0]);
      methodObject27622 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setUsingXAFlag", new Class[] { Boolean.TYPE });
      methodObject27656 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Date.class });
      methodObject27712 = Connection.class.getDeclaredMethod("setSavepoint", new Class[] { String.class });
      methodObject27726 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class });
      methodObject27499 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("toDatum", new Class[] { CustomDatum.class });
      methodObject27615 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getImplicitCachingEnabled", new Class[0]);
      methodObject27619 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("purgeExplicitCache", new Class[0]);
      methodObject27681 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Date.class, Calendar.class });
      methodObject27674 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { String.class, Integer.TYPE });
      methodObject27618 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("purgeImplicitCache", new Class[0]);
      methodObject27540 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isDescriptorSharable", new Class[] { oracle.jdbc.internal.OracleConnection.class });
      methodObject27482 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createClobDBAccess", new Class[0]);
      methodObject27692 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { String.class, Calendar.class });
      methodObject27486 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("classForNameAndSchema", new Class[] { String.class, String.class });
      methodObject27669 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Long.TYPE });
      methodObject27628 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setCreateStatementAsRefCursor", new Class[] { Boolean.TYPE });
      methodObject27564 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getProperties", new Class[0]);
      methodObject27484 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDefaultFixedString", new Class[0]);
      methodObject27504 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("newArrayDataResultSet", new Class[] { ARRAY.class, Long.TYPE, Integer.TYPE, Map.class });
      methodObject27696 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getTypeDescriptorsFromList", new Class[] { String[][].class });
      methodObject27635 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setWrapper", new Class[] { oracle.jdbc.OracleConnection.class });
      methodObject27491 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getPhysicalConnection", new Class[0]);
      methodObject27714 = Connection.class.getDeclaredMethod("createArrayOf", new Class[] { String.class, Object[].class });
      methodObject27602 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerSQLType", new Class[] { String.class, String.class });
      methodObject27593 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getJavaObject", new Class[] { String.class });
      methodObject27494 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("numberOfDescriptorCacheEntries", new Class[0]);
      methodObject27629 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCreateStatementAsRefCursor", new Class[0]);
      methodObject27621 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCallWithKey", new Class[] { String.class });
      methodObject27728 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27517 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("javaCharsToCHARBytes", new Class[] { char[].class, Integer.TYPE, byte[].class });
      methodObject27601 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerSQLType", new Class[] { String.class, Class.class });
      methodObject27548 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("doXSNamespaceOp", new Class[] { OracleConnection.XSOperationCode.class, byte[].class, XSNamespace[].class, XSNamespace[][].class });
      methodObject27680 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Date.class });
      methodObject27520 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getStartTime", new Class[0]);
      methodObject27479 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getInstanceProperty", new Class[] { OracleConnection.InstanceProperty.class });
      methodObject27560 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isLobStreamPosStandardCompliant", new Class[0]);
      methodObject27546 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("executeLightweightSessionRoundtrip", new Class[] { Integer.TYPE, byte[].class, KeywordValueLong[].class, Integer.TYPE, KeywordValueLong[][].class, int[].class });
      methodObject27542 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getXAResource", new Class[0]);
      methodObject27569 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleRollback", new Class[] { OracleSavepoint.class });
      methodObject27743 = Connection.class.getDeclaredMethod("nativeSQL", new Class[] { String.class });
      methodObject27585 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("openProxySession", new Class[] { Integer.TYPE, Properties.class });
      methodObject27686 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { String.class });
      methodObject27672 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { BigDecimal.class });
      methodObject27730 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, int[].class });
      methodObject27687 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { String.class, Calendar.class });
      methodObject27715 = Connection.class.getDeclaredMethod("createStruct", new Class[] { String.class, Object[].class });
      methodObject27625 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("startup", new Class[] { OracleConnection.DatabaseStartupMode.class });
      methodObject27464 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createClob", new Class[] { byte[].class });
      methodObject27576 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createOracleArray", new Class[] { String.class, Object.class });
      methodObject27688 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { DATE.class });
      methodObject27640 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerConnectionCacheCallback", new Class[] { OracleConnectionCacheCallback.class, Object.class, Integer.TYPE });
      methodObject27613 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStatementCacheSize", new Class[0]);
      methodObject27739 = Connection.class.getDeclaredMethod("getClientInfo", new Class[] { String.class });
      methodObject27469 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTypeMap", new Class[0]);
      methodObject27505 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("newArrayLocatorResultSet", new Class[] { ArrayDescriptor.class, byte[].class, Long.TYPE, Integer.TYPE, Map.class });
      methodObject27474 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDBAccessProperties", new Class[0]);
      methodObject27574 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject27670 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Float.TYPE });
      methodObject27589 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEndToEndMetrics", new Class[0]);
      methodObject27513 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getMaxCharSize", new Class[0]);
      methodObject27496 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTdoCState", new Class[] { String.class, String.class });
      methodObject27702 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultTimeZone", new Class[0]);
      methodObject27511 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDriverCharSet", new Class[0]);
      methodObject27502 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getNCharSet", new Class[0]);
      methodObject27725 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27557 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTimestamptzInGmt", new Class[0]);
      methodObject27584 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("archive", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject27677 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Time.class });
      methodObject27572 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleReleaseSavepoint", new Class[] { OracleSavepoint.class });
      methodObject27595 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSQLType", new Class[] { Object.class });
      methodObject27561 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isConnectionSocketKeepAlive", new Class[0]);
      methodObject27663 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createINTERVALDS", new Class[] { String.class });
      methodObject27721 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject27594 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getRemarksReporting", new Class[0]);
      methodObject27581 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("pingDatabase", new Class[0]);
      methodObject27691 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Timestamp.class, Calendar.class });
      methodObject27642 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getConnectionReleasePriority", new Class[0]);
      methodObject27662 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { String.class });
      methodObject27600 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getXAErrorFlag", new Class[0]);
      methodObject27624 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("startup", new Class[] { String.class, Integer.TYPE });
      methodObject27604 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultExecuteBatch", new Class[] { Integer.TYPE });
      methodObject27634 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerTAFCallback", new Class[] { OracleOCIFailover.class, Object.class });
      methodObject27471 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDefaultSchemaNameForNamedTypes", new Class[0]);
      methodObject27526 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTxnMode", new Class[0]);
      methodObject27579 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUserName", new Class[0]);
      methodObject27538 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createBlobWithUnpickledBytes", new Class[] { byte[].class });
      methodObject27603 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setAutoClose", new Class[] { Boolean.TYPE });
      methodObject27718 = Connection.class.getDeclaredMethod("createNClob", new Class[0]);
      methodObject27660 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Time.class, Calendar.class });
      methodObject27636 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isProxySession", new Class[0]);
      methodObject27665 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Boolean.TYPE });
      methodObject27749 = Connection.class.getDeclaredMethod("setTransactionIsolation", new Class[] { Integer.TYPE });
      methodObject27737 = Connection.class.getDeclaredMethod("getAutoCommit", new Class[0]);
      methodObject27551 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("addXSEventListener", new Class[] { XSEventListener.class });
      methodObject27501 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getJdbcCsId", new Class[0]);
      methodObject27734 = Connection.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject27713 = Connection.class.getDeclaredMethod("releaseSavepoint", new Class[] { Savepoint.class });
      methodObject27531 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("closeInternal", new Class[] { Boolean.TYPE });
      methodObject27529 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setAbandonedTimeoutEnabled", new Class[] { Boolean.TYPE });
      methodObject27508 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("CHARBytesToJavaChars", new Class[] { byte[].class, Integer.TYPE, char[].class });
      methodObject27703 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setApplicationContext", new Class[] { String.class, String.class, String.class });
      methodObject27498 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getCharBufferCacheStatistics", new Class[0]);
      methodObject27709 = Connection.class.getDeclaredMethod("rollback", new Class[0]);
      methodObject27583 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("putDescriptor", new Class[] { String.class, Object.class });
      methodObject27506 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("newStructMetaData", new Class[] { StructDescriptor.class });
      methodObject27537 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createClobWithUnpickledBytes", new Class[] { byte[].class });
      methodObject27559 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isDataInLocatorEnabled", new Class[0]);
      methodObject27591 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getIncludeSynonyms", new Class[0]);
      methodObject27528 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getOCIEnvHeapAllocSize", new Class[0]);
      methodObject27587 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultExecuteBatch", new Class[0]);
      methodObject27701 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultTimeZone", new Class[] { TimeZone.class });
      methodObject27481 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createBlobDBAccess", new Class[0]);
      methodObject27685 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Timestamp.class, Calendar.class });
      methodObject27536 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getServerSessionInfo", new Class[0]);
      methodObject27545 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createLightweightSession", new Class[] { String.class, KeywordValueLong[].class, Integer.TYPE, KeywordValueLong[][].class, int[].class });
      methodObject27530 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getHeartbeatNoChangeCount", new Class[0]);
      methodObject27707 = Connection.class.getDeclaredMethod("isReadOnly", new Class[0]);
      methodObject27588 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultRowPrefetch", new Class[0]);
      methodObject27722 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27614 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setImplicitCachingEnabled", new Class[] { Boolean.TYPE });
      methodObject27679 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { String.class });
      methodObject27563 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDescriptor", new Class[] { String.class });
      methodObject27723 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class });
      methodObject27684 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Timestamp.class });
      methodObject27571 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleSetSavepoint", new Class[] { String.class });
      methodObject27697 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDataIntegrityAlgorithmName", new Class[0]);
      methodObject27512 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getC2SNlsRatio", new Class[0]);
      methodObject27751 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject27748 = Connection.class.getDeclaredMethod("setHoldability", new Class[] { Integer.TYPE });
      methodObject27731 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, String[].class });
      methodObject27525 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setTxnMode", new Class[] { Integer.TYPE });
      methodObject27503 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("newArrayDataResultSet", new Class[] { Datum[].class, Long.TYPE, Integer.TYPE, Map.class });
      methodObject27590 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEndToEndECIDSequenceNumber", new Class[0]);
      methodObject27480 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createBfileDBAccess", new Class[0]);
      methodObject27596 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStmtCacheSize", new Class[0]);
      methodObject27608 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setRemarksReporting", new Class[] { Boolean.TYPE });
      methodObject27695 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getTypeDescriptorsFromListInCurrentSchema", new Class[] { String[].class });
      methodObject27708 = Connection.class.getDeclaredMethod("commit", new Class[0]);
      methodObject27735 = Connection.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject27492 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("removeDescriptor", new Class[] { String.class });
      methodObject27463 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createBlob", new Class[] { byte[].class });
      methodObject27477 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getJavaObjectTypeMap", new Class[0]);
      methodObject27558 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getUse1900AsYearForTime", new Class[0]);
      methodObject27470 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("setTypeMap", new Class[] { Map.class });
      methodObject27609 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setRestrictGetTables", new Class[] { Boolean.TYPE });
      methodObject27578 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("_getPC", new Class[0]);
      methodObject27704 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("clearAllApplicationContext", new Class[] { String.class });
      methodObject27745 = Connection.class.getDeclaredMethod("setCatalog", new Class[] { String.class });
      methodObject27683 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Time.class, Calendar.class });
      methodObject27706 = Connection.class.getDeclaredMethod("close", new Class[0]);
      methodObject27657 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Time.class });
      methodObject27556 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDatabaseTimeZone", new Class[0]);
      methodObject27710 = Connection.class.getDeclaredMethod("rollback", new Class[] { Savepoint.class });
      methodObject27717 = Connection.class.getDeclaredMethod("createClob", new Class[0]);
      methodObject27573 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("abort", new Class[0]);
      methodObject27534 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getConnectionCacheCallbackPrivObj", new Class[0]);
      methodObject27699 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAuthenticationAdaptorName", new Class[0]);
      methodObject27489 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getBigEndian", new Class[0]);
      methodObject27655 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createBINARY_FLOAT", new Class[] { Float.TYPE });
      methodObject27611 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStmtCacheSize", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject27747 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { Properties.class });
      methodObject27638 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getConnectionAttributes", new Class[0]);
      methodObject27516 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isCharSetMultibyte", new Class[] { Short.TYPE });
      methodObject27610 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStmtCacheSize", new Class[] { Integer.TYPE });
      methodObject27490 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("putDescriptor", new Class[] { byte[].class, Object.class });
      methodObject27738 = Connection.class.getDeclaredMethod("getCatalog", new Class[0]);
      methodObject27466 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getTransactionState", new Class[0]);
      methodObject27468 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDatabaseProductVersion", new Class[0]);
      methodObject27553 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("removeXSEventListener", new Class[] { XSEventListener.class });
      methodObject27637 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("applyConnectionAttributes", new Class[] { Properties.class });
      methodObject27671 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Double.TYPE });
      methodObject27605 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultRowPrefetch", new Class[] { Integer.TYPE });
      methodObject27617 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getExplicitCachingEnabled", new Class[0]);
      methodObject27568 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("commit", new Class[] { EnumSet.class });
      methodObject27598 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCurrentSchema", new Class[0]);
      methodObject27522 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getPropertyForPooledConnection", new Class[] { OraclePooledConnection.class });
      methodObject27566 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("close", new Class[] { Properties.class });
      methodObject27570 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleSetSavepoint", new Class[0]);
      methodObject27651 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Integer.TYPE, String.class, Integer.TYPE });
      methodObject27500 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getDbCsId", new Class[0]);
      methodObject27509 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("NCHARBytesToJavaChars", new Class[] { byte[].class, Integer.TYPE, char[].class });
      methodObject27521 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("isStatementCacheInitialized", new Class[0]);
      methodObject27544 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getMapDateToTimestamp", new Class[0]);
      methodObject27716 = Connection.class.getDeclaredMethod("createBlob", new Class[0]);
      methodObject27514 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getMaxCharbyteSize", new Class[0]);
      methodObject27549 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("doXSNamespaceOp", new Class[] { OracleConnection.XSOperationCode.class, byte[].class, XSNamespace[].class });
      methodObject27476 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getVersionNumber", new Class[0]);
      methodObject27497 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getByteBufferCacheStatistics", new Class[0]);
      methodObject27475 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getOCIHandles", new Class[0]);
      methodObject27510 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("IsNCharFixedWith", new Class[0]);
      methodObject27523 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getProtocolType", new Class[0]);
      methodObject27744 = Connection.class.getDeclaredMethod("setAutoCommit", new Class[] { Boolean.TYPE });
      methodObject27746 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { String.class, String.class });
      methodObject27539 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("createBfile", new Class[] { byte[].class });
      methodObject27742 = Connection.class.getDeclaredMethod("isValid", new Class[] { Integer.TYPE });
      methodObject27678 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Timestamp.class });
      methodObject27631 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSessionTimeZone", new Class[0]);
      methodObject27700 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isUsable", new Class[0]);
      methodObject27666 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Byte.TYPE });
      methodObject27623 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setXAErrorFlag", new Class[] { Boolean.TYPE });
      methodObject27552 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("addXSEventListener", new Class[] { XSEventListener.class, Executor.class });
      methodObject27645 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterAQNotification", new Class[] { AQNotificationRegistration.class });
      methodObject27467 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getCurrentSCN", new Class[0]);
      methodObject27535 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("getConnectionCacheCallbackFlag", new Class[0]);
      methodObject27630 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setSessionTimeZone", new Class[] { String.class });
      methodObject27541 = oracle.jdbc.internal.OracleConnection.class.getDeclaredMethod("refCursorCursorToStatement", new Class[] { Integer.TYPE });
      methodObject27565 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("enqueue", new Class[] { String.class, AQEnqueueOptions.class, AQMessage.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1internal$1OracleConnection$$$Proxy(oracle.jdbc.internal.OracleConnection paramOracleConnection, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleConnection;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1internal$1OracleConnection$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */