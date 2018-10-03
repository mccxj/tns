package oracle.jdbc.xa.client;
import java.sql.Connection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import oracle.jdbc.xa.OracleXAResource;
public class OracleXAHeteroConnection
  extends OracleXAConnection
{
/*  36 */   private int rmid = -1;
/*  37 */   private String xaCloseString = null;
  
  public OracleXAHeteroConnection()
    throws XAException
  {}
  
  public OracleXAHeteroConnection(Connection paramConnection)
    throws XAException
  {
/*  62 */     super(paramConnection);
  }
  
  public synchronized XAResource getXAResource()
  {
    try
    {
/*  81 */       if (this.xaResource == null)
      {
/*  87 */         this.xaResource = new OracleXAHeteroResource(this.physicalConn, this);
        
/*  93 */         ((OracleXAHeteroResource)this.xaResource).setRmid(this.rmid);
        
/*  95 */         if (this.logicalHandle != null)
        {
/*  99 */           ((OracleXAResource)this.xaResource).setLogicalConnection(this.logicalHandle);
        }
      }
    }
    catch (XAException localXAException)
    {
/* 105 */       this.xaResource = null;
    }
    
/* 108 */     return this.xaResource;
  }
  
  synchronized void setRmid(int paramInt)
  {
/* 124 */     this.rmid = paramInt;
  }
  
  synchronized int getRmid()
  {
/* 139 */     return this.rmid;
  }
  
  synchronized void setXaCloseString(String paramString)
  {
/* 155 */     this.xaCloseString = paramString;
  }
  
  synchronized String getXaCloseString()
  {
/* 170 */     return this.xaCloseString;
  }
  
/* 175 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXAHeteroConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */