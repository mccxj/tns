package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValueLong;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIoxsscs
  extends T4CTTIfun
{
/*  58 */   private String userName = null;
/*  59 */   private KeywordValueLong[] inKV = null;
  private int inFlags;
/*  61 */   private byte[] userNameArr = null;
  
/*  63 */   private byte[] sessionId = null;
/*  64 */   private KeywordValueLong[] outKV = null;
/*  65 */   private int outFlags = -1;
  
  T4CTTIoxsscs(T4CConnection paramT4CConnection)
  {
/*  69 */     super(paramT4CConnection, (byte)3);
    
/*  71 */     setFunCode((short)155);
  }
  
  void doOXSSCS(String paramString, KeywordValueLong[] paramArrayOfKeywordValueLong, int paramInt)
    throws IOException, SQLException
  {
/*  82 */     this.userName = paramString;
/*  83 */     this.inKV = paramArrayOfKeywordValueLong;
/*  84 */     this.inFlags = paramInt;
/*  85 */     if ((this.userName != null) && (this.userName.length() > 0)) {
/*  86 */       this.userNameArr = this.meg.conv.StringToCharBytes(this.userName);
    } else {
/*  88 */       this.userNameArr = null;
    }
    
/*  91 */     this.sessionId = null;
/*  92 */     this.outKV = null;
/*  93 */     this.outFlags = -1;
    
/*  95 */     if (this.inKV != null)
/*  96 */       for (int i = 0; i < this.inKV.length; i++)
/*  97 */         ((KeywordValueLongI)this.inKV[i]).doCharConversion(this.meg.conv);
/*  98 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 104 */     this.meg.marshalPTR();
/* 105 */     this.meg.marshalPTR();
/* 106 */     if (this.userNameArr != null)
    {
/* 108 */       this.meg.marshalPTR();
/* 109 */       this.meg.marshalSB4(this.userNameArr.length);
    }
    else
    {
/* 113 */       this.meg.marshalNULLPTR();
/* 114 */       this.meg.marshalSB4(0);
    }
/* 116 */     int i = 0;
/* 117 */     if ((this.inKV != null) && (this.inKV.length > 0))
    {
/* 119 */       i = 1;
/* 120 */       this.meg.marshalPTR();
/* 121 */       this.meg.marshalSB4(this.inKV.length);
    }
    else
    {
/* 125 */       this.meg.marshalNULLPTR();
/* 126 */       this.meg.marshalSB4(0);
    }
/* 128 */     this.meg.marshalUB4(this.inFlags);
/* 129 */     this.meg.marshalPTR();
/* 130 */     this.meg.marshalPTR();
/* 131 */     this.meg.marshalPTR();
    
/* 133 */     if (this.userNameArr != null)
/* 134 */       this.meg.marshalCHR(this.userNameArr);
/* 135 */     if (i != 0) {
/* 136 */       for (int j = 0; j < this.inKV.length; j++) {
/* 137 */         ((KeywordValueLongI)this.inKV[j]).marshal(this.meg);
      }
    }
  }
  
  byte[] getSessionId()
  {
/* 145 */     return this.sessionId;
  }
  
  KeywordValueLong[] getOutKV()
  {
/* 151 */     return this.outKV;
  }
  
  int getOutFlags()
  {
/* 157 */     return this.outFlags;
  }
  
  void readRPA()
    throws SQLException, IOException
  {
/* 163 */     int i = (int)this.meg.unmarshalUB4();
/* 164 */     this.sessionId = this.meg.unmarshalNBytes(i);
/* 165 */     int j = (int)this.meg.unmarshalUB4();
/* 166 */     this.outKV = new KeywordValueLong[j];
/* 167 */     for (int k = 0; k < j; k++)
/* 168 */       this.outKV[k] = KeywordValueLongI.unmarshal(this.meg);
/* 169 */     this.outFlags = ((int)this.meg.unmarshalUB4());
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 184 */     return this.connection;
  }
  
/* 189 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoxsscs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */