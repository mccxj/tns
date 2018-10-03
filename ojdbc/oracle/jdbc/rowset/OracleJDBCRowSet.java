/*      */ package oracle.jdbc.rowset;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.NClob;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Savepoint;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Calendar;
/*      */ import java.util.Map;
/*      */ import javax.naming.InitialContext;
/*      */ import javax.naming.NamingException;
/*      */ import javax.sql.DataSource;
/*      */ import javax.sql.RowSet;
/*      */ import javax.sql.rowset.JdbcRowSet;
/*      */ import javax.sql.rowset.RowSetWarning;
/*      */ import oracle.jdbc.OracleResultSet;
/*      */ import oracle.jdbc.OracleSavepoint;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleDriver;
/*      */ import oracle.jdbc.internal.OraclePreparedStatement;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleJDBCRowSet
/*      */   extends OracleRowSet
/*      */   implements RowSet, JdbcRowSet
/*      */ {
/*      */   private Connection connection;
/*      */   private static boolean driverManagerInitialized;
/*      */   private PreparedStatement preparedStatement;
/*      */   private ResultSet resultSet;
/*      */   
/*      */   public OracleJDBCRowSet()
/*      */     throws SQLException
/*      */   {
/*  177 */     driverManagerInitialized = false;
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
/*      */   public OracleJDBCRowSet(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  192 */     this();
/*      */     
/*      */ 
/*      */ 
/*  196 */     this.connection = paramConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void execute()
/*      */     throws SQLException
/*      */   {
/*  206 */     this.connection = getConnection(this);
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  212 */       this.connection.setTransactionIsolation(getTransactionIsolation());
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  219 */     this.connection.setTypeMap(getTypeMap());
/*      */     
/*  221 */     if (this.preparedStatement == null) {
/*  222 */       this.preparedStatement = this.connection.prepareStatement(getCommand(), getType(), getConcurrency());
/*      */     }
/*  224 */     this.preparedStatement.setFetchSize(getFetchSize());
/*  225 */     this.preparedStatement.setFetchDirection(getFetchDirection());
/*  226 */     this.preparedStatement.setMaxFieldSize(getMaxFieldSize());
/*  227 */     this.preparedStatement.setMaxRows(getMaxRows());
/*  228 */     this.preparedStatement.setQueryTimeout(getQueryTimeout());
/*  229 */     this.preparedStatement.setEscapeProcessing(getEscapeProcessing());
/*      */     
/*      */ 
/*      */ 
/*  233 */     this.resultSet = this.preparedStatement.executeQuery();
/*  234 */     notifyRowSetChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void close()
/*      */     throws SQLException
/*      */   {
/*  246 */     if (this.resultSet != null) {
/*  247 */       this.resultSet.close();
/*      */     }
/*  249 */     if (this.preparedStatement != null) {
/*  250 */       this.preparedStatement.close();
/*      */     }
/*  252 */     if ((this.connection != null) && (!this.connection.isClosed()))
/*      */     {
/*  254 */       this.connection.commit();
/*  255 */       this.connection.close();
/*      */     }
/*  257 */     notifyRowSetChanged();
/*      */     
/*  259 */     this.isClosed = true;
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
/*      */   private Connection getConnection(RowSet paramRowSet)
/*      */     throws SQLException
/*      */   {
/*  277 */     Connection localConnection = null;
/*      */     
/*      */ 
/*      */ 
/*  281 */     if ((this.connection != null) && (!this.connection.isClosed()))
/*      */     {
/*  283 */       localConnection = this.connection;
/*      */     } else { Object localObject;
/*  285 */       if (paramRowSet.getDataSourceName() != null)
/*      */       {
/*      */         try
/*      */         {
/*  289 */           InitialContext localInitialContext = new InitialContext();
/*  290 */           localObject = (DataSource)localInitialContext.lookup(paramRowSet.getDataSourceName());
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  295 */           if ((paramRowSet.getUsername() == null) || (paramRowSet.getPassword() == null))
/*      */           {
/*      */ 
/*  298 */             localConnection = ((DataSource)localObject).getConnection();
/*      */           }
/*      */           else
/*      */           {
/*  302 */             localConnection = ((DataSource)localObject).getConnection(paramRowSet.getUsername(), paramRowSet.getPassword());
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*      */         catch (NamingException localNamingException)
/*      */         {
/*  309 */           localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 300, localNamingException.getMessage());
/*  310 */           ((SQLException)localObject).fillInStackTrace();
/*  311 */           throw ((Throwable)localObject);
/*      */         }
/*      */         
/*      */       }
/*  315 */       else if (paramRowSet.getUrl() != null)
/*      */       {
/*  317 */         if (!driverManagerInitialized)
/*      */         {
/*  319 */           DriverManager.registerDriver(new OracleDriver());
/*  320 */           driverManagerInitialized = true;
/*      */         }
/*  322 */         String str1 = paramRowSet.getUrl();
/*  323 */         localObject = paramRowSet.getUsername();
/*  324 */         String str2 = paramRowSet.getPassword();
/*      */         
/*      */ 
/*      */ 
/*  328 */         if ((str1.equals("")) || (((String)localObject).equals("")) || (str2.equals("")))
/*      */         {
/*      */ 
/*  331 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 301);
/*  332 */           localSQLException.fillInStackTrace();
/*  333 */           throw localSQLException;
/*      */         }
/*      */         
/*  336 */         localConnection = DriverManager.getConnection(str1, (String)localObject, str2);
/*      */       }
/*      */     }
/*  339 */     return localConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  352 */     return this.resultSet.wasNull();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/*  361 */     return this.resultSet.getWarnings();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/*  370 */     this.resultSet.clearWarnings();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getCursorName()
/*      */     throws SQLException
/*      */   {
/*  380 */     return this.resultSet.getCursorName();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/*  389 */     return new OracleRowSetMetaData(this.resultSet.getMetaData());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/*  398 */     return this.resultSet.findColumn(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearParameters()
/*      */     throws SQLException
/*      */   {
/*  407 */     this.preparedStatement.clearParameters();
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
/*      */   public Statement getStatement()
/*      */     throws SQLException
/*      */   {
/*  422 */     return this.resultSet.getStatement();
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
/*      */   public void setCommand(String paramString)
/*      */     throws SQLException
/*      */   {
/*  437 */     super.setCommand(paramString);
/*      */     
/*  439 */     if ((this.connection == null) || (this.connection.isClosed())) {
/*  440 */       this.connection = getConnection(this);
/*      */     }
/*  442 */     if (this.preparedStatement != null)
/*      */     {
/*      */       try
/*      */       {
/*  446 */         this.preparedStatement.close();
/*  447 */         this.preparedStatement = null;
/*      */       }
/*      */       catch (SQLException localSQLException) {}
/*      */     }
/*  451 */     this.preparedStatement = this.connection.prepareStatement(paramString, getType(), getConcurrency());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setReadOnly(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  461 */     super.setReadOnly(paramBoolean);
/*      */     
/*  463 */     if (this.connection != null)
/*      */     {
/*  465 */       this.connection.setReadOnly(paramBoolean);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  470 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/*  471 */       localSQLException.fillInStackTrace();
/*  472 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFetchDirection(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  484 */     super.setFetchDirection(paramInt);
/*      */     
/*  486 */     this.resultSet.setFetchDirection(this.fetchDirection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShowDeleted(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  496 */     if (paramBoolean)
/*      */     {
/*      */ 
/*  499 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 303);
/*  500 */       localSQLException.fillInStackTrace();
/*  501 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  506 */     super.setShowDeleted(paramBoolean);
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
/*      */   public boolean next()
/*      */     throws SQLException
/*      */   {
/*  521 */     boolean bool = this.resultSet.next();
/*      */     
/*      */ 
/*      */ 
/*  525 */     if (bool) {
/*  526 */       notifyCursorMoved();
/*      */     }
/*  528 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean previous()
/*      */     throws SQLException
/*      */   {
/*  537 */     boolean bool = this.resultSet.previous();
/*      */     
/*      */ 
/*      */ 
/*  541 */     if (bool) {
/*  542 */       notifyCursorMoved();
/*      */     }
/*  544 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void beforeFirst()
/*      */     throws SQLException
/*      */   {
/*  553 */     if (!isBeforeFirst())
/*      */     {
/*  555 */       this.resultSet.beforeFirst();
/*  556 */       notifyCursorMoved();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void afterLast()
/*      */     throws SQLException
/*      */   {
/*  567 */     if (!isAfterLast())
/*      */     {
/*  569 */       this.resultSet.afterLast();
/*  570 */       notifyCursorMoved();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean first()
/*      */     throws SQLException
/*      */   {
/*  581 */     boolean bool = this.resultSet.first();
/*      */     
/*      */ 
/*      */ 
/*  585 */     if (bool) {
/*  586 */       notifyCursorMoved();
/*      */     }
/*  588 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean last()
/*      */     throws SQLException
/*      */   {
/*  597 */     boolean bool = this.resultSet.last();
/*      */     
/*      */ 
/*      */ 
/*  601 */     if (bool) {
/*  602 */       notifyCursorMoved();
/*      */     }
/*  604 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean absolute(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  614 */     boolean bool = this.resultSet.absolute(paramInt);
/*      */     
/*      */ 
/*      */ 
/*  618 */     if (bool) {
/*  619 */       notifyCursorMoved();
/*      */     }
/*  621 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean relative(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  630 */     boolean bool = this.resultSet.relative(paramInt);
/*      */     
/*      */ 
/*      */ 
/*  634 */     if (bool) {
/*  635 */       notifyCursorMoved();
/*      */     }
/*  637 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBeforeFirst()
/*      */     throws SQLException
/*      */   {
/*  646 */     return this.resultSet.isBeforeFirst();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/*  655 */     return this.resultSet.isAfterLast();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/*  664 */     return this.resultSet.isFirst();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/*  673 */     return this.resultSet.isLast();
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
/*      */   public void insertRow()
/*      */     throws SQLException
/*      */   {
/*  687 */     if (isReadOnly())
/*      */     {
/*  689 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  690 */       localSQLException.fillInStackTrace();
/*  691 */       throw localSQLException;
/*      */     }
/*      */     
/*  694 */     this.resultSet.insertRow();
/*  695 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRow()
/*      */     throws SQLException
/*      */   {
/*  705 */     if (isReadOnly())
/*      */     {
/*  707 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  708 */       localSQLException.fillInStackTrace();
/*  709 */       throw localSQLException;
/*      */     }
/*      */     
/*  712 */     this.resultSet.updateRow();
/*  713 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void deleteRow()
/*      */     throws SQLException
/*      */   {
/*  723 */     if (isReadOnly())
/*      */     {
/*  725 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  726 */       localSQLException.fillInStackTrace();
/*  727 */       throw localSQLException;
/*      */     }
/*      */     
/*  730 */     this.resultSet.deleteRow();
/*  731 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void refreshRow()
/*      */     throws SQLException
/*      */   {
/*  741 */     this.resultSet.refreshRow();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancelRowUpdates()
/*      */     throws SQLException
/*      */   {
/*  751 */     this.resultSet.cancelRowUpdates();
/*  752 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToInsertRow()
/*      */     throws SQLException
/*      */   {
/*  762 */     if (isReadOnly())
/*      */     {
/*  764 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  765 */       localSQLException.fillInStackTrace();
/*  766 */       throw localSQLException;
/*      */     }
/*      */     
/*  769 */     this.resultSet.moveToInsertRow();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToCurrentRow()
/*      */     throws SQLException
/*      */   {
/*  779 */     if (isReadOnly())
/*      */     {
/*  781 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  782 */       localSQLException.fillInStackTrace();
/*  783 */       throw localSQLException;
/*      */     }
/*      */     
/*  786 */     this.resultSet.moveToCurrentRow();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/*  796 */     return this.resultSet.getRow();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowUpdated()
/*      */     throws SQLException
/*      */   {
/*  805 */     return this.resultSet.rowUpdated();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowInserted()
/*      */     throws SQLException
/*      */   {
/*  814 */     return this.resultSet.rowInserted();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowDeleted()
/*      */     throws SQLException
/*      */   {
/*  823 */     return this.resultSet.rowDeleted();
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
/*      */   public void setNull(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  837 */     this.preparedStatement.setNull(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/*  847 */     this.preparedStatement.setNull(paramInt1, paramInt2, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  857 */     this.preparedStatement.setBoolean(paramInt, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  867 */     this.preparedStatement.setByte(paramInt, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/*  877 */     this.preparedStatement.setShort(paramInt, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  887 */     this.preparedStatement.setInt(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  897 */     this.preparedStatement.setLong(paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/*  907 */     this.preparedStatement.setFloat(paramInt, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/*  917 */     this.preparedStatement.setDouble(paramInt, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/*  927 */     this.preparedStatement.setBigDecimal(paramInt, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  937 */     this.preparedStatement.setString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/*  947 */     this.preparedStatement.setBytes(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/*  957 */     this.preparedStatement.setDate(paramInt, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/*  967 */     this.preparedStatement.setTime(paramInt, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/*  977 */     this.preparedStatement.setObject(paramInt, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/*  987 */     this.preparedStatement.setRef(paramInt, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/*  997 */     this.preparedStatement.setBlob(paramInt, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 1007 */     this.preparedStatement.setClob(paramInt, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 1017 */     this.preparedStatement.setArray(paramInt, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1028 */     this.preparedStatement.setBinaryStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1038 */     this.preparedStatement.setTime(paramInt, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1049 */     this.preparedStatement.setTimestamp(paramInt, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 1059 */     this.preparedStatement.setTimestamp(paramInt, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1070 */     this.preparedStatement.setAsciiStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1081 */     this.preparedStatement.setCharacterStream(paramInt1, paramReader, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1092 */     this.preparedStatement.setObject(paramInt1, paramObject, paramInt2, paramInt3);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1102 */     this.preparedStatement.setObject(paramInt1, paramObject, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1112 */     this.preparedStatement.setDate(paramInt, paramDate, paramCalendar);
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
/*      */   public Object getObject(int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1126 */     return this.resultSet.getObject(paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1135 */     return this.resultSet.getBigDecimal(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1144 */     return this.resultSet.getRef(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1153 */     return this.resultSet.getBlob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1162 */     return this.resultSet.getClob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1171 */     return this.resultSet.getArray(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1180 */     return this.resultSet.getDate(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1189 */     return this.resultSet.getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1198 */     return this.resultSet.getTime(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1207 */     return this.resultSet.getBinaryStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1216 */     return this.resultSet.getTimestamp(paramInt, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1224 */     return this.resultSet.getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1233 */     return this.resultSet.getBoolean(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1242 */     return this.resultSet.getByte(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1251 */     return this.resultSet.getShort(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1260 */     return this.resultSet.getLong(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1269 */     return this.resultSet.getFloat(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1278 */     return this.resultSet.getDouble(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1287 */     return this.resultSet.getBigDecimal(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1296 */     return this.resultSet.getBytes(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1305 */     return this.resultSet.getDate(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1314 */     return this.resultSet.getTime(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1323 */     return this.resultSet.getTimestamp(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1332 */     return this.resultSet.getAsciiStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1341 */     return this.resultSet.getUnicodeStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1350 */     return this.resultSet.getInt(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1360 */     return this.resultSet.getObject(paramInt);
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
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1374 */     return this.resultSet.getInt(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1383 */     return this.resultSet.getLong(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1392 */     return this.resultSet.getFloat(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1401 */     return this.resultSet.getDouble(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1410 */     return this.resultSet.getBigDecimal(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1419 */     return this.resultSet.getBytes(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1428 */     return this.resultSet.getDate(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1437 */     return this.resultSet.getTime(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1446 */     return this.resultSet.getTimestamp(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1455 */     return this.resultSet.getAsciiStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1464 */     return this.resultSet.getUnicodeStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1473 */     return this.resultSet.getObject(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1482 */     return this.resultSet.getCharacterStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1492 */     return this.resultSet.getObject(paramString, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1501 */     return this.resultSet.getRef(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1510 */     return this.resultSet.getBlob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1519 */     return this.resultSet.getClob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1528 */     return this.resultSet.getArray(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1537 */     return this.resultSet.getBigDecimal(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1546 */     return this.resultSet.getDate(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1555 */     return this.resultSet.getTime(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1564 */     return this.resultSet.getBinaryStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 1573 */     return this.resultSet.getTimestamp(paramString, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1581 */     return this.resultSet.getString(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1590 */     return this.resultSet.getBoolean(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1599 */     return this.resultSet.getByte(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1608 */     return this.resultSet.getShort(paramString);
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
/*      */   public void updateNull(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1622 */     if (isReadOnly())
/*      */     {
/* 1624 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1625 */       localSQLException.fillInStackTrace();
/* 1626 */       throw localSQLException;
/*      */     }
/*      */     
/* 1629 */     this.resultSet.updateNull(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1640 */     if (isReadOnly())
/*      */     {
/* 1642 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1643 */       localSQLException.fillInStackTrace();
/* 1644 */       throw localSQLException;
/*      */     }
/*      */     
/* 1647 */     this.resultSet.updateCharacterStream(paramInt1, paramReader, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 1657 */     if (isReadOnly())
/*      */     {
/* 1659 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1660 */       localSQLException.fillInStackTrace();
/* 1661 */       throw localSQLException;
/*      */     }
/*      */     
/* 1664 */     this.resultSet.updateTimestamp(paramInt, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1675 */     if (isReadOnly())
/*      */     {
/* 1677 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1678 */       localSQLException.fillInStackTrace();
/* 1679 */       throw localSQLException;
/*      */     }
/*      */     
/* 1682 */     this.resultSet.updateBinaryStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1693 */     if (isReadOnly())
/*      */     {
/* 1695 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1696 */       localSQLException.fillInStackTrace();
/* 1697 */       throw localSQLException;
/*      */     }
/*      */     
/* 1700 */     this.resultSet.updateAsciiStream(paramInt1, paramInputStream, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1710 */     if (isReadOnly())
/*      */     {
/* 1712 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1713 */       localSQLException.fillInStackTrace();
/* 1714 */       throw localSQLException;
/*      */     }
/*      */     
/* 1717 */     this.resultSet.updateBoolean(paramInt, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 1727 */     if (isReadOnly())
/*      */     {
/* 1729 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1730 */       localSQLException.fillInStackTrace();
/* 1731 */       throw localSQLException;
/*      */     }
/*      */     
/* 1734 */     this.resultSet.updateByte(paramInt, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 1744 */     if (isReadOnly())
/*      */     {
/* 1746 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1747 */       localSQLException.fillInStackTrace();
/* 1748 */       throw localSQLException;
/*      */     }
/*      */     
/* 1751 */     this.resultSet.updateShort(paramInt, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1761 */     if (isReadOnly())
/*      */     {
/* 1763 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1764 */       localSQLException.fillInStackTrace();
/* 1765 */       throw localSQLException;
/*      */     }
/*      */     
/* 1768 */     this.resultSet.updateInt(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1778 */     if (isReadOnly())
/*      */     {
/* 1780 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1781 */       localSQLException.fillInStackTrace();
/* 1782 */       throw localSQLException;
/*      */     }
/*      */     
/* 1785 */     this.resultSet.updateLong(paramInt, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 1795 */     if (isReadOnly())
/*      */     {
/* 1797 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1798 */       localSQLException.fillInStackTrace();
/* 1799 */       throw localSQLException;
/*      */     }
/*      */     
/* 1802 */     this.resultSet.updateFloat(paramInt, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 1812 */     if (isReadOnly())
/*      */     {
/* 1814 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1815 */       localSQLException.fillInStackTrace();
/* 1816 */       throw localSQLException;
/*      */     }
/*      */     
/* 1819 */     this.resultSet.updateDouble(paramInt, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 1829 */     if (isReadOnly())
/*      */     {
/* 1831 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1832 */       localSQLException.fillInStackTrace();
/* 1833 */       throw localSQLException;
/*      */     }
/*      */     
/* 1836 */     this.resultSet.updateBigDecimal(paramInt, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1846 */     if (isReadOnly())
/*      */     {
/* 1848 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1849 */       localSQLException.fillInStackTrace();
/* 1850 */       throw localSQLException;
/*      */     }
/*      */     
/* 1853 */     this.resultSet.updateString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1863 */     if (isReadOnly())
/*      */     {
/* 1865 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1866 */       localSQLException.fillInStackTrace();
/* 1867 */       throw localSQLException;
/*      */     }
/*      */     
/* 1870 */     this.resultSet.updateBytes(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDate(int paramInt, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 1880 */     if (isReadOnly())
/*      */     {
/* 1882 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1883 */       localSQLException.fillInStackTrace();
/* 1884 */       throw localSQLException;
/*      */     }
/*      */     
/* 1887 */     this.resultSet.updateDate(paramInt, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 1897 */     if (isReadOnly())
/*      */     {
/* 1899 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1900 */       localSQLException.fillInStackTrace();
/* 1901 */       throw localSQLException;
/*      */     }
/*      */     
/* 1904 */     this.resultSet.updateTime(paramInt, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 1914 */     if (isReadOnly())
/*      */     {
/* 1916 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1917 */       localSQLException.fillInStackTrace();
/* 1918 */       throw localSQLException;
/*      */     }
/*      */     
/* 1921 */     this.resultSet.updateObject(paramInt, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1931 */     if (isReadOnly())
/*      */     {
/* 1933 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1934 */       localSQLException.fillInStackTrace();
/* 1935 */       throw localSQLException;
/*      */     }
/*      */     
/* 1938 */     this.resultSet.updateObject(paramInt1, paramObject, paramInt2);
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
/*      */   public void updateNull(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1953 */     if (isReadOnly())
/*      */     {
/* 1955 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1956 */       localSQLException.fillInStackTrace();
/* 1957 */       throw localSQLException;
/*      */     }
/*      */     
/* 1960 */     this.resultSet.updateNull(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1970 */     if (isReadOnly())
/*      */     {
/* 1972 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1973 */       localSQLException.fillInStackTrace();
/* 1974 */       throw localSQLException;
/*      */     }
/*      */     
/* 1977 */     this.resultSet.updateBoolean(paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 1987 */     if (isReadOnly())
/*      */     {
/* 1989 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 1990 */       localSQLException.fillInStackTrace();
/* 1991 */       throw localSQLException;
/*      */     }
/*      */     
/* 1994 */     this.resultSet.updateByte(paramString, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2004 */     if (isReadOnly())
/*      */     {
/* 2006 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2007 */       localSQLException.fillInStackTrace();
/* 2008 */       throw localSQLException;
/*      */     }
/*      */     
/* 2011 */     this.resultSet.updateShort(paramString, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2021 */     if (isReadOnly())
/*      */     {
/* 2023 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2024 */       localSQLException.fillInStackTrace();
/* 2025 */       throw localSQLException;
/*      */     }
/*      */     
/* 2028 */     this.resultSet.updateInt(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2038 */     if (isReadOnly())
/*      */     {
/* 2040 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2041 */       localSQLException.fillInStackTrace();
/* 2042 */       throw localSQLException;
/*      */     }
/*      */     
/* 2045 */     this.resultSet.updateLong(paramString, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 2055 */     if (isReadOnly())
/*      */     {
/* 2057 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2058 */       localSQLException.fillInStackTrace();
/* 2059 */       throw localSQLException;
/*      */     }
/*      */     
/* 2062 */     this.resultSet.updateFloat(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 2072 */     if (isReadOnly())
/*      */     {
/* 2074 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2075 */       localSQLException.fillInStackTrace();
/* 2076 */       throw localSQLException;
/*      */     }
/*      */     
/* 2079 */     this.resultSet.updateDouble(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 2089 */     if (isReadOnly())
/*      */     {
/* 2091 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2092 */       localSQLException.fillInStackTrace();
/* 2093 */       throw localSQLException;
/*      */     }
/*      */     
/* 2096 */     this.resultSet.updateBigDecimal(paramString, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 2106 */     if (isReadOnly())
/*      */     {
/* 2108 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2109 */       localSQLException.fillInStackTrace();
/* 2110 */       throw localSQLException;
/*      */     }
/*      */     
/* 2113 */     this.resultSet.updateString(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2123 */     if (isReadOnly())
/*      */     {
/* 2125 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2126 */       localSQLException.fillInStackTrace();
/* 2127 */       throw localSQLException;
/*      */     }
/*      */     
/* 2130 */     this.resultSet.updateBytes(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 2140 */     if (isReadOnly())
/*      */     {
/* 2142 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2143 */       localSQLException.fillInStackTrace();
/* 2144 */       throw localSQLException;
/*      */     }
/*      */     
/* 2147 */     this.resultSet.updateDate(paramString, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 2157 */     if (isReadOnly())
/*      */     {
/* 2159 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2160 */       localSQLException.fillInStackTrace();
/* 2161 */       throw localSQLException;
/*      */     }
/*      */     
/* 2164 */     this.resultSet.updateTime(paramString, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 2174 */     if (isReadOnly())
/*      */     {
/* 2176 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2177 */       localSQLException.fillInStackTrace();
/* 2178 */       throw localSQLException;
/*      */     }
/*      */     
/* 2181 */     this.resultSet.updateObject(paramString, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2191 */     if (isReadOnly())
/*      */     {
/* 2193 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2194 */       localSQLException.fillInStackTrace();
/* 2195 */       throw localSQLException;
/*      */     }
/*      */     
/* 2198 */     this.resultSet.updateObject(paramString, paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2209 */     if (isReadOnly())
/*      */     {
/* 2211 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2212 */       localSQLException.fillInStackTrace();
/* 2213 */       throw localSQLException;
/*      */     }
/*      */     
/* 2216 */     this.resultSet.updateBinaryStream(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2227 */     if (isReadOnly())
/*      */     {
/* 2229 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2230 */       localSQLException.fillInStackTrace();
/* 2231 */       throw localSQLException;
/*      */     }
/*      */     
/* 2234 */     this.resultSet.updateAsciiStream(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 2244 */     if (isReadOnly())
/*      */     {
/* 2246 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2247 */       localSQLException.fillInStackTrace();
/* 2248 */       throw localSQLException;
/*      */     }
/*      */     
/* 2251 */     this.resultSet.updateTimestamp(paramString, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2262 */     if (isReadOnly())
/*      */     {
/* 2264 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2265 */       localSQLException.fillInStackTrace();
/* 2266 */       throw localSQLException;
/*      */     }
/*      */     
/* 2269 */     this.resultSet.updateCharacterStream(paramString, paramReader, paramInt);
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
/*      */   public URL getURL(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2297 */     return ((OracleResultSet)this.resultSet).getURL(paramInt);
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
/*      */   public URL getURL(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2321 */     return ((OracleResultSet)this.resultSet).getURL(paramString);
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
/*      */   public void updateRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 2344 */     if (isReadOnly())
/*      */     {
/* 2346 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2347 */       localSQLException.fillInStackTrace();
/* 2348 */       throw localSQLException;
/*      */     }
/*      */     
/* 2351 */     ((OracleResultSet)this.resultSet).updateRef(paramInt, paramRef);
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
/*      */   public void updateRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 2375 */     if (isReadOnly())
/*      */     {
/* 2377 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2378 */       localSQLException.fillInStackTrace();
/* 2379 */       throw localSQLException;
/*      */     }
/*      */     
/* 2382 */     ((OracleResultSet)this.resultSet).updateRef(paramString, paramRef);
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
/*      */   public void updateBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 2406 */     if (isReadOnly())
/*      */     {
/* 2408 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2409 */       localSQLException.fillInStackTrace();
/* 2410 */       throw localSQLException;
/*      */     }
/*      */     
/* 2413 */     ((OracleResultSet)this.resultSet).updateBlob(paramInt, paramBlob);
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
/*      */   public void updateBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 2437 */     if (isReadOnly())
/*      */     {
/* 2439 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2440 */       localSQLException.fillInStackTrace();
/* 2441 */       throw localSQLException;
/*      */     }
/*      */     
/* 2444 */     ((OracleResultSet)this.resultSet).updateBlob(paramString, paramBlob);
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
/*      */   public void updateClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 2468 */     if (isReadOnly())
/*      */     {
/* 2470 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2471 */       localSQLException.fillInStackTrace();
/* 2472 */       throw localSQLException;
/*      */     }
/*      */     
/* 2475 */     ((OracleResultSet)this.resultSet).updateClob(paramInt, paramClob);
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
/*      */   public void updateClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 2499 */     if (isReadOnly())
/*      */     {
/* 2501 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2502 */       localSQLException.fillInStackTrace();
/* 2503 */       throw localSQLException;
/*      */     }
/*      */     
/* 2506 */     ((OracleResultSet)this.resultSet).updateClob(paramString, paramClob);
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
/*      */   public void updateArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 2530 */     if (isReadOnly())
/*      */     {
/* 2532 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2533 */       localSQLException.fillInStackTrace();
/* 2534 */       throw localSQLException;
/*      */     }
/*      */     
/* 2537 */     ((OracleResultSet)this.resultSet).updateArray(paramInt, paramArray);
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
/*      */   public void updateArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 2561 */     if (isReadOnly())
/*      */     {
/* 2563 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2564 */       localSQLException.fillInStackTrace();
/* 2565 */       throw localSQLException;
/*      */     }
/*      */     
/* 2568 */     ((OracleResultSet)this.resultSet).updateArray(paramString, paramArray);
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
/*      */   public void commit()
/*      */     throws SQLException
/*      */   {
/* 2593 */     if (this.connection != null)
/*      */     {
/* 2595 */       this.connection.commit();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2600 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2601 */       localSQLException.fillInStackTrace();
/* 2602 */       throw localSQLException;
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
/*      */ 
/*      */   public void rollback()
/*      */     throws SQLException
/*      */   {
/* 2626 */     if (this.connection != null)
/*      */     {
/* 2628 */       this.connection.rollback();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2633 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2634 */       localSQLException.fillInStackTrace();
/* 2635 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public void rollback(Savepoint paramSavepoint)
/*      */     throws SQLException
/*      */   {
/* 2661 */     if (this.connection != null)
/*      */     {
/* 2663 */       this.connection.rollback(paramSavepoint);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2668 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2669 */       localSQLException.fillInStackTrace();
/* 2670 */       throw localSQLException;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void oracleRollback(OracleSavepoint paramOracleSavepoint)
/*      */     throws SQLException
/*      */   {
/* 2698 */     if (this.connection != null)
/*      */     {
/* 2700 */       ((oracle.jdbc.OracleConnection)this.connection).oracleRollback(paramOracleSavepoint);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2705 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2706 */       localSQLException.fillInStackTrace();
/* 2707 */       throw localSQLException;
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
/*      */   public boolean getAutoCommit()
/*      */     throws SQLException
/*      */   {
/* 2729 */     if (this.connection != null)
/*      */     {
/* 2731 */       return this.connection.getAutoCommit();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2736 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2737 */     localSQLException.fillInStackTrace();
/* 2738 */     throw localSQLException;
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
/*      */   public void setAutoCommit(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2764 */     if (this.connection != null)
/*      */     {
/* 2766 */       this.connection.setAutoCommit(paramBoolean);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2771 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 302);
/* 2772 */       localSQLException.fillInStackTrace();
/* 2773 */       throw localSQLException;
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
/*      */   public RowSetWarning getRowSetWarnings()
/*      */     throws SQLException
/*      */   {
/* 2790 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String getTableName()
/*      */     throws SQLException
/*      */   {
/* 2803 */     return getMetaData().getTableName(getMatchColumnIndexes()[0]);
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
/*      */   public Reader getNCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2822 */     return this.resultSet.getNCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2831 */     return this.resultSet.getNClob(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2840 */     return this.resultSet.getNClob(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2849 */     return this.resultSet.getNString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2858 */     return this.resultSet.getRowId(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2867 */     return this.resultSet.getSQLXML(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getNCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2880 */     return this.resultSet.getNCharacterStream(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2889 */     return this.resultSet.getNString(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2898 */     return this.resultSet.getRowId(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2907 */     return this.resultSet.getSQLXML(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2920 */     this.preparedStatement.setAsciiStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2930 */     this.preparedStatement.setBinaryStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 2940 */     this.preparedStatement.setBlob(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2950 */     this.preparedStatement.setBlob(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2960 */     this.preparedStatement.setCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2970 */     this.preparedStatement.setClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2980 */     this.preparedStatement.setClob(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 2990 */     this.preparedStatement.setNCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3000 */     this.preparedStatement.setNCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 3010 */     this.preparedStatement.setNClob(paramInt, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3020 */     this.preparedStatement.setNClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3030 */     this.preparedStatement.setNClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 3040 */     this.preparedStatement.setNString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 3050 */     this.preparedStatement.setRowId(paramInt, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 3060 */     this.preparedStatement.setSQLXML(paramInt, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(int paramInt, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 3070 */     this.preparedStatement.setURL(paramInt, paramURL);
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
/*      */   public void setArray(String paramString, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 3088 */     ((OraclePreparedStatement)this.preparedStatement).setArrayAtName(paramString, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 3098 */     ((OraclePreparedStatement)this.preparedStatement).setBigDecimalAtName(paramString, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 3108 */     ((OraclePreparedStatement)this.preparedStatement).setBlobAtName(paramString, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3118 */     ((OraclePreparedStatement)this.preparedStatement).setBooleanAtName(paramString, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 3128 */     ((OraclePreparedStatement)this.preparedStatement).setByteAtName(paramString, paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 3138 */     ((OraclePreparedStatement)this.preparedStatement).setBytesAtName(paramString, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 3148 */     ((OraclePreparedStatement)this.preparedStatement).setClobAtName(paramString, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 3158 */     ((OraclePreparedStatement)this.preparedStatement).setDateAtName(paramString, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3168 */     ((OraclePreparedStatement)this.preparedStatement).setDateAtName(paramString, paramDate, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 3178 */     ((OraclePreparedStatement)this.preparedStatement).setDoubleAtName(paramString, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 3188 */     ((OraclePreparedStatement)this.preparedStatement).setFloatAtName(paramString, paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3198 */     ((OraclePreparedStatement)this.preparedStatement).setIntAtName(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3208 */     ((OraclePreparedStatement)this.preparedStatement).setLongAtName(paramString, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 3218 */     ((OraclePreparedStatement)this.preparedStatement).setNClobAtName(paramString, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3228 */     ((OraclePreparedStatement)this.preparedStatement).setNStringAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 3238 */     ((OraclePreparedStatement)this.preparedStatement).setObjectAtName(paramString, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3248 */     ((OraclePreparedStatement)this.preparedStatement).setObjectAtName(paramString, paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 3258 */     ((OraclePreparedStatement)this.preparedStatement).setRefAtName(paramString, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 3268 */     ((OraclePreparedStatement)this.preparedStatement).setRowIdAtName(paramString, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 3278 */     ((OraclePreparedStatement)this.preparedStatement).setShortAtName(paramString, paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 3288 */     ((OraclePreparedStatement)this.preparedStatement).setSQLXMLAtName(paramString, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3298 */     ((OraclePreparedStatement)this.preparedStatement).setStringAtName(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 3308 */     ((OraclePreparedStatement)this.preparedStatement).setTimeAtName(paramString, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3318 */     ((OraclePreparedStatement)this.preparedStatement).setTimeAtName(paramString, paramTime, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 3328 */     ((OraclePreparedStatement)this.preparedStatement).setTimestampAtName(paramString, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3338 */     ((OraclePreparedStatement)this.preparedStatement).setTimestampAtName(paramString, paramTimestamp, paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 3348 */     ((OraclePreparedStatement)this.preparedStatement).setURLAtName(paramString, paramURL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3358 */     ((OraclePreparedStatement)this.preparedStatement).setBlobAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3368 */     ((OraclePreparedStatement)this.preparedStatement).setBlobAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3378 */     ((OraclePreparedStatement)this.preparedStatement).setClobAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3388 */     ((OraclePreparedStatement)this.preparedStatement).setClobAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3398 */     ((OraclePreparedStatement)this.preparedStatement).setNClobAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3408 */     ((OraclePreparedStatement)this.preparedStatement).setNClobAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3418 */     ((OraclePreparedStatement)this.preparedStatement).setAsciiStreamAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3428 */     ((OraclePreparedStatement)this.preparedStatement).setAsciiStreamAtName(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3438 */     ((OraclePreparedStatement)this.preparedStatement).setAsciiStreamAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3448 */     ((OraclePreparedStatement)this.preparedStatement).setBinaryStreamAtName(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3458 */     ((OraclePreparedStatement)this.preparedStatement).setBinaryStreamAtName(paramString, paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3468 */     ((OraclePreparedStatement)this.preparedStatement).setBinaryStreamAtName(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3478 */     ((OraclePreparedStatement)this.preparedStatement).setCharacterStreamAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3488 */     ((OraclePreparedStatement)this.preparedStatement).setCharacterStreamAtName(paramString, paramReader, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3498 */     ((OraclePreparedStatement)this.preparedStatement).setCharacterStreamAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3508 */     ((OraclePreparedStatement)this.preparedStatement).setNCharacterStreamAtName(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3518 */     ((OraclePreparedStatement)this.preparedStatement).setNCharacterStreamAtName(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3528 */     ((OraclePreparedStatement)this.preparedStatement).setUnicodeStreamAtName(paramString, paramInputStream, paramInt);
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
/*      */   public void setNull(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3545 */     ((OraclePreparedStatement)this.preparedStatement).setNullAtName(paramString, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 3555 */     ((OraclePreparedStatement)this.preparedStatement).setNullAtName(paramString1, paramInt, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3566 */     ((OraclePreparedStatement)this.preparedStatement).setObjectAtName(paramString, paramObject, paramInt1, paramInt2);
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
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3580 */     if (isReadOnly())
/*      */     {
/* 3582 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3583 */       localSQLException.fillInStackTrace();
/* 3584 */       throw localSQLException;
/*      */     }
/*      */     
/* 3587 */     this.resultSet.updateAsciiStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3597 */     if (isReadOnly())
/*      */     {
/* 3599 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3600 */       localSQLException.fillInStackTrace();
/* 3601 */       throw localSQLException;
/*      */     }
/*      */     
/* 3604 */     this.resultSet.updateAsciiStream(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3614 */     if (isReadOnly())
/*      */     {
/* 3616 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3617 */       localSQLException.fillInStackTrace();
/* 3618 */       throw localSQLException;
/*      */     }
/*      */     
/* 3621 */     this.resultSet.updateAsciiStream(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3631 */     if (isReadOnly())
/*      */     {
/* 3633 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3634 */       localSQLException.fillInStackTrace();
/* 3635 */       throw localSQLException;
/*      */     }
/*      */     
/* 3638 */     this.resultSet.updateAsciiStream(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3648 */     if (isReadOnly())
/*      */     {
/* 3650 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3651 */       localSQLException.fillInStackTrace();
/* 3652 */       throw localSQLException;
/*      */     }
/*      */     
/* 3655 */     this.resultSet.updateBinaryStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3665 */     if (isReadOnly())
/*      */     {
/* 3667 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3668 */       localSQLException.fillInStackTrace();
/* 3669 */       throw localSQLException;
/*      */     }
/*      */     
/* 3672 */     this.resultSet.updateBinaryStream(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3682 */     if (isReadOnly())
/*      */     {
/* 3684 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3685 */       localSQLException.fillInStackTrace();
/* 3686 */       throw localSQLException;
/*      */     }
/*      */     
/* 3689 */     this.resultSet.updateBinaryStream(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3699 */     if (isReadOnly())
/*      */     {
/* 3701 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3702 */       localSQLException.fillInStackTrace();
/* 3703 */       throw localSQLException;
/*      */     }
/*      */     
/* 3706 */     this.resultSet.updateBinaryStream(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3716 */     if (isReadOnly())
/*      */     {
/* 3718 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3719 */       localSQLException.fillInStackTrace();
/* 3720 */       throw localSQLException;
/*      */     }
/*      */     
/* 3723 */     this.resultSet.updateBlob(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3733 */     if (isReadOnly())
/*      */     {
/* 3735 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3736 */       localSQLException.fillInStackTrace();
/* 3737 */       throw localSQLException;
/*      */     }
/*      */     
/* 3740 */     this.resultSet.updateBlob(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 3750 */     if (isReadOnly())
/*      */     {
/* 3752 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3753 */       localSQLException.fillInStackTrace();
/* 3754 */       throw localSQLException;
/*      */     }
/*      */     
/* 3757 */     this.resultSet.updateBlob(paramString, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3767 */     if (isReadOnly())
/*      */     {
/* 3769 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3770 */       localSQLException.fillInStackTrace();
/* 3771 */       throw localSQLException;
/*      */     }
/*      */     
/* 3774 */     this.resultSet.updateBlob(paramString, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3784 */     if (isReadOnly())
/*      */     {
/* 3786 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3787 */       localSQLException.fillInStackTrace();
/* 3788 */       throw localSQLException;
/*      */     }
/*      */     
/* 3791 */     this.resultSet.updateCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3801 */     if (isReadOnly())
/*      */     {
/* 3803 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3804 */       localSQLException.fillInStackTrace();
/* 3805 */       throw localSQLException;
/*      */     }
/*      */     
/* 3808 */     this.resultSet.updateCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3818 */     if (isReadOnly())
/*      */     {
/* 3820 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3821 */       localSQLException.fillInStackTrace();
/* 3822 */       throw localSQLException;
/*      */     }
/*      */     
/* 3825 */     this.resultSet.updateCharacterStream(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3835 */     if (isReadOnly())
/*      */     {
/* 3837 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3838 */       localSQLException.fillInStackTrace();
/* 3839 */       throw localSQLException;
/*      */     }
/*      */     
/* 3842 */     this.resultSet.updateCharacterStream(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3852 */     if (isReadOnly())
/*      */     {
/* 3854 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3855 */       localSQLException.fillInStackTrace();
/* 3856 */       throw localSQLException;
/*      */     }
/*      */     
/* 3859 */     this.resultSet.updateClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3869 */     if (isReadOnly())
/*      */     {
/* 3871 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3872 */       localSQLException.fillInStackTrace();
/* 3873 */       throw localSQLException;
/*      */     }
/*      */     
/* 3876 */     this.resultSet.updateClob(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3886 */     if (isReadOnly())
/*      */     {
/* 3888 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3889 */       localSQLException.fillInStackTrace();
/* 3890 */       throw localSQLException;
/*      */     }
/*      */     
/* 3893 */     this.resultSet.updateClob(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3903 */     if (isReadOnly())
/*      */     {
/* 3905 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3906 */       localSQLException.fillInStackTrace();
/* 3907 */       throw localSQLException;
/*      */     }
/*      */     
/* 3910 */     this.resultSet.updateClob(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3920 */     if (isReadOnly())
/*      */     {
/* 3922 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3923 */       localSQLException.fillInStackTrace();
/* 3924 */       throw localSQLException;
/*      */     }
/*      */     
/* 3927 */     this.resultSet.updateNCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3937 */     if (isReadOnly())
/*      */     {
/* 3939 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3940 */       localSQLException.fillInStackTrace();
/* 3941 */       throw localSQLException;
/*      */     }
/*      */     
/* 3944 */     this.resultSet.updateNCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 3954 */     if (isReadOnly())
/*      */     {
/* 3956 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3957 */       localSQLException.fillInStackTrace();
/* 3958 */       throw localSQLException;
/*      */     }
/*      */     
/* 3961 */     this.resultSet.updateNCharacterStream(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3971 */     if (isReadOnly())
/*      */     {
/* 3973 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3974 */       localSQLException.fillInStackTrace();
/* 3975 */       throw localSQLException;
/*      */     }
/*      */     
/* 3978 */     this.resultSet.updateNCharacterStream(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 3988 */     if (isReadOnly())
/*      */     {
/* 3990 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 3991 */       localSQLException.fillInStackTrace();
/* 3992 */       throw localSQLException;
/*      */     }
/*      */     
/* 3995 */     this.resultSet.updateNClob(paramInt, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 4005 */     if (isReadOnly())
/*      */     {
/* 4007 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4008 */       localSQLException.fillInStackTrace();
/* 4009 */       throw localSQLException;
/*      */     }
/*      */     
/* 4012 */     this.resultSet.updateNClob(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 4022 */     if (isReadOnly())
/*      */     {
/* 4024 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4025 */       localSQLException.fillInStackTrace();
/* 4026 */       throw localSQLException;
/*      */     }
/*      */     
/* 4029 */     this.resultSet.updateNClob(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 4039 */     if (isReadOnly())
/*      */     {
/* 4041 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4042 */       localSQLException.fillInStackTrace();
/* 4043 */       throw localSQLException;
/*      */     }
/*      */     
/* 4046 */     this.resultSet.updateNClob(paramString, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 4056 */     if (isReadOnly())
/*      */     {
/* 4058 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4059 */       localSQLException.fillInStackTrace();
/* 4060 */       throw localSQLException;
/*      */     }
/*      */     
/* 4063 */     this.resultSet.updateNClob(paramString, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 4073 */     if (isReadOnly())
/*      */     {
/* 4075 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4076 */       localSQLException.fillInStackTrace();
/* 4077 */       throw localSQLException;
/*      */     }
/*      */     
/* 4080 */     this.resultSet.updateNClob(paramString, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4090 */     if (isReadOnly())
/*      */     {
/* 4092 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4093 */       localSQLException.fillInStackTrace();
/* 4094 */       throw localSQLException;
/*      */     }
/*      */     
/* 4097 */     this.resultSet.updateNString(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4107 */     if (isReadOnly())
/*      */     {
/* 4109 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4110 */       localSQLException.fillInStackTrace();
/* 4111 */       throw localSQLException;
/*      */     }
/*      */     
/* 4114 */     this.resultSet.updateNString(paramString1, paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 4124 */     if (isReadOnly())
/*      */     {
/* 4126 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4127 */       localSQLException.fillInStackTrace();
/* 4128 */       throw localSQLException;
/*      */     }
/*      */     
/* 4131 */     this.resultSet.updateRowId(paramInt, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 4141 */     if (isReadOnly())
/*      */     {
/* 4143 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4144 */       localSQLException.fillInStackTrace();
/* 4145 */       throw localSQLException;
/*      */     }
/*      */     
/* 4148 */     this.resultSet.updateRowId(paramString, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 4158 */     if (isReadOnly())
/*      */     {
/* 4160 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4161 */       localSQLException.fillInStackTrace();
/* 4162 */       throw localSQLException;
/*      */     }
/*      */     
/* 4165 */     this.resultSet.updateSQLXML(paramInt, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 4175 */     if (isReadOnly())
/*      */     {
/* 4177 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 4178 */       localSQLException.fillInStackTrace();
/* 4179 */       throw localSQLException;
/*      */     }
/*      */     
/* 4182 */     this.resultSet.updateSQLXML(paramString, paramSQLXML);
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/*      */     try
/*      */     {
/* 4203 */       return (oracle.jdbc.internal.OracleConnection)this.connection;
/*      */     }
/*      */     catch (Exception localException) {}
/*      */     
/* 4207 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 4214 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleJDBCRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */