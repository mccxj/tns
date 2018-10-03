package oracle.jdbc.replay.internal;
import java.sql.Connection;
import java.sql.SQLException;
public abstract interface ConnectionInitializationCallback
{
  public abstract void initialize(Connection paramConnection)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/internal/ConnectionInitializationCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */