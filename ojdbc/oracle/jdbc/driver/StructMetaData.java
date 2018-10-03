package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.OracleArray;
import oracle.jdbc.OracleBfile;
import oracle.jdbc.OracleBlob;
import oracle.jdbc.OracleClob;
import oracle.jdbc.OracleNClob;
import oracle.jdbc.OracleOpaque;
import oracle.jdbc.OracleRef;
import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
import oracle.jdbc.OracleStruct;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.oracore.OracleType;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.jdbc.oracore.OracleTypeCHAR;
import oracle.jdbc.oracore.OracleTypeFLOAT;
import oracle.jdbc.oracore.OracleTypeNUMBER;
import oracle.jdbc.oracore.OracleTypeRAW;
import oracle.jdbc.oracore.OracleTypeREF;
import oracle.sql.StructDescriptor;
class StructMetaData
  implements oracle.jdbc.internal.StructMetaData
{
  StructDescriptor descriptor;
  OracleTypeADT otype;
  OracleType[] types;
  
  public StructMetaData(StructDescriptor paramStructDescriptor)
    throws SQLException
  {
/*  40 */     if (paramStructDescriptor == null)
    {
/*  42 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "illegal operation: descriptor is null");
/*  43 */       localSQLException.fillInStackTrace();
/*  44 */       throw localSQLException;
    }
    
/*  47 */     this.descriptor = paramStructDescriptor;
/*  48 */     this.otype = paramStructDescriptor.getOracleTypeADT();
/*  49 */     this.types = this.otype.getAttrTypes();
  }
  
  public int getColumnCount()
    throws SQLException
  {
/*  62 */     return this.types.length;
  }
  
  public boolean isAutoIncrement(int paramInt)
    throws SQLException
  {
/*  70 */     return false;
  }
  
  public boolean isSearchable(int paramInt)
    throws SQLException
  {
/*  80 */     return false;
  }
  
  public OracleResultSetMetaData.SecurityAttribute getSecurityAttribute(int paramInt)
    throws SQLException
  {
/*  87 */     return OracleResultSetMetaData.SecurityAttribute.NONE;
  }
  
  public boolean isCurrency(int paramInt)
    throws SQLException
  {
/*  95 */     int i = getValidColumnIndex(paramInt);
    
/*  97 */     return ((this.types[i] instanceof OracleTypeNUMBER)) || ((this.types[i] instanceof OracleTypeFLOAT));
  }
  
  public boolean isCaseSensitive(int paramInt)
    throws SQLException
  {
/* 107 */     int i = getValidColumnIndex(paramInt);
    
/* 109 */     return this.types[i] instanceof OracleTypeCHAR;
  }
  
  public int isNullable(int paramInt)
    throws SQLException
  {
/* 117 */     return 1;
  }
  
  public boolean isSigned(int paramInt)
    throws SQLException
  {
/* 125 */     return true;
  }
  
  public int getColumnDisplaySize(int paramInt)
    throws SQLException
  {
/* 137 */     int i = getValidColumnIndex(paramInt);
    
/* 139 */     if ((this.types[i] instanceof OracleTypeCHAR))
/* 140 */       return ((OracleTypeCHAR)this.types[i]).getLength();
/* 141 */     if ((this.types[i] instanceof OracleTypeRAW)) {
/* 142 */       return ((OracleTypeRAW)this.types[i]).getLength();
    }
/* 144 */     return 0;
  }
  
  public String getColumnLabel(int paramInt)
    throws SQLException
  {
/* 152 */     return getColumnName(paramInt);
  }
  
  public String getColumnName(int paramInt)
    throws SQLException
  {
/* 167 */     return this.otype.getAttributeName(paramInt);
  }
  
  public String getSchemaName(int paramInt)
    throws SQLException
  {
/* 182 */     int i = getValidColumnIndex(paramInt);
    
/* 184 */     if ((this.types[i] instanceof OracleTypeADT)) {
/* 185 */       return ((OracleTypeADT)this.types[i]).getSchemaName();
    }
/* 187 */     return "";
  }
  
  public int getPrecision(int paramInt)
    throws SQLException
  {
/* 195 */     int i = getValidColumnIndex(paramInt);
    
/* 198 */     return this.types[i].getPrecision();
  }
  
  public int getScale(int paramInt)
    throws SQLException
  {
/* 206 */     int i = getValidColumnIndex(paramInt);
    
/* 209 */     return this.types[i].getScale();
  }
  
  public String getTableName(int paramInt)
    throws SQLException
  {
/* 217 */     return null;
  }
  
  public String getCatalogName(int paramInt)
    throws SQLException
  {
/* 225 */     return null;
  }
  
  public int getColumnType(int paramInt)
    throws SQLException
  {
/* 233 */     int i = getValidColumnIndex(paramInt);
    
/* 235 */     return this.types[i].getTypeCode();
  }
  
  public String getColumnTypeName(int paramInt)
    throws SQLException
  {
/* 251 */     int i = getColumnType(paramInt);
/* 252 */     int j = getValidColumnIndex(paramInt);
    
/* 254 */     switch (i)
    {
    case 12: 
/* 258 */       return "VARCHAR";
    
    case 1: 
/* 261 */       return "CHAR";
    
    case -2: 
/* 264 */       return "RAW";
    
    case 6: 
/* 267 */       return "FLOAT";
    
    case 2: 
/* 270 */       return "NUMBER";
    
    case 8: 
/* 273 */       return "DOUBLE";
    
    case 3: 
/* 276 */       return "DECIMAL";
    
    case 100: 
/* 279 */       return "BINARY_FLOAT";
    
    case 101: 
/* 282 */       return "BINARY_DOUBLE";
    
    case 91: 
/* 285 */       return "DATE";
    
    case -104: 
/* 288 */       return "INTERVALDS";
    
    case -103: 
/* 291 */       return "INTERVALYM";
    
    case 93: 
/* 294 */       return "TIMESTAMP";
    
    case -101: 
/* 297 */       return "TIMESTAMP WITH TIME ZONE";
    
    case -102: 
/* 300 */       return "TIMESTAMP WITH LOCAL TIME ZONE";
    
    case 2004: 
/* 303 */       return "BLOB";
    
    case 2005: 
/* 306 */       return "CLOB";
    
    case -13: 
/* 309 */       return "BFILE";
    
    case 2002: 
    case 2003: 
    case 2007: 
    case 2008: 
/* 318 */       return ((OracleTypeADT)this.types[j]).getFullName();
    
    case 2006: 
/* 321 */       return "REF " + ((OracleTypeREF)this.types[j]).getFullName();
    
    case -15: 
/* 326 */       return "NCHAR";
    
    case -9: 
/* 329 */       return "NVARCHAR";
    
    case 2011: 
/* 332 */       return "NCLOB";
    }
    
    
/* 338 */     return null;
  }
  
  public boolean isReadOnly(int paramInt)
    throws SQLException
  {
/* 347 */     return false;
  }
  
  public boolean isWritable(int paramInt)
    throws SQLException
  {
/* 355 */     return false;
  }
  
  public boolean isDefinitelyWritable(int paramInt)
    throws SQLException
  {
/* 363 */     return false;
  }
  
  public String getColumnClassName(int paramInt)
    throws SQLException
  {
/* 371 */     int i = getColumnType(paramInt);
    
/* 373 */     switch (i)
    {
    case -15: 
    case -9: 
    case 1: 
    case 12: 
/* 385 */       return "java.lang.String";
    
    case -2: 
/* 388 */       return "byte[]";
    
    case 2: 
    case 3: 
    case 6: 
    case 8: 
/* 397 */       return "java.math.BigDecimal";
    
    case 91: 
/* 400 */       return "java.sql.Timestamp";
    
    case -103: 
/* 403 */       return "oracle.sql.INTERVALYM";
    
    case -104: 
/* 406 */       return "oracle.sql.INTERVALDS";
    
    case 93: 
/* 409 */       return "oracle.sql.TIMESTAMP";
    
    case -101: 
/* 412 */       return "oracle.sql.TIMESTAMPTZ";
    
    case -102: 
/* 415 */       return "oracle.sql.TIMESTAMPLTZ";
    
    case 2004: 
/* 418 */       return OracleBlob.class.getName();
    
    case 2005: 
/* 421 */       return OracleClob.class.getName();
    
    case 2011: 
/* 427 */       return OracleNClob.class.getName();
    
    case -13: 
/* 431 */       return OracleBfile.class.getName();
    
    case 2002: 
    case 2008: 
/* 436 */       return OracleStruct.class.getName();
    
    case 2007: 
/* 439 */       return OracleOpaque.class.getName();
    
    case 2003: 
/* 442 */       return OracleArray.class.getName();
    
    case 2006: 
/* 445 */       return OracleRef.class.getName();
    }
    
    
/* 450 */     return null;
  }
  
  public String getOracleColumnClassName(int paramInt)
    throws SQLException
  {
/* 464 */     int i = getColumnType(paramInt);
    
/* 466 */     switch (i)
    {
    case -15: 
    case -9: 
    case 1: 
    case 12: 
/* 478 */       return "CHAR";
    
    case -2: 
/* 481 */       return "RAW";
    
    case 2: 
    case 3: 
    case 6: 
    case 8: 
/* 490 */       return "NUMBER";
    
    case 91: 
/* 493 */       return "DATE";
    
    case -103: 
/* 496 */       return "INTERVALYM";
    
    case -104: 
/* 499 */       return "INTERVALDS";
    
    case 93: 
/* 502 */       return "TIMESTAMP";
    
    case -101: 
/* 505 */       return "TIMESTAMPTZ";
    
    case -102: 
/* 508 */       return "TIMESTAMPLTZ";
    
    case 2004: 
/* 511 */       return "BLOB";
    
    case 2005: 
/* 514 */       return "CLOB";
    
    case 2011: 
/* 520 */       return "NCLOB";
    
    case -13: 
/* 524 */       return "BFILE";
    
    case 2002: 
/* 527 */       return "STRUCT";
    
    case 2008: 
/* 530 */       return "JAVA_STRUCT";
    
    case 2007: 
/* 533 */       return "OPAQUE";
    
    case 2003: 
/* 536 */       return "ARRAY";
    
    case 2006: 
/* 539 */       return "REF";
    }
    
    
/* 544 */     return null;
  }
  
  public int getLocalColumnCount()
    throws SQLException
  {
/* 559 */     return this.descriptor.getLocalAttributeCount();
  }
  
  public boolean isInherited(int paramInt)
    throws SQLException
  {
/* 575 */     return paramInt <= getColumnCount() - getLocalColumnCount();
  }
  
  public String getAttributeJavaName(int paramInt)
    throws SQLException
  {
/* 592 */     int i = getValidColumnIndex(paramInt);
    
/* 594 */     return this.descriptor.getAttributeJavaName(i);
  }
  
  private int getValidColumnIndex(int paramInt)
    throws SQLException
  {
/* 611 */     int i = paramInt - 1;
    
/* 613 */     if ((i < 0) || (i >= this.types.length))
    {
/* 615 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "getValidColumnIndex");
/* 616 */       localSQLException.fillInStackTrace();
/* 617 */       throw localSQLException;
    }
    
/* 620 */     return i;
  }
  
  public boolean isNCHAR(int paramInt)
    throws SQLException
  {
/* 628 */     int i = getValidColumnIndex(paramInt);
    
/* 630 */     return this.types[i].isNCHAR();
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 649 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 651 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 652 */     localSQLException.fillInStackTrace();
/* 653 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 670 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 672 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 673 */     localSQLException.fillInStackTrace();
/* 674 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 691 */     return null;
  }
  
/* 696 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/StructMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */