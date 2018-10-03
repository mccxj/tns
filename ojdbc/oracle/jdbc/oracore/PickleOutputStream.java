/*    */ package oracle.jdbc.oracore;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ public class PickleOutputStream
/*    */   extends ByteArrayOutputStream
/*    */ {
/*    */   public PickleOutputStream() {}
/*    */   
/*    */   public PickleOutputStream(int paramInt)
/*    */   {
/* 26 */     super(paramInt);
/*    */   }
/*    */   
/*    */   public synchronized int offset()
/*    */   {
/* 34 */     return this.count;
/*    */   }
/*    */   
/*    */   public synchronized void overwrite(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*    */   {
/* 43 */     if ((paramInt2 < 0) || (paramInt2 > paramArrayOfByte.length) || (paramInt3 < 0) || (paramInt2 + paramInt3 > paramArrayOfByte.length) || (paramInt2 + paramInt3 < 0) || (paramInt1 + paramInt3 > this.buf.length))
/*    */     {
/* 46 */       throw new IndexOutOfBoundsException();
/*    */     }
/* 48 */     if (paramInt3 == 0)
/*    */     {
/* 50 */       return;
/*    */     }
/*    */     
/* 53 */     for (int i = 0; i < paramInt3; i++)
/*    */     {
/* 55 */       this.buf[(paramInt1 + i)] = paramArrayOfByte[(paramInt2 + i)];
/*    */     }
/*    */   }
/*    */   
/* 77 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/PickleOutputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */