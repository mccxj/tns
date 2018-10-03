/*    */ package oracle.sql;
/*    */ import java.sql.SQLException;
/*    */ import oracle.jdbc.OracleConnection;
/*    */ import oracle.jdbc.internal.OracleNClob;
/*    */ public class NCLOB
/*    */   extends CLOB
/*    */   implements OracleNClob
/*    */ {
/*    */   protected NCLOB() {}
/*    */   
/*    */   public NCLOB(OracleConnection paramOracleConnection)
/*    */     throws SQLException
/*    */   {
/* 45 */     this(paramOracleConnection, null);
/*    */   }
/*    */   
/*    */   public NCLOB(OracleConnection paramOracleConnection, byte[] paramArrayOfByte)
/*    */     throws SQLException
/*    */   {
/* 61 */     super(paramOracleConnection, paramArrayOfByte, (short)2);
/*    */   }
/*    */   
/*    */   public NCLOB(CLOB paramCLOB)
/*    */     throws SQLException
/*    */   {
/* 76 */     this(paramCLOB.getPhysicalConnection(), paramCLOB.getBytes());
/*    */   }
/*    */   
/* 80 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/NCLOB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */