package oracle.net.resolver;
import java.util.BitSet;
import java.util.Random;
import java.util.Vector;
import oracle.net.jdbc.TNSAddress.DescriptionList;
import oracle.net.jdbc.TNSAddress.SchemaObjectFactoryInterface;
import oracle.net.nt.ConnStrategy;
public class NavDescriptionList
  extends DescriptionList
  implements NavSchemaObject
{
  private Vector activeChildren;
  private int descProcessed;
  private boolean done;
  
  public NavDescriptionList(SchemaObjectFactoryInterface paramSchemaObjectFactoryInterface)
  {
/*  64 */     super(paramSchemaObjectFactoryInterface);
/*  65 */     this.activeChildren = new Vector(1, 10);
  }
  
  public void navigate(ConnStrategy paramConnStrategy, StringBuffer paramStringBuffer)
  {
/*  76 */     paramStringBuffer.append("(DESCRIPTION_LIST=");
    
/*  79 */     this.activeChildren = setActiveChildren(this.children, this.failover, this.loadBalance);
/*  80 */     while (this.descProcessed < this.activeChildren.size())
    {
/*  82 */       ((NavSchemaObject)this.activeChildren.elementAt(this.descProcessed)).navigate(paramConnStrategy, paramStringBuffer);
      
/*  84 */       this.descProcessed += 1;
    }
  }
  
  public void addToString(ConnStrategy paramConnStrategy) {}
  
  public static Vector setActiveChildren(Vector paramVector, boolean paramBoolean1, boolean paramBoolean2)
  {
/* 104 */     int j = paramVector.size();
/* 105 */     Vector localVector = new Vector(1, 10);
/* 106 */     Random localRandom = new Random();
/* 107 */     BitSet localBitSet = new BitSet(j);
    
    int i;
/* 110 */     if (paramBoolean1)
    {
/* 112 */       if (paramBoolean2)
      {
/* 114 */         for (int k = 0; k < j; k++)
        {
          do
          {
/* 118 */             i = Math.abs(localRandom.nextInt()) % j;
/* 119 */           } while (localBitSet.get(i));
/* 120 */           localBitSet.set(i);
/* 121 */           localVector.addElement(paramVector.elementAt(i));
        }
        
      }
      else {
/* 126 */         localVector = paramVector;
      }
      
    }
/* 131 */     else if (paramBoolean2)
    {
/* 133 */       i = Math.abs(localRandom.nextInt()) % j;
/* 134 */       localVector.addElement(paramVector.elementAt(i));
    }
    else
    {
/* 138 */       localVector.addElement(paramVector.elementAt(0));
    }
    
/* 142 */     return localVector;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/NavDescriptionList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */