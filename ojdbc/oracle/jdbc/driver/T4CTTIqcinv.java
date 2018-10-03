/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ class T4CTTIqcinv
/*    */   extends T4CTTIMsg
/*    */ {
/*    */   long kpdqcqid;
/*    */   long kpdqcopflg;
/* 49 */   T4CTTIkscn kpdqcscn = null;
/*    */   
/*    */   T4CTTIqcinv(T4CConnection paramT4CConnection) {
/* 52 */     super(paramT4CConnection, (byte)0);
/*    */   }
/*    */   
/*    */   void unmarshal() throws SQLException, IOException {
/* 56 */     this.kpdqcqid = this.meg.unmarshalSB8();
/* 57 */     this.kpdqcopflg = this.meg.unmarshalUB4();
/* 58 */     this.kpdqcscn = new T4CTTIkscn(this.connection);
/* 59 */     this.kpdqcscn.unmarshal();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIqcinv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */