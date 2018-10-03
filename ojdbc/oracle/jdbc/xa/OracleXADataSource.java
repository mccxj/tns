package oracle.jdbc.xa;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import oracle.jdbc.pool.OracleDataSource;
public abstract class OracleXADataSource
  extends OracleConnectionPoolDataSource
  implements XADataSource
{
/*  35 */   protected boolean useNativeXA = false;
/*  36 */   protected boolean thinUseNativeXA = true;
  
  public OracleXADataSource()
    throws SQLException
  {
/*  49 */     this.dataSourceName = "OracleXADataSource";
  }
  
  public abstract XAConnection getXAConnection()
    throws SQLException;
  
  public abstract XAConnection getXAConnection(String paramString1, String paramString2)
    throws SQLException;
  
  public abstract XAConnection getXAConnection(Properties paramProperties)
    throws SQLException;
  
  public synchronized void setNativeXA(boolean paramBoolean)
  {
/*  92 */     this.useNativeXA = paramBoolean;
/*  93 */     this.thinUseNativeXA = paramBoolean;
  }
  
  public synchronized boolean getNativeXA()
  {
/* 108 */     return this.useNativeXA;
  }
  
  protected void copy(OracleDataSource paramOracleDataSource)
    throws SQLException
  {
/* 117 */     super.copy(paramOracleDataSource);
    
/* 119 */     ((OracleXADataSource)paramOracleDataSource).useNativeXA = this.useNativeXA;
/* 120 */     ((OracleXADataSource)paramOracleDataSource).thinUseNativeXA = this.thinUseNativeXA;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 136 */     return null;
  }
  
/* 141 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleXADataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */