package oracle.jdbc.xa.client;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.driver.OracleCloseCallback;
import oracle.jdbc.internal.OracleConnection;
public class OracleXAHeteroCloseCallback
  implements OracleCloseCallback
{
  public synchronized void beforeClose(OracleConnection paramOracleConnection, Object paramObject) {}
  
  public synchronized void afterClose(Object paramObject)
  {
/*  53 */     int i = ((OracleXAHeteroConnection)paramObject).getRmid();
/*  54 */     String str = ((OracleXAHeteroConnection)paramObject).getXaCloseString();
    
    try
    {
/*  59 */       int j = t2cDoXaClose(str, i, 0, 0);
      
/*  65 */       if (j != 0)
      {
/*  68 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), -1 * j);
/*  69 */         localSQLException2.fillInStackTrace();
/*  70 */         throw localSQLException2;
      }
    }
    catch (SQLException localSQLException1) {}
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/*  93 */     return null;
  }
  
/* 101 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  private native int t2cDoXaClose(String paramString, int paramInt1, int paramInt2, int paramInt3);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXAHeteroCloseCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */