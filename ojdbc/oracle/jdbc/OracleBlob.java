package oracle.jdbc;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
public abstract interface OracleBlob
  extends Blob
{
  public abstract void open(LargeObjectAccessMode paramLargeObjectAccessMode)
    throws SQLException;
  
  public abstract void close()
    throws SQLException;
  
  public abstract boolean isOpen()
    throws SQLException;
  
  public abstract int getBytes(long paramLong, int paramInt, byte[] paramArrayOfByte)
    throws SQLException;
  
  public abstract boolean isEmptyLob()
    throws SQLException;
  
  public abstract boolean isSecureFile()
    throws SQLException;
  
  public abstract InputStream getBinaryStream(long paramLong)
    throws SQLException;
  
  public abstract boolean isTemporary()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */