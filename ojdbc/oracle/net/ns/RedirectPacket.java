/*    */ package oracle.net.ns;
/*    */ import java.io.IOException;
/*    */ public class RedirectPacket
/*    */   extends Packet
/*    */   implements SQLnetDef
/*    */ {
/*    */   public RedirectPacket(Packet paramPacket)
/*    */     throws IOException, NetException
/*    */   {
/* 53 */     super(paramPacket);
/*    */     
/* 55 */     this.dataOff = 10;
/*    */     
/* 57 */     this.dataLen = (this.buffer[8] & 0xFF);
/* 58 */     this.dataLen <<= 8;
/* 59 */     this.dataLen |= this.buffer[9] & 0xFF;
/*    */     
/* 61 */     extractData();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ns/RedirectPacket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */