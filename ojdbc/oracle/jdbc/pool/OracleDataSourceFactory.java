package oracle.jdbc.pool;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.xa.client.OracleXADataSource;
public class OracleDataSourceFactory
  implements ObjectFactory
{
  private static final String CONNECTION_CACHING_ENABLED = "connectionCachingEnabled";
  private static final String CONNECTION_CACHE_NAME = "connectionCacheName";
  private static final String CONNECTION_CACHE_PROPERTIES = "connectionCacheProperties";
  private static final String CONNECTION_PROPERTIES = "connectionProperties";
  private static final String FAST_CONNECTION_FAILOVER_ENABLED = "fastConnectionFailoverEnabled";
  private static final String ONS_CONFIG_STR = "onsConfigStr";
  private static final String ORACLE_CONN_DATA_POOL_SOURCE = "oracle.jdbc.pool.OracleConnectionPoolDataSource";
  private static final String ORACLE_OCI_CONN_POOL = "oracle.jdbc.pool.OracleOCIConnectionPool";
  private static final String ORACLE_DATA_SOURCE = "oracle.jdbc.pool.OracleDataSource";
  private static final String ORACLE_XA_DATA_SOURCE = "oracle.jdbc.xa.client.OracleXADataSource";
  
  public Object getObjectInstance(Object paramObject, Name paramName, Context paramContext, Hashtable paramHashtable)
    throws Exception
  {
/*  63 */     Reference localReference = (Reference)paramObject;
/*  64 */     Object localObject1 = null;
/*  65 */     String str1 = localReference.getClassName();
/*  66 */     Properties localProperties = new Properties();
    Object localObject2;
/*  68 */     String str2; Object localObject3; if ((str1.equals("oracle.jdbc.pool.OracleDataSource")) || (str1.equals("oracle.jdbc.xa.client.OracleXADataSource")))
    {
/*  71 */       if (str1.equals("oracle.jdbc.pool.OracleDataSource")) {
/*  72 */         localObject1 = new OracleDataSource();
      }
      else {
/*  75 */         localObject1 = new OracleXADataSource();
      }
      
/*  78 */       localObject2 = null;
      
/*  81 */       if ((localObject2 = (StringRefAddr)localReference.get("connectionCachingEnabled")) != null) {
/*  82 */         str2 = (String)((StringRefAddr)localObject2).getContent();
        
/*  84 */         if (str2.equals(String.valueOf("true"))) {
/*  85 */           ((OracleDataSource)localObject1).setConnectionCachingEnabled(true);
        }
      }
      
/*  89 */       if ((localObject2 = (StringRefAddr)localReference.get("connectionCacheName")) != null) {
/*  90 */         ((OracleDataSource)localObject1).setConnectionCacheName((String)((StringRefAddr)localObject2).getContent());
      }
      
/*  93 */       if ((localObject2 = (StringRefAddr)localReference.get("connectionCacheProperties")) != null) {
/*  94 */         str2 = (String)((StringRefAddr)localObject2).getContent();
/*  95 */         localObject3 = extractConnectionCacheProperties(str2);
/*  96 */         ((OracleDataSource)localObject1).setConnectionCacheProperties((Properties)localObject3);
      }
      
/*  99 */       if ((localObject2 = (StringRefAddr)localReference.get("connectionProperties")) != null) {
/* 100 */         str2 = (String)((StringRefAddr)localObject2).getContent();
/* 101 */         localObject3 = extractConnectionProperties(str2);
/* 102 */         ((OracleDataSource)localObject1).setConnectionProperties((Properties)localObject3);
      }
      
/* 105 */       if ((localObject2 = (StringRefAddr)localReference.get("fastConnectionFailoverEnabled")) != null)
      {
/* 107 */         str2 = (String)((StringRefAddr)localObject2).getContent();
        
/* 109 */         if (str2.equals(String.valueOf("true"))) {
/* 110 */           ((OracleDataSource)localObject1).setFastConnectionFailoverEnabled(true);
        }
      }
      
/* 114 */       if ((localObject2 = (StringRefAddr)localReference.get("onsConfigStr")) != null) {
/* 115 */         ((OracleDataSource)localObject1).setONSConfiguration((String)((StringRefAddr)localObject2).getContent());
      }
    }
/* 118 */     else if (str1.equals("oracle.jdbc.pool.OracleConnectionPoolDataSource")) {
/* 119 */       localObject1 = new OracleConnectionPoolDataSource();
    }
/* 121 */     else if (str1.equals("oracle.jdbc.pool.OracleOCIConnectionPool")) {
/* 122 */       localObject1 = new OracleOCIConnectionPool();
      
/* 124 */       localObject2 = null;
/* 125 */       str2 = null;
/* 126 */       localObject3 = null;
/* 127 */       String str3 = null;
/* 128 */       String str4 = null;
/* 129 */       String str5 = null;
/* 130 */       String str6 = null;
/* 131 */       StringRefAddr localStringRefAddr = null;
      
/* 133 */       Object localObject4 = null;
/* 134 */       String str7 = null;
      
/* 138 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_min_limit")) != null)
      {
/* 140 */         localObject2 = (String)localStringRefAddr.getContent();
      }
      
/* 143 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_max_limit")) != null)
      {
/* 145 */         str2 = (String)localStringRefAddr.getContent();
      }
      
/* 148 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_increment")) != null)
      {
/* 150 */         localObject3 = (String)localStringRefAddr.getContent();
      }
      
/* 153 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_active_size")) != null)
      {
/* 155 */         str3 = (String)localStringRefAddr.getContent();
      }
      
/* 158 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_pool_size")) != null)
      {
/* 160 */         str4 = (String)localStringRefAddr.getContent();
      }
      
/* 163 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_timeout")) != null)
      {
/* 165 */         str5 = (String)localStringRefAddr.getContent();
      }
      
/* 168 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("connpool_nowait")) != null)
      {
/* 170 */         str6 = (String)localStringRefAddr.getContent();
      }
      
/* 173 */       if ((localStringRefAddr = (StringRefAddr)localReference.get("transactions_distributed")) != null)
      {
/* 175 */         str7 = (String)localStringRefAddr.getContent();
      }
      
/* 180 */       localProperties.put("connpool_min_limit", localObject2);
/* 181 */       localProperties.put("connpool_max_limit", str2);
/* 182 */       localProperties.put("connpool_increment", localObject3);
/* 183 */       localProperties.put("connpool_active_size", str3);
      
/* 185 */       localProperties.put("connpool_pool_size", str4);
/* 186 */       localProperties.put("connpool_timeout", str5);
      
/* 188 */       if (str6 == "true") {
/* 189 */         localProperties.put("connpool_nowait", str6);
      }
      
/* 192 */       if (str7 == "true") {
/* 193 */         localProperties.put("transactions_distributed", str7);
      }
      
    }
    else
    {
/* 200 */       return null;
    }
    
/* 203 */     if (localObject1 != null)
    {
/* 205 */       localObject2 = null;
      
/* 207 */       if ((localObject2 = (StringRefAddr)localReference.get("url")) != null)
      {
/* 209 */         ((OracleDataSource)localObject1).setURL((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 212 */       if (((localObject2 = (StringRefAddr)localReference.get("userName")) != null) || ((localObject2 = (StringRefAddr)localReference.get("u")) != null) || ((localObject2 = (StringRefAddr)localReference.get("user")) != null))
      {
/* 215 */         ((OracleDataSource)localObject1).setUser((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 218 */       if (((localObject2 = (StringRefAddr)localReference.get("passWord")) != null) || ((localObject2 = (StringRefAddr)localReference.get("password")) != null))
      {
/* 220 */         ((OracleDataSource)localObject1).setPassword((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 223 */       if (((localObject2 = (StringRefAddr)localReference.get("description")) != null) || ((localObject2 = (StringRefAddr)localReference.get("describe")) != null))
      {
/* 225 */         ((OracleDataSource)localObject1).setDescription((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 228 */       if (((localObject2 = (StringRefAddr)localReference.get("driverType")) != null) || ((localObject2 = (StringRefAddr)localReference.get("driver")) != null))
      {
/* 230 */         ((OracleDataSource)localObject1).setDriverType((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 233 */       if (((localObject2 = (StringRefAddr)localReference.get("serverName")) != null) || ((localObject2 = (StringRefAddr)localReference.get("host")) != null))
      {
/* 235 */         ((OracleDataSource)localObject1).setServerName((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 238 */       if (((localObject2 = (StringRefAddr)localReference.get("databaseName")) != null) || ((localObject2 = (StringRefAddr)localReference.get("sid")) != null))
      {
/* 240 */         ((OracleDataSource)localObject1).setDatabaseName((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 243 */       if ((localObject2 = (StringRefAddr)localReference.get("serviceName")) != null) {
/* 244 */         ((OracleDataSource)localObject1).setServiceName((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 247 */       if (((localObject2 = (StringRefAddr)localReference.get("networkProtocol")) != null) || ((localObject2 = (StringRefAddr)localReference.get("protocol")) != null))
      {
/* 249 */         ((OracleDataSource)localObject1).setNetworkProtocol((String)((StringRefAddr)localObject2).getContent());
      }
      
/* 252 */       if (((localObject2 = (StringRefAddr)localReference.get("portNumber")) != null) || ((localObject2 = (StringRefAddr)localReference.get("port")) != null))
      {
/* 254 */         str2 = (String)((StringRefAddr)localObject2).getContent();
        
/* 256 */         ((OracleDataSource)localObject1).setPortNumber(Integer.parseInt(str2));
      }
      
/* 259 */       if (((localObject2 = (StringRefAddr)localReference.get("tnsentryname")) != null) || ((localObject2 = (StringRefAddr)localReference.get("tns")) != null))
      {
/* 261 */         ((OracleDataSource)localObject1).setTNSEntryName((String)((StringRefAddr)localObject2).getContent());
      }
/* 263 */       else if (str1.equals("oracle.jdbc.pool.OracleOCIConnectionPool"))
      {
/* 278 */         str2 = null;
        
/* 280 */         if ((localObject2 = (StringRefAddr)localReference.get("connpool_is_poolcreated")) != null)
        {
/* 282 */           str2 = (String)((StringRefAddr)localObject2).getContent();
        }
        
/* 285 */         if (str2.equals(String.valueOf("true"))) {
/* 286 */           ((OracleOCIConnectionPool)localObject1).setPoolConfig(localProperties);
        }
      }
    }
    
/* 291 */     return localObject1;
  }
  
  private Properties extractConnectionCacheProperties(String paramString)
    throws SQLException
  {
/* 312 */     Properties localProperties = new Properties();
    
/* 315 */     paramString = paramString.substring(1, paramString.length() - 1);
    
/* 318 */     int i = paramString.indexOf("AttributeWeights", 0);
    String str1;
/* 320 */     String str3; if (i >= 0)
    {
/* 329 */       if ((paramString.charAt(i + 16) != '=') || ((i > 0) && (paramString.charAt(i - 1) != ' ')))
      {
/* 333 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 139);
        
/* 335 */         ((SQLException)localObject1).fillInStackTrace();
/* 336 */         throw ((Throwable)localObject1);
      }
      
/* 340 */       localObject1 = new Properties();
/* 341 */       int j = paramString.indexOf("}", i);
/* 342 */       str1 = paramString.substring(i, j);
      
/* 345 */       String str2 = str1.substring(18);
      
/* 347 */       StringTokenizer localStringTokenizer = new StringTokenizer(str2, ", ");
      
/* 350 */       synchronized (localStringTokenizer)
      {
/* 352 */         while (localStringTokenizer.hasMoreTokens())
        {
/* 354 */           str3 = localStringTokenizer.nextToken();
/* 355 */           int n = str3.length();
/* 356 */           int i1 = str3.indexOf("=");
          
/* 358 */           String str4 = str3.substring(0, i1);
/* 359 */           String str5 = str3.substring(i1 + 1, n);
          
/* 361 */           ((Properties)localObject1).setProperty(str4, str5);
        }
      }
      
/* 365 */       localProperties.put("AttributeWeights", localObject1);
      
/* 368 */       if ((i > 0) && (j + 1 == paramString.length()))
      {
/* 370 */         paramString = paramString.substring(0, i - 2);
      }
/* 372 */       else if ((i > 0) && (j + 1 < paramString.length()))
      {
/* 374 */         ??? = paramString.substring(0, i - 2);
/* 375 */         str3 = paramString.substring(j + 1, paramString.length());
        
/* 377 */         paramString = ((String)???).concat(str3);
      }
      else
      {
/* 381 */         paramString = paramString.substring(j + 2, paramString.length());
      }
    }
    
/* 386 */     Object localObject1 = new StringTokenizer(paramString, ", ");
    
/* 388 */     synchronized (localObject1)
    {
/* 390 */       while (((StringTokenizer)localObject1).hasMoreTokens())
      {
/* 392 */         str1 = ((StringTokenizer)localObject1).nextToken();
/* 393 */         int k = str1.length();
/* 394 */         int m = str1.indexOf("=");
        
/* 396 */         ??? = str1.substring(0, m);
/* 397 */         str3 = str1.substring(m + 1, k);
        
/* 399 */         localProperties.setProperty((String)???, str3);
      }
    }
/* 402 */     return localProperties;
  }
  
  private Properties extractConnectionProperties(String paramString)
    throws SQLException
  {
/* 411 */     Properties localProperties = new Properties();
    
/* 414 */     paramString = paramString.substring(1, paramString.length() - 1);
    
/* 416 */     String[] arrayOfString1 = paramString.split(";");
    
/* 418 */     for (String str1 : arrayOfString1) {
/* 419 */       int k = str1.length();
/* 420 */       int m = str1.indexOf("=");
      
/* 422 */       if ((k == 0) || (m <= 0))
      {
/* 424 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 190);
        
/* 426 */         ((SQLException)localObject).fillInStackTrace();
/* 427 */         throw ((Throwable)localObject);
      }
      
/* 431 */       Object localObject = str1.substring(0, m);
/* 432 */       String str2 = str1.substring(m + 1, k);
      
/* 434 */       localProperties.setProperty(((String)localObject).trim(), str2.trim());
    }
/* 436 */     return localProperties;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 451 */     return null;
  }
  
/* 456 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/pool/OracleDataSourceFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */