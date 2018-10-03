/*    */ package oracle.jdbc.driver;
/*    */ import oracle.jdbc.dcn.RowChangeDescription;
/*    */ import oracle.jdbc.dcn.RowChangeDescription.RowOperation;
/*    */ import oracle.sql.ROWID;
/*    */ class NTFDCNRowChanges
/*    */   implements RowChangeDescription
/*    */ {
/*    */   RowChangeDescription.RowOperation opcode;
/*    */   int rowidLength;
/*    */   byte[] rowid;
/*    */   ROWID rowidObj;
/*    */   
/*    */   NTFDCNRowChanges(RowChangeDescription.RowOperation paramRowOperation, int paramInt, byte[] paramArrayOfByte)
/*    */   {
/* 40 */     this.opcode = paramRowOperation;
/* 41 */     this.rowidLength = paramInt;
/* 42 */     this.rowid = paramArrayOfByte;
/* 43 */     this.rowidObj = null;
/*    */   }
/*    */   
/*    */   public ROWID getRowid()
/*    */   {
/* 50 */     if (this.rowidObj == null)
/* 51 */       this.rowidObj = new ROWID(this.rowid);
/* 52 */     return this.rowidObj;
/*    */   }
/*    */   
/*    */   public RowChangeDescription.RowOperation getRowOperation()
/*    */   {
/* 58 */     return this.opcode;
/*    */   }
/*    */   
/*    */   public String toString()
/*    */   {
/* 65 */     StringBuffer localStringBuffer = new StringBuffer();
/* 66 */     localStringBuffer.append("      ROW:  operation=" + getRowOperation() + ", ROWID=" + new String(this.rowid) + "\n");
/* 67 */     return localStringBuffer.toString();
/*    */   }
/*    */   
/* 71 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFDCNRowChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */