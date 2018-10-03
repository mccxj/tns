package oracle.jdbc.replay;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.replay.internal.ConnectionInitializationCallback;
public abstract interface OracleDataSource
  extends DataSource
{
  public static final String USER = "user";
  public static final String URL = "url";
  public static final String PASSWORD = "password";
  public static final String SERVER_NAME = "serverName";
  public static final String PORT_NUMBER = "portNumber";
  public static final String DATABASE_NAME = "databaseName";
  public static final String DATA_SOURCE_NAME = "dataSourceName";
  public static final String DESCRIPTION = "description";
  public static final String NETWORK_PROTOCOL = "networkProtocol";
  public static final String ROLE_NAME = "roleName";
  public static final String CONNECTION_PROPERTIES = "connectionProperties";
  public static final String MAX_STATEMENTS = "maxStatements";
  public static final String IMPLICIT_CACHING_ENABLED = "implicitCachingEnabled";
  public static final String EXPLICIT_CACHING_ENABLED = "explicitCachingEnabled";
  
  public abstract void setURL(String paramString)
    throws SQLException;
  
  public abstract String getURL();
  
  public abstract void setUser(String paramString)
    throws SQLException;
  
  public abstract String getUser();
  
  public abstract void setPassword(String paramString)
    throws SQLException;
  
  public abstract void setServerName(String paramString)
    throws SQLException;
  
  public abstract String getServerName();
  
  public abstract void setPortNumber(int paramInt)
    throws SQLException;
  
  public abstract int getPortNumber();
  
  public abstract void setDatabaseName(String paramString)
    throws SQLException;
  
  public abstract String getDatabaseName();
  
  public abstract void setDataSourceName(String paramString)
    throws SQLException;
  
  public abstract String getDataSourceName();
  
  public abstract void setDescription(String paramString)
    throws SQLException;
  
  public abstract String getDescription();
  
  public abstract void setNetworkProtocol(String paramString)
    throws SQLException;
  
  public abstract String getNetworkProtocol();
  
  public abstract void setRoleName(String paramString)
    throws SQLException;
  
  public abstract String getRoleName();
  
  public abstract void registerConnectionInitializationCallback(ConnectionInitializationCallback paramConnectionInitializationCallback)
    throws SQLException;
  
  public abstract void unregisterConnectionInitializationCallback(ConnectionInitializationCallback paramConnectionInitializationCallback)
    throws SQLException;
  
  public abstract ConnectionInitializationCallback getConnectionInitializationCallback();
  
  public abstract Properties getConnectionProperties();
  
  public abstract String getConnectionProperty(String paramString);
  
  public abstract void setConnectionProperty(String paramString1, String paramString2)
    throws SQLException;
  
  public abstract void setConnectionProperties(Properties paramProperties)
    throws SQLException;
  
  public abstract void setMaxStatements(int paramInt)
    throws SQLException;
  
  public abstract int getMaxStatements()
    throws SQLException;
  
  public abstract void setImplicitCachingEnabled(boolean paramBoolean)
    throws SQLException;
  
  public abstract boolean getImplicitCachingEnabled()
    throws SQLException;
  
  public abstract void setExplicitCachingEnabled(boolean paramBoolean)
    throws SQLException;
  
  public abstract boolean getExplicitCachingEnabled()
    throws SQLException;
  
  public abstract Connection getConnectionNoProxy()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/OracleDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */