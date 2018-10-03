package oracle.jdbc.xa;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import oracle.jdbc.pool.OraclePooledConnection;
public abstract class OracleXAConnection
  extends OraclePooledConnection
  implements XAConnection
{
/*  34 */   protected XAResource xaResource = null;
  
  public OracleXAConnection()
    throws XAException
  {
/*  43 */     this(null);
  }
  
  public OracleXAConnection(Connection paramConnection)
    throws XAException
  {
/*  60 */     super(paramConnection);
  }
  
  public abstract XAResource getXAResource();
  
  public synchronized Connection getConnection()
    throws SQLException
  {
/*  91 */     Connection localConnection = super.getConnection();
    
/*  93 */     if (this.xaResource != null) {
/*  94 */       ((OracleXAResource)this.xaResource).setLogicalConnection(localConnection);
    }
/*  96 */     return localConnection;
  }
  
  boolean getAutoCommit()
    throws SQLException
  {
/* 104 */     return this.autoCommit;
  }
  
  void setAutoCommit(boolean paramBoolean)
    throws SQLException
  {
/* 112 */     this.autoCommit = paramBoolean;
  }
  
/* 118 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleXAConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */