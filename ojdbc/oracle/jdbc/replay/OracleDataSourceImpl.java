package oracle.jdbc.replay;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.naming.StringRefAddr;
import javax.naming.spi.ObjectFactory;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.proxy.ProxyFactory;
import oracle.jdbc.replay.driver.NonTxnReplayableArray;
import oracle.jdbc.replay.driver.NonTxnReplayableBase;
import oracle.jdbc.replay.driver.NonTxnReplayableBfile;
import oracle.jdbc.replay.driver.NonTxnReplayableBlob;
import oracle.jdbc.replay.driver.NonTxnReplayableClob;
import oracle.jdbc.replay.driver.NonTxnReplayableConnection;
import oracle.jdbc.replay.driver.NonTxnReplayableNClob;
import oracle.jdbc.replay.driver.NonTxnReplayableOpaque;
import oracle.jdbc.replay.driver.NonTxnReplayableOthers;
import oracle.jdbc.replay.driver.NonTxnReplayableRef;
import oracle.jdbc.replay.driver.NonTxnReplayableResultSet;
import oracle.jdbc.replay.driver.NonTxnReplayableStatement;
import oracle.jdbc.replay.driver.NonTxnReplayableStruct;
import oracle.jdbc.replay.driver.ReplayLoggerFactory;
import oracle.jdbc.replay.internal.ConnectionInitializationCallback;
import oracle.jdbc.replay.internal.ReplayableConnection;
public class OracleDataSourceImpl
  implements OracleDataSource, Serializable, Referenceable, ObjectFactory
{
  private static final long serialVersionUID = 5634196469087099680L;
  private static final String FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.OracleDataSourceImpl";
/*  70 */   private static final Logger RDS_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.OracleDataSourceImpl");
  
/*  73 */   private static ProxyFactory PROXY_FACTORY = null;
  
/*  75 */   private String user = null;
/*  76 */   private String password = null;
/*  77 */   private String url = null;
/*  78 */   private String serverName = null;
/*  79 */   private int portNumber = 0;
/*  80 */   private String databaseName = null;
/*  81 */   private String dataSourceName = null;
/*  82 */   private String description = null;
/*  83 */   private String networkProtocol = null;
/*  84 */   private String roleName = null;
  
/*  86 */   private final Properties connectionProperties = new Properties();
  
/*  89 */   private int maxStatements = 0;
/*  90 */   private boolean implicitCachingEnabled = false;
/*  91 */   private boolean explicitCachingEnabled = false;
  
/*  93 */   private ConnectionInitializationCallback connectionInitializationCallback = null;
  
/*  95 */   private AtomicBoolean isFirstConnection = new AtomicBoolean(true);
  
  private static final String RECONNECT_DELAY_PROPERTY = "AUTH_FAILOVER_DELAY";
  
  private static final String RECONNECT_RETRIES_PROPERTY = "AUTH_FAILOVER_RETRIES";
  
/* 103 */   private int reconnectDelay = 10;
  
/* 108 */   private int reconnectRetries = 18;
  
  private static final String CHECKSUM_PROPERTY = "oracle.jdbc.calculateChecksum";
  
  static
  {
/* 116 */     if (PROXY_FACTORY == null)
    {
/* 118 */       PROXY_FACTORY = ProxyFactory.createProxyFactory(new Class[] { NonTxnReplayableBase.class, NonTxnReplayableConnection.class, NonTxnReplayableStatement.class, NonTxnReplayableResultSet.class, NonTxnReplayableArray.class, NonTxnReplayableBfile.class, NonTxnReplayableBlob.class, NonTxnReplayableClob.class, NonTxnReplayableNClob.class, NonTxnReplayableOpaque.class, NonTxnReplayableRef.class, NonTxnReplayableStruct.class, NonTxnReplayableOthers.class });
    }
  }
  
  public Connection getConnection()
    throws SQLException
  {
/* 141 */     return getConnection(this.user, this.password);
  }
  
  public Connection getConnection(String paramString1, String paramString2)
    throws SQLException
  {
/* 158 */     return getConnectionInternal(paramString1, paramString2, true);
  }
  
  public Connection getConnectionNoProxy()
    throws SQLException
  {
/* 166 */     int i = 1;
/* 167 */     Connection localConnection = null;
/* 168 */     Object localObject1 = null;
    
    do
    {
      try
      {
/* 175 */         if (this.reconnectDelay > 0)
        {
/* 177 */           RDS_LOGGER.log(Level.FINER, "Reconnecting: DELAY for {0} seconds", Integer.valueOf(this.reconnectDelay));
          
/* 179 */           Thread.sleep(this.reconnectDelay * 1000);
        }
        
/* 182 */         RDS_LOGGER.log(Level.FINER, "Reconnecting: RETRY {0}", Integer.valueOf(i));
        
/* 185 */         localObject1 = null;
/* 186 */         localConnection = getConnectionInternal(this.user, this.password, false);
      }
      catch (InterruptedException localInterruptedException)
      {
/* 190 */         localConnection = null;
/* 191 */         localObject1 = localInterruptedException;
/* 192 */         RDS_LOGGER.log(Level.FINER, "Reconnect threw exception during DELAY: {0}", localInterruptedException);
      }
      catch (Exception localException)
      {
/* 197 */         localConnection = null;
/* 198 */         localObject1 = localException;
/* 199 */         RDS_LOGGER.log(Level.FINER, "Reconnect FAILED, exception: {0}", localException);
      }
      finally
      {
/* 204 */         if ((localConnection != null) && (localObject1 == null)) {
/* 205 */           return localConnection;
        }
/* 207 */         i++;
      }
      
/* 210 */     } while (i <= this.reconnectRetries);
    
/* 215 */     return null;
  }
  
  private Connection getConnectionInternal(String paramString1, String paramString2, boolean paramBoolean)
    throws SQLException
  {
/* 224 */     Connection localConnection = null;
    
/* 226 */     oracle.jdbc.pool.OracleDataSource localOracleDataSource = new oracle.jdbc.pool.OracleDataSource();
    
/* 228 */     localOracleDataSource.setUser(paramString1);
/* 229 */     localOracleDataSource.setPassword(paramString2);
/* 230 */     localOracleDataSource.setURL(getURL());
/* 231 */     setConnectionProperty("oracle.jdbc.calculateChecksum", "true");
/* 232 */     localOracleDataSource.setConnectionProperties(getConnectionProperties());
/* 233 */     localOracleDataSource.setMaxStatements(getMaxStatements());
/* 234 */     localOracleDataSource.setImplicitCachingEnabled(getImplicitCachingEnabled());
/* 235 */     localOracleDataSource.setExplicitCachingEnabled(getExplicitCachingEnabled());
    
/* 237 */     localConnection = localOracleDataSource.getConnection();
    Object localObject1;
/* 239 */     Object localObject2; if (this.isFirstConnection.get())
    {
/* 241 */       localObject1 = (OracleConnection)localConnection;
/* 242 */       localObject2 = ((OracleConnection)localObject1).getServerSessionInfo();
      
/* 244 */       String str1 = ((Properties)localObject2).getProperty("AUTH_FAILOVER_DELAY");
      
/* 246 */       if ((str1 != null) && (!"".equals(str1)))
      {
/* 248 */         int i = Integer.parseInt(str1);
/* 249 */         if (i > 0) {
/* 250 */           this.reconnectDelay = i;
        } else {
/* 252 */           RDS_LOGGER.log(Level.WARNING, "Server FAILOVER_DELAY: {0}, use driver default {1} seconds instead", new Object[] { Integer.valueOf(i), Integer.valueOf(10) });
        }
      }
      
/* 257 */       String str2 = ((Properties)localObject2).getProperty("AUTH_FAILOVER_RETRIES");
      
/* 259 */       if ((str2 != null) && (!"".equals(str2)))
      {
/* 261 */         j = Integer.parseInt(str2);
/* 262 */         if (j > 0) {
/* 263 */           this.reconnectRetries = j;
        } else {
/* 265 */           RDS_LOGGER.log(Level.WARNING, "Server FAILOVER_RETRIES: {0}, use driver default {1} instead", new Object[] { Integer.valueOf(j), Integer.valueOf(18) });
        }
      }
      
/* 271 */       int j = ((OracleConnection)localObject1).getVersionNumber();
/* 272 */       if (j < 11203) {
/* 273 */         throw DatabaseError.createSqlException(393);
      }
      
/* 276 */       this.isFirstConnection.set(false);
    }
    
/* 281 */     if (paramBoolean)
    {
/* 283 */       localObject1 = (Connection)PROXY_FACTORY.proxyFor(localConnection);
/* 284 */       localObject2 = (ReplayableConnection)localObject1;
/* 285 */       ((ReplayableConnection)localObject2).initialize(this);
      
/* 287 */       return (Connection)localObject1;
    }
    
/* 291 */     return localConnection;
  }
  
  public PrintWriter getLogWriter() throws SQLException
  {
/* 296 */     return null;
  }
  
  public int getLoginTimeout()
    throws SQLException
  {
/* 309 */     return 0;
  }
  
  public String getUser()
  {
/* 314 */     return this.user;
  }
  
  public void setUser(String paramString) throws SQLException
  {
/* 319 */     RDS_LOGGER.log(Level.FINEST, "user: {0}", paramString);
    
/* 321 */     this.user = paramString;
  }
  
  public void setPassword(String paramString) throws SQLException
  {
/* 326 */     this.password = paramString;
  }
  
  public String getURL()
  {
/* 331 */     return this.url;
  }
  
  public void setURL(String paramString) throws SQLException
  {
/* 336 */     RDS_LOGGER.log(Level.FINEST, "URL: {0}", paramString);
    
/* 338 */     this.url = paramString;
  }
  
  public void setServerName(String paramString)
    throws SQLException
  {
/* 349 */     RDS_LOGGER.log(Level.FINEST, "Server Name: {0}", paramString);
    
/* 351 */     this.serverName = paramString;
  }
  
  public String getServerName()
  {
/* 361 */     return this.serverName;
  }
  
  public void setPortNumber(int paramInt)
    throws SQLException
  {
/* 372 */     RDS_LOGGER.log(Level.FINEST, "Port Number: {0}", Integer.valueOf(paramInt));
    
/* 374 */     this.portNumber = paramInt;
  }
  
  public int getPortNumber()
  {
/* 384 */     return this.portNumber;
  }
  
  public void setDatabaseName(String paramString)
    throws SQLException
  {
/* 395 */     RDS_LOGGER.log(Level.FINEST, "Database Name : {0}", paramString);
    
/* 397 */     this.databaseName = paramString;
  }
  
  public String getDatabaseName()
  {
/* 407 */     return this.databaseName;
  }
  
  public void setDataSourceName(String paramString)
    throws SQLException
  {
/* 418 */     RDS_LOGGER.log(Level.FINEST, "DataSourceName : {0}", paramString);
/* 419 */     this.dataSourceName = paramString;
  }
  
  public String getDataSourceName()
  {
/* 429 */     return this.dataSourceName;
  }
  
  public void setDescription(String paramString)
    throws SQLException
  {
/* 440 */     RDS_LOGGER.log(Level.FINEST, "Description : {0}", paramString);
/* 441 */     this.description = paramString;
  }
  
  public String getDescription()
  {
/* 451 */     return this.description;
  }
  
  public void setNetworkProtocol(String paramString)
    throws SQLException
  {
/* 462 */     RDS_LOGGER.log(Level.FINEST, "networkProtocol : {0}", paramString);
/* 463 */     this.networkProtocol = paramString;
  }
  
  public String getNetworkProtocol()
  {
/* 473 */     return this.networkProtocol;
  }
  
  public void setRoleName(String paramString)
    throws SQLException
  {
/* 484 */     RDS_LOGGER.log(Level.FINEST, "RoleName : {0}", paramString);
    
/* 486 */     this.roleName = paramString;
  }
  
  public String getRoleName()
  {
/* 496 */     return this.roleName;
  }
  
  public void registerConnectionInitializationCallback(ConnectionInitializationCallback paramConnectionInitializationCallback)
    throws SQLException
  {
/* 510 */     RDS_LOGGER.finest("Connection Initialization Callback registered");
    
/* 512 */     if (paramConnectionInitializationCallback == null) {
/* 513 */       throw new NullPointerException("callback has to be non-null");
    }
/* 515 */     this.connectionInitializationCallback = paramConnectionInitializationCallback;
  }
  
  public void unregisterConnectionInitializationCallback(ConnectionInitializationCallback paramConnectionInitializationCallback)
    throws SQLException
  {
/* 528 */     RDS_LOGGER.finest("Connection Initialization Callback removed");
    
/* 530 */     this.connectionInitializationCallback = null;
  }
  
  public ConnectionInitializationCallback getConnectionInitializationCallback()
  {
/* 535 */     RDS_LOGGER.finest("connection initialization callback obtained");
    
/* 537 */     return this.connectionInitializationCallback;
  }
  
  public Properties getConnectionProperties()
  {
/* 547 */     return this.connectionProperties;
  }
  
  public String getConnectionProperty(String paramString)
  {
/* 560 */     return this.connectionProperties.getProperty(paramString);
  }
  
  public void setConnectionProperty(String paramString1, String paramString2)
    throws SQLException
  {
/* 578 */     RDS_LOGGER.log(Level.FINEST, "name: {0}, value: {1}", new Object[] { paramString1, paramString2 });
    
/* 581 */     if ((paramString2 == null) || (paramString2.equals(""))) {
/* 582 */       throw new IllegalArgumentException();
    }
/* 584 */     this.connectionProperties.setProperty(paramString1, paramString2);
  }
  
  public void setConnectionProperties(Properties paramProperties)
    throws SQLException
  {
/* 600 */     RDS_LOGGER.log(Level.FINEST, "ConnectionProperties: {0}", paramProperties);
    
/* 603 */     if (paramProperties.size() <= 0) {
/* 604 */       throw new IllegalArgumentException();
    }
    
/* 607 */     for (Map.Entry localEntry : paramProperties.entrySet()) {
/* 608 */       this.connectionProperties.setProperty((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }
  
  public void setMaxStatements(int paramInt)
    throws SQLException
  {
/* 628 */     this.maxStatements = paramInt;
  }
  
  public int getMaxStatements()
    throws SQLException
  {
/* 636 */     return this.maxStatements;
  }
  
  public void setImplicitCachingEnabled(boolean paramBoolean)
    throws SQLException
  {
/* 650 */     this.implicitCachingEnabled = paramBoolean;
  }
  
  public boolean getImplicitCachingEnabled()
    throws SQLException
  {
/* 660 */     return this.implicitCachingEnabled;
  }
  
  public void setExplicitCachingEnabled(boolean paramBoolean)
    throws SQLException
  {
/* 675 */     this.explicitCachingEnabled = paramBoolean;
  }
  
  public boolean getExplicitCachingEnabled()
    throws SQLException
  {
/* 683 */     return this.explicitCachingEnabled;
  }
  
  public Reference getReference()
    throws NamingException
  {
/* 691 */     Reference localReference = new Reference(getClass().getName(), "oracle.jdbc.replay.OracleDataSourceImpl", null);
    
/* 694 */     if (this.user != null) {
/* 695 */       localReference.add(new StringRefAddr("user", this.user));
    }
/* 697 */     if (this.password != null) {
/* 698 */       localReference.add(new StringRefAddr("password", this.password));
    }
/* 700 */     if (this.url != null) {
/* 701 */       localReference.add(new StringRefAddr("url", this.url));
    }
/* 703 */     if (this.serverName != null) {
/* 704 */       localReference.add(new StringRefAddr("serverName", this.serverName));
    }
/* 706 */     localReference.add(new StringRefAddr("portNumber", Integer.toString(this.portNumber)));
    
/* 709 */     if (this.databaseName != null) {
/* 710 */       localReference.add(new StringRefAddr("databaseName", this.databaseName));
    }
/* 712 */     if (this.dataSourceName != null) {
/* 713 */       localReference.add(new StringRefAddr("dataSourceName", this.dataSourceName.toString()));
    }
    
/* 716 */     if (this.description != null) {
/* 717 */       localReference.add(new StringRefAddr("description", this.description.toString()));
    }
    
/* 720 */     if (this.networkProtocol != null) {
/* 721 */       localReference.add(new StringRefAddr("networkProtocol", this.networkProtocol.toString()));
    }
    
/* 725 */     if (this.roleName != null) {
/* 726 */       localReference.add(new StringRefAddr("roleName", this.roleName));
    }
    
/* 730 */     if (this.connectionProperties.size() > 0) {
/* 731 */       localReference.add(new StringRefAddr("connectionProperties", this.connectionProperties.toString()));
    }
    
/* 735 */     if (this.maxStatements != 0) {
/* 736 */       localReference.add(new StringRefAddr("maxStatements", Integer.toString(this.maxStatements)));
    }
    
/* 739 */     if (this.implicitCachingEnabled) {
/* 740 */       localReference.add(new StringRefAddr("implicitCachingEnabled", "true"));
    }
/* 742 */     if (this.explicitCachingEnabled) {
/* 743 */       localReference.add(new StringRefAddr("explicitCachingEnabled", "true"));
    }
/* 745 */     return localReference;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 756 */     paramObjectOutputStream.defaultWriteObject();
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException, SQLException
  {
/* 767 */     paramObjectInputStream.defaultReadObject();
  }
  
  public Object getObjectInstance(Object paramObject, Name paramName, Context paramContext, Hashtable<?, ?> paramHashtable)
    throws Exception
  {
/* 776 */     Reference localReference = (Reference)paramObject;
/* 777 */     String str1 = localReference.getClassName();
/* 778 */     OracleDataSourceImpl localOracleDataSourceImpl = null;
    
/* 780 */     if ((str1.equals("oracle.jdbc.replay.OracleDataSource")) || (str1.equals("oracle.jdbc.replay.OracleDataSourceImpl")))
    {
/* 783 */       localOracleDataSourceImpl = new OracleDataSourceImpl();
    }
    
/* 786 */     if (localOracleDataSourceImpl == null) {
/* 787 */       return null;
    }
    
/* 790 */     StringRefAddr localStringRefAddr = (StringRefAddr)localReference.get("user");
/* 791 */     if (localStringRefAddr != null) {
/* 792 */       localOracleDataSourceImpl.setUser((String)localStringRefAddr.getContent());
    }
/* 794 */     localStringRefAddr = (StringRefAddr)localReference.get("password");
/* 795 */     if (localStringRefAddr != null) {
/* 796 */       localOracleDataSourceImpl.setPassword((String)localStringRefAddr.getContent());
    }
/* 798 */     localStringRefAddr = (StringRefAddr)localReference.get("url");
/* 799 */     if (localStringRefAddr != null) {
/* 800 */       localOracleDataSourceImpl.setURL((String)localStringRefAddr.getContent());
    }
/* 802 */     localStringRefAddr = (StringRefAddr)localReference.get("serverName");
/* 803 */     if (localStringRefAddr != null) {
/* 804 */       localOracleDataSourceImpl.setServerName((String)localStringRefAddr.getContent());
    }
/* 806 */     localStringRefAddr = (StringRefAddr)localReference.get("portNumber");
/* 807 */     if (localStringRefAddr != null) {
/* 808 */       localOracleDataSourceImpl.setPortNumber(Integer.parseInt((String)localStringRefAddr.getContent()));
    }
/* 810 */     localStringRefAddr = (StringRefAddr)localReference.get("databaseName");
/* 811 */     if (localStringRefAddr != null) {
/* 812 */       localOracleDataSourceImpl.setDatabaseName((String)localStringRefAddr.getContent());
    }
/* 814 */     localStringRefAddr = (StringRefAddr)localReference.get("dataSourceName");
/* 815 */     if (localStringRefAddr != null) {
/* 816 */       localOracleDataSourceImpl.setDataSourceName((String)localStringRefAddr.getContent());
    }
/* 818 */     localStringRefAddr = (StringRefAddr)localReference.get("description");
/* 819 */     if (localStringRefAddr != null) {
/* 820 */       localOracleDataSourceImpl.setDescription((String)localStringRefAddr.getContent());
    }
/* 822 */     localStringRefAddr = (StringRefAddr)localReference.get("networkProtocol");
/* 823 */     if (localStringRefAddr != null) {
/* 824 */       localOracleDataSourceImpl.setNetworkProtocol((String)localStringRefAddr.getContent());
    }
/* 826 */     localStringRefAddr = (StringRefAddr)localReference.get("roleName");
/* 827 */     if (localStringRefAddr != null) {
/* 828 */       localOracleDataSourceImpl.setRoleName((String)localStringRefAddr.getContent());
    }
/* 830 */     localStringRefAddr = (StringRefAddr)localReference.get("connectionProperties");
/* 831 */     String str2; if (localStringRefAddr != null)
    {
/* 833 */       str2 = (String)localStringRefAddr.getContent();
/* 834 */       Properties localProperties = new Properties();
      
/* 838 */       String[] arrayOfString1 = str2.substring(1, str2.length() - 1).split(", ");
      
/* 840 */       for (String str3 : arrayOfString1)
      {
/* 843 */         String[] arrayOfString3 = str3.split("=");
/* 844 */         localProperties.setProperty(arrayOfString3[0], arrayOfString3[1]);
      }
/* 846 */       localOracleDataSourceImpl.setConnectionProperties(localProperties);
    }
    
/* 849 */     localStringRefAddr = (StringRefAddr)localReference.get("maxStatements");
/* 850 */     if (localStringRefAddr != null) {
/* 851 */       localOracleDataSourceImpl.setMaxStatements(Integer.parseInt((String)localStringRefAddr.getContent()));
    }
/* 853 */     localStringRefAddr = (StringRefAddr)localReference.get("implicitCachingEnabled");
/* 854 */     if (localStringRefAddr != null)
    {
/* 856 */       str2 = (String)localStringRefAddr.getContent();
/* 857 */       if (str2.equalsIgnoreCase("true")) {
/* 858 */         localOracleDataSourceImpl.setImplicitCachingEnabled(true);
      } else {
/* 860 */         localOracleDataSourceImpl.setImplicitCachingEnabled(false);
      }
    }
/* 863 */     localStringRefAddr = (StringRefAddr)localReference.get("explicitCachingEnabled");
/* 864 */     if (localStringRefAddr != null)
    {
/* 866 */       str2 = (String)localStringRefAddr.getContent();
/* 867 */       if (str2.equalsIgnoreCase("true")) {
/* 868 */         localOracleDataSourceImpl.setExplicitCachingEnabled(true);
      } else {
/* 870 */         localOracleDataSourceImpl.setExplicitCachingEnabled(false);
      }
    }
/* 873 */     return localOracleDataSourceImpl;
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 890 */     return false;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 907 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this)))
/* 908 */       return this;
/* 909 */     return null;
  }
  
  public void setLogWriter(PrintWriter paramPrintWriter)
    throws SQLException
  {}
  
  public void setLoginTimeout(int paramInt)
    throws SQLException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/OracleDataSourceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */