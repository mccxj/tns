package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
final class T4C7Oversion
  extends T4CTTIfun
{
/*  39 */   byte[] rdbmsVersion = { 78, 111, 116, 32, 100, 101, 116, 101, 114, 109, 105, 110, 101, 100, 32, 121, 101, 116 };
  
/*  46 */   private final boolean rdbmsVersionO2U = true;
  
/*  48 */   private final int bufLen = 256;
/*  49 */   private final boolean retVerLenO2U = true;
/*  50 */   int retVerLen = 0;
/*  51 */   private final boolean retVerNumO2U = true;
/*  52 */   long retVerNum = 0L;
  
  T4C7Oversion(T4CConnection paramT4CConnection)
  {
/*  61 */     super(paramT4CConnection, (byte)3);
    
/*  63 */     setFunCode((short)59);
  }
  
  void doOVERSION()
    throws SQLException, IOException
  {
/*  70 */     doRPC();
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/*  79 */     this.retVerLen = this.meg.unmarshalUB2();
/*  80 */     this.rdbmsVersion = this.meg.unmarshalCHR(this.retVerLen);
/*  81 */     this.retVerNum = this.meg.unmarshalUB4();
  }
  
  void processRPA()
    throws SQLException
  {
/*  89 */     if (this.rdbmsVersion == null)
    {
/*  91 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 438);
/*  92 */       localSQLException.fillInStackTrace();
/*  93 */       throw localSQLException;
    }
  }
  
  byte[] getVersion()
  {
/* 108 */     return this.rdbmsVersion;
  }
  
  short getVersionNumber()
  {
/* 121 */     int i = 0;
    
/* 123 */     i = (int)(i + (this.retVerNum >>> 24 & 0xFF) * 1000L);
/* 124 */     i = (int)(i + (this.retVerNum >>> 20 & 0xF) * 100L);
/* 125 */     i = (int)(i + (this.retVerNum >>> 12 & 0xF) * 10L);
/* 126 */     i = (int)(i + (this.retVerNum >>> 8 & 0xF));
    
/* 128 */     return (short)i;
  }
  
  long getVersionNumberasIs()
  {
/* 141 */     return this.retVerNum;
  }
  
  void marshal()
    throws IOException
  {
/* 155 */     this.meg.marshalO2U(true);
/* 156 */     this.meg.marshalSWORD(256);
/* 157 */     this.meg.marshalO2U(true);
/* 158 */     this.meg.marshalO2U(true);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 174 */     return this.connection;
  }
  
/* 179 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C7Oversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */