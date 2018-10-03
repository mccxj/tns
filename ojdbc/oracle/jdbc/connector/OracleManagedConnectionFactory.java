package oracle.jdbc.connector;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;
import javax.resource.spi.ConnectionRequestInfo;
import javax.resource.spi.EISSystemException;
import javax.resource.spi.ManagedConnection;
import javax.resource.spi.ManagedConnectionFactory;
import javax.resource.spi.ResourceAdapterInternalException;
import javax.resource.spi.security.PasswordCredential;
import javax.security.auth.Subject;
import javax.sql.DataSource;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
public class OracleManagedConnectionFactory
  implements ManagedConnectionFactory
{
/*  44 */   private XADataSource xaDataSource = null;
/*  45 */   private String xaDataSourceName = null;
  
  private static final String RAERR_MCF_SET_XADS = "invalid xads";
  
  private static final String RAERR_MCF_GET_PCRED = "no password credential";
  
  public OracleManagedConnectionFactory()
    throws ResourceException
  {}
  
  public OracleManagedConnectionFactory(XADataSource paramXADataSource)
    throws ResourceException
  {
/*  65 */     this.xaDataSource = paramXADataSource;
/*  66 */     this.xaDataSourceName = "XADataSource";
  }
  
  public void setXADataSourceName(String paramString)
  {
/*  75 */     this.xaDataSourceName = paramString;
  }
  
  public String getXADataSourceName()
  {
/*  84 */     return this.xaDataSourceName;
  }
  
  public Object createConnectionFactory(ConnectionManager paramConnectionManager)
    throws ResourceException
  {
/* 104 */     if (this.xaDataSource == null)
    {
/* 106 */       setupXADataSource();
    }
    
/* 109 */     return (DataSource)this.xaDataSource;
  }
  
  public Object createConnectionFactory()
    throws ResourceException
  {
/* 127 */     return createConnectionFactory(null);
  }
  
  public ManagedConnection createManagedConnection(Subject paramSubject, ConnectionRequestInfo paramConnectionRequestInfo)
    throws ResourceException
  {
    try
    {
/* 152 */       if (this.xaDataSource == null)
      {
/* 154 */         setupXADataSource();
      }
      
/* 157 */       XAConnection localXAConnection = null;
/* 158 */       localObject = getPasswordCredential(paramSubject, paramConnectionRequestInfo);
      
/* 160 */       if (localObject == null)
      {
/* 162 */         localXAConnection = this.xaDataSource.getXAConnection();
      }
      else
      {
/* 166 */         localXAConnection = this.xaDataSource.getXAConnection(((PasswordCredential)localObject).getUserName(), new String(((PasswordCredential)localObject).getPassword()));
      }
      
/* 170 */       OracleManagedConnection localOracleManagedConnection = new OracleManagedConnection(localXAConnection);
      
/* 172 */       localOracleManagedConnection.setPasswordCredential((PasswordCredential)localObject);
      
/* 175 */       localOracleManagedConnection.setLogWriter(getLogWriter());
      
/* 177 */       return localOracleManagedConnection;
    }
    catch (SQLException localSQLException)
    {
/* 181 */       Object localObject = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 184 */       ((ResourceException)localObject).setLinkedException(localSQLException);
      
/* 186 */       throw ((Throwable)localObject);
    }
  }
  
  public ManagedConnection matchManagedConnections(Set paramSet, Subject paramSubject, ConnectionRequestInfo paramConnectionRequestInfo)
    throws ResourceException
  {
/* 214 */     PasswordCredential localPasswordCredential = getPasswordCredential(paramSubject, paramConnectionRequestInfo);
/* 215 */     Iterator localIterator = paramSet.iterator();
    
/* 217 */     while (localIterator.hasNext())
    {
/* 219 */       Object localObject = localIterator.next();
      
/* 221 */       if ((localObject instanceof OracleManagedConnection))
      {
/* 223 */         OracleManagedConnection localOracleManagedConnection = (OracleManagedConnection)localObject;
        
/* 226 */         if (localOracleManagedConnection.getPasswordCredential().equals(localPasswordCredential))
        {
/* 228 */           return localOracleManagedConnection;
        }
      }
    }
    
/* 233 */     return null;
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter)
    throws ResourceException
  {
    try
    {
/* 253 */       if (this.xaDataSource == null)
      {
/* 255 */         setupXADataSource();
      }
      
/* 258 */       this.xaDataSource.setLogWriter(paramPrintWriter);
    }
    catch (SQLException localSQLException)
    {
/* 264 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 267 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 269 */       throw localEISSystemException;
    }
  }
  
  public PrintWriter getLogWriter()
    throws ResourceException
  {
    try
    {
/* 289 */       if (this.xaDataSource == null)
      {
/* 291 */         setupXADataSource();
      }
      
/* 294 */       return this.xaDataSource.getLogWriter();
    }
    catch (SQLException localSQLException)
    {
/* 300 */       EISSystemException localEISSystemException = new EISSystemException("SQLException: " + localSQLException.getMessage());
      
/* 303 */       localEISSystemException.setLinkedException(localSQLException);
      
/* 305 */       throw localEISSystemException;
    }
  }
  
  private void setupXADataSource()
    throws ResourceException
  {
    try
    {
/* 352 */       InitialContext localInitialContext = null;
      
      try
      {
/* 356 */         Properties localProperties = System.getProperties();
        
/* 358 */         localInitialContext = new InitialContext(localProperties);
      }
      catch (SecurityException localSecurityException) {}
      
/* 364 */       if (localInitialContext == null)
      {
/* 366 */         localInitialContext = new InitialContext();
      }
      
/* 369 */       localObject = (XADataSource)localInitialContext.lookup(this.xaDataSourceName);
      
/* 371 */       if (localObject == null)
      {
/* 373 */         throw new ResourceAdapterInternalException("Invalid XADataSource object");
      }
      
/* 376 */       this.xaDataSource = ((XADataSource)localObject);
    }
    catch (NamingException localNamingException)
    {
/* 380 */       Object localObject = new ResourceException("NamingException: " + localNamingException.getMessage());
      
/* 383 */       ((ResourceException)localObject).setLinkedException(localNamingException);
      
/* 385 */       throw ((Throwable)localObject);
    }
  }
  
  private PasswordCredential getPasswordCredential(Subject paramSubject, ConnectionRequestInfo paramConnectionRequestInfo)
    throws ResourceException
  {
/* 398 */     if (paramSubject != null)
    {
/* 402 */       localObject1 = paramSubject.getPrivateCredentials(PasswordCredential.class);
/* 403 */       localObject2 = ((Set)localObject1).iterator();
      
/* 405 */       while (((Iterator)localObject2).hasNext())
      {
/* 407 */         PasswordCredential localPasswordCredential = (PasswordCredential)((Iterator)localObject2).next();
        
/* 409 */         if (localPasswordCredential.getManagedConnectionFactory().equals(this))
        {
/* 411 */           return localPasswordCredential;
        }
      }
      
/* 415 */       throw new javax.resource.spi.SecurityException("Can not find user/password information", "no password credential");
    }
    
/* 419 */     if (paramConnectionRequestInfo == null)
    {
/* 421 */       return null;
    }
    
/* 425 */     Object localObject1 = (OracleConnectionRequestInfo)paramConnectionRequestInfo;
    
/* 427 */     Object localObject2 = new PasswordCredential(((OracleConnectionRequestInfo)localObject1).getUser(), ((OracleConnectionRequestInfo)localObject1).getPassword().toCharArray());
    
/* 430 */     ((PasswordCredential)localObject2).setManagedConnectionFactory(this);
    
/* 432 */     return (PasswordCredential)localObject2;
  }
  
/* 441 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleManagedConnectionFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */