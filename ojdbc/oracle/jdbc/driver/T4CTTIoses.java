/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ final class T4CTTIoses
/*    */   extends T4CTTIfun
/*    */ {
/*    */   static final int OSESSWS = 1;
/*    */   static final int OSESDET = 3;
/*    */   private int sididx;
/*    */   private int sidser;
/*    */   private int sidopc;
/*    */   
/*    */   T4CTTIoses(T4CConnection paramT4CConnection)
/*    */   {
/* 46 */     super(paramT4CConnection, (byte)17);
/*    */     
/* 48 */     setFunCode((short)107);
/*    */   }
/*    */   
/*    */   void doO80SES(int paramInt1, int paramInt2, int paramInt3)
/*    */     throws IOException, SQLException
/*    */   {
/* 59 */     this.sididx = paramInt1;
/* 60 */     this.sidser = paramInt2;
/* 61 */     this.sidopc = paramInt3;
/*    */     
/* 63 */     if ((this.sidopc != 1) && (this.sidopc != 3))
/* 64 */       throw new SQLException("Wrong operation : can only do switch or detach");
/* 65 */     doPigRPC();
/*    */   }
/*    */   
/*    */   void marshal()
/*    */     throws IOException
/*    */   {
/* 72 */     this.meg.marshalUB4(this.sididx);
/* 73 */     this.meg.marshalUB4(this.sidser);
/* 74 */     this.meg.marshalUB4(this.sidopc);
/*    */   }
/*    */   
/* 79 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */