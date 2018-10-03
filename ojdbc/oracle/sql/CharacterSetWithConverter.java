package oracle.sql;
import java.sql.SQLException;
import oracle.sql.converter.CharacterConverterFactory;
import oracle.sql.converter.CharacterConverterFactoryJDBC;
import oracle.sql.converter.JdbcCharacterConverters;
public abstract class CharacterSetWithConverter
  extends CharacterSet
{
/*  70 */   public static CharacterConverterFactory ccFactory = new CharacterConverterFactoryJDBC();
  
  JdbcCharacterConverters m_converter;
  
  CharacterSetWithConverter(int paramInt, JdbcCharacterConverters paramJdbcCharacterConverters)
  {
/*  83 */     super(paramInt);
    
/*  85 */     this.m_converter = paramJdbcCharacterConverters;
  }
  
  static CharacterSet getInstance(int paramInt)
  {
/*  97 */     JdbcCharacterConverters localJdbcCharacterConverters = ccFactory.make(paramInt);
    
/*  99 */     if (localJdbcCharacterConverters == null)
    {
/* 101 */       return null;
    }
    
/* 106 */     Object localObject = null;
    
/* 108 */     if ((localObject = CharacterSet1Byte.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 111 */       return (CharacterSet)localObject;
    }
    
/* 116 */     if ((localObject = CharacterSetSJIS.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 119 */       return (CharacterSet)localObject;
    }
    
/* 122 */     if ((localObject = CharacterSetShift.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 125 */       return (CharacterSet)localObject;
    }
    
/* 128 */     if ((localObject = CharacterSet2ByteFixed.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 131 */       return (CharacterSet)localObject;
    }
    
/* 134 */     if ((localObject = CharacterSetGB18030.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 137 */       return (CharacterSet)localObject;
    }
    
/* 140 */     if ((localObject = CharacterSet12Byte.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 143 */       return (CharacterSet)localObject;
    }
    
/* 146 */     if ((localObject = CharacterSetJAEUC.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 149 */       return (CharacterSet)localObject;
    }
    
/* 152 */     if ((localObject = CharacterSetZHTEUC.getInstance(paramInt, localJdbcCharacterConverters)) != null)
    {
/* 155 */       return (CharacterSet)localObject;
    }
    
/* 159 */     return CharacterSetLCFixed.getInstance(paramInt, localJdbcCharacterConverters);
  }
  
  public boolean isLossyFrom(CharacterSet paramCharacterSet)
  {
/* 166 */     return paramCharacterSet.getOracleId() != getOracleId();
  }
  
  public boolean isConvertibleFrom(CharacterSet paramCharacterSet)
  {
/* 173 */     return paramCharacterSet.getOracleId() == getOracleId();
  }
  
  public String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/* 180 */     return this.m_converter.toUnicodeStringWithReplacement(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 188 */     return this.m_converter.toUnicodeString(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public byte[] convert(String paramString)
    throws SQLException
  {
/* 195 */     return this.m_converter.toOracleString(paramString);
  }
  
  public byte[] convertWithReplacement(String paramString)
  {
/* 202 */     return this.m_converter.toOracleStringWithReplacement(paramString);
  }
  
  public byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 210 */     if (paramCharacterSet.getOracleId() == getOracleId())
    {
/* 212 */       return useOrCopy(paramArrayOfByte, paramInt1, paramInt2);
    }
    
/* 216 */     return convert(paramCharacterSet.toString(paramArrayOfByte, paramInt1, paramInt2));
  }
  
/* 221 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSetWithConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */