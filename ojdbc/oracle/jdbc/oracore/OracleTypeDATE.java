package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.TIMESTAMP;
public class OracleTypeDATE
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -5858803341118747965L;
  
  public OracleTypeDATE() {}
  
  public OracleTypeDATE(int paramInt)
  {
/*  41 */     super(paramInt);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  56 */     DATE localDATE = null;
    
/*  58 */     if (paramObject != null)
    {
      try
      {
/*  62 */         if ((paramObject instanceof DATE)) {
/*  63 */           localDATE = (DATE)paramObject;
/*  64 */         } else if ((paramObject instanceof TIMESTAMP)) {
/*  65 */           localDATE = new DATE(((TIMESTAMP)paramObject).timestampValue());
        } else {
/*  67 */           localDATE = new DATE(paramObject);
        }
      }
      catch (SQLException localSQLException1)
      {
/*  72 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  73 */         localSQLException2.fillInStackTrace();
/*  74 */         throw localSQLException2;
      }
    }
    
/*  78 */     return localDATE;
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
/* 119 */     return arrayOfDatum;
  }
  
  public int getTypeCode()
  {
/* 129 */     return 91;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 152 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 153 */       return null;
    }
/* 155 */     if (paramInt == 1)
/* 156 */       return new DATE(paramArrayOfByte);
/* 157 */     if (paramInt == 2)
/* 158 */       return DATE.toTimestamp(paramArrayOfByte);
/* 159 */     if (paramInt == 3) {
/* 160 */       return paramArrayOfByte;
    }
    
/* 163 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 164 */     localSQLException.fillInStackTrace();
/* 165 */     throw localSQLException;
  }
  
/* 215 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeDATE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */