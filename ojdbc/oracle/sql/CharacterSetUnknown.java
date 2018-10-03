package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
class CharacterSetUnknown
  extends CharacterSet
  implements CharacterRepConstants
{
  CharacterSetUnknown(int paramInt)
  {
/* 154 */     super(paramInt);
    
/* 157 */     this.rep = (1024 + paramInt);
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/* 164 */     return paramCharacterSet.getOracleId() != getOracleId();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/* 171 */     return paramCharacterSet.getOracleId() == getOracleId();
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 178 */     return "???";
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 186 */     failCharsetUnknown(this);
    
/* 188 */     return null;
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 195 */     failCharsetUnknown(this);
    
/* 197 */     return null;
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 204 */     return new byte[0];
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 212 */     if (paramCharacterSet.getOracleId() == getOracleId())
    {
/* 214 */       return useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    
/* 218 */     failCharsetUnknown(this);
    
/* 221 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 228 */     failCharsetUnknown(this);
    
/* 230 */     return 0;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 237 */     failCharsetUnknown(this);
  }
  
  static void failCharsetUnknown(CharacterSet paramCharacterSet)
    throws SQLException
  {
/* 245 */     SQLException localSQLException = DatabaseError.createSqlException(null, 56, paramCharacterSet);
/* 246 */     localSQLException.fillInStackTrace();
/* 247 */     throw localSQLException;
  }
  
  public boolean isUnknown()
  {
/* 254 */     return true;
  }
  
/* 259 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetUnknown.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */