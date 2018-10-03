package oracle.jdbc.dcn;
import java.util.EventObject;
public abstract class DatabaseChangeEvent
  extends EventObject
{
  protected DatabaseChangeEvent(Object paramObject)
  {
/*  51 */     super(paramObject);
  }
  
  public abstract EventType getEventType();
  
  public abstract AdditionalEventType getAdditionalEventType();
  
  public static enum EventType
  {
/*  61 */     NONE(0), 
    
/*  65 */     STARTUP(1), 
    
/*  69 */     SHUTDOWN(2), 
    
/*  73 */     SHUTDOWN_ANY(3), 
    
/*  77 */     DEREG(5), 
    
/*  81 */     OBJCHANGE(6), 
    
/*  85 */     QUERYCHANGE(7);
    
    private final int code;
    
    private EventType(int paramInt) {
/*  90 */       this.code = paramInt;
    }
    
    public final int getCode()
    {
/*  98 */       return this.code;
    }
    
    public static final EventType getEventType(int paramInt)
    {
/* 105 */       if (paramInt == STARTUP.getCode())
/* 106 */         return STARTUP;
/* 107 */       if (paramInt == SHUTDOWN.getCode())
/* 108 */         return SHUTDOWN;
/* 109 */       if (paramInt == SHUTDOWN_ANY.getCode())
/* 110 */         return SHUTDOWN_ANY;
/* 111 */       if (paramInt == DEREG.getCode())
/* 112 */         return DEREG;
/* 113 */       if (paramInt == OBJCHANGE.getCode())
/* 114 */         return OBJCHANGE;
/* 115 */       if (paramInt == QUERYCHANGE.getCode()) {
/* 116 */         return QUERYCHANGE;
      }
/* 118 */       return NONE;
    }
  }
  
  public static enum AdditionalEventType
  {
/* 133 */     NONE(0), 
    
/* 137 */     TIMEOUT(1), 
    
/* 141 */     GROUPING(2);
    
    private final int code;
    
    private AdditionalEventType(int paramInt)
    {
/* 147 */       this.code = paramInt;
    }
    
    public final int getCode()
    {
/* 155 */       return this.code;
    }
    
    public static final AdditionalEventType getEventType(int paramInt)
    {
/* 162 */       if (paramInt == TIMEOUT.getCode())
/* 163 */         return TIMEOUT;
/* 164 */       if (paramInt == GROUPING.getCode()) {
/* 165 */         return GROUPING;
      }
/* 167 */       return NONE;
    }
  }
  
  public abstract TableChangeDescription[] getTableChangeDescription();
  
  public abstract QueryChangeDescription[] getQueryChangeDescription();
  
  public abstract String getConnectionInformation();
  
  public abstract String getDatabaseName();
  
  /**
   * @deprecated
   */
  public abstract int getRegistrationId();
  
  public abstract long getRegId();
  
  public abstract byte[] getTransactionId();
  
  public abstract String getTransactionId(boolean paramBoolean);
  
  public abstract String toString();
  
/* 258 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/dcn/DatabaseChangeEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */