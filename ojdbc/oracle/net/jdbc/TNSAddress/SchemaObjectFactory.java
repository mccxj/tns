package oracle.net.jdbc.TNSAddress;
public class SchemaObjectFactory
  implements SchemaObjectFactoryInterface
{
  public SchemaObject create(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return new Address(this);
    case 1: 
      return new AddressList(this);
    case 2: 
      return new Description(this);
    case 3: 
      return new DescriptionList(this);
    case 4: 
      return new ServiceAlias(this);
    }
    return null;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jdbc/TNSAddress/SchemaObjectFactory.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */