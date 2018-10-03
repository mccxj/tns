package oracle.jdbc.driver;
class OracleTimeoutPollingThread
  extends Thread
{
  protected static final String threadName = "OracleTimeoutPollingThread";
  
  public static final String pollIntervalProperty = "oracle.jdbc.TimeoutPollInterval";
  
  public static final String pollIntervalDefault = "1000";
  
  private OracleTimeoutThreadPerVM[] knownTimeouts;
  
  private int count;
  
  private long sleepMillis;
  
  public OracleTimeoutPollingThread()
  {
/*  71 */     super("OracleTimeoutPollingThread");
    
/*  73 */     setDaemon(true);
/*  74 */     setPriority(10);
    
/*  76 */     this.knownTimeouts = new OracleTimeoutThreadPerVM[2];
/*  77 */     this.count = 0;
/*  78 */     this.sleepMillis = Long.parseLong(PhysicalConnection.getSystemPropertyPollInterval());
    
/*  81 */     start();
  }
  
  public synchronized void addTimeout(OracleTimeoutThreadPerVM paramOracleTimeoutThreadPerVM)
  {
/*  92 */     int i = 0;
    
/*  94 */     if (this.count >= this.knownTimeouts.length)
    {
/*  98 */       OracleTimeoutThreadPerVM[] arrayOfOracleTimeoutThreadPerVM = new OracleTimeoutThreadPerVM[this.knownTimeouts.length * 4];
      
/* 101 */       System.arraycopy(this.knownTimeouts, 0, arrayOfOracleTimeoutThreadPerVM, 0, this.knownTimeouts.length);
      
/* 103 */       i = this.knownTimeouts.length;
/* 104 */       this.knownTimeouts = arrayOfOracleTimeoutThreadPerVM;
    }
/* 107 */     for (; 
/* 107 */         i < this.knownTimeouts.length; i++)
    {
/* 109 */       if (this.knownTimeouts[i] == null)
      {
/* 111 */         this.knownTimeouts[i] = paramOracleTimeoutThreadPerVM;
/* 112 */         this.count += 1;
        
/* 115 */         break;
      }
    }
  }
  
  public synchronized void removeTimeout(OracleTimeoutThreadPerVM paramOracleTimeoutThreadPerVM)
  {
/* 128 */     for (int i = 0; i < this.knownTimeouts.length; i++)
    {
/* 130 */       if (this.knownTimeouts[i] == paramOracleTimeoutThreadPerVM)
      {
/* 132 */         this.knownTimeouts[i] = null;
/* 133 */         this.count -= 1;
        
/* 135 */         break;
      }
    }
  }
  
  public void run()
  {
    for (;;)
    {
      try
      {
/* 150 */         Thread.sleep(this.sleepMillis);
      }
      catch (InterruptedException localInterruptedException) {}
      
/* 158 */       pollOnce();
    }
  }
  
  private void pollOnce()
  {
/* 195 */     if (this.count > 0)
    {
/* 197 */       long l = System.currentTimeMillis();
      
/* 199 */       for (int i = 0; i < this.knownTimeouts.length; i++)
      {
        try
        {
/* 203 */           if (this.knownTimeouts[i] != null) {
/* 204 */             this.knownTimeouts[i].interruptIfAppropriate(l);
          }
        }
        catch (NullPointerException localNullPointerException) {}
      }
    }
  }
  
/* 217 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleTimeoutPollingThread.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */