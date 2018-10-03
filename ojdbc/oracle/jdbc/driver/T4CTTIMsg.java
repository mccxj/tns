package oracle.jdbc.driver;
import java.io.IOException;
import oracle.jdbc.internal.OracleConnection;
class T4CTTIMsg
{
  static final byte TTIPRO = 1;
  static final byte TTIDTY = 2;
  static final byte TTIFUN = 3;
  static final byte TTIOER = 4;
  static final byte TTIRXH = 6;
  static final byte TTIRXD = 7;
  static final byte TTIRPA = 8;
  static final byte TTISTA = 9;
  static final byte TTIIOV = 11;
  static final byte TTISLG = 12;
  static final byte TTIOAC = 13;
  static final byte TTILOBD = 14;
  static final byte TTIWRN = 15;
  static final byte TTIDCB = 16;
  static final byte TTIPFN = 17;
  static final byte TTIFOB = 19;
  static final byte TTINTY = 1;
  static final byte TTIBVC = 21;
  static final byte TTISPF = 23;
  static final byte OERFSPND = 1;
  static final byte OERFATAL = 2;
  static final byte OERFPLSW = 4;
  static final byte OERFUPD = 8;
  static final byte OERFEXIT = 16;
  static final byte OERFNCF = 32;
  static final byte OERFRDONLY = 64;
  static final short OERFSBRK = 128;
  static final byte OERwANY = 1;
  static final byte OERwTRUN = 2;
  static final byte OERwLICM = 2;
  static final byte OERwNVIC = 4;
  static final byte OERwITCE = 8;
  static final byte OERwUDnW = 16;
  static final byte OERwCPER = 32;
  static final byte OERwPLEX = 64;
  static final short ORACLE8_PROD_VERSION = 8030;
  static final short ORACLE81_PROD_VERSION = 8100;
  static final short MIN_OVERSION_SUPPORTED = 7230;
  static final short MIN_TTCVER_SUPPORTED = 4;
  static final short V8_TTCVER_SUPPORTED = 5;
  static final short MAX_TTCVER_SUPPORTED = 6;
  static final int REFCURSOR_SIZE = 5;
  static final byte OCQCINV = 1;
  static final byte OCOSPID = 2;
  static final byte OCTRCEVT = 3;
  static final byte OCSESSRET = 4;
  static final byte OCSSYNC = 5;
  static final byte OCXSSS = 6;
  static final byte MAX_OCFN = 6;
  static final int TTIEOCFRO = 1;
  static final int TTIEOCCUR = 2;
  static final int TTIEOCDON = 4;
  static final int TTIEOCECT = 8;
  static final int TTIEOCFSE = 16;
  static final int TTIEOCFPR = 32;
  static final int TTIEOCFSW = 64;
  static final int TTIEOCFMF = 128;
  static final int TTIEOCETS = 256;
  static final int TTIEOCFCP = 512;
  static final int TTIEOCFTI = 1024;
  static final int TTIEOCFIV = Integer.MIN_VALUE;
  private byte ttcCode;
  final T4CConnection connection;
  final T4CMAREngine meg;
  
  private T4CTTIMsg()
  {
/* 242 */     this.ttcCode = 0;
/* 243 */     this.connection = null;
/* 244 */     this.meg = null;
  }
  
  T4CTTIMsg(T4CConnection paramT4CConnection, byte paramByte)
  {
/* 250 */     this.connection = paramT4CConnection;
/* 251 */     this.ttcCode = paramByte;
/* 252 */     this.meg = paramT4CConnection.getMarshalEngine();
  }
  
  final byte getTTCCode()
  {
/* 259 */     return this.ttcCode;
  }
  
  final void setTTCCode(byte paramByte)
  {
/* 272 */     this.ttcCode = paramByte;
  }
  
  void marshalTTCcode()
    throws IOException
  {
/* 279 */     this.meg.marshalUB1((short)this.ttcCode);
  }
  
  void send()
    throws IOException
  {}
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 304 */     return null;
  }
  
/* 309 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIMsg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */