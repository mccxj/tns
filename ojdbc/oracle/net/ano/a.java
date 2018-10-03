package oracle.net.ano;
import java.lang.reflect.Method;
import java.security.PrivilegedExceptionAction;
final class a
  implements PrivilegedExceptionAction
{
  a(Method paramMethod) {}
  
  public final Object run()
    throws Exception
  {
    if (!this.a.isAccessible()) {
      this.a.setAccessible(true);
    }
    AuthenticationService.a(this.a);
    return null;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ano/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */