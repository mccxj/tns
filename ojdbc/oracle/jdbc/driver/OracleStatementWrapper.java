package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import oracle.jdbc.OracleResultSetCache;
import oracle.jdbc.dcn.DatabaseChangeRegistration;
import oracle.jdbc.internal.OracleCallableStatement;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class OracleStatementWrapper
  implements oracle.jdbc.internal.OracleStatement
{
  private Object forEquals;
  protected oracle.jdbc.internal.OracleStatement statement;
/*  63 */   static final OracleCallableStatement closedStatement = new OracleClosedStatement();
  
  OracleStatementWrapper(oracle.jdbc.OracleStatement paramOracleStatement)
    throws SQLException
  {
/*  72 */     this.forEquals = paramOracleStatement;
    
/*  74 */     this.statement = ((oracle.jdbc.internal.OracleStatement)paramOracleStatement);
/*  75 */     ((OracleStatement)paramOracleStatement).wrapper = this;
  }
  
  public void close()
    throws SQLException
  {
/*  83 */     if (this.statement == closedStatement) {
/*  84 */       return;
    }
    
/*  88 */     this.checkSum = (((OracleStatement)this.statement).checkSum = this.checkSum);
    
/*  90 */     this.checkSumComputationFailure = ((OracleStatement)this.statement).checkSumComputationFailure;
    
/*  92 */     this.sqlKind = ((OracleStatement)this.statement).sqlKind;
    
/*  94 */     this.statement.close();
/*  95 */     this.statement = closedStatement;
  }
  
  public void closeWithKey(String paramString)
    throws SQLException
  {
/* 102 */     this.statement.closeWithKey(paramString);
/* 103 */     this.statement = closedStatement;
  }
  
  public boolean equals(Object paramObject)
  {
/* 108 */     return (paramObject != null) && (getClass() == paramObject.getClass()) && (this.forEquals == ((OracleStatementWrapper)paramObject).forEquals);
  }
  
  public int hashCode()
  {
/* 114 */     return this.forEquals.hashCode();
  }
  
  public int getFetchDirection()
    throws SQLException
  {
/* 126 */     return this.statement.getFetchDirection();
  }
  
  public int getFetchSize()
    throws SQLException
  {
/* 134 */     return this.statement.getFetchSize();
  }
  
  public int getMaxFieldSize()
    throws SQLException
  {
/* 142 */     return this.statement.getMaxFieldSize();
  }
  
  public int getMaxRows()
    throws SQLException
  {
/* 150 */     return this.statement.getMaxRows();
  }
  
  public int getQueryTimeout()
    throws SQLException
  {
/* 158 */     return this.statement.getQueryTimeout();
  }
  
  public int getResultSetConcurrency()
    throws SQLException
  {
/* 166 */     return this.statement.getResultSetConcurrency();
  }
  
  public int getResultSetHoldability()
    throws SQLException
  {
/* 174 */     return this.statement.getResultSetHoldability();
  }
  
  public int getResultSetType()
    throws SQLException
  {
/* 182 */     return this.statement.getResultSetType();
  }
  
  public int getUpdateCount()
    throws SQLException
  {
/* 190 */     return this.statement.getUpdateCount();
  }
  
  public void cancel()
    throws SQLException
  {
/* 198 */     this.statement.cancel();
  }
  
  public void clearBatch()
    throws SQLException
  {
/* 206 */     this.statement.clearBatch();
  }
  
  public void clearWarnings()
    throws SQLException
  {
/* 214 */     this.statement.clearWarnings();
  }
  
  public boolean getMoreResults()
    throws SQLException
  {
/* 222 */     return this.statement.getMoreResults();
  }
  
  public int[] executeBatch()
    throws SQLException
  {
/* 230 */     return this.statement.executeBatch();
  }
  
  public void setFetchDirection(int paramInt)
    throws SQLException
  {
/* 238 */     this.statement.setFetchDirection(paramInt);
  }
  
  public void setFetchSize(int paramInt)
    throws SQLException
  {
/* 246 */     this.statement.setFetchSize(paramInt);
  }
  
  public void setMaxFieldSize(int paramInt)
    throws SQLException
  {
/* 254 */     this.statement.setMaxFieldSize(paramInt);
  }
  
  public void setMaxRows(int paramInt)
    throws SQLException
  {
/* 262 */     this.statement.setMaxRows(paramInt);
  }
  
  public void setQueryTimeout(int paramInt)
    throws SQLException
  {
/* 270 */     this.statement.setQueryTimeout(paramInt);
  }
  
  public boolean getMoreResults(int paramInt)
    throws SQLException
  {
/* 278 */     return this.statement.getMoreResults(paramInt);
  }
  
  public void setEscapeProcessing(boolean paramBoolean)
    throws SQLException
  {
/* 286 */     this.statement.setEscapeProcessing(paramBoolean);
  }
  
  public int executeUpdate(String paramString)
    throws SQLException
  {
/* 294 */     return this.statement.executeUpdate(paramString);
  }
  
  public void addBatch(String paramString)
    throws SQLException
  {
/* 302 */     this.statement.addBatch(paramString);
  }
  
  public void setCursorName(String paramString)
    throws SQLException
  {
/* 310 */     this.statement.setCursorName(paramString);
  }
  
  public boolean execute(String paramString)
    throws SQLException
  {
/* 318 */     return this.statement.execute(paramString);
  }
  
  public int executeUpdate(String paramString, int paramInt)
    throws SQLException
  {
/* 326 */     return this.statement.executeUpdate(paramString, paramInt);
  }
  
  public boolean execute(String paramString, int paramInt)
    throws SQLException
  {
/* 334 */     return this.statement.execute(paramString, paramInt);
  }
  
  public int executeUpdate(String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
/* 342 */     return this.statement.executeUpdate(paramString, paramArrayOfInt);
  }
  
  public boolean execute(String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
/* 350 */     return this.statement.execute(paramString, paramArrayOfInt);
  }
  
  public Connection getConnection()
    throws SQLException
  {
/* 358 */     return this.statement.getConnection();
  }
  
  public ResultSet getGeneratedKeys()
    throws SQLException
  {
/* 366 */     return this.statement.getGeneratedKeys();
  }
  
  public ResultSet getResultSet()
    throws SQLException
  {
/* 374 */     return this.statement.getResultSet();
  }
  
  public SQLWarning getWarnings()
    throws SQLException
  {
/* 382 */     return this.statement.getWarnings();
  }
  
  public int executeUpdate(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
/* 390 */     return this.statement.executeUpdate(paramString, paramArrayOfString);
  }
  
  public boolean execute(String paramString, String[] paramArrayOfString)
    throws SQLException
  {
/* 398 */     return this.statement.execute(paramString, paramArrayOfString);
  }
  
  public ResultSet executeQuery(String paramString)
    throws SQLException
  {
/* 406 */     return this.statement.executeQuery(paramString);
  }
  
  public void clearDefines()
    throws SQLException
  {
/* 420 */     this.statement.clearDefines();
  }
  
  public void defineColumnType(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 428 */     this.statement.defineColumnType(paramInt1, paramInt2);
  }
  
  public void defineColumnType(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
/* 436 */     this.statement.defineColumnType(paramInt1, paramInt2, paramInt3);
  }
  
  public void defineColumnType(int paramInt1, int paramInt2, int paramInt3, short paramShort)
    throws SQLException
  {
/* 444 */     this.statement.defineColumnType(paramInt1, paramInt2, paramInt3, paramShort);
  }
  
  public void defineColumnTypeBytes(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
/* 452 */     this.statement.defineColumnTypeBytes(paramInt1, paramInt2, paramInt3);
  }
  
  public void defineColumnTypeChars(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException
  {
/* 460 */     this.statement.defineColumnTypeChars(paramInt1, paramInt2, paramInt3);
  }
  
  public void defineColumnType(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/* 468 */     this.statement.defineColumnType(paramInt1, paramInt2, paramString);
  }
  
  public int getRowPrefetch()
  {
/* 475 */     return this.statement.getRowPrefetch();
  }
  
  public void setResultSetCache(OracleResultSetCache paramOracleResultSetCache)
    throws SQLException
  {
/* 483 */     this.statement.setResultSetCache(paramOracleResultSetCache);
  }
  
  public void setRowPrefetch(int paramInt)
    throws SQLException
  {
/* 491 */     this.statement.setRowPrefetch(paramInt);
  }
  
  public int getLobPrefetchSize()
  {
/* 498 */     return this.statement.getLobPrefetchSize();
  }
  
  public void setLobPrefetchSize(int paramInt)
    throws SQLException
  {
/* 505 */     this.statement.setLobPrefetchSize(paramInt);
  }
  
  public int creationState()
  {
/* 513 */     return this.statement.creationState();
  }
  
  public boolean isNCHAR(int paramInt)
    throws SQLException
  {
/* 521 */     return this.statement.isNCHAR(paramInt);
  }
  
  public void setDatabaseChangeRegistration(DatabaseChangeRegistration paramDatabaseChangeRegistration)
    throws SQLException
  {
/* 530 */     this.statement.setDatabaseChangeRegistration(paramDatabaseChangeRegistration);
  }
  
  public boolean isClosed()
    throws SQLException
  {
/* 543 */     return this.statement.isClosed();
  }
  
  public boolean isPoolable()
    throws SQLException
  {
/* 551 */     return this.statement.isPoolable();
  }
  
  public void setPoolable(boolean paramBoolean)
    throws SQLException
  {
/* 557 */     this.statement.setPoolable(paramBoolean);
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 572 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 574 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 575 */     localSQLException.fillInStackTrace();
/* 576 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 594 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 596 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 597 */     localSQLException.fillInStackTrace();
/* 598 */     throw localSQLException;
  }
  
  public void setFixedString(boolean paramBoolean)
  {
/* 612 */     this.statement.setFixedString(paramBoolean);
  }
  
  public boolean getFixedString()
  {
/* 619 */     return this.statement.getFixedString();
  }
  
  public int sendBatch()
    throws SQLException
  {
/* 626 */     return this.statement.sendBatch();
  }
  
  public boolean getserverCursor()
  {
/* 633 */     return this.statement.getserverCursor();
  }
  
  public int getcacheState()
  {
/* 640 */     return this.statement.getcacheState();
  }
  
  public int getstatementType()
  {
/* 648 */     return this.statement.getstatementType();
  }
  
  public String[] getRegisteredTableNames()
    throws SQLException
  {
/* 655 */     return this.statement.getRegisteredTableNames();
  }
  
  public long getRegisteredQueryId()
    throws SQLException
  {
/* 662 */     return this.statement.getRegisteredQueryId();
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 677 */     return null;
  }
  
  public OracleStatement.SqlKind getSqlKind()
    throws SQLException
  {
/* 684 */     return this.statement == closedStatement ? this.sqlKind : this.statement.getSqlKind();
  }
  
/* 688 */   OracleStatement.SqlKind sqlKind = OracleStatement.SqlKind.UNINITIALIZED;
/* 689 */   long checkSum = 0L;
/* 690 */   boolean checkSumComputationFailure = false;
  
  public long getChecksum()
    throws SQLException
  {
/* 695 */     if (this.statement != closedStatement) {
/* 696 */       return this.statement.getChecksum();
    }
    
/* 699 */     if (this.checkSumComputationFailure)
    {
/* 701 */       SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 702 */       localSQLException.fillInStackTrace();
/* 703 */       throw localSQLException;
    }
    
/* 706 */     return this.checkSum;
  }
  
  public void setSnapshotSCN(long paramLong)
    throws SQLException
  {
/* 713 */     this.statement.setSnapshotSCN(paramLong);
  }
  
/* 718 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleStatementWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */