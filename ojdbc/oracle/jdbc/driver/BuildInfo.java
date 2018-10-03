package oracle.jdbc.driver;
public class BuildInfo
{
  public static final boolean isDMS()
  {
/*  52 */     return false;
  }
  
  public static final boolean isInServer()
  {
/*  69 */     return false;
  }
  
  /**
   * @deprecated
   */
  public static final boolean isJDK14()
  {
/*  84 */     return true;
  }
  
  public static final boolean isDebug()
  {
/*  98 */     return false;
  }
  
  public static final boolean isPrivateDebug()
  {
/* 113 */     return false;
  }
  
  public static final String getJDBCVersion()
  {
/* 129 */     return "JDBC 4.0";
  }
  
  public static final String getDriverVersion()
  {
/* 141 */     return "11.2.0.4.0";
  }
  
  public static final String getBuildDate()
  {
/* 153 */     return "Thu_Jul_03_18:17:32_PDT_2014";
  }
  
/* 158 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/BuildInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */