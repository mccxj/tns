/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ class T4CTTIkscn
/*    */   extends T4CTTIMsg
/*    */ {
/*    */   long kscnbas;
/*    */   int kscnwrp;
/*    */   
/*    */   T4CTTIkscn(T4CConnection paramT4CConnection)
/*    */   {
/* 53 */     super(paramT4CConnection, (byte)0);
/*    */   }
/*    */   
/*    */   void unmarshal() throws SQLException, IOException {
/* 57 */     this.kscnbas = this.meg.unmarshalUB4();
/* 58 */     this.kscnwrp = this.meg.unmarshalUB2();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIkscn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */