package oracle.jdbc.driver;
import java.sql.ClientInfoStatus;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
import oracle.jdbc.OracleConnectionWrapper;
import oracle.jdbc.internal.ClientDataSupport;
public abstract class OracleConnection
  extends OracleConnectionWrapper
  implements oracle.jdbc.internal.OracleConnection, ClientDataSupport
{
/*  42 */   static int DEFAULT_ROW_PREFETCH = 10;
  
  static final String svptPrefix = "ORACLE_SVPT_";
  
  static final int BINARYSTREAM = 0;
  
  static final int ASCIISTREAM = 1;
  
  static final int UNICODESTREAM = 2;
  
  static final int EOJ_NON = 0;
  
  static final int EOJ_B_TO_A = 1;
  
  static final int EOJ_B_TO_U = 2;
  
  static final int EOJ_A_TO_U = 3;
  
  static final int EOJ_8_TO_A = 4;
  
  static final int EOJ_8_TO_U = 5;
  
  static final int EOJ_U_TO_A = 6;
  
  static final int ASCII_CHARSET = 0;
  
  static final int NLS_CHARSET = 1;
  
  static final int CHAR_TO_ASCII = 0;
  
  static final int CHAR_TO_UNICODE = 1;
  
  static final int RAW_TO_ASCII = 2;
  
  static final int RAW_TO_UNICODE = 3;
  
  static final int UNICODE_TO_CHAR = 4;
  
  static final int ASCII_TO_CHAR = 5;
  
  static final int NONE = 6;
  
  static final int JAVACHAR_TO_CHAR = 7;
  
  static final int RAW_TO_JAVACHAR = 8;
  
  static final int CHAR_TO_JAVACHAR = 9;
  
  static final int JAVACHAR_TO_ASCII = 10;
  
  static final int JAVACHAR_TO_UNICODE = 11;
  
  static final int UNICODE_TO_ASCII = 12;
  
  public static final int KOLBLLENB = 0;
  
  public static final int KOLBLVSNB = 2;
  
  public static final byte KOLL1FLG = 4;
  
  public static final byte KOLL2FLG = 5;
  
  public static final byte KOLL3FLG = 6;
  
  public static final byte KOLL4FLG = 7;
  
  public static final int KOLBLCIDB = 32;
  
  static final byte ALLFLAGS = -1;
  
  public static final int KOLBLIMRLL = 86;
  
  public static final byte KOLBLBLOB = 1;
  
  public static final byte KOLBLCLOB = 2;
  
  public static final byte KOLBLNLOB = 4;
  
  public static final byte KOLBLBFIL = 8;
  
  public static final byte KOLBLCFIL = 16;
  
  public static final byte KOLBLNFIL = 32;
  
  public static final byte KOLBLABS = 64;
  
  public static final byte KOLBLPXY = -128;
  
  public static final byte KOLBLPKEY = 1;
  
  public static final byte KOLBLIMP = 2;
  
  public static final byte KOLBLIDX = 4;
  
  public static final byte KOLBLINI = 8;
  
  public static final byte KOLBLEMP = 16;
  
  public static final byte KOLBLVIEW = 32;
  
  public static final byte KOLBL0FRM = 64;
  
  public static final byte KOLBL1FRM = -128;
  
  public static final byte KOLBLRDO = 1;
  
  public static final byte KOLBLPART = 2;
  
  public static final byte KOLBLCPD = 4;
  
  public static final byte KOLBLDIL = 8;
  
  public static final byte KOLBLBUF = 16;
  
  public static final byte KOLBLBPS = 32;
  
  public static final byte KOLBLMOD = 64;
  
  public static final byte KOLBLVAR = -128;
  
  public static final byte KOLBLTMP = 1;
  
  public static final byte KOLBLCACHE = 2;
  
  public static final byte KOLBLOPEN = 8;
  
  public static final byte KOLBLRDWR = 16;
  public static final byte KOLBLCLI = 32;
  public static final byte KOLBLVLE = 64;
  public static final byte KOLBLLCL = -128;
  static final int KOLBLLIDB = 10;
  static final int KOLBLPREL = 2;
  static final int KOLBLLIDL = 10;
  static final int KOLBLTLMXL = 40;
  
  static boolean containsKey(Map paramMap, Object paramObject)
  {
/* 179 */     return paramMap.get(paramObject) != null;
  }
  
  public abstract Object getClientData(Object paramObject);
  
  public abstract Object setClientData(Object paramObject1, Object paramObject2);
  
  public abstract Object removeClientData(Object paramObject);
  
  /**
   * @deprecated
   */
  public abstract void setClientIdentifier(String paramString)
    throws SQLException;
  
  /**
   * @deprecated
   */
  public abstract void clearClientIdentifier(String paramString)
    throws SQLException;
  
  public boolean isValid(int paramInt)
    throws SQLException
  {
/* 228 */     return pingDatabase(paramInt) == 0;
  }
  
/* 236 */   static final List<String> RESERVED_NAMESPACES = Arrays.asList(new String[] { "SYS" });
  
/* 242 */   static final Pattern SUPPORTED_NAMESPACE_PATTERN = Pattern.compile("CLIENTCONTEXT");
  
  public void setClientInfo(String paramString1, String paramString2)
    throws SQLClientInfoException
  {
/* 260 */     setClientInfoInternal(paramString1, paramString2, null);
  }
  
  public void setClientInfo(Properties paramProperties)
    throws SQLClientInfoException
  {
/* 277 */     Properties localProperties = (Properties)paramProperties.clone();
/* 278 */     for (String str1 : paramProperties.stringPropertyNames()) {
/* 279 */       String str2 = paramProperties.getProperty(str1);
/* 280 */       setClientInfoInternal(str1, str2, localProperties);
/* 281 */       localProperties.remove(str1);
    }
  }
  
  void setClientInfoInternal(String paramString1, String paramString2, Properties paramProperties)
    throws SQLClientInfoException
  {
/* 302 */     HashMap localHashMap = new HashMap();
/* 303 */     if (paramProperties != null) {
/* 304 */       for (localObject = paramProperties.stringPropertyNames().iterator(); ((Iterator)localObject).hasNext();) { String str = (String)((Iterator)localObject).next();
/* 305 */         localHashMap.put(str, ClientInfoStatus.REASON_UNKNOWN);
      }
    }
    
/* 309 */     Object localObject = DatabaseError.createSQLClientInfoException(253, localHashMap, null);
/* 310 */     ((SQLClientInfoException)localObject).fillInStackTrace();
/* 311 */     throw ((Throwable)localObject);
  }
  
  public String getClientInfo(String paramString)
    throws SQLException
  {
/* 356 */     if (isClosed()) {
/* 357 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 358 */       localSQLException.fillInStackTrace();
/* 359 */       throw localSQLException;
    }
/* 361 */     return null;
  }
  
  public Properties getClientInfo()
    throws SQLException
  {
/* 377 */     if (isClosed()) {
/* 378 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 8);
/* 379 */       localSQLException.fillInStackTrace();
/* 380 */       throw localSQLException;
    }
/* 382 */     return new Properties();
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 398 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 400 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 401 */     localSQLException.fillInStackTrace();
/* 402 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 421 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 423 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 424 */     localSQLException.fillInStackTrace();
/* 425 */     throw localSQLException;
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 443 */     return this;
  }
  
  public Class getClassForType(String paramString, Map<String, Class> paramMap)
  {
/* 460 */     Class localClass = (Class)paramMap.get(paramString);
    
/* 462 */     if (localClass == null)
    {
/* 464 */       ClassRef localClassRef = (ClassRef)OracleDriver.systemTypeMap.get(paramString);
/* 465 */       if (localClassRef != null) { localClass = localClassRef.get();
      }
    }
/* 468 */     return localClass;
  }
  
/* 473 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */