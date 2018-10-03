/*    */ package oracle.jdbc.dcn;
/*    */ import oracle.sql.ROWID;
/*    */ public abstract interface RowChangeDescription
/*    */ {
/*    */   public abstract RowOperation getRowOperation();
/*    */   
/*    */   public abstract ROWID getRowid();
/*    */   
/*    */   public static enum RowOperation
/*    */   {
/* 49 */     INSERT(TableChangeDescription.TableOperation.INSERT.getCode()), 
/*    */     
/* 53 */     UPDATE(TableChangeDescription.TableOperation.UPDATE.getCode()), 
/*    */     
/* 57 */     DELETE(TableChangeDescription.TableOperation.DELETE.getCode());
/*    */     
/*    */     private final int code;
/*    */     
/* 61 */     private RowOperation(int paramInt) { this.code = paramInt; }
/*    */     
/*    */     public final int getCode()
/*    */     {
/* 68 */       return this.code;
/*    */     }
/*    */     
/*    */     public static final RowOperation getRowOperation(int paramInt)
/*    */     {
/* 75 */       if (paramInt == INSERT.getCode())
/* 76 */         return INSERT;
/* 77 */       if (paramInt == UPDATE.getCode()) {
/* 78 */         return UPDATE;
/*    */       }
/* 80 */       return DELETE;
/*    */     }
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/dcn/RowChangeDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */