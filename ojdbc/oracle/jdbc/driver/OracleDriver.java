package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import oracle.jdbc.OracleDatabaseMetaData;
import oracle.jdbc.internal.OracleConnection;
public class OracleDriver
  implements Driver
{
  public static final String oracle_string = "oracle";
  public static final String jdbc_string = "jdbc";
  public static final String protocol_string = "protocol";
  public static final String user_string = "user";
  public static final String password_string = "password";
  public static final String database_string = "database";
  public static final String server_string = "server";
  /**
   * @deprecated
   */
  public static final String access_string = "access";
  /**
   * @deprecated
   */
  public static final String protocolFullName_string = "protocolFullName";
  public static final String logon_as_internal_str = "internal_logon";
  public static final String proxy_client_name = "oracle.jdbc.proxyClientName";
  public static final String prefetch_string = "prefetch";
  public static final String row_prefetch_string = "rowPrefetch";
  public static final String default_row_prefetch_string = "defaultRowPrefetch";
  public static final String batch_string = "batch";
  public static final String execute_batch_string = "executeBatch";
  public static final String default_execute_batch_string = "defaultExecuteBatch";
  public static final String process_escapes_string = "processEscapes";
  public static final String accumulate_batch_result = "AccumulateBatchResult";
  public static final String j2ee_compliance = "oracle.jdbc.J2EE13Compliant";
  public static final String v8compatible_string = "V8Compatible";
  public static final String permit_timestamp_date_mismatch_string = "oracle.jdbc.internal.permitBindDateDefineTimestampMismatch";
  public static final String StreamChunkSize_string = "oracle.jdbc.StreamChunkSize";
  public static final String prelim_auth_string = "prelim_auth";
  public static final String SetFloatAndDoubleUseBinary_string = "SetFloatAndDoubleUseBinary";
  /**
   * @deprecated
   */
  public static final String xa_trans_loose = "oracle.jdbc.XATransLoose";
  public static final String tcp_no_delay = "oracle.jdbc.TcpNoDelay";
  public static final String read_timeout = "oracle.jdbc.ReadTimeout";
  public static final String defaultnchar_string = "oracle.jdbc.defaultNChar";
  public static final String defaultncharprop_string = "defaultNChar";
  public static final String useFetchSizeWithLongColumn_prop_string = "useFetchSizeWithLongColumn";
  public static final String useFetchSizeWithLongColumn_string = "oracle.jdbc.useFetchSizeWithLongColumn";
  public static final String remarks_string = "remarks";
  public static final String report_remarks_string = "remarksReporting";
  public static final String synonyms_string = "synonyms";
  public static final String include_synonyms_string = "includeSynonyms";
  public static final String restrict_getTables_string = "restrictGetTables";
  public static final String fixed_string_string = "fixedString";
  public static final String dll_string = "oracle.jdbc.ocinativelibrary";
  public static final String nls_lang_backdoor = "oracle.jdbc.ociNlsLangBackwardCompatible";
  public static final String disable_defineColumnType_string = "disableDefineColumnType";
  public static final String convert_nchar_literals_string = "oracle.jdbc.convertNcharLiterals";
  public static final String dataSizeUnitsPropertyName = "";
  public static final String dataSizeBytes = "";
  public static final String dataSizeChars = "";
  public static final String set_new_password_string = "OCINewPassword";
  public static final String retain_v9_bind_behavior_string = "oracle.jdbc.RetainV9LongBindBehavior";
  public static final String no_caching_buffers = "oracle.jdbc.FreeMemoryOnEnterImplicitCache";
  static final int EXTENSION_TYPE_ORACLE_ERROR = -3;
  static final int EXTENSION_TYPE_GEN_ERROR = -2;
  static final int EXTENSION_TYPE_TYPE4_CLIENT = 0;
  static final int EXTENSION_TYPE_TYPE4_SERVER = 1;
  static final int EXTENSION_TYPE_TYPE2_CLIENT = 2;
  static final int EXTENSION_TYPE_TYPE2_SERVER = 3;
  private static final int NUMBER_OF_EXTENSION_TYPES = 4;
/* 144 */   private OracleDriverExtension[] driverExtensions = new OracleDriverExtension[4];
  
  private static final String DRIVER_PACKAGE_STRING = "driver";
  
/* 160 */   private static final String[] driverExtensionClassNames = { "oracle.jdbc.driver.T4CDriverExtension", "oracle.jdbc.driver.T4CDriverExtension", "oracle.jdbc.driver.T2CDriverExtension", "oracle.jdbc.driver.T2SDriverExtension" };
  
  private static Properties driverAccess;
  
/* 179 */   protected static Connection defaultConn = null;
/* 180 */   private static OracleDriver defaultDriver = null;
  public static final Map<String, ClassRef> systemTypeMap;
  private static final String DEFAULT_CONNECTION_PROPERTIES_RESOURCE_NAME = "/oracle/jdbc/defaultConnectionProperties.properties";
  protected static final Properties DEFAULT_CONNECTION_PROPERTIES;
  
  static {
    try {
/* 187 */       if (defaultDriver == null)
      {
/* 189 */         defaultDriver = new oracle.jdbc.OracleDriver();
/* 190 */         DriverManager.registerDriver(defaultDriver);
      }
      
/* 197 */       AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
/* 201 */           OracleDriver.registerMBeans();
/* 202 */           return null;
        }
        
/* 217 */       });
/* 218 */       Timestamp localTimestamp = Timestamp.valueOf("2000-01-01 00:00:00.0");
    }
    catch (SQLException localSQLException)
    {
/* 223 */       Logger.getLogger("oracle.jdbc.driver").log(Level.SEVERE, "SQLException in static block.", localSQLException);
    }
    catch (RuntimeException localRuntimeException)
    {
/* 229 */       Logger.getLogger("oracle.jdbc.driver").log(Level.SEVERE, "RuntimeException in static block.", localRuntimeException);
    }
    
    try
    {
/* 243 */       ClassRef localClassRef = ClassRef.newInstance("oracle.security.pki.OraclePKIProvider");
/* 244 */       Object localObject = localClassRef.get().newInstance();
    }
    catch (Throwable localThrowable) {}
    
/* 255 */     systemTypeMap = new Hashtable(3);
    
    try
    {
/* 262 */       systemTypeMap.put("SYS.XMLTYPE", ClassRef.newInstance("oracle.xdb.XMLTypeFactory"));
    }
    catch (ClassNotFoundException localClassNotFoundException1) {}
    
    try
    {
/* 272 */       systemTypeMap.put("SYS.ANYDATA", ClassRef.newInstance("oracle.sql.AnyDataFactory"));
/* 273 */       systemTypeMap.put("SYS.ANYTYPE", ClassRef.newInstance("oracle.sql.TypeDescriptorFactory"));
    }
    catch (ClassNotFoundException localClassNotFoundException2) {}
    
/* 288 */     DEFAULT_CONNECTION_PROPERTIES = new Properties();
    try
    {
/* 291 */       InputStream localInputStream = PhysicalConnection.class.getResourceAsStream("/oracle/jdbc/defaultConnectionProperties.properties");
/* 292 */       if (localInputStream != null) { DEFAULT_CONNECTION_PROPERTIES.load(localInputStream);
      }
    }
    catch (IOException localIOException) {}
  }
  
  public static void registerMBeans()
  {
    try
    {
/* 310 */       MBeanServer localMBeanServer = null;
      Object localObject1;
      Object localObject3;
/* 313 */       try { ClassRef localClassRef = ClassRef.newInstance("oracle.as.jmx.framework.PortableMBeanFactory");
/* 314 */         localObject1 = localClassRef.get().getConstructor(new Class[0]);
/* 315 */         Object localObject2 = ((Constructor)localObject1).newInstance(new Object[0]);
/* 316 */         localObject3 = localClassRef.get().getMethod("getMBeanServer", new Class[0]);
/* 317 */         localMBeanServer = (MBeanServer)((Method)localObject3).invoke(localObject2, new Object[0]);
      }
      catch (NoClassDefFoundError localNoClassDefFoundError)
      {
/* 322 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
/* 327 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
      catch (NoSuchMethodException localNoSuchMethodException) {
/* 330 */         Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Found Oracle Apps MBeanServer but not the getMBeanServer method.", localNoSuchMethodException);
        
/* 334 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
      catch (InstantiationException localInstantiationException) {
/* 337 */         Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Found Oracle Apps MBeanServer but could not create an instance.", localInstantiationException);
        
/* 341 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
      catch (IllegalAccessException localIllegalAccessException) {
/* 344 */         Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Found Oracle Apps MBeanServer but could not access the getMBeanServer method.", localIllegalAccessException);
        
/* 348 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
      catch (InvocationTargetException localInvocationTargetException) {
/* 351 */         Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Found Oracle Apps MBeanServer but the getMBeanServer method threw an exception.", localInvocationTargetException);
        
/* 355 */         localMBeanServer = ManagementFactory.getPlatformMBeanServer();
      }
/* 357 */       if (localMBeanServer != null) {
/* 358 */         ClassLoader localClassLoader = OracleDriver.class.getClassLoader();
/* 359 */         localObject1 = localClassLoader == null ? "nullLoader" : localClassLoader.getClass().getName();
/* 360 */         int i = 0;
        for (;;) {
/* 362 */           localObject3 = (String)localObject1 + "@" + Integer.toHexString((localClassLoader == null ? 0 : localClassLoader.hashCode()) + i++);
          
/* 364 */           ObjectName localObjectName = new ObjectName("com.oracle.jdbc:type=diagnosability,name=" + (String)localObject3);
          try
          {
/* 367 */             localMBeanServer.registerMBean(new OracleDiagnosabilityMBean(), localObjectName);
          }
          catch (InstanceAlreadyExistsException localInstanceAlreadyExistsException) {}
        }
      }
      else
      {
/* 375 */         Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Unable to find an MBeanServer so no MBears are registered.");
      }
    }
    catch (JMException localJMException)
    {
/* 380 */       Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Error while registering Oracle JDBC Diagnosability MBean.", localJMException);
    }
    catch (Throwable localThrowable)
    {
/* 386 */       Logger.getLogger("oracle.jdbc").log(Level.WARNING, "Error while registering Oracle JDBC Diagnosability MBean.", localThrowable);
    }
  }
  
  public Connection connect(String paramString, Properties paramProperties)
    throws SQLException
  {
/* 420 */     if (paramString.regionMatches(0, "jdbc:default:connection", 0, 23))
    {
/* 422 */       String str = "jdbc:oracle:kprb";
/* 423 */       int j = paramString.length();
      
/* 425 */       if (j > 23) {
/* 426 */         paramString = str.concat(paramString.substring(23, paramString.length()));
      } else {
/* 428 */         paramString = str.concat(":");
      }
/* 430 */       str = null;
    }
    
/* 440 */     int i = oracleDriverExtensionTypeFromURL(paramString);
    
/* 442 */     if (i == -2) {
/* 443 */       return null;
    }
/* 445 */     if (i == -3)
    {
/* 447 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 67);
/* 448 */       ((SQLException)localObject1).fillInStackTrace();
/* 449 */       throw ((Throwable)localObject1);
    }
    
/* 452 */     Object localObject1 = null;
    
/* 454 */     localObject1 = this.driverExtensions[i];
    
/* 456 */     if (localObject1 == null)
    {
      try
      {
/* 461 */         synchronized (this)
        {
/* 463 */           if (localObject1 == null)
          {
/* 469 */             localObject1 = (OracleDriverExtension)Class.forName(driverExtensionClassNames[i]).newInstance();
            
/* 471 */             this.driverExtensions[i] = localObject1;
          }
          else
          {
/* 475 */             localObject1 = this.driverExtensions[i];
          }
          
        }
        
      }
      catch (Exception localException)
      {
/* 483 */         localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localException);
/* 484 */         ((SQLException)localObject3).fillInStackTrace();
/* 485 */         throw ((Throwable)localObject3);
      }
    }
    
/* 492 */     if (paramProperties == null) {
/* 493 */       paramProperties = new Properties();
    }
    
/* 504 */     Enumeration localEnumeration = DriverManager.getDrivers();
    
/* 507 */     while (localEnumeration.hasMoreElements())
    {
/* 509 */       localObject3 = (Driver)localEnumeration.nextElement();
      
/* 511 */       if ((localObject3 instanceof OracleDriver)) {
        break;
      }
    }
    
/* 516 */     while (localEnumeration.hasMoreElements())
    {
/* 518 */       localObject3 = (Driver)localEnumeration.nextElement();
      
/* 520 */       if ((localObject3 instanceof OracleDriver)) {
/* 521 */         DriverManager.deregisterDriver((Driver)localObject3);
      }
    }
    
/* 528 */     Object localObject3 = (PhysicalConnection)((OracleDriverExtension)localObject1).getConnection(paramString, paramProperties);
    
/* 531 */     ((PhysicalConnection)localObject3).protocolId = i;
    
/* 533 */     return (Connection)localObject3;
  }
  
  public Connection defaultConnection()
    throws SQLException
  {
/* 546 */     if ((defaultConn == null) || (defaultConn.isClosed()))
    {
/* 548 */       synchronized (OracleDriver.class)
      {
/* 550 */         if ((defaultConn == null) || (defaultConn.isClosed()))
        {
/* 552 */           defaultConn = connect("jdbc:oracle:kprb:", new Properties());
        }
      }
    }
    
/* 557 */     return defaultConn;
  }
  
  static final int oracleDriverExtensionTypeFromURL(String paramString)
  {
/* 582 */     int i = paramString.indexOf(':');
    
/* 584 */     if (i == -1) {
/* 585 */       return -2;
    }
/* 587 */     if (!paramString.regionMatches(true, 0, "jdbc", 0, i)) {
/* 588 */       return -2;
    }
/* 590 */     i++;
    
/* 592 */     int j = paramString.indexOf(':', i);
    
/* 594 */     if (j == -1) {
/* 595 */       return -2;
    }
/* 597 */     if (!paramString.regionMatches(true, i, "oracle", 0, j - i))
    {
/* 599 */       return -2;
    }
/* 601 */     j++;
    
/* 603 */     int k = paramString.indexOf(':', j);
    
/* 605 */     String str = null;
    
/* 612 */     if (k == -1) {
/* 613 */       return -3;
    }
/* 615 */     str = paramString.substring(j, k);
    
/* 617 */     if (str.equals("thin")) {
/* 618 */       return 0;
    }
/* 620 */     if ((str.equals("oci8")) || (str.equals("oci"))) {
/* 621 */       return 2;
    }
    
/* 625 */     return -3;
  }
  
  public boolean acceptsURL(String paramString)
  {
/* 645 */     if (paramString.startsWith("jdbc:oracle:"))
    {
/* 647 */       return oracleDriverExtensionTypeFromURL(paramString) > -2;
    }
    
/* 650 */     return false;
  }
  
  public DriverPropertyInfo[] getPropertyInfo(String paramString, Properties paramProperties)
    throws SQLException
  {
/* 658 */     Class localClass = null;
    try
    {
/* 661 */       localClass = ClassRef.newInstance("oracle.jdbc.OracleConnection").get();
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    
/* 666 */     int i = 0;
/* 667 */     Object localObject1 = new String[''];
/* 668 */     Object localObject2 = new String[''];
    
/* 670 */     Field[] arrayOfField = localClass.getFields();
/* 671 */     for (int j = 0; j < arrayOfField.length; j++)
    {
/* 673 */       if ((arrayOfField[j].getName().startsWith("CONNECTION_PROPERTY_")) && (!arrayOfField[j].getName().endsWith("_DEFAULT")) && (!arrayOfField[j].getName().endsWith("_ACCESSMODE")))
      {
        try
        {
/* 679 */           String str1 = (String)arrayOfField[j].get(null);
/* 680 */           Field localField = localClass.getField(arrayOfField[j].getName() + "_DEFAULT");
/* 681 */           String str2 = (String)localField.get(null);
/* 682 */           if (i == localObject1.length)
          {
/* 684 */             String[] arrayOfString1 = new String[localObject1.length * 2];
/* 685 */             String[] arrayOfString2 = new String[localObject1.length * 2];
/* 686 */             System.arraycopy(localObject1, 0, arrayOfString1, 0, localObject1.length);
/* 687 */             System.arraycopy(localObject2, 0, arrayOfString2, 0, localObject1.length);
/* 688 */             localObject1 = arrayOfString1;
/* 689 */             localObject2 = arrayOfString2;
          }
/* 691 */           localObject1[i] = str1;
/* 692 */           localObject2[i] = str2;
/* 693 */           i++;
        }
        catch (IllegalAccessException localIllegalAccessException) {}catch (NoSuchFieldException localNoSuchFieldException) {}
      }
    }
    
/* 701 */     DriverPropertyInfo[] arrayOfDriverPropertyInfo = new DriverPropertyInfo[i];
/* 702 */     for (int k = 0; k < i; k++)
/* 703 */       arrayOfDriverPropertyInfo[k] = new DriverPropertyInfo(localObject1[k], localObject2[k]);
/* 704 */     return arrayOfDriverPropertyInfo;
  }
  
  public int getMajorVersion()
  {
/* 711 */     return OracleDatabaseMetaData.getDriverMajorVersionInfo();
  }
  
  public int getMinorVersion()
  {
/* 718 */     return OracleDatabaseMetaData.getDriverMinorVersionInfo();
  }
  
  public boolean jdbcCompliant()
  {
/* 725 */     return true;
  }
  
  public String processSqlEscapes(String paramString)
    throws SQLException
  {
/* 740 */     OracleSql localOracleSql = new OracleSql(null);
    
/* 744 */     localOracleSql.initialize(paramString);
    
/* 746 */     return localOracleSql.parse(paramString);
  }
  
  public static String getCompileTime()
  {
/* 761 */     return BuildInfo.getBuildDate();
  }
  
  public static String getSystemPropertyFastConnectionFailover(String paramString)
  {
/* 768 */     return PhysicalConnection.getSystemPropertyFastConnectionFailover(paramString);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 783 */     return null;
  }
  
/* 788 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleDriver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */