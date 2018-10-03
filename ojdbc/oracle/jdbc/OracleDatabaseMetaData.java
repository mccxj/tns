/*      */ package oracle.jdbc;
/*      */ 
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.RowIdLifetime;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleSql;
/*      */ import oracle.jdbc.internal.OracleResultSet;
/*      */ import oracle.sql.SQLName;
/*      */ import oracle.sql.TypeDescriptor;
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
/*      */ public class OracleDatabaseMetaData
/*      */   implements AdditionalDatabaseMetaData
/*      */ {
/*   74 */   private static String DRIVER_NAME = "Oracle JDBC driver";
/*   75 */   private static String DRIVER_VERSION = "11.2.0.4.0";
/*   76 */   private static int DRIVER_MAJOR_VERSION = 11;
/*   77 */   private static int DRIVER_MINOR_VERSION = 2;
/*   78 */   private static String LOB_MAXSIZE = "4294967295";
/*   79 */   private static long LOB_MAXLENGTH_32BIT = 4294967295L;
/*      */   
/*      */ 
/*      */ 
/*      */   protected oracle.jdbc.internal.OracleConnection connection;
/*      */   
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public OracleDatabaseMetaData(OracleConnection paramOracleConnection)
/*      */   {
/*   92 */     this.connection = paramOracleConnection.physicalConnectionWithin();
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
/*      */   public boolean allProceduresAreCallable()
/*      */     throws SQLException
/*      */   {
/*  110 */     return false;
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
/*      */   public boolean allTablesAreSelectable()
/*      */     throws SQLException
/*      */   {
/*  125 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getURL()
/*      */     throws SQLException
/*      */   {
/*  138 */     return this.connection.getURL();
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
/*      */   public String getUserName()
/*      */     throws SQLException
/*      */   {
/*  152 */     return this.connection.getUserName();
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
/*      */   public boolean isReadOnly()
/*      */     throws SQLException
/*      */   {
/*  166 */     return false;
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
/*      */   public boolean nullsAreSortedHigh()
/*      */     throws SQLException
/*      */   {
/*  180 */     return false;
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
/*      */   public boolean nullsAreSortedLow()
/*      */     throws SQLException
/*      */   {
/*  194 */     return true;
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
/*      */   public boolean nullsAreSortedAtStart()
/*      */     throws SQLException
/*      */   {
/*  208 */     return false;
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
/*      */   public boolean nullsAreSortedAtEnd()
/*      */     throws SQLException
/*      */   {
/*  222 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDatabaseProductName()
/*      */     throws SQLException
/*      */   {
/*  235 */     return "Oracle";
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
/*      */   public String getDatabaseProductVersion()
/*      */     throws SQLException
/*      */   {
/*  249 */     return this.connection.getDatabaseProductVersion();
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
/*      */   public String getDriverName()
/*      */     throws SQLException
/*      */   {
/*  263 */     return DRIVER_NAME;
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
/*      */   public String getDriverVersion()
/*      */     throws SQLException
/*      */   {
/*  277 */     return DRIVER_VERSION;
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
/*      */   public int getDriverMajorVersion()
/*      */   {
/*  290 */     return DRIVER_MAJOR_VERSION;
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
/*      */   public int getDriverMinorVersion()
/*      */   {
/*  303 */     return DRIVER_MINOR_VERSION;
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
/*      */   public boolean usesLocalFiles()
/*      */     throws SQLException
/*      */   {
/*  317 */     return false;
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
/*      */   public boolean usesLocalFilePerTable()
/*      */     throws SQLException
/*      */   {
/*  331 */     return false;
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
/*      */   public boolean supportsMixedCaseIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  348 */     return false;
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
/*      */   public boolean storesUpperCaseIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  363 */     return true;
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
/*      */   public boolean storesLowerCaseIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  378 */     return false;
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
/*      */   public boolean storesMixedCaseIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  393 */     return false;
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
/*      */   public boolean supportsMixedCaseQuotedIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  410 */     return true;
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
/*      */   public boolean storesUpperCaseQuotedIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  425 */     return false;
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
/*      */   public boolean storesLowerCaseQuotedIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  440 */     return false;
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
/*      */   public boolean storesMixedCaseQuotedIdentifiers()
/*      */     throws SQLException
/*      */   {
/*  455 */     return true;
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
/*      */   public String getIdentifierQuoteString()
/*      */     throws SQLException
/*      */   {
/*  472 */     return "\"";
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
/*      */   public String getSQLKeywords()
/*      */     throws SQLException
/*      */   {
/*  487 */     return "ACCESS, ADD, ALTER, AUDIT, CLUSTER, COLUMN, COMMENT, COMPRESS, CONNECT, DATE, DROP, EXCLUSIVE, FILE, IDENTIFIED, IMMEDIATE, INCREMENT, INDEX, INITIAL, INTERSECT, LEVEL, LOCK, LONG, MAXEXTENTS, MINUS, MODE, NOAUDIT, NOCOMPRESS, NOWAIT, NUMBER, OFFLINE, ONLINE, PCTFREE, PRIOR, all_PL_SQL_reserved_ words";
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
/*      */   public String getNumericFunctions()
/*      */     throws SQLException
/*      */   {
/*  526 */     return "ABS,ACOS,ASIN,ATAN,ATAN2,CEILING,COS,EXP,FLOOR,LOG,LOG10,MOD,PI,POWER,ROUND,SIGN,SIN,SQRT,TAN,TRUNCATE";
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
/*      */   public String getStringFunctions()
/*      */     throws SQLException
/*      */   {
/*  564 */     return "ASCII,CHAR,CHAR_LENGTH,CHARACTER_LENGTH,CONCAT,LCASE,LENGTH,LTRIM,OCTET_LENGTH,REPLACE,RTRIM,SOUNDEX,SUBSTRING,UCASE";
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getSystemFunctions()
/*      */     throws SQLException
/*      */   {
/*  594 */     return "USER";
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getTimeDateFunctions()
/*      */     throws SQLException
/*      */   {
/*  626 */     return "CURRENT_DATE,CURRENT_TIMESTAMP,CURDATE,EXTRACT,HOUR,MINUTE,MONTH,SECOND,YEAR";
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
/*      */   public String getSearchStringEscape()
/*      */     throws SQLException
/*      */   {
/*  651 */     return "/";
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
/*      */   public String getExtraNameCharacters()
/*      */     throws SQLException
/*      */   {
/*  666 */     return "$#";
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
/*      */   public boolean supportsAlterTableWithAddColumn()
/*      */     throws SQLException
/*      */   {
/*  683 */     return true;
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
/*      */   public boolean supportsAlterTableWithDropColumn()
/*      */     throws SQLException
/*      */   {
/*  697 */     return false;
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
/*      */   public boolean supportsColumnAliasing()
/*      */     throws SQLException
/*      */   {
/*  717 */     return true;
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
/*      */   public boolean nullPlusNonNullIsNull()
/*      */     throws SQLException
/*      */   {
/*  733 */     return true;
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
/*      */   public boolean supportsConvert()
/*      */     throws SQLException
/*      */   {
/*  747 */     return false;
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
/*      */   public boolean supportsConvert(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  764 */     return false;
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
/*      */   public boolean supportsTableCorrelationNames()
/*      */     throws SQLException
/*      */   {
/*  780 */     return true;
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
/*      */   public boolean supportsDifferentTableCorrelationNames()
/*      */     throws SQLException
/*      */   {
/*  795 */     return true;
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
/*      */   public boolean supportsExpressionsInOrderBy()
/*      */     throws SQLException
/*      */   {
/*  809 */     return true;
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
/*      */   public boolean supportsOrderByUnrelated()
/*      */     throws SQLException
/*      */   {
/*  823 */     return true;
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
/*      */   public boolean supportsGroupBy()
/*      */     throws SQLException
/*      */   {
/*  837 */     return true;
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
/*      */   public boolean supportsGroupByUnrelated()
/*      */     throws SQLException
/*      */   {
/*  851 */     return true;
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
/*      */   public boolean supportsGroupByBeyondSelect()
/*      */     throws SQLException
/*      */   {
/*  866 */     return true;
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
/*      */   public boolean supportsLikeEscapeClause()
/*      */     throws SQLException
/*      */   {
/*  882 */     return true;
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
/*      */   public boolean supportsMultipleResultSets()
/*      */     throws SQLException
/*      */   {
/*  896 */     return false;
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
/*      */   public boolean supportsMultipleTransactions()
/*      */     throws SQLException
/*      */   {
/*  911 */     return true;
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
/*      */   public boolean supportsNonNullableColumns()
/*      */     throws SQLException
/*      */   {
/*  927 */     return true;
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
/*      */   public boolean supportsMinimumSQLGrammar()
/*      */     throws SQLException
/*      */   {
/*  943 */     return true;
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
/*      */   public boolean supportsCoreSQLGrammar()
/*      */     throws SQLException
/*      */   {
/*  957 */     return true;
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
/*      */   public boolean supportsExtendedSQLGrammar()
/*      */     throws SQLException
/*      */   {
/*  971 */     return true;
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
/*      */   public boolean supportsANSI92EntryLevelSQL()
/*      */     throws SQLException
/*      */   {
/*  987 */     return true;
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
/*      */   public boolean supportsANSI92IntermediateSQL()
/*      */     throws SQLException
/*      */   {
/* 1001 */     return false;
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
/*      */   public boolean supportsANSI92FullSQL()
/*      */     throws SQLException
/*      */   {
/* 1015 */     return false;
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
/*      */   public boolean supportsIntegrityEnhancementFacility()
/*      */     throws SQLException
/*      */   {
/* 1029 */     return true;
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
/*      */   public boolean supportsOuterJoins()
/*      */     throws SQLException
/*      */   {
/* 1043 */     return true;
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
/*      */   public boolean supportsFullOuterJoins()
/*      */     throws SQLException
/*      */   {
/* 1057 */     return true;
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
/*      */   public boolean supportsLimitedOuterJoins()
/*      */     throws SQLException
/*      */   {
/* 1072 */     return true;
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
/*      */   public String getSchemaTerm()
/*      */     throws SQLException
/*      */   {
/* 1086 */     return "schema";
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
/*      */   public String getProcedureTerm()
/*      */     throws SQLException
/*      */   {
/* 1100 */     return "procedure";
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
/*      */   public String getCatalogTerm()
/*      */     throws SQLException
/*      */   {
/* 1114 */     return "";
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
/*      */   public boolean isCatalogAtStart()
/*      */     throws SQLException
/*      */   {
/* 1129 */     return false;
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
/*      */   public String getCatalogSeparator()
/*      */     throws SQLException
/*      */   {
/* 1143 */     return "";
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
/*      */   public boolean supportsSchemasInDataManipulation()
/*      */     throws SQLException
/*      */   {
/* 1157 */     return true;
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
/*      */   public boolean supportsSchemasInProcedureCalls()
/*      */     throws SQLException
/*      */   {
/* 1171 */     return true;
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
/*      */   public boolean supportsSchemasInTableDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1185 */     return true;
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
/*      */   public boolean supportsSchemasInIndexDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1199 */     return true;
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
/*      */   public boolean supportsSchemasInPrivilegeDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1213 */     return true;
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
/*      */   public boolean supportsCatalogsInDataManipulation()
/*      */     throws SQLException
/*      */   {
/* 1227 */     return false;
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
/*      */   public boolean supportsCatalogsInProcedureCalls()
/*      */     throws SQLException
/*      */   {
/* 1241 */     return false;
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
/*      */   public boolean supportsCatalogsInTableDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1255 */     return false;
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
/*      */   public boolean supportsCatalogsInIndexDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1269 */     return false;
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
/*      */   public boolean supportsCatalogsInPrivilegeDefinitions()
/*      */     throws SQLException
/*      */   {
/* 1283 */     return false;
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
/*      */   public boolean supportsPositionedDelete()
/*      */     throws SQLException
/*      */   {
/* 1306 */     return false;
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
/*      */   public boolean supportsPositionedUpdate()
/*      */     throws SQLException
/*      */   {
/* 1328 */     return false;
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
/*      */   public boolean supportsSelectForUpdate()
/*      */     throws SQLException
/*      */   {
/* 1342 */     return true;
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
/*      */   public boolean supportsStoredProcedures()
/*      */     throws SQLException
/*      */   {
/* 1357 */     return true;
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
/*      */   public boolean supportsSubqueriesInComparisons()
/*      */     throws SQLException
/*      */   {
/* 1373 */     return true;
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
/*      */   public boolean supportsSubqueriesInExists()
/*      */     throws SQLException
/*      */   {
/* 1389 */     return true;
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
/*      */   public boolean supportsSubqueriesInIns()
/*      */     throws SQLException
/*      */   {
/* 1405 */     return true;
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
/*      */   public boolean supportsSubqueriesInQuantifieds()
/*      */     throws SQLException
/*      */   {
/* 1421 */     return true;
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
/*      */   public boolean supportsCorrelatedSubqueries()
/*      */     throws SQLException
/*      */   {
/* 1437 */     return true;
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
/*      */   public boolean supportsUnion()
/*      */     throws SQLException
/*      */   {
/* 1451 */     return true;
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
/*      */   public boolean supportsUnionAll()
/*      */     throws SQLException
/*      */   {
/* 1465 */     return true;
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
/*      */   public boolean supportsOpenCursorsAcrossCommit()
/*      */     throws SQLException
/*      */   {
/* 1480 */     return false;
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
/*      */   public boolean supportsOpenCursorsAcrossRollback()
/*      */     throws SQLException
/*      */   {
/* 1495 */     return false;
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
/*      */   public boolean supportsOpenStatementsAcrossCommit()
/*      */     throws SQLException
/*      */   {
/* 1510 */     return false;
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
/*      */   public boolean supportsOpenStatementsAcrossRollback()
/*      */     throws SQLException
/*      */   {
/* 1525 */     return false;
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
/*      */   public int getMaxBinaryLiteralLength()
/*      */     throws SQLException
/*      */   {
/* 1547 */     return 1000;
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
/*      */   public int getMaxCharLiteralLength()
/*      */     throws SQLException
/*      */   {
/* 1561 */     return 2000;
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
/*      */   public int getMaxColumnNameLength()
/*      */     throws SQLException
/*      */   {
/* 1575 */     return 30;
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
/*      */   public int getMaxColumnsInGroupBy()
/*      */     throws SQLException
/*      */   {
/* 1589 */     return 0;
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
/*      */   public int getMaxColumnsInIndex()
/*      */     throws SQLException
/*      */   {
/* 1603 */     return 32;
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
/*      */   public int getMaxColumnsInOrderBy()
/*      */     throws SQLException
/*      */   {
/* 1617 */     return 0;
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
/*      */   public int getMaxColumnsInSelect()
/*      */     throws SQLException
/*      */   {
/* 1631 */     return 0;
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
/*      */   public int getMaxColumnsInTable()
/*      */     throws SQLException
/*      */   {
/* 1645 */     return 1000;
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
/*      */   public int getMaxConnections()
/*      */     throws SQLException
/*      */   {
/* 1659 */     return 0;
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
/*      */   public int getMaxCursorNameLength()
/*      */     throws SQLException
/*      */   {
/* 1673 */     return 0;
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
/*      */   public int getMaxIndexLength()
/*      */     throws SQLException
/*      */   {
/* 1687 */     return 0;
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
/*      */   public int getMaxSchemaNameLength()
/*      */     throws SQLException
/*      */   {
/* 1701 */     return 30;
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
/*      */   public int getMaxProcedureNameLength()
/*      */     throws SQLException
/*      */   {
/* 1715 */     return 30;
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
/*      */   public int getMaxCatalogNameLength()
/*      */     throws SQLException
/*      */   {
/* 1729 */     return 0;
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
/*      */   public int getMaxRowSize()
/*      */     throws SQLException
/*      */   {
/* 1743 */     return 0;
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
/*      */   public boolean doesMaxRowSizeIncludeBlobs()
/*      */     throws SQLException
/*      */   {
/* 1758 */     return true;
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
/*      */   public int getMaxStatementLength()
/*      */     throws SQLException
/*      */   {
/* 1772 */     return 65535;
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
/*      */   public int getMaxStatements()
/*      */     throws SQLException
/*      */   {
/* 1787 */     return 0;
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
/*      */   public int getMaxTableNameLength()
/*      */     throws SQLException
/*      */   {
/* 1801 */     return 30;
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
/*      */   public int getMaxTablesInSelect()
/*      */     throws SQLException
/*      */   {
/* 1815 */     return 0;
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
/*      */   public int getMaxUserNameLength()
/*      */     throws SQLException
/*      */   {
/* 1829 */     return 30;
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
/*      */   public int getDefaultTransactionIsolation()
/*      */     throws SQLException
/*      */   {
/* 1847 */     return 2;
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
/*      */   public boolean supportsTransactions()
/*      */     throws SQLException
/*      */   {
/* 1862 */     return true;
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
/*      */   public boolean supportsTransactionIsolationLevel(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1879 */     return (paramInt == 2) || (paramInt == 8);
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
/*      */   public boolean supportsDataDefinitionAndDataManipulationTransactions()
/*      */     throws SQLException
/*      */   {
/* 1896 */     return true;
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
/*      */   public boolean supportsDataManipulationTransactionsOnly()
/*      */     throws SQLException
/*      */   {
/* 1911 */     return true;
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
/*      */   public boolean dataDefinitionCausesTransactionCommit()
/*      */     throws SQLException
/*      */   {
/* 1926 */     return true;
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
/*      */   public boolean dataDefinitionIgnoredInTransactions()
/*      */     throws SQLException
/*      */   {
/* 1940 */     return false;
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
/*      */   public synchronized ResultSet getProcedures(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 1999 */     String str1 = "SELECT\n  -- Standalone procedures and functions\n  NULL AS procedure_cat,\n  owner AS procedure_schem,\n  object_name AS procedure_name,\n  NULL,\n  NULL,\n  NULL,\n  'Standalone procedure or function' AS remarks,\n  DECODE(object_type, 'PROCEDURE', 1,\n                      'FUNCTION', 2,\n                      0) AS procedure_type\n,  NULL AS specific_name\nFROM all_objects\nWHERE (object_type = 'PROCEDURE' OR object_type = 'FUNCTION')\n  AND owner LIKE :1 ESCAPE '/'\n  AND object_name LIKE :2 ESCAPE '/'\n";
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
/* 2021 */     String str2 = "SELECT\n  -- Packaged procedures with no arguments\n  package_name AS procedure_cat,\n  owner AS procedure_schem,\n  object_name AS procedure_name,\n  NULL,\n  NULL,\n  NULL,\n  'Packaged procedure' AS remarks,\n  1 AS procedure_type\n,  NULL AS specific_name\nFROM all_arguments\nWHERE argument_name IS NULL\n  AND data_type IS NULL\n  AND ";
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
/* 2040 */     String str3 = "SELECT\n  -- Packaged procedures with arguments\n  package_name AS procedure_cat,\n  owner AS procedure_schem,\n  object_name AS procedure_name,\n  NULL,\n  NULL,\n  NULL,\n  'Packaged procedure' AS remarks,\n  1 AS procedure_type\n,  NULL AS specific_name\nFROM all_arguments\nWHERE argument_name IS NOT NULL\n  AND position = 1\n  AND position = sequence\n  AND ";
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
/* 2059 */     String str4 = "SELECT\n  -- Packaged functions\n  package_name AS procedure_cat,\n  owner AS procedure_schem,\n  object_name AS procedure_name,\n  NULL,\n  NULL,\n  NULL,\n  'Packaged function' AS remarks,\n  2 AS procedure_type\n,  NULL AS specific_name\nFROM all_arguments\nWHERE argument_name IS NULL\n  AND in_out = 'OUT'\n  AND   data_level = 0\n  AND ";
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
/* 2079 */     String str5 = "package_name LIKE :3 ESCAPE '/'\n  AND owner LIKE :4 ESCAPE '/'\n  AND object_name LIKE :5 ESCAPE '/'\n";
/*      */     
/*      */ 
/* 2082 */     String str6 = "package_name IS NOT NULL\n  AND owner LIKE :6 ESCAPE '/'\n  AND object_name LIKE :7 ESCAPE '/'\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2087 */     String str7 = "ORDER BY procedure_schem, procedure_name\n";
/*      */     
/* 2089 */     PreparedStatement localPreparedStatement = null;
/* 2090 */     String str8 = null;
/*      */     
/*      */ 
/* 2093 */     String str9 = paramString2;
/*      */     
/* 2095 */     if (paramString2 == null) {
/* 2096 */       str9 = "%";
/* 2097 */     } else if (paramString2.equals("")) {
/* 2098 */       str9 = getUserName().toUpperCase();
/*      */     }
/* 2100 */     String str10 = paramString3;
/*      */     
/* 2102 */     if (paramString3 == null) {
/* 2103 */       str10 = "%";
/* 2104 */     } else if (paramString3.equals(""))
/*      */     {
/* 2106 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2107 */       ((SQLException)localObject).fillInStackTrace();
/* 2108 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 2111 */     if (paramString1 == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2116 */       str8 = str1 + "UNION ALL " + str2 + str6 + "UNION ALL " + str3 + str6 + "UNION ALL " + str4 + str6 + str7;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2122 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2124 */       localPreparedStatement.setString(1, str9);
/* 2125 */       localPreparedStatement.setString(2, str10);
/* 2126 */       localPreparedStatement.setString(3, str9);
/* 2127 */       localPreparedStatement.setString(4, str10);
/* 2128 */       localPreparedStatement.setString(5, str9);
/* 2129 */       localPreparedStatement.setString(6, str10);
/* 2130 */       localPreparedStatement.setString(7, str9);
/* 2131 */       localPreparedStatement.setString(8, str10);
/*      */     }
/* 2133 */     else if (paramString1.equals(""))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2138 */       str8 = str1;
/*      */       
/* 2140 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2142 */       localPreparedStatement.setString(1, str9);
/* 2143 */       localPreparedStatement.setString(2, str10);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 2149 */       str8 = str2 + str5 + "UNION ALL " + str3 + str5 + "UNION ALL " + str4 + str5 + str7;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 2154 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2156 */       localPreparedStatement.setString(1, paramString1);
/* 2157 */       localPreparedStatement.setString(2, str9);
/* 2158 */       localPreparedStatement.setString(3, str10);
/* 2159 */       localPreparedStatement.setString(4, paramString1);
/* 2160 */       localPreparedStatement.setString(5, str9);
/* 2161 */       localPreparedStatement.setString(6, str10);
/* 2162 */       localPreparedStatement.setString(7, paramString1);
/* 2163 */       localPreparedStatement.setString(8, str9);
/* 2164 */       localPreparedStatement.setString(9, str10);
/*      */     }
/*      */     
/*      */ 
/* 2168 */     Object localObject = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 2171 */     ((OracleResultSet)localObject).closeStatementOnClose();
/*      */     
/* 2173 */     return (ResultSet)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2180 */   int procedureResultUnknown = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2185 */   int procedureNoResult = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2190 */   int procedureReturnsResult = 2;
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
/*      */   public synchronized ResultSet getProcedureColumns(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 2280 */     boolean bool = this.connection.getIncludeSynonyms();
/*      */     
/* 2282 */     if (("".equals(paramString1)) && ((paramString2 == null) || (!hasSqlWildcard(paramString2))) && (paramString3 != null) && (!hasSqlWildcard(paramString3)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2287 */       return getUnpackagedProcedureColumnsNoWildcards(paramString2 != null ? stripSqlEscapes(paramString2) : null, paramString3 != null ? stripSqlEscapes(paramString3) : null, paramString4);
/*      */     }
/*      */     
/* 2290 */     if ((paramString1 != null) && (paramString1.length() != 0) && (!hasSqlWildcard(paramString1)) && ((paramString2 == null) || (!hasSqlWildcard(paramString2))))
/*      */     {
/*      */ 
/*      */ 
/* 2294 */       return getPackagedProcedureColumnsNoWildcards(paramString1 != null ? stripSqlEscapes(paramString1) : null, paramString2 != null ? stripSqlEscapes(paramString2) : null, paramString3, paramString4);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2301 */     return getProcedureColumnsWithWildcards(paramString1, paramString2, paramString3, paramString4, bool);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ResultSet getUnpackagedProcedureColumnsNoWildcards(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 2313 */     if ("".equals(paramString3))
/*      */     {
/* 2315 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2316 */       ((SQLException)localObject1).fillInStackTrace();
/* 2317 */       throw ((Throwable)localObject1);
/*      */     }
/* 2319 */     Object localObject1 = getUnpackagedProcedureColumnsNoWildcardsPlsql();
/* 2320 */     CallableStatement localCallableStatement = null;
/* 2321 */     ResultSet localResultSet = null;
/*      */     try
/*      */     {
/* 2324 */       localCallableStatement = this.connection.prepareCall((String)localObject1);
/* 2325 */       localCallableStatement.setString(1, paramString1);
/* 2326 */       localCallableStatement.setString(2, paramString2);
/* 2327 */       localCallableStatement.setString(3, paramString3 == null ? "%" : paramString3);
/* 2328 */       localCallableStatement.registerOutParameter(4, -10);
/* 2329 */       localCallableStatement.registerOutParameter(5, 2);
/* 2330 */       localCallableStatement.execute();
/* 2331 */       int i = localCallableStatement.getInt(5);
/* 2332 */       if (i == 0)
/*      */       {
/* 2334 */         localResultSet = ((OracleCallableStatement)localCallableStatement).getCursor(4);
/* 2335 */         ((OracleResultSet)localResultSet).closeStatementOnClose();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2340 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 258);
/* 2341 */         localSQLException.fillInStackTrace();
/* 2342 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 2347 */       if ((localResultSet == null) && (localCallableStatement != null)) localCallableStatement.close();
/*      */     }
/* 2349 */     return localResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   ResultSet getPackagedProcedureColumnsNoWildcards(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 2358 */     if ("".equals(paramString4))
/*      */     {
/* 2360 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2361 */       ((SQLException)localObject1).fillInStackTrace();
/* 2362 */       throw ((Throwable)localObject1);
/*      */     }
/* 2364 */     Object localObject1 = getPackagedProcedureColumnsNoWildcardsPlsql();
/* 2365 */     CallableStatement localCallableStatement = null;
/* 2366 */     ResultSet localResultSet = null;
/*      */     try
/*      */     {
/* 2369 */       localCallableStatement = this.connection.prepareCall((String)localObject1);
/* 2370 */       localCallableStatement.setString(1, paramString1);
/* 2371 */       localCallableStatement.setString(2, paramString2);
/* 2372 */       localCallableStatement.setString(3, paramString3);
/* 2373 */       localCallableStatement.setString(4, paramString4 == null ? "%" : paramString4);
/* 2374 */       localCallableStatement.registerOutParameter(5, -10);
/* 2375 */       localCallableStatement.registerOutParameter(6, 2);
/* 2376 */       localCallableStatement.execute();
/* 2377 */       int i = localCallableStatement.getInt(6);
/* 2378 */       if (i == 0)
/*      */       {
/* 2380 */         localResultSet = ((OracleCallableStatement)localCallableStatement).getCursor(5);
/* 2381 */         ((OracleResultSet)localResultSet).closeStatementOnClose();
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2386 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 258);
/* 2387 */         localSQLException.fillInStackTrace();
/* 2388 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 2393 */       if ((localResultSet == null) && (localCallableStatement != null)) localCallableStatement.close();
/*      */     }
/* 2395 */     return localResultSet;
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
/*      */   ResultSet getProcedureColumnsWithWildcards(String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2420 */     String str1 = "SELECT package_name AS procedure_cat,\n       owner AS procedure_schem,\n       object_name AS procedure_name,\n       argument_name AS column_name,\n       DECODE(position, 0, 5,\n                        DECODE(in_out, 'IN', 1,\n                                       'OUT', 4,\n                                       'IN/OUT', 2,\n                                       0)) AS column_type,\n       DECODE (data_type, 'CHAR', 1,\n                          'VARCHAR2', 12,\n                          'NUMBER', 3,\n                          'LONG', -1,\n                          'DATE', " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + "                          'RAW', -3,\n" + "                          'LONG RAW', -4,\n" + "                          'TIMESTAMP', 93, \n" + "                          'TIMESTAMP WITH TIME ZONE', -101, \n" + "               'TIMESTAMP WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR TO MONTH', -103, \n" + "               'INTERVAL DAY TO SECOND', -104, \n" + "               'BINARY_FLOAT', 100, 'BINAvRY_DOUBLE', 101," + "               1111) AS data_type,\n" + "       DECODE(data_type, 'OBJECT', type_owner || '.' || type_name, " + "              data_type) AS type_name,\n" + "       DECODE (data_precision, NULL, data_length,\n" + "                               data_precision) AS precision,\n" + "       data_length AS length,\n" + "       data_scale AS scale,\n" + "       10 AS radix,\n" + "       1 AS nullable,\n" + "       NULL AS remarks,\n" + "       default_value AS column_def,\n" + "       NULL as sql_data_type,\n" + "       NULL AS sql_datetime_sub,\n" + "       DECODE(data_type,\n" + "                         'CHAR', 32767,\n" + "                         'VARCHAR2', 32767,\n" + "                         'LONG', 32767,\n" + "                         'RAW', 32767,\n" + "                         'LONG RAW', 32767,\n" + "                         NULL) AS char_octet_length,\n" + "       (sequence - 1) AS ordinal_position,\n" + "       'YES' AS is_nullable,\n" + "       NULL AS specific_name,\n" + "       sequence,\n" + "       overload,\n" + "       default_value\n" + " FROM all_arguments\n" + "WHERE owner LIKE :1 ESCAPE '/'\n" + "  AND object_name LIKE :2 ESCAPE '/' AND data_level = 0\n";
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
/* 2478 */     String str2 = "  AND package_name LIKE :3 ESCAPE '/'\n";
/* 2479 */     String str3 = "  AND package_name IS NULL\n";
/*      */     
/* 2481 */     String str4 = "  AND argument_name LIKE :4 ESCAPE '/'\n";
/* 2482 */     String str5 = "  AND (argument_name LIKE :5 ESCAPE '/'\n       OR (argument_name IS NULL\n           AND data_type IS NOT NULL))\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2488 */     String str6 = "ORDER BY procedure_schem, procedure_name, overload, sequence\n";
/*      */     
/*      */ 
/* 2491 */     String str7 = null;
/* 2492 */     PreparedStatement localPreparedStatement = null;
/* 2493 */     String str8 = null;
/*      */     
/*      */ 
/* 2496 */     String str9 = paramString2;
/*      */     
/* 2498 */     if (paramString2 == null) {
/* 2499 */       str9 = "%";
/* 2500 */     } else if (paramString2.equals("")) {
/* 2501 */       str9 = getUserName().toUpperCase();
/*      */     }
/* 2503 */     String str10 = paramString3;
/*      */     
/* 2505 */     if (paramString3 == null) {
/* 2506 */       str10 = "%";
/* 2507 */     } else if (paramString3.equals(""))
/*      */     {
/* 2509 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2510 */       ((SQLException)localObject1).fillInStackTrace();
/* 2511 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 2514 */     Object localObject1 = paramString4;
/*      */     
/* 2516 */     if ((paramString4 == null) || (paramString4.equals("%")))
/*      */     {
/* 2518 */       localObject1 = "%";
/* 2519 */       str8 = str5;
/*      */     } else {
/* 2521 */       if (paramString4.equals(""))
/*      */       {
/* 2523 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2524 */         ((SQLException)localObject2).fillInStackTrace();
/* 2525 */         throw ((Throwable)localObject2);
/*      */       }
/*      */       
/* 2528 */       str8 = str4;
/*      */     }
/* 2530 */     if (paramString1 == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2535 */       str7 = str1 + str8 + str6;
/*      */       
/* 2537 */       localPreparedStatement = this.connection.prepareStatement(str7);
/*      */       
/* 2539 */       localPreparedStatement.setString(1, str9);
/* 2540 */       localPreparedStatement.setString(2, str10);
/* 2541 */       localPreparedStatement.setString(3, (String)localObject1);
/*      */     }
/* 2543 */     else if (paramString1.equals(""))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2548 */       str7 = str1 + str3 + str8 + str6;
/*      */       
/* 2550 */       localPreparedStatement = this.connection.prepareStatement(str7);
/*      */       
/* 2552 */       localPreparedStatement.setString(1, str9);
/* 2553 */       localPreparedStatement.setString(2, str10);
/* 2554 */       localPreparedStatement.setString(3, (String)localObject1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 2560 */       str7 = str1 + str2 + str8 + str6;
/*      */       
/* 2562 */       localPreparedStatement = this.connection.prepareStatement(str7);
/*      */       
/* 2564 */       localPreparedStatement.setString(1, str9);
/* 2565 */       localPreparedStatement.setString(2, str10);
/* 2566 */       localPreparedStatement.setString(3, paramString1);
/* 2567 */       localPreparedStatement.setString(4, (String)localObject1);
/*      */     }
/*      */     
/*      */ 
/* 2571 */     Object localObject2 = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 2574 */     ((OracleResultSet)localObject2).closeStatementOnClose();
/*      */     
/* 2576 */     return (ResultSet)localObject2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getFunctionColumns(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 2683 */     String str1 = "SELECT package_name AS function_cat,\n       arg.owner AS function_schem,\n       arg.object_name AS function_name,\n       arg.argument_name AS column_name,\n       DECODE(arg.position, 0, 5,\n                        DECODE(arg.in_out, 'IN', 1,\n                                       'OUT', 4,\n                                       'IN/OUT', 2,\n                                       0)) AS column_type,\n       DECODE (arg.data_type, 'CHAR', 1,\n                          'VARCHAR2', 12,\n                          'NUMBER', 3,\n                          'LONG', -1,\n                          'DATE', " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + "                          'RAW', -3,\n" + "                          'LONG RAW', -4,\n" + "                          'TIMESTAMP', 93, \n" + "                          'TIMESTAMP WITH TIME ZONE', -101, \n" + "               'TIMESTAMP WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR TO MONTH', -103, \n" + "               'INTERVAL DAY TO SECOND', -104, \n" + "               'BINARY_FLOAT', 100, 'BINAvRY_DOUBLE', 101," + "               1111) AS data_type,\n" + "       DECODE(arg.data_type, 'OBJECT', arg.type_owner || '.' || arg.type_name, " + "              arg.data_type) AS type_name,\n" + "       DECODE (arg.data_precision, NULL, arg.data_length,\n" + "                               arg.data_precision) AS precision,\n" + "       arg.data_length AS length,\n" + "       arg.data_scale AS scale,\n" + "       10 AS radix,\n" + "       1 AS nullable,\n" + "       NULL AS remarks,\n" + "       arg.default_value AS column_def,\n" + "       NULL as sql_data_type,\n" + "       NULL AS sql_datetime_sub,\n" + "       DECODE(arg.data_type,\n" + "                         'CHAR', 32767,\n" + "                         'VARCHAR2', 32767,\n" + "                         'LONG', 32767,\n" + "                         'RAW', 32767,\n" + "                         'LONG RAW', 32767,\n" + "                         NULL) AS char_octet_length,\n" + "       (arg.sequence - 1) AS ordinal_position,\n" + "       'YES' AS is_nullable,\n" + "       NULL AS specific_name,\n" + "       arg.sequence,\n" + "       arg.overload,\n" + "       arg.default_value\n" + " FROM all_arguments arg, all_procedures proc\n" + " WHERE arg.owner LIKE :1 ESCAPE '/'\n" + "  AND arg.object_name LIKE :2 ESCAPE '/'\n";
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
/* 2738 */     int i = this.connection.getVersionNumber();
/*      */     
/* 2740 */     String str2 = i >= 10200 ? "  AND proc.object_id = arg.object_id\n  AND proc.object_type = 'FUNCTION'\n" : "  AND proc.owner = arg.owner\n  AND proc.object_name = arg.object_name\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2748 */     String str3 = "  AND arg.package_name LIKE :3 ESCAPE '/'\n";
/*      */     
/* 2750 */     String str4 = "  AND arg.package_name IS NULL\n";
/*      */     
/* 2752 */     String str5 = "  AND arg.argument_name LIKE :4 ESCAPE '/'\n";
/*      */     
/* 2754 */     String str6 = "  AND (arg.argument_name LIKE :5 ESCAPE '/'\n     OR (arg.argument_name IS NULL\n         AND arg.data_type IS NOT NULL))\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2761 */     String str7 = "ORDER BY function_schem, function_name, overload, sequence\n";
/*      */     
/*      */ 
/* 2764 */     String str8 = null;
/* 2765 */     PreparedStatement localPreparedStatement = null;
/* 2766 */     String str9 = null;
/*      */     
/*      */ 
/* 2769 */     String str10 = paramString2;
/*      */     
/* 2771 */     if (paramString2 == null) {
/* 2772 */       str10 = "%";
/* 2773 */     } else if (paramString2.equals("")) {
/* 2774 */       str10 = getUserName().toUpperCase();
/*      */     }
/* 2776 */     String str11 = paramString3;
/*      */     
/* 2778 */     if (paramString3 == null) {
/* 2779 */       str11 = "%";
/* 2780 */     } else if (paramString3.equals(""))
/*      */     {
/* 2782 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2783 */       ((SQLException)localObject1).fillInStackTrace();
/* 2784 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 2787 */     Object localObject1 = paramString4;
/*      */     
/* 2789 */     if ((paramString4 == null) || (paramString4.equals("%")))
/*      */     {
/* 2791 */       localObject1 = "%";
/* 2792 */       str9 = str6;
/*      */     } else {
/* 2794 */       if (paramString4.equals(""))
/*      */       {
/* 2796 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 2797 */         ((SQLException)localObject2).fillInStackTrace();
/* 2798 */         throw ((Throwable)localObject2);
/*      */       }
/*      */       
/* 2801 */       str9 = str5;
/*      */     }
/* 2803 */     if (paramString1 == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2808 */       str8 = str1 + str2 + str9 + str7;
/*      */       
/* 2810 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2812 */       localPreparedStatement.setString(1, str10);
/* 2813 */       localPreparedStatement.setString(2, str11);
/* 2814 */       localPreparedStatement.setString(3, (String)localObject1);
/*      */     }
/* 2816 */     else if (paramString1.equals(""))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2821 */       str8 = str1 + str2 + str4 + str9 + str7;
/*      */       
/*      */ 
/* 2824 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2826 */       localPreparedStatement.setString(1, str10);
/* 2827 */       localPreparedStatement.setString(2, str11);
/* 2828 */       localPreparedStatement.setString(3, (String)localObject1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 2834 */       str8 = str1 + str2 + str3 + str9 + str7;
/*      */       
/*      */ 
/* 2837 */       localPreparedStatement = this.connection.prepareStatement(str8);
/*      */       
/* 2839 */       localPreparedStatement.setString(1, str10);
/* 2840 */       localPreparedStatement.setString(2, str11);
/* 2841 */       localPreparedStatement.setString(3, paramString1);
/* 2842 */       localPreparedStatement.setString(4, (String)localObject1);
/*      */     }
/*      */     
/*      */ 
/* 2846 */     Object localObject2 = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 2849 */     ((OracleResultSet)localObject2).closeStatementOnClose();
/*      */     
/* 2851 */     return (ResultSet)localObject2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2859 */   int procedureColumnUnknown = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2864 */   int procedureColumnIn = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2869 */   int procedureColumnInOut = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2874 */   int procedureColumnOut = 4;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2879 */   int procedureColumnReturn = 5;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2884 */   int procedureColumnResult = 3;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2889 */   int procedureNoNulls = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2894 */   int procedureNullable = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 2899 */   int procedureNullableUnknown = 2;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized ResultSet getTables(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 2945 */     String str1 = "SELECT NULL AS table_cat,\n       o.owner AS table_schem,\n       o.object_name AS table_name,\n       o.object_type AS table_type,\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2950 */     String str2 = "       c.comments AS remarks\n";
/* 2951 */     String str3 = "       NULL AS remarks\n";
/*      */     
/* 2953 */     String str4 = "  FROM all_objects o, all_tab_comments c\n";
/* 2954 */     String str5 = "  FROM all_objects o\n";
/*      */     
/* 2956 */     String str6 = "  WHERE o.owner LIKE :1 ESCAPE '/'\n    AND o.object_name LIKE :2 ESCAPE '/'\n";
/*      */     
/*      */ 
/* 2959 */     String str7 = "    AND o.owner = c.owner (+)\n    AND o.object_name = c.table_name (+)\n";
/*      */     
/*      */ 
/* 2962 */     int i = 0;
/*      */     
/* 2964 */     String str8 = "";
/* 2965 */     String str9 = "";
/*      */     
/*      */ 
/* 2968 */     if (paramArrayOfString != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2976 */       str8 = "    AND o.object_type IN ('xxx'";
/* 2977 */       str9 = "    AND o.object_type IN ('xxx'";
/*      */       
/* 2979 */       for (int j = 0; j < paramArrayOfString.length; j++)
/*      */       {
/* 2981 */         if (paramArrayOfString[j].equals("SYNONYM"))
/*      */         {
/*      */ 
/*      */ 
/* 2985 */           str8 = str8 + ", '" + paramArrayOfString[j] + "'";
/* 2986 */           i = 1;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 2992 */           str8 = str8 + ", '" + paramArrayOfString[j] + "'";
/* 2993 */           str9 = str9 + ", '" + paramArrayOfString[j] + "'";
/*      */         }
/*      */       }
/*      */       
/* 2997 */       str8 = str8 + ")\n";
/* 2998 */       str9 = str9 + ")\n";
/*      */     }
/*      */     else
/*      */     {
/* 3002 */       i = 1;
/* 3003 */       str8 = "    AND o.object_type IN ('TABLE', 'SYNONYM', 'VIEW')\n";
/* 3004 */       str9 = "    AND o.object_type IN ('TABLE', 'VIEW')\n";
/*      */     }
/*      */     
/* 3007 */     String str10 = "  ORDER BY table_type, table_schem, table_name\n";
/*      */     
/* 3009 */     String str11 = "SELECT NULL AS table_cat,\n       s.owner AS table_schem,\n       s.synonym_name AS table_name,\n       'SYNONYM' AS table_table_type,\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3014 */     String str12 = "       c.comments AS remarks\n";
/* 3015 */     String str13 = "       NULL AS remarks\n";
/*      */     
/* 3017 */     String str14 = "  FROM all_synonyms s, all_objects o, all_tab_comments c\n";
/*      */     
/* 3019 */     String str15 = "  FROM all_synonyms s, all_objects o\n";
/*      */     
/* 3021 */     String str16 = "  WHERE s.owner LIKE :3 ESCAPE '/'\n    AND s.synonym_name LIKE :4 ESCAPE '/'\n    AND s.table_owner = o.owner\n    AND s.table_name = o.object_name\n    AND o.object_type IN ('TABLE', 'VIEW')\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3028 */     String str17 = "";
/*      */     
/* 3030 */     str17 = str17 + str1;
/*      */     
/* 3032 */     if (this.connection.getRemarksReporting()) {
/* 3033 */       str17 = str17 + str2 + str4;
/*      */     } else {
/* 3035 */       str17 = str17 + str3 + str5;
/*      */     }
/* 3037 */     str17 = str17 + str6;
/*      */     
/* 3039 */     if (this.connection.getRestrictGetTables()) {
/* 3040 */       str17 = str17 + str9;
/*      */     } else {
/* 3042 */       str17 = str17 + str8;
/*      */     }
/* 3044 */     if (this.connection.getRemarksReporting()) {
/* 3045 */       str17 = str17 + str7;
/*      */     }
/* 3047 */     if ((i != 0) && (this.connection.getRestrictGetTables()))
/*      */     {
/*      */ 
/*      */ 
/* 3051 */       str17 = str17 + "UNION\n" + str11;
/*      */       
/* 3053 */       if (this.connection.getRemarksReporting()) {
/* 3054 */         str17 = str17 + str12 + str14;
/*      */       } else {
/* 3056 */         str17 = str17 + str13 + str15;
/*      */       }
/* 3058 */       str17 = str17 + str16;
/*      */       
/* 3060 */       if (this.connection.getRemarksReporting()) {
/* 3061 */         str17 = str17 + str7;
/*      */       }
/*      */     }
/* 3064 */     str17 = str17 + str10;
/*      */     
/*      */ 
/* 3067 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement(str17);
/*      */     
/* 3069 */     localPreparedStatement.setString(1, paramString2 == null ? "%" : paramString2);
/* 3070 */     localPreparedStatement.setString(2, paramString3 == null ? "%" : paramString3);
/*      */     
/* 3072 */     if ((i != 0) && (this.connection.getRestrictGetTables()))
/*      */     {
/* 3074 */       localPreparedStatement.setString(3, paramString2 == null ? "%" : paramString2);
/* 3075 */       localPreparedStatement.setString(4, paramString3 == null ? "%" : paramString3);
/*      */     }
/*      */     
/* 3078 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3081 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3083 */     return localOracleResultSet;
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
/*      */   public ResultSet getSchemas()
/*      */     throws SQLException
/*      */   {
/* 3105 */     Statement localStatement = this.connection.createStatement();
/* 3106 */     String str = "SELECT username AS table_schem,null as table_catalog  FROM all_users ORDER BY table_schem";
/*      */     
/*      */ 
/*      */ 
/* 3110 */     OracleResultSet localOracleResultSet = (OracleResultSet)localStatement.executeQuery(str);
/*      */     
/*      */ 
/* 3113 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3115 */     return localOracleResultSet;
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
/*      */   public ResultSet getCatalogs()
/*      */     throws SQLException
/*      */   {
/* 3136 */     Statement localStatement = this.connection.createStatement();
/* 3137 */     String str = "select 'nothing' as table_cat from dual where 1 = 2";
/* 3138 */     OracleResultSet localOracleResultSet = (OracleResultSet)localStatement.executeQuery(str);
/*      */     
/*      */ 
/* 3141 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3143 */     return localOracleResultSet;
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
/*      */   public ResultSet getTableTypes()
/*      */     throws SQLException
/*      */   {
/* 3165 */     Statement localStatement = this.connection.createStatement();
/* 3166 */     String str = "select 'TABLE' as table_type from dual\nunion select 'VIEW' as table_type from dual\nunion select 'SYNONYM' as table_type from dual\n";
/*      */     
/*      */ 
/*      */ 
/* 3170 */     OracleResultSet localOracleResultSet = (OracleResultSet)localStatement.executeQuery(str);
/*      */     
/*      */ 
/* 3173 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3175 */     return localOracleResultSet;
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
/*      */   public ResultSet getColumns(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 3251 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 3252 */     localSQLException.fillInStackTrace();
/* 3253 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3261 */   int columnNoNulls = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3266 */   int columnNullable = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3271 */   int columnNullableUnknown = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int bestRowTemporary = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int bestRowTransaction = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int bestRowSession = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int bestRowUnknown = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int bestRowNotPseudo = 1;
/*      */   
/*      */ 
/*      */ 
/*      */   static final int bestRowPseudo = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized ResultSet getColumnPrivileges(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 3307 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT NULL AS table_cat,\n       table_schema AS table_schem,\n       table_name,\n       column_name,\n       grantor,\n       grantee,\n       privilege,\n       grantable AS is_grantable\nFROM all_col_privs\nWHERE table_schema LIKE :1 ESCAPE '/'\n  AND table_name LIKE :2 ESCAPE '/'\n  AND column_name LIKE :3 ESCAPE '/'\nORDER BY column_name, privilege\n");
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
/* 3321 */     localPreparedStatement.setString(1, paramString2 == null ? "%" : paramString2);
/* 3322 */     localPreparedStatement.setString(2, paramString3 == null ? "%" : paramString3);
/* 3323 */     localPreparedStatement.setString(3, paramString4 == null ? "%" : paramString4);
/*      */     
/* 3325 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3328 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3330 */     return localOracleResultSet;
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
/*      */   public synchronized ResultSet getTablePrivileges(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3369 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT NULL AS table_cat,\n       table_schema AS table_schem,\n       table_name,\n       grantor,\n       grantee,\n       privilege,\n       grantable AS is_grantable\nFROM all_tab_privs\nWHERE table_schema LIKE :1 ESCAPE '/'\n  AND table_name LIKE :2 ESCAPE '/'\nORDER BY table_schem, table_name, privilege\n");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3377 */     localPreparedStatement.setString(1, paramString2 == null ? "%" : paramString2);
/* 3378 */     localPreparedStatement.setString(2, paramString3 == null ? "%" : paramString3);
/*      */     
/* 3380 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3383 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3385 */     return localOracleResultSet;
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
/*      */   public synchronized ResultSet getBestRowIdentifier(String paramString1, String paramString2, String paramString3, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3430 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT 1 AS scope, 'ROWID' AS column_name, -8 AS data_type,\n 'ROWID' AS type_name, 0 AS column_size, 0 AS buffer_length,\n       0 AS decimal_digits, 2 AS pseudo_column\nFROM DUAL\nWHERE :1 = 1\nUNION\nSELECT 2 AS scope,\n  t.column_name,\n DECODE (t.data_type, 'CHAR', 1, 'VARCHAR2', 12, 'NUMBER', 3,\n 'LONG', -1, 'DATE', " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + " 'RAW', -3, 'LONG RAW', -4, \n" + " 'TIMESTAMP(6)', 93, " + " 'TIMESTAMP(6) WITH TIME ZONE', -101, \n" + " 'TIMESTAMP(6) WITH LOCAL TIME ZONE', -102, \n" + " 'INTERVAL YEAR(2) TO MONTH', -103, \n" + " 'INTERVAL DAY(2) TO SECOND(6)', -104, \n" + " 'BINARY_FLOAT', 100, " + " 'BINARY_DOUBLE', 101," + " 1111)\n" + " AS data_type,\n" + " t.data_type AS type_name,\n" + " DECODE (t.data_precision, null, t.data_length, t.data_precision)\n" + "  AS column_size,\n" + "  0 AS buffer_length,\n" + "  t.data_scale AS decimal_digits,\n" + "       1 AS pseudo_column\n" + "FROM all_tab_columns t, all_ind_columns i\n" + "WHERE :2 = 1\n" + "  AND t.table_name = :3\n" + "  AND t.owner like :4 escape '/'\n" + "  AND t.nullable != :5\n" + "  AND t.owner = i.table_owner\n" + "  AND t.table_name = i.table_name\n" + "  AND t.column_name = i.column_name\n");
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
/* 3467 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */     case 0: 
/* 3471 */       localPreparedStatement.setInt(1, 0);
/* 3472 */       localPreparedStatement.setInt(2, 0);
/*      */       
/* 3474 */       break;
/*      */     
/*      */     case 1: 
/* 3477 */       localPreparedStatement.setInt(1, 1);
/* 3478 */       localPreparedStatement.setInt(2, 1);
/*      */       
/* 3480 */       break;
/*      */     
/*      */     case 2: 
/* 3483 */       localPreparedStatement.setInt(1, 0);
/* 3484 */       localPreparedStatement.setInt(2, 1);
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 3489 */     localPreparedStatement.setString(3, paramString3);
/* 3490 */     localPreparedStatement.setString(4, paramString2 == null ? "%" : paramString2);
/* 3491 */     localPreparedStatement.setString(5, paramBoolean ? "X" : "Y");
/*      */     
/* 3493 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3496 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3498 */     return localOracleResultSet;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized ResultSet getVersionColumns(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3566 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT 0 AS scope,\n t.column_name,\n DECODE (c.data_type, 'CHAR', 1, 'VARCHAR2', 12, 'NUMBER', 3,\n  'LONG', -1, 'DATE',  " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + "  'RAW', -3, 'LONG RAW', -4, " + "  'TIMESTAMP(6)', 93, 'TIMESTAMP(6) WITH TIME ZONE', -101, \n" + "  'TIMESTAMP(6) WITH LOCAL TIME ZONE', -102, \n" + "  'INTERVAL YEAR(2) TO MONTH', -103, \n" + "  'INTERVAL DAY(2) TO SECOND(6)', -104, \n" + "  'BINARY_FLOAT', 100, 'BINARY_DOUBLE', 101," + "   1111)\n " + " AS data_type,\n" + "       c.data_type AS type_name,\n" + " DECODE (c.data_precision, null, c.data_length, c.data_precision)\n" + "   AS column_size,\n" + "       0 as buffer_length,\n" + "   c.data_scale as decimal_digits,\n" + "   0 as pseudo_column\n" + "FROM all_trigger_cols t, all_tab_columns c\n" + "WHERE t.table_name = :1\n" + "  AND c.owner like :2 escape '/'\n" + " AND t.table_owner = c.owner\n" + "  AND t.table_name = c.table_name\n" + " AND t.column_name = c.column_name\n");
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
/* 3589 */     localPreparedStatement.setString(1, paramString3);
/* 3590 */     localPreparedStatement.setString(2, paramString2 == null ? "%" : paramString2);
/*      */     
/* 3592 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3595 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3597 */     return localOracleResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3604 */   int versionColumnUnknown = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3609 */   int versionColumnNotPseudo = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 3614 */   int versionColumnPseudo = 2;
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
/*      */   public ResultSet getPrimaryKeys(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3642 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT NULL AS table_cat,\n       c.owner AS table_schem,\n       c.table_name,\n       c.column_name,\n       c.position AS key_seq,\n       c.constraint_name AS pk_name\nFROM all_cons_columns c, all_constraints k\nWHERE k.constraint_type = 'P'\n  AND k.table_name = :1\n  AND k.owner like :2 escape '/'\n  AND k.constraint_name = c.constraint_name \n  AND k.table_name = c.table_name \n  AND k.owner = c.owner \nORDER BY column_name\n");
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
/* 3653 */     localPreparedStatement.setString(1, paramString3);
/* 3654 */     localPreparedStatement.setString(2, paramString2 == null ? "%" : paramString2);
/*      */     
/* 3656 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3659 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3661 */     return localOracleResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ResultSet keys_query(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
/*      */     throws SQLException
/*      */   {
/* 3674 */     int i = 1;
/* 3675 */     int j = paramString2 != null ? i++ : 0;
/* 3676 */     int k = paramString4 != null ? i++ : 0;
/* 3677 */     int m = (paramString1 != null) && (paramString1.length() > 0) ? i++ : 0;
/* 3678 */     int n = (paramString3 != null) && (paramString3.length() > 0) ? i++ : 0;
/*      */     
/*      */ 
/* 3681 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement("SELECT NULL AS pktable_cat,\n       p.owner as pktable_schem,\n       p.table_name as pktable_name,\n       pc.column_name as pkcolumn_name,\n       NULL as fktable_cat,\n       f.owner as fktable_schem,\n       f.table_name as fktable_name,\n       fc.column_name as fkcolumn_name,\n       fc.position as key_seq,\n       NULL as update_rule,\n       decode (f.delete_rule, 'CASCADE', 0, 'SET NULL', 2, 1) as delete_rule,\n       f.constraint_name as fk_name,\n       p.constraint_name as pk_name,\n       decode(f.deferrable,       'DEFERRABLE',5      ,'NOT DEFERRABLE',7      , 'DEFERRED', 6      ) deferrability \n      FROM all_cons_columns pc, all_constraints p,\n      all_cons_columns fc, all_constraints f\nWHERE 1 = 1\n" + (j != 0 ? "  AND p.table_name = :1\n" : "") + (k != 0 ? "  AND f.table_name = :2\n" : "") + (m != 0 ? "  AND p.owner = :3\n" : "") + (n != 0 ? "  AND f.owner = :4\n" : "") + "  AND f.constraint_type = 'R'\n" + "  AND p.owner = f.r_owner\n" + "  AND p.constraint_name = f.r_constraint_name\n" + "  AND p.constraint_type = 'P'\n" + "  AND pc.owner = p.owner\n" + "  AND pc.constraint_name = p.constraint_name\n" + "  AND pc.table_name = p.table_name\n" + "  AND fc.owner = f.owner\n" + "  AND fc.constraint_name = f.constraint_name\n" + "  AND fc.table_name = f.table_name\n" + "  AND fc.position = pc.position\n" + paramString5);
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
/* 3711 */     if (j != 0)
/*      */     {
/* 3713 */       localPreparedStatement.setString(j, paramString2);
/*      */     }
/*      */     
/* 3716 */     if (k != 0)
/*      */     {
/* 3718 */       localPreparedStatement.setString(k, paramString4);
/*      */     }
/*      */     
/* 3721 */     if (m != 0)
/*      */     {
/* 3723 */       localPreparedStatement.setString(m, paramString1);
/*      */     }
/*      */     
/* 3726 */     if (n != 0)
/*      */     {
/* 3728 */       localPreparedStatement.setString(n, paramString3);
/*      */     }
/*      */     
/* 3731 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 3734 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 3736 */     return localOracleResultSet;
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
/*      */   public synchronized ResultSet getImportedKeys(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3798 */     return keys_query(null, null, paramString2, paramString3, "ORDER BY pktable_schem, pktable_name, key_seq");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3808 */   int importedKeyCascade = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3814 */   int importedKeyRestrict = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3820 */   int importedKeySetNull = 2;
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
/*      */   public ResultSet getExportedKeys(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 3880 */     return keys_query(paramString2, paramString3, null, null, "ORDER BY fktable_schem, fktable_name, key_seq");
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
/*      */   public ResultSet getCrossReference(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
/*      */     throws SQLException
/*      */   {
/* 3954 */     return keys_query(paramString2, paramString3, paramString5, paramString6, "ORDER BY fktable_schem, fktable_name, key_seq");
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
/*      */   public ResultSet getTypeInfo()
/*      */     throws SQLException
/*      */   {
/* 4009 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4010 */     localSQLException.fillInStackTrace();
/* 4011 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4019 */   int typeNoNulls = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4024 */   int typeNullable = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4029 */   int typeNullableUnknown = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4034 */   int typePredNone = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4039 */   int typePredChar = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4044 */   int typePredBasic = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4049 */   int typeSearchable = 3;
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
/*      */   public synchronized ResultSet getIndexInfo(String paramString1, String paramString2, String paramString3, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/* 4109 */     Statement localStatement = this.connection.createStatement();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4117 */     if (((paramString2 != null) && (paramString2.length() != 0) && (!OracleSql.isValidObjectName(paramString2))) || ((paramString3 != null) && (paramString3.length() != 0) && (!OracleSql.isValidObjectName(paramString3))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4125 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 4126 */       ((SQLException)localObject).fillInStackTrace();
/* 4127 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4132 */     if (!paramBoolean2)
/*      */     {
/* 4134 */       localObject = "analyze table " + (paramString2 == null ? "" : new StringBuilder().append(paramString2).append(".").toString()) + paramString3 + " compute statistics";
/*      */       
/*      */ 
/*      */ 
/* 4138 */       localStatement.executeUpdate((String)localObject);
/*      */     }
/*      */     
/* 4141 */     if ((paramString3.startsWith("\"")) && (paramString3.endsWith("\"")) && (paramString3.length() > 2))
/*      */     {
/*      */ 
/*      */ 
/* 4145 */       paramString3 = paramString3.substring(1, paramString3.length() - 1);
/*      */     }
/*      */     
/*      */ 
/* 4149 */     Object localObject = "select null as table_cat,\n       owner as table_schem,\n       table_name,\n       0 as NON_UNIQUE,\n       null as index_qualifier,\n       null as index_name, 0 as type,\n       0 as ordinal_position, null as column_name,\n       null as asc_or_desc,\n       num_rows as cardinality,\n       blocks as pages,\n       null as filter_condition\nfrom all_tables\nwhere table_name = '" + paramString3 + "'\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4159 */     String str1 = "";
/*      */     
/* 4161 */     if ((paramString2 != null) && (paramString2.length() > 0))
/*      */     {
/* 4163 */       if ((paramString2.startsWith("\"")) && (paramString2.endsWith("\"")) && (paramString2.length() > 2))
/*      */       {
/*      */ 
/*      */ 
/* 4167 */         paramString2 = paramString2.substring(1, paramString2.length() - 1);
/*      */       }
/*      */       
/* 4170 */       str1 = "  and owner = '" + paramString2 + "'\n";
/*      */     }
/*      */     
/*      */ 
/* 4174 */     String str2 = "select null as table_cat,\n       i.owner as table_schem,\n       i.table_name,\n       decode (i.uniqueness, 'UNIQUE', 0, 1),\n       null as index_qualifier,\n       i.index_name,\n       1 as type,\n       c.column_position as ordinal_position,\n       c.column_name,\n       null as asc_or_desc,\n       i.distinct_keys as cardinality,\n       i.leaf_blocks as pages,\n       null as filter_condition\nfrom all_indexes i, all_ind_columns c\nwhere i.table_name = '" + paramString3 + "'\n";
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
/* 4189 */     String str3 = "";
/*      */     
/* 4191 */     if ((paramString2 != null) && (paramString2.length() > 0)) {
/* 4192 */       str3 = "  and i.owner = '" + paramString2 + "'\n";
/*      */     }
/* 4194 */     String str4 = "";
/*      */     
/* 4196 */     if (paramBoolean1) {
/* 4197 */       str4 = "  and i.uniqueness = 'UNIQUE'\n";
/*      */     }
/* 4199 */     String str5 = "  and i.index_name = c.index_name\n  and i.table_owner = c.table_owner\n  and i.table_name = c.table_name\n  and i.owner = c.index_owner\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 4204 */     String str6 = "order by non_unique, type, index_name, ordinal_position\n";
/*      */     
/*      */ 
/* 4207 */     String str7 = (String)localObject + str1 + "union\n" + str2 + str3 + str4 + str5 + str6;
/*      */     
/*      */ 
/*      */ 
/* 4211 */     OracleResultSet localOracleResultSet = (OracleResultSet)localStatement.executeQuery(str7);
/*      */     
/*      */ 
/* 4214 */     localOracleResultSet.closeStatementOnClose();
/*      */     
/* 4216 */     return localOracleResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4224 */   short tableIndexStatistic = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4229 */   short tableIndexClustered = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4234 */   short tableIndexHashed = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/* 4239 */   short tableIndexOther = 3;
/*      */   
/*      */ 
/*      */   SQLException fail()
/*      */   {
/* 4244 */     SQLException localSQLException = new SQLException("Not implemented yet");
/*      */     
/* 4246 */     return localSQLException;
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
/*      */   public boolean supportsResultSetType(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4269 */     return true;
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
/*      */   public boolean supportsResultSetConcurrency(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4291 */     return true;
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
/*      */   public boolean ownUpdatesAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4310 */     return paramInt != 1003;
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
/*      */   public boolean ownDeletesAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4329 */     return paramInt != 1003;
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
/*      */   public boolean ownInsertsAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4348 */     return false;
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
/*      */   public boolean othersUpdatesAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4367 */     if (paramInt == 1005) {
/* 4368 */       return true;
/*      */     }
/* 4370 */     return false;
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
/*      */   public boolean othersDeletesAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4391 */     return false;
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
/*      */   public boolean othersInsertsAreVisible(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4412 */     return false;
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
/*      */   public boolean updatesAreDetected(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4433 */     return false;
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
/*      */   public boolean deletesAreDetected(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4453 */     return false;
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
/*      */   public boolean insertsAreDetected(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4474 */     return false;
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
/*      */   public boolean supportsBatchUpdates()
/*      */     throws SQLException
/*      */   {
/* 4493 */     return true;
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
/*      */   public ResultSet getUDTs(String paramString1, String paramString2, String paramString3, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 4542 */     int i = 0;
/*      */     
/* 4544 */     if ((paramString3 == null) || (paramString3.length() == 0))
/*      */     {
/* 4546 */       i = 0;
/*      */ 
/*      */ 
/*      */     }
/* 4550 */     else if (paramArrayOfInt == null)
/*      */     {
/* 4552 */       i = 1;
/*      */     }
/*      */     else
/*      */     {
/* 4556 */       for (int j = 0; j < paramArrayOfInt.length; j++)
/*      */       {
/* 4558 */         if (paramArrayOfInt[j] == 2002)
/*      */         {
/* 4560 */           i = 1;
/*      */           
/* 4562 */           break;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4569 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */     
/* 4571 */     localStringBuffer.append("SELECT NULL AS TYPE_CAT, owner AS TYPE_SCHEM, type_name, NULL AS CLASS_NAME, 'STRUCT' AS DATA_TYPE, NULL AS REMARKS FROM all_types ");
/*      */     
/* 4573 */     if (i != 0)
/*      */     {
/* 4575 */       localStringBuffer.append("WHERE typecode = 'OBJECT' AND owner LIKE :1 ESCAPE '/' AND type_name LIKE :2 ESCAPE '/'");
/*      */     }
/*      */     else
/*      */     {
/* 4579 */       localStringBuffer.append("WHERE 1 = 2");
/*      */     }
/*      */     
/*      */ 
/* 4583 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement(localStringBuffer.substring(0, localStringBuffer.length()));
/*      */     
/*      */ 
/* 4586 */     if (i != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 4591 */       localObject = new String[1];
/* 4592 */       String[] arrayOfString = new String[1];
/*      */       
/* 4594 */       if (SQLName.parse(paramString3, (String[])localObject, arrayOfString))
/*      */       {
/* 4596 */         localPreparedStatement.setString(1, localObject[0]);
/* 4597 */         localPreparedStatement.setString(2, arrayOfString[0]);
/*      */       }
/*      */       else
/*      */       {
/* 4601 */         if (paramString2 != null) {
/* 4602 */           localPreparedStatement.setString(1, paramString2);
/*      */         } else {
/* 4604 */           localPreparedStatement.setNull(1, 12);
/*      */         }
/* 4606 */         localPreparedStatement.setString(2, paramString3);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4611 */     Object localObject = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 4614 */     ((OracleResultSet)localObject).closeStatementOnClose();
/*      */     
/* 4616 */     return (ResultSet)localObject;
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
/*      */   public Connection getConnection()
/*      */     throws SQLException
/*      */   {
/* 4631 */     return this.connection.getWrapper();
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
/*      */   public boolean supportsSavepoints()
/*      */     throws SQLException
/*      */   {
/* 4651 */     return true;
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
/*      */   public boolean supportsNamedParameters()
/*      */     throws SQLException
/*      */   {
/* 4669 */     return true;
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
/*      */   public boolean supportsMultipleOpenResults()
/*      */     throws SQLException
/*      */   {
/* 4689 */     return false;
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
/*      */   public boolean supportsGetGeneratedKeys()
/*      */     throws SQLException
/*      */   {
/* 4707 */     return true;
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
/*      */   public ResultSet getSuperTypes(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 4758 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4759 */     localSQLException.fillInStackTrace();
/* 4760 */     throw localSQLException;
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
/*      */   public ResultSet getSuperTables(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 4805 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4806 */     localSQLException.fillInStackTrace();
/* 4807 */     throw localSQLException;
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
/* 4821 */   short attributeNoNulls = 0;
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
/* 4832 */   short attributeNullable = 1;
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
/* 4844 */   short attributeNullableUnknown = 2;
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
/*      */ 
/*      */ 
/*      */   public ResultSet getAttributes(String paramString1, String paramString2, String paramString3, String paramString4)
/*      */     throws SQLException
/*      */   {
/* 4924 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 4925 */     localSQLException.fillInStackTrace();
/* 4926 */     throw localSQLException;
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
/*      */   public boolean supportsResultSetHoldability(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4952 */     if (paramInt == 1) {
/* 4953 */       return true;
/*      */     }
/* 4955 */     return false;
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
/*      */   public int getResultSetHoldability()
/*      */     throws SQLException
/*      */   {
/* 4978 */     return 1;
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
/*      */   public int getDatabaseMajorVersion()
/*      */     throws SQLException
/*      */   {
/* 4994 */     return this.connection.getVersionNumber() / 1000;
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
/*      */   public int getDatabaseMinorVersion()
/*      */     throws SQLException
/*      */   {
/* 5010 */     return this.connection.getVersionNumber() % 1000 / 100;
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
/*      */   public int getJDBCMajorVersion()
/*      */     throws SQLException
/*      */   {
/* 5027 */     return DRIVER_MAJOR_VERSION;
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
/*      */   public int getJDBCMinorVersion()
/*      */     throws SQLException
/*      */   {
/* 5044 */     return DRIVER_MINOR_VERSION;
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
/* 5058 */   int sqlStateXOpen = 1;
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
/* 5069 */   int sqlStateSQL99 = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static final String sqlWildcardRegex = "^%|^_|[^/]%|[^/]_";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSQLStateType()
/*      */     throws SQLException
/*      */   {
/* 5085 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean locatorsUpdateCopy()
/*      */     throws SQLException
/*      */   {
/* 5097 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean supportsStatementPooling()
/*      */     throws SQLException
/*      */   {
/* 5109 */     return true;
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
/*      */   public static String getDriverNameInfo()
/*      */     throws SQLException
/*      */   {
/* 5127 */     return DRIVER_NAME;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static String getDriverVersionInfo()
/*      */     throws SQLException
/*      */   {
/* 5141 */     return DRIVER_VERSION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static int getDriverMajorVersionInfo()
/*      */   {
/* 5154 */     return DRIVER_MAJOR_VERSION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static int getDriverMinorVersionInfo()
/*      */   {
/* 5167 */     return DRIVER_MINOR_VERSION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static String getLobPrecision()
/*      */     throws SQLException
/*      */   {
/* 5180 */     return "-1";
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
/*      */   public long getLobMaxLength()
/*      */     throws SQLException
/*      */   {
/* 5195 */     return this.connection.getVersionNumber() >= 10000 ? Long.MAX_VALUE : LOB_MAXLENGTH_32BIT;
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
/*      */   public RowIdLifetime getRowIdLifetime()
/*      */     throws SQLException
/*      */   {
/* 5213 */     return RowIdLifetime.ROWID_VALID_FOREVER;
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
/*      */ 
/*      */   public ResultSet getSchemas(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 5240 */     if (paramString2 == null) {
/* 5241 */       return getSchemas();
/*      */     }
/*      */     
/* 5244 */     String str = "SELECT username AS table_schem, null as table_catalog FROM all_users WHERE username LIKE ? ORDER BY table_schem";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5249 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement(str);
/* 5250 */     localPreparedStatement.setString(1, paramString2);
/* 5251 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/* 5253 */     localOracleResultSet.closeStatementOnClose();
/* 5254 */     return localOracleResultSet;
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
/*      */   public boolean supportsStoredFunctionsUsingCallSyntax()
/*      */     throws SQLException
/*      */   {
/* 5270 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean autoCommitFailureClosesAllResultSets()
/*      */     throws SQLException
/*      */   {
/* 5283 */     return false;
/*      */   }
/*      */   
/*      */   public ResultSet getClientInfoProperties()
/*      */     throws SQLException
/*      */   {
/* 5289 */     Statement localStatement = this.connection.createStatement();
/* 5290 */     return localStatement.executeQuery("select NULL NAME, -1 MAX_LEN, NULL DEFAULT_VALUE, NULL DESCRIPTION  from dual where 0 = 1");
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
/*      */   public ResultSet getFunctions(String paramString1, String paramString2, String paramString3)
/*      */     throws SQLException
/*      */   {
/* 5311 */     String str1 = "SELECT\n  -- Standalone functions\n  NULL AS function_cat,\n  owner AS function_schem,\n  object_name AS function_name,\n  'Standalone function' AS remarks,\n  0 AS function_type,\n  NULL AS specific_name\nFROM all_objects\nWHERE object_type = 'FUNCTION'\n  AND owner LIKE :1 ESCAPE '/'\n  AND object_name LIKE :2 ESCAPE '/'\n";
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
/* 5325 */     String str2 = "SELECT\n  -- Packaged functions\n  package_name AS function_cat,\n  owner AS function_schem,\n  object_name AS function_name,\n  'Packaged function' AS remarks,\n  decode (data_type, 'TABLE', 2, 'PL/SQL TABLE', 2, 1) AS function_type,\n  NULL AS specific_name\nFROM all_arguments\nWHERE argument_name IS NULL\n  AND in_out = 'OUT'\n  AND data_level = 0\n";
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
/* 5341 */     String str3 = "  AND package_name LIKE :3 ESCAPE '/'\n  AND owner LIKE :4 ESCAPE '/'\n  AND object_name LIKE :5 ESCAPE '/'\n";
/*      */     
/*      */ 
/*      */ 
/* 5345 */     String str4 = "  AND package_name IS NOT NULL\n  AND owner LIKE :6 ESCAPE '/'\n  AND object_name LIKE :7 ESCAPE '/'\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5351 */     String str5 = "ORDER BY function_schem, function_name\n";
/*      */     
/* 5353 */     PreparedStatement localPreparedStatement = null;
/* 5354 */     String str6 = null;
/*      */     
/*      */ 
/* 5357 */     String str7 = paramString2;
/*      */     
/* 5359 */     if (paramString2 == null) {
/* 5360 */       str7 = "%";
/* 5361 */     } else if (paramString2.equals("")) {
/* 5362 */       str7 = getUserName().toUpperCase();
/*      */     }
/* 5364 */     String str8 = paramString3;
/*      */     
/* 5366 */     if (paramString3 == null) {
/* 5367 */       str8 = "%";
/* 5368 */     } else if (paramString3.equals(""))
/*      */     {
/* 5370 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74);
/* 5371 */       ((SQLException)localObject).fillInStackTrace();
/* 5372 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/* 5375 */     if (paramString1 == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 5380 */       str6 = str1 + "UNION ALL " + str2 + str4 + str5;
/*      */       
/*      */ 
/*      */ 
/* 5384 */       localPreparedStatement = this.connection.prepareStatement(str6);
/*      */       
/* 5386 */       localPreparedStatement.setString(1, str7);
/* 5387 */       localPreparedStatement.setString(2, str8);
/* 5388 */       localPreparedStatement.setString(3, str7);
/* 5389 */       localPreparedStatement.setString(4, str8);
/*      */     }
/* 5391 */     else if (paramString1.equals(""))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 5396 */       str6 = str1;
/*      */       
/* 5398 */       localPreparedStatement = this.connection.prepareStatement(str6);
/*      */       
/* 5400 */       localPreparedStatement.setString(1, str7);
/* 5401 */       localPreparedStatement.setString(2, str8);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 5407 */       str6 = str2 + str3 + str5;
/*      */       
/* 5409 */       localPreparedStatement = this.connection.prepareStatement(str6);
/*      */       
/* 5411 */       localPreparedStatement.setString(1, str7);
/* 5412 */       localPreparedStatement.setString(2, str7);
/* 5413 */       localPreparedStatement.setString(3, str8);
/*      */     }
/*      */     
/*      */ 
/* 5417 */     Object localObject = (OracleResultSet)localPreparedStatement.executeQuery();
/*      */     
/*      */ 
/* 5420 */     ((OracleResultSet)localObject).closeStatementOnClose();
/*      */     
/* 5422 */     return (ResultSet)localObject;
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 5437 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 5439 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 5440 */     localSQLException.fillInStackTrace();
/* 5441 */     throw localSQLException;
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 5458 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 5460 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 5461 */     localSQLException.fillInStackTrace();
/* 5462 */     throw localSQLException;
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 5479 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 5484 */   protected static Pattern sqlWildcardPattern = null;
/*      */   protected static final String sqlEscapeRegex = "/";
/* 5486 */   protected static Pattern sqlEscapePattern = null;
/*      */   
/*      */ 
/*      */   protected boolean hasSqlWildcard(String paramString)
/*      */   {
/* 5491 */     if (sqlWildcardPattern == null)
/* 5492 */       sqlWildcardPattern = Pattern.compile("^%|^_|[^/]%|[^/]_");
/* 5493 */     Matcher localMatcher = sqlWildcardPattern.matcher(paramString);
/* 5494 */     return localMatcher.find();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected String stripSqlEscapes(String paramString)
/*      */   {
/* 5501 */     if (sqlEscapePattern == null)
/* 5502 */       sqlEscapePattern = Pattern.compile("/");
/* 5503 */     Matcher localMatcher = sqlEscapePattern.matcher(paramString);
/* 5504 */     StringBuffer localStringBuffer = new StringBuffer();
/* 5505 */     while (localMatcher.find())
/*      */     {
/* 5507 */       localMatcher.appendReplacement(localStringBuffer, "");
/*      */     }
/* 5509 */     localMatcher.appendTail(localStringBuffer);
/* 5510 */     return localStringBuffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */   String getUnpackagedProcedureColumnsNoWildcardsPlsql()
/*      */     throws SQLException
/*      */   {
/* 5517 */     String str1 = "declare\n  in_owner varchar2(32) := null;\n  in_name varchar2(32) := null;\n  my_user_name varchar2(32) := null;\n  cnt number := 0;\n  temp_owner varchar2(32) := null;\n  temp_name  varchar2(32):= null;\n  out_owner varchar2(32) := null;\n  out_name  varchar2(32):= null;\n  loc varchar2(32) := null;\n  status number := 0;\n  TYPE recursion_check_type is table of number index by varchar2(65);\n  recursion_check recursion_check_type;\n  dotted_name varchar2(65);\n  recursion_cnt number := 0;\n  xxx SYS_REFCURSOR;\nbegin\n  in_owner := ?;\n  in_name := ?;\n  select user into my_user_name from dual;\n  if( my_user_name = in_owner ) then\n    select count(*) into cnt from user_procedures where object_name = in_name;\n    if( cnt = 1 ) then\n      out_owner := in_owner;\n      out_name := in_name;\n      loc := 'USER_PROCEDURES';\n    end if;\n  else\n    select count(*) into cnt from all_arguments where owner = in_owner and object_name = in_name;\n    if( cnt = 1 ) then\n      out_owner := in_owner;\n      out_name := in_name;\n      loc := 'ALL_ARGUMENTS';\n    end if;\n  end if;\n  if loc is null then\n    temp_owner := in_owner;\n    temp_name := in_name;\n    loop\n      begin\n        dotted_name := temp_owner || '.' ||temp_name;\n        begin\n          recursion_cnt := recursion_check(dotted_name );\n          status := -1;\n          exit;\n        exception\n        when NO_DATA_FOUND then\n          recursion_check( dotted_name ) := 1;\n        end;\n        select table_owner, table_name into out_owner, out_name from all_synonyms \n          where  owner = temp_owner and synonym_name = temp_name;\n        cnt := cnt + 1;\n        temp_owner  := out_owner;\n        temp_name := out_name;\n        exception\n        when NO_DATA_FOUND then\n          exit;\n        end;\n      end loop;\n      if( not(out_owner is null) ) then\n        loc := 'ALL_SYNONYMS';\n    end if;\n  end if;\n";
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
/* 5582 */     int i = this.connection.getVersionNumber();
/* 5583 */     String str2 = "if( status = 0 ) then \n open xxx for \n";
/*      */     
/* 5585 */     String str3 = "SELECT package_name AS procedure_cat,\n       owner AS procedure_schem,\n       object_name AS procedure_name,\n       argument_name AS column_name,\n       DECODE(position, 0, 5,\n                        DECODE(in_out, 'IN', 1,\n                                       'OUT', 4,\n                                       'IN/OUT', 2,\n                                       0)) AS column_type,\n       DECODE (data_type, 'CHAR', 1,\n                          'VARCHAR2', 12,\n                          'NUMBER', 3,\n                          'LONG', -1,\n                          'DATE', " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + "                          'RAW', -3,\n" + "                          'LONG RAW', -4,\n" + "                          'TIMESTAMP', 93, \n" + "                          'TIMESTAMP WITH TIME ZONE', -101, \n" + "               'TIMESTAMP WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR TO MONTH', -103, \n" + "               'INTERVAL DAY TO SECOND', -104, \n" + "               'BINARY_FLOAT', 100, 'BINAvRY_DOUBLE', 101," + "               1111) AS data_type,\n" + "       DECODE(data_type, 'OBJECT', type_owner || '.' || type_name, " + "              data_type) AS type_name,\n" + "       DECODE (data_precision, NULL, data_length,\n" + "                               data_precision) AS precision,\n" + "       data_length AS length,\n" + "       data_scale AS scale,\n" + "       10 AS radix,\n" + "       1 AS nullable,\n" + "       NULL AS remarks,\n" + "       default_value AS column_def,\n" + "       NULL as sql_data_type,\n" + "       NULL AS sql_datetime_sub,\n" + "       DECODE(data_type,\n" + "                         'CHAR', 32767,\n" + "                         'VARCHAR2', 32767,\n" + "                         'LONG', 32767,\n" + "                         'RAW', 32767,\n" + "                         'LONG RAW', 32767,\n" + "                         NULL) AS char_octet_length,\n" + "       (sequence - 1) AS ordinal_position,\n" + "       'YES' AS is_nullable,\n" + "       NULL AS specific_name,\n" + "       sequence,\n" + "       overload,\n" + "       default_value\n";
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
/* 5642 */     String str4 = "FROM all_arguments a";
/*      */     
/* 5644 */     String str5 = "WHERE a.owner = out_owner \n  AND a.object_name = out_name\n AND a.argument_name LIKE ? ESCAPE '/'\n AND data_level = 0\n AND package_name is null\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5649 */     String str6 = "ORDER BY procedure_schem, procedure_name, overload, sequence\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5655 */     String str7 = str2;
/* 5656 */     str7 = str7 + str3;
/*      */     
/* 5658 */     str7 = str7 + str4;
/*      */     
/* 5660 */     str7 = str7 + "\n" + str5;
/*      */     
/* 5662 */     str7 = str7 + "\n" + str6;
/*      */     
/* 5664 */     String str8 = "; \n end if;\n  ? := xxx; ? := status;\n end;";
/*      */     
/* 5666 */     String str9 = str1 + str7 + str8;
/* 5667 */     return str9;
/*      */   }
/*      */   
/*      */ 
/*      */   String getPackagedProcedureColumnsNoWildcardsPlsql()
/*      */     throws SQLException
/*      */   {
/* 5674 */     String str1 = "declare\n  in_package_name varchar2(32) := null;\n  in_owner varchar2(32) := null;\n  in_name varchar2(32) := null;\n  my_user_name varchar2(32) := null;\n  cnt number := 0;\n  temp_package_name varchar2(32) := null;\n  temp_owner varchar2(32) := null;\n  out_package_name varchar2(32) := null;\n  out_owner varchar2(32) := null;\n  loc varchar2(32) := null;\n  status number := 0;\n  TYPE recursion_check_type is table of number index by varchar2(65);\n  recursion_check recursion_check_type;\n  dotted_name varchar2(65);\n  recursion_cnt number := 0;\n  xxx SYS_REFCURSOR;\nbegin\n  in_package_name := ?;\n  in_owner := ?;\n  in_name := ?;\n  select user into my_user_name from dual;\n  if( my_user_name = in_owner ) then\n    select count(*) into cnt from user_arguments where package_name = in_package_name;\n    if( cnt >= 1 ) then\n      out_owner := in_owner;\n      out_package_name := in_package_name;\n      loc := 'USER_ARGUMENTS';\n    end if;\n  else\n    select count(*) into cnt from all_arguments where owner = in_owner and package_name = in_package_name;\n    if( cnt >= 1 ) then\n      out_owner := in_owner;\n      out_package_name := in_package_name;\n      loc := 'ALL_ARGUMENTS';\n    end if;\n  end if;\n  if loc is null then\n  temp_owner := in_owner;\n  temp_package_name := in_package_name;\n  loop\n    begin\n      dotted_name := temp_owner || '.' ||temp_package_name;\n      begin\n        recursion_cnt := recursion_check(dotted_name );\n        status := -1;\n        exit;\n      exception\n      when NO_DATA_FOUND then\n        recursion_check( dotted_name ) := 1;\n      end;\n      select table_owner, table_name into out_owner, out_package_name from all_synonyms \n        where  owner = temp_owner and synonym_name = temp_package_name;\n      cnt := cnt + 1;\n      temp_owner  := out_owner;\n      temp_package_name := out_package_name;\n      exception\n      when NO_DATA_FOUND then\n        exit;\n      end;\n    end loop;\n    if( not(out_owner is null) ) then\n      loc := 'ALL_SYNONYMS';\n    end if;\n  end if;\n";
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
/* 5742 */     int i = this.connection.getVersionNumber();
/* 5743 */     String str2 = "if( status = 0 ) then \n open xxx for \n";
/*      */     
/* 5745 */     String str3 = "SELECT package_name AS procedure_cat,\n       owner AS procedure_schem,\n       object_name AS procedure_name,\n       argument_name AS column_name,\n       DECODE(position, 0, 5,\n                        DECODE(in_out, 'IN', 1,\n                                       'OUT', 4,\n                                       'IN/OUT', 2,\n                                       0)) AS column_type,\n       DECODE (data_type, 'CHAR', 1,\n                          'VARCHAR2', 12,\n                          'NUMBER', 3,\n                          'LONG', -1,\n                          'DATE', " + (this.connection.getMapDateToTimestamp() ? "93,\n" : "91,\n") + "                          'RAW', -3,\n" + "                          'LONG RAW', -4,\n" + "                          'TIMESTAMP', 93, \n" + "                          'TIMESTAMP WITH TIME ZONE', -101, \n" + "               'TIMESTAMP WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR TO MONTH', -103, \n" + "               'INTERVAL DAY TO SECOND', -104, \n" + "               'BINARY_FLOAT', 100, 'BINAvRY_DOUBLE', 101," + "               1111) AS data_type,\n" + "       DECODE(data_type, 'OBJECT', type_owner || '.' || type_name, " + "              data_type) AS type_name,\n" + "       DECODE (data_precision, NULL, data_length,\n" + "                               data_precision) AS precision,\n" + "       data_length AS length,\n" + "       data_scale AS scale,\n" + "       10 AS radix,\n" + "       1 AS nullable,\n" + "       NULL AS remarks,\n" + "       default_value AS column_def,\n" + "       NULL as sql_data_type,\n" + "       NULL AS sql_datetime_sub,\n" + "       DECODE(data_type,\n" + "                         'CHAR', 32767,\n" + "                         'VARCHAR2', 32767,\n" + "                         'LONG', 32767,\n" + "                         'RAW', 32767,\n" + "                         'LONG RAW', 32767,\n" + "                         NULL) AS char_octet_length,\n" + "       (sequence - 1) AS ordinal_position,\n" + "       'YES' AS is_nullable,\n" + "       NULL AS specific_name,\n" + "       sequence,\n" + "       overload,\n" + "       default_value\n";
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
/* 5802 */     String str4 = "FROM all_arguments a";
/*      */     
/* 5804 */     String str5 = "WHERE a.owner = out_owner \n  AND a.object_name LIKE in_name ESCAPE '/' \n AND a.argument_name LIKE ? ESCAPE '/' \n AND data_level = 0\n AND package_name = out_package_name\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 5809 */     String str6 = "ORDER BY procedure_schem, procedure_name, overload, sequence\n";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5815 */     String str7 = str2;
/* 5816 */     str7 = str7 + str3;
/*      */     
/* 5818 */     str7 = str7 + str4;
/*      */     
/* 5820 */     str7 = str7 + "\n" + str5;
/*      */     
/* 5822 */     str7 = str7 + "\n" + str6;
/*      */     
/* 5824 */     String str8 = "; \n end if;\n  ? := xxx; ? := status;\n end;";
/*      */     
/* 5826 */     String str9 = str1 + str7 + str8;
/* 5827 */     return str9;
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleTypeMetaData getOracleTypeMetaData(String paramString)
/*      */     throws SQLException
/*      */   {
/* 5834 */     return TypeDescriptor.getTypeDescriptor(paramString, this.connection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 5839 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/OracleDatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */