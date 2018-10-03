/*    */ package oracle.jdbc;
/*    */ import java.sql.ResultSetMetaData;
/*    */ import java.sql.SQLException;
/*    */ public abstract interface OracleResultSetMetaData
/*    */   extends ResultSetMetaData
/*    */ {
/*    */   public abstract boolean isNCHAR(int paramInt)
/*    */     throws SQLException;
/*    */   
/*    */   public abstract SecurityAttribute getSecurityAttribute(int paramInt)
/*    */     throws SQLException;
/*    */   
/*    */   public static enum SecurityAttribute
/*    */   {
/* 73 */     NONE, 
/*    */     
/* 81 */     ENABLED, 
/*    */     
/* 97 */     UNKNOWN;
/*    */     
/*    */     private SecurityAttribute() {}
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */