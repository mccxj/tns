package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetGB18030
  extends CharacterSetWithConverter
{
  static final int MAX_7BIT = 127;
  static Class m_charConvSuperclass;
  
  CharacterSetGB18030(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  80 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSetGB18030 getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  92 */     if (paramJdbcCharacterConverters.getGroupId() == 9)
    {
/*  94 */       return new CharacterSetGB18030(paramInt, paramJdbcCharacterConverters);
    }
    
/*  98 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 106 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 108 */     if (i > 127)
    {
      SQLException localSQLException;
      
/* 112 */       if (paramCharacterWalker.bytes.length > paramCharacterWalker.next + 1)
      {
/* 114 */         if (((paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF) >= 129) && ((paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF) <= 254) && ((paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)] & 0xFF) >= 48) && ((paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)] & 0xFF) <= 57))
        {
/* 121 */           if (paramCharacterWalker.bytes.length > paramCharacterWalker.next + 3)
          {
/* 123 */             if (((paramCharacterWalker.bytes[(paramCharacterWalker.next + 2)] & 0xFF) >= 129) && ((paramCharacterWalker.bytes[(paramCharacterWalker.next + 2)] & 0xFF) <= 254) && ((paramCharacterWalker.bytes[(paramCharacterWalker.next + 3)] & 0xFF) >= 48) && ((paramCharacterWalker.bytes[(paramCharacterWalker.next + 3)] & 0xFF) <= 57))
            {
/* 128 */               i = (paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF) << 24 | (paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)] & 0xFF) << 16 | (paramCharacterWalker.bytes[(paramCharacterWalker.next + 2)] & 0xFF) << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next + 3)] & 0xFF;
              
/* 132 */               paramCharacterWalker.next += 4;
            }
            else
            {
/* 138 */               i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
/* 139 */               paramCharacterWalker.next += 1;
            }
            
          }
          else
          {
/* 145 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 146 */             localSQLException.fillInStackTrace();
/* 147 */             throw localSQLException;
          }
          
        }
        else
        {
/* 155 */           i = (paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF) << 8 | paramCharacterWalker.bytes[(paramCharacterWalker.next + 1)] & 0xFF;
          
/* 157 */           paramCharacterWalker.next += 2;
        }
        
      }
      else
      {
/* 163 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 182, "destination too small");
/* 164 */         localSQLException.fillInStackTrace();
/* 165 */         throw localSQLException;
      }
    }
    
/* 170 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 177 */     int i = 0;
/* 178 */     int j = 0;
    
/* 180 */     while (paramInt >> i != 0)
    {
/* 182 */       i = (short)(i + 8);
/* 183 */       j = (short)(j + 1);
    }
    
/* 186 */     if (paramInt >> 16 != 0)
    {
/* 188 */       i = 3;
/* 189 */       j = 4;
    }
/* 191 */     else if (paramInt >> 8 != 0)
    {
/* 193 */       i = 1;
/* 194 */       j = 2;
    }
    else
    {
/* 198 */       i = 0;
/* 199 */       j = 1;
    }
    
/* 202 */     need(paramCharacterBuffer, j);
    
/* 204 */     while (i >= 0)
    {
/* 206 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> i & 0xFF));
/* 207 */       i = (short)(i - 8);
    }
  }
  
/* 212 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetGB18030.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */