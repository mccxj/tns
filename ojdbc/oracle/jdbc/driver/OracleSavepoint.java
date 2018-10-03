package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class OracleSavepoint
  implements oracle.jdbc.OracleSavepoint
{
  private static final int MAX_ID_VALUE = 2147483646;
  private static final int INVALID_ID_VALUE = -1;
  static final int NAMED_SAVEPOINT_TYPE = 2;
  static final int UNNAMED_SAVEPOINT_TYPE = 1;
  static final int UNKNOWN_SAVEPOINT_TYPE = 0;
/*  34 */   private static int s_seedId = 0;
/*  35 */   private int m_id = -1;
/*  36 */   private String m_name = null;
/*  37 */   private int m_type = 0;
  
  OracleSavepoint()
  {
/*  48 */     this.m_type = 1;
/*  49 */     this.m_id = getNextId();
/*  50 */     this.m_name = null;
  }
  
  OracleSavepoint(String paramString)
    throws SQLException
  {
/*  64 */     if ((paramString != null) && (paramString.length() != 0) && (!OracleSql.isValidObjectName(paramString)))
    {
/*  68 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  69 */       localSQLException.fillInStackTrace();
/*  70 */       throw localSQLException;
    }
    
/*  73 */     this.m_type = 2;
/*  74 */     this.m_name = paramString;
/*  75 */     this.m_id = -1;
  }
  
  public int getSavepointId()
    throws SQLException
  {
/*  92 */     if (this.m_type == 2)
    {
/*  94 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 118);
/*  95 */       localSQLException.fillInStackTrace();
/*  96 */       throw localSQLException;
    }
    
/*  99 */     return this.m_id;
  }
  
  public String getSavepointName()
    throws SQLException
  {
/* 116 */     if (this.m_type == 1)
    {
/* 118 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 119);
/* 119 */       localSQLException.fillInStackTrace();
/* 120 */       throw localSQLException;
    }
    
/* 123 */     return this.m_name;
  }
  
  int getType()
  {
/* 134 */     return this.m_type;
  }
  
  private synchronized int getNextId()
  {
/* 141 */     s_seedId = (s_seedId + 1) % 2147483646;
    
/* 143 */     return s_seedId;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 158 */     return null;
  }
  
/* 163 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleSavepoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */