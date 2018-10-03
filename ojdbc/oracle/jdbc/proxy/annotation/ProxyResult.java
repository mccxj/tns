package oracle.jdbc.proxy.annotation;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
public @interface ProxyResult
{
  ProxyResultPolicy value() default ProxyResultPolicy.CACHE;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/annotation/ProxyResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */