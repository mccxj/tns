package oracle.jdbc.internal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.OpaqueDescriptor;
public abstract interface OracleOpaque
  extends OracleDatumWithConnection, oracle.jdbc.OracleOpaque
{
  public abstract OpaqueDescriptor getDescriptor()
    throws SQLException;
  
  public abstract void setDescriptor(OpaqueDescriptor paramOpaqueDescriptor);
  
  public abstract byte[] toBytes()
    throws SQLException;
  
  public abstract byte[] getBytesValue()
    throws SQLException;
  
  public abstract void setValue(byte[] paramArrayOfByte)
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract Map getMap();
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract Object toJdbc(Map paramMap)
    throws SQLException;
  
  public abstract Object toClass(Class paramClass)
    throws SQLException;
  
  public abstract Object toClass(Class paramClass, Map paramMap)
    throws SQLException;
  
  public abstract void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws SQLException;
  
  public abstract void setImageLength(long paramLong)
    throws SQLException;
  
  public abstract long getImageOffset();
  
  public abstract long getImageLength();
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleOpaque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */