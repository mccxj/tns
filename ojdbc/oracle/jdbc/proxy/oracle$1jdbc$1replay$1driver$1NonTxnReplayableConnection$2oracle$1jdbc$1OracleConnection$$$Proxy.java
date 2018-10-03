package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.NClob;
import java.sql.PreparedStatement;
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
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import oracle.jdbc.OracleConnection.DatabaseShutdownMode;
import oracle.jdbc.OracleConnection.DatabaseStartupMode;
import oracle.jdbc.OracleOCIFailover;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.aq.AQDequeueOptions;
import oracle.jdbc.aq.AQEnqueueOptions;
import oracle.jdbc.aq.AQMessage;
import oracle.jdbc.aq.AQNotificationRegistration;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.pool.OracleConnectionCacheCallback;
import oracle.jdbc.replay.driver.NonTxnReplayableConnection;
import oracle.sql.ARRAY;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.DATE;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
import oracle.sql.NUMBER;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
import oracle.sql.TypeDescriptor;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1OracleConnection$$$Proxy
  extends NonTxnReplayableConnection
  implements oracle.jdbc.OracleConnection, _Proxy_
{
  private oracle.jdbc.OracleConnection delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject27323;
  private static Method methodObject27354;
  private static Method methodObject27356;
  private static Method methodObject27409;
  private static Method methodObject27432;
  private static Method methodObject27269;
  private static Method methodObject27291;
  private static Method methodObject27384;
  private static Method methodObject27279;
  private static Method methodObject27302;
  private static Method methodObject27436;
  private static Method methodObject27274;
  private static Method methodObject27368;
  private static Method methodObject27370;
  private static Method methodObject27428;
  private static Method methodObject27375;
  private static Method methodObject27301;
  private static Method methodObject27288;
  private static Method methodObject27398;
  private static Method methodObject27349;
  private static Method methodObject27369;
  private static Method methodObject27307;
  private static Method methodObject27331;
  private static Method methodObject27311;
  private static Method methodObject27341;
  private static Method methodObject27382;
  private static Method methodObject27282;
  private static Method methodObject27286;
  private static Method methodObject27418;
  private static Method methodObject27293;
  private static Method methodObject27412;
  private static Method methodObject27396;
  private static Method methodObject27310;
  private static Method methodObject27425;
  private static Method methodObject27367;
  private static Method methodObject27366;
  private static Method methodObject27284;
  private static Method methodObject27343;
  private static Method methodObject27372;
  private static Method methodObject27327;
  private static Method methodObject27457;
  private static Method methodObject27389;
  private static Method methodObject27444;
  private static Method methodObject27448;
  private static Method methodObject27360;
  private static Method methodObject27365;
  private static Method methodObject27340;
  private static Method methodObject27441;
  private static Method methodObject27420;
  private static Method methodObject27410;
  private static Method methodObject27416;
  private static Method methodObject27314;
  private static Method methodObject27290;
  private static Method methodObject27426;
  private static Method methodObject27357;
  private static Method methodObject27298;
  private static Method methodObject27405;
  private static Method methodObject27401;
  private static Method methodObject27294;
  private static Method methodObject27408;
  private static Method methodObject27400;
  private static Method methodObject27319;
  private static Method methodObject27392;
  private static Method methodObject27339;
  private static Method methodObject27447;
  private static Method methodObject27414;
  private static Method methodObject27306;
  private static Method methodObject27295;
  private static Method methodObject27350;
  private static Method methodObject27429;
  private static Method methodObject27287;
  private static Method methodObject27459;
  private static Method methodObject27321;
  private static Method methodObject27440;
  private static Method methodObject27386;
  private static Method methodObject27270;
  private static Method methodObject27430;
  private static Method methodObject27391;
  private static Method methodObject27278;
  private static Method methodObject27333;
  private static Method methodObject27434;
  private static Method methodObject27355;
  private static Method methodObject27431;
  private static Method methodObject27404;
  private static Method methodObject27359;
  private static Method methodObject27460;
  private static Method methodObject27427;
  private static Method methodObject27456;
  private static Method methodObject27438;
  private static Method methodObject27439;
  private static Method methodObject27297;
  private static Method methodObject27443;
  private static Method methodObject27303;
  private static Method methodObject27315;
  private static Method methodObject27402;
  private static Method methodObject27397;
  private static Method methodObject27289;
  private static Method methodObject27415;
  private static Method methodObject27313;
  private static Method methodObject27442;
  private static Method methodObject27374;
  private static Method methodObject27334;
  private static Method methodObject27351;
  private static Method methodObject27458;
  private static Method methodObject27371;
  private static Method methodObject27316;
  private static Method methodObject27285;
  private static Method methodObject27411;
  private static Method methodObject27453;
  private static Method methodObject27390;
  private static Method methodObject27361;
  private static Method methodObject27380;
  private static Method methodObject27353;
  private static Method methodObject27413;
  private static Method methodObject27364;
  private static Method methodObject27304;
  private static Method methodObject27348;
  private static Method methodObject27383;
  private static Method methodObject27299;
  private static Method methodObject27346;
  private static Method methodObject27417;
  private static Method methodObject27329;
  private static Method methodObject27363;
  private static Method methodObject27424;
  private static Method methodObject27419;
  private static Method methodObject27433;
  private static Method methodObject27280;
  private static Method methodObject27322;
  private static Method methodObject27326;
  private static Method methodObject27388;
  private static Method methodObject27381;
  private static Method methodObject27325;
  private static Method methodObject27406;
  private static Method methodObject27399;
  private static Method methodObject27318;
  private static Method methodObject27362;
  private static Method methodObject27455;
  private static Method methodObject27345;
  private static Method methodObject27317;
  private static Method methodObject27376;
  private static Method methodObject27335;
  private static Method methodObject27271;
  private static Method methodObject27445;
  private static Method methodObject27344;
  private static Method methodObject27378;
  private static Method methodObject27312;
  private static Method methodObject27403;
  private static Method methodObject27342;
  private static Method methodObject27324;
  private static Method methodObject27421;
  private static Method methodObject27275;
  private static Method methodObject27309;
  private static Method methodObject27305;
  private static Method methodObject27300;
  private static Method methodObject27277;
  private static Method methodObject27273;
  private static Method methodObject27336;
  private static Method methodObject27358;
  private static Method methodObject27328;
  private static Method methodObject27435;
  private static Method methodObject27308;
  private static Method methodObject27387;
  private static Method methodObject27423;
  private static Method methodObject27276;
  private static Method methodObject27451;
  private static Method methodObject27292;
  private static Method methodObject27393;
  private static Method methodObject27379;
  private static Method methodObject27437;
  private static Method methodObject27394;
  private static Method methodObject27422;
  private static Method methodObject27452;
  private static Method methodObject27332;
  private static Method methodObject27454;
  private static Method methodObject27283;
  private static Method methodObject27450;
  private static Method methodObject27395;
  private static Method methodObject27385;
  private static Method methodObject27338;
  private static Method methodObject27347;
  private static Method methodObject27407;
  private static Method methodObject27373;
  private static Method methodObject27320;
  private static Method methodObject27449;
  private static Method methodObject27446;
  private static Method methodObject27330;
  private static Method methodObject27281;
  private static Method methodObject27377;
  private static Method methodObject27296;
  private static Method methodObject27352;
  private static Method methodObject27337;
  private static Method methodObject27272;
  
  public void setExplicitCachingEnabled(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27323, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setExplicitCachingEnabled(arg0);
      postForAll(methodObject27323);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27323, e);
    }
  }
  
  public AQMessage dequeue(String arg0, AQDequeueOptions arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27354, this, new Object[] { arg0, arg1, arg2 });
      return (AQMessage)postForAll(methodObject27354, (Object)this.delegate.dequeue(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQMessage)postForAll(methodObject27354, onErrorForAll(methodObject27354, e));
    }
  }
  
  public DatabaseChangeRegistration getDatabaseChangeRegistration(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27356, this, new Object[] { Integer.valueOf(arg0) });
      return (DatabaseChangeRegistration)postForAll(methodObject27356, (Object)this.delegate.getDatabaseChangeRegistration(arg0));
    }
    catch (SQLException e)
    {
      return (DatabaseChangeRegistration)postForAll(methodObject27356, onErrorForAll(methodObject27356, e));
    }
  }
  
  public TimeZone getDefaultTimeZone()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27409, this, zeroLengthObjectArray);
      return (TimeZone)postForAll(methodObject27409, (Object)this.delegate.getDefaultTimeZone());
    }
    catch (SQLException e)
    {
      return (TimeZone)postForAll(methodObject27409, onErrorForAll(methodObject27409, e));
    }
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27432, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (CallableStatement)postForAll(methodObject27432, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27432));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27432, onErrorForAll(methodObject27432, e));
    }
  }
  
  public void shutdown(OracleConnection.DatabaseShutdownMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27269, this, new Object[] { arg0 });
      this.delegate.shutdown(arg0);
      postForAll(methodObject27269);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27269, e);
    }
  }
  
  public void archive(int arg0, int arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27291, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), arg2 });
      this.delegate.archive(arg0, arg1, arg2);
      postForAll(methodObject27291);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27291, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27384, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27384, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27384, onErrorForAll(methodObject27384, e));
    }
  }
  
  public void oracleReleaseSavepoint(OracleSavepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27279, this, new Object[] { arg0 });
      this.delegate.oracleReleaseSavepoint((arg0 instanceof _Proxy_) ? (OracleSavepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27279);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27279, e);
    }
  }
  
  public String getSQLType(Object arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27302, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27302, (Object)this.delegate.getSQLType((arg0 instanceof _Proxy_) ? (Object)((_Proxy_)arg0)._getDelegate_() : arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27302, onErrorForAll(methodObject27302, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27436, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (PreparedStatement)postForAll(methodObject27436, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27436));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27436, onErrorForAll(methodObject27436, e));
    }
  }
  
  public void close(int arg0)
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27274, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.close(arg0);
      postForClosure(methodObject27274);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27274, e);
    }
  }
  
  public DATE createDATE(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27368, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27368, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27368, onErrorForAll(methodObject27368, e));
    }
  }
  
  public INTERVALDS createINTERVALDS(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27370, this, new Object[] { arg0 });
      return (INTERVALDS)postForAll(methodObject27370, (Object)this.delegate.createINTERVALDS(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALDS)postForAll(methodObject27370, onErrorForAll(methodObject27370, e));
    }
  }
  
  public Statement createStatement(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27428, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return (Statement)postForAll(methodObject27428, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1), this, this.proxyCache, methodObject27428));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27428, onErrorForAll(methodObject27428, e));
    }
  }
  
  public NUMBER createNUMBER(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27375, this, new Object[] { Integer.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27375, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27375, onErrorForAll(methodObject27375, e));
    }
  }
  
  public boolean getRemarksReporting()
  {
    super.preForAll(methodObject27301, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27301, Boolean.valueOf(this.delegate.getRemarksReporting()))).booleanValue();
  }
  
  public int pingDatabase()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27288, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27288, Integer.valueOf(this.delegate.pingDatabase()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27288, onErrorForAll(methodObject27288, e))).intValue();
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27398, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27398, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27398, onErrorForAll(methodObject27398, e));
    }
  }
  
  public int getConnectionReleasePriority()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27349, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27349, Integer.valueOf(this.delegate.getConnectionReleasePriority()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27349, onErrorForAll(methodObject27349, e))).intValue();
    }
  }
  
  public DATE createDATE(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27369, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27369, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27369, onErrorForAll(methodObject27369, e));
    }
  }
  
  public boolean getXAErrorFlag()
  {
    super.preForAll(methodObject27307, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27307, Boolean.valueOf(this.delegate.getXAErrorFlag()))).booleanValue();
  }
  
  public void startup(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27331, this, new Object[] { arg0, Integer.valueOf(arg1) });
      this.delegate.startup(arg0, arg1);
      postForAll(methodObject27331);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27331, e);
    }
  }
  
  public void setDefaultExecuteBatch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27311, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setDefaultExecuteBatch(arg0);
      postForAll(methodObject27311);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27311, e);
    }
  }
  
  public void registerTAFCallback(OracleOCIFailover arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27341, this, new Object[] { arg0, arg1 });
      this.delegate.registerTAFCallback(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27341);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27341, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27382, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27382, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27382, onErrorForAll(methodObject27382, e));
    }
  }
  
  public ARRAY createARRAY(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27282, this, new Object[] { arg0, arg1 });
      return (ARRAY)postForAll(methodObject27282, (Object)super.createARRAY(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1));
    }
    catch (SQLException e)
    {
      return (ARRAY)postForAll(methodObject27282, onErrorForAll(methodObject27282, e));
    }
  }
  
  public String getUserName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27286, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27286, (Object)this.delegate.getUserName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27286, onErrorForAll(methodObject27286, e));
    }
  }
  
  public Savepoint setSavepoint()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27418, this, zeroLengthObjectArray);
      return (Savepoint)postForAll(methodObject27418, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(), this, this.proxyCache, methodObject27418));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27418, onErrorForAll(methodObject27418, e));
    }
  }
  
  public boolean getAutoClose()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27293, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27293, Boolean.valueOf(this.delegate.getAutoClose()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27293, onErrorForAll(methodObject27293, e))).booleanValue();
    }
  }
  
  public void setReadOnly(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27412, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setReadOnly(arg0);
      postForAll(methodObject27412);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27412, e);
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27396, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27396, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27396, onErrorForAll(methodObject27396, e));
    }
  }
  
  public void setAutoClose(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27310, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoClose(arg0);
      postForAll(methodObject27310);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27310, e);
    }
  }
  
  public NClob createNClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27425, this, zeroLengthObjectArray);
      return (NClob)postForAll(methodObject27425, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createNClob(), this, this.proxyCache, methodObject27425));
    }
    catch (SQLException e)
    {
      return (NClob)postForAll(methodObject27425, onErrorForAll(methodObject27425, e));
    }
  }
  
  public DATE createDATE(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27367, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27367, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27367, onErrorForAll(methodObject27367, e));
    }
  }
  
  public DATE createDATE(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27366, this, new Object[] { arg0, arg1 });
      return (DATE)postForAll(methodObject27366, (Object)this.delegate.createDATE(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27366, onErrorForAll(methodObject27366, e));
    }
  }
  
  public oracle.jdbc.OracleConnection unwrap()
  {
    super.preForAll(methodObject27284, this, zeroLengthObjectArray);
    return (oracle.jdbc.OracleConnection)postForAll(methodObject27284, (Object)super.unwrap());
  }
  
  public boolean isProxySession()
  {
    super.preForAll(methodObject27343, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27343, Boolean.valueOf(this.delegate.isProxySession()))).booleanValue();
  }
  
  public NUMBER createNUMBER(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27372, this, new Object[] { Boolean.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27372, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27372, onErrorForAll(methodObject27372, e));
    }
  }
  
  public PreparedStatement getStatementWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27327, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27327, this.proxyFactory.proxyForCache((Object)this.delegate.getStatementWithKey(arg0), this, this.proxyCache, methodObject27327));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27327, onErrorForAll(methodObject27327, e));
    }
  }
  
  public void setTransactionIsolation(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27457, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setTransactionIsolation(arg0);
      postForAll(methodObject27457);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27457, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27389, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27389, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27389, onErrorForAll(methodObject27389, e));
    }
  }
  
  public boolean getAutoCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27444, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27444, Boolean.valueOf(this.delegate.getAutoCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27444, onErrorForAll(methodObject27444, e))).booleanValue();
    }
  }
  
  public int getTransactionIsolation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27448, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27448, Integer.valueOf(this.delegate.getTransactionIsolation()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27448, onErrorForAll(methodObject27448, e))).intValue();
    }
  }
  
  public void unregisterDatabaseChangeNotification(long arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27360, this, new Object[] { Long.valueOf(arg0), arg1 });
      this.delegate.unregisterDatabaseChangeNotification(arg0, arg1);
      postForAll(methodObject27360);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27360, e);
    }
  }
  
  public DATE createDATE(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27365, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27365, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27365, onErrorForAll(methodObject27365, e));
    }
  }
  
  public boolean isLogicalConnection()
  {
    super.preForAll(methodObject27340, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27340, Boolean.valueOf(this.delegate.isLogicalConnection()))).booleanValue();
  }
  
  public int getHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27441, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27441, Integer.valueOf(this.delegate.getHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27441, onErrorForAll(methodObject27441, e))).intValue();
    }
  }
  
  public void releaseSavepoint(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27420, this, new Object[] { arg0 });
      this.delegate.releaseSavepoint((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27420);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27420, e);
    }
  }
  
  public void setApplicationContext(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27410, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.setApplicationContext(arg0, arg1, arg2);
      postForAll(methodObject27410);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27410, e);
    }
  }
  
  public void rollback()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27416, this, zeroLengthObjectArray);
      this.delegate.rollback();
      postForAll(methodObject27416);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27416, e);
    }
  }
  
  public void setIncludeSynonyms(boolean arg0)
  {
    super.preForAll(methodObject27314, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setIncludeSynonyms(arg0);
    postForAll(methodObject27314);
  }
  
  public void putDescriptor(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27290, this, new Object[] { arg0, arg1 });
      this.delegate.putDescriptor(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1);
      postForAll(methodObject27290);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27290, e);
    }
  }
  
  public SQLXML createSQLXML()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27426, this, zeroLengthObjectArray);
      return (SQLXML)postForAll(methodObject27426, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createSQLXML(), this, this.proxyCache, methodObject27426));
    }
    catch (SQLException e)
    {
      return (SQLXML)postForAll(methodObject27426, onErrorForAll(methodObject27426, e));
    }
  }
  
  public void unregisterDatabaseChangeNotification(DatabaseChangeRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27357, this, new Object[] { arg0 });
      this.delegate.unregisterDatabaseChangeNotification(arg0);
      postForAll(methodObject27357);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27357, e);
    }
  }
  
  public boolean getIncludeSynonyms()
  {
    super.preForAll(methodObject27298, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27298, Boolean.valueOf(this.delegate.getIncludeSynonyms()))).booleanValue();
  }
  
  public String getEncryptionAlgorithmName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27405, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27405, (Object)this.delegate.getEncryptionAlgorithmName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27405, onErrorForAll(methodObject27405, e));
    }
  }
  
  public TypeDescriptor[] getAllTypeDescriptorsInCurrentSchema()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27401, this, zeroLengthObjectArray);
      return (TypeDescriptor[])postForAll(methodObject27401, (Object)this.delegate.getAllTypeDescriptorsInCurrentSchema());
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27401, onErrorForAll(methodObject27401, e));
    }
  }
  
  public int getDefaultExecuteBatch()
  {
    super.preForAll(methodObject27294, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27294, Integer.valueOf(this.delegate.getDefaultExecuteBatch()))).intValue();
  }
  
  public void setDefaultTimeZone(TimeZone arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27408, this, new Object[] { arg0 });
      this.delegate.setDefaultTimeZone(arg0);
      postForAll(methodObject27408);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27408, e);
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(DATE arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27400, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27400, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27400, onErrorForAll(methodObject27400, e));
    }
  }
  
  public void setStatementCacheSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27319, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setStatementCacheSize(arg0);
      postForAll(methodObject27319);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27319, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27392, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27392, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27392, onErrorForAll(methodObject27392, e));
    }
  }
  
  public String getSessionTimeZoneOffset()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27339, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27339, (Object)this.delegate.getSessionTimeZoneOffset());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27339, onErrorForAll(methodObject27339, e));
    }
  }
  
  public Properties getClientInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27447, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27447, (Object)this.delegate.getClientInfo());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27447, onErrorForAll(methodObject27447, e));
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27414, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27414, Boolean.valueOf(this.delegate.isReadOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27414, onErrorForAll(methodObject27414, e))).booleanValue();
    }
  }
  
  public boolean getUsingXAFlag()
  {
    super.preForAll(methodObject27306, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27306, Boolean.valueOf(this.delegate.getUsingXAFlag()))).booleanValue();
  }
  
  public int getDefaultRowPrefetch()
  {
    super.preForAll(methodObject27295, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27295, Integer.valueOf(this.delegate.getDefaultRowPrefetch()))).intValue();
  }
  
  public void setPlsqlWarnings(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27350, this, new Object[] { arg0 });
      this.delegate.setPlsqlWarnings(arg0);
      postForAll(methodObject27350);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27350, e);
    }
  }
  
  public Statement createStatement(int arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27429, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (Statement)postForAll(methodObject27429, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27429));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27429, onErrorForAll(methodObject27429, e));
    }
  }
  
  public oracle.jdbc.internal.OracleConnection physicalConnectionWithin()
  {
    super.preForAll(methodObject27287, this, zeroLengthObjectArray);
    return (oracle.jdbc.internal.OracleConnection)postForAll(methodObject27287, this.proxyFactory.proxyForCache((Object)this.delegate.physicalConnectionWithin(), this, this.proxyCache, methodObject27287));
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27459, this, new Object[] { arg0 });
      return postForAll(methodObject27459, super.unwrap(arg0));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27459, onErrorForAll(methodObject27459, e));
    }
  }
  
  public void setImplicitCachingEnabled(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27321, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setImplicitCachingEnabled(arg0);
      postForAll(methodObject27321);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27321, e);
    }
  }
  
  public void clearWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27440, this, zeroLengthObjectArray);
      this.delegate.clearWarnings();
      postForAll(methodObject27440);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27440, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27386, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27386, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27386, onErrorForAll(methodObject27386, e));
    }
  }
  
  public Object getDescriptor(String arg0)
  {
    super.preForAll(methodObject27270, this, new Object[] { arg0 });
    return postForAll(methodObject27270, this.proxyFactory.proxyForCache(this.delegate.getDescriptor(arg0), this, this.proxyCache, methodObject27270));
  }
  
  public CallableStatement prepareCall(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27430, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27430, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0), this, this.proxyCache, methodObject27430));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27430, onErrorForAll(methodObject27430, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27391, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27391, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27391, onErrorForAll(methodObject27391, e));
    }
  }
  
  public OracleSavepoint oracleSetSavepoint(String arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27278, this, new Object[] { arg0 });
      return (OracleSavepoint)postForAll(methodObject27278, this.proxyFactory.proxyForCreate((Object)this.delegate.oracleSetSavepoint(arg0), this, this.proxyCache, methodObject27278));
    }
    catch (SQLException e)
    {
      return (OracleSavepoint)postForAll(methodObject27278, onErrorForAll(methodObject27278, e));
    }
  }
  
  public PreparedStatement prepareStatementWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27333, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27333, this.proxyFactory.proxyForCache((Object)this.delegate.prepareStatementWithKey(arg0), this, this.proxyCache, methodObject27333));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27333, onErrorForAll(methodObject27333, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27434, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (PreparedStatement)postForAll(methodObject27434, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2), this, this.proxyCache, methodObject27434));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27434, onErrorForAll(methodObject27434, e));
    }
  }
  
  public DatabaseChangeRegistration registerDatabaseChangeNotification(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27355, this, new Object[] { arg0 });
      return (DatabaseChangeRegistration)postForAll(methodObject27355, (Object)this.delegate.registerDatabaseChangeNotification(arg0));
    }
    catch (SQLException e)
    {
      return (DatabaseChangeRegistration)postForAll(methodObject27355, onErrorForAll(methodObject27355, e));
    }
  }
  
  public CallableStatement prepareCall(String arg0, int arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27431, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2) });
      return (CallableStatement)postForAll(methodObject27431, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareCall(arg0, arg1, arg2), this, this.proxyCache, methodObject27431));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27431, onErrorForAll(methodObject27431, e));
    }
  }
  
  public String getDataIntegrityAlgorithmName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27404, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27404, (Object)this.delegate.getDataIntegrityAlgorithmName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27404, onErrorForAll(methodObject27404, e));
    }
  }
  
  public void unregisterDatabaseChangeNotification(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27359, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.unregisterDatabaseChangeNotification(arg0);
      postForAll(methodObject27359);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27359, e);
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27460, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject27460, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27460, onErrorForAll(methodObject27460, e))).booleanValue();
    }
  }
  
  public Statement createStatement()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27427, this, zeroLengthObjectArray);
      return (Statement)postForAll(methodObject27427, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createStatement(), this, this.proxyCache, methodObject27427));
    }
    catch (SQLException e)
    {
      return (Statement)postForAll(methodObject27427, onErrorForAll(methodObject27427, e));
    }
  }
  
  public void setHoldability(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27456, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setHoldability(arg0);
      postForAll(methodObject27456);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27456, e);
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, String[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27438, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27438, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27438));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27438, onErrorForAll(methodObject27438, e));
    }
  }
  
  public DatabaseMetaData getMetaData()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27439, this, zeroLengthObjectArray);
      return (DatabaseMetaData)postForAll(methodObject27439, this.proxyFactory.proxyForCache((Object)this.delegate.getMetaData(), this, this.proxyCache, methodObject27439));
    }
    catch (SQLException e)
    {
      return (DatabaseMetaData)postForAll(methodObject27439, onErrorForAll(methodObject27439, e));
    }
  }
  
  public short getEndToEndECIDSequenceNumber()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27297, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27297, Short.valueOf(this.delegate.getEndToEndECIDSequenceNumber()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27297, onErrorForAll(methodObject27297, e))).shortValue();
    }
  }
  
  public boolean isClosed()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27443, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27443, Boolean.valueOf(this.delegate.isClosed()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27443, onErrorForAll(methodObject27443, e))).booleanValue();
    }
  }
  
  public int getStmtCacheSize()
  {
    super.preForAll(methodObject27303, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject27303, Integer.valueOf(this.delegate.getStmtCacheSize()))).intValue();
  }
  
  public void setRemarksReporting(boolean arg0)
  {
    super.preForAll(methodObject27315, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setRemarksReporting(arg0);
    postForAll(methodObject27315);
  }
  
  public TypeDescriptor[] getTypeDescriptorsFromListInCurrentSchema(String[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27402, this, new Object[] { arg0 });
      return (TypeDescriptor[])postForAll(methodObject27402, (Object)this.delegate.getTypeDescriptorsFromListInCurrentSchema(arg0));
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27402, onErrorForAll(methodObject27402, e));
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27397, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27397, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27397, onErrorForAll(methodObject27397, e));
    }
  }
  
  public int pingDatabase(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27289, this, new Object[] { Integer.valueOf(arg0) });
      return ((Integer)postForAll(methodObject27289, Integer.valueOf(this.delegate.pingDatabase(arg0)))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27289, onErrorForAll(methodObject27289, e))).intValue();
    }
  }
  
  public void commit()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27415, this, zeroLengthObjectArray);
      this.delegate.commit();
      postForAll(methodObject27415);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27415, e);
    }
  }
  
  public void setEndToEndMetrics(String[] arg0, short arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27313, this, new Object[] { arg0, Short.valueOf(arg1) });
      this.delegate.setEndToEndMetrics(arg0, arg1);
      postForAll(methodObject27313);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27313, e);
    }
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27442, this, zeroLengthObjectArray);
      return (SQLWarning)postForAll(methodObject27442, (Object)this.delegate.getWarnings());
    }
    catch (SQLException e)
    {
      return (SQLWarning)postForAll(methodObject27442, onErrorForAll(methodObject27442, e));
    }
  }
  
  public NUMBER createNUMBER(short arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27374, this, new Object[] { Short.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27374, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27374, onErrorForAll(methodObject27374, e));
    }
  }
  
  public CallableStatement prepareCallWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27334, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27334, this.proxyFactory.proxyForCache((Object)this.delegate.prepareCallWithKey(arg0), this, this.proxyCache, methodObject27334));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27334, onErrorForAll(methodObject27334, e));
    }
  }
  
  public AQNotificationRegistration[] registerAQNotification(String[] arg0, Properties[] arg1, Properties arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27351, this, new Object[] { arg0, arg1, arg2 });
      return (AQNotificationRegistration[])postForAll(methodObject27351, (Object)this.delegate.registerAQNotification(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQNotificationRegistration[])postForAll(methodObject27351, onErrorForAll(methodObject27351, e));
    }
  }
  
  public void setTypeMap(Map arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27458, this, new Object[] { arg0 });
      this.delegate.setTypeMap(arg0);
      postForAll(methodObject27458);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27458, e);
    }
  }
  
  public INTERVALYM createINTERVALYM(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27371, this, new Object[] { arg0 });
      return (INTERVALYM)postForAll(methodObject27371, (Object)this.delegate.createINTERVALYM(arg0));
    }
    catch (SQLException e)
    {
      return (INTERVALYM)postForAll(methodObject27371, onErrorForAll(methodObject27371, e));
    }
  }
  
  public void setRestrictGetTables(boolean arg0)
  {
    super.preForAll(methodObject27316, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setRestrictGetTables(arg0);
    postForAll(methodObject27316);
  }
  
  public Connection _getPC()
  {
    super.preForAll(methodObject27285, this, zeroLengthObjectArray);
    return (Connection)postForAll(methodObject27285, (Object)this.delegate._getPC());
  }
  
  public void clearAllApplicationContext(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27411, this, new Object[] { arg0 });
      this.delegate.clearAllApplicationContext(arg0);
      postForAll(methodObject27411);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27411, e);
    }
  }
  
  public void setCatalog(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27453, this, new Object[] { arg0 });
      this.delegate.setCatalog(arg0);
      postForAll(methodObject27453);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27453, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Time arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27390, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27390, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27390, onErrorForAll(methodObject27390, e));
    }
  }
  
  public BINARY_DOUBLE createBINARY_DOUBLE(double arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27361, this, new Object[] { Double.valueOf(arg0) });
      return (BINARY_DOUBLE)postForAll(methodObject27361, (Object)this.delegate.createBINARY_DOUBLE(arg0));
    }
    catch (SQLException e)
    {
      return (BINARY_DOUBLE)postForAll(methodObject27361, onErrorForAll(methodObject27361, e));
    }
  }
  
  public NUMBER createNUMBER(BigInteger arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27380, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject27380, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27380, onErrorForAll(methodObject27380, e));
    }
  }
  
  public AQMessage dequeue(String arg0, AQDequeueOptions arg1, byte[] arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27353, this, new Object[] { arg0, arg1, arg2 });
      return (AQMessage)postForAll(methodObject27353, (Object)this.delegate.dequeue(arg0, arg1, arg2));
    }
    catch (SQLException e)
    {
      return (AQMessage)postForAll(methodObject27353, onErrorForAll(methodObject27353, e));
    }
  }
  
  public void close()
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27413, this, zeroLengthObjectArray);
      this.delegate.close();
      postForClosure(methodObject27413);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27413, e);
    }
  }
  
  public DATE createDATE(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27364, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27364, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27364, onErrorForAll(methodObject27364, e));
    }
  }
  
  public short getStructAttrCsId()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27304, this, zeroLengthObjectArray);
      return ((Short)postForAll(methodObject27304, Short.valueOf(this.delegate.getStructAttrCsId()))).shortValue();
    }
    catch (SQLException e)
    {
      return ((Short)postForAll(methodObject27304, onErrorForAll(methodObject27304, e))).shortValue();
    }
  }
  
  public void setConnectionReleasePriority(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27348, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setConnectionReleasePriority(arg0);
      postForAll(methodObject27348);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27348, e);
    }
  }
  
  public TIMESTAMP createTIMESTAMP(DATE arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27383, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27383, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27383, onErrorForAll(methodObject27383, e));
    }
  }
  
  public boolean getRestrictGetTables()
  {
    super.preForAll(methodObject27299, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27299, Boolean.valueOf(this.delegate.getRestrictGetTables()))).booleanValue();
  }
  
  public Properties getUnMatchedConnectionAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27346, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27346, (Object)this.delegate.getUnMatchedConnectionAttributes());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27346, onErrorForAll(methodObject27346, e));
    }
  }
  
  public void rollback(Savepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27417, this, new Object[] { arg0 });
      this.delegate.rollback((arg0 instanceof _Proxy_) ? (Savepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27417);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27417, e);
    }
  }
  
  public void setUsingXAFlag(boolean arg0)
  {
    super.preForAll(methodObject27329, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setUsingXAFlag(arg0);
    postForAll(methodObject27329);
  }
  
  public DATE createDATE(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27363, this, new Object[] { arg0 });
      return (DATE)postForAll(methodObject27363, (Object)this.delegate.createDATE(arg0));
    }
    catch (SQLException e)
    {
      return (DATE)postForAll(methodObject27363, onErrorForAll(methodObject27363, e));
    }
  }
  
  public Clob createClob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27424, this, zeroLengthObjectArray);
      return (Clob)postForAll(methodObject27424, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createClob(), this, this.proxyCache, methodObject27424));
    }
    catch (SQLException e)
    {
      return (Clob)postForAll(methodObject27424, onErrorForAll(methodObject27424, e));
    }
  }
  
  public Savepoint setSavepoint(String arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27419, this, new Object[] { arg0 });
      return (Savepoint)postForAll(methodObject27419, this.proxyFactory.proxyForCreate((Object)this.delegate.setSavepoint(arg0), this, this.proxyCache, methodObject27419));
    }
    catch (SQLException e)
    {
      return (Savepoint)postForAll(methodObject27419, onErrorForAll(methodObject27419, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27433, this, new Object[] { arg0 });
      return (PreparedStatement)postForAll(methodObject27433, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0), this, this.proxyCache, methodObject27433));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27433, onErrorForAll(methodObject27433, e));
    }
  }
  
  public void abort()
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27280, this, zeroLengthObjectArray);
      this.delegate.abort();
      postForClosure(methodObject27280);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27280, e);
    }
  }
  
  public boolean getImplicitCachingEnabled()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27322, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27322, Boolean.valueOf(this.delegate.getImplicitCachingEnabled()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27322, onErrorForAll(methodObject27322, e))).booleanValue();
    }
  }
  
  public void purgeExplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27326, this, zeroLengthObjectArray);
      this.delegate.purgeExplicitCache();
      postForAll(methodObject27326);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27326, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Date arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27388, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27388, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27388, onErrorForAll(methodObject27388, e));
    }
  }
  
  public NUMBER createNUMBER(String arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27381, this, new Object[] { arg0, Integer.valueOf(arg1) });
      return (NUMBER)postForAll(methodObject27381, (Object)this.delegate.createNUMBER(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27381, onErrorForAll(methodObject27381, e));
    }
  }
  
  public void purgeImplicitCache()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27325, this, zeroLengthObjectArray);
      this.delegate.purgeImplicitCache();
      postForAll(methodObject27325);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27325, e);
    }
  }
  
  public String getAuthenticationAdaptorName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27406, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27406, (Object)this.delegate.getAuthenticationAdaptorName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27406, onErrorForAll(methodObject27406, e));
    }
  }
  
  public TIMESTAMPLTZ createTIMESTAMPLTZ(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27399, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPLTZ)postForAll(methodObject27399, (Object)this.delegate.createTIMESTAMPLTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPLTZ)postForAll(methodObject27399, onErrorForAll(methodObject27399, e));
    }
  }
  
  public void setStmtCacheSize(int arg0, boolean arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27318, this, new Object[] { Integer.valueOf(arg0), Boolean.valueOf(arg1) });
      this.delegate.setStmtCacheSize(arg0, arg1);
      postForAll(methodObject27318);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27318, e);
    }
  }
  
  public BINARY_FLOAT createBINARY_FLOAT(float arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27362, this, new Object[] { Float.valueOf(arg0) });
      return (BINARY_FLOAT)postForAll(methodObject27362, (Object)this.delegate.createBINARY_FLOAT(arg0));
    }
    catch (SQLException e)
    {
      return (BINARY_FLOAT)postForAll(methodObject27362, onErrorForAll(methodObject27362, e));
    }
  }
  
  public void setClientInfo(Properties arg0)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27455, this, new Object[] { arg0 });
    this.delegate.setClientInfo(arg0);
    postForAll(methodObject27455);
  }
  
  public Properties getConnectionAttributes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27345, this, zeroLengthObjectArray);
      return (Properties)postForAll(methodObject27345, (Object)this.delegate.getConnectionAttributes());
    }
    catch (SQLException e)
    {
      return (Properties)postForAll(methodObject27345, onErrorForAll(methodObject27345, e));
    }
  }
  
  public void setStmtCacheSize(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27317, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setStmtCacheSize(arg0);
      postForAll(methodObject27317);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27317, e);
    }
  }
  
  public NUMBER createNUMBER(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27376, this, new Object[] { Long.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27376, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27376, onErrorForAll(methodObject27376, e));
    }
  }
  
  public void setCreateStatementAsRefCursor(boolean arg0)
  {
    super.preForAll(methodObject27335, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setCreateStatementAsRefCursor(arg0);
    postForAll(methodObject27335);
  }
  
  public Properties getProperties()
  {
    super.preForAll(methodObject27271, this, zeroLengthObjectArray);
    return (Properties)postForAll(methodObject27271, (Object)this.delegate.getProperties());
  }
  
  public String getCatalog()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27445, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27445, (Object)this.delegate.getCatalog());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27445, onErrorForAll(methodObject27445, e));
    }
  }
  
  public void applyConnectionAttributes(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27344, this, new Object[] { arg0 });
      this.delegate.applyConnectionAttributes(arg0);
      postForAll(methodObject27344);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27344, e);
    }
  }
  
  public NUMBER createNUMBER(double arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27378, this, new Object[] { Double.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27378, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27378, onErrorForAll(methodObject27378, e));
    }
  }
  
  public void setDefaultRowPrefetch(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27312, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.setDefaultRowPrefetch(arg0);
      postForAll(methodObject27312);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27312, e);
    }
  }
  
  public TypeDescriptor[] getTypeDescriptorsFromList(String[][] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27403, this, new Object[] { arg0 });
      return (TypeDescriptor[])postForAll(methodObject27403, (Object)this.delegate.getTypeDescriptorsFromList(arg0));
    }
    catch (SQLException e)
    {
      return (TypeDescriptor[])postForAll(methodObject27403, onErrorForAll(methodObject27403, e));
    }
  }
  
  public void setWrapper(oracle.jdbc.OracleConnection arg0)
  {
    super.preForAll(methodObject27342, this, new Object[] { arg0 });
    this.delegate.setWrapper((arg0 instanceof _Proxy_) ? (oracle.jdbc.OracleConnection)((_Proxy_)arg0)._getDelegate_() : arg0);
    postForAll(methodObject27342);
  }
  
  public boolean getExplicitCachingEnabled()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27324, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject27324, Boolean.valueOf(this.delegate.getExplicitCachingEnabled()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27324, onErrorForAll(methodObject27324, e))).booleanValue();
    }
  }
  
  public Array createArrayOf(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27421, this, new Object[] { arg0, arg1 });
      return (Array)postForAll(methodObject27421, this.proxyFactory.proxyForCreateCache((Object)super.createArrayOf(arg0, arg1), this, this.proxyCache, methodObject27421));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27421, onErrorForAll(methodObject27421, e));
    }
  }
  
  public void commit(EnumSet arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27275, this, new Object[] { arg0 });
      this.delegate.commit(arg0);
      postForAll(methodObject27275);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27275, e);
    }
  }
  
  public void registerSQLType(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27309, this, new Object[] { arg0, arg1 });
      this.delegate.registerSQLType(arg0, arg1);
      postForAll(methodObject27309);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27309, e);
    }
  }
  
  public String getCurrentSchema()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27305, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject27305, (Object)this.delegate.getCurrentSchema());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27305, onErrorForAll(methodObject27305, e));
    }
  }
  
  public Object getJavaObject(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27300, this, new Object[] { arg0 });
      return postForAll(methodObject27300, this.proxyFactory.proxyForCache(this.delegate.getJavaObject(arg0), this, this.proxyCache, methodObject27300));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject27300, onErrorForAll(methodObject27300, e));
    }
  }
  
  public OracleSavepoint oracleSetSavepoint()
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27277, this, zeroLengthObjectArray);
      return (OracleSavepoint)postForAll(methodObject27277, this.proxyFactory.proxyForCreate((Object)this.delegate.oracleSetSavepoint(), this, this.proxyCache, methodObject27277));
    }
    catch (SQLException e)
    {
      return (OracleSavepoint)postForAll(methodObject27277, onErrorForAll(methodObject27277, e));
    }
  }
  
  public void close(Properties arg0)
    throws SQLException
  {
    try
    {
      super.preForClosure(methodObject27273, this, new Object[] { arg0 });
      this.delegate.close(arg0);
      postForClosure(methodObject27273);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27273, e);
    }
  }
  
  public boolean getCreateStatementAsRefCursor()
  {
    super.preForAll(methodObject27336, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27336, Boolean.valueOf(this.delegate.getCreateStatementAsRefCursor()))).booleanValue();
  }
  
  public void unregisterDatabaseChangeNotification(int arg0, String arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27358, this, new Object[] { Integer.valueOf(arg0), arg1, Integer.valueOf(arg2) });
      this.delegate.unregisterDatabaseChangeNotification(arg0, arg1, arg2);
      postForAll(methodObject27358);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27358, e);
    }
  }
  
  public CallableStatement getCallWithKey(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27328, this, new Object[] { arg0 });
      return (CallableStatement)postForAll(methodObject27328, this.proxyFactory.proxyForCache((Object)this.delegate.getCallWithKey(arg0), this, this.proxyCache, methodObject27328));
    }
    catch (SQLException e)
    {
      return (CallableStatement)postForAll(methodObject27328, onErrorForAll(methodObject27328, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27435, this, new Object[] { arg0, Integer.valueOf(arg1), Integer.valueOf(arg2), Integer.valueOf(arg3) });
      return (PreparedStatement)postForAll(methodObject27435, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject27435));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27435, onErrorForAll(methodObject27435, e));
    }
  }
  
  public void registerSQLType(String arg0, Class arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27308, this, new Object[] { arg0, arg1 });
      this.delegate.registerSQLType(arg0, arg1);
      postForAll(methodObject27308);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27308, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27387, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27387, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27387, onErrorForAll(methodObject27387, e));
    }
  }
  
  public Blob createBlob()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27423, this, zeroLengthObjectArray);
      return (Blob)postForAll(methodObject27423, this.proxyFactory.proxyForCreateCache((Object)this.delegate.createBlob(), this, this.proxyCache, methodObject27423));
    }
    catch (SQLException e)
    {
      return (Blob)postForAll(methodObject27423, onErrorForAll(methodObject27423, e));
    }
  }
  
  public void oracleRollback(OracleSavepoint arg0)
    throws SQLException
  {
    try
    {
      super.preForTxnControl(methodObject27276, this, new Object[] { arg0 });
      this.delegate.oracleRollback((arg0 instanceof _Proxy_) ? (OracleSavepoint)((_Proxy_)arg0)._getDelegate_() : arg0);
      postForAll(methodObject27276);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27276, e);
    }
  }
  
  public String nativeSQL(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27451, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27451, (Object)this.delegate.nativeSQL(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27451, onErrorForAll(methodObject27451, e));
    }
  }
  
  public void openProxySession(int arg0, Properties arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27292, this, new Object[] { Integer.valueOf(arg0), arg1 });
      this.delegate.openProxySession(arg0, arg1);
      postForAll(methodObject27292);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27292, e);
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27393, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27393, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27393, onErrorForAll(methodObject27393, e));
    }
  }
  
  public NUMBER createNUMBER(BigDecimal arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27379, this, new Object[] { arg0 });
      return (NUMBER)postForAll(methodObject27379, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27379, onErrorForAll(methodObject27379, e));
    }
  }
  
  public PreparedStatement prepareStatement(String arg0, int[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27437, this, new Object[] { arg0, arg1 });
      return (PreparedStatement)postForAll(methodObject27437, this.proxyFactory.proxyForCreateCache((Object)this.delegate.prepareStatement(arg0, arg1), this, this.proxyCache, methodObject27437));
    }
    catch (SQLException e)
    {
      return (PreparedStatement)postForAll(methodObject27437, onErrorForAll(methodObject27437, e));
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(String arg0, Calendar arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27394, this, new Object[] { arg0, arg1 });
      return (TIMESTAMPTZ)postForAll(methodObject27394, (Object)this.delegate.createTIMESTAMPTZ(arg0, arg1));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27394, onErrorForAll(methodObject27394, e));
    }
  }
  
  public Struct createStruct(String arg0, Object[] arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27422, this, new Object[] { arg0, arg1 });
      return (Struct)postForAll(methodObject27422, this.proxyFactory.proxyForCreateCache((Object)super.createStruct(arg0, arg1), this, this.proxyCache, methodObject27422));
    }
    catch (SQLException e)
    {
      return (Struct)postForAll(methodObject27422, onErrorForAll(methodObject27422, e));
    }
  }
  
  public void setAutoCommit(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27452, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.setAutoCommit(arg0);
      postForAll(methodObject27452);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27452, e);
    }
  }
  
  public void startup(OracleConnection.DatabaseStartupMode arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27332, this, new Object[] { arg0 });
      this.delegate.startup(arg0);
      postForAll(methodObject27332);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27332, e);
    }
  }
  
  public void setClientInfo(String arg0, String arg1)
    throws SQLClientInfoException
  {
    super.preForAll(methodObject27454, this, new Object[] { arg0, arg1 });
    this.delegate.setClientInfo(arg0, arg1);
    postForAll(methodObject27454);
  }
  
  public Array createOracleArray(String arg0, Object arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27283, this, new Object[] { arg0, arg1 });
      return (Array)postForAll(methodObject27283, this.proxyFactory.proxyForCreateCache((Object)super.createOracleArray(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1), this, this.proxyCache, methodObject27283));
    }
    catch (SQLException e)
    {
      return (Array)postForAll(methodObject27283, onErrorForAll(methodObject27283, e));
    }
  }
  
  public boolean isValid(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27450, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject27450, Boolean.valueOf(this.delegate.isValid(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject27450, onErrorForAll(methodObject27450, e))).booleanValue();
    }
  }
  
  public TIMESTAMPTZ createTIMESTAMPTZ(DATE arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27395, this, new Object[] { arg0 });
      return (TIMESTAMPTZ)postForAll(methodObject27395, (Object)this.delegate.createTIMESTAMPTZ(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMPTZ)postForAll(methodObject27395, onErrorForAll(methodObject27395, e));
    }
  }
  
  public TIMESTAMP createTIMESTAMP(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27385, this, new Object[] { arg0 });
      return (TIMESTAMP)postForAll(methodObject27385, (Object)this.delegate.createTIMESTAMP(arg0));
    }
    catch (SQLException e)
    {
      return (TIMESTAMP)postForAll(methodObject27385, onErrorForAll(methodObject27385, e));
    }
  }
  
  public String getSessionTimeZone()
  {
    super.preForAll(methodObject27338, this, zeroLengthObjectArray);
    return (String)postForAll(methodObject27338, (Object)this.delegate.getSessionTimeZone());
  }
  
  public void registerConnectionCacheCallback(OracleConnectionCacheCallback arg0, Object arg1, int arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27347, this, new Object[] { arg0, arg1, Integer.valueOf(arg2) });
      this.delegate.registerConnectionCacheCallback(arg0, (arg1 instanceof _Proxy_) ? (Object)((_Proxy_)arg1)._getDelegate_() : arg1, arg2);
      postForAll(methodObject27347);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27347, e);
    }
  }
  
  public boolean isUsable()
  {
    super.preForAll(methodObject27407, this, zeroLengthObjectArray);
    return ((Boolean)postForAll(methodObject27407, Boolean.valueOf(this.delegate.isUsable()))).booleanValue();
  }
  
  public NUMBER createNUMBER(byte arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27373, this, new Object[] { Byte.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27373, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27373, onErrorForAll(methodObject27373, e));
    }
  }
  
  public int getStatementCacheSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27320, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject27320, Integer.valueOf(this.delegate.getStatementCacheSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject27320, onErrorForAll(methodObject27320, e))).intValue();
    }
  }
  
  public Map getTypeMap()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27449, this, zeroLengthObjectArray);
      return (Map)postForAll(methodObject27449, (Object)this.delegate.getTypeMap());
    }
    catch (SQLException e)
    {
      return (Map)postForAll(methodObject27449, onErrorForAll(methodObject27449, e));
    }
  }
  
  public String getClientInfo(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27446, this, new Object[] { arg0 });
      return (String)postForAll(methodObject27446, (Object)this.delegate.getClientInfo(arg0));
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject27446, onErrorForAll(methodObject27446, e));
    }
  }
  
  public void setXAErrorFlag(boolean arg0)
  {
    super.preForAll(methodObject27330, this, new Object[] { Boolean.valueOf(arg0) });
    this.delegate.setXAErrorFlag(arg0);
    postForAll(methodObject27330);
  }
  
  public void cancel()
    throws SQLException
  {
    try
    {
      super.preForCancel(methodObject27281, this, zeroLengthObjectArray);
      this.delegate.cancel();
      postForCancel(methodObject27281);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27281, e);
    }
  }
  
  public NUMBER createNUMBER(float arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27377, this, new Object[] { Float.valueOf(arg0) });
      return (NUMBER)postForAll(methodObject27377, (Object)this.delegate.createNUMBER(arg0));
    }
    catch (SQLException e)
    {
      return (NUMBER)postForAll(methodObject27377, onErrorForAll(methodObject27377, e));
    }
  }
  
  public String[] getEndToEndMetrics()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27296, this, zeroLengthObjectArray);
      return (String[])postForAll(methodObject27296, (Object)this.delegate.getEndToEndMetrics());
    }
    catch (SQLException e)
    {
      return (String[])postForAll(methodObject27296, onErrorForAll(methodObject27296, e));
    }
  }
  
  public void unregisterAQNotification(AQNotificationRegistration arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27352, this, new Object[] { arg0 });
      this.delegate.unregisterAQNotification(arg0);
      postForAll(methodObject27352);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27352, e);
    }
  }
  
  public void setSessionTimeZone(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27337, this, new Object[] { arg0 });
      this.delegate.setSessionTimeZone(arg0);
      postForAll(methodObject27337);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27337, e);
    }
  }
  
  public void enqueue(String arg0, AQEnqueueOptions arg1, AQMessage arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject27272, this, new Object[] { arg0, arg1, arg2 });
      this.delegate.enqueue(arg0, arg1, arg2);
      postForAll(methodObject27272);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject27272, e);
    }
  }
  
  public oracle.jdbc.OracleConnection _getDelegate_()
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
      methodObject27323 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setExplicitCachingEnabled", new Class[] { Boolean.TYPE });
      methodObject27354 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("dequeue", new Class[] { String.class, AQDequeueOptions.class, String.class });
      methodObject27356 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDatabaseChangeRegistration", new Class[] { Integer.TYPE });
      methodObject27409 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultTimeZone", new Class[0]);
      methodObject27432 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27269 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("shutdown", new Class[] { OracleConnection.DatabaseShutdownMode.class });
      methodObject27291 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("archive", new Class[] { Integer.TYPE, Integer.TYPE, String.class });
      methodObject27384 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Time.class });
      methodObject27279 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleReleaseSavepoint", new Class[] { OracleSavepoint.class });
      methodObject27302 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSQLType", new Class[] { Object.class });
      methodObject27436 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE });
      methodObject27274 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("close", new Class[] { Integer.TYPE });
      methodObject27368 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Timestamp.class, Calendar.class });
      methodObject27370 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createINTERVALDS", new Class[] { String.class });
      methodObject27428 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject27375 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Integer.TYPE });
      methodObject27301 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getRemarksReporting", new Class[0]);
      methodObject27288 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("pingDatabase", new Class[0]);
      methodObject27398 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Timestamp.class, Calendar.class });
      methodObject27349 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getConnectionReleasePriority", new Class[0]);
      methodObject27369 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { String.class });
      methodObject27307 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getXAErrorFlag", new Class[0]);
      methodObject27331 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("startup", new Class[] { String.class, Integer.TYPE });
      methodObject27311 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultExecuteBatch", new Class[] { Integer.TYPE });
      methodObject27341 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerTAFCallback", new Class[] { OracleOCIFailover.class, Object.class });
      methodObject27382 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Date.class });
      methodObject27282 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createARRAY", new Class[] { String.class, Object.class });
      methodObject27286 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUserName", new Class[0]);
      methodObject27418 = Connection.class.getDeclaredMethod("setSavepoint", new Class[0]);
      methodObject27293 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAutoClose", new Class[0]);
      methodObject27412 = Connection.class.getDeclaredMethod("setReadOnly", new Class[] { Boolean.TYPE });
      methodObject27396 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Date.class, Calendar.class });
      methodObject27310 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setAutoClose", new Class[] { Boolean.TYPE });
      methodObject27425 = Connection.class.getDeclaredMethod("createNClob", new Class[0]);
      methodObject27367 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Time.class, Calendar.class });
      methodObject27366 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Date.class, Calendar.class });
      methodObject27284 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unwrap", new Class[0]);
      methodObject27343 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isProxySession", new Class[0]);
      methodObject27372 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Boolean.TYPE });
      methodObject27327 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStatementWithKey", new Class[] { String.class });
      methodObject27457 = Connection.class.getDeclaredMethod("setTransactionIsolation", new Class[] { Integer.TYPE });
      methodObject27389 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Time.class });
      methodObject27444 = Connection.class.getDeclaredMethod("getAutoCommit", new Class[0]);
      methodObject27448 = Connection.class.getDeclaredMethod("getTransactionIsolation", new Class[0]);
      methodObject27360 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Long.TYPE, String.class });
      methodObject27365 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Timestamp.class });
      methodObject27340 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isLogicalConnection", new Class[0]);
      methodObject27441 = Connection.class.getDeclaredMethod("getHoldability", new Class[0]);
      methodObject27420 = Connection.class.getDeclaredMethod("releaseSavepoint", new Class[] { Savepoint.class });
      methodObject27410 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setApplicationContext", new Class[] { String.class, String.class, String.class });
      methodObject27416 = Connection.class.getDeclaredMethod("rollback", new Class[0]);
      methodObject27314 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setIncludeSynonyms", new Class[] { Boolean.TYPE });
      methodObject27290 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("putDescriptor", new Class[] { String.class, Object.class });
      methodObject27426 = Connection.class.getDeclaredMethod("createSQLXML", new Class[0]);
      methodObject27357 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { DatabaseChangeRegistration.class });
      methodObject27298 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getIncludeSynonyms", new Class[0]);
      methodObject27405 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEncryptionAlgorithmName", new Class[0]);
      methodObject27401 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAllTypeDescriptorsInCurrentSchema", new Class[0]);
      methodObject27294 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultExecuteBatch", new Class[0]);
      methodObject27408 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultTimeZone", new Class[] { TimeZone.class });
      methodObject27400 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { DATE.class, Calendar.class });
      methodObject27319 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStatementCacheSize", new Class[] { Integer.TYPE });
      methodObject27392 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Timestamp.class, Calendar.class });
      methodObject27339 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSessionTimeZoneOffset", new Class[0]);
      methodObject27447 = Connection.class.getDeclaredMethod("getClientInfo", new Class[0]);
      methodObject27414 = Connection.class.getDeclaredMethod("isReadOnly", new Class[0]);
      methodObject27306 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUsingXAFlag", new Class[0]);
      methodObject27295 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDefaultRowPrefetch", new Class[0]);
      methodObject27350 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setPlsqlWarnings", new Class[] { String.class });
      methodObject27429 = Connection.class.getDeclaredMethod("createStatement", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27287 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("physicalConnectionWithin", new Class[0]);
      methodObject27459 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject27321 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setImplicitCachingEnabled", new Class[] { Boolean.TYPE });
      methodObject27440 = Connection.class.getDeclaredMethod("clearWarnings", new Class[0]);
      methodObject27386 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { String.class });
      methodObject27270 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDescriptor", new Class[] { String.class });
      methodObject27430 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class });
      methodObject27391 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Timestamp.class });
      methodObject27278 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleSetSavepoint", new Class[] { String.class });
      methodObject27333 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("prepareStatementWithKey", new Class[] { String.class });
      methodObject27434 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27355 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerDatabaseChangeNotification", new Class[] { Properties.class });
      methodObject27431 = Connection.class.getDeclaredMethod("prepareCall", new Class[] { String.class, Integer.TYPE, Integer.TYPE });
      methodObject27404 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getDataIntegrityAlgorithmName", new Class[0]);
      methodObject27359 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Integer.TYPE });
      methodObject27460 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject27427 = Connection.class.getDeclaredMethod("createStatement", new Class[0]);
      methodObject27456 = Connection.class.getDeclaredMethod("setHoldability", new Class[] { Integer.TYPE });
      methodObject27438 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, String[].class });
      methodObject27439 = Connection.class.getDeclaredMethod("getMetaData", new Class[0]);
      methodObject27297 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEndToEndECIDSequenceNumber", new Class[0]);
      methodObject27443 = Connection.class.getDeclaredMethod("isClosed", new Class[0]);
      methodObject27303 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStmtCacheSize", new Class[0]);
      methodObject27315 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setRemarksReporting", new Class[] { Boolean.TYPE });
      methodObject27402 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getTypeDescriptorsFromListInCurrentSchema", new Class[] { String[].class });
      methodObject27397 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { Time.class, Calendar.class });
      methodObject27289 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("pingDatabase", new Class[] { Integer.TYPE });
      methodObject27415 = Connection.class.getDeclaredMethod("commit", new Class[0]);
      methodObject27313 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setEndToEndMetrics", new Class[] { String[].class, Short.TYPE });
      methodObject27442 = Connection.class.getDeclaredMethod("getWarnings", new Class[0]);
      methodObject27374 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Short.TYPE });
      methodObject27334 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("prepareCallWithKey", new Class[] { String.class });
      methodObject27351 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerAQNotification", new Class[] { String[].class, Properties[].class, Properties.class });
      methodObject27458 = Connection.class.getDeclaredMethod("setTypeMap", new Class[] { Map.class });
      methodObject27371 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createINTERVALYM", new Class[] { String.class });
      methodObject27316 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setRestrictGetTables", new Class[] { Boolean.TYPE });
      methodObject27285 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("_getPC", new Class[0]);
      methodObject27411 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("clearAllApplicationContext", new Class[] { String.class });
      methodObject27453 = Connection.class.getDeclaredMethod("setCatalog", new Class[] { String.class });
      methodObject27390 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Time.class, Calendar.class });
      methodObject27361 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createBINARY_DOUBLE", new Class[] { Double.TYPE });
      methodObject27380 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { BigInteger.class });
      methodObject27353 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("dequeue", new Class[] { String.class, AQDequeueOptions.class, byte[].class });
      methodObject27413 = Connection.class.getDeclaredMethod("close", new Class[0]);
      methodObject27364 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Time.class });
      methodObject27304 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStructAttrCsId", new Class[0]);
      methodObject27348 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setConnectionReleasePriority", new Class[] { Integer.TYPE });
      methodObject27383 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { DATE.class });
      methodObject27299 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getRestrictGetTables", new Class[0]);
      methodObject27346 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getUnMatchedConnectionAttributes", new Class[0]);
      methodObject27417 = Connection.class.getDeclaredMethod("rollback", new Class[] { Savepoint.class });
      methodObject27329 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setUsingXAFlag", new Class[] { Boolean.TYPE });
      methodObject27363 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createDATE", new Class[] { Date.class });
      methodObject27424 = Connection.class.getDeclaredMethod("createClob", new Class[0]);
      methodObject27419 = Connection.class.getDeclaredMethod("setSavepoint", new Class[] { String.class });
      methodObject27433 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class });
      methodObject27280 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("abort", new Class[0]);
      methodObject27322 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getImplicitCachingEnabled", new Class[0]);
      methodObject27326 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("purgeExplicitCache", new Class[0]);
      methodObject27388 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Date.class, Calendar.class });
      methodObject27381 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { String.class, Integer.TYPE });
      methodObject27325 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("purgeImplicitCache", new Class[0]);
      methodObject27406 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getAuthenticationAdaptorName", new Class[0]);
      methodObject27399 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPLTZ", new Class[] { String.class, Calendar.class });
      methodObject27318 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStmtCacheSize", new Class[] { Integer.TYPE, Boolean.TYPE });
      methodObject27362 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createBINARY_FLOAT", new Class[] { Float.TYPE });
      methodObject27455 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { Properties.class });
      methodObject27345 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getConnectionAttributes", new Class[0]);
      methodObject27317 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setStmtCacheSize", new Class[] { Integer.TYPE });
      methodObject27376 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Long.TYPE });
      methodObject27335 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setCreateStatementAsRefCursor", new Class[] { Boolean.TYPE });
      methodObject27271 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getProperties", new Class[0]);
      methodObject27445 = Connection.class.getDeclaredMethod("getCatalog", new Class[0]);
      methodObject27344 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("applyConnectionAttributes", new Class[] { Properties.class });
      methodObject27378 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Double.TYPE });
      methodObject27312 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setDefaultRowPrefetch", new Class[] { Integer.TYPE });
      methodObject27403 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getTypeDescriptorsFromList", new Class[] { String[][].class });
      methodObject27342 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setWrapper", new Class[] { oracle.jdbc.OracleConnection.class });
      methodObject27324 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getExplicitCachingEnabled", new Class[0]);
      methodObject27421 = Connection.class.getDeclaredMethod("createArrayOf", new Class[] { String.class, Object[].class });
      methodObject27275 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("commit", new Class[] { EnumSet.class });
      methodObject27309 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerSQLType", new Class[] { String.class, String.class });
      methodObject27305 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCurrentSchema", new Class[0]);
      methodObject27300 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getJavaObject", new Class[] { String.class });
      methodObject27277 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleSetSavepoint", new Class[0]);
      methodObject27273 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("close", new Class[] { Properties.class });
      methodObject27336 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCreateStatementAsRefCursor", new Class[0]);
      methodObject27358 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterDatabaseChangeNotification", new Class[] { Integer.TYPE, String.class, Integer.TYPE });
      methodObject27328 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getCallWithKey", new Class[] { String.class });
      methodObject27435 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE });
      methodObject27308 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerSQLType", new Class[] { String.class, Class.class });
      methodObject27387 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { Date.class });
      methodObject27423 = Connection.class.getDeclaredMethod("createBlob", new Class[0]);
      methodObject27276 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("oracleRollback", new Class[] { OracleSavepoint.class });
      methodObject27451 = Connection.class.getDeclaredMethod("nativeSQL", new Class[] { String.class });
      methodObject27292 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("openProxySession", new Class[] { Integer.TYPE, Properties.class });
      methodObject27393 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { String.class });
      methodObject27379 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { BigDecimal.class });
      methodObject27437 = Connection.class.getDeclaredMethod("prepareStatement", new Class[] { String.class, int[].class });
      methodObject27394 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { String.class, Calendar.class });
      methodObject27422 = Connection.class.getDeclaredMethod("createStruct", new Class[] { String.class, Object[].class });
      methodObject27452 = Connection.class.getDeclaredMethod("setAutoCommit", new Class[] { Boolean.TYPE });
      methodObject27332 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("startup", new Class[] { OracleConnection.DatabaseStartupMode.class });
      methodObject27454 = Connection.class.getDeclaredMethod("setClientInfo", new Class[] { String.class, String.class });
      methodObject27283 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createOracleArray", new Class[] { String.class, Object.class });
      methodObject27450 = Connection.class.getDeclaredMethod("isValid", new Class[] { Integer.TYPE });
      methodObject27395 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMPTZ", new Class[] { DATE.class });
      methodObject27385 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createTIMESTAMP", new Class[] { Timestamp.class });
      methodObject27338 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getSessionTimeZone", new Class[0]);
      methodObject27347 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("registerConnectionCacheCallback", new Class[] { OracleConnectionCacheCallback.class, Object.class, Integer.TYPE });
      methodObject27407 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("isUsable", new Class[0]);
      methodObject27373 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Byte.TYPE });
      methodObject27320 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getStatementCacheSize", new Class[0]);
      methodObject27449 = Connection.class.getDeclaredMethod("getTypeMap", new Class[0]);
      methodObject27446 = Connection.class.getDeclaredMethod("getClientInfo", new Class[] { String.class });
      methodObject27330 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setXAErrorFlag", new Class[] { Boolean.TYPE });
      methodObject27281 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("cancel", new Class[0]);
      methodObject27377 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("createNUMBER", new Class[] { Float.TYPE });
      methodObject27296 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("getEndToEndMetrics", new Class[0]);
      methodObject27352 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("unregisterAQNotification", new Class[] { AQNotificationRegistration.class });
      methodObject27337 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("setSessionTimeZone", new Class[] { String.class });
      methodObject27272 = oracle.jdbc.OracleConnection.class.getDeclaredMethod("enqueue", new Class[] { String.class, AQEnqueueOptions.class, AQMessage.class });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1OracleConnection$$$Proxy(oracle.jdbc.OracleConnection paramOracleConnection, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramOracleConnection;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableConnection$2oracle$1jdbc$1OracleConnection$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */