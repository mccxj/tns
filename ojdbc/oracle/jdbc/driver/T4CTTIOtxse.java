package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIOtxse
  extends T4CTTIfun
{
  static final int OTXSTA = 1;
  static final int OTXDET = 2;
  static final int OCI_TRANS_NEW = 1;
  static final int OCI_TRANS_JOIN = 2;
  static final int OCI_TRANS_RESUME = 4;
  static final int OCI_TRANS_STARTMASK = 255;
  static final int OCI_TRANS_READONLY = 256;
  static final int OCI_TRANS_READWRITE = 512;
  static final int OCI_TRANS_SERIALIZABLE = 1024;
  static final int OCI_TRANS_ISOLMASK = 65280;
  static final int OCI_TRANS_LOOSE = 65536;
  static final int OCI_TRANS_TIGHT = 131072;
  static final int OCI_TRANS_TYPEMASK = 983040;
  static final int OCI_TRANS_NOMIGRATE = 1048576;
  static final int OCI_TRANS_SEPARABLE = 2097152;
/* 120 */   boolean sendTransactionContext = false;
  private int operation;
  private int formatId;
  private int gtridLength;
/* 124 */   T4CTTIOtxse(T4CConnection paramT4CConnection) { super(paramT4CConnection, (byte)3);
    
/* 126 */     setFunCode((short)103); }
  
  private int bqualLength;
  private int timeout;
/* 130 */   private int flag; private int[] xidapp = null;
/* 131 */   private byte[] transactionContext; private byte[] xid = null;
  
/* 134 */   private int applicationValue = -1;
/* 135 */   private byte[] ctx = null;
  
  void doOTXSE(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt)
    throws IOException, SQLException
  {
/* 143 */     if ((paramInt1 != 1) && (paramInt1 != 2))
/* 144 */       throw new SQLException("Invalid operation.");
/* 145 */     this.operation = paramInt1;
/* 146 */     this.formatId = paramInt2;
/* 147 */     this.gtridLength = paramInt3;
/* 148 */     this.bqualLength = paramInt4;
/* 149 */     this.timeout = paramInt5;
/* 150 */     this.flag = paramInt6;
/* 151 */     this.xidapp = paramArrayOfInt;
/* 152 */     this.transactionContext = paramArrayOfByte1;
/* 153 */     this.xid = paramArrayOfByte2;
    
/* 156 */     this.applicationValue = -1;
/* 157 */     this.ctx = null;
    
/* 159 */     if ((this.operation == 2) && (this.transactionContext == null))
    {
/* 161 */       throw new SQLException("Transaction context cannot be null when detach is called.");
    }
/* 163 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 172 */     int i = this.operation;
    
/* 174 */     this.meg.marshalSWORD(i);
    
/* 177 */     if (this.operation == 2)
    {
/* 179 */       this.sendTransactionContext = true;
      
/* 181 */       this.meg.marshalPTR();
    }
    else
    {
/* 185 */       this.sendTransactionContext = false;
      
/* 187 */       this.meg.marshalNULLPTR();
    }
    
/* 191 */     if (this.transactionContext == null) {
/* 192 */       this.meg.marshalUB4(0L);
    } else {
/* 194 */       this.meg.marshalUB4(this.transactionContext.length);
    }
    
/* 197 */     this.meg.marshalUB4(this.formatId);
    
/* 200 */     this.meg.marshalUB4(this.gtridLength);
    
/* 203 */     this.meg.marshalUB4(this.bqualLength);
    
/* 206 */     if (this.xid != null) {
/* 207 */       this.meg.marshalPTR();
    } else {
/* 209 */       this.meg.marshalNULLPTR();
    }
    
/* 212 */     if (this.xid != null) {
/* 213 */       this.meg.marshalUB4(this.xid.length);
    } else {
/* 215 */       this.meg.marshalUB4(0L);
    }
    
/* 218 */     this.meg.marshalUB4(this.flag);
    
/* 221 */     this.meg.marshalUWORD(this.timeout);
    
/* 224 */     if (this.xidapp != null) {
/* 225 */       this.meg.marshalPTR();
    } else {
/* 227 */       this.meg.marshalNULLPTR();
    }
/* 229 */     this.meg.marshalPTR();
/* 230 */     this.meg.marshalPTR();
    
/* 232 */     int j = 0;
/* 233 */     int k = 0;
    
/* 235 */     if (this.connection.getTTCVersion() >= 5)
    {
/* 237 */       if (this.connection.internalName != null)
      {
/* 239 */         j = 1;
/* 240 */         this.meg.marshalPTR();
/* 241 */         this.meg.marshalUB4(this.connection.internalName.length);
      }
      else
      {
/* 245 */         this.meg.marshalNULLPTR();
/* 246 */         this.meg.marshalUB4(0L);
      }
/* 248 */       if (this.connection.externalName != null)
      {
/* 250 */         k = 1;
/* 251 */         this.meg.marshalPTR();
/* 252 */         this.meg.marshalUB4(this.connection.externalName.length);
      }
      else
      {
/* 256 */         this.meg.marshalNULLPTR();
/* 257 */         this.meg.marshalUB4(0L);
      }
    }
    
/* 263 */     if (this.sendTransactionContext) {
/* 264 */       this.meg.marshalB1Array(this.transactionContext);
    }
/* 266 */     if (this.xid != null) {
/* 267 */       this.meg.marshalB1Array(this.xid);
    }
/* 269 */     if (this.xidapp != null) {
/* 270 */       this.meg.marshalUB4(this.xidapp[0]);
    }
/* 272 */     if (this.connection.getTTCVersion() >= 5)
    {
/* 274 */       if (j != 0)
/* 275 */         this.meg.marshalCHR(this.connection.internalName);
/* 276 */       if (k != 0) {
/* 277 */         this.meg.marshalCHR(this.connection.externalName);
      }
    }
  }
  
  byte[] getContext()
  {
/* 286 */     return this.ctx;
  }
  
  int getApplicationValue()
  {
/* 292 */     return this.applicationValue;
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 299 */     this.applicationValue = ((int)this.meg.unmarshalUB4());
    
/* 302 */     int i = this.meg.unmarshalUB2();
    
/* 304 */     this.ctx = this.meg.unmarshalNBytes(i);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 320 */     return this.connection;
  }
  
/* 325 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIOtxse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */