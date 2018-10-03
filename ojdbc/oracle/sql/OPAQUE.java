package oracle.sql;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleOpaque;
public class OPAQUE
  extends DatumWithConnection
  implements OracleOpaque
{
  OpaqueDescriptor descriptor;
  byte[] value;
  long imageOffset;
  long imageLength;
  
  public OPAQUE(OpaqueDescriptor paramOpaqueDescriptor, Connection paramConnection, Object paramObject)
    throws SQLException
  {
    Object localObject;
/*  54 */     if (paramOpaqueDescriptor != null) {
/*  55 */       this.descriptor = paramOpaqueDescriptor;
    }
    else
    {
/*  59 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 61, "OPAQUE");
/*  60 */       ((SQLException)localObject).fillInStackTrace();
/*  61 */       throw ((Throwable)localObject);
    }
    
/*  64 */     if (paramConnection != null) {
/*  65 */       setPhysicalConnectionOf(paramConnection);
    }
/*  67 */     if ((paramObject instanceof ANYDATA))
    {
/*  72 */       localObject = (ANYDATA)paramObject;
/*  73 */       byte[] arrayOfByte = new byte[((ANYDATA)localObject).getImageSize()];
/*  74 */       ((ANYDATA)localObject).pickle(arrayOfByte, 0);
/*  75 */       this.value = arrayOfByte;
    }
/*  77 */     else if ((paramObject instanceof byte[])) {
/*  78 */       this.value = ((byte[])paramObject);
    }
    else
    {
/*  82 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/*  83 */       ((SQLException)localObject).fillInStackTrace();
/*  84 */       throw ((Throwable)localObject);
    }
  }
  
  public OPAQUE(OpaqueDescriptor paramOpaqueDescriptor, byte[] paramArrayOfByte, Connection paramConnection)
    throws SQLException
  {
/* 102 */     super(paramArrayOfByte);
    
/* 104 */     setPhysicalConnectionOf(paramConnection);
    
/* 106 */     this.descriptor = paramOpaqueDescriptor;
/* 107 */     this.value = null;
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
/* 125 */     return this.descriptor.getName();
  }
  
  public String stringValue()
    throws SQLException
  {
/* 132 */     String str1 = "OPAQUE";
    try
    {
/* 135 */       String str2 = null;
/* 136 */       Object localObject = toJdbc();
/* 137 */       Class localClass = localObject.getClass();
      
/* 140 */       if (!localClass.equals(getClass()))
      {
        try
        {
/* 145 */           Method localMethod1 = localClass.getMethod("getStringVal", new Class[0]);
/* 146 */           if (localMethod1.getDeclaringClass().equals(localClass))
          {
/* 148 */             str2 = (String)localMethod1.invoke(localObject, new Object[0]);
          }
        }
        catch (Exception localException2) {}
/* 152 */         if (str2 == null)
        {
          try
          {
/* 156 */             Method localMethod2 = localClass.getMethod("stringValue", new Class[0]);
/* 157 */             if (localMethod2.getDeclaringClass() == localClass)
            {
/* 159 */               str2 = (String)localMethod2.invoke(localObject, new Object[0]);
            }
          }
          catch (Exception localException3) {}
        }
/* 164 */         if (str2 != null)
/* 165 */           str1 = str1 + "(" + str2 + ")";
      }
    } catch (Exception localException1) {}
/* 168 */     return str1;
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
/* 184 */     return getDescriptor();
  }
  
  public OpaqueDescriptor getDescriptor()
    throws SQLException
  {
/* 199 */     return this.descriptor;
  }
  
  public void setDescriptor(OpaqueDescriptor paramOpaqueDescriptor)
  {
/* 210 */     this.descriptor = paramOpaqueDescriptor;
  }
  
  public byte[] toBytes()
    throws SQLException
  {
/* 223 */     return this.descriptor.toBytes(this, false);
  }
  
  public Object getValue()
    throws SQLException
  {
/* 234 */     return this.descriptor.toValue(this, false);
  }
  
  public byte[] getBytesValue()
    throws SQLException
  {
/* 246 */     return this.descriptor.toValue(this, false);
  }
  
  public void setValue(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 256 */     this.value = paramArrayOfByte;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 270 */     return false;
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 280 */     return new Object[paramInt];
  }
  
  public Map getMap()
  {
    try
    {
/* 293 */       return getInternalConnection().getTypeMap();
    }
    catch (SQLException localSQLException) {}
    
/* 297 */     return null;
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 313 */     Map localMap = getMap();
/* 314 */     return toJdbc(localMap);
  }
  
  public Object toJdbc(Map paramMap)
    throws SQLException
  {
/* 326 */     Object localObject = this;
    
/* 328 */     if (paramMap != null)
    {
/* 330 */       Class localClass = this.descriptor.getClass(paramMap);
      
/* 332 */       if (localClass != null) {
/* 333 */         localObject = toClass(localClass, paramMap);
      }
    }
/* 336 */     return localObject;
  }
  
  public Object toClass(Class paramClass)
    throws SQLException
  {
/* 348 */     return toClass(paramClass, getMap());
  }
  
  public Object toClass(Class paramClass, Map paramMap)
    throws SQLException
  {
/* 368 */     Object localObject1 = null;
    
    try
    {
/* 372 */       if ((paramClass == null) || (paramClass == OPAQUE.class)) {
/* 373 */         localObject1 = this;
      }
      else {
/* 376 */         Object localObject2 = null;
/* 377 */         localObject3 = paramClass.newInstance();
        Object localObject4;
/* 379 */         if ((localObject3 instanceof ORADataFactory))
        {
/* 381 */           localObject4 = (ORADataFactory)localObject3;
          
/* 383 */           localObject2 = ((ORADataFactory)localObject4).create(this, 2007);
        }
/* 385 */         else if ((localObject3 instanceof OracleDataFactory))
        {
/* 387 */           localObject4 = (OracleDataFactory)localObject3;
/* 388 */           localObject2 = ((OracleDataFactory)localObject4).create(this, 2007);
        }
        else
        {
/* 394 */           localObject4 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, this.descriptor.getName());
/* 395 */           ((SQLException)localObject4).fillInStackTrace();
/* 396 */           throw ((Throwable)localObject4);
        }
        
/* 400 */         localObject1 = localObject2;
      }
      
    }
    catch (InstantiationException localInstantiationException)
    {
/* 406 */       localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, "InstantiationException: " + localInstantiationException.getMessage());
      
/* 408 */       ((SQLException)localObject3).fillInStackTrace();
/* 409 */       throw ((Throwable)localObject3);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 416 */       Object localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, "IllegalAccessException: " + localIllegalAccessException.getMessage());
      
/* 418 */       ((SQLException)localObject3).fillInStackTrace();
/* 419 */       throw ((Throwable)localObject3);
    }
    
/* 423 */     return localObject1;
  }
  
  public void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws SQLException
  {
/* 434 */     setShareBytes(paramArrayOfByte);
    
/* 436 */     this.imageOffset = paramLong1;
/* 437 */     this.imageLength = paramLong2;
  }
  
  public void setImageLength(long paramLong)
    throws SQLException
  {
/* 447 */     this.imageLength = paramLong;
  }
  
  public long getImageOffset()
  {
/* 457 */     return this.imageOffset;
  }
  
  public long getImageLength()
  {
/* 467 */     return this.imageLength;
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
/* 473 */     return super.getJavaSqlConnection();
  }
  
/* 477 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/OPAQUE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */