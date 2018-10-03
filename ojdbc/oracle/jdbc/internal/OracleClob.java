package oracle.jdbc.internal;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.ClobDBAccess;
public abstract interface OracleClob
  extends OracleDatumWithConnection, oracle.jdbc.OracleClob
{
  public abstract InputStream getAsciiStream(long paramLong)
    throws SQLException;
  
  public abstract Reader getCharacterStream(long paramLong)
    throws SQLException;
  
  public abstract boolean isNCLOB();
  
  public abstract int getChars(long paramLong, int paramInt, char[] paramArrayOfChar)
    throws SQLException;
  
  public abstract int putChars(long paramLong, char[] paramArrayOfChar)
    throws SQLException;
  
  public abstract int putChars(long paramLong, char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract int putString(long paramLong, String paramString)
    throws SQLException;
  
  public abstract int getChunkSize()
    throws SQLException;
  
  public abstract int getBufferSize()
    throws SQLException;
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Reader characterStreamValue()
    throws SQLException;
  
  public abstract InputStream asciiStreamValue()
    throws SQLException;
  
  public abstract InputStream binaryStreamValue()
    throws SQLException;
  
  public abstract String stringValue()
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract void trim(long paramLong)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract OutputStream getAsciiOutputStream(long paramLong)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract Writer getCharacterOutputStream(long paramLong)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract Writer getCharacterOutputStream()
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract OutputStream getAsciiOutputStream()
    throws SQLException;
  
  public abstract byte[] getLocator();
  
  public abstract void setLocator(byte[] paramArrayOfByte);
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract ClobDBAccess getDBAccess()
    throws SQLException;
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
  
  public abstract void setLength(long paramLong);
  
  public abstract void setChunkSize(int paramInt);
  
  public abstract void setPrefetchedData(char[] paramArrayOfChar);
  
  public abstract void setPrefetchedData(char[] paramArrayOfChar, int paramInt);
  
  public abstract char[] getPrefetchedData();
  
  public abstract int getPrefetchedDataSize();
  
  public abstract void setActivePrefetch(boolean paramBoolean);
  
  public abstract void clearCachedData();
  
  public abstract boolean isActivePrefetch();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */