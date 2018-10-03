/*    */ package oracle.jdbc;
/*    */ import java.util.Properties;
/*    */ public abstract interface NotificationRegistration
/*    */ {
/*    */   public abstract Properties getRegistrationOptions();
/*    */   
/*    */   public abstract String getDatabaseName();
/*    */   
/*    */   public abstract String getUserName();
/*    */   
/*    */   public abstract RegistrationState getState();
/*    */   
/*    */   public static enum RegistrationState
/*    */   {
/* 47 */     ACTIVE, 
/*    */     
/* 51 */     CLOSED;
/*    */     
/*    */     private RegistrationState() {}
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/NotificationRegistration.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */