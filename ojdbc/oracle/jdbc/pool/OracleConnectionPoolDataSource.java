package oracle.jdbc.pool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import oracle.jdbc.internal.OracleConnection;
public class OracleConnectionPoolDataSource
  extends OracleDataSource
  implements ConnectionPoolDataSource
{
  public OracleConnectionPoolDataSource()
    throws SQLException
  {
/*  42 */     this.dataSourceName = "OracleConnectionPoolDataSource";
/*  43 */     this.isOracleDataSource = false;
    
/*  46 */     this.connCachingEnabled = false;
    
/*  49 */     this.fastConnFailover = false;
  }
  
  public PooledConnection getPooledConnection()
    throws SQLException
  {
/*  64 */     String str1 = null;
/*  65 */     String str2 = null;
/*  66 */     synchronized (this)
    {
/*  68 */       str1 = this.user;
/*  69 */       str2 = this.password;
    }
/*  71 */     return getPooledConnection(str1, str2);
  }
  
  public PooledConnection getPooledConnection(String paramString1, String paramString2)
    throws SQLException
  {
/*  89 */     Connection localConnection = getPhysicalConnection(paramString1, paramString2);
/*  90 */     OraclePooledConnection localOraclePooledConnection = new OraclePooledConnection(localConnection);
    
/*  93 */     if (paramString2 == null)
/*  94 */       paramString2 = this.password;
/*  95 */     localOraclePooledConnection.setUserName(!paramString1.startsWith("\"") ? paramString1.toLowerCase() : paramString1, paramString2);
    
/*  98 */     return localOraclePooledConnection;
  }
  
  PooledConnection getPooledConnection(Properties paramProperties)
    throws SQLException
  {
/* 106 */     Connection localConnection = getPhysicalConnection(paramProperties);
/* 107 */     OraclePooledConnection localOraclePooledConnection = new OraclePooledConnection(localConnection);
    
/* 109 */     String str1 = paramProperties.getProperty("user");
/* 110 */     if (str1 == null)
/* 111 */       str1 = ((OracleConnection)localConnection).getUserName();
/* 112 */     String str2 = paramProperties.getProperty("password");
/* 113 */     if (str2 == null)
/* 114 */       str2 = this.password;
/* 115 */     localOraclePooledConnection.setUserName(!str1.startsWith("\"") ? str1.toLowerCase() : str1, str2);
    
/* 118 */     return localOraclePooledConnection;
  }
  
  protected Connection getPhysicalConnection()
    throws SQLException
  {
/* 128 */     return super.getConnection(this.user, this.password);
  }
  
  protected Connection getPhysicalConnection(String paramString1, String paramString2, String paramString3)
    throws SQLException
  {
/* 139 */     this.url = paramString1;
/* 140 */     return super.getConnection(paramString2, paramString3);
  }
  
  protected Connection getPhysicalConnection(String paramString1, String paramString2)
    throws SQLException
  {
/* 151 */     return super.getConnection(paramString1, paramString2);
  }
  
/* 156 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleConnectionPoolDataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */