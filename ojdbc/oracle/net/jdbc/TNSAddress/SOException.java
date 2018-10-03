package oracle.net.jdbc.TNSAddress;
public class SOException
  extends Throwable
{
  public String error = null;
  
  public SOException() {}
  
  public SOException(String paramString)
  {
    super(paramString);
    this.error = paramString;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jdbc/TNSAddress/SOException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */