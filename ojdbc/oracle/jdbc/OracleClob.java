package oracle.jdbc;
import java.sql.Clob;
import java.sql.SQLException;
public abstract interface OracleClob
  extends Clob
{
  public abstract void open(LargeObjectAccessMode paramLargeObjectAccessMode)
    throws SQLException;
  
  public abstract void close()
    throws SQLException;
  
  public abstract boolean isOpen()
    throws SQLException;
  
  public abstract boolean isTemporary()
    throws SQLException;
  
  public abstract boolean isEmptyLob()
    throws SQLException;
  
  public abstract boolean isSecureFile()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */