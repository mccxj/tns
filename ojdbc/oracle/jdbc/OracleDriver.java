package oracle.jdbc;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Properties;
import oracle.jdbc.driver.BuildInfo;
public class OracleDriver
  extends oracle.jdbc.driver.OracleDriver
{
  public static final boolean isDMS()
  {
/* 309 */     return BuildInfo.isDMS();
  }
  
  public static final boolean isInServer()
  {
/* 321 */     return BuildInfo.isInServer();
  }
  
  /**
   * @deprecated
   */
  public static final boolean isJDK14()
  {
/* 335 */     return BuildInfo.isJDK14();
  }
  
  public static final boolean isDebug()
  {
/* 348 */     return BuildInfo.isDebug();
  }
  
  public static final boolean isPrivateDebug()
  {
/* 361 */     return BuildInfo.isPrivateDebug();
  }
  
  public static final String getJDBCVersion()
  {
/* 373 */     return BuildInfo.getJDBCVersion();
  }
  
  public static final String getDriverVersion()
  {
/* 385 */     return BuildInfo.getDriverVersion();
  }
  
  public static final String getBuildDate()
  {
/* 397 */     return BuildInfo.getBuildDate();
  }
  
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
/* 416 */     String str = "JDK6";
    
/* 418 */     System.out.println("Oracle " + getDriverVersion() + " " + getJDBCVersion() + (isDMS() ? " DMS" : "") + (isPrivateDebug() ? " private" : "") + (isDebug() ? " debug" : "") + (isInServer() ? " for JAVAVM" : "") + " compiled with " + str + " on " + getBuildDate());
    
/* 431 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(128);
/* 432 */     DEFAULT_CONNECTION_PROPERTIES.store(localByteArrayOutputStream, "Default Connection Properties Resource");
/* 433 */     System.out.println(localByteArrayOutputStream.toString("ISO-8859-1"));
  }
  
/* 439 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */