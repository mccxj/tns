package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CLOB;
import oracle.sql.Datum;
public class OracleTypeCLOB
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 1122821330765834411L;
/*  34 */   static int fixedDataSize = 86;
  
  transient OracleConnection connection;
  
  int form;
  
  protected OracleTypeCLOB() {}
  
  public OracleTypeCLOB(OracleConnection paramOracleConnection)
  {
/*  49 */     this.connection = paramOracleConnection;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  63 */     CLOB localCLOB = null;
    
/*  65 */     if (paramObject != null)
    {
/*  67 */       if ((paramObject instanceof CLOB)) {
/*  68 */         localCLOB = (CLOB)paramObject;
      }
      else {
/*  71 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  72 */         localSQLException.fillInStackTrace();
/*  73 */         throw localSQLException;
      }
    }
    
/*  77 */     return localCLOB;
  }
  
  public int getTypeCode()
  {
/*  87 */     return this.form == 2 ? 2011 : 2005;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 113 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 114 */       return null;
    }
/* 116 */     if ((paramInt == 1) || (paramInt == 2)) {
/* 117 */       return this.connection.createClobWithUnpickledBytes(paramArrayOfByte);
    }
    
/* 121 */     if (paramInt == 3) {
/* 122 */       return paramArrayOfByte;
    }
    
/* 125 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 126 */     localSQLException.fillInStackTrace();
/* 127 */     throw localSQLException;
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
/* 153 */     this.connection = paramOracleConnection;
  }
  
  public boolean isNCHAR()
    throws SQLException
  {
/* 165 */     return this.form == 2;
  }
  
  public void setForm(int paramInt)
  {
/* 172 */     this.form = paramInt;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 187 */     return this.connection;
  }
  
/* 225 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeCLOB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */