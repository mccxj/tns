package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetZHTEUC
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverterZHTEUC";
  static final int MAX_7BIT = 127;
  static final int CHARLENGTH = 4;
  static Class m_charConvSuperclass;
  char[] m_leadingCodes;
  
  CharacterSetZHTEUC(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  87 */     super(paramInt, paramJdbcCharacterConverters);
    
/*  90 */     this.m_leadingCodes = paramJdbcCharacterConverters.getLeadingCodes();
  }
  
  static CharacterSetZHTEUC getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/* 101 */     if (paramJdbcCharacterConverters.getGroupId() == 5)
    {
/* 103 */       return new CharacterSetZHTEUC(paramInt, paramJdbcCharacterConverters);
    }
    
/* 107 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 116 */     if (paramCharacterWalker.next + 1 < paramCharacterWalker.bytes.length)
    {
/* 120 */       i = paramCharacterWalker.bytes[paramCharacterWalker.next] << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)];
      
/* 123 */       for (int j = 0; j < this.m_leadingCodes.length; j++)
      {
/* 125 */         if (i == this.m_leadingCodes[j])
        {
/* 129 */           if (paramCharacterWalker.bytes.length - paramCharacterWalker.next < 4)
          {
/* 132 */             SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 133 */             localSQLException2.fillInStackTrace();
/* 134 */             throw localSQLException2;
          }
          
/* 138 */           int k = 0;
          
/* 140 */           for (int m = 0; m < 4; m++)
          {
/* 142 */             k = k << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next++)];
          }
          
/* 145 */           return k;
        }
      }
    }
    
/* 152 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 154 */     paramCharacterWalker.next += 1;
    
/* 156 */     if (i > 127)
    {
/* 160 */       if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
      {
/* 162 */         i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 163 */         paramCharacterWalker.next += 1;
      }
      else
      {
/* 168 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182);
/* 169 */         localSQLException1.fillInStackTrace();
/* 170 */         throw localSQLException1;
      }
    }
    
/* 175 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 184 */     int i = paramInt >> 16;
    
/* 186 */     for (int j = 0; j < this.m_leadingCodes.length; j++)
    {
/* 188 */       if (i == this.m_leadingCodes[j])
      {
/* 192 */         need(paramCharacterBuffer, 4);
        
/* 194 */         for (int k = 0; k < 4; k++)
        {
/* 196 */           paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)paramInt);
/* 197 */           paramInt >>= 8;
        }
        
/* 200 */         return;
      }
    }
    
/* 206 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 181, "Failed to find valid leading code");
/* 207 */     localSQLException.fillInStackTrace();
/* 208 */     throw localSQLException;
  }
  
/* 213 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetZHTEUC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */