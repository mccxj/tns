package oracle.jdbc;
import java.sql.SQLException;
public abstract interface StructMetaData
  extends OracleResultSetMetaData
{
  public abstract String getAttributeJavaName(int paramInt)
    throws SQLException;
  
  public abstract String getOracleColumnClassName(int paramInt)
    throws SQLException;
  
  public abstract boolean isInherited(int paramInt)
    throws SQLException;
  
  public abstract int getLocalColumnCount()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/StructMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */