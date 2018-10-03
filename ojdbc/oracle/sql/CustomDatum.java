package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleConnection;
import oracle.jdbc.internal.ObjectData;
public abstract interface CustomDatum
  extends ObjectData
{
  public abstract Datum toDatum(OracleConnection paramOracleConnection)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CustomDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */