package oracle.jdbc.connector;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionEvent;
import javax.resource.spi.ConnectionEventListener;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.EISSystemException;
import javax.resource.spi.IllegalStateException;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionMetaData;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.xa.OracleXAConnection;
public class OracleManagedConnection
  implements ManagedConnection
{
/*  41 */   private OracleXAConnection xaConnection = null;
/*  42 */   private Hashtable connectionListeners = null;
/*  43 */   private Connection connection = null;
/*  44 */   private PrintWriter logWriter = null;
/*  45 */   private PasswordCredential passwordCredential = null;
/*  46 */   private OracleLocalTransaction localTxn = null;
  
  OracleManagedConnection(XAConnection paramXAConnection)
  {
/*  53 */     this.xaConnection = ((OracleXAConnection)paramXAConnection);
/*  54 */     this.connectionListeners = new Hashtable(10);
  }
  
  public Object getConnection(Subject paramSubject, ConnectionRequestInfo paramConnectionRequestInfo)
    throws ResourceException
  {
    try
    {
/*  87 */       if (this.connection != null) {
/*  88 */         this.connection.close();
      }
/*  90 */       this.connection = this.xaConnection.getConnection();
      
/*  92 */       return this.connection;
    }
    catch (SQLException localSQLException)
    {
/*  96 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/*  99 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 101 */       throw localEISSystemException;
    }
  }
  
  public void destroy()
    throws ResourceException
  {
    try
    {
/* 119 */       if (this.xaConnection != null)
      {
/* 123 */         Connection localConnection = this.xaConnection.getPhysicalHandle();
        
/* 127 */         if (((this.localTxn != null) && (this.localTxn.isBeginCalled)) || (((OracleConnection)localConnection).getTxnMode() == 1))
        {
/* 131 */           throw new IllegalStateException("Could not close connection while transaction is active");
        }
      }
      
/* 135 */       if (this.connection != null) {
/* 136 */         this.connection.close();
      }
/* 138 */       if (this.xaConnection != null) {
/* 139 */         this.xaConnection.close();
      }
    }
    catch (SQLException localSQLException)
    {
/* 144 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 147 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 149 */       throw localEISSystemException;
    }
  }
  
  public void cleanup()
    throws ResourceException
  {
    try
    {
/* 174 */       if (this.connection != null)
      {
/* 179 */         if (((this.localTxn != null) && (this.localTxn.isBeginCalled)) || (((OracleConnection)this.connection).getTxnMode() == 1))
        {
/* 183 */           throw new IllegalStateException("Could not close connection while transaction is active");
        }
        
/* 186 */         this.connection.close();
      }
      
    }
    catch (SQLException localSQLException)
    {
/* 192 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 195 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 197 */       throw localEISSystemException;
    }
  }
  
  public void associateConnection(Object paramObject) {}
  
  public void addConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
  {
/* 234 */     this.connectionListeners.put(paramConnectionEventListener, paramConnectionEventListener);
  }
  
  public void removeConnectionEventListener(ConnectionEventListener paramConnectionEventListener)
  {
/* 255 */     this.connectionListeners.remove(paramConnectionEventListener);
  }
  
  public XAResource getXAResource()
    throws ResourceException
  {
/* 276 */     return this.xaConnection.getXAResource();
  }
  
  public LocalTransaction getLocalTransaction()
    throws ResourceException
  {
/* 293 */     if (this.localTxn == null)
    {
/* 295 */       this.localTxn = new OracleLocalTransaction(this);
    }
    
/* 298 */     return this.localTxn;
  }
  
  public ManagedConnectionMetaData getMetaData()
    throws ResourceException
  {
/* 314 */     return new OracleManagedConnectionMetaData(this);
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter)
    throws ResourceException
  {
/* 331 */     this.logWriter = paramPrintWriter;
  }
  
  public PrintWriter getLogWriter()
    throws ResourceException
  {
/* 347 */     return this.logWriter;
  }
  
  Connection getPhysicalConnection()
    throws ResourceException
  {
    try
    {
/* 358 */       return this.xaConnection.getPhysicalHandle();
    }
    catch (Exception localException)
    {
/* 363 */       EISSystemException localEISSystemException = new EISSystemException("Exception: " + localException.getMessage());
      
/* 366 */       localEISSystemException.setLinkedException(localException);
      
/* 368 */       throw localEISSystemException;
    }
  }
  
  void setPasswordCredential(PasswordCredential paramPasswordCredential)
  {
/* 379 */     this.passwordCredential = paramPasswordCredential;
  }
  
  PasswordCredential getPasswordCredential()
  {
/* 388 */     return this.passwordCredential;
  }
  
  void eventOccurred(int paramInt)
    throws ResourceException
  {
/* 397 */     Enumeration localEnumeration = this.connectionListeners.keys();
    
/* 399 */     while (localEnumeration.hasMoreElements())
    {
/* 403 */       ConnectionEventListener localConnectionEventListener = (ConnectionEventListener)localEnumeration.nextElement();
      
/* 407 */       ConnectionEvent localConnectionEvent = new ConnectionEvent(this, paramInt);
      
/* 409 */       switch (paramInt)
      {
      case 1: 
/* 414 */         localConnectionEventListener.connectionClosed(localConnectionEvent);
        
/* 416 */         break;
      
      case 2: 
/* 420 */         localConnectionEventListener.localTransactionStarted(localConnectionEvent);
        
/* 422 */         break;
      
      case 3: 
/* 425 */         localConnectionEventListener.localTransactionCommitted(localConnectionEvent);
        
/* 427 */         break;
      
      case 4: 
/* 430 */         localConnectionEventListener.localTransactionRolledback(localConnectionEvent);
        
/* 432 */         break;
      
      case 5: 
/* 436 */         localConnectionEventListener.connectionErrorOccurred(localConnectionEvent);
        
/* 438 */         break;
      
      default: 
/* 441 */         throw new IllegalArgumentException("Illegal eventType in eventOccurred(): " + paramInt);
      }
      
    }
  }
  
/* 450 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleManagedConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */