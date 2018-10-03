package oracle.jdbc.pool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;
import oracle.jdbc.internal.OracleConnection;
/**
 * @deprecated
 */
class OracleImplicitConnectionCacheThread
  extends Thread
{
/*  35 */   private OracleImplicitConnectionCache implicitCache = null;
/*  36 */   protected boolean timeToLive = true;
/*  37 */   protected boolean isSleeping = false;
  
  OracleImplicitConnectionCacheThread(OracleImplicitConnectionCache paramOracleImplicitConnectionCache)
    throws SQLException
  {
/*  45 */     this.implicitCache = paramOracleImplicitConnectionCache;
  }
  
  public void run()
  {
/*  53 */     long l1 = 0L;
/*  54 */     long l2 = 0L;
/*  55 */     long l3 = 0L;
    
/*  60 */     while (this.timeToLive)
    {
      try
      {
/*  67 */         if ((this.timeToLive) && ((l1 = this.implicitCache.getCacheTimeToLiveTimeout()) > 0L))
        {
/*  70 */           runTimeToLiveTimeout(l1);
        }
        
/*  74 */         if ((this.timeToLive) && ((l2 = this.implicitCache.getCacheInactivityTimeout()) > 0L))
        {
/*  76 */           runInactivityTimeout();
        }
        
/*  80 */         if ((this.timeToLive) && ((l3 = this.implicitCache.getCacheAbandonedTimeout()) > 0L))
        {
/*  82 */           runAbandonedTimeout(l3);
        }
        
/*  86 */         if (this.timeToLive)
        {
/*  88 */           this.isSleeping = true;
          
          try
          {
/*  93 */             sleep(this.implicitCache.getCachePropertyCheckInterval() * 1000);
          }
          catch (InterruptedException localInterruptedException) {}
          
/* 100 */           this.isSleeping = false;
        }
        
/* 104 */         if ((this.implicitCache == null) || ((l1 <= 0L) && (l2 <= 0L) && (l3 <= 0L)))
        {
/* 107 */           this.timeToLive = false;
        }
      }
      catch (SQLException localSQLException) {}
    }
  }
  
  private void runTimeToLiveTimeout(long paramLong)
    throws SQLException
  {
/* 123 */     long l1 = 0L;
/* 124 */     long l2 = 0L;
    
/* 127 */     if (this.implicitCache.getNumberOfCheckedOutConnections() > 0)
    {
/* 129 */       OraclePooledConnection localOraclePooledConnection = null;
      
/* 134 */       synchronized (this.implicitCache)
      {
/* 138 */         Object[] arrayOfObject = this.implicitCache.checkedOutConnectionList.toArray();
/* 139 */         int i = this.implicitCache.checkedOutConnectionList.size();
        
/* 141 */         for (int j = 0; j < i; j++)
        {
/* 143 */           localOraclePooledConnection = (OraclePooledConnection)arrayOfObject[j];
          
/* 145 */           Connection localConnection = localOraclePooledConnection.getLogicalHandle();
          
/* 147 */           if (localConnection != null)
          {
/* 149 */             l2 = ((OracleConnection)localConnection).getStartTime();
            
/* 151 */             l1 = System.currentTimeMillis();
            
/* 154 */             if (l1 - l2 > paramLong * 1000L)
            {
              try
              {
/* 160 */                 this.implicitCache.closeCheckedOutConnection(localOraclePooledConnection, true);
              }
              catch (SQLException localSQLException) {}
            }
          }
        }
      }
    }
  }
  
  private void runInactivityTimeout()
  {
    try
    {
/* 182 */       this.implicitCache.doForEveryCachedConnection(4);
    }
    catch (SQLException localSQLException) {}
  }
  
  private void runAbandonedTimeout(long paramLong)
    throws SQLException
  {
/* 200 */     if (this.implicitCache.getNumberOfCheckedOutConnections() > 0)
    {
/* 202 */       OraclePooledConnection localOraclePooledConnection = null;
      
/* 207 */       synchronized (this.implicitCache)
      {
/* 209 */         Object[] arrayOfObject = this.implicitCache.checkedOutConnectionList.toArray();
        
/* 212 */         for (int i = 0; i < arrayOfObject.length; i++)
        {
/* 214 */           localOraclePooledConnection = (OraclePooledConnection)arrayOfObject[i];
          
/* 216 */           OracleConnection localOracleConnection = (OracleConnection)localOraclePooledConnection.getLogicalHandle();
          
/* 218 */           if (localOracleConnection != null)
          {
/* 222 */             OracleConnectionCacheCallback localOracleConnectionCacheCallback = localOracleConnection.getConnectionCacheCallbackObj();
            
/* 226 */             if (localOracleConnection.getHeartbeatNoChangeCount() * this.implicitCache.getCachePropertyCheckInterval() > paramLong)
            {
              try
              {
/* 234 */                 boolean bool = true;
/* 235 */                 if ((localOracleConnectionCacheCallback != null) && ((localOracleConnection.getConnectionCacheCallbackFlag() == 4) || (localOracleConnection.getConnectionCacheCallbackFlag() == 1)))
                {
/* 246 */                   bool = localOracleConnectionCacheCallback.handleAbandonedConnection(localOracleConnection, localOracleConnection.getConnectionCacheCallbackPrivObj());
                }
                
/* 251 */                 if (bool)
                {
/* 253 */                   this.implicitCache.closeCheckedOutConnection(localOraclePooledConnection, true);
/* 254 */                   this.implicitCache.checkedOutConnectionList.remove(localOraclePooledConnection);
/* 255 */                   this.implicitCache.storeCacheConnection(localOraclePooledConnection.cachedConnectionAttributes, localOraclePooledConnection);
                }
              }
              catch (SQLException localSQLException) {}
            }
          }
        }
      }
    }
  }
  
/* 274 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleImplicitConnectionCacheThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */