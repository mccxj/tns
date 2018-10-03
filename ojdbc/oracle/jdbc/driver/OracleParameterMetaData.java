package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class OracleParameterMetaData
  implements oracle.jdbc.OracleParameterMetaData
{
/*  25 */   int parameterCount = 0;
  
  OracleParameterMetaData(int paramInt) throws SQLException
  {
/*  29 */     this.parameterCount = paramInt;
  }
  
  public int getParameterCount()
    throws SQLException
  {
/*  45 */     return this.parameterCount;
  }
  
  public int isNullable(int paramInt)
    throws SQLException
  {
/*  64 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/*  65 */     localSQLException.fillInStackTrace();
/*  66 */     throw localSQLException;
  }
  
/*  76 */   int parameterNoNulls = 0;
  
/*  84 */   int parameterNullable = 1;
  
/*  92 */   int parameterNullableUnknown = 2;
  
  public boolean isSigned(int paramInt)
    throws SQLException
  {
/* 107 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 108 */     localSQLException.fillInStackTrace();
/* 109 */     throw localSQLException;
  }
  
  public int getPrecision(int paramInt)
    throws SQLException
  {
/* 126 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 127 */     localSQLException.fillInStackTrace();
/* 128 */     throw localSQLException;
  }
  
  public int getScale(int paramInt)
    throws SQLException
  {
/* 145 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 146 */     localSQLException.fillInStackTrace();
/* 147 */     throw localSQLException;
  }
  
  public int getParameterType(int paramInt)
    throws SQLException
  {
/* 165 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 166 */     localSQLException.fillInStackTrace();
/* 167 */     throw localSQLException;
  }
  
  public String getParameterTypeName(int paramInt)
    throws SQLException
  {
/* 185 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 186 */     localSQLException.fillInStackTrace();
/* 187 */     throw localSQLException;
  }
  
  public String getParameterClassName(int paramInt)
    throws SQLException
  {
/* 210 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 211 */     localSQLException.fillInStackTrace();
/* 212 */     throw localSQLException;
  }
  
/* 221 */   int parameterModeUnknown = 0;
  
/* 228 */   int parameterModeIn = 1;
  
/* 235 */   int parameterModeInOut = 2;
  
/* 242 */   int parameterModeOut = 4;
  
  public int getParameterMode(int paramInt)
    throws SQLException
  {
/* 261 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 262 */     localSQLException.fillInStackTrace();
/* 263 */     throw localSQLException;
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 281 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 283 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 284 */     localSQLException.fillInStackTrace();
/* 285 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 302 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 304 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 305 */     localSQLException.fillInStackTrace();
/* 306 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 323 */     return null;
  }
  
/* 328 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleParameterMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */