/*    */ package oracle.jdbc.replay.driver;
/*    */ import java.util.logging.Handler;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ public class ReplayLoggerFactory
/*    */ {
/* 29 */   private static final Logger logger = Logger.getLogger(ReplayLoggerFactory.class.getCanonicalName());
/*    */   
/*    */   public static Logger getLogger(String paramString)
/*    */   {
/* 43 */     return Logger.getLogger(paramString);
/*    */   }
/*    */   
/*    */   public static synchronized void setLogLevel(Level paramLevel)
/*    */   {
/*    */     try
/*    */     {
/* 56 */       Logger.getLogger("oracle.jdbc.internal.replay").setLevel(paramLevel);
/*    */       
/* 59 */       if (paramLevel != null)
/*    */       {
/* 63 */         Handler[] arrayOfHandler = Logger.getLogger("").getHandlers();
/* 64 */         for (int i = 0; i < arrayOfHandler.length; i++)
/*    */         {
/* 67 */           arrayOfHandler[i].setLevel(paramLevel);
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 73 */       logger.log(Level.FINEST, "setLogLevel", localException);
/*    */     }
/*    */   }
/*    */   
/*    */   public static Level getLogLevel()
/*    */   {
/* 84 */     return Logger.getLogger("oracle.jdbc.internal.replay").getLevel();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/ReplayLoggerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */