package oracle.jdbc;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
public abstract interface AdditionalDatabaseMetaData
  extends DatabaseMetaData
{
  public abstract OracleTypeMetaData getOracleTypeMetaData(String paramString)
    throws SQLException;
  
  public abstract long getLobMaxLength()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/AdditionalDatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */