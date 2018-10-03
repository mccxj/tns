package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValueLong;
import oracle.jdbc.internal.XSEvent;
class NTFXSEvent
  extends XSEvent
{
  private final byte[] sid_kpuzxsss;
  private final KeywordValueLongI[] sess_kpuzxsss;
  private final int flg_kpuzxsss;
  
  NTFXSEvent(T4CConnection paramT4CConnection)
    throws SQLException, IOException
  {
/*  49 */     super(paramT4CConnection);
    
/*  63 */     T4CMAREngine localT4CMAREngine = paramT4CConnection.getMarshalEngine();
    
/*  65 */     this.sid_kpuzxsss = localT4CMAREngine.unmarshalDALC();
/*  66 */     int i = (int)localT4CMAREngine.unmarshalUB4();
/*  67 */     int j = (byte)localT4CMAREngine.unmarshalUB1();
/*  68 */     this.sess_kpuzxsss = new KeywordValueLongI[i];
/*  69 */     for (int k = 0; k < i; k++)
    {
/*  71 */       this.sess_kpuzxsss[k] = KeywordValueLongI.unmarshal(localT4CMAREngine);
    }
/*  73 */     this.flg_kpuzxsss = ((int)localT4CMAREngine.unmarshalUB4());
  }
  
  public byte[] getSessionId()
  {
/*  79 */     return this.sid_kpuzxsss;
  }
  
  public KeywordValueLong[] getDetails()
  {
/*  86 */     return (KeywordValueLong[])this.sess_kpuzxsss;
  }
  
  public int getFlags()
  {
/*  93 */     return this.flg_kpuzxsss;
  }
  
  public String toString()
  {
/* 100 */     StringBuffer localStringBuffer = new StringBuffer();
/* 101 */     localStringBuffer.append("sid_kpuzxsss  : " + NTFAQEvent.byteBufferToHexString(this.sid_kpuzxsss, 50) + "\n");
/* 102 */     localStringBuffer.append("sess_kpuzxsss : \n");
/* 103 */     localStringBuffer.append("  size : " + this.sess_kpuzxsss.length + "\n");
/* 104 */     for (int i = 0; i < this.sess_kpuzxsss.length; i++)
    {
/* 106 */       localStringBuffer.append("  sess_kpuzxsss #" + i + " : \n");
/* 107 */       if (this.sess_kpuzxsss[i] == null) {
/* 108 */         localStringBuffer.append("null\n");
      } else
/* 110 */         localStringBuffer.append(this.sess_kpuzxsss[i].toString());
    }
/* 112 */     localStringBuffer.append("flg_kpuzxsss  : " + this.flg_kpuzxsss + "\n");
/* 113 */     return localStringBuffer.toString();
  }
  
/* 118 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFXSEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */