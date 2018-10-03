package oracle.jdbc.rowset;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;
public abstract interface OraclePredicate
  extends Predicate
{
  public abstract boolean evaluate(RowSet paramRowSet);
  
  public abstract boolean evaluate(Object paramObject, int paramInt)
    throws SQLException;
  
  public abstract boolean evaluate(Object paramObject, String paramString)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OraclePredicate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */