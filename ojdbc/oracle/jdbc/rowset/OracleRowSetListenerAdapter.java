package oracle.jdbc.rowset;
import java.io.Serializable;
import javax.sql.RowSetEvent;
import javax.sql.RowSetListener;
public abstract class OracleRowSetListenerAdapter
  implements RowSetListener, Serializable
{
  public void cursorMoved(RowSetEvent paramRowSetEvent) {}
  
  public void rowChanged(RowSetEvent paramRowSetEvent) {}
  
  public void rowSetChanged(RowSetEvent paramRowSetEvent) {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleRowSetListenerAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */