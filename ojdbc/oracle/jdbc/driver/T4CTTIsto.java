package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIsto
  extends T4CTTIfun
{
  static final short OV6STRT = 48;
  static final short OV6STOP = 49;
  static final int STOMFDBA = 1;
  static final int STOMFACA = 2;
  static final int STOMFALO = 4;
  static final int STOMFSHU = 8;
  static final int STOMFFRC = 16;
  static final int STOMFPOL = 32;
  static final int STOMFABO = 64;
  static final int STOMFATX = 128;
  static final int STOMFLTX = 256;
  static final int STOSDONE = 1;
  static final int STOSINPR = 2;
  static final int STOSERR = 3;
  
  T4CTTIsto(T4CConnection paramT4CConnection)
  {
/*  95 */     super(paramT4CConnection, (byte)3);
  }
  
/*  99 */   private int inmode = 0;
/* 100 */   private int outmode = 0;
  
  void doOV6STRT(int paramInt) throws IOException, SQLException
  {
/* 104 */     setFunCode((short)48);
/* 105 */     this.inmode = paramInt;
/* 106 */     this.outmode = 0;
/* 107 */     doRPC();
  }
  
  void doOV6STOP(int paramInt)
    throws IOException, SQLException
  {
/* 113 */     setFunCode((short)49);
/* 114 */     this.inmode = paramInt;
/* 115 */     this.outmode = 0;
/* 116 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 122 */     this.meg.marshalSWORD(this.inmode);
/* 123 */     this.meg.marshalPTR();
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 136 */     this.outmode = ((int)this.meg.unmarshalUB4());
/* 137 */     if (this.outmode == 3) {}
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 156 */     return this.connection;
  }
  
/* 161 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIsto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */