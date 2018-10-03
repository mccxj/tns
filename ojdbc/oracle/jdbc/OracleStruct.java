package oracle.jdbc;
import java.sql.SQLException;
import java.sql.Struct;
public abstract interface OracleStruct
  extends Struct
{
  public abstract OracleTypeMetaData getOracleMetaData()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleStruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */