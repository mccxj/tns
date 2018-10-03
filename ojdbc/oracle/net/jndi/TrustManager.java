/*    */ package oracle.net.jndi;
/*    */ import java.security.KeyStore;
/*    */ import java.security.cert.CertificateException;
/*    */ import java.security.cert.X509Certificate;
/*    */ import javax.net.ssl.TrustManagerFactory;
/*    */ import javax.net.ssl.X509TrustManager;
/*    */ public class TrustManager
/*    */   implements X509TrustManager
/*    */ {
/*    */   X509TrustManager nativeTm;
/*    */   
/*    */   public TrustManager()
/*    */   {
/*    */     try
/*    */     {
/* 52 */       TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
/*    */       
/* 55 */       localTrustManagerFactory.init((KeyStore)null);
/* 56 */       this.nativeTm = ((X509TrustManager)localTrustManagerFactory.getTrustManagers()[0]);
/*    */     } catch (Exception localException) {
/* 58 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */   
/*    */   public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*    */     throws CertificateException
/*    */   {}
/*    */   
/*    */   public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
/*    */     throws CertificateException
/*    */   {}
/*    */   
/*    */   public X509Certificate[] getAcceptedIssuers()
/*    */   {
/* 76 */     return this.nativeTm.getAcceptedIssuers();
/*    */   }
/*    */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jndi/TrustManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */