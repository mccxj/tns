package oracle.sql;
import java.sql.SQLException;
import java.util.NoSuchElementException;
public final class CharacterWalker
{
  CharacterSet charSet;
  byte[] bytes;
  int next;
  int end;
  int shiftstate;
  
  public CharacterWalker(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 110 */     this.charSet = paramCharacterSet;
/* 111 */     this.bytes = paramArrayOfByte;
/* 112 */     this.next = paramInt1;
/* 113 */     this.end = (paramInt1 + paramInt2);
    
/* 115 */     if (this.next < 0)
    {
/* 117 */       this.next = 0;
    }
    
/* 120 */     if (this.end > paramArrayOfByte.length)
    {
/* 122 */       this.end = paramArrayOfByte.length;
    }
  }
  
  public int nextCharacter()
    throws NoSuchElementException
  {
    try
    {
/* 140 */       return this.charSet.decode(this);
    }
    catch (SQLException localSQLException)
    {
/* 144 */       throw new NoSuchElementException(localSQLException.getMessage());
    }
  }
  
  public boolean hasMoreCharacters()
  {
/* 157 */     return this.next < this.end;
  }
  
/* 161 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterWalker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */