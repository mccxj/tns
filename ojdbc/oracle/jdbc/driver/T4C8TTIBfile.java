package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.Datum;
final class T4C8TTIBfile
  extends T4C8TTILob
{
  T4C8TTIBfile(T4CConnection paramT4CConnection)
  {
/* 108 */     super(paramT4CConnection);
  }
  
  Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt)
    throws SQLException, IOException
  {
/* 129 */     Object localObject = null;
    
/* 132 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), "cannot create a temporary BFILE", -1);
/* 133 */     localSQLException.fillInStackTrace();
/* 134 */     throw localSQLException;
  }
  
  boolean open(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 154 */     boolean bool = false;
    
/* 157 */     bool = _open(paramArrayOfByte, 11, 256);
    
/* 159 */     return bool;
  }
  
  boolean close(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 177 */     boolean bool = false;
    
/* 179 */     bool = _close(paramArrayOfByte, 512);
    
/* 181 */     return bool;
  }
  
  boolean isOpen(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 200 */     boolean bool = _isOpen(paramArrayOfByte, 1024);
    
/* 202 */     return bool;
  }
  
  boolean doesExist(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 221 */     boolean bool = false;
    
/* 224 */     initializeLobdef();
    
/* 227 */     this.sourceLobLocator = paramArrayOfByte;
/* 228 */     this.lobops = 2048L;
/* 229 */     this.nullO2U = true;
    
/* 231 */     doRPC();
    
/* 234 */     bool = this.lobnull;
/* 235 */     return bool;
  }
  
/* 240 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIBfile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */