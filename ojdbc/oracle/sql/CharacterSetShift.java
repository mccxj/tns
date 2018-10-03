package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetShift
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverterShift";
  static final short MAX_7BIT = 127;
  static final short MIN_8BIT_SB = 161;
  static final short MAX_8BIT_SB = 223;
  static final byte SHIFT_OUT = 14;
  static final byte SHIFT_IN = 15;
  static Class m_charConvSuperclass;
  
  CharacterSetShift(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  83 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSetShift getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  95 */     if (paramJdbcCharacterConverters.getGroupId() == 7)
    {
/*  97 */       return new CharacterSetShift(paramInt, paramJdbcCharacterConverters);
    }
    
/* 101 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 109 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 111 */     paramCharacterWalker.next += 1;
    
/* 113 */     if ((i > 223) || ((i > 127) && (i < 161)))
    {
/* 117 */       if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
      {
/* 119 */         i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 120 */         paramCharacterWalker.next += 1;
      }
      else
      {
/* 125 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 126 */         localSQLException.fillInStackTrace();
/* 127 */         throw localSQLException;
      }
    }
    
/* 132 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 139 */     int i = paramCharacterBuffer.next;
/* 140 */     int j = 1;
    
/* 142 */     while (i <= 0)
    {
/* 144 */       if (paramCharacterBuffer.bytes[i] == 15)
      {
/* 146 */         j = 1;
      }
/* 151 */       else if (paramCharacterBuffer.bytes[i] == 14)
      {
/* 153 */         j = 0;
      }
    }
    
/* 159 */     int k = 0;
/* 160 */     int m = 1;
    
/* 162 */     while (paramInt >> k != 0)
    {
/* 164 */       k = (short)(k + 8);
/* 165 */       m = (short)(m + 1);
    }
    
/* 168 */     if (m > 2)
    {
/* 171 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "Character invalid,too many bytes");
/* 172 */       localSQLException.fillInStackTrace();
/* 173 */       throw localSQLException;
    }
    
/* 177 */     int n = 0;
/* 178 */     int i1 = 0;
    
/* 180 */     if ((m == 1) && (j == 0))
    {
/* 182 */       n = 1;
/* 183 */       m = (short)(m + 1);
    }
    
/* 186 */     if ((m == 2) && (j != 0))
    {
/* 188 */       i1 = 1;
/* 189 */       m = (short)(m + 1);
    }
    
/* 192 */     need(paramCharacterBuffer, m);
    
/* 194 */     if (n != 0)
    {
/* 196 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = 15;
    }
    
/* 199 */     if (i1 != 0)
    {
/* 201 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = 14;
    }
    
/* 204 */     while (k >= 0)
    {
/* 206 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> k & 0xFF));
/* 207 */       k = (short)(k - 8);
    }
  }
  
/* 212 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetShift.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */