package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetLCFixed
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverterLCFixed";
  static final int CHARLENGTH = 4;
  static Class m_charConvSuperclass;
  char[] m_leadingCodes;
  
  CharacterSetLCFixed(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  87 */     super(paramInt, paramJdbcCharacterConverters);
    
/*  90 */     this.m_leadingCodes = paramJdbcCharacterConverters.getLeadingCodes();
  }
  
  static CharacterSetLCFixed getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/* 101 */     if (paramJdbcCharacterConverters.getGroupId() == 3)
    {
/* 103 */       return new CharacterSetLCFixed(paramInt, paramJdbcCharacterConverters);
    }
    
/* 107 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 115 */     if (paramCharacterWalker.bytes.length - paramCharacterWalker.next < 4)
    {
/* 118 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 119 */       localSQLException1.fillInStackTrace();
/* 120 */       throw localSQLException1;
    }
    
/* 125 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)];
    
/* 128 */     for (int j = 0; j < this.m_leadingCodes.length; j++)
    {
/* 130 */       if (i == this.m_leadingCodes[j])
      {
/* 134 */         int k = 0;
        
/* 136 */         for (int m = 0; m < 4; m++)
        {
/* 138 */           k = k << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next++)];
        }
        
/* 141 */         return k;
      }
    }
    
/* 147 */     SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 181, "Leading code invalid");
/* 148 */     localSQLException2.fillInStackTrace();
/* 149 */     throw localSQLException2;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 159 */     int i = paramInt >> 16;
    
/* 161 */     for (int j = 0; j < this.m_leadingCodes.length; j++)
    {
/* 163 */       if (i == this.m_leadingCodes[j])
      {
/* 167 */         need(paramCharacterBuffer, 4);
        
/* 169 */         for (int k = 3; k >= 0; k--)
        {
/* 171 */           paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> 8 * k & 0xFF));
        }
        
/* 174 */         return;
      }
    }
    
/* 180 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 181, "Leading code invalid");
/* 181 */     localSQLException.fillInStackTrace();
/* 182 */     throw localSQLException;
  }
  
/* 187 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetLCFixed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */