package oracle.sql;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleTypeMetaData.Kind;
import oracle.jdbc.OracleTypeMetaData.Opaque;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.oracore.OracleNamedType;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.jdbc.oracore.OracleTypeOPAQUE;
public class OpaqueDescriptor
  extends TypeDescriptor
  implements OracleTypeMetaData.Opaque, Serializable
{
  static final boolean DEBUG = false;
  static final long serialVersionUID = 1013921343538311063L;
  
  public OpaqueDescriptor(String paramString, Connection paramConnection)
    throws SQLException
  {
/*  57 */     super((short)58, paramString, paramConnection);
    
/*  59 */     initPickler();
  }
  
  public OpaqueDescriptor(SQLName paramSQLName, Connection paramConnection)
    throws SQLException
  {
/*  73 */     super((short)58, paramSQLName, paramConnection);
    
/*  75 */     initPickler();
  }
  
  public OpaqueDescriptor(SQLName paramSQLName, OracleTypeOPAQUE paramOracleTypeOPAQUE, Connection paramConnection)
    throws SQLException
  {
/*  90 */     super((short)58, paramSQLName, paramOracleTypeOPAQUE, paramConnection);
  }
  
  public OpaqueDescriptor(OracleTypeADT paramOracleTypeADT, Connection paramConnection)
    throws SQLException
  {
/* 106 */     super((short)58, paramOracleTypeADT, paramConnection);
  }
  
  OpaqueDescriptor(byte[] paramArrayOfByte, int paramInt, Connection paramConnection)
    throws SQLException
  {
/* 118 */     super((short)108);
    
/* 120 */     this.toid = paramArrayOfByte;
/* 121 */     this.toidVersion = paramInt;
/* 122 */     setPhysicalConnectionOf(paramConnection);
/* 123 */     initPickler();
  }
  
  public static OpaqueDescriptor createDescriptor(String paramString, Connection paramConnection)
    throws SQLException
  {
/* 141 */     if ((paramString == null) || (paramString.length() == 0))
    {
/* 144 */       localObject = DatabaseError.createSqlException(null, 60, "Invalid argument,'name' shouldn't be null nor an empty string and 'conn' should not be null");
      
/* 146 */       ((SQLException)localObject).fillInStackTrace();
/* 147 */       throw ((Throwable)localObject);
    }
    
/* 151 */     Object localObject = new SQLName(paramString, (oracle.jdbc.OracleConnection)paramConnection);
/* 152 */     return createDescriptor((SQLName)localObject, paramConnection);
  }
  
  public static OpaqueDescriptor createDescriptor(SQLName paramSQLName, Connection paramConnection)
    throws SQLException
  {
/* 168 */     String str = paramSQLName.getName();
    
/* 171 */     OpaqueDescriptor localOpaqueDescriptor = null;
/* 172 */     if (paramConnection != null) {
/* 173 */       localOpaqueDescriptor = (OpaqueDescriptor)((oracle.jdbc.OracleConnection)paramConnection).getDescriptor(str);
    }
    
/* 176 */     if (localOpaqueDescriptor == null) {
      OracleTypeOPAQUE localOracleTypeOPAQUE;
/* 178 */       if (str.equals("SYS.ANYTYPE"))
      {
/* 180 */         localOracleTypeOPAQUE = new OracleTypeOPAQUE(TypeDescriptor.ANYTYPETOID, 1, 0, (short)0, str, 7L);
        
/* 182 */         localOpaqueDescriptor = new OpaqueDescriptor(paramSQLName, localOracleTypeOPAQUE, paramConnection);
      }
/* 184 */       else if (str.equals("SYS.ANYDATA"))
      {
/* 186 */         localOracleTypeOPAQUE = new OracleTypeOPAQUE(TypeDescriptor.ANYDATATOID, 1, 0, (short)0, str, 7L);
        
/* 188 */         localOpaqueDescriptor = new OpaqueDescriptor(paramSQLName, localOracleTypeOPAQUE, paramConnection);
      }
      else {
/* 191 */         localOpaqueDescriptor = new OpaqueDescriptor(paramSQLName, paramConnection);
      }
/* 193 */       if (paramConnection != null) {
/* 194 */         ((oracle.jdbc.OracleConnection)paramConnection).putDescriptor(str, localOpaqueDescriptor);
      }
    }
/* 197 */     return localOpaqueDescriptor;
  }
  
  public OracleTypeMetaData.Kind getKind()
  {
/* 206 */     return OracleTypeMetaData.Kind.OPAQUE;
  }
  
  private void initPickler()
    throws SQLException
  {
    try
    {
/* 215 */       this.pickler = new OracleTypeADT(getName(), this.connection);
      
/* 217 */       ((OracleTypeADT)this.pickler).init(this.connection);
      
/* 219 */       this.pickler = ((OracleTypeOPAQUE)((OracleTypeADT)this.pickler).cleanup());
      
/* 221 */       this.pickler.setDescriptor(this);
    }
    catch (Exception localException)
    {
/* 227 */       if ((localException instanceof SQLException)) {
/* 228 */         throw ((SQLException)localException);
      }
      
/* 231 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Unable to resolve type \"" + getName() + "\"");
      
/* 233 */       localSQLException.fillInStackTrace();
/* 234 */       throw localSQLException;
    }
  }
  
  byte[] toBytes(OPAQUE paramOPAQUE, boolean paramBoolean)
    throws SQLException
  {
/* 243 */     byte[] arrayOfByte = null;
    
/* 245 */     if (paramOPAQUE.shareBytes() != null)
    {
/* 247 */       arrayOfByte = paramOPAQUE.shareBytes();
    }
    else
    {
      try
      {
/* 253 */         arrayOfByte = this.pickler.linearize(paramOPAQUE);
      }
      finally
      {
/* 257 */         if (!paramBoolean) {
/* 258 */           paramOPAQUE.setShareBytes(null);
        }
      }
    }
/* 262 */     return arrayOfByte;
  }
  
  byte[] toValue(OPAQUE paramOPAQUE, boolean paramBoolean)
    throws SQLException
  {
/* 269 */     byte[] arrayOfByte = null;
    
/* 271 */     if (paramOPAQUE.value != null)
    {
/* 273 */       arrayOfByte = paramOPAQUE.value;
    }
    else
    {
      try
      {
/* 279 */         this.pickler.unlinearize(paramOPAQUE.shareBytes(), 0L, paramOPAQUE, 1, null);
        
/* 281 */         arrayOfByte = paramOPAQUE.value;
      }
      finally
      {
/* 285 */         if (!paramBoolean)
/* 286 */           paramOPAQUE.value = null;
      }
    }
/* 289 */     return arrayOfByte;
  }
  
/* 295 */   private static boolean doesXMLTypeImplementSQLXML = false;
  
/* 297 */   static { Class localClass1 = null;
    try {
/* 299 */       localClass1 = Class.forName("oracle.xdb.XMLType");
      
/* 304 */       Class[] arrayOfClass1 = localClass1.getInterfaces();
/* 305 */       for (Class localClass2 : arrayOfClass1)
      {
/* 307 */         String str = localClass2.getCanonicalName();
/* 308 */         if (str.compareTo("java.sql.SQLXML") == 0)
        {
/* 310 */           doesXMLTypeImplementSQLXML = true;
/* 311 */           break;
        }
      }
    }
    catch (Throwable localThrowable) {}
  }
  
  public int getTypeCode()
    throws SQLException
  {
/* 328 */     if ((doesXMLTypeImplementSQLXML) && ("SYS.XMLTYPE".equalsIgnoreCase(this.sqlName.getName()))) {
/* 329 */       return 2009;
    }
    
/* 332 */     return 2007;
  }
  
  public boolean isInHierarchyOf(String paramString)
    throws SQLException
  {
/* 346 */     OpaqueDescriptor localOpaqueDescriptor = this;
/* 347 */     String str = localOpaqueDescriptor.getName();
/* 348 */     return paramString.equals(str);
  }
  
  public long getMaxLength()
    throws SQLException
  {
/* 360 */     long l = hasUnboundedSize() ? 0L : ((OracleTypeOPAQUE)this.pickler).getMaxLength();
    
/* 363 */     return l;
  }
  
  public boolean isTrustedLibrary()
    throws SQLException
  {
/* 379 */     return ((OracleTypeOPAQUE)this.pickler).isTrustedLibrary();
  }
  
  public boolean isModeledInC()
    throws SQLException
  {
/* 392 */     return ((OracleTypeOPAQUE)this.pickler).isModeledInC();
  }
  
  public boolean hasUnboundedSize()
    throws SQLException
  {
/* 405 */     return ((OracleTypeOPAQUE)this.pickler).isUnboundedSized();
  }
  
  public boolean hasFixedSize()
    throws SQLException
  {
/* 419 */     return ((OracleTypeOPAQUE)this.pickler).isFixedSized();
  }
  
  public String descType()
    throws SQLException
  {
/* 431 */     StringBuffer localStringBuffer = new StringBuffer();
/* 432 */     return descType(localStringBuffer, 0);
  }
  
  String descType(StringBuffer paramStringBuffer, int paramInt)
    throws SQLException
  {
/* 439 */     String str1 = "";
    
/* 441 */     for (int i = 0; i < paramInt; i++) {
/* 442 */       str1 = str1 + "  ";
    }
/* 444 */     String str2 = str1 + "  ";
    
/* 446 */     paramStringBuffer.append(str1);
/* 447 */     paramStringBuffer.append(getTypeName());
/* 448 */     paramStringBuffer.append(" maxLen=" + getMaxLength() + " isTrusted=" + isTrustedLibrary() + " hasUnboundedSize=" + hasUnboundedSize() + " hasFixedSize=" + hasFixedSize());
    
/* 451 */     paramStringBuffer.append("\n");
    
/* 453 */     return paramStringBuffer.toString();
  }
  
  public Class getClass(Map paramMap)
    throws SQLException
  {
/* 465 */     Object localObject = null;
    
/* 470 */     String str1 = getName();
    
/* 473 */     Class localClass = this.connection.getClassForType(str1, paramMap);
    
/* 475 */     String str2 = getSchemaName();
/* 476 */     String str3 = getTypeName();
    
/* 478 */     if (localClass == null)
    {
/* 480 */       if (this.connection.getDefaultSchemaNameForNamedTypes().equals(str2)) {
/* 481 */         localClass = (Class)paramMap.get(str3);
      }
    }
/* 484 */     if (!SQLName.s_parseAllFormat)
    {
/* 486 */       localObject = localClass;
    }
    else
    {
/* 490 */       if (localClass == null)
      {
/* 492 */         if (this.connection.getDefaultSchemaNameForNamedTypes().equals(str2)) {
/* 493 */           localClass = (Class)paramMap.get("\"" + str3 + "\"");
        }
      }
/* 496 */       if (localClass == null)
      {
/* 498 */         localClass = (Class)paramMap.get("\"" + str2 + "\"" + "." + "\"" + str3 + "\"");
      }
      
/* 501 */       if (localClass == null)
      {
/* 503 */         localClass = (Class)paramMap.get("\"" + str2 + "\"" + "." + str3);
      }
      
/* 506 */       if (localClass == null)
      {
/* 508 */         localClass = (Class)paramMap.get(str2 + "." + "\"" + str3 + "\"");
      }
      
/* 511 */       localObject = localClass;
    }
    
/* 514 */     return (Class)localObject;
  }
  
/* 534 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/OpaqueDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */