package oracle.jdbc.pool;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.sql.SQLException;
import oracle.ons.Notification;
import oracle.ons.ONSException;
import oracle.ons.Subscriber;
import oracle.ons.SubscriptionException;
/**
 * @deprecated
 */
class OracleFailoverEventHandlerThread
  extends Thread
{
/*  41 */   private Notification event = null;
/*  42 */   private OracleConnectionCacheManager cacheManager = null;
  
  OracleFailoverEventHandlerThread()
    throws SQLException
  {
/*  48 */     this.cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
  }
  
  public void run()
  {
/*  57 */     Subscriber localSubscriber = null;
    
/*  62 */     while (this.cacheManager.failoverEnabledCacheExists())
    {
      try
      {
/*  67 */         localSubscriber = (Subscriber)AccessController.doPrivileged(new PrivilegedExceptionAction()
        {
          public Object run()
          {
            try
            {
/*  76 */               return new Subscriber("(%\"eventType=database/event/service\")|(%\"eventType=database/event/host\")", "", 30000L);
            }
            catch (SubscriptionException localSubscriptionException) {}
            
/*  81 */             return null;
          }
        });
      }
      catch (PrivilegedActionException localPrivilegedActionException) {}
      
/*  91 */       if (localSubscriber != null)
      {
        try
        {
/*  95 */           while (this.cacheManager.failoverEnabledCacheExists())
          {
/*  98 */             if ((this.event = localSubscriber.receive(true)) != null) {
/*  99 */               handleEvent(this.event);
            }
          }
        }
        catch (ONSException localONSException) {
/* 104 */           localSubscriber.close();
        }
      }
      
      try
      {
/* 112 */         Thread.currentThread();Thread.sleep(10000L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  void handleEvent(Notification paramNotification)
  {
    try
    {
/* 129 */       int i = 0;
      
/* 131 */       if (paramNotification.type().equalsIgnoreCase("database/event/service")) {
/* 132 */         i = 256;
/* 133 */       } else if (paramNotification.type().equalsIgnoreCase("database/event/host")) {
/* 134 */         i = 512;
      }
/* 136 */       if (i != 0) {
/* 137 */         this.cacheManager.verifyAndHandleEvent(i, paramNotification.body());
      }
    }
    catch (SQLException localSQLException) {}
  }
  
/* 147 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleFailoverEventHandlerThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */