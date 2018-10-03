package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
class ArrayLocatorResultSet
  extends OracleResultSetImpl
{
/*  23 */   static int COUNT_UNLIMITED = -1;
  
  Map map;
  
  long beginIndex;
  
  int count;
  
  long currentIndex;
  
  public ArrayLocatorResultSet(OracleConnection paramOracleConnection, ArrayDescriptor paramArrayDescriptor, byte[] paramArrayOfByte, Map paramMap)
    throws SQLException
  {
/*  43 */     this(paramOracleConnection, paramArrayDescriptor, paramArrayOfByte, 0L, COUNT_UNLIMITED, paramMap);
  }
  
  public ArrayLocatorResultSet(OracleConnection paramOracleConnection, ArrayDescriptor paramArrayDescriptor, byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
    throws SQLException
  {
/*  63 */     super((PhysicalConnection)paramOracleConnection, (OracleStatement)null);
    
/*  66 */     if ((paramArrayDescriptor == null) || (paramOracleConnection == null))
    {
/*  68 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid arguments");
/*  69 */       ((SQLException)localObject).fillInStackTrace();
/*  70 */       throw ((Throwable)localObject);
    }
    
/*  75 */     this.close_statement_on_close = true;
    
/*  77 */     this.count = paramInt;
/*  78 */     this.currentIndex = 0L;
/*  79 */     this.beginIndex = paramLong;
    
/*  81 */     this.map = paramMap;
    
/*  83 */     Object localObject = null;
    
/*  86 */     ARRAY localARRAY = new ARRAY(paramArrayDescriptor, paramOracleConnection, (byte[])null);
    
/*  88 */     localARRAY.setLocator(paramArrayOfByte);
    
/*  91 */     if ((paramArrayDescriptor.getBaseType() == 2002) || (paramArrayDescriptor.getBaseType() == 2008))
    {
/*  97 */       localObject = (OraclePreparedStatement)((OraclePreparedStatementWrapper)paramOracleConnection.prepareStatement("SELECT ROWNUM, SYS_NC_ROWINFO$ FROM TABLE( CAST(:1 AS " + paramArrayDescriptor.getName() + ") )")).preparedStatement;
    }
    else
    {
/* 106 */       localObject = (OraclePreparedStatement)((OraclePreparedStatementWrapper)paramOracleConnection.prepareStatement("SELECT ROWNUM, COLUMN_VALUE FROM TABLE( CAST(:1 AS " + paramArrayDescriptor.getName() + ") )")).preparedStatement;
    }
    
/* 111 */     ((OraclePreparedStatement)localObject).setArray(1, localARRAY);
/* 112 */     ((OraclePreparedStatement)localObject).executeQuery();
    
/* 114 */     this.statement = ((OracleStatement)localObject);
  }
  
  public boolean next()
    throws SQLException
  {
/* 123 */     synchronized (this.connection)
    {
/* 126 */       if (this.currentIndex < this.beginIndex)
      {
/* 128 */         while (this.currentIndex < this.beginIndex)
        {
/* 130 */           this.currentIndex += 1L;
          
/* 132 */           if (!super.next()) {
/* 133 */             return false;
          }
        }
/* 136 */         return true;
      }
      
/* 140 */       if (this.count == COUNT_UNLIMITED)
      {
/* 142 */         return super.next();
      }
/* 144 */       if (this.currentIndex < this.beginIndex + this.count - 1L)
      {
/* 146 */         this.currentIndex += 1L;
        
/* 148 */         return super.next();
      }
      
/* 152 */       return false;
    }
  }
  
  /* Error */
  public Object getObject(int paramInt)
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 35	oracle/jdbc/driver/ArrayLocatorResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
    //   4: dup
    //   5: astore_2
    //   6: monitorenter
    //   7: aload_0
    //   8: iload_1
    //   9: aload_0
    //   10: getfield 14	oracle/jdbc/driver/ArrayLocatorResultSet:map	Ljava/util/Map;
    //   13: invokevirtual 37	oracle/jdbc/driver/ArrayLocatorResultSet:getObject	(ILjava/util/Map;)Ljava/lang/Object;
    //   16: aload_2
    //   17: monitorexit
    //   18: areturn
    //   19: astore_3
    //   20: aload_2
    //   21: monitorexit
    //   22: aload_3
    //   23: athrow
    // Line number table:
    //   Java source line #162	-> byte code offset #0
    //   Java source line #165	-> byte code offset #7
    //   Java source line #167	-> byte code offset #19
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	24	0	this	ArrayLocatorResultSet
    //   0	24	1	paramInt	int
    //   5	16	2	Ljava/lang/Object;	Object
    //   19	4	3	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	18	19	finally
    //   19	22	19	finally
  }
  
  public int findColumn(String paramString)
    throws SQLException
  {
/* 175 */     synchronized (this.connection)
    {
/* 178 */       if (paramString.equalsIgnoreCase("index"))
/* 179 */         return 1;
/* 180 */       if (paramString.equalsIgnoreCase("value")) {
/* 181 */         return 2;
      }
      
/* 184 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6, "get_column_index");
/* 185 */       localSQLException.fillInStackTrace();
/* 186 */       throw localSQLException;
    }
  }
  
/* 194 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ArrayLocatorResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */