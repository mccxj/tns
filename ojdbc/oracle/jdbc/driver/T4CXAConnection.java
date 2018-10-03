/*    */ package oracle.jdbc.driver;
/*    */ import java.sql.Connection;
/*    */ import javax.transaction.xa.XAException;
/*    */ import javax.transaction.xa.XAResource;
/*    */ import oracle.jdbc.xa.OracleXAResource;
/*    */ import oracle.jdbc.xa.client.OracleXAConnection;
/*    */ public class T4CXAConnection
/*    */   extends OracleXAConnection
/*    */ {
/*    */   T4CTTIOtxen otxen;
/*    */   T4CTTIOtxse otxse;
/*    */   T4CConnection physicalConnection;
/*    */   
/*    */   public T4CXAConnection(Connection paramConnection)
/*    */     throws XAException
/*    */   {
/* 33 */     super(paramConnection);
/*    */     
/* 35 */     this.physicalConnection = ((T4CConnection)paramConnection);
/* 36 */     this.xaResource = null;
/*    */   }
/*    */   
/*    */   public synchronized XAResource getXAResource()
/*    */   {
/*    */     try
/*    */     {
/* 45 */       if (this.xaResource == null)
/*    */       {
/* 47 */         this.xaResource = new T4CXAResource(this.physicalConnection, this, this.isXAResourceTransLoose);
/*    */         
/* 50 */         if (this.logicalHandle != null)
/*    */         {
/* 54 */           ((OracleXAResource)this.xaResource).setLogicalConnection(this.logicalHandle);
/*    */         }
/*    */         
/*    */       }
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 61 */       this.xaResource = null;
/*    */     }
/* 63 */     return this.xaResource;
/*    */   }
/*    */   
/* 68 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CXAConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */