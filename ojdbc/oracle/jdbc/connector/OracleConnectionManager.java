/*    */ package oracle.jdbc.connector;
/*    */ import javax.resource.ResourceException;
/*    */ import javax.resource.spi.ConnectionManager;
/*    */ import javax.resource.spi.ConnectionRequestInfo;
/*    */ import javax.resource.spi.ManagedConnection;
/*    */ import javax.resource.spi.ManagedConnectionFactory;
/*    */ public class OracleConnectionManager
/*    */   implements ConnectionManager
/*    */ {
/*    */   public Object allocateConnection(ManagedConnectionFactory paramManagedConnectionFactory, ConnectionRequestInfo paramConnectionRequestInfo)
/*    */     throws ResourceException
/*    */   {
/* 63 */     ManagedConnection localManagedConnection = paramManagedConnectionFactory.createManagedConnection(null, paramConnectionRequestInfo);
/*    */     
/* 65 */     return localManagedConnection.getConnection(null, paramConnectionRequestInfo);
/*    */   }
/*    */   
/* 70 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleConnectionManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */