package oracle.net.jdbc.TNSAddress;
public abstract interface SchemaObjectFactoryInterface
{
  public static final int ADDR = 0;
  public static final int ADDR_LIST = 1;
  public static final int DESC = 2;
  public static final int DESC_LIST = 3;
  public static final int ALIAS = 4;
  public static final int SERVICE = 5;
  public static final int DB_SERVICE = 6;
  
  public abstract SchemaObject create(int paramInt);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/jdbc/TNSAddress/SchemaObjectFactoryInterface.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */