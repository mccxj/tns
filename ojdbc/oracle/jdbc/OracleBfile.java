package oracle.jdbc;
import java.io.InputStream;
import java.sql.SQLException;
public abstract interface OracleBfile
{
  public abstract long length()
    throws SQLException;
  
  public abstract byte[] getBytes(long paramLong, int paramInt)
    throws SQLException;
  
  public abstract int getBytes(long paramLong, int paramInt, byte[] paramArrayOfByte)
    throws SQLException;
  
  public abstract InputStream getBinaryStream()
    throws SQLException;
  
  public abstract InputStream getBinaryStream(long paramLong)
    throws SQLException;
  
  public abstract long position(byte[] paramArrayOfByte, long paramLong)
    throws SQLException;
  
  public abstract long position(OracleBfile paramOracleBfile, long paramLong)
    throws SQLException;
  
  public abstract String getName()
    throws SQLException;
  
  public abstract String getDirAlias()
    throws SQLException;
  
  public abstract void openFile()
    throws SQLException;
  
  public abstract boolean isFileOpen()
    throws SQLException;
  
  public abstract boolean fileExists()
    throws SQLException;
  
  public abstract void closeFile()
    throws SQLException;
  
  public abstract void open(LargeObjectAccessMode paramLargeObjectAccessMode)
    throws SQLException;
  
  public abstract void close()
    throws SQLException;
  
  public abstract boolean isOpen()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleBfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */