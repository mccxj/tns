package oracle.net.resolver;
import java.util.Vector;
import oracle.net.jdbc.TNSAddress.AddressList;
import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
import oracle.net.nt.ConnOption;
import oracle.net.nt.ConnStrategy;
public class NavAddressList
  extends AddressList
  implements NavSchemaObject
{
  private Vector activeChildren;
  private int sBuflength;
  
  public NavAddressList(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
  {
/*  57 */     super(paramSchemaObjectFactoryInterface);
/*  58 */     this.activeChildren = new Vector(1, 10);
  }
  
  public void navigate(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
  {
/*  69 */     navigate2(paramConnStrategy, paramStringBuffer, 0);
  }
  
  private void navigate2(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer, int paramInt)
  {
/*  74 */     int i = 0;
    
/*  76 */     paramInt++;
/*  77 */     this.sBuflength = paramStringBuffer.length();
/*  78 */     paramStringBuffer.append("(ADDRESS_LIST=");
/*  79 */     int j; if (this.sourceRoute)
    {
/*  81 */       this.activeChildren = this.children;
/*  82 */       ((NavSchemaObject)this.activeChildren.elementAt(0)).navigate(paramConnStrategy, paramStringBuffer);
      
/*  84 */       for (j = 1; j < this.activeChildren.size(); j++) {
/*  85 */         ((NavSchemaObject)this.activeChildren.elementAt(j)).addToString(paramConnStrategy);
      }
    }
    else {
/*  89 */       this.activeChildren = NavDescriptionList.setActiveChildren(this.children, this.failover, this.loadBalance);
      
/*  92 */       for (j = 0; j < this.activeChildren.size(); j++)
      {
/*  94 */         if (getChildrenType(j) == 1)
        {
/*  96 */           i = 1;
/*  97 */           ((NavAddressList)this.activeChildren.elementAt(j)).navigate2(paramConnStrategy, paramStringBuffer, paramInt);
        }
        else
        {
/* 101 */           ((NavSchemaObject)this.activeChildren.elementAt(j)).navigate(paramConnStrategy, paramStringBuffer);
        }
      }
    }
    
/* 106 */     paramInt--;
    
/* 108 */     if (((paramInt != 0) || (i == 0)) && (!this.sourceRoute))
    {
/* 111 */       closeNVPair(paramConnStrategy, false);
    }
    else
    {
/* 117 */       closeNVPair(paramConnStrategy, true);
    }
/* 119 */     paramStringBuffer.setLength(this.sBuflength);
  }
  
  public void addToString(ConnStrategy paramConnStrategy)
  {
/* 129 */     String str = toString();
    
/* 131 */     for (int i = paramConnStrategy.cOpts.size() - 1; 
/* 132 */         (i >= 0) && (!((ConnOption)paramConnStrategy.cOpts.elementAt(i)).done); i--)
    {
/* 134 */       ((ConnOption)paramConnStrategy.cOpts.elementAt(i)).conn_data.append(str);
    }
  }
  
  public String toString()
  {
/* 144 */     String str = "";
/* 145 */     str = str + "(ADDRESS_LIST=";
    
/* 147 */     for (int i = 0; i < this.children.size(); i++) {
/* 148 */       str = str + ((NavSchemaObject)this.children.elementAt(i)).toString();
    }
/* 150 */     if (this.sourceRoute) str = str + "(SOURCE_ROUTE=yes)(HOP_COUNT=0)";
/* 151 */     if (this.loadBalance) str = str + "(LOAD_BALANCE=yes)";
/* 152 */     if (!this.failover) { str = str + "(FAILOVER=false)";
    }
/* 154 */     str = str + ")";
    
/* 156 */     return str;
  }
  
  public int getChildrenSize()
  {
/* 164 */     return this.children.size();
  }
  
  public int getChildrenType(int paramInt)
  {
/* 172 */     return ((NavSchemaObject)this.children.elementAt(paramInt)).isA();
  }
  
  public NavAddress getChild(int paramInt)
  {
/* 180 */     return (NavAddress)this.children.elementAt(paramInt);
  }
  
  private void closeNVPair(ConnStrategy paramConnStrategy, boolean paramBoolean)
  {
/* 189 */     for (int i = paramConnStrategy.cOpts.size() - 1; 
/* 190 */         (i >= 0) && (!((ConnOption)paramConnStrategy.cOpts.elementAt(i)).done); i--)
    {
/* 193 */       if ((!paramBoolean) && (paramConnStrategy.cOpts.size() - 1 - i >= getChildrenSize())) {
        break;
      }
/* 196 */       if (this.sourceRoute)
      {
/* 198 */         ((ConnOption)paramConnStrategy.cOpts.elementAt(i)).conn_data.append("(SOURCE_ROUTE=yes)");
/* 199 */         ((ConnOption)paramConnStrategy.cOpts.elementAt(i)).conn_data.append("(HOP_COUNT=0)");
      }
      
/* 202 */       ((ConnOption)paramConnStrategy.cOpts.elementAt(i)).conn_data.append(")");
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavAddressList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */