package oracle.jdbc.driver;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanInfo;
import javax.management.StandardMBean;
public class OracleDiagnosabilityMBean
  extends StandardMBean
  implements DiagnosabilityMXBean
{
  OracleDiagnosabilityMBean()
  {
/*  34 */     super(DiagnosabilityMXBean.class, true);
  }
  
  public boolean getLoggingEnabled()
  {
/*  51 */     return OracleLog.isEnabled();
  }
  
  public void setLoggingEnabled(boolean paramBoolean)
  {
/*  67 */     OracleLog.setTrace(paramBoolean);
  }
  
  public boolean stateManageable()
  {
/*  78 */     return false;
  }
  
  public boolean statisticsProvider()
  {
/*  89 */     return false;
  }
  
  protected String getDescription(MBeanInfo paramMBeanInfo)
  {
/*  97 */     return DatabaseError.findMessage("DiagnosabilityMBeanDescription", this);
  }
  
  protected String getDescription(MBeanConstructorInfo paramMBeanConstructorInfo)
  {
/* 106 */     return DatabaseError.findMessage("DiagnosabilityMBeanConstructor()", this);
  }
  
  protected String getDescription(MBeanAttributeInfo paramMBeanAttributeInfo)
  {
/* 114 */     String str = paramMBeanAttributeInfo.getName();
/* 115 */     if (str.equals("LoggingEnabled")) {
/* 116 */       return DatabaseError.findMessage("DiagnosabilityMBeanLoggingEnabledDescription", this);
    }
/* 118 */     if (str.equals("stateManageable")) {
/* 119 */       return DatabaseError.findMessage("DiagnosabilityMBeanStateManageableDescription", this);
    }
/* 121 */     if (str.equals("statisticsProvider")) {
/* 122 */       return DatabaseError.findMessage("DiagnosabilityMBeanStatisticsProviderDescription", this);
    }
    
/* 125 */     Logger.getLogger("oracle.jdbc.driver").log(Level.SEVERE, "Got a request to describe an unexpected  Attribute: " + str);
    
/* 128 */     return super.getDescription(paramMBeanAttributeInfo);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleDiagnosabilityMBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */