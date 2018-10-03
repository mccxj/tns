package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.KeywordValueLong;
class KeywordValueLongI
  extends KeywordValueLong
{
  private int keyword;
  private String textValue;
  private byte[] textValueArr;
  private byte[] binaryValue;
  
  KeywordValueLongI(int paramInt, String paramString, byte[] paramArrayOfByte)
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
/*  92 */       paramT4CMAREngine.marshalUB4(this.textValueArr.length);
/*  93 */       paramT4CMAREngine.marshalCLR(this.textValueArr, this.textValueArr.length);
/*  94 */       paramT4CMAREngine.marshalUB4(0L);
    }
    else
    {
/*  98 */       paramT4CMAREngine.marshalUB4(0L);
/*  99 */       if (this.binaryValue != null)
      {
/* 101 */         paramT4CMAREngine.marshalUB4(this.binaryValue.length);
/* 102 */         paramT4CMAREngine.marshalCLR(this.binaryValue, this.binaryValue.length);
      }
      else {
/* 105 */         paramT4CMAREngine.marshalUB4(0L);
      } }
/* 107 */     paramT4CMAREngine.marshalUB2(this.keyword);
  }
  
  static KeywordValueLongI unmarshal(T4CMAREngine paramT4CMAREngine) throws SQLException, IOException
  {
/* 112 */     int[] arrayOfInt = new int[1];
/* 113 */     String str = null;
/* 114 */     byte[] arrayOfByte1 = null;
/* 115 */     int i = (int)paramT4CMAREngine.unmarshalUB4();
/* 116 */     if (i != 0)
    {
/* 118 */       byte[] arrayOfByte2 = new byte[i];
/* 119 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte2, 0, arrayOfInt);
/* 120 */       str = paramT4CMAREngine.conv.CharBytesToString(arrayOfByte2, arrayOfByte2.length);
    }
    
/* 123 */     int j = (int)paramT4CMAREngine.unmarshalUB4();
/* 124 */     if (j != 0)
    {
/* 126 */       arrayOfByte1 = new byte[j];
/* 127 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte1, 0, arrayOfInt);
    }
    
/* 130 */     int k = paramT4CMAREngine.unmarshalUB2();
/* 131 */     return new KeywordValueLongI(k, str, arrayOfByte1);
  }
  
  public String toString()
  {
/* 138 */     StringBuffer localStringBuffer = new StringBuffer();
/* 139 */     localStringBuffer.append("keyword    : " + this.keyword + "\n");
/* 140 */     localStringBuffer.append("text value : " + this.textValue + "\n");
/* 141 */     if (this.binaryValue == null) {
/* 142 */       localStringBuffer.append("bin value  : null\n");
    } else
/* 144 */       localStringBuffer.append("bin value  : " + NTFAQEvent.byteBufferToHexString(this.binaryValue, 50) + "\n");
/* 145 */     return localStringBuffer.toString();
  }
  
/* 151 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/KeywordValueLongI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */