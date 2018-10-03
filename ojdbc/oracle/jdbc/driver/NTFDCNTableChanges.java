package oracle.jdbc.driver;
import java.nio.ByteBuffer;
import java.util.EnumSet;
import oracle.jdbc.dcn.RowChangeDescription;
import oracle.jdbc.dcn.RowChangeDescription.RowOperation;
import oracle.jdbc.dcn.TableChangeDescription;
import oracle.jdbc.dcn.TableChangeDescription.TableOperation;
import oracle.sql.CharacterSet;
class NTFDCNTableChanges
  implements TableChangeDescription
{
  final EnumSet<TableChangeDescription.TableOperation> opcode;
  String tableName;
  final int objectNumber;
  final int numberOfRows;
  final RowChangeDescription.RowOperation[] rowOpcode;
  final int[] rowIdLength;
  final byte[][] rowid;
  final CharacterSet charset;
/*  46 */   NTFDCNRowChanges[] rowsDescription = null;
  
  private static final byte OPERATION_ANY = 0;
  
  private static final byte OPERATION_UNKNOWN = 64;
  
  NTFDCNTableChanges(ByteBuffer paramByteBuffer, int paramInt)
  {
/*  54 */     this.charset = CharacterSet.make(paramInt);
/*  55 */     this.opcode = TableChangeDescription.TableOperation.getTableOperations(paramByteBuffer.getInt());
/*  56 */     int i = paramByteBuffer.getShort();
/*  57 */     byte[] arrayOfByte = new byte[i];
/*  58 */     paramByteBuffer.get(arrayOfByte, 0, i);
/*  59 */     this.tableName = this.charset.toStringWithReplacement(arrayOfByte, 0, i);
    
/*  62 */     this.objectNumber = paramByteBuffer.getInt();
/*  63 */     if (!this.opcode.contains(TableChangeDescription.TableOperation.ALL_ROWS))
    {
/*  65 */       this.numberOfRows = paramByteBuffer.getShort();
/*  66 */       this.rowOpcode = new RowChangeDescription.RowOperation[this.numberOfRows];
/*  67 */       this.rowIdLength = new int[this.numberOfRows];
/*  68 */       this.rowid = new byte[this.numberOfRows][];
/*  69 */       for (int j = 0; j < this.numberOfRows; j++)
      {
/*  71 */         this.rowOpcode[j] = RowChangeDescription.RowOperation.getRowOperation(paramByteBuffer.getInt());
/*  72 */         this.rowIdLength[j] = paramByteBuffer.getShort();
/*  73 */         this.rowid[j] = new byte[this.rowIdLength[j]];
/*  74 */         paramByteBuffer.get(this.rowid[j], 0, this.rowIdLength[j]);
      }
    }
    else
    {
/*  79 */       this.numberOfRows = 0;
/*  80 */       this.rowid = ((byte[][])null);
/*  81 */       this.rowOpcode = null;
/*  82 */       this.rowIdLength = null;
    }
  }
  
  public String getTableName()
  {
/*  90 */     return this.tableName;
  }
  
  public int getObjectNumber()
  {
/*  96 */     return this.objectNumber;
  }
  
  public RowChangeDescription[] getRowChangeDescription()
  {
/* 102 */     if (this.rowsDescription == null)
    {
/* 104 */       synchronized (this)
      {
/* 106 */         if (this.rowsDescription == null)
        {
/* 108 */           this.rowsDescription = new NTFDCNRowChanges[this.numberOfRows];
/* 109 */           for (int i = 0; i < this.rowsDescription.length; i++)
/* 110 */             this.rowsDescription[i] = new NTFDCNRowChanges(this.rowOpcode[i], this.rowIdLength[i], this.rowid[i]);
        }
      }
    }
/* 114 */     return this.rowsDescription;
  }
  
  public EnumSet<TableChangeDescription.TableOperation> getTableOperations()
  {
/* 120 */     return this.opcode;
  }
  
  public String toString()
  {
/* 131 */     StringBuffer localStringBuffer = new StringBuffer();
/* 132 */     localStringBuffer.append("    operation=" + getTableOperations() + ", tableName=" + this.tableName + ", objectNumber=" + this.objectNumber + "\n");
/* 133 */     RowChangeDescription[] arrayOfRowChangeDescription = getRowChangeDescription();
/* 134 */     if ((arrayOfRowChangeDescription != null) && (arrayOfRowChangeDescription.length > 0))
    {
/* 136 */       localStringBuffer.append("    Row Change Description (length=" + arrayOfRowChangeDescription.length + "):\n");
/* 137 */       for (int i = 0; i < arrayOfRowChangeDescription.length; i++)
/* 138 */         localStringBuffer.append(arrayOfRowChangeDescription[i].toString());
    }
/* 140 */     return localStringBuffer.toString();
  }
  
/* 144 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFDCNTableChanges.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */