package oracle.jdbc.xa.client;
import java.io.IOException;
import java.sql.Connection;
import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;
import oracle.jdbc.oracore.Util;
public class OracleXAHeteroResource
  extends OracleXAResource
{
/*  33 */   private int rmid = -1;
  
  public OracleXAHeteroResource(Connection paramConnection, OracleXAConnection paramOracleXAConnection)
    throws XAException
  {
/*  43 */     this.connection = paramConnection;
/*  44 */     this.xaconnection = paramOracleXAConnection;
    
/*  46 */     if (this.connection == null) {
/*  47 */       throw new XAException(-7);
    }
  }
  
  public void start(Xid paramXid, int paramInt)
    throws XAException
  {
/*  85 */     if (paramXid == null)
    {
/*  89 */       throw new XAException(-5);
    }
    
/*  93 */     int i = paramInt & 0xFF00;
    
/*  95 */     paramInt &= 0xFFFF00FF;
    
/*  98 */     if ((paramInt & 0x8200002) != paramInt)
    {
/* 102 */       throw new XAException(-5);
    }
    
/* 106 */     if (((i & 0xFF00) != 0) && (i != 256) && (i != 512) && (i != 1024))
    {
/* 111 */       throw new XAException(-5);
    }
    
/* 115 */     if (((i & 0xFF00) != 0) && ((paramInt & 0x8200000) != 0))
    {
/* 120 */       throw new XAException(-5);
    }
    
    try
    {
/* 125 */       saveAndAlterAutoCommitModeForGlobalTransaction();
      
/* 128 */       paramInt |= i;
      
/* 130 */       int j = paramXid.getFormatId();
/* 131 */       byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 132 */       byte[] arrayOfByte2 = paramXid.getBranchQualifier();
      
/* 134 */       int k = t2cDoXaStart(j, arrayOfByte1, arrayOfByte2, this.rmid, paramInt, 0);
      
/* 139 */       checkStatus(k);
/* 140 */       enterGlobalTxnMode();
    }
    catch (XAException localXAException)
    {
/* 145 */       restoreAutoCommitModeForGlobalTransaction();
/* 146 */       throw localXAException;
    }
  }
  
  public void end(Xid paramXid, int paramInt)
    throws XAException
  {
    try
    {
/* 180 */       if (paramXid == null)
      {
/* 184 */         throw new XAException(-5);
      }
      
/* 188 */       int i = 638582786;
/* 189 */       if ((paramInt & i) != paramInt)
      {
/* 193 */         throw new XAException(-5);
      }
      
/* 196 */       int j = paramXid.getFormatId();
/* 197 */       byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 198 */       byte[] arrayOfByte2 = paramXid.getBranchQualifier();
/* 199 */       exitGlobalTxnMode();
      
/* 202 */       int k = t2cDoXaEnd(j, arrayOfByte1, arrayOfByte2, this.rmid, paramInt, 0);
      
/* 207 */       checkStatus(k);
    }
    finally
    {
/* 211 */       restoreAutoCommitModeForGlobalTransaction();
    }
  }
  
  public void commit(Xid paramXid, boolean paramBoolean)
    throws XAException
  {
/* 237 */     if (paramXid == null)
    {
/* 241 */       throw new XAException(-5);
    }
    
/* 244 */     int i = paramBoolean ? 1073741824 : 0;
    
/* 246 */     int j = paramXid.getFormatId();
/* 247 */     byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 248 */     byte[] arrayOfByte2 = paramXid.getBranchQualifier();
    
/* 250 */     int k = t2cDoXaCommit(j, arrayOfByte1, arrayOfByte2, this.rmid, i, 0);
    
/* 255 */     checkStatus(k);
  }
  
  public int prepare(Xid paramXid)
    throws XAException
  {
/* 277 */     if (paramXid == null)
    {
/* 281 */       throw new XAException(-5);
    }
    
/* 284 */     int i = paramXid.getFormatId();
/* 285 */     byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 286 */     byte[] arrayOfByte2 = paramXid.getBranchQualifier();
    
/* 288 */     int j = t2cDoXaPrepare(i, arrayOfByte1, arrayOfByte2, this.rmid, 0, 0);
    
/* 293 */     if ((j != 0) && (j != 3))
    {
/* 295 */       checkStatus(j);
    }
    
/* 298 */     return j;
  }
  
  public void forget(Xid paramXid)
    throws XAException
  {
/* 315 */     if (paramXid == null)
    {
/* 319 */       throw new XAException(-5);
    }
    
/* 322 */     int i = paramXid.getFormatId();
/* 323 */     byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 324 */     byte[] arrayOfByte2 = paramXid.getBranchQualifier();
    
/* 326 */     int j = t2cDoXaForget(i, arrayOfByte1, arrayOfByte2, this.rmid, 0, 0);
    
/* 331 */     checkStatus(j);
  }
  
  public void rollback(Xid paramXid)
    throws XAException
  {
/* 347 */     if (paramXid == null)
    {
/* 351 */       throw new XAException(-5);
    }
    
/* 354 */     int i = paramXid.getFormatId();
/* 355 */     byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/* 356 */     byte[] arrayOfByte2 = paramXid.getBranchQualifier();
    
/* 358 */     int j = t2cDoXaRollback(i, arrayOfByte1, arrayOfByte2, this.rmid, 0, 0);
    
/* 363 */     checkStatus(j);
  }
  
  private native int t2cDoXaStart(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  private native int t2cDoXaEnd(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  private native int t2cDoXaCommit(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  private native int t2cDoXaPrepare(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  private native int t2cDoXaForget(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  private native int t2cDoXaRollback(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3, int paramInt4);
  
  synchronized void setRmid(int paramInt)
  {
/* 398 */     this.rmid = paramInt;
  }
  
  synchronized int getRmid()
  {
/* 413 */     return this.rmid;
  }
  
  private static byte[] getSerializedBytes(Xid paramXid)
  {
    try
    {
/* 428 */       return Util.serializeObject(paramXid);
    }
    catch (IOException localIOException)
    {
/* 434 */       localIOException.printStackTrace();
    }
    
/* 437 */     return null;
  }
  
  private void checkStatus(int paramInt)
    throws XAException
  {
/* 447 */     if (paramInt != 0) {
/* 448 */       throw new XAException(paramInt);
    }
  }
  
/* 454 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXAHeteroResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */