package oracle.jdbc.aq;
import java.sql.SQLException;
import java.sql.Timestamp;
public abstract interface AQMessageProperties
{
  public static final int MESSAGE_NO_DELAY = 0;
  public static final int MESSAGE_NO_EXPIRATION = -1;
  
  public abstract int getDequeueAttemptsCount();
  
  public abstract void setCorrelation(String paramString)
    throws SQLException;
  
  public abstract String getCorrelation();
  
  public abstract void setDelay(int paramInt)
    throws SQLException;
  
  public abstract int getDelay();
  
  public abstract Timestamp getEnqueueTime();
  
  public abstract void setExceptionQueue(String paramString)
    throws SQLException;
  
  public abstract String getExceptionQueue();
  
  public abstract void setExpiration(int paramInt)
    throws SQLException;
  
  public abstract int getExpiration();
  
  public abstract MessageState getState();
  
  public abstract void setPriority(int paramInt)
    throws SQLException;
  
  public abstract int getPriority();
  
  public abstract void setRecipientList(AQAgent[] paramArrayOfAQAgent)
    throws SQLException;
  
  public abstract AQAgent[] getRecipientList();
  
  public abstract void setSender(AQAgent paramAQAgent)
    throws SQLException;
  
  public abstract AQAgent getSender();
  
  public abstract String getTransactionGroup();
  
  public abstract byte[] getPreviousQueueMessageId();
  
  public abstract DeliveryMode getDeliveryMode();
  
  public abstract String toString();
  
  public static enum MessageState
  {
/*  74 */     WAITING(1), 
    
/*  78 */     READY(0), 
    
/*  82 */     PROCESSED(2), 
    
/*  86 */     EXPIRED(3);
    
    private final int code;
    
/*  90 */     private MessageState(int paramInt) { this.code = paramInt; }
    
    public final int getCode()
    {
/*  98 */       return this.code;
    }
    
    public static final MessageState getMessageState(int paramInt)
    {
/* 106 */       if (paramInt == WAITING.getCode())
/* 107 */         return WAITING;
/* 108 */       if (paramInt == READY.getCode())
/* 109 */         return READY;
/* 110 */       if (paramInt == PROCESSED.getCode()) {
/* 111 */         return PROCESSED;
      }
/* 113 */       return EXPIRED;
    }
  }
  
  public static enum DeliveryMode
  {
/* 123 */     PERSISTENT(1), 
    
/* 127 */     BUFFERED(2);
    
    private final int code;
    
/* 131 */     private DeliveryMode(int paramInt) { this.code = paramInt; }
    
    public final int getCode()
    {
/* 139 */       return this.code;
    }
    
    public static final DeliveryMode getDeliveryMode(int paramInt)
    {
/* 147 */       if (paramInt == BUFFERED.getCode()) {
/* 148 */         return BUFFERED;
      }
/* 150 */       return PERSISTENT;
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQMessageProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */