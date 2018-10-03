package oracle.net.ns;
public class MarkerPacket
  extends Packet
  implements SQLnetDef
{
  protected int data;
  
  private boolean isReset;
  
  private boolean isBreak;
  
  public MarkerPacket(SessionAtts paramSessionAtts, int paramInt)
  {
/*  54 */     super(paramSessionAtts);
    
/*  56 */     createBuffer(11, 12, 0);
    
/*  58 */     this.buffer[4] = 12;
/*  59 */     this.buffer[8] = 1;
/*  60 */     this.buffer[10] = ((byte)paramInt);
  }
  
  public MarkerPacket(Packet paramPacket)
    throws NetException
  {
/*  69 */     super(paramPacket);
    
/*  71 */     this.type = this.buffer[8];
    
/*  73 */     switch (this.type)
    {
    case 0: 
/*  76 */       this.data = 0;
/*  77 */       this.isBreak = true;
/*  78 */       break;
    case 1: 
/*  80 */       this.data = this.buffer[10];
      
/*  82 */       if (this.data == 2) {
/*  83 */         this.isReset = true;
      }
      else
      {
/*  91 */         this.isBreak = true; }
/*  92 */       break;
    default: 
/*  94 */       throw new NetException(205);
    }
    
  }
  
  public boolean isBreakPkt()
  {
/* 103 */     return this.isBreak;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/MarkerPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */