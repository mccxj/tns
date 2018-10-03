package oracle.jdbc.aq;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import oracle.jdbc.NotificationRegistration;
public abstract interface AQNotificationRegistration
  extends NotificationRegistration
{
  public abstract void addListener(AQNotificationListener paramAQNotificationListener)
    throws SQLException;
  
  public abstract void addListener(AQNotificationListener paramAQNotificationListener, Executor paramExecutor)
    throws SQLException;
  
  public abstract void removeListener(AQNotificationListener paramAQNotificationListener)
    throws SQLException;
  
  public abstract String getQueueName();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQNotificationRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */