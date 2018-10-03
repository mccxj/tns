package oracle.jdbc.xa.client;
import java.sql.Connection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
public class OracleXAConnection
  extends oracle.jdbc.xa.OracleXAConnection
{
/*  39 */   protected boolean isXAResourceTransLoose = false;
  
  public OracleXAConnection()
    throws XAException
  {}
  
  public OracleXAConnection(Connection paramConnection)
    throws XAException
  {
/*  63 */     super(paramConnection);
  }
  
  public synchronized XAResource getXAResource()
  {
    try
    {
/*  81 */       if (this.xaResource == null)
      {
/*  83 */         this.xaResource = new OracleXAResource(this.physicalConn, this);
/*  84 */         ((OracleXAResource)this.xaResource).isTransLoose = this.isXAResourceTransLoose;
        
/*  86 */         if (this.logicalHandle != null)
        {
/*  90 */           ((oracle.jdbc.xa.OracleXAResource)this.xaResource).setLogicalConnection(this.logicalHandle);
        }
      }
    }
    catch (XAException localXAException)
    {
/*  96 */       this.xaResource = null;
    }
    
/*  99 */     return this.xaResource;
  }
  
/* 104 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXAConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */