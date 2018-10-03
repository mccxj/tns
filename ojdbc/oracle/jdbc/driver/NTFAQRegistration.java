package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;
import oracle.jdbc.aq.AQNotificationListener;
import oracle.jdbc.aq.AQNotificationRegistration;
class NTFAQRegistration
  extends NTFRegistration
  implements AQNotificationRegistration
{
  private final String name;
  
  NTFAQRegistration(int paramInt1, boolean paramBoolean, String paramString1, String paramString2, String paramString3, int paramInt2, Properties paramProperties, String paramString4, short paramShort)
  {
/*  61 */     super(paramInt1, 1, paramBoolean, paramString1, paramString3, paramInt2, paramProperties, paramString2, paramShort);
    
/*  71 */     this.name = paramString4;
  }
  
  public void addListener(AQNotificationListener paramAQNotificationListener, Executor paramExecutor)
    throws SQLException
  {
/*  81 */     NTFEventListener localNTFEventListener = new NTFEventListener(paramAQNotificationListener);
/*  82 */     localNTFEventListener.setExecutor(paramExecutor);
/*  83 */     addListener(localNTFEventListener);
  }
  
  public void addListener(AQNotificationListener paramAQNotificationListener)
    throws SQLException
  {
/*  92 */     NTFEventListener localNTFEventListener = new NTFEventListener(paramAQNotificationListener);
/*  93 */     addListener(localNTFEventListener);
  }
  
  public void removeListener(AQNotificationListener paramAQNotificationListener)
    throws SQLException
  {
/* 101 */     super.removeListener(paramAQNotificationListener);
  }
  
  public String getQueueName()
  {
/* 108 */     return this.name;
  }
  
/* 113 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFAQRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */