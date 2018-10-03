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
class OracleRuntimeLoadBalancingEventHandlerThread
  extends Thread
{
/*  41 */   private Notification event = null;
/*  42 */   private OracleConnectionCacheManager cacheManager = null;
  
  String m_service;
  
  OracleRuntimeLoadBalancingEventHandlerThread(String paramString)
    throws SQLException
  {
/*  50 */     this.m_service = paramString;
/*  51 */     this.cacheManager = OracleConnectionCacheManager.getConnectionCacheManagerInstance();
  }
  
  public void run()
  {
/*  60 */     Subscriber localSubscriber = null;
    
/*  63 */     final String str = "%\"eventType=database/event/servicemetrics/" + this.m_service + "\"";
    
/*  66 */     while (this.cacheManager.failoverEnabledCacheExists())
    {
      try
      {
/*  71 */         localSubscriber = (Subscriber)AccessController.doPrivileged(new PrivilegedExceptionAction()
        {
          public Object run()
          {
            try
            {
/*  80 */               return new Subscriber(str, "", 30000L);
            }
            catch (SubscriptionException localSubscriptionException) {}
            
/*  85 */             return null;
          }
        });
      }
      catch (PrivilegedActionException localPrivilegedActionException) {}
      
/*  96 */       if (localSubscriber != null)
      {
        try
        {
/* 100 */           while (this.cacheManager.failoverEnabledCacheExists())
          {
/* 106 */             if ((this.event = localSubscriber.receive(300000L)) != null) {
/* 107 */               handleEvent(this.event);
            }
          }
        }
        catch (ONSException localONSException) {
/* 112 */           localSubscriber.close();
        }
      }
      
      try
      {
/* 120 */         Thread.currentThread();Thread.sleep(10000L);
      }
      catch (InterruptedException localInterruptedException) {}
    }
  }
  
  void handleEvent(Notification paramNotification)
  {
    try
    {
/* 136 */       this.cacheManager.parseRuntimeLoadBalancingEvent(this.m_service, paramNotification == null ? null : paramNotification.body());
    }
    catch (SQLException localSQLException) {}
  }
  
/* 146 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleRuntimeLoadBalancingEventHandlerThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */