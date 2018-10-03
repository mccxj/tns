package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class AutoKeyInfo
  extends OracleResultSetMetaData
{
  String originalSql;
  String newSql;
  String tableName;
/*  27 */   OracleStatement.SqlKind sqlKind = OracleStatement.SqlKind.UNINITIALIZED;
  
  int sqlParserParamCount;
  
  String[] sqlParserParamList;
  
  boolean useNamedParameter;
  
  int current_argument;
  
  String[] columnNames;
  
  int[] columnIndexes;
  
  int numColumns;
  
  String[] tableColumnNames;
  
  int[] tableColumnTypes;
  
  int[] tableMaxLengths;
  boolean[] tableNullables;
  short[] tableFormOfUses;
  int[] tablePrecisions;
  int[] tableScales;
  String[] tableTypeNames;
  int autoKeyType;
  static final int KEYFLAG = 0;
  static final int COLUMNAME = 1;
  static final int COLUMNINDEX = 2;
  static final char QMARK = '?';
  int[] returnTypes;
  Accessor[] returnAccessors;
  
  AutoKeyInfo(String paramString)
  {
/*  63 */     this.originalSql = paramString;
/*  64 */     this.autoKeyType = 0;
  }
  
  AutoKeyInfo(String paramString, String[] paramArrayOfString)
  {
/*  71 */     this.originalSql = paramString;
/*  72 */     this.columnNames = paramArrayOfString;
/*  73 */     this.autoKeyType = 1;
  }
  
  AutoKeyInfo(String paramString, int[] paramArrayOfInt)
  {
/*  80 */     this.originalSql = paramString;
/*  81 */     this.columnIndexes = paramArrayOfInt;
/*  82 */     this.autoKeyType = 2;
  }
  
  private void parseSql() throws SQLException
  {
/*  87 */     if (this.originalSql == null)
    {
/*  89 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  90 */       ((SQLException)localObject).fillInStackTrace();
/*  91 */       throw ((Throwable)localObject);
    }
    
/*  94 */     Object localObject = (OracleSql)SQL_PARSER.get();
/*  95 */     ((OracleSql)localObject).initialize(this.originalSql);
    
/* 109 */     this.sqlKind = ((OracleSql)localObject).getSqlKind();
    
/* 112 */     if (this.sqlKind == OracleStatement.SqlKind.INSERT)
    {
/* 114 */       this.sqlParserParamCount = ((OracleSql)localObject).getParameterCount();
/* 115 */       this.sqlParserParamList = ((OracleSql)localObject).getParameterList();
      
/* 117 */       if (this.sqlParserParamList == OracleSql.EMPTY_LIST) {
/* 118 */         this.useNamedParameter = false;
      }
      else {
/* 121 */         this.useNamedParameter = true;
        
/* 123 */         this.current_argument = this.sqlParserParamCount;
      }
    }
  }
  
  private String generateUniqueNamedParameter()
  {
    int i;
    String str;
    do
    {
/* 135 */       i = 0;
/* 136 */       str = Integer.toString(++this.current_argument).intern();
      
/* 138 */       for (int j = 0; j < this.sqlParserParamCount; j++)
      {
/* 140 */         if (this.sqlParserParamList[j] == str)
        {
/* 142 */           i = 1;
/* 143 */           break;
        }
      }
/* 146 */     } while (i != 0);
    
/* 148 */     return ":" + str;
  }
  
  String getNewSql()
    throws SQLException
  {
    try
    {
/* 160 */       if (this.newSql != null) { return this.newSql;
      }
/* 162 */       if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) { parseSql();
      }
/* 164 */       switch (this.autoKeyType)
      {
      case 0: 
/* 167 */         this.newSql = (this.originalSql + " RETURNING ROWID INTO " + (this.useNamedParameter ? generateUniqueNamedParameter() : Character.valueOf('?')));
        
/* 169 */         this.returnTypes = new int[1];
/* 170 */         this.returnTypes[0] = 104;
/* 171 */         break;
      case 1: 
/* 173 */         getNewSqlByColumnName();
/* 174 */         break;
      case 2: 
/* 176 */         getNewSqlByColumnIndexes();
      }
      
      
/* 181 */       this.sqlKind = OracleStatement.SqlKind.UNINITIALIZED;
/* 182 */       this.sqlParserParamList = null;
/* 183 */       return this.newSql;
    }
    catch (Exception localException)
    {
/* 188 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localException);
/* 189 */       localSQLException.fillInStackTrace();
/* 190 */       throw localSQLException;
    }
  }
  
  private String getNewSqlByColumnName()
    throws SQLException
  {
/* 198 */     this.returnTypes = new int[this.columnNames.length];
    
/* 201 */     this.columnIndexes = new int[this.columnNames.length];
    
/* 203 */     StringBuffer localStringBuffer = new StringBuffer(this.originalSql);
/* 204 */     localStringBuffer.append(" RETURNING ");
    
/* 207 */     for (int j = 0; j < this.columnNames.length; j++)
    {
/* 209 */       int i = getReturnParamTypeCode(j, this.columnNames[j], this.columnIndexes);
/* 210 */       this.returnTypes[j] = i;
      
/* 212 */       localStringBuffer.append(this.columnNames[j]);
      
/* 214 */       if (j < this.columnNames.length - 1) { localStringBuffer.append(", ");
      }
    }
/* 217 */     localStringBuffer.append(" INTO ");
    
/* 219 */     for (j = 0; j < this.columnNames.length - 1; j++)
    {
/* 221 */       localStringBuffer.append((this.useNamedParameter ? generateUniqueNamedParameter() : Character.valueOf('?')) + ", ");
    }
    
/* 224 */     localStringBuffer.append(this.useNamedParameter ? generateUniqueNamedParameter() : Character.valueOf('?'));
    
/* 226 */     this.newSql = new String(localStringBuffer);
/* 227 */     return this.newSql;
  }
  
  private String getNewSqlByColumnIndexes() throws SQLException
  {
/* 232 */     this.returnTypes = new int[this.columnIndexes.length];
    
/* 234 */     StringBuffer localStringBuffer = new StringBuffer(this.originalSql);
/* 235 */     localStringBuffer.append(" RETURNING ");
    
/* 240 */     for (int j = 0; j < this.columnIndexes.length; j++)
    {
/* 242 */       int k = this.columnIndexes[j] - 1;
/* 243 */       if ((k < 0) || (k > this.tableColumnNames.length))
      {
/* 246 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 247 */         localSQLException.fillInStackTrace();
/* 248 */         throw localSQLException;
      }
      
/* 252 */       int i = this.tableColumnTypes[k];
/* 253 */       String str = this.tableColumnNames[k];
/* 254 */       this.returnTypes[j] = i;
      
/* 256 */       localStringBuffer.append(str);
      
/* 258 */       if (j < this.columnIndexes.length - 1) { localStringBuffer.append(", ");
      }
    }
/* 261 */     localStringBuffer.append(" INTO ");
    
/* 263 */     for (j = 0; j < this.columnIndexes.length - 1; j++)
    {
/* 265 */       localStringBuffer.append((this.useNamedParameter ? generateUniqueNamedParameter() : Character.valueOf('?')) + ", ");
    }
    
/* 268 */     localStringBuffer.append(this.useNamedParameter ? generateUniqueNamedParameter() : Character.valueOf('?'));
    
/* 270 */     this.newSql = new String(localStringBuffer);
/* 271 */     return this.newSql;
  }
  
  private final int getReturnParamTypeCode(int paramInt, String paramString, int[] paramArrayOfInt)
    throws SQLException
  {
/* 279 */     for (int i = 0; i < this.tableColumnNames.length; i++)
    {
/* 281 */       if (paramString.equalsIgnoreCase(this.tableColumnNames[i]))
      {
/* 283 */         paramArrayOfInt[paramInt] = (i + 1);
/* 284 */         return this.tableColumnTypes[i];
      }
    }
    
/* 290 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 291 */     localSQLException.fillInStackTrace();
/* 292 */     throw localSQLException;
  }
  
/* 297 */   private static final ThreadLocal<OracleSql> SQL_PARSER = new ThreadLocal() {
    protected OracleSql initialValue() {
/* 299 */       return new OracleSql(null);
    }
  };
  
  final boolean isInsertSqlStmt() throws SQLException
  {
/* 305 */     if (this.sqlKind == OracleStatement.SqlKind.UNINITIALIZED) {
/* 306 */       parseSql();
    }
/* 308 */     return this.sqlKind == OracleStatement.SqlKind.INSERT;
  }
  
  String getTableName() throws SQLException
  {
/* 313 */     if (this.tableName != null) { return this.tableName;
    }
/* 315 */     String str = this.originalSql.trim().toUpperCase();
    
/* 317 */     int i = str.indexOf("INSERT");
/* 318 */     i = str.indexOf("INTO", i);
    
/* 320 */     if (i < 0)
    {
/* 322 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 323 */       localSQLException1.fillInStackTrace();
/* 324 */       throw localSQLException1;
    }
    
/* 327 */     int j = str.length();
/* 328 */     int k = i + 5;
    
/* 330 */     while ((k < j) && (str.charAt(k) == ' ')) { k++;
    }
/* 332 */     if (k >= j)
    {
/* 334 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 335 */       localSQLException2.fillInStackTrace();
/* 336 */       throw localSQLException2;
    }
    
/* 339 */     int m = k + 1;
    
/* 342 */     while ((m < j) && (str.charAt(m) != ' ') && (str.charAt(m) != '(')) { m++;
    }
/* 344 */     if (k == m - 1)
    {
/* 346 */       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 347 */       localSQLException3.fillInStackTrace();
/* 348 */       throw localSQLException3;
    }
    
/* 351 */     this.tableName = str.substring(k, m);
    
/* 353 */     return this.tableName;
  }
  
  void allocateSpaceForDescribedData(int paramInt) throws SQLException
  {
/* 358 */     this.numColumns = paramInt;
    
/* 360 */     this.tableColumnNames = new String[paramInt];
/* 361 */     this.tableColumnTypes = new int[paramInt];
/* 362 */     this.tableMaxLengths = new int[paramInt];
/* 363 */     this.tableNullables = new boolean[paramInt];
/* 364 */     this.tableFormOfUses = new short[paramInt];
/* 365 */     this.tablePrecisions = new int[paramInt];
/* 366 */     this.tableScales = new int[paramInt];
/* 367 */     this.tableTypeNames = new String[paramInt];
  }
  
  void fillDescribedData(int paramInt1, String paramString1, int paramInt2, int paramInt3, boolean paramBoolean, short paramShort, int paramInt4, int paramInt5, String paramString2)
    throws SQLException
  {
/* 375 */     this.tableColumnNames[paramInt1] = paramString1;
/* 376 */     this.tableColumnTypes[paramInt1] = paramInt2;
/* 377 */     this.tableMaxLengths[paramInt1] = paramInt3;
/* 378 */     this.tableNullables[paramInt1] = paramBoolean;
/* 379 */     this.tableFormOfUses[paramInt1] = paramShort;
/* 380 */     this.tablePrecisions[paramInt1] = paramInt4;
/* 381 */     this.tableScales[paramInt1] = paramInt5;
/* 382 */     this.tableTypeNames[paramInt1] = paramString2;
  }
  
  void initMetaData(OracleReturnResultSet paramOracleReturnResultSet) throws SQLException
  {
/* 387 */     if (this.returnAccessors != null) { return;
    }
/* 389 */     this.returnAccessors = paramOracleReturnResultSet.returnAccessors;
    
/* 392 */     switch (this.autoKeyType)
    {
    case 0: 
/* 395 */       initMetaDataKeyFlag();
/* 396 */       break;
    case 1: 
    case 2: 
/* 399 */       initMetaDataColumnIndexes();
    }
    
  }
  
  void initMetaDataKeyFlag()
    throws SQLException
  {
/* 407 */     this.returnAccessors[0].columnName = "ROWID";
/* 408 */     this.returnAccessors[0].describeType = 104;
/* 409 */     this.returnAccessors[0].describeMaxLength = 4;
/* 410 */     this.returnAccessors[0].nullable = true;
/* 411 */     this.returnAccessors[0].precision = 0;
/* 412 */     this.returnAccessors[0].scale = 0;
/* 413 */     this.returnAccessors[0].formOfUse = 0;
  }
  
  void initMetaDataColumnIndexes()
    throws SQLException
  {
/* 421 */     for (int j = 0; j < this.returnAccessors.length; j++)
    {
/* 423 */       Accessor localAccessor = this.returnAccessors[j];
/* 424 */       int i = this.columnIndexes[j] - 1;
      
/* 426 */       localAccessor.columnName = this.tableColumnNames[i];
/* 427 */       localAccessor.describeType = this.tableColumnTypes[i];
/* 428 */       localAccessor.describeMaxLength = this.tableMaxLengths[i];
/* 429 */       localAccessor.nullable = this.tableNullables[i];
/* 430 */       localAccessor.precision = this.tablePrecisions[i];
/* 431 */       localAccessor.scale = this.tablePrecisions[i];
/* 432 */       localAccessor.formOfUse = this.tableFormOfUses[i];
    }
  }
  
  int getValidColumnIndex(int paramInt)
    throws SQLException
  {
/* 442 */     if ((paramInt <= 0) || (paramInt > this.returnAccessors.length))
    {
/* 444 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 445 */       localSQLException.fillInStackTrace();
/* 446 */       throw localSQLException;
    }
    
/* 449 */     return paramInt - 1;
  }
  
  public int getColumnCount() throws SQLException
  {
/* 454 */     return this.returnAccessors.length;
  }
  
  public String getColumnName(int paramInt)
    throws SQLException
  {
/* 460 */     if ((paramInt <= 0) || (paramInt > this.returnAccessors.length))
    {
/* 462 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 463 */       localSQLException.fillInStackTrace();
/* 464 */       throw localSQLException;
    }
    
/* 467 */     return this.returnAccessors[(paramInt - 1)].columnName;
  }
  
  public String getTableName(int paramInt)
    throws SQLException
  {
/* 473 */     if ((paramInt <= 0) || (paramInt > this.returnAccessors.length))
    {
/* 475 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3);
/* 476 */       localSQLException.fillInStackTrace();
/* 477 */       throw localSQLException;
    }
    
/* 480 */     return getTableName();
  }
  
  Accessor[] getDescription() throws SQLException
  {
/* 485 */     return this.returnAccessors;
  }
  
/* 489 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/AutoKeyInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */