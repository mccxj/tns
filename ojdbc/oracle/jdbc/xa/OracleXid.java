package oracle.jdbc.xa;
import java.io.Serializable;
import javax.transaction.xa.XAException;
import javax.transaction.xa.Xid;
import oracle.jdbc.internal.OracleConnection;
public class OracleXid
  implements Xid, Serializable
{
  private int formatId;
/*  39 */   private byte[] gtrid = null;
/*  40 */   private byte[] bqual = null;
/*  41 */   private byte[] txctx = null;
  
  public static final int MAXGTRIDSIZE = 64;
  
  public static final int MAXBQUALSIZE = 64;
  
  private int state;
  
  public OracleXid(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws XAException
  {
/*  64 */     this(paramInt, paramArrayOfByte1, paramArrayOfByte2, null);
  }
  
  public OracleXid(int paramInt, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws XAException
  {
/*  88 */     this.formatId = paramInt;
    
/*  90 */     if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 64)) {
/*  91 */       throw new XAException(-4);
    }
/*  93 */     this.gtrid = paramArrayOfByte1;
    
/*  95 */     if ((paramArrayOfByte2 != null) && (paramArrayOfByte2.length > 64)) {
/*  96 */       throw new XAException(-4);
    }
/*  98 */     this.bqual = paramArrayOfByte2;
/*  99 */     this.txctx = paramArrayOfByte3;
/* 100 */     this.state = 0;
  }
  
  public void setState(int paramInt)
  {
/* 114 */     this.state = paramInt;
  }
  
  public int getState()
  {
/* 127 */     return this.state;
  }
  
  public int getFormatId()
  {
/* 141 */     return this.formatId;
  }
  
  public byte[] getGlobalTransactionId()
  {
/* 156 */     return this.gtrid;
  }
  
  public byte[] getBranchQualifier()
  {
/* 170 */     return this.bqual;
  }
  
  public byte[] getTxContext()
  {
/* 182 */     return this.txctx;
  }
  
  public void setTxContext(byte[] paramArrayOfByte)
  {
/* 212 */     this.txctx = paramArrayOfByte;
  }
  
  public static final boolean isLocalTransaction(Xid paramXid)
  {
/* 224 */     byte[] arrayOfByte = paramXid.getGlobalTransactionId();
    
/* 226 */     if (arrayOfByte == null) {
/* 227 */       return true;
    }
/* 229 */     for (int i = 0; i < arrayOfByte.length; i++)
    {
/* 231 */       if (arrayOfByte[i] != 0) {
/* 232 */         return false;
      }
    }
/* 235 */     return true;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 326 */     return null;
  }
  
/* 331 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleXid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */