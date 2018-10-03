package oracle.jdbc.diagnostics;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
public class DemultiplexingLogHandler
  extends FileHandler
{
  static final String PROPERTY_PATTERN = "oracle.jdbc.diagnostics.DemultiplexingLogHandler.pattern";
  static final String PROPERTY_LIMIT = "oracle.jdbc.diagnostics.DemultiplexingLogHandler.limit";
  static final String PROPERTY_COUNT = "oracle.jdbc.diagnostics.DemultiplexingLogHandler.count";
  static final String PROPERTY_APPEND = "oracle.jdbc.diagnostics.DemultiplexingLogHandler.append";
  static final String DEFAULT_PATTERN = "%h/ojdbc_%s.trc";
/*  51 */   static final String DEFAULT_APPEND = String.valueOf(false);
/*  52 */   static final String DEFAULT_LIMIT = String.valueOf(Integer.MAX_VALUE);
/*  53 */   static final String DEFAULT_COUNT = String.valueOf(1);
  
  String localPattern;
  boolean localAppend;
  int localLimit;
  int localCount;
  
  public DemultiplexingLogHandler()
    throws IOException
  {
/*  63 */     super(getFilename(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.pattern", "%h/ojdbc_%s.trc"), "MAIN"), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.limit", DEFAULT_LIMIT)), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.count", DEFAULT_COUNT)), Boolean.getBoolean(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.append", DEFAULT_APPEND)));
  }
  
  public DemultiplexingLogHandler(String paramString)
    throws IOException
  {
/*  72 */     super(getFilename(paramString, "MAIN"), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.limit", DEFAULT_LIMIT)), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.count", DEFAULT_COUNT)), Boolean.getBoolean(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.append", DEFAULT_APPEND)));
  }
  
  public DemultiplexingLogHandler(String paramString, boolean paramBoolean)
    throws IOException
  {
/*  81 */     super(getFilename(paramString, "MAIN"), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.limit", DEFAULT_LIMIT)), Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.count", DEFAULT_COUNT)), paramBoolean);
  }
  
  public DemultiplexingLogHandler(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
/*  90 */     super(getFilename(paramString, "MAIN"), paramInt1, paramInt2, Boolean.getBoolean(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.append", DEFAULT_APPEND)));
  }
  
  public DemultiplexingLogHandler(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
    throws IOException
  {
/* 101 */     super(getFilename(paramString, "MAIN"), paramInt1, paramInt2, paramBoolean);
  }
  
  void initValues()
  {
/* 112 */     this.localPattern = getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.pattern", "%h/ojdbc_%s.trc");
/* 113 */     this.localLimit = Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.limit", DEFAULT_LIMIT));
/* 114 */     this.localCount = Integer.parseInt(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.count", DEFAULT_COUNT));
/* 115 */     this.localAppend = Boolean.getBoolean(getProperty("oracle.jdbc.diagnostics.DemultiplexingLogHandler.append", DEFAULT_APPEND));
  }
  
  static final String getFilename(String paramString1, String paramString2)
  {
/* 124 */     if (paramString1 == null) {
/* 125 */       paramString1 = "%h/ojdbc_%s.trc";
    }
/* 127 */     if (paramString1.contains("%s"))
    {
/* 129 */       return paramString1.replaceAll("%s", paramString2);
    }
    
/* 132 */     return paramString1 + "." + paramString2;
  }
  
  static String getProperty(String paramString1, String paramString2)
  {
/* 140 */     String str = LogManager.getLogManager().getProperty(paramString1);
/* 141 */     return str != null ? str : paramString2;
  }
  
/* 145 */   Hashtable<Object, Handler> handlerList = new Hashtable(50);
  
  public void publish(LogRecord paramLogRecord)
  {
/* 149 */     Object[] arrayOfObject = paramLogRecord.getParameters();
/* 150 */     if ((arrayOfObject != null) && (arrayOfObject.length > 0))
    {
/* 153 */       Object localObject = (Handler)this.handlerList.get(arrayOfObject[0]);
/* 154 */       if (localObject == null)
      {
/* 156 */         if (this.localPattern == null) {
/* 157 */           initValues();
        }
        try {
/* 160 */           localObject = new FileHandler(getFilename(this.localPattern, (String)arrayOfObject[0]), this.localLimit, this.localCount, this.localAppend);
          
/* 165 */           ((Handler)localObject).setFormatter(getFormatter());
/* 166 */           ((Handler)localObject).setFilter(getFilter());
/* 167 */           ((Handler)localObject).setLevel(getLevel());
/* 168 */           ((Handler)localObject).setEncoding(getEncoding());
/* 169 */           ((Handler)localObject).setErrorManager(getErrorManager());
        }
        catch (IOException localIOException)
        {
/* 173 */           reportError("Unable open FileHandler", localIOException, 0);
        }
        
/* 176 */         this.handlerList.put(arrayOfObject[0], localObject);
      }
/* 178 */       ((Handler)localObject).publish(paramLogRecord);
    }
    else {
/* 181 */       super.publish(paramLogRecord);
    }
  }
  
  public void close()
  {
/* 187 */     for (Handler localHandler : this.handlerList.values()) {
/* 188 */       localHandler.close();
    }
/* 190 */     super.close();
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/diagnostics/DemultiplexingLogHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */