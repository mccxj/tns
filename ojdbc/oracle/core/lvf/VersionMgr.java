package oracle.core.lvf;
public final class VersionMgr
{
  public static final byte ALPHA = 1;
  
  public static final byte BETA = 2;
  
  public static final byte PROD = 3;
  
  public static final byte NONE = 4;
  
/*  57 */   private final byte MAX_LEN = 64;
/*  58 */   private final byte MAX_PRODLEN = 30;
/*  59 */   private final byte MAX_VERLEN = 15;
/*  60 */   private final byte MAX_DISTLEN = 5;
/*  61 */   private final String alpha = "Alpha";
/*  62 */   private final String beta = "Beta";
/*  63 */   private final String prod = "Production";
  
  private String version;
  
  public void setVersion(String paramString1, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, char paramChar, String paramString2, byte paramByte6, int paramInt)
  {
/*  88 */     char[] arrayOfChar = new char[64];
    
/*  93 */     String str2 = "";
    
    int k;
/*  96 */     if ((k = (byte)paramString1.length()) > 30) {
/*  97 */       k = 30;
    }
    
/* 100 */     int j = 0;
/* 101 */     for (;;) { k = (byte)(k - 1); if (0 >= k)
        break;
/* 103 */       arrayOfChar[j] = paramString1.charAt(j);
/* 104 */       j = (byte)(j + 1);
    }
    
/* 108 */     j = (byte)(j + 1);arrayOfChar[j] = '\t';
    
/* 111 */     if (paramByte1 < 0)
/* 112 */       paramByte1 = 0;
/* 113 */     if (paramByte2 < 0)
/* 114 */       paramByte2 = 0;
/* 115 */     if (paramByte3 < 0)
/* 116 */       paramByte3 = 0;
/* 117 */     if (paramByte4 < 0)
/* 118 */       paramByte4 = 0;
/* 119 */     if (paramByte5 < 0) {
/* 120 */       paramByte5 = 0;
    }
    
/* 123 */     if (paramByte1 > 99)
/* 124 */       paramByte1 = 99;
/* 125 */     if (paramByte2 > 99)
/* 126 */       paramByte2 = 99;
/* 127 */     if (paramByte3 > 99)
/* 128 */       paramByte3 = 99;
/* 129 */     if (paramByte4 > 99)
/* 130 */       paramByte4 = 99;
/* 131 */     if (paramByte5 > 99) {
/* 132 */       paramByte5 = 99;
    }
    String str1;
/* 135 */     if (paramChar != 0) {
/* 136 */       str1 = paramByte1 + "." + paramByte2 + "." + paramByte3 + "." + paramByte4 + "." + paramByte5 + paramChar;
    } else
/* 138 */       str1 = paramByte1 + "." + paramByte2 + "." + paramByte3 + "." + paramByte4 + "." + paramByte5;
/* 139 */     int m = (byte)str1.length();
    
/* 142 */     int i = 0;
/* 143 */     for (;;) { m = (byte)(m - 1); if (0 >= m) break;
/* 144 */       j = (byte)(j + 1);i = (byte)(i + 1);arrayOfChar[j] = str1.charAt(i);
    }
/* 146 */     if (paramByte6 != 4)
    {
/* 149 */       j = (byte)(j + 1);arrayOfChar[j] = '\t';
      
/* 151 */       if (paramString2 != null)
      {
/* 153 */         int i1 = 0;
        
/* 156 */         if ((i1 = (byte)paramString2.length()) > 5) {
/* 157 */           i1 = 5;
        }
        
/* 160 */         i = 0;
/* 161 */         for (;;) { i1 = (byte)(i1 - 1); if (0 >= i1) break;
/* 162 */           j = (byte)(j + 1);i = (byte)(i + 1);arrayOfChar[j] = paramString2.charAt(i);
        }
        
/* 165 */         j = (byte)(j + 1);arrayOfChar[j] = '\t';
      }
      
/* 169 */       switch (paramByte6)
      {
      case 1: 
/* 172 */         str2 = "Alpha";
/* 173 */         break;
      case 2: 
/* 175 */         str2 = "Beta";
/* 176 */         break;
      case 3: 
/* 178 */         str2 = "Production";
      }
      
      
/* 182 */       i = 0;
/* 183 */       int n = (byte)str2.length();
      for (;;)
      {
/* 186 */         n = (byte)(n - 1); if (0 >= n) break;
/* 187 */         j = (byte)(j + 1);i = (byte)(i + 1);arrayOfChar[j] = str2.charAt(i);
      }
    }
    
/* 191 */     this.version = new String(arrayOfChar, 0, j);
  }
  
  public String getVersion()
  {
/* 200 */     return this.version;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/core/lvf/VersionMgr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */