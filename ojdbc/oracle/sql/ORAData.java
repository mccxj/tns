package oracle.sql;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.internal.ObjectData;
public abstract interface ORAData
  extends ObjectData
{
  public abstract Datum toDatum(Connection paramConnection)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ORAData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */