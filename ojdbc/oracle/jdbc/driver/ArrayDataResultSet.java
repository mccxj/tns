/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CHAR;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.CustomDatumFactory;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.NCLOB;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.OPAQUE;
/*      */ import oracle.sql.ORAData;
/*      */ import oracle.sql.ORADataFactory;
/*      */ import oracle.sql.RAW;
/*      */ import oracle.sql.REF;
/*      */ import oracle.sql.ROWID;
/*      */ import oracle.sql.STRUCT;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ class ArrayDataResultSet extends BaseResultSet
/*      */ {
/*      */   Datum[] data;
/*      */   Map map;
/*      */   private int currentIndex;
/*      */   private int lastIndex;
/*      */   PhysicalConnection connection;
/*      */   private Boolean wasNull;
/*      */   private int fetchSize;
/*      */   ARRAY array;
/*      */   
/*      */   public ArrayDataResultSet(OracleConnection paramOracleConnection, Datum[] paramArrayOfDatum, Map paramMap) throws SQLException
/*      */   {
/*   54 */     this.connection = ((PhysicalConnection)paramOracleConnection);
/*   55 */     this.data = paramArrayOfDatum;
/*   56 */     this.map = paramMap;
/*   57 */     this.currentIndex = 0;
/*   58 */     this.lastIndex = (this.data == null ? 0 : this.data.length);
/*   59 */     this.fetchSize = OracleConnection.DEFAULT_ROW_PREFETCH;
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
/*      */   public ArrayDataResultSet(OracleConnection paramOracleConnection, Datum[] paramArrayOfDatum, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*   77 */     this.connection = ((PhysicalConnection)paramOracleConnection);
/*   78 */     this.data = paramArrayOfDatum;
/*   79 */     this.map = paramMap;
/*   80 */     this.currentIndex = ((int)paramLong - 1);
/*      */     
/*   82 */     int i = this.data == null ? 0 : this.data.length;
/*      */     
/*   84 */     this.lastIndex = (this.currentIndex + Math.min(i - this.currentIndex, paramInt));
/*      */     
/*   86 */     this.fetchSize = OracleConnection.DEFAULT_ROW_PREFETCH;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ArrayDataResultSet(OracleConnection paramOracleConnection, ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*   95 */     this.connection = ((PhysicalConnection)paramOracleConnection);
/*   96 */     this.array = paramARRAY;
/*   97 */     this.map = paramMap;
/*   98 */     this.currentIndex = ((int)paramLong - 1);
/*      */     
/*  100 */     int i = this.array == null ? 0 : paramARRAY.length();
/*      */     
/*  102 */     this.lastIndex = (this.currentIndex + (paramInt == -1 ? i - this.currentIndex : Math.min(i - this.currentIndex, paramInt)));
/*      */     
/*      */ 
/*  105 */     this.fetchSize = OracleConnection.DEFAULT_ROW_PREFETCH;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean next()
/*      */     throws SQLException
/*      */   {
/*  113 */     if (this.closed)
/*      */     {
/*  115 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10, "next");
/*  116 */       localSQLException.fillInStackTrace();
/*  117 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  122 */     this.currentIndex += 1;
/*      */     
/*  124 */     return this.currentIndex <= this.lastIndex;
/*      */   }
/*      */   
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/*  130 */     synchronized (this.connection)
/*      */     {
/*  132 */       super.close();
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  139 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  142 */       if (this.wasNull == null)
/*      */       {
/*  144 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 24, null);
/*  145 */         localSQLException.fillInStackTrace();
/*  146 */         throw localSQLException;
/*      */       }
/*      */       
/*  149 */       return this.wasNull.booleanValue();
/*      */     }
/*      */   }
/*      */   
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  156 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  159 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  161 */       if (localDatum != null) {
/*  162 */         return localDatum.stringValue();
/*      */       }
/*  164 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public ResultSet getCursor(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  171 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*  175 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCursor");
/*  176 */       localSQLException.fillInStackTrace();
/*  177 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum getOracleObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  188 */     if (this.currentIndex <= 0)
/*      */     {
/*  190 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 14, null);
/*  191 */       ((SQLException)localObject).fillInStackTrace();
/*  192 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*  195 */     if (paramInt == 1)
/*      */     {
/*  197 */       this.wasNull = Boolean.FALSE;
/*      */       
/*  199 */       return new NUMBER(this.currentIndex);
/*      */     }
/*  201 */     if (paramInt == 2)
/*      */     {
/*  203 */       if (this.data != null)
/*      */       {
/*  205 */         this.wasNull = (this.data[(this.currentIndex - 1)] == null ? Boolean.TRUE : Boolean.FALSE);
/*      */         
/*      */ 
/*  208 */         return this.data[(this.currentIndex - 1)];
/*      */       }
/*  210 */       if (this.array != null)
/*      */       {
/*      */ 
/*      */ 
/*  214 */         localObject = this.array.getOracleArray(this.currentIndex, 1);
/*      */         
/*  216 */         if ((localObject != null) && (localObject.length >= 1))
/*      */         {
/*  218 */           this.wasNull = (localObject[0] == null ? Boolean.TRUE : Boolean.FALSE);
/*      */           
/*  220 */           return localObject[0];
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  225 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Out of sync");
/*  226 */       ((SQLException)localObject).fillInStackTrace();
/*  227 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  232 */     Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, null);
/*  233 */     ((SQLException)localObject).fillInStackTrace();
/*  234 */     throw ((Throwable)localObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ROWID getROWID(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  242 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  245 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  247 */       if (localDatum != null)
/*      */       {
/*  249 */         if ((localDatum instanceof ROWID)) {
/*  250 */           return (ROWID)localDatum;
/*      */         }
/*      */         
/*  253 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getROWID");
/*  254 */         localSQLException.fillInStackTrace();
/*  255 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  259 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public NUMBER getNUMBER(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  266 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  269 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  271 */       if (localDatum != null)
/*      */       {
/*  273 */         if ((localDatum instanceof NUMBER)) {
/*  274 */           return (NUMBER)localDatum;
/*      */         }
/*      */         
/*  277 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getNUMBER");
/*  278 */         localSQLException.fillInStackTrace();
/*  279 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  283 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public DATE getDATE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  290 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  293 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  295 */       if (localDatum != null)
/*      */       {
/*  297 */         if ((localDatum instanceof DATE)) {
/*  298 */           return (DATE)localDatum;
/*      */         }
/*      */         
/*  301 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getDATE");
/*  302 */         localSQLException.fillInStackTrace();
/*  303 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  307 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public ARRAY getARRAY(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  314 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  317 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  319 */       if (localDatum != null)
/*      */       {
/*  321 */         if ((localDatum instanceof ARRAY)) {
/*  322 */           return (ARRAY)localDatum;
/*      */         }
/*      */         
/*  325 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getARRAY");
/*  326 */         localSQLException.fillInStackTrace();
/*  327 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  331 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public STRUCT getSTRUCT(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  338 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  341 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  343 */       if (localDatum != null)
/*      */       {
/*  345 */         if ((localDatum instanceof STRUCT)) {
/*  346 */           return (STRUCT)localDatum;
/*      */         }
/*      */         
/*  349 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getSTRUCT");
/*  350 */         localSQLException.fillInStackTrace();
/*  351 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  355 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public OPAQUE getOPAQUE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  362 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  365 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  367 */       if (localDatum != null)
/*      */       {
/*  369 */         if ((localDatum instanceof OPAQUE)) {
/*  370 */           return (OPAQUE)localDatum;
/*      */         }
/*      */         
/*  373 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getOPAQUE");
/*  374 */         localSQLException.fillInStackTrace();
/*  375 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  379 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public REF getREF(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  386 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  389 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  391 */       if (localDatum != null)
/*      */       {
/*  393 */         if ((localDatum instanceof REF)) {
/*  394 */           return (REF)localDatum;
/*      */         }
/*      */         
/*  397 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getREF");
/*  398 */         localSQLException.fillInStackTrace();
/*  399 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  403 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public CHAR getCHAR(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  410 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  413 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  415 */       if (localDatum != null)
/*      */       {
/*  417 */         if ((localDatum instanceof CHAR)) {
/*  418 */           return (CHAR)localDatum;
/*      */         }
/*      */         
/*  421 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCHAR");
/*  422 */         localSQLException.fillInStackTrace();
/*  423 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  427 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public RAW getRAW(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  434 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  437 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  439 */       if (localDatum != null)
/*      */       {
/*  441 */         if ((localDatum instanceof RAW)) {
/*  442 */           return (RAW)localDatum;
/*      */         }
/*      */         
/*  445 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getRAW");
/*  446 */         localSQLException.fillInStackTrace();
/*  447 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  451 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public BLOB getBLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  458 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  461 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  463 */       if (localDatum != null)
/*      */       {
/*  465 */         if ((localDatum instanceof BLOB)) {
/*  466 */           return (BLOB)localDatum;
/*      */         }
/*      */         
/*  469 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBLOB");
/*  470 */         localSQLException.fillInStackTrace();
/*  471 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  475 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public CLOB getCLOB(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  482 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  485 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  487 */       if (localDatum != null)
/*      */       {
/*  489 */         if ((localDatum instanceof CLOB)) {
/*  490 */           return (CLOB)localDatum;
/*      */         }
/*      */         
/*  493 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getCLOB");
/*  494 */         localSQLException.fillInStackTrace();
/*  495 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  499 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public BFILE getBFILE(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  506 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  509 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  511 */       if (localDatum != null)
/*      */       {
/*  513 */         if ((localDatum instanceof BFILE)) {
/*  514 */           return (BFILE)localDatum;
/*      */         }
/*      */         
/*  517 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBFILE");
/*  518 */         localSQLException.fillInStackTrace();
/*  519 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  523 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public INTERVALDS getINTERVALDS(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  530 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  533 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  535 */       if (localDatum != null)
/*      */       {
/*  537 */         if ((localDatum instanceof INTERVALDS)) {
/*  538 */           return (INTERVALDS)localDatum;
/*      */         }
/*      */         
/*  541 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  542 */         localSQLException.fillInStackTrace();
/*  543 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  547 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public INTERVALYM getINTERVALYM(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  554 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  557 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  559 */       if (localDatum != null)
/*      */       {
/*  561 */         if ((localDatum instanceof INTERVALYM)) {
/*  562 */           return (INTERVALYM)localDatum;
/*      */         }
/*      */         
/*  565 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/*  566 */         localSQLException.fillInStackTrace();
/*  567 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  571 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public BFILE getBfile(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 56	oracle/jdbc/driver/ArrayDataResultSet:getBFILE	(I)Loracle/sql/BFILE;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #579	-> byte code offset #0
/*      */     //   Java source line #582	-> byte code offset #7
/*      */     //   Java source line #584	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ArrayDataResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public TIMESTAMP getTIMESTAMP(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  589 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  592 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  594 */       if (localDatum != null)
/*      */       {
/*  596 */         if ((localDatum instanceof TIMESTAMP)) {
/*  597 */           return (TIMESTAMP)localDatum;
/*      */         }
/*      */         
/*  600 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMP");
/*  601 */         localSQLException.fillInStackTrace();
/*  602 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  606 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public TIMESTAMPTZ getTIMESTAMPTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  613 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  616 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  618 */       if (localDatum != null)
/*      */       {
/*  620 */         if ((localDatum instanceof TIMESTAMPTZ)) {
/*  621 */           return (TIMESTAMPTZ)localDatum;
/*      */         }
/*      */         
/*  624 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPTZ");
/*  625 */         localSQLException.fillInStackTrace();
/*  626 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  630 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public TIMESTAMPLTZ getTIMESTAMPLTZ(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  637 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  640 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  642 */       if (localDatum != null)
/*      */       {
/*  644 */         if ((localDatum instanceof TIMESTAMPLTZ)) {
/*  645 */           return (TIMESTAMPLTZ)localDatum;
/*      */         }
/*      */         
/*  648 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getTIMESTAMPLTZ");
/*  649 */         localSQLException.fillInStackTrace();
/*  650 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  654 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  661 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  664 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  666 */       if (localDatum != null) {
/*  667 */         return localDatum.booleanValue();
/*      */       }
/*  669 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public oracle.jdbc.OracleResultSet.AuthorizationIndicator getAuthorizationIndicator(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  678 */     return null;
/*      */   }
/*      */   
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  684 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  687 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  689 */       if (localDatum != null) {
/*  690 */         return localDatum.byteValue();
/*      */       }
/*  692 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  699 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  702 */       long l = getLong(paramInt);
/*      */       
/*  704 */       if ((l > 65537L) || (l < -65538L))
/*      */       {
/*  706 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26, "getShort");
/*  707 */         localSQLException.fillInStackTrace();
/*  708 */         throw localSQLException;
/*      */       }
/*      */       
/*  711 */       return (short)(int)l;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  718 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  721 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  723 */       if (localDatum != null)
/*      */       {
/*  725 */         return localDatum.intValue();
/*      */       }
/*      */       
/*  728 */       return 0;
/*      */     }
/*      */   }
/*      */   
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  735 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  738 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  740 */       if (localDatum != null)
/*      */       {
/*  742 */         return localDatum.longValue();
/*      */       }
/*      */       
/*  745 */       return 0L;
/*      */     }
/*      */   }
/*      */   
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  752 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  755 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  757 */       if (localDatum != null)
/*      */       {
/*  759 */         return localDatum.floatValue();
/*      */       }
/*      */       
/*  762 */       return 0.0F;
/*      */     }
/*      */   }
/*      */   
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  769 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  772 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  774 */       if (localDatum != null)
/*      */       {
/*  776 */         return localDatum.doubleValue();
/*      */       }
/*      */       
/*  779 */       return 0.0D;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  787 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  790 */       Datum localDatum = getOracleObject(paramInt1);
/*      */       
/*  792 */       if (localDatum != null)
/*      */       {
/*  794 */         return localDatum.bigDecimalValue();
/*      */       }
/*      */       
/*  797 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  804 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  807 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  809 */       if (localDatum != null)
/*      */       {
/*  811 */         if ((localDatum instanceof RAW)) {
/*  812 */           return ((RAW)localDatum).shareBytes();
/*      */         }
/*      */         
/*  815 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getBytes");
/*  816 */         localSQLException.fillInStackTrace();
/*  817 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  821 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  828 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  831 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  833 */       if (localDatum != null)
/*      */       {
/*  835 */         return localDatum.dateValue();
/*      */       }
/*      */       
/*  838 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  845 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  848 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  850 */       if (localDatum != null)
/*      */       {
/*  852 */         return localDatum.timeValue();
/*      */       }
/*      */       
/*  855 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  863 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  866 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  868 */       if (localDatum != null)
/*      */       {
/*  870 */         return localDatum.timestampValue();
/*      */       }
/*      */       
/*  873 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  881 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  884 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  886 */       if (localDatum != null)
/*      */       {
/*  888 */         localDatum.asciiStreamValue();
/*      */       }
/*      */       
/*  891 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  899 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  902 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  904 */       if (localDatum != null)
/*      */       {
/*  906 */         DBConversion localDBConversion = this.connection.conversion;
/*  907 */         byte[] arrayOfByte = localDatum.shareBytes();
/*      */         
/*  909 */         if ((localDatum instanceof RAW))
/*      */         {
/*  911 */           return localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 3);
/*      */         }
/*  913 */         if ((localDatum instanceof CHAR))
/*      */         {
/*  915 */           return localDBConversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 1);
/*      */         }
/*      */         
/*      */ 
/*  919 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, "getUnicodeStream");
/*  920 */         localSQLException.fillInStackTrace();
/*  921 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  925 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  933 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  936 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*  938 */       if (localDatum != null)
/*      */       {
/*  940 */         return localDatum.binaryStreamValue();
/*      */       }
/*      */       
/*  943 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObject(int paramInt, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/*  951 */     synchronized (this.connection)
/*      */     {
/*  953 */       Object localObject1 = getObject(paramInt);
/*  954 */       return paramOracleDataFactory.create(localObject1, 0);
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: aload_0
/*      */     //   10: getfield 5	oracle/jdbc/driver/ArrayDataResultSet:map	Ljava/util/Map;
/*      */     //   13: invokevirtual 91	oracle/jdbc/driver/ArrayDataResultSet:getObject	(ILjava/util/Map;)Ljava/lang/Object;
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: areturn
/*      */     //   19: astore_3
/*      */     //   20: aload_2
/*      */     //   21: monitorexit
/*      */     //   22: aload_3
/*      */     //   23: athrow
/*      */     // Line number table:
/*      */     //   Java source line #961	-> byte code offset #0
/*      */     //   Java source line #965	-> byte code offset #7
/*      */     //   Java source line #967	-> byte code offset #19
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	24	0	this	ArrayDataResultSet
/*      */     //   0	24	1	paramInt	int
/*      */     //   5	16	2	Ljava/lang/Object;	Object
/*      */     //   19	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	18	19	finally
/*      */     //   19	22	19	finally
/*      */   }
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public oracle.sql.CustomDatum getCustomDatum(int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/*  976 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  979 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  984 */       return paramCustomDatumFactory.create(localDatum, 0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public ORAData getORAData(int paramInt, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/*  992 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  995 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1000 */       return paramORADataFactory.create(localDatum, 0);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/* 1010 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1013 */       if (this.closed)
/*      */       {
/* 1015 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10, "getMetaData");
/* 1016 */         localSQLException.fillInStackTrace();
/* 1017 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1023 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "getMetaData");
/* 1024 */       localSQLException.fillInStackTrace();
/* 1025 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1034 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1037 */       if (paramString.equalsIgnoreCase("index"))
/* 1038 */         return 1;
/* 1039 */       if (paramString.equalsIgnoreCase("value")) {
/* 1040 */         return 2;
/*      */       }
/*      */       
/* 1043 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6, "get_column_index");
/* 1044 */       localSQLException.fillInStackTrace();
/* 1045 */       throw localSQLException;
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
/*      */   public java.sql.Statement getStatement()
/*      */     throws SQLException
/*      */   {
/* 1060 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1067 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1070 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1072 */       if (localDatum != null)
/*      */       {
/* 1074 */         if ((localDatum instanceof STRUCT)) {
/* 1075 */           return ((STRUCT)localDatum).toJdbc(paramMap);
/*      */         }
/* 1077 */         return localDatum.toJdbc();
/*      */       }
/*      */       
/* 1080 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 101	oracle/jdbc/driver/ArrayDataResultSet:getREF	(I)Loracle/sql/REF;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1087	-> byte code offset #0
/*      */     //   Java source line #1090	-> byte code offset #7
/*      */     //   Java source line #1092	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ArrayDataResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 102	oracle/jdbc/driver/ArrayDataResultSet:getBLOB	(I)Loracle/sql/BLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1097	-> byte code offset #0
/*      */     //   Java source line #1100	-> byte code offset #7
/*      */     //   Java source line #1102	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ArrayDataResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 103	oracle/jdbc/driver/ArrayDataResultSet:getCLOB	(I)Loracle/sql/CLOB;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1107	-> byte code offset #0
/*      */     //   Java source line #1110	-> byte code offset #7
/*      */     //   Java source line #1112	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ArrayDataResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public java.sql.Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 3	oracle/jdbc/driver/ArrayDataResultSet:connection	Loracle/jdbc/driver/PhysicalConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: iload_1
/*      */     //   9: invokevirtual 104	oracle/jdbc/driver/ArrayDataResultSet:getARRAY	(I)Loracle/sql/ARRAY;
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1118	-> byte code offset #0
/*      */     //   Java source line #1121	-> byte code offset #7
/*      */     //   Java source line #1123	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	ArrayDataResultSet
/*      */     //   0	20	1	paramInt	int
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1136 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1139 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1141 */       if (localDatum != null)
/*      */       {
/* 1143 */         return localDatum.characterStreamValue();
/*      */       }
/*      */       
/* 1146 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1154 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1157 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1159 */       if (localDatum != null)
/*      */       {
/* 1161 */         return localDatum.bigDecimalValue();
/*      */       }
/*      */       
/* 1164 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1172 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1175 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1177 */       if (localDatum != null)
/*      */       {
/* 1179 */         DATE localDATE = null;
/*      */         
/* 1181 */         if ((localDatum instanceof DATE)) {
/* 1182 */           localDATE = (DATE)localDatum;
/*      */         } else {
/* 1184 */           localDATE = new DATE(localDatum.stringValue());
/*      */         }
/* 1186 */         if (localDATE != null) {
/* 1187 */           return localDATE.dateValue(paramCalendar);
/*      */         }
/*      */       }
/* 1190 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1198 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1201 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1203 */       if (localDatum != null)
/*      */       {
/* 1205 */         DATE localDATE = null;
/*      */         
/* 1207 */         if ((localDatum instanceof DATE)) {
/* 1208 */           localDATE = (DATE)localDatum;
/*      */         } else {
/* 1210 */           localDATE = new DATE(localDatum.stringValue());
/*      */         }
/* 1212 */         if (localDATE != null) {
/* 1213 */           return localDATE.timeValue(paramCalendar);
/*      */         }
/*      */       }
/* 1216 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1224 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1227 */       Datum localDatum = getOracleObject(paramInt);
/*      */       
/* 1229 */       if (localDatum != null)
/*      */       {
/* 1231 */         DATE localDATE = null;
/*      */         
/* 1233 */         if ((localDatum instanceof DATE)) {
/* 1234 */           localDATE = (DATE)localDatum;
/*      */         } else {
/* 1236 */           localDATE = new DATE(localDatum.stringValue());
/*      */         }
/* 1238 */         if (localDATE != null) {
/* 1239 */           return localDATE.timestampValue(paramCalendar);
/*      */         }
/*      */       }
/* 1242 */       return null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public java.net.URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1251 */     synchronized (this.connection)
/*      */     {
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
/* 1288 */       SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 1289 */       localSQLException.fillInStackTrace();
/* 1290 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public String getCursorName()
/*      */     throws SQLException
/*      */   {
/* 1298 */     synchronized (this.connection)
/*      */     {
/*      */ 
/* 1301 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "getCursorName");
/* 1302 */       localSQLException.fillInStackTrace();
/* 1303 */       throw localSQLException;
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
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1317 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/* 1319 */     if (localDatum != null)
/*      */     {
/* 1321 */       if ((localDatum instanceof NCLOB)) {
/* 1322 */         return (NCLOB)localDatum;
/*      */       }
/*      */       
/* 1325 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 1326 */       localSQLException.fillInStackTrace();
/* 1327 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1331 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1338 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/* 1340 */     if (localDatum != null)
/*      */     {
/* 1342 */       return localDatum.stringValue();
/*      */     }
/*      */     
/* 1345 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1353 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/* 1355 */     if (localDatum != null)
/*      */     {
/* 1357 */       return localDatum.characterStreamValue();
/*      */     }
/*      */     
/* 1360 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   public java.sql.RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1367 */     return getROWID(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1375 */     Datum localDatum = getOracleObject(paramInt);
/*      */     
/* 1377 */     if (localDatum != null)
/*      */     {
/* 1379 */       if ((localDatum instanceof SQLXML)) {
/* 1380 */         return (SQLXML)localDatum;
/*      */       }
/*      */       
/* 1383 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 1384 */       localSQLException.fillInStackTrace();
/* 1385 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1389 */     return null;
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
/*      */   public boolean isBeforeFirst()
/*      */     throws SQLException
/*      */   {
/* 1403 */     if (this.closed)
/*      */     {
/* 1405 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 1406 */       localSQLException.fillInStackTrace();
/* 1407 */       throw localSQLException;
/*      */     }
/* 1409 */     return this.currentIndex < 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/* 1417 */     if (this.closed)
/*      */     {
/* 1419 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 1420 */       localSQLException.fillInStackTrace();
/* 1421 */       throw localSQLException;
/*      */     }
/* 1423 */     return this.currentIndex > this.lastIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/* 1431 */     if (this.closed)
/*      */     {
/* 1433 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 1434 */       localSQLException.fillInStackTrace();
/* 1435 */       throw localSQLException;
/*      */     }
/* 1437 */     return this.currentIndex == 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/* 1445 */     if (this.closed)
/*      */     {
/* 1447 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 1448 */       localSQLException.fillInStackTrace();
/* 1449 */       throw localSQLException;
/*      */     }
/* 1451 */     return this.currentIndex == this.lastIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/* 1459 */     if (this.closed)
/*      */     {
/* 1461 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 10);
/* 1462 */       localSQLException.fillInStackTrace();
/* 1463 */       throw localSQLException;
/*      */     }
/* 1465 */     return this.currentIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1477 */     if (paramInt < 0)
/*      */     {
/* 1479 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1480 */       localSQLException.fillInStackTrace();
/* 1481 */       throw localSQLException;
/*      */     }
/* 1483 */     if (paramInt == 0) {
/* 1484 */       this.fetchSize = OracleConnection.DEFAULT_ROW_PREFETCH;
/*      */     } else {
/* 1486 */       this.fetchSize = paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/* 1493 */     return this.fetchSize;
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1508 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1513 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/ArrayDataResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */