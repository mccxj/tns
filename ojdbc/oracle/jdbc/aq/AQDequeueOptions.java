package oracle.jdbc.aq;
import java.sql.SQLException;
public class AQDequeueOptions
{
  public static final int DEQUEUE_WAIT_FOREVER = -1;
  public static final int DEQUEUE_NO_WAIT = 0;
  private String attrConsumerName;
  private String attrCorrelation;
  private DequeueMode attrDeqMode;
  private byte[] attrDeqMsgId;
  private NavigationOption attrNavigation;
  private VisibilityOption attrVisibility;
  private int attrWait;
  private int maxBufferLength;
  private DeliveryFilter attrDeliveryMode;
  private boolean retrieveMsgId;
  private String transformation;
  private String condition;
  public static final int MAX_RAW_PAYLOAD = 67108787;
  
  public static enum DequeueMode
  {
/*  53 */     BROWSE(1), 
    
/*  59 */     LOCKED(2), 
    
/*  65 */     REMOVE(3), 
    
/*  70 */     REMOVE_NODATA(4);
    
    private final int mode;
    
    private DequeueMode(int paramInt) {
/*  75 */       this.mode = paramInt;
    }
    
    public final int getCode()
    {
/*  82 */       return this.mode;
    }
  }
  
  public static enum NavigationOption
  {
/*  93 */     FIRST_MESSAGE(1), 
    
/* 100 */     NEXT_MESSAGE(3), 
    
/* 107 */     NEXT_TRANSACTION(2);
    
    private final int mode;
    
/* 111 */     private NavigationOption(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/* 118 */       return this.mode;
    }
  }
  
  public static enum VisibilityOption
  {
/* 137 */     ON_COMMIT(2), 
    
/* 142 */     IMMEDIATE(1);
    
    private final int mode;
    
/* 146 */     private VisibilityOption(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/* 153 */       return this.mode;
    }
  }
  
  public static enum DeliveryFilter
  {
/* 161 */     PERSISTENT(1),  BUFFERED(2),  PERSISTENT_OR_BUFFERED(3);
    
    private final int mode;
    
/* 165 */     private DeliveryFilter(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/* 172 */       return this.mode;
    }
  }
  
  public AQDequeueOptions()
  {
/* 212 */     this.attrConsumerName = null;
/* 213 */     this.attrCorrelation = null;
/* 214 */     this.attrDeqMode = DequeueMode.REMOVE;
/* 215 */     this.attrDeqMsgId = null;
/* 216 */     this.attrNavigation = NavigationOption.NEXT_MESSAGE;
/* 217 */     this.attrVisibility = VisibilityOption.ON_COMMIT;
/* 218 */     this.attrWait = -1;
/* 219 */     this.maxBufferLength = 67108787;
/* 220 */     this.attrDeliveryMode = DeliveryFilter.PERSISTENT;
    
/* 224 */     this.retrieveMsgId = false;
  }
  
  public void setConsumerName(String paramString)
    throws SQLException
  {
/* 239 */     this.attrConsumerName = paramString;
  }
  
  public String getConsumerName()
  {
/* 251 */     return this.attrConsumerName;
  }
  
  public void setCorrelation(String paramString)
    throws SQLException
  {
/* 274 */     this.attrCorrelation = paramString;
  }
  
  public String getCorrelation()
  {
/* 286 */     return this.attrCorrelation;
  }
  
  public void setDequeueMode(DequeueMode paramDequeueMode)
    throws SQLException
  {
/* 305 */     this.attrDeqMode = paramDequeueMode;
  }
  
  public DequeueMode getDequeueMode()
  {
/* 317 */     return this.attrDeqMode;
  }
  
  public void setDequeueMessageId(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 332 */     this.attrDeqMsgId = paramArrayOfByte;
  }
  
  public byte[] getDequeueMessageId()
  {
/* 345 */     return this.attrDeqMsgId;
  }
  
  public void setNavigation(NavigationOption paramNavigationOption)
    throws SQLException
  {
/* 369 */     this.attrNavigation = paramNavigationOption;
  }
  
  public NavigationOption getNavigation()
  {
/* 381 */     return this.attrNavigation;
  }
  
  public void setVisibility(VisibilityOption paramVisibilityOption)
    throws SQLException
  {
/* 398 */     this.attrVisibility = paramVisibilityOption;
  }
  
  public VisibilityOption getVisibility()
  {
/* 410 */     return this.attrVisibility;
  }
  
  public void setWait(int paramInt)
    throws SQLException
  {
/* 435 */     this.attrWait = paramInt;
  }
  
  public int getWait()
  {
/* 447 */     return this.attrWait;
  }
  
  public void setMaximumBufferLength(int paramInt)
    throws SQLException
  {
/* 465 */     if (paramInt > 0) {
/* 466 */       this.maxBufferLength = paramInt;
    }
  }
  
  public int getMaximumBufferLength()
  {
/* 478 */     return this.maxBufferLength;
  }
  
  public void setDeliveryFilter(DeliveryFilter paramDeliveryFilter)
    throws SQLException
  {
/* 501 */     this.attrDeliveryMode = paramDeliveryFilter;
  }
  
  public DeliveryFilter getDeliveryFilter()
  {
/* 513 */     return this.attrDeliveryMode;
  }
  
  public void setRetrieveMessageId(boolean paramBoolean)
  {
/* 526 */     this.retrieveMsgId = paramBoolean;
  }
  
  public boolean getRetrieveMessageId()
  {
/* 539 */     return this.retrieveMsgId;
  }
  
  public void setTransformation(String paramString)
  {
/* 559 */     this.transformation = paramString;
  }
  
  public String getTransformation()
  {
/* 571 */     return this.transformation;
  }
  
  public void setCondition(String paramString)
  {
/* 598 */     this.condition = paramString;
  }
  
  public String getCondition()
  {
/* 610 */     return this.condition;
  }
  
/* 614 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQDequeueOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */