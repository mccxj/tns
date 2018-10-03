package oracle.jdbc.rowset;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.StringTokenizer;
import javax.sql.RowSet;
import javax.sql.RowSetInternal;
import javax.sql.RowSetWriter;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class OracleCachedRowSetWriter
  implements RowSetWriter, Serializable
{
  static final long serialVersionUID = 8932894189919931169L;
/*  61 */   private StringBuffer updateClause = new StringBuffer("");
  
/*  67 */   private StringBuffer deleteClause = new StringBuffer("");
  
/*  73 */   private StringBuffer insertClause = new StringBuffer("");
  
  private PreparedStatement insertStmt;
  
  private PreparedStatement updateStmt;
  
  private PreparedStatement deleteStmt;
  
  private ResultSetMetaData rsmd;
  
  private transient Connection connection;
  
  private int columnCount;
  
  static final int ASCII_STREAM = 1;
  
  static final int BINARY_STREAM = 2;
  
  static final int CHARACTER_STREAM = 3;
  
  static final int NCHARACTER_STREAM = 4;
  
  private String getSchemaName(RowSet paramRowSet)
    throws SQLException
  {
/* 130 */     return paramRowSet.getUsername();
  }
  
  private String getTableName(RowSet paramRowSet)
    throws SQLException
  {
/* 139 */     String str1 = ((OracleCachedRowSet)paramRowSet).getTableName();
/* 140 */     if (str1 != null) {
/* 141 */       return str1;
    }
/* 143 */     String str2 = paramRowSet.getCommand().toUpperCase();
    
/* 147 */     int i = str2.indexOf(" FROM ");
    
/* 151 */     if (i == -1)
    {
/* 153 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 343, str2.length() != 0 ? str2 : "Please use RowSet.setCommand (String) to set the SQL query string.");
/* 154 */       ((SQLException)localObject).fillInStackTrace();
/* 155 */       throw ((Throwable)localObject);
    }
    
/* 161 */     Object localObject = str2.substring(i + 6).trim();
    
/* 165 */     StringTokenizer localStringTokenizer = new StringTokenizer((String)localObject);
/* 166 */     if (localStringTokenizer.hasMoreTokens()) {
/* 167 */       localObject = localStringTokenizer.nextToken();
    }
/* 169 */     return (String)localObject;
  }
  
  private void initSQLStatement(RowSet paramRowSet)
    throws SQLException
  {
/* 181 */     this.insertClause = new StringBuffer("INSERT INTO " + getTableName(paramRowSet) + "(");
/* 182 */     this.updateClause = new StringBuffer("UPDATE " + getTableName(paramRowSet) + " SET ");
/* 183 */     this.deleteClause = new StringBuffer("DELETE FROM " + getTableName(paramRowSet) + " WHERE ");
    
/* 186 */     this.rsmd = paramRowSet.getMetaData();
/* 187 */     this.columnCount = this.rsmd.getColumnCount();
    
/* 191 */     for (int i = 0; i < this.columnCount; i++)
    {
/* 193 */       if (i != 0) this.insertClause.append(", ");
/* 194 */       this.insertClause.append(this.rsmd.getColumnName(i + 1));
      
/* 196 */       if (i != 0) this.updateClause.append(", ");
/* 197 */       this.updateClause.append(this.rsmd.getColumnName(i + 1) + " = :" + i);
      
/* 199 */       if (i != 0) this.deleteClause.append(" AND ");
/* 200 */       this.deleteClause.append(this.rsmd.getColumnName(i + 1) + " = :" + i);
    }
/* 202 */     this.insertClause.append(") VALUES (");
/* 203 */     this.updateClause.append(" WHERE ");
    
/* 205 */     for (i = 0; i < this.columnCount; i++)
    {
/* 207 */       if (i != 0) this.insertClause.append(", ");
/* 208 */       this.insertClause.append(":" + i);
      
/* 210 */       if (i != 0) this.updateClause.append(" AND ");
/* 211 */       this.updateClause.append(this.rsmd.getColumnName(i + 1) + " = :" + i);
    }
/* 213 */     this.insertClause.append(")");
    
/* 215 */     this.insertStmt = this.connection.prepareStatement(this.insertClause.substring(0, this.insertClause.length()));
    
/* 217 */     this.updateStmt = this.connection.prepareStatement(this.updateClause.substring(0, this.updateClause.length()));
    
/* 219 */     this.deleteStmt = this.connection.prepareStatement(this.deleteClause.substring(0, this.deleteClause.length()));
  }
  
  private boolean insertRow(OracleRow paramOracleRow)
    throws SQLException
  {
/* 235 */     this.insertStmt.clearParameters();
/* 236 */     for (int i = 1; i <= this.columnCount; i++)
    {
/* 238 */       Object localObject = null;
/* 239 */       localObject = paramOracleRow.isColumnChanged(i) ? paramOracleRow.getModifiedColumn(i) : paramOracleRow.getColumn(i);
      
/* 244 */       if (localObject == null) {
/* 245 */         this.insertStmt.setNull(i, this.rsmd.getColumnType(i));
/* 246 */         paramOracleRow.markOriginalNull(i, true);
      } else {
/* 248 */         this.insertStmt.setObject(i, localObject);
      }
    }
/* 251 */     return this.insertStmt.executeUpdate() == 1;
  }
  
  private boolean updateRow(RowSet paramRowSet, OracleRow paramOracleRow)
    throws SQLException
  {
/* 263 */     this.updateStmt.clearParameters();
/* 264 */     for (int i = 1; i <= this.columnCount; i++)
    {
/* 266 */       Object localObject = null;
/* 267 */       localObject = paramOracleRow.isColumnChanged(i) ? paramOracleRow.getModifiedColumn(i) : paramOracleRow.getColumn(i);
      
/* 272 */       if (localObject == null) {
/* 273 */         this.updateStmt.setNull(i, this.rsmd.getColumnType(i));
      }
/* 276 */       else if ((localObject instanceof Reader))
      {
/* 278 */         OraclePreparedStatement localOraclePreparedStatement = (OraclePreparedStatement)this.updateStmt;
/* 279 */         if (paramOracleRow.columnTypeInfo[(i - 1)][1] == 4) {
/* 280 */           localOraclePreparedStatement.setFormOfUse(i, (short)2);
/* 281 */         } else if (paramOracleRow.columnTypeInfo[(i - 1)][1] == 3) {
/* 282 */           localOraclePreparedStatement.setFormOfUse(i, (short)1);
        }
/* 284 */         this.updateStmt.setCharacterStream(i, (Reader)localObject, paramOracleRow.columnTypeInfo[(i - 1)][0]);
      }
/* 288 */       else if ((localObject instanceof InputStream))
      {
/* 290 */         if (paramOracleRow.columnTypeInfo[(i - 1)][1] == 2) {
/* 291 */           this.updateStmt.setBinaryStream(i, (InputStream)localObject, paramOracleRow.columnTypeInfo[(i - 1)][0]);
        }
/* 294 */         else if (paramOracleRow.columnTypeInfo[(i - 1)][1] == 1) {
/* 295 */           this.updateStmt.setAsciiStream(i, (InputStream)localObject, paramOracleRow.columnTypeInfo[(i - 1)][0]);
        }
      }
      else
      {
/* 300 */         this.updateStmt.setObject(i, localObject);
      }
    }
/* 303 */     for (i = 1; i <= this.columnCount; i++)
    {
/* 305 */       if (paramOracleRow.isOriginalNull(i)) {
/* 306 */         return updateRowWithNull(paramRowSet, paramOracleRow);
      }
/* 308 */       this.updateStmt.setObject(i + this.columnCount, paramOracleRow.getColumn(i));
    }
    
/* 311 */     return this.updateStmt.executeUpdate() == 1;
  }
  
  private boolean updateRowWithNull(RowSet paramRowSet, OracleRow paramOracleRow)
    throws SQLException
  {
/* 320 */     boolean bool = false;
/* 321 */     StringBuffer localStringBuffer = new StringBuffer("UPDATE " + getTableName(paramRowSet) + " SET ");
    
/* 324 */     for (int i = 1; i <= this.columnCount; i++)
    {
/* 326 */       if (i != 1) {
/* 327 */         localStringBuffer.append(", ");
      }
/* 329 */       localStringBuffer.append(this.rsmd.getColumnName(i) + " = :" + i);
    }
    
/* 332 */     localStringBuffer.append(" WHERE ");
    
/* 334 */     for (i = 1; i <= this.columnCount; i++)
    {
/* 336 */       if (i != 1)
/* 337 */         localStringBuffer.append(" AND ");
/* 338 */       if (paramOracleRow.isOriginalNull(i)) {
/* 339 */         localStringBuffer.append(this.rsmd.getColumnName(i) + " IS NULL ");
      } else {
/* 341 */         localStringBuffer.append(this.rsmd.getColumnName(i) + " = :" + i);
      }
    }
/* 344 */     PreparedStatement localPreparedStatement = null;
    try
    {
/* 347 */       localPreparedStatement = this.connection.prepareStatement(localStringBuffer.substring(0, localStringBuffer.length()));
      
/* 350 */       for (int j = 1; j <= this.columnCount; j++)
      {
/* 352 */         Object localObject1 = null;
/* 353 */         localObject1 = paramOracleRow.isColumnChanged(j) ? paramOracleRow.getModifiedColumn(j) : paramOracleRow.getColumn(j);
        
/* 358 */         if (localObject1 == null) {
/* 359 */           localPreparedStatement.setNull(j, this.rsmd.getColumnType(j));
        }
/* 362 */         else if ((localObject1 instanceof Reader))
        {
/* 364 */           OraclePreparedStatement localOraclePreparedStatement = (OraclePreparedStatement)localPreparedStatement;
/* 365 */           if (paramOracleRow.columnTypeInfo[(j - 1)][1] == 4) {
/* 366 */             localOraclePreparedStatement.setFormOfUse(j, (short)2);
/* 367 */           } else if (paramOracleRow.columnTypeInfo[(j - 1)][1] == 3) {
/* 368 */             localOraclePreparedStatement.setFormOfUse(j, (short)1);
          }
/* 370 */           localPreparedStatement.setCharacterStream(j, (Reader)localObject1, paramOracleRow.columnTypeInfo[(j - 1)][0]);
        }
/* 374 */         else if ((localObject1 instanceof InputStream))
        {
/* 376 */           if (paramOracleRow.columnTypeInfo[(j - 1)][1] == 2) {
/* 377 */             localPreparedStatement.setBinaryStream(j, (InputStream)localObject1, paramOracleRow.columnTypeInfo[(j - 1)][0]);
          }
/* 380 */           else if (paramOracleRow.columnTypeInfo[(j - 1)][1] == 1) {
/* 381 */             localPreparedStatement.setAsciiStream(j, (InputStream)localObject1, paramOracleRow.columnTypeInfo[(j - 1)][0]);
          }
        }
        else
        {
/* 386 */           localPreparedStatement.setObject(j, localObject1);
        }
      }
      
/* 390 */       j = 1; for (int k = 1; j <= this.columnCount; j++)
      {
/* 392 */         if (!paramOracleRow.isOriginalNull(j))
        {
/* 395 */           localPreparedStatement.setObject(k + this.columnCount, paramOracleRow.getColumn(j));
          
/* 397 */           k++;
        } }
/* 399 */       bool = localPreparedStatement.executeUpdate() == 1;
    }
    finally {
/* 402 */       if (localPreparedStatement != null) {
/* 403 */         localPreparedStatement.close();
      }
    }
/* 406 */     return bool;
  }
  
  private boolean deleteRow(RowSet paramRowSet, OracleRow paramOracleRow)
    throws SQLException
  {
/* 419 */     this.deleteStmt.clearParameters();
/* 420 */     for (int i = 1; i <= this.columnCount; i++)
    {
/* 422 */       if (paramOracleRow.isOriginalNull(i)) {
/* 423 */         return deleteRowWithNull(paramRowSet, paramOracleRow);
      }
/* 425 */       Object localObject = paramOracleRow.getColumn(i);
/* 426 */       if (localObject == null) {
/* 427 */         this.deleteStmt.setNull(i, this.rsmd.getColumnType(i));
      } else {
/* 429 */         this.deleteStmt.setObject(i, localObject);
      }
    }
/* 432 */     return this.deleteStmt.executeUpdate() == 1;
  }
  
  private boolean deleteRowWithNull(RowSet paramRowSet, OracleRow paramOracleRow)
    throws SQLException
  {
/* 442 */     boolean bool = false;
/* 443 */     StringBuffer localStringBuffer = new StringBuffer("DELETE FROM " + getTableName(paramRowSet) + " WHERE ");
    
/* 446 */     for (int i = 1; i <= this.columnCount; i++)
    {
/* 448 */       if (i != 1)
/* 449 */         localStringBuffer.append(" AND ");
/* 450 */       if (paramOracleRow.isOriginalNull(i)) {
/* 451 */         localStringBuffer.append(this.rsmd.getColumnName(i) + " IS NULL ");
      } else {
/* 453 */         localStringBuffer.append(this.rsmd.getColumnName(i) + " = :" + i);
      }
    }
/* 456 */     PreparedStatement localPreparedStatement = null;
    try
    {
/* 459 */       localPreparedStatement = this.connection.prepareStatement(localStringBuffer.substring(0, localStringBuffer.length()));
      
/* 463 */       int j = 1; for (int k = 1; j <= this.columnCount; j++)
      {
/* 465 */         if (!paramOracleRow.isOriginalNull(j))
        {
/* 468 */           localPreparedStatement.setObject(k++, paramOracleRow.getColumn(j)); }
      }
/* 470 */       bool = localPreparedStatement.executeUpdate() == 1;
    }
    finally {
/* 473 */       if (localPreparedStatement != null) {
/* 474 */         localPreparedStatement.close();
      }
    }
/* 477 */     return bool;
  }
  
  public synchronized boolean writeData(RowSetInternal paramRowSetInternal)
    throws SQLException
  {
/* 486 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)paramRowSetInternal;
/* 487 */     this.connection = ((OracleCachedRowSetReader)localOracleCachedRowSet.getReader()).getConnection(paramRowSetInternal);
    
/* 492 */     if (this.connection == null)
    {
/* 494 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 342);
/* 495 */       localSQLException.fillInStackTrace();
/* 496 */       throw localSQLException;
    }
    
/* 499 */     if (this.connection.getAutoCommit()) {
/* 500 */       this.connection.setAutoCommit(false);
    }
    try
    {
/* 504 */       this.connection.setTransactionIsolation(localOracleCachedRowSet.getTransactionIsolation());
    }
    catch (Exception localException) {}
    
/* 511 */     initSQLStatement(localOracleCachedRowSet);
/* 512 */     if (this.columnCount < 1)
    {
/* 514 */       this.connection.close();
/* 515 */       return true;
    }
/* 517 */     boolean bool = localOracleCachedRowSet.getShowDeleted();
/* 518 */     localOracleCachedRowSet.setShowDeleted(true);
/* 519 */     localOracleCachedRowSet.beforeFirst();
/* 520 */     int i = 1;
/* 521 */     int j = 1;
/* 522 */     int k = 1;
/* 523 */     OracleRow localOracleRow = null;
/* 524 */     while (localOracleCachedRowSet.next())
    {
/* 526 */       if (localOracleCachedRowSet.rowInserted())
      {
/* 529 */         if (!localOracleCachedRowSet.rowDeleted())
        {
/* 531 */           localOracleRow = localOracleCachedRowSet.getCurrentRow();
          
/* 533 */           j = (insertRow(localOracleRow)) || (j != 0) ? 1 : 0;
        }
/* 535 */       } else if (localOracleCachedRowSet.rowUpdated())
      {
/* 537 */         localOracleRow = localOracleCachedRowSet.getCurrentRow();
        
/* 539 */         i = (updateRow(localOracleCachedRowSet, localOracleRow)) || (i != 0) ? 1 : 0;
      }
/* 541 */       else if (localOracleCachedRowSet.rowDeleted())
      {
/* 543 */         localOracleRow = localOracleCachedRowSet.getCurrentRow();
        
/* 545 */         k = (deleteRow(localOracleCachedRowSet, localOracleRow)) || (k != 0) ? 1 : 0;
      }
    }
    
/* 552 */     if ((i != 0) && (j != 0) && (k != 0))
    {
/* 555 */       this.connection.commit();
      
/* 557 */       localOracleCachedRowSet.setOriginal();
    }
    else {
/* 560 */       this.connection.rollback();
    }
/* 562 */     this.insertStmt.close();
/* 563 */     this.updateStmt.close();
/* 564 */     this.deleteStmt.close();
    
/* 568 */     if (!localOracleCachedRowSet.isConnectionStayingOpen())
    {
/* 570 */       this.connection.close();
    }
    
/* 573 */     localOracleCachedRowSet.setShowDeleted(bool);
/* 574 */     return true;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 589 */     return null;
  }
  
/* 594 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleCachedRowSetWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */