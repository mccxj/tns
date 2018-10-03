package oracle.jdbc;
import java.sql.Connection;
import java.sql.SQLException;
public abstract interface OracleData
{
  public abstract Object toJDBCObject(Connection paramConnection)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */