/*    */ package oracle.jdbc.internal;
/*    */ import java.util.EventObject;
/*    */ public abstract class XSEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected XSEvent(Object paramObject)
/*    */   {
/* 39 */     super(paramObject);
/*    */   }
/*    */   
/*    */   public abstract byte[] getSessionId();
/*    */   
/*    */   public abstract KeywordValueLong[] getDetails();
/*    */   
/*    */   public abstract int getFlags();
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/XSEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */