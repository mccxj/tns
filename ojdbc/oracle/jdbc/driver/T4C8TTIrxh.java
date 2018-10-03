package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
class T4C8TTIrxh
  extends T4CTTIMsg
{
  short rxhflg;
  int numRqsts;
  int iterNum;
  int numItersThisTime;
  int uacBufLength;
  static final byte RXHFU2O = 1;
  static final byte RXHFEOR = 2;
  static final byte RXHPLSV = 4;
  static final byte RXHFRXR = 8;
  static final byte RXHFKCO = 16;
  static final byte RXHFDCF = 32;
  
  T4C8TTIrxh(T4CConnection paramT4CConnection)
  {
/*  99 */     super(paramT4CConnection, (byte)0);
  }
  
  void unmarshalV10(T4CTTIrxd paramT4CTTIrxd)
    throws SQLException, IOException
  {
/* 111 */     this.rxhflg = this.meg.unmarshalUB1();
/* 112 */     this.numRqsts = this.meg.unmarshalUB2();
/* 113 */     this.iterNum = this.meg.unmarshalUB2();
    
/* 117 */     this.numRqsts += this.iterNum * 256;
    
/* 119 */     this.numItersThisTime = this.meg.unmarshalUB2();
/* 120 */     this.uacBufLength = this.meg.unmarshalUB2();
    
/* 123 */     byte[] arrayOfByte1 = this.meg.unmarshalDALC();
/* 124 */     paramT4CTTIrxd.readBitVector(arrayOfByte1);
    
/* 127 */     byte[] arrayOfByte2 = this.meg.unmarshalDALC();
  }
  
  void init()
  {
/* 134 */     this.rxhflg = 0;
/* 135 */     this.numRqsts = 0;
/* 136 */     this.iterNum = 0;
/* 137 */     this.numItersThisTime = 0;
/* 138 */     this.uacBufLength = 0;
  }
  
/* 188 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  void print() {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIrxh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */