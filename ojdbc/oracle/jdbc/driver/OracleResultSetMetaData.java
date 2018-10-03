package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
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
import oracle.jdbc.oracore.OracleNamedType;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
class OracleResultSetMetaData
  implements oracle.jdbc.internal.OracleResultSetMetaData
{
  PhysicalConnection connection;
  OracleStatement statement;
  int m_beginColumnIndex;
  
  public OracleResultSetMetaData() {}
  
  public OracleResultSetMetaData(PhysicalConnection paramPhysicalConnection, OracleStatement paramOracleStatement)
    throws SQLException
  {
/*  52 */     this.connection = paramPhysicalConnection;
/*  53 */     this.statement = paramOracleStatement;
    
/*  55 */     paramOracleStatement.describe();
    
/*  57 */     this.m_beginColumnIndex = 0;
  }
  
  OracleResultSetMetaData(PhysicalConnection paramPhysicalConnection, OracleStatement paramOracleStatement, int paramInt)
    throws SQLException
  {
/*  69 */     this.connection = paramPhysicalConnection;
/*  70 */     this.statement = paramOracleStatement;
    
/*  72 */     paramOracleStatement.describe();
    
/*  74 */     this.m_beginColumnIndex = paramInt;
  }
  
  public OracleResultSetMetaData(OracleResultSet paramOracleResultSet)
    throws SQLException
  {
/*  85 */     this.statement = ((OracleStatement)((OracleStatementWrapper)paramOracleResultSet.getStatement()).statement);
/*  86 */     this.connection = ((PhysicalConnection)this.statement.getConnection());
    
/*  88 */     this.statement.describe();
    
/*  90 */     this.m_beginColumnIndex = paramOracleResultSet.getFirstUserColumnIndex();
  }
  
  public int getColumnCount()
    throws SQLException
  {
/* 104 */     return this.statement.getNumberOfColumns() - this.m_beginColumnIndex;
  }
  
  public boolean isAutoIncrement(int paramInt)
    throws SQLException
  {
/* 117 */     return false;
  }
  
  int getValidColumnIndex(int paramInt)
    throws SQLException
  {
/* 133 */     int i = paramInt + this.m_beginColumnIndex - 1;
    
/* 135 */     if ((i < 0) || (i >= this.statement.getNumberOfColumns()))
    {
/* 137 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "getValidColumnIndex");
/* 138 */       localSQLException.fillInStackTrace();
/* 139 */       throw localSQLException;
    }
    
/* 142 */     return i;
  }
  
  public boolean isCaseSensitive(int paramInt)
    throws SQLException
  {
/* 155 */     int i = getColumnType(paramInt);
    
/* 157 */     return (i == 1) || (i == 12) || (i == -1) || (i == -15) || (i == -9);
  }
  
  public boolean isSearchable(int paramInt)
    throws SQLException
  {
/* 175 */     int i = getColumnType(paramInt);
    
/* 178 */     return (i != -4) && (i != -1) && (i != 2004) && (i != 2005) && (i != -13) && (i != 2011) && (i != 2002) && (i != 2008) && (i != 2007) && (i != 2003) && (i != 2006) && (i != -10);
  }
  
  public boolean isCurrency(int paramInt)
    throws SQLException
  {
/* 200 */     int i = getColumnType(paramInt);
    
/* 202 */     return (i == 2) || (i == 6);
  }
  
  public int isNullable(int paramInt)
    throws SQLException
  {
/* 215 */     int i = getValidColumnIndex(paramInt);
    
/* 217 */     return getDescription()[i].nullable ? 1 : 0;
  }
  
  public boolean isSigned(int paramInt)
    throws SQLException
  {
/* 231 */     return true;
  }
  
  public int getColumnDisplaySize(int paramInt)
    throws SQLException
  {
/* 244 */     int i = getValidColumnIndex(paramInt);
    
/* 247 */     int j = getDescription()[i].describeType;
    
/* 249 */     switch (j)
    {
    case 2: 
/* 253 */       int k = getPrecision(paramInt);
/* 254 */       int m = getDescription()[i].scale;
      
/* 256 */       if ((k != 0) && (m == -127))
      {
/* 260 */         k = (int)(k / 3.32193D);
/* 261 */         m = 1;
      }
      else
      {
/* 265 */         if (k == 0) {
/* 266 */           k = 38;
        }
/* 268 */         if (m == -127) {
/* 269 */           m = 0;
        }
      }
/* 272 */       int n = k + (m != 0 ? 1 : 0) + 1;
      
/* 274 */       return n;
    }
    
    
/* 278 */     return getDescription()[i].describeMaxLength;
  }
  
  public String getColumnLabel(int paramInt)
    throws SQLException
  {
/* 293 */     return getColumnName(paramInt);
  }
  
  public String getColumnName(int paramInt)
    throws SQLException
  {
/* 306 */     int i = getValidColumnIndex(paramInt);
    
/* 308 */     return this.statement.getDescriptionWithNames()[i].columnName;
  }
  
  public String getSchemaName(int paramInt)
    throws SQLException
  {
/* 322 */     return "";
  }
  
  public int getPrecision(int paramInt)
    throws SQLException
  {
/* 335 */     int i = getValidColumnIndex(paramInt);
    
/* 338 */     int j = getDescription()[i].describeType;
    
/* 340 */     switch (j)
    {
    case 112: 
    case 113: 
/* 344 */       return -1;
    
    case 8: 
    case 24: 
/* 348 */       return Integer.MAX_VALUE;
    
    case 1: 
    case 96: 
/* 352 */       return getDescription()[i].describeMaxLength;
    }
    
/* 355 */     return getDescription()[i].precision;
  }
  
  public OracleResultSetMetaData.SecurityAttribute getSecurityAttribute(int paramInt)
    throws SQLException
  {
/* 364 */     int i = getValidColumnIndex(paramInt);
/* 365 */     return getDescription()[i].securityAttribute;
  }
  
  public int getScale(int paramInt)
    throws SQLException
  {
/* 378 */     int i = getValidColumnIndex(paramInt);
/* 379 */     int j = getDescription()[i].scale;
    
/* 381 */     return (j == -127) && (this.statement.connection.j2ee13Compliant) ? 0 : j;
  }
  
  public String getTableName(int paramInt)
    throws SQLException
  {
/* 395 */     return "";
  }
  
  public String getCatalogName(int paramInt)
    throws SQLException
  {
/* 409 */     return "";
  }
  
  public int getColumnType(int paramInt)
    throws SQLException
  {
/* 427 */     int i = getValidColumnIndex(paramInt);
    
/* 430 */     int j = getDescription()[i].describeType;
    
/* 434 */     switch (j)
    {
    case 96: 
/* 440 */       if (getDescription()[i].formOfUse == 2)
      {
/* 442 */         return -15;
      }
/* 444 */       return 1;
    
    case 1: 
/* 451 */       if (getDescription()[i].formOfUse == 2)
      {
/* 453 */         return -9;
      }
/* 455 */       return 12;
    
    case 8: 
/* 459 */       return -1;
    
    case 2: 
    case 6: 
/* 463 */       if ((this.statement.connection.j2ee13Compliant) && (getDescription()[i].precision != 0) && (getDescription()[i].scale == -127))
      {
/* 466 */         return 6;
      }
/* 468 */       return 2;
    
    case 100: 
/* 471 */       return 100;
    
    case 101: 
/* 474 */       return 101;
    
    case 23: 
/* 477 */       return -3;
    
    case 24: 
/* 480 */       return -4;
    
    case 104: 
    case 208: 
/* 484 */       return -8;
    
    case 102: 
/* 487 */       return -10;
    
    case 12: 
/* 493 */       return this.connection.mapDateToTimestamp ? 93 : 91;
    
    case 180: 
/* 497 */       return 93;
    
    case 181: 
/* 500 */       return -101;
    
    case 231: 
/* 503 */       return -102;
    
    case 113: 
/* 506 */       return 2004;
    
    case 112: 
/* 512 */       if (getDescription()[i].formOfUse == 2)
      {
/* 514 */         return 2011;
      }
/* 516 */       return 2005;
    
    case 114: 
/* 520 */       return -13;
    
    case 109: 
/* 524 */       OracleNamedType localOracleNamedType = (OracleNamedType)getDescription()[i].describeOtype;
      
/* 527 */       TypeDescriptor localTypeDescriptor = TypeDescriptor.getTypeDescriptor(localOracleNamedType.getFullName(), this.connection);
      
/* 531 */       if (localTypeDescriptor != null) {
/* 532 */         return localTypeDescriptor.getTypeCode();
      }
      
/* 535 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60);
/* 536 */       localSQLException.fillInStackTrace();
/* 537 */       throw localSQLException;
    
    case 111: 
/* 542 */       return 2006;
    
    case 182: 
/* 545 */       return -103;
    
    case 183: 
/* 548 */       return -104;
    }
    
/* 551 */     return 1111;
  }
  
  public String getColumnTypeName(int paramInt)
    throws SQLException
  {
/* 565 */     int i = getValidColumnIndex(paramInt);
    
/* 568 */     int j = getDescription()[i].describeType;
    
    OracleTypeADT localOracleTypeADT;
    
/* 572 */     switch (j)
    {
    case 96: 
/* 578 */       if (getDescription()[i].formOfUse == 2)
      {
/* 580 */         return "NCHAR";
      }
/* 582 */       return "CHAR";
    
    case 1: 
/* 589 */       if (getDescription()[i].formOfUse == 2)
      {
/* 591 */         return "NVARCHAR2";
      }
/* 593 */       return "VARCHAR2";
    
    case 8: 
/* 597 */       return "LONG";
    
    case 2: 
    case 6: 
/* 601 */       if ((this.statement.connection.j2ee13Compliant) && (getDescription()[i].precision != 0) && (getDescription()[i].scale == -127))
      {
/* 604 */         return "FLOAT";
      }
/* 606 */       return "NUMBER";
    
    case 100: 
/* 609 */       return "BINARY_FLOAT";
    
    case 101: 
/* 612 */       return "BINARY_DOUBLE";
    
    case 23: 
/* 615 */       return "RAW";
    
    case 24: 
/* 618 */       return "LONG RAW";
    
    case 104: 
    case 208: 
/* 622 */       return "ROWID";
    
    case 102: 
/* 625 */       return "REFCURSOR";
    
    case 12: 
/* 631 */       return "DATE";
    
    case 180: 
/* 634 */       return "TIMESTAMP";
    
    case 181: 
/* 637 */       return "TIMESTAMP WITH TIME ZONE";
    
    case 231: 
/* 640 */       return "TIMESTAMP WITH LOCAL TIME ZONE";
    
    case 113: 
/* 643 */       return "BLOB";
    
    case 112: 
/* 649 */       if (getDescription()[i].formOfUse == 2)
      {
/* 651 */         return "NCLOB";
      }
/* 653 */       return "CLOB";
    
    case 114: 
/* 657 */       return "BFILE";
    
    case 109: 
/* 661 */       localOracleTypeADT = (OracleTypeADT)getDescription()[i].describeOtype;
      
/* 664 */       return localOracleTypeADT.getFullName();
    
    case 111: 
/* 669 */       localOracleTypeADT = (OracleTypeADT)getDescription()[i].describeOtype;
      
/* 672 */       return localOracleTypeADT.getFullName();
    
    case 182: 
/* 676 */       return "INTERVALYM";
    
    case 183: 
/* 679 */       return "INTERVALDS";
    }
    
/* 682 */     return null;
  }
  
  public boolean isReadOnly(int paramInt)
    throws SQLException
  {
/* 697 */     return false;
  }
  
  public boolean isWritable(int paramInt)
    throws SQLException
  {
/* 711 */     return true;
  }
  
  public boolean isDefinitelyWritable(int paramInt)
    throws SQLException
  {
/* 725 */     return false;
  }
  
  public String getColumnClassName(int paramInt)
    throws SQLException
  {
/* 747 */     int i = getValidColumnIndex(paramInt);
    
/* 750 */     int j = getDescription()[i].describeType;
    
/* 752 */     switch (j)
    {
    case 1: 
    case 8: 
    case 96: 
    case 999: 
/* 758 */       return "java.lang.String";
    
    case 2: 
    case 6: 
/* 762 */       if ((getDescription()[i].precision != 0) && (getDescription()[i].scale == -127))
      {
/* 764 */         return "java.lang.Double";
      }
/* 766 */       return "java.math.BigDecimal";
    
    case 23: 
    case 24: 
/* 770 */       return "byte[]";
    
    case 12: 
/* 773 */       return "java.sql.Timestamp";
    
    case 180: 
/* 776 */       if (this.statement.connection.j2ee13Compliant) {
/* 777 */         return "java.sql.Timestamp";
      }
/* 779 */       return "oracle.sql.TIMESTAMP";
    
    case 181: 
/* 782 */       return "oracle.sql.TIMESTAMPTZ";
    
    case 231: 
/* 785 */       return "oracle.sql.TIMESTAMPLTZ";
    
    case 182: 
/* 788 */       return "oracle.sql.INTERVALYM";
    
    case 183: 
/* 791 */       return "oracle.sql.INTERVALDS";
    
    case 104: 
    case 208: 
/* 795 */       return "oracle.sql.ROWID";
    
    case 113: 
/* 798 */       return OracleBlob.class.getName();
    
    case 112: 
/* 804 */       if (getDescription()[i].formOfUse == 2)
      {
/* 806 */         return OracleNClob.class.getName();
      }
/* 808 */       return OracleClob.class.getName();
    
    case 114: 
/* 812 */       return OracleBfile.class.getName();
    
    case 102: 
/* 815 */       return "OracleResultSet";
    case 109: 
      Object localObject2;
      
/* 819 */       switch (getColumnType(paramInt))
      {
      case 2003: 
/* 822 */         return OracleArray.class.getName();
      
      case 2007: 
/* 825 */         return OracleOpaque.class.getName();
      
      case 2008: 
/* 829 */         localObject1 = (OracleNamedType)getDescription()[i].describeOtype;
        
/* 832 */         localObject2 = this.connection.getJavaObjectTypeMap();
        
/* 834 */         if (localObject2 != null)
        {
/* 836 */           Class localClass = (Class)((Map)localObject2).get(((OracleNamedType)localObject1).getFullName());
          
/* 838 */           if (localClass != null) {
/* 839 */             return localClass.getName();
          }
        }
/* 842 */         return StructDescriptor.getJavaObjectClassName(this.connection, ((OracleNamedType)localObject1).getSchemaName(), ((OracleNamedType)localObject1).getSimpleName());
      
      case 2002: 
/* 848 */         localObject1 = this.connection.getTypeMap();
        
/* 850 */         if (localObject1 != null)
        {
/* 852 */           localObject2 = (Class)((Map)localObject1).get(((OracleNamedType)getDescription()[i].describeOtype).getFullName());
          
/* 855 */           if (localObject2 != null) {
/* 856 */             return ((Class)localObject2).getName();
          }
        }
/* 859 */         return OracleStruct.class.getName();
      
      case 2009: 
/* 866 */         return "java.sql.SQLXML";
      }
      
      
/* 871 */       Object localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 872 */       ((SQLException)localObject1).fillInStackTrace();
/* 873 */       throw ((Throwable)localObject1);
    
    case 111: 
/* 879 */       return OracleRef.class.getName();
    
    case 101: 
/* 883 */       return "oracle.sql.BINARY_DOUBLE";
    
    case 100: 
/* 886 */       return "oracle.sql.BINARY_FLOAT";
    }
    
/* 889 */     return null;
  }
  
  public boolean isNCHAR(int paramInt)
    throws SQLException
  {
/* 908 */     int i = getValidColumnIndex(paramInt);
    
/* 910 */     return getDescription()[i].formOfUse == 2;
  }
  
  Accessor[] getDescription()
    throws SQLException
  {
/* 920 */     return this.statement.getDescription();
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 938 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 940 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 941 */     localSQLException.fillInStackTrace();
/* 942 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 959 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 961 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 962 */     localSQLException.fillInStackTrace();
/* 963 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 980 */     return this.connection;
  }
  
/* 985 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleResultSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */