package oracle.jdbc.aq;
import java.util.EventListener;
public abstract interface AQNotificationListener
  extends EventListener
{
  public abstract void onAQNotification(AQNotificationEvent paramAQNotificationEvent);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQNotificationListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */