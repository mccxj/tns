package oracle.jdbc.connector;
import java.sql.Connection;
import java.sql.SQLException;
import javax.resource.ResourceException;
import javax.resource.spi.EISSystemException;
import javax.resource.spi.IllegalStateException;
import javax.resource.spi.LocalTransaction;
import javax.resource.spi.LocalTransactionException;
import oracle.jdbc.internal.OracleConnection;
public class OracleLocalTransaction
  implements LocalTransaction
{
/*  32 */   private OracleManagedConnection managedConnection = null;
/*  33 */   private Connection connection = null;
/*  34 */   boolean isBeginCalled = false;
  
  private static final String RAERR_LTXN_COMMIT = "commit without begin";
  
  private static final String RAERR_LTXN_ROLLBACK = "rollback without begin";
  
  OracleLocalTransaction(OracleManagedConnection paramOracleManagedConnection)
    throws ResourceException
  {
/*  45 */     this.managedConnection = paramOracleManagedConnection;
/*  46 */     this.connection = paramOracleManagedConnection.getPhysicalConnection();
/*  47 */     this.isBeginCalled = false;
  }
  
  public void begin()
    throws ResourceException
  {
    try
    {
/*  71 */       if (((OracleConnection)this.connection).getTxnMode() == 1)
      {
/*  74 */         throw new IllegalStateException("Could not start a new transaction inside an active transaction");
      }
      
/*  77 */       if (this.connection.getAutoCommit()) {
/*  78 */         this.connection.setAutoCommit(false);
      }
/*  80 */       this.isBeginCalled = true;
    }
    catch (SQLException localSQLException)
    {
/*  85 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/*  88 */       localEISSystemException.setLinkedException(localSQLException);
      
/*  90 */       throw localEISSystemException;
    }
    
/*  94 */     this.managedConnection.eventOccurred(2);
  }
  
  public void commit()
    throws ResourceException
  {
/* 113 */     if (!this.isBeginCalled) {
/* 114 */       throw new LocalTransactionException("begin() must be called before commit()", "commit without begin");
    }
    try
    {
/* 118 */       this.connection.commit();
    }
    catch (SQLException localSQLException)
    {
/* 123 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 126 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 128 */       throw localEISSystemException;
    }
    
/* 132 */     this.isBeginCalled = false;
    
/* 134 */     this.managedConnection.eventOccurred(3);
  }
  
  public void rollback()
    throws ResourceException
  {
/* 153 */     if (!this.isBeginCalled) {
/* 154 */       throw new LocalTransactionException("begin() must be called before rollback()", "rollback without begin");
    }
    try
    {
/* 158 */       this.connection.rollback();
    }
    catch (SQLException localSQLException)
    {
/* 163 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 166 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 168 */       throw localEISSystemException;
    }
    
/* 172 */     this.isBeginCalled = false;
    
/* 174 */     this.managedConnection.eventOccurred(4);
  }
  
/* 180 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleLocalTransaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */