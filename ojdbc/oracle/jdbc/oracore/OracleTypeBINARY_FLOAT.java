package oracle.jdbc.oracore;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.BINARY_FLOAT;
import oracle.sql.Datum;
public class OracleTypeBINARY_FLOAT
  extends OracleType
  implements Serializable
{
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  47 */     BINARY_FLOAT localBINARY_FLOAT = null;
    
/*  49 */     if (paramObject != null)
    {
/*  51 */       if ((paramObject instanceof BINARY_FLOAT)) {
/*  52 */         localBINARY_FLOAT = (BINARY_FLOAT)paramObject;
/*  53 */       } else if ((paramObject instanceof Float)) {
/*  54 */         localBINARY_FLOAT = new BINARY_FLOAT((Float)paramObject);
/*  55 */       } else if ((paramObject instanceof byte[])) {
/*  56 */         localBINARY_FLOAT = new BINARY_FLOAT((byte[])paramObject);
      }
      else {
/*  59 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  60 */         localSQLException.fillInStackTrace();
/*  61 */         throw localSQLException;
      }
    }
    
/*  65 */     return localBINARY_FLOAT;
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  79 */     Datum[] arrayOfDatum = null;
    
/*  81 */     if (paramObject != null)
    {
/*  83 */       if ((paramObject instanceof Object[]))
      {
/*  85 */         Object[] arrayOfObject = (Object[])paramObject;
        
/*  87 */         int i = (int)(paramInt == -1 ? arrayOfObject.length : Math.min(arrayOfObject.length - paramLong + 1L, paramInt));
        
/*  90 */         arrayOfDatum = new Datum[i];
        
/*  92 */         for (int j = 0; j < i; j++)
        {
/*  94 */           Object localObject = arrayOfObject[((int)paramLong + j - 1)];
          
/*  96 */           if (localObject != null)
          {
/*  98 */             if ((localObject instanceof Float)) {
/*  99 */               arrayOfDatum[j] = new BINARY_FLOAT(((Float)localObject).floatValue());
            }
/* 101 */             else if ((localObject instanceof BINARY_FLOAT)) {
/* 102 */               arrayOfDatum[j] = ((BINARY_FLOAT)localObject);
            }
            else {
/* 105 */               SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 106 */               localSQLException.fillInStackTrace();
/* 107 */               throw localSQLException;
            }
            
          }
          else {
/* 113 */             arrayOfDatum[j] = null;
          }
        }
      }
    }
    
/* 119 */     return arrayOfDatum;
  }
  
  public int getTypeCode()
  {
/* 129 */     return 100;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 155 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 156 */       return null;
    }
/* 158 */     if (paramInt == 1)
/* 159 */       return new BINARY_FLOAT(paramArrayOfByte);
/* 160 */     if (paramInt == 2)
/* 161 */       return new BINARY_FLOAT(paramArrayOfByte).toJdbc();
/* 162 */     if (paramInt == 3) {
/* 163 */       return paramArrayOfByte;
    }
    
/* 166 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 167 */     localSQLException.fillInStackTrace();
/* 168 */     throw localSQLException;
  }
  
/* 173 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeBINARY_FLOAT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */