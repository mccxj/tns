package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.aq.AQAgent;
class AQAgentI
  implements AQAgent
{
  private String attrAgentAddress;
  private String attrAgentName;
  private int attrAgentProtocol;
  
  AQAgentI()
    throws SQLException
  {
/*  46 */     this.attrAgentName = null;
/*  47 */     this.attrAgentAddress = null;
/*  48 */     this.attrAgentProtocol = 0;
  }
  
  public void setAddress(String paramString)
    throws SQLException
  {
/*  56 */     this.attrAgentAddress = paramString;
  }
  
  public String getAddress()
  {
/*  63 */     return this.attrAgentAddress;
  }
  
  public void setName(String paramString)
    throws SQLException
  {
/*  71 */     this.attrAgentName = paramString;
  }
  
  public String getName()
  {
/*  78 */     return this.attrAgentName;
  }
  
  public void setProtocol(int paramInt)
    throws SQLException
  {
/*  85 */     this.attrAgentProtocol = paramInt;
  }
  
  public int getProtocol()
  {
/*  92 */     return this.attrAgentProtocol;
  }
  
  public String toString()
  {
/*  99 */     StringBuffer localStringBuffer = new StringBuffer();
/* 100 */     localStringBuffer.append("Name=\"");
/* 101 */     localStringBuffer.append(getName());
/* 102 */     localStringBuffer.append("\" ");
/* 103 */     localStringBuffer.append("Address=\"");
/* 104 */     localStringBuffer.append(getAddress());
/* 105 */     localStringBuffer.append("\" ");
/* 106 */     localStringBuffer.append("Protocol=\"");
/* 107 */     localStringBuffer.append(getProtocol());
/* 108 */     localStringBuffer.append("\"");
/* 109 */     return localStringBuffer.toString();
  }
  
/* 114 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/AQAgentI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */