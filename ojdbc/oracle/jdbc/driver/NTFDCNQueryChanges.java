package oracle.jdbc.driver;
import java.nio.ByteBuffer;
import oracle.jdbc.dcn.QueryChangeDescription;
import oracle.jdbc.dcn.QueryChangeDescription.QueryChangeEventType;
import oracle.jdbc.dcn.TableChangeDescription;
class NTFDCNQueryChanges
  implements QueryChangeDescription
{
  private final long queryId;
  private final QueryChangeDescription.QueryChangeEventType queryopflags;
  private final int numberOfTables;
  private final NTFDCNTableChanges[] tcdesc;
  
  NTFDCNQueryChanges(ByteBuffer paramByteBuffer, int paramInt)
  {
/*  53 */     long l1 = paramByteBuffer.getInt() & 0xFFFFFFFF;
/*  54 */     long l2 = paramByteBuffer.getInt() & 0xFFFFFFFF;
/*  55 */     this.queryId = (l1 | l2 << 32);
/*  56 */     this.queryopflags = QueryChangeDescription.QueryChangeEventType.getQueryChangeEventType(paramByteBuffer.getInt());
/*  57 */     this.numberOfTables = paramByteBuffer.getShort();
/*  58 */     this.tcdesc = new NTFDCNTableChanges[this.numberOfTables];
/*  59 */     for (int i = 0; i < this.tcdesc.length; i++) {
/*  60 */       this.tcdesc[i] = new NTFDCNTableChanges(paramByteBuffer, paramInt);
    }
  }
  
  public long getQueryId()
  {
/*  67 */     return this.queryId;
  }
  
  public QueryChangeDescription.QueryChangeEventType getQueryChangeEventType()
  {
/*  74 */     return this.queryopflags;
  }
  
  public TableChangeDescription[] getTableChangeDescription()
  {
/*  81 */     return this.tcdesc;
  }
  
  public String toString()
  {
/*  88 */     StringBuffer localStringBuffer = new StringBuffer();
/*  89 */     localStringBuffer.append("  query ID=" + this.queryId + ", query change event type=" + this.queryopflags + "\n");
/*  90 */     TableChangeDescription[] arrayOfTableChangeDescription = getTableChangeDescription();
/*  91 */     if (arrayOfTableChangeDescription != null)
    {
/*  93 */       localStringBuffer.append("  Table Change Description (length=" + arrayOfTableChangeDescription.length + "):");
/*  94 */       for (int i = 0; i < arrayOfTableChangeDescription.length; i++)
/*  95 */         localStringBuffer.append(arrayOfTableChangeDescription[i].toString());
    }
/*  97 */     return localStringBuffer.toString();
  }
  
/* 102 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFDCNQueryChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */