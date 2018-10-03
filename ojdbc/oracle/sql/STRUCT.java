package oracle.sql;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.util.Hashtable;
import java.util.Map;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleStruct;
import oracle.jdbc.proxy.OracleProxy;
import oracle.jdbc.proxy.ProxyFactory;
public class STRUCT
  extends DatumWithConnection
  implements OracleStruct
{
  StructDescriptor descriptor;
  Datum[] datumArray;
  Object[] objectArray;
/*  58 */   boolean enableLocalCache = false;
  
  long imageOffset;
  
  long imageLength;
  
  public STRUCT(StructDescriptor paramStructDescriptor, Connection paramConnection, Object[] paramArrayOfObject)
    throws SQLException
  {
/*  88 */     assertNotNull(paramStructDescriptor);
    
/*  90 */     this.descriptor = paramStructDescriptor;
    
/*  92 */     assertNotNull(paramConnection);
    SQLException localSQLException;
/*  94 */     if (!paramStructDescriptor.getInternalConnection().isDescriptorSharable(((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin()))
    {
/*  97 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct STRUCT instance,invalid connection");
      
/*  99 */       localSQLException.fillInStackTrace();
/* 100 */       throw localSQLException;
    }
    
/* 104 */     paramStructDescriptor.setConnection(paramConnection);
    
/* 106 */     if (!this.descriptor.isInstantiable())
    {
/* 109 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct STRUCT instance for a non-instantiable object type");
      
/* 111 */       localSQLException.fillInStackTrace();
/* 112 */       throw localSQLException;
    }
    
/* 116 */     setPhysicalConnectionOf(paramConnection);
    
/* 118 */     if (paramArrayOfObject != null) {
/* 119 */       this.datumArray = this.descriptor.toArray(paramArrayOfObject);
    } else {
/* 121 */       this.datumArray = new Datum[this.descriptor.getLength()];
    }
  }
  
  public STRUCT(StructDescriptor paramStructDescriptor, Connection paramConnection, Map paramMap)
    throws SQLException
  {
/* 129 */     assertNotNull(paramStructDescriptor);
    
/* 131 */     this.descriptor = paramStructDescriptor;
    
/* 133 */     assertNotNull(paramConnection);
    SQLException localSQLException;
/* 135 */     if (!paramStructDescriptor.getInternalConnection().isDescriptorSharable(((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin()))
    {
/* 138 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct STRUCT instance,invalid connection");
      
/* 140 */       localSQLException.fillInStackTrace();
/* 141 */       throw localSQLException;
    }
    
/* 145 */     paramStructDescriptor.setConnection(paramConnection);
    
/* 147 */     if (!this.descriptor.isInstantiable())
    {
/* 150 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct STRUCT instance for a non-instantiable object type");
      
/* 152 */       localSQLException.fillInStackTrace();
/* 153 */       throw localSQLException;
    }
    
/* 157 */     setPhysicalConnectionOf(paramConnection);
    
/* 159 */     this.datumArray = this.descriptor.toOracleArray(paramMap);
  }
  
  public STRUCT(StructDescriptor paramStructDescriptor, byte[] paramArrayOfByte, Connection paramConnection)
    throws SQLException
  {
/* 176 */     super(paramArrayOfByte);
    
/* 178 */     assertNotNull(paramStructDescriptor);
    
/* 180 */     this.descriptor = paramStructDescriptor;
    
/* 182 */     assertNotNull(paramConnection);
    
/* 184 */     if (!paramStructDescriptor.getInternalConnection().isDescriptorSharable(((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin()))
    {
/* 187 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct STRUCT instance,invalid connection");
      
/* 189 */       localSQLException.fillInStackTrace();
/* 190 */       throw localSQLException;
    }
    
/* 194 */     paramStructDescriptor.setConnection(paramConnection);
/* 195 */     setPhysicalConnectionOf(paramConnection);
    
/* 197 */     this.datumArray = null;
  }
  
  /* Error */
  public String getSQLTypeName()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 4	oracle/sql/STRUCT:descriptor	Loracle/sql/StructDescriptor;
    //   11: invokevirtual 25	oracle/sql/StructDescriptor:getName	()Ljava/lang/String;
    //   14: aload_1
    //   15: monitorexit
    //   16: areturn
    //   17: astore_2
    //   18: aload_1
    //   19: monitorexit
    //   20: aload_2
    //   21: athrow
    // Line number table:
    //   Java source line #218	-> byte code offset #0
    //   Java source line #220	-> byte code offset #7
    //   Java source line #222	-> byte code offset #17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	22	0	this	STRUCT
    //   5	14	1	Ljava/lang/Object;	Object
    //   17	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	16	17	finally
    //   17	20	17	finally
  }
  
  public Object[] getAttributes()
    throws SQLException
  {
/* 248 */     synchronized (getInternalConnection())
    {
/* 250 */       Object[] arrayOfObject = getAttributes(getMap());
      
/* 252 */       return arrayOfObject;
    }
  }
  
  /* Error */
  public Object[] getAttributes(Map paramMap)
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_2
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 4	oracle/sql/STRUCT:descriptor	Loracle/sql/StructDescriptor;
    //   11: aload_0
    //   12: aload_1
    //   13: aload_0
    //   14: getfield 2	oracle/sql/STRUCT:enableLocalCache	Z
    //   17: invokevirtual 28	oracle/sql/StructDescriptor:toArray	(Loracle/sql/STRUCT;Ljava/util/Map;Z)[Ljava/lang/Object;
    //   20: aload_2
    //   21: monitorexit
    //   22: areturn
    //   23: astore_3
    //   24: aload_2
    //   25: monitorexit
    //   26: aload_3
    //   27: athrow
    // Line number table:
    //   Java source line #282	-> byte code offset #0
    //   Java source line #284	-> byte code offset #7
    //   Java source line #286	-> byte code offset #23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	28	0	this	STRUCT
    //   0	28	1	paramMap	Map
    //   5	20	2	Ljava/lang/Object;	Object
    //   23	4	3	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	22	23	finally
    //   23	26	23	finally
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
/* 301 */     return getDescriptor();
  }
  
  /* Error */
  public StructDescriptor getDescriptor()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 4	oracle/sql/STRUCT:descriptor	Loracle/sql/StructDescriptor;
    //   11: aload_1
    //   12: monitorexit
    //   13: areturn
    //   14: astore_2
    //   15: aload_1
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Line number table:
    //   Java source line #315	-> byte code offset #0
    //   Java source line #317	-> byte code offset #7
    //   Java source line #319	-> byte code offset #14
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	19	0	this	STRUCT
    //   5	11	1	Ljava/lang/Object;	Object
    //   14	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	13	14	finally
    //   14	17	14	finally
  }
  
  public void setDescriptor(StructDescriptor paramStructDescriptor)
  {
/* 329 */     this.descriptor = paramStructDescriptor;
  }
  
  /* Error */
  public Datum[] getOracleAttributes()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 4	oracle/sql/STRUCT:descriptor	Loracle/sql/StructDescriptor;
    //   11: aload_0
    //   12: aload_0
    //   13: getfield 2	oracle/sql/STRUCT:enableLocalCache	Z
    //   16: invokevirtual 30	oracle/sql/StructDescriptor:toOracleArray	(Loracle/sql/STRUCT;Z)[Loracle/sql/Datum;
    //   19: aload_1
    //   20: monitorexit
    //   21: areturn
    //   22: astore_2
    //   23: aload_1
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Line number table:
    //   Java source line #344	-> byte code offset #0
    //   Java source line #346	-> byte code offset #7
    //   Java source line #348	-> byte code offset #22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	STRUCT
    //   5	19	1	Ljava/lang/Object;	Object
    //   22	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	21	22	finally
    //   22	25	22	finally
  }
  
  public Map getMap()
  {
/* 358 */     Map localMap = null;
    
    try
    {
/* 362 */       localMap = getInternalConnection().getTypeMap();
    }
    catch (SQLException localSQLException) {}
    
/* 370 */     return localMap;
  }
  
  /* Error */
  public byte[] toBytes()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 4	oracle/sql/STRUCT:descriptor	Loracle/sql/StructDescriptor;
    //   11: aload_0
    //   12: aload_0
    //   13: getfield 2	oracle/sql/STRUCT:enableLocalCache	Z
    //   16: invokevirtual 33	oracle/sql/StructDescriptor:toBytes	(Loracle/sql/STRUCT;Z)[B
    //   19: aload_1
    //   20: monitorexit
    //   21: areturn
    //   22: astore_2
    //   23: aload_1
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Line number table:
    //   Java source line #384	-> byte code offset #0
    //   Java source line #386	-> byte code offset #7
    //   Java source line #388	-> byte code offset #22
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	STRUCT
    //   5	19	1	Ljava/lang/Object;	Object
    //   22	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	21	22	finally
    //   22	25	22	finally
  }
  
  public void setDatumArray(Datum[] paramArrayOfDatum)
  {
    try
    {
/* 402 */       this.datumArray = (paramArrayOfDatum == null ? new Datum[this.descriptor.getLength()] : paramArrayOfDatum);
    }
    catch (SQLException localSQLException) {}
  }
  
  public void setObjArray(Object[] paramArrayOfObject)
    throws SQLException
  {
/* 418 */     synchronized (getInternalConnection())
    {
/* 420 */       this.objectArray = (paramArrayOfObject == null ? new Object[0] : paramArrayOfObject);
    }
  }
  
  public static STRUCT toSTRUCT(Object paramObject, oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 437 */     STRUCT localSTRUCT = null;
    
/* 439 */     if (paramObject != null) {
/* 440 */       if ((paramObject instanceof STRUCT))
      {
/* 442 */         localSTRUCT = (STRUCT)paramObject;
      }
/* 444 */       else if ((paramObject instanceof ORAData))
      {
/* 446 */         localSTRUCT = (STRUCT)((ORAData)paramObject).toDatum(paramOracleConnection); } else { Object localObject1;
        Object localObject2;
/* 448 */         if ((paramObject instanceof OracleData))
        {
/* 450 */           localObject1 = ((OracleData)paramObject).toJDBCObject(paramOracleConnection);
          
/* 458 */           if ((localObject1 instanceof OracleProxy))
          {
/* 460 */             localObject2 = (OracleProxy)localObject1;
/* 461 */             localObject1 = AccessController.doPrivileged(new PrivilegedAction()
            {
              public Object run()
              {
/* 466 */                 return ProxyFactory.extractDelegate(this.val$proxiedJDBCObject);
              }
            });
          }
          
/* 472 */           localSTRUCT = (STRUCT)localObject1;
        }
/* 475 */         else if ((paramObject instanceof CustomDatum))
        {
/* 477 */           localSTRUCT = (STRUCT)((oracle.jdbc.internal.OracleConnection)paramOracleConnection).toDatum((CustomDatum)paramObject);
        }
/* 481 */         else if ((paramObject instanceof SQLData))
        {
/* 483 */           localObject1 = (SQLData)paramObject;
          
/* 485 */           localObject2 = StructDescriptor.createDescriptor(((SQLData)localObject1).getSQLTypeName(), paramOracleConnection);
          
/* 488 */           SQLOutput localSQLOutput = ((StructDescriptor)localObject2).toJdbc2SQLOutput();
          
/* 490 */           ((SQLData)localObject1).writeSQL(localSQLOutput);
          
/* 492 */           localSTRUCT = ((OracleSQLOutput)localSQLOutput).getSTRUCT();
        }
        else
        {
/* 497 */           localObject1 = DatabaseError.createSqlException(null, 59, paramObject);
/* 498 */           ((SQLException)localObject1).fillInStackTrace();
/* 499 */           throw ((Throwable)localObject1);
        }
      }
    }
/* 503 */     return localSTRUCT;
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 519 */     Map localMap = getMap();
/* 520 */     return toJdbc(localMap);
  }
  
  public Object toJdbc(Map paramMap)
    throws SQLException
  {
/* 533 */     Object localObject = this;
    
/* 535 */     if (paramMap != null)
    {
/* 537 */       Class localClass = this.descriptor.getClass(paramMap);
      
/* 539 */       if (localClass != null) {
/* 540 */         localObject = toClass(localClass, paramMap);
      }
    }
/* 543 */     return localObject;
  }
  
  public Object toClass(Class paramClass)
    throws SQLException
  {
/* 555 */     return toClass(paramClass, getMap());
  }
  
  public Object toClass(Class paramClass, Map paramMap)
    throws SQLException
  {
/* 579 */     Object localObject1 = null;
    
    try
    {
/* 583 */       if ((paramClass == null) || (paramClass == STRUCT.class) || (paramClass == Struct.class))
      {
/* 585 */         localObject1 = this;
      }
      else
      {
/* 590 */         Object localObject2 = paramClass.newInstance();
        
/* 592 */         if ((localObject2 instanceof SQLData))
        {
/* 594 */           ((SQLData)localObject2).readSQL(this.descriptor.toJdbc2SQLInput(this, paramMap), this.descriptor.getName());
          
/* 597 */           localObject1 = localObject2;
        }
/* 599 */         else if ((localObject2 instanceof ORADataFactory))
        {
/* 601 */           localObject3 = (ORADataFactory)localObject2;
          
/* 603 */           localObject1 = ((ORADataFactory)localObject3).create(this, 2002);
        }
/* 605 */         else if ((localObject2 instanceof OracleDataFactory))
        {
/* 607 */           localObject3 = (OracleDataFactory)localObject2;
/* 608 */           localObject1 = ((OracleDataFactory)localObject3).create(this, 2002);
        }
/* 611 */         else if ((localObject2 instanceof CustomDatumFactory))
        {
/* 613 */           localObject3 = (CustomDatumFactory)localObject2;
          
/* 615 */           localObject1 = ((CustomDatumFactory)localObject3).create(this, 2002);
        }
        else
        {
/* 621 */           localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, this.descriptor.getName());
/* 622 */           ((SQLException)localObject3).fillInStackTrace();
/* 623 */           throw ((Throwable)localObject3);
        }
        
      }
      
    }
    catch (InstantiationException localInstantiationException)
    {
/* 632 */       localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, "InstantiationException: " + localInstantiationException.getMessage());
      
/* 634 */       ((SQLException)localObject3).fillInStackTrace();
/* 635 */       throw ((Throwable)localObject3);
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
/* 642 */       Object localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, "IllegalAccessException: " + localIllegalAccessException.getMessage());
      
/* 644 */       ((SQLException)localObject3).fillInStackTrace();
/* 645 */       throw ((Throwable)localObject3);
    }
    
/* 649 */     return localObject1;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 663 */     return false;
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 673 */     return new Object[paramInt];
  }
  
  public void setAutoBuffering(boolean paramBoolean)
    throws SQLException
  {
/* 692 */     synchronized (getInternalConnection())
    {
/* 694 */       this.enableLocalCache = paramBoolean;
    }
  }
  
  /* Error */
  public boolean getAutoBuffering()
    throws SQLException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 24	oracle/sql/STRUCT:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
    //   4: dup
    //   5: astore_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 2	oracle/sql/STRUCT:enableLocalCache	Z
    //   11: aload_1
    //   12: monitorexit
    //   13: ireturn
    //   14: astore_2
    //   15: aload_1
    //   16: monitorexit
    //   17: aload_2
    //   18: athrow
    // Line number table:
    //   Java source line #710	-> byte code offset #0
    //   Java source line #712	-> byte code offset #7
    //   Java source line #714	-> byte code offset #14
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	19	0	this	STRUCT
    //   5	11	1	Ljava/lang/Object;	Object
    //   14	4	2	localObject1	Object
    // Exception table:
    //   from	to	target	type
    //   7	13	14	finally
    //   14	17	14	finally
  }
  
  public void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
    throws SQLException
  {
/* 725 */     setShareBytes(paramArrayOfByte);
    
/* 727 */     this.imageOffset = paramLong1;
/* 728 */     this.imageLength = paramLong2;
  }
  
  public void setImageLength(long paramLong)
    throws SQLException
  {
/* 739 */     this.imageLength = paramLong;
  }
  
  public long getImageOffset()
  {
/* 750 */     return this.imageOffset;
  }
  
  public long getImageLength()
  {
/* 762 */     return this.imageLength;
  }
  
  public CustomDatumFactory getFactory(Hashtable paramHashtable, String paramString)
    throws SQLException
  {
/* 776 */     String str = getSQLTypeName();
/* 777 */     Object localObject = paramHashtable.get(str);
    
/* 779 */     if (localObject == null)
    {
/* 782 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to convert a \"" + str + "\" to a \"" + paramString + "\" or a subclass of \"" + paramString + "\"");
      
/* 785 */       localSQLException.fillInStackTrace();
/* 786 */       throw localSQLException;
    }
    
/* 790 */     return (CustomDatumFactory)localObject;
  }
  
  public ORADataFactory getORADataFactory(Hashtable paramHashtable, String paramString)
    throws SQLException
  {
/* 804 */     String str = getSQLTypeName();
/* 805 */     Object localObject = paramHashtable.get(str);
    
/* 807 */     if (localObject == null)
    {
/* 810 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to convert a \"" + str + "\" to a \"" + paramString + "\" or a subclass of \"" + paramString + "\"");
      
/* 813 */       localSQLException.fillInStackTrace();
/* 814 */       throw localSQLException;
    }
    
/* 818 */     return (ORADataFactory)localObject;
  }
  
  public OracleDataFactory getOracleDataFactory(Hashtable paramHashtable, String paramString)
    throws SQLException
  {
/* 826 */     String str = getSQLTypeName();
/* 827 */     Object localObject = paramHashtable.get(str);
    
/* 829 */     if (localObject == null)
    {
/* 832 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to convert a \"" + str + "\" to a \"" + paramString + "\" or a subclass of \"" + paramString + "\"");
      
/* 835 */       localSQLException.fillInStackTrace();
/* 836 */       throw localSQLException;
    }
    
/* 840 */     return (OracleDataFactory)localObject;
  }
  
  public String debugString()
  {
/* 853 */     StringWriter localStringWriter = new StringWriter();
/* 854 */     String str = null;
    
    try
    {
/* 858 */       StructDescriptor localStructDescriptor = getDescriptor();
      
/* 860 */       localStringWriter.write("name = " + localStructDescriptor.getName());
/* 861 */       int i; localStringWriter.write(" length = " + (i = localStructDescriptor.getLength()));
      
/* 863 */       Object[] arrayOfObject = getAttributes();
      
/* 865 */       for (int j = 0; j < i; j++)
      {
/* 867 */         localStringWriter.write(" attribute[" + j + "] = " + arrayOfObject[j]);
      }
/* 869 */       str = localStringWriter.toString();
    }
    catch (SQLException localSQLException)
    {
/* 875 */       str = "StructDescriptor missing or bad";
    }
/* 877 */     return str;
  }
  
  public boolean isInHierarchyOf(String paramString)
    throws SQLException
  {
/* 890 */     return getDescriptor().isInHierarchyOf(paramString);
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
/* 897 */     return super.getJavaSqlConnection();
  }
  
  public String dump()
    throws SQLException
  {
/* 908 */     return dump(this);
  }
  
  public static String dump(Object paramObject)
    throws SQLException
  {
/* 919 */     StringWriter localStringWriter = new StringWriter();
/* 920 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 921 */     dump(paramObject, localPrintWriter);
/* 922 */     return localStringWriter.getBuffer().substring(0);
  }
  
  public static void dump(Object paramObject, PrintStream paramPrintStream) throws SQLException
  {
/* 927 */     dump(paramObject, new PrintWriter(paramPrintStream, true));
  }
  
  public static void dump(Object paramObject, PrintWriter paramPrintWriter) throws SQLException
  {
/* 932 */     dump(paramObject, paramPrintWriter, 0);
  }
  
  static void dump(Object paramObject, PrintWriter paramPrintWriter, int paramInt) throws SQLException
  {
/* 937 */     if ((paramObject instanceof STRUCT)) { dump((STRUCT)paramObject, paramPrintWriter, paramInt);return; }
/* 938 */     if ((paramObject instanceof ARRAY)) { ARRAY.dump((ARRAY)paramObject, paramPrintWriter, paramInt);return; }
/* 939 */     if (paramObject == null) {
/* 940 */       paramPrintWriter.println("null");
    } else {
/* 942 */       paramPrintWriter.println(paramObject.toString());
    }
  }
  
  static void dump(STRUCT paramSTRUCT, PrintWriter paramPrintWriter, int paramInt)
    throws SQLException
  {
/* 949 */     StructDescriptor localStructDescriptor = paramSTRUCT.getDescriptor();
/* 950 */     ResultSetMetaData localResultSetMetaData = localStructDescriptor.getMetaData();
    
/* 952 */     for (int j = 0; j < paramInt; j++) paramPrintWriter.print(' ');
/* 953 */     paramPrintWriter.println("name = " + localStructDescriptor.getName());
    
/* 955 */     for (j = 0; j < paramInt; j++) paramPrintWriter.print(' ');
/* 956 */     int i; paramPrintWriter.println("length = " + (i = localStructDescriptor.getLength()));
/* 957 */     Object[] arrayOfObject = paramSTRUCT.getAttributes();
/* 958 */     for (j = 0; j < i; j++)
    {
/* 960 */       for (int k = 0; k < paramInt; k++) paramPrintWriter.print(' ');
/* 961 */       paramPrintWriter.print(localResultSetMetaData.getColumnName(j + 1) + " = ");
/* 962 */       dump(arrayOfObject[j], paramPrintWriter, paramInt + 1);
    }
  }
  
/* 966 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/STRUCT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */