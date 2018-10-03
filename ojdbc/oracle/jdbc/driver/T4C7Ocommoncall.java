package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4C7Ocommoncall
  extends T4CTTIfun
{
  T4C7Ocommoncall(T4CConnection paramT4CConnection)
  {
/*  52 */     super(paramT4CConnection, (byte)3);
  }
  
  void doOLOGOFF()
    throws SQLException, IOException
  {
/*  60 */     setFunCode((short)9);
/*  61 */     doRPC();
  }
  
  void doOROLLBACK()
    throws SQLException, IOException
  {
/*  67 */     setFunCode((short)15);
/*  68 */     doRPC();
  }
  
  void doOCOMMIT()
    throws SQLException, IOException
  {
/*  74 */     setFunCode((short)14);
/*  75 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {}
  
  void processError()
    throws SQLException
  {
/*  92 */     if (this.oer.retCode != 2089) {
/*  93 */       this.oer.processError();
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 108 */     return null;
  }
  
/* 113 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C7Ocommoncall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */