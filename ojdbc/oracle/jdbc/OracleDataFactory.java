package oracle.jdbc;
import java.sql.SQLException;
public abstract interface OracleDataFactory
{
  public abstract OracleData create(Object paramObject, int paramInt)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleDataFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */