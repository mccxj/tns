package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValueLong;
final class T4CTTIoxsspo
  extends T4CTTIfun
{
  private int functionId;
  private byte[] sessionId;
  private KeywordValueLong[] inKV;
  private int inFlags;
  
  T4CTTIoxsspo(T4CConnection paramT4CConnection)
  {
/*  62 */     super(paramT4CConnection, (byte)17);
    
/*  64 */     setFunCode((short)157);
  }
  
  void doOXSSPO(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2)
    throws IOException, SQLException
  {
/*  80 */     this.functionId = paramInt1;
/*  81 */     this.sessionId = paramArrayOfByte;
/*  82 */     this.inKV = paramArrayOfKeywordValueLong;
/*  83 */     this.inFlags = paramInt2;
/*  84 */     if (this.inKV != null)
/*  85 */       for (int i = 0; i < this.inKV.length; i++)
/*  86 */         ((KeywordValueLongI)this.inKV[i]).doCharConversion(this.meg.conv);
/*  87 */     doPigRPC();
  }
  
  void marshal()
    throws IOException
  {
/*  94 */     this.meg.marshalUB4(this.functionId);
/*  95 */     int i = 0;
/*  96 */     if ((this.sessionId != null) && (this.sessionId.length > 0))
    {
/*  98 */       i = 1;
/*  99 */       this.meg.marshalPTR();
/* 100 */       this.meg.marshalUB4(this.sessionId.length);
    }
    else
    {
/* 104 */       this.meg.marshalNULLPTR();
/* 105 */       this.meg.marshalUB4(0L);
    }
    
/* 108 */     int j = 0;
/* 109 */     if ((this.inKV != null) && (this.inKV.length > 0))
    {
/* 111 */       j = 1;
/* 112 */       this.meg.marshalPTR();
/* 113 */       this.meg.marshalUB4(this.inKV.length);
    }
    else
    {
/* 117 */       this.meg.marshalNULLPTR();
/* 118 */       this.meg.marshalUB4(0L);
    }
/* 120 */     this.meg.marshalUB4(this.inFlags);
    
/* 123 */     if (i != 0)
/* 124 */       this.meg.marshalB1Array(this.sessionId);
/* 125 */     if (j != 0) {
/* 126 */       for (int k = 0; k < this.inKV.length; k++) {
/* 127 */         ((KeywordValueLongI)this.inKV[k]).marshal(this.meg);
      }
    }
  }
  
/* 132 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoxsspo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */