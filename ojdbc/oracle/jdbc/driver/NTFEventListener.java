package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.concurrent.Executor;
import oracle.jdbc.aq.AQNotificationListener;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.XSEventListener;
class NTFEventListener
{
  private final AQNotificationListener aqlistener;
  private final DatabaseChangeListener dcnlistener;
  private final XSEventListener xslistener;
/*  52 */   private Executor executor = null;
  
  NTFEventListener(DatabaseChangeListener paramDatabaseChangeListener)
    throws SQLException
  {
/*  58 */     if (paramDatabaseChangeListener == null)
    {
/*  62 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 246);
/*  63 */       localSQLException.fillInStackTrace();
/*  64 */       throw localSQLException;
    }
    
/*  67 */     this.dcnlistener = paramDatabaseChangeListener;
/*  68 */     this.aqlistener = null;
/*  69 */     this.xslistener = null;
  }
  
  NTFEventListener(AQNotificationListener paramAQNotificationListener)
    throws SQLException
  {
/*  77 */     if (paramAQNotificationListener == null)
    {
/*  81 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 246);
/*  82 */       localSQLException.fillInStackTrace();
/*  83 */       throw localSQLException;
    }
    
/*  86 */     this.aqlistener = paramAQNotificationListener;
/*  87 */     this.dcnlistener = null;
/*  88 */     this.xslistener = null;
  }
  
  NTFEventListener(XSEventListener paramXSEventListener)
    throws SQLException
  {
/*  96 */     if (paramXSEventListener == null)
    {
/* 100 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 246);
/* 101 */       localSQLException.fillInStackTrace();
/* 102 */       throw localSQLException;
    }
    
/* 105 */     this.aqlistener = null;
/* 106 */     this.dcnlistener = null;
/* 107 */     this.xslistener = paramXSEventListener;
  }
  
  void setExecutor(Executor paramExecutor)
  {
/* 114 */     this.executor = paramExecutor;
  }
  
  Executor getExecutor()
  {
/* 120 */     return this.executor;
  }
  
  EventListener getListener()
  {
/* 127 */     Object localObject = this.dcnlistener;
/* 128 */     if (localObject == null)
/* 129 */       localObject = this.aqlistener;
/* 130 */     return (EventListener)localObject;
  }
  
  AQNotificationListener getAQListener()
  {
/* 137 */     return this.aqlistener;
  }
  
  DatabaseChangeListener getDCNListener()
  {
/* 144 */     return this.dcnlistener;
  }
  
  XSEventListener getXSEventListener()
  {
/* 150 */     return this.xslistener;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 167 */     return null;
  }
  
/* 172 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFEventListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */