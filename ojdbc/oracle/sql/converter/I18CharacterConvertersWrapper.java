package oracle.sql.converter;
import java.sql.SQLException;
import java.util.Vector;
import oracle.i18n.text.converter.CharacterConverter;
import oracle.jdbc.internal.OracleConnection;
public class I18CharacterConvertersWrapper
  implements JdbcCharacterConverters
{
  CharacterConverter wrapper;
  
  public I18CharacterConvertersWrapper(CharacterConverter paramCharacterConverter)
  {
/*  54 */     this.wrapper = paramCharacterConverter;
  }
  
  public int getGroupId()
  {
/*  59 */     return this.wrapper.getGroupId();
  }
  
  public int getOracleId()
  {
/*  64 */     return this.wrapper.getOracleId();
  }
  
  public char[] getLeadingCodes()
  {
/*  69 */     return this.wrapper.getLeadingCodes();
  }
  
  public String toUnicodeString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  75 */     return this.wrapper.toUnicodeString(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public String toUnicodeStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/*  80 */     return this.wrapper.toUnicodeStringWithReplacement(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public byte[] toOracleString(String paramString)
    throws SQLException
  {
/*  86 */     return this.wrapper.toOracleString(paramString);
  }
  
  public byte[] toOracleStringWithReplacement(String paramString)
  {
/*  91 */     return this.wrapper.toOracleStringWithReplacement(paramString);
  }
  
  public void buildUnicodeToOracleMapping()
  {
/*  96 */     this.wrapper.buildUnicodeToOracleMapping();
  }
  
  public void extractCodepoints(Vector paramVector)
  {
/* 101 */     this.wrapper.extractCodepoints(paramVector);
  }
  
  public void extractExtraMappings(Vector paramVector)
  {
/* 106 */     this.wrapper.extractExtraMappings(paramVector);
  }
  
  public boolean hasExtraMappings()
  {
/* 111 */     return this.wrapper.hasExtraMappings();
  }
  
  public char getOraChar1ByteRep()
  {
/* 116 */     return this.wrapper.getOraChar1ByteRep();
  }
  
  public char getOraChar2ByteRep()
  {
/* 121 */     return this.wrapper.getOraChar2ByteRep();
  }
  
  public int getUCS2CharRep()
  {
/* 126 */     return this.wrapper.getUCS2CharRep();
  }
  
  public int toUnicodeChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
    throws SQLException
  {
/* 133 */     return this.wrapper.toUnicodeCharsWithReplacement(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramInt3);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 147 */     return null;
  }
  
/* 152 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/I18CharacterConvertersWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */