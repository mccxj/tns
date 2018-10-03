/*    */ package oracle.jdbc.driver;
/*    */ class ByteArrayKey
/*    */ {
/*    */   private byte[] theBytes;
/*    */   
/* 24 */   private int cachedHashCode = -1;
/*    */   
/*    */   public ByteArrayKey(byte[] paramArrayOfByte)
/*    */   {
/* 28 */     this.theBytes = paramArrayOfByte;
/* 29 */     for (int k : this.theBytes) {
/* 30 */       this.cachedHashCode = (this.cachedHashCode << 1 & (this.cachedHashCode < 0 ? 1 : 0) ^ k);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 35 */     if (paramObject == this) {
/* 36 */       return true;
/*    */     }
/* 38 */     if (!(paramObject instanceof ByteArrayKey))
/*    */     {
/* 41 */       return false;
/*    */     }
/*    */     
/* 44 */     byte[] arrayOfByte = ((ByteArrayKey)paramObject).theBytes;
/*    */     
/* 46 */     if (this.theBytes.length != arrayOfByte.length) {
/* 47 */       return false;
/*    */     }
/* 49 */     for (int i = 0; i < this.theBytes.length; i++) {
/* 50 */       if (this.theBytes[i] != arrayOfByte[i])
/* 51 */         return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 58 */     return this.cachedHashCode;
/*    */   }
/*    */   
/* 62 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ByteArrayKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */