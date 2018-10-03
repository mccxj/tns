package oracle.jdbc.rowset;
import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.sql.RowSetMetaData;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class OracleRowSetMetaData
  implements RowSetMetaData, Serializable
{
  private int columnCount;
  private int[] nullable;
  private int[] columnDisplaySize;
  private int[] precision;
  private int[] scale;
  private int[] columnType;
  private boolean[] searchable;
  private boolean[] caseSensitive;
  private boolean[] readOnly;
  private boolean[] writable;
  private boolean[] definatelyWritable;
  private boolean[] currency;
  private boolean[] autoIncrement;
  private boolean[] signed;
  private String[] columnLabel;
  private String[] schemaName;
  private String[] columnName;
  private String[] tableName;
  private String[] columnTypeName;
  private String[] catalogName;
  private String[] columnClassName;
  
  OracleRowSetMetaData(int paramInt)
    throws SQLException
  {
/* 147 */     this.columnCount = paramInt;
/* 148 */     this.searchable = new boolean[this.columnCount];
/* 149 */     this.caseSensitive = new boolean[this.columnCount];
/* 150 */     this.readOnly = new boolean[this.columnCount];
/* 151 */     this.nullable = new int[this.columnCount];
/* 152 */     this.signed = new boolean[this.columnCount];
/* 153 */     this.columnDisplaySize = new int[this.columnCount];
/* 154 */     this.columnType = new int[this.columnCount];
/* 155 */     this.columnLabel = new String[this.columnCount];
/* 156 */     this.columnName = new String[this.columnCount];
/* 157 */     this.schemaName = new String[this.columnCount];
/* 158 */     this.precision = new int[this.columnCount];
/* 159 */     this.scale = new int[this.columnCount];
/* 160 */     this.tableName = new String[this.columnCount];
/* 161 */     this.columnTypeName = new String[this.columnCount];
/* 162 */     this.writable = new boolean[this.columnCount];
/* 163 */     this.definatelyWritable = new boolean[this.columnCount];
/* 164 */     this.currency = new boolean[this.columnCount];
/* 165 */     this.autoIncrement = new boolean[this.columnCount];
/* 166 */     this.catalogName = new String[this.columnCount];
/* 167 */     this.columnClassName = new String[this.columnCount];
    
/* 169 */     for (int i = 0; i < this.columnCount; i++)
    {
/* 171 */       this.searchable[i] = false;
/* 172 */       this.caseSensitive[i] = false;
/* 173 */       this.readOnly[i] = false;
/* 174 */       this.nullable[i] = 1;
/* 175 */       this.signed[i] = false;
/* 176 */       this.columnDisplaySize[i] = 0;
/* 177 */       this.columnType[i] = 0;
/* 178 */       this.columnLabel[i] = "";
/* 179 */       this.columnName[i] = "";
/* 180 */       this.schemaName[i] = "";
/* 181 */       this.precision[i] = 0;
/* 182 */       this.scale[i] = 0;
/* 183 */       this.tableName[i] = "";
/* 184 */       this.columnTypeName[i] = "";
/* 185 */       this.writable[i] = false;
/* 186 */       this.definatelyWritable[i] = false;
/* 187 */       this.currency[i] = false;
/* 188 */       this.autoIncrement[i] = true;
/* 189 */       this.catalogName[i] = "";
/* 190 */       this.columnClassName[i] = "";
    }
  }
  
  OracleRowSetMetaData(ResultSetMetaData paramResultSetMetaData)
    throws SQLException
  {
/* 201 */     this.columnCount = paramResultSetMetaData.getColumnCount();
/* 202 */     this.searchable = new boolean[this.columnCount];
/* 203 */     this.caseSensitive = new boolean[this.columnCount];
/* 204 */     this.readOnly = new boolean[this.columnCount];
/* 205 */     this.nullable = new int[this.columnCount];
/* 206 */     this.signed = new boolean[this.columnCount];
/* 207 */     this.columnDisplaySize = new int[this.columnCount];
/* 208 */     this.columnType = new int[this.columnCount];
/* 209 */     this.columnLabel = new String[this.columnCount];
/* 210 */     this.columnName = new String[this.columnCount];
/* 211 */     this.schemaName = new String[this.columnCount];
/* 212 */     this.precision = new int[this.columnCount];
/* 213 */     this.scale = new int[this.columnCount];
/* 214 */     this.tableName = new String[this.columnCount];
/* 215 */     this.columnTypeName = new String[this.columnCount];
/* 216 */     this.writable = new boolean[this.columnCount];
/* 217 */     this.definatelyWritable = new boolean[this.columnCount];
/* 218 */     this.currency = new boolean[this.columnCount];
/* 219 */     this.autoIncrement = new boolean[this.columnCount];
/* 220 */     this.catalogName = new String[this.columnCount];
/* 221 */     this.columnClassName = new String[this.columnCount];
    
/* 223 */     for (int i = 0; i < this.columnCount; i++)
    {
/* 225 */       this.searchable[i] = paramResultSetMetaData.isSearchable(i + 1);
/* 226 */       this.caseSensitive[i] = paramResultSetMetaData.isCaseSensitive(i + 1);
/* 227 */       this.readOnly[i] = paramResultSetMetaData.isReadOnly(i + 1);
/* 228 */       this.nullable[i] = paramResultSetMetaData.isNullable(i + 1);
/* 229 */       this.signed[i] = paramResultSetMetaData.isSigned(i + 1);
/* 230 */       this.columnDisplaySize[i] = paramResultSetMetaData.getColumnDisplaySize(i + 1);
/* 231 */       this.columnType[i] = paramResultSetMetaData.getColumnType(i + 1);
/* 232 */       this.columnLabel[i] = paramResultSetMetaData.getColumnLabel(i + 1);
/* 233 */       this.columnName[i] = paramResultSetMetaData.getColumnName(i + 1);
/* 234 */       this.schemaName[i] = paramResultSetMetaData.getSchemaName(i + 1);
      
/* 236 */       if ((this.columnType[i] == 2) || (this.columnType[i] == 2) || (this.columnType[i] == -5) || (this.columnType[i] == 3) || (this.columnType[i] == 8) || (this.columnType[i] == 6) || (this.columnType[i] == 4))
      {
/* 244 */         this.precision[i] = paramResultSetMetaData.getPrecision(i + 1);
/* 245 */         this.scale[i] = paramResultSetMetaData.getScale(i + 1);
      }
      else
      {
/* 249 */         this.precision[i] = 0;
/* 250 */         this.scale[i] = 0;
      }
      
/* 253 */       this.tableName[i] = paramResultSetMetaData.getTableName(i + 1);
/* 254 */       this.columnTypeName[i] = paramResultSetMetaData.getColumnTypeName(i + 1);
/* 255 */       this.writable[i] = paramResultSetMetaData.isWritable(i + 1);
/* 256 */       this.definatelyWritable[i] = paramResultSetMetaData.isDefinitelyWritable(i + 1);
/* 257 */       this.currency[i] = paramResultSetMetaData.isCurrency(i + 1);
/* 258 */       this.autoIncrement[i] = paramResultSetMetaData.isAutoIncrement(i + 1);
/* 259 */       this.catalogName[i] = paramResultSetMetaData.getCatalogName(i + 1);
/* 260 */       this.columnClassName[i] = paramResultSetMetaData.getColumnClassName(i + 1);
    }
  }
  
  private void validateColumnIndex(int paramInt)
    throws SQLException
  {
/* 271 */     if ((paramInt < 1) || (paramInt > this.columnCount))
    {
/* 273 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "" + paramInt);
/* 274 */       localSQLException.fillInStackTrace();
/* 275 */       throw localSQLException;
    }
  }
  
  public int getColumnCount()
    throws SQLException
  {
/* 291 */     return this.columnCount;
  }
  
  public boolean isAutoIncrement(int paramInt)
    throws SQLException
  {
/* 307 */     validateColumnIndex(paramInt);
/* 308 */     return this.autoIncrement[(paramInt - 1)];
  }
  
  public boolean isCaseSensitive(int paramInt)
    throws SQLException
  {
/* 324 */     validateColumnIndex(paramInt);
/* 325 */     return this.caseSensitive[(paramInt - 1)];
  }
  
  public boolean isSearchable(int paramInt)
    throws SQLException
  {
/* 341 */     validateColumnIndex(paramInt);
/* 342 */     return this.searchable[(paramInt - 1)];
  }
  
  public boolean isCurrency(int paramInt)
    throws SQLException
  {
/* 358 */     validateColumnIndex(paramInt);
/* 359 */     return this.currency[(paramInt - 1)];
  }
  
  public int isNullable(int paramInt)
    throws SQLException
  {
/* 376 */     validateColumnIndex(paramInt);
/* 377 */     return this.nullable[(paramInt - 1)];
  }
  
  public boolean isSigned(int paramInt)
    throws SQLException
  {
/* 392 */     validateColumnIndex(paramInt);
/* 393 */     return this.signed[(paramInt - 1)];
  }
  
  public int getColumnDisplaySize(int paramInt)
    throws SQLException
  {
/* 410 */     validateColumnIndex(paramInt);
/* 411 */     return this.columnDisplaySize[(paramInt - 1)];
  }
  
  public String getColumnLabel(int paramInt)
    throws SQLException
  {
/* 428 */     validateColumnIndex(paramInt);
/* 429 */     return this.columnLabel[(paramInt - 1)];
  }
  
  public String getColumnName(int paramInt)
    throws SQLException
  {
/* 445 */     validateColumnIndex(paramInt);
/* 446 */     return this.columnName[(paramInt - 1)];
  }
  
  public String getSchemaName(int paramInt)
    throws SQLException
  {
/* 462 */     validateColumnIndex(paramInt);
/* 463 */     return this.schemaName[(paramInt - 1)];
  }
  
  public int getPrecision(int paramInt)
    throws SQLException
  {
/* 479 */     validateColumnIndex(paramInt);
/* 480 */     return this.precision[(paramInt - 1)];
  }
  
  public int getScale(int paramInt)
    throws SQLException
  {
/* 496 */     validateColumnIndex(paramInt);
/* 497 */     return this.scale[(paramInt - 1)];
  }
  
  public String getTableName(int paramInt)
    throws SQLException
  {
/* 513 */     validateColumnIndex(paramInt);
/* 514 */     return this.tableName[(paramInt - 1)];
  }
  
  public String getCatalogName(int paramInt)
    throws SQLException
  {
/* 530 */     validateColumnIndex(paramInt);
/* 531 */     return this.catalogName[(paramInt - 1)];
  }
  
  public int getColumnType(int paramInt)
    throws SQLException
  {
/* 548 */     validateColumnIndex(paramInt);
/* 549 */     return this.columnType[(paramInt - 1)];
  }
  
  public String getColumnTypeName(int paramInt)
    throws SQLException
  {
/* 566 */     validateColumnIndex(paramInt);
/* 567 */     return this.columnTypeName[(paramInt - 1)];
  }
  
  public boolean isReadOnly(int paramInt)
    throws SQLException
  {
/* 583 */     validateColumnIndex(paramInt);
/* 584 */     return this.readOnly[(paramInt - 1)];
  }
  
  public boolean isWritable(int paramInt)
    throws SQLException
  {
/* 600 */     validateColumnIndex(paramInt);
/* 601 */     return this.writable[(paramInt - 1)];
  }
  
  public boolean isDefinitelyWritable(int paramInt)
    throws SQLException
  {
/* 617 */     validateColumnIndex(paramInt);
/* 618 */     return this.definatelyWritable[(paramInt - 1)];
  }
  
  public String getColumnClassName(int paramInt)
    throws SQLException
  {
/* 644 */     validateColumnIndex(paramInt);
/* 645 */     return this.columnClassName[(paramInt - 1)];
  }
  
  public void setAutoIncrement(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 658 */     validateColumnIndex(paramInt);
/* 659 */     this.autoIncrement[(paramInt - 1)] = paramBoolean;
  }
  
  public void setCaseSensitive(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 669 */     validateColumnIndex(paramInt);
/* 670 */     this.caseSensitive[(paramInt - 1)] = paramBoolean;
  }
  
  public void setCatalogName(int paramInt, String paramString)
    throws SQLException
  {
/* 680 */     validateColumnIndex(paramInt);
/* 681 */     this.catalogName[(paramInt - 1)] = paramString;
  }
  
  public void setColumnCount(int paramInt)
    throws SQLException
  {
/* 691 */     this.columnCount = paramInt;
  }
  
  public void setColumnDisplaySize(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 701 */     validateColumnIndex(paramInt1);
/* 702 */     this.columnDisplaySize[(paramInt1 - 1)] = paramInt2;
  }
  
  public void setColumnLabel(int paramInt, String paramString)
    throws SQLException
  {
/* 712 */     validateColumnIndex(paramInt);
/* 713 */     this.columnLabel[(paramInt - 1)] = paramString;
  }
  
  public void setColumnName(int paramInt, String paramString)
    throws SQLException
  {
/* 723 */     validateColumnIndex(paramInt);
/* 724 */     this.columnName[(paramInt - 1)] = paramString;
  }
  
  public void setColumnType(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 734 */     validateColumnIndex(paramInt1);
/* 735 */     this.columnType[(paramInt1 - 1)] = paramInt2;
  }
  
  public void setColumnTypeName(int paramInt, String paramString)
    throws SQLException
  {
/* 745 */     validateColumnIndex(paramInt);
/* 746 */     this.columnTypeName[(paramInt - 1)] = paramString;
  }
  
  public void setCurrency(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 756 */     validateColumnIndex(paramInt);
/* 757 */     this.currency[(paramInt - 1)] = paramBoolean;
  }
  
  public void setNullable(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 767 */     validateColumnIndex(paramInt1);
/* 768 */     this.nullable[(paramInt1 - 1)] = paramInt2;
  }
  
  public void setPrecision(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 778 */     validateColumnIndex(paramInt1);
/* 779 */     this.precision[(paramInt1 - 1)] = paramInt2;
  }
  
  public void setScale(int paramInt1, int paramInt2)
    throws SQLException
  {
/* 789 */     validateColumnIndex(paramInt1);
/* 790 */     this.scale[(paramInt1 - 1)] = paramInt2;
  }
  
  public void setSchemaName(int paramInt, String paramString)
    throws SQLException
  {
/* 800 */     validateColumnIndex(paramInt);
/* 801 */     this.schemaName[(paramInt - 1)] = paramString;
  }
  
  public void setSearchable(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 811 */     validateColumnIndex(paramInt);
/* 812 */     this.searchable[(paramInt - 1)] = paramBoolean;
  }
  
  public void setSigned(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 822 */     validateColumnIndex(paramInt);
/* 823 */     this.signed[(paramInt - 1)] = paramBoolean;
  }
  
  public void setTableName(int paramInt, String paramString)
    throws SQLException
  {
/* 833 */     validateColumnIndex(paramInt);
/* 834 */     this.tableName[(paramInt - 1)] = paramString;
  }
  
  public boolean isWrapperFor(Class<?> paramClass)
    throws SQLException
  {
/* 854 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
    }
/* 856 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 857 */     localSQLException.fillInStackTrace();
/* 858 */     throw localSQLException;
  }
  
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 875 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
    }
/* 877 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 878 */     localSQLException.fillInStackTrace();
/* 879 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 897 */     return null;
  }
  
/* 902 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleRowSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */