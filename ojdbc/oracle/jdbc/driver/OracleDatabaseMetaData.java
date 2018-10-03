package oracle.jdbc.driver;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.internal.OracleResultSet;
class OracleDatabaseMetaData
  extends oracle.jdbc.OracleDatabaseMetaData
{
  public OracleDatabaseMetaData(oracle.jdbc.internal.OracleConnection paramOracleConnection)
  {
/*  37 */     super(paramOracleConnection);
  }
  
  public OracleDatabaseMetaData(OracleConnection paramOracleConnection)
  {
/*  44 */     this(paramOracleConnection);
  }
  
  public synchronized ResultSet getColumns(String paramString1, String paramString2, String paramString3, String paramString4)
    throws SQLException
  {
/* 114 */     boolean bool = this.connection.getIncludeSynonyms();
/* 115 */     if ((bool) && (paramString2 != null) && (!hasSqlWildcard(paramString2)) && (paramString3 != null) && (!hasSqlWildcard(paramString3)))
    {
/* 121 */       return getColumnsNoWildcards(stripSqlEscapes(paramString2), stripSqlEscapes(paramString3), paramString4);
    }
    
/* 125 */     return getColumnsWithWildcards(paramString2, paramString3, paramString4, bool);
  }
  
  ResultSet getColumnsNoWildcards(String paramString1, String paramString2, String paramString3)
    throws SQLException
  {
/* 140 */     String str = getColumnsNoWildcardsPlsql();
/* 141 */     CallableStatement localCallableStatement = this.connection.prepareCall(str);
/* 142 */     localCallableStatement.setString(1, paramString1);
/* 143 */     localCallableStatement.setString(2, paramString2);
/* 144 */     localCallableStatement.setString(3, paramString3 == null ? "%" : paramString3);
/* 145 */     localCallableStatement.registerOutParameter(4, -10);
/* 146 */     localCallableStatement.execute();
/* 147 */     ResultSet localResultSet = ((OracleCallableStatement)localCallableStatement).getCursor(4);
/* 148 */     ((OracleResultSet)localResultSet).closeStatementOnClose();
/* 149 */     return localResultSet;
  }
  
  ResultSet getColumnsWithWildcards(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
    throws SQLException
  {
/* 161 */     int i = this.connection.getVersionNumber();
    
/* 174 */     String str1 = "SELECT ";
/* 175 */     String str2 = " NULL AS table_cat,\n";
    
/* 181 */     String str3 = "";
/* 182 */     if (((i >= 10200 ? 1 : 0) & (i < 11100 ? 1 : 0) & paramBoolean) != 0) { str3 = "/*+ CHOOSE */";
    }
/* 184 */     String str4 = "       t.owner AS table_schem,\n       t.table_name AS table_name,\n";
    
/* 187 */     String str5 = "       DECODE(s.owner, NULL, t.owner, s.owner)\n              AS table_schem,\n       DECODE(s.synonym_name, NULL, t.table_name, s.synonym_name)\n              AS table_name,\n";
    
/* 193 */     String str6 = "         DECODE (t.data_type, 'CHAR', t.char_length,                   'VARCHAR', t.char_length,                   'VARCHAR2', t.char_length,                   'NVARCHAR2', t.char_length,                   'NCHAR', t.char_length,                   'NUMBER', 0,           t.data_length)";
    
/* 202 */     String str7 = "       t.column_name AS column_name,\n       DECODE (t.data_type, 'CHAR', 1, 'VARCHAR2', 12, 'NUMBER', 3,\n               'LONG', -1, 'DATE', " + (((PhysicalConnection)this.connection).mapDateToTimestamp ? "93" : "91") + ", 'RAW', -3, 'LONG RAW', -4,  \n" + "               'BLOB', 2004, 'CLOB', 2005, 'BFILE', -13, 'FLOAT', 6, \n" + "               'TIMESTAMP(6)', 93, 'TIMESTAMP(6) WITH TIME ZONE', -101, \n" + "               'TIMESTAMP(6) WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR(2) TO MONTH', -103, \n" + "               'INTERVAL DAY(2) TO SECOND(6)', -104, \n" + "               'BINARY_FLOAT', 100, 'BINARY_DOUBLE', 101, \n" + "               'XMLTYPE', 2009, \n" + "               1111)\n" + "              AS data_type,\n" + "       t.data_type AS type_name,\n" + "       DECODE (t.data_precision, " + "               null, DECODE(t.data_type, " + "                       'NUMBER', DECODE(t.data_scale, " + "                                   null, " + (((PhysicalConnection)this.connection).j2ee13Compliant ? "38" : "0") + "                                   , 38), " + (i > 9000 ? str6 : "t.data_length") + "                           )," + "         t.data_precision)\n" + "              AS column_size,\n" + "       0 AS buffer_length,\n" + "       DECODE (t.data_type, " + "               'NUMBER', DECODE(t.data_precision, " + "                                null, DECODE(t.data_scale, " + "                                             null, " + (((PhysicalConnection)this.connection).j2ee13Compliant ? "0" : "-127") + "                                             , t.data_scale), " + "                                 t.data_scale), " + "               t.data_scale) AS decimal_digits,\n" + "       10 AS num_prec_radix,\n" + "       DECODE (t.nullable, 'N', 0, 1) AS nullable,\n";
    
/* 240 */     String str8 = "       c.comments AS remarks,\n";
    
/* 242 */     String str9 = "       NULL AS remarks,\n";
    
/* 244 */     String str10 = "       t.data_default AS column_def,\n       0 AS sql_data_type,\n       0 AS sql_datetime_sub,\n       t.data_length AS char_octet_length,\n       t.column_id AS ordinal_position,\n       DECODE (t.nullable, 'N', 'NO', 'YES') AS is_nullable,\n";
    
/* 251 */     String str11 = "         null as SCOPE_CATALOG,\n       null as SCOPE_SCHEMA,\n       null as SCOPE_TABLE,\n       null as SOURCE_DATA_TYPE,\n       'NO' as IS_AUTOINCREMENT\n";
    
/* 258 */     String str12 = "FROM all_tab_columns t";
    
/* 260 */     String str13 = ", all_synonyms s";
/* 261 */     String str14 = ", all_col_comments c";
    
/* 263 */     String str15 = "WHERE t.owner LIKE :1 ESCAPE '/'\n  AND t.table_name LIKE :2 ESCAPE '/'\n  AND t.column_name LIKE :3 ESCAPE '/'\n";
    
/* 267 */     String str16 = "WHERE (t.owner LIKE :4 ESCAPE '/' OR\n       (s.owner LIKE :5 ESCAPE '/' AND t.owner = s.table_owner))\n  AND (t.table_name LIKE :6 ESCAPE '/' OR\n       s.synonym_name LIKE :7 ESCAPE '/')\n  AND t.column_name LIKE :8 ESCAPE '/'\n";
    
/* 274 */     String str17 = "  AND t.owner = c.owner (+)\n  AND t.table_name = c.table_name (+)\n  AND t.column_name = c.column_name (+)\n";
    
/* 278 */     String str18 = "  AND s.table_name (+) = t.table_name\n  AND ((DECODE(s.owner, t.owner, 'OK',\n                       'PUBLIC', 'OK',\n                       NULL, 'OK',\n                       'NOT OK') = 'OK') OR\n       (t.owner LIKE :9 AND t.owner = s.table_owner) OR\n       (s.owner LIKE :10 AND t.owner = s.table_owner))";
    
/* 287 */     String str19 = "ORDER BY table_schem, table_name, ordinal_position\n";
    
/* 293 */     String str20 = str1 + str3 + str2;
    
/* 295 */     if (paramBoolean) {
/* 296 */       str20 = str20 + str5;
    } else {
/* 298 */       str20 = str20 + str4;
    }
/* 300 */     str20 = str20 + str7;
    
/* 302 */     if (this.connection.getRemarksReporting()) {
/* 303 */       str20 = str20 + str8;
    } else {
/* 305 */       str20 = str20 + str9;
    }
/* 307 */     str20 = str20 + str10 + str11 + str12;
    
/* 309 */     if (this.connection.getRemarksReporting()) {
/* 310 */       str20 = str20 + str14;
    }
/* 312 */     if (paramBoolean) {
/* 313 */       str20 = str20 + str13;
    }
/* 315 */     if (paramBoolean) {
/* 316 */       str20 = str20 + "\n" + str16;
    } else {
/* 318 */       str20 = str20 + "\n" + str15;
    }
/* 320 */     if (this.connection.getRemarksReporting()) {
/* 321 */       str20 = str20 + str17;
    }
/* 323 */     if (this.connection.getIncludeSynonyms()) {
/* 324 */       str20 = str20 + str18;
    }
/* 326 */     str20 = str20 + "\n" + str19;
    
/* 329 */     PreparedStatement localPreparedStatement = this.connection.prepareStatement(str20);
    
/* 331 */     if (paramBoolean)
    {
/* 333 */       localPreparedStatement.setString(1, paramString1 == null ? "%" : paramString1);
/* 334 */       localPreparedStatement.setString(2, paramString1 == null ? "%" : paramString1);
/* 335 */       localPreparedStatement.setString(3, paramString2 == null ? "%" : paramString2);
/* 336 */       localPreparedStatement.setString(4, paramString2 == null ? "%" : paramString2);
/* 337 */       localPreparedStatement.setString(5, paramString3 == null ? "%" : paramString3);
/* 338 */       localPreparedStatement.setString(6, paramString1 == null ? "%" : paramString1);
/* 339 */       localPreparedStatement.setString(7, paramString1 == null ? "%" : paramString1);
    }
    else
    {
/* 343 */       localPreparedStatement.setString(1, paramString1 == null ? "%" : paramString1);
/* 344 */       localPreparedStatement.setString(2, paramString2 == null ? "%" : paramString2);
/* 345 */       localPreparedStatement.setString(3, paramString3 == null ? "%" : paramString3);
    }
    
/* 348 */     OracleResultSet localOracleResultSet = (OracleResultSet)localPreparedStatement.executeQuery();
    
/* 351 */     localOracleResultSet.closeStatementOnClose();
    
/* 353 */     return localOracleResultSet;
  }
  
  public ResultSet getTypeInfo()
    throws SQLException
  {
/* 406 */     Statement localStatement = this.connection.createStatement();
/* 407 */     int i = this.connection.getVersionNumber();
    
/* 481 */     String str1 = "union select\n 'CHAR' as type_name, 1 as data_type, " + (i >= 8100 ? 2000 : 255) + " as precision,\n" + " '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 1 as case_sensitive, 3 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'CHAR' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 491 */     String str2 = "union select\n 'VARCHAR2' as type_name, 12 as data_type, " + (i >= 8100 ? 4000 : 2000) + " as precision,\n" + " '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 1 as case_sensitive, 3 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'VARCHAR2' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 511 */     String str3 = "union select\n 'DATE' as type_name, " + (((PhysicalConnection)this.connection).mapDateToTimestamp ? "93" : "91") + "as data_type, 7 as precision,\n" + " NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 0 as case_sensitive, 3 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'DATE' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 583 */     String str4 = "union select\n 'RAW' as type_name, -3 as data_type, " + (i >= 8100 ? 2000 : 255) + " as precision,\n" + " '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 0 as case_sensitive, 3 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'RAW' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 603 */     String str5 = "-1";
    
/* 605 */     String str6 = "union select\n 'BLOB' as type_name, 2004 as data_type, " + str5 + " as precision,\n" + " null as literal_prefix, null as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 0 as case_sensitive, 0 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'BLOB' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 615 */     String str7 = "union select\n 'CLOB' as type_name, 2005 as data_type, " + str5 + " as precision,\n" + " '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n" + " 1 as nullable, 1 as case_sensitive, 0 as searchable,\n" + " 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n" + " 'CLOB' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n" + " NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\n" + "from dual\n";
    
/* 658 */     String str8 = "select\n 'NUMBER' as type_name, 2 as data_type, 38 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n 'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + str1 + str2 + str3 + "union select\n 'DATE' as type_name, 92 as data_type, 7 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'DATE' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'TIMESTAMP' as type_name, 93 as data_type, 11 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'TIMESTAMP' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'TIMESTAMP WITH TIME ZONE' as type_name, -101 as data_type, 13 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'TIMESTAMP WITH TIME ZONE' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'TIMESTAMP WITH LOCAL TIME ZONE' as type_name, -102 as data_type, 11 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'TIMESTAMP WITH LOCAL TIME ZONE' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'INTERVALYM' as type_name, -103 as data_type, 5 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'INTERVALYM' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'INTERVALDS' as type_name, -104 as data_type, 4 as precision,\n NULL as literal_prefix, NULL as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 3 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'INTERVALDS' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + str4 + "union select\n 'LONG' as type_name, -1 as data_type, 2147483647 as precision,\n '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n 1 as nullable, 1 as case_sensitive, 0 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'LONG' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'LONG RAW' as type_name, -4 as data_type, 2147483647 as precision,\n '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n 1 as nullable, 0 as case_sensitive, 0 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'LONG RAW' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'NUMBER' as type_name, -7 as data_type, 1 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \n'(1)' as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'NUMBER' as type_name, -6 as data_type, 3 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \n'(3)' as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'NUMBER' as type_name, 5 as data_type, 5 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \n'(5)' as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'NUMBER' as type_name, 4 as data_type, 10 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \n'(10)' as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'NUMBER' as type_name, -5 as data_type, 38 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \nNULL as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'NUMBER' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'FLOAT' as type_name, 6 as data_type, 63 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \nNULL as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'FLOAT' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select 'REAL' as type_name, 7 as data_type, 63 as precision,\nNULL as literal_prefix, NULL as literal_suffix, \nNULL as create_params, 1 as nullable, 0 as case_sensitive, 3 as searchable,\n0 as unsigned_attribute, 1 as fixed_prec_scale, 0 as auto_increment,\n'REAL' as local_type_name, -84 as minimum_scale, 127 as maximum_scale,\nNULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + (i >= 8100 ? str6 + str7 + "union select\n 'REF' as type_name, 2006 as data_type, 0 as precision,\n '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n 1 as nullable, 1 as case_sensitive, 0 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'REF' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'ARRAY' as type_name, 2003 as data_type, 0 as precision,\n '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n 1 as nullable, 1 as case_sensitive, 0 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'ARRAY' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" + "union select\n 'STRUCT' as type_name, 2002 as data_type, 0 as precision,\n '''' as literal_prefix, '''' as literal_suffix, NULL as create_params,\n 1 as nullable, 1 as case_sensitive, 0 as searchable,\n 0 as unsigned_attribute, 0 as fixed_prec_scale, 0 as auto_increment,\n 'STRUCT' as local_type_name, 0 as minimum_scale, 0 as maximum_scale,\n NULL as sql_data_type, NULL as sql_datetime_sub, 10 as num_prec_radix\nfrom dual\n" : "") + "order by data_type\n";
    
/* 676 */     OracleResultSet localOracleResultSet = (OracleResultSet)localStatement.executeQuery(str8);
    
/* 679 */     localOracleResultSet.closeStatementOnClose();
    
/* 681 */     return localOracleResultSet;
  }
  
  String getColumnsNoWildcardsPlsql()
    throws SQLException
  {
/* 688 */     String str1 = "declare\n  in_owner varchar2(32) := null;\n  in_name varchar2(32) := null;\n  my_user_name varchar2(32) := null;\n  cnt number := 0;\n  out_owner varchar2(32) := null;\n  out_name  varchar2(32):= null;\n  loc varchar2(32) := null;\n  xxx SYS_REFCURSOR;\nbegin\n  in_owner := ?;\n  in_name := ?;\n  select user into my_user_name from dual;\n  if( my_user_name = in_owner ) then\n    select count(*) into cnt from user_tables where table_name = in_name;\n    if( cnt = 1 ) then\n      out_owner := in_owner;\n      out_name := in_name;\n      loc := 'USER_TABLES';\n    else\n      begin\n        select table_owner, table_name into out_owner, out_name from user_synonyms where synonym_name = in_name;\n      exception\n        when NO_DATA_FOUND then\n        out_owner := null;\n        out_name := null;\n      end;\n      if( not(out_name is null) ) then\n        loc := 'USER_SYNONYMS';\n      end if;\n    end if;\n  else\n    select count(*) into cnt from all_tables where owner = in_owner and table_name = in_name;\n    if( cnt = 1 ) then\n      out_owner := in_owner;\n      out_name := in_name;\n      loc := 'ALL_TABLES';\n    else\n      begin\n        select table_owner, table_name into out_owner, out_name from all_synonyms \n          where  owner = in_owner and synonym_name = in_name;\n      exception\n        when NO_DATA_FOUND then\n          out_owner := null;\n          out_name := null;\n      end;\n      if( not(out_owner is null) ) then\n        loc := 'ALL_SYNONYMS';\n      end if;\n    end if;\n  end if;\n";
    
/* 741 */     int i = this.connection.getVersionNumber();
/* 742 */     String str2 = "open xxx for SELECT NULL AS table_cat,\n";
    
/* 744 */     String str3 = "       in_owner AS table_schem,\n       in_name AS table_name,\n";
    
/* 747 */     String str4 = "         DECODE (t.data_type, 'CHAR', t.char_length,                   'VARCHAR', t.char_length,                   'VARCHAR2', t.char_length,                   'NVARCHAR2', t.char_length,                   'NCHAR', t.char_length,                   'NUMBER', 0,           t.data_length)";
    
/* 756 */     String str5 = "       t.column_name AS column_name,\n       DECODE (t.data_type, 'CHAR', 1, 'VARCHAR2', 12, 'NUMBER', 3,\n               'LONG', -1, 'DATE', " + (this.connection.getMapDateToTimestamp() ? "93" : "91") + ", 'RAW', -3, 'LONG RAW', -4,  \n" + "               'BLOB', 2004, 'CLOB', 2005, 'BFILE', -13, 'FLOAT', 6, \n" + "               'TIMESTAMP(6)', 93, 'TIMESTAMP(6) WITH TIME ZONE', -101, \n" + "               'TIMESTAMP(6) WITH LOCAL TIME ZONE', -102, \n" + "               'INTERVAL YEAR(2) TO MONTH', -103, \n" + "               'INTERVAL DAY(2) TO SECOND(6)', -104, \n" + "               'BINARY_FLOAT', 100, 'BINARY_DOUBLE', 101, \n" + "               'XMLTYPE', 2009, \n" + "               1111)\n" + "              AS data_type,\n" + "       t.data_type AS type_name,\n" + "       DECODE (t.data_precision, " + "               null, DECODE(t.data_type, " + "                       'NUMBER', DECODE(t.data_scale, " + "                                   null, " + (((PhysicalConnection)this.connection).j2ee13Compliant ? "38" : "0") + "                                   , 38), " + (i > 9000 ? str4 : "t.data_length") + "                           )," + "         t.data_precision)\n" + "              AS column_size,\n" + "       0 AS buffer_length,\n" + "       DECODE (t.data_type, " + "               'NUMBER', DECODE(t.data_precision, " + "                                null, DECODE(t.data_scale, " + "                                             null, " + (((PhysicalConnection)this.connection).j2ee13Compliant ? "0" : "-127") + "                                             , t.data_scale), " + "                                 t.data_scale), " + "               t.data_scale) AS decimal_digits,\n" + "       10 AS num_prec_radix,\n" + "       DECODE (t.nullable, 'N', 0, 1) AS nullable,\n";
    
/* 794 */     String str6 = "       c.comments AS remarks,\n";
    
/* 796 */     String str7 = "       NULL AS remarks,\n";
    
/* 798 */     String str8 = "       t.data_default AS column_def,\n       0 AS sql_data_type,\n       0 AS sql_datetime_sub,\n       t.data_length AS char_octet_length,\n       t.column_id AS ordinal_position,\n       DECODE (t.nullable, 'N', 'NO', 'YES') AS is_nullable,\n";
    
/* 805 */     String str9 = "         null as SCOPE_CATALOG,\n       null as SCOPE_SCHEMA,\n       null as SCOPE_TABLE,\n       null as SOURCE_DATA_TYPE,\n       'NO' as IS_AUTOINCREMENT\n";
    
/* 812 */     String str10 = "FROM all_tab_columns t";
    
/* 814 */     String str11 = ", all_col_comments c";
    
/* 816 */     String str12 = "WHERE t.owner = out_owner \n  AND t.table_name = out_name\n AND t.column_name LIKE ? ESCAPE '/'\n";
    
/* 821 */     String str13 = "  AND t.owner = c.owner (+)\n  AND t.table_name = c.table_name (+)\n  AND t.column_name = c.column_name (+)\n";
    
/* 826 */     String str14 = "ORDER BY table_schem, table_name, ordinal_position\n";
    
/* 831 */     String str15 = str2;
    
/* 833 */     str15 = str15 + str3;
    
/* 835 */     str15 = str15 + str5;
    
/* 837 */     if (this.connection.getRemarksReporting()) {
/* 838 */       str15 = str15 + str6;
    } else {
/* 840 */       str15 = str15 + str7;
    }
/* 842 */     str15 = str15 + str8 + str9 + str10;
    
/* 844 */     if (this.connection.getRemarksReporting()) {
/* 845 */       str15 = str15 + str11;
    }
/* 847 */     str15 = str15 + "\n" + str12;
    
/* 849 */     if (this.connection.getRemarksReporting()) {
/* 850 */       str15 = str15 + str13;
    }
    
/* 853 */     str15 = str15 + "\n" + str14;
    
/* 855 */     String str16 = "; \n ? := xxx;\n end;";
    
/* 857 */     String str17 = str1 + str15 + str16;
/* 858 */     return str17;
  }
  
/* 863 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleDatabaseMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */