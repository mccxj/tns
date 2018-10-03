package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BLOB;
import oracle.sql.Datum;
public class OracleTypeBLOB
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -2311211431562030662L;
/*  33 */   static int fixedDataSize = 86;
  
  transient OracleConnection connection;
  
  protected OracleTypeBLOB() {}
  
  public OracleTypeBLOB(OracleConnection paramOracleConnection)
  {
/*  48 */     this.connection = paramOracleConnection;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  62 */     BLOB localBLOB = null;
    
/*  64 */     if (paramObject != null)
    {
/*  66 */       if ((paramObject instanceof BLOB)) {
/*  67 */         localBLOB = (BLOB)paramObject;
      }
      else {
/*  70 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  71 */         localSQLException.fillInStackTrace();
/*  72 */         throw localSQLException;
      }
    }
    
/*  76 */     return localBLOB;
  }
  
  public int getTypeCode()
  {
/*  86 */     return 2004;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 111 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 112 */       return null;
    }
/* 114 */     switch (paramInt)
    {
    case 1: 
    case 2: 
/* 122 */       return this.connection.createBlobWithUnpickledBytes(paramArrayOfByte);
    
    case 3: 
/* 126 */       return paramArrayOfByte;
    }
    
    
/* 130 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 131 */     localSQLException.fillInStackTrace();
/* 132 */     throw localSQLException;
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
/* 158 */     this.connection = paramOracleConnection;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 173 */     return this.connection;
  }
  
/* 210 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeBLOB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */