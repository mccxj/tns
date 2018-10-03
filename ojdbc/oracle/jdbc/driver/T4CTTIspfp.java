package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIspfp
  extends T4CTTIfun
{
  T4CTTIspfp(T4CConnection paramT4CConnection)
  {
/*  59 */     super(paramT4CConnection, (byte)3);
    
/*  61 */     setFunCode((short)138);
  }
  
  void doOSPFPPUT()
    throws IOException, SQLException
  {
/*  67 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/*  74 */     this.meg.marshalPTR();
/*  75 */     this.meg.marshalSWORD(100);
/*  76 */     this.meg.marshalPTR();
/*  77 */     this.meg.marshalPTR();
/*  78 */     this.meg.marshalSWORD(0);
/*  79 */     this.meg.marshalNULLPTR();
/*  80 */     this.meg.marshalSWORD(0);
/*  81 */     this.meg.marshalNULLPTR();
/*  82 */     this.meg.marshalNULLPTR();
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/*  89 */     int i = this.meg.unmarshalUB2();
/*  90 */     byte[] arrayOfByte = this.meg.unmarshalNBytes(i);
/*  91 */     if (i > 1)
    {
/*  93 */       String str = this.meg.conv.CharBytesToString(arrayOfByte, i, true);
/*  94 */       SQLWarning localSQLWarning1 = new SQLWarning(str);
/*  95 */       SQLWarning localSQLWarning2 = this.connection.getWarnings();
/*  96 */       if (localSQLWarning2 == null) {
/*  97 */         this.connection.setWarnings(localSQLWarning1);
      } else
/*  99 */         localSQLWarning2.setNextWarning(localSQLWarning1);
    }
/* 101 */     this.meg.unmarshalUB2();
/* 102 */     this.meg.unmarshalUB2();
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 117 */     return this.connection;
  }
  
/* 122 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIspfp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */