/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.PrintWriter;
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleData;
/*      */ import oracle.jdbc.OracleTypeMetaData;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleArray;
/*      */ import oracle.jdbc.proxy.OracleProxy;
/*      */ import oracle.jdbc.proxy.ProxyFactory;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ARRAY
/*      */   extends DatumWithConnection
/*      */   implements OracleArray
/*      */ {
/*      */   static final byte KOPUP_INLINE_COLL = 1;
/*      */   ArrayDescriptor descriptor;
/*      */   Object objArray;
/*      */   Datum[] datumArray;
/*      */   byte[] locator;
/*      */   byte prefixFlag;
/*      */   byte[] prefixSegment;
/*   64 */   int numElems = -1;
/*      */   
/*   66 */   boolean enableBuffering = false;
/*   67 */   boolean enableIndexing = false;
/*      */   
/*      */   public static final int ACCESS_FORWARD = 1;
/*      */   public static final int ACCESS_REVERSE = 2;
/*      */   public static final int ACCESS_UNKNOWN = 3;
/*   72 */   int accessDirection = 3;
/*      */   
/*      */ 
/*      */   long lastIndex;
/*      */   
/*      */ 
/*      */   long lastOffset;
/*      */   
/*      */ 
/*      */   long[] indexArray;
/*      */   
/*      */   long imageOffset;
/*      */   
/*      */   long imageLength;
/*      */   
/*      */ 
/*      */   public ARRAY(ArrayDescriptor paramArrayDescriptor, Connection paramConnection, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*   91 */     assertNotNull(paramArrayDescriptor);
/*      */     
/*   93 */     this.descriptor = paramArrayDescriptor;
/*      */     
/*   95 */     assertNotNull(paramConnection);
/*      */     
/*   97 */     if (!paramArrayDescriptor.getInternalConnection().isDescriptorSharable(((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin()))
/*      */     {
/*      */ 
/*  100 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct ARRAY instance,invalid connection");
/*      */       
/*  102 */       localSQLException.fillInStackTrace();
/*  103 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  107 */     paramArrayDescriptor.setConnection(paramConnection);
/*  108 */     setPhysicalConnectionOf(paramConnection);
/*      */     
/*      */ 
/*  111 */     if (paramObject == null) {
/*  112 */       this.datumArray = new Datum[0];
/*      */     } else {
/*  114 */       this.datumArray = this.descriptor.toOracleArray(paramObject, 1L, -1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ARRAY(ArrayDescriptor paramArrayDescriptor, byte[] paramArrayOfByte, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  131 */     super(paramArrayOfByte);
/*      */     
/*      */ 
/*      */ 
/*  135 */     assertNotNull(paramArrayDescriptor);
/*      */     
/*  137 */     this.descriptor = paramArrayDescriptor;
/*      */     
/*  139 */     assertNotNull(paramConnection);
/*      */     
/*  141 */     if (!paramArrayDescriptor.getInternalConnection().isDescriptorSharable(((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin()))
/*      */     {
/*      */ 
/*  144 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Cannot construct ARRAY instance,invalid connection");
/*      */       
/*  146 */       localSQLException.fillInStackTrace();
/*  147 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  151 */     paramArrayDescriptor.setConnection(paramConnection);
/*  152 */     setPhysicalConnectionOf(paramConnection);
/*      */     
/*  154 */     this.datumArray = null;
/*  155 */     this.locator = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ARRAY toARRAY(Object paramObject, oracle.jdbc.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  174 */     ARRAY localARRAY = null;
/*      */     
/*  176 */     if (paramObject != null)
/*  177 */       if ((paramObject instanceof ARRAY))
/*      */       {
/*  179 */         localARRAY = (ARRAY)paramObject;
/*      */       }
/*  181 */       else if ((paramObject instanceof ORAData))
/*      */       {
/*  183 */         localARRAY = (ARRAY)((ORAData)paramObject).toDatum(paramOracleConnection);
/*      */       } else { Object localObject;
/*  185 */         if ((paramObject instanceof OracleData))
/*      */         {
/*      */ 
/*  188 */           localObject = ((OracleData)paramObject).toJDBCObject(paramOracleConnection);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  196 */           if ((localObject instanceof OracleProxy))
/*      */           {
/*  198 */             OracleProxy localOracleProxy = (OracleProxy)localObject;
/*  199 */             localObject = AccessController.doPrivileged(new PrivilegedAction()
/*      */             {
/*      */ 
/*      */               public Object run()
/*      */               {
/*  204 */                 return ProxyFactory.extractDelegate(this.val$proxiedJDBCObject);
/*      */               }
/*      */             });
/*      */           }
/*      */           
/*      */ 
/*  210 */           localARRAY = (ARRAY)localObject;
/*      */ 
/*      */         }
/*  213 */         else if ((paramObject instanceof CustomDatum))
/*      */         {
/*  215 */           localARRAY = (ARRAY)paramOracleConnection.physicalConnectionWithin().toDatum((CustomDatum)paramObject);
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  222 */           localObject = DatabaseError.createSqlException(null, 59, paramObject);
/*  223 */           ((SQLException)localObject).fillInStackTrace();
/*  224 */           throw ((Throwable)localObject);
/*      */         }
/*      */       }
/*  227 */     return localARRAY;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public String getBaseTypeName()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: invokevirtual 36	oracle/sql/ArrayDescriptor:getBaseName	()Ljava/lang/String;
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: areturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #256	-> byte code offset #0
/*      */     //   Java source line #258	-> byte code offset #7
/*      */     //   Java source line #260	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	ARRAY
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int getBaseType()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: invokevirtual 37	oracle/sql/ArrayDescriptor:getBaseType	()I
/*      */     //   14: aload_1
/*      */     //   15: monitorexit
/*      */     //   16: ireturn
/*      */     //   17: astore_2
/*      */     //   18: aload_1
/*      */     //   19: monitorexit
/*      */     //   20: aload_2
/*      */     //   21: athrow
/*      */     // Line number table:
/*      */     //   Java source line #277	-> byte code offset #0
/*      */     //   Java source line #279	-> byte code offset #7
/*      */     //   Java source line #281	-> byte code offset #17
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	22	0	this	ARRAY
/*      */     //   5	14	1	Ljava/lang/Object;	Object
/*      */     //   17	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	16	17	finally
/*      */     //   17	20	17	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object getArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: aload_0
/*      */     //   15: invokevirtual 38	oracle/sql/ARRAY:getMap	()Ljava/util/Map;
/*      */     //   18: aload_0
/*      */     //   19: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   22: invokevirtual 39	oracle/sql/ArrayDescriptor:toJavaArray	(Loracle/sql/ARRAY;JILjava/util/Map;Z)[Ljava/lang/Object;
/*      */     //   25: aload_1
/*      */     //   26: monitorexit
/*      */     //   27: areturn
/*      */     //   28: astore_2
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: aload_2
/*      */     //   32: athrow
/*      */     // Line number table:
/*      */     //   Java source line #297	-> byte code offset #0
/*      */     //   Java source line #299	-> byte code offset #7
/*      */     //   Java source line #301	-> byte code offset #28
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	33	0	this	ARRAY
/*      */     //   5	25	1	Ljava/lang/Object;	Object
/*      */     //   28	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	27	28	finally
/*      */     //   28	31	28	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object getArray(Map paramMap)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: aload_1
/*      */     //   15: aload_0
/*      */     //   16: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   19: invokevirtual 39	oracle/sql/ArrayDescriptor:toJavaArray	(Loracle/sql/ARRAY;JILjava/util/Map;Z)[Ljava/lang/Object;
/*      */     //   22: aload_2
/*      */     //   23: monitorexit
/*      */     //   24: areturn
/*      */     //   25: astore_3
/*      */     //   26: aload_2
/*      */     //   27: monitorexit
/*      */     //   28: aload_3
/*      */     //   29: athrow
/*      */     // Line number table:
/*      */     //   Java source line #317	-> byte code offset #0
/*      */     //   Java source line #319	-> byte code offset #7
/*      */     //   Java source line #321	-> byte code offset #25
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	30	0	this	ARRAY
/*      */     //   0	30	1	paramMap	Map
/*      */     //   5	22	2	Ljava/lang/Object;	Object
/*      */     //   25	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	24	25	finally
/*      */     //   25	28	25	finally
/*      */   }
/*      */   
/*      */   public Object getArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  341 */     synchronized (getInternalConnection())
/*      */     {
/*      */ 
/*  344 */       if ((paramLong < 1L) || (paramInt < 0))
/*      */       {
/*      */ 
/*      */ 
/*  348 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "Invalid arguments,'index' should be >= 1 and 'count' >= 0. An exception is thrown.");
/*      */         
/*  350 */         localSQLException.fillInStackTrace();
/*  351 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  355 */       return this.descriptor.toJavaArray(this, paramLong, paramInt, getMap(), false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getArray(long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  378 */     synchronized (getInternalConnection())
/*      */     {
/*      */ 
/*  381 */       if ((paramLong < 1L) || (paramInt < 0))
/*      */       {
/*      */ 
/*      */ 
/*  385 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "Invalid arguments,'index' should be >= 1 and 'count' >= 0. An exception is thrown.");
/*      */         
/*  387 */         localSQLException.fillInStackTrace();
/*  388 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  392 */       return this.descriptor.toJavaArray(this, paramLong, paramInt, paramMap, false);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ResultSet getResultSet()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: aload_0
/*      */     //   9: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   12: invokeinterface 41 1 0
/*      */     //   17: invokevirtual 42	oracle/sql/ARRAY:getResultSet	(Ljava/util/Map;)Ljava/sql/ResultSet;
/*      */     //   20: aload_1
/*      */     //   21: monitorexit
/*      */     //   22: areturn
/*      */     //   23: astore_2
/*      */     //   24: aload_1
/*      */     //   25: monitorexit
/*      */     //   26: aload_2
/*      */     //   27: athrow
/*      */     // Line number table:
/*      */     //   Java source line #420	-> byte code offset #0
/*      */     //   Java source line #424	-> byte code offset #7
/*      */     //   Java source line #426	-> byte code offset #23
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	28	0	this	ARRAY
/*      */     //   5	20	1	Ljava/lang/Object;	Object
/*      */     //   23	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	22	23	finally
/*      */     //   23	26	23	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ResultSet getResultSet(Map paramMap)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: aload_1
/*      */     //   15: aload_0
/*      */     //   16: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   19: invokevirtual 43	oracle/sql/ArrayDescriptor:toResultSet	(Loracle/sql/ARRAY;JILjava/util/Map;Z)Ljava/sql/ResultSet;
/*      */     //   22: aload_2
/*      */     //   23: monitorexit
/*      */     //   24: areturn
/*      */     //   25: astore_3
/*      */     //   26: aload_2
/*      */     //   27: monitorexit
/*      */     //   28: aload_3
/*      */     //   29: athrow
/*      */     // Line number table:
/*      */     //   Java source line #456	-> byte code offset #0
/*      */     //   Java source line #458	-> byte code offset #7
/*      */     //   Java source line #460	-> byte code offset #25
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	30	0	this	ARRAY
/*      */     //   0	30	1	paramMap	Map
/*      */     //   5	22	2	Ljava/lang/Object;	Object
/*      */     //   25	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	24	25	finally
/*      */     //   25	28	25	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public ResultSet getResultSet(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: lload_1
/*      */     //   10: iload_3
/*      */     //   11: aload_0
/*      */     //   12: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   15: invokeinterface 41 1 0
/*      */     //   20: invokevirtual 44	oracle/sql/ARRAY:getResultSet	(JILjava/util/Map;)Ljava/sql/ResultSet;
/*      */     //   23: aload 4
/*      */     //   25: monitorexit
/*      */     //   26: areturn
/*      */     //   27: astore 5
/*      */     //   29: aload 4
/*      */     //   31: monitorexit
/*      */     //   32: aload 5
/*      */     //   34: athrow
/*      */     // Line number table:
/*      */     //   Java source line #494	-> byte code offset #0
/*      */     //   Java source line #498	-> byte code offset #8
/*      */     //   Java source line #500	-> byte code offset #27
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	35	0	this	ARRAY
/*      */     //   0	35	1	paramLong	long
/*      */     //   0	35	3	paramInt	int
/*      */     //   5	25	4	Ljava/lang/Object;	Object
/*      */     //   27	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	26	27	finally
/*      */     //   27	32	27	finally
/*      */   }
/*      */   
/*      */   public ResultSet getResultSet(long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  538 */     synchronized (getInternalConnection())
/*      */     {
/*      */ 
/*  541 */       if ((paramLong < 1L) || (paramInt < -1))
/*      */       {
/*      */ 
/*  544 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getResultSet()");
/*  545 */         localSQLException.fillInStackTrace();
/*  546 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  550 */       return this.descriptor.toResultSet(this, paramLong, paramInt, paramMap, false);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Datum[] getOracleArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: aload_0
/*      */     //   15: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   18: invokevirtual 46	oracle/sql/ArrayDescriptor:toOracleArray	(Loracle/sql/ARRAY;JIZ)[Loracle/sql/Datum;
/*      */     //   21: aload_1
/*      */     //   22: monitorexit
/*      */     //   23: areturn
/*      */     //   24: astore_2
/*      */     //   25: aload_1
/*      */     //   26: monitorexit
/*      */     //   27: aload_2
/*      */     //   28: athrow
/*      */     // Line number table:
/*      */     //   Java source line #570	-> byte code offset #0
/*      */     //   Java source line #572	-> byte code offset #7
/*      */     //   Java source line #574	-> byte code offset #24
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	29	0	this	ARRAY
/*      */     //   5	21	1	Ljava/lang/Object;	Object
/*      */     //   24	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	23	24	finally
/*      */     //   24	27	24	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int length()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: invokevirtual 47	oracle/sql/ArrayDescriptor:toLength	(Loracle/sql/ARRAY;)I
/*      */     //   15: aload_1
/*      */     //   16: monitorexit
/*      */     //   17: ireturn
/*      */     //   18: astore_2
/*      */     //   19: aload_1
/*      */     //   20: monitorexit
/*      */     //   21: aload_2
/*      */     //   22: athrow
/*      */     // Line number table:
/*      */     //   Java source line #587	-> byte code offset #0
/*      */     //   Java source line #589	-> byte code offset #7
/*      */     //   Java source line #591	-> byte code offset #18
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	23	0	this	ARRAY
/*      */     //   5	15	1	Ljava/lang/Object;	Object
/*      */     //   18	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	17	18	finally
/*      */     //   18	21	18	finally
/*      */   }
/*      */   
/*      */   public Datum[] getOracleArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  607 */     synchronized (getInternalConnection())
/*      */     {
/*      */ 
/*  610 */       if ((paramLong < 1L) || (paramInt < 0))
/*      */       {
/*      */ 
/*  613 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "getOracleArray()");
/*  614 */         localSQLException.fillInStackTrace();
/*  615 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  619 */       return this.descriptor.toOracleArray(this, paramLong, paramInt, false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getSQLTypeName()
/*      */     throws SQLException
/*      */   {
/*  639 */     synchronized (getInternalConnection())
/*      */     {
/*  641 */       String str = null;
/*      */       
/*  643 */       if (this.descriptor != null)
/*      */       {
/*  645 */         str = this.descriptor.getName();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  650 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 61, "ARRAY");
/*  651 */         localSQLException.fillInStackTrace();
/*  652 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  656 */       return str;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map getMap()
/*      */     throws SQLException
/*      */   {
/*  669 */     return getInternalConnection().getTypeMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeMetaData getOracleMetaData()
/*      */     throws SQLException
/*      */   {
/*  681 */     return getDescriptor();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayDescriptor getDescriptor()
/*      */     throws SQLException
/*      */   {
/*  697 */     return this.descriptor;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public byte[] toBytes()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: aload_0
/*      */     //   13: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   16: invokevirtual 52	oracle/sql/ArrayDescriptor:toBytes	(Loracle/sql/ARRAY;Z)[B
/*      */     //   19: aload_1
/*      */     //   20: monitorexit
/*      */     //   21: areturn
/*      */     //   22: astore_2
/*      */     //   23: aload_1
/*      */     //   24: monitorexit
/*      */     //   25: aload_2
/*      */     //   26: athrow
/*      */     // Line number table:
/*      */     //   Java source line #709	-> byte code offset #0
/*      */     //   Java source line #711	-> byte code offset #7
/*      */     //   Java source line #713	-> byte code offset #22
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	27	0	this	ARRAY
/*      */     //   5	19	1	Ljava/lang/Object;	Object
/*      */     //   22	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	21	22	finally
/*      */     //   22	25	22	finally
/*      */   }
/*      */   
/*      */   public void setDatumArray(Datum[] paramArrayOfDatum)
/*      */   {
/*  727 */     this.datumArray = paramArrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObjArray(Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  746 */     synchronized (getInternalConnection())
/*      */     {
/*      */ 
/*  749 */       if (paramObject == null)
/*      */       {
/*      */ 
/*      */ 
/*  753 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid argument,'oarray' should not be null. An exception is thrown.");
/*      */         
/*  755 */         localSQLException.fillInStackTrace();
/*  756 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  760 */       this.objArray = paramObject;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLocator(byte[] paramArrayOfByte)
/*      */   {
/*  778 */     if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0)) {
/*  779 */       this.locator = paramArrayOfByte;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPrefixSegment(byte[] paramArrayOfByte)
/*      */   {
/*  793 */     if ((paramArrayOfByte != null) && (paramArrayOfByte.length != 0)) {
/*  794 */       this.prefixSegment = paramArrayOfByte;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPrefixFlag(byte paramByte)
/*      */   {
/*  808 */     this.prefixFlag = paramByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getLocator()
/*      */   {
/*  821 */     return this.locator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLength(int paramInt)
/*      */   {
/*  832 */     this.numElems = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean hasDataSeg()
/*      */   {
/*  844 */     return this.locator == null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInline()
/*      */   {
/*  856 */     return (this.prefixFlag & 0x1) == 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object toJdbc()
/*      */     throws SQLException
/*      */   {
/*  873 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isConvertibleTo(Class paramClass)
/*      */   {
/*  890 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object makeJdbcArray(int paramInt)
/*      */   {
/*  901 */     return new Object[paramInt][];
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int[] getIntArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: iconst_4
/*      */     //   15: aload_0
/*      */     //   16: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   19: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   22: checkcast 59	[I
/*      */     //   25: checkcast 59	[I
/*      */     //   28: aload_1
/*      */     //   29: monitorexit
/*      */     //   30: areturn
/*      */     //   31: astore_2
/*      */     //   32: aload_1
/*      */     //   33: monitorexit
/*      */     //   34: aload_2
/*      */     //   35: athrow
/*      */     // Line number table:
/*      */     //   Java source line #915	-> byte code offset #0
/*      */     //   Java source line #917	-> byte code offset #7
/*      */     //   Java source line #920	-> byte code offset #31
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	36	0	this	ARRAY
/*      */     //   5	28	1	Ljava/lang/Object;	Object
/*      */     //   31	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	30	31	finally
/*      */     //   31	34	31	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public int[] getIntArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   12: aload_0
/*      */     //   13: lload_1
/*      */     //   14: iload_3
/*      */     //   15: iconst_4
/*      */     //   16: iconst_0
/*      */     //   17: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   20: checkcast 59	[I
/*      */     //   23: checkcast 59	[I
/*      */     //   26: aload 4
/*      */     //   28: monitorexit
/*      */     //   29: areturn
/*      */     //   30: astore 5
/*      */     //   32: aload 4
/*      */     //   34: monitorexit
/*      */     //   35: aload 5
/*      */     //   37: athrow
/*      */     // Line number table:
/*      */     //   Java source line #936	-> byte code offset #0
/*      */     //   Java source line #938	-> byte code offset #8
/*      */     //   Java source line #940	-> byte code offset #30
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	38	0	this	ARRAY
/*      */     //   0	38	1	paramLong	long
/*      */     //   0	38	3	paramInt	int
/*      */     //   5	28	4	Ljava/lang/Object;	Object
/*      */     //   30	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	29	30	finally
/*      */     //   30	35	30	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public double[] getDoubleArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: iconst_5
/*      */     //   15: aload_0
/*      */     //   16: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   19: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   22: checkcast 60	[D
/*      */     //   25: checkcast 60	[D
/*      */     //   28: aload_1
/*      */     //   29: monitorexit
/*      */     //   30: areturn
/*      */     //   31: astore_2
/*      */     //   32: aload_1
/*      */     //   33: monitorexit
/*      */     //   34: aload_2
/*      */     //   35: athrow
/*      */     // Line number table:
/*      */     //   Java source line #953	-> byte code offset #0
/*      */     //   Java source line #955	-> byte code offset #7
/*      */     //   Java source line #958	-> byte code offset #31
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	36	0	this	ARRAY
/*      */     //   5	28	1	Ljava/lang/Object;	Object
/*      */     //   31	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	30	31	finally
/*      */     //   31	34	31	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public double[] getDoubleArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   12: aload_0
/*      */     //   13: lload_1
/*      */     //   14: iload_3
/*      */     //   15: iconst_5
/*      */     //   16: iconst_0
/*      */     //   17: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   20: checkcast 60	[D
/*      */     //   23: checkcast 60	[D
/*      */     //   26: aload 4
/*      */     //   28: monitorexit
/*      */     //   29: areturn
/*      */     //   30: astore 5
/*      */     //   32: aload 4
/*      */     //   34: monitorexit
/*      */     //   35: aload 5
/*      */     //   37: athrow
/*      */     // Line number table:
/*      */     //   Java source line #974	-> byte code offset #0
/*      */     //   Java source line #976	-> byte code offset #8
/*      */     //   Java source line #978	-> byte code offset #30
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	38	0	this	ARRAY
/*      */     //   0	38	1	paramLong	long
/*      */     //   0	38	3	paramInt	int
/*      */     //   5	28	4	Ljava/lang/Object;	Object
/*      */     //   30	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	29	30	finally
/*      */     //   30	35	30	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public short[] getShortArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: bipush 8
/*      */     //   16: aload_0
/*      */     //   17: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   20: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   23: checkcast 61	[S
/*      */     //   26: checkcast 61	[S
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: areturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #991	-> byte code offset #0
/*      */     //   Java source line #993	-> byte code offset #7
/*      */     //   Java source line #995	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	ARRAY
/*      */     //   5	29	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public short[] getShortArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   12: aload_0
/*      */     //   13: lload_1
/*      */     //   14: iload_3
/*      */     //   15: bipush 8
/*      */     //   17: iconst_0
/*      */     //   18: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   21: checkcast 61	[S
/*      */     //   24: checkcast 61	[S
/*      */     //   27: aload 4
/*      */     //   29: monitorexit
/*      */     //   30: areturn
/*      */     //   31: astore 5
/*      */     //   33: aload 4
/*      */     //   35: monitorexit
/*      */     //   36: aload 5
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1002	-> byte code offset #0
/*      */     //   Java source line #1004	-> byte code offset #8
/*      */     //   Java source line #1006	-> byte code offset #31
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	ARRAY
/*      */     //   0	39	1	paramLong	long
/*      */     //   0	39	3	paramInt	int
/*      */     //   5	29	4	Ljava/lang/Object;	Object
/*      */     //   31	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	30	31	finally
/*      */     //   31	36	31	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public long[] getLongArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: bipush 7
/*      */     //   16: aload_0
/*      */     //   17: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   20: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   23: checkcast 62	[J
/*      */     //   26: checkcast 62	[J
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: areturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1018	-> byte code offset #0
/*      */     //   Java source line #1020	-> byte code offset #7
/*      */     //   Java source line #1022	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	ARRAY
/*      */     //   5	29	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public long[] getLongArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   12: aload_0
/*      */     //   13: lload_1
/*      */     //   14: iload_3
/*      */     //   15: bipush 7
/*      */     //   17: iconst_0
/*      */     //   18: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   21: checkcast 62	[J
/*      */     //   24: checkcast 62	[J
/*      */     //   27: aload 4
/*      */     //   29: monitorexit
/*      */     //   30: areturn
/*      */     //   31: astore 5
/*      */     //   33: aload 4
/*      */     //   35: monitorexit
/*      */     //   36: aload 5
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1037	-> byte code offset #0
/*      */     //   Java source line #1039	-> byte code offset #8
/*      */     //   Java source line #1041	-> byte code offset #31
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	ARRAY
/*      */     //   0	39	1	paramLong	long
/*      */     //   0	39	3	paramInt	int
/*      */     //   5	29	4	Ljava/lang/Object;	Object
/*      */     //   31	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	30	31	finally
/*      */     //   31	36	31	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public float[] getFloatArray()
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_1
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   11: aload_0
/*      */     //   12: lconst_1
/*      */     //   13: iconst_m1
/*      */     //   14: bipush 6
/*      */     //   16: aload_0
/*      */     //   17: getfield 3	oracle/sql/ARRAY:enableBuffering	Z
/*      */     //   20: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   23: checkcast 63	[F
/*      */     //   26: checkcast 63	[F
/*      */     //   29: aload_1
/*      */     //   30: monitorexit
/*      */     //   31: areturn
/*      */     //   32: astore_2
/*      */     //   33: aload_1
/*      */     //   34: monitorexit
/*      */     //   35: aload_2
/*      */     //   36: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1053	-> byte code offset #0
/*      */     //   Java source line #1055	-> byte code offset #7
/*      */     //   Java source line #1057	-> byte code offset #32
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	37	0	this	ARRAY
/*      */     //   5	29	1	Ljava/lang/Object;	Object
/*      */     //   32	4	2	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	31	32	finally
/*      */     //   32	35	32	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public float[] getFloatArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual 35	oracle/sql/ARRAY:getInternalConnection	()Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore 4
/*      */     //   7: monitorenter
/*      */     //   8: aload_0
/*      */     //   9: getfield 7	oracle/sql/ARRAY:descriptor	Loracle/sql/ArrayDescriptor;
/*      */     //   12: aload_0
/*      */     //   13: lload_1
/*      */     //   14: iload_3
/*      */     //   15: bipush 6
/*      */     //   17: iconst_0
/*      */     //   18: invokevirtual 58	oracle/sql/ArrayDescriptor:toNumericArray	(Loracle/sql/ARRAY;JIIZ)Ljava/lang/Object;
/*      */     //   21: checkcast 63	[F
/*      */     //   24: checkcast 63	[F
/*      */     //   27: aload 4
/*      */     //   29: monitorexit
/*      */     //   30: areturn
/*      */     //   31: astore 5
/*      */     //   33: aload 4
/*      */     //   35: monitorexit
/*      */     //   36: aload 5
/*      */     //   38: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1072	-> byte code offset #0
/*      */     //   Java source line #1074	-> byte code offset #8
/*      */     //   Java source line #1076	-> byte code offset #31
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	39	0	this	ARRAY
/*      */     //   0	39	1	paramLong	long
/*      */     //   0	39	3	paramInt	int
/*      */     //   5	29	4	Ljava/lang/Object;	Object
/*      */     //   31	6	5	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   8	30	31	finally
/*      */     //   31	36	31	finally
/*      */   }
/*      */   
/*      */   public void setAutoBuffering(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1095 */     synchronized (getInternalConnection())
/*      */     {
/* 1097 */       this.enableBuffering = paramBoolean;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoBuffering()
/*      */     throws SQLException
/*      */   {
/* 1115 */     return this.enableBuffering;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoIndexing(boolean paramBoolean, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1139 */     synchronized (getInternalConnection())
/*      */     {
/* 1141 */       this.enableIndexing = paramBoolean;
/* 1142 */       this.accessDirection = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoIndexing(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1162 */     synchronized (getInternalConnection())
/*      */     {
/* 1164 */       this.enableIndexing = paramBoolean;
/* 1165 */       this.accessDirection = 3;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoIndexing()
/*      */     throws SQLException
/*      */   {
/* 1184 */     return this.enableIndexing;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getAccessDirection()
/*      */     throws SQLException
/*      */   {
/* 1202 */     return this.accessDirection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLastIndexOffset(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1219 */     this.lastIndex = paramLong1;
/* 1220 */     this.lastOffset = paramLong2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIndexOffset(long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1233 */     if (this.indexArray == null) {
/* 1234 */       this.indexArray = new long[this.numElems];
/*      */     }
/* 1236 */     this.indexArray[((int)paramLong1 - 1)] = paramLong2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLastIndex()
/*      */     throws SQLException
/*      */   {
/* 1248 */     return this.lastIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLastOffset()
/*      */     throws SQLException
/*      */   {
/* 1259 */     return this.lastOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getOffset(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1270 */     long l = -1L;
/*      */     
/* 1272 */     if (this.indexArray != null) {
/* 1273 */       l = this.indexArray[((int)paramLong - 1)];
/*      */     }
/* 1275 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setImage(byte[] paramArrayOfByte, long paramLong1, long paramLong2)
/*      */     throws SQLException
/*      */   {
/* 1292 */     setShareBytes(paramArrayOfByte);
/*      */     
/* 1294 */     this.imageOffset = paramLong1;
/* 1295 */     this.imageLength = paramLong2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setImageLength(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1308 */     this.imageLength = paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getImageOffset()
/*      */   {
/* 1321 */     return this.imageOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getImageLength()
/*      */   {
/* 1333 */     return this.imageLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1338 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */ 
/*      */ 
/*      */   public String dump()
/*      */     throws SQLException
/*      */   {
/* 1349 */     return STRUCT.dump(this);
/*      */   }
/*      */   
/*      */ 
/*      */   public String stringValue()
/*      */     throws SQLException
/*      */   {
/* 1356 */     Datum[] arrayOfDatum = getOracleArray();
/* 1357 */     String str = "[";
/* 1358 */     for (int i = 0; i < arrayOfDatum.length; i++)
/*      */     {
/* 1360 */       if (i != 0)
/*      */       {
/* 1362 */         str = str + ", ";
/*      */       }
/* 1364 */       if (arrayOfDatum[i] == null) {
/* 1365 */         str = str + "null";
/*      */       } else
/* 1367 */         str = str + arrayOfDatum[i].stringValue();
/*      */     }
/* 1369 */     str = str + "]";
/* 1370 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */   static void dump(ARRAY paramARRAY, PrintWriter paramPrintWriter, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1377 */     if (paramInt > 0) { paramPrintWriter.println();
/*      */     }
/*      */     
/* 1380 */     ArrayDescriptor localArrayDescriptor = paramARRAY.getDescriptor();
/* 1381 */     for (int j = 0; j < paramInt; j++) paramPrintWriter.print(' ');
/* 1382 */     paramPrintWriter.println("name = " + localArrayDescriptor.getName());
/*      */     
/* 1384 */     for (j = 0; j < paramInt; j++) paramPrintWriter.print(' ');
/* 1385 */     paramPrintWriter.println("max length = " + localArrayDescriptor.getMaxLength());
/* 1386 */     Object[] arrayOfObject = (Object[])paramARRAY.getArray();
/* 1387 */     for (j = 0; j < paramInt; j++) paramPrintWriter.print(' ');
/* 1388 */     int i; paramPrintWriter.println("length = " + (i = arrayOfObject.length));
/* 1389 */     for (j = 0; j < i; j++)
/*      */     {
/* 1391 */       for (int k = 0; k < paramInt; k++) paramPrintWriter.print(' ');
/* 1392 */       paramPrintWriter.print("element[" + j + "] = ");
/* 1393 */       STRUCT.dump(arrayOfObject[j], paramPrintWriter, paramInt + 4);
/*      */     }
/*      */   }
/*      */   
/*      */   public void free()
/*      */     throws SQLException
/*      */   {}
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ARRAY.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */