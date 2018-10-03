package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValueLong;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIoxssro
  extends T4CTTIfun
{
  private int functionId;
  
  T4CTTIoxssro(T4CConnection paramT4CConnection)
  {
/*  63 */     super(paramT4CConnection, (byte)3);
    
/*  65 */     setFunCode((short)156);
  }
  
/*  69 */   private byte[] sessionId = null;
/*  70 */   private KeywordValueLong[] inKV = null;
  
  private int inFlags;
/*  73 */   private KeywordValueLong[] outKV = null;
/*  74 */   private int outFlags = -1;
  
  void doOXSSRO(int paramInt1, byte[] paramArrayOfByte, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt2)
    throws IOException, SQLException
  {
/*  83 */     this.functionId = paramInt1;
/*  84 */     this.sessionId = paramArrayOfByte;
/*  85 */     this.inKV = paramArrayOfKeywordValueLong;
/*  86 */     this.inFlags = paramInt2;
/*  87 */     this.outKV = null;
/*  88 */     this.outFlags = -1;
    
/*  90 */     if (this.inKV != null)
/*  91 */       for (int i = 0; i < this.inKV.length; i++)
/*  92 */         ((KeywordValueLongI)this.inKV[i]).doCharConversion(this.meg.conv);
/*  93 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/*  99 */     this.meg.marshalUB4(this.functionId);
/* 100 */     int i = 0;
/* 101 */     if ((this.sessionId != null) && (this.sessionId.length > 0))
    {
/* 103 */       i = 1;
/* 104 */       this.meg.marshalPTR();
/* 105 */       this.meg.marshalUB4(this.sessionId.length);
    }
    else
    {
/* 109 */       this.meg.marshalNULLPTR();
/* 110 */       this.meg.marshalUB4(0L);
    }
/* 112 */     int j = 0;
/* 113 */     if ((this.inKV != null) && (this.inKV.length > 0))
    {
/* 115 */       j = 1;
/* 116 */       this.meg.marshalPTR();
/* 117 */       this.meg.marshalUB4(this.inKV.length);
    }
    else
    {
/* 121 */       this.meg.marshalNULLPTR();
/* 122 */       this.meg.marshalUB4(0L);
    }
/* 124 */     this.meg.marshalUB4(this.inFlags);
/* 125 */     this.meg.marshalPTR();
/* 126 */     this.meg.marshalPTR();
/* 127 */     this.meg.marshalPTR();
    
/* 129 */     if (i != 0)
/* 130 */       this.meg.marshalB1Array(this.sessionId);
/* 131 */     if (j != 0) {
/* 132 */       for (int k = 0; k < this.inKV.length; k++) {
/* 133 */         ((KeywordValueLongI)this.inKV[k]).marshal(this.meg);
      }
    }
  }
  
  KeywordValueLong[] getOutKV()
  {
/* 141 */     return this.outKV;
  }
  
  int getOutFlags()
  {
/* 147 */     return this.outFlags;
  }
  
  void readRPA()
    throws SQLException, IOException
  {
/* 153 */     int i = (int)this.meg.unmarshalUB4();
/* 154 */     this.outKV = new KeywordValueLong[i];
/* 155 */     for (int j = 0; j < i; j++)
/* 156 */       this.outKV[j] = KeywordValueLongI.unmarshal(this.meg);
/* 157 */     this.outFlags = ((int)this.meg.unmarshalUB4());
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 172 */     return this.connection;
  }
  
/* 177 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoxssro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */