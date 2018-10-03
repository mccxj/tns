package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class ResultSetUtil
{
/*  25 */   static final int[][] allRsetTypes = { { 0, 0 }, { 1003, 1007 }, { 1003, 1008 }, { 1004, 1007 }, { 1004, 1008 }, { 1005, 1007 }, { 1005, 1008 } };
  
  static OracleResultSet createScrollResultSet(ScrollRsetStatement paramScrollRsetStatement, OracleResultSet paramOracleResultSet, int paramInt)
    throws SQLException
  {
/*  62 */     switch (paramInt)
    {
    case 1: 
/*  66 */       return paramOracleResultSet;
    
    case 2: 
/*  69 */       return new UpdatableResultSet(paramScrollRsetStatement, (OracleResultSetImpl)paramOracleResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
    
    case 3: 
/*  75 */       return new ScrollableResultSet(paramScrollRsetStatement, (OracleResultSetImpl)paramOracleResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
    
    case 4: 
/*  81 */       ScrollableResultSet localScrollableResultSet = new ScrollableResultSet(paramScrollRsetStatement, (OracleResultSetImpl)paramOracleResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
      
/*  85 */       return new UpdatableResultSet(paramScrollRsetStatement, localScrollableResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
    
    case 5: 
/*  89 */       return new SensitiveScrollableResultSet(paramScrollRsetStatement, (OracleResultSetImpl)paramOracleResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
    
    case 6: 
/*  95 */       SensitiveScrollableResultSet localSensitiveScrollableResultSet = new SensitiveScrollableResultSet(paramScrollRsetStatement, (OracleResultSetImpl)paramOracleResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
      
/*  99 */       return new UpdatableResultSet(paramScrollRsetStatement, localSensitiveScrollableResultSet, getScrollType(paramInt), getUpdateConcurrency(paramInt));
    }
    
    
/* 104 */     SQLException localSQLException = DatabaseError.createSqlException(null, 23, null);
/* 105 */     localSQLException.fillInStackTrace();
/* 106 */     throw localSQLException;
  }
  
  static int getScrollType(int paramInt)
  {
/* 120 */     return allRsetTypes[paramInt][0];
  }
  
  static int getUpdateConcurrency(int paramInt)
  {
/* 131 */     return allRsetTypes[paramInt][1];
  }
  
  static int getRsetTypeCode(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 144 */     for (int i = 0; i < allRsetTypes.length; i++)
    {
/* 146 */       if ((allRsetTypes[i][0] == paramInt1) && (allRsetTypes[i][1] == paramInt2))
      {
/* 150 */         return i;
      }
    }
    
/* 155 */     SQLException localSQLException = DatabaseError.createSqlException(null, 68);
/* 156 */     localSQLException.fillInStackTrace();
/* 157 */     throw localSQLException;
  }
  
  static boolean needIdentifier(int paramInt)
    throws SQLException
  {
/* 172 */     return (paramInt != 1) && (paramInt != 3);
  }
  
  static boolean needIdentifier(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 180 */     return needIdentifier(getRsetTypeCode(paramInt1, paramInt2));
  }
  
  static boolean needCache(int paramInt)
    throws SQLException
  {
/* 192 */     return paramInt >= 3;
  }
  
  static boolean needCache(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 199 */     return needCache(getRsetTypeCode(paramInt1, paramInt2));
  }
  
  static boolean supportRefreshRow(int paramInt)
    throws SQLException
  {
/* 210 */     return paramInt >= 4;
  }
  
  static boolean supportRefreshRow(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 218 */     return supportRefreshRow(getRsetTypeCode(paramInt1, paramInt2));
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 233 */     return null;
  }
  
/* 238 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ResultSetUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */