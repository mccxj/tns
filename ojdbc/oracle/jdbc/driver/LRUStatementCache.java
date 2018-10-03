package oracle.jdbc.driver;
import java.io.PrintStream;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class LRUStatementCache
{
  private int cacheSize;
  private int numElements;
  private OracleStatementCacheEntry applicationCacheStart;
  private OracleStatementCacheEntry applicationCacheEnd;
  private OracleStatementCacheEntry implicitCacheStart;
  private OracleStatementCacheEntry explicitCacheStart;
  boolean implicitCacheEnabled;
  boolean explicitCacheEnabled;
/*  49 */   private boolean debug = false;
  
  protected LRUStatementCache(int paramInt)
    throws SQLException
  {
/*  65 */     if (paramInt < 0)
    {
/*  67 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 123);
/*  68 */       localSQLException.fillInStackTrace();
/*  69 */       throw localSQLException;
    }
    
/*  72 */     this.cacheSize = paramInt;
/*  73 */     this.numElements = 0;
    
/*  75 */     this.implicitCacheStart = null;
/*  76 */     this.explicitCacheStart = null;
    
/*  78 */     this.implicitCacheEnabled = false;
/*  79 */     this.explicitCacheEnabled = false;
  }
  
  protected void resize(int paramInt)
    throws SQLException
  {
    Object localObject;
    
/*  97 */     if (paramInt < 0)
    {
/*  99 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 123);
/* 100 */       ((SQLException)localObject).fillInStackTrace();
/* 101 */       throw ((Throwable)localObject);
    }
    
/* 104 */     if ((paramInt >= this.cacheSize) || (paramInt >= this.numElements))
    {
/* 107 */       this.cacheSize = paramInt;
    }
    else
    {
/* 113 */       for (localObject = this.applicationCacheEnd; 
/* 114 */           this.numElements > paramInt; localObject = ((OracleStatementCacheEntry)localObject).applicationPrev) {
/* 115 */         purgeCacheEntry((OracleStatementCacheEntry)localObject);
      }
/* 117 */       this.cacheSize = paramInt;
    }
  }
  
  public void setImplicitCachingEnabled(boolean paramBoolean)
    throws SQLException
  {
/* 135 */     if (!paramBoolean) {
/* 136 */       purgeImplicitCache();
    }
/* 138 */     this.implicitCacheEnabled = paramBoolean;
  }
  
  public boolean getImplicitCachingEnabled()
    throws SQLException
  {
    boolean bool;
    
/* 155 */     if (this.cacheSize == 0) {
/* 156 */       bool = false;
    } else
/* 158 */       bool = this.implicitCacheEnabled;
/* 159 */     return bool;
  }
  
  public void setExplicitCachingEnabled(boolean paramBoolean)
    throws SQLException
  {
/* 176 */     if (!paramBoolean) {
/* 177 */       purgeExplicitCache();
    }
/* 179 */     this.explicitCacheEnabled = paramBoolean;
  }
  
  public boolean getExplicitCachingEnabled()
    throws SQLException
  {
    boolean bool;
    
/* 196 */     if (this.cacheSize == 0) {
/* 197 */       bool = false;
    } else
/* 199 */       bool = this.explicitCacheEnabled;
/* 200 */     return bool;
  }
  
  protected void addToImplicitCache(OraclePreparedStatement paramOraclePreparedStatement, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 222 */     if ((!this.implicitCacheEnabled) || (this.cacheSize == 0) || (paramOraclePreparedStatement.cacheState == 2))
    {
/* 229 */       return;
    }
    
/* 233 */     if (this.numElements == this.cacheSize) {
/* 234 */       purgeCacheEntry(this.applicationCacheEnd);
    }
    
/* 238 */     paramOraclePreparedStatement.enterImplicitCache();
    
/* 241 */     OracleStatementCacheEntry localOracleStatementCacheEntry = new OracleStatementCacheEntry();
    
/* 243 */     localOracleStatementCacheEntry.statement = paramOraclePreparedStatement;
/* 244 */     localOracleStatementCacheEntry.onImplicit = true;
    
/* 246 */     localOracleStatementCacheEntry.sql = paramString;
/* 247 */     localOracleStatementCacheEntry.statementType = paramInt1;
/* 248 */     localOracleStatementCacheEntry.scrollType = paramInt2;
    
/* 251 */     localOracleStatementCacheEntry.applicationNext = this.applicationCacheStart;
/* 252 */     localOracleStatementCacheEntry.applicationPrev = null;
    
/* 254 */     if (this.applicationCacheStart != null) {
/* 255 */       this.applicationCacheStart.applicationPrev = localOracleStatementCacheEntry;
    }
/* 257 */     this.applicationCacheStart = localOracleStatementCacheEntry;
    
/* 259 */     localOracleStatementCacheEntry.implicitNext = this.implicitCacheStart;
/* 260 */     localOracleStatementCacheEntry.implicitPrev = null;
    
/* 262 */     if (this.implicitCacheStart != null) {
/* 263 */       this.implicitCacheStart.implicitPrev = localOracleStatementCacheEntry;
    }
/* 265 */     this.implicitCacheStart = localOracleStatementCacheEntry;
    
/* 268 */     if (this.applicationCacheEnd == null) {
/* 269 */       this.applicationCacheEnd = localOracleStatementCacheEntry;
    }
    
/* 273 */     this.numElements += 1;
  }
  
  protected void addToExplicitCache(OraclePreparedStatement paramOraclePreparedStatement, String paramString)
    throws SQLException
  {
/* 291 */     if ((!this.explicitCacheEnabled) || (this.cacheSize == 0) || (paramOraclePreparedStatement.cacheState == 2))
    {
/* 296 */       return;
    }
    
/* 300 */     if (this.numElements == this.cacheSize) {
/* 301 */       purgeCacheEntry(this.applicationCacheEnd);
    }
    
/* 305 */     paramOraclePreparedStatement.enterExplicitCache();
    
/* 308 */     OracleStatementCacheEntry localOracleStatementCacheEntry = new OracleStatementCacheEntry();
    
/* 310 */     localOracleStatementCacheEntry.statement = paramOraclePreparedStatement;
/* 311 */     localOracleStatementCacheEntry.sql = paramString;
/* 312 */     localOracleStatementCacheEntry.onImplicit = false;
    
/* 315 */     localOracleStatementCacheEntry.applicationNext = this.applicationCacheStart;
/* 316 */     localOracleStatementCacheEntry.applicationPrev = null;
    
/* 318 */     if (this.applicationCacheStart != null) {
/* 319 */       this.applicationCacheStart.applicationPrev = localOracleStatementCacheEntry;
    }
/* 321 */     this.applicationCacheStart = localOracleStatementCacheEntry;
    
/* 323 */     localOracleStatementCacheEntry.explicitNext = this.explicitCacheStart;
/* 324 */     localOracleStatementCacheEntry.explicitPrev = null;
    
/* 326 */     if (this.explicitCacheStart != null) {
/* 327 */       this.explicitCacheStart.explicitPrev = localOracleStatementCacheEntry;
    }
/* 329 */     this.explicitCacheStart = localOracleStatementCacheEntry;
    
/* 332 */     if (this.applicationCacheEnd == null) {
/* 333 */       this.applicationCacheEnd = localOracleStatementCacheEntry;
    }
    
/* 337 */     this.numElements += 1;
  }
  
  protected OracleStatement searchImplicitCache(String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 360 */     if (!this.implicitCacheEnabled)
    {
/* 364 */       return null;
    }
    
/* 368 */     OracleStatementCacheEntry localOracleStatementCacheEntry = null;
    
/* 370 */     for (localOracleStatementCacheEntry = this.implicitCacheStart; localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.implicitNext)
    {
/* 372 */       if ((localOracleStatementCacheEntry.statementType == paramInt1) && (localOracleStatementCacheEntry.scrollType == paramInt2) && (localOracleStatementCacheEntry.sql.equals(paramString))) {
        break;
      }
    }
    
/* 377 */     if (localOracleStatementCacheEntry != null)
    {
/* 386 */       if (localOracleStatementCacheEntry.applicationPrev != null) {
/* 387 */         localOracleStatementCacheEntry.applicationPrev.applicationNext = localOracleStatementCacheEntry.applicationNext;
      }
/* 389 */       if (localOracleStatementCacheEntry.applicationNext != null) {
/* 390 */         localOracleStatementCacheEntry.applicationNext.applicationPrev = localOracleStatementCacheEntry.applicationPrev;
      }
/* 392 */       if (this.applicationCacheStart == localOracleStatementCacheEntry) {
/* 393 */         this.applicationCacheStart = localOracleStatementCacheEntry.applicationNext;
      }
/* 395 */       if (this.applicationCacheEnd == localOracleStatementCacheEntry) {
/* 396 */         this.applicationCacheEnd = localOracleStatementCacheEntry.applicationPrev;
      }
/* 398 */       if (localOracleStatementCacheEntry.implicitPrev != null) {
/* 399 */         localOracleStatementCacheEntry.implicitPrev.implicitNext = localOracleStatementCacheEntry.implicitNext;
      }
/* 401 */       if (localOracleStatementCacheEntry.implicitNext != null) {
/* 402 */         localOracleStatementCacheEntry.implicitNext.implicitPrev = localOracleStatementCacheEntry.implicitPrev;
      }
/* 404 */       if (this.implicitCacheStart == localOracleStatementCacheEntry) {
/* 405 */         this.implicitCacheStart = localOracleStatementCacheEntry.implicitNext;
      }
      
/* 409 */       this.numElements -= 1;
      
/* 412 */       localOracleStatementCacheEntry.statement.exitImplicitCacheToActive();
      
/* 416 */       return localOracleStatementCacheEntry.statement;
    }
    
/* 426 */     return null;
  }
  
  protected OracleStatement searchExplicitCache(String paramString)
    throws SQLException
  {
/* 445 */     if (!this.explicitCacheEnabled)
    {
/* 449 */       return null;
    }
    
/* 453 */     OracleStatementCacheEntry localOracleStatementCacheEntry = null;
    
/* 455 */     for (localOracleStatementCacheEntry = this.explicitCacheStart; localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.explicitNext)
    {
/* 457 */       if (localOracleStatementCacheEntry.sql.equals(paramString)) {
        break;
      }
    }
/* 461 */     if (localOracleStatementCacheEntry != null)
    {
/* 471 */       if (localOracleStatementCacheEntry.applicationPrev != null) {
/* 472 */         localOracleStatementCacheEntry.applicationPrev.applicationNext = localOracleStatementCacheEntry.applicationNext;
      }
/* 474 */       if (localOracleStatementCacheEntry.applicationNext != null) {
/* 475 */         localOracleStatementCacheEntry.applicationNext.applicationPrev = localOracleStatementCacheEntry.applicationPrev;
      }
/* 477 */       if (this.applicationCacheStart == localOracleStatementCacheEntry) {
/* 478 */         this.applicationCacheStart = localOracleStatementCacheEntry.applicationNext;
      }
/* 480 */       if (this.applicationCacheEnd == localOracleStatementCacheEntry) {
/* 481 */         this.applicationCacheEnd = localOracleStatementCacheEntry.applicationPrev;
      }
/* 483 */       if (localOracleStatementCacheEntry.explicitPrev != null) {
/* 484 */         localOracleStatementCacheEntry.explicitPrev.explicitNext = localOracleStatementCacheEntry.explicitNext;
      }
/* 486 */       if (localOracleStatementCacheEntry.explicitNext != null) {
/* 487 */         localOracleStatementCacheEntry.explicitNext.explicitPrev = localOracleStatementCacheEntry.explicitPrev;
      }
/* 489 */       if (this.explicitCacheStart == localOracleStatementCacheEntry) {
/* 490 */         this.explicitCacheStart = localOracleStatementCacheEntry.explicitNext;
      }
      
/* 494 */       this.numElements -= 1;
      
/* 497 */       localOracleStatementCacheEntry.statement.exitExplicitCacheToActive();
      
/* 499 */       return localOracleStatementCacheEntry.statement;
    }
    
/* 507 */     return null;
  }
  
  protected void purgeImplicitCache()
    throws SQLException
  {
/* 523 */     for (OracleStatementCacheEntry localOracleStatementCacheEntry = this.implicitCacheStart; localOracleStatementCacheEntry != null; 
/* 524 */         localOracleStatementCacheEntry = localOracleStatementCacheEntry.implicitNext) {
/* 525 */       purgeCacheEntry(localOracleStatementCacheEntry);
    }
/* 527 */     this.implicitCacheStart = null;
  }
  
  protected void purgeExplicitCache()
    throws SQLException
  {
/* 543 */     for (OracleStatementCacheEntry localOracleStatementCacheEntry = this.explicitCacheStart; localOracleStatementCacheEntry != null; 
/* 544 */         localOracleStatementCacheEntry = localOracleStatementCacheEntry.explicitNext) {
/* 545 */       purgeCacheEntry(localOracleStatementCacheEntry);
    }
/* 547 */     this.explicitCacheStart = null;
  }
  
  private void purgeCacheEntry(OracleStatementCacheEntry paramOracleStatementCacheEntry)
    throws SQLException
  {
/* 565 */     if (paramOracleStatementCacheEntry.applicationNext != null) {
/* 566 */       paramOracleStatementCacheEntry.applicationNext.applicationPrev = paramOracleStatementCacheEntry.applicationPrev;
    }
/* 568 */     if (paramOracleStatementCacheEntry.applicationPrev != null) {
/* 569 */       paramOracleStatementCacheEntry.applicationPrev.applicationNext = paramOracleStatementCacheEntry.applicationNext;
    }
/* 571 */     if (this.applicationCacheStart == paramOracleStatementCacheEntry) {
/* 572 */       this.applicationCacheStart = paramOracleStatementCacheEntry.applicationNext;
    }
/* 574 */     if (this.applicationCacheEnd == paramOracleStatementCacheEntry) {
/* 575 */       this.applicationCacheEnd = paramOracleStatementCacheEntry.applicationPrev;
    }
/* 577 */     if (paramOracleStatementCacheEntry.onImplicit)
    {
/* 579 */       if (paramOracleStatementCacheEntry.implicitNext != null) {
/* 580 */         paramOracleStatementCacheEntry.implicitNext.implicitPrev = paramOracleStatementCacheEntry.implicitPrev;
      }
/* 582 */       if (paramOracleStatementCacheEntry.implicitPrev != null) {
/* 583 */         paramOracleStatementCacheEntry.implicitPrev.implicitNext = paramOracleStatementCacheEntry.implicitNext;
      }
/* 585 */       if (this.implicitCacheStart == paramOracleStatementCacheEntry) {
/* 586 */         this.implicitCacheStart = paramOracleStatementCacheEntry.implicitNext;
      }
    }
    else {
/* 590 */       if (paramOracleStatementCacheEntry.explicitNext != null) {
/* 591 */         paramOracleStatementCacheEntry.explicitNext.explicitPrev = paramOracleStatementCacheEntry.explicitPrev;
      }
/* 593 */       if (paramOracleStatementCacheEntry.explicitPrev != null) {
/* 594 */         paramOracleStatementCacheEntry.explicitPrev.explicitNext = paramOracleStatementCacheEntry.explicitNext;
      }
/* 596 */       if (this.explicitCacheStart == paramOracleStatementCacheEntry) {
/* 597 */         this.explicitCacheStart = paramOracleStatementCacheEntry.explicitNext;
      }
    }
    
/* 601 */     this.numElements -= 1;
    
/* 604 */     if (paramOracleStatementCacheEntry.onImplicit) {
/* 605 */       paramOracleStatementCacheEntry.statement.exitImplicitCacheToClose();
    } else {
/* 607 */       paramOracleStatementCacheEntry.statement.exitExplicitCacheToClose();
    }
  }
  
  public int getCacheSize()
  {
/* 619 */     return this.cacheSize;
  }
  
  public void printCache(String paramString)
    throws SQLException
  {
/* 634 */     System.out.println("*** Start of Statement Cache Dump (" + paramString + ") ***");
/* 635 */     System.out.println("cache size: " + this.cacheSize + " num elements: " + this.numElements + " implicit enabled: " + this.implicitCacheEnabled + " explicit enabled: " + this.explicitCacheEnabled);
    
/* 639 */     System.out.println("applicationStart: " + this.applicationCacheStart + "  applicationEnd: " + this.applicationCacheEnd);
    
/* 642 */     for (OracleStatementCacheEntry localOracleStatementCacheEntry = this.applicationCacheStart; localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.applicationNext) {
/* 643 */       localOracleStatementCacheEntry.print();
    }
/* 645 */     System.out.println("implicitStart: " + this.implicitCacheStart);
    
/* 647 */     for (localOracleStatementCacheEntry = this.implicitCacheStart; localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.implicitNext) {
/* 648 */       localOracleStatementCacheEntry.print();
    }
/* 650 */     System.out.println("explicitStart: " + this.explicitCacheStart);
    
/* 652 */     for (localOracleStatementCacheEntry = this.explicitCacheStart; localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.explicitNext) {
/* 653 */       localOracleStatementCacheEntry.print();
    }
/* 655 */     System.out.println("*** End of Statement Cache Dump (" + paramString + ") ***");
  }
  
  public void close()
    throws SQLException
  {
/* 667 */     for (OracleStatementCacheEntry localOracleStatementCacheEntry = this.applicationCacheStart; 
/* 668 */         localOracleStatementCacheEntry != null; localOracleStatementCacheEntry = localOracleStatementCacheEntry.applicationNext)
    {
/* 672 */       if (localOracleStatementCacheEntry.onImplicit) {
/* 673 */         localOracleStatementCacheEntry.statement.exitImplicitCacheToClose();
      } else {
/* 675 */         localOracleStatementCacheEntry.statement.exitExplicitCacheToClose();
      }
    }
    
/* 681 */     this.applicationCacheStart = null;
/* 682 */     this.applicationCacheEnd = null;
/* 683 */     this.implicitCacheStart = null;
/* 684 */     this.explicitCacheStart = null;
/* 685 */     this.numElements = 0;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 700 */     return null;
  }
  
/* 705 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/LRUStatementCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */