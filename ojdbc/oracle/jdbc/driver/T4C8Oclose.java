/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ final class T4C8Oclose
/*    */   extends T4CTTIfun
/*    */ {
/* 25 */   private int[] cursorId = null;
/* 26 */   private int offset = 0;
/*    */   
/*    */   T4C8Oclose(T4CConnection paramT4CConnection)
/*    */   {
/* 30 */     super(paramT4CConnection, (byte)17);
/*    */   }
/*    */   
/*    */   void doOCANA(int[] paramArrayOfInt, int paramInt)
/*    */     throws IOException
/*    */   {
/* 39 */     setFunCode((short)120);
/* 40 */     this.cursorId = paramArrayOfInt;
/* 41 */     this.offset = paramInt;
/* 42 */     doPigRPC();
/*    */   }
/*    */   
/*    */   void doOCCA(int[] paramArrayOfInt, int paramInt)
/*    */     throws IOException
/*    */   {
/* 48 */     setFunCode((short)105);
/* 49 */     this.cursorId = paramArrayOfInt;
/* 50 */     this.offset = paramInt;
/* 51 */     doPigRPC();
/*    */   }
/*    */   
/*    */   void marshal()
/*    */     throws IOException
/*    */   {
/* 59 */     this.meg.marshalPTR();
/* 60 */     this.meg.marshalUB4(this.offset);
/*    */     
/* 62 */     for (int i = 0; i < this.offset; i++)
/*    */     {
/* 64 */       this.meg.marshalUB4(this.cursorId[i]);
/*    */     }
/*    */   }
/*    */   
/* 69 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8Oclose.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */