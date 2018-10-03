package oracle.jdbc.internal;
public abstract interface ClientDataSupport
{
  public abstract Object getClientData(Object paramObject);
  
  public abstract Object setClientData(Object paramObject1, Object paramObject2);
  
  public abstract Object removeClientData(Object paramObject);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/internal/ClientDataSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */