/*    */ package oracle.jdbc;
/*    */ public enum LargeObjectAccessMode
/*    */ {
/* 21 */   MODE_READONLY(0), 
/* 22 */   MODE_READWRITE(1);
/*    */   
/* 25 */   private LargeObjectAccessMode(int paramInt) { this.code = paramInt; }
/*    */   
/*    */   private final int code;
/* 28 */   public int getCode() { return this.code; }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/LargeObjectAccessMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */