package oracle.sql;
class CharacterSetFactoryThin
  extends CharacterSetFactory
{
  public CharacterSet make(int paramInt)
  {
/*  92 */     if (paramInt == -1)
    {
/*  96 */       paramInt = 31;
    }
    
/* 107 */     if (paramInt == 2000)
    {
/* 109 */       return new CharacterSetAL16UTF16(paramInt);
    }
/* 111 */     if ((paramInt == 870) || (paramInt == 871))
    {
/* 113 */       return new CharacterSetUTF(paramInt);
    }
/* 115 */     if (paramInt == 873)
    {
/* 117 */       return new CharacterSetAL32UTF8(paramInt);
    }
/* 119 */     if (paramInt == 872)
    {
/* 121 */       return new CharacterSetUTFE(paramInt);
    }
/* 123 */     if (paramInt == 2002)
    {
/* 125 */       return new CharacterSetAL16UTF16LE(paramInt);
    }
    
/* 129 */     CharacterSet localCharacterSet = CharacterSetWithConverter.getInstance(paramInt);
    
/* 131 */     if (localCharacterSet != null)
    {
/* 133 */       return localCharacterSet;
    }
    
/* 137 */     return new CharacterSetUnknown(paramInt);
  }
  
/* 141 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetFactoryThin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */