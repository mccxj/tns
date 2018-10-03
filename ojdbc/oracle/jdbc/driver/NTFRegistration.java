package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.Properties;
import java.util.concurrent.Executor;
import oracle.jdbc.NotificationRegistration.RegistrationState;
import oracle.jdbc.aq.AQNotificationEvent.EventType;
import oracle.jdbc.aq.AQNotificationListener;
import oracle.jdbc.dcn.DatabaseChangeListener;
import oracle.jdbc.internal.OracleConnection;
abstract class NTFRegistration
{
  private final boolean jdbcGetsNotification;
  private final String clientHost;
  private final int clientTCPPort;
  private final Properties options;
  private final boolean isPurgeOnNTF;
  private final String username;
  private final int namespace;
  private final int jdbcRegId;
  private final String dbName;
  private final short databaseVersion;
  private NotificationRegistration.RegistrationState state;
/*  73 */   private NTFEventListener[] listeners = new NTFEventListener[0];
  
  NTFRegistration(int paramInt1, int paramInt2, boolean paramBoolean, String paramString1, String paramString2, int paramInt3, Properties paramProperties, String paramString3, short paramShort)
  {
/*  88 */     this.namespace = paramInt2;
/*  89 */     this.clientHost = paramString2;
/*  90 */     this.clientTCPPort = paramInt3;
/*  91 */     this.options = paramProperties;
/*  92 */     this.jdbcRegId = paramInt1;
/*  93 */     this.username = paramString3;
/*  94 */     this.jdbcGetsNotification = paramBoolean;
/*  95 */     this.dbName = paramString1;
/*  96 */     this.state = NotificationRegistration.RegistrationState.ACTIVE;
/*  97 */     if (this.options.getProperty("NTF_QOS_PURGE_ON_NTFN", "false").compareToIgnoreCase("true") == 0)
    {
/*  99 */       this.isPurgeOnNTF = true;
    } else
/* 101 */       this.isPurgeOnNTF = false;
/* 102 */     this.databaseVersion = paramShort;
  }
  
  short getDatabaseVersion()
  {
/* 107 */     return this.databaseVersion;
  }
  
  synchronized void addListener(NTFEventListener paramNTFEventListener) throws SQLException
  {
    SQLException localSQLException1;
/* 113 */     if (this.state == NotificationRegistration.RegistrationState.CLOSED)
    {
/* 116 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 251);
/* 117 */       localSQLException1.fillInStackTrace();
/* 118 */       throw localSQLException1;
    }
    
/* 122 */     if (!this.jdbcGetsNotification)
    {
/* 126 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 247);
/* 127 */       localSQLException1.fillInStackTrace();
/* 128 */       throw localSQLException1;
    }
    
/* 132 */     int i = this.listeners.length;
/* 133 */     for (int j = 0; j < i; j++) {
/* 134 */       if (this.listeners[j].getListener() == paramNTFEventListener.getListener())
      {
/* 138 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 248);
/* 139 */         localSQLException2.fillInStackTrace();
/* 140 */         throw localSQLException2;
      }
    }
    
/* 144 */     NTFEventListener[] arrayOfNTFEventListener = new NTFEventListener[i + 1];
/* 145 */     System.arraycopy(this.listeners, 0, arrayOfNTFEventListener, 0, i);
/* 146 */     arrayOfNTFEventListener[i] = paramNTFEventListener;
    
/* 148 */     this.listeners = arrayOfNTFEventListener;
  }
  
  synchronized void removeListener(EventListener paramEventListener)
    throws SQLException
  {
/* 158 */     int i = 0;
/* 159 */     int j = this.listeners.length;
    
/* 161 */     for (i = 0; i < j; i++)
/* 162 */       if (this.listeners[i].getListener() == paramEventListener)
        break;
/* 164 */     if (i == j)
    {
/* 168 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 249);
/* 169 */       ((SQLException)localObject).fillInStackTrace();
/* 170 */       throw ((Throwable)localObject);
    }
    
/* 174 */     Object localObject = new NTFEventListener[j - 1];
/* 175 */     int k = 0;
/* 176 */     for (i = 0; i < j; i++) {
/* 177 */       if (this.listeners[i].getListener() != paramEventListener) {
/* 178 */         localObject[(k++)] = this.listeners[i];
      }
    }
/* 181 */     this.listeners = ((NTFEventListener[])localObject);
  }
  
  void notify(final NTFDCNEvent paramNTFDCNEvent)
  {
/* 190 */     long l = 0L;
    
/* 192 */     NTFEventListener[] arrayOfNTFEventListener = this.listeners;
    
/* 196 */     int i = arrayOfNTFEventListener.length;
/* 197 */     for (int j = 0; j < i; j++)
    {
/* 199 */       Executor localExecutor = arrayOfNTFEventListener[j].getExecutor();
      
/* 203 */       if (localExecutor != null)
      {
/* 205 */         final DatabaseChangeListener localDatabaseChangeListener = arrayOfNTFEventListener[j].getDCNListener();
        
/* 207 */         localExecutor.execute(new Runnable() {
          public void run() {
/* 209 */             localDatabaseChangeListener.onDatabaseChangeNotification(paramNTFDCNEvent);
          }
        });
      }
      else
      {
/* 215 */         arrayOfNTFEventListener[j].getDCNListener().onDatabaseChangeNotification(paramNTFDCNEvent);
      }
    }
    
/* 223 */     if ((paramNTFDCNEvent.isDeregistrationEvent()) || (this.isPurgeOnNTF))
    {
/* 225 */       PhysicalConnection.ntfManager.removeRegistration(this);
/* 226 */       PhysicalConnection.ntfManager.freeJdbcRegId(getJdbcRegId());
/* 227 */       PhysicalConnection.ntfManager.cleanListenersT4C(getClientTCPPort());
/* 228 */       this.state = NotificationRegistration.RegistrationState.CLOSED;
    }
  }
  
  void notify(final NTFAQEvent paramNTFAQEvent)
  {
/* 240 */     long l = 0L;
    
/* 242 */     NTFEventListener[] arrayOfNTFEventListener = this.listeners;
    
/* 246 */     int i = arrayOfNTFEventListener.length;
/* 247 */     for (int j = 0; j < i; j++)
    {
/* 249 */       Executor localExecutor = arrayOfNTFEventListener[j].getExecutor();
      
/* 254 */       if (localExecutor != null)
      {
/* 256 */         final AQNotificationListener localAQNotificationListener = arrayOfNTFEventListener[j].getAQListener();
        
/* 258 */         localExecutor.execute(new Runnable() {
          public void run() {
/* 260 */             localAQNotificationListener.onAQNotification(paramNTFAQEvent);
          }
        });
      }
      else
      {
/* 266 */         arrayOfNTFEventListener[j].getAQListener().onAQNotification(paramNTFAQEvent);
      }
    }
    
/* 275 */     if ((paramNTFAQEvent.getEventType() == AQNotificationEvent.EventType.DEREG) || (this.isPurgeOnNTF))
    {
/* 277 */       PhysicalConnection.ntfManager.removeRegistration(this);
/* 278 */       PhysicalConnection.ntfManager.freeJdbcRegId(getJdbcRegId());
/* 279 */       PhysicalConnection.ntfManager.cleanListenersT4C(getClientTCPPort());
/* 280 */       this.state = NotificationRegistration.RegistrationState.CLOSED;
    }
  }
  
  public Properties getRegistrationOptions()
  {
/* 288 */     return this.options;
  }
  
  int getJdbcRegId()
  {
/* 295 */     return this.jdbcRegId;
  }
  
  public String getUserName()
  {
/* 300 */     return this.username;
  }
  
  String getClientHost() {
/* 304 */     return this.clientHost;
  }
  
  int getClientTCPPort()
  {
/* 309 */     return this.clientTCPPort;
  }
  
  public String getDatabaseName() {
/* 313 */     return this.dbName;
  }
  
  public NotificationRegistration.RegistrationState getState() {
/* 317 */     return this.state;
  }
  
  protected void setState(NotificationRegistration.RegistrationState paramRegistrationState) {
/* 321 */     this.state = paramRegistrationState;
  }
  
  int getNamespace() {
/* 325 */     return this.namespace;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 339 */     return null;
  }
  
/* 344 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */