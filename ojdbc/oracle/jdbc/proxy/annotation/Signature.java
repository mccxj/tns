package oracle.jdbc.proxy.annotation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface Signature
{
  String name();
  
  Class[] args();
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/annotation/Signature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */