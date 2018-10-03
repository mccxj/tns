/*    */ package oracle.jdbc.driver;
/*    */ import java.io.PrintStream;
/*    */ import java.sql.SQLException;
/*    */ class OracleStatementCacheEntry
/*    */ {
/* 47 */   protected OracleStatementCacheEntry applicationNext = null;
/* 48 */   protected OracleStatementCacheEntry applicationPrev = null;
/*    */   
/* 51 */   protected OracleStatementCacheEntry explicitNext = null;
/* 52 */   protected OracleStatementCacheEntry explicitPrev = null;
/*    */   
/* 55 */   protected OracleStatementCacheEntry implicitNext = null;
/* 56 */   protected OracleStatementCacheEntry implicitPrev = null;
/*    */   
/*    */   boolean onImplicit;
/*    */   
/*    */   String sql;
/*    */   
/*    */   int statementType;
/*    */   
/*    */   int scrollType;
/*    */   
/*    */   OraclePreparedStatement statement;
/*    */   
/*    */   public void print()
/*    */     throws SQLException
/*    */   {
/* 77 */     System.out.println("Cache entry " + this);
/* 78 */     System.out.println("  Key: " + this.sql + "$$" + this.statementType + "$$" + this.scrollType);
/*    */     
/* 80 */     System.out.println("  Statement: " + this.statement);
/* 81 */     System.out.println("  onImplicit: " + this.onImplicit);
/* 82 */     System.out.println("  applicationNext: " + this.applicationNext + "  applicationPrev: " + this.applicationPrev);
/*    */     
/* 84 */     System.out.println("  implicitNext: " + this.implicitNext + "  implicitPrev: " + this.implicitPrev);
/*    */     
/* 86 */     System.out.println("  explicitNext: " + this.explicitNext + "  explicitPrev: " + this.explicitPrev);
/*    */   }
/*    */   
/* 94 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleStatementCacheEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */