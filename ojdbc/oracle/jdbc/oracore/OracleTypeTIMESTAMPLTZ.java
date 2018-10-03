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
import oracle.sql.TIMESTAMPLTZ;
public class OracleTypeTIMESTAMPLTZ
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 1615519855865602397L;
/*  60 */   int precision = 0;
  
  transient OracleConnection connection;
  
  protected OracleTypeTIMESTAMPLTZ() {}
  
  public OracleTypeTIMESTAMPLTZ(OracleConnection paramOracleConnection)
  {
/*  81 */     this.connection = paramOracleConnection;
  }
  
  public int getTypeCode()
  {
/*  92 */     return -102;
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
/* 146 */       return new TIMESTAMPLTZ(paramArrayOfByte);
    
    case 2: 
/* 149 */       return TIMESTAMPLTZ.toTimestamp(this.connection, paramArrayOfByte);
    
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
/* 176 */     TIMESTAMPLTZ localTIMESTAMPLTZ = null;
    
/* 178 */     if (paramObject != null)
    {
      try
      {
/* 182 */         if ((paramObject instanceof TIMESTAMPLTZ)) {
/* 183 */           localTIMESTAMPLTZ = (TIMESTAMPLTZ)paramObject;
/* 184 */         } else if ((paramObject instanceof byte[])) {
/* 185 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ((byte[])paramObject);
/* 186 */         } else if ((paramObject instanceof Timestamp)) {
/* 187 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ(paramOracleConnection, (Timestamp)paramObject);
/* 188 */         } else if ((paramObject instanceof DATE)) {
/* 189 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ(paramOracleConnection, (DATE)paramObject);
/* 190 */         } else if ((paramObject instanceof String)) {
/* 191 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ(paramOracleConnection, (String)paramObject);
/* 192 */         } else if ((paramObject instanceof Date)) {
/* 193 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ(paramOracleConnection, (Date)paramObject);
/* 194 */         } else if ((paramObject instanceof Time)) {
/* 195 */           localTIMESTAMPLTZ = new TIMESTAMPLTZ(paramOracleConnection, (Time)paramObject);
        }
        else {
/* 198 */           SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 199 */           localSQLException1.fillInStackTrace();
/* 200 */           throw localSQLException1;
        }
        
      }
      catch (Exception localException)
      {
/* 206 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 207 */         localSQLException2.fillInStackTrace();
/* 208 */         throw localSQLException2;
      }
    }
    
/* 213 */     return localTIMESTAMPLTZ;
  }
  
  protected Object unpickle81rec(UnpickleContext paramUnpickleContext, int paramInt1, int paramInt2, Map paramMap)
    throws SQLException
  {
/* 226 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 227 */     localSQLException.fillInStackTrace();
/* 228 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 244 */     return this.connection;
  }
  
/* 249 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeTIMESTAMPLTZ.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */