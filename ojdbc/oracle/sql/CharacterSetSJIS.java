package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetSJIS
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverterSJIS";
  static final short MAX_7BIT = 127;
  static final short MIN_8BIT_SB = 161;
  static final short MAX_8BIT_SB = 223;
  static Class m_charConvSuperclass;
  
  CharacterSetSJIS(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  84 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSetSJIS getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  96 */     if (paramJdbcCharacterConverters.getGroupId() == 4)
    {
/*  98 */       return new CharacterSetSJIS(paramInt, paramJdbcCharacterConverters);
    }
    
/* 102 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 110 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 112 */     paramCharacterWalker.next += 1;
    
/* 114 */     if ((i > 223) || ((i > 127) && (i < 161)))
    {
/* 118 */       if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
      {
/* 120 */         i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 121 */         paramCharacterWalker.next += 1;
      }
      else
      {
/* 126 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 127 */         localSQLException.fillInStackTrace();
/* 128 */         throw localSQLException;
      }
    }
    
/* 133 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 140 */     int i = 0;
/* 141 */     int j = 1;
    
/* 143 */     while (paramInt >> i != 0)
    {
/* 145 */       i = (short)(i + 8);
/* 146 */       j = (short)(j + 1);
    }
    
/* 152 */     need(paramCharacterBuffer, j);
    
/* 154 */     while (i >= 0)
    {
/* 156 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> i & 0xFF));
/* 157 */       i = (short)(i - 8);
    }
  }
  
/* 162 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetSJIS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */