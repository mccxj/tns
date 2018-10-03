package oracle.jdbc.proxy;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1DatabaseMetaData$$$Proxy
  extends NonTxnReplayableBase
  implements DatabaseMetaData, _Proxy_
{
  private DatabaseMetaData delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject32131;
  private static Method methodObject32235;
  private static Method methodObject32205;
  private static Method methodObject32127;
  private static Method methodObject32121;
  private static Method methodObject32109;
  private static Method methodObject32226;
  private static Method methodObject32163;
  private static Method methodObject32128;
  private static Method methodObject32098;
  private static Method methodObject32215;
  private static Method methodObject32165;
  private static Method methodObject32189;
  private static Method methodObject32249;
  private static Method methodObject32147;
  private static Method methodObject32219;
  private static Method methodObject32180;
  private static Method methodObject32169;
  private static Method methodObject32266;
  private static Method methodObject32160;
  private static Method methodObject32176;
  private static Method methodObject32152;
  private static Method methodObject32123;
  private static Method methodObject32191;
  private static Method methodObject32138;
  private static Method methodObject32155;
  private static Method methodObject32162;
  private static Method methodObject32258;
  private static Method methodObject32265;
  private static Method methodObject32250;
  private static Method methodObject32238;
  private static Method methodObject32122;
  private static Method methodObject32102;
  private static Method methodObject32111;
  private static Method methodObject32117;
  private static Method methodObject32260;
  private static Method methodObject32167;
  private static Method methodObject32115;
  private static Method methodObject32140;
  private static Method methodObject32187;
  private static Method methodObject32190;
  private static Method methodObject32255;
  private static Method methodObject32248;
  private static Method methodObject32119;
  private static Method methodObject32166;
  private static Method methodObject32106;
  private static Method methodObject32175;
  private static Method methodObject32251;
  private static Method methodObject32224;
  private static Method methodObject32103;
  private static Method methodObject32243;
  private static Method methodObject32182;
  private static Method methodObject32110;
  private static Method methodObject32120;
  private static Method methodObject32206;
  private static Method methodObject32179;
  private static Method methodObject32244;
  private static Method methodObject32133;
  private static Method methodObject32158;
  private static Method methodObject32196;
  private static Method methodObject32097;
  private static Method methodObject32216;
  private static Method methodObject32220;
  private static Method methodObject32150;
  private static Method methodObject32174;
  private static Method methodObject32151;
  private static Method methodObject32268;
  private static Method methodObject32263;
  private static Method methodObject32257;
  private static Method methodObject32186;
  private static Method methodObject32239;
  private static Method methodObject32267;
  private static Method methodObject32200;
  private static Method methodObject32157;
  private static Method methodObject32153;
  private static Method methodObject32114;
  private static Method methodObject32161;
  private static Method methodObject32148;
  private static Method methodObject32173;
  private static Method methodObject32269;
  private static Method methodObject32172;
  private static Method methodObject32247;
  private static Method methodObject32188;
  private static Method methodObject32242;
  private static Method methodObject32124;
  private static Method methodObject32178;
  private static Method methodObject32195;
  private static Method methodObject32228;
  private static Method methodObject32197;
  private static Method methodObject32212;
  private static Method methodObject32116;
  private static Method methodObject32146;
  private static Method methodObject32185;
  private static Method methodObject32233;
  private static Method methodObject32096;
  private static Method methodObject32177;
  private static Method methodObject32262;
  private static Method methodObject32261;
  private static Method methodObject32099;
  private static Method methodObject32125;
  private static Method methodObject32229;
  private static Method methodObject32193;
  private static Method methodObject32218;
  private static Method methodObject32210;
  private static Method methodObject32199;
  private static Method methodObject32254;
  private static Method methodObject32143;
  private static Method methodObject32113;
  private static Method methodObject32132;
  private static Method methodObject32234;
  private static Method methodObject32130;
  private static Method methodObject32225;
  private static Method methodObject32213;
  private static Method methodObject32156;
  private static Method methodObject32184;
  private static Method methodObject32246;
  private static Method methodObject32134;
  private static Method methodObject32203;
  private static Method methodObject32217;
  private static Method methodObject32245;
  private static Method methodObject32104;
  private static Method methodObject32240;
  private static Method methodObject32168;
  private static Method methodObject32183;
  private static Method methodObject32264;
  private static Method methodObject32100;
  private static Method methodObject32204;
  private static Method methodObject32129;
  private static Method methodObject32159;
  private static Method methodObject32118;
  private static Method methodObject32145;
  private static Method methodObject32194;
  private static Method methodObject32209;
  private static Method methodObject32202;
  private static Method methodObject32232;
  private static Method methodObject32221;
  private static Method methodObject32141;
  private static Method methodObject32139;
  private static Method methodObject32142;
  private static Method methodObject32101;
  private static Method methodObject32171;
  private static Method methodObject32181;
  private static Method methodObject32256;
  private static Method methodObject32144;
  private static Method methodObject32259;
  private static Method methodObject32237;
  private static Method methodObject32107;
  private static Method methodObject32154;
  private static Method methodObject32170;
  private static Method methodObject32208;
  private static Method methodObject32222;
  private static Method methodObject32211;
  private static Method methodObject32253;
  private static Method methodObject32214;
  private static Method methodObject32230;
  private static Method methodObject32137;
  private static Method methodObject32136;
  private static Method methodObject32149;
  private static Method methodObject32201;
  private static Method methodObject32223;
  private static Method methodObject32198;
  private static Method methodObject32227;
  private static Method methodObject32135;
  private static Method methodObject32126;
  private static Method methodObject32112;
  private static Method methodObject32192;
  private static Method methodObject32105;
  private static Method methodObject32231;
  private static Method methodObject32236;
  private static Method methodObject32252;
  private static Method methodObject32241;
  private static Method methodObject32207;
  private static Method methodObject32164;
  private static Method methodObject32108;
  
  public int getJDBCMajorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32131, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32131, Integer.valueOf(this.delegate.getJDBCMajorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32131, onErrorForAll(methodObject32131, e))).intValue();
    }
  }
  
  public boolean supportsOpenCursorsAcrossCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32235, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32235, Boolean.valueOf(this.delegate.supportsOpenCursorsAcrossCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32235, onErrorForAll(methodObject32235, e))).booleanValue();
    }
  }
  
  public boolean supportsCatalogsInIndexDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32205, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32205, Boolean.valueOf(this.delegate.supportsCatalogsInIndexDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32205, onErrorForAll(methodObject32205, e))).booleanValue();
    }
  }
  
  public ResultSet getFunctions(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32127, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32127, this.proxyFactory.proxyForCache((Object)this.delegate.getFunctions(arg0, arg1, arg2), this, this.proxyCache, methodObject32127));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32127, onErrorForAll(methodObject32127, e));
    }
  }
  
  public int getDriverMinorVersion()
  {
    super.preForAll(methodObject32121, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject32121, Integer.valueOf(this.delegate.getDriverMinorVersion()))).intValue();
  }
  
  public String getCatalogTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32109, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32109, (Object)this.delegate.getCatalogTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32109, onErrorForAll(methodObject32109, e));
    }
  }
  
  public boolean supportsLimitedOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32226, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32226, Boolean.valueOf(this.delegate.supportsLimitedOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32226, onErrorForAll(methodObject32226, e))).booleanValue();
    }
  }
  
  public ResultSet getSchemas()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32163, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32163, this.proxyFactory.proxyForCache((Object)this.delegate.getSchemas(), this, this.proxyCache, methodObject32163));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32163, onErrorForAll(methodObject32163, e));
    }
  }
  
  public String getIdentifierQuoteString()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32128, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32128, (Object)this.delegate.getIdentifierQuoteString());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32128, onErrorForAll(methodObject32128, e));
    }
  }
  
  public ResultSet getAttributes(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32098, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32098, this.proxyFactory.proxyForCache((Object)this.delegate.getAttributes(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32098));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32098, onErrorForAll(methodObject32098, e));
    }
  }
  
  public boolean supportsDataManipulationTransactionsOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32215, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32215, Boolean.valueOf(this.delegate.supportsDataManipulationTransactionsOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32215, onErrorForAll(methodObject32215, e))).booleanValue();
    }
  }
  
  public String getSearchStringEscape()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32165, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32165, (Object)this.delegate.getSearchStringEscape());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32165, onErrorForAll(methodObject32165, e));
    }
  }
  
  public boolean ownDeletesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32189, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32189, Boolean.valueOf(this.delegate.ownDeletesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32189, onErrorForAll(methodObject32189, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInPrivilegeDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32249, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32249, Boolean.valueOf(this.delegate.supportsSchemasInPrivilegeDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32249, onErrorForAll(methodObject32249, e))).booleanValue();
    }
  }
  
  public int getMaxSchemaNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32147, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32147, Integer.valueOf(this.delegate.getMaxSchemaNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32147, onErrorForAll(methodObject32147, e))).intValue();
    }
  }
  
  public boolean supportsFullOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32219, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32219, Boolean.valueOf(this.delegate.supportsFullOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32219, onErrorForAll(methodObject32219, e))).booleanValue();
    }
  }
  
  public boolean locatorsUpdateCopy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32180, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32180, Boolean.valueOf(this.delegate.locatorsUpdateCopy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32180, onErrorForAll(methodObject32180, e))).booleanValue();
    }
  }
  
  public String getSystemFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32169, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32169, (Object)this.delegate.getSystemFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32169, onErrorForAll(methodObject32169, e));
    }
  }
  
  public boolean usesLocalFilePerTable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32266, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32266, Boolean.valueOf(this.delegate.usesLocalFilePerTable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32266, onErrorForAll(methodObject32266, e))).booleanValue();
    }
  }
  
  public String getSQLKeywords()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32160, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32160, (Object)this.delegate.getSQLKeywords());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32160, onErrorForAll(methodObject32160, e));
    }
  }
  
  public String getUserName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32176, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32176, (Object)this.delegate.getUserName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32176, onErrorForAll(methodObject32176, e));
    }
  }
  
  public int getMaxUserNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32152, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32152, Integer.valueOf(this.delegate.getMaxUserNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32152, onErrorForAll(methodObject32152, e))).intValue();
    }
  }
  
  public String getDriverVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32123, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32123, (Object)this.delegate.getDriverVersion());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32123, onErrorForAll(methodObject32123, e));
    }
  }
  
  public boolean ownUpdatesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32191, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32191, Boolean.valueOf(this.delegate.ownUpdatesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32191, onErrorForAll(methodObject32191, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInIndex()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32138, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32138, Integer.valueOf(this.delegate.getMaxColumnsInIndex()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32138, onErrorForAll(methodObject32138, e))).intValue();
    }
  }
  
  public ResultSet getProcedureColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32155, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32155, this.proxyFactory.proxyForCache((Object)this.delegate.getProcedureColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32155));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32155, onErrorForAll(methodObject32155, e));
    }
  }
  
  public String getSchemaTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32162, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32162, (Object)this.delegate.getSchemaTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32162, onErrorForAll(methodObject32162, e));
    }
  }
  
  public boolean supportsSubqueriesInIns()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32258, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32258, Boolean.valueOf(this.delegate.supportsSubqueriesInIns()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32258, onErrorForAll(methodObject32258, e))).booleanValue();
    }
  }
  
  public boolean updatesAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32265, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32265, Boolean.valueOf(this.delegate.updatesAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32265, onErrorForAll(methodObject32265, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInProcedureCalls()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32250, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32250, Boolean.valueOf(this.delegate.supportsSchemasInProcedureCalls()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32250, onErrorForAll(methodObject32250, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenStatementsAcrossRollback()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32238, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32238, Boolean.valueOf(this.delegate.supportsOpenStatementsAcrossRollback()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32238, onErrorForAll(methodObject32238, e))).booleanValue();
    }
  }
  
  public String getDriverName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32122, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32122, (Object)this.delegate.getDriverName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32122, onErrorForAll(methodObject32122, e));
    }
  }
  
  public boolean autoCommitFailureClosesAllResultSets()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32102, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32102, Boolean.valueOf(this.delegate.autoCommitFailureClosesAllResultSets()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32102, onErrorForAll(methodObject32102, e))).booleanValue();
    }
  }
  
  public ResultSet getClientInfoProperties()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32111, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32111, this.proxyFactory.proxyForCache((Object)this.delegate.getClientInfoProperties(), this, this.proxyCache, methodObject32111));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32111, onErrorForAll(methodObject32111, e));
    }
  }
  
  public String getDatabaseProductName()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32117, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32117, (Object)this.delegate.getDatabaseProductName());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32117, onErrorForAll(methodObject32117, e));
    }
  }
  
  public boolean supportsTableCorrelationNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32260, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32260, Boolean.valueOf(this.delegate.supportsTableCorrelationNames()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32260, onErrorForAll(methodObject32260, e))).booleanValue();
    }
  }
  
  public ResultSet getSuperTables(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32167, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32167, this.proxyFactory.proxyForCache((Object)this.delegate.getSuperTables(arg0, arg1, arg2), this, this.proxyCache, methodObject32167));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32167, onErrorForAll(methodObject32167, e));
    }
  }
  
  public int getDatabaseMajorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32115, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32115, Integer.valueOf(this.delegate.getDatabaseMajorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32115, onErrorForAll(methodObject32115, e))).intValue();
    }
  }
  
  public int getMaxColumnsInSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32140, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32140, Integer.valueOf(this.delegate.getMaxColumnsInSelect()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32140, onErrorForAll(methodObject32140, e))).intValue();
    }
  }
  
  public boolean othersInsertsAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32187, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32187, Boolean.valueOf(this.delegate.othersInsertsAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32187, onErrorForAll(methodObject32187, e))).booleanValue();
    }
  }
  
  public boolean ownInsertsAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32190, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32190, Boolean.valueOf(this.delegate.ownInsertsAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32190, onErrorForAll(methodObject32190, e))).booleanValue();
    }
  }
  
  public boolean supportsStoredProcedures()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32255, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32255, Boolean.valueOf(this.delegate.supportsStoredProcedures()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32255, onErrorForAll(methodObject32255, e))).booleanValue();
    }
  }
  
  public boolean supportsSchemasInIndexDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32248, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32248, Boolean.valueOf(this.delegate.supportsSchemasInIndexDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32248, onErrorForAll(methodObject32248, e))).booleanValue();
    }
  }
  
  public int getDefaultTransactionIsolation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32119, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32119, Integer.valueOf(this.delegate.getDefaultTransactionIsolation()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32119, onErrorForAll(methodObject32119, e))).intValue();
    }
  }
  
  public String getStringFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32166, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32166, (Object)this.delegate.getStringFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32166, onErrorForAll(methodObject32166, e));
    }
  }
  
  public boolean doesMaxRowSizeIncludeBlobs()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32106, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32106, Boolean.valueOf(this.delegate.doesMaxRowSizeIncludeBlobs()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32106, onErrorForAll(methodObject32106, e))).booleanValue();
    }
  }
  
  public ResultSet getUDTs(String arg0, String arg1, String arg2, int[] arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32175, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32175, this.proxyFactory.proxyForCache((Object)this.delegate.getUDTs(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32175));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32175, onErrorForAll(methodObject32175, e));
    }
  }
  
  public boolean supportsSchemasInTableDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32251, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32251, Boolean.valueOf(this.delegate.supportsSchemasInTableDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32251, onErrorForAll(methodObject32251, e))).booleanValue();
    }
  }
  
  public boolean supportsIntegrityEnhancementFacility()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32224, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32224, Boolean.valueOf(this.delegate.supportsIntegrityEnhancementFacility()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32224, onErrorForAll(methodObject32224, e))).booleanValue();
    }
  }
  
  public boolean dataDefinitionCausesTransactionCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32103, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32103, Boolean.valueOf(this.delegate.dataDefinitionCausesTransactionCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32103, onErrorForAll(methodObject32103, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetConcurrency(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32243, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return ((Boolean)postForAll(methodObject32243, Boolean.valueOf(this.delegate.supportsResultSetConcurrency(arg0, arg1)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32243, onErrorForAll(methodObject32243, e))).booleanValue();
    }
  }
  
  public boolean nullsAreSortedAtEnd()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32182, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32182, Boolean.valueOf(this.delegate.nullsAreSortedAtEnd()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32182, onErrorForAll(methodObject32182, e))).booleanValue();
    }
  }
  
  public ResultSet getCatalogs()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32110, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32110, this.proxyFactory.proxyForCache((Object)this.delegate.getCatalogs(), this, this.proxyCache, methodObject32110));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32110, onErrorForAll(methodObject32110, e));
    }
  }
  
  public int getDriverMajorVersion()
  {
    super.preForAll(methodObject32120, this, zeroLengthObjectArray);
    return ((Integer)postForAll(methodObject32120, Integer.valueOf(this.delegate.getDriverMajorVersion()))).intValue();
  }
  
  public boolean supportsCatalogsInPrivilegeDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32206, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32206, Boolean.valueOf(this.delegate.supportsCatalogsInPrivilegeDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32206, onErrorForAll(methodObject32206, e))).booleanValue();
    }
  }
  
  public boolean isCatalogAtStart()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32179, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32179, Boolean.valueOf(this.delegate.isCatalogAtStart()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32179, onErrorForAll(methodObject32179, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetHoldability(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32244, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32244, Boolean.valueOf(this.delegate.supportsResultSetHoldability(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32244, onErrorForAll(methodObject32244, e))).booleanValue();
    }
  }
  
  public int getMaxBinaryLiteralLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32133, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32133, Integer.valueOf(this.delegate.getMaxBinaryLiteralLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32133, onErrorForAll(methodObject32133, e))).intValue();
    }
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32158, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32158, Integer.valueOf(this.delegate.getResultSetHoldability()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32158, onErrorForAll(methodObject32158, e))).intValue();
    }
  }
  
  public boolean storesUpperCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32196, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32196, Boolean.valueOf(this.delegate.storesUpperCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32196, onErrorForAll(methodObject32196, e))).booleanValue();
    }
  }
  
  public boolean isReadOnly()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32097, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32097, Boolean.valueOf(this.delegate.isReadOnly()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32097, onErrorForAll(methodObject32097, e))).booleanValue();
    }
  }
  
  public boolean supportsDifferentTableCorrelationNames()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32216, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32216, Boolean.valueOf(this.delegate.supportsDifferentTableCorrelationNames()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32216, onErrorForAll(methodObject32216, e))).booleanValue();
    }
  }
  
  public boolean supportsGetGeneratedKeys()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32220, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32220, Boolean.valueOf(this.delegate.supportsGetGeneratedKeys()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32220, onErrorForAll(methodObject32220, e))).booleanValue();
    }
  }
  
  public int getMaxTableNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32150, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32150, Integer.valueOf(this.delegate.getMaxTableNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32150, onErrorForAll(methodObject32150, e))).intValue();
    }
  }
  
  public ResultSet getTypeInfo()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32174, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32174, this.proxyFactory.proxyForCache((Object)this.delegate.getTypeInfo(), this, this.proxyCache, methodObject32174));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32174, onErrorForAll(methodObject32174, e));
    }
  }
  
  public int getMaxTablesInSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32151, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32151, Integer.valueOf(this.delegate.getMaxTablesInSelect()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32151, onErrorForAll(methodObject32151, e))).intValue();
    }
  }
  
  public Object unwrap(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32268, this, new Object[] { arg0 });
      return postForAll(methodObject32268, this.proxyFactory.proxyForCache(this.delegate.unwrap(arg0), this, this.proxyCache, methodObject32268));
    }
    catch (SQLException e)
    {
      return postForAll(methodObject32268, onErrorForAll(methodObject32268, e));
    }
  }
  
  public boolean supportsUnion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32263, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32263, Boolean.valueOf(this.delegate.supportsUnion()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32263, onErrorForAll(methodObject32263, e))).booleanValue();
    }
  }
  
  public boolean supportsSubqueriesInExists()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32257, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32257, Boolean.valueOf(this.delegate.supportsSubqueriesInExists()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32257, onErrorForAll(methodObject32257, e))).booleanValue();
    }
  }
  
  public boolean othersDeletesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32186, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32186, Boolean.valueOf(this.delegate.othersDeletesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32186, onErrorForAll(methodObject32186, e))).booleanValue();
    }
  }
  
  public boolean supportsOrderByUnrelated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32239, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32239, Boolean.valueOf(this.delegate.supportsOrderByUnrelated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32239, onErrorForAll(methodObject32239, e))).booleanValue();
    }
  }
  
  public boolean usesLocalFiles()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32267, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32267, Boolean.valueOf(this.delegate.usesLocalFiles()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32267, onErrorForAll(methodObject32267, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92IntermediateSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32200, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32200, Boolean.valueOf(this.delegate.supportsANSI92IntermediateSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32200, onErrorForAll(methodObject32200, e))).booleanValue();
    }
  }
  
  public ResultSet getProcedures(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32157, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32157, this.proxyFactory.proxyForCache((Object)this.delegate.getProcedures(arg0, arg1, arg2), this, this.proxyCache, methodObject32157));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32157, onErrorForAll(methodObject32157, e));
    }
  }
  
  public String getNumericFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32153, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32153, (Object)this.delegate.getNumericFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32153, onErrorForAll(methodObject32153, e));
    }
  }
  
  public ResultSet getCrossReference(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32114, this, new Object[] { arg0, arg1, arg2, arg3, arg4, arg5 });
      return (ResultSet)postForAll(methodObject32114, this.proxyFactory.proxyForCache((Object)this.delegate.getCrossReference(arg0, arg1, arg2, arg3, arg4, arg5), this, this.proxyCache, methodObject32114));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32114, onErrorForAll(methodObject32114, e));
    }
  }
  
  public int getSQLStateType()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32161, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32161, Integer.valueOf(this.delegate.getSQLStateType()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32161, onErrorForAll(methodObject32161, e))).intValue();
    }
  }
  
  public int getMaxStatementLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32148, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32148, Integer.valueOf(this.delegate.getMaxStatementLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32148, onErrorForAll(methodObject32148, e))).intValue();
    }
  }
  
  public String getTimeDateFunctions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32173, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32173, (Object)this.delegate.getTimeDateFunctions());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32173, onErrorForAll(methodObject32173, e));
    }
  }
  
  public boolean isWrapperFor(Class arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32269, this, new Object[] { arg0 });
      return ((Boolean)postForAll(methodObject32269, Boolean.valueOf(this.delegate.isWrapperFor(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32269, onErrorForAll(methodObject32269, e))).booleanValue();
    }
  }
  
  public ResultSet getTables(String arg0, String arg1, String arg2, String[] arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32172, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32172, this.proxyFactory.proxyForCache((Object)this.delegate.getTables(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32172));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32172, onErrorForAll(methodObject32172, e));
    }
  }
  
  public boolean supportsSchemasInDataManipulation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32247, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32247, Boolean.valueOf(this.delegate.supportsSchemasInDataManipulation()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32247, onErrorForAll(methodObject32247, e))).booleanValue();
    }
  }
  
  public boolean othersUpdatesAreVisible(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32188, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32188, Boolean.valueOf(this.delegate.othersUpdatesAreVisible(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32188, onErrorForAll(methodObject32188, e))).booleanValue();
    }
  }
  
  public boolean supportsPositionedUpdate()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32242, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32242, Boolean.valueOf(this.delegate.supportsPositionedUpdate()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32242, onErrorForAll(methodObject32242, e))).booleanValue();
    }
  }
  
  public ResultSet getExportedKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32124, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32124, this.proxyFactory.proxyForCache((Object)this.delegate.getExportedKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32124));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32124, onErrorForAll(methodObject32124, e));
    }
  }
  
  public boolean insertsAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32178, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32178, Boolean.valueOf(this.delegate.insertsAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32178, onErrorForAll(methodObject32178, e))).booleanValue();
    }
  }
  
  public boolean storesMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32195, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32195, Boolean.valueOf(this.delegate.storesMixedCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32195, onErrorForAll(methodObject32195, e))).booleanValue();
    }
  }
  
  public boolean supportsMixedCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32228, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32228, Boolean.valueOf(this.delegate.supportsMixedCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32228, onErrorForAll(methodObject32228, e))).booleanValue();
    }
  }
  
  public boolean storesUpperCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32197, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32197, Boolean.valueOf(this.delegate.storesUpperCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32197, onErrorForAll(methodObject32197, e))).booleanValue();
    }
  }
  
  public boolean supportsCoreSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32212, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32212, Boolean.valueOf(this.delegate.supportsCoreSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32212, onErrorForAll(methodObject32212, e))).booleanValue();
    }
  }
  
  public int getDatabaseMinorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32116, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32116, Integer.valueOf(this.delegate.getDatabaseMinorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32116, onErrorForAll(methodObject32116, e))).intValue();
    }
  }
  
  public int getMaxRowSize()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32146, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32146, Integer.valueOf(this.delegate.getMaxRowSize()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32146, onErrorForAll(methodObject32146, e))).intValue();
    }
  }
  
  public boolean nullsAreSortedLow()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32185, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32185, Boolean.valueOf(this.delegate.nullsAreSortedLow()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32185, onErrorForAll(methodObject32185, e))).booleanValue();
    }
  }
  
  public boolean supportsNamedParameters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32233, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32233, Boolean.valueOf(this.delegate.supportsNamedParameters()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32233, onErrorForAll(methodObject32233, e))).booleanValue();
    }
  }
  
  public String getURL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32096, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32096, (Object)this.delegate.getURL());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32096, onErrorForAll(methodObject32096, e));
    }
  }
  
  public ResultSet getVersionColumns(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32177, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32177, this.proxyFactory.proxyForCache((Object)this.delegate.getVersionColumns(arg0, arg1, arg2), this, this.proxyCache, methodObject32177));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32177, onErrorForAll(methodObject32177, e));
    }
  }
  
  public boolean supportsTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32262, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32262, Boolean.valueOf(this.delegate.supportsTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32262, onErrorForAll(methodObject32262, e))).booleanValue();
    }
  }
  
  public boolean supportsTransactionIsolationLevel(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32261, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32261, Boolean.valueOf(this.delegate.supportsTransactionIsolationLevel(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32261, onErrorForAll(methodObject32261, e))).booleanValue();
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32099, this, zeroLengthObjectArray);
      return (Connection)postForAll(methodObject32099, this.proxyFactory.proxyForCache((Object)this.delegate.getConnection(), this, this.proxyCache, methodObject32099));
    }
    catch (SQLException e)
    {
      return (Connection)postForAll(methodObject32099, onErrorForAll(methodObject32099, e));
    }
  }
  
  public String getExtraNameCharacters()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32125, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32125, (Object)this.delegate.getExtraNameCharacters());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32125, onErrorForAll(methodObject32125, e));
    }
  }
  
  public boolean supportsMixedCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32229, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32229, Boolean.valueOf(this.delegate.supportsMixedCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32229, onErrorForAll(methodObject32229, e))).booleanValue();
    }
  }
  
  public boolean storesLowerCaseQuotedIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32193, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32193, Boolean.valueOf(this.delegate.storesLowerCaseQuotedIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32193, onErrorForAll(methodObject32193, e))).booleanValue();
    }
  }
  
  public boolean supportsExtendedSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32218, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32218, Boolean.valueOf(this.delegate.supportsExtendedSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32218, onErrorForAll(methodObject32218, e))).booleanValue();
    }
  }
  
  public boolean supportsConvert()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32210, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32210, Boolean.valueOf(this.delegate.supportsConvert()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32210, onErrorForAll(methodObject32210, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92FullSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32199, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32199, Boolean.valueOf(this.delegate.supportsANSI92FullSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32199, onErrorForAll(methodObject32199, e))).booleanValue();
    }
  }
  
  public boolean supportsStoredFunctionsUsingCallSyntax()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32254, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32254, Boolean.valueOf(this.delegate.supportsStoredFunctionsUsingCallSyntax()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32254, onErrorForAll(methodObject32254, e))).booleanValue();
    }
  }
  
  public int getMaxCursorNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32143, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32143, Integer.valueOf(this.delegate.getMaxCursorNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32143, onErrorForAll(methodObject32143, e))).intValue();
    }
  }
  
  public ResultSet getColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32113, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32113, this.proxyFactory.proxyForCache((Object)this.delegate.getColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32113));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32113, onErrorForAll(methodObject32113, e));
    }
  }
  
  public int getJDBCMinorVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32132, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32132, Integer.valueOf(this.delegate.getJDBCMinorVersion()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32132, onErrorForAll(methodObject32132, e))).intValue();
    }
  }
  
  public boolean supportsNonNullableColumns()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32234, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32234, Boolean.valueOf(this.delegate.supportsNonNullableColumns()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32234, onErrorForAll(methodObject32234, e))).booleanValue();
    }
  }
  
  public ResultSet getIndexInfo(String arg0, String arg1, String arg2, boolean arg3, boolean arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32130, this, new Object[] { arg0, arg1, arg2, Boolean.valueOf(arg3), Boolean.valueOf(arg4) });
      return (ResultSet)postForAll(methodObject32130, this.proxyFactory.proxyForCache((Object)this.delegate.getIndexInfo(arg0, arg1, arg2, arg3, arg4), this, this.proxyCache, methodObject32130));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32130, onErrorForAll(methodObject32130, e));
    }
  }
  
  public boolean supportsLikeEscapeClause()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32225, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32225, Boolean.valueOf(this.delegate.supportsLikeEscapeClause()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32225, onErrorForAll(methodObject32225, e))).booleanValue();
    }
  }
  
  public boolean supportsCorrelatedSubqueries()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32213, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32213, Boolean.valueOf(this.delegate.supportsCorrelatedSubqueries()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32213, onErrorForAll(methodObject32213, e))).booleanValue();
    }
  }
  
  public String getProcedureTerm()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32156, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32156, (Object)this.delegate.getProcedureTerm());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32156, onErrorForAll(methodObject32156, e));
    }
  }
  
  public boolean nullsAreSortedHigh()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32184, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32184, Boolean.valueOf(this.delegate.nullsAreSortedHigh()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32184, onErrorForAll(methodObject32184, e))).booleanValue();
    }
  }
  
  public boolean supportsSavepoints()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32246, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32246, Boolean.valueOf(this.delegate.supportsSavepoints()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32246, onErrorForAll(methodObject32246, e))).booleanValue();
    }
  }
  
  public int getMaxCatalogNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32134, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32134, Integer.valueOf(this.delegate.getMaxCatalogNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32134, onErrorForAll(methodObject32134, e))).intValue();
    }
  }
  
  public boolean supportsBatchUpdates()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32203, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32203, Boolean.valueOf(this.delegate.supportsBatchUpdates()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32203, onErrorForAll(methodObject32203, e))).booleanValue();
    }
  }
  
  public boolean supportsExpressionsInOrderBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32217, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32217, Boolean.valueOf(this.delegate.supportsExpressionsInOrderBy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32217, onErrorForAll(methodObject32217, e))).booleanValue();
    }
  }
  
  public boolean supportsResultSetType(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32245, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32245, Boolean.valueOf(this.delegate.supportsResultSetType(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32245, onErrorForAll(methodObject32245, e))).booleanValue();
    }
  }
  
  public boolean dataDefinitionIgnoredInTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32104, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32104, Boolean.valueOf(this.delegate.dataDefinitionIgnoredInTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32104, onErrorForAll(methodObject32104, e))).booleanValue();
    }
  }
  
  public boolean supportsOuterJoins()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32240, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32240, Boolean.valueOf(this.delegate.supportsOuterJoins()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32240, onErrorForAll(methodObject32240, e))).booleanValue();
    }
  }
  
  public ResultSet getSuperTypes(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32168, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32168, this.proxyFactory.proxyForCache((Object)this.delegate.getSuperTypes(arg0, arg1, arg2), this, this.proxyCache, methodObject32168));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32168, onErrorForAll(methodObject32168, e));
    }
  }
  
  public boolean nullsAreSortedAtStart()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32183, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32183, Boolean.valueOf(this.delegate.nullsAreSortedAtStart()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32183, onErrorForAll(methodObject32183, e))).booleanValue();
    }
  }
  
  public boolean supportsUnionAll()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32264, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32264, Boolean.valueOf(this.delegate.supportsUnionAll()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32264, onErrorForAll(methodObject32264, e))).booleanValue();
    }
  }
  
  public boolean allProceduresAreCallable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32100, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32100, Boolean.valueOf(this.delegate.allProceduresAreCallable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32100, onErrorForAll(methodObject32100, e))).booleanValue();
    }
  }
  
  public boolean supportsCatalogsInDataManipulation()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32204, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32204, Boolean.valueOf(this.delegate.supportsCatalogsInDataManipulation()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32204, onErrorForAll(methodObject32204, e))).booleanValue();
    }
  }
  
  public ResultSet getImportedKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32129, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32129, this.proxyFactory.proxyForCache((Object)this.delegate.getImportedKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32129));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32129, onErrorForAll(methodObject32129, e));
    }
  }
  
  public RowIdLifetime getRowIdLifetime()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32159, this, zeroLengthObjectArray);
      return (RowIdLifetime)postForAll(methodObject32159, (Object)this.delegate.getRowIdLifetime());
    }
    catch (SQLException e)
    {
      return (RowIdLifetime)postForAll(methodObject32159, onErrorForAll(methodObject32159, e));
    }
  }
  
  public String getDatabaseProductVersion()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32118, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32118, (Object)this.delegate.getDatabaseProductVersion());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32118, onErrorForAll(methodObject32118, e));
    }
  }
  
  public int getMaxProcedureNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32145, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32145, Integer.valueOf(this.delegate.getMaxProcedureNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32145, onErrorForAll(methodObject32145, e))).intValue();
    }
  }
  
  public boolean storesMixedCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32194, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32194, Boolean.valueOf(this.delegate.storesMixedCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32194, onErrorForAll(methodObject32194, e))).booleanValue();
    }
  }
  
  public boolean supportsColumnAliasing()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32209, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32209, Boolean.valueOf(this.delegate.supportsColumnAliasing()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32209, onErrorForAll(methodObject32209, e))).booleanValue();
    }
  }
  
  public boolean supportsAlterTableWithDropColumn()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32202, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32202, Boolean.valueOf(this.delegate.supportsAlterTableWithDropColumn()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32202, onErrorForAll(methodObject32202, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32232, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32232, Boolean.valueOf(this.delegate.supportsMultipleTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32232, onErrorForAll(methodObject32232, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32221, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32221, Boolean.valueOf(this.delegate.supportsGroupBy()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32221, onErrorForAll(methodObject32221, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInTable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32141, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32141, Integer.valueOf(this.delegate.getMaxColumnsInTable()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32141, onErrorForAll(methodObject32141, e))).intValue();
    }
  }
  
  public int getMaxColumnsInOrderBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32139, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32139, Integer.valueOf(this.delegate.getMaxColumnsInOrderBy()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32139, onErrorForAll(methodObject32139, e))).intValue();
    }
  }
  
  public int getMaxConnections()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32142, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32142, Integer.valueOf(this.delegate.getMaxConnections()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32142, onErrorForAll(methodObject32142, e))).intValue();
    }
  }
  
  public boolean allTablesAreSelectable()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32101, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32101, Boolean.valueOf(this.delegate.allTablesAreSelectable()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32101, onErrorForAll(methodObject32101, e))).booleanValue();
    }
  }
  
  public ResultSet getTableTypes()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32171, this, zeroLengthObjectArray);
      return (ResultSet)postForAll(methodObject32171, this.proxyFactory.proxyForCache((Object)this.delegate.getTableTypes(), this, this.proxyCache, methodObject32171));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32171, onErrorForAll(methodObject32171, e));
    }
  }
  
  public boolean nullPlusNonNullIsNull()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32181, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32181, Boolean.valueOf(this.delegate.nullPlusNonNullIsNull()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32181, onErrorForAll(methodObject32181, e))).booleanValue();
    }
  }
  
  public boolean supportsSubqueriesInComparisons()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32256, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32256, Boolean.valueOf(this.delegate.supportsSubqueriesInComparisons()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32256, onErrorForAll(methodObject32256, e))).booleanValue();
    }
  }
  
  public int getMaxIndexLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32144, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32144, Integer.valueOf(this.delegate.getMaxIndexLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32144, onErrorForAll(methodObject32144, e))).intValue();
    }
  }
  
  public boolean supportsSubqueriesInQuantifieds()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32259, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32259, Boolean.valueOf(this.delegate.supportsSubqueriesInQuantifieds()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32259, onErrorForAll(methodObject32259, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenStatementsAcrossCommit()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32237, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32237, Boolean.valueOf(this.delegate.supportsOpenStatementsAcrossCommit()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32237, onErrorForAll(methodObject32237, e))).booleanValue();
    }
  }
  
  public ResultSet getBestRowIdentifier(String arg0, String arg1, String arg2, int arg3, boolean arg4)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32107, this, new Object[] { arg0, arg1, arg2, Integer.valueOf(arg3), Boolean.valueOf(arg4) });
      return (ResultSet)postForAll(methodObject32107, this.proxyFactory.proxyForCache((Object)this.delegate.getBestRowIdentifier(arg0, arg1, arg2, arg3, arg4), this, this.proxyCache, methodObject32107));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32107, onErrorForAll(methodObject32107, e));
    }
  }
  
  public ResultSet getPrimaryKeys(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32154, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32154, this.proxyFactory.proxyForCache((Object)this.delegate.getPrimaryKeys(arg0, arg1, arg2), this, this.proxyCache, methodObject32154));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32154, onErrorForAll(methodObject32154, e));
    }
  }
  
  public ResultSet getTablePrivileges(String arg0, String arg1, String arg2)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32170, this, new Object[] { arg0, arg1, arg2 });
      return (ResultSet)postForAll(methodObject32170, this.proxyFactory.proxyForCache((Object)this.delegate.getTablePrivileges(arg0, arg1, arg2), this, this.proxyCache, methodObject32170));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32170, onErrorForAll(methodObject32170, e));
    }
  }
  
  public boolean supportsCatalogsInTableDefinitions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32208, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32208, Boolean.valueOf(this.delegate.supportsCatalogsInTableDefinitions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32208, onErrorForAll(methodObject32208, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupByBeyondSelect()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32222, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32222, Boolean.valueOf(this.delegate.supportsGroupByBeyondSelect()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32222, onErrorForAll(methodObject32222, e))).booleanValue();
    }
  }
  
  public boolean supportsConvert(int arg0, int arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32211, this, new Object[] { Integer.valueOf(arg0), Integer.valueOf(arg1) });
      return ((Boolean)postForAll(methodObject32211, Boolean.valueOf(this.delegate.supportsConvert(arg0, arg1)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32211, onErrorForAll(methodObject32211, e))).booleanValue();
    }
  }
  
  public boolean supportsStatementPooling()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32253, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32253, Boolean.valueOf(this.delegate.supportsStatementPooling()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32253, onErrorForAll(methodObject32253, e))).booleanValue();
    }
  }
  
  public boolean supportsDataDefinitionAndDataManipulationTransactions()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32214, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32214, Boolean.valueOf(this.delegate.supportsDataDefinitionAndDataManipulationTransactions()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32214, onErrorForAll(methodObject32214, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleOpenResults()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32230, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32230, Boolean.valueOf(this.delegate.supportsMultipleOpenResults()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32230, onErrorForAll(methodObject32230, e))).booleanValue();
    }
  }
  
  public int getMaxColumnsInGroupBy()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32137, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32137, Integer.valueOf(this.delegate.getMaxColumnsInGroupBy()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32137, onErrorForAll(methodObject32137, e))).intValue();
    }
  }
  
  public int getMaxColumnNameLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32136, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32136, Integer.valueOf(this.delegate.getMaxColumnNameLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32136, onErrorForAll(methodObject32136, e))).intValue();
    }
  }
  
  public int getMaxStatements()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32149, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32149, Integer.valueOf(this.delegate.getMaxStatements()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32149, onErrorForAll(methodObject32149, e))).intValue();
    }
  }
  
  public boolean supportsAlterTableWithAddColumn()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32201, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32201, Boolean.valueOf(this.delegate.supportsAlterTableWithAddColumn()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32201, onErrorForAll(methodObject32201, e))).booleanValue();
    }
  }
  
  public boolean supportsGroupByUnrelated()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32223, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32223, Boolean.valueOf(this.delegate.supportsGroupByUnrelated()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32223, onErrorForAll(methodObject32223, e))).booleanValue();
    }
  }
  
  public boolean supportsANSI92EntryLevelSQL()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32198, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32198, Boolean.valueOf(this.delegate.supportsANSI92EntryLevelSQL()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32198, onErrorForAll(methodObject32198, e))).booleanValue();
    }
  }
  
  public boolean supportsMinimumSQLGrammar()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32227, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32227, Boolean.valueOf(this.delegate.supportsMinimumSQLGrammar()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32227, onErrorForAll(methodObject32227, e))).booleanValue();
    }
  }
  
  public int getMaxCharLiteralLength()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32135, this, zeroLengthObjectArray);
      return ((Integer)postForAll(methodObject32135, Integer.valueOf(this.delegate.getMaxCharLiteralLength()))).intValue();
    }
    catch (SQLException e)
    {
      return ((Integer)postForAll(methodObject32135, onErrorForAll(methodObject32135, e))).intValue();
    }
  }
  
  public ResultSet getFunctionColumns(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32126, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32126, this.proxyFactory.proxyForCache((Object)this.delegate.getFunctionColumns(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32126));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32126, onErrorForAll(methodObject32126, e));
    }
  }
  
  public ResultSet getColumnPrivileges(String arg0, String arg1, String arg2, String arg3)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32112, this, new Object[] { arg0, arg1, arg2, arg3 });
      return (ResultSet)postForAll(methodObject32112, this.proxyFactory.proxyForCache((Object)this.delegate.getColumnPrivileges(arg0, arg1, arg2, arg3), this, this.proxyCache, methodObject32112));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32112, onErrorForAll(methodObject32112, e));
    }
  }
  
  public boolean storesLowerCaseIdentifiers()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32192, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32192, Boolean.valueOf(this.delegate.storesLowerCaseIdentifiers()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32192, onErrorForAll(methodObject32192, e))).booleanValue();
    }
  }
  
  public boolean deletesAreDetected(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32105, this, new Object[] { Integer.valueOf(arg0) });
      return ((Boolean)postForAll(methodObject32105, Boolean.valueOf(this.delegate.deletesAreDetected(arg0)))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32105, onErrorForAll(methodObject32105, e))).booleanValue();
    }
  }
  
  public boolean supportsMultipleResultSets()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32231, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32231, Boolean.valueOf(this.delegate.supportsMultipleResultSets()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32231, onErrorForAll(methodObject32231, e))).booleanValue();
    }
  }
  
  public boolean supportsOpenCursorsAcrossRollback()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32236, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32236, Boolean.valueOf(this.delegate.supportsOpenCursorsAcrossRollback()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32236, onErrorForAll(methodObject32236, e))).booleanValue();
    }
  }
  
  public boolean supportsSelectForUpdate()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32252, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32252, Boolean.valueOf(this.delegate.supportsSelectForUpdate()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32252, onErrorForAll(methodObject32252, e))).booleanValue();
    }
  }
  
  public boolean supportsPositionedDelete()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32241, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32241, Boolean.valueOf(this.delegate.supportsPositionedDelete()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32241, onErrorForAll(methodObject32241, e))).booleanValue();
    }
  }
  
  public boolean supportsCatalogsInProcedureCalls()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32207, this, zeroLengthObjectArray);
      return ((Boolean)postForAll(methodObject32207, Boolean.valueOf(this.delegate.supportsCatalogsInProcedureCalls()))).booleanValue();
    }
    catch (SQLException e)
    {
      return ((Boolean)postForAll(methodObject32207, onErrorForAll(methodObject32207, e))).booleanValue();
    }
  }
  
  public ResultSet getSchemas(String arg0, String arg1)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32164, this, new Object[] { arg0, arg1 });
      return (ResultSet)postForAll(methodObject32164, this.proxyFactory.proxyForCache((Object)this.delegate.getSchemas(arg0, arg1), this, this.proxyCache, methodObject32164));
    }
    catch (SQLException e)
    {
      return (ResultSet)postForAll(methodObject32164, onErrorForAll(methodObject32164, e));
    }
  }
  
  public String getCatalogSeparator()
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject32108, this, zeroLengthObjectArray);
      return (String)postForAll(methodObject32108, (Object)this.delegate.getCatalogSeparator());
    }
    catch (SQLException e)
    {
      return (String)postForAll(methodObject32108, onErrorForAll(methodObject32108, e));
    }
  }
  
  public DatabaseMetaData _getDelegate_()
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
      methodObject32131 = DatabaseMetaData.class.getDeclaredMethod("getJDBCMajorVersion", new Class[0]);
      methodObject32235 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenCursorsAcrossCommit", new Class[0]);
      methodObject32205 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInIndexDefinitions", new Class[0]);
      methodObject32127 = DatabaseMetaData.class.getDeclaredMethod("getFunctions", new Class[] { String.class, String.class, String.class });
      methodObject32121 = DatabaseMetaData.class.getDeclaredMethod("getDriverMinorVersion", new Class[0]);
      methodObject32109 = DatabaseMetaData.class.getDeclaredMethod("getCatalogTerm", new Class[0]);
      methodObject32226 = DatabaseMetaData.class.getDeclaredMethod("supportsLimitedOuterJoins", new Class[0]);
      methodObject32163 = DatabaseMetaData.class.getDeclaredMethod("getSchemas", new Class[0]);
      methodObject32128 = DatabaseMetaData.class.getDeclaredMethod("getIdentifierQuoteString", new Class[0]);
      methodObject32098 = DatabaseMetaData.class.getDeclaredMethod("getAttributes", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32215 = DatabaseMetaData.class.getDeclaredMethod("supportsDataManipulationTransactionsOnly", new Class[0]);
      methodObject32165 = DatabaseMetaData.class.getDeclaredMethod("getSearchStringEscape", new Class[0]);
      methodObject32189 = DatabaseMetaData.class.getDeclaredMethod("ownDeletesAreVisible", new Class[] { Integer.TYPE });
      methodObject32249 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInPrivilegeDefinitions", new Class[0]);
      methodObject32147 = DatabaseMetaData.class.getDeclaredMethod("getMaxSchemaNameLength", new Class[0]);
      methodObject32219 = DatabaseMetaData.class.getDeclaredMethod("supportsFullOuterJoins", new Class[0]);
      methodObject32180 = DatabaseMetaData.class.getDeclaredMethod("locatorsUpdateCopy", new Class[0]);
      methodObject32169 = DatabaseMetaData.class.getDeclaredMethod("getSystemFunctions", new Class[0]);
      methodObject32266 = DatabaseMetaData.class.getDeclaredMethod("usesLocalFilePerTable", new Class[0]);
      methodObject32160 = DatabaseMetaData.class.getDeclaredMethod("getSQLKeywords", new Class[0]);
      methodObject32176 = DatabaseMetaData.class.getDeclaredMethod("getUserName", new Class[0]);
      methodObject32152 = DatabaseMetaData.class.getDeclaredMethod("getMaxUserNameLength", new Class[0]);
      methodObject32123 = DatabaseMetaData.class.getDeclaredMethod("getDriverVersion", new Class[0]);
      methodObject32191 = DatabaseMetaData.class.getDeclaredMethod("ownUpdatesAreVisible", new Class[] { Integer.TYPE });
      methodObject32138 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInIndex", new Class[0]);
      methodObject32155 = DatabaseMetaData.class.getDeclaredMethod("getProcedureColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32162 = DatabaseMetaData.class.getDeclaredMethod("getSchemaTerm", new Class[0]);
      methodObject32258 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInIns", new Class[0]);
      methodObject32265 = DatabaseMetaData.class.getDeclaredMethod("updatesAreDetected", new Class[] { Integer.TYPE });
      methodObject32250 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInProcedureCalls", new Class[0]);
      methodObject32238 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenStatementsAcrossRollback", new Class[0]);
      methodObject32122 = DatabaseMetaData.class.getDeclaredMethod("getDriverName", new Class[0]);
      methodObject32102 = DatabaseMetaData.class.getDeclaredMethod("autoCommitFailureClosesAllResultSets", new Class[0]);
      methodObject32111 = DatabaseMetaData.class.getDeclaredMethod("getClientInfoProperties", new Class[0]);
      methodObject32117 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseProductName", new Class[0]);
      methodObject32260 = DatabaseMetaData.class.getDeclaredMethod("supportsTableCorrelationNames", new Class[0]);
      methodObject32167 = DatabaseMetaData.class.getDeclaredMethod("getSuperTables", new Class[] { String.class, String.class, String.class });
      methodObject32115 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseMajorVersion", new Class[0]);
      methodObject32140 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInSelect", new Class[0]);
      methodObject32187 = DatabaseMetaData.class.getDeclaredMethod("othersInsertsAreVisible", new Class[] { Integer.TYPE });
      methodObject32190 = DatabaseMetaData.class.getDeclaredMethod("ownInsertsAreVisible", new Class[] { Integer.TYPE });
      methodObject32255 = DatabaseMetaData.class.getDeclaredMethod("supportsStoredProcedures", new Class[0]);
      methodObject32248 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInIndexDefinitions", new Class[0]);
      methodObject32119 = DatabaseMetaData.class.getDeclaredMethod("getDefaultTransactionIsolation", new Class[0]);
      methodObject32166 = DatabaseMetaData.class.getDeclaredMethod("getStringFunctions", new Class[0]);
      methodObject32106 = DatabaseMetaData.class.getDeclaredMethod("doesMaxRowSizeIncludeBlobs", new Class[0]);
      methodObject32175 = DatabaseMetaData.class.getDeclaredMethod("getUDTs", new Class[] { String.class, String.class, String.class, int[].class });
      methodObject32251 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInTableDefinitions", new Class[0]);
      methodObject32224 = DatabaseMetaData.class.getDeclaredMethod("supportsIntegrityEnhancementFacility", new Class[0]);
      methodObject32103 = DatabaseMetaData.class.getDeclaredMethod("dataDefinitionCausesTransactionCommit", new Class[0]);
      methodObject32243 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetConcurrency", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32182 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedAtEnd", new Class[0]);
      methodObject32110 = DatabaseMetaData.class.getDeclaredMethod("getCatalogs", new Class[0]);
      methodObject32120 = DatabaseMetaData.class.getDeclaredMethod("getDriverMajorVersion", new Class[0]);
      methodObject32206 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInPrivilegeDefinitions", new Class[0]);
      methodObject32179 = DatabaseMetaData.class.getDeclaredMethod("isCatalogAtStart", new Class[0]);
      methodObject32244 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetHoldability", new Class[] { Integer.TYPE });
      methodObject32133 = DatabaseMetaData.class.getDeclaredMethod("getMaxBinaryLiteralLength", new Class[0]);
      methodObject32158 = DatabaseMetaData.class.getDeclaredMethod("getResultSetHoldability", new Class[0]);
      methodObject32196 = DatabaseMetaData.class.getDeclaredMethod("storesUpperCaseIdentifiers", new Class[0]);
      methodObject32097 = DatabaseMetaData.class.getDeclaredMethod("isReadOnly", new Class[0]);
      methodObject32216 = DatabaseMetaData.class.getDeclaredMethod("supportsDifferentTableCorrelationNames", new Class[0]);
      methodObject32220 = DatabaseMetaData.class.getDeclaredMethod("supportsGetGeneratedKeys", new Class[0]);
      methodObject32150 = DatabaseMetaData.class.getDeclaredMethod("getMaxTableNameLength", new Class[0]);
      methodObject32174 = DatabaseMetaData.class.getDeclaredMethod("getTypeInfo", new Class[0]);
      methodObject32151 = DatabaseMetaData.class.getDeclaredMethod("getMaxTablesInSelect", new Class[0]);
      methodObject32268 = Wrapper.class.getDeclaredMethod("unwrap", new Class[] { Class.class });
      methodObject32263 = DatabaseMetaData.class.getDeclaredMethod("supportsUnion", new Class[0]);
      methodObject32257 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInExists", new Class[0]);
      methodObject32186 = DatabaseMetaData.class.getDeclaredMethod("othersDeletesAreVisible", new Class[] { Integer.TYPE });
      methodObject32239 = DatabaseMetaData.class.getDeclaredMethod("supportsOrderByUnrelated", new Class[0]);
      methodObject32267 = DatabaseMetaData.class.getDeclaredMethod("usesLocalFiles", new Class[0]);
      methodObject32200 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92IntermediateSQL", new Class[0]);
      methodObject32157 = DatabaseMetaData.class.getDeclaredMethod("getProcedures", new Class[] { String.class, String.class, String.class });
      methodObject32153 = DatabaseMetaData.class.getDeclaredMethod("getNumericFunctions", new Class[0]);
      methodObject32114 = DatabaseMetaData.class.getDeclaredMethod("getCrossReference", new Class[] { String.class, String.class, String.class, String.class, String.class, String.class });
      methodObject32161 = DatabaseMetaData.class.getDeclaredMethod("getSQLStateType", new Class[0]);
      methodObject32148 = DatabaseMetaData.class.getDeclaredMethod("getMaxStatementLength", new Class[0]);
      methodObject32173 = DatabaseMetaData.class.getDeclaredMethod("getTimeDateFunctions", new Class[0]);
      methodObject32269 = Wrapper.class.getDeclaredMethod("isWrapperFor", new Class[] { Class.class });
      methodObject32172 = DatabaseMetaData.class.getDeclaredMethod("getTables", new Class[] { String.class, String.class, String.class, String[].class });
      methodObject32247 = DatabaseMetaData.class.getDeclaredMethod("supportsSchemasInDataManipulation", new Class[0]);
      methodObject32188 = DatabaseMetaData.class.getDeclaredMethod("othersUpdatesAreVisible", new Class[] { Integer.TYPE });
      methodObject32242 = DatabaseMetaData.class.getDeclaredMethod("supportsPositionedUpdate", new Class[0]);
      methodObject32124 = DatabaseMetaData.class.getDeclaredMethod("getExportedKeys", new Class[] { String.class, String.class, String.class });
      methodObject32178 = DatabaseMetaData.class.getDeclaredMethod("insertsAreDetected", new Class[] { Integer.TYPE });
      methodObject32195 = DatabaseMetaData.class.getDeclaredMethod("storesMixedCaseQuotedIdentifiers", new Class[0]);
      methodObject32228 = DatabaseMetaData.class.getDeclaredMethod("supportsMixedCaseIdentifiers", new Class[0]);
      methodObject32197 = DatabaseMetaData.class.getDeclaredMethod("storesUpperCaseQuotedIdentifiers", new Class[0]);
      methodObject32212 = DatabaseMetaData.class.getDeclaredMethod("supportsCoreSQLGrammar", new Class[0]);
      methodObject32116 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseMinorVersion", new Class[0]);
      methodObject32146 = DatabaseMetaData.class.getDeclaredMethod("getMaxRowSize", new Class[0]);
      methodObject32185 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedLow", new Class[0]);
      methodObject32233 = DatabaseMetaData.class.getDeclaredMethod("supportsNamedParameters", new Class[0]);
      methodObject32096 = DatabaseMetaData.class.getDeclaredMethod("getURL", new Class[0]);
      methodObject32177 = DatabaseMetaData.class.getDeclaredMethod("getVersionColumns", new Class[] { String.class, String.class, String.class });
      methodObject32262 = DatabaseMetaData.class.getDeclaredMethod("supportsTransactions", new Class[0]);
      methodObject32261 = DatabaseMetaData.class.getDeclaredMethod("supportsTransactionIsolationLevel", new Class[] { Integer.TYPE });
      methodObject32099 = DatabaseMetaData.class.getDeclaredMethod("getConnection", new Class[0]);
      methodObject32125 = DatabaseMetaData.class.getDeclaredMethod("getExtraNameCharacters", new Class[0]);
      methodObject32229 = DatabaseMetaData.class.getDeclaredMethod("supportsMixedCaseQuotedIdentifiers", new Class[0]);
      methodObject32193 = DatabaseMetaData.class.getDeclaredMethod("storesLowerCaseQuotedIdentifiers", new Class[0]);
      methodObject32218 = DatabaseMetaData.class.getDeclaredMethod("supportsExtendedSQLGrammar", new Class[0]);
      methodObject32210 = DatabaseMetaData.class.getDeclaredMethod("supportsConvert", new Class[0]);
      methodObject32199 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92FullSQL", new Class[0]);
      methodObject32254 = DatabaseMetaData.class.getDeclaredMethod("supportsStoredFunctionsUsingCallSyntax", new Class[0]);
      methodObject32143 = DatabaseMetaData.class.getDeclaredMethod("getMaxCursorNameLength", new Class[0]);
      methodObject32113 = DatabaseMetaData.class.getDeclaredMethod("getColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32132 = DatabaseMetaData.class.getDeclaredMethod("getJDBCMinorVersion", new Class[0]);
      methodObject32234 = DatabaseMetaData.class.getDeclaredMethod("supportsNonNullableColumns", new Class[0]);
      methodObject32130 = DatabaseMetaData.class.getDeclaredMethod("getIndexInfo", new Class[] { String.class, String.class, String.class, Boolean.TYPE, Boolean.TYPE });
      methodObject32225 = DatabaseMetaData.class.getDeclaredMethod("supportsLikeEscapeClause", new Class[0]);
      methodObject32213 = DatabaseMetaData.class.getDeclaredMethod("supportsCorrelatedSubqueries", new Class[0]);
      methodObject32156 = DatabaseMetaData.class.getDeclaredMethod("getProcedureTerm", new Class[0]);
      methodObject32184 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedHigh", new Class[0]);
      methodObject32246 = DatabaseMetaData.class.getDeclaredMethod("supportsSavepoints", new Class[0]);
      methodObject32134 = DatabaseMetaData.class.getDeclaredMethod("getMaxCatalogNameLength", new Class[0]);
      methodObject32203 = DatabaseMetaData.class.getDeclaredMethod("supportsBatchUpdates", new Class[0]);
      methodObject32217 = DatabaseMetaData.class.getDeclaredMethod("supportsExpressionsInOrderBy", new Class[0]);
      methodObject32245 = DatabaseMetaData.class.getDeclaredMethod("supportsResultSetType", new Class[] { Integer.TYPE });
      methodObject32104 = DatabaseMetaData.class.getDeclaredMethod("dataDefinitionIgnoredInTransactions", new Class[0]);
      methodObject32240 = DatabaseMetaData.class.getDeclaredMethod("supportsOuterJoins", new Class[0]);
      methodObject32168 = DatabaseMetaData.class.getDeclaredMethod("getSuperTypes", new Class[] { String.class, String.class, String.class });
      methodObject32183 = DatabaseMetaData.class.getDeclaredMethod("nullsAreSortedAtStart", new Class[0]);
      methodObject32264 = DatabaseMetaData.class.getDeclaredMethod("supportsUnionAll", new Class[0]);
      methodObject32100 = DatabaseMetaData.class.getDeclaredMethod("allProceduresAreCallable", new Class[0]);
      methodObject32204 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInDataManipulation", new Class[0]);
      methodObject32129 = DatabaseMetaData.class.getDeclaredMethod("getImportedKeys", new Class[] { String.class, String.class, String.class });
      methodObject32159 = DatabaseMetaData.class.getDeclaredMethod("getRowIdLifetime", new Class[0]);
      methodObject32118 = DatabaseMetaData.class.getDeclaredMethod("getDatabaseProductVersion", new Class[0]);
      methodObject32145 = DatabaseMetaData.class.getDeclaredMethod("getMaxProcedureNameLength", new Class[0]);
      methodObject32194 = DatabaseMetaData.class.getDeclaredMethod("storesMixedCaseIdentifiers", new Class[0]);
      methodObject32209 = DatabaseMetaData.class.getDeclaredMethod("supportsColumnAliasing", new Class[0]);
      methodObject32202 = DatabaseMetaData.class.getDeclaredMethod("supportsAlterTableWithDropColumn", new Class[0]);
      methodObject32232 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleTransactions", new Class[0]);
      methodObject32221 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupBy", new Class[0]);
      methodObject32141 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInTable", new Class[0]);
      methodObject32139 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInOrderBy", new Class[0]);
      methodObject32142 = DatabaseMetaData.class.getDeclaredMethod("getMaxConnections", new Class[0]);
      methodObject32101 = DatabaseMetaData.class.getDeclaredMethod("allTablesAreSelectable", new Class[0]);
      methodObject32171 = DatabaseMetaData.class.getDeclaredMethod("getTableTypes", new Class[0]);
      methodObject32181 = DatabaseMetaData.class.getDeclaredMethod("nullPlusNonNullIsNull", new Class[0]);
      methodObject32256 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInComparisons", new Class[0]);
      methodObject32144 = DatabaseMetaData.class.getDeclaredMethod("getMaxIndexLength", new Class[0]);
      methodObject32259 = DatabaseMetaData.class.getDeclaredMethod("supportsSubqueriesInQuantifieds", new Class[0]);
      methodObject32237 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenStatementsAcrossCommit", new Class[0]);
      methodObject32107 = DatabaseMetaData.class.getDeclaredMethod("getBestRowIdentifier", new Class[] { String.class, String.class, String.class, Integer.TYPE, Boolean.TYPE });
      methodObject32154 = DatabaseMetaData.class.getDeclaredMethod("getPrimaryKeys", new Class[] { String.class, String.class, String.class });
      methodObject32170 = DatabaseMetaData.class.getDeclaredMethod("getTablePrivileges", new Class[] { String.class, String.class, String.class });
      methodObject32208 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInTableDefinitions", new Class[0]);
      methodObject32222 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupByBeyondSelect", new Class[0]);
      methodObject32211 = DatabaseMetaData.class.getDeclaredMethod("supportsConvert", new Class[] { Integer.TYPE, Integer.TYPE });
      methodObject32253 = DatabaseMetaData.class.getDeclaredMethod("supportsStatementPooling", new Class[0]);
      methodObject32214 = DatabaseMetaData.class.getDeclaredMethod("supportsDataDefinitionAndDataManipulationTransactions", new Class[0]);
      methodObject32230 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleOpenResults", new Class[0]);
      methodObject32137 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnsInGroupBy", new Class[0]);
      methodObject32136 = DatabaseMetaData.class.getDeclaredMethod("getMaxColumnNameLength", new Class[0]);
      methodObject32149 = DatabaseMetaData.class.getDeclaredMethod("getMaxStatements", new Class[0]);
      methodObject32201 = DatabaseMetaData.class.getDeclaredMethod("supportsAlterTableWithAddColumn", new Class[0]);
      methodObject32223 = DatabaseMetaData.class.getDeclaredMethod("supportsGroupByUnrelated", new Class[0]);
      methodObject32198 = DatabaseMetaData.class.getDeclaredMethod("supportsANSI92EntryLevelSQL", new Class[0]);
      methodObject32227 = DatabaseMetaData.class.getDeclaredMethod("supportsMinimumSQLGrammar", new Class[0]);
      methodObject32135 = DatabaseMetaData.class.getDeclaredMethod("getMaxCharLiteralLength", new Class[0]);
      methodObject32126 = DatabaseMetaData.class.getDeclaredMethod("getFunctionColumns", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32112 = DatabaseMetaData.class.getDeclaredMethod("getColumnPrivileges", new Class[] { String.class, String.class, String.class, String.class });
      methodObject32192 = DatabaseMetaData.class.getDeclaredMethod("storesLowerCaseIdentifiers", new Class[0]);
      methodObject32105 = DatabaseMetaData.class.getDeclaredMethod("deletesAreDetected", new Class[] { Integer.TYPE });
      methodObject32231 = DatabaseMetaData.class.getDeclaredMethod("supportsMultipleResultSets", new Class[0]);
      methodObject32236 = DatabaseMetaData.class.getDeclaredMethod("supportsOpenCursorsAcrossRollback", new Class[0]);
      methodObject32252 = DatabaseMetaData.class.getDeclaredMethod("supportsSelectForUpdate", new Class[0]);
      methodObject32241 = DatabaseMetaData.class.getDeclaredMethod("supportsPositionedDelete", new Class[0]);
      methodObject32207 = DatabaseMetaData.class.getDeclaredMethod("supportsCatalogsInProcedureCalls", new Class[0]);
      methodObject32164 = DatabaseMetaData.class.getDeclaredMethod("getSchemas", new Class[] { String.class, String.class });
      methodObject32108 = DatabaseMetaData.class.getDeclaredMethod("getCatalogSeparator", new Class[0]);
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1DatabaseMetaData$$$Proxy(DatabaseMetaData paramDatabaseMetaData, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramDatabaseMetaData;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableBase$2java$1sql$1DatabaseMetaData$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */