package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BFILE;
import oracle.sql.Datum;
public class OracleTypeBFILE
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -707073491109554687L;
/*  30 */   static int fixedDataSize = 530;
  
  transient OracleConnection connection;
  
  public OracleTypeBFILE() {}
  
  public OracleTypeBFILE(OracleConnection paramOracleConnection)
  {
/*  44 */     this.connection = paramOracleConnection;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  58 */     BFILE localBFILE = null;
    
/*  60 */     if (paramObject != null)
    {
/*  62 */       if ((paramObject instanceof BFILE)) {
/*  63 */         localBFILE = (BFILE)paramObject;
      }
      else {
/*  66 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  67 */         localSQLException.fillInStackTrace();
/*  68 */         throw localSQLException;
      }
    }
    
/*  72 */     return localBFILE;
  }
  
  public int getTypeCode()
  {
/*  82 */     return -13;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 106 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 107 */       return null;
    }
/* 109 */     if ((paramInt == 1) || (paramInt == 2)) {
/* 110 */       return this.connection.createBfile(paramArrayOfByte);
    }
    
/* 113 */     if (paramInt == 3) {
/* 114 */       return paramArrayOfByte;
    }
    
/* 117 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 118 */     localSQLException.fillInStackTrace();
/* 119 */     throw localSQLException;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 144 */     this.connection = paramOracleConnection;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 159 */     return this.connection;
  }
  
/* 194 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeBFILE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */