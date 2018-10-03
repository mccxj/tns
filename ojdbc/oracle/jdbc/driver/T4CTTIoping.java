/*    */ package oracle.jdbc.driver;
/*    */ import java.io.IOException;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.internal.OracleConnection;
/*    */ final class T4CTTIoping
/*    */   extends T4CTTIfun
/*    */ {
/*    */   T4CTTIoping(T4CConnection paramT4CConnection)
/*    */   {
/* 43 */     super(paramT4CConnection, (byte)3);
/*    */     
/* 45 */     setFunCode((short)147);
/*    */   }
/*    */   
/*    */   void doOPING()
/*    */     throws IOException, SQLException
/*    */   {
/* 52 */     doRPC();
/*    */   }
/*    */   
/*    */   void marshal()
/*    */     throws IOException
/*    */   {}
/*    */   
/*    */   protected OracleConnection getConnectionDuringExceptionHandling()
/*    */   {
/* 73 */     return this.connection;
/*    */   }
/*    */   
/* 78 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */