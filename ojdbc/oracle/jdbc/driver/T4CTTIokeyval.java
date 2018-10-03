package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
final class T4CTTIokeyval
  extends T4CTTIfun
{
  static final byte KVASET_KPDUSR = 1;
  static final byte KVACLA_KPDUSR = 2;
  private byte[] namespaceByteArr;
  private char[] charArr;
  private byte[][] attrArr;
  private int[] attrArrSize;
  private byte[][] valueArr;
  private int[] valueArrSize;
  private byte[] kvalflg;
  private int nbNamespaceBytes;
  private int nbKeyVal;
  private boolean clear;
  
  T4CTTIokeyval(T4CConnection paramT4CConnection)
  {
/*  59 */     super(paramT4CConnection, (byte)17);
    
/*  61 */     setFunCode((short)154);
    
/*  63 */     this.namespaceByteArr = new byte[100];
/*  64 */     this.charArr = new char[100];
    
/*  66 */     this.attrArr = new byte[10][];
/*  67 */     this.attrArrSize = new int[10];
/*  68 */     this.valueArr = new byte[10][];
/*  69 */     this.valueArrSize = new int[10];
    
/*  71 */     this.kvalflg = new byte[10];
  }
  
  void doOKEYVAL(Namespace paramNamespace)
    throws IOException, SQLException
  {
/*  77 */     String str1 = paramNamespace.name;
/*  78 */     String[] arrayOfString1 = paramNamespace.keys;
/*  79 */     String[] arrayOfString2 = paramNamespace.values;
/*  80 */     this.clear = paramNamespace.clear;
/*  81 */     this.nbKeyVal = paramNamespace.nbPairs;
    
/*  84 */     int i = str1.length() * this.meg.conv.cMaxCharSize;
/*  85 */     if (i > this.namespaceByteArr.length)
/*  86 */       this.namespaceByteArr = new byte[i];
/*  87 */     if (str1.length() > this.charArr.length)
/*  88 */       this.charArr = new char[str1.length()];
/*  89 */     str1.getChars(0, str1.length(), this.charArr, 0);
/*  90 */     this.nbNamespaceBytes = this.meg.conv.javaCharsToCHARBytes(this.charArr, 0, this.namespaceByteArr, 0, str1.length());
    
/*  97 */     if (this.nbKeyVal > 0)
    {
/*  99 */       if (this.nbKeyVal > this.attrArr.length)
      {
/* 101 */         this.attrArr = new byte[this.nbKeyVal][];
/* 102 */         this.attrArrSize = new int[this.nbKeyVal];
/* 103 */         this.valueArr = new byte[this.nbKeyVal][];
/* 104 */         this.valueArrSize = new int[this.nbKeyVal];
/* 105 */         this.kvalflg = new byte[this.nbKeyVal];
      }
      
/* 108 */       for (int j = 0; j < this.nbKeyVal; j++)
      {
/* 110 */         String str2 = arrayOfString1[j];
/* 111 */         String str3 = arrayOfString2[j];
        
/* 113 */         int k = str2.length() * this.meg.conv.cMaxCharSize;
/* 114 */         if ((this.attrArr[j] == null) || (this.attrArr[j].length < k)) {
/* 115 */           this.attrArr[j] = new byte[k];
        }
/* 117 */         int m = str3.length() * this.meg.conv.cMaxCharSize;
/* 118 */         if ((this.valueArr[j] == null) || (this.valueArr[j].length < m)) {
/* 119 */           this.valueArr[j] = new byte[m];
        }
/* 121 */         if (str2.length() > this.charArr.length)
/* 122 */           this.charArr = new char[str2.length()];
/* 123 */         str2.getChars(0, str2.length(), this.charArr, 0);
/* 124 */         this.attrArrSize[j] = this.meg.conv.javaCharsToCHARBytes(this.charArr, 0, this.attrArr[j], 0, str2.length());
        
/* 129 */         if (str3.length() > this.charArr.length)
/* 130 */           this.charArr = new char[str3.length()];
/* 131 */         str3.getChars(0, str3.length(), this.charArr, 0);
/* 132 */         this.valueArrSize[j] = this.meg.conv.javaCharsToCHARBytes(this.charArr, 0, this.valueArr[j], 0, str3.length());
      }
    }
    
/* 138 */     doPigRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 147 */     this.meg.marshalPTR();
/* 148 */     this.meg.marshalUB4(this.nbNamespaceBytes);
/* 149 */     if (this.nbKeyVal > 0) {
/* 150 */       this.meg.marshalPTR();
    } else
/* 152 */       this.meg.marshalNULLPTR();
/* 153 */     this.meg.marshalUB4(this.nbKeyVal);
/* 154 */     int i = 0;
/* 155 */     if (this.nbKeyVal > 0)
/* 156 */       i = 1;
/* 157 */     if (this.clear)
/* 158 */       i |= 0x2;
/* 159 */     this.meg.marshalUB2(i);
/* 160 */     this.meg.marshalNULLPTR();
    
/* 164 */     this.meg.marshalCHR(this.namespaceByteArr, 0, this.nbNamespaceBytes);
/* 165 */     if (this.nbKeyVal > 0) {
/* 166 */       this.meg.marshalKEYVAL(this.attrArr, this.attrArrSize, this.valueArr, this.valueArrSize, this.kvalflg, this.nbKeyVal);
    }
  }
  
/* 171 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIokeyval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */