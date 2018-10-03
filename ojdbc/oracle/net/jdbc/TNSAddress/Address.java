package oracle.net.jdbc.TNSAddress;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NVFactory;
import oracle.net.jdbc.nl.NVNavigator;
import oracle.net.jdbc.nl.NVPair;
public class Address
  implements SchemaObject
{
  public String addr;
  public String prot;
  protected SchemaObjectFactoryInterface f = null;
  
  public Address(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
  {
    this.f = paramSchemaObjectFactoryInterface;
  }
  
  public int isA()
  {
    return 0;
  }
  
  public String isA_String()
  {
    return "ADDRESS";
  }
  
  public void initFromString(String paramString)
    throws NLException, SOException
  {
    NVPair localNVPair = new NVFactory().createNVPair(paramString);
    initFromNVPair(localNVPair);
  }
  
  public void initFromNVPair(NVPair paramNVPair)
    throws SOException
  {
    init();
    if ((paramNVPair == null) || (!paramNVPair.getName().equalsIgnoreCase("address"))) {
      throw new SOException();
    }
    NVNavigator localNVNavigator = new NVNavigator();
    NVPair localNVPair = localNVNavigator.findNVPair(paramNVPair, "PROTOCOL");
    if (localNVPair == null) {
      throw new SOException();
    }
    this.prot = localNVPair.getAtom();
    if (this.addr == null) {
      this.addr = paramNVPair.toString();
    }
  }
  
  public String toString()
  {
    return this.addr;
  }
  
  public String getProtocol()
  {
    return this.prot;
  }
  
  protected void init()
  {
    this.addr = null;
    this.prot = null;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jdbc/TNSAddress/Address.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */