package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.sql.OPAQUE;
public abstract interface Opaqueable
{
  public abstract OPAQUE toOpaque()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/Opaqueable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */