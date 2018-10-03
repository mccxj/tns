/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ class T4CTTIidc
/*    */   extends T4CTTIMsg
/*    */ {
/*    */   T4CTTIkscn kpdqidcscn;
/*    */   T4CTTIqcinv[] kpdqidccinv;
/*    */   T4CTTIqcinv[] kpdqidcusr;
/*    */   long kpdqidcflg;
/*    */   
/*    */   T4CTTIidc(T4CConnection paramT4CConnection)
/*    */   {
/* 63 */     super(paramT4CConnection, (byte)0);
/*    */   }
/*    */   
/*    */   void unmarshal() throws SQLException, IOException {
/* 67 */     this.kpdqidcscn = new T4CTTIkscn(this.connection);
/* 68 */     this.kpdqidcscn.unmarshal();
/* 69 */     int i = this.meg.unmarshalSWORD();
/* 70 */     int j = (byte)this.meg.unmarshalUB1();
/* 71 */     if (i > 0)
/*    */     {
/* 73 */       this.kpdqidccinv = new T4CTTIqcinv[i];
/* 74 */       for (k = 0; k < i; k++)
/*    */       {
/* 76 */         this.kpdqidccinv[k] = new T4CTTIqcinv(this.connection);
/* 77 */         this.kpdqidccinv[k].unmarshal();
/*    */       }
/*    */     }
/*    */     else {
/* 81 */       this.kpdqidccinv = null;
/*    */     }
/* 83 */     int k = this.meg.unmarshalSWORD();
/* 84 */     if (k > 0)
/*    */     {
/* 86 */       this.kpdqidcusr = new T4CTTIqcinv[k];
/* 87 */       for (int m = 0; m < k; m++)
/*    */       {
/* 89 */         this.kpdqidcusr[m] = new T4CTTIqcinv(this.connection);
/* 90 */         this.kpdqidcusr[m].unmarshal();
/*    */       }
/*    */     }
/*    */     else {
/* 94 */       this.kpdqidcusr = null; }
/* 95 */     this.kpdqidcflg = this.meg.unmarshalUB4();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIidc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */