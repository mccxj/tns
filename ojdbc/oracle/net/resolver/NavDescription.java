package oracle.net.resolver;
import java.util.Vector;
import oracle.net.jdbc.TNSAddress.Description;
import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
import oracle.net.nt.ConnOption;
import oracle.net.nt.ConnStrategy;
public class NavDescription
  extends Description
  implements NavSchemaObject
{
  private Vector activeChildren;
  private int descProcessed;
  private boolean done;
  
  public NavDescription(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
  {
/*  68 */     super(paramSchemaObjectFactoryInterface);
/*  69 */     this.activeChildren = new Vector(1, 10);
/*  70 */     this.done = false;
  }
  
  public void navigate(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
  {
/*  81 */     paramStringBuffer.setLength(0);
/*  82 */     paramStringBuffer.append("(DESCRIPTION=");
    try
    {
/*  85 */       if (this.SDU != null)
/*  86 */         paramConnStrategy.sdu = Integer.parseInt(this.SDU);
/*  87 */       if (this.TDU != null) {
/*  88 */         paramConnStrategy.tdu = Integer.parseInt(this.TDU);
      }
/*  90 */       if (this.connectTimeout != null)
/*  91 */         paramConnStrategy.connectTimeout = (Integer.parseInt(this.connectTimeout) * 1000);
/*  92 */       if (this.retryCount != null) {
/*  93 */         paramConnStrategy.retryCount = Integer.parseInt(this.retryCount);
      }
    }
    catch (Exception localException) {}
    
/* 100 */     paramConnStrategy.addSocketOptions(this.keepAlive);
    
    int i;
    
/* 105 */     if (this.sourceRoute)
    {
/* 108 */       if (!backwardCompatibilityCase(this.children, paramConnStrategy))
      {
/* 110 */         this.activeChildren = this.children;
        
/* 112 */         ((NavSchemaObject)this.activeChildren.elementAt(0)).navigate(paramConnStrategy, paramStringBuffer);
        
/* 114 */         for (i = 1; i < this.activeChildren.size(); i++) {
/* 115 */           ((NavSchemaObject)this.activeChildren.elementAt(i)).addToString(paramConnStrategy);
        }
      }
      else {
/* 119 */         setConnectionOption(paramConnStrategy, paramStringBuffer);
      }
      
/* 123 */       closeNVPair(paramConnStrategy);
    }
    else
    {
/* 129 */       this.activeChildren = NavDescriptionList.setActiveChildren(this.children, this.failover, this.loadBalance);
      
/* 133 */       for (i = 0; i < this.activeChildren.size(); i++)
      {
/* 135 */         ((NavSchemaObject)this.activeChildren.elementAt(i)).navigate(paramConnStrategy, paramStringBuffer);
      }
/* 137 */       closeNVPair(paramConnStrategy);
    }
/* 139 */     this.done = true;
  }
  
  public void closeNVPair(ConnStrategy paramConnStrategy)
  {
/* 148 */     for (int i = paramConnStrategy.cOpts.size() - 1; 
/* 149 */         (i >= 0) && (!((ConnOption)paramConnStrategy.cOpts.elementAt(i)).done); i--)
    {
/* 151 */       ConnOption localConnOption = (ConnOption)paramConnStrategy.cOpts.elementAt(i);
      
/* 153 */       if (this.sourceRoute)
      {
/* 155 */         localConnOption.conn_data.append("(SOURCE_ROUTE=yes)");
      }
      
/* 160 */       if (this.connectData == null) {
/* 161 */         this.connectData = "(SERVICE_NAME=)";
      }
/* 163 */       localConnOption.conn_data.append("(CONNECT_DATA=");
/* 164 */       localConnOption.conn_data.append("(CID=(PROGRAM=");
/* 165 */       localConnOption.conn_data.append(paramConnStrategy.getProgramName());
/* 166 */       localConnOption.conn_data.append(")(HOST=__jdbc__)(USER=");
/* 167 */       localConnOption.conn_data.append(paramConnStrategy.getOSUsername());
/* 168 */       localConnOption.conn_data.append("))");
/* 169 */       localConnOption.conn_data.append(this.connectData);
/* 170 */       localConnOption.conn_data.append(")");
      
/* 172 */       if (this.SID != null)
/* 173 */         localConnOption.sid = this.SID;
/* 174 */       if (this.serviceName != null)
/* 175 */         localConnOption.service_name = this.serviceName;
/* 176 */       if (this.instanceName != null)
/* 177 */         localConnOption.instance_name = this.instanceName;
/* 178 */       if (this.sslServerCertDN != null) {
/* 179 */         localConnOption.sslServerCertDN = this.sslServerCertDN;
      }
      
/* 183 */       localConnOption.conn_data.append(")");
/* 184 */       localConnOption.done = true;
    }
  }
  
  public void addToString(ConnStrategy paramConnStrategy) {}
  
  private boolean backwardCompatibilityCase(Vector paramVector, ConnStrategy paramConnStrategy)
  {
/* 200 */     if ((paramVector.size() == 1) && (((NavSchemaObject)paramVector.elementAt(0)).isA() == 1) && (!((NavAddressList)paramVector.elementAt(0)).sourceRoute))
    {
/* 204 */       NavAddressList localNavAddressList = (NavAddressList)paramVector.elementAt(0);
/* 205 */       int i = localNavAddressList.getChildrenSize();
      
/* 207 */       if (i == 0) {
/* 208 */         return false;
      }
      
/* 213 */       for (int j = 0; j < i; j++)
      {
/* 217 */         if (localNavAddressList.getChildrenType(j) != 0)
/* 218 */           return false;
      }
/* 220 */       return true;
    }
/* 222 */     return false;
  }
  
  private void setConnectionOption(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
  {
/* 236 */     paramStringBuffer.append("(ADDRESS_LIST=");
    
/* 239 */     NavAddressList localNavAddressList = (NavAddressList)this.children.elementAt(0);
/* 240 */     NavAddress localNavAddress = localNavAddressList.getChild(0);
/* 241 */     int i = localNavAddressList.getChildrenSize();
    
/* 244 */     localNavAddress.navigate(paramConnStrategy, paramStringBuffer);
    
/* 247 */     for (int j = 1; j < i; j++)
    {
/* 249 */       localNavAddressList.getChild(j).addToString(paramConnStrategy);
    }
    
/* 252 */     ((ConnOption)paramConnStrategy.cOpts.elementAt(0)).conn_data.append(")");
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */