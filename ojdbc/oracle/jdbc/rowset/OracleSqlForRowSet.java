/*    */ package oracle.jdbc.rowset;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.OracleSql;
/*    */ class OracleSqlForRowSet
/*    */   extends OracleSql
/*    */ {
/*    */   OracleSqlForRowSet(String paramString)
/*    */     throws SQLException
/*    */   {
/* 37 */     super(null);
/*    */     
/* 39 */     initialize(paramString);
/*    */   }
/*    */   
/*    */   protected void initialize(String paramString)
/*    */     throws SQLException
/*    */   {
/* 55 */     super.initialize(paramString);
/*    */   }
/*    */   
/*    */   protected int getParameterCount()
/*    */     throws SQLException
/*    */   {
/* 70 */     return super.getParameterCount();
/*    */   }
/*    */   
/*    */   protected String[] getParameterList()
/*    */     throws SQLException
/*    */   {
/* 87 */     return super.getParameterList();
/*    */   }
/*    */   
/* 92 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleSqlForRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */