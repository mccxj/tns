package oracle.jdbc.replay;
import java.sql.SQLException;
public abstract interface ReplayableConnection
{
  public abstract void beginRequest()
    throws SQLException;
  
  public abstract void endRequest()
    throws SQLException;
  
  public abstract void disableReplay()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/ReplayableConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */