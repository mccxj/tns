package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.internal.ObjectDataFactory;
public abstract interface ORADataFactory
  extends ObjectDataFactory
{
  public abstract ORAData create(Datum paramDatum, int paramInt)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ORADataFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */