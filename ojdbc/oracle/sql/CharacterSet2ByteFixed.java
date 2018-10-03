package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSet2ByteFixed
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverter2ByteFixed";
  static final short MAX_7BIT = 127;
  static final short MIN_8BIT_SB = 161;
  static final short MAX_8BIT_SB = 223;
  static final short CHARLENGTH = 2;
  static Class m_charConvSuperclass;
  
  CharacterSet2ByteFixed(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  83 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSet2ByteFixed getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  95 */     if (paramJdbcCharacterConverters.getGroupId() == 6)
    {
/*  97 */       return new CharacterSet2ByteFixed(paramInt, paramJdbcCharacterConverters);
    }
    
/* 101 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 109 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 111 */     paramCharacterWalker.next += 1;
    
/* 114 */     if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
    {
/* 116 */       i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 117 */       paramCharacterWalker.next += 1;
    }
    else
    {
/* 122 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182);
/* 123 */       localSQLException.fillInStackTrace();
/* 124 */       throw localSQLException;
    }
    
/* 128 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 135 */     need(paramCharacterBuffer, 2);
    
/* 137 */     paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> 8 & 0xFF));
/* 138 */     paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt & 0xFF));
  }
  
/* 143 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSet2ByteFixed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */