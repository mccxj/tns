/*    */ package oracle.sql;
/*    */ import java.sql.Timestamp;
/*    */ public class OffsetDST
/*    */ {
/*    */   private Timestamp timestamp;
/*    */   private int offset;
/*    */   private byte DSTflag;
/*    */   
/*    */   public OffsetDST(Timestamp paramTimestamp, int paramInt, byte paramByte)
/*    */   {
/* 44 */     this.timestamp = paramTimestamp;
/* 45 */     this.offset = paramInt;
/* 46 */     this.DSTflag = paramByte;
/*    */   }
/*    */   
/*    */   public OffsetDST()
/*    */   {
/* 53 */     this.timestamp = new Timestamp(0L);
/* 54 */     this.offset = 0;
/* 55 */     this.DSTflag = 0;
/*    */   }
/*    */   
/*    */   public int getOFFSET()
/*    */   {
/* 64 */     return this.offset;
/*    */   }
/*    */   
/*    */   public byte getDSTFLAG()
/*    */   {
/* 69 */     return this.DSTflag;
/*    */   }
/*    */   
/*    */   public Timestamp getTimestamp()
/*    */   {
/* 74 */     return this.timestamp;
/*    */   }
/*    */   
/*    */   public void setOFFSET(int paramInt)
/*    */   {
/* 80 */     this.offset = paramInt;
/*    */   }
/*    */   
/*    */   public void setDSTFLAG(byte paramByte)
/*    */   {
/* 85 */     this.DSTflag = paramByte;
/*    */   }
/*    */   
/*    */   public long getTime()
/*    */   {
/* 90 */     return this.timestamp.getTime();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/OffsetDST.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */