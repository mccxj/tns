package oracle.jdbc.connector;
import java.sql.SQLException;
import javax.resource.ResourceException;
import javax.resource.spi.EISSystemException;
import javax.resource.spi.ManagedConnectionMetaData;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDatabaseMetaData;
public class OracleManagedConnectionMetaData
  implements ManagedConnectionMetaData
{
/*  30 */   private OracleManagedConnection managedConnection = null;
/*  31 */   private OracleDatabaseMetaData databaseMetaData = null;
  
  OracleManagedConnectionMetaData(OracleManagedConnection paramOracleManagedConnection)
    throws ResourceException
  {
    try
    {
/*  42 */       this.managedConnection = paramOracleManagedConnection;
      
/*  44 */       OracleConnection localOracleConnection = (OracleConnection)paramOracleManagedConnection.getPhysicalConnection();
      
/*  46 */       this.databaseMetaData = ((OracleDatabaseMetaData)localOracleConnection.getMetaData());
    }
    catch (Exception localException)
    {
/*  50 */       EISSystemException localEISSystemException = new EISSystemException("Exception: " + localException.getMessage());
      
/*  53 */       localEISSystemException.setLinkedException(localException);
      
/*  55 */       throw localEISSystemException;
    }
  }
  
  public String getEISProductName()
    throws ResourceException
  {
    try
    {
/*  76 */       return this.databaseMetaData.getDatabaseProductName();
    }
    catch (SQLException localSQLException)
    {
/*  82 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/*  85 */       localEISSystemException.setLinkedException(localSQLException);
      
/*  87 */       throw localEISSystemException;
    }
  }
  
  public String getEISProductVersion()
    throws ResourceException
  {
    try
    {
/* 108 */       return this.databaseMetaData.getDatabaseProductVersion();
    }
    catch (Exception localException)
    {
/* 113 */       EISSystemException localEISSystemException = new EISSystemException("Exception: " + localException.getMessage());
      
/* 116 */       localEISSystemException.setLinkedException(localException);
      
/* 118 */       throw localEISSystemException;
    }
  }
  
  public int getMaxConnections()
    throws ResourceException
  {
    try
    {
/* 141 */       return this.databaseMetaData.getMaxConnections();
    }
    catch (SQLException localSQLException)
    {
/* 147 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 150 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 152 */       throw localEISSystemException;
    }
  }
  
  public String getUserName()
    throws ResourceException
  {
    try
    {
/* 175 */       return this.databaseMetaData.getUserName();
    }
    catch (SQLException localSQLException)
    {
/* 181 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 184 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 186 */       throw localEISSystemException;
    }
  }
  
/* 195 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleManagedConnectionMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */