package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.XSAttribute;
class XSAttributeI
  extends XSAttribute
{
  String attributeName;
  byte[] attributeNameBytes;
  String attributeValue;
  byte[] attributeValueBytes;
  String attributeDefaultValue;
  byte[] attributeDefaultValueBytes;
  long flag;
  
  XSAttributeI()
  {
/*  62 */     this.attributeName = null;
/*  63 */     this.attributeValue = null;
/*  64 */     this.attributeDefaultValue = null;
/*  65 */     this.flag = 0L;
  }
  
  public void setAttributeName(String paramString) throws SQLException {
/*  69 */     this.attributeName = paramString;
  }
  
  void doCharConversion(DBConversion paramDBConversion) throws SQLException {
/*  73 */     if (this.attributeName != null) {
/*  74 */       this.attributeNameBytes = paramDBConversion.StringToCharBytes(this.attributeName);
    } else {
/*  76 */       this.attributeNameBytes = null;
    }
/*  78 */     if (this.attributeValue != null) {
/*  79 */       this.attributeValueBytes = paramDBConversion.StringToCharBytes(this.attributeValue);
    } else {
/*  81 */       this.attributeValueBytes = null;
    }
/*  83 */     if (this.attributeDefaultValue != null) {
/*  84 */       this.attributeDefaultValueBytes = paramDBConversion.StringToCharBytes(this.attributeDefaultValue);
    } else
/*  86 */       this.attributeDefaultValueBytes = null;
  }
  
  public void setAttributeValue(String paramString) throws SQLException {
/*  90 */     this.attributeValue = paramString;
  }
  
  public void setAttributeDefaultValue(String paramString) throws SQLException {
/*  94 */     this.attributeDefaultValue = paramString;
  }
  
  public void setFlag(long paramLong) throws SQLException {
/*  98 */     this.flag = paramLong;
  }
  
  public String getAttributeName()
  {
/* 103 */     return this.attributeName;
  }
  
  public String getAttributeValue()
  {
/* 108 */     return this.attributeValue;
  }
  
  public String getAttributeDefaultValue() {
/* 112 */     return this.attributeDefaultValue;
  }
  
  public long getFlag() {
/* 116 */     return this.flag;
  }
  
  void marshal(T4CMAREngine paramT4CMAREngine) throws IOException {
/* 120 */     if (this.attributeNameBytes != null)
    {
/* 122 */       paramT4CMAREngine.marshalUB4(this.attributeNameBytes.length);
/* 123 */       paramT4CMAREngine.marshalCLR(this.attributeNameBytes, this.attributeNameBytes.length);
    }
    else {
/* 126 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 128 */     if (this.attributeValueBytes != null)
    {
/* 130 */       paramT4CMAREngine.marshalUB4(this.attributeValueBytes.length);
/* 131 */       paramT4CMAREngine.marshalCLR(this.attributeValueBytes, this.attributeValueBytes.length);
    }
    else {
/* 134 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 136 */     if (this.attributeDefaultValueBytes != null)
    {
/* 138 */       paramT4CMAREngine.marshalUB4(this.attributeDefaultValueBytes.length);
/* 139 */       paramT4CMAREngine.marshalCLR(this.attributeDefaultValueBytes, this.attributeDefaultValueBytes.length);
    }
    else {
/* 142 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 144 */     paramT4CMAREngine.marshalUB4(this.flag);
  }
  
  static XSAttributeI unmarshal(T4CMAREngine paramT4CMAREngine) throws SQLException, IOException {
/* 148 */     int[] arrayOfInt = new int[1];
/* 149 */     String str1 = null;
/* 150 */     String str2 = null;
/* 151 */     String str3 = null;
    
/* 154 */     int i = (int)paramT4CMAREngine.unmarshalUB4();
/* 155 */     if (i > 0)
    {
/* 157 */       byte[] arrayOfByte1 = new byte[i];
/* 158 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte1, 0, arrayOfInt);
/* 159 */       str1 = paramT4CMAREngine.conv.CharBytesToString(arrayOfByte1, arrayOfInt[0]);
    }
/* 161 */     int j = (int)paramT4CMAREngine.unmarshalUB4();
/* 162 */     if (j > 0)
    {
/* 164 */       byte[] arrayOfByte2 = new byte[j];
/* 165 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte2, 0, arrayOfInt);
/* 166 */       str2 = paramT4CMAREngine.conv.CharBytesToString(arrayOfByte2, arrayOfInt[0]);
    }
/* 168 */     int k = (int)paramT4CMAREngine.unmarshalUB4();
/* 169 */     if (k > 0)
    {
/* 171 */       localObject = new byte[k];
/* 172 */       paramT4CMAREngine.unmarshalCLR((byte[])localObject, 0, arrayOfInt);
/* 173 */       str3 = paramT4CMAREngine.conv.CharBytesToString((byte[])localObject, arrayOfInt[0]);
    }
/* 175 */     long l = paramT4CMAREngine.unmarshalUB4();
/* 176 */     Object localObject = new XSAttributeI();
/* 177 */     ((XSAttributeI)localObject).setAttributeName(str1);
/* 178 */     ((XSAttributeI)localObject).setAttributeValue(str2);
/* 179 */     ((XSAttributeI)localObject).setAttributeDefaultValue(str3);
/* 180 */     ((XSAttributeI)localObject).setFlag(l);
/* 181 */     return (XSAttributeI)localObject;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/XSAttributeI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */