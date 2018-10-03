package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;
import oracle.sql.BLOB;
import oracle.sql.Datum;
final class T4C8TTIBlob
  extends T4C8TTILob
{
  T4C8TTIBlob(T4CConnection paramT4CConnection)
  {
/* 116 */     super(paramT4CConnection);
  }
  
  Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt)
    throws SQLException, IOException
  {
/* 140 */     if (paramInt == 12)
    {
/* 142 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 158);
/* 143 */       ((SQLException)localObject).fillInStackTrace();
/* 144 */       throw ((Throwable)localObject);
    }
    
/* 149 */     Object localObject = null;
    
/* 152 */     initializeLobdef();
    
/* 155 */     this.lobops = 272L;
/* 156 */     this.sourceLobLocator = new byte[40];
/* 157 */     this.sourceLobLocator[1] = 84;
    
/* 160 */     this.characterSet = 1;
    
/* 164 */     this.destinationOffset = 113L;
    
/* 169 */     this.destinationLength = paramInt;
    
/* 171 */     this.lobamt = paramInt;
/* 172 */     this.sendLobamt = true;
    
/* 175 */     this.nullO2U = true;
    
/* 177 */     if (this.connection.versionNumber >= 9000)
    {
/* 179 */       this.lobscn = new int[1];
/* 180 */       this.lobscn[0] = (paramBoolean ? 1 : 0);
/* 181 */       this.lobscnl = 1;
    }
    
/* 184 */     doRPC();
    
/* 188 */     if (this.sourceLobLocator != null)
    {
/* 190 */       localObject = new BLOB((OracleConnection)paramConnection, this.sourceLobLocator);
    }
    
/* 194 */     return (Datum)localObject;
  }
  
  boolean open(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 212 */     boolean bool = false;
    
/* 216 */     int i = 2;
    
/* 218 */     if (paramInt == 0) {
/* 219 */       i = 1;
    }
/* 221 */     bool = _open(paramArrayOfByte, i, 32768);
    
/* 223 */     return bool;
  }
  
  boolean close(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 241 */     boolean bool = false;
    
/* 243 */     bool = _close(paramArrayOfByte, 65536);
    
/* 245 */     return bool;
  }
  
  boolean isOpen(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 263 */     boolean bool = false;
    
/* 265 */     bool = _isOpen(paramArrayOfByte, 69632);
    
/* 267 */     return bool;
  }
  
/* 271 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIBlob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */