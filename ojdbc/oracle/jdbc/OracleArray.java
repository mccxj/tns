package oracle.jdbc;
import java.sql.Array;
import java.sql.SQLException;
public abstract interface OracleArray
  extends Array
{
  public abstract OracleTypeMetaData getOracleMetaData()
    throws SQLException;
  
  public abstract int length()
    throws SQLException;
  
  public abstract String getSQLTypeName()
    throws SQLException;
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract int[] getIntArray()
    throws SQLException;
  
  public abstract int[] getIntArray(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract double[] getDoubleArray()
    throws SQLException;
  
  public abstract double[] getDoubleArray(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract short[] getShortArray()
    throws SQLException;
  
  public abstract short[] getShortArray(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract long[] getLongArray()
    throws SQLException;
  
  public abstract long[] getLongArray(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract float[] getFloatArray()
    throws SQLException;
  
  public abstract float[] getFloatArray(long paramLong, int paramInt)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */