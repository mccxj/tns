/*    */ package oracle.sql;
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.OracleCallableStatement;
/*    */ public class TRANSDUMP
/*    */ {
/* 47 */   private static byte[] GMT_TRANSITIONS = { 1, 118, 100, 1, 1, 1, 1, 1, 20, 60, 0 };
/*    */   
/*    */   public static byte[] getTransitions(Connection paramConnection, int paramInt)
/*    */     throws SQLException
/*    */   {
/* 57 */     arrayOfByte = null;
/*    */     
/* 59 */     if (paramInt == ZONEIDMAP.getID("GMT")) {
/* 60 */       arrayOfByte = GMT_TRANSITIONS;
/*    */     }
/*    */     else
/*    */     {
/* 65 */       OracleCallableStatement localOracleCallableStatement = (OracleCallableStatement)paramConnection.prepareCall("begin dbms_utility.get_tz_transitions(:1,:2); end;");
/*    */       
/* 71 */       localOracleCallableStatement.setInt(1, paramInt);
/*    */       
/* 74 */       localOracleCallableStatement.registerOutParameter(2, -2);
/*    */       
/*    */       try
/*    */       {
/* 79 */         localOracleCallableStatement.execute();
/*    */         
/* 82 */         return localOracleCallableStatement.getBytes(2);
/*    */       }
/*    */       finally
/*    */       {
/*    */         try
/*    */         {
/* 88 */           localOracleCallableStatement.close();
/*    */         }
/*    */         catch (Exception localException2) {}
/*    */       }
/*    */     }
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TRANSDUMP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */