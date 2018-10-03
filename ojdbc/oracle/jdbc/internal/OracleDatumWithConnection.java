package oracle.jdbc.internal;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
public abstract interface OracleDatumWithConnection
{
  public abstract byte[] shareBytes();
  
  public abstract long getLength();
  
  public abstract void setBytes(byte[] paramArrayOfByte);
  
  public abstract void setShareBytes(byte[] paramArrayOfByte);
  
  public abstract byte[] getBytes();
  
  public abstract InputStream getStream()
    throws SQLException;
  
  public abstract String stringValue()
    throws SQLException;
  
  public abstract String stringValue(Connection paramConnection)
    throws SQLException;
  
  public abstract boolean booleanValue()
    throws SQLException;
  
  public abstract int intValue()
    throws SQLException;
  
  public abstract long longValue()
    throws SQLException;
  
  public abstract float floatValue()
    throws SQLException;
  
  public abstract double doubleValue()
    throws SQLException;
  
  public abstract byte byteValue()
    throws SQLException;
  
  public abstract BigDecimal bigDecimalValue()
    throws SQLException;
  
  public abstract Date dateValue()
    throws SQLException;
  
  public abstract Time timeValue()
    throws SQLException;
  
  public abstract Time timeValue(Calendar paramCalendar)
    throws SQLException;
  
  public abstract Timestamp timestampValue()
    throws SQLException;
  
  public abstract Timestamp timestampValue(Calendar paramCalendar)
    throws SQLException;
  
  public abstract Reader characterStreamValue()
    throws SQLException;
  
  public abstract InputStream asciiStreamValue()
    throws SQLException;
  
  public abstract InputStream binaryStreamValue()
    throws SQLException;
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract Object makeJdbcArray(int paramInt);
  
  public abstract Connection getJavaSqlConnection()
    throws SQLException;
  
  public abstract oracle.jdbc.OracleConnection getOracleConnection()
    throws SQLException;
  
  public abstract OracleConnection getInternalConnection()
    throws SQLException;
  
  public abstract oracle.jdbc.driver.OracleConnection getConnection()
    throws SQLException;
  
  public abstract void setPhysicalConnectionOf(Connection paramConnection);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OracleDatumWithConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */