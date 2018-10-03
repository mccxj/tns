package oracle.jdbc.connector;
import javax.resource.spi.ConnectionRequestInfo;
public class OracleConnectionRequestInfo
  implements ConnectionRequestInfo
{
/*  23 */   private String user = null;
/*  24 */   private String password = null;
  
  public OracleConnectionRequestInfo(String paramString1, String paramString2)
  {
/*  33 */     this.user = paramString1;
/*  34 */     this.password = paramString2;
  }
  
  public String getUser()
  {
/*  43 */     return this.user;
  }
  
  public void setUser(String paramString)
  {
/*  51 */     this.user = paramString;
  }
  
  public String getPassword()
  {
/*  60 */     return this.password;
  }
  
  public void setPassword(String paramString)
  {
/*  68 */     this.password = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
/*  98 */     if (!(paramObject instanceof OracleConnectionRequestInfo)) {
/*  99 */       return false;
    }
/* 101 */     OracleConnectionRequestInfo localOracleConnectionRequestInfo = (OracleConnectionRequestInfo)paramObject;
    
/* 104 */     return (this.user.equalsIgnoreCase(localOracleConnectionRequestInfo.getUser())) && (this.password.equals(localOracleConnectionRequestInfo.getPassword()));
  }
  
/* 109 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleConnectionRequestInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */