package oracle.net.resolver;
import java.net.InetAddress;
import java.net.UnknownHostException;
import oracle.net.ns.NetException;
public class HostnameNamingAdapter
  implements NamingAdapterInterface
{
  public static final int DEFAULT_DATABASE_PORT = 1521;
  public static final String DEFAULT_PROTOCOL = "TCP";
  String osuser;
  String programName;
  
  private HostnameNamingAdapter() {}
  
  public HostnameNamingAdapter(String paramString1, String paramString2)
  {
/*  68 */     this.osuser = paramString1;
/*  69 */     this.programName = paramString2;
  }
  
  public String resolve(String paramString)
    throws NetException
  {
/*  93 */     int j = 0;
/*  94 */     int k = 0;
/*  95 */     int m = 0;
/*  96 */     int n = 0;
/*  97 */     int i1 = 0;
    
/* 100 */     if (paramString.startsWith("//") == true) {
/* 101 */       paramString = paramString.substring(2);
    }
/* 103 */     if (paramString.charAt(0) == '[')
    {
/* 105 */       i1 = 1;
/* 106 */       n = paramString.indexOf(']');
/* 107 */       if (n != -1) {
/* 108 */         j = paramString.indexOf(':', n);
      }
/* 110 */       if ((n == -1) || ((j != -1) && (j != n + 1)))
      {
/* 112 */         throw new NetException(117);
      }
/* 114 */       m = 1;
/* 115 */       k = paramString.indexOf('/', n);
    }
    else
    {
/* 119 */       m = 0;
/* 120 */       j = paramString.indexOf(':');
/* 121 */       k = paramString.indexOf('/', m);
    }
    
/* 126 */     if (((k != -1) && (j > k)) || (paramString.endsWith("/")) || (paramString.endsWith(":")))
    {
/* 129 */       throw new NetException(117);
    }
    
    String str3;
    try
    {
      String str1;
/* 136 */       if (i1 != 0) {
/* 137 */         str1 = paramString.substring(m, n);
      }
/* 140 */       else if (j != -1) {
/* 141 */         str1 = paramString.substring(m, j);
/* 142 */       } else if (k != -1) {
/* 143 */         str1 = paramString.substring(m, k);
      } else {
/* 145 */         str1 = paramString.substring(m);
      }
      int i;
/* 148 */       if (j != -1)
      {
/* 150 */         if (k != -1) {
/* 151 */           i = Integer.parseInt(paramString.substring(j + 1, k));
        } else {
/* 153 */           i = Integer.parseInt(paramString.substring(j + 1));
        }
      } else
/* 156 */         i = 1521;
      String str2;
/* 158 */       if (k != -1) {
/* 159 */         str2 = paramString.substring(k + 1);
      }
      else
      {
/* 164 */         str2 = "";
      }
      
/* 167 */       InetAddress[] arrayOfInetAddress = InetAddress.getAllByName(str1);
      
/* 170 */       String str4 = "";
/* 171 */       for (int i2 = 0; i2 < arrayOfInetAddress.length; i2++)
      {
/* 173 */         str1 = arrayOfInetAddress[i2].getHostAddress();
/* 174 */         str4 = str4 + "(ADDRESS=" + "(PROTOCOL=" + "TCP" + ")(HOST=" + str1 + ")(PORT=" + i + "))";
      }
      
/* 179 */       String str5 = "(DESCRIPTION=(CONNECT_DATA=(SERVICE_NAME=" + str2 + ")(CID=(PROGRAM=" + this.programName + ")(HOST=__jdbc__)(USER=" + this.osuser + ")))";
      
/* 183 */       str3 = str5 + str4 + ")";
    }
    catch (NumberFormatException localNumberFormatException)
    {
/* 187 */       throw new NetException(116);
    }
    catch (UnknownHostException localUnknownHostException)
    {
/* 191 */       throw new NetException(118);
    }
/* 193 */     return str3;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/HostnameNamingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */