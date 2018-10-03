package oracle.jdbc.internal;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.BFILE;
import oracle.sql.BfileDBAccess;
public abstract interface OracleBfile
  extends OracleDatumWithConnection, oracle.jdbc.OracleBfile
{
  public abstract long position(BFILE paramBFILE, long paramLong)
    throws SQLException;
  
  public abstract byte[] getLocator();
  
  public abstract void setLocator(byte[] paramArrayOfByte);
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Reader characterStreamValue()
    throws SQLException;
  
  public abstract InputStream asciiStreamValue()
    throws SQLException;
  
  public abstract InputStream binaryStreamValue()
    throws SQLException;
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract BfileDBAccess getDBAccess()
    throws SQLException;
  
  public abstract void setLength(long paramLong);
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleBfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */