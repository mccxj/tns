package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.TIMESTAMPTZ;
final class T4CTTIokpn
  extends T4CTTIfun
{
  static final int REGISTER_KPNDEF = 1;
  static final int UNREGISTER_KPNDEF = 2;
  static final int POST_KPNDEF = 3;
  static final int EXISTINGCLIENT_KPNDEF = 0;
  static final int NEWCLIENT_KPNDEF = 1;
  static final int KPUN_PRS_RAW = 1;
  static final int KPUN_VER_10200 = 2;
  static final int KPUN_VER_11100 = 3;
  static final int KPUN_VER_11200 = 4;
  static final int OCI_SUBSCR_NAMESPACE_ANONYMOUS = 0;
  static final int OCI_SUBSCR_NAMESPACE_AQ = 1;
  static final int OCI_SUBSCR_NAMESPACE_DBCHANGE = 2;
  static final int OCI_SUBSCR_NAMESPACE_MAX = 3;
  static final int KPD_CHNF_OPFILTER = 1;
  static final int KPD_CHNF_INSERT = 2;
  static final int KPD_CHNF_UPDATE = 4;
  static final int KPD_CHNF_DELETE = 8;
  static final int KPD_CHNF_ROWID = 16;
  static final int KPD_CQ_QUERYNF = 32;
  static final int KPD_CQ_BEST_EFFORT = 64;
  static final int KPD_CQ_CLQRYCACHE = 128;
  static final int KPD_CHNF_INVALID_REGID = 0;
  static final int SUBSCR_QOS_RELIABLE = 1;
  static final int SUBSCR_QOS_PAYLOAD = 2;
  static final int SUBSCR_QOS_REPLICATE = 4;
  static final int SUBSCR_QOS_SECURE = 8;
  static final int SUBSCR_QOS_PURGE_ON_NTFN = 16;
  static final int SUBSCR_QOS_MULTICBK = 32;
  static final byte SUBSCR_NTFN_GROUPING_CLASS_NONE = 0;
  static final byte SUBSCR_NTFN_GROUPING_CLASS_TIME = 1;
  static final byte SUBSCR_NTFN_GROUPING_TYPE_SUMMARY = 1;
  static final byte SUBSCR_NTFN_GROUPING_TYPE_LAST = 2;
  private int opcode;
  private int mode;
  private int nbOfRegistrationInfo;
  private String user;
  private String location;
  private int[] namespace;
  private int[] kpdnrgrpval;
  private int[] kpdnrgrprepcnt;
  private int[] payloadType;
  private int[] qosFlags;
  private int[] timeout;
  private int[] dbchangeOpFilter;
  
  T4CTTIokpn(T4CConnection paramT4CConnection)
  {
/* 199 */     super(paramT4CConnection, (byte)3);
    
/* 201 */     setFunCode((short)125);
  }
  
/* 207 */   private int[] dbchangeTxnLag = null;
  
/* 210 */   private byte[][] registeredAgentName = (byte[][])null;
/* 211 */   private byte[][] kpdnrcx = (byte[][])null;
/* 212 */   private byte[] kpdnrgrpcla; private byte[] kpdnrgrptyp = null;
/* 213 */   private TIMESTAMPTZ[] kpdnrgrpstatim = null;
/* 214 */   private long[] dbchangeRegistrationId = null;
/* 215 */   private byte[] userArr = null;
/* 216 */   private byte[] locationArr = null;
  
/* 218 */   private long regid = 0L;
  
  void doOKPN(int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, int[] paramArrayOfInt1, String[] paramArrayOfString, byte[][] paramArrayOfByte, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[] paramArrayOfInt4, int[] paramArrayOfInt5, int[] paramArrayOfInt6, long[] paramArrayOfLong, byte[] paramArrayOfByte1, int[] paramArrayOfInt7, byte[] paramArrayOfByte2, TIMESTAMPTZ[] paramArrayOfTIMESTAMPTZ, int[] paramArrayOfInt8)
    throws IOException, SQLException
  {
/* 245 */     this.opcode = paramInt1;
/* 246 */     this.mode = paramInt2;
/* 247 */     this.user = paramString1;
/* 248 */     this.location = paramString2;
/* 249 */     this.nbOfRegistrationInfo = paramInt3;
/* 250 */     this.namespace = paramArrayOfInt1;
/* 251 */     this.kpdnrcx = paramArrayOfByte;
/* 252 */     this.payloadType = paramArrayOfInt2;
/* 253 */     this.qosFlags = paramArrayOfInt3;
/* 254 */     this.timeout = paramArrayOfInt4;
/* 255 */     this.dbchangeOpFilter = paramArrayOfInt5;
/* 256 */     this.dbchangeTxnLag = paramArrayOfInt6;
/* 257 */     this.dbchangeRegistrationId = paramArrayOfLong;
/* 258 */     this.kpdnrgrpcla = paramArrayOfByte1;
/* 259 */     this.kpdnrgrpval = paramArrayOfInt7;
/* 260 */     this.kpdnrgrptyp = paramArrayOfByte2;
/* 261 */     this.kpdnrgrpstatim = paramArrayOfTIMESTAMPTZ;
/* 262 */     this.kpdnrgrprepcnt = paramArrayOfInt8;
    
/* 264 */     this.registeredAgentName = new byte[this.nbOfRegistrationInfo][];
/* 265 */     for (int i = 0; i < this.nbOfRegistrationInfo; i++) {
/* 266 */       if (paramArrayOfString[i] != null)
/* 267 */         this.registeredAgentName[i] = this.meg.conv.StringToCharBytes(paramArrayOfString[i]);
    }
/* 269 */     if (this.user != null) {
/* 270 */       this.userArr = this.meg.conv.StringToCharBytes(this.user);
    } else {
/* 272 */       this.userArr = null;
    }
/* 274 */     if (this.location != null) {
/* 275 */       this.locationArr = this.meg.conv.StringToCharBytes(this.location);
    } else {
/* 277 */       this.locationArr = null;
    }
    
/* 280 */     this.regid = 0L;
    
/* 282 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 290 */     int i = 1;
/* 291 */     int j = 2;
    
/* 294 */     this.meg.marshalUB1((short)(byte)this.opcode);
    
/* 296 */     this.meg.marshalUB4(this.mode);
    
/* 298 */     if (this.userArr != null)
    {
/* 300 */       this.meg.marshalPTR();
/* 301 */       this.meg.marshalUB4(this.userArr.length);
    }
    else
    {
/* 305 */       this.meg.marshalNULLPTR();
/* 306 */       this.meg.marshalUB4(0L);
    }
    
/* 310 */     if (this.locationArr != null)
    {
/* 312 */       this.meg.marshalPTR();
/* 313 */       this.meg.marshalUB4(this.locationArr.length);
    }
    else
    {
/* 317 */       this.meg.marshalNULLPTR();
/* 318 */       this.meg.marshalUB4(0L);
    }
    
/* 322 */     this.meg.marshalPTR();
/* 323 */     this.meg.marshalUB4(this.nbOfRegistrationInfo);
    
/* 325 */     this.meg.marshalUB2(i);
    
/* 327 */     this.meg.marshalUB2(j);
/* 328 */     if (this.connection.getTTCVersion() >= 4)
    {
/* 331 */       this.meg.marshalNULLPTR();
      
/* 333 */       this.meg.marshalPTR();
      
/* 335 */       if (this.connection.getTTCVersion() >= 5)
      {
/* 338 */         this.meg.marshalNULLPTR();
        
/* 340 */         this.meg.marshalPTR();
      }
    }
    
/* 346 */     if (this.userArr != null)
/* 347 */       this.meg.marshalCHR(this.userArr);
/* 348 */     if (this.locationArr != null) {
/* 349 */       this.meg.marshalCHR(this.locationArr);
    }
/* 351 */     for (int k = 0; k < this.nbOfRegistrationInfo; k++)
    {
/* 354 */       this.meg.marshalUB4(this.namespace[k]);
      
/* 356 */       byte[] arrayOfByte1 = this.registeredAgentName[k];
      
/* 358 */       if ((arrayOfByte1 != null) && (arrayOfByte1.length > 0))
      {
/* 360 */         this.meg.marshalUB4(arrayOfByte1.length);
/* 361 */         this.meg.marshalCLR(arrayOfByte1, 0, arrayOfByte1.length);
      }
      else {
/* 364 */         this.meg.marshalUB4(0L);
      }
/* 366 */       if ((this.kpdnrcx[k] != null) && (this.kpdnrcx[k].length > 0))
      {
/* 368 */         this.meg.marshalUB4(this.kpdnrcx[k].length);
/* 369 */         this.meg.marshalCLR(this.kpdnrcx[k], 0, this.kpdnrcx[k].length);
      }
      else {
/* 372 */         this.meg.marshalUB4(0L);
      }
/* 374 */       this.meg.marshalUB4(this.payloadType[k]);
/* 375 */       if (this.connection.getTTCVersion() >= 4)
      {
/* 378 */         this.meg.marshalUB4(this.qosFlags[k]);
/* 379 */         byte[] arrayOfByte2 = new byte[0];
/* 380 */         this.meg.marshalUB4(arrayOfByte2.length);
/* 381 */         if (arrayOfByte2.length > 0) {
/* 382 */           this.meg.marshalCLR(arrayOfByte2, arrayOfByte2.length);
        }
/* 384 */         this.meg.marshalUB4(this.timeout[k]);
        
/* 386 */         int m = 0;
/* 387 */         this.meg.marshalUB4(m);
        
/* 389 */         this.meg.marshalUB4(this.dbchangeOpFilter[k]);
        
/* 391 */         this.meg.marshalUB4(this.dbchangeTxnLag[k]);
/* 392 */         this.meg.marshalUB4((int)this.dbchangeRegistrationId[k]);
        
/* 394 */         if (this.connection.getTTCVersion() >= 5)
        {
/* 396 */           this.meg.marshalUB1((short)this.kpdnrgrpcla[k]);
/* 397 */           this.meg.marshalUB4(this.kpdnrgrpval[k]);
/* 398 */           this.meg.marshalUB1((short)this.kpdnrgrptyp[k]);
/* 399 */           if (this.kpdnrgrpstatim[k] == null) {
/* 400 */             this.meg.marshalDALC(null);
          } else
/* 402 */             this.meg.marshalDALC(this.kpdnrgrpstatim[k].shareBytes());
/* 403 */           this.meg.marshalSB4(this.kpdnrgrprepcnt[k]);
          
/* 406 */           this.meg.marshalSB8(this.dbchangeRegistrationId[k]);
        }
      }
    }
  }
  
  long getRegistrationId()
  {
/* 416 */     return this.regid;
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 426 */     int i = (int)this.meg.unmarshalUB4();
/* 427 */     for (int j = 0; j < i; j++)
/* 428 */       this.meg.unmarshalUB4();
/* 429 */     int[] arrayOfInt = new int[i];
/* 430 */     for (int k = 0; k < i; k++)
/* 431 */       arrayOfInt[k] = ((int)this.meg.unmarshalUB4());
/* 432 */     this.regid = arrayOfInt[0];
/* 433 */     if (this.connection.getTTCVersion() >= 5)
    {
/* 435 */       k = (int)this.meg.unmarshalUB4();
/* 436 */       long l = this.meg.unmarshalSB8();
/* 437 */       this.regid = l;
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 453 */     return this.connection;
  }
  
/* 458 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIokpn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */