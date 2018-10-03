package oracle.net.ano;
import oracle.net.ns.NetOutputStream;
import oracle.net.ns.SessionAtts;
public class AnoNetOutputStream
  extends NetOutputStream
{
  public AnoNetOutputStream(SessionAtts paramSessionAtts)
  {
    super(paramSessionAtts);
    this.daPkt = new CryptoDataPacket(paramSessionAtts);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/ano/AnoNetOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */