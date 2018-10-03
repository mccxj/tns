package oracle.jdbc.dcn;
import java.util.EnumSet;
public abstract interface TableChangeDescription
{
  public abstract EnumSet<TableOperation> getTableOperations();
  
  public abstract String getTableName();
  
  public abstract int getObjectNumber();
  
  public abstract RowChangeDescription[] getRowChangeDescription();
  
  public static enum TableOperation
  {
/*  59 */     ALL_ROWS(1), 
    
/*  63 */     INSERT(2), 
    
/*  67 */     UPDATE(4), 
    
/*  71 */     DELETE(8), 
    
/*  75 */     ALTER(16), 
    
/*  79 */     DROP(32);
    
    private final int code;
    
/*  83 */     private TableOperation(int paramInt) { this.code = paramInt; }
    
    public final int getCode()
    {
/*  90 */       return this.code;
    }
    
    public static final EnumSet<TableOperation> getTableOperations(int paramInt)
    {
/*  97 */       EnumSet localEnumSet = EnumSet.noneOf(TableOperation.class);
/*  98 */       if ((paramInt & ALL_ROWS.getCode()) != 0)
/*  99 */         localEnumSet.add(ALL_ROWS);
/* 100 */       if ((paramInt & INSERT.getCode()) != 0)
/* 101 */         localEnumSet.add(INSERT);
/* 102 */       if ((paramInt & UPDATE.getCode()) != 0)
/* 103 */         localEnumSet.add(UPDATE);
/* 104 */       if ((paramInt & DELETE.getCode()) != 0)
/* 105 */         localEnumSet.add(DELETE);
/* 106 */       if ((paramInt & ALTER.getCode()) != 0)
/* 107 */         localEnumSet.add(ALTER);
/* 108 */       if ((paramInt & DROP.getCode()) != 0)
/* 109 */         localEnumSet.add(DROP);
/* 110 */       return localEnumSet;
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/dcn/TableChangeDescription.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */