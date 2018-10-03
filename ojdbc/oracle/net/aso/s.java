package oracle.net.aso;
import java.io.IOException;
public final class s
  extends IOException
{
  private int a;
  
  public s(int paramInt)
  {
    this.a = paramInt;
  }
  
  public final String getMessage()
  {
    return Integer.toString(this.a);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/aso/s.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */