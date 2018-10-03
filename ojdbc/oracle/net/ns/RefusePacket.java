/*    */ package oracle.net.ns;
/*    */ import java.io.IOException;
/*    */ public class RefusePacket
/*    */   extends Packet
/*    */   implements SQLnetDef
/*    */ {
/*    */   protected int userReason;
/*    */   protected int systemReason;
/*    */   
/*    */   public RefusePacket(Packet paramPacket)
/*    */     throws IOException, NetException
/*    */   {
/* 58 */     super(paramPacket);
/*    */     
/* 61 */     this.userReason = this.buffer[8];
/* 62 */     this.systemReason = this.buffer[9];
/*    */     
/* 64 */     this.dataOff = 12;
/*    */     
/* 66 */     this.dataLen = (this.buffer[10] & 0xFF);
/* 67 */     this.dataLen <<= 8;
/* 68 */     this.dataLen |= this.buffer[11] & 0xFF;
/*    */     
/* 72 */     extractData();
/*    */   }
/*    */   
/* 77 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/RefusePacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */