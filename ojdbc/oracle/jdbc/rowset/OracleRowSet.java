/*      */ package oracle.jdbc.rowset;
/*      */ 
/*      */ import java.io.Serializable;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import javax.sql.RowSet;
/*      */ import javax.sql.RowSetEvent;
/*      */ import javax.sql.RowSetListener;
/*      */ import javax.sql.rowset.Joinable;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ abstract class OracleRowSet
/*      */   implements Serializable, Cloneable, Joinable
/*      */ {
/*      */   protected String dataSource;
/*      */   protected String dataSourceName;
/*      */   protected String url;
/*      */   protected String username;
/*      */   protected String password;
/*      */   protected Map typeMap;
/*      */   protected int maxFieldSize;
/*      */   protected int maxRows;
/*      */   protected int queryTimeout;
/*      */   protected int fetchSize;
/*      */   protected int transactionIsolation;
/*      */   protected boolean escapeProcessing;
/*      */   protected String command;
/*      */   protected int concurrency;
/*      */   protected boolean readOnly;
/*      */   protected int fetchDirection;
/*      */   protected int rowsetType;
/*      */   protected boolean showDeleted;
/*      */   protected Vector listener;
/*      */   protected RowSetEvent rowsetEvent;
/*      */   protected Vector matchColumnIndexes;
/*      */   protected Vector matchColumnNames;
/*      */   protected boolean isClosed;
/*      */   
/*      */   protected OracleRowSet()
/*      */     throws SQLException
/*      */   {
/*  264 */     initializeProperties();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  269 */     this.matchColumnIndexes = new Vector(10);
/*  270 */     this.matchColumnNames = new Vector(10);
/*      */     
/*  272 */     this.listener = new Vector();
/*  273 */     this.rowsetEvent = new RowSetEvent((RowSet)this);
/*      */     
/*  275 */     this.isClosed = false;
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
/*      */   protected void initializeProperties()
/*      */   {
/*  288 */     this.command = null;
/*  289 */     this.concurrency = 1007;
/*  290 */     this.dataSource = null;
/*  291 */     this.dataSourceName = null;
/*      */     
/*  293 */     this.escapeProcessing = true;
/*  294 */     this.fetchDirection = 1002;
/*  295 */     this.fetchSize = 0;
/*  296 */     this.maxFieldSize = 0;
/*  297 */     this.maxRows = 0;
/*  298 */     this.queryTimeout = 0;
/*  299 */     this.readOnly = false;
/*  300 */     this.showDeleted = false;
/*  301 */     this.transactionIsolation = 2;
/*  302 */     this.rowsetType = 1005;
/*  303 */     this.typeMap = new HashMap();
/*  304 */     this.username = null;
/*  305 */     this.password = null;
/*  306 */     this.url = null;
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
/*      */   public String getCommand()
/*      */   {
/*  322 */     return this.command;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getConcurrency()
/*      */     throws SQLException
/*      */   {
/*  331 */     return this.concurrency;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public String getDataSource()
/*      */   {
/*  342 */     return this.dataSource;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDataSourceName()
/*      */   {
/*  350 */     return this.dataSourceName;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getEscapeProcessing()
/*      */     throws SQLException
/*      */   {
/*  359 */     return this.escapeProcessing;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getFetchDirection()
/*      */     throws SQLException
/*      */   {
/*  368 */     return this.fetchDirection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getFetchSize()
/*      */     throws SQLException
/*      */   {
/*  377 */     return this.fetchSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaxFieldSize()
/*      */     throws SQLException
/*      */   {
/*  386 */     return this.maxFieldSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaxRows()
/*      */     throws SQLException
/*      */   {
/*  395 */     return this.maxRows;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPassword()
/*      */   {
/*  403 */     return this.password;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getQueryTimeout()
/*      */     throws SQLException
/*      */   {
/*  412 */     return this.queryTimeout;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getReadOnly()
/*      */   {
/*  420 */     return isReadOnly();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isReadOnly()
/*      */   {
/*  428 */     return this.readOnly;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getShowDeleted()
/*      */   {
/*  436 */     return this.showDeleted;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTransactionIsolation()
/*      */   {
/*  444 */     return this.transactionIsolation;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getType()
/*      */     throws SQLException
/*      */   {
/*  453 */     return this.rowsetType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Map getTypeMap()
/*      */     throws SQLException
/*      */   {
/*  462 */     return this.typeMap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getUrl()
/*      */   {
/*  470 */     return this.url;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getUsername()
/*      */   {
/*  478 */     return this.username;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCommand(String paramString)
/*      */     throws SQLException
/*      */   {
/*  488 */     this.command = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setConcurrency(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  498 */     if ((paramInt == 1007) || (paramInt == 1008)) {
/*  499 */       this.concurrency = paramInt;
/*      */     }
/*      */     else {
/*  502 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  503 */       localSQLException.fillInStackTrace();
/*  504 */       throw localSQLException;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setDataSource(String paramString)
/*      */   {
/*  521 */     this.dataSource = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataSourceName(String paramString)
/*      */     throws SQLException
/*      */   {
/*  531 */     this.dataSourceName = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEscapeProcessing(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  541 */     this.escapeProcessing = paramBoolean;
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
/*      */   public void setFetchDirection(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  557 */     this.fetchDirection = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFetchSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  567 */     this.fetchSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaxFieldSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  578 */     this.maxFieldSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaxRows(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  588 */     this.maxRows = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPassword(String paramString)
/*      */     throws SQLException
/*      */   {
/*  598 */     this.password = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setQueryTimeout(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  608 */     this.queryTimeout = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setReadOnly(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  619 */     this.readOnly = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShowDeleted(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  629 */     this.showDeleted = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTransactionIsolation(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  639 */     this.transactionIsolation = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setType(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  649 */     if ((paramInt == 1003) || (paramInt == 1004) || (paramInt == 1005))
/*      */     {
/*      */ 
/*  652 */       this.rowsetType = paramInt;
/*      */     }
/*      */     else {
/*  655 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  656 */       localSQLException.fillInStackTrace();
/*  657 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTypeMap(Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  668 */     this.typeMap = paramMap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUrl(String paramString)
/*      */   {
/*  677 */     this.url = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUsername(String paramString)
/*      */     throws SQLException
/*      */   {
/*  687 */     this.username = paramString;
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
/*      */   public void addRowSetListener(RowSetListener paramRowSetListener)
/*      */   {
/*  707 */     for (int i = 0; i < this.listener.size(); i++)
/*  708 */       if (this.listener.elementAt(i).equals(paramRowSetListener))
/*  709 */         return;
/*  710 */     this.listener.add(paramRowSetListener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeRowSetListener(RowSetListener paramRowSetListener)
/*      */   {
/*  719 */     for (int i = 0; i < this.listener.size(); i++) {
/*  720 */       if (this.listener.elementAt(i).equals(paramRowSetListener)) {
/*  721 */         this.listener.remove(i);
/*      */       }
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
/*      */   protected synchronized void notifyCursorMoved()
/*      */   {
/*  735 */     int i = this.listener.size();
/*  736 */     if (i > 0) {
/*  737 */       for (int j = 0; j < i; j++) {
/*  738 */         ((RowSetListener)this.listener.elementAt(j)).cursorMoved(this.rowsetEvent);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void notifyRowChanged()
/*      */   {
/*  751 */     int i = this.listener.size();
/*  752 */     if (i > 0) {
/*  753 */       for (int j = 0; j < i; j++)
/*      */       {
/*  755 */         ((RowSetListener)this.listener.elementAt(j)).rowChanged(this.rowsetEvent);
/*      */       }
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
/*      */   protected void notifyRowSetChanged()
/*      */   {
/*  769 */     int i = this.listener.size();
/*  770 */     if (i > 0) {
/*  771 */       for (int j = 0; j < i; j++)
/*      */       {
/*  773 */         ((RowSetListener)this.listener.elementAt(j)).rowSetChanged(this.rowsetEvent);
/*      */       }
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
/*      */   public int[] getMatchColumnIndexes()
/*      */     throws SQLException
/*      */   {
/*  802 */     if ((this.matchColumnIndexes.size() == 0) && (this.matchColumnNames.size() == 0))
/*      */     {
/*  804 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 334);
/*  805 */       localSQLException1.fillInStackTrace();
/*  806 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */     int i;
/*      */     int[] arrayOfInt;
/*      */     int k;
/*  812 */     if (this.matchColumnNames.size() > 0)
/*      */     {
/*  814 */       String[] arrayOfString = getMatchColumnNames();
/*  815 */       i = arrayOfString.length;
/*  816 */       arrayOfInt = new int[i];
/*      */       
/*  818 */       for (k = 0; k < i; k++)
/*      */       {
/*  820 */         arrayOfInt[k] = findColumn(arrayOfString[k]);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  825 */       i = this.matchColumnIndexes.size();
/*  826 */       arrayOfInt = new int[i];
/*  827 */       int j = -1;
/*      */       
/*  829 */       for (k = 0; k < i; k++)
/*      */       {
/*      */         try
/*      */         {
/*  833 */           j = ((Integer)this.matchColumnIndexes.get(k)).intValue();
/*      */ 
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/*      */ 
/*  839 */           SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/*  840 */           localSQLException3.fillInStackTrace();
/*  841 */           throw localSQLException3;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  847 */         if (j <= 0)
/*      */         {
/*      */ 
/*  850 */           SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/*  851 */           localSQLException2.fillInStackTrace();
/*  852 */           throw localSQLException2;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  858 */         arrayOfInt[k] = j;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  863 */     return arrayOfInt;
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
/*      */   public String[] getMatchColumnNames()
/*      */     throws SQLException
/*      */   {
/*  882 */     checkIfMatchColumnNamesSet();
/*      */     
/*  884 */     int i = this.matchColumnNames.size();
/*  885 */     String[] arrayOfString = new String[i];
/*  886 */     String str = null;
/*      */     
/*  888 */     for (int j = 0; j < i; j++)
/*      */     {
/*      */       try
/*      */       {
/*  892 */         str = (String)this.matchColumnNames.get(j);
/*      */ 
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */ 
/*  898 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 337);
/*  899 */         localSQLException2.fillInStackTrace();
/*  900 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  906 */       if ((str == null) || (str.equals("")))
/*      */       {
/*      */ 
/*  909 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 337);
/*  910 */         localSQLException1.fillInStackTrace();
/*  911 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  917 */       arrayOfString[j] = str;
/*      */     }
/*      */     
/*      */ 
/*  921 */     return arrayOfString;
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
/*      */   public void setMatchColumn(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  940 */     if (paramInt <= 0)
/*      */     {
/*      */ 
/*  943 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/*  944 */       localSQLException1.fillInStackTrace();
/*  945 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  952 */       this.matchColumnIndexes.clear();
/*  953 */       this.matchColumnNames.clear();
/*      */       
/*  955 */       this.matchColumnIndexes.add(0, Integer.valueOf(paramInt));
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */ 
/*  961 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 338);
/*  962 */       localSQLException2.fillInStackTrace();
/*  963 */       throw localSQLException2;
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
/*      */   public void setMatchColumn(int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/*  986 */     this.matchColumnIndexes.clear();
/*  987 */     this.matchColumnNames.clear();
/*      */     
/*  989 */     if (paramArrayOfInt == null)
/*      */     {
/*      */ 
/*  992 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  993 */       localSQLException1.fillInStackTrace();
/*  994 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*  998 */     for (int i = 0; i < paramArrayOfInt.length; i++)
/*      */     {
/* 1000 */       if (paramArrayOfInt[i] <= 0)
/*      */       {
/*      */ 
/* 1003 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/* 1004 */         localSQLException2.fillInStackTrace();
/* 1005 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1011 */         this.matchColumnIndexes.add(i, Integer.valueOf(paramArrayOfInt[i]));
/*      */ 
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */ 
/* 1017 */         SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 338);
/* 1018 */         localSQLException3.fillInStackTrace();
/* 1019 */         throw localSQLException3;
/*      */       }
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
/*      */   public void setMatchColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1043 */     if ((paramString == null) || (paramString.equals("")))
/*      */     {
/*      */ 
/* 1046 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1047 */       localSQLException1.fillInStackTrace();
/* 1048 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/* 1055 */       this.matchColumnIndexes.clear();
/* 1056 */       this.matchColumnNames.clear();
/*      */       
/* 1058 */       this.matchColumnNames.add(0, paramString.trim());
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */ 
/* 1064 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 339);
/* 1065 */       localSQLException2.fillInStackTrace();
/* 1066 */       throw localSQLException2;
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
/*      */   public void setMatchColumn(String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 1090 */     this.matchColumnIndexes.clear();
/* 1091 */     this.matchColumnNames.clear();
/*      */     
/* 1093 */     for (int i = 0; i < paramArrayOfString.length; i++)
/*      */     {
/* 1095 */       if ((paramArrayOfString[i] == null) || (paramArrayOfString[i].equals("")))
/*      */       {
/*      */ 
/* 1098 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1099 */         localSQLException1.fillInStackTrace();
/* 1100 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1106 */         this.matchColumnNames.add(i, paramArrayOfString[i].trim());
/*      */ 
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */ 
/* 1112 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 339);
/* 1113 */         localSQLException2.fillInStackTrace();
/* 1114 */         throw localSQLException2;
/*      */       }
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
/*      */   public void unsetMatchColumn(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1138 */     checkIfMatchColumnIndexesSet();
/*      */     
/* 1140 */     if (paramInt <= 0)
/*      */     {
/*      */ 
/* 1143 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/* 1144 */       localSQLException1.fillInStackTrace();
/* 1145 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/* 1149 */     int i = -1;
/*      */     
/*      */     try
/*      */     {
/* 1153 */       i = ((Integer)this.matchColumnIndexes.get(0)).intValue();
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */ 
/* 1159 */       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 334);
/* 1160 */       localSQLException3.fillInStackTrace();
/* 1161 */       throw localSQLException3;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1166 */     if (i != paramInt)
/*      */     {
/*      */ 
/* 1169 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 340);
/* 1170 */       localSQLException2.fillInStackTrace();
/* 1171 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1176 */     this.matchColumnIndexes.clear();
/* 1177 */     this.matchColumnNames.clear();
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
/*      */   public void unsetMatchColumn(int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 1196 */     checkIfMatchColumnIndexesSet();
/*      */     
/* 1198 */     if (paramArrayOfInt == null)
/*      */     {
/*      */ 
/* 1201 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1202 */       localSQLException1.fillInStackTrace();
/* 1203 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/* 1207 */     int i = -1;
/*      */     
/* 1209 */     for (int j = 0; j < paramArrayOfInt.length; j++)
/*      */     {
/* 1211 */       if (paramArrayOfInt[j] <= 0)
/*      */       {
/*      */ 
/* 1214 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 336);
/* 1215 */         localSQLException2.fillInStackTrace();
/* 1216 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1222 */         i = ((Integer)this.matchColumnIndexes.get(j)).intValue();
/*      */ 
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */ 
/* 1228 */         SQLException localSQLException4 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 334);
/* 1229 */         localSQLException4.fillInStackTrace();
/* 1230 */         throw localSQLException4;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1235 */       if (i != paramArrayOfInt[j])
/*      */       {
/*      */ 
/* 1238 */         SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 340);
/* 1239 */         localSQLException3.fillInStackTrace();
/* 1240 */         throw localSQLException3;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1246 */     this.matchColumnIndexes.clear();
/* 1247 */     this.matchColumnNames.clear();
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
/*      */   public void unsetMatchColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1267 */     checkIfMatchColumnNamesSet();
/*      */     
/* 1269 */     if ((paramString == null) || (paramString.equals("")))
/*      */     {
/*      */ 
/* 1272 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1273 */       ((SQLException)localObject).fillInStackTrace();
/* 1274 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1278 */     Object localObject = null;
/*      */     
/*      */     try
/*      */     {
/* 1282 */       localObject = (String)this.matchColumnNames.get(0);
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*      */ 
/* 1288 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 335);
/* 1289 */       localSQLException2.fillInStackTrace();
/* 1290 */       throw localSQLException2;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1296 */     if (!((String)localObject).equals(paramString.trim()))
/*      */     {
/*      */ 
/* 1299 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 341);
/* 1300 */       localSQLException1.fillInStackTrace();
/* 1301 */       throw localSQLException1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1306 */     this.matchColumnIndexes.clear();
/* 1307 */     this.matchColumnNames.clear();
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
/*      */   public void unsetMatchColumn(String[] paramArrayOfString)
/*      */     throws SQLException
/*      */   {
/* 1326 */     checkIfMatchColumnNamesSet();
/*      */     
/* 1328 */     if (paramArrayOfString == null)
/*      */     {
/*      */ 
/* 1331 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1332 */       ((SQLException)localObject).fillInStackTrace();
/* 1333 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1337 */     Object localObject = null;
/*      */     
/* 1339 */     for (int i = 0; i < paramArrayOfString.length; i++)
/*      */     {
/* 1341 */       if ((paramArrayOfString[i] == null) || (paramArrayOfString[i].equals("")))
/*      */       {
/*      */ 
/* 1344 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 1345 */         localSQLException1.fillInStackTrace();
/* 1346 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1352 */         localObject = (String)this.matchColumnNames.get(i);
/*      */ 
/*      */       }
/*      */       catch (Exception localException)
/*      */       {
/*      */ 
/* 1358 */         SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 335);
/* 1359 */         localSQLException3.fillInStackTrace();
/* 1360 */         throw localSQLException3;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1366 */       if (!((String)localObject).equals(paramArrayOfString[i]))
/*      */       {
/*      */ 
/* 1369 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 341);
/* 1370 */         localSQLException2.fillInStackTrace();
/* 1371 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1377 */     this.matchColumnIndexes.clear();
/* 1378 */     this.matchColumnNames.clear();
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
/*      */   protected void checkIfMatchColumnIndexesSet()
/*      */     throws SQLException
/*      */   {
/* 1393 */     if (this.matchColumnIndexes.size() == 0)
/*      */     {
/* 1395 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 334);
/* 1396 */       localSQLException.fillInStackTrace();
/* 1397 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkIfMatchColumnNamesSet()
/*      */     throws SQLException
/*      */   {
/* 1409 */     if (this.matchColumnNames.size() == 0)
/*      */     {
/* 1411 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 335);
/* 1412 */       localSQLException.fillInStackTrace();
/* 1413 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public abstract int findColumn(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */   public abstract ResultSetMetaData getMetaData()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */   abstract String getTableName()
/*      */     throws SQLException;
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isClosed()
/*      */     throws SQLException
/*      */   {
/* 1437 */     return this.isClosed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getHoldability()
/*      */     throws SQLException
/*      */   {
/* 1445 */     return 1;
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
/*      */   public boolean isWrapperFor(Class<?> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1461 */     if (paramClass.isInterface()) { return paramClass.isInstance(this);
/*      */     }
/* 1463 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1464 */     localSQLException.fillInStackTrace();
/* 1465 */     throw localSQLException;
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
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1483 */     if ((paramClass.isInterface()) && (paramClass.isInstance(this))) { return this;
/*      */     }
/* 1485 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 177);
/* 1486 */     localSQLException.fillInStackTrace();
/* 1487 */     throw localSQLException;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1504 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1509 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */