/*    */ package oracle.net.jndi;
/*    */ import javax.net.ssl.SSLContext;
/*    */ public final class TrustManagerSSLSocketFactory
/*    */   extends CustomSSLSocketFactory
/*    */ {
/*    */   private static final boolean DEBUG = false;
/*    */   
/*    */   protected void setDefaultFactory()
/*    */   {
/*    */     try
/*    */     {
/* 59 */       SSLContext localSSLContext = SSLContext.getInstance("SSL");
/* 60 */       localSSLContext.init(null, new javax.net.ssl.TrustManager[] { new TrustManager() }, null);
/*    */       
/* 63 */       setFactory(localSSLContext.getSocketFactory());
/*    */     }
/*    */     catch (Exception localException)
/*    */     {
/* 77 */       super.setDefaultFactory();
/*    */     }
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jndi/TrustManagerSSLSocketFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */