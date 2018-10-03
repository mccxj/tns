package oracle.jdbc.pool;
import java.io.Serializable;
import java.sql.SQLException;
import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
/**
 * @deprecated
 */
class OracleConnectionCacheEventListener
  implements ConnectionEventListener, Serializable
{
  static final int CONNECTION_CLOSED_EVENT = 101;
  static final int CONNECTION_ERROROCCURED_EVENT = 102;
/*  41 */   protected OracleImplicitConnectionCache implicitCache = null;
  
  public OracleConnectionCacheEventListener()
  {
/*  46 */     this(null);
  }
  
  public OracleConnectionCacheEventListener(OracleImplicitConnectionCache paramOracleImplicitConnectionCache)
  {
/*  55 */     this.implicitCache = paramOracleImplicitConnectionCache;
  }
  
  public void connectionClosed(ConnectionEvent paramConnectionEvent)
  {
    try
    {
/*  72 */       if (this.implicitCache != null)
      {
/*  74 */         this.implicitCache.reusePooledConnection((PooledConnection)paramConnectionEvent.getSource());
      }
    }
    catch (SQLException localSQLException) {}
  }
  
  public void connectionErrorOccurred(ConnectionEvent paramConnectionEvent)
  {
    try
    {
/*  92 */       if (this.implicitCache != null)
      {
/*  94 */         this.implicitCache.closePooledConnection((PooledConnection)paramConnectionEvent.getSource());
      }
    }
    catch (SQLException localSQLException) {}
  }
  
/* 105 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleConnectionCacheEventListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */