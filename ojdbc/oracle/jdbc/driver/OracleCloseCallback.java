package oracle.jdbc.driver;
import oracle.jdbc.internal.OracleConnection;
public abstract interface OracleCloseCallback
{
  public abstract void beforeClose(OracleConnection paramOracleConnection, Object paramObject);
  
  public abstract void afterClose(Object paramObject);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleCloseCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */