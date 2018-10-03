package oracle.sql;
import java.sql.SQLException;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSet1Byte
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverter1Byte";
  static Class m_charConvSuperclass;
  
  CharacterSet1Byte(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  81 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSet1Byte getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  93 */     if (paramJdbcCharacterConverters.getGroupId() == 0)
    {
/*  95 */       return new CharacterSet1Byte(paramInt, paramJdbcCharacterConverters);
    }
    
/*  99 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 107 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 109 */     paramCharacterWalker.next += 1;
    
/* 111 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 118 */     need(paramCharacterBuffer, 1);
    
/* 120 */     if (paramInt < 256)
    {
/* 122 */       paramCharacterBuffer.bytes[paramCharacterBuffer.next] = ((byte)paramInt);
/* 123 */       paramCharacterBuffer.next += 1;
    }
  }
  
  public int toCharWithReplacement(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
    throws SQLException
  {
/* 134 */     return this.m_converter.toUnicodeChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramInt3);
  }
  
/* 139 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSet1Byte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */