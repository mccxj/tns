package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
import oracle.sql.INTERVALDS;
import oracle.sql.INTERVALYM;
public class OracleTypeINTERVAL
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 1394800182554224957L;
  static final int LDIINTYEARMONTH = 7;
  static final int LDIINTDAYSECOND = 10;
  static final int SIZE_INTERVAL_YM = 5;
  static final int SIZE_INTERVAL_DS = 11;
/*  65 */   byte typeId = 0;
/*  66 */   int scale = 0;
/*  67 */   int precision = 0;
  
  protected OracleTypeINTERVAL() {}
  
  public OracleTypeINTERVAL(OracleConnection paramOracleConnection) {}
  
  public int getTypeCode()
  {
/*  96 */     if (this.typeId == 7)
/*  97 */       return -103;
/*  98 */     if (this.typeId == 10) {
/*  99 */       return -104;
    }
/* 101 */     return 1111;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 109 */     this.typeId = paramTDSReader.readByte();
/* 110 */     this.precision = paramTDSReader.readByte();
/* 111 */     this.scale = paramTDSReader.readByte();
  }
  
  public int getScale()
    throws SQLException
  {
/* 118 */     return this.scale;
  }
  
  public int getPrecision()
    throws SQLException
  {
/* 125 */     return this.precision;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 134 */     this.typeId = paramObjectInputStream.readByte();
/* 135 */     this.precision = paramObjectInputStream.readByte();
/* 136 */     this.scale = paramObjectInputStream.readByte();
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 144 */     paramObjectOutputStream.writeByte(this.typeId);
/* 145 */     paramObjectOutputStream.writeByte(this.precision);
/* 146 */     paramObjectOutputStream.writeByte(this.scale);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 154 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 155 */       return null;
    }
/* 157 */     switch (paramInt)
    {
    case 1: 
/* 161 */       if (paramArrayOfByte.length == 5)
/* 162 */         return new INTERVALYM(paramArrayOfByte);
/* 163 */       if (paramArrayOfByte.length == 11) {
/* 164 */         return new INTERVALDS(paramArrayOfByte);
      }
      
      break;
    case 2: 
/* 169 */       if (paramArrayOfByte.length == 5)
/* 170 */         return INTERVALYM.toString(paramArrayOfByte);
/* 171 */       if (paramArrayOfByte.length == 11) {
/* 172 */         return INTERVALDS.toString(paramArrayOfByte);
      }
      
      break;
    case 3: 
/* 177 */       return paramArrayOfByte;
    
    default: 
/* 181 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 182 */       localSQLException.fillInStackTrace();
/* 183 */       throw localSQLException;
    }
    
    
/* 187 */     return null;
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 202 */     Object localObject = null;
    
/* 204 */     if (paramObject != null)
    {
/* 206 */       if (((paramObject instanceof INTERVALYM)) || ((paramObject instanceof INTERVALDS)))
      {
/* 208 */         localObject = (Datum)paramObject;
/* 209 */       } else if ((paramObject instanceof String))
      {
        try
        {
/* 216 */           localObject = new INTERVALDS((String)paramObject);
        }
        catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException)
        {
/* 220 */           localObject = new INTERVALYM((String)paramObject);
        }
      }
      else
      {
/* 225 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 226 */         localSQLException.fillInStackTrace();
/* 227 */         throw localSQLException;
      }
    }
    
/* 231 */     return (Datum)localObject;
  }
  
  protected Object unpickle81rec(UnpickleContext paramUnpickleContext, int paramInt1, int paramInt2, Map paramMap)
    throws SQLException
  {
/* 245 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 246 */     localSQLException.fillInStackTrace();
/* 247 */     throw localSQLException;
  }
  
/* 257 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeINTERVAL.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */