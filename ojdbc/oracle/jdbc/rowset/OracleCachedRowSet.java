/*      */ package oracle.jdbc.rowset;
/*      */ 
/*      */ import java.io.BufferedReader;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.CharArrayReader;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.PipedInputStream;
/*      */ import java.io.PipedOutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringBufferInputStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.BigInteger;
/*      */ import java.net.MalformedURLException;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.DatabaseMetaData;
/*      */ import java.sql.DriverManager;
/*      */ import java.sql.NClob;
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
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Properties;
/*      */ import java.util.TimeZone;
/*      */ import java.util.TreeMap;
/*      */ import java.util.Vector;
/*      */ import javax.naming.InitialContext;
/*      */ import javax.naming.NamingException;
/*      */ import javax.sql.DataSource;
/*      */ import javax.sql.RowSet;
/*      */ import javax.sql.RowSetEvent;
/*      */ import javax.sql.RowSetInternal;
/*      */ import javax.sql.RowSetMetaData;
/*      */ import javax.sql.RowSetReader;
/*      */ import javax.sql.RowSetWriter;
/*      */ import javax.sql.rowset.CachedRowSet;
/*      */ import javax.sql.rowset.RowSetWarning;
/*      */ import javax.sql.rowset.spi.SyncFactory;
/*      */ import javax.sql.rowset.spi.SyncFactoryException;
/*      */ import javax.sql.rowset.spi.SyncProvider;
/*      */ import javax.sql.rowset.spi.SyncProviderException;
/*      */ import oracle.jdbc.OracleConnection;
/*      */ import oracle.jdbc.OracleSavepoint;
/*      */ import oracle.jdbc.driver.DBConversion;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.driver.OracleDriver;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.ROWID;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleCachedRowSet
/*      */   extends OracleRowSet
/*      */   implements RowSet, RowSetInternal, Serializable, Cloneable, CachedRowSet
/*      */ {
/*      */   static final long serialVersionUID = -2066958142885801470L;
/*      */   private SQLWarning sqlWarning;
/*      */   private RowSetWarning rowsetWarning;
/*      */   protected int presentRow;
/*      */   private int currentPage;
/*      */   private boolean isPopulateDone;
/*      */   private boolean previousColumnWasNull;
/*      */   private OracleRow insertRow;
/*      */   private int insertRowPosition;
/*      */   private boolean insertRowFlag;
/*      */   private int updateRowPosition;
/*      */   private boolean updateRowFlag;
/*      */   protected ResultSetMetaData rowsetMetaData;
/*      */   private transient ResultSet resultSet;
/*      */   private transient Connection connection;
/*  316 */   private transient boolean isConnectionStayingOpenForTxnControl = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  321 */   private transient OracleSqlForRowSet osql = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Vector rows;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Vector param;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String[] metaData;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int colCount;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int rowCount;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private RowSetReader reader;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private RowSetWriter writer;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int[] keyColumns;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int pageSize;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private SyncProvider syncProvider;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final String DEFAULT_SYNCPROVIDER = "com.sun.rowset.providers.RIOptimisticProvider";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String tableName;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  405 */   private boolean executeCalled = false;
/*      */   
/*  407 */   private boolean driverManagerInitialized = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MAX_CHAR_BUFFER_SIZE = 1024;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static final int MAX_BYTE_BUFFER_SIZE = 1024;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleCachedRowSet()
/*      */     throws SQLException
/*      */   {
/*  431 */     this.insertRowFlag = false;
/*  432 */     this.updateRowFlag = false;
/*      */     
/*  434 */     this.presentRow = 0;
/*  435 */     this.previousColumnWasNull = false;
/*      */     
/*  437 */     this.param = new Vector();
/*      */     
/*  439 */     this.rows = new Vector();
/*      */     
/*  441 */     this.sqlWarning = new SQLWarning();
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  447 */       this.syncProvider = SyncFactory.getInstance("com.sun.rowset.providers.RIOptimisticProvider");
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (SyncFactoryException localSyncFactoryException)
/*      */     {
/*      */ 
/*      */ 
/*  455 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 304);
/*  456 */       localSQLException.fillInStackTrace();
/*  457 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  462 */     setReader(new OracleCachedRowSetReader());
/*  463 */     setWriter(new OracleCachedRowSetWriter());
/*      */     
/*  465 */     this.currentPage = 0;
/*  466 */     this.pageSize = 0;
/*  467 */     this.isPopulateDone = false;
/*      */     
/*  469 */     this.keyColumns = null;
/*  470 */     this.tableName = null;
/*      */   }
/*      */   
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
/*  485 */     return getConnectionInternal();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Connection getConnectionInternal()
/*      */     throws SQLException
/*      */   {
/*  494 */     if ((this.connection == null) || (this.connection.isClosed()))
/*      */     {
/*  496 */       String str1 = getUsername();
/*  497 */       String str2 = getPassword();
/*  498 */       Object localObject2; if (getDataSourceName() != null)
/*      */       {
/*      */         try
/*      */         {
/*  502 */           InitialContext localInitialContext = null;
/*      */           
/*      */ 
/*      */ 
/*      */           try
/*      */           {
/*  508 */             Properties localProperties = System.getProperties();
/*  509 */             localInitialContext = new InitialContext(localProperties);
/*      */           }
/*      */           catch (SecurityException localSecurityException) {}
/*      */           
/*      */ 
/*  514 */           if (localInitialContext == null)
/*  515 */             localInitialContext = new InitialContext();
/*  516 */           localObject2 = (DataSource)localInitialContext.lookup(getDataSourceName());
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  521 */           if ((this.username == null) || (str2 == null)) {
/*  522 */             this.connection = ((DataSource)localObject2).getConnection();
/*      */           } else {
/*  524 */             this.connection = ((DataSource)localObject2).getConnection(this.username, str2);
/*      */           }
/*      */         }
/*      */         catch (NamingException localNamingException)
/*      */         {
/*  529 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 300, localNamingException.getMessage());
/*  530 */           ((SQLException)localObject2).fillInStackTrace();
/*  531 */           throw ((Throwable)localObject2);
/*      */         }
/*      */       } else {
/*      */         Object localObject1;
/*  535 */         if (getUrl() != null)
/*      */         {
/*  537 */           if (!this.driverManagerInitialized)
/*      */           {
/*  539 */             DriverManager.registerDriver(new OracleDriver());
/*  540 */             this.driverManagerInitialized = true;
/*      */           }
/*  542 */           localObject1 = getUrl();
/*      */           
/*      */ 
/*      */ 
/*  546 */           if ((((String)localObject1).equals("")) || (str1.equals("")) || (str2.equals("")))
/*      */           {
/*      */ 
/*  549 */             localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 301);
/*  550 */             ((SQLException)localObject2).fillInStackTrace();
/*  551 */             throw ((Throwable)localObject2);
/*      */           }
/*      */           
/*  554 */           this.connection = DriverManager.getConnection((String)localObject1, str1, str2);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  559 */           localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 301);
/*  560 */           ((SQLException)localObject1).fillInStackTrace();
/*  561 */           throw ((Throwable)localObject1);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  566 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Statement getStatement()
/*      */     throws SQLException
/*      */   {
/*  577 */     if (this.resultSet == null)
/*      */     {
/*      */ 
/*      */ 
/*  581 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 305);
/*  582 */       localSQLException.fillInStackTrace();
/*  583 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  587 */     return this.resultSet.getStatement();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowSetReader getReader()
/*      */   {
/*  595 */     return this.reader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowSetWriter getWriter()
/*      */   {
/*  603 */     return this.writer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  636 */     if (this.rowsetType == 1005)
/*      */     {
/*  638 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 306);
/*  639 */       localSQLException.fillInStackTrace();
/*  640 */       throw localSQLException;
/*      */     }
/*      */     
/*  643 */     switch (paramInt)
/*      */     {
/*      */     case 1000: 
/*      */     case 1002: 
/*  647 */       this.presentRow = 0;
/*  648 */       break;
/*      */     case 1001: 
/*  650 */       if (this.rowsetType == 1003)
/*      */       {
/*  652 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 307);
/*  653 */         localSQLException.fillInStackTrace();
/*  654 */         throw localSQLException;
/*      */       }
/*  656 */       this.presentRow = (this.rowCount + 1);
/*  657 */       break;
/*      */     
/*      */     default: 
/*  660 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 308);
/*  661 */       localSQLException.fillInStackTrace();
/*  662 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  667 */     super.setFetchDirection(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCommand(String paramString)
/*      */     throws SQLException
/*      */   {
/*  677 */     super.setCommand(paramString);
/*  678 */     if ((paramString == null) || (paramString.equals(""))) {
/*  679 */       this.osql = null;
/*      */     } else {
/*  681 */       this.osql = new OracleSqlForRowSet(paramString);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setReader(RowSetReader paramRowSetReader)
/*      */   {
/*  690 */     this.reader = paramRowSetReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWriter(RowSetWriter paramRowSetWriter)
/*      */   {
/*  699 */     this.writer = paramRowSetWriter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final int getColumnIndex(String paramString)
/*      */     throws SQLException
/*      */   {
/*  722 */     if ((paramString == null) || (paramString.equals("")))
/*      */     {
/*  724 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6, paramString);
/*  725 */       localSQLException1.fillInStackTrace();
/*  726 */       throw localSQLException1;
/*      */     }
/*      */     
/*  729 */     paramString = paramString.toUpperCase();
/*  730 */     for (int i = 0; 
/*  731 */         i < this.metaData.length; i++)
/*      */     {
/*  733 */       if (paramString.equals(this.metaData[i])) {
/*      */         break;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  739 */     if (i >= this.metaData.length)
/*      */     {
/*  741 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 6, paramString);
/*  742 */       localSQLException2.fillInStackTrace();
/*  743 */       throw localSQLException2;
/*      */     }
/*      */     
/*  746 */     return i + 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void checkColumnIndex(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  761 */     if (this.readOnly)
/*      */     {
/*  763 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/*  764 */       localSQLException.fillInStackTrace();
/*  765 */       throw localSQLException;
/*      */     }
/*  767 */     if ((paramInt < 1) || (paramInt > this.colCount))
/*      */     {
/*  769 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "" + paramInt);
/*  770 */       localSQLException.fillInStackTrace();
/*  771 */       throw localSQLException;
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
/*      */   private final boolean isUpdated(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  790 */     if ((paramInt < 1) || (paramInt > this.colCount))
/*      */     {
/*  792 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "" + paramInt);
/*  793 */       localSQLException.fillInStackTrace();
/*  794 */       throw localSQLException;
/*      */     }
/*      */     
/*  797 */     return getCurrentRow().isColumnChanged(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private final void checkParamIndex(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  811 */     if (paramInt < 1)
/*      */     {
/*  813 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 310, "" + paramInt);
/*  814 */       localSQLException.fillInStackTrace();
/*  815 */       throw localSQLException;
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
/*      */   private final void populateInit(ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/*  832 */     this.resultSet = paramResultSet;
/*  833 */     Statement localStatement = paramResultSet.getStatement();
/*      */     
/*      */ 
/*      */ 
/*  837 */     this.maxFieldSize = localStatement.getMaxFieldSize();
/*      */     
/*      */ 
/*  840 */     this.fetchSize = localStatement.getFetchSize();
/*  841 */     this.queryTimeout = localStatement.getQueryTimeout();
/*      */     
/*  843 */     this.connection = localStatement.getConnection();
/*      */     
/*      */ 
/*      */ 
/*  847 */     this.transactionIsolation = this.connection.getTransactionIsolation();
/*      */     
/*      */ 
/*      */ 
/*  851 */     this.typeMap = this.connection.getTypeMap();
/*  852 */     DatabaseMetaData localDatabaseMetaData = this.connection.getMetaData();
/*      */     
/*      */ 
/*      */ 
/*  856 */     this.url = localDatabaseMetaData.getURL();
/*  857 */     this.username = localDatabaseMetaData.getUserName();
/*      */     
/*      */ 
/*      */ 
/*  861 */     this.presentRow = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized InputStream getStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  878 */     Object localObject1 = getObject(paramInt);
/*      */     
/*      */ 
/*      */ 
/*  882 */     if (localObject1 == null) {
/*  883 */       return null;
/*      */     }
/*  885 */     if ((localObject1 instanceof InputStream)) {
/*  886 */       return (InputStream)localObject1;
/*      */     }
/*  888 */     if ((localObject1 instanceof String))
/*      */     {
/*  890 */       return new ByteArrayInputStream(((String)localObject1).getBytes());
/*      */     }
/*  892 */     if ((localObject1 instanceof byte[]))
/*      */     {
/*  894 */       return new ByteArrayInputStream((byte[])localObject1);
/*      */     }
/*  896 */     if ((localObject1 instanceof OracleSerialClob))
/*  897 */       return ((OracleSerialClob)localObject1).getAsciiStream();
/*  898 */     if ((localObject1 instanceof OracleSerialBlob))
/*  899 */       return ((OracleSerialBlob)localObject1).getBinaryStream();
/*  900 */     if ((localObject1 instanceof Reader))
/*      */     {
/*  902 */       localObject2 = null;
/*  903 */       PipedOutputStream localPipedOutputStream = null;
/*      */       try
/*      */       {
/*  906 */         localObject2 = new BufferedReader((Reader)localObject1);
/*  907 */         int i = 0;
/*  908 */         localObject3 = new PipedInputStream();
/*  909 */         localPipedOutputStream = new PipedOutputStream((PipedInputStream)localObject3);
/*  910 */         while ((i = ((Reader)localObject2).read()) != -1)
/*  911 */           localPipedOutputStream.write(i);
/*  912 */         SQLException localSQLException1; return (InputStream)localObject3;
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException1)
/*      */       {
/*  917 */         Object localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 311, localIOException1.getMessage());
/*  918 */         ((SQLException)localObject3).fillInStackTrace();
/*  919 */         throw ((Throwable)localObject3);
/*      */       }
/*      */       finally {
/*      */         SQLException localSQLException2;
/*      */         try {
/*  924 */           if (localObject2 != null) {
/*  925 */             ((Reader)localObject2).close();
/*      */           }
/*      */         }
/*      */         catch (IOException localIOException4) {
/*  929 */           localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 311, localIOException4.getMessage());
/*  930 */           localSQLException2.fillInStackTrace();
/*  931 */           throw localSQLException2;
/*      */         }
/*      */         try
/*      */         {
/*  935 */           if (localPipedOutputStream != null) {
/*  936 */             localPipedOutputStream.close();
/*      */           }
/*      */         }
/*      */         catch (IOException localIOException5) {
/*  940 */           localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 311, localIOException5.getMessage());
/*  941 */           localSQLException2.fillInStackTrace();
/*  942 */           throw localSQLException2;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  949 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 312);
/*  950 */     ((SQLException)localObject2).fillInStackTrace();
/*  951 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private final Calendar getSessionCalendar(Connection paramConnection)
/*      */   {
/*  958 */     String str = ((OracleConnection)paramConnection).getSessionTimeZone();
/*      */     
/*      */     Calendar localCalendar;
/*  961 */     if (str == null) {
/*  962 */       localCalendar = Calendar.getInstance();
/*      */     }
/*      */     else {
/*  965 */       TimeZone localTimeZone = TimeZone.getDefault();
/*  966 */       localTimeZone.setID(str);
/*  967 */       localCalendar = Calendar.getInstance(localTimeZone);
/*      */     }
/*      */     
/*  970 */     return localCalendar;
/*      */   }
/*      */   
/*      */ 
/*      */   private boolean isStreamType(int paramInt)
/*      */   {
/*  976 */     return (paramInt == 2004) || (paramInt == 2005) || (paramInt == -4) || (paramInt == -1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1006 */     if (this.insertRowFlag)
/*      */     {
/* 1008 */       this.insertRowFlag = false;
/* 1009 */       this.insertRow.setRowUpdated(false);
/* 1010 */       this.sqlWarning.setNextWarning(new SQLWarning("Cancelling insertion, due to cursor movement."));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/* 1018 */     else if (this.updateRowFlag)
/*      */     {
/*      */       try
/*      */       {
/* 1022 */         this.updateRowFlag = false;
/* 1023 */         int i = this.presentRow;
/* 1024 */         this.presentRow = this.updateRowPosition;
/* 1025 */         getCurrentRow().setRowUpdated(false);
/* 1026 */         this.presentRow = i;
/* 1027 */         this.sqlWarning.setNextWarning(new SQLWarning("Cancelling all updates, due to cursor movement."));
/*      */       }
/*      */       catch (SQLException localSQLException) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1037 */     super.notifyCursorMoved();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkAndFilterObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleRow getCurrentRow()
/*      */     throws SQLException
/*      */   {
/* 1067 */     int i = this.presentRow - 1;
/*      */     
/*      */ 
/*      */ 
/* 1071 */     if ((this.presentRow < 1) || (this.presentRow > this.rowCount))
/*      */     {
/* 1073 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 313);
/* 1074 */       localSQLException.fillInStackTrace();
/* 1075 */       throw localSQLException;
/*      */     }
/*      */     
/* 1078 */     return (OracleRow)this.rows.elementAt(this.presentRow - 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isExecuteCalled()
/*      */   {
/* 1088 */     return this.executeCalled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int getCurrentPage()
/*      */   {
/* 1097 */     return this.currentPage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean isConnectionStayingOpen()
/*      */   {
/* 1106 */     return this.isConnectionStayingOpenForTxnControl;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setOriginal()
/*      */     throws SQLException
/*      */   {
/* 1118 */     int i = 1;
/*      */     
/*      */     do
/*      */     {
/* 1122 */       boolean bool = setOriginalRowInternal(i);
/*      */       
/* 1124 */       if (!bool)
/*      */       {
/* 1126 */         i++;
/*      */       }
/*      */       
/* 1129 */     } while (i <= this.rowCount);
/*      */     
/* 1131 */     notifyRowSetChanged();
/*      */     
/*      */ 
/*      */ 
/* 1135 */     this.presentRow = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean setOriginalRowInternal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1148 */     if ((paramInt < 1) || (paramInt > this.rowCount))
/*      */     {
/* 1150 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 313);
/* 1151 */       localSQLException.fillInStackTrace();
/* 1152 */       throw localSQLException;
/*      */     }
/*      */     
/* 1155 */     boolean bool = false;
/*      */     
/* 1157 */     OracleRow localOracleRow = (OracleRow)this.rows.elementAt(paramInt - 1);
/*      */     
/* 1159 */     if (localOracleRow.isRowDeleted())
/*      */     {
/* 1161 */       this.rows.remove(paramInt - 1);
/* 1162 */       this.rowCount -= 1;
/* 1163 */       bool = true;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1168 */       if (localOracleRow.isRowInserted())
/*      */       {
/* 1170 */         localOracleRow.setInsertedFlag(false);
/*      */       }
/*      */       
/* 1173 */       if (localOracleRow.isRowUpdated())
/*      */       {
/* 1175 */         localOracleRow.makeUpdatesOriginal();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1182 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1224 */     if (this.rowCount < 0)
/*      */     {
/* 1226 */       return false;
/*      */     }
/*      */     
/* 1229 */     if ((this.fetchDirection == 1000) || (this.fetchDirection == 1002))
/*      */     {
/* 1231 */       if (this.presentRow + 1 <= this.rowCount)
/*      */       {
/* 1233 */         this.presentRow += 1;
/* 1234 */         if ((!this.showDeleted) && (getCurrentRow().isRowDeleted())) {
/* 1235 */           return next();
/*      */         }
/* 1237 */         notifyCursorMoved();
/* 1238 */         return true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1245 */       this.presentRow = (this.rowCount + 1);
/* 1246 */       return false;
/*      */     }
/*      */     
/* 1249 */     if (this.fetchDirection == 1001)
/*      */     {
/* 1251 */       if (this.presentRow - 1 > 0)
/*      */       {
/* 1253 */         this.presentRow -= 1;
/* 1254 */         if ((!this.showDeleted) && (getCurrentRow().isRowDeleted())) {
/* 1255 */           return next();
/*      */         }
/* 1257 */         notifyCursorMoved();
/* 1258 */         return true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1265 */       this.presentRow = 0;
/* 1266 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1272 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean previous()
/*      */     throws SQLException
/*      */   {
/* 1287 */     if (this.rowCount < 0)
/*      */     {
/* 1289 */       return false;
/*      */     }
/*      */     
/* 1292 */     if (this.fetchDirection == 1001)
/*      */     {
/* 1294 */       if (this.presentRow + 1 <= this.rowCount)
/*      */       {
/* 1296 */         this.presentRow += 1;
/* 1297 */         if ((!this.showDeleted) && (getCurrentRow().isRowDeleted())) {
/* 1298 */           return previous();
/*      */         }
/* 1300 */         notifyCursorMoved();
/* 1301 */         return true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1308 */       this.presentRow = (this.rowCount + 1);
/* 1309 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 1313 */     if ((this.fetchDirection == 1000) || (this.fetchDirection == 1002))
/*      */     {
/* 1315 */       if (this.presentRow - 1 > 0)
/*      */       {
/* 1317 */         this.presentRow -= 1;
/* 1318 */         if ((!this.showDeleted) && (getCurrentRow().isRowDeleted())) {
/* 1319 */           return previous();
/*      */         }
/* 1321 */         notifyCursorMoved();
/* 1322 */         return true;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1329 */       this.presentRow = 0;
/* 1330 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1336 */     return false;
/*      */   }
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
/* 1348 */     return (this.rowCount > 0) && (this.presentRow == 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isAfterLast()
/*      */     throws SQLException
/*      */   {
/* 1359 */     return (this.rowCount > 0) && (this.presentRow == this.rowCount + 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isFirst()
/*      */     throws SQLException
/*      */   {
/* 1368 */     return this.presentRow == 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isLast()
/*      */     throws SQLException
/*      */   {
/* 1379 */     return this.presentRow == this.rowCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void beforeFirst()
/*      */     throws SQLException
/*      */   {
/* 1387 */     this.presentRow = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void afterLast()
/*      */     throws SQLException
/*      */   {
/* 1396 */     this.presentRow = (this.rowCount + 1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean first()
/*      */     throws SQLException
/*      */   {
/* 1407 */     return absolute(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean last()
/*      */     throws SQLException
/*      */   {
/* 1416 */     return absolute(-1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean absolute(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1433 */     if (this.rowsetType == 1003)
/*      */     {
/* 1435 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 314);
/* 1436 */       localSQLException.fillInStackTrace();
/* 1437 */       throw localSQLException;
/*      */     }
/* 1439 */     if ((paramInt == 0) || (Math.abs(paramInt) > this.rowCount))
/* 1440 */       return false;
/* 1441 */     this.presentRow = (paramInt < 0 ? this.rowCount + paramInt + 1 : paramInt);
/* 1442 */     notifyCursorMoved();
/*      */     
/* 1444 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean relative(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1457 */     return absolute(this.presentRow + paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void populate(ResultSet paramResultSet)
/*      */     throws SQLException
/*      */   {
/* 1494 */     if (this.rows == null)
/*      */     {
/*      */ 
/* 1497 */       this.rows = new Vector(50, 10);
/*      */     }
/*      */     else
/* 1500 */       this.rows.clear();
/* 1501 */     this.rowsetMetaData = new OracleRowSetMetaData(paramResultSet.getMetaData());
/* 1502 */     this.metaData = new String[this.colCount = this.rowsetMetaData.getColumnCount()];
/* 1503 */     for (int i = 0; i < this.colCount; i++) {
/* 1504 */       this.metaData[i] = this.rowsetMetaData.getColumnName(i + 1);
/*      */     }
/*      */     
/*      */ 
/* 1508 */     if (!(paramResultSet instanceof OracleCachedRowSet)) {
/* 1509 */       populateInit(paramResultSet);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1519 */     i = (this.fetchDirection == 1000) || (this.fetchDirection == 1002) ? 1 : 0;
/*      */     
/*      */ 
/* 1522 */     this.rowCount = 0;
/* 1523 */     OracleRow localOracleRow = null;
/*      */     
/*      */ 
/*      */     int j;
/*      */     
/*      */ 
/* 1529 */     if ((this.maxRows == 0) && (this.pageSize == 0))
/*      */     {
/* 1531 */       j = Integer.MAX_VALUE;
/*      */     }
/* 1533 */     else if ((this.maxRows == 0) || (this.pageSize == 0))
/*      */     {
/* 1535 */       j = Math.max(this.maxRows, this.pageSize);
/*      */     }
/*      */     else
/*      */     {
/* 1539 */       j = Math.min(this.maxRows, this.pageSize);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1549 */     if ((paramResultSet.getType() != 1003) && (this.rows.size() == 0))
/*      */     {
/* 1551 */       if (i == 0)
/*      */       {
/* 1553 */         paramResultSet.afterLast();
/*      */       }
/*      */     }
/*      */     
/* 1557 */     int k = 0;
/*      */     
/* 1559 */     while (this.rowCount < j)
/*      */     {
/* 1561 */       if (i != 0)
/*      */       {
/* 1563 */         if (!paramResultSet.next())
/*      */         {
/* 1565 */           k = 1;
/* 1566 */           break;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1571 */       else if (!paramResultSet.previous())
/*      */       {
/* 1573 */         k = 1;
/* 1574 */         break;
/*      */       }
/*      */       
/*      */ 
/* 1578 */       localOracleRow = new OracleRow(this.colCount);
/* 1579 */       for (int m = 1; m <= this.colCount; m++)
/*      */       {
/* 1581 */         Object localObject = null;
/*      */         try
/*      */         {
/* 1584 */           localObject = paramResultSet.getObject(m, this.typeMap);
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/* 1588 */           localObject = paramResultSet.getObject(m);
/*      */         }
/*      */         catch (AbstractMethodError localAbstractMethodError)
/*      */         {
/* 1592 */           localObject = paramResultSet.getObject(m);
/*      */         }
/*      */         
/*      */ 
/* 1596 */         if (((localObject instanceof Clob)) || ((localObject instanceof CLOB))) {
/* 1597 */           localOracleRow.setColumnValue(m, new OracleSerialClob((Clob)localObject));
/*      */         }
/* 1599 */         else if (((localObject instanceof Blob)) || ((localObject instanceof BLOB))) {
/* 1600 */           localOracleRow.setColumnValue(m, new OracleSerialBlob((Blob)localObject));
/*      */         }
/*      */         else {
/* 1603 */           localOracleRow.setColumnValue(m, localObject);
/*      */         }
/* 1605 */         localOracleRow.markOriginalNull(m, paramResultSet.wasNull());
/*      */       }
/*      */       
/* 1608 */       if (i != 0)
/*      */       {
/* 1610 */         this.rows.add(localOracleRow);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1615 */         this.rows.add(1, localOracleRow);
/*      */       }
/*      */       
/* 1618 */       this.rowCount += 1;
/*      */     }
/*      */     
/* 1621 */     if ((k != 0) || ((i != 0) && (paramResultSet.isAfterLast())) || ((i == 0) && (paramResultSet.isBeforeFirst())))
/*      */     {
/*      */ 
/*      */ 
/* 1625 */       this.isPopulateDone = true;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1632 */     this.connection = null;
/*      */     
/* 1634 */     notifyRowSetChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getCursorName()
/*      */     throws SQLException
/*      */   {
/* 1645 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 1646 */     localSQLException.fillInStackTrace();
/* 1647 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void clearParameters()
/*      */     throws SQLException
/*      */   {
/* 1657 */     this.param = null;
/* 1658 */     this.param = new Vector();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/* 1667 */     return this.previousColumnWasNull;
/*      */   }
/*      */   
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
/* 1680 */     release();
/*      */     
/* 1682 */     this.isClosed = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLWarning getWarnings()
/*      */     throws SQLException
/*      */   {
/* 1692 */     return this.sqlWarning;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearWarnings()
/*      */     throws SQLException
/*      */   {
/* 1701 */     this.sqlWarning = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/* 1711 */     return this.rowsetMetaData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int findColumn(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1720 */     return getColumnIndex(paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object[] getParams()
/*      */     throws SQLException
/*      */   {
/* 1729 */     return this.param.toArray();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMetaData(RowSetMetaData paramRowSetMetaData)
/*      */     throws SQLException
/*      */   {
/* 1738 */     this.rowsetMetaData = paramRowSetMetaData;
/*      */     
/*      */ 
/*      */ 
/* 1742 */     if (paramRowSetMetaData != null)
/*      */     {
/* 1744 */       this.colCount = paramRowSetMetaData.getColumnCount();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void execute()
/*      */     throws SQLException
/*      */   {
/* 1756 */     this.isConnectionStayingOpenForTxnControl = false;
/* 1757 */     getReader().readData(this);
/*      */     
/* 1759 */     this.executeCalled = true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void acceptChanges()
/*      */     throws SyncProviderException
/*      */   {
/*      */     try
/*      */     {
/* 1776 */       getWriter().writeData(this);
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1780 */       throw new SyncProviderException(localSQLException.getMessage());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void acceptChanges(Connection paramConnection)
/*      */     throws SyncProviderException
/*      */   {
/* 1791 */     this.connection = paramConnection;
/*      */     
/*      */ 
/*      */ 
/* 1795 */     this.isConnectionStayingOpenForTxnControl = true;
/* 1796 */     acceptChanges();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/*      */     try
/*      */     {
/* 1810 */       return createCopy();
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1814 */       throw new CloneNotSupportedException("SQL Error occured while cloning: " + localSQLException.getMessage());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CachedRowSet createCopy()
/*      */     throws SQLException
/*      */   {
/* 1824 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)createShared();
/* 1825 */     int i = this.rows.size();
/* 1826 */     localOracleCachedRowSet.rows = new Vector(i);
/* 1827 */     for (int j = 0; j < i; j++) {
/* 1828 */       localOracleCachedRowSet.rows.add(((OracleRow)this.rows.elementAt(j)).createCopy());
/*      */     }
/* 1830 */     return localOracleCachedRowSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowSet createShared()
/*      */     throws SQLException
/*      */   {
/* 1839 */     OracleCachedRowSet localOracleCachedRowSet = new OracleCachedRowSet();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1844 */     localOracleCachedRowSet.rows = this.rows;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1850 */     localOracleCachedRowSet.setDataSource(getDataSource());
/* 1851 */     localOracleCachedRowSet.setDataSourceName(getDataSourceName());
/* 1852 */     localOracleCachedRowSet.setUsername(getUsername());
/* 1853 */     localOracleCachedRowSet.setPassword(getPassword());
/* 1854 */     localOracleCachedRowSet.setUrl(getUrl());
/* 1855 */     localOracleCachedRowSet.setTypeMap(getTypeMap());
/* 1856 */     localOracleCachedRowSet.setMaxFieldSize(getMaxFieldSize());
/* 1857 */     localOracleCachedRowSet.setMaxRows(getMaxRows());
/* 1858 */     localOracleCachedRowSet.setQueryTimeout(getQueryTimeout());
/* 1859 */     localOracleCachedRowSet.setFetchSize(getFetchSize());
/* 1860 */     localOracleCachedRowSet.setEscapeProcessing(getEscapeProcessing());
/* 1861 */     localOracleCachedRowSet.setConcurrency(getConcurrency());
/* 1862 */     localOracleCachedRowSet.setReadOnly(this.readOnly);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1867 */     this.rowsetType = getType();
/* 1868 */     this.fetchDirection = getFetchDirection();
/* 1869 */     localOracleCachedRowSet.setCommand(getCommand());
/* 1870 */     localOracleCachedRowSet.setTransactionIsolation(getTransactionIsolation());
/*      */     
/*      */ 
/* 1873 */     localOracleCachedRowSet.presentRow = this.presentRow;
/* 1874 */     localOracleCachedRowSet.colCount = this.colCount;
/* 1875 */     localOracleCachedRowSet.rowCount = this.rowCount;
/* 1876 */     localOracleCachedRowSet.showDeleted = this.showDeleted;
/*      */     
/* 1878 */     localOracleCachedRowSet.syncProvider = this.syncProvider;
/* 1879 */     localOracleCachedRowSet.currentPage = this.currentPage;
/* 1880 */     localOracleCachedRowSet.pageSize = this.pageSize;
/* 1881 */     localOracleCachedRowSet.tableName = (this.tableName == null ? null : this.tableName);
/* 1882 */     localOracleCachedRowSet.keyColumns = (this.keyColumns == null ? null : (int[])this.keyColumns.clone());
/*      */     
/* 1884 */     int i = this.listener.size();
/* 1885 */     for (int j = 0; j < i; j++) {
/* 1886 */       localOracleCachedRowSet.listener.add(this.listener.elementAt(j));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1895 */     localOracleCachedRowSet.rowsetMetaData = new OracleRowSetMetaData(this.rowsetMetaData);
/*      */     
/* 1897 */     i = this.param.size();
/* 1898 */     for (j = 0; j < i; j++) {
/* 1899 */       localOracleCachedRowSet.param.add(this.param.elementAt(j));
/*      */     }
/* 1901 */     localOracleCachedRowSet.metaData = new String[this.metaData.length];
/* 1902 */     System.arraycopy(this.metaData, 0, localOracleCachedRowSet.metaData, 0, this.metaData.length);
/*      */     
/*      */ 
/*      */ 
/* 1906 */     return localOracleCachedRowSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void release()
/*      */     throws SQLException
/*      */   {
/* 1915 */     this.rows = null;
/* 1916 */     this.rows = new Vector();
/* 1917 */     if ((this.connection != null) && (!this.connection.isClosed()))
/* 1918 */       this.connection.close();
/* 1919 */     this.rowCount = 0;
/* 1920 */     this.presentRow = 0;
/* 1921 */     notifyRowSetChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void restoreOriginal()
/*      */     throws SQLException
/*      */   {
/* 1931 */     int i = 0;
/* 1932 */     for (int j = 0; j < this.rowCount; j++)
/*      */     {
/* 1934 */       OracleRow localOracleRow = (OracleRow)this.rows.elementAt(j);
/* 1935 */       if (localOracleRow.isRowInserted())
/*      */       {
/* 1937 */         this.rows.remove(j);
/* 1938 */         this.rowCount -= 1;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 1943 */         j--;
/* 1944 */         i = 1;
/*      */       }
/* 1946 */       else if (localOracleRow.isRowUpdated())
/*      */       {
/* 1948 */         localOracleRow.setRowUpdated(false);
/* 1949 */         i = 1;
/*      */       }
/* 1951 */       else if (localOracleRow.isRowDeleted())
/*      */       {
/* 1953 */         localOracleRow.setRowDeleted(false);
/* 1954 */         i = 1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1960 */     if (i == 0)
/*      */     {
/* 1962 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 315);
/* 1963 */       localSQLException.fillInStackTrace();
/* 1964 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1968 */     notifyRowSetChanged();
/*      */     
/*      */ 
/* 1971 */     this.presentRow = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection toCollection()
/*      */     throws SQLException
/*      */   {
/* 1982 */     Map localMap = Collections.synchronizedMap(new TreeMap());
/*      */     
/*      */     try
/*      */     {
/* 1986 */       for (int i = 0; i < this.rowCount; i++)
/*      */       {
/* 1988 */         localMap.put(Integer.valueOf(i), ((OracleRow)this.rows.elementAt(i)).toCollection());
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1995 */       localMap = null;
/*      */       
/* 1997 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 316);
/* 1998 */       localSQLException.fillInStackTrace();
/* 1999 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2004 */     return localMap.values();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection toCollection(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2013 */     if ((paramInt < 1) || (paramInt > this.colCount))
/*      */     {
/* 2015 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "" + paramInt);
/* 2016 */       ((SQLException)localObject1).fillInStackTrace();
/* 2017 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/* 2020 */     Object localObject1 = new Vector(this.rowCount);
/* 2021 */     for (int i = 0; i < this.rowCount; i++)
/*      */     {
/* 2023 */       OracleRow localOracleRow = (OracleRow)this.rows.elementAt(i);
/* 2024 */       Object localObject2 = localOracleRow.isColumnChanged(paramInt) ? localOracleRow.getModifiedColumn(paramInt) : localOracleRow.getColumn(paramInt);
/*      */       
/*      */ 
/* 2027 */       ((Vector)localObject1).add(localObject2);
/*      */     }
/*      */     
/* 2030 */     return (Collection)localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection toCollection(String paramString)
/*      */     throws SQLException
/*      */   {
/* 2039 */     return toCollection(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRow()
/*      */     throws SQLException
/*      */   {
/* 2057 */     if (this.presentRow > this.rowCount) {
/* 2058 */       return this.rowCount;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2066 */     return this.presentRow;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancelRowInsert()
/*      */     throws SQLException
/*      */   {
/* 2075 */     if (getCurrentRow().isRowInserted())
/*      */     {
/* 2077 */       this.rows.remove(--this.presentRow);
/* 2078 */       this.rowCount -= 1;
/* 2079 */       notifyRowChanged();
/*      */     }
/*      */     else
/*      */     {
/* 2083 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 317);
/* 2084 */       localSQLException.fillInStackTrace();
/* 2085 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancelRowDelete()
/*      */     throws SQLException
/*      */   {
/* 2096 */     if (getCurrentRow().isRowDeleted())
/*      */     {
/* 2098 */       getCurrentRow().setRowDeleted(false);
/* 2099 */       notifyRowChanged();
/*      */     }
/*      */     else
/*      */     {
/* 2103 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 318);
/* 2104 */       localSQLException.fillInStackTrace();
/* 2105 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void cancelRowUpdates()
/*      */     throws SQLException
/*      */   {
/* 2116 */     if (getCurrentRow().isRowUpdated())
/*      */     {
/* 2118 */       this.updateRowFlag = false;
/* 2119 */       getCurrentRow().setRowUpdated(false);
/* 2120 */       notifyRowChanged();
/*      */     }
/*      */     else
/*      */     {
/* 2124 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 319);
/* 2125 */       localSQLException.fillInStackTrace();
/* 2126 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void insertRow()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 2139 */     if (isReadOnly())
/*      */     {
/* 2141 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2142 */       localSQLException.fillInStackTrace();
/* 2143 */       throw localSQLException;
/*      */     }
/*      */     
/* 2146 */     if (!this.insertRowFlag)
/*      */     {
/* 2148 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 317);
/* 2149 */       localSQLException.fillInStackTrace();
/* 2150 */       throw localSQLException;
/*      */     }
/*      */     
/* 2153 */     if (!this.insertRow.isRowFullyPopulated())
/*      */     {
/* 2155 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 320);
/* 2156 */       localSQLException.fillInStackTrace();
/* 2157 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2163 */     this.insertRow.insertRow();
/* 2164 */     this.rows.insertElementAt(this.insertRow, this.insertRowPosition - 1);
/* 2165 */     this.insertRowFlag = false;
/* 2166 */     this.rowCount += 1;
/* 2167 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRow()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/* 2179 */     if (isReadOnly())
/*      */     {
/* 2181 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2182 */       localSQLException.fillInStackTrace();
/* 2183 */       throw localSQLException;
/*      */     }
/*      */     
/* 2186 */     if (this.updateRowFlag)
/*      */     {
/* 2188 */       this.updateRowFlag = false;
/* 2189 */       getCurrentRow().setRowUpdated(true);
/* 2190 */       notifyRowChanged();
/*      */     }
/*      */     else
/*      */     {
/* 2194 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 319);
/* 2195 */       localSQLException.fillInStackTrace();
/* 2196 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void deleteRow()
/*      */     throws SQLException
/*      */   {
/* 2207 */     if (isReadOnly())
/*      */     {
/* 2209 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2210 */       localSQLException.fillInStackTrace();
/* 2211 */       throw localSQLException;
/*      */     }
/*      */     
/* 2214 */     getCurrentRow().setRowDeleted(true);
/* 2215 */     notifyRowChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void refreshRow()
/*      */     throws SQLException
/*      */   {
/* 2225 */     OracleRow localOracleRow = getCurrentRow();
/* 2226 */     if (localOracleRow.isRowUpdated()) {
/* 2227 */       localOracleRow.cancelRowUpdates();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToInsertRow()
/*      */     throws SQLException
/*      */   {
/* 2239 */     if (isReadOnly())
/*      */     {
/* 2241 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2242 */       localSQLException.fillInStackTrace();
/* 2243 */       throw localSQLException;
/*      */     }
/*      */     
/* 2246 */     this.insertRow = new OracleRow(this.colCount, true);
/* 2247 */     this.insertRowFlag = true;
/*      */     
/* 2249 */     if (isAfterLast()) {
/* 2250 */       this.insertRowPosition = this.presentRow;
/*      */     } else {
/* 2252 */       this.insertRowPosition = (this.presentRow + 1);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void moveToCurrentRow()
/*      */     throws SQLException
/*      */   {
/* 2264 */     if (isReadOnly())
/*      */     {
/* 2266 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 309);
/* 2267 */       localSQLException.fillInStackTrace();
/* 2268 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2275 */     this.insertRowFlag = false;
/* 2276 */     this.updateRowFlag = false;
/* 2277 */     absolute(this.presentRow);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowUpdated()
/*      */     throws SQLException
/*      */   {
/* 2287 */     return getCurrentRow().isRowUpdated();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowInserted()
/*      */     throws SQLException
/*      */   {
/* 2296 */     return getCurrentRow().isRowInserted();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean rowDeleted()
/*      */     throws SQLException
/*      */   {
/* 2305 */     return getCurrentRow().isRowDeleted();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getOriginalRow()
/*      */     throws SQLException
/*      */   {
/* 2317 */     OracleCachedRowSet localOracleCachedRowSet = new OracleCachedRowSet();
/* 2318 */     localOracleCachedRowSet.rowsetMetaData = this.rowsetMetaData;
/* 2319 */     localOracleCachedRowSet.rowCount = 1;
/* 2320 */     localOracleCachedRowSet.colCount = this.colCount;
/* 2321 */     localOracleCachedRowSet.presentRow = 0;
/* 2322 */     localOracleCachedRowSet.setReader(null);
/* 2323 */     localOracleCachedRowSet.setWriter(null);
/* 2324 */     OracleRow localOracleRow = new OracleRow(this.rowsetMetaData.getColumnCount(), getCurrentRow().getOriginalRow());
/*      */     
/*      */ 
/* 2327 */     localOracleCachedRowSet.rows.add(localOracleRow);
/*      */     
/* 2329 */     return localOracleCachedRowSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet getOriginal()
/*      */     throws SQLException
/*      */   {
/* 2341 */     OracleCachedRowSet localOracleCachedRowSet = new OracleCachedRowSet();
/* 2342 */     localOracleCachedRowSet.rowsetMetaData = this.rowsetMetaData;
/* 2343 */     localOracleCachedRowSet.rowCount = this.rowCount;
/* 2344 */     localOracleCachedRowSet.colCount = this.colCount;
/*      */     
/*      */ 
/* 2347 */     localOracleCachedRowSet.presentRow = 0;
/*      */     
/*      */ 
/* 2350 */     localOracleCachedRowSet.setType(1004);
/* 2351 */     localOracleCachedRowSet.setConcurrency(1008);
/*      */     
/* 2353 */     localOracleCachedRowSet.setReader(null);
/* 2354 */     localOracleCachedRowSet.setWriter(null);
/* 2355 */     int i = this.rowsetMetaData.getColumnCount();
/* 2356 */     OracleRow localOracleRow = null;
/*      */     
/* 2358 */     Iterator localIterator = this.rows.iterator();
/* 2359 */     for (; localIterator.hasNext(); 
/* 2360 */         localOracleCachedRowSet.rows.add(localOracleRow)) {
/* 2361 */       localOracleRow = new OracleRow(i, ((OracleRow)localIterator.next()).getOriginalRow());
/*      */     }
/* 2363 */     return localOracleCachedRowSet;
/*      */   }
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
/* 2377 */     checkParamIndex(paramInt1);
/*      */     
/* 2379 */     this.param.add(paramInt1 - 1, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(int paramInt1, int paramInt2, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2389 */     checkParamIndex(paramInt1);
/* 2390 */     Object[] arrayOfObject = { Integer.valueOf(paramInt2), paramString };
/* 2391 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2401 */     checkParamIndex(paramInt);
/* 2402 */     this.param.add(paramInt - 1, Boolean.valueOf(paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 2412 */     checkParamIndex(paramInt);
/* 2413 */     this.param.add(paramInt - 1, new Byte(paramByte));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 2423 */     checkParamIndex(paramInt);
/* 2424 */     this.param.add(paramInt - 1, Short.valueOf(paramShort));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2434 */     checkParamIndex(paramInt1);
/* 2435 */     this.param.add(paramInt1 - 1, Integer.valueOf(paramInt2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2445 */     checkParamIndex(paramInt);
/* 2446 */     this.param.add(paramInt - 1, Long.valueOf(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 2456 */     checkParamIndex(paramInt);
/* 2457 */     this.param.add(paramInt - 1, Float.valueOf(paramFloat));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 2467 */     checkParamIndex(paramInt);
/* 2468 */     this.param.add(paramInt - 1, Double.valueOf(paramDouble));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 2478 */     checkParamIndex(paramInt);
/* 2479 */     this.param.add(paramInt - 1, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 2489 */     checkParamIndex(paramInt);
/* 2490 */     this.param.add(paramInt - 1, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2500 */     checkParamIndex(paramInt);
/* 2501 */     this.param.add(paramInt - 1, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 2511 */     checkParamIndex(paramInt);
/* 2512 */     this.param.add(paramInt - 1, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 2522 */     checkParamIndex(paramInt);
/* 2523 */     this.param.add(paramInt - 1, paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 2533 */     checkParamIndex(paramInt);
/* 2534 */     this.param.add(paramInt - 1, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(int paramInt, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 2544 */     checkParamIndex(paramInt);
/* 2545 */     this.param.add(paramInt - 1, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 2555 */     checkParamIndex(paramInt);
/* 2556 */     this.param.add(paramInt - 1, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 2566 */     checkParamIndex(paramInt);
/* 2567 */     this.param.add(paramInt - 1, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setArray(int paramInt, Array paramArray)
/*      */     throws SQLException
/*      */   {
/* 2577 */     checkParamIndex(paramInt);
/* 2578 */     this.param.add(paramInt - 1, paramArray);
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
/* 2589 */     checkParamIndex(paramInt1);
/* 2590 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(paramInt2), Integer.valueOf(2) };
/*      */     
/* 2592 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(int paramInt, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2602 */     checkParamIndex(paramInt);
/* 2603 */     Object[] arrayOfObject = { paramTime, paramCalendar };
/* 2604 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2614 */     checkParamIndex(paramInt);
/* 2615 */     Object[] arrayOfObject = { paramTimestamp, paramCalendar };
/* 2616 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 2625 */     checkParamIndex(paramInt);
/* 2626 */     this.param.add(paramInt - 1, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2636 */     checkParamIndex(paramInt1);
/* 2637 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(paramInt2), Integer.valueOf(3) };
/*      */     
/* 2639 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2649 */     checkParamIndex(paramInt1);
/* 2650 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(paramInt2), Integer.valueOf(1) };
/*      */     
/* 2652 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2662 */     checkParamIndex(paramInt1);
/* 2663 */     Object[] arrayOfObject = { paramReader, Integer.valueOf(paramInt2), Integer.valueOf(4) };
/*      */     
/* 2665 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2675 */     checkParamIndex(paramInt1);
/* 2676 */     Object[] arrayOfObject = { paramObject, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) };
/* 2677 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2687 */     checkParamIndex(paramInt1);
/* 2688 */     Object[] arrayOfObject = { paramObject, Integer.valueOf(paramInt2) };
/* 2689 */     this.param.add(paramInt1 - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(int paramInt, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 2699 */     checkParamIndex(paramInt);
/* 2700 */     Object[] arrayOfObject = { paramDate, paramCalendar };
/* 2701 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(int paramInt, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 2711 */     checkParamIndex(paramInt);
/* 2712 */     this.param.add(paramInt - 1, paramURL.toString());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Object getObject(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2732 */     int i = this.presentRow * this.colCount + paramInt - 1;
/* 2733 */     Object localObject = null;
/*      */     
/* 2735 */     if (!isUpdated(paramInt)) {
/* 2736 */       localObject = getCurrentRow().getColumn(paramInt);
/*      */     } else
/* 2738 */       localObject = getCurrentRow().getModifiedColumn(paramInt);
/* 2739 */     this.previousColumnWasNull = (localObject == null);
/*      */     
/* 2741 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized Number getNumber(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2751 */     Object localObject = getObject(paramInt);
/*      */     
/* 2753 */     if ((localObject == null) || ((localObject instanceof BigDecimal)) || ((localObject instanceof Number)))
/*      */     {
/*      */ 
/* 2756 */       return (Number)localObject;
/*      */     }
/* 2758 */     if ((localObject instanceof Boolean)) {
/* 2759 */       return Integer.valueOf(((Boolean)localObject).booleanValue() ? 1 : 0);
/*      */     }
/* 2761 */     if ((localObject instanceof String))
/*      */     {
/*      */       try
/*      */       {
/* 2765 */         return new BigDecimal((String)localObject);
/*      */ 
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException)
/*      */       {
/* 2770 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 2771 */         localSQLException2.fillInStackTrace();
/* 2772 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2779 */     SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 2780 */     localSQLException1.fillInStackTrace();
/* 2781 */     throw localSQLException1;
/*      */   }
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
/* 2793 */     return getObject(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2802 */     Object localObject = getObject(paramInt);
/*      */     
/* 2804 */     if (localObject == null) {
/* 2805 */       return false;
/*      */     }
/* 2807 */     if ((localObject instanceof Boolean)) {
/* 2808 */       return ((Boolean)localObject).booleanValue();
/*      */     }
/* 2810 */     if ((localObject instanceof Number)) {
/* 2811 */       return ((Number)localObject).doubleValue() != 0.0D;
/*      */     }
/*      */     
/*      */ 
/* 2815 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 2816 */     localSQLException.fillInStackTrace();
/* 2817 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2832 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 2834 */     if (localObject1 == null) {
/* 2835 */       return 0;
/*      */     }
/* 2837 */     if ((localObject1 instanceof BigDecimal))
/*      */     {
/* 2839 */       localObject2 = (BigDecimal)localObject1;
/* 2840 */       if ((((BigDecimal)localObject2).compareTo(new BigDecimal(127)) == 1) || (((BigDecimal)localObject2).compareTo(new BigDecimal(-128)) == -1))
/*      */       {
/*      */ 
/* 2843 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26);
/* 2844 */         localSQLException.fillInStackTrace();
/* 2845 */         throw localSQLException;
/*      */       }
/*      */       
/* 2848 */       return ((BigDecimal)localObject2).byteValue();
/*      */     }
/*      */     
/* 2851 */     if ((localObject1 instanceof Number)) {
/* 2852 */       return ((Number)localObject1).byteValue();
/*      */     }
/* 2854 */     if ((localObject1 instanceof String)) {
/* 2855 */       return ((String)localObject1).getBytes()[0];
/*      */     }
/* 2857 */     if ((localObject1 instanceof OracleSerialBlob))
/*      */     {
/* 2859 */       localObject2 = (OracleSerialBlob)localObject1;
/* 2860 */       return localObject2.getBytes(1L, 1)[0];
/*      */     }
/* 2862 */     if ((localObject1 instanceof OracleSerialClob))
/*      */     {
/* 2864 */       localObject2 = (OracleSerialClob)localObject1;
/* 2865 */       return localObject2.getSubString(1L, 1).getBytes()[0];
/*      */     }
/*      */     
/*      */ 
/* 2869 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 2870 */     ((SQLException)localObject2).fillInStackTrace();
/* 2871 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2882 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2884 */     return localNumber == null ? 0 : localNumber.shortValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2893 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2895 */     return localNumber == null ? 0 : localNumber.intValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2904 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2906 */     return localNumber == null ? 0L : localNumber.longValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2915 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2917 */     return localNumber == null ? 0.0F : localNumber.floatValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2926 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2928 */     return localNumber == null ? 0.0D : localNumber.doubleValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2937 */     Number localNumber = getNumber(paramInt);
/*      */     
/* 2939 */     if ((localNumber == null) || ((localNumber instanceof BigDecimal))) {
/* 2940 */       return (BigDecimal)localNumber;
/*      */     }
/* 2942 */     return new BigDecimal(localNumber.doubleValue());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 2952 */     return getBigDecimal(paramInt1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date getDate(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2961 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 2963 */     if (localObject1 == null) {
/* 2964 */       return (java.sql.Date)localObject1;
/*      */     }
/* 2966 */     if ((localObject1 instanceof Time))
/*      */     {
/* 2968 */       localObject2 = (Time)localObject1;
/* 2969 */       return new java.sql.Date(((Time)localObject2).getTime());
/*      */     }
/*      */     
/* 2972 */     if ((localObject1 instanceof java.util.Date))
/*      */     {
/* 2974 */       localObject2 = (java.util.Date)localObject1;
/* 2975 */       return new java.sql.Date(((java.util.Date)localObject2).getYear(), ((java.util.Date)localObject2).getMonth(), ((java.util.Date)localObject2).getDate());
/*      */     }
/*      */     
/* 2978 */     if ((localObject1 instanceof TIMESTAMP)) {
/* 2979 */       return ((TIMESTAMP)localObject1).dateValue();
/*      */     }
/* 2981 */     if ((localObject1 instanceof TIMESTAMPTZ)) {
/* 2982 */       return ((TIMESTAMPTZ)localObject1).dateValue(getConnectionInternal());
/*      */     }
/* 2984 */     if ((localObject1 instanceof TIMESTAMPLTZ))
/*      */     {
/* 2986 */       localObject2 = getConnectionInternal();
/*      */       
/* 2988 */       return ((TIMESTAMPLTZ)localObject1).dateValue((Connection)localObject2, getSessionCalendar((Connection)localObject2));
/*      */     }
/*      */     
/*      */ 
/* 2992 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 2993 */     ((SQLException)localObject2).fillInStackTrace();
/* 2994 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date getDate(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3005 */     return getDate(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3015 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 3017 */     if (localObject1 == null) {
/* 3018 */       return (Time)localObject1;
/*      */     }
/* 3020 */     if ((localObject1 instanceof java.util.Date))
/*      */     {
/* 3022 */       localObject2 = (java.util.Date)localObject1;
/* 3023 */       return new Time(((java.util.Date)localObject2).getHours(), ((java.util.Date)localObject2).getMinutes(), ((java.util.Date)localObject2).getSeconds());
/*      */     }
/*      */     
/*      */ 
/* 3027 */     if ((localObject1 instanceof TIMESTAMP)) {
/* 3028 */       return ((TIMESTAMP)localObject1).timeValue();
/*      */     }
/* 3030 */     if ((localObject1 instanceof TIMESTAMPTZ)) {
/* 3031 */       return ((TIMESTAMPTZ)localObject1).timeValue(getConnectionInternal());
/*      */     }
/* 3033 */     if ((localObject1 instanceof TIMESTAMPLTZ))
/*      */     {
/* 3035 */       localObject2 = getConnectionInternal();
/*      */       
/* 3037 */       return ((TIMESTAMPLTZ)localObject1).timeValue((Connection)localObject2, getSessionCalendar((Connection)localObject2));
/*      */     }
/*      */     
/*      */ 
/* 3041 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3042 */     ((SQLException)localObject2).fillInStackTrace();
/* 3043 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3054 */     return getTime(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3063 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 3065 */     if ((localObject1 == null) || ((localObject1 instanceof Timestamp))) {
/* 3066 */       return (Timestamp)localObject1;
/*      */     }
/* 3068 */     if ((localObject1 instanceof java.util.Date)) {
/* 3069 */       return new Timestamp(((java.util.Date)localObject1).getTime());
/*      */     }
/* 3071 */     if ((localObject1 instanceof TIMESTAMP)) {
/* 3072 */       return ((TIMESTAMP)localObject1).timestampValue();
/*      */     }
/* 3074 */     if ((localObject1 instanceof TIMESTAMPTZ)) {
/* 3075 */       return ((TIMESTAMPTZ)localObject1).timestampValue(getConnectionInternal());
/*      */     }
/* 3077 */     if ((localObject1 instanceof TIMESTAMPLTZ))
/*      */     {
/* 3079 */       localObject2 = getConnectionInternal();
/*      */       
/* 3081 */       return ((TIMESTAMPLTZ)localObject1).timestampValue((Connection)localObject2, getSessionCalendar((Connection)localObject2));
/*      */     }
/*      */     
/*      */ 
/* 3085 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3086 */     ((SQLException)localObject2).fillInStackTrace();
/* 3087 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(int paramInt, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3098 */     return getTimestamp(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3107 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 3109 */     if (localObject1 == null) {
/* 3110 */       return (byte[])localObject1;
/*      */     }
/* 3112 */     if ((localObject1 instanceof byte[])) {
/* 3113 */       return (byte[])localObject1;
/*      */     }
/* 3115 */     if ((localObject1 instanceof String)) {
/* 3116 */       return (byte[])((String)localObject1).getBytes();
/*      */     }
/* 3118 */     if ((localObject1 instanceof Number)) {
/* 3119 */       return (byte[])((Number)localObject1).toString().getBytes();
/*      */     }
/* 3121 */     if ((localObject1 instanceof BigDecimal)) {
/* 3122 */       return (byte[])((BigDecimal)localObject1).toString().getBytes();
/*      */     }
/* 3124 */     if ((localObject1 instanceof OracleSerialBlob))
/*      */     {
/* 3126 */       localObject2 = (OracleSerialBlob)localObject1;
/* 3127 */       return ((OracleSerialBlob)localObject2).getBytes(1L, (int)((OracleSerialBlob)localObject2).length());
/*      */     }
/* 3129 */     if ((localObject1 instanceof OracleSerialClob))
/*      */     {
/* 3131 */       localObject2 = (OracleSerialClob)localObject1;
/* 3132 */       return ((OracleSerialClob)localObject2).getSubString(1L, (int)((OracleSerialClob)localObject2).length()).getBytes();
/*      */     }
/*      */     
/*      */ 
/* 3136 */     Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 3137 */     ((SQLException)localObject2).fillInStackTrace();
/* 3138 */     throw ((Throwable)localObject2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref getRef(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3149 */     Object localObject = getObject(paramInt);
/*      */     
/* 3151 */     if ((localObject == null) || ((localObject instanceof Ref))) {
/* 3152 */       return (Ref)localObject;
/*      */     }
/*      */     
/* 3155 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3156 */     localSQLException.fillInStackTrace();
/* 3157 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob getBlob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3168 */     Object localObject = getObject(paramInt);
/*      */     
/* 3170 */     if ((localObject == null) || ((localObject instanceof OracleSerialBlob))) {
/* 3171 */       return (Blob)localObject;
/*      */     }
/*      */     
/* 3174 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3175 */     localSQLException.fillInStackTrace();
/* 3176 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob getClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3187 */     Object localObject = getObject(paramInt);
/*      */     
/* 3189 */     if ((localObject == null) || ((localObject instanceof OracleSerialClob))) {
/* 3190 */       return (Clob)localObject;
/*      */     }
/*      */     
/* 3193 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3194 */     localSQLException.fillInStackTrace();
/* 3195 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3206 */     Object localObject = getObject(paramInt);
/*      */     
/* 3208 */     if ((localObject == null) || ((localObject instanceof Array))) {
/* 3209 */       return (Array)localObject;
/*      */     }
/*      */     
/* 3212 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 3213 */     localSQLException.fillInStackTrace();
/* 3214 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3224 */     Object localObject1 = getObject(paramInt);
/*      */     
/* 3226 */     if (localObject1 == null) {
/* 3227 */       return (String)localObject1;
/*      */     }
/* 3229 */     if ((localObject1 instanceof String)) {
/* 3230 */       return (String)localObject1;
/*      */     }
/* 3232 */     if (((localObject1 instanceof Number)) || ((localObject1 instanceof BigDecimal))) {
/* 3233 */       return localObject1.toString();
/*      */     }
/* 3235 */     if ((localObject1 instanceof java.sql.Date)) {
/* 3236 */       return localObject1.toString();
/*      */     }
/* 3238 */     if ((localObject1 instanceof Timestamp)) {
/* 3239 */       return localObject1.toString();
/*      */     }
/* 3241 */     if ((localObject1 instanceof byte[]))
/* 3242 */       return new String((byte[])localObject1);
/*      */     Object localObject2;
/* 3244 */     if ((localObject1 instanceof OracleSerialClob))
/*      */     {
/* 3246 */       localObject2 = (OracleSerialClob)localObject1;
/* 3247 */       return ((OracleSerialClob)localObject2).getSubString(1L, (int)((OracleSerialClob)localObject2).length());
/*      */     }
/*      */     
/* 3250 */     if ((localObject1 instanceof OracleSerialBlob))
/*      */     {
/* 3252 */       localObject2 = (OracleSerialBlob)localObject1;
/* 3253 */       return new String(((OracleSerialBlob)localObject2).getBytes(1L, (int)((OracleSerialBlob)localObject2).length()));
/*      */     }
/*      */     
/*      */ 
/* 3257 */     if ((localObject1 instanceof URL)) {
/* 3258 */       return ((URL)localObject1).toString();
/*      */     }
/* 3260 */     if ((localObject1 instanceof ROWID)) {
/* 3261 */       return ((ROWID)localObject1).stringValue();
/*      */     }
/* 3263 */     if ((localObject1 instanceof Reader))
/*      */     {
/*      */       try
/*      */       {
/* 3267 */         localObject2 = (Reader)localObject1;
/* 3268 */         localObject3 = new char['Ѐ'];
/* 3269 */         int i = 0;
/* 3270 */         StringBuffer localStringBuffer = new StringBuffer(1024);
/* 3271 */         while ((i = ((Reader)localObject2).read((char[])localObject3)) > 0)
/* 3272 */           localStringBuffer.append((char[])localObject3, 0, i);
/* 3273 */         return localStringBuffer.substring(0, localStringBuffer.length());
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 3278 */         Object localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 321, localIOException.getMessage());
/* 3279 */         ((SQLException)localObject3).fillInStackTrace();
/* 3280 */         throw ((Throwable)localObject3);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3286 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 3287 */     localSQLException.fillInStackTrace();
/* 3288 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3299 */     InputStream localInputStream = getStream(paramInt);
/*      */     
/* 3301 */     return localInputStream == null ? null : localInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3310 */     Object localObject = getObject(paramInt);
/*      */     
/* 3312 */     if (localObject == null) {
/* 3313 */       return (InputStream)localObject;
/*      */     }
/*      */     
/* 3316 */     if ((localObject instanceof String)) {
/* 3317 */       return new StringBufferInputStream((String)localObject);
/*      */     }
/*      */     
/* 3320 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59);
/* 3321 */     localSQLException.fillInStackTrace();
/* 3322 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3334 */     InputStream localInputStream = getStream(paramInt);
/*      */     
/* 3336 */     return localInputStream == null ? null : localInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Reader getCharacterStream(int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 3348 */       InputStream localInputStream = getAsciiStream(paramInt);
/*      */       
/* 3350 */       if (localInputStream == null) {
/* 3351 */         return null;
/*      */       }
/* 3353 */       localObject = new StringBuffer();
/* 3354 */       int i = 0;
/* 3355 */       while ((i = localInputStream.read()) != -1) {
/* 3356 */         ((StringBuffer)localObject).append((char)i);
/*      */       }
/*      */       
/*      */ 
/* 3360 */       char[] arrayOfChar = new char[((StringBuffer)localObject).length()];
/* 3361 */       ((StringBuffer)localObject).getChars(0, ((StringBuffer)localObject).length(), arrayOfChar, 0);
/* 3362 */       CharArrayReader localCharArrayReader = new CharArrayReader(arrayOfChar);
/* 3363 */       arrayOfChar = null;
/*      */       
/* 3365 */       return localCharArrayReader;
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/* 3370 */       Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 322);
/* 3371 */       ((SQLException)localObject).fillInStackTrace();
/* 3372 */       throw ((Throwable)localObject);
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
/*      */   public synchronized Object getObject(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3389 */     return getObject(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBoolean(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3398 */     return getBoolean(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte getByte(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3407 */     return getByte(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getShort(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3416 */     return getShort(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInt(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3425 */     return getInt(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getLong(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3434 */     return getLong(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getFloat(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3443 */     return getFloat(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDouble(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3452 */     return getDouble(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3461 */     return getBigDecimal(getColumnIndex(paramString), paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getBytes(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3470 */     return getBytes(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date getDate(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3479 */     return getDate(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3488 */     return getTime(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3497 */     return getTimestamp(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time getTime(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3506 */     return getTime(getColumnIndex(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public java.sql.Date getDate(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3515 */     return getDate(getColumnIndex(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getAsciiStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3524 */     return getAsciiStream(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getUnicodeStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3533 */     return getUnicodeStream(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3542 */     return getString(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream getBinaryStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3551 */     return getBinaryStream(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader getCharacterStream(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3560 */     return getCharacterStream(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal getBigDecimal(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3569 */     return getBigDecimal(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp getTimestamp(String paramString, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 3578 */     return getTimestamp(getColumnIndex(paramString), paramCalendar);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object getObject(String paramString, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 3586 */     return getObject(getColumnIndex(paramString), paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref getRef(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3595 */     return getRef(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob getBlob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3604 */     return getBlob(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob getClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3613 */     return getClob(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array getArray(String paramString)
/*      */     throws SQLException
/*      */   {
/* 3622 */     return getArray(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 3636 */     updateObject(paramInt, paramObject, (int[])null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected synchronized void updateObject(int paramInt, Object paramObject, int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 3654 */     checkColumnIndex(paramInt);
/* 3655 */     if (this.insertRowFlag)
/*      */     {
/* 3657 */       checkAndFilterObject(paramInt, paramObject);
/* 3658 */       this.insertRow.updateObject(paramInt, paramObject, paramArrayOfInt);
/*      */     }
/* 3660 */     else if ((!isBeforeFirst()) && (!isAfterLast()))
/*      */     {
/*      */ 
/*      */ 
/* 3664 */       this.updateRowFlag = true;
/* 3665 */       this.updateRowPosition = this.presentRow;
/* 3666 */       getCurrentRow().updateObject(paramInt, paramObject, paramArrayOfInt);
/*      */     }
/*      */     else {
/* 3669 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 90);
/* 3670 */       localSQLException.fillInStackTrace();
/* 3671 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNull(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3682 */     updateObject(paramInt, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void updateCharacterStream(int paramInt1, Reader paramReader, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3693 */     updateCharacterStreamInternal(paramInt1, paramReader, paramInt2, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void updateCharacterStreamInternal(int paramInt1, Reader paramReader, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3704 */     checkColumnIndex(paramInt1);
/*      */     
/* 3706 */     if (paramInt2 < 0)
/*      */     {
/* 3708 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3709 */       localSQLException.fillInStackTrace();
/* 3710 */       throw localSQLException;
/*      */     }
/*      */     
/* 3713 */     int i = getMetaData().getColumnType(paramInt1);
/* 3714 */     if (!isStreamType(i))
/*      */     {
/*      */       try
/*      */       {
/* 3718 */         int j = 0;
/* 3719 */         int m = paramInt2;
/* 3720 */         char[] arrayOfChar = new char['Ѐ'];
/* 3721 */         StringBuilder localStringBuilder = new StringBuilder(1024);
/*      */         
/* 3723 */         while (m > 0)
/*      */         {
/* 3725 */           if (m >= 1024) {
/* 3726 */             j = paramReader.read(arrayOfChar);
/*      */           } else {
/* 3728 */             j = paramReader.read(arrayOfChar, 0, m);
/*      */           }
/*      */           
/*      */ 
/* 3732 */           if (j == -1) {
/*      */             break;
/*      */           }
/* 3735 */           localStringBuilder.append(arrayOfChar, 0, j);
/* 3736 */           m -= j;
/*      */         }
/*      */         
/* 3739 */         paramReader.close();
/* 3740 */         if (m == paramInt2)
/*      */         {
/* 3742 */           updateNull(paramInt1);
/* 3743 */           return;
/*      */         }
/*      */         
/* 3746 */         updateString(paramInt1, localStringBuilder.toString());
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 3751 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3752 */         ((SQLException)localObject).fillInStackTrace();
/* 3753 */         throw ((Throwable)localObject);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3759 */     int k = paramBoolean ? 4 : 3;
/*      */     
/*      */ 
/* 3762 */     Object localObject = { paramInt2, k };
/*      */     
/* 3764 */     updateObject(paramInt1, paramReader, (int[])localObject);
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
/* 3775 */     updateCharacterStream(getColumnIndex(paramString), paramReader, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 3785 */     updateTimestamp(getColumnIndex(paramString), paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3795 */     updateBinaryStream(getColumnIndex(paramString), paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void updateBinaryStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3806 */     checkColumnIndex(paramInt1);
/*      */     
/* 3808 */     if (paramInt2 < 0)
/*      */     {
/* 3810 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3811 */       localSQLException1.fillInStackTrace();
/* 3812 */       throw localSQLException1;
/*      */     }
/*      */     
/* 3815 */     int i = getMetaData().getColumnType(paramInt1);
/* 3816 */     if (!isStreamType(i))
/*      */     {
/*      */       try
/*      */       {
/* 3820 */         int j = 0;
/* 3821 */         int k = paramInt2;
/* 3822 */         byte[] arrayOfByte = new byte['Ѐ'];
/* 3823 */         ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(1024);
/*      */         
/*      */ 
/* 3826 */         while (k > 0)
/*      */         {
/* 3828 */           if (k >= 1024) {
/* 3829 */             j = paramInputStream.read(arrayOfByte);
/*      */           } else {
/* 3831 */             j = paramInputStream.read(arrayOfByte, 0, k);
/*      */           }
/*      */           
/*      */ 
/* 3835 */           if (j == -1) {
/*      */             break;
/*      */           }
/* 3838 */           localByteArrayOutputStream.write(arrayOfByte, 0, j);
/* 3839 */           k -= j;
/*      */         }
/*      */         
/* 3842 */         paramInputStream.close();
/* 3843 */         if (k == paramInt2)
/*      */         {
/* 3845 */           updateNull(paramInt1);
/* 3846 */           return;
/*      */         }
/*      */         
/* 3849 */         updateBytes(paramInt1, localByteArrayOutputStream.toByteArray());
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 3854 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3855 */         localSQLException2.fillInStackTrace();
/* 3856 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3862 */     int[] arrayOfInt = { paramInt2, 2 };
/*      */     
/* 3864 */     updateObject(paramInt1, paramInputStream, arrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void updateAsciiStream(int paramInt1, InputStream paramInputStream, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3875 */     checkColumnIndex(paramInt1);
/*      */     
/* 3877 */     if (paramInt2 < 0)
/*      */     {
/* 3879 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 3880 */       localSQLException1.fillInStackTrace();
/* 3881 */       throw localSQLException1;
/*      */     }
/*      */     
/* 3884 */     int i = getMetaData().getColumnType(paramInt1);
/* 3885 */     if (!isStreamType(i))
/*      */     {
/*      */       try
/*      */       {
/* 3889 */         int j = 0;
/* 3890 */         int k = paramInt2;
/* 3891 */         byte[] arrayOfByte = new byte['Ѐ'];
/* 3892 */         char[] arrayOfChar = new char['Ѐ'];
/* 3893 */         StringBuilder localStringBuilder = new StringBuilder(1024);
/*      */         
/* 3895 */         while (k > 0)
/*      */         {
/* 3897 */           if (k >= 1024) {
/* 3898 */             j = paramInputStream.read(arrayOfByte);
/*      */           } else {
/* 3900 */             j = paramInputStream.read(arrayOfByte, 0, k);
/*      */           }
/*      */           
/*      */ 
/* 3904 */           if (j == -1) {
/*      */             break;
/*      */           }
/* 3907 */           DBConversion.asciiBytesToJavaChars(arrayOfByte, j, arrayOfChar);
/*      */           
/* 3909 */           localStringBuilder.append(arrayOfChar, 0, j);
/* 3910 */           k -= j;
/*      */         }
/*      */         
/* 3913 */         paramInputStream.close();
/* 3914 */         if (k == paramInt2)
/*      */         {
/* 3916 */           updateNull(paramInt1);
/* 3917 */           return;
/*      */         }
/*      */         
/* 3920 */         updateString(paramInt1, localStringBuilder.toString());
/*      */ 
/*      */       }
/*      */       catch (IOException localIOException)
/*      */       {
/* 3925 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 3926 */         localSQLException2.fillInStackTrace();
/* 3927 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3933 */     int[] arrayOfInt = { paramInt2, 1 };
/*      */     
/* 3935 */     updateObject(paramInt1, paramInputStream, arrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTimestamp(int paramInt, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 3946 */     updateObject(paramInt, paramTimestamp);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBoolean(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3956 */     updateObject(paramInt, Boolean.valueOf(paramBoolean));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateByte(int paramInt, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 3966 */     updateObject(paramInt, new Byte(paramByte));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateShort(int paramInt, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 3976 */     updateObject(paramInt, Short.valueOf(paramShort));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateInt(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 3986 */     updateObject(paramInt1, Integer.valueOf(paramInt2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateLong(int paramInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 3996 */     updateObject(paramInt, Long.valueOf(paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateFloat(int paramInt, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 4006 */     updateObject(paramInt, Float.valueOf(paramFloat));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDouble(int paramInt, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 4016 */     updateObject(paramInt, Double.valueOf(paramDouble));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(int paramInt, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 4026 */     updateObject(paramInt, paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 4036 */     updateObject(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBytes(int paramInt, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4046 */     updateObject(paramInt, paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDate(int paramInt, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 4056 */     updateObject(paramInt, paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTime(int paramInt, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 4066 */     updateObject(paramInt, new Timestamp(0, 0, 0, paramTime.getHours(), paramTime.getMinutes(), paramTime.getSeconds(), 0));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(int paramInt1, Object paramObject, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 4078 */     if (!(paramObject instanceof Number))
/*      */     {
/* 4080 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 4081 */       localSQLException.fillInStackTrace();
/* 4082 */       throw localSQLException;
/*      */     }
/*      */     
/* 4085 */     updateObject(paramInt1, new BigDecimal(new BigInteger(((Number)paramObject).toString()), paramInt2));
/*      */   }
/*      */   
/*      */ 
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
/* 4101 */     updateNull(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4111 */     updateAsciiStream(getColumnIndex(paramString), paramInputStream, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 4121 */     updateBoolean(getColumnIndex(paramString), paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 4131 */     updateByte(getColumnIndex(paramString), paramByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 4141 */     updateShort(getColumnIndex(paramString), paramShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4151 */     updateInt(getColumnIndex(paramString), paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 4161 */     updateLong(getColumnIndex(paramString), paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 4171 */     updateFloat(getColumnIndex(paramString), paramFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 4181 */     updateDouble(getColumnIndex(paramString), paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 4191 */     updateBigDecimal(getColumnIndex(paramString), paramBigDecimal);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 4201 */     updateString(getColumnIndex(paramString1), paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 4211 */     updateBytes(getColumnIndex(paramString), paramArrayOfByte);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateDate(String paramString, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 4221 */     updateDate(getColumnIndex(paramString), paramDate);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 4231 */     updateTime(getColumnIndex(paramString), paramTime);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 4242 */     updateObject(getColumnIndex(paramString), paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4252 */     updateObject(getColumnIndex(paramString), paramObject, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4279 */     Object localObject = getObject(paramInt);
/*      */     
/* 4281 */     if (localObject == null) {
/* 4282 */       return (URL)localObject;
/*      */     }
/* 4284 */     if ((localObject instanceof URL)) {
/* 4285 */       return (URL)localObject;
/*      */     }
/* 4287 */     if ((localObject instanceof String))
/*      */     {
/*      */       try
/*      */       {
/* 4291 */         return new URL((String)localObject);
/*      */ 
/*      */       }
/*      */       catch (MalformedURLException localMalformedURLException)
/*      */       {
/* 4296 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 136);
/* 4297 */         localSQLException2.fillInStackTrace();
/* 4298 */         throw localSQLException2;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4304 */     SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 4305 */     localSQLException1.fillInStackTrace();
/* 4306 */     throw localSQLException1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4332 */     return getURL(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4355 */     updateObject(paramInt, paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4379 */     updateRef(getColumnIndex(paramString), paramRef);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4403 */     updateObject(paramInt, paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4427 */     updateBlob(getColumnIndex(paramString), paramBlob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4451 */     updateObject(paramInt, paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4475 */     updateClob(getColumnIndex(paramString), paramClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4499 */     updateObject(paramInt, paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4523 */     updateArray(getColumnIndex(paramString), paramArray);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int[] getKeyColumns()
/*      */     throws SQLException
/*      */   {
/* 4549 */     return this.keyColumns;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setKeyColumns(int[] paramArrayOfInt)
/*      */     throws SQLException
/*      */   {
/* 4574 */     int i = 0;
/*      */     
/* 4576 */     if (this.rowsetMetaData != null)
/*      */     {
/* 4578 */       i = this.rowsetMetaData.getColumnCount();
/* 4579 */       if ((paramArrayOfInt == null) || (paramArrayOfInt.length > i))
/*      */       {
/*      */ 
/* 4582 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 324);
/* 4583 */         localSQLException1.fillInStackTrace();
/* 4584 */         throw localSQLException1;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4589 */     int j = paramArrayOfInt.length;
/* 4590 */     this.keyColumns = new int[j];
/*      */     
/* 4592 */     for (int k = 0; k < j; k++)
/*      */     {
/* 4594 */       if ((paramArrayOfInt[k] <= 0) || (paramArrayOfInt[k] > i))
/*      */       {
/*      */ 
/* 4597 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 3, "" + paramArrayOfInt[k]);
/* 4598 */         localSQLException2.fillInStackTrace();
/* 4599 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/* 4603 */       this.keyColumns[k] = paramArrayOfInt[k];
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
/*      */   public int getPageSize()
/*      */   {
/* 4618 */     return this.pageSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPageSize(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4638 */     if ((paramInt < 0) || ((this.maxRows > 0) && (paramInt > this.maxRows)))
/*      */     {
/*      */ 
/*      */ 
/* 4642 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 325);
/* 4643 */       localSQLException.fillInStackTrace();
/* 4644 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 4648 */     this.pageSize = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SyncProvider getSyncProvider()
/*      */     throws SQLException
/*      */   {
/* 4666 */     return this.syncProvider;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSyncProvider(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4684 */     this.syncProvider = SyncFactory.getInstance(paramString);
/* 4685 */     this.reader = this.syncProvider.getRowSetReader();
/* 4686 */     this.writer = this.syncProvider.getRowSetWriter();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getTableName()
/*      */     throws SQLException
/*      */   {
/* 4706 */     return this.tableName;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTableName(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4725 */     this.tableName = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CachedRowSet createCopyNoConstraints()
/*      */     throws SQLException
/*      */   {
/* 4753 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)createCopy();
/*      */     
/*      */ 
/*      */ 
/* 4757 */     localOracleCachedRowSet.initializeProperties();
/*      */     
/*      */ 
/* 4760 */     localOracleCachedRowSet.listener = new Vector();
/*      */     
/*      */     try
/*      */     {
/* 4764 */       localOracleCachedRowSet.unsetMatchColumn(localOracleCachedRowSet.getMatchColumnIndexes());
/* 4765 */       localOracleCachedRowSet.unsetMatchColumn(localOracleCachedRowSet.getMatchColumnNames());
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/*      */ 
/*      */ 
/* 4771 */     return localOracleCachedRowSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CachedRowSet createCopySchema()
/*      */     throws SQLException
/*      */   {
/* 4794 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)createCopy();
/*      */     
/*      */ 
/* 4797 */     localOracleCachedRowSet.rows = null;
/* 4798 */     localOracleCachedRowSet.rowCount = 0;
/* 4799 */     localOracleCachedRowSet.currentPage = 0;
/*      */     
/* 4801 */     return localOracleCachedRowSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean columnUpdated(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 4820 */     if (this.insertRowFlag)
/*      */     {
/*      */ 
/* 4823 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 326);
/* 4824 */       localSQLException.fillInStackTrace();
/* 4825 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 4829 */     return getCurrentRow().isColumnChanged(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean columnUpdated(String paramString)
/*      */     throws SQLException
/*      */   {
/* 4848 */     return columnUpdated(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized void execute(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 4880 */     this.connection = paramConnection;
/* 4881 */     execute();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4909 */     getConnectionInternal().commit();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4929 */     getConnectionInternal().rollback();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 4952 */     Connection localConnection = getConnectionInternal();
/* 4953 */     boolean bool = localConnection.getAutoCommit();
/*      */     try
/*      */     {
/* 4956 */       localConnection.setAutoCommit(false);
/* 4957 */       localConnection.rollback(paramSavepoint);
/*      */     }
/*      */     finally {
/* 4960 */       localConnection.setAutoCommit(bool);
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
/*      */   public void oracleRollback(OracleSavepoint paramOracleSavepoint)
/*      */     throws SQLException
/*      */   {
/* 4986 */     ((OracleConnection)getConnectionInternal()).oracleRollback(paramOracleSavepoint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOriginalRow()
/*      */     throws SQLException
/*      */   {
/* 5008 */     if (this.insertRowFlag)
/*      */     {
/*      */ 
/* 5011 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 327);
/* 5012 */       localSQLException.fillInStackTrace();
/* 5013 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 5018 */     setOriginalRowInternal(this.presentRow);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/* 5033 */     return this.rowCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean nextPage()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5054 */     if ((this.fetchDirection == 1001) && (this.resultSet != null) && (this.resultSet.getType() == 1003))
/*      */     {
/*      */ 
/* 5057 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 328);
/* 5058 */       localSQLException.fillInStackTrace();
/* 5059 */       throw localSQLException;
/*      */     }
/*      */     
/* 5062 */     if ((this.rows.size() == 0) && (!this.isPopulateDone))
/*      */     {
/* 5064 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 329);
/* 5065 */       localSQLException.fillInStackTrace();
/* 5066 */       throw localSQLException;
/*      */     }
/*      */     
/* 5069 */     if (((isExecuteCalled()) || (this.isPopulateDone)) && (this.rowCount < this.pageSize)) {
/* 5070 */       return false;
/*      */     }
/* 5072 */     if (isExecuteCalled())
/*      */     {
/* 5074 */       this.currentPage += 1;
/* 5075 */       execute();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 5080 */       populate(this.resultSet);
/* 5081 */       this.currentPage += 1;
/*      */     }
/*      */     
/* 5084 */     return !this.isPopulateDone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean previousPage()
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 5105 */     if ((this.resultSet != null) && (this.resultSet.getType() == 1003))
/*      */     {
/* 5107 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 328);
/* 5108 */       localSQLException.fillInStackTrace();
/* 5109 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 5113 */     if ((this.rows.size() == 0) && (!this.isPopulateDone))
/*      */     {
/* 5115 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 329);
/* 5116 */       localSQLException.fillInStackTrace();
/* 5117 */       throw localSQLException;
/*      */     }
/*      */     
/* 5120 */     if (this.currentPage == 0) {
/* 5121 */       return false;
/*      */     }
/*      */     
/*      */ 
/* 5125 */     if (this.fetchDirection == 1001)
/*      */     {
/* 5127 */       this.resultSet.relative(this.pageSize * 2);
/*      */     }
/*      */     else
/*      */     {
/* 5131 */       this.resultSet.relative(-2 * this.pageSize);
/*      */     }
/*      */     
/* 5134 */     populate(this.resultSet);
/*      */     
/* 5136 */     if (this.currentPage > 0)
/*      */     {
/* 5138 */       this.currentPage -= 1;
/*      */     }
/*      */     
/* 5141 */     return this.currentPage != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void rowSetPopulated(RowSetEvent paramRowSetEvent, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5150 */     if ((paramInt <= 0) || (paramInt < this.fetchSize))
/*      */     {
/*      */ 
/* 5153 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 330);
/* 5154 */       localSQLException.fillInStackTrace();
/* 5155 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 5159 */     if (this.rowCount % paramInt == 0)
/*      */     {
/* 5161 */       this.rowsetEvent = new RowSetEvent(this);
/* 5162 */       notifyRowSetChanged();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowSetWarning getRowSetWarnings()
/*      */     throws SQLException
/*      */   {
/* 5173 */     return this.rowsetWarning;
/*      */   }
/*      */   
/*      */ 
/*      */   public void populate(ResultSet paramResultSet, int paramInt)
/*      */     throws SQLException
/*      */   {
/*      */     SQLException localSQLException1;
/*      */     
/* 5182 */     if (paramInt < 0)
/*      */     {
/* 5184 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 331);
/* 5185 */       localSQLException1.fillInStackTrace();
/* 5186 */       throw localSQLException1;
/*      */     }
/*      */     
/* 5189 */     if (paramResultSet == null)
/*      */     {
/* 5191 */       localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 332);
/* 5192 */       localSQLException1.fillInStackTrace();
/* 5193 */       throw localSQLException1;
/*      */     }
/*      */     
/* 5196 */     int i = paramResultSet.getType();
/*      */     
/* 5198 */     if (i == 1003)
/*      */     {
/* 5200 */       int j = 0;
/* 5201 */       int k = 0;
/* 5202 */       while (j < paramInt)
/*      */       {
/* 5204 */         if (!paramResultSet.next())
/*      */         {
/* 5206 */           k = 1;
/* 5207 */           break;
/*      */         }
/* 5209 */         j++;
/*      */       }
/*      */       
/* 5212 */       if ((j < paramInt) && (paramInt > 0) && (k != 0))
/*      */       {
/* 5214 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 333);
/* 5215 */         localSQLException2.fillInStackTrace();
/* 5216 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 5221 */     else if (paramInt == 0) {
/* 5222 */       paramResultSet.beforeFirst();
/*      */     } else {
/* 5224 */       paramResultSet.absolute(paramInt);
/*      */     }
/*      */     
/* 5227 */     populate(paramResultSet);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void undoDelete()
/*      */     throws SQLException
/*      */   {
/* 5237 */     cancelRowDelete();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void undoInsert()
/*      */     throws SQLException
/*      */   {
/* 5247 */     cancelRowInsert();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void undoUpdate()
/*      */     throws SQLException
/*      */   {
/* 5257 */     cancelRowUpdates();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 5277 */     return getCharacterStream(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5286 */     Object localObject = getObject(paramInt);
/*      */     
/* 5288 */     if ((localObject == null) || ((localObject instanceof OracleSerialClob))) {
/* 5289 */       return (NClob)localObject;
/*      */     }
/*      */     
/* 5292 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 5293 */     localSQLException.fillInStackTrace();
/* 5294 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNString(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5305 */     return getString(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowId getRowId(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5314 */     Object localObject = getObject(paramInt);
/*      */     
/*      */ 
/* 5317 */     if ((localObject instanceof RowId)) {
/* 5318 */       return localObject == null ? null : (RowId)localObject;
/*      */     }
/*      */     
/* 5321 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 5322 */     localSQLException.fillInStackTrace();
/* 5323 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5334 */     Object localObject = getObject(paramInt);
/* 5335 */     if ((localObject == null) || ((localObject instanceof SQLXML))) {
/* 5336 */       return (SQLXML)localObject;
/*      */     }
/*      */     
/* 5339 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
/* 5340 */     localSQLException.fillInStackTrace();
/* 5341 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
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
/* 5356 */     return getNCharacterStream(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob getNClob(String paramString)
/*      */     throws SQLException
/*      */   {
/* 5365 */     return getNClob(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNString(String paramString)
/*      */     throws SQLException
/*      */   {
/* 5374 */     return getNString(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public RowId getRowId(String paramString)
/*      */     throws SQLException
/*      */   {
/* 5383 */     return getRowId(getColumnIndex(paramString));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLXML getSQLXML(String paramString)
/*      */     throws SQLException
/*      */   {
/* 5392 */     return getSQLXML(getColumnIndex(paramString));
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
/* 5405 */     checkParamIndex(paramInt);
/* 5406 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(7) };
/*      */     
/* 5408 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5419 */     checkParamIndex(paramInt);
/* 5420 */     Object[] arrayOfObject = { paramInputStream, Long.valueOf(paramLong), Integer.valueOf(8) };
/*      */     
/* 5422 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 5432 */     checkParamIndex(paramInt);
/* 5433 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(5) };
/*      */     
/* 5435 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5446 */     checkParamIndex(paramInt);
/* 5447 */     Object[] arrayOfObject = { paramInputStream, Long.valueOf(paramLong), Integer.valueOf(6) };
/*      */     
/* 5449 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 5459 */     checkParamIndex(paramInt);
/* 5460 */     Object[] arrayOfObject = { paramInputStream, Integer.valueOf(13) };
/*      */     
/* 5462 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5472 */     checkParamIndex(paramInt);
/* 5473 */     Object[] arrayOfObject = { paramInputStream, Long.valueOf(paramLong), Integer.valueOf(14) };
/*      */     
/* 5475 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5485 */     checkParamIndex(paramInt);
/* 5486 */     Object[] arrayOfObject = { paramReader, Integer.valueOf(9) };
/*      */     
/* 5488 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5498 */     checkParamIndex(paramInt);
/* 5499 */     Object[] arrayOfObject = { paramReader, Long.valueOf(paramLong), Integer.valueOf(10) };
/*      */     
/* 5501 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5511 */     checkParamIndex(paramInt);
/* 5512 */     Object[] arrayOfObject = { paramReader, Integer.valueOf(15) };
/*      */     
/* 5514 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5524 */     checkParamIndex(paramInt);
/* 5525 */     Object[] arrayOfObject = { this.reader, Long.valueOf(paramLong), Integer.valueOf(16) };
/*      */     
/* 5527 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5537 */     checkParamIndex(paramInt);
/* 5538 */     Object[] arrayOfObject = { paramReader, Integer.valueOf(11) };
/*      */     
/* 5540 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5550 */     checkParamIndex(paramInt);
/* 5551 */     Object[] arrayOfObject = { paramReader, Long.valueOf(paramLong), Integer.valueOf(12) };
/*      */     
/* 5553 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 5563 */     checkParamIndex(paramInt);
/* 5564 */     this.param.add(paramInt - 1, paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 5574 */     checkParamIndex(paramInt);
/* 5575 */     Object[] arrayOfObject = { this.reader, Integer.valueOf(17) };
/*      */     
/* 5577 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5587 */     checkParamIndex(paramInt);
/* 5588 */     Object[] arrayOfObject = { this.reader, Long.valueOf(paramLong), Integer.valueOf(18) };
/*      */     
/* 5590 */     this.param.add(paramInt - 1, arrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 5600 */     checkParamIndex(paramInt);
/* 5601 */     this.param.add(paramInt - 1, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 5611 */     checkParamIndex(paramInt);
/* 5612 */     this.param.add(paramInt - 1, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 5622 */     checkParamIndex(paramInt);
/* 5623 */     this.param.add(paramInt - 1, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 5642 */     String str = paramString.intern();
/* 5643 */     String[] arrayOfString = this.osql.getParameterList();
/* 5644 */     int i = 0;
/* 5645 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5647 */     for (int k = 0; k < j; k++) {
/* 5648 */       if (arrayOfString[k] == str)
/*      */       {
/* 5650 */         setArray(k + 1, paramArray);
/*      */         
/* 5652 */         i = 1;
/*      */       }
/*      */     }
/* 5655 */     if (i == 0)
/*      */     {
/* 5657 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5658 */       localSQLException.fillInStackTrace();
/* 5659 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBigDecimal(String paramString, BigDecimal paramBigDecimal)
/*      */     throws SQLException
/*      */   {
/* 5671 */     String str = paramString.intern();
/* 5672 */     String[] arrayOfString = this.osql.getParameterList();
/* 5673 */     int i = 0;
/* 5674 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5676 */     for (int k = 0; k < j; k++) {
/* 5677 */       if (arrayOfString[k] == str)
/*      */       {
/* 5679 */         setBigDecimal(k + 1, paramBigDecimal);
/*      */         
/* 5681 */         i = 1;
/*      */       }
/*      */     }
/* 5684 */     if (i == 0)
/*      */     {
/* 5686 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5687 */       localSQLException.fillInStackTrace();
/* 5688 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, Blob paramBlob)
/*      */     throws SQLException
/*      */   {
/* 5700 */     String str = paramString.intern();
/* 5701 */     String[] arrayOfString = this.osql.getParameterList();
/* 5702 */     int i = 0;
/* 5703 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5705 */     for (int k = 0; k < j; k++) {
/* 5706 */       if (arrayOfString[k] == str)
/*      */       {
/* 5708 */         setBlob(k + 1, paramBlob);
/*      */         
/* 5710 */         i = 1;
/*      */       }
/*      */     }
/* 5713 */     if (i == 0)
/*      */     {
/* 5715 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5716 */       localSQLException.fillInStackTrace();
/* 5717 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBoolean(String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 5729 */     String str = paramString.intern();
/* 5730 */     String[] arrayOfString = this.osql.getParameterList();
/* 5731 */     int i = 0;
/* 5732 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5734 */     for (int k = 0; k < j; k++) {
/* 5735 */       if (arrayOfString[k] == str)
/*      */       {
/* 5737 */         setBoolean(k + 1, paramBoolean);
/*      */         
/* 5739 */         i = 1;
/*      */       }
/*      */     }
/* 5742 */     if (i == 0)
/*      */     {
/* 5744 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5745 */       localSQLException.fillInStackTrace();
/* 5746 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setByte(String paramString, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 5758 */     String str = paramString.intern();
/* 5759 */     String[] arrayOfString = this.osql.getParameterList();
/* 5760 */     int i = 0;
/* 5761 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5763 */     for (int k = 0; k < j; k++) {
/* 5764 */       if (arrayOfString[k] == str)
/*      */       {
/* 5766 */         setByte(k + 1, paramByte);
/*      */         
/* 5768 */         i = 1;
/*      */       }
/*      */     }
/* 5771 */     if (i == 0)
/*      */     {
/* 5773 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5774 */       localSQLException.fillInStackTrace();
/* 5775 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBytes(String paramString, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 5787 */     String str = paramString.intern();
/* 5788 */     String[] arrayOfString = this.osql.getParameterList();
/* 5789 */     int i = 0;
/* 5790 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5792 */     for (int k = 0; k < j; k++) {
/* 5793 */       if (arrayOfString[k] == str)
/*      */       {
/* 5795 */         setBytes(k + 1, paramArrayOfByte);
/*      */         
/* 5797 */         i = 1;
/*      */       }
/*      */     }
/* 5800 */     if (i == 0)
/*      */     {
/* 5802 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5803 */       localSQLException.fillInStackTrace();
/* 5804 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Clob paramClob)
/*      */     throws SQLException
/*      */   {
/* 5816 */     String str = paramString.intern();
/* 5817 */     String[] arrayOfString = this.osql.getParameterList();
/* 5818 */     int i = 0;
/* 5819 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5821 */     for (int k = 0; k < j; k++) {
/* 5822 */       if (arrayOfString[k] == str)
/*      */       {
/* 5824 */         setClob(k + 1, paramClob);
/*      */         
/* 5826 */         i = 1;
/*      */       }
/*      */     }
/* 5829 */     if (i == 0)
/*      */     {
/* 5831 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5832 */       localSQLException.fillInStackTrace();
/* 5833 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, java.sql.Date paramDate)
/*      */     throws SQLException
/*      */   {
/* 5845 */     String str = paramString.intern();
/* 5846 */     String[] arrayOfString = this.osql.getParameterList();
/* 5847 */     int i = 0;
/* 5848 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5850 */     for (int k = 0; k < j; k++) {
/* 5851 */       if (arrayOfString[k] == str)
/*      */       {
/* 5853 */         setDate(k + 1, paramDate);
/*      */         
/* 5855 */         i = 1;
/*      */       }
/*      */     }
/* 5858 */     if (i == 0)
/*      */     {
/* 5860 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5861 */       localSQLException.fillInStackTrace();
/* 5862 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDate(String paramString, java.sql.Date paramDate, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 5874 */     String str = paramString.intern();
/* 5875 */     String[] arrayOfString = this.osql.getParameterList();
/* 5876 */     int i = 0;
/* 5877 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5879 */     for (int k = 0; k < j; k++) {
/* 5880 */       if (arrayOfString[k] == str)
/*      */       {
/* 5882 */         setDate(k + 1, paramDate, paramCalendar);
/*      */         
/* 5884 */         i = 1;
/*      */       }
/*      */     }
/* 5887 */     if (i == 0)
/*      */     {
/* 5889 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5890 */       localSQLException.fillInStackTrace();
/* 5891 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDouble(String paramString, double paramDouble)
/*      */     throws SQLException
/*      */   {
/* 5903 */     String str = paramString.intern();
/* 5904 */     String[] arrayOfString = this.osql.getParameterList();
/* 5905 */     int i = 0;
/* 5906 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5908 */     for (int k = 0; k < j; k++) {
/* 5909 */       if (arrayOfString[k] == str)
/*      */       {
/* 5911 */         setDouble(k + 1, paramDouble);
/*      */         
/* 5913 */         i = 1;
/*      */       }
/*      */     }
/* 5916 */     if (i == 0)
/*      */     {
/* 5918 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5919 */       localSQLException.fillInStackTrace();
/* 5920 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFloat(String paramString, float paramFloat)
/*      */     throws SQLException
/*      */   {
/* 5932 */     String str = paramString.intern();
/* 5933 */     String[] arrayOfString = this.osql.getParameterList();
/* 5934 */     int i = 0;
/* 5935 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5937 */     for (int k = 0; k < j; k++) {
/* 5938 */       if (arrayOfString[k] == str)
/*      */       {
/* 5940 */         setFloat(k + 1, paramFloat);
/*      */         
/* 5942 */         i = 1;
/*      */       }
/*      */     }
/* 5945 */     if (i == 0)
/*      */     {
/* 5947 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5948 */       localSQLException.fillInStackTrace();
/* 5949 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInt(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 5961 */     String str = paramString.intern();
/* 5962 */     String[] arrayOfString = this.osql.getParameterList();
/* 5963 */     int i = 0;
/* 5964 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5966 */     for (int k = 0; k < j; k++) {
/* 5967 */       if (arrayOfString[k] == str)
/*      */       {
/* 5969 */         setInt(k + 1, paramInt);
/*      */         
/* 5971 */         i = 1;
/*      */       }
/*      */     }
/* 5974 */     if (i == 0)
/*      */     {
/* 5976 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 5977 */       localSQLException.fillInStackTrace();
/* 5978 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLong(String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 5990 */     String str = paramString.intern();
/* 5991 */     String[] arrayOfString = this.osql.getParameterList();
/* 5992 */     int i = 0;
/* 5993 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 5995 */     for (int k = 0; k < j; k++) {
/* 5996 */       if (arrayOfString[k] == str)
/*      */       {
/* 5998 */         setLong(k + 1, paramLong);
/*      */         
/* 6000 */         i = 1;
/*      */       }
/*      */     }
/* 6003 */     if (i == 0)
/*      */     {
/* 6005 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6006 */       localSQLException.fillInStackTrace();
/* 6007 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 6019 */     String str = paramString.intern();
/* 6020 */     String[] arrayOfString = this.osql.getParameterList();
/* 6021 */     int i = 0;
/* 6022 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6024 */     for (int k = 0; k < j; k++) {
/* 6025 */       if (arrayOfString[k] == str)
/*      */       {
/* 6027 */         setNClob(k + 1, paramNClob);
/*      */         
/* 6029 */         i = 1;
/*      */       }
/*      */     }
/* 6032 */     if (i == 0)
/*      */     {
/* 6034 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6035 */       localSQLException.fillInStackTrace();
/* 6036 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 6048 */     String str = paramString1.intern();
/* 6049 */     String[] arrayOfString = this.osql.getParameterList();
/* 6050 */     int i = 0;
/* 6051 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6053 */     for (int k = 0; k < j; k++) {
/* 6054 */       if (arrayOfString[k] == str)
/*      */       {
/* 6056 */         setNString(k + 1, paramString2);
/*      */         
/* 6058 */         i = 1;
/*      */       }
/*      */     }
/* 6061 */     if (i == 0)
/*      */     {
/* 6063 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 6064 */       localSQLException.fillInStackTrace();
/* 6065 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 6077 */     String str = paramString.intern();
/* 6078 */     String[] arrayOfString = this.osql.getParameterList();
/* 6079 */     int i = 0;
/* 6080 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6082 */     for (int k = 0; k < j; k++) {
/* 6083 */       if (arrayOfString[k] == str)
/*      */       {
/* 6085 */         setObject(k + 1, paramObject);
/*      */         
/* 6087 */         i = 1;
/*      */       }
/*      */     }
/* 6090 */     if (i == 0)
/*      */     {
/* 6092 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6093 */       localSQLException.fillInStackTrace();
/* 6094 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6106 */     String str = paramString.intern();
/* 6107 */     String[] arrayOfString = this.osql.getParameterList();
/* 6108 */     int i = 0;
/* 6109 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6111 */     for (int k = 0; k < j; k++) {
/* 6112 */       if (arrayOfString[k] == str)
/*      */       {
/* 6114 */         setObject(k + 1, paramObject, paramInt);
/*      */         
/* 6116 */         i = 1;
/*      */       }
/*      */     }
/* 6119 */     if (i == 0)
/*      */     {
/* 6121 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6122 */       localSQLException.fillInStackTrace();
/* 6123 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRef(String paramString, Ref paramRef)
/*      */     throws SQLException
/*      */   {
/* 6135 */     String str = paramString.intern();
/* 6136 */     String[] arrayOfString = this.osql.getParameterList();
/* 6137 */     int i = 0;
/* 6138 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6140 */     for (int k = 0; k < j; k++) {
/* 6141 */       if (arrayOfString[k] == str)
/*      */       {
/* 6143 */         setRef(k + 1, paramRef);
/*      */         
/* 6145 */         i = 1;
/*      */       }
/*      */     }
/* 6148 */     if (i == 0)
/*      */     {
/* 6150 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6151 */       localSQLException.fillInStackTrace();
/* 6152 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 6164 */     String str = paramString.intern();
/* 6165 */     String[] arrayOfString = this.osql.getParameterList();
/* 6166 */     int i = 0;
/* 6167 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6169 */     for (int k = 0; k < j; k++) {
/* 6170 */       if (arrayOfString[k] == str)
/*      */       {
/* 6172 */         setRowId(k + 1, paramRowId);
/*      */         
/* 6174 */         i = 1;
/*      */       }
/*      */     }
/* 6177 */     if (i == 0)
/*      */     {
/* 6179 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6180 */       localSQLException.fillInStackTrace();
/* 6181 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShort(String paramString, short paramShort)
/*      */     throws SQLException
/*      */   {
/* 6193 */     String str = paramString.intern();
/* 6194 */     String[] arrayOfString = this.osql.getParameterList();
/* 6195 */     int i = 0;
/* 6196 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6198 */     for (int k = 0; k < j; k++) {
/* 6199 */       if (arrayOfString[k] == str)
/*      */       {
/* 6201 */         setShort(k + 1, paramShort);
/*      */         
/* 6203 */         i = 1;
/*      */       }
/*      */     }
/* 6206 */     if (i == 0)
/*      */     {
/* 6208 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6209 */       localSQLException.fillInStackTrace();
/* 6210 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 6222 */     String str = paramString.intern();
/* 6223 */     String[] arrayOfString = this.osql.getParameterList();
/* 6224 */     int i = 0;
/* 6225 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6227 */     for (int k = 0; k < j; k++) {
/* 6228 */       if (arrayOfString[k] == str)
/*      */       {
/* 6230 */         setSQLXML(k + 1, paramSQLXML);
/*      */         
/* 6232 */         i = 1;
/*      */       }
/*      */     }
/* 6235 */     if (i == 0)
/*      */     {
/* 6237 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6238 */       localSQLException.fillInStackTrace();
/* 6239 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 6251 */     String str = paramString1.intern();
/* 6252 */     String[] arrayOfString = this.osql.getParameterList();
/* 6253 */     int i = 0;
/* 6254 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6256 */     for (int k = 0; k < j; k++) {
/* 6257 */       if (arrayOfString[k] == str)
/*      */       {
/* 6259 */         setString(k + 1, paramString2);
/*      */         
/* 6261 */         i = 1;
/*      */       }
/*      */     }
/* 6264 */     if (i == 0)
/*      */     {
/* 6266 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 6267 */       localSQLException.fillInStackTrace();
/* 6268 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime)
/*      */     throws SQLException
/*      */   {
/* 6280 */     String str = paramString.intern();
/* 6281 */     String[] arrayOfString = this.osql.getParameterList();
/* 6282 */     int i = 0;
/* 6283 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6285 */     for (int k = 0; k < j; k++) {
/* 6286 */       if (arrayOfString[k] == str)
/*      */       {
/* 6288 */         setTime(k + 1, paramTime);
/*      */         
/* 6290 */         i = 1;
/*      */       }
/*      */     }
/* 6293 */     if (i == 0)
/*      */     {
/* 6295 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6296 */       localSQLException.fillInStackTrace();
/* 6297 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTime(String paramString, Time paramTime, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 6309 */     String str = paramString.intern();
/* 6310 */     String[] arrayOfString = this.osql.getParameterList();
/* 6311 */     int i = 0;
/* 6312 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6314 */     for (int k = 0; k < j; k++) {
/* 6315 */       if (arrayOfString[k] == str)
/*      */       {
/* 6317 */         setTime(k + 1, paramTime, paramCalendar);
/*      */         
/* 6319 */         i = 1;
/*      */       }
/*      */     }
/* 6322 */     if (i == 0)
/*      */     {
/* 6324 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6325 */       localSQLException.fillInStackTrace();
/* 6326 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp)
/*      */     throws SQLException
/*      */   {
/* 6338 */     String str = paramString.intern();
/* 6339 */     String[] arrayOfString = this.osql.getParameterList();
/* 6340 */     int i = 0;
/* 6341 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6343 */     for (int k = 0; k < j; k++) {
/* 6344 */       if (arrayOfString[k] == str)
/*      */       {
/* 6346 */         setTimestamp(k + 1, paramTimestamp);
/*      */         
/* 6348 */         i = 1;
/*      */       }
/*      */     }
/* 6351 */     if (i == 0)
/*      */     {
/* 6353 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6354 */       localSQLException.fillInStackTrace();
/* 6355 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimestamp(String paramString, Timestamp paramTimestamp, Calendar paramCalendar)
/*      */     throws SQLException
/*      */   {
/* 6367 */     String str = paramString.intern();
/* 6368 */     String[] arrayOfString = this.osql.getParameterList();
/* 6369 */     int i = 0;
/* 6370 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6372 */     for (int k = 0; k < j; k++) {
/* 6373 */       if (arrayOfString[k] == str)
/*      */       {
/* 6375 */         setTimestamp(k + 1, paramTimestamp, paramCalendar);
/*      */         
/* 6377 */         i = 1;
/*      */       }
/*      */     }
/* 6380 */     if (i == 0)
/*      */     {
/* 6382 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6383 */       localSQLException.fillInStackTrace();
/* 6384 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURL(String paramString, URL paramURL)
/*      */     throws SQLException
/*      */   {
/* 6396 */     String str = paramString.intern();
/* 6397 */     String[] arrayOfString = this.osql.getParameterList();
/* 6398 */     int i = 0;
/* 6399 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6401 */     for (int k = 0; k < j; k++) {
/* 6402 */       if (arrayOfString[k] == str)
/*      */       {
/* 6404 */         setURL(k + 1, paramURL);
/*      */         
/* 6406 */         i = 1;
/*      */       }
/*      */     }
/* 6409 */     if (i == 0)
/*      */     {
/* 6411 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6412 */       localSQLException.fillInStackTrace();
/* 6413 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6425 */     String str = paramString.intern();
/* 6426 */     String[] arrayOfString = this.osql.getParameterList();
/* 6427 */     int i = 0;
/* 6428 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6430 */     for (int k = 0; k < j; k++) {
/* 6431 */       if (arrayOfString[k] == str)
/*      */       {
/* 6433 */         setBlob(k + 1, paramInputStream);
/*      */         
/* 6435 */         i = 1;
/*      */       }
/*      */     }
/* 6438 */     if (i == 0)
/*      */     {
/* 6440 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6441 */       localSQLException.fillInStackTrace();
/* 6442 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6454 */     String str = paramString.intern();
/* 6455 */     String[] arrayOfString = this.osql.getParameterList();
/* 6456 */     int i = 0;
/* 6457 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6459 */     for (int k = 0; k < j; k++) {
/* 6460 */       if (arrayOfString[k] == str)
/*      */       {
/* 6462 */         setBlob(k + 1, paramInputStream, paramLong);
/*      */         
/* 6464 */         i = 1;
/*      */       }
/*      */     }
/* 6467 */     if (i == 0)
/*      */     {
/* 6469 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6470 */       localSQLException.fillInStackTrace();
/* 6471 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6483 */     String str = paramString.intern();
/* 6484 */     String[] arrayOfString = this.osql.getParameterList();
/* 6485 */     int i = 0;
/* 6486 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6488 */     for (int k = 0; k < j; k++) {
/* 6489 */       if (arrayOfString[k] == str)
/*      */       {
/* 6491 */         setClob(k + 1, paramReader);
/*      */         
/* 6493 */         i = 1;
/*      */       }
/*      */     }
/* 6496 */     if (i == 0)
/*      */     {
/* 6498 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6499 */       localSQLException.fillInStackTrace();
/* 6500 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6512 */     String str = paramString.intern();
/* 6513 */     String[] arrayOfString = this.osql.getParameterList();
/* 6514 */     int i = 0;
/* 6515 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6517 */     for (int k = 0; k < j; k++) {
/* 6518 */       if (arrayOfString[k] == str)
/*      */       {
/* 6520 */         setClob(k + 1, paramReader, paramLong);
/*      */         
/* 6522 */         i = 1;
/*      */       }
/*      */     }
/* 6525 */     if (i == 0)
/*      */     {
/* 6527 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6528 */       localSQLException.fillInStackTrace();
/* 6529 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6541 */     String str = paramString.intern();
/* 6542 */     String[] arrayOfString = this.osql.getParameterList();
/* 6543 */     int i = 0;
/* 6544 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6546 */     for (int k = 0; k < j; k++) {
/* 6547 */       if (arrayOfString[k] == str)
/*      */       {
/* 6549 */         setNClob(k + 1, paramReader);
/*      */         
/* 6551 */         i = 1;
/*      */       }
/*      */     }
/* 6554 */     if (i == 0)
/*      */     {
/* 6556 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6557 */       localSQLException.fillInStackTrace();
/* 6558 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6570 */     String str = paramString.intern();
/* 6571 */     String[] arrayOfString = this.osql.getParameterList();
/* 6572 */     int i = 0;
/* 6573 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6575 */     for (int k = 0; k < j; k++) {
/* 6576 */       if (arrayOfString[k] == str)
/*      */       {
/* 6578 */         setNClob(k + 1, paramReader, paramLong);
/*      */         
/* 6580 */         i = 1;
/*      */       }
/*      */     }
/* 6583 */     if (i == 0)
/*      */     {
/* 6585 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6586 */       localSQLException.fillInStackTrace();
/* 6587 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6599 */     String str = paramString.intern();
/* 6600 */     String[] arrayOfString = this.osql.getParameterList();
/* 6601 */     int i = 0;
/* 6602 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6604 */     for (int k = 0; k < j; k++) {
/* 6605 */       if (arrayOfString[k] == str)
/*      */       {
/* 6607 */         setAsciiStream(k + 1, paramInputStream);
/*      */         
/* 6609 */         i = 1;
/*      */       }
/*      */     }
/* 6612 */     if (i == 0)
/*      */     {
/* 6614 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6615 */       localSQLException.fillInStackTrace();
/* 6616 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6628 */     String str = paramString.intern();
/* 6629 */     String[] arrayOfString = this.osql.getParameterList();
/* 6630 */     int i = 0;
/* 6631 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6633 */     for (int k = 0; k < j; k++) {
/* 6634 */       if (arrayOfString[k] == str)
/*      */       {
/* 6636 */         setAsciiStream(k + 1, paramInputStream, paramInt);
/*      */         
/* 6638 */         i = 1;
/*      */       }
/*      */     }
/* 6641 */     if (i == 0)
/*      */     {
/* 6643 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6644 */       localSQLException.fillInStackTrace();
/* 6645 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6657 */     String str = paramString.intern();
/* 6658 */     String[] arrayOfString = this.osql.getParameterList();
/* 6659 */     int i = 0;
/* 6660 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6662 */     for (int k = 0; k < j; k++) {
/* 6663 */       if (arrayOfString[k] == str)
/*      */       {
/* 6665 */         setAsciiStream(k + 1, paramInputStream, paramLong);
/*      */         
/* 6667 */         i = 1;
/*      */       }
/*      */     }
/* 6670 */     if (i == 0)
/*      */     {
/* 6672 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6673 */       localSQLException.fillInStackTrace();
/* 6674 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 6686 */     String str = paramString.intern();
/* 6687 */     String[] arrayOfString = this.osql.getParameterList();
/* 6688 */     int i = 0;
/* 6689 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6691 */     for (int k = 0; k < j; k++) {
/* 6692 */       if (arrayOfString[k] == str)
/*      */       {
/* 6694 */         setBinaryStream(k + 1, paramInputStream);
/*      */         
/* 6696 */         i = 1;
/*      */       }
/*      */     }
/* 6699 */     if (i == 0)
/*      */     {
/* 6701 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6702 */       localSQLException.fillInStackTrace();
/* 6703 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6715 */     String str = paramString.intern();
/* 6716 */     String[] arrayOfString = this.osql.getParameterList();
/* 6717 */     int i = 0;
/* 6718 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6720 */     for (int k = 0; k < j; k++) {
/* 6721 */       if (arrayOfString[k] == str)
/*      */       {
/* 6723 */         setBinaryStream(k + 1, paramInputStream, paramInt);
/*      */         
/* 6725 */         i = 1;
/*      */       }
/*      */     }
/* 6728 */     if (i == 0)
/*      */     {
/* 6730 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6731 */       localSQLException.fillInStackTrace();
/* 6732 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6744 */     String str = paramString.intern();
/* 6745 */     String[] arrayOfString = this.osql.getParameterList();
/* 6746 */     int i = 0;
/* 6747 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6749 */     for (int k = 0; k < j; k++) {
/* 6750 */       if (arrayOfString[k] == str)
/*      */       {
/* 6752 */         setBinaryStream(k + 1, paramInputStream, paramLong);
/*      */         
/* 6754 */         i = 1;
/*      */       }
/*      */     }
/* 6757 */     if (i == 0)
/*      */     {
/* 6759 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6760 */       localSQLException.fillInStackTrace();
/* 6761 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6773 */     String str = paramString.intern();
/* 6774 */     String[] arrayOfString = this.osql.getParameterList();
/* 6775 */     int i = 0;
/* 6776 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6778 */     for (int k = 0; k < j; k++) {
/* 6779 */       if (arrayOfString[k] == str)
/*      */       {
/* 6781 */         setCharacterStream(k + 1, paramReader);
/*      */         
/* 6783 */         i = 1;
/*      */       }
/*      */     }
/* 6786 */     if (i == 0)
/*      */     {
/* 6788 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6789 */       localSQLException.fillInStackTrace();
/* 6790 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6802 */     String str = paramString.intern();
/* 6803 */     String[] arrayOfString = this.osql.getParameterList();
/* 6804 */     int i = 0;
/* 6805 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6807 */     for (int k = 0; k < j; k++) {
/* 6808 */       if (arrayOfString[k] == str)
/*      */       {
/* 6810 */         setCharacterStream(k + 1, paramReader, paramInt);
/*      */         
/* 6812 */         i = 1;
/*      */       }
/*      */     }
/* 6815 */     if (i == 0)
/*      */     {
/* 6817 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6818 */       localSQLException.fillInStackTrace();
/* 6819 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6831 */     String str = paramString.intern();
/* 6832 */     String[] arrayOfString = this.osql.getParameterList();
/* 6833 */     int i = 0;
/* 6834 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6836 */     for (int k = 0; k < j; k++) {
/* 6837 */       if (arrayOfString[k] == str)
/*      */       {
/* 6839 */         setCharacterStream(k + 1, paramReader, paramLong);
/*      */         
/* 6841 */         i = 1;
/*      */       }
/*      */     }
/* 6844 */     if (i == 0)
/*      */     {
/* 6846 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6847 */       localSQLException.fillInStackTrace();
/* 6848 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 6860 */     String str = paramString.intern();
/* 6861 */     String[] arrayOfString = this.osql.getParameterList();
/* 6862 */     int i = 0;
/* 6863 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6865 */     for (int k = 0; k < j; k++) {
/* 6866 */       if (arrayOfString[k] == str)
/*      */       {
/* 6868 */         setNCharacterStream(k + 1, paramReader);
/*      */         
/* 6870 */         i = 1;
/*      */       }
/*      */     }
/* 6873 */     if (i == 0)
/*      */     {
/* 6875 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6876 */       localSQLException.fillInStackTrace();
/* 6877 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 6889 */     String str = paramString.intern();
/* 6890 */     String[] arrayOfString = this.osql.getParameterList();
/* 6891 */     int i = 0;
/* 6892 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6894 */     for (int k = 0; k < j; k++) {
/* 6895 */       if (arrayOfString[k] == str)
/*      */       {
/* 6897 */         setNCharacterStream(k + 1, paramReader, paramLong);
/*      */         
/* 6899 */         i = 1;
/*      */       }
/*      */     }
/* 6902 */     if (i == 0)
/*      */     {
/* 6904 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6905 */       localSQLException.fillInStackTrace();
/* 6906 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnicodeStream(String paramString, InputStream paramInputStream, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6918 */     String str = paramString.intern();
/* 6919 */     String[] arrayOfString = this.osql.getParameterList();
/* 6920 */     int i = 0;
/* 6921 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6923 */     for (int k = 0; k < j; k++) {
/* 6924 */       if (arrayOfString[k] == str)
/*      */       {
/* 6926 */         setUnicodeStream(k + 1, paramInputStream, paramInt);
/*      */         
/* 6928 */         i = 1;
/*      */       }
/*      */     }
/* 6931 */     if (i == 0)
/*      */     {
/* 6933 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6934 */       localSQLException.fillInStackTrace();
/* 6935 */       throw localSQLException;
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
/*      */   public void setNull(String paramString, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 6954 */     String str = paramString.intern();
/* 6955 */     String[] arrayOfString = this.osql.getParameterList();
/* 6956 */     int i = 0;
/* 6957 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6959 */     for (int k = 0; k < j; k++) {
/* 6960 */       if (arrayOfString[k] == str)
/*      */       {
/* 6962 */         setNull(k + 1, paramInt);
/*      */         
/* 6964 */         i = 1;
/*      */       }
/*      */     }
/* 6967 */     if (i == 0)
/*      */     {
/* 6969 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 6970 */       localSQLException.fillInStackTrace();
/* 6971 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNull(String paramString1, int paramInt, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 6983 */     String str = paramString1.intern();
/* 6984 */     String[] arrayOfString = this.osql.getParameterList();
/* 6985 */     int i = 0;
/* 6986 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 6988 */     for (int k = 0; k < j; k++) {
/* 6989 */       if (arrayOfString[k] == str)
/*      */       {
/* 6991 */         setNull(k + 1, paramInt, paramString2);
/*      */         
/* 6993 */         i = 1;
/*      */       }
/*      */     }
/* 6996 */     if (i == 0)
/*      */     {
/* 6998 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString1);
/* 6999 */       localSQLException.fillInStackTrace();
/* 7000 */       throw localSQLException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setObject(String paramString, Object paramObject, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 7013 */     String str = paramString.intern();
/* 7014 */     String[] arrayOfString = this.osql.getParameterList();
/* 7015 */     int i = 0;
/* 7016 */     int j = Math.min(this.osql.getParameterCount(), arrayOfString.length);
/*      */     
/* 7018 */     for (int k = 0; k < j; k++) {
/* 7019 */       if (arrayOfString[k] == str)
/*      */       {
/* 7021 */         setObject(k + 1, paramObject, paramInt1, paramInt2);
/*      */         
/* 7023 */         i = 1;
/*      */       }
/*      */     }
/* 7026 */     if (i == 0)
/*      */     {
/* 7028 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 147, paramString);
/* 7029 */       localSQLException.fillInStackTrace();
/* 7030 */       throw localSQLException;
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
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7045 */     updateAsciiStream(paramInt, paramInputStream, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7054 */     updateAsciiStream(paramInt, paramInputStream, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7063 */     updateAsciiStream(getColumnIndex(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateAsciiStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7073 */     updateAsciiStream(getColumnIndex(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7083 */     updateBinaryStream(paramInt, paramInputStream, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7092 */     updateBinaryStream(paramInt, paramInputStream, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7101 */     updateBinaryStream(getColumnIndex(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBinaryStream(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7110 */     updateBinaryStream(getColumnIndex(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7119 */     updateBinaryStream(paramInt, paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(int paramInt, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7129 */     updateBinaryStream(paramInt, paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(String paramString, InputStream paramInputStream)
/*      */     throws SQLException
/*      */   {
/* 7139 */     updateBlob(getColumnIndex(paramString), paramInputStream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateBlob(String paramString, InputStream paramInputStream, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7149 */     updateBlob(getColumnIndex(paramString), paramInputStream, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7159 */     updateCharacterStream(paramInt, paramReader, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7169 */     updateCharacterStream(paramInt, paramReader, (int)paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7179 */     updateCharacterStream(getColumnIndex(paramString), paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7189 */     updateCharacterStream(getColumnIndex(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7199 */     updateCharacterStream(paramInt, paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7209 */     updateCharacterStream(paramInt, paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7219 */     updateClob(getColumnIndex(paramString), paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7229 */     updateClob(getColumnIndex(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7239 */     updateNCharacterStream(paramInt, paramReader, 2147483647L);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7249 */     updateCharacterStreamInternal(paramInt, paramReader, (int)paramLong, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7259 */     updateNCharacterStream(getColumnIndex(paramString), paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNCharacterStream(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7269 */     updateNCharacterStream(getColumnIndex(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 7279 */     updateObject(paramInt, new OracleSerialClob(paramNClob));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7289 */     updateClob(paramInt, new OracleSerialClob(paramReader));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(int paramInt, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7299 */     updateClob(paramInt, new OracleSerialClob(paramReader, paramLong));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, NClob paramNClob)
/*      */     throws SQLException
/*      */   {
/* 7309 */     updateNClob(getColumnIndex(paramString), paramNClob);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, Reader paramReader)
/*      */     throws SQLException
/*      */   {
/* 7319 */     updateNClob(getColumnIndex(paramString), paramReader);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNClob(String paramString, Reader paramReader, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 7329 */     updateNClob(getColumnIndex(paramString), paramReader, paramLong);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNString(int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 7339 */     updateObject(paramInt, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateNString(String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 7349 */     updateNString(getColumnIndex(paramString1), paramString2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRowId(int paramInt, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 7359 */     updateObject(paramInt, paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateRowId(String paramString, RowId paramRowId)
/*      */     throws SQLException
/*      */   {
/* 7369 */     updateRowId(getColumnIndex(paramString), paramRowId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateSQLXML(int paramInt, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 7379 */     updateObject(paramInt, paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateSQLXML(String paramString, SQLXML paramSQLXML)
/*      */     throws SQLException
/*      */   {
/* 7389 */     updateSQLXML(getColumnIndex(paramString), paramSQLXML);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 7397 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleCachedRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */