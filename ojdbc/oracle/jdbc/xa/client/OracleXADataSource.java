package oracle.jdbc.xa.client;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import javax.transaction.xa.XAException;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.driver.T2CConnection;
import oracle.jdbc.driver.T4CXAConnection;
public class OracleXADataSource
  extends oracle.jdbc.xa.OracleXADataSource
{
  private static final boolean DEBUG = false;
/*  46 */   private int rmid = -1;
/*  47 */   private String xaOpenString = null;
/*  48 */   private static boolean libraryLoaded = false;
  
  private static final String dbSuffix = "HeteroXA";
  private static final String dllName = "heteroxa11";
  private static final char atSignChar = '@';
/*  53 */   private static int rmidSeed = 0;
  
  private static final int MAX_RMID_SEED = 65536;
  
/*  57 */   private String driverCharSetIdString = null;
  
/*  61 */   private String oldTnsEntry = null;
  
  public OracleXADataSource()
    throws SQLException
  {
/*  75 */     this.isOracleDataSource = true;
  }
  
  public XAConnection getXAConnection()
    throws SQLException
  {
/*  92 */     Properties localProperties = new Properties(this.connectionProperties);
    
/*  95 */     if ((this.user != null) && (this.password != null))
    {
/*  97 */       localProperties.setProperty("user", this.user);
/*  98 */       localProperties.setProperty("password", this.password);
    }
    
/* 101 */     return getXAConnection(localProperties);
  }
  
  public XAConnection getXAConnection(String paramString1, String paramString2)
    throws SQLException
  {
/* 118 */     Properties localProperties = new Properties(this.connectionProperties);
/* 119 */     if ((paramString1 != null) && (paramString2 != null))
    {
/* 121 */       localProperties.setProperty("user", paramString1);
/* 122 */       localProperties.setProperty("password", paramString2);
    }
    else {
/* 125 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 126 */       localSQLException.fillInStackTrace();
/* 127 */       throw localSQLException;
    }
    
/* 130 */     return getXAConnection(localProperties);
  }
  
  public XAConnection getXAConnection(Properties paramProperties)
    throws SQLException
  {
/* 148 */     if (this.connCachingEnabled)
    {
/* 151 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 163);
/* 152 */       localSQLException.fillInStackTrace();
/* 153 */       throw localSQLException;
    }
    
/* 156 */     return (XAConnection)getPooledConnection(paramProperties);
  }
  
  public PooledConnection getPooledConnection(String paramString1, String paramString2)
    throws SQLException
  {
/* 177 */     Properties localProperties = new Properties();
/* 178 */     localProperties.setProperty("user", paramString1);
/* 179 */     localProperties.setProperty("password", paramString2);
    
/* 181 */     return getPooledConnection(localProperties);
  }
  
  public PooledConnection getPooledConnection(Properties paramProperties)
    throws SQLException
  {
    try
    {
/* 205 */       String str1 = getURL();
/* 206 */       String str2 = paramProperties.getProperty("user");
/* 207 */       String str3 = paramProperties.getProperty("password");
/* 208 */       String str4 = null;
/* 209 */       String str5 = null;
/* 210 */       String str6 = null;
/* 211 */       int i = 0;
      
/* 216 */       if ((this.useNativeXA) && ((str1.startsWith("jdbc:oracle:oci8")) || (str1.startsWith("jdbc:oracle:oci"))))
      {
/* 220 */         localObject1 = new long[] { 0L, 0L };
        
/* 225 */         String str7 = null;
/* 226 */         String str8 = null;
        
/* 228 */         synchronized (this)
        {
/* 231 */           if (this.tnsEntry != null) {
/* 232 */             str7 = this.tnsEntry;
          } else {
/* 234 */             str7 = getTNSEntryFromUrl(str1);
          }
          
/* 237 */           if (((str7 != null) && (str7.length() == 0)) || (str7.startsWith("(DESCRIPTION")))
          {
/* 242 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 207);
/* 243 */             localSQLException.fillInStackTrace();
/* 244 */             throw localSQLException;
          }
          
/* 248 */           if (!libraryLoaded)
          {
/* 250 */             synchronized (OracleXADataSource.class)
            {
/* 252 */               if (!libraryLoaded)
              {
                try
                {
/* 256 */                   System.loadLibrary("heteroxa11");
                  
/* 258 */                   libraryLoaded = true;
                }
                catch (Error localError)
                {
/* 266 */                   libraryLoaded = false;
                  
/* 268 */                   throw localError;
                }
              }
            }
          }
          
/* 277 */           if (this.connectionProperties != null)
          {
/* 279 */             str8 = this.connectionProperties.getProperty("oracle.jdbc.ociNlsLangBackwardCompatible");
          }
        }
        
/* 284 */         if ((str8 != null) && (str8.equalsIgnoreCase("true")))
        {
/* 291 */           ??? = T2CConnection.getDriverCharSetIdFromNLS_LANG();
/* 292 */           this.driverCharSetIdString = Integer.toString(???);
        }
/* 296 */         else if (!str7.equals(this.oldTnsEntry))
        {
/* 299 */           ??? = T2CConnection.getClientCharSetId();
          
/* 301 */           this.driverCharSetIdString = Integer.toString(???);
/* 302 */           this.oldTnsEntry = str7;
        }
        
/* 306 */         synchronized (this)
        {
/* 312 */           str4 = this.databaseName + "HeteroXA" + rmidSeed;
          
/* 314 */           this.rmid = (i = rmidSeed);
          
/* 316 */           synchronized (OracleXADataSource.class)
          {
/* 318 */             rmidSeed = (rmidSeed + 1) % 65536;
          }
          
/* 321 */           int k = 0;
          
/* 336 */           localOracleXAHeteroConnection = this.connectionProperties != null ? this.connectionProperties.getProperty("oracle.jdbc.XATransLoose") : null;
          
/* 341 */           this.xaOpenString = (str6 = generateXAOpenString(str4, str7, str2, str3, 60, 2000, true, true, ".", k, false, (localOracleXAHeteroConnection != null) && (localOracleXAHeteroConnection.equalsIgnoreCase("true")), this.driverCharSetIdString, this.driverCharSetIdString));
          
/* 349 */           str5 = generateXACloseString(str4, false);
        }
        
/* 354 */         int j = t2cDoXaOpen(str6, i, 0, 0);
        
/* 357 */         if (j != 0)
        {
/* 360 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), -1 * j);
/* 361 */           ((SQLException)localObject2).fillInStackTrace();
/* 362 */           throw ((Throwable)localObject2);
        }
        
/* 372 */         j = t2cConvertOciHandles(str4, (long[])localObject1);
        
/* 374 */         if (j != 0)
        {
/* 379 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), -1 * j);
/* 380 */           ((SQLException)localObject2).fillInStackTrace();
/* 381 */           throw ((Throwable)localObject2);
        }
        
/* 392 */         paramProperties.put("OCISvcCtxHandle", String.valueOf(localObject1[0]));
/* 393 */         paramProperties.put("OCIEnvHandle", String.valueOf(localObject1[1]));
/* 394 */         paramProperties.put("JDBCDriverCharSetId", this.driverCharSetIdString);
        
/* 396 */         if (this.loginTimeout != 0) {
/* 397 */           paramProperties.put("oracle.net.CONNECT_TIMEOUT", "" + this.loginTimeout * 1000);
        }
        
/* 402 */         Object localObject2 = this.driver.connect(getURL(), paramProperties);
        
/* 405 */         ((OracleConnection)localObject2).setStatementCacheSize(this.maxStatements);
/* 406 */         ((OracleConnection)localObject2).setExplicitCachingEnabled(this.explicitCachingEnabled);
/* 407 */         ((OracleConnection)localObject2).setImplicitCachingEnabled(this.implicitCachingEnabled);
        
/* 410 */         if ((this.maxStatements > 0) && (!this.explicitCachingEnabled) && (!this.implicitCachingEnabled))
        {
/* 414 */           ((OracleConnection)localObject2).setImplicitCachingEnabled(true);
/* 415 */           ((OracleConnection)localObject2).setExplicitCachingEnabled(true);
        }
        
/* 420 */         OracleXAHeteroConnection localOracleXAHeteroConnection = new OracleXAHeteroConnection((Connection)localObject2);
        
/* 422 */         if ((str2 != null) && (str3 != null))
/* 423 */           localOracleXAHeteroConnection.setUserName(str2, str3);
/* 424 */         localOracleXAHeteroConnection.setRmid(i);
/* 425 */         localOracleXAHeteroConnection.setXaCloseString(str5);
/* 426 */         localOracleXAHeteroConnection.registerCloseCallback(new OracleXAHeteroCloseCallback(), localOracleXAHeteroConnection);
        
/* 428 */         return localOracleXAHeteroConnection;
      }
/* 430 */       if ((this.thinUseNativeXA) && (str1.startsWith("jdbc:oracle:thin")))
      {
/* 437 */         localObject1 = new Properties();
/* 438 */         synchronized (this)
        {
/* 440 */           synchronized (OracleXADataSource.class)
          {
/* 442 */             rmidSeed = (rmidSeed + 1) % 65536;
            
/* 444 */             this.rmid = rmidSeed;
          }
          
/* 448 */           if (this.connectionProperties == null) {
/* 449 */             this.connectionProperties = new Properties();
          }
/* 451 */           this.connectionProperties.put("RessourceManagerId", Integer.toString(this.rmid));
/* 452 */           if (str2 != null)
/* 453 */             ((Properties)localObject1).setProperty("user", str2);
/* 454 */           if (str3 != null)
/* 455 */             ((Properties)localObject1).setProperty("password", str3);
/* 456 */           ((Properties)localObject1).setProperty("stmt_cache_size", "" + this.maxStatements);
          
/* 458 */           ((Properties)localObject1).setProperty("ImplicitStatementCachingEnabled", "" + this.implicitCachingEnabled);
          
/* 461 */           ((Properties)localObject1).setProperty("ExplicitStatementCachingEnabled", "" + this.explicitCachingEnabled);
          
/* 464 */           if (this.loginTimeout != 0)
          {
/* 466 */             ((Properties)localObject1).setProperty("LoginTimeout", "" + this.loginTimeout);
          }
        }
        
/* 472 */         ??? = new T4CXAConnection(super.getPhysicalConnection((Properties)localObject1));
        
/* 478 */         if ((str2 != null) && (str3 != null)) {
/* 479 */           ((T4CXAConnection)???).setUserName(str2, str3);
        }
/* 481 */         ??? = this.connectionProperties != null ? this.connectionProperties.getProperty("oracle.jdbc.XATransLoose") : null;
        
/* 486 */         ((OracleXAConnection)???).isXAResourceTransLoose = ((??? != null) && ((((String)???).equals("true")) || (((String)???).equalsIgnoreCase("true"))));
        
/* 489 */         return (PooledConnection)???;
      }
      
/* 495 */       Object localObject1 = new Properties();
/* 496 */       synchronized (this)
      {
/* 498 */         if (str2 != null)
/* 499 */           ((Properties)localObject1).setProperty("user", str2);
/* 500 */         if (str3 != null)
/* 501 */           ((Properties)localObject1).setProperty("password", str3);
/* 502 */         ((Properties)localObject1).setProperty("stmt_cache_size", "" + this.maxStatements);
        
/* 504 */         ((Properties)localObject1).setProperty("ImplicitStatementCachingEnabled", "" + this.implicitCachingEnabled);
        
/* 507 */         ((Properties)localObject1).setProperty("ExplicitStatementCachingEnabled", "" + this.explicitCachingEnabled);
        
/* 510 */         if (this.loginTimeout != 0)
        {
/* 512 */           ((Properties)localObject1).setProperty("LoginTimeout", "" + this.loginTimeout);
        }
      }
      
/* 518 */       ??? = new OracleXAConnection(super.getPhysicalConnection((Properties)localObject1));
      
/* 523 */       if ((str2 != null) && (str3 != null)) {
/* 524 */         ((OracleXAConnection)???).setUserName(str2, str3);
      }
/* 526 */       ??? = this.connectionProperties != null ? this.connectionProperties.getProperty("oracle.jdbc.XATransLoose") : null;
      
/* 531 */       ((OracleXAConnection)???).isXAResourceTransLoose = ((??? != null) && ((((String)???).equals("true")) || (((String)???).equalsIgnoreCase("true"))));
      
/* 535 */       return (PooledConnection)???;
    }
    catch (XAException localXAException) {}
    
/* 545 */     return null;
  }
  
  private native int t2cDoXaOpen(String paramString, int paramInt1, int paramInt2, int paramInt3);
  
  private native int t2cConvertOciHandles(String paramString, long[] paramArrayOfLong);
  
  synchronized void setRmid(int paramInt)
  {
/* 570 */     this.rmid = paramInt;
  }
  
  synchronized int getRmid()
  {
/* 585 */     return this.rmid;
  }
  
  synchronized void setXaOpenString(String paramString)
  {
/* 601 */     this.xaOpenString = paramString;
  }
  
  synchronized String getXaOpenString()
  {
/* 616 */     return this.xaOpenString;
  }
  
  private String generateXAOpenString(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, String paramString5, int paramInt3, boolean paramBoolean3, boolean paramBoolean4, String paramString6, String paramString7)
  {
/* 638 */     return "ORACLE_XA+DB=" + paramString1 + "+ACC=P/" + paramString3 + "/" + paramString4 + "+SESTM=" + paramInt2 + "+SESWT=" + paramInt1 + "+LOGDIR=" + paramString5 + "+SQLNET=" + paramString2 + (paramBoolean1 ? "+THREADS=true" : "") + (paramBoolean2 ? "+OBJECTS=true" : "") + "+DBGFL=0x" + paramInt3 + (paramBoolean3 ? "+CONNCACHE=t" : "+CONNCACHE=f") + (paramBoolean4 ? "+Loose_Coupling=t" : "") + "+CharSet=" + paramString6 + "+NCharSet=" + paramString7;
  }
  
  private String generateXACloseString(String paramString, boolean paramBoolean)
  {
/* 654 */     return "ORACLE_XA+DB=" + paramString + (paramBoolean ? "+CONNCACHE=t" : "+CONNCACHE=f");
  }
  
  private String getTNSEntryFromUrl(String paramString)
  {
/* 663 */     int i = paramString.indexOf('@');
    
/* 665 */     return paramString.substring(i + 1);
  }
  
/* 670 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXADataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */