package oracle.jdbc;
import java.sql.SQLException;
public abstract interface OracleOpaque
{
  public abstract OracleTypeMetaData getOracleMetaData()
    throws SQLException;
  
  public abstract String getSQLTypeName()
    throws SQLException;
  
  public abstract Object getValue()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleOpaque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */