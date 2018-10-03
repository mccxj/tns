package oracle.jdbc.driver;
public abstract interface DiagnosabilityMXBean
{
  public abstract boolean stateManageable();
  
  public abstract boolean statisticsProvider();
  
  public abstract boolean getLoggingEnabled();
  
  public abstract void setLoggingEnabled(boolean paramBoolean);
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/DiagnosabilityMXBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */