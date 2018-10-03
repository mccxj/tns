package oracle.net.resolver;
import oracle.net.ns.NetException;
public abstract interface NamingAdapterInterface
{
  public static final boolean DEBUG = false;
  
  public abstract String resolve(String paramString)
    throws NetException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NamingAdapterInterface.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */