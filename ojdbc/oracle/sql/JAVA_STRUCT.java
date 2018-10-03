package oracle.sql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class JAVA_STRUCT
  extends STRUCT
{
  static final long serialVersionUID = 2211611973003094149L;
  
  public JAVA_STRUCT(StructDescriptor paramStructDescriptor, Connection paramConnection, Object[] paramArrayOfObject)
    throws SQLException
  {
/*  63 */     super(paramStructDescriptor, paramConnection, paramArrayOfObject);
  }
  
  public JAVA_STRUCT(StructDescriptor paramStructDescriptor, byte[] paramArrayOfByte, Connection paramConnection)
    throws SQLException
  {
/*  81 */     super(paramStructDescriptor, paramArrayOfByte, paramConnection);
  }
  
  public Object toJdbc()
    throws SQLException
  {
/*  98 */     Object localObject = getInternalConnection().getJavaObjectTypeMap();
    
/* 100 */     Class localClass = null;
    
/* 102 */     if (localObject != null) {
/* 103 */       localClass = this.descriptor.getClass((Map)localObject);
    }
    else {
/* 106 */       localObject = new Hashtable(10);
      
/* 108 */       getInternalConnection().setJavaObjectTypeMap((Map)localObject);
    }
    
/* 111 */     if (localClass == null)
    {
/* 113 */       String str1 = StructDescriptor.getJavaObjectClassName(getInternalConnection(), getDescriptor());
      
/* 118 */       String str2 = getDescriptor().getSchemaName();
      
/* 120 */       if ((str1 == null) || (str1.length() == 0))
      {
/* 124 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 125 */         localSQLException1.fillInStackTrace();
/* 126 */         throw localSQLException1;
      }
      
      try
      {
/* 132 */         localClass = getInternalConnection().classForNameAndSchema(str1, str2);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
/* 140 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, "ClassNotFoundException: " + localClassNotFoundException.getMessage());
        
/* 142 */         localSQLException2.fillInStackTrace();
/* 143 */         throw localSQLException2;
      }
      
/* 147 */       ((Map)localObject).put(getSQLTypeName(), localClass);
    }
/* 149 */     return toClass(localClass, getMap());
  }
  
  public Object toJdbc(Map paramMap)
    throws SQLException
  {
/* 163 */     return toJdbc();
  }
  
/* 168 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/JAVA_STRUCT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */