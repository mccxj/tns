package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.XSAttribute;
import oracle.jdbc.internal.XSNamespace;
import oracle.sql.TIMESTAMPTZ;
class XSNamespaceI
  extends XSNamespace
{
  String namespaceName;
  byte[] namespaceNameBytes;
  XSAttributeI[] attributes;
  byte[] timestampBytes;
  long flag;
  byte[][] aclList;
  
  XSNamespaceI()
  {
/*  65 */     this.namespaceName = null;
/*  66 */     this.attributes = null;
/*  67 */     this.timestampBytes = null;
/*  68 */     this.flag = 0L;
/*  69 */     this.aclList = ((byte[][])null);
  }
  
  public void setNamespaceName(String paramString) throws SQLException {
/*  73 */     this.namespaceName = paramString;
  }
  
  public void setTimestamp(TIMESTAMPTZ paramTIMESTAMPTZ) throws SQLException {
/*  77 */     this.timestampBytes = paramTIMESTAMPTZ.toBytes();
  }
  
  private void setTimestamp(byte[] paramArrayOfByte) throws SQLException {
/*  81 */     this.timestampBytes = paramArrayOfByte;
  }
  
  public void setACLIdList(byte[][] paramArrayOfByte) throws SQLException {
/*  85 */     this.aclList = paramArrayOfByte;
  }
  
  public void setFlag(long paramLong) throws SQLException
  {
/*  90 */     this.flag = paramLong;
  }
  
  public void setAttributes(XSAttribute[] paramArrayOfXSAttribute) throws SQLException {
/*  94 */     if (paramArrayOfXSAttribute != null)
    {
/*  96 */       XSAttributeI[] arrayOfXSAttributeI = new XSAttributeI[paramArrayOfXSAttribute.length];
/*  97 */       for (int i = 0; i < paramArrayOfXSAttribute.length; i++) {
/*  98 */         arrayOfXSAttributeI[i] = ((XSAttributeI)paramArrayOfXSAttribute[i]);
      }
/* 100 */       this.attributes = arrayOfXSAttributeI;
    }
  }
  
  void doCharConversion(DBConversion paramDBConversion) throws SQLException
  {
/* 106 */     if (this.namespaceName != null) {
/* 107 */       this.namespaceNameBytes = paramDBConversion.StringToCharBytes(this.namespaceName);
    } else {
/* 109 */       this.namespaceNameBytes = null;
    }
/* 111 */     if (this.attributes != null)
    {
/* 113 */       for (int i = 0; i < this.attributes.length; i++) {
/* 114 */         this.attributes[i].doCharConversion(paramDBConversion);
      }
    }
  }
  
  public String getNamespaceName() {
/* 120 */     return this.namespaceName;
  }
  
  public TIMESTAMPTZ getTimestamp()
  {
/* 125 */     return new TIMESTAMPTZ(this.timestampBytes);
  }
  
  public long getFlag()
  {
/* 130 */     return this.flag;
  }
  
  public XSAttribute[] getAttributes()
  {
/* 135 */     return this.attributes;
  }
  
  public byte[][] getACLIdList() {
/* 139 */     return this.aclList;
  }
  
  void marshal(T4CMAREngine paramT4CMAREngine) throws IOException
  {
/* 144 */     if (this.namespaceNameBytes != null)
    {
/* 146 */       paramT4CMAREngine.marshalUB4(this.namespaceNameBytes.length);
/* 147 */       paramT4CMAREngine.marshalCLR(this.namespaceNameBytes, this.namespaceNameBytes.length);
    }
    else {
/* 150 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 152 */     if (this.timestampBytes != null)
    {
/* 154 */       paramT4CMAREngine.marshalUB4(this.timestampBytes.length);
/* 155 */       paramT4CMAREngine.marshalCLR(this.timestampBytes, this.timestampBytes.length);
    }
    else {
/* 158 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 160 */     paramT4CMAREngine.marshalUB4(this.flag);
    
/* 162 */     if (this.attributes != null)
    {
/* 164 */       paramT4CMAREngine.marshalUB4(this.attributes.length);
      
/* 167 */       paramT4CMAREngine.marshalUB1((short)28);
/* 168 */       for (int i = 0; i < this.attributes.length; i++) {
/* 169 */         this.attributes[i].marshal(paramT4CMAREngine);
      }
    } else {
/* 172 */       paramT4CMAREngine.marshalUB4(0L);
    }
/* 174 */     if (this.aclList != null)
    {
/* 176 */       byte[] arrayOfByte = new byte[this.aclList.length * 16];
/* 177 */       for (int j = 0; j < this.aclList.length; j++)
/* 178 */         System.arraycopy(this.aclList[j], 0, arrayOfByte, 16 * j, 16);
/* 179 */       paramT4CMAREngine.marshalUB4(arrayOfByte.length);
/* 180 */       paramT4CMAREngine.marshalCLR(arrayOfByte, arrayOfByte.length);
    }
    else {
/* 183 */       paramT4CMAREngine.marshalUB4(0L);
    }
  }
  
/* 187 */   static XSNamespaceI unmarshal(T4CMAREngine paramT4CMAREngine) throws SQLException, IOException { int[] arrayOfInt = new int[1];
/* 188 */     String str = null;
    
/* 191 */     int i = (int)paramT4CMAREngine.unmarshalUB4();
/* 192 */     if (i > 0)
    {
/* 194 */       arrayOfByte = new byte[i];
/* 195 */       paramT4CMAREngine.unmarshalCLR(arrayOfByte, 0, arrayOfInt);
/* 196 */       str = paramT4CMAREngine.conv.CharBytesToString(arrayOfByte, arrayOfInt[0]);
    }
    
/* 199 */     byte[] arrayOfByte = null;
/* 200 */     int j = (int)paramT4CMAREngine.unmarshalUB4();
/* 201 */     if (j > 0) {
/* 202 */       arrayOfByte = paramT4CMAREngine.unmarshalNBytes(j);
    }
/* 204 */     long l = paramT4CMAREngine.unmarshalUB4();
    
/* 206 */     XSAttribute[] arrayOfXSAttribute = null;
/* 207 */     int k = (int)paramT4CMAREngine.unmarshalUB4();
/* 208 */     arrayOfXSAttribute = new XSAttribute[k];
/* 209 */     if (k > 0)
/* 210 */       paramT4CMAREngine.unmarshalUB1();
/* 211 */     for (int m = 0; m < k; m++) {
/* 212 */       arrayOfXSAttribute[m] = XSAttributeI.unmarshal(paramT4CMAREngine);
    }
/* 214 */     m = (int)paramT4CMAREngine.unmarshalUB4();
/* 215 */     byte[][] arrayOfByte1 = (byte[][])null;
/* 216 */     if (m > 0)
    {
/* 218 */       localObject = new byte[m];
/* 219 */       paramT4CMAREngine.unmarshalCLR((byte[])localObject, 0, arrayOfInt);
      
/* 221 */       int n = m / 16;
/* 222 */       arrayOfByte1 = new byte[n][];
/* 223 */       for (int i1 = 0; i1 < n; i1++)
      {
/* 225 */         arrayOfByte1[i1] = new byte[16];
/* 226 */         System.arraycopy(localObject, i1 * 16, arrayOfByte1[i1], 0, 16);
      }
    }
    
/* 230 */     Object localObject = new XSNamespaceI();
/* 231 */     ((XSNamespaceI)localObject).setNamespaceName(str);
/* 232 */     ((XSNamespaceI)localObject).setTimestamp(arrayOfByte);
/* 233 */     ((XSNamespaceI)localObject).setFlag(l);
/* 234 */     ((XSNamespaceI)localObject).setAttributes(arrayOfXSAttribute);
/* 235 */     ((XSNamespaceI)localObject).setACLIdList(arrayOfByte1);
/* 236 */     return (XSNamespaceI)localObject;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/XSNamespaceI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */