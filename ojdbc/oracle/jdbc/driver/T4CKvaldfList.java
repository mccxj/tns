package oracle.jdbc.driver;
import java.sql.SQLException;
class T4CKvaldfList
{
  static final int INTIAL_CAPACITY = 30;
  private int capacity;
  private int offset;
  private byte[][] keys;
  private byte[][] values;
  private byte[] flags;
  DBConversion conv;
  
  T4CKvaldfList(DBConversion paramDBConversion)
  {
/*  56 */     this.conv = paramDBConversion;
/*  57 */     initializeList();
  }
  
  void initializeList()
  {
/*  64 */     this.capacity = 30;
/*  65 */     this.offset = 0;
/*  66 */     this.keys = new byte[this.capacity][];
/*  67 */     this.values = new byte[this.capacity][];
/*  68 */     this.flags = new byte[this.capacity];
  }
  
  void add(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte paramByte)
  {
/*  75 */     if (this.offset == this.capacity)
    {
/*  77 */       byte[][] arrayOfByte1 = new byte[this.capacity * 2][];
/*  78 */       byte[][] arrayOfByte2 = new byte[this.capacity * 2][];
/*  79 */       byte[] arrayOfByte = new byte[this.capacity * 2];
/*  80 */       System.arraycopy(this.keys, 0, arrayOfByte1, 0, this.capacity);
/*  81 */       System.arraycopy(this.values, 0, arrayOfByte2, 0, this.capacity);
/*  82 */       System.arraycopy(this.flags, 0, arrayOfByte, 0, this.capacity);
/*  83 */       this.keys = arrayOfByte1;
/*  84 */       this.values = arrayOfByte2;
/*  85 */       this.flags = arrayOfByte;
/*  86 */       this.capacity *= 2;
    }
/*  88 */     this.keys[this.offset] = paramArrayOfByte1;
/*  89 */     this.values[this.offset] = paramArrayOfByte2;
/*  90 */     this.flags[(this.offset++)] = paramByte;
  }
  
  void add(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
/*  97 */     add(paramArrayOfByte1, paramArrayOfByte2, (byte)0);
  }
  
  void add(String paramString, byte[] paramArrayOfByte)
    throws SQLException
  {
/* 104 */     add(this.conv.StringToCharBytes(paramString), paramArrayOfByte, (byte)0);
  }
  
  void add(String paramString, byte[] paramArrayOfByte, byte paramByte)
    throws SQLException
  {
/* 110 */     add(this.conv.StringToCharBytes(paramString), paramArrayOfByte, paramByte);
  }
  
  int size()
  {
/* 117 */     return this.offset;
  }
  
  byte[][] getKeys()
  {
/* 123 */     return this.keys;
  }
  
  byte[][] getValues()
  {
/* 129 */     return this.values;
  }
  
  byte[] getFlags()
  {
/* 135 */     return this.flags;
  }
  
/* 140 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CKvaldfList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */