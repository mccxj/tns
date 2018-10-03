package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
abstract interface ScrollRsetStatement
{
  public abstract Connection getConnection()
    throws SQLException;
  
  public abstract void notifyCloseRset()
    throws SQLException;
  
  public abstract int copyBinds(Statement paramStatement, int paramInt)
    throws SQLException;
  
  public abstract String getOriginalSql()
    throws SQLException;
  
  public abstract OracleResultSetCache getResultSetCache()
    throws SQLException;
  
  public abstract int getMaxFieldSize()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ScrollRsetStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */