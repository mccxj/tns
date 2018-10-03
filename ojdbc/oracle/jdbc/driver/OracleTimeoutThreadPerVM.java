package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class OracleTimeoutThreadPerVM
  extends OracleTimeout
{
/*  29 */   private static final OracleTimeoutPollingThread watchdog = new OracleTimeoutPollingThread();
  
  private OracleStatement statement;
  
  private long interruptAfter;
  
  private String name;
  
  OracleTimeoutThreadPerVM(String paramString)
  {
/*  46 */     this.name = paramString;
/*  47 */     this.interruptAfter = Long.MAX_VALUE;
/*  48 */     watchdog.addTimeout(this);
  }
  
  void close()
  {
/*  62 */     watchdog.removeTimeout(this);
  }
  
  synchronized void setTimeout(long paramLong, OracleStatement paramOracleStatement)
    throws SQLException
  {
/*  81 */     if (this.interruptAfter != Long.MAX_VALUE)
    {
/*  85 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 131);
/*  86 */       localSQLException.fillInStackTrace();
/*  87 */       throw localSQLException;
    }
    
/*  91 */     this.statement = paramOracleStatement;
/*  92 */     this.interruptAfter = (System.currentTimeMillis() + paramLong);
  }
  
  synchronized void cancelTimeout()
    throws SQLException
  {
/* 116 */     this.statement = null;
/* 117 */     this.interruptAfter = Long.MAX_VALUE;
  }
  
  void interruptIfAppropriate(long paramLong)
  {
/* 144 */     if (paramLong > this.interruptAfter)
    {
/* 146 */       synchronized (this)
      {
/* 148 */         if (paramLong > this.interruptAfter)
        {
/* 151 */           if (this.statement.connection.spawnNewThreadToCancel) {
/* 152 */             final OracleStatement localOracleStatement = this.statement;
/* 153 */             Thread localThread = new Thread(new Runnable() {
              public void run() {
                try {
/* 156 */                   localOracleStatement.cancel();
                }
                catch (Throwable localThrowable) {}
              }
              
/* 162 */             });
/* 163 */             localThread.setName("interruptIfAppropriate_" + this);
/* 164 */             localThread.setDaemon(true);
/* 165 */             localThread.setPriority(10);
/* 166 */             localThread.start();
          }
          else {
            try {
/* 170 */               this.statement.cancel();
            }
            catch (Throwable localThrowable) {}
          }
          
/* 177 */           this.statement = null;
/* 178 */           this.interruptAfter = Long.MAX_VALUE;
        }
      }
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 197 */     return null;
  }
  
/* 203 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleTimeoutThreadPerVM.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */