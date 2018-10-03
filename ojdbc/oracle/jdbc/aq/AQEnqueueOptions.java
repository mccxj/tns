package oracle.jdbc.aq;
import java.sql.SQLException;
public class AQEnqueueOptions
{
  private byte[] attrRelativeMessageId;
  private SequenceDeviationOption attrSequenceDeviation;
  private VisibilityOption attrVisibility;
  private DeliveryMode attrDeliveryMode;
  private boolean retrieveMsgId;
  private String transformation;
  
  public static enum VisibilityOption
  {
/*  50 */     ON_COMMIT(2), 
    
/*  55 */     IMMEDIATE(1);
    
    private final int mode;
    
/*  59 */     private VisibilityOption(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/*  66 */       return this.mode;
    }
  }
  
  public static enum SequenceDeviationOption
  {
/*  75 */     BOTTOM(0), 
    
/*  80 */     BEFORE(2), 
    
/*  84 */     TOP(3);
    
    private final int mode;
    
/*  88 */     private SequenceDeviationOption(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/*  95 */       return this.mode;
    }
  }
  
  public static enum DeliveryMode
  {
/* 105 */     PERSISTENT(AQDequeueOptions.DeliveryFilter.PERSISTENT.getCode()), 
    
/* 110 */     BUFFERED(AQDequeueOptions.DeliveryFilter.BUFFERED.getCode());
    
    private final int mode;
    
/* 114 */     private DeliveryMode(int paramInt) { this.mode = paramInt; }
    
    public final int getCode()
    {
/* 121 */       return this.mode;
    }
  }
  
  public AQEnqueueOptions()
  {
/* 144 */     this.attrRelativeMessageId = null;
/* 145 */     this.attrSequenceDeviation = SequenceDeviationOption.BOTTOM;
/* 146 */     this.attrVisibility = VisibilityOption.ON_COMMIT;
/* 147 */     this.attrDeliveryMode = DeliveryMode.PERSISTENT;
    
/* 151 */     this.retrieveMsgId = false;
  }
  
  /**
   * @deprecated
   */
  public void setRelativeMessageId(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 169 */     this.attrRelativeMessageId = paramArrayOfByte;
  }
  
  public byte[] getRelativeMessageId()
  {
/* 181 */     return this.attrRelativeMessageId;
  }
  
  /**
   * @deprecated
   */
  public void setSequenceDeviation(SequenceDeviationOption paramSequenceDeviationOption)
    throws SQLException
  {
/* 201 */     this.attrSequenceDeviation = paramSequenceDeviationOption;
  }
  
  public SequenceDeviationOption getSequenceDeviation()
  {
/* 213 */     return this.attrSequenceDeviation;
  }
  
  public void setVisibility(VisibilityOption paramVisibilityOption)
    throws SQLException
  {
/* 229 */     this.attrVisibility = paramVisibilityOption;
  }
  
  public VisibilityOption getVisibility()
  {
/* 241 */     return this.attrVisibility;
  }
  
  public void setDeliveryMode(DeliveryMode paramDeliveryMode)
    throws SQLException
  {
/* 259 */     this.attrDeliveryMode = paramDeliveryMode;
  }
  
  public DeliveryMode getDeliveryMode()
  {
/* 271 */     return this.attrDeliveryMode;
  }
  
  public void setRetrieveMessageId(boolean paramBoolean)
  {
/* 286 */     this.retrieveMsgId = paramBoolean;
  }
  
  public boolean getRetrieveMessageId()
  {
/* 298 */     return this.retrieveMsgId;
  }
  
  public void setTransformation(String paramString)
  {
/* 319 */     this.transformation = paramString;
  }
  
  public String getTransformation()
  {
/* 331 */     return this.transformation;
  }
  
/* 336 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQEnqueueOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */