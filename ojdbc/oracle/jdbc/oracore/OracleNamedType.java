package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
import oracle.sql.SQLName;
import oracle.sql.TypeDescriptor;
public abstract class OracleNamedType
  extends OracleType
  implements Serializable
{
  transient OracleConnection connection;
/*  35 */   SQLName sqlName = null;
/*  36 */   transient OracleTypeADT parent = null;
  transient int idx;
/*  38 */   transient TypeDescriptor descriptor = null;
  
  protected OracleNamedType() {}
  
  public OracleNamedType(String paramString, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  46 */     setConnectionInternal(paramOracleConnection);
/*  47 */     this.sqlName = new SQLName(paramString, paramOracleConnection);
  }
  
  protected OracleNamedType(OracleTypeADT paramOracleTypeADT, int paramInt, OracleConnection paramOracleConnection)
  {
/*  56 */     setConnectionInternal(paramOracleConnection);
/*  57 */     this.parent = paramOracleTypeADT;
/*  58 */     this.idx = paramInt;
  }
  
  public String getFullName()
    throws SQLException
  {
/*  68 */     return getFullName(false);
  }
  
  public String getFullName(boolean paramBoolean)
    throws SQLException
  {
/*  76 */     String str = null;
    
/*  78 */     if ((paramBoolean) || (this.sqlName == null))
    {
/*  81 */       if ((this.parent != null) && ((str = this.parent.getAttributeType(this.idx)) != null))
      {
/*  84 */         this.sqlName = new SQLName(str, this.connection);
      }
      else
      {
/*  88 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to resolve name");
/*  89 */         localSQLException.fillInStackTrace();
/*  90 */         throw localSQLException;
      }
    }
/*  93 */     return this.sqlName.getName();
  }
  
  public String getSchemaName()
    throws SQLException
  {
/* 100 */     if (this.sqlName == null) getFullName();
/* 101 */     return this.sqlName.getSchema();
  }
  
  public String getSimpleName()
    throws SQLException
  {
/* 108 */     if (this.sqlName == null) getFullName();
/* 109 */     return this.sqlName.getSimpleName();
  }
  
  public boolean hasName()
    throws SQLException
  {
/* 116 */     return this.sqlName != null;
  }
  
  public OracleTypeADT getParent()
    throws SQLException
  {
/* 123 */     return this.parent;
  }
  
  public void setParent(OracleTypeADT paramOracleTypeADT)
    throws SQLException
  {
/* 130 */     this.parent = paramOracleTypeADT;
  }
  
  public int getOrder()
    throws SQLException
  {
/* 137 */     return this.idx;
  }
  
  public void setOrder(int paramInt)
    throws SQLException
  {
/* 144 */     this.idx = paramInt;
  }
  
  public OracleConnection getConnection()
    throws SQLException
  {
/* 158 */     return this.connection;
  }
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 173 */     setConnectionInternal(paramOracleConnection);
  }
  
  public void setConnectionInternal(OracleConnection paramOracleConnection)
  {
/* 180 */     this.connection = paramOracleConnection;
  }
  
  public Datum unlinearize(byte[] paramArrayOfByte, long paramLong, Datum paramDatum, int paramInt, Map paramMap)
    throws SQLException
  {
/* 199 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 200 */     localSQLException.fillInStackTrace();
/* 201 */     throw localSQLException;
  }
  
  public Datum unlinearize(byte[] paramArrayOfByte, long paramLong1, Datum paramDatum, long paramLong2, int paramInt1, int paramInt2, Map paramMap)
    throws SQLException
  {
/* 221 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 222 */     localSQLException.fillInStackTrace();
/* 223 */     throw localSQLException;
  }
  
  public byte[] linearize(Datum paramDatum)
    throws SQLException
  {
/* 236 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 237 */     localSQLException.fillInStackTrace();
/* 238 */     throw localSQLException;
  }
  
  public TypeDescriptor getDescriptor()
    throws SQLException
  {
/* 246 */     return this.descriptor;
  }
  
  public void setDescriptor(TypeDescriptor paramTypeDescriptor)
    throws SQLException
  {
/* 253 */     this.descriptor = paramTypeDescriptor;
  }
  
  public int getTypeVersion()
  {
/* 260 */     return 1;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    try
    {
/* 274 */       paramObjectOutputStream.writeUTF(getFullName());
    }
    catch (SQLException localSQLException)
    {
/* 279 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 280 */       localIOException.fillInStackTrace();
/* 281 */       throw localIOException;
    }
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 291 */     String str = paramObjectInputStream.readUTF();
/* 292 */     try { this.sqlName = new SQLName(str, null); } catch (SQLException localSQLException) {}
/* 293 */     this.parent = null;
/* 294 */     this.idx = -1;
  }
  
  public void fixupConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 302 */     if (this.connection == null) { setConnection(paramOracleConnection);
    }
  }
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt)
    throws SQLException
  {
/* 314 */     printXML(paramPrintWriter, paramInt, false);
  }
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 320 */     for (int i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 321 */     paramPrintWriter.println("<OracleNamedType/>");
  }
  
  public void initNamesRecursively()
    throws SQLException
  {
/* 328 */     Map localMap = createTypesTreeMap();
/* 329 */     if (localMap.size() > 0) {
/* 330 */       initChildNamesRecursively(localMap);
    }
  }
  
  public void setNames(String paramString1, String paramString2)
    throws SQLException
  {
/* 337 */     this.sqlName = new SQLName(paramString1, paramString2, this.connection);
  }
  
  public void setSqlName(SQLName paramSQLName)
  {
/* 344 */     this.sqlName = paramSQLName;
  }
  
  public Map createTypesTreeMap()
    throws SQLException
  {
/* 358 */     Map localMap = null;
/* 359 */     String str = this.connection.getDefaultSchemaNameForNamedTypes();
/* 360 */     if (this.sqlName.getSchema().equals(str)) {
/* 361 */       localMap = getNodeMapFromUserTypes();
    }
/* 363 */     if (localMap == null)
/* 364 */       localMap = getNodeMapFromAllTypes();
/* 365 */     return localMap;
  }
  
/* 369 */   static String getUserTypeTreeSql = "select level depth, parent_type, child_type, ATTR_NO, child_type_owner from  (select TYPE_NAME parent_type, ELEM_TYPE_NAME child_type, 0 ATTR_NO,       ELEM_TYPE_OWNER child_type_owner     from USER_COLL_TYPES  union   select TYPE_NAME parent_type, ATTR_TYPE_NAME child_type, ATTR_NO,       ATTR_TYPE_OWNER child_type_owner     from USER_TYPE_ATTRS  ) start with parent_type  = ?  connect by prior  child_type = parent_type";
  
/* 384 */   String sqlHint = null;
  
  String getSqlHint() throws SQLException
  {
/* 388 */     if (this.sqlHint == null)
    {
/* 390 */       if (this.connection.getVersionNumber() >= 11000) {
/* 391 */         this.sqlHint = "";
      } else
/* 393 */         this.sqlHint = "/*+RULE*/";
    }
/* 395 */     return this.sqlHint;
  }
  
  Map getNodeMapFromUserTypes()
    throws SQLException
  {
/* 403 */     HashMap localHashMap = new HashMap();
/* 404 */     PreparedStatement localPreparedStatement = null;
/* 405 */     ResultSet localResultSet = null;
    try
    {
/* 408 */       localPreparedStatement = this.connection.prepareStatement(getSqlHint() + getUserTypeTreeSql);
/* 409 */       localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 410 */       localResultSet = localPreparedStatement.executeQuery();
      
/* 412 */       while (localResultSet.next())
      {
/* 414 */         int i = localResultSet.getInt(1);
/* 415 */         String str1 = localResultSet.getString(2);
/* 416 */         String str2 = localResultSet.getString(3);
/* 417 */         int j = localResultSet.getInt(4);
/* 418 */         String str3 = localResultSet.getString(5);
/* 419 */         if ((str3 != null) && (!str3.equals(this.sqlName.getSchema())))
        {
/* 421 */           localHashMap = null;
/* 422 */           break;
        }
/* 424 */         if (str1.length() > 0)
        {
/* 426 */           SQLName localSQLName = new SQLName(this.sqlName.getSchema(), str1, this.connection);
/* 427 */           TypeTreeElement localTypeTreeElement = null;
/* 428 */           if (localHashMap.containsKey(localSQLName)) {
/* 429 */             localTypeTreeElement = (TypeTreeElement)localHashMap.get(localSQLName);
          }
          else {
/* 432 */             localTypeTreeElement = new TypeTreeElement(this.sqlName.getSchema(), str1);
/* 433 */             localHashMap.put(localSQLName, localTypeTreeElement);
          }
/* 435 */           localTypeTreeElement.putChild(this.sqlName.getSchema(), str2, j);
        }
      }
    } finally {
/* 439 */       if (localResultSet != null) localResultSet.close(); if (localPreparedStatement != null) localPreparedStatement.close(); }
/* 440 */     return localHashMap;
  }
  
/* 445 */   static String getAllTypeTreeSql = "select parent_type, parent_type_owner, child_type, ATTR_NO, child_type_owner from ( select TYPE_NAME parent_type,  OWNER parent_type_owner,     ELEM_TYPE_NAME child_type, 0 ATTR_NO,     ELEM_TYPE_OWNER child_type_owner   from ALL_COLL_TYPES union   select TYPE_NAME parent_type, OWNER parent_type_owner,     ATTR_TYPE_NAME child_type, ATTR_NO,     ATTR_TYPE_OWNER child_type_owner   from ALL_TYPE_ATTRS ) start with parent_type  = ?  and parent_type_owner = ? connect by prior child_type = parent_type   and ( child_type_owner = parent_type_owner or child_type_owner is null )";
  
  Map getNodeMapFromAllTypes()
    throws SQLException
  {
/* 463 */     HashMap localHashMap = new HashMap();
/* 464 */     PreparedStatement localPreparedStatement = null;
/* 465 */     ResultSet localResultSet = null;
    try
    {
/* 468 */       localPreparedStatement = this.connection.prepareStatement(getSqlHint() + getAllTypeTreeSql);
/* 469 */       localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 470 */       localPreparedStatement.setString(2, this.sqlName.getSchema());
/* 471 */       localResultSet = localPreparedStatement.executeQuery();
      
/* 473 */       while (localResultSet.next())
      {
/* 475 */         String str1 = localResultSet.getString(1);
/* 476 */         String str2 = localResultSet.getString(2);
/* 477 */         String str3 = localResultSet.getString(3);
/* 478 */         int i = localResultSet.getInt(4);
/* 479 */         String str4 = localResultSet.getString(5);
/* 480 */         if (str4 == null) str4 = "SYS";
/* 481 */         if (str1.length() > 0)
        {
/* 483 */           SQLName localSQLName = new SQLName(str2, str1, this.connection);
/* 484 */           TypeTreeElement localTypeTreeElement = null;
/* 485 */           if (localHashMap.containsKey(localSQLName)) {
/* 486 */             localTypeTreeElement = (TypeTreeElement)localHashMap.get(localSQLName);
          }
          else {
/* 489 */             localTypeTreeElement = new TypeTreeElement(str2, str1);
/* 490 */             localHashMap.put(localSQLName, localTypeTreeElement);
          }
/* 492 */           localTypeTreeElement.putChild(str4, str3, i);
        }
      }
    } finally {
/* 496 */       if (localResultSet != null) localResultSet.close(); if (localPreparedStatement != null) localPreparedStatement.close(); }
/* 497 */     return localHashMap;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 512 */     return this.connection;
  }
  
/* 558 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleNamedType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */