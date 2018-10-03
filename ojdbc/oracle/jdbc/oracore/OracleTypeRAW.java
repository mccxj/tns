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
import oracle.sql.RAW;
public class OracleTypeRAW
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -6083664758336974576L;
  int length;
  
  public OracleTypeRAW() {}
  
  public OracleTypeRAW(int paramInt)
  {
/*  42 */     super(paramInt);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  57 */     RAW localRAW = null;
    
/*  59 */     if (paramObject != null)
    {
      try
      {
/*  63 */         if ((paramObject instanceof RAW)) {
/*  64 */           localRAW = (RAW)paramObject;
        } else {
/*  66 */           localRAW = new RAW(paramObject);
        }
      }
      catch (SQLException localSQLException1)
      {
/*  71 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  72 */         localSQLException2.fillInStackTrace();
/*  73 */         throw localSQLException2;
      }
    }
    
/*  78 */     return localRAW;
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  92 */     Datum[] arrayOfDatum = null;
    
/*  94 */     if (paramObject != null) {
      Object localObject;
/*  96 */       if ((paramObject instanceof char[][]))
      {
/*  98 */         localObject = (char[][])paramObject;
/*  99 */         int i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 102 */         arrayOfDatum = new Datum[i];
        
/* 104 */         for (int j = 0; j < i; j++) {
/* 105 */           arrayOfDatum[j] = toDatum(new String(localObject[((int)paramLong + j - 1)]), paramOracleConnection);
        }
      } else {
/* 108 */         if ((paramObject instanceof Object[]))
        {
/* 110 */           return super.toDatumArray(paramObject, paramOracleConnection, paramLong, paramInt);
        }
        
/* 114 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 115 */         ((SQLException)localObject).fillInStackTrace();
/* 116 */         throw ((Throwable)localObject);
      }
    }
    
/* 120 */     return arrayOfDatum;
  }
  
  public int getTypeCode()
  {
/* 130 */     return -2;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 143 */     super.parseTDSrec(paramTDSReader);
    
/* 147 */     this.length = paramTDSReader.readUB2();
  }
  
  protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
    throws SQLException
  {
/* 170 */     if (paramDatum.getLength() > this.length)
    {
/* 172 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 72, this);
/* 173 */       localSQLException.fillInStackTrace();
/* 174 */       throw localSQLException;
    }
    
/* 177 */     int i = paramPickleContext.writeLength((int)paramDatum.getLength());
    
/* 179 */     i += paramPickleContext.writeData(paramDatum.shareBytes());
    
/* 181 */     return i;
  }
  
  public int getLength()
  {
/* 192 */     return this.length;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 201 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 202 */       return null;
    }
/* 204 */     switch (paramInt)
    {
    case 1: 
/* 208 */       return new RAW(paramArrayOfByte);
    
    case 2: 
    case 3: 
/* 213 */       return paramArrayOfByte;
    }
    
    
/* 217 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 218 */     localSQLException.fillInStackTrace();
/* 219 */     throw localSQLException;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 233 */     paramObjectOutputStream.writeInt(this.length);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 241 */     this.length = paramObjectInputStream.readInt();
  }
  
/* 274 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeRAW.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */