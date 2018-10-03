package oracle.jdbc;
import java.sql.Ref;
import java.sql.SQLException;
public abstract interface OracleRef
  extends Ref
{
  public abstract OracleTypeMetaData getOracleMetaData()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */