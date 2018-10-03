package oracle.jdbc.driver;
import java.sql.SQLException;
import java.sql.Timestamp;
import oracle.jdbc.aq.AQAgent;
import oracle.jdbc.aq.AQMessageProperties;
import oracle.jdbc.aq.AQMessageProperties.DeliveryMode;
import oracle.jdbc.aq.AQMessageProperties.MessageState;
class AQMessagePropertiesI
  implements AQMessageProperties
{
  private int attrAttempts;
  private String attrCorrelation;
  private int attrDelay;
  private Timestamp attrEnqTime;
  private String attrExceptionQueue;
  private int attrExpiration;
  private AQMessageProperties.MessageState attrMsgState;
  private int attrPriority;
  private AQAgentI[] attrRecipientList;
  private AQAgentI attrSenderId;
  private String attrTransactionGroup;
  private byte[] attrPreviousQueueMsgId;
  private AQMessageProperties.DeliveryMode deliveryMode;
  
  AQMessagePropertiesI()
  {
/*  66 */     this.attrAttempts = -1;
/*  67 */     this.attrCorrelation = null;
/*  68 */     this.attrDelay = 0;
/*  69 */     this.attrEnqTime = null;
/*  70 */     this.attrExceptionQueue = null;
/*  71 */     this.attrExpiration = -1;
/*  72 */     this.attrMsgState = null;
/*  73 */     this.attrPriority = 0;
/*  74 */     this.attrRecipientList = null;
/*  75 */     this.attrSenderId = null;
/*  76 */     this.attrTransactionGroup = null;
/*  77 */     this.attrPreviousQueueMsgId = null;
/*  78 */     this.deliveryMode = null;
  }
  
  public int getDequeueAttemptsCount()
  {
/*  86 */     return this.attrAttempts;
  }
  
  public void setCorrelation(String paramString)
    throws SQLException
  {
/*  94 */     this.attrCorrelation = paramString;
  }
  
  public String getCorrelation()
  {
/* 101 */     return this.attrCorrelation;
  }
  
  public void setDelay(int paramInt)
    throws SQLException
  {
/* 108 */     this.attrDelay = paramInt;
  }
  
  public int getDelay()
  {
/* 115 */     return this.attrDelay;
  }
  
  public Timestamp getEnqueueTime()
  {
/* 122 */     return this.attrEnqTime;
  }
  
  public void setExceptionQueue(String paramString)
    throws SQLException
  {
/* 130 */     this.attrExceptionQueue = paramString;
  }
  
  public String getExceptionQueue()
  {
/* 137 */     return this.attrExceptionQueue;
  }
  
  public void setExpiration(int paramInt)
    throws SQLException
  {
/* 145 */     this.attrExpiration = paramInt;
  }
  
  public int getExpiration()
  {
/* 152 */     return this.attrExpiration;
  }
  
  public AQMessageProperties.MessageState getState()
  {
/* 159 */     return this.attrMsgState;
  }
  
  public void setPriority(int paramInt)
    throws SQLException
  {
/* 166 */     this.attrPriority = paramInt;
  }
  
  public int getPriority()
  {
/* 173 */     return this.attrPriority;
  }
  
  public void setRecipientList(AQAgent[] paramArrayOfAQAgent)
    throws SQLException
  {
/* 181 */     this.attrRecipientList = new AQAgentI[paramArrayOfAQAgent.length];
/* 182 */     for (int i = 0; i < paramArrayOfAQAgent.length; i++) {
/* 183 */       this.attrRecipientList[i] = ((AQAgentI)paramArrayOfAQAgent[i]);
    }
  }
  
  public AQAgent[] getRecipientList()
  {
/* 190 */     return this.attrRecipientList;
  }
  
  public void setSender(AQAgent paramAQAgent)
    throws SQLException
  {
/* 197 */     this.attrSenderId = ((AQAgentI)paramAQAgent);
  }
  
  public AQAgent getSender()
  {
/* 204 */     return this.attrSenderId;
  }
  
  public String getTransactionGroup()
  {
/* 211 */     return this.attrTransactionGroup;
  }
  
  void setTransactionGroup(String paramString)
  {
/* 218 */     this.attrTransactionGroup = paramString;
  }
  
  void setPreviousQueueMessageId(byte[] paramArrayOfByte)
  {
/* 225 */     this.attrPreviousQueueMsgId = paramArrayOfByte;
  }
  
  public byte[] getPreviousQueueMessageId()
  {
/* 232 */     return this.attrPreviousQueueMsgId;
  }
  
  public AQMessageProperties.DeliveryMode getDeliveryMode()
  {
/* 239 */     return this.deliveryMode;
  }
  
  void setDeliveryMode(AQMessageProperties.DeliveryMode paramDeliveryMode)
  {
/* 246 */     this.deliveryMode = paramDeliveryMode;
  }
  
  public String toString()
  {
/* 284 */     StringBuffer localStringBuffer = new StringBuffer();
/* 285 */     localStringBuffer.append("Correlation             : " + getCorrelation() + "\n");
/* 286 */     Timestamp localTimestamp = getEnqueueTime();
/* 287 */     if (localTimestamp != null)
/* 288 */       localStringBuffer.append("Enqueue time            : " + localTimestamp + "\n");
/* 289 */     localStringBuffer.append("Exception Queue         : " + getExceptionQueue() + "\n");
/* 290 */     localStringBuffer.append("Sender                  : (" + getSender() + ")\n");
/* 291 */     int i = getDequeueAttemptsCount();
/* 292 */     if (i != -1)
/* 293 */       localStringBuffer.append("Attempts                : " + i + "\n");
/* 294 */     localStringBuffer.append("Delay                   : " + getDelay() + "\n");
/* 295 */     localStringBuffer.append("Expiration              : " + getExpiration() + "\n");
/* 296 */     AQMessageProperties.MessageState localMessageState = getState();
    
/* 298 */     if (localMessageState != null)
/* 299 */       localStringBuffer.append("State                   : " + localMessageState + "\n");
/* 300 */     localStringBuffer.append("Priority                : " + getPriority() + "\n");
/* 301 */     AQMessageProperties.DeliveryMode localDeliveryMode = getDeliveryMode();
    
/* 303 */     if (localDeliveryMode != null)
/* 304 */       localStringBuffer.append("Delivery Mode           : " + localDeliveryMode + "\n");
/* 305 */     localStringBuffer.append("Recipient List          : {");
/* 306 */     AQAgent[] arrayOfAQAgent = getRecipientList();
/* 307 */     if (arrayOfAQAgent != null)
    {
/* 309 */       for (int j = 0; j < arrayOfAQAgent.length; j++)
      {
/* 311 */         localStringBuffer.append(arrayOfAQAgent[j]);
/* 312 */         if (j != arrayOfAQAgent.length - 1)
/* 313 */           localStringBuffer.append("; ");
      }
    }
/* 316 */     localStringBuffer.append("}");
    
/* 318 */     return localStringBuffer.toString();
  }
  
  void setAttempts(int paramInt)
    throws SQLException
  {
/* 325 */     this.attrAttempts = paramInt;
  }
  
  void setEnqueueTime(Timestamp paramTimestamp)
    throws SQLException
  {
/* 334 */     this.attrEnqTime = paramTimestamp;
  }
  
  void setMessageState(AQMessageProperties.MessageState paramMessageState)
    throws SQLException
  {
/* 342 */     this.attrMsgState = paramMessageState;
  }
  
/* 347 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/AQMessagePropertiesI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */