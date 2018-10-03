package oracle.jdbc.pool;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
public abstract interface OracleConnectionCache
  extends DataSource
{
  public abstract void reusePooledConnection(PooledConnection paramPooledConnection)
    throws SQLException;
  
  public abstract void closePooledConnection(PooledConnection paramPooledConnection)
    throws SQLException;
  
  public abstract void close()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleConnectionCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */