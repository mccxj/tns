package oracle.jdbc.internal;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.BlobDBAccess;
public abstract interface OracleBlob
  extends OracleDatumWithConnection, oracle.jdbc.OracleBlob
{
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  /**
   * @deprecated
   */
  public abstract int putBytes(long paramLong, byte[] paramArrayOfByte)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract int putBytes(long paramLong, byte[] paramArrayOfByte, int paramInt)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract OutputStream getBinaryOutputStream()
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract OutputStream getBinaryOutputStream(long paramLong)
    throws SQLException;
  
  public abstract Reader characterStreamValue()
    throws SQLException;
  
  public abstract InputStream asciiStreamValue()
    throws SQLException;
  
  public abstract InputStream binaryStreamValue()
    throws SQLException;
  
  public abstract byte[] getLocator();
  
  public abstract void setLocator(byte[] paramArrayOfByte);
  
  public abstract int getChunkSize()
    throws SQLException;
  
  public abstract int getBufferSize()
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract void trim(long paramLong)
    throws SQLException;
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract BlobDBAccess getDBAccess()
    throws SQLException;
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
  
  public abstract void setLength(long paramLong);
  
  public abstract void setChunkSize(int paramInt);
  
  public abstract void setPrefetchedData(byte[] paramArrayOfByte);
  
  public abstract void setPrefetchedData(byte[] paramArrayOfByte, int paramInt);
  
  public abstract byte[] getPrefetchedData();
  
  public abstract int getPrefetchedDataSize();
  
  public abstract void setActivePrefetch(boolean paramBoolean);
  
  public abstract void clearCachedData();
  
  public abstract boolean isActivePrefetch();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */