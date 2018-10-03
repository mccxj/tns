package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValue;
class T4CTTIkvarr
  extends T4CTTIMsg
{
/* 106 */   KeywordValue[] kpdkvarrptr = null;
  long kpdkvarrflg;
  
  T4CTTIkvarr(T4CConnection paramT4CConnection) {
/* 110 */     super(paramT4CConnection, (byte)0);
  }
  
  void unmarshal() throws SQLException, IOException {
/* 114 */     int i = (int)this.meg.unmarshalUB4();
/* 115 */     int j = (byte)this.meg.unmarshalUB1();
/* 116 */     if (i > 0)
    {
/* 118 */       this.kpdkvarrptr = new KeywordValueI[i];
/* 119 */       for (int k = 0; k < i; k++)
/* 120 */         this.kpdkvarrptr[k] = KeywordValueI.unmarshal(this.meg);
/* 121 */       this.connection.updateSessionProperties(this.kpdkvarrptr);
    }
    else {
/* 124 */       this.kpdkvarrptr = null; }
/* 125 */     this.kpdkvarrflg = this.meg.unmarshalUB4();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIkvarr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */