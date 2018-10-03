package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.TIMESTAMPTZ;
public class OracleTypeTIMESTAMPTZ
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 5643686037837085645L;
/*  60 */   int precision = 0;
  
  transient OracleConnection connection;
  
  protected OracleTypeTIMESTAMPTZ() {}
  
  public OracleTypeTIMESTAMPTZ(OracleConnection paramOracleConnection)
  {
/*  81 */     this.connection = paramOracleConnection;
  }
  
  public int getTypeCode()
  {
/*  92 */     return -101;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 100 */     this.precision = paramTDSReader.readByte();
  }
  
  public int getScale()
    throws SQLException
  {
/* 107 */     return 0;
  }
  
  public int getPrecision()
    throws SQLException
  {
/* 114 */     return this.precision;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 123 */     this.precision = paramObjectInputStream.readByte();
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 131 */     paramObjectOutputStream.writeByte(this.precision);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 139 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 140 */       return null;
    }
/* 142 */     switch (paramInt)
    {
    case 1: 
/* 146 */       return new TIMESTAMPTZ(paramArrayOfByte);
    
    case 2: 
/* 149 */       return TIMESTAMPTZ.toTimestamp(this.connection, paramArrayOfByte);
    
    case 3: 
/* 152 */       return paramArrayOfByte;
    }
    
    
/* 156 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 157 */     localSQLException.fillInStackTrace();
/* 158 */     throw localSQLException;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 177 */     TIMESTAMPTZ localTIMESTAMPTZ = null;
    
/* 179 */     if (paramObject != null)
    {
      try
      {
/* 183 */         if ((paramObject instanceof TIMESTAMPTZ)) {
/* 184 */           localTIMESTAMPTZ = (TIMESTAMPTZ)paramObject;
/* 185 */         } else if ((paramObject instanceof byte[])) {
/* 186 */           localTIMESTAMPTZ = new TIMESTAMPTZ((byte[])paramObject);
/* 187 */         } else if ((paramObject instanceof Timestamp)) {
/* 188 */           localTIMESTAMPTZ = new TIMESTAMPTZ(paramOracleConnection, (Timestamp)paramObject);
/* 189 */         } else if ((paramObject instanceof DATE)) {
/* 190 */           localTIMESTAMPTZ = new TIMESTAMPTZ(paramOracleConnection, (DATE)paramObject);
/* 191 */         } else if ((paramObject instanceof String)) {
/* 192 */           localTIMESTAMPTZ = new TIMESTAMPTZ(paramOracleConnection, (String)paramObject);
/* 193 */         } else if ((paramObject instanceof Date)) {
/* 194 */           localTIMESTAMPTZ = new TIMESTAMPTZ(paramOracleConnection, (Date)paramObject);
/* 195 */         } else if ((paramObject instanceof Time)) {
/* 196 */           localTIMESTAMPTZ = new TIMESTAMPTZ(paramOracleConnection, (Time)paramObject);
        }
        else {
/* 199 */           SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 200 */           localSQLException1.fillInStackTrace();
/* 201 */           throw localSQLException1;
        }
        
      }
      catch (Exception localException)
      {
/* 207 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 208 */         localSQLException2.fillInStackTrace();
/* 209 */         throw localSQLException2;
      }
    }
    
/* 214 */     return localTIMESTAMPTZ;
  }
  
  protected Object unpickle81rec(UnpickleContext paramUnpickleContext, int paramInt1, int paramInt2, Map paramMap)
    throws SQLException
  {
/* 228 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 229 */     localSQLException.fillInStackTrace();
/* 230 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 246 */     return this.connection;
  }
  
/* 254 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeTIMESTAMPTZ.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */