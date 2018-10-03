/*    */ package oracle.jdbc;
/*    */ import java.security.BasicPermission;
/*    */ public final class OracleSQLPermission
/*    */   extends BasicPermission
/*    */ {
/* 25 */   private static final String[] allowedTargets = { "callAbort" };
/*    */   
/*    */   private final void checkTarget(String paramString) {
/* 28 */     for (int i = 0; i < allowedTargets.length; i++) {
/* 29 */       if (paramString.equals(allowedTargets[i])) return;
/*    */     }
/* 31 */     throw new IllegalArgumentException(paramString);
/*    */   }
/*    */   
/*    */   public OracleSQLPermission(String paramString) {
/* 35 */     super(paramString);
/* 36 */     checkTarget(paramString);
/*    */   }
/*    */   
/*    */   public OracleSQLPermission(String paramString1, String paramString2) {
/* 40 */     super(paramString1, paramString2);
/* 41 */     checkTarget(paramString1);
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleSQLPermission.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */