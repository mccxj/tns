package oracle.jdbc.driver;
import java.sql.ResultSet;
import java.sql.SQLException;
class ResultSetAccessor
  extends Accessor
{
  static final int maxLength = 16;
  OracleStatement currentStmt;
  
  ResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  27 */     init(paramOracleStatement, 102, 116, paramShort, paramBoolean);
/*  28 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  ResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  37 */     init(paramOracleStatement, 102, 116, paramShort, false);
/*  38 */     initForDescribe(102, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  40 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  48 */     if (paramInt1 != 0) {
/*  49 */       this.externalType = paramInt1;
    }
/*  51 */     this.internalTypeMaxLength = 16;
    
/*  53 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  54 */       this.internalTypeMaxLength = paramInt2;
    }
/*  56 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  ResultSet getCursor(int paramInt)
    throws SQLException
  {
/*  72 */     byte[] arrayOfByte = getBytes(paramInt);
    
/*  74 */     OracleStatement localOracleStatement = this.statement.connection.RefCursorBytesToStatement(arrayOfByte, this.statement);
    
/*  77 */     if ((this.currentStmt != null) && (this.currentStmt.cursorId == localOracleStatement.cursorId) && (this.currentStmt.currentResultSet != null))
    {
/*  80 */       return this.currentStmt.currentResultSet;
    }
    
/*  83 */     localOracleStatement.doDescribe(false);
/*  84 */     localOracleStatement.prepareAccessors();
    
/*  86 */     localOracleStatement.setPrefetchInternal(this.statement.getFetchSize(), false, false);
    
/*  88 */     OracleResultSetImpl localOracleResultSetImpl = new OracleResultSetImpl(localOracleStatement.connection, localOracleStatement);
    
/*  91 */     localOracleResultSetImpl.close_statement_on_close = true;
/*  92 */     localOracleStatement.currentResultSet = localOracleResultSetImpl;
/*  93 */     this.currentStmt = localOracleStatement;
    
/*  95 */     return localOracleResultSetImpl;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 110 */     return getCursor(paramInt);
  }
  
/* 115 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ResultSetAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */