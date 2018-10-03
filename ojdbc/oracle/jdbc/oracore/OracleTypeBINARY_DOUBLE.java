package oracle.jdbc.oracore;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BINARY_DOUBLE;
import oracle.sql.Datum;
public class OracleTypeBINARY_DOUBLE
  extends OracleType
  implements Serializable
{
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  48 */     BINARY_DOUBLE localBINARY_DOUBLE = null;
    
/*  50 */     if (paramObject != null)
    {
/*  52 */       if ((paramObject instanceof BINARY_DOUBLE)) {
/*  53 */         localBINARY_DOUBLE = (BINARY_DOUBLE)paramObject;
/*  54 */       } else if ((paramObject instanceof Double)) {
/*  55 */         localBINARY_DOUBLE = new BINARY_DOUBLE((Double)paramObject);
/*  56 */       } else if ((paramObject instanceof byte[])) {
/*  57 */         localBINARY_DOUBLE = new BINARY_DOUBLE((byte[])paramObject);
      }
      else {
/*  60 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  61 */         localSQLException.fillInStackTrace();
/*  62 */         throw localSQLException;
      }
    }
    
/*  66 */     return localBINARY_DOUBLE;
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  82 */     Datum[] arrayOfDatum = null;
    
/*  84 */     if (paramObject != null)
    {
/*  86 */       if ((paramObject instanceof Object[]))
      {
/*  88 */         Object[] arrayOfObject = (Object[])paramObject;
        
/*  90 */         int i = (int)(paramInt == -1 ? arrayOfObject.length : Math.min(arrayOfObject.length - paramLong + 1L, paramInt));
        
/*  93 */         arrayOfDatum = new Datum[i];
        
/*  95 */         for (int j = 0; j < i; j++)
        {
/*  97 */           Object localObject = arrayOfObject[((int)paramLong + j - 1)];
          
/*  99 */           if (localObject != null)
          {
/* 101 */             if ((localObject instanceof Double)) {
/* 102 */               arrayOfDatum[j] = new BINARY_DOUBLE(((Double)localObject).doubleValue());
            }
/* 104 */             else if ((localObject instanceof BINARY_DOUBLE)) {
/* 105 */               arrayOfDatum[j] = ((BINARY_DOUBLE)localObject);
            }
            else {
/* 108 */               SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 109 */               localSQLException.fillInStackTrace();
/* 110 */               throw localSQLException;
            }
            
          }
          else {
/* 116 */             arrayOfDatum[j] = null;
          }
        }
      }
    }
    
/* 122 */     return arrayOfDatum;
  }
  
  public int getTypeCode()
  {
/* 132 */     return 101;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 157 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 158 */       return null;
    }
/* 160 */     if (paramInt == 1)
/* 161 */       return new BINARY_DOUBLE(paramArrayOfByte);
/* 162 */     if (paramInt == 2)
/* 163 */       return new BINARY_DOUBLE(paramArrayOfByte).toJdbc();
/* 164 */     if (paramInt == 3) {
/* 165 */       return paramArrayOfByte;
    }
    
/* 168 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 169 */     localSQLException.fillInStackTrace();
/* 170 */     throw localSQLException;
  }
  
/* 175 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeBINARY_DOUBLE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */