package oracle.sql.converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import oracle.sql.ConverterArchive;
public abstract class CharacterConverterJDBC
  implements JdbcCharacterConverters, Serializable
{
  static final long serialVersionUID = 5948085171100875165L;
  static final String CONVERTERNAMEPREFIX = "converter_xcharset/lx2";
  static final String CONVERTERIDPREFIX = "0000";
  static final int HIBYTEMASK = 65280;
  static final int LOWBYTEMASK = 255;
  static final int STORE_INCREMENT = 10;
  static final int INVALID_ORA_CHAR = -1;
  static final int FIRSTBSHIFT = 24;
  static final int SECONDBSHIFT = 16;
  static final int THIRDBSHIFT = 8;
  static final int UB2MASK = 65535;
  static final int UB4MASK = 65535;
/*  74 */   static final HashMap m_converterStore = new HashMap();
  
  public int m_groupId;
  
  public int m_oracleId;
  
/*  83 */   public int[][] extraUnicodeToOracleMapping = (int[][])null;
  
  public int getGroupId()
  {
/*  94 */     return this.m_groupId;
  }
  
  public int getOracleId()
  {
/* 104 */     return this.m_oracleId;
  }
  
  public char[] getLeadingCodes()
  {
/* 109 */     return null;
  }
  
  public static JdbcCharacterConverters getInstance(int paramInt)
  {
/* 124 */     CharacterConverterJDBC localCharacterConverterJDBC = null;
/* 125 */     int i = 0;
/* 126 */     int j = 0;
/* 127 */     String str1 = Integer.toHexString(paramInt);
    
/* 129 */     synchronized (m_converterStore)
    {
/* 131 */       localCharacterConverterJDBC = (CharacterConverterJDBC)m_converterStore.get(str1);
      
/* 133 */       if (localCharacterConverterJDBC != null)
      {
/* 135 */         return localCharacterConverterJDBC;
      }
      
/* 139 */       String str2 = "converter_xcharset/lx2" + "0000".substring(0, 4 - str1.length()) + str1;
      
/* 142 */       ConverterArchive localConverterArchive = new ConverterArchive();
      
/* 144 */       localCharacterConverterJDBC = (CharacterConverterJDBC)localConverterArchive.readObj(str2 + ".glb");
      
/* 147 */       if (localCharacterConverterJDBC == null)
      {
/* 149 */         return null;
      }
      
/* 152 */       localCharacterConverterJDBC.buildUnicodeToOracleMapping();
/* 153 */       m_converterStore.put(str1, localCharacterConverterJDBC);
      
/* 155 */       return localCharacterConverterJDBC;
    }
  }
  
  protected void storeMappingRange(int paramInt, Hashtable paramHashtable1, Hashtable paramHashtable2)
  {
/* 170 */     int i = paramInt >> 24 & 0xFF;
/* 171 */     int j = paramInt >> 16 & 0xFF;
/* 172 */     int k = paramInt >> 8 & 0xFF;
/* 173 */     int m = paramInt & 0xFF;
/* 174 */     Integer localInteger1 = Integer.valueOf(i);
/* 175 */     Integer localInteger2 = Integer.valueOf(paramInt >> 16 & 0xFFFF);
/* 176 */     Integer localInteger3 = Integer.valueOf(paramInt >> 8 & 0xFFFFFF);
    
/* 180 */     if (paramInt >>> 26 == 54)
    {
/* 184 */       arrayOfChar = (char[])paramHashtable1.get(localInteger1);
      
/* 186 */       if (arrayOfChar == null)
      {
/* 188 */         arrayOfChar = new char[] { 'ÿ', '\000' };
      }
      
/* 194 */       if ((arrayOfChar[0] == 'ÿ') && (arrayOfChar[1] == 0))
      {
/* 196 */         arrayOfChar[0] = ((char)j);
/* 197 */         arrayOfChar[1] = ((char)j);
      }
      else
      {
/* 201 */         if (j < (arrayOfChar[0] & 0xFFFF))
        {
/* 203 */           arrayOfChar[0] = ((char)j);
        }
        
/* 206 */         if (j > (arrayOfChar[0] & 0xFFFF))
        {
/* 208 */           arrayOfChar[1] = ((char)j);
        }
      }
      
/* 212 */       paramHashtable1.put(localInteger1, arrayOfChar);
      
/* 215 */       arrayOfChar = (char[])paramHashtable1.get(localInteger2);
      
/* 217 */       if (arrayOfChar == null)
      {
/* 219 */         arrayOfChar = new char[] { 'ÿ', '\000' };
      }
      
/* 225 */       if ((arrayOfChar[0] == 'ÿ') && (arrayOfChar[1] == 0))
      {
/* 227 */         arrayOfChar[0] = ((char)k);
/* 228 */         arrayOfChar[1] = ((char)k);
      }
      else
      {
/* 232 */         if (k < (arrayOfChar[0] & 0xFFFF))
        {
/* 234 */           arrayOfChar[0] = ((char)k);
        }
        
/* 237 */         if (k > (arrayOfChar[0] & 0xFFFF))
        {
/* 239 */           arrayOfChar[1] = ((char)k);
        }
      }
      
/* 243 */       paramHashtable1.put(localInteger2, arrayOfChar);
    }
    
/* 247 */     char[] arrayOfChar = (char[])paramHashtable2.get(localInteger3);
    
/* 249 */     if (arrayOfChar == null)
    {
/* 251 */       arrayOfChar = new char[] { 'ÿ', '\000' };
    }
    
/* 257 */     if ((arrayOfChar[0] == 'ÿ') && (arrayOfChar[1] == 0))
    {
/* 259 */       arrayOfChar[0] = ((char)m);
/* 260 */       arrayOfChar[1] = ((char)m);
    }
    else
    {
/* 264 */       if (m < (arrayOfChar[0] & 0xFFFF))
      {
/* 266 */         arrayOfChar[0] = ((char)m);
      }
      
/* 269 */       if (m > (arrayOfChar[0] & 0xFFFF))
      {
/* 271 */         arrayOfChar[1] = ((char)m);
      }
    }
    
/* 275 */     paramHashtable2.put(localInteger3, arrayOfChar);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/CharacterConverterJDBC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */