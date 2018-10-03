package oracle.jdbc.aq;
import java.sql.SQLException;
public abstract interface AQAgent
{
  public abstract void setAddress(String paramString)
    throws SQLException;
  
  public abstract String getAddress();
  
  public abstract void setName(String paramString)
    throws SQLException;
  
  public abstract String getName();
  
  public abstract void setProtocol(int paramInt)
    throws SQLException;
  
  public abstract int getProtocol();
  
  public abstract String toString();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQAgent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */