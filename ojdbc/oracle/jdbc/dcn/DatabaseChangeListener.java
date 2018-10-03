package oracle.jdbc.dcn;
import java.util.EventListener;
public abstract interface DatabaseChangeListener
  extends EventListener
{
  public abstract void onDatabaseChangeNotification(DatabaseChangeEvent paramDatabaseChangeEvent);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/dcn/DatabaseChangeListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */