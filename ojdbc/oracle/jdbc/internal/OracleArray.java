package oracle.jdbc.internal;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.ArrayDescriptor;
import oracle.sql.Datum;
public abstract interface OracleArray
  extends OracleDatumWithConnection, oracle.jdbc.OracleArray
{
  public static final int ACCESS_FORWARD = 1;
  public static final int ACCESS_REVERSE = 2;
  public static final int ACCESS_UNKNOWN = 3;
  
  public abstract Datum[] getOracleArray()
    throws SQLException;
  
  public abstract Datum[] getOracleArray(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract void setAutoBuffering(boolean paramBoolean)
    throws SQLException;
  
  public abstract boolean getAutoBuffering()
    throws SQLException;
  
  public abstract void setAutoIndexing(boolean paramBoolean, int paramInt)
    throws SQLException;
  
  public abstract void setAutoIndexing(boolean paramBoolean)
    throws SQLException;
  
  public abstract boolean getAutoIndexing()
    throws SQLException;
  
  public abstract int getAccessDirection()
    throws SQLException;
  
  public abstract ArrayDescriptor getDescriptor()
    throws SQLException;
  
  public abstract Map getMap()
    throws SQLException;
  
  public abstract byte[] toBytes()
    throws SQLException;
  
  public abstract void setDatumArray(Datum[] paramArrayOfDatum);
  
  public abstract void setObjArray(Object paramObject)
    throws SQLException;
  
  public abstract void setLocator(byte[] paramArrayOfByte);
  
  public abstract void setPrefixSegment(byte[] paramArrayOfByte);
  
  public abstract void setPrefixFlag(byte paramByte);
  
  public abstract byte[] getLocator();
  
  public abstract void setLength(int paramInt);
  
  public abstract boolean hasDataSeg();
  
  public abstract boolean isInline();
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract void setLastIndexOffset(long paramLong1, long paramLong2)
    throws SQLException;
  
  public abstract void setIndexOffset(long paramLong1, long paramLong2)
    throws SQLException;
  
  public abstract long getLastIndex()
    throws SQLException;
  
  public abstract long getLastOffset()
    throws SQLException;
  
  public abstract long getOffset(long paramLong)
    throws SQLException;
  
  public abstract void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws SQLException;
  
  public abstract void setImageLength(long paramLong)
    throws SQLException;
  
  public abstract long getImageOffset();
  
  public abstract long getImageLength();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */