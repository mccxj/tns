package oracle.net.resolver;
import java.io.File;
import java.io.IOException;
import oracle.net.jdbc.nl.NLException;
import oracle.net.jdbc.nl.NLParamParser;
import oracle.net.jdbc.nl.NVPair;
import oracle.net.ns.NetException;
public class TNSNamesNamingAdapter
  implements NamingAdapterInterface
{
  private NLParamParser tnsEntriesHdl;
  private String tnsDir;
  private long lastModifiedTime;
  private long nextPollTime;
  private static final String TNSFILE = "tnsnames.ora";
  private static final long REFRESH_INTERVAL = 0L;
  
  public TNSNamesNamingAdapter(String paramString)
  {
/*  76 */     this.tnsDir = paramString;
    
/*  79 */     resetAttr();
  }
  
  private void resetAttr()
  {
/*  87 */     this.tnsEntriesHdl = null;
/*  88 */     this.lastModifiedTime = 0L;
/*  89 */     this.nextPollTime = 0L;
  }
  
  public String resolve(String paramString)
    throws NetException
  {
/* 107 */     NVPair localNVPair = null;
    
/* 109 */     synchronized (this)
    {
/* 112 */       checkAndReload();
      
/* 116 */       localNVPair = this.tnsEntriesHdl.getNLPListElement(paramString);
    }
    
/* 119 */     if (localNVPair == null)
    {
/* 121 */       throw new NetException(122, "\"" + paramString + "\"");
    }
    
/* 124 */     return localNVPair.valueToString();
  }
  
  private void loadFile()
    throws NetException
  {
/* 138 */     File localFile = new File(this.tnsDir, "tnsnames.ora");
/* 139 */     String str = localFile.getAbsolutePath();
    
/* 152 */     if ((!localFile.isFile()) || (!localFile.canRead()))
    {
/* 155 */       resetAttr();
      
/* 157 */       throw new NetException(123, ": " + str);
    }
    
/* 163 */     long l = localFile.lastModified();
/* 164 */     if (this.lastModifiedTime != l)
    {
      try
      {
/* 168 */         this.tnsEntriesHdl = new NLParamParser(str, (byte)1);
        
/* 173 */         this.lastModifiedTime = l;
      }
      catch (IOException localIOException)
      {
/* 186 */         resetAttr();
        
/* 188 */         throw new NetException(123, ": " + str);
      }
      catch (NLException localNLException) {}
    }
  }
  
  private void checkAndReload()
    throws NetException
  {
/* 227 */     long l = System.currentTimeMillis();
    
/* 239 */     if (l > this.nextPollTime)
    {
/* 241 */       this.nextPollTime = (l + 0L);
/* 242 */       loadFile();
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/net/resolver/TNSNamesNamingAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */