package oracle.security.o5logon;
import java.io.IOException;
public final class a
  extends IOException
{
  private int a;
  
  public a(int paramInt)
  {
    this.a = paramInt;
  }
  
  public final String getMessage()
  {
    return Integer.toString(this.a);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/security/o5logon/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */