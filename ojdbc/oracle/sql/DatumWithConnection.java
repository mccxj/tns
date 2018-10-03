package oracle.sql;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.internal.OracleDatumWithConnection;
public abstract class DatumWithConnection
  extends Datum
  implements OracleDatumWithConnection
{
/*  31 */   private oracle.jdbc.internal.OracleConnection physicalConnection = null;
  
  oracle.jdbc.internal.OracleConnection getPhysicalConnection()
  {
/*  41 */     if (this.physicalConnection == null)
    {
      try
      {
/*  56 */         this.physicalConnection = ((oracle.jdbc.internal.OracleConnection)new OracleDriver().defaultConnection());
      }
      catch (SQLException localSQLException) {}
    }
    
/*  69 */     return this.physicalConnection;
  }
  
  public DatumWithConnection(byte[] paramArrayOfByte)
    throws SQLException
  {
/*  75 */     super(paramArrayOfByte);
  }
  
  public DatumWithConnection() {}
  
  public static void assertNotNull(Connection paramConnection)
    throws SQLException
  {
/*  90 */     if (paramConnection == null)
    {
/*  92 */       SQLException localSQLException = DatabaseError.createSqlException(null, 68, "Connection is null");
/*  93 */       localSQLException.fillInStackTrace();
/*  94 */       throw localSQLException;
    }
  }
  
  public static void assertNotNull(TypeDescriptor paramTypeDescriptor)
    throws SQLException
  {
/* 102 */     if (paramTypeDescriptor == null)
    {
/* 104 */       SQLException localSQLException = DatabaseError.createSqlException(null, 61);
/* 105 */       localSQLException.fillInStackTrace();
/* 106 */       throw localSQLException;
    }
  }
  
  public void setPhysicalConnectionOf(Connection paramConnection)
  {
/* 121 */     this.physicalConnection = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
/* 141 */     return getPhysicalConnection().getWrapper();
  }
  
  public oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException
  {
/* 159 */     return getPhysicalConnection().getWrapper();
  }
  
  public oracle.jdbc.internal.OracleConnection getInternalConnection()
    throws SQLException
  {
/* 180 */     return getPhysicalConnection();
  }
  
  /**
   * @deprecated
   */
  public oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException
  {
/* 202 */     oracle.jdbc.driver.OracleConnection localOracleConnection = null;
    
    try
    {
/* 206 */       localOracleConnection = (oracle.jdbc.driver.OracleConnection)((oracle.jdbc.driver.OracleConnection)this.physicalConnection).getWrapper();
    }
    catch (ClassCastException localClassCastException)
    {
/* 211 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 103);
/* 212 */       localSQLException.fillInStackTrace();
/* 213 */       throw localSQLException;
    }
    
/* 216 */     return localOracleConnection;
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 231 */     return this.physicalConnection;
  }
  
/* 236 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/DatumWithConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */