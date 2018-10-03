package oracle.jdbc.pool;
import oracle.jdbc.OracleConnection;
/**
 * @deprecated
 */
public abstract interface OracleConnectionCacheCallback
{
  /**
   * @deprecated
   */
  public abstract boolean handleAbandonedConnection(OracleConnection paramOracleConnection, Object paramObject);
  
  /**
   * @deprecated
   */
  public abstract void releaseConnection(OracleConnection paramOracleConnection, Object paramObject);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleConnectionCacheCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */