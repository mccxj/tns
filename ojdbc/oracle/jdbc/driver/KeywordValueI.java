package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValue;
class KeywordValueI
  extends KeywordValue
{
  private int keyword;
  private byte[] binaryValue;
  private String textValue;
  private byte[] textValueArr;
  
  KeywordValueI(int paramInt, String paramString, byte[] paramArrayOfByte)
  {
/*  62 */     this.keyword = paramInt;
/*  63 */     this.textValue = paramString;
/*  64 */     this.binaryValue = paramArrayOfByte;
/*  65 */     this.textValueArr = null;
  }
  
  void doCharConversion(DBConversion paramDBConversion) throws SQLException {
/*  69 */     if (this.textValue != null) {
/*  70 */       this.textValueArr = paramDBConversion.StringToCharBytes(this.textValue);
    } else {
/*  72 */       this.textValueArr = null;
    }
  }
  
  public byte[] getBinaryValue() throws SQLException {
/*  77 */     return this.binaryValue;
  }
  
  public String getTextValue() throws SQLException {
/*  81 */     return this.textValue;
  }
  
  public int getKeyword() throws SQLException {
/*  85 */     return this.keyword;
  }
  
  void marshal(T4CMAREngine paramT4CMAREngine) throws IOException
  {
/*  90 */     if (this.textValueArr != null)
    {
/*  92 */       paramT4CMAREngine.marshalUB2(this.textValueArr.length);
/*  93 */       paramT4CMAREngine.marshalCLR(this.textValueArr, this.textValueArr.length);
/*  94 */       paramT4CMAREngine.marshalUB2(0);
    }
    else
    {
/*  98 */       paramT4CMAREngine.marshalUB2(0);
/*  99 */       if (this.binaryValue != null)
      {
/* 101 */         paramT4CMAREngine.marshalUB2(this.binaryValue.length);
/* 102 */         paramT4CMAREngine.marshalCLR(this.binaryValue, this.binaryValue.length);
      }
      else {
/* 105 */         paramT4CMAREngine.marshalUB2(0);
      } }
/* 107 */     paramT4CMAREngine.marshalUB2(this.keyword);
  }
  
  static KeywordValueI unmarshal(T4CMAREngine paramT4CMAREngine) throws SQLException, IOException
  {
/* 112 */     int[] arrayOfInt = new int[1];
/* 113 */     String str = null;
/* 114 */     byte[] arrayOfByte1 = null;
/* 115 */     int i = paramT4CMAREngine.unmarshalUB2();
/* 116 */     if (i != 0)
    {
/* 118 */       byte[] arrayOfByte2 = new byte[i];
/* 119 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte2, 0, arrayOfInt);
/* 120 */       str = paramT4CMAREngine.conv.CharBytesToString(arrayOfByte2, arrayOfByte2.length);
    }
    
/* 123 */     int j = paramT4CMAREngine.unmarshalUB2();
/* 124 */     if (j != 0)
    {
/* 126 */       arrayOfByte1 = new byte[j];
/* 127 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte1, 0, arrayOfInt);
    }
    
/* 130 */     int k = paramT4CMAREngine.unmarshalUB2();
/* 131 */     return new KeywordValueI(k, str, arrayOfByte1);
  }
  
/* 136 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/KeywordValueI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */