package oracle.jdbc.replay.internal;
import java.sql.SQLException;
import oracle.jdbc.replay.OracleDataSource;
public abstract interface ReplayableConnection
  extends oracle.jdbc.replay.ReplayableConnection
{
  public abstract void setReplayInitiationTimeout(int paramInt)
    throws SQLException;
  
  public abstract void initialize(OracleDataSource paramOracleDataSource)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/internal/ReplayableConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */