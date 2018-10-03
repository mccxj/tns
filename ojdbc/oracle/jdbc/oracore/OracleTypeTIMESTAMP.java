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
import oracle.sql.TIMESTAMP;
public class OracleTypeTIMESTAMP
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 3948043338303602796L;
/*  57 */   int precision = 0;
  
  protected OracleTypeTIMESTAMP() {}
  
  public OracleTypeTIMESTAMP(OracleConnection paramOracleConnection) {}
  
  public int getTypeCode()
  {
/*  86 */     return 93;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/*  94 */     this.precision = paramTDSReader.readByte();
  }
  
  public int getScale()
    throws SQLException
  {
/* 101 */     return 0;
  }
  
  public int getPrecision()
    throws SQLException
  {
/* 108 */     return this.precision;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 117 */     this.precision = paramObjectInputStream.readByte();
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 125 */     paramObjectOutputStream.writeByte(this.precision);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 133 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 134 */       return null;
    }
/* 136 */     switch (paramInt)
    {
    case 1: 
/* 140 */       return new TIMESTAMP(paramArrayOfByte);
    
    case 2: 
/* 143 */       return TIMESTAMP.toTimestamp(paramArrayOfByte);
    
    case 3: 
/* 146 */       return paramArrayOfByte;
    }
    
    
/* 150 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 151 */     localSQLException.fillInStackTrace();
/* 152 */     throw localSQLException;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 170 */     TIMESTAMP localTIMESTAMP = null;
    
/* 172 */     if (paramObject != null)
    {
      try
      {
/* 176 */         if ((paramObject instanceof TIMESTAMP)) {
/* 177 */           localTIMESTAMP = (TIMESTAMP)paramObject;
/* 178 */         } else if ((paramObject instanceof byte[])) {
/* 179 */           localTIMESTAMP = new TIMESTAMP((byte[])paramObject);
/* 180 */         } else if ((paramObject instanceof Timestamp)) {
/* 181 */           localTIMESTAMP = new TIMESTAMP((Timestamp)paramObject);
/* 182 */         } else if ((paramObject instanceof DATE)) {
/* 183 */           localTIMESTAMP = new TIMESTAMP((DATE)paramObject);
/* 184 */         } else if ((paramObject instanceof String)) {
/* 185 */           localTIMESTAMP = new TIMESTAMP((String)paramObject);
/* 186 */         } else if ((paramObject instanceof Date)) {
/* 187 */           localTIMESTAMP = new TIMESTAMP((Date)paramObject);
/* 188 */         } else if ((paramObject instanceof Time)) {
/* 189 */           localTIMESTAMP = new TIMESTAMP((Time)paramObject);
        }
        else {
/* 192 */           SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 193 */           localSQLException1.fillInStackTrace();
/* 194 */           throw localSQLException1;
        }
        
      }
      catch (Exception localException)
      {
/* 200 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 201 */         localSQLException2.fillInStackTrace();
/* 202 */         throw localSQLException2;
      }
    }
    
/* 207 */     return localTIMESTAMP;
  }
  
  protected Object unpickle81rec(UnpickleContext paramUnpickleContext, int paramInt1, int paramInt2, Map paramMap)
    throws SQLException
  {
/* 221 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 222 */     localSQLException.fillInStackTrace();
/* 223 */     throw localSQLException;
  }
  
/* 232 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeTIMESTAMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */