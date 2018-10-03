package oracle.jdbc.driver;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.Map;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.oracore.OracleType;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.Datum;
import oracle.sql.JAVA_STRUCT;
import oracle.sql.OPAQUE;
import oracle.sql.OpaqueDescriptor;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
class NamedTypeAccessor
  extends TypeAccessor
{
  private static final Class xmlType;
  
  NamedTypeAccessor(OracleStatement paramOracleStatement, String paramString, short paramShort, int paramInt, boolean paramBoolean)
    throws SQLException
  {
/*  40 */     init(paramOracleStatement, 109, 109, paramShort, paramBoolean);
/*  41 */     initForDataAccess(paramInt, 0, paramString);
  }
  
  NamedTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString)
    throws SQLException
  {
/*  51 */     init(paramOracleStatement, 109, 109, paramShort, false);
/*  52 */     initForDescribe(109, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/*  54 */     initForDataAccess(0, paramInt1, paramString);
  }
  
  NamedTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString, OracleType paramOracleType)
    throws SQLException
  {
/*  64 */     init(paramOracleStatement, 109, 109, paramShort, false);
    
/*  66 */     this.describeOtype = paramOracleType;
    
/*  68 */     initForDescribe(109, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/*  71 */     this.internalOtype = paramOracleType;
    
/*  73 */     initForDataAccess(0, paramInt1, paramString);
  }
  
  OracleType otypeFromName(String paramString)
    throws SQLException
  {
/*  80 */     if (!this.outBind) {
/*  81 */       return TypeDescriptor.getTypeDescriptor(paramString, this.statement.connection).getPickler();
    }
/*  83 */     if (this.externalType == 2003) {
/*  84 */       return ArrayDescriptor.createDescriptor(paramString, this.statement.connection).getOracleTypeCOLLECTION();
    }
/*  86 */     if ((this.externalType == 2007) || (this.externalType == 2009))
    {
/*  93 */       return OpaqueDescriptor.createDescriptor(paramString, this.statement.connection).getPickler();
    }
    
/*  96 */     return StructDescriptor.createDescriptor(paramString, this.statement.connection).getOracleTypeADT();
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/* 105 */     super.initForDataAccess(paramInt1, paramInt2, paramString);
    
/* 107 */     this.byteLength = this.statement.connection.namedTypeAccessorByteLen;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 123 */     return getObject(paramInt, this.statement.connection.getTypeMap());
  }
  
  Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
    throws SQLException
  {
/* 139 */     return paramOracleDataFactory.create(getObject(paramInt, this.statement.connection.getTypeMap()), 0);
  }
  
  static
  {
/* 149 */     Class localClass = null;
    try {
/* 151 */       localClass = Class.forName("oracle.xdb.XMLType");
    }
    catch (Throwable localThrowable) {}
/* 154 */     xmlType = localClass;
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
    Object localObject1;
    
/* 166 */     if (this.rowSpaceIndicator == null)
    {
/* 168 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 169 */       ((SQLException)localObject1).fillInStackTrace();
/* 170 */       throw ((Throwable)localObject1);
    }
    
/* 176 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 180 */       if (this.externalType == 0)
      {
/* 182 */         localObject1 = getOracleObject(paramInt);
        
/* 186 */         if (localObject1 == null) {
/* 187 */           return null;
        }
/* 189 */         if ((localObject1 instanceof STRUCT)) {
/* 190 */           return ((STRUCT)localObject1).toJdbc(paramMap);
        }
/* 192 */         if ((localObject1 instanceof OPAQUE)) {
/* 193 */           Object localObject2 = ((OPAQUE)localObject1).toJdbc(paramMap);
/* 194 */           return localObject2;
        }
        
/* 197 */         return ((Datum)localObject1).toJdbc();
      }
      
/* 202 */       switch (this.externalType)
      {
      case 2008: 
/* 208 */         paramMap = null;
      
      case 2000: 
      case 2002: 
      case 2003: 
      case 2007: 
/* 217 */         localObject1 = getOracleObject(paramInt);
        
/* 221 */         if (localObject1 == null) {
/* 222 */           return null;
        }
/* 224 */         if ((localObject1 instanceof STRUCT)) {
/* 225 */           return ((STRUCT)localObject1).toJdbc(paramMap);
        }
/* 227 */         return ((Datum)localObject1).toJdbc();
      
      case 2009: 
/* 233 */         localObject1 = getOracleObject(paramInt);
/* 234 */         if (localObject1 == null) {
/* 235 */           return null;
        }
        try {
/* 238 */           return (SQLXML)localObject1;
        }
        catch (ClassCastException localClassCastException)
        {
/* 243 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 244 */           localSQLException.fillInStackTrace();
/* 245 */           throw localSQLException;
        }
      }
      
      
/* 252 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 253 */       localSQLException.fillInStackTrace();
/* 254 */       throw localSQLException;
    }
    
/* 261 */     return null;
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 277 */     Object localObject1 = null;
    
/* 280 */     if (this.rowSpaceIndicator == null)
    {
/* 282 */       localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 283 */       ((SQLException)localObject2).fillInStackTrace();
/* 284 */       throw ((Throwable)localObject2);
    }
    
/* 291 */     Object localObject2 = pickledBytes(paramInt);
    
/* 293 */     if ((localObject2 == null) || (localObject2.length == 0))
    {
/* 295 */       return null;
    }
    
/* 298 */     PhysicalConnection localPhysicalConnection = this.statement.connection;
/* 299 */     OracleTypeADT localOracleTypeADT = (OracleTypeADT)this.internalOtype;
/* 300 */     TypeDescriptor localTypeDescriptor = TypeDescriptor.getTypeDescriptor(localOracleTypeADT.getFullName(), localPhysicalConnection, (byte[])localObject2, 0L);
    
/* 303 */     switch (localTypeDescriptor.getTypeCode())
    {
    case 2003: 
/* 307 */       localObject1 = new ARRAY((ArrayDescriptor)localTypeDescriptor, (byte[])localObject2, localPhysicalConnection);
      
/* 309 */       break;
    
    case 2002: 
/* 312 */       localObject1 = new STRUCT((StructDescriptor)localTypeDescriptor, (byte[])localObject2, localPhysicalConnection);
      
/* 314 */       break;
    
    case 2009: 
/* 320 */       localObject1 = ClassRef.XMLTYPE.createXML(new OPAQUE((OpaqueDescriptor)localTypeDescriptor, (byte[])localObject2, localPhysicalConnection));
      
/* 322 */       break;
    
    case 2007: 
/* 326 */       localObject1 = new OPAQUE((OpaqueDescriptor)localTypeDescriptor, (byte[])localObject2, localPhysicalConnection);
      
/* 328 */       break;
    
    case 2008: 
/* 331 */       localObject1 = new JAVA_STRUCT((StructDescriptor)localTypeDescriptor, (byte[])localObject2, localPhysicalConnection);
      
/* 333 */       break;
    
    case 2004: 
    case 2005: 
    case 2006: 
    default: 
/* 339 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 340 */       localSQLException.fillInStackTrace();
/* 341 */       throw localSQLException;
    }
    
    
/* 348 */     return (Datum)localObject1;
  }
  
  ARRAY getARRAY(int paramInt)
    throws SQLException
  {
/* 364 */     return (ARRAY)getOracleObject(paramInt);
  }
  
  STRUCT getSTRUCT(int paramInt)
    throws SQLException
  {
/* 380 */     return (STRUCT)getOracleObject(paramInt);
  }
  
  OPAQUE getOPAQUE(int paramInt)
    throws SQLException
  {
/* 396 */     return (OPAQUE)getOracleObject(paramInt);
  }
  
  boolean isNull(int paramInt)
    throws SQLException
  {
/* 403 */     if (this.rowSpaceIndicator == null)
    {
/* 407 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 408 */       ((SQLException)localObject).fillInStackTrace();
/* 409 */       throw ((Throwable)localObject);
    }
    
/* 414 */     Object localObject = pickledBytes(paramInt);
/* 415 */     return (localObject == null) || (localObject.length == 0);
  }
  
  SQLXML getSQLXML(int paramInt)
    throws SQLException
  {
    try
    {
/* 431 */       OPAQUE localOPAQUE = (OPAQUE)getOracleObject(paramInt);
/* 432 */       if (localOPAQUE == null) return null;
/* 433 */       return (SQLXML)localOPAQUE;
    }
    catch (ClassCastException localClassCastException)
    {
/* 437 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 438 */       localSQLException.fillInStackTrace();
/* 439 */       throw localSQLException;
    }
  }
  
/* 448 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NamedTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */