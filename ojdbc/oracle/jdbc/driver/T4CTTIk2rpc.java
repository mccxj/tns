package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4CTTIk2rpc
  extends T4CTTIfun
{
  static final int K2RPClogon = 1;
  static final int K2RPCbegin = 2;
  static final int K2RPCend = 3;
  static final int K2RPCrecover = 4;
  static final int K2RPCsession = 5;
  private int k2rpctyp;
  private int command;
  
  T4CTTIk2rpc(T4CConnection paramT4CConnection)
  {
/*  62 */     super(paramT4CConnection, (byte)3);
    
/*  64 */     setFunCode((short)67);
  }
  
  void doOK2RPC(int paramInt1, int paramInt2)
    throws IOException, SQLException
  {
/*  71 */     this.k2rpctyp = paramInt1;
/*  72 */     this.command = paramInt2;
/*  73 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/*  81 */     this.meg.marshalUB4(0L);
/*  82 */     this.meg.marshalUB4(this.k2rpctyp);
/*  83 */     this.meg.marshalPTR();
/*  84 */     this.meg.marshalUB4(3L);
/*  85 */     this.meg.marshalNULLPTR();
/*  86 */     this.meg.marshalUB4(0L);
/*  87 */     this.meg.marshalNULLPTR();
/*  88 */     this.meg.marshalUB4(0L);
/*  89 */     this.meg.marshalPTR();
/*  90 */     this.meg.marshalUB4(3L);
/*  91 */     this.meg.marshalPTR();
/*  92 */     this.meg.marshalNULLPTR();
/*  93 */     this.meg.marshalUB4(0L);
/*  94 */     this.meg.marshalNULLPTR();
/*  95 */     this.meg.marshalNULLPTR();
/*  96 */     this.meg.marshalUB4(0L);
/*  97 */     this.meg.marshalNULLPTR();
    
/* 101 */     this.meg.marshalUB4(this.command);
/* 102 */     this.meg.marshalUB4(0L);
/* 103 */     this.meg.marshalUB4(0L);
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 110 */     int i = this.meg.unmarshalUB2();
    
/* 112 */     for (int j = 0; j < i; j++)
    {
/* 114 */       this.meg.unmarshalUB4();
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 130 */     return null;
  }
  
/* 136 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIk2rpc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */