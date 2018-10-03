package oracle.jdbc;
import java.io.IOException;
public abstract interface OracleResultSetCache
{
  public abstract void put(int paramInt1, int paramInt2, Object paramObject)
    throws IOException;
  
  public abstract Object get(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void remove(int paramInt)
    throws IOException;
  
  public abstract void remove(int paramInt1, int paramInt2)
    throws IOException;
  
  public abstract void clear()
    throws IOException;
  
  public abstract void close()
    throws IOException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleResultSetCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */