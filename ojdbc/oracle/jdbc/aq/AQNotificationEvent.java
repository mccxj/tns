package oracle.jdbc.aq;
import java.sql.SQLException;
import java.util.EventObject;
public abstract class AQNotificationEvent
  extends EventObject
{
  public static enum EventType
  {
/*  50 */     REGULAR(0), 
    
/*  54 */     DEREG(1);
    
    private final int code;
    
    private EventType(int paramInt) {
/*  59 */       this.code = paramInt;
    }
    
    public final int getCode()
    {
/*  67 */       return this.code;
    }
  }
  
  public static enum AdditionalEventType
  {
/*  81 */     NONE(0), 
    
/*  85 */     TIMEOUT(1), 
    
/*  89 */     GROUPING(2);
    
    private final int code;
    
    private AdditionalEventType(int paramInt)
    {
/*  95 */       this.code = paramInt;
    }
    
    public final int getCode()
    {
/* 103 */       return this.code;
    }
    
    public static final AdditionalEventType getEventType(int paramInt)
    {
/* 110 */       if (paramInt == TIMEOUT.getCode())
/* 111 */         return TIMEOUT;
/* 112 */       if (paramInt == GROUPING.getCode()) {
/* 113 */         return GROUPING;
      }
/* 115 */       return NONE;
    }
  }
  
  protected AQNotificationEvent(Object paramObject) {
/* 120 */     super(paramObject);
  }
  
/* 185 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  public abstract AQMessageProperties getMessageProperties()
    throws SQLException;
  
  public abstract String getRegistration()
    throws SQLException;
  
  public abstract byte[] getPayload()
    throws SQLException;
  
  public abstract String getQueueName()
    throws SQLException;
  
  public abstract byte[] getMessageId()
    throws SQLException;
  
  public abstract String getConsumerName()
    throws SQLException;
  
  public abstract String getConnectionInformation();
  
  public abstract EventType getEventType();
  
  public abstract AdditionalEventType getAdditionalEventType();
  
  public abstract String toString();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQNotificationEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */