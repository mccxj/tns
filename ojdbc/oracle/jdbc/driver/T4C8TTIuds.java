package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
class T4C8TTIuds
  extends T4CTTIMsg
{
  T4CTTIoac udsoac;
  boolean udsnull;
  short udscnl;
  byte optimizeOAC;
  byte[] udscolnm;
  short udscolnl;
  byte[] udssnm;
  long udssnl;
  int[] snnumchar;
  byte[] udstnm;
  long udstnl;
  int[] tnnumchar;
  int[] numBytes;
  short udskpos;
  int udsflg;
  static final int UDSFCOLSEC_ENABLED = 1;
  static final int UDSFCOLSEC_UNKNOWN = 2;
  static final int UDSFCOLSEC_UNAUTH_DATA_NULL = 4;
  
  T4C8TTIuds(T4CConnection paramT4CConnection)
  {
/* 122 */     super(paramT4CConnection, (byte)0);
    
/* 124 */     this.udskpos = -1;
/* 125 */     this.udsoac = new T4CTTIoac(paramT4CConnection);
  }
  
  void unmarshal()
    throws IOException, SQLException
  {
/* 141 */     this.udsoac.unmarshal();
    
/* 143 */     int i = this.meg.unmarshalUB1();
/* 144 */     this.udsnull = (i > 0);
/* 145 */     this.udscnl = this.meg.unmarshalUB1();
    
/* 147 */     this.numBytes = new int[1];
/* 148 */     this.udscolnm = this.meg.unmarshalDALC(this.numBytes);
    
/* 151 */     this.snnumchar = new int[1];
/* 152 */     this.udssnm = this.meg.unmarshalDALC(this.snnumchar);
/* 153 */     this.udssnl = this.udssnm.length;
    
/* 156 */     this.tnnumchar = new int[1];
/* 157 */     this.udstnm = this.meg.unmarshalDALC(this.tnnumchar);
/* 158 */     this.udstnl = this.udstnm.length;
    
/* 161 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 163 */       this.udskpos = ((short)this.meg.unmarshalUB2());
      
/* 165 */       if (this.connection.getTTCVersion() >= 6) {
/* 166 */         this.udsflg = ((int)this.meg.unmarshalUB4());
      }
    }
    else {
/* 170 */       this.udskpos = -1;
    }
  }
  
  short getKernelPosition()
  {
/* 187 */     return this.udskpos;
  }
  
  byte[] getColumName()
  {
/* 194 */     return this.udscolnm;
  }
  
  byte[] getTypeName()
  {
/* 201 */     return this.udstnm;
  }
  
  byte[] getSchemaName()
  {
/* 208 */     return this.udssnm;
  }
  
  short getTypeCharLength()
  {
/* 215 */     return (short)this.tnnumchar[0];
  }
  
  short getColumNameByteLength()
  {
/* 222 */     return (short)this.numBytes[0];
  }
  
  short getSchemaCharLength()
  {
/* 229 */     return (short)this.snnumchar[0];
  }
  
/* 278 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  void print() {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIuds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */