package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.AdditionalDatabaseMetaData;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1AdditionalDatabaseMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements AdditionalDatabaseMetaData, _Proxy_
{
  private AdditionalDatabaseMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32384;
  private static Method methodObject32488;
  private static Method methodObject32458;
  private static Method methodObject32380;
  private static Method methodObject32374;
  private static Method methodObject32362;
  private static Method methodObject32479;
  private static Method methodObject32416;
  private static Method methodObject32381;
  private static Method methodObject32351;
  private static Method methodObject32468;
  private static Method methodObject32418;
  private static Method methodObject32442;
  private static Method methodObject32502;
  private static Method methodObject32400;
  private static Method methodObject32472;
  private static Method methodObject32433;
  private static Method methodObject32422;
  private static Method methodObject32519;
  private static Method methodObject32413;
  private static Method methodObject32429;
  private static Method methodObject32405;
  private static Method methodObject32376;
  private static Method methodObject32444;
  private static Method methodObject32391;
  private static Method methodObject32408;
  private static Method methodObject32415;
  private static Method methodObject32511;
  private static Method methodObject32518;
  private static Method methodObject32503;
  private static Method methodObject32491;
  private static Method methodObject32375;
  private static Method methodObject32355;
  private static Method methodObject32364;
  private static Method methodObject32370;
  private static Method methodObject32513;
  private static Method methodObject32420;
  private static Method methodObject32368;
  private static Method methodObject32393;
  private static Method methodObject32440;
  private static Method methodObject32443;
  private static Method methodObject32508;
  private static Method methodObject32501;
  private static Method methodObject32372;
  private static Method methodObject32419;
  private static Method methodObject32359;
  private static Method methodObject32428;
  private static Method methodObject32504;
  private static Method methodObject32477;
  private static Method methodObject32356;
  private static Method methodObject32496;
  private static Method methodObject32435;
  private static Method methodObject32363;
  private static Method methodObject32373;
  private static Method methodObject32347;
  private static Method methodObject32459;
  private static Method methodObject32432;
  private static Method methodObject32497;
  private static Method methodObject32386;
  private static Method methodObject32411;
  private static Method methodObject32449;
  private static Method methodObject32350;
  private static Method methodObject32469;
  private static Method methodObject32473;
  private static Method methodObject32403;
  private static Method methodObject32427;
  private static Method methodObject32404;
  private static Method methodObject32521;
  private static Method methodObject32516;
  private static Method methodObject32510;
  private static Method methodObject32439;
  private static Method methodObject32492;
  private static Method methodObject32520;
  private static Method methodObject32453;
  private static Method methodObject32410;
  private static Method methodObject32406;
  private static Method methodObject32367;
  private static Method methodObject32414;
  private static Method methodObject32401;
  private static Method methodObject32426;
  private static Method methodObject32522;
  private static Method methodObject32425;
  private static Method methodObject32500;
  private static Method methodObject32441;
  private static Method methodObject32495;
  private static Method methodObject32377;
  private static Method methodObject32431;
  private static Method methodObject32448;
  private static Method methodObject32481;
  private static Method methodObject32450;
  private static Method methodObject32465;
  private static Method methodObject32369;
  private static Method methodObject32399;
  private static Method methodObject32438;
  private static Method methodObject32486;
  private static Method methodObject32349;
  private static Method methodObject32430;
  private static Method methodObject32515;
  private static Method methodObject32514;
  private static Method methodObject32352;
  private static Method methodObject32378;
  private static Method methodObject32482;
  private static Method methodObject32446;
  private static Method methodObject32471;
  private static Method methodObject32463;
  private static Method methodObject32452;
  private static Method methodObject32507;
  private static Method methodObject32366;
  private static Method methodObject32396;
  private static Method methodObject32385;
  private static Method methodObject32487;
  private static Method methodObject32383;
  private static Method methodObject32478;
  private static Method methodObject32466;
  private static Method methodObject32409;
  private static Method methodObject32437;
  private static Method methodObject32499;
  private static Method methodObject32387;
  private static Method methodObject32456;
  private static Method methodObject32470;
  private static Method methodObject32498;
  private static Method methodObject32357;
  private static Method methodObject32493;
  private static Method methodObject32421;
  private static Method methodObject32436;
  private static Method methodObject32517;
  private static Method methodObject32353;
  private static Method methodObject32348;
  private static Method methodObject32457;
  private static Method methodObject32382;
  private static Method methodObject32412;
  private static Method methodObject32371;
  private static Method methodObject32398;
  private static Method methodObject32447;
  private static Method methodObject32462;
  private static Method methodObject32455;
  private static Method methodObject32485;
  private static Method methodObject32474;
  private static Method methodObject32394;
  private static Method methodObject32392;
  private static Method methodObject32395;
  private static Method methodObject32354;
  private static Method methodObject32424;
  private static Method methodObject32434;
  private static Method methodObject32509;
  private static Method methodObject32397;
  private static Method methodObject32512;
  private static Method methodObject32490;
  private static Method methodObject32360;
  private static Method methodObject32407;
  private static Method methodObject32423;
  private static Method methodObject32461;
  private static Method methodObject32475;
  private static Method methodObject32464;
  private static Method methodObject32506;
  private static Method methodObject32467;
  private static Method methodObject32483;
  private static Method methodObject32390;
  private static Method methodObject32389;
  private static Method methodObject32402;
  private static Method methodObject32454;
  private static Method methodObject32476;
  private static Method methodObject32451;
  private static Method methodObject32480;
  private static Method methodObject32388;
  private static Method methodObject32379;
  private static Method methodObject32365;
  private static Method methodObject32445;
  private static Method methodObject32358;
  private static Method methodObject32484;
  private static Method methodObject32489;
  private static Method methodObject32505;
  private static Method methodObject32494;
  private static Method methodObject32460;
  private static Method methodObject32417;
  private static Method methodObject32361;
  
  public int getJDBCMajorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32384, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32384, Integer.valueOf(this.delegate.getJDBCMajorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32384, onErrorForAll(methodObject32384, e))).intValue();
    }
  }
  
  public boolean supportsOpenCursorsAcrossCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32488, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32488, Boolean.valueOf(this.delegate.supportsOpenCursorsAcrossCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32488, onErrorForAll(methodObject32488, e))).booleanValue();
    }
  }
  
  public boolean supportsCatalogsInIndexDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32458, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32458, Boolean.valueOf(this.delegate.supportsCatalogsInIndexDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32458, onErrorForAll(methodObject32458, e))).booleanValue();
    }
  }
  
  public ResultSet getFunctions(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32380, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32380, this.proxyFactory.proxyForCache((Object)this.delegate.getFunctions(arg0, arg1, arg2), this, this.proxyCache, methodObject32380));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32380, onErrorForAll(methodObject32380, e));
    }
  }
  
  public int getDriverMinorVersion()
  {
    super.preForAll(methodObject32374, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject32374, Integer.valueOf(this.delegate.getDriverMinorVersion()))).intValue();
  }
  
  public String getCatalogTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32362, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32362, (Object)this.delegate.getCatalogTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32362, onErrorForAll(methodObject32362, e));
    }
  }
  
  public boolean supportsLimitedOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32479, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32479, Boolean.valueOf(this.delegate.supportsLimitedOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32479, onErrorForAll(methodObject32479, e))).booleanValue();
    }
  }
  
  public ResultSet getSchemas()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32416, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32416, this.proxyFactory.proxyForCache((Object)this.delegate.getSchemas(), this, this.proxyCache, methodObject32416));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32416, onErrorForAll(methodObject32416, e));
    }
  }
  
  public String getIdentifierQuoteString()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32381, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32381, (Object)this.delegate.getIdentifierQuoteString());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32381, onErrorForAll(methodObject32381, e));
    }
  }
  
  public ResultSet getAttributes(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32351, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32351, this.proxyFactory.proxyForCache((Object)this.delegate.getAttributes(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32351));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32351, onErrorForAll(methodObject32351, e));
    }
  }
  
  public boolean supportsDataManipulationTransactionsOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32468, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32468, Boolean.valueOf(this.delegate.supportsDataManipulationTransactionsOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32468, onErrorForAll(methodObject32468, e))).booleanValue();
    }
  }
  
  public String getSearchStringEscape()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32418, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32418, (Object)this.delegate.getSearchStringEscape());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32418, onErrorForAll(methodObject32418, e));
    }
  }
  
  public boolean ownDeletesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32442, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32442, Boolean.valueOf(this.delegate.ownDeletesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32442, onErrorForAll(methodObject32442, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInPrivilegeDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32502, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32502, Boolean.valueOf(this.delegate.supportsSchemasInPrivilegeDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32502, onErrorForAll(methodObject32502, e))).booleanValue();
    }
  }
  
  public int getMaxSchemaNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32400, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32400, Integer.valueOf(this.delegate.getMaxSchemaNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32400, onErrorForAll(methodObject32400, e))).intValue();
    }
  }
  
  public boolean supportsFullOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32472, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32472, Boolean.valueOf(this.delegate.supportsFullOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32472, onErrorForAll(methodObject32472, e))).booleanValue();
    }
  }
  
  public boolean locatorsUpdateCopy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32433, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32433, Boolean.valueOf(this.delegate.locatorsUpdateCopy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32433, onErrorForAll(methodObject32433, e))).booleanValue();
    }
  }
  
  public String getSystemFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32422, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32422, (Object)this.delegate.getSystemFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32422, onErrorForAll(methodObject32422, e));
    }
  }
  
  public boolean usesLocalFilePerTable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32519, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32519, Boolean.valueOf(this.delegate.usesLocalFilePerTable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32519, onErrorForAll(methodObject32519, e))).booleanValue();
    }
  }
  
  public String getSQLKeywords()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32413, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32413, (Object)this.delegate.getSQLKeywords());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32413, onErrorForAll(methodObject32413, e));
    }
  }
  
  public String getUserName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32429, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32429, (Object)this.delegate.getUserName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32429, onErrorForAll(methodObject32429, e));
    }
  }
  
  public int getMaxUserNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32405, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32405, Integer.valueOf(this.delegate.getMaxUserNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32405, onErrorForAll(methodObject32405, e))).intValue();
    }
  }
  
  public String getDriverVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32376, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32376, (Object)this.delegate.getDriverVersion());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32376, onErrorForAll(methodObject32376, e));
    }
  }
  
  public boolean ownUpdatesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32444, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32444, Boolean.valueOf(this.delegate.ownUpdatesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32444, onErrorForAll(methodObject32444, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInIndex()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32391, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32391, Integer.valueOf(this.delegate.getMaxColumnsInIndex()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32391, onErrorForAll(methodObject32391, e))).intValue();
    }
  }
  
  public ResultSet getProcedureColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32408, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32408, this.proxyFactory.proxyForCache((Object)this.delegate.getProcedureColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32408));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32408, onErrorForAll(methodObject32408, e));
    }
  }
  
  public String getSchemaTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32415, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32415, (Object)this.delegate.getSchemaTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32415, onErrorForAll(methodObject32415, e));
    }
  }
  
  public boolean supportsSubqueriesInIns()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32511, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32511, Boolean.valueOf(this.delegate.supportsSubqueriesInIns()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32511, onErrorForAll(methodObject32511, e))).booleanValue();
    }
  }
  
  public boolean updatesAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32518, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32518, Boolean.valueOf(this.delegate.updatesAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32518, onErrorForAll(methodObject32518, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInProcedureCalls()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32503, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32503, Boolean.valueOf(this.delegate.supportsSchemasInProcedureCalls()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32503, onErrorForAll(methodObject32503, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenStatementsAcrossRollback()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32491, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32491, Boolean.valueOf(this.delegate.supportsOpenStatementsAcrossRollback()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32491, onErrorForAll(methodObject32491, e))).booleanValue();
    }
  }
  
  public String getDriverName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32375, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32375, (Object)this.delegate.getDriverName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32375, onErrorForAll(methodObject32375, e));
    }
  }
  
  public boolean autoCommitFailureClosesAllResultSets()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32355, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32355, Boolean.valueOf(this.delegate.autoCommitFailureClosesAllResultSets()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32355, onErrorForAll(methodObject32355, e))).booleanValue();
    }
  }
  
  public ResultSet getClientInfoProperties()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32364, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32364, this.proxyFactory.proxyForCache((Object)this.delegate.getClientInfoProperties(), this, this.proxyCache, methodObject32364));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32364, onErrorForAll(methodObject32364, e));
    }
  }
  
  public String getDatabaseProductName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32370, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32370, (Object)this.delegate.getDatabaseProductName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32370, onErrorForAll(methodObject32370, e));
    }
  }
  
  public boolean supportsTableCorrelationNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32513, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32513, Boolean.valueOf(this.delegate.supportsTableCorrelationNames()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32513, onErrorForAll(methodObject32513, e))).booleanValue();
    }
  }
  
  public ResultSet getSuperTables(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32420, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32420, this.proxyFactory.proxyForCache((Object)this.delegate.getSuperTables(arg0, arg1, arg2), this, this.proxyCache, methodObject32420));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32420, onErrorForAll(methodObject32420, e));
    }
  }
  
  public int getDatabaseMajorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32368, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32368, Integer.valueOf(this.delegate.getDatabaseMajorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32368, onErrorForAll(methodObject32368, e))).intValue();
    }
  }
  
  public int getMaxColumnsInSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32393, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32393, Integer.valueOf(this.delegate.getMaxColumnsInSelect()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32393, onErrorForAll(methodObject32393, e))).intValue();
    }
  }
  
  public boolean othersInsertsAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32440, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32440, Boolean.valueOf(this.delegate.othersInsertsAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32440, onErrorForAll(methodObject32440, e))).booleanValue();
    }
  }
  
  public boolean ownInsertsAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32443, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32443, Boolean.valueOf(this.delegate.ownInsertsAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32443, onErrorForAll(methodObject32443, e))).booleanValue();
    }
  }
  
  public boolean supportsStoredProcedures()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32508, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32508, Boolean.valueOf(this.delegate.supportsStoredProcedures()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32508, onErrorForAll(methodObject32508, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInIndexDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32501, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32501, Boolean.valueOf(this.delegate.supportsSchemasInIndexDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32501, onErrorForAll(methodObject32501, e))).booleanValue();
    }
  }
  
  public int getDefaultTransactionIsolation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32372, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32372, Integer.valueOf(this.delegate.getDefaultTransactionIsolation()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32372, onErrorForAll(methodObject32372, e))).intValue();
    }
  }
  
  public String getStringFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32419, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32419, (Object)this.delegate.getStringFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32419, onErrorForAll(methodObject32419, e));
    }
  }
  
  public boolean doesMaxRowSizeIncludeBlobs()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32359, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32359, Boolean.valueOf(this.delegate.doesMaxRowSizeIncludeBlobs()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32359, onErrorForAll(methodObject32359, e))).booleanValue();
    }
  }
  
  public ResultSet getUDTs(String arg0, String arg1, String arg2, int[] arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32428, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32428, this.proxyFactory.proxyForCache((Object)this.delegate.getUDTs(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32428));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32428, onErrorForAll(methodObject32428, e));
    }
  }
  
  public boolean supportsSchemasInTableDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32504, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32504, Boolean.valueOf(this.delegate.supportsSchemasInTableDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32504, onErrorForAll(methodObject32504, e))).booleanValue();
    }
  }
  
  public boolean supportsIntegrityEnhancementFacility()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32477, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32477, Boolean.valueOf(this.delegate.supportsIntegrityEnhancementFacility()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32477, onErrorForAll(methodObject32477, e))).booleanValue();
    }
  }
  
  public boolean dataDefinitionCausesTransactionCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32356, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32356, Boolean.valueOf(this.delegate.dataDefinitionCausesTransactionCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32356, onErrorForAll(methodObject32356, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetConcurrency(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32496, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return ((Boolean)postForAll(methodObject32496, Boolean.valueOf(this.delegate.supportsResultSetConcurrency(arg0, arg1)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32496, onErrorForAll(methodObject32496, e))).booleanValue();
    }
  }
  
  public boolean nullsAreSortedAtEnd()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32435, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32435, Boolean.valueOf(this.delegate.nullsAreSortedAtEnd()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32435, onErrorForAll(methodObject32435, e))).booleanValue();
    }
  }
  
  public ResultSet getCatalogs()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32363, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32363, this.proxyFactory.proxyForCache((Object)this.delegate.getCatalogs(), this, this.proxyCache, methodObject32363));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32363, onErrorForAll(methodObject32363, e));
    }
  }
  
  public int getDriverMajorVersion()
  {
    super.preForAll(methodObject32373, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject32373, Integer.valueOf(this.delegate.getDriverMajorVersion()))).intValue();
  }
  
  public OracleTypeMetaData getOracleTypeMetaData(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32347, this, new Object[] { arg0 });
      return (OracleTypeMetaData)postForAll(methodObject32347, this.proxyFactory.proxyForCache((Object)this.delegate.getOracleTypeMetaData(arg0), this, this.proxyCache, methodObject32347));
    }
    catch (SQLException e)
    {
      return (OracleTypeMetaData)postForAll(methodObject32347, onErrorForAll(methodObject32347, e));
    }
  }
  
  public boolean supportsCatalogsInPrivilegeDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32459, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32459, Boolean.valueOf(this.delegate.supportsCatalogsInPrivilegeDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32459, onErrorForAll(methodObject32459, e))).booleanValue();
    }
  }
  
  public boolean isCatalogAtStart()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32432, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32432, Boolean.valueOf(this.delegate.isCatalogAtStart()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32432, onErrorForAll(methodObject32432, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetHoldability(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32497, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32497, Boolean.valueOf(this.delegate.supportsResultSetHoldability(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32497, onErrorForAll(methodObject32497, e))).booleanValue();
    }
  }
  
  public int getMaxBinaryLiteralLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32386, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32386, Integer.valueOf(this.delegate.getMaxBinaryLiteralLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32386, onErrorForAll(methodObject32386, e))).intValue();
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32411, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32411, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32411, onErrorForAll(methodObject32411, e))).intValue();
    }
  }
  
  public boolean storesUpperCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32449, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32449, Boolean.valueOf(this.delegate.storesUpperCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32449, onErrorForAll(methodObject32449, e))).booleanValue();
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32350, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32350, Boolean.valueOf(this.delegate.isReadOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32350, onErrorForAll(methodObject32350, e))).booleanValue();
    }
  }
  
  public boolean supportsDifferentTableCorrelationNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32469, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32469, Boolean.valueOf(this.delegate.supportsDifferentTableCorrelationNames()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32469, onErrorForAll(methodObject32469, e))).booleanValue();
    }
  }
  
  public boolean supportsGetGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32473, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32473, Boolean.valueOf(this.delegate.supportsGetGeneratedKeys()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32473, onErrorForAll(methodObject32473, e))).booleanValue();
    }
  }
  
  public int getMaxTableNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32403, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32403, Integer.valueOf(this.delegate.getMaxTableNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32403, onErrorForAll(methodObject32403, e))).intValue();
    }
  }
  
  public ResultSet getTypeInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32427, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32427, this.proxyFactory.proxyForCache((Object)this.delegate.getTypeInfo(), this, this.proxyCache, methodObject32427));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32427, onErrorForAll(methodObject32427, e));
    }
  }
  
  public int getMaxTablesInSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32404, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32404, Integer.valueOf(this.delegate.getMaxTablesInSelect()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32404, onErrorForAll(methodObject32404, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32521, this, new Object[] { arg0 });
      return postForAll(methodObject32521, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32521));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32521, onErrorForAll(methodObject32521, e));
    }
  }
  
  public boolean supportsUnion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32516, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32516, Boolean.valueOf(this.delegate.supportsUnion()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32516, onErrorForAll(methodObject32516, e))).booleanValue();
    }
  }
  
  public boolean supportsSubqueriesInExists()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32510, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32510, Boolean.valueOf(this.delegate.supportsSubqueriesInExists()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32510, onErrorForAll(methodObject32510, e))).booleanValue();
    }
  }
  
  public boolean othersDeletesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32439, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32439, Boolean.valueOf(this.delegate.othersDeletesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32439, onErrorForAll(methodObject32439, e))).booleanValue();
    }
  }
  
  public boolean supportsOrderByUnrelated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32492, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32492, Boolean.valueOf(this.delegate.supportsOrderByUnrelated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32492, onErrorForAll(methodObject32492, e))).booleanValue();
    }
  }
  
  public boolean usesLocalFiles()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32520, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32520, Boolean.valueOf(this.delegate.usesLocalFiles()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32520, onErrorForAll(methodObject32520, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92IntermediateSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32453, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32453, Boolean.valueOf(this.delegate.supportsANSI92IntermediateSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32453, onErrorForAll(methodObject32453, e))).booleanValue();
    }
  }
  
  public ResultSet getProcedures(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32410, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32410, this.proxyFactory.proxyForCache((Object)this.delegate.getProcedures(arg0, arg1, arg2), this, this.proxyCache, methodObject32410));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32410, onErrorForAll(methodObject32410, e));
    }
  }
  
  public String getNumericFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32406, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32406, (Object)this.delegate.getNumericFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32406, onErrorForAll(methodObject32406, e));
    }
  }
  
  public ResultSet getCrossReference(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32367, this, new Object[] { arg0, arg1, arg2, arg3, arg4, arg5 });
      return (ResultSet)postForAll(methodObject32367, this.proxyFactory.proxyForCache((Object)this.delegate.getCrossReference(arg0, arg1, arg2, arg3, arg4, arg5), this, this.proxyCache, methodObject32367));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32367, onErrorForAll(methodObject32367, e));
    }
  }
  
  public int getSQLStateType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32414, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32414, Integer.valueOf(this.delegate.getSQLStateType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32414, onErrorForAll(methodObject32414, e))).intValue();
    }
  }
  
  public int getMaxStatementLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32401, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32401, Integer.valueOf(this.delegate.getMaxStatementLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32401, onErrorForAll(methodObject32401, e))).intValue();
    }
  }
  
  public String getTimeDateFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32426, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32426, (Object)this.delegate.getTimeDateFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32426, onErrorForAll(methodObject32426, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32522, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32522, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32522, onErrorForAll(methodObject32522, e))).booleanValue();
    }
  }
  
  public ResultSet getTables(String arg0, String arg1, String arg2, String[] arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32425, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32425, this.proxyFactory.proxyForCache((Object)this.delegate.getTables(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32425));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32425, onErrorForAll(methodObject32425, e));
    }
  }
  
  public boolean supportsSchemasInDataManipulation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32500, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32500, Boolean.valueOf(this.delegate.supportsSchemasInDataManipulation()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32500, onErrorForAll(methodObject32500, e))).booleanValue();
    }
  }
  
  public boolean othersUpdatesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32441, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32441, Boolean.valueOf(this.delegate.othersUpdatesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32441, onErrorForAll(methodObject32441, e))).booleanValue();
    }
  }
  
  public boolean supportsPositionedUpdate()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32495, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32495, Boolean.valueOf(this.delegate.supportsPositionedUpdate()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32495, onErrorForAll(methodObject32495, e))).booleanValue();
    }
  }
  
  public ResultSet getExportedKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32377, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32377, this.proxyFactory.proxyForCache((Object)this.delegate.getExportedKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32377));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32377, onErrorForAll(methodObject32377, e));
    }
  }
  
  public boolean insertsAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32431, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32431, Boolean.valueOf(this.delegate.insertsAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32431, onErrorForAll(methodObject32431, e))).booleanValue();
    }
  }
  
  public boolean storesMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32448, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32448, Boolean.valueOf(this.delegate.storesMixedCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32448, onErrorForAll(methodObject32448, e))).booleanValue();
    }
  }
  
  public boolean supportsMixedCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32481, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32481, Boolean.valueOf(this.delegate.supportsMixedCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32481, onErrorForAll(methodObject32481, e))).booleanValue();
    }
  }
  
  public boolean storesUpperCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32450, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32450, Boolean.valueOf(this.delegate.storesUpperCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32450, onErrorForAll(methodObject32450, e))).booleanValue();
    }
  }
  
  public boolean supportsCoreSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32465, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32465, Boolean.valueOf(this.delegate.supportsCoreSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32465, onErrorForAll(methodObject32465, e))).booleanValue();
    }
  }
  
  public int getDatabaseMinorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32369, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32369, Integer.valueOf(this.delegate.getDatabaseMinorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32369, onErrorForAll(methodObject32369, e))).intValue();
    }
  }
  
  public int getMaxRowSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32399, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32399, Integer.valueOf(this.delegate.getMaxRowSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32399, onErrorForAll(methodObject32399, e))).intValue();
    }
  }
  
  public boolean nullsAreSortedLow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32438, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32438, Boolean.valueOf(this.delegate.nullsAreSortedLow()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32438, onErrorForAll(methodObject32438, e))).booleanValue();
    }
  }
  
  public boolean supportsNamedParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32486, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32486, Boolean.valueOf(this.delegate.supportsNamedParameters()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32486, onErrorForAll(methodObject32486, e))).booleanValue();
    }
  }
  
  public String getURL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32349, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32349, (Object)this.delegate.getURL());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32349, onErrorForAll(methodObject32349, e));
    }
  }
  
  public ResultSet getVersionColumns(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32430, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32430, this.proxyFactory.proxyForCache((Object)this.delegate.getVersionColumns(arg0, arg1, arg2), this, this.proxyCache, methodObject32430));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32430, onErrorForAll(methodObject32430, e));
    }
  }
  
  public boolean supportsTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32515, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32515, Boolean.valueOf(this.delegate.supportsTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32515, onErrorForAll(methodObject32515, e))).booleanValue();
    }
  }
  
  public boolean supportsTransactionIsolationLevel(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32514, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32514, Boolean.valueOf(this.delegate.supportsTransactionIsolationLevel(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32514, onErrorForAll(methodObject32514, e))).booleanValue();
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32352, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject32352, this.proxyFactory.proxyForCache((Object)this.delegate.getConnection(), this, this.proxyCache, methodObject32352));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject32352, onErrorForAll(methodObject32352, e));
    }
  }
  
  public String getExtraNameCharacters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32378, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32378, (Object)this.delegate.getExtraNameCharacters());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32378, onErrorForAll(methodObject32378, e));
    }
  }
  
  public boolean supportsMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32482, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32482, Boolean.valueOf(this.delegate.supportsMixedCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32482, onErrorForAll(methodObject32482, e))).booleanValue();
    }
  }
  
  public boolean storesLowerCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32446, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32446, Boolean.valueOf(this.delegate.storesLowerCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32446, onErrorForAll(methodObject32446, e))).booleanValue();
    }
  }
  
  public boolean supportsExtendedSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32471, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32471, Boolean.valueOf(this.delegate.supportsExtendedSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32471, onErrorForAll(methodObject32471, e))).booleanValue();
    }
  }
  
  public boolean supportsConvert()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32463, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32463, Boolean.valueOf(this.delegate.supportsConvert()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32463, onErrorForAll(methodObject32463, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92FullSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32452, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32452, Boolean.valueOf(this.delegate.supportsANSI92FullSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32452, onErrorForAll(methodObject32452, e))).booleanValue();
    }
  }
  
  public boolean supportsStoredFunctionsUsingCallSyntax()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32507, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32507, Boolean.valueOf(this.delegate.supportsStoredFunctionsUsingCallSyntax()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32507, onErrorForAll(methodObject32507, e))).booleanValue();
    }
  }
  
  public ResultSet getColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32366, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32366, this.proxyFactory.proxyForCache((Object)this.delegate.getColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32366));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32366, onErrorForAll(methodObject32366, e));
    }
  }
  
  public int getMaxCursorNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32396, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32396, Integer.valueOf(this.delegate.getMaxCursorNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32396, onErrorForAll(methodObject32396, e))).intValue();
    }
  }
  
  public int getJDBCMinorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32385, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32385, Integer.valueOf(this.delegate.getJDBCMinorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32385, onErrorForAll(methodObject32385, e))).intValue();
    }
  }
  
  public boolean supportsNonNullableColumns()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32487, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32487, Boolean.valueOf(this.delegate.supportsNonNullableColumns()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32487, onErrorForAll(methodObject32487, e))).booleanValue();
    }
  }
  
  public ResultSet getIndexInfo(String arg0, String arg1, String arg2, boolean arg3, boolean arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32383, this, new Object[] { arg0, arg1, arg2, Boolean.valueOf(arg3), Boolean.valueOf(arg4) });
      return (ResultSet)postForAll(methodObject32383, this.proxyFactory.proxyForCache((Object)this.delegate.getIndexInfo(arg0, arg1, arg2, arg3, arg4), this, this.proxyCache, methodObject32383));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32383, onErrorForAll(methodObject32383, e));
    }
  }
  
  public boolean supportsLikeEscapeClause()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32478, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32478, Boolean.valueOf(this.delegate.supportsLikeEscapeClause()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32478, onErrorForAll(methodObject32478, e))).booleanValue();
    }
  }
  
  public boolean supportsCorrelatedSubqueries()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32466, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32466, Boolean.valueOf(this.delegate.supportsCorrelatedSubqueries()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32466, onErrorForAll(methodObject32466, e))).booleanValue();
    }
  }
  
  public String getProcedureTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32409, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32409, (Object)this.delegate.getProcedureTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32409, onErrorForAll(methodObject32409, e));
    }
  }
  
  public boolean nullsAreSortedHigh()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32437, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32437, Boolean.valueOf(this.delegate.nullsAreSortedHigh()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32437, onErrorForAll(methodObject32437, e))).booleanValue();
    }
  }
  
  public boolean supportsSavepoints()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32499, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32499, Boolean.valueOf(this.delegate.supportsSavepoints()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32499, onErrorForAll(methodObject32499, e))).booleanValue();
    }
  }
  
  public int getMaxCatalogNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32387, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32387, Integer.valueOf(this.delegate.getMaxCatalogNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32387, onErrorForAll(methodObject32387, e))).intValue();
    }
  }
  
  public boolean supportsBatchUpdates()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32456, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32456, Boolean.valueOf(this.delegate.supportsBatchUpdates()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32456, onErrorForAll(methodObject32456, e))).booleanValue();
    }
  }
  
  public boolean supportsExpressionsInOrderBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32470, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32470, Boolean.valueOf(this.delegate.supportsExpressionsInOrderBy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32470, onErrorForAll(methodObject32470, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32498, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32498, Boolean.valueOf(this.delegate.supportsResultSetType(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32498, onErrorForAll(methodObject32498, e))).booleanValue();
    }
  }
  
  public boolean dataDefinitionIgnoredInTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32357, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32357, Boolean.valueOf(this.delegate.dataDefinitionIgnoredInTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32357, onErrorForAll(methodObject32357, e))).booleanValue();
    }
  }
  
  public boolean supportsOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32493, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32493, Boolean.valueOf(this.delegate.supportsOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32493, onErrorForAll(methodObject32493, e))).booleanValue();
    }
  }
  
  public ResultSet getSuperTypes(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32421, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32421, this.proxyFactory.proxyForCache((Object)this.delegate.getSuperTypes(arg0, arg1, arg2), this, this.proxyCache, methodObject32421));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32421, onErrorForAll(methodObject32421, e));
    }
  }
  
  public boolean nullsAreSortedAtStart()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32436, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32436, Boolean.valueOf(this.delegate.nullsAreSortedAtStart()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32436, onErrorForAll(methodObject32436, e))).booleanValue();
    }
  }
  
  public boolean supportsUnionAll()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32517, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32517, Boolean.valueOf(this.delegate.supportsUnionAll()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32517, onErrorForAll(methodObject32517, e))).booleanValue();
    }
  }
  
  public boolean allProceduresAreCallable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32353, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32353, Boolean.valueOf(this.delegate.allProceduresAreCallable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32353, onErrorForAll(methodObject32353, e))).booleanValue();
    }
  }
  
  public long getLobMaxLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32348, this, zeroLengthObjectArray);
      return ((Long)postForAll(methodObject32348, Long.valueOf(this.delegate.getLobMaxLength()))).longValue();
    }
    catch (SQLException e)
    {
      return ((Long)postForAll(methodObject32348, onErrorForAll(methodObject32348, e))).longValue();
    }
  }
  
  public boolean supportsCatalogsInDataManipulation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32457, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32457, Boolean.valueOf(this.delegate.supportsCatalogsInDataManipulation()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32457, onErrorForAll(methodObject32457, e))).booleanValue();
    }
  }
  
  public ResultSet getImportedKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32382, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32382, this.proxyFactory.proxyForCache((Object)this.delegate.getImportedKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32382));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32382, onErrorForAll(methodObject32382, e));
    }
  }
  
  public RowIdLifetime getRowIdLifetime()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32412, this, zeroLengthObjectArray);
      return (RowIdLifetime)postForAll(methodObject32412, (Object)this.delegate.getRowIdLifetime());
    }
    catch (SQLException e)
    {
      return (RowIdLifetime)postForAll(methodObject32412, onErrorForAll(methodObject32412, e));
    }
  }
  
  public String getDatabaseProductVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32371, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32371, (Object)this.delegate.getDatabaseProductVersion());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32371, onErrorForAll(methodObject32371, e));
    }
  }
  
  public int getMaxProcedureNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32398, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32398, Integer.valueOf(this.delegate.getMaxProcedureNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32398, onErrorForAll(methodObject32398, e))).intValue();
    }
  }
  
  public boolean storesMixedCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32447, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32447, Boolean.valueOf(this.delegate.storesMixedCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32447, onErrorForAll(methodObject32447, e))).booleanValue();
    }
  }
  
  public boolean supportsColumnAliasing()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32462, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32462, Boolean.valueOf(this.delegate.supportsColumnAliasing()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32462, onErrorForAll(methodObject32462, e))).booleanValue();
    }
  }
  
  public boolean supportsAlterTableWithDropColumn()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32455, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32455, Boolean.valueOf(this.delegate.supportsAlterTableWithDropColumn()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32455, onErrorForAll(methodObject32455, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32485, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32485, Boolean.valueOf(this.delegate.supportsMultipleTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32485, onErrorForAll(methodObject32485, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32474, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32474, Boolean.valueOf(this.delegate.supportsGroupBy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32474, onErrorForAll(methodObject32474, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInTable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32394, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32394, Integer.valueOf(this.delegate.getMaxColumnsInTable()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32394, onErrorForAll(methodObject32394, e))).intValue();
    }
  }
  
  public int getMaxColumnsInOrderBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32392, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32392, Integer.valueOf(this.delegate.getMaxColumnsInOrderBy()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32392, onErrorForAll(methodObject32392, e))).intValue();
    }
  }
  
  public int getMaxConnections()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32395, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32395, Integer.valueOf(this.delegate.getMaxConnections()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32395, onErrorForAll(methodObject32395, e))).intValue();
    }
  }
  
  public boolean allTablesAreSelectable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32354, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32354, Boolean.valueOf(this.delegate.allTablesAreSelectable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32354, onErrorForAll(methodObject32354, e))).booleanValue();
    }
  }
  
  public ResultSet getTableTypes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32424, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32424, this.proxyFactory.proxyForCache((Object)this.delegate.getTableTypes(), this, this.proxyCache, methodObject32424));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32424, onErrorForAll(methodObject32424, e));
    }
  }
  
  public boolean nullPlusNonNullIsNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32434, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32434, Boolean.valueOf(this.delegate.nullPlusNonNullIsNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32434, onErrorForAll(methodObject32434, e))).booleanValue();
    }
  }
  
  public boolean supportsSubqueriesInComparisons()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32509, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32509, Boolean.valueOf(this.delegate.supportsSubqueriesInComparisons()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32509, onErrorForAll(methodObject32509, e))).booleanValue();
    }
  }
  
  public int getMaxIndexLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32397, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32397, Integer.valueOf(this.delegate.getMaxIndexLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32397, onErrorForAll(methodObject32397, e))).intValue();
    }
  }
  
  public boolean supportsSubqueriesInQuantifieds()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32512, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32512, Boolean.valueOf(this.delegate.supportsSubqueriesInQuantifieds()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32512, onErrorForAll(methodObject32512, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenStatementsAcrossCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32490, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32490, Boolean.valueOf(this.delegate.supportsOpenStatementsAcrossCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32490, onErrorForAll(methodObject32490, e))).booleanValue();
    }
  }
  
  public ResultSet getBestRowIdentifier(String arg0, String arg1, String arg2, int arg3, boolean arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32360, this, new Object[] { arg0, arg1, arg2, Integer.valueOf(arg3), Boolean.valueOf(arg4) });
      return (ResultSet)postForAll(methodObject32360, this.proxyFactory.proxyForCache((Object)this.delegate.getBestRowIdentifier(arg0, arg1, arg2, arg3, arg4), this, this.proxyCache, methodObject32360));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32360, onErrorForAll(methodObject32360, e));
    }
  }
  
  public ResultSet getPrimaryKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32407, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32407, this.proxyFactory.proxyForCache((Object)this.delegate.getPrimaryKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32407));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32407, onErrorForAll(methodObject32407, e));
    }
  }
  
  public ResultSet getTablePrivileges(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32423, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32423, this.proxyFactory.proxyForCache((Object)this.delegate.getTablePrivileges(arg0, arg1, arg2), this, this.proxyCache, methodObject32423));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32423, onErrorForAll(methodObject32423, e));
    }
  }
  
  public boolean supportsCatalogsInTableDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32461, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32461, Boolean.valueOf(this.delegate.supportsCatalogsInTableDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32461, onErrorForAll(methodObject32461, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupByBeyondSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32475, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32475, Boolean.valueOf(this.delegate.supportsGroupByBeyondSelect()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32475, onErrorForAll(methodObject32475, e))).booleanValue();
    }
  }
  
  public boolean supportsConvert(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32464, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return ((Boolean)postForAll(methodObject32464, Boolean.valueOf(this.delegate.supportsConvert(arg0, arg1)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32464, onErrorForAll(methodObject32464, e))).booleanValue();
    }
  }
  
  public boolean supportsStatementPooling()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32506, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32506, Boolean.valueOf(this.delegate.supportsStatementPooling()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32506, onErrorForAll(methodObject32506, e))).booleanValue();
    }
  }
  
  public boolean supportsDataDefinitionAndDataManipulationTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32467, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32467, Boolean.valueOf(this.delegate.supportsDataDefinitionAndDataManipulationTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32467, onErrorForAll(methodObject32467, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleOpenResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32483, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32483, Boolean.valueOf(this.delegate.supportsMultipleOpenResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32483, onErrorForAll(methodObject32483, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInGroupBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32390, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32390, Integer.valueOf(this.delegate.getMaxColumnsInGroupBy()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32390, onErrorForAll(methodObject32390, e))).intValue();
    }
  }
  
  public int getMaxColumnNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32389, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32389, Integer.valueOf(this.delegate.getMaxColumnNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32389, onErrorForAll(methodObject32389, e))).intValue();
    }
  }
  
  public int getMaxStatements()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32402, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32402, Integer.valueOf(this.delegate.getMaxStatements()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32402, onErrorForAll(methodObject32402, e))).intValue();
    }
  }
  
  public boolean supportsAlterTableWithAddColumn()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32454, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32454, Boolean.valueOf(this.delegate.supportsAlterTableWithAddColumn()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32454, onErrorForAll(methodObject32454, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupByUnrelated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32476, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32476, Boolean.valueOf(this.delegate.supportsGroupByUnrelated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32476, onErrorForAll(methodObject32476, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92EntryLevelSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32451, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32451, Boolean.valueOf(this.delegate.supportsANSI92EntryLevelSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32451, onErrorForAll(methodObject32451, e))).booleanValue();
    }
  }
  
  public boolean supportsMinimumSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32480, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32480, Boolean.valueOf(this.delegate.supportsMinimumSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32480, onErrorForAll(methodObject32480, e))).booleanValue();
    }
  }
  
  public int getMaxCharLiteralLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32388, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32388, Integer.valueOf(this.delegate.getMaxCharLiteralLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32388, onErrorForAll(methodObject32388, e))).intValue();
    }
  }
  
  public ResultSet getFunctionColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32379, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32379, this.proxyFactory.proxyForCache((Object)this.delegate.getFunctionColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32379));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32379, onErrorForAll(methodObject32379, e));
    }
  }
  
  public ResultSet getColumnPrivileges(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32365, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32365, this.proxyFactory.proxyForCache((Object)this.delegate.getColumnPrivileges(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32365));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32365, onErrorForAll(methodObject32365, e));
    }
  }
  
  public boolean storesLowerCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32445, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32445, Boolean.valueOf(this.delegate.storesLowerCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32445, onErrorForAll(methodObject32445, e))).booleanValue();
    }
  }
  
  public boolean deletesAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32358, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32358, Boolean.valueOf(this.delegate.deletesAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32358, onErrorForAll(methodObject32358, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleResultSets()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32484, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32484, Boolean.valueOf(this.delegate.supportsMultipleResultSets()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32484, onErrorForAll(methodObject32484, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenCursorsAcrossRollback()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32489, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32489, Boolean.valueOf(this.delegate.supportsOpenCursorsAcrossRollback()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32489, onErrorForAll(methodObject32489, e))).booleanValue();
    }
  }
  
  public boolean supportsSelectForUpdate()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32505, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32505, Boolean.valueOf(this.delegate.supportsSelectForUpdate()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32505, onErrorForAll(methodObject32505, e))).booleanValue();
    }
  }
  
  public boolean supportsPositionedDelete()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32494, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32494, Boolean.valueOf(this.delegate.supportsPositionedDelete()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32494, onErrorForAll(methodObject32494, e))).booleanValue();
    }
  }
  
  public boolean supportsCatalogsInProcedureCalls()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32460, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32460, Boolean.valueOf(this.delegate.supportsCatalogsInProcedureCalls()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32460, onErrorForAll(methodObject32460, e))).booleanValue();
    }
  }
  
  public ResultSet getSchemas(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32417, this, new Object[] { arg0, arg1 });
      return (ResultSet)postForAll(methodObject32417, this.proxyFactory.proxyForCache((Object)this.delegate.getSchemas(arg0, arg1), this, this.proxyCache, methodObject32417));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32417, onErrorForAll(methodObject32417, e));
    }
  }
  
  public String getCatalogSeparator()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32361, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32361, (Object)this.delegate.getCatalogSeparator());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32361, onErrorForAll(methodObject32361, e));
    }
  }
  
  public AdditionalDatabaseMetaData _getDelegate_()
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
      methodObject32384 = DatabaseMetaData.class.getDeclaredMethod("getJDBCMajorVersion", new Class[0]);
      methodObject32488 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenCursorsAcrossCommit", new Class[0]);
      methodObject32458 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInIndexDefinitions", new Class[0]);
      methodObject32380 = DatabaseMetaData.class.getDeclaredMethod("getFunctions", new Class[] { String.class, String.class, String.class });
      methodObject32374 = DatabaseMetaData.class.getDeclaredMethod("getDriverMinorVersion", new Class[0]);
      methodObject32362 = DatabaseMetaData.class.getDeclaredMethod("getCatalogTerm", new Class[0]);
      methodObject32479 = DatabaseMetaData.class.getDeclaredMethod("supportsLimitedOuterJoins", new Class[0]);
      methodObject32416 = DatabaseMetaData.class.getDeclaredMethod("getSchemas", new Class[0]);
      methodObject32381 = DatabaseMetaData.class.getDeclaredMethod("getIdentifierQuoteString", new Class[0]);
      methodObject32351 = DatabaseMetaData.class.getDeclaredMethod("getAttributes", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32468 = DatabaseMetaData.class.getDeclaredMethod("supportsDataManipulationTransactionsOnly", new Class[0]);
      methodObject32418 = DatabaseMetaData.class.getDeclaredMethod("getSearchStringEscape", new Class[0]);
      methodObject32442 = DatabaseMetaData.class.getDeclaredMethod("ownDeletesAreVisible", new Class[] { Integer.TYPE });
      methodObject32502 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInPrivilegeDefinitions", new Class[0]);
      methodObject32400 = DatabaseMetaData.class.getDeclaredMethod("getMaxSchemaNameLength", new Class[0]);
      methodObject32472 = DatabaseMetaData.class.getDeclaredMethod("supportsFullOuterJoins", new Class[0]);
      methodObject32433 = DatabaseMetaData.class.getDeclaredMethod("locatorsUpdateCopy", new Class[0]);
      methodObject32422 = DatabaseMetaData.class.getDeclaredMethod("getSystemFunctions", new Class[0]);
      methodObject32519 = DatabaseMetaData.class.getDeclaredMethod("usesLocalFilePerTable", new Class[0]);
      methodObject32413 = DatabaseMetaData.class.getDeclaredMethod("getSQLKeywords", new Class[0]);
      methodObject32429 = DatabaseMetaData.class.getDeclaredMethod("getUserName", new Class[0]);
      methodObject32405 = DatabaseMetaData.class.getDeclaredMethod("getMaxUserNameLength", new Class[0]);
      methodObject32376 = DatabaseMetaData.class.getDeclaredMethod("getDriverVersion", new Class[0]);
      methodObject32444 = DatabaseMetaData.class.getDeclaredMethod("ownUpdatesAreVisible", new Class[] { Integer.TYPE });
      methodObject32391 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInIndex", new Class[0]);
      methodObject32408 = DatabaseMetaData.class.getDeclaredMethod("getProcedureColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32415 = DatabaseMetaData.class.getDeclaredMethod("getSchemaTerm", new Class[0]);
      methodObject32511 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInIns", new Class[0]);
      methodObject32518 = DatabaseMetaData.class.getDeclaredMethod("updatesAreDetected", new Class[] { Integer.TYPE });
      methodObject32503 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInProcedureCalls", new Class[0]);
      methodObject32491 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenStatementsAcrossRollback", new Class[0]);
      methodObject32375 = DatabaseMetaData.class.getDeclaredMethod("getDriverName", new Class[0]);
      methodObject32355 = DatabaseMetaData.class.getDeclaredMethod("autoCommitFailureClosesAllResultSets", new Class[0]);
      methodObject32364 = DatabaseMetaData.class.getDeclaredMethod("getClientInfoProperties", new Class[0]);
      methodObject32370 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseProductName", new Class[0]);
      methodObject32513 = DatabaseMetaData.class.getDeclaredMethod("supportsTableCorrelationNames", new Class[0]);
      methodObject32420 = DatabaseMetaData.class.getDeclaredMethod("getSuperTables", new Class[] { String.class, String.class, String.class });
      methodObject32368 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseMajorVersion", new Class[0]);
      methodObject32393 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInSelect", new Class[0]);
      methodObject32440 = DatabaseMetaData.class.getDeclaredMethod("othersInsertsAreVisible", new Class[] { Integer.TYPE });
      methodObject32443 = DatabaseMetaData.class.getDeclaredMethod("ownInsertsAreVisible", new Class[] { Integer.TYPE });
      methodObject32508 = DatabaseMetaData.class.getDeclaredMethod("supportsStoredProcedures", new Class[0]);
      methodObject32501 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInIndexDefinitions", new Class[0]);
      methodObject32372 = DatabaseMetaData.class.getDeclaredMethod("getDefaultTransactionIsolation", new Class[0]);
      methodObject32419 = DatabaseMetaData.class.getDeclaredMethod("getStringFunctions", new Class[0]);
      methodObject32359 = DatabaseMetaData.class.getDeclaredMethod("doesMaxRowSizeIncludeBlobs", new Class[0]);
      methodObject32428 = DatabaseMetaData.class.getDeclaredMethod("getUDTs", new Class[] { String.class, String.class, String.class, int[].class });
      methodObject32504 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInTableDefinitions", new Class[0]);
      methodObject32477 = DatabaseMetaData.class.getDeclaredMethod("supportsIntegrityEnhancementFacility", new Class[0]);
      methodObject32356 = DatabaseMetaData.class.getDeclaredMethod("dataDefinitionCausesTransactionCommit", new Class[0]);
      methodObject32496 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetConcurrency", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32435 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedAtEnd", new Class[0]);
      methodObject32363 = DatabaseMetaData.class.getDeclaredMethod("getCatalogs", new Class[0]);
      methodObject32373 = DatabaseMetaData.class.getDeclaredMethod("getDriverMajorVersion", new Class[0]);
      methodObject32347 = AdditionalDatabaseMetaData.class.getDeclaredMethod("getOracleTypeMetaData", new Class[] { String.class });
      methodObject32459 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInPrivilegeDefinitions", new Class[0]);
      methodObject32432 = DatabaseMetaData.class.getDeclaredMethod("isCatalogAtStart", new Class[0]);
      methodObject32497 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetHoldability", new Class[] { Integer.TYPE });
      methodObject32386 = DatabaseMetaData.class.getDeclaredMethod("getMaxBinaryLiteralLength", new Class[0]);
      methodObject32411 = DatabaseMetaData.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject32449 = DatabaseMetaData.class.getDeclaredMethod("storesUpperCaseIdentifiers", new Class[0]);
      methodObject32350 = DatabaseMetaData.class.getDeclaredMethod("isReadOnly", new Class[0]);
      methodObject32469 = DatabaseMetaData.class.getDeclaredMethod("supportsDifferentTableCorrelationNames", new Class[0]);
      methodObject32473 = DatabaseMetaData.class.getDeclaredMethod("supportsGetGeneratedKeys", new Class[0]);
      methodObject32403 = DatabaseMetaData.class.getDeclaredMethod("getMaxTableNameLength", new Class[0]);
      methodObject32427 = DatabaseMetaData.class.getDeclaredMethod("getTypeInfo", new Class[0]);
      methodObject32404 = DatabaseMetaData.class.getDeclaredMethod("getMaxTablesInSelect", new Class[0]);
      methodObject32521 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject32516 = DatabaseMetaData.class.getDeclaredMethod("supportsUnion", new Class[0]);
      methodObject32510 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInExists", new Class[0]);
      methodObject32439 = DatabaseMetaData.class.getDeclaredMethod("othersDeletesAreVisible", new Class[] { Integer.TYPE });
      methodObject32492 = DatabaseMetaData.class.getDeclaredMethod("supportsOrderByUnrelated", new Class[0]);
      methodObject32520 = DatabaseMetaData.class.getDeclaredMethod("usesLocalFiles", new Class[0]);
      methodObject32453 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92IntermediateSQL", new Class[0]);
      methodObject32410 = DatabaseMetaData.class.getDeclaredMethod("getProcedures", new Class[] { String.class, String.class, String.class });
      methodObject32406 = DatabaseMetaData.class.getDeclaredMethod("getNumericFunctions", new Class[0]);
      methodObject32367 = DatabaseMetaData.class.getDeclaredMethod("getCrossReference", new Class[] { String.class, String.class, String.class, String.class, String.class, String.class });
      methodObject32414 = DatabaseMetaData.class.getDeclaredMethod("getSQLStateType", new Class[0]);
      methodObject32401 = DatabaseMetaData.class.getDeclaredMethod("getMaxStatementLength", new Class[0]);
      methodObject32426 = DatabaseMetaData.class.getDeclaredMethod("getTimeDateFunctions", new Class[0]);
      methodObject32522 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32425 = DatabaseMetaData.class.getDeclaredMethod("getTables", new Class[] { String.class, String.class, String.class, String[].class });
      methodObject32500 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInDataManipulation", new Class[0]);
      methodObject32441 = DatabaseMetaData.class.getDeclaredMethod("othersUpdatesAreVisible", new Class[] { Integer.TYPE });
      methodObject32495 = DatabaseMetaData.class.getDeclaredMethod("supportsPositionedUpdate", new Class[0]);
      methodObject32377 = DatabaseMetaData.class.getDeclaredMethod("getExportedKeys", new Class[] { String.class, String.class, String.class });
      methodObject32431 = DatabaseMetaData.class.getDeclaredMethod("insertsAreDetected", new Class[] { Integer.TYPE });
      methodObject32448 = DatabaseMetaData.class.getDeclaredMethod("storesMixedCaseQuotedIdentifiers", new Class[0]);
      methodObject32481 = DatabaseMetaData.class.getDeclaredMethod("supportsMixedCaseIdentifiers", new Class[0]);
      methodObject32450 = DatabaseMetaData.class.getDeclaredMethod("storesUpperCaseQuotedIdentifiers", new Class[0]);
      methodObject32465 = DatabaseMetaData.class.getDeclaredMethod("supportsCoreSQLGrammar", new Class[0]);
      methodObject32369 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseMinorVersion", new Class[0]);
      methodObject32399 = DatabaseMetaData.class.getDeclaredMethod("getMaxRowSize", new Class[0]);
      methodObject32438 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedLow", new Class[0]);
      methodObject32486 = DatabaseMetaData.class.getDeclaredMethod("supportsNamedParameters", new Class[0]);
      methodObject32349 = DatabaseMetaData.class.getDeclaredMethod("getURL", new Class[0]);
      methodObject32430 = DatabaseMetaData.class.getDeclaredMethod("getVersionColumns", new Class[] { String.class, String.class, String.class });
      methodObject32515 = DatabaseMetaData.class.getDeclaredMethod("supportsTransactions", new Class[0]);
      methodObject32514 = DatabaseMetaData.class.getDeclaredMethod("supportsTransactionIsolationLevel", new Class[] { Integer.TYPE });
      methodObject32352 = DatabaseMetaData.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject32378 = DatabaseMetaData.class.getDeclaredMethod("getExtraNameCharacters", new Class[0]);
      methodObject32482 = DatabaseMetaData.class.getDeclaredMethod("supportsMixedCaseQuotedIdentifiers", new Class[0]);
      methodObject32446 = DatabaseMetaData.class.getDeclaredMethod("storesLowerCaseQuotedIdentifiers", new Class[0]);
      methodObject32471 = DatabaseMetaData.class.getDeclaredMethod("supportsExtendedSQLGrammar", new Class[0]);
      methodObject32463 = DatabaseMetaData.class.getDeclaredMethod("supportsConvert", new Class[0]);
      methodObject32452 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92FullSQL", new Class[0]);
      methodObject32507 = DatabaseMetaData.class.getDeclaredMethod("supportsStoredFunctionsUsingCallSyntax", new Class[0]);
      methodObject32366 = DatabaseMetaData.class.getDeclaredMethod("getColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32396 = DatabaseMetaData.class.getDeclaredMethod("getMaxCursorNameLength", new Class[0]);
      methodObject32385 = DatabaseMetaData.class.getDeclaredMethod("getJDBCMinorVersion", new Class[0]);
      methodObject32487 = DatabaseMetaData.class.getDeclaredMethod("supportsNonNullableColumns", new Class[0]);
      methodObject32383 = DatabaseMetaData.class.getDeclaredMethod("getIndexInfo", new Class[] { String.class, String.class, String.class, Boolean.TYPE, Boolean.TYPE });
      methodObject32478 = DatabaseMetaData.class.getDeclaredMethod("supportsLikeEscapeClause", new Class[0]);
      methodObject32466 = DatabaseMetaData.class.getDeclaredMethod("supportsCorrelatedSubqueries", new Class[0]);
      methodObject32409 = DatabaseMetaData.class.getDeclaredMethod("getProcedureTerm", new Class[0]);
      methodObject32437 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedHigh", new Class[0]);
      methodObject32499 = DatabaseMetaData.class.getDeclaredMethod("supportsSavepoints", new Class[0]);
      methodObject32387 = DatabaseMetaData.class.getDeclaredMethod("getMaxCatalogNameLength", new Class[0]);
      methodObject32456 = DatabaseMetaData.class.getDeclaredMethod("supportsBatchUpdates", new Class[0]);
      methodObject32470 = DatabaseMetaData.class.getDeclaredMethod("supportsExpressionsInOrderBy", new Class[0]);
      methodObject32498 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetType", new Class[] { Integer.TYPE });
      methodObject32357 = DatabaseMetaData.class.getDeclaredMethod("dataDefinitionIgnoredInTransactions", new Class[0]);
      methodObject32493 = DatabaseMetaData.class.getDeclaredMethod("supportsOuterJoins", new Class[0]);
      methodObject32421 = DatabaseMetaData.class.getDeclaredMethod("getSuperTypes", new Class[] { String.class, String.class, String.class });
      methodObject32436 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedAtStart", new Class[0]);
      methodObject32517 = DatabaseMetaData.class.getDeclaredMethod("supportsUnionAll", new Class[0]);
      methodObject32353 = DatabaseMetaData.class.getDeclaredMethod("allProceduresAreCallable", new Class[0]);
      methodObject32348 = AdditionalDatabaseMetaData.class.getDeclaredMethod("getLobMaxLength", new Class[0]);
      methodObject32457 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInDataManipulation", new Class[0]);
      methodObject32382 = DatabaseMetaData.class.getDeclaredMethod("getImportedKeys", new Class[] { String.class, String.class, String.class });
      methodObject32412 = DatabaseMetaData.class.getDeclaredMethod("getRowIdLifetime", new Class[0]);
      methodObject32371 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseProductVersion", new Class[0]);
      methodObject32398 = DatabaseMetaData.class.getDeclaredMethod("getMaxProcedureNameLength", new Class[0]);
      methodObject32447 = DatabaseMetaData.class.getDeclaredMethod("storesMixedCaseIdentifiers", new Class[0]);
      methodObject32462 = DatabaseMetaData.class.getDeclaredMethod("supportsColumnAliasing", new Class[0]);
      methodObject32455 = DatabaseMetaData.class.getDeclaredMethod("supportsAlterTableWithDropColumn", new Class[0]);
      methodObject32485 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleTransactions", new Class[0]);
      methodObject32474 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupBy", new Class[0]);
      methodObject32394 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInTable", new Class[0]);
      methodObject32392 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInOrderBy", new Class[0]);
      methodObject32395 = DatabaseMetaData.class.getDeclaredMethod("getMaxConnections", new Class[0]);
      methodObject32354 = DatabaseMetaData.class.getDeclaredMethod("allTablesAreSelectable", new Class[0]);
      methodObject32424 = DatabaseMetaData.class.getDeclaredMethod("getTableTypes", new Class[0]);
      methodObject32434 = DatabaseMetaData.class.getDeclaredMethod("nullPlusNonNullIsNull", new Class[0]);
      methodObject32509 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInComparisons", new Class[0]);
      methodObject32397 = DatabaseMetaData.class.getDeclaredMethod("getMaxIndexLength", new Class[0]);
      methodObject32512 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInQuantifieds", new Class[0]);
      methodObject32490 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenStatementsAcrossCommit", new Class[0]);
      methodObject32360 = DatabaseMetaData.class.getDeclaredMethod("getBestRowIdentifier", new Class[] { String.class, String.class, String.class, Integer.TYPE, Boolean.TYPE });
      methodObject32407 = DatabaseMetaData.class.getDeclaredMethod("getPrimaryKeys", new Class[] { String.class, String.class, String.class });
      methodObject32423 = DatabaseMetaData.class.getDeclaredMethod("getTablePrivileges", new Class[] { String.class, String.class, String.class });
      methodObject32461 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInTableDefinitions", new Class[0]);
      methodObject32475 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupByBeyondSelect", new Class[0]);
      methodObject32464 = DatabaseMetaData.class.getDeclaredMethod("supportsConvert", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32506 = DatabaseMetaData.class.getDeclaredMethod("supportsStatementPooling", new Class[0]);
      methodObject32467 = DatabaseMetaData.class.getDeclaredMethod("supportsDataDefinitionAndDataManipulationTransactions", new Class[0]);
      methodObject32483 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleOpenResults", new Class[0]);
      methodObject32390 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInGroupBy", new Class[0]);
      methodObject32389 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnNameLength", new Class[0]);
      methodObject32402 = DatabaseMetaData.class.getDeclaredMethod("getMaxStatements", new Class[0]);
      methodObject32454 = DatabaseMetaData.class.getDeclaredMethod("supportsAlterTableWithAddColumn", new Class[0]);
      methodObject32476 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupByUnrelated", new Class[0]);
      methodObject32451 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92EntryLevelSQL", new Class[0]);
      methodObject32480 = DatabaseMetaData.class.getDeclaredMethod("supportsMinimumSQLGrammar", new Class[0]);
      methodObject32388 = DatabaseMetaData.class.getDeclaredMethod("getMaxCharLiteralLength", new Class[0]);
      methodObject32379 = DatabaseMetaData.class.getDeclaredMethod("getFunctionColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32365 = DatabaseMetaData.class.getDeclaredMethod("getColumnPrivileges", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32445 = DatabaseMetaData.class.getDeclaredMethod("storesLowerCaseIdentifiers", new Class[0]);
      methodObject32358 = DatabaseMetaData.class.getDeclaredMethod("deletesAreDetected", new Class[] { Integer.TYPE });
      methodObject32484 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleResultSets", new Class[0]);
      methodObject32489 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenCursorsAcrossRollback", new Class[0]);
      methodObject32505 = DatabaseMetaData.class.getDeclaredMethod("supportsSelectForUpdate", new Class[0]);
      methodObject32494 = DatabaseMetaData.class.getDeclaredMethod("supportsPositionedDelete", new Class[0]);
      methodObject32460 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInProcedureCalls", new Class[0]);
      methodObject32417 = DatabaseMetaData.class.getDeclaredMethod("getSchemas", new Class[] { String.class, String.class });
      methodObject32361 = DatabaseMetaData.class.getDeclaredMethod("getCatalogSeparator", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1AdditionalDatabaseMetaData$$$Proxy(AdditionalDatabaseMetaData paramAdditionalDatabaseMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramAdditionalDatabaseMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2oracle$1jdbc$1AdditionalDatabaseMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */