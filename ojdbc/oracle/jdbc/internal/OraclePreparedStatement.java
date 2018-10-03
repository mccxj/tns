package oracle.jdbc.internal;
import java.io.Reader;
import java.sql.SQLException;
public abstract interface OraclePreparedStatement
  extends oracle.jdbc.OraclePreparedStatement, OracleStatement
{
  public abstract void setCheckBindTypes(boolean paramBoolean);
  
  public abstract void setInternalBytes(int paramInt1, byte[] paramArrayOfByte, int paramInt2)
    throws SQLException;
  
  public abstract void enterImplicitCache()
    throws SQLException;
  
  public abstract void enterExplicitCache()
    throws SQLException;
  
  public abstract void exitImplicitCacheToActive()
    throws SQLException;
  
  public abstract void exitExplicitCacheToActive()
    throws SQLException;
  
  public abstract void exitImplicitCacheToClose()
    throws SQLException;
  
  public abstract void exitExplicitCacheToClose()
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract void setCharacterStreamAtName(String paramString, Reader paramReader, int paramInt)
    throws SQLException;
  
  public abstract String getOriginalSql()
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/OraclePreparedStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */