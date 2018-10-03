package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.pool.OraclePooledConnection;
import oracle.sql.BLOB;
import oracle.sql.CLOB;
class ClosedConnection
  extends PhysicalConnection
{
  ClosedConnection()
  {
/*  29 */     this.lifecycle = 4;
  }
  
  void initializePassword(String paramString)
    throws SQLException
  {}
  
  OracleStatement RefCursorBytesToStatement(byte[] paramArrayOfByte, OracleStatement paramOracleStatement)
    throws SQLException
  {
/*  45 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  46 */     localSQLException.fillInStackTrace();
/*  47 */     throw localSQLException;
  }
  
  int getDefaultStreamChunkSize()
  {
/*  55 */     return -1;
  }
  
  short doGetVersionNumber()
    throws SQLException
  {
/*  63 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  64 */     localSQLException.fillInStackTrace();
/*  65 */     throw localSQLException;
  }
  
  String doGetDatabaseProductVersion()
    throws SQLException
  {
/*  75 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  76 */     localSQLException.fillInStackTrace();
/*  77 */     throw localSQLException;
  }
  
  void doRollback()
    throws SQLException
  {
/*  87 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  88 */     localSQLException.fillInStackTrace();
/*  89 */     throw localSQLException;
  }
  
  void doCommit(int paramInt)
    throws SQLException
  {
/*  98 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/*  99 */     localSQLException.fillInStackTrace();
/* 100 */     throw localSQLException;
  }
  
  void doSetAutoCommit(boolean paramBoolean)
    throws SQLException
  {
/* 109 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 110 */     localSQLException.fillInStackTrace();
/* 111 */     throw localSQLException;
  }
  
  void cancelOperationOnServer(boolean paramBoolean)
    throws SQLException
  {
/* 120 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 121 */     localSQLException.fillInStackTrace();
/* 122 */     throw localSQLException;
  }
  
  void doAbort()
    throws SQLException
  {}
  
  void open(OracleStatement paramOracleStatement)
    throws SQLException
  {
/* 138 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 139 */     localSQLException.fillInStackTrace();
/* 140 */     throw localSQLException;
  }
  
  void logon()
    throws SQLException
  {
/* 149 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 150 */     localSQLException.fillInStackTrace();
/* 151 */     throw localSQLException;
  }
  
  public void getPropertyForPooledConnection(OraclePooledConnection paramOraclePooledConnection)
    throws SQLException
  {
/* 161 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 162 */     localSQLException.fillInStackTrace();
/* 163 */     throw localSQLException;
  }
  
  public BLOB createTemporaryBlob(Connection paramConnection, boolean paramBoolean, int paramInt)
    throws SQLException
  {
/* 172 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 173 */     localSQLException.fillInStackTrace();
/* 174 */     throw localSQLException;
  }
  
  public CLOB createTemporaryClob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
    throws SQLException
  {
/* 183 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 184 */     localSQLException.fillInStackTrace();
/* 185 */     throw localSQLException;
  }
  
/* 191 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ClosedConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */