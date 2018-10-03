package oracle.jdbc.internal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import oracle.sql.Datum;
import oracle.sql.ORADataFactory;
import oracle.sql.StructDescriptor;
public abstract interface OracleStruct
  extends OracleDatumWithConnection, oracle.jdbc.OracleStruct
{
  public abstract StructDescriptor getDescriptor()
    throws SQLException;
  
  public abstract void setDescriptor(StructDescriptor paramStructDescriptor);
  
  public abstract Datum[] getOracleAttributes()
    throws SQLException;
  
  public abstract Map getMap();
  
  public abstract byte[] toBytes()
    throws SQLException;
  
  public abstract void setDatumArray(Datum[] paramArrayOfDatum);
  
  public abstract void setObjArray(Object[] paramArrayOfObject)
    throws SQLException;
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract Object toJdbc(Map paramMap)
    throws SQLException;
  
  public abstract Object toClass(Class paramClass)
    throws SQLException;
  
  public abstract Object toClass(Class paramClass, Map paramMap)
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract void setAutoBuffering(boolean paramBoolean)
    throws SQLException;
  
  public abstract boolean getAutoBuffering()
    throws SQLException;
  
  public abstract void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws SQLException;
  
  public abstract void setImageLength(long paramLong)
    throws SQLException;
  
  public abstract long getImageOffset();
  
  public abstract long getImageLength();
  
  public abstract ORADataFactory getORADataFactory(Hashtable paramHashtable, String paramString)
    throws SQLException;
  
  public abstract boolean isInHierarchyOf(String paramString)
    throws SQLException;
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleStruct.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */