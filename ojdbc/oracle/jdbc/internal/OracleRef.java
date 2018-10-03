package oracle.jdbc.internal;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public abstract interface OracleRef
  extends OracleDatumWithConnection, oracle.jdbc.OracleRef
{
  public abstract Object getValue(Map paramMap)
    throws SQLException;
  
  public abstract Object getValue()
    throws SQLException;
  
  public abstract STRUCT getSTRUCT()
    throws SQLException;
  
  public abstract void setValue(Object paramObject)
    throws SQLException;
  
  public abstract StructDescriptor getDescriptor()
    throws SQLException;
  
  public abstract String getSQLTypeName()
    throws SQLException;
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract Object clone()
    throws CloneNotSupportedException;
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */