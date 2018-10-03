package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIOtxen
  extends T4CTTIfun
{
  static final int OTXCOMIT = 1;
  static final int OTXABORT = 2;
  static final int OTXPREPA = 3;
  static final int OTXFORGT = 4;
  static final int OTXRECOV = 5;
  static final int OTXMLPRE = 6;
  static final int K2CMDprepare = 0;
  static final int K2CMDrqcommit = 1;
  static final int K2CMDcommit = 2;
  static final int K2CMDabort = 3;
  static final int K2CMDrdonly = 4;
  static final int K2CMDforget = 5;
  static final int K2CMDrecovered = 7;
  static final int K2CMDtimeout = 8;
  static final int K2STAidle = 0;
  static final int K2STAcollecting = 1;
  static final int K2STAprepared = 2;
  static final int K2STAcommitted = 3;
  static final int K2STAhabort = 4;
  static final int K2STAhcommit = 5;
  static final int K2STAhdamage = 6;
  static final int K2STAtimeout = 7;
  static final int K2STAinactive = 9;
  static final int K2STAactive = 10;
  static final int K2STAptprepared = 11;
  static final int K2STAptcommitted = 12;
  static final int K2STAmax = 13;
  static final int OTXNDEF_F_CWRBATCH = 1;
  static final int OTXNDEF_F_CWRBATOPT = 2;
  static final int OTXNDEF_F_CWRNOWAIT = 4;
  static final int OTXNDEF_F_CWRWATOPT = 8;
  static final int OTXNDEF_F_CWRBATMSK = 3;
  static final int OTXNDEF_F_CWRWATMSK = 12;
  private int operation;
  private int formatId;
  private int gtridLength;
  private int bqualLength;
  private int timeout;
  private int inState;
  private int txnflg;
  private byte[] transactionContext;
  
  T4CTTIOtxen(T4CConnection paramT4CConnection)
  {
/* 133 */     super(paramT4CConnection, (byte)3);
    
/* 135 */     setFunCode((short)104);
  }
  
/* 140 */   private byte[] xid = null;
/* 141 */   private int outState = -1;
  
  void doOTXEN(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    throws IOException, SQLException
  {
/* 149 */     if ((paramInt1 != 1) && (paramInt1 != 2) && (paramInt1 != 3) && (paramInt1 != 4) && (paramInt1 != 5) && (paramInt1 != 6))
    {
/* 154 */       throw new SQLException("Invalid operation."); }
/* 155 */     this.operation = paramInt1;
/* 156 */     this.formatId = paramInt2;
/* 157 */     this.gtridLength = paramInt3;
/* 158 */     this.bqualLength = paramInt4;
/* 159 */     this.timeout = paramInt5;
/* 160 */     this.inState = paramInt6;
/* 161 */     this.txnflg = paramInt7;
    
/* 163 */     this.transactionContext = paramArrayOfByte1;
/* 164 */     this.xid = paramArrayOfByte2;
    
/* 167 */     this.outState = -1;
/* 168 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 177 */     int i = this.operation;
    
/* 179 */     this.meg.marshalSWORD(i);
    
/* 182 */     if (this.transactionContext == null) {
/* 183 */       this.meg.marshalNULLPTR();
    } else {
/* 185 */       this.meg.marshalPTR();
    }
    
/* 188 */     if (this.transactionContext == null) {
/* 189 */       this.meg.marshalUB4(0L);
    } else {
/* 191 */       this.meg.marshalUB4(this.transactionContext.length);
    }
    
/* 194 */     this.meg.marshalUB4(this.formatId);
    
/* 197 */     this.meg.marshalUB4(this.gtridLength);
    
/* 200 */     this.meg.marshalUB4(this.bqualLength);
    
/* 203 */     if (this.xid != null) {
/* 204 */       this.meg.marshalPTR();
    } else {
/* 206 */       this.meg.marshalNULLPTR();
    }
    
/* 209 */     if (this.xid != null) {
/* 210 */       this.meg.marshalUB4(this.xid.length);
    } else {
/* 212 */       this.meg.marshalUB4(0L);
    }
    
/* 215 */     this.meg.marshalUWORD(this.timeout);
    
/* 218 */     this.meg.marshalUB4(this.inState);
    
/* 221 */     this.meg.marshalPTR();
    
/* 223 */     if (this.connection.getTTCVersion() >= 4)
    {
/* 236 */       this.meg.marshalUB4(this.txnflg);
    }
    
/* 241 */     if (this.transactionContext != null) {
/* 242 */       this.meg.marshalB1Array(this.transactionContext);
    }
/* 244 */     if (this.xid != null) {
/* 245 */       this.meg.marshalB1Array(this.xid);
    }
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 253 */     this.outState = ((int)this.meg.unmarshalUB4());
  }
  
  int getOutStateFromServer()
  {
/* 259 */     return this.outState;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 274 */     return null;
  }
  
/* 279 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIOtxen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */