/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
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
/*      */ public class OracleSql
/*      */ {
/*      */   static final int UNINITIALIZED = -1;
/*   39 */   static final String[] EMPTY_LIST = new String[0];
/*      */   
/*      */   DBConversion conversion;
/*      */   
/*      */   String originalSql;
/*      */   
/*      */   String parameterSql;
/*      */   
/*      */   String utickSql;
/*      */   String processedSql;
/*      */   String rowidSql;
/*      */   String actualSql;
/*      */   byte[] sqlBytes;
/*   52 */   OracleStatement.SqlKind sqlKind = OracleStatement.SqlKind.UNINITIALIZED;
/*   53 */   byte sqlKindByte = -1;
/*   54 */   int parameterCount = -1;
/*   55 */   int returningIntoParameterCount = -1;
/*   56 */   boolean currentConvertNcharLiterals = true;
/*   57 */   boolean currentProcessEscapes = true;
/*   58 */   boolean includeRowid = false;
/*   59 */   String[] parameterList = EMPTY_LIST;
/*   60 */   char[] currentParameter = null;
/*      */   
/*   62 */   int bindParameterCount = -1;
/*   63 */   String[] bindParameterList = null;
/*   64 */   int cachedBindParameterCount = -1;
/*   65 */   String[] cachedBindParameterList = null;
/*      */   String cachedParameterSql;
/*      */   String cachedUtickSql;
/*      */   String cachedProcessedSql;
/*      */   String cachedRowidSql;
/*      */   String cachedActualSql;
/*      */   byte[] cachedSqlBytes;
/*   72 */   int selectEndIndex = -1;
/*   73 */   int orderByStartIndex = -1;
/*   74 */   int orderByEndIndex = -1;
/*   75 */   int whereStartIndex = -1;
/*   76 */   int whereEndIndex = -1;
/*   77 */   int forUpdateStartIndex = -1;
/*   78 */   int forUpdateEndIndex = -1;
/*      */   
/*   80 */   int[] ncharLiteralLocation = new int['ȁ'];
/*   81 */   int lastNcharLiteralLocation = -1;
/*      */   
/*      */   static final String paramPrefix = "rowid";
/*   84 */   int paramSuffix = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected OracleSql(DBConversion paramDBConversion)
/*      */   {
/*   95 */     this.conversion = paramDBConversion;
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
/*      */   protected void initialize(String paramString)
/*      */     throws SQLException
/*      */   {
/*  112 */     if ((paramString == null) || (paramString.length() == 0))
/*      */     {
/*  114 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 104);
/*  115 */       localSQLException.fillInStackTrace();
/*  116 */       throw localSQLException;
/*      */     }
/*      */     
/*  119 */     this.originalSql = paramString;
/*  120 */     this.utickSql = null;
/*  121 */     this.processedSql = null;
/*  122 */     this.rowidSql = null;
/*  123 */     this.actualSql = null;
/*  124 */     this.sqlBytes = null;
/*  125 */     this.sqlKind = OracleStatement.SqlKind.UNINITIALIZED;
/*  126 */     this.parameterCount = -1;
/*  127 */     this.parameterList = EMPTY_LIST;
/*  128 */     this.includeRowid = false;
/*      */     
/*  130 */     this.parameterSql = this.originalSql;
/*  131 */     this.bindParameterCount = -1;
/*  132 */     this.bindParameterList = null;
/*  133 */     this.cachedBindParameterCount = -1;
/*  134 */     this.cachedBindParameterList = null;
/*  135 */     this.cachedParameterSql = null;
/*  136 */     this.cachedActualSql = null;
/*  137 */     this.cachedProcessedSql = null;
/*  138 */     this.cachedRowidSql = null;
/*  139 */     this.cachedSqlBytes = null;
/*  140 */     this.selectEndIndex = -1;
/*  141 */     this.orderByStartIndex = -1;
/*  142 */     this.orderByEndIndex = -1;
/*  143 */     this.whereStartIndex = -1;
/*  144 */     this.whereEndIndex = -1;
/*  145 */     this.forUpdateStartIndex = -1;
/*  146 */     this.forUpdateEndIndex = -1;
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
/*      */   String getOriginalSql()
/*      */   {
/*  160 */     return this.originalSql;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean setNamedParameters(int paramInt, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/*  172 */     boolean bool = false;
/*      */     
/*  174 */     if (paramInt == 0)
/*      */     {
/*  176 */       this.bindParameterCount = -1;
/*  177 */       bool = this.bindParameterCount != this.cachedBindParameterCount;
/*      */     }
/*      */     else
/*      */     {
/*  181 */       this.bindParameterCount = paramInt;
/*  182 */       this.bindParameterList = paramArrayOfString;
/*  183 */       bool = this.bindParameterCount != this.cachedBindParameterCount;
/*      */       
/*  185 */       if (!bool) {
/*  186 */         for (int j = 0; j < paramInt; j++)
/*  187 */           if (this.bindParameterList[j] != this.cachedBindParameterList[j])
/*      */           {
/*  189 */             bool = true;
/*      */             
/*  191 */             break;
/*      */           }
/*      */       }
/*  194 */       if (bool)
/*      */       {
/*  196 */         if (this.bindParameterCount != getParameterCount())
/*      */         {
/*  198 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 197);
/*  199 */           ((SQLException)localObject).fillInStackTrace();
/*  200 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*  203 */         Object localObject = this.originalSql.toCharArray();
/*  204 */         StringBuffer localStringBuffer = new StringBuffer();
/*      */         
/*  206 */         int k = 0;
/*      */         
/*  208 */         for (int m = 0; m < localObject.length; m++)
/*      */         {
/*  210 */           if (localObject[m] != '?')
/*      */           {
/*  212 */             localStringBuffer.append(localObject[m]);
/*      */           }
/*      */           else
/*      */           {
/*  216 */             localStringBuffer.append(this.bindParameterList[(k++)]);
/*  217 */             localStringBuffer.append("=>" + nextArgument());
/*      */           }
/*      */         }
/*      */         
/*  221 */         this.parameterSql = localStringBuffer.toString();
/*  222 */         this.actualSql = null;
/*  223 */         this.utickSql = null;
/*  224 */         this.processedSql = null;
/*  225 */         this.rowidSql = null;
/*  226 */         this.sqlBytes = null;
/*      */       }
/*      */       else
/*      */       {
/*  230 */         this.parameterSql = this.cachedParameterSql;
/*  231 */         this.actualSql = this.cachedActualSql;
/*  232 */         this.utickSql = this.cachedUtickSql;
/*  233 */         this.processedSql = this.cachedProcessedSql;
/*  234 */         this.rowidSql = this.cachedRowidSql;
/*  235 */         this.sqlBytes = this.cachedSqlBytes;
/*      */       }
/*      */     }
/*      */     
/*  239 */     this.cachedBindParameterList = null;
/*  240 */     this.cachedParameterSql = null;
/*  241 */     this.cachedActualSql = null;
/*  242 */     this.cachedUtickSql = null;
/*  243 */     this.cachedProcessedSql = null;
/*  244 */     this.cachedRowidSql = null;
/*  245 */     this.cachedSqlBytes = null;
/*      */     
/*  247 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void resetNamedParameters()
/*      */   {
/*  257 */     this.cachedBindParameterCount = this.bindParameterCount;
/*      */     
/*  259 */     if (this.bindParameterCount != -1)
/*      */     {
/*  261 */       if ((this.cachedBindParameterList == null) || (this.cachedBindParameterList == this.bindParameterList) || (this.cachedBindParameterList.length < this.bindParameterCount))
/*      */       {
/*      */ 
/*  264 */         this.cachedBindParameterList = new String[this.bindParameterCount];
/*      */       }
/*  266 */       System.arraycopy(this.bindParameterList, 0, this.cachedBindParameterList, 0, this.bindParameterCount);
/*      */       
/*      */ 
/*  269 */       this.cachedParameterSql = this.parameterSql;
/*  270 */       this.cachedActualSql = this.actualSql;
/*  271 */       this.cachedUtickSql = this.utickSql;
/*  272 */       this.cachedProcessedSql = this.processedSql;
/*  273 */       this.cachedRowidSql = this.rowidSql;
/*  274 */       this.cachedSqlBytes = this.sqlBytes;
/*      */       
/*  276 */       this.bindParameterCount = -1;
/*  277 */       this.bindParameterList = null;
/*  278 */       this.parameterSql = this.originalSql;
/*  279 */       this.actualSql = null;
/*  280 */       this.utickSql = null;
/*  281 */       this.processedSql = null;
/*  282 */       this.rowidSql = null;
/*  283 */       this.sqlBytes = null;
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
/*      */   String getSql(boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*  302 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) {
/*  303 */       computeBasicInfo(this.parameterSql);
/*      */     }
/*      */     
/*  306 */     if ((paramBoolean1 != this.currentProcessEscapes) || (paramBoolean2 != this.currentConvertNcharLiterals))
/*      */     {
/*      */ 
/*  309 */       if (paramBoolean2 != this.currentConvertNcharLiterals) {
/*  310 */         this.utickSql = null;
/*      */       }
/*      */       
/*  313 */       this.processedSql = null;
/*  314 */       this.rowidSql = null;
/*  315 */       this.actualSql = null;
/*  316 */       this.sqlBytes = null;
/*      */     }
/*      */     
/*  319 */     this.currentConvertNcharLiterals = paramBoolean2;
/*  320 */     this.currentProcessEscapes = paramBoolean1;
/*      */     
/*  322 */     if (this.actualSql == null) {
/*  323 */       if (this.utickSql == null) {
/*  324 */         this.utickSql = (this.currentConvertNcharLiterals ? convertNcharLiterals(this.parameterSql) : this.parameterSql);
/*      */       }
/*      */       
/*  327 */       if (this.processedSql == null) {
/*  328 */         this.processedSql = (this.currentProcessEscapes ? parse(this.utickSql) : this.utickSql);
/*      */       }
/*  330 */       if (this.rowidSql == null) {
/*  331 */         this.rowidSql = (this.includeRowid ? addRowid(this.processedSql) : this.processedSql);
/*      */       }
/*  333 */       this.actualSql = this.rowidSql;
/*      */     }
/*      */     
/*      */ 
/*  337 */     return this.actualSql;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   String getRevisedSql()
/*      */     throws SQLException
/*      */   {
/*  346 */     String str = null;
/*      */     
/*  348 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) { computeBasicInfo(this.parameterSql);
/*      */     }
/*  350 */     str = removeForUpdate(this.parameterSql);
/*      */     
/*  352 */     return addRowid(str);
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
/*      */   String removeForUpdate(String paramString)
/*      */     throws SQLException
/*      */   {
/*  367 */     if ((this.orderByStartIndex != -1) && ((this.forUpdateStartIndex == -1) || (this.forUpdateStartIndex > this.orderByStartIndex)))
/*      */     {
/*      */ 
/*      */ 
/*  371 */       paramString = paramString.substring(0, this.orderByStartIndex);
/*      */     }
/*  373 */     else if (this.forUpdateStartIndex != -1)
/*      */     {
/*  375 */       paramString = paramString.substring(0, this.forUpdateStartIndex);
/*      */     }
/*      */     
/*  378 */     return paramString;
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
/*      */   void appendForUpdate(StringBuffer paramStringBuffer)
/*      */     throws SQLException
/*      */   {
/*  396 */     if ((this.orderByStartIndex != -1) && ((this.forUpdateStartIndex == -1) || (this.forUpdateStartIndex > this.orderByStartIndex)))
/*      */     {
/*      */ 
/*      */ 
/*  400 */       paramStringBuffer.append(this.originalSql.substring(this.orderByStartIndex));
/*      */     }
/*  402 */     else if (this.forUpdateStartIndex != -1)
/*      */     {
/*  404 */       paramStringBuffer.append(this.originalSql.substring(this.forUpdateStartIndex));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*  409 */   StringBuffer stringBufferForScrollableStatement = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int cMax = 127;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   String getInsertSqlForUpdatableResultSet(UpdatableResultSet paramUpdatableResultSet)
/*      */     throws SQLException
/*      */   {
/*  422 */     String str = getOriginalSql();
/*  423 */     boolean bool = generatedSqlNeedEscapeProcessing();
/*      */     
/*  425 */     if (this.stringBufferForScrollableStatement == null) {
/*  426 */       this.stringBufferForScrollableStatement = new StringBuffer(str.length() + 100);
/*      */     } else {
/*  428 */       this.stringBufferForScrollableStatement.delete(0, this.stringBufferForScrollableStatement.length());
/*      */     }
/*  430 */     this.stringBufferForScrollableStatement.append("insert into (");
/*      */     
/*  432 */     this.stringBufferForScrollableStatement.append(removeForUpdate(str));
/*  433 */     this.stringBufferForScrollableStatement.append(") values ( ");
/*      */     
/*  435 */     for (int j = 1; 
/*  436 */         j < paramUpdatableResultSet.getColumnCount(); 
/*  437 */         j++)
/*      */     {
/*  439 */       if (j != 1) {
/*  440 */         this.stringBufferForScrollableStatement.append(", ");
/*      */       }
/*  442 */       if (bool) {
/*  443 */         this.stringBufferForScrollableStatement.append("?");
/*      */       } else {
/*  445 */         this.stringBufferForScrollableStatement.append(":" + generateParameterName());
/*      */       }
/*      */     }
/*  448 */     this.stringBufferForScrollableStatement.append(")");
/*      */     
/*  450 */     this.paramSuffix = 0;
/*      */     
/*  452 */     return this.stringBufferForScrollableStatement.substring(0, this.stringBufferForScrollableStatement.length());
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
/*      */   String getRefetchSqlForScrollableResultSet(ScrollableResultSet paramScrollableResultSet, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  472 */     String str = getRevisedSql();
/*  473 */     boolean bool = generatedSqlNeedEscapeProcessing();
/*      */     
/*  475 */     if (this.stringBufferForScrollableStatement == null) {
/*  476 */       this.stringBufferForScrollableStatement = new StringBuffer(str.length() + 100);
/*      */     } else {
/*  478 */       this.stringBufferForScrollableStatement.delete(0, this.stringBufferForScrollableStatement.length());
/*      */     }
/*      */     
/*  481 */     this.stringBufferForScrollableStatement.append(str);
/*      */     
/*  483 */     if (this.whereStartIndex == -1)
/*      */     {
/*  485 */       this.stringBufferForScrollableStatement.append(" WHERE ( ROWID = :" + generateParameterName());
/*      */     }
/*      */     else {
/*  488 */       this.stringBufferForScrollableStatement.append(" AND ( ROWID = :" + generateParameterName());
/*      */     }
/*      */     
/*  491 */     for (int j = 0; j < paramInt - 1; j++) {
/*  492 */       if (bool) {
/*  493 */         this.stringBufferForScrollableStatement.append(" OR ROWID = ?");
/*      */       }
/*      */       else
/*  496 */         this.stringBufferForScrollableStatement.append(" OR ROWID = :" + generateParameterName());
/*      */     }
/*  498 */     this.stringBufferForScrollableStatement.append(" ) ");
/*      */     
/*      */ 
/*  501 */     appendForUpdate(this.stringBufferForScrollableStatement);
/*      */     
/*  503 */     this.paramSuffix = 0;
/*      */     
/*  505 */     return this.stringBufferForScrollableStatement.substring(0, this.stringBufferForScrollableStatement.length());
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
/*      */   String getUpdateSqlForUpdatableResultSet(UpdatableResultSet paramUpdatableResultSet, int paramInt, Object[] paramArrayOfObject, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/*  523 */     String str = getRevisedSql();
/*  524 */     boolean bool = generatedSqlNeedEscapeProcessing();
/*      */     
/*  526 */     if (this.stringBufferForScrollableStatement == null) {
/*  527 */       this.stringBufferForScrollableStatement = new StringBuffer(str.length() + 100);
/*      */     } else {
/*  529 */       this.stringBufferForScrollableStatement.delete(0, this.stringBufferForScrollableStatement.length());
/*      */     }
/*      */     
/*      */ 
/*  533 */     this.stringBufferForScrollableStatement.append("update (");
/*  534 */     this.stringBufferForScrollableStatement.append(str);
/*  535 */     this.stringBufferForScrollableStatement.append(") set ");
/*      */     
/*      */ 
/*  538 */     if (paramArrayOfObject != null)
/*      */     {
/*  540 */       for (int j = 0; j < paramInt; j++)
/*      */       {
/*  542 */         if (j > 0) {
/*  543 */           this.stringBufferForScrollableStatement.append(", ");
/*      */         }
/*  545 */         this.stringBufferForScrollableStatement.append(paramUpdatableResultSet.getInternalMetadata().getColumnName(paramArrayOfInt[j] + 1));
/*      */         
/*      */ 
/*  548 */         if (bool) {
/*  549 */           this.stringBufferForScrollableStatement.append(" = ?");
/*      */         } else {
/*  551 */           this.stringBufferForScrollableStatement.append(" = :" + generateParameterName());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  556 */     this.stringBufferForScrollableStatement.append(" WHERE ");
/*  557 */     if (bool) {
/*  558 */       this.stringBufferForScrollableStatement.append(" ROWID = ?");
/*      */     } else {
/*  560 */       this.stringBufferForScrollableStatement.append(" ROWID = :" + generateParameterName());
/*      */     }
/*      */     
/*  563 */     this.paramSuffix = 0;
/*      */     
/*  565 */     return this.stringBufferForScrollableStatement.substring(0, this.stringBufferForScrollableStatement.length());
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
/*      */   String getDeleteSqlForUpdatableResultSet(UpdatableResultSet paramUpdatableResultSet)
/*      */     throws SQLException
/*      */   {
/*  581 */     String str = getRevisedSql();
/*  582 */     boolean bool = generatedSqlNeedEscapeProcessing();
/*      */     
/*  584 */     if (this.stringBufferForScrollableStatement == null) {
/*  585 */       this.stringBufferForScrollableStatement = new StringBuffer(str.length() + 100);
/*      */     } else {
/*  587 */       this.stringBufferForScrollableStatement.delete(0, this.stringBufferForScrollableStatement.length());
/*      */     }
/*      */     
/*      */ 
/*  591 */     this.stringBufferForScrollableStatement.append("delete from (");
/*  592 */     this.stringBufferForScrollableStatement.append(str);
/*  593 */     this.stringBufferForScrollableStatement.append(") where ");
/*      */     
/*  595 */     if (bool) {
/*  596 */       this.stringBufferForScrollableStatement.append(" ROWID = ?");
/*      */     } else {
/*  598 */       this.stringBufferForScrollableStatement.append(" ROWID = :" + generateParameterName());
/*      */     }
/*      */     
/*  601 */     this.paramSuffix = 0;
/*      */     
/*  603 */     return this.stringBufferForScrollableStatement.substring(0, this.stringBufferForScrollableStatement.length());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final boolean generatedSqlNeedEscapeProcessing()
/*      */   {
/*  615 */     return (this.parameterCount > 0) && (this.parameterList == EMPTY_LIST);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] getSqlBytes(boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*  628 */     if ((this.sqlBytes == null) || (paramBoolean1 != this.currentProcessEscapes))
/*      */     {
/*  630 */       this.sqlBytes = this.conversion.StringToCharBytes(getSql(paramBoolean1, paramBoolean2));
/*      */     }
/*      */     
/*      */ 
/*  634 */     return this.sqlBytes;
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
/*      */   OracleStatement.SqlKind getSqlKind()
/*      */     throws SQLException
/*      */   {
/*  648 */     if (this.parameterSql == null) {
/*  649 */       return OracleStatement.SqlKind.UNINITIALIZED;
/*      */     }
/*  651 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) {
/*  652 */       computeBasicInfo(this.parameterSql);
/*      */     }
/*  654 */     return this.sqlKind;
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
/*      */   protected int getParameterCount()
/*      */     throws SQLException
/*      */   {
/*  668 */     if (this.parameterCount == -1)
/*      */     {
/*  670 */       computeBasicInfo(this.parameterSql);
/*      */     }
/*      */     
/*  673 */     return this.parameterCount;
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
/*      */   protected String[] getParameterList()
/*      */     throws SQLException
/*      */   {
/*  690 */     if (this.parameterCount == -1)
/*      */     {
/*  692 */       computeBasicInfo(this.parameterSql);
/*      */     }
/*      */     
/*  695 */     return this.parameterList;
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
/*      */   void setIncludeRowid(boolean paramBoolean)
/*      */   {
/*  710 */     if (paramBoolean != this.includeRowid)
/*      */     {
/*  712 */       this.includeRowid = paramBoolean;
/*  713 */       this.rowidSql = null;
/*  714 */       this.actualSql = null;
/*  715 */       this.sqlBytes = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  724 */     return this.parameterSql == null ? "null" : this.parameterSql;
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
/*      */   private String hexUnicode(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  740 */     String str = Integer.toHexString(paramInt);
/*  741 */     switch (str.length()) {
/*      */     case 0: 
/*  743 */       return "\\0000";
/*  744 */     case 1:  return "\\000" + str;
/*  745 */     case 2:  return "\\00" + str;
/*  746 */     case 3:  return "\\0" + str;
/*  747 */     case 4:  return "\\" + str;
/*      */     }
/*      */     
/*  750 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 89, "Unexpected case in OracleSql.hexUnicode: " + paramInt);
/*  751 */     localSQLException.fillInStackTrace();
/*  752 */     throw localSQLException;
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
/*      */   String convertNcharLiterals(String paramString)
/*      */     throws SQLException
/*      */   {
/*  775 */     if (this.lastNcharLiteralLocation <= 2) return paramString;
/*  776 */     String str = "";
/*  777 */     int j = 0;
/*      */     for (;;)
/*      */     {
/*  780 */       int k = this.ncharLiteralLocation[(j++)];
/*  781 */       int m = this.ncharLiteralLocation[(j++)];
/*      */       
/*  783 */       str = str + paramString.substring(k, m);
/*  784 */       if (j >= this.lastNcharLiteralLocation) break;
/*  785 */       k = this.ncharLiteralLocation[j];
/*  786 */       str = str + "u'";
/*      */       
/*  788 */       for (int n = m + 2; n < k; n++)
/*      */       {
/*  790 */         char c = paramString.charAt(n);
/*  791 */         if (c == '\\') { str = str + "\\\\";
/*  792 */         } else if (c < '') str = str + c; else
/*  793 */           str = str + hexUnicode(c);
/*      */       }
/*      */     }
/*  796 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  804 */   private static final int[][] TRANSITION = OracleSqlReadOnly.TRANSITION;
/*      */   
/*      */ 
/*  807 */   private static final int[][] ACTION = OracleSqlReadOnly.ACTION;
/*      */   
/*      */   private static final int NO_ACTION = 0;
/*      */   
/*      */   private static final int DELETE_ACTION = 1;
/*      */   
/*      */   private static final int INSERT_ACTION = 2;
/*      */   
/*      */   private static final int MERGE_ACTION = 3;
/*      */   
/*      */   private static final int UPDATE_ACTION = 4;
/*      */   
/*      */   private static final int PLSQL_ACTION = 5;
/*      */   
/*      */   private static final int CALL_ACTION = 6;
/*      */   private static final int SELECT_ACTION = 7;
/*      */   private static final int ORDER_ACTION = 10;
/*      */   private static final int ORDER_BY_ACTION = 11;
/*      */   private static final int WHERE_ACTION = 9;
/*      */   private static final int FOR_ACTION = 12;
/*      */   private static final int FOR_UPDATE_ACTION = 13;
/*      */   private static final int OTHER_ACTION = 8;
/*      */   private static final int QUESTION_ACTION = 14;
/*      */   private static final int PARAMETER_ACTION = 15;
/*      */   private static final int END_PARAMETER_ACTION = 16;
/*      */   private static final int START_NCHAR_LITERAL_ACTION = 17;
/*      */   private static final int END_NCHAR_LITERAL_ACTION = 18;
/*      */   private static final int SAVE_DELIMITER_ACTION = 19;
/*      */   private static final int LOOK_FOR_DELIMITER_ACTION = 20;
/*      */   private static final int ALTER_SESSION_ACTION = 21;
/*      */   private static final int RETURNING_ACTION = 22;
/*      */   private static final int INTO_ACTION = 23;
/*      */   private static final int INITIAL_STATE = 0;
/*      */   private static final int RESTART_STATE = 66;
/*  841 */   private static final OracleSqlReadOnly.ODBCAction[][] ODBC_ACTION = OracleSqlReadOnly.ODBC_ACTION;
/*      */   
/*      */ 
/*      */   private static final boolean DEBUG_CBI = false;
/*      */   
/*      */ 
/*      */   int current_argument;
/*      */   
/*      */ 
/*      */   int i;
/*      */   
/*      */ 
/*      */   int length;
/*      */   
/*      */ 
/*      */   char currentChar;
/*      */   
/*      */ 
/*      */   boolean first;
/*      */   
/*      */ 
/*      */   String odbc_sql;
/*      */   
/*      */   StringBuffer oracle_sql;
/*      */   
/*      */   StringBuffer token_buffer;
/*      */   
/*      */ 
/*      */   void computeBasicInfo(String paramString)
/*      */     throws SQLException
/*      */   {
/*  872 */     this.parameterCount = 0;
/*  873 */     int j = 0;
/*  874 */     int k = 0;
/*  875 */     this.returningIntoParameterCount = 0;
/*      */     
/*  877 */     this.lastNcharLiteralLocation = 0;
/*  878 */     this.ncharLiteralLocation[(this.lastNcharLiteralLocation++)] = 0;
/*      */     
/*  880 */     char c1 = '\000';
/*      */     
/*  882 */     int m = 0;
/*      */     
/*  884 */     int n = 0;
/*  885 */     int i1 = paramString.length();
/*  886 */     int i2 = -1;
/*  887 */     int i3 = -1;
/*  888 */     int i4 = i1 + 1;
/*      */     
/*      */ 
/*  891 */     for (int i5 = 0; i5 < i4; i5++)
/*      */     {
/*  893 */       char c2 = i5 < i1 ? paramString.charAt(i5) : ' ';
/*  894 */       this.currentChar = c2;
/*      */       
/*  896 */       if (c2 > '')
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  902 */         if (Character.isLetterOrDigit(c2)) {
/*  903 */           this.currentChar = 'X';
/*      */         } else {
/*  905 */           this.currentChar = ' ';
/*      */         }
/*      */       }
/*      */       
/*      */       Object localObject;
/*  910 */       switch (ACTION[n][this.currentChar])
/*      */       {
/*      */       case 0: 
/*      */         break;
/*      */       
/*      */ 
/*      */       case 1: 
/*  917 */         this.sqlKind = OracleStatement.SqlKind.DELETE;
/*      */         
/*  919 */         break;
/*      */       
/*      */       case 2: 
/*  922 */         this.sqlKind = OracleStatement.SqlKind.INSERT;
/*      */         
/*  924 */         break;
/*      */       
/*      */       case 3: 
/*  927 */         this.sqlKind = OracleStatement.SqlKind.MERGE;
/*      */         
/*  929 */         break;
/*      */       
/*      */       case 4: 
/*  932 */         this.sqlKind = OracleStatement.SqlKind.UPDATE;
/*      */         
/*  934 */         break;
/*      */       
/*      */       case 5: 
/*  937 */         this.sqlKind = OracleStatement.SqlKind.PLSQL_BLOCK;
/*      */         
/*  939 */         break;
/*      */       
/*      */       case 6: 
/*  942 */         this.sqlKind = OracleStatement.SqlKind.CALL_BLOCK;
/*      */         
/*  944 */         break;
/*      */       
/*      */       case 7: 
/*  947 */         this.sqlKind = OracleStatement.SqlKind.SELECT;
/*  948 */         this.selectEndIndex = i5;
/*      */         
/*  950 */         break;
/*      */       
/*      */       case 8: 
/*  953 */         this.sqlKind = OracleStatement.SqlKind.OTHER;
/*      */         
/*  955 */         break;
/*      */       
/*      */       case 9: 
/*  958 */         this.whereStartIndex = (i5 - 5);
/*  959 */         this.whereEndIndex = i5;
/*      */         
/*  961 */         break;
/*      */       
/*      */       case 10: 
/*  964 */         i2 = i5 - 5;
/*      */         
/*  966 */         break;
/*      */       
/*      */       case 11: 
/*  969 */         this.orderByStartIndex = i2;
/*  970 */         this.orderByEndIndex = i5;
/*      */         
/*  972 */         break;
/*      */       
/*      */       case 12: 
/*  975 */         i3 = i5 - 3;
/*      */         
/*  977 */         break;
/*      */       
/*      */       case 13: 
/*  980 */         this.forUpdateStartIndex = i3;
/*  981 */         this.forUpdateEndIndex = i5;
/*  982 */         if (this.sqlKind == OracleStatement.SqlKind.SELECT) {
/*  983 */           this.sqlKind = OracleStatement.SqlKind.SELECT_FOR_UPDATE;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 21: 
/*  988 */         this.sqlKind = OracleStatement.SqlKind.ALTER_SESSION;
/*      */         
/*  990 */         break;
/*      */       
/*      */       case 14: 
/*  993 */         this.parameterCount += 1;
/*  994 */         if (k != 0) { this.returningIntoParameterCount += 1;
/*      */         }
/*      */         
/*      */         break;
/*      */       case 15: 
/*  999 */         if (this.currentParameter == null) {
/* 1000 */           this.currentParameter = new char[32];
/*      */         }
/* 1002 */         if (m >= this.currentParameter.length)
/*      */         {
/* 1004 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 134, new String(this.currentParameter));
/* 1005 */           ((SQLException)localObject).fillInStackTrace();
/* 1006 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/* 1009 */         this.currentParameter[(m++)] = c2;
/*      */         
/* 1011 */         break;
/*      */       
/*      */       case 16: 
/* 1014 */         if (m > 0)
/*      */         {
/* 1016 */           if (this.parameterList == EMPTY_LIST)
/*      */           {
/* 1018 */             this.parameterList = new String[Math.max(8, this.parameterCount * 4)];
/*      */           }
/* 1020 */           else if (this.parameterList.length <= this.parameterCount)
/*      */           {
/* 1022 */             localObject = new String[this.parameterList.length * 4];
/*      */             
/* 1024 */             System.arraycopy(this.parameterList, 0, localObject, 0, this.parameterList.length);
/*      */             
/*      */ 
/* 1027 */             this.parameterList = ((String[])localObject);
/*      */           }
/*      */           
/* 1030 */           this.parameterList[this.parameterCount] = new String(this.currentParameter, 0, m).intern();
/*      */           
/* 1032 */           m = 0;
/* 1033 */           this.parameterCount += 1;
/* 1034 */           if (k != 0) { this.returningIntoParameterCount += 1;
/*      */           }
/*      */         }
/*      */         
/*      */         break;
/*      */       case 17: 
/* 1040 */         this.ncharLiteralLocation[(this.lastNcharLiteralLocation++)] = (i5 - 1);
/* 1041 */         if (this.lastNcharLiteralLocation >= this.ncharLiteralLocation.length) {
/* 1042 */           growNcharLiteralLocation(this.ncharLiteralLocation.length << 2);
/*      */         }
/*      */         break;
/*      */       case 18: 
/* 1046 */         this.ncharLiteralLocation[(this.lastNcharLiteralLocation++)] = i5;
/* 1047 */         if (this.lastNcharLiteralLocation >= this.ncharLiteralLocation.length) {
/* 1048 */           growNcharLiteralLocation(this.ncharLiteralLocation.length << 2);
/*      */         }
/*      */         
/*      */         break;
/*      */       case 19: 
/* 1053 */         if (c2 == '[') { c1 = ']';
/* 1054 */         } else if (c2 == '{') { c1 = '}';
/* 1055 */         } else if (c2 == '<') { c1 = '>';
/* 1056 */         } else if (c2 == '(') c1 = ')'; else
/* 1057 */           c1 = c2;
/* 1058 */         break;
/*      */       
/*      */       case 20: 
/* 1061 */         if (c2 == c1)
/*      */         {
/*      */ 
/* 1064 */           n += 1;
/*      */         }
/*      */         
/*      */ 
/*      */         break;
/*      */       case 22: 
/* 1070 */         j = 1;
/* 1071 */         break;
/*      */       
/*      */       case 23: 
/* 1074 */         if (j != 0) { k = 1;
/*      */         }
/*      */         break;
/*      */       }
/* 1078 */       n = TRANSITION[n][this.currentChar];
/*      */     }
/*      */     
/* 1081 */     if (this.lastNcharLiteralLocation + 2 >= this.ncharLiteralLocation.length)
/* 1082 */       growNcharLiteralLocation(this.lastNcharLiteralLocation + 2);
/* 1083 */     this.ncharLiteralLocation[(this.lastNcharLiteralLocation++)] = i1;
/* 1084 */     this.ncharLiteralLocation[this.lastNcharLiteralLocation] = i1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   void growNcharLiteralLocation(int paramInt)
/*      */   {
/* 1092 */     int[] arrayOfInt = new int[paramInt];
/* 1093 */     System.arraycopy(this.ncharLiteralLocation, 0, arrayOfInt, 0, this.ncharLiteralLocation.length);
/* 1094 */     this.ncharLiteralLocation = null;
/* 1095 */     this.ncharLiteralLocation = arrayOfInt;
/*      */   }
/*      */   
/*      */ 
/*      */   private String addRowid(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1102 */     if (this.selectEndIndex == -1)
/*      */     {
/* 1104 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 88);
/* 1105 */       ((SQLException)localObject).fillInStackTrace();
/* 1106 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 1109 */     Object localObject = "select rowid as \"__Oracle_JDBC_interal_ROWID__\"," + paramString.substring(this.selectEndIndex);
/* 1110 */     return (String)localObject;
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
/*      */   static enum ParseMode
/*      */   {
/* 1124 */     NORMAL,  SCALAR,  LOCATE_1,  LOCATE_2;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     private ParseMode() {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String parse(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1143 */     this.first = true;
/* 1144 */     this.current_argument = 1;
/* 1145 */     this.i = 0;
/* 1146 */     this.odbc_sql = paramString;
/* 1147 */     this.length = this.odbc_sql.length();
/*      */     
/* 1149 */     if (this.oracle_sql == null)
/*      */     {
/* 1151 */       this.oracle_sql = new StringBuffer(this.length);
/* 1152 */       this.token_buffer = new StringBuffer(32);
/*      */     }
/*      */     else
/*      */     {
/* 1156 */       this.oracle_sql.ensureCapacity(this.length);
/*      */     }
/*      */     
/* 1159 */     this.oracle_sql.delete(0, this.oracle_sql.length());
/* 1160 */     skipSpace();
/* 1161 */     handleODBC(ParseMode.NORMAL);
/*      */     
/* 1163 */     if (this.i < this.length)
/*      */     {
/* 1165 */       Integer localInteger = Integer.valueOf(this.i);
/*      */       
/*      */ 
/* 1168 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 33, localInteger);
/* 1169 */       localSQLException.fillInStackTrace();
/* 1170 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1174 */     return this.oracle_sql.substring(0, this.oracle_sql.length());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void handleODBC(ParseMode paramParseMode)
/*      */     throws SQLException
/*      */   {
/* 1184 */     int j = paramParseMode == ParseMode.NORMAL ? 0 : 66;
/* 1185 */     char c1 = '\000';
/* 1186 */     int k = 0;
/*      */     
/* 1188 */     while (this.i < this.length)
/*      */     {
/* 1190 */       char c2 = this.i < this.length ? this.odbc_sql.charAt(this.i) : ' ';
/* 1191 */       this.currentChar = c2;
/*      */       
/* 1193 */       if (c2 > '')
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1199 */         if (Character.isLetterOrDigit(c2)) {
/* 1200 */           this.currentChar = 'X';
/*      */         } else {
/* 1202 */           this.currentChar = ' ';
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1207 */       switch (ODBC_ACTION[j][this.currentChar])
/*      */       {
/*      */       case NONE: 
/*      */         break;
/*      */       
/*      */ 
/*      */       case COPY: 
/* 1214 */         this.oracle_sql.append(c2);
/* 1215 */         break;
/*      */       
/*      */       case QUESTION: 
/* 1218 */         this.oracle_sql.append(nextArgument());
/* 1219 */         this.oracle_sql.append(' ');
/* 1220 */         break;
/*      */       
/*      */       case SAVE_DELIMITER: 
/* 1223 */         if (c2 == '[') { c1 = ']';
/* 1224 */         } else if (c2 == '{') { c1 = '}';
/* 1225 */         } else if (c2 == '<') { c1 = '>';
/* 1226 */         } else if (c2 == '(') c1 = ')'; else
/* 1227 */           c1 = c2;
/* 1228 */         this.oracle_sql.append(c2);
/* 1229 */         break;
/*      */       
/*      */       case LOOK_FOR_DELIMITER: 
/* 1232 */         if (c2 == c1)
/*      */         {
/*      */ 
/* 1235 */           j += 1;
/*      */         }
/* 1237 */         this.oracle_sql.append(c2);
/* 1238 */         break;
/*      */       
/*      */       case FUNCTION: 
/* 1241 */         handleFunction();
/* 1242 */         break;
/*      */       
/*      */       case CALL: 
/* 1245 */         handleCall();
/* 1246 */         break;
/*      */       
/*      */       case TIME: 
/* 1249 */         handleTime();
/* 1250 */         break;
/*      */       
/*      */       case TIMESTAMP: 
/* 1253 */         handleTimestamp();
/* 1254 */         break;
/*      */       
/*      */       case DATE: 
/* 1257 */         handleDate();
/* 1258 */         break;
/*      */       
/*      */       case ESCAPE: 
/* 1261 */         handleEscape();
/* 1262 */         break;
/*      */       
/*      */       case SCALAR_FUNCTION: 
/* 1265 */         handleScalarFunction();
/* 1266 */         break;
/*      */       
/*      */       case OUTER_JOIN: 
/* 1269 */         handleOuterJoin();
/* 1270 */         break;
/*      */       
/*      */ 
/*      */       case UNKNOWN_ESCAPE: 
/* 1274 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, Integer.valueOf(this.i));
/* 1275 */         localSQLException.fillInStackTrace();
/* 1276 */         throw localSQLException;
/*      */       
/*      */ 
/*      */       case END_ODBC_ESCAPE: 
/* 1280 */         if (paramParseMode == ParseMode.SCALAR)
/*      */         {
/* 1282 */           j = TRANSITION[j][this.currentChar];
/* 1283 */           return;
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */       case COMMA: 
/* 1289 */         if ((paramParseMode == ParseMode.LOCATE_1) && (k > 1)) {
/* 1290 */           this.oracle_sql.append(c2);
/*      */         } else {
/* 1292 */           if (paramParseMode == ParseMode.LOCATE_1)
/*      */           {
/* 1294 */             j = TRANSITION[j][this.currentChar];
/* 1295 */             return;
/*      */           }
/* 1297 */           if (paramParseMode != ParseMode.LOCATE_2)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/* 1302 */             this.oracle_sql.append(c2); }
/*      */         }
/* 1304 */         break;
/*      */       
/*      */       case OPEN_PAREN: 
/* 1307 */         if (paramParseMode == ParseMode.LOCATE_1) {
/* 1308 */           if (k > 0) this.oracle_sql.append(c2);
/* 1309 */           k++;
/*      */         }
/* 1311 */         else if (paramParseMode == ParseMode.LOCATE_2) {
/* 1312 */           k++;
/* 1313 */           this.oracle_sql.append(c2);
/*      */         }
/*      */         else
/*      */         {
/* 1317 */           this.oracle_sql.append(c2);
/*      */         }
/* 1319 */         break;
/*      */       
/*      */       case CLOSE_PAREN: 
/* 1322 */         if (paramParseMode == ParseMode.LOCATE_1) {
/* 1323 */           k--;
/* 1324 */           this.oracle_sql.append(c2);
/*      */         }
/* 1326 */         else if ((paramParseMode == ParseMode.LOCATE_2) && (k > 1)) {
/* 1327 */           k--;
/* 1328 */           this.oracle_sql.append(c2);
/*      */         } else {
/* 1330 */           if (paramParseMode == ParseMode.LOCATE_2) {
/* 1331 */             this.i += 1;
/*      */             
/* 1333 */             j = TRANSITION[j][this.currentChar];
/* 1334 */             return;
/*      */           }
/*      */           
/*      */ 
/* 1338 */           this.oracle_sql.append(c2);
/*      */         }
/* 1340 */         break;
/*      */       
/*      */       case BEGIN: 
/* 1343 */         this.first = false;
/* 1344 */         this.oracle_sql.append(c2);
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 1349 */       j = TRANSITION[j][this.currentChar];
/* 1350 */       this.i += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void handleFunction()
/*      */     throws SQLException
/*      */   {
/* 1359 */     boolean bool = this.first;
/* 1360 */     this.first = false;
/*      */     
/* 1362 */     if (bool) {
/* 1363 */       this.oracle_sql.append("BEGIN ");
/*      */     }
/* 1365 */     appendChar(this.oracle_sql, '?');
/* 1366 */     skipSpace();
/*      */     String str;
/* 1368 */     SQLException localSQLException; if (this.currentChar != '=')
/*      */     {
/* 1370 */       str = this.i + ". Expecting \"=\" got \"" + this.currentChar + "\"";
/*      */       
/*      */ 
/* 1373 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 33, str);
/* 1374 */       localSQLException.fillInStackTrace();
/* 1375 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1379 */     this.i += 1;
/*      */     
/* 1381 */     skipSpace();
/*      */     
/* 1383 */     if (!this.odbc_sql.startsWith("call", this.i))
/*      */     {
/* 1385 */       str = this.i + ". Expecting \"call\"";
/*      */       
/*      */ 
/* 1388 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 33, str);
/* 1389 */       localSQLException.fillInStackTrace();
/* 1390 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1394 */     this.i += 4;
/*      */     
/* 1396 */     this.oracle_sql.append(" := ");
/* 1397 */     skipSpace();
/* 1398 */     handleODBC(ParseMode.SCALAR);
/*      */     
/* 1400 */     if (bool) {
/* 1401 */       this.oracle_sql.append("; END;");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void handleCall()
/*      */     throws SQLException
/*      */   {
/* 1409 */     boolean bool = this.first;
/* 1410 */     this.first = false;
/*      */     
/* 1412 */     if (bool) {
/* 1413 */       this.oracle_sql.append("BEGIN ");
/*      */     }
/* 1415 */     skipSpace();
/* 1416 */     handleODBC(ParseMode.SCALAR);
/* 1417 */     skipSpace();
/*      */     
/* 1419 */     if (bool) {
/* 1420 */       this.oracle_sql.append("; END;");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void handleTimestamp()
/*      */     throws SQLException
/*      */   {
/* 1429 */     this.oracle_sql.append("TO_TIMESTAMP (");
/* 1430 */     skipSpace();
/* 1431 */     handleODBC(ParseMode.SCALAR);
/* 1432 */     this.oracle_sql.append(", 'YYYY-MM-DD HH24:MI:SS.FF')");
/*      */   }
/*      */   
/*      */ 
/*      */   void handleTime()
/*      */     throws SQLException
/*      */   {
/* 1439 */     this.oracle_sql.append("TO_DATE (");
/* 1440 */     skipSpace();
/* 1441 */     handleODBC(ParseMode.SCALAR);
/* 1442 */     this.oracle_sql.append(", 'HH24:MI:SS')");
/*      */   }
/*      */   
/*      */ 
/*      */   void handleDate()
/*      */     throws SQLException
/*      */   {
/* 1449 */     this.oracle_sql.append("TO_DATE (");
/* 1450 */     skipSpace();
/* 1451 */     handleODBC(ParseMode.SCALAR);
/* 1452 */     this.oracle_sql.append(", 'YYYY-MM-DD')");
/*      */   }
/*      */   
/*      */ 
/*      */   void handleEscape()
/*      */     throws SQLException
/*      */   {
/* 1459 */     this.oracle_sql.append("ESCAPE ");
/* 1460 */     skipSpace();
/* 1461 */     handleODBC(ParseMode.SCALAR);
/*      */   }
/*      */   
/*      */ 
/*      */   void handleScalarFunction()
/*      */     throws SQLException
/*      */   {
/* 1468 */     this.token_buffer.delete(0, this.token_buffer.length());
/*      */     
/* 1470 */     this.i += 1;
/*      */     
/* 1472 */     skipSpace();
/*      */     
/*      */ 
/*      */ 
/* 1476 */     while ((this.i < this.length) && ((Character.isJavaLetterOrDigit(this.currentChar = this.odbc_sql.charAt(this.i))) || (this.currentChar == '?')))
/*      */     {
/*      */ 
/* 1479 */       this.token_buffer.append(this.currentChar);
/*      */       
/* 1481 */       this.i += 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1487 */     String str = this.token_buffer.substring(0, this.token_buffer.length()).toUpperCase().intern();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1495 */     if (str == "ABS") {
/* 1496 */       usingFunctionName(str);
/* 1497 */     } else if (str == "ACOS") {
/* 1498 */       usingFunctionName(str);
/* 1499 */     } else if (str == "ASIN") {
/* 1500 */       usingFunctionName(str);
/* 1501 */     } else if (str == "ATAN") {
/* 1502 */       usingFunctionName(str);
/* 1503 */     } else if (str == "ATAN2") {
/* 1504 */       usingFunctionName(str);
/* 1505 */     } else if (str == "CEILING") {
/* 1506 */       usingFunctionName("CEIL");
/* 1507 */     } else if (str == "COS") {
/* 1508 */       usingFunctionName(str); } else { Object localObject;
/* 1509 */       if (str == "COT")
/*      */       {
/* 1511 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1512 */         ((SQLException)localObject).fillInStackTrace();
/* 1513 */         throw ((Throwable)localObject);
/*      */       }
/* 1515 */       if (str == "DEGREES")
/*      */       {
/* 1517 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1518 */         ((SQLException)localObject).fillInStackTrace();
/* 1519 */         throw ((Throwable)localObject);
/*      */       }
/* 1521 */       if (str == "EXP") {
/* 1522 */         usingFunctionName(str);
/* 1523 */       } else if (str == "FLOOR") {
/* 1524 */         usingFunctionName(str);
/* 1525 */       } else if (str == "LOG") {
/* 1526 */         usingFunctionName("LN");
/* 1527 */       } else if (str == "LOG10") {
/* 1528 */         replacingFunctionPrefix("LOG ( 10, ");
/* 1529 */       } else if (str == "MOD") {
/* 1530 */         usingFunctionName(str);
/* 1531 */       } else if (str == "PI") {
/* 1532 */         replacingFunctionPrefix("( 3.141592653589793238462643383279502884197169399375 ");
/* 1533 */       } else if (str == "POWER") {
/* 1534 */         usingFunctionName(str);
/* 1535 */       } else { if (str == "RADIANS")
/*      */         {
/* 1537 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1538 */           ((SQLException)localObject).fillInStackTrace();
/* 1539 */           throw ((Throwable)localObject);
/*      */         }
/* 1541 */         if (str == "RAND")
/*      */         {
/* 1543 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1544 */           ((SQLException)localObject).fillInStackTrace();
/* 1545 */           throw ((Throwable)localObject);
/*      */         }
/* 1547 */         if (str == "ROUND") {
/* 1548 */           usingFunctionName(str);
/* 1549 */         } else if (str == "SIGN") {
/* 1550 */           usingFunctionName(str);
/* 1551 */         } else if (str == "SIN") {
/* 1552 */           usingFunctionName(str);
/* 1553 */         } else if (str == "SQRT") {
/* 1554 */           usingFunctionName(str);
/* 1555 */         } else if (str == "TAN") {
/* 1556 */           usingFunctionName(str);
/* 1557 */         } else if (str == "TRUNCATE") {
/* 1558 */           usingFunctionName("TRUNC");
/*      */ 
/*      */         }
/* 1561 */         else if (str == "ASCII") {
/* 1562 */           usingFunctionName(str);
/* 1563 */         } else if (str == "CHAR") {
/* 1564 */           usingFunctionName("CHR");
/*      */ 
/*      */ 
/*      */         }
/* 1568 */         else if (str == "CHAR_LENGTH") {
/* 1569 */           usingFunctionName("LENGTH");
/* 1570 */         } else if (str == "CHARACTER_LENGTH") {
/* 1571 */           usingFunctionName("LENGTH");
/*      */         }
/* 1573 */         else if (str == "CONCAT") {
/* 1574 */           usingFunctionName(str);
/* 1575 */         } else { if (str == "DIFFERENCE")
/*      */           {
/* 1577 */             localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1578 */             ((SQLException)localObject).fillInStackTrace();
/* 1579 */             throw ((Throwable)localObject);
/*      */           }
/* 1581 */           if (str == "INSERT")
/*      */           {
/* 1583 */             localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1584 */             ((SQLException)localObject).fillInStackTrace();
/* 1585 */             throw ((Throwable)localObject);
/*      */           }
/* 1587 */           if (str == "LCASE") {
/* 1588 */             usingFunctionName("LOWER");
/* 1589 */           } else { if (str == "LEFT")
/*      */             {
/* 1591 */               localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1592 */               ((SQLException)localObject).fillInStackTrace();
/* 1593 */               throw ((Throwable)localObject);
/*      */             }
/* 1595 */             if (str == "LENGTH") {
/* 1596 */               usingFunctionName(str);
/* 1597 */             } else if (str == "LOCATE")
/*      */             {
/*      */ 
/*      */ 
/* 1601 */               localObject = this.oracle_sql;
/* 1602 */               this.oracle_sql = new StringBuffer();
/* 1603 */               handleODBC(ParseMode.LOCATE_1);
/* 1604 */               StringBuffer localStringBuffer = this.oracle_sql;
/* 1605 */               this.oracle_sql = ((StringBuffer)localObject);
/* 1606 */               this.oracle_sql.append("INSTR(");
/* 1607 */               handleODBC(ParseMode.LOCATE_2);
/* 1608 */               this.oracle_sql.append(',');
/* 1609 */               this.oracle_sql.append(localStringBuffer);
/* 1610 */               this.oracle_sql.append(')');
/* 1611 */               handleODBC(ParseMode.SCALAR);
/*      */             }
/* 1613 */             else if (str == "LTRIM") {
/* 1614 */               usingFunctionName(str);
/*      */ 
/*      */ 
/*      */             }
/* 1618 */             else if (str == "OCTET_LENGTH") {
/* 1619 */               usingFunctionName("LENGTHB");
/* 1620 */             } else { if (str == "POSITION")
/*      */               {
/* 1622 */                 localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1623 */                 ((SQLException)localObject).fillInStackTrace();
/* 1624 */                 throw ((Throwable)localObject);
/*      */               }
/*      */               
/* 1627 */               if (str == "REPEAT")
/*      */               {
/* 1629 */                 localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1630 */                 ((SQLException)localObject).fillInStackTrace();
/* 1631 */                 throw ((Throwable)localObject);
/*      */               }
/* 1633 */               if (str == "REPLACE") {
/* 1634 */                 usingFunctionName(str);
/* 1635 */               } else { if (str == "RIGHT")
/*      */                 {
/* 1637 */                   localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1638 */                   ((SQLException)localObject).fillInStackTrace();
/* 1639 */                   throw ((Throwable)localObject);
/*      */                 }
/* 1641 */                 if (str == "RTRIM") {
/* 1642 */                   usingFunctionName(str);
/* 1643 */                 } else if (str == "SOUNDEX") {
/* 1644 */                   usingFunctionName(str);
/* 1645 */                 } else { if (str == "SPACE")
/*      */                   {
/* 1647 */                     localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1648 */                     ((SQLException)localObject).fillInStackTrace();
/* 1649 */                     throw ((Throwable)localObject);
/*      */                   }
/* 1651 */                   if (str == "SUBSTRING") {
/* 1652 */                     usingFunctionName("SUBSTR");
/* 1653 */                   } else if (str == "UCASE") {
/* 1654 */                     usingFunctionName("UPPER");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   }
/* 1661 */                   else if (str == "CURRENT_DATE") {
/* 1662 */                     replacingFunctionPrefix("(CURRENT_DATE");
/* 1663 */                   } else { if (str == "CURRENT_TIME")
/*      */                     {
/* 1665 */                       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1666 */                       ((SQLException)localObject).fillInStackTrace();
/* 1667 */                       throw ((Throwable)localObject);
/*      */                     }
/* 1669 */                     if (str == "CURRENT_TIMESTAMP") {
/* 1670 */                       replacingFunctionPrefix("(CURRENT_TIMESTAMP");
/*      */                     }
/* 1672 */                     else if (str == "CURDATE") {
/* 1673 */                       replacingFunctionPrefix("(CURRENT_DATE");
/* 1674 */                     } else if (str == "CURTIME") {
/* 1675 */                       replacingFunctionPrefix("(CURRENT_TIMESTAMP");
/* 1676 */                     } else { if (str == "DAYNAME")
/*      */                       {
/* 1678 */                         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1679 */                         ((SQLException)localObject).fillInStackTrace();
/* 1680 */                         throw ((Throwable)localObject);
/*      */                       }
/* 1682 */                       if (str == "DAYOFMONTH") {
/* 1683 */                         replacingFunctionPrefix("EXTRACT ( DAY FROM ");
/* 1684 */                       } else { if (str == "DAYOFWEEK")
/*      */                         {
/* 1686 */                           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1687 */                           ((SQLException)localObject).fillInStackTrace();
/* 1688 */                           throw ((Throwable)localObject);
/*      */                         }
/* 1690 */                         if (str == "DAYOFYEAR")
/*      */                         {
/* 1692 */                           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1693 */                           ((SQLException)localObject).fillInStackTrace();
/* 1694 */                           throw ((Throwable)localObject);
/*      */                         }
/*      */                         
/*      */ 
/*      */ 
/* 1699 */                         if (str == "EXTRACT") {
/* 1700 */                           usingFunctionName("EXTRACT");
/*      */                         }
/* 1702 */                         else if (str == "HOUR") {
/* 1703 */                           replacingFunctionPrefix("EXTRACT ( HOUR FROM ");
/* 1704 */                         } else if (str == "MINUTE") {
/* 1705 */                           replacingFunctionPrefix("EXTRACT ( MINUTE FROM ");
/* 1706 */                         } else if (str == "MONTH") {
/* 1707 */                           replacingFunctionPrefix("EXTRACT ( MONTH FROM ");
/* 1708 */                         } else { if (str == "MONTHNAME")
/*      */                           {
/* 1710 */                             localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1711 */                             ((SQLException)localObject).fillInStackTrace();
/* 1712 */                             throw ((Throwable)localObject);
/*      */                           }
/* 1714 */                           if (str == "NOW") {
/* 1715 */                             replacingFunctionPrefix("(CURRENT_TIMESTAMP");
/* 1716 */                           } else { if (str == "QUARTER")
/*      */                             {
/* 1718 */                               localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1719 */                               ((SQLException)localObject).fillInStackTrace();
/* 1720 */                               throw ((Throwable)localObject);
/*      */                             }
/* 1722 */                             if (str == "SECOND") {
/* 1723 */                               replacingFunctionPrefix("EXTRACT ( SECOND FROM ");
/* 1724 */                             } else { if (str == "TIMESTAMPADD")
/*      */                               {
/* 1726 */                                 localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1727 */                                 ((SQLException)localObject).fillInStackTrace();
/* 1728 */                                 throw ((Throwable)localObject);
/*      */                               }
/* 1730 */                               if (str == "TIMESTAMPDIFF")
/*      */                               {
/* 1732 */                                 localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1733 */                                 ((SQLException)localObject).fillInStackTrace();
/* 1734 */                                 throw ((Throwable)localObject);
/*      */                               }
/* 1736 */                               if (str == "WEEK")
/*      */                               {
/* 1738 */                                 localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1739 */                                 ((SQLException)localObject).fillInStackTrace();
/* 1740 */                                 throw ((Throwable)localObject);
/*      */                               }
/* 1742 */                               if (str == "YEAR") {
/* 1743 */                                 replacingFunctionPrefix("EXTRACT ( YEAR FROM ");
/*      */                               }
/*      */                               else {
/* 1746 */                                 if (str == "DATABASE")
/*      */                                 {
/* 1748 */                                   localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1749 */                                   ((SQLException)localObject).fillInStackTrace();
/* 1750 */                                   throw ((Throwable)localObject);
/*      */                                 }
/* 1752 */                                 if (str == "IFNULL") {
/* 1753 */                                   usingFunctionName("NVL");
/* 1754 */                                 } else if (str == "USER") {
/* 1755 */                                   replacingFunctionPrefix("(USER");
/*      */                                 }
/*      */                                 else {
/* 1758 */                                   if (str == "CONVERT")
/*      */                                   {
/*      */ 
/* 1761 */                                     localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1762 */                                     ((SQLException)localObject).fillInStackTrace();
/* 1763 */                                     throw ((Throwable)localObject);
/*      */                                   }
/*      */                                   
/*      */ 
/*      */ 
/*      */ 
/* 1769 */                                   localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 34, str);
/* 1770 */                                   ((SQLException)localObject).fillInStackTrace();
/* 1771 */                                   throw ((Throwable)localObject);
/*      */                                 }
/*      */                               }
/*      */                             }
/*      */                           }
/*      */                         }
/*      */                       } } } } } } } } } } }
/*      */   
/* 1779 */   void usingFunctionName(String paramString) throws SQLException { this.oracle_sql.append(paramString);
/* 1780 */     skipSpace();
/* 1781 */     handleODBC(ParseMode.SCALAR);
/*      */   }
/*      */   
/*      */ 
/*      */   void replacingFunctionPrefix(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1788 */     skipSpace();
/*      */     
/*      */ 
/* 1791 */     if ((this.i < this.length) && ((this.currentChar = this.odbc_sql.charAt(this.i)) == '(')) {
/* 1792 */       this.i += 1;
/*      */     }
/*      */     else {
/* 1795 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 33);
/* 1796 */       localSQLException.fillInStackTrace();
/* 1797 */       throw localSQLException;
/*      */     }
/*      */     
/* 1800 */     this.oracle_sql.append(paramString);
/* 1801 */     skipSpace();
/* 1802 */     handleODBC(ParseMode.SCALAR);
/*      */   }
/*      */   
/*      */ 
/*      */   void handleOuterJoin()
/*      */     throws SQLException
/*      */   {
/* 1809 */     this.oracle_sql.append(" ( ");
/* 1810 */     skipSpace();
/* 1811 */     handleODBC(ParseMode.SCALAR);
/* 1812 */     this.oracle_sql.append(" ) ");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   String nextArgument()
/*      */   {
/* 1819 */     String str = ":" + this.current_argument;
/*      */     
/* 1821 */     this.current_argument += 1;
/*      */     
/* 1823 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void appendChar(StringBuffer paramStringBuffer, char paramChar)
/*      */   {
/* 1830 */     if (paramChar == '?') {
/* 1831 */       paramStringBuffer.append(nextArgument());
/*      */     } else {
/* 1833 */       paramStringBuffer.append(paramChar);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void skipSpace()
/*      */   {
/* 1840 */     while ((this.i < this.length) && ((this.currentChar = this.odbc_sql.charAt(this.i)) == ' ')) {
/* 1841 */       this.i += 1;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String generateParameterName()
/*      */   {
/* 1852 */     if ((this.parameterCount == 0) || (this.parameterList == null))
/*      */     {
/* 1854 */       return "rowid" + this.paramSuffix++;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1860 */     String str = "rowid" + this.paramSuffix++;
/* 1861 */     for (int j = 0;; j++) { if (j >= this.parameterList.length)
/*      */         return str;
/* 1863 */       if (str.equals(this.parameterList[j])) break;
/*      */     }
/* 1865 */     return str;
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
/*      */   static boolean isValidPlsqlWarning(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1885 */     return paramString.matches("('\\s*([a-zA-Z0-9:,\\(\\)\\s])*')\\s*(,\\s*'([a-zA-Z0-9:,\\(\\)\\s])*')*");
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
/*      */   public static boolean isValidObjectName(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1903 */     if (paramString.matches("([a-zA-Z]{1}\\w*(\\$|\\#)*\\w*)|(\".*)"))
/*      */     {
/* 1905 */       return true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1912 */     char[] arrayOfChar = paramString.toCharArray();
/* 1913 */     int j = arrayOfChar.length;
/*      */     
/*      */ 
/* 1916 */     if (!Character.isLetter(arrayOfChar[0]))
/*      */     {
/* 1918 */       return false;
/*      */     }
/*      */     
/* 1921 */     for (int k = 1; k < j; k++)
/*      */     {
/* 1923 */       if ((!Character.isLetterOrDigit(arrayOfChar[k])) && (arrayOfChar[k] != '$') && (arrayOfChar[k] != '#') && (arrayOfChar[k] != '_'))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/* 1928 */         return false;
/*      */       }
/*      */     }
/*      */     
/* 1932 */     return true;
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
/*      */ 
/*      */ 
/*      */   public static void main(String[] paramArrayOfString)
/*      */   {
/* 1957 */     if (paramArrayOfString.length < 2) {
/* 1958 */       System.err.println("ERROR: incorrect usage. OracleSql (-transition <file> | <process_escapes> <convert_nchars> { <sql> } )");
/* 1959 */       return;
/*      */     }
/* 1961 */     if (paramArrayOfString[0].equals("-dump")) {
/* 1962 */       dumpTransitionMatrix(paramArrayOfString[1]);
/* 1963 */       return;
/*      */     }
/* 1965 */     boolean bool1 = paramArrayOfString[0].equals("true");
/* 1966 */     boolean bool2 = paramArrayOfString[1].equals("true");
/*      */     
/*      */     String[] arrayOfString1;
/* 1969 */     if (paramArrayOfString.length > 2) {
/* 1970 */       arrayOfString1 = new String[paramArrayOfString.length - 2];
/* 1971 */       System.arraycopy(paramArrayOfString, 2, arrayOfString1, 0, arrayOfString1.length);
/*      */     }
/*      */     else {
/* 1974 */       arrayOfString1 = new String[] { "select ? from dual", "insert into dual values (?)", "delete from dual", "update dual set dummy = ?", "merge tab into dual", " select ? from dual where ? = ?", "select ?from dual where?=?for update", "select '?', n'?', q'???', q'{?}', q'{cat's}' from dual", "select'?',n'?',q'???',q'{?}',q'{cat's}'from dual", "select--line\n? from dual", "select --line\n? from dual", "--line\nselect ? from dual", " --line\nselect ? from dual", "--line\n select ? from dual", "begin proc4in4out (:x1, :x2, :x3, :x4); end;", "{CALL tkpjpn01(:pin, :pinout, :pout)}", "select :NumberBindVar as the_number from dual", "select {fn locate(bob(carol(),ted(alice,sue)), 'xfy')} from dual", "CREATE USER vijay6 IDENTIFIED BY \"vjay?\"", "ALTER SESSION SET TIME", "SELECT ename FROM emp WHERE hiredate BETWEEN {ts'1980-12-17'} AND {ts '1981-09-28'} " };
/*      */     }
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
/* 2003 */     for (String str1 : arrayOfString1) {
/*      */       try {
/* 2005 */         System.out.println("\n\n-----------------------");
/* 2006 */         System.out.println(str1);
/* 2007 */         System.out.println();
/* 2008 */         OracleSql localOracleSql = new OracleSql(null);
/*      */         
/* 2010 */         localOracleSql.initialize(str1);
/* 2011 */         String str2 = localOracleSql.getSql(bool1, bool2);
/*      */         
/* 2013 */         System.out.println(localOracleSql.sqlKind + ", " + localOracleSql.parameterCount);
/*      */         
/* 2015 */         String[] arrayOfString3 = localOracleSql.getParameterList();
/*      */         int m;
/* 2017 */         if (arrayOfString3 == EMPTY_LIST) {
/* 2018 */           System.out.println("parameterList is empty");
/*      */         } else {
/* 2020 */           for (m = 0; m < arrayOfString3.length; m++)
/* 2021 */             System.out.println("parameterList[" + m + "] = " + arrayOfString3[m]);
/*      */         }
/* 2023 */         if (localOracleSql.getSqlKind().isDML()) {
/* 2024 */           m = localOracleSql.getReturnParameterCount();
/* 2025 */           if (m == -1) System.out.println("no return parameters"); else {
/* 2026 */             System.out.println(m + " return parameters");
/*      */           }
/*      */         }
/* 2029 */         if (localOracleSql.lastNcharLiteralLocation == 2) { System.out.println("No NCHAR literals");
/*      */         } else {
/* 2031 */           System.out.println("NCHAR Literals");
/* 2032 */           for (m = 1; m < localOracleSql.lastNcharLiteralLocation - 1;)
/* 2033 */             System.out.println(str2.substring(localOracleSql.ncharLiteralLocation[(m++)], localOracleSql.ncharLiteralLocation[(m++)]));
/*      */         }
/* 2035 */         System.out.println("Keywords");
/* 2036 */         if (localOracleSql.selectEndIndex == -1) System.out.println("no select"); else
/* 2037 */           System.out.println("'" + str2.substring(localOracleSql.selectEndIndex - 6, localOracleSql.selectEndIndex) + "'");
/* 2038 */         if (localOracleSql.orderByStartIndex == -1) System.out.println("no order by"); else
/* 2039 */           System.out.println("'" + str2.substring(localOracleSql.orderByStartIndex, localOracleSql.orderByEndIndex) + "'");
/* 2040 */         if (localOracleSql.whereStartIndex == -1) System.out.println("no where"); else
/* 2041 */           System.out.println("'" + str2.substring(localOracleSql.whereStartIndex, localOracleSql.whereEndIndex) + "'");
/* 2042 */         if (localOracleSql.forUpdateStartIndex == -1) System.out.println("no for update"); else {
/* 2043 */           System.out.println("'" + str2.substring(localOracleSql.forUpdateStartIndex, localOracleSql.forUpdateEndIndex) + "'");
/*      */         }
/* 2045 */         System.out.println("isPlsqlOrCall(): " + localOracleSql.getSqlKind().isPlsqlOrCall());
/* 2046 */         System.out.println("isDML(): " + localOracleSql.getSqlKind().isDML());
/* 2047 */         System.out.println("isSELECT(): " + localOracleSql.getSqlKind().isSELECT());
/* 2048 */         System.out.println("isOTHER(): " + localOracleSql.getSqlKind().isOTHER());
/* 2049 */         System.out.println("\"" + str2 + "\"");
/* 2050 */         System.out.println("\"" + localOracleSql.getRevisedSql() + "\"");
/*      */       }
/*      */       catch (Exception localException) {
/* 2053 */         System.out.println(localException);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private static final void dumpTransitionMatrix(String paramString) {
/*      */     try {
/* 2060 */       PrintWriter localPrintWriter = new PrintWriter(paramString);
/* 2061 */       localPrintWriter.print(",");
/* 2062 */       for (int j = 0; j < 128; j++) localPrintWriter.print("'" + (j < 32 ? "0x" + Integer.toHexString(j) : Character.toString((char)j)) + (j < 127 ? "'," : "'"));
/* 2063 */       localPrintWriter.println();
/* 2064 */       int[][] arrayOfInt = OracleSqlReadOnly.TRANSITION;
/* 2065 */       String[] arrayOfString = OracleSqlReadOnly.PARSER_STATE_NAME;
/* 2066 */       for (int k = 0; k < TRANSITION.length; k++) {
/* 2067 */         localPrintWriter.print(arrayOfString[k] + ",");
/* 2068 */         for (int m = 0; m < arrayOfInt[k].length; m++) localPrintWriter.print(arrayOfString[arrayOfInt[k][m]] + (m < 127 ? "," : ""));
/* 2069 */         localPrintWriter.println();
/*      */       }
/* 2071 */       localPrintWriter.close();
/*      */     }
/*      */     catch (Throwable localThrowable) {
/* 2074 */       System.err.println(localThrowable);
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 2091 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int getReturnParameterCount()
/*      */     throws SQLException
/*      */   {
/* 2104 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) {
/* 2105 */       computeBasicInfo(this.parameterSql);
/*      */     }
/*      */     
/* 2108 */     if (!this.sqlKind.isDML()) return -1;
/* 2109 */     return this.returningIntoParameterCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private int getSubstrPos(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2117 */     int j = -1;
/* 2118 */     int k = paramString1.indexOf(paramString2);
/*      */     
/* 2120 */     if ((k >= 1) && (Character.isWhitespace(paramString1.charAt(k - 1))))
/*      */     {
/*      */ 
/* 2123 */       int m = k + paramString2.length();
/*      */       
/* 2125 */       if ((m < paramString1.length()) && (Character.isWhitespace(paramString1.charAt(m))))
/*      */       {
/*      */ 
/* 2128 */         j = k;
/*      */       }
/*      */     }
/* 2131 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2137 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleSql.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */