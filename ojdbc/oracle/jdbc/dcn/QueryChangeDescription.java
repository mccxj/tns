/*    */ package oracle.jdbc.dcn;
/*    */ public abstract interface QueryChangeDescription
/*    */ {
/*    */   public abstract long getQueryId();
/*    */   
/*    */   public abstract QueryChangeEventType getQueryChangeEventType();
/*    */   
/*    */   public abstract TableChangeDescription[] getTableChangeDescription();
/*    */   
/*    */   public static enum QueryChangeEventType
/*    */   {
/* 56 */     DEREG(DatabaseChangeEvent.EventType.DEREG.getCode()), 
/*    */     
/* 61 */     QUERYCHANGE(DatabaseChangeEvent.EventType.QUERYCHANGE.getCode());
/*    */     
/*    */     private final int code;
/*    */     
/* 65 */     private QueryChangeEventType(int paramInt) { this.code = paramInt; }
/*    */     
/*    */     public final int getCode()
/*    */     {
/* 73 */       return this.code;
/*    */     }
/*    */     
/*    */     public static final QueryChangeEventType getQueryChangeEventType(int paramInt)
/*    */     {
/* 80 */       if (paramInt == DEREG.getCode()) {
/* 81 */         return DEREG;
/*    */       }
/* 83 */       return QUERYCHANGE;
/*    */     }
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/dcn/QueryChangeDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */