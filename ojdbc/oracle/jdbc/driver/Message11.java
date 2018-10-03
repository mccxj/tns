/*    */ package oracle.jdbc.driver;
/*    */ import java.util.ResourceBundle;
/*    */ class Message11
/*    */   implements Message
/*    */ {
/*    */   private static ResourceBundle bundle;
/*    */   private static final String messageFile = "oracle.jdbc.driver.Messages";
/*    */   
/*    */   public String msg(String paramString, Object paramObject)
/*    */   {
/* 32 */     if (bundle == null)
/*    */     {
/*    */       try
/*    */       {
/* 36 */         bundle = ResourceBundle.getBundle("oracle.jdbc.driver.Messages");
/*    */       }
/*    */       catch (Exception localException1)
/*    */       {
/* 41 */         return "Message file 'oracle.jdbc.driver.Messages' is missing.";
/*    */       }
/*    */     }
/*    */     
/*    */     try
/*    */     {
/* 48 */       if (paramObject != null) {
/* 49 */         return bundle.getString(paramString) + ": " + paramObject;
/*    */       }
/* 51 */       return bundle.getString(paramString);
/*    */     }
/*    */     catch (Exception localException2) {}
/*    */     
/* 55 */     return "Message [" + paramString + "] not found in '" + "oracle.jdbc.driver.Messages" + "'.";
/*    */   }
/*    */   
/* 61 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/Message11.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */