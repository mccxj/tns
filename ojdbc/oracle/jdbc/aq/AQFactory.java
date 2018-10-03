/*    */ package oracle.jdbc.aq;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.driver.InternalFactory;
/*    */ public abstract class AQFactory
/*    */ {
/*    */   public static AQMessage createAQMessage(AQMessageProperties paramAQMessageProperties)
/*    */     throws SQLException
/*    */   {
/* 52 */     return InternalFactory.createAQMessage(paramAQMessageProperties);
/*    */   }
/*    */   
/*    */   public static AQMessageProperties createAQMessageProperties()
/*    */     throws SQLException
/*    */   {
/* 63 */     return InternalFactory.createAQMessageProperties();
/*    */   }
/*    */   
/*    */   public static AQAgent createAQAgent()
/*    */     throws SQLException
/*    */   {
/* 73 */     return InternalFactory.createAQAgent();
/*    */   }
/*    */   
/* 77 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/aq/AQFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */