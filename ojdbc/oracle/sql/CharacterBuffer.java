package oracle.sql;
import java.sql.SQLException;
public final class CharacterBuffer
{
  CharacterSet charSet;
  byte[] bytes;
  int next;
  
  public CharacterBuffer(CharacterSet paramCharacterSet)
  {
/*  91 */     this.charSet = paramCharacterSet;
    
/*  93 */     this.next = 0;
    
/*  96 */     this.bytes = new byte[32];
  }
  
  public void append(int paramInt)
    throws SQLException
  {
/* 109 */     this.charSet.encode(this, paramInt);
  }
  
  public byte[] getBytes()
  {
/* 121 */     return CharacterSet.useOrCopy(this.bytes, 0, this.next);
  }
  
/* 125 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */