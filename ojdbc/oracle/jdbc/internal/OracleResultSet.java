package oracle.jdbc.internal;
import java.sql.SQLException;
public abstract interface OracleResultSet
  extends oracle.jdbc.OracleResultSet
{
  public abstract void closeStatementOnClose()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */