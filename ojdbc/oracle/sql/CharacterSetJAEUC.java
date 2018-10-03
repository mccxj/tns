package oracle.sql;
import java.sql.SQLException;
import oracle.sql.converter.JdbcCharacterConverters;
class CharacterSetJAEUC
  extends CharacterSetWithConverter
{
  static final String CHAR_CONV_SUPERCLASS_NAME = "oracle.sql.converter.CharacterConverterJAEUC";
  static final transient short MAX_7BIT = 127;
  static final transient short LEADINGCODE = 143;
  static Class m_charConvSuperclass;
  
  CharacterSetJAEUC(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  82 */     super(paramInt, paramJdbcCharacterConverters);
  }
  
  static CharacterSetJAEUC getInstance(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  94 */     if (paramJdbcCharacterConverters.getGroupId() == 2)
    {
/*  96 */       return new CharacterSetJAEUC(paramInt, paramJdbcCharacterConverters);
    }
    
/* 100 */     return null;
  }
  
  int decode(CharacterWalker paramCharacterWalker)
    throws SQLException
  {
/* 108 */     int i = paramCharacterWalker.bytes[paramCharacterWalker.next] & 0xFF;
    
/* 110 */     paramCharacterWalker.next += 1;
    
/* 112 */     if (i > 127)
    {
/* 114 */       if (i != 143)
      {
/* 118 */         if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
        {
/* 120 */           i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 121 */           paramCharacterWalker.next += 1;
        }
        
      }
      else
      {
/* 128 */         for (int j = 0; j < 2; j++)
        {
/* 130 */           if (paramCharacterWalker.bytes.length > paramCharacterWalker.next)
          {
/* 132 */             i = i << 8 | paramCharacterWalker.bytes[paramCharacterWalker.next];
/* 133 */             paramCharacterWalker.next += 1;
          }
        }
      }
    }
    
/* 139 */     return i;
  }
  
  void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
    throws SQLException
  {
/* 146 */     int i = 0;
/* 147 */     int j = 1;
    
/* 149 */     while (paramInt >> i != 0)
    {
/* 151 */       i = (short)(i + 8);
/* 152 */       j = (short)(j + 1);
    }
    
/* 158 */     need(paramCharacterBuffer, j);
    
/* 160 */     while (i >= 0)
    {
/* 162 */       paramCharacterBuffer.bytes[(paramCharacterBuffer.next++)] = ((byte)(paramInt >> i & 0xFF));
/* 163 */       i = (short)(i - 8);
    }
  }
  
/* 168 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetJAEUC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */