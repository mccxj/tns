/*    */ package oracle.jdbc.connector;
/*    */ import javax.resource.NotSupportedException;
/*    */ import javax.resource.ResourceException;
/*    */ import javax.resource.spi.ActivationSpec;
/*    */ import javax.resource.spi.BootstrapContext;
/*    */ import javax.resource.spi.ResourceAdapter;
/*    */ import javax.resource.spi.ResourceAdapterInternalException;
/*    */ import javax.resource.spi.endpoint.MessageEndpointFactory;
/*    */ import javax.transaction.xa.XAResource;
/*    */ public class OracleResourceAdapter
/*    */   implements ResourceAdapter
/*    */ {
/*    */   public void start(BootstrapContext paramBootstrapContext)
/*    */     throws ResourceAdapterInternalException
/*    */   {}
/*    */   
/*    */   public void stop() {}
/*    */   
/*    */   public void endpointActivation(MessageEndpointFactory paramMessageEndpointFactory, ActivationSpec paramActivationSpec)
/*    */     throws NotSupportedException
/*    */   {}
/*    */   
/*    */   public void endpointDeactivation(MessageEndpointFactory paramMessageEndpointFactory, ActivationSpec paramActivationSpec) {}
/*    */   
/*    */   public XAResource[] getXAResources(ActivationSpec[] paramArrayOfActivationSpec)
/*    */     throws ResourceException
/*    */   {
/* 81 */     return new XAResource[0];
/*    */   }
/*    */   
/* 86 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*    */   public static final boolean TRACE = false;
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/connector/OracleResourceAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */