/*      */ package oracle.jdbc.replay.driver;
/*      */ 
/*      */ import java.lang.reflect.Method;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Connection;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ParameterMetaData;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.Ref;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLRecoverableException;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Statement;
/*      */ import java.sql.Struct;
/*      */ import java.util.EnumSet;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.internal.OracleConnection.TransactionState;
/*      */ import oracle.jdbc.internal.OracleStatement.SqlKind;
/*      */ import oracle.jdbc.proxy.annotation.GetCreator;
/*      */ import oracle.jdbc.proxy.annotation.GetDelegate;
/*      */ import oracle.jdbc.proxy.annotation.Methods;
/*      */ import oracle.jdbc.proxy.annotation.OnError;
/*      */ import oracle.jdbc.proxy.annotation.Post;
/*      */ import oracle.jdbc.proxy.annotation.Pre;
/*      */ import oracle.jdbc.proxy.annotation.ProxyFor;
/*      */ import oracle.jdbc.proxy.annotation.ProxyResult;
/*      */ import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
/*      */ import oracle.jdbc.proxy.annotation.SetDelegate;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @ProxyFor({CallableStatement.class, PreparedStatement.class, Statement.class, oracle.jdbc.OracleCallableStatement.class, oracle.jdbc.OraclePreparedStatement.class, oracle.jdbc.OracleStatement.class, oracle.jdbc.internal.OracleCallableStatement.class, oracle.jdbc.internal.OraclePreparedStatement.class, oracle.jdbc.internal.OracleStatement.class})
/*      */ public abstract class NonTxnReplayableStatement
/*      */   extends NonTxnReplayableBase
/*      */   implements Replayable
/*      */ {
/*      */   private static final String STMT_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableStatement";
/*   83 */   private static Logger STMT_REPLAY_LOGGER = null;
/*      */   
/*      */   static
/*      */   {
/*   87 */     if (STMT_REPLAY_LOGGER == null) {
/*   88 */       STMT_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableStatement");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Pre
/*      */   protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*      */   {
/*  100 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Pre
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="execute", args={}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int[].class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, String[].class})})
/*      */   protected void preForExecute(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*      */   {
/*  115 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*  117 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  118 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */     try
/*      */     {
/*  124 */       oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  125 */       OracleStatement.SqlKind localSqlKind = localOracleStatement.getSqlKind();
/*  126 */       STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, original SqlKind: {1}", new Object[] { this, localSqlKind });
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  134 */       if ((localSqlKind.isSELECT()) || (localSqlKind.isPlsqlOrCall()) || (localSqlKind == OracleStatement.SqlKind.ALTER_SESSION) || (localSqlKind == OracleStatement.SqlKind.UNINITIALIZED))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  141 */         preForAll(paramMethod, paramObject, paramVarArgs);
/*      */ 
/*      */ 
/*      */       }
/*  145 */       else if (localSqlKind.isDML())
/*      */       {
/*  147 */         if (this.failoverMngr != null) {
/*  148 */           this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  154 */           STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*  160 */       else if (localSqlKind.isOTHER())
/*      */       {
/*  162 */         if (this.failoverMngr != null) {
/*  163 */           this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  169 */           STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  175 */       STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, could not get original SqlKind: {1}", new Object[] { this, localSQLException });
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
/*      */   @Pre
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, int[].class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, String[].class})})
/*      */   protected void preForExecuteUpdate(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*      */   {
/*  193 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*  195 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  196 */       return;
/*      */     }
/*  198 */     STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, preForExecuteUpdate", this);
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  204 */       oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  205 */       OracleStatement.SqlKind localSqlKind = localOracleStatement.getSqlKind();
/*  206 */       STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, original SqlKind: {1}", new Object[] { this, localSqlKind });
/*      */       
/*      */ 
/*      */ 
/*  210 */       if (localSqlKind.isPlsqlOrCall())
/*      */       {
/*  212 */         preForAll(paramMethod, paramObject, paramVarArgs);
/*      */       }
/*  214 */       else if (this.failoverMngr != null) {
/*  215 */         this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  221 */         STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  226 */       STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, could not get original SqlKind: {1}", new Object[] { this, localSQLException });
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Pre
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="executeBatch", args={})})
/*      */   protected void preForExecuteBatch(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*      */   {
/*  238 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*  240 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  241 */       return;
/*      */     }
/*  243 */     STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, preForExecuteBatch", this);
/*      */     
/*      */ 
/*      */ 
/*  247 */     if (this.failoverMngr != null) {
/*  248 */       this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  254 */       STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Pre
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={String.class, java.io.Reader.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={String.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStream", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStream", args={String.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={int.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={int.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStream", args={int.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={int.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={int.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStream", args={int.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={int.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={int.class, java.io.Reader.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStream", args={int.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStream", args={int.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStream", args={int.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStreamAtName", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStreamAtName", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setAsciiStreamAtName", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStreamAtName", args={String.class, java.io.InputStream.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStreamAtName", args={String.class, java.io.InputStream.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setBinaryStreamAtName", args={String.class, java.io.InputStream.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStreamAtName", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStreamAtName", args={String.class, java.io.Reader.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="setCharacterStreamAtName", args={String.class, java.io.Reader.class, long.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStreamAtName", args={String.class, java.io.Reader.class}), @oracle.jdbc.proxy.annotation.Signature(name="setNCharacterStreamAtName", args={String.class, java.io.Reader.class, long.class})})
/*      */   protected void preForSetStreams(Method paramMethod, Object paramObject, Object... paramVarArgs)
/*      */   {
/*  336 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*  338 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/*  339 */       return;
/*      */     }
/*  341 */     STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, preForSetStreams", this);
/*      */     
/*      */ 
/*      */ 
/*  345 */     if (this.failoverMngr != null) {
/*  346 */       this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  352 */       STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   @Post
/*      */   protected Object postForAll(Method paramMethod, Object paramObject)
/*      */   {
/*  360 */     return super.postForAll(paramMethod, paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Post
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="execute", args={}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int[].class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, String[].class})})
/*      */   protected boolean postForExecute(Method paramMethod, boolean paramBoolean)
/*      */   {
/*  374 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*      */ 
/*  377 */     switch (localReplayLifecycle)
/*      */     {
/*      */ 
/*      */ 
/*      */     case ENABLED_NOT_REPLAYING: 
/*      */     case REPLAYING_LASTCALL: 
/*  383 */       doPostWhenRecordingExecutes(paramMethod, Boolean.valueOf(paramBoolean), null);
/*  384 */       break;
/*      */     
/*      */ 
/*      */     case INTERNALLY_FAILED: 
/*      */     case ALWAYS_DISABLED: 
/*      */     case INTERNALLY_DISABLED: 
/*      */     case EXTERNALLY_DISABLED: 
/*      */     case REPLAYING_CALLBACK: 
/*      */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case REPLAYING: 
/*  397 */       doPostWhenReplayingExecutes(paramMethod, Boolean.valueOf(paramBoolean), null);
/*      */     }
/*      */     
/*  400 */     return paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Post
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, int[].class}), @oracle.jdbc.proxy.annotation.Signature(name="executeUpdate", args={String.class, String[].class})})
/*      */   protected int postForExecuteUpdate(Method paramMethod, int paramInt)
/*      */   {
/*  414 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*      */ 
/*  417 */     switch (localReplayLifecycle)
/*      */     {
/*      */ 
/*      */ 
/*      */     case ENABLED_NOT_REPLAYING: 
/*      */     case REPLAYING_LASTCALL: 
/*  423 */       doPostWhenRecordingExecutes(paramMethod, Integer.valueOf(paramInt), null);
/*  424 */       break;
/*      */     
/*      */ 
/*      */     case INTERNALLY_FAILED: 
/*      */     case ALWAYS_DISABLED: 
/*      */     case INTERNALLY_DISABLED: 
/*      */     case EXTERNALLY_DISABLED: 
/*      */     case REPLAYING_CALLBACK: 
/*      */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case REPLAYING: 
/*  437 */       doPostWhenReplayingExecutes(paramMethod, Integer.valueOf(paramInt), null);
/*      */     }
/*      */     
/*  440 */     return paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @Post
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="executeQuery", args={}), @oracle.jdbc.proxy.annotation.Signature(name="executeQuery", args={String.class})})
/*      */   protected ResultSet postForExecuteQuery(Method paramMethod, ResultSet paramResultSet)
/*      */   {
/*  452 */     if ((paramResultSet instanceof NonTxnReplayableBase))
/*      */     {
/*  454 */       localObject = (NonTxnReplayableBase)paramResultSet;
/*  455 */       ((NonTxnReplayableBase)localObject).setFailoverManager(getFailoverManager());
/*      */     }
/*      */     
/*  458 */     Object localObject = this.failoverMngr.getReplayLifecycle();
/*      */     
/*      */ 
/*  461 */     switch (localObject)
/*      */     {
/*      */ 
/*      */ 
/*      */     case ENABLED_NOT_REPLAYING: 
/*      */     case REPLAYING_LASTCALL: 
/*  467 */       doPostWhenRecordingExecutes(paramMethod, paramResultSet, null);
/*  468 */       break;
/*      */     
/*      */ 
/*      */     case INTERNALLY_FAILED: 
/*      */     case ALWAYS_DISABLED: 
/*      */     case INTERNALLY_DISABLED: 
/*      */     case EXTERNALLY_DISABLED: 
/*      */     case REPLAYING_CALLBACK: 
/*      */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case REPLAYING: 
/*  481 */       doPostWhenReplayingExecutes(paramMethod, paramResultSet, null);
/*      */     }
/*      */     
/*  484 */     return paramResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */   @Post
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="close", args={})})
/*      */   protected void postForClose(Method paramMethod)
/*      */   {
/*  492 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*      */ 
/*  495 */     switch (localReplayLifecycle)
/*      */     {
/*      */ 
/*      */     case ENABLED_NOT_REPLAYING: 
/*  499 */       doPostWhenRecordingClose(paramMethod, null);
/*  500 */       break;
/*      */     case INTERNALLY_FAILED: 
/*      */     case ALWAYS_DISABLED: 
/*      */     case INTERNALLY_DISABLED: 
/*      */     case EXTERNALLY_DISABLED: 
/*      */     case REPLAYING_CALLBACK: 
/*      */       break;
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
/*  517 */     this.isClosedAndNoReplay = true;
/*      */   }
/*      */   
/*      */   @OnError(SQLException.class)
/*      */   protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
/*      */     throws SQLException
/*      */   {
/*  524 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
/*      */   }
/*      */   
/*      */   @OnError(SQLException.class)
/*      */   protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
/*      */     throws SQLException
/*      */   {
/*  531 */     return super.onErrorForAll(paramMethod, paramSQLException);
/*      */   }
/*      */   
/*      */ 
/*      */   @OnError(SQLException.class)
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="close", args={})})
/*      */   protected void onErrorVoidForClose(Method paramMethod, SQLException paramSQLException)
/*      */     throws SQLException
/*      */   {
/*  540 */     if ((paramSQLException instanceof SQLRecoverableException)) {
/*  541 */       return;
/*      */     }
/*  543 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   @OnError(SQLException.class)
/*      */   @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="execute", args={}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int.class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, int[].class}), @oracle.jdbc.proxy.annotation.Signature(name="execute", args={String.class, String[].class})})
/*      */   protected boolean onErrorForExecute(Method paramMethod, SQLException paramSQLException)
/*      */     throws SQLException
/*      */   {
/*  559 */     if (this.isClosedAndNoReplay) {
/*  560 */       throw paramSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  569 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */     
/*  571 */     if (((paramSQLException instanceof SQLRecoverableException)) && (localReplayLifecycle == FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/*  577 */         oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  578 */         OracleStatement.SqlKind localSqlKind = localOracleStatement.getSqlKind();
/*  579 */         STMT_REPLAY_LOGGER.log(Level.FINEST, "On statement {0}, original SqlKind: {1}", new Object[] { this, localSqlKind });
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  584 */         if (localSqlKind.isPlsqlOrCall())
/*      */         {
/*  586 */           STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, last call is PL/SQL", this);
/*      */           
/*      */ 
/*  589 */           this.failoverMngr.disableReplayInternal(paramMethod, 373, "Replay disabled because outage occurred during PL/SQL execution", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*  597 */         else if (localSqlKind.isDML())
/*      */         {
/*  599 */           if (this.failoverMngr != null) {
/*  600 */             this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*  606 */             STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */ 
/*      */           }
/*      */           
/*      */ 
/*      */         }
/*  612 */         else if ((localSqlKind.isOTHER()) && (localSqlKind != OracleStatement.SqlKind.ALTER_SESSION))
/*      */         {
/*      */ 
/*  615 */           if (this.failoverMngr != null) {
/*  616 */             this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*  622 */             STMT_REPLAY_LOGGER.log(Level.SEVERE, "On statement {0}, failover manager not set", this);
/*      */           }
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  628 */         STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, could not get original SqlKind: {1}", new Object[] { this, localSQLException });
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  633 */     return ((Boolean)super.onErrorForAll(paramMethod, paramSQLException)).booleanValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void fillInChecksum(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry)
/*      */     throws SQLException
/*      */   {
/*  655 */     oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*      */     
/*      */ 
/*  658 */     long l = localOracleStatement.getChecksum();
/*  659 */     String str = (paramCallHistoryEntry == null) || (paramCallHistoryEntry.method == null) ? "NULL METHOD" : paramCallHistoryEntry.method.getName();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  664 */     STMT_REPLAY_LOGGER.log(Level.FINEST, "On proxy {0}, method {1}, filling in checksum: {2}", new Object[] { paramCallHistoryEntry.jdbcProxy, str, Long.valueOf(l) });
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  669 */     this.failoverMngr.update(this, paramCallHistoryEntry, paramCallHistoryEntry.result, paramCallHistoryEntry.callStatus, l, paramCallHistoryEntry.scn, paramCallHistoryEntry.callException);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object replayOneCall(FailoverManagerImpl.CallHistoryEntry paramCallHistoryEntry, SQLRecoverableException paramSQLRecoverableException)
/*      */     throws SQLException
/*      */   {
/*  682 */     if (paramCallHistoryEntry.scn != -1L)
/*      */     {
/*  684 */       oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  685 */       localOracleStatement.setSnapshotSCN(paramCallHistoryEntry.scn);
/*      */       
/*  687 */       String str = (paramCallHistoryEntry == null) || (paramCallHistoryEntry.method == null) ? "NULL METHOD" : paramCallHistoryEntry.method.getName();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  692 */       STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, replaying method {1}, set original SCN: {2}", new Object[] { paramCallHistoryEntry.jdbcProxy, str, Long.valueOf(paramCallHistoryEntry.scn) });
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  697 */     return super.replayOneCall(paramCallHistoryEntry, paramSQLRecoverableException);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doPostWhenReplaying(Method paramMethod, Object paramObject, SQLException paramSQLException)
/*      */   {
/*      */     try
/*      */     {
/*  708 */       FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */       
/*  710 */       switch (localReplayLifecycle)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case REPLAYING: 
/*  720 */         if (this.replayingCallEntry.checksum != 0L)
/*      */         {
/*  722 */           oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  727 */           long l = localOracleStatement.getChecksum();
/*  728 */           String str = (this.replayingCallEntry == null) || (this.replayingCallEntry.method == null) ? "NULL METHOD" : this.replayingCallEntry.method.getName();
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  733 */           STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, replaying method {1}, new checksum: {2}, original checksum: {3}", new Object[] { this.replayingCallEntry.jdbcProxy, str, Long.valueOf(l), Long.valueOf(this.replayingCallEntry.checksum) });
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  741 */           if (this.replayingCallEntry.checksum != l)
/*      */           {
/*      */ 
/*  744 */             this.failoverMngr.disableReplayAndThrowException(this.replayingCallEntry.method, 386, "Replay failed because of checksum mismatch", this.originalError);
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  759 */       STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, doPostWhenReplaying exception: {1}", new Object[] { this, localSQLException });
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doPostWhenReplayingExecutes(Method paramMethod, Object paramObject, SQLException paramSQLException)
/*      */   {
/*      */     try
/*      */     {
/*  773 */       FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
/*      */       
/*  775 */       switch (localReplayLifecycle)
/*      */       {
/*      */ 
/*      */       case REPLAYING: 
/*  779 */         doPostWhenReplaying(paramMethod, paramObject, paramSQLException);
/*  780 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       case REPLAYING_LASTCALL: 
/*  785 */         oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  786 */         OracleStatement.SqlKind localSqlKind = localOracleStatement.getSqlKind();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  791 */         if (localSqlKind.isPlsqlOrCall())
/*      */         {
/*  793 */           STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, last call is PL/SQL", this.replayingCallEntry.jdbcProxy);
/*      */           
/*      */ 
/*      */ 
/*  797 */           this.failoverMngr.disableReplayInternal(this.replayingCallEntry.method, 373, "Replay disabled because outage occurred during PL/SQL execution", this.originalError);
/*      */         }
/*      */         
/*      */ 
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  807 */       STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, doPostWhenReplayingExecutes exception: {1}", new Object[] { this, localSQLException });
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doPostWhenRecordingClose(Method paramMethod, SQLException paramSQLException)
/*      */   {
/*  875 */     if (okToPurgeSameProxyList()) {
/*  876 */       purgeSameProxyList();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void doPostWhenRecordingExecutes(Method paramMethod, Object paramObject, SQLException paramSQLException)
/*      */   {
/*      */     try
/*      */     {
/*  885 */       oracle.jdbc.internal.OracleStatement localOracleStatement = (oracle.jdbc.internal.OracleStatement)getDelegate();
/*  886 */       OracleConnection localOracleConnection = (OracleConnection)localOracleStatement.getConnection();
/*  887 */       OracleStatement.SqlKind localSqlKind = localOracleStatement.getSqlKind();
/*      */       
/*  889 */       long l1 = 0L;
/*  890 */       long l2 = -1L;
/*  891 */       String str = paramMethod == null ? "NULL METHOD" : paramMethod.getName();
/*      */       
/*      */ 
/*      */ 
/*  895 */       if ((localSqlKind.isSELECT()) || (localSqlKind.isPlsqlOrCall()) || (localSqlKind == OracleStatement.SqlKind.ALTER_SESSION))
/*      */       {
/*      */ 
/*      */ 
/*  899 */         EnumSet localEnumSet = localOracleConnection.getTransactionState();
/*      */         
/*      */ 
/*  902 */         STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, method {1}, transaction state: {2}", new Object[] { this, str, localEnumSet });
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  914 */         if ((localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_INTENTION)) || ((localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_STARTED)) && (!localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_READONLY))))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  921 */           this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of transaction", null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  934 */           l1 = 0L;
/*      */           
/*      */ 
/*  937 */           if ((!this.failoverMngr.isReplayInCurrentMode()) && (localSqlKind.isPlsqlOrCall()))
/*      */           {
/*  939 */             this.failoverMngr.setReplayInCurrentMode();
/*      */           }
/*      */           
/*  942 */           l2 = this.failoverMngr.isReplayInCurrentMode() ? -1L : localOracleConnection.getCurrentSCN();
/*      */           
/*      */ 
/*      */ 
/*  946 */           STMT_REPLAY_LOGGER.log(Level.FINER, "On proxy {0}, method {1}, SCN to record: {2}", new Object[] { this, str, Long.valueOf(l2) });
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  951 */         if (this.failoverMngr.getReplayLifecycle() == FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  956 */           this.failoverMngr.update(this, null, paramObject, "completed", l1, l2, paramSQLException);
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  965 */         this.failoverMngr.update(this, null, paramObject, "completed", l1, l2, paramSQLException);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  976 */         this.failoverMngr.disableReplayInternal(paramMethod, 372, "Replay disabled because of nonreplayable call", null);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*      */ 
/*  985 */       STMT_REPLAY_LOGGER.log(Level.WARNING, "On statement {0}, could not get original SqlKind: {1}", new Object[] { this, localSQLException });
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
/*      */   @ProxyResult(ProxyResultPolicy.MANUAL)
/*      */   public Connection getConnection()
/*      */   {
/* 1000 */     return (Connection)getCreator();
/*      */   }
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.MANUAL)
/*      */   public <T> T unwrap(Class<T> paramClass)
/*      */     throws SQLException
/*      */   {
/* 1007 */     return (T)getDelegate();
/*      */   }
/*      */   
/*      */   @GetDelegate
/*      */   protected abstract Object getDelegate();
/*      */   
/*      */   @SetDelegate
/*      */   protected abstract void setDelegate(Object paramObject);
/*      */   
/*      */   @GetCreator
/*      */   protected abstract Object getCreator();
/*      */   
/*      */   /* Error */
/*      */   boolean okToPurgeSameProxyList()
/*      */   {
/*      */     // Byte code:
/*      */     //   0: iconst_0
/*      */     //   1: istore_1
/*      */     //   2: aload_0
/*      */     //   3: invokevirtual 6	oracle/jdbc/replay/driver/NonTxnReplayableStatement:getDelegate	()Ljava/lang/Object;
/*      */     //   6: checkcast 7	oracle/jdbc/internal/OracleStatement
/*      */     //   9: astore_2
/*      */     //   10: aload_2
/*      */     //   11: invokeinterface 8 1 0
/*      */     //   16: astore_3
/*      */     //   17: aload_3
/*      */     //   18: getstatic 17	oracle/jdbc/internal/OracleStatement$SqlKind:UNINITIALIZED	Loracle/jdbc/internal/OracleStatement$SqlKind;
/*      */     //   21: if_acmpne +8 -> 29
/*      */     //   24: iconst_1
/*      */     //   25: istore_1
/*      */     //   26: goto +96 -> 122
/*      */     //   29: aload_3
/*      */     //   30: invokevirtual 14	oracle/jdbc/internal/OracleStatement$SqlKind:isSELECT	()Z
/*      */     //   33: ifeq +89 -> 122
/*      */     //   36: aload_3
/*      */     //   37: getstatic 79	oracle/jdbc/internal/OracleStatement$SqlKind:SELECT_FOR_UPDATE	Loracle/jdbc/internal/OracleStatement$SqlKind;
/*      */     //   40: if_acmpeq +82 -> 122
/*      */     //   43: aload_0
/*      */     //   44: getfield 80	oracle/jdbc/replay/driver/NonTxnReplayableStatement:headSameProxy	Loracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry;
/*      */     //   47: astore 5
/*      */     //   49: aload 5
/*      */     //   51: ifnull +71 -> 122
/*      */     //   54: aload 5
/*      */     //   56: getfield 54	oracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry:method	Ljava/lang/reflect/Method;
/*      */     //   59: ifnonnull +8 -> 67
/*      */     //   62: ldc 55
/*      */     //   64: goto +11 -> 75
/*      */     //   67: aload 5
/*      */     //   69: getfield 54	oracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry:method	Ljava/lang/reflect/Method;
/*      */     //   72: invokevirtual 56	java/lang/reflect/Method:getName	()Ljava/lang/String;
/*      */     //   75: astore 4
/*      */     //   77: ldc 81
/*      */     //   79: aload 4
/*      */     //   81: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   84: ifne +13 -> 97
/*      */     //   87: ldc 83
/*      */     //   89: aload 4
/*      */     //   91: invokevirtual 82	java/lang/String:equals	(Ljava/lang/Object;)Z
/*      */     //   94: ifeq +18 -> 112
/*      */     //   97: aload 5
/*      */     //   99: getfield 62	oracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry:scn	J
/*      */     //   102: lconst_0
/*      */     //   103: lcmp
/*      */     //   104: ifle +8 -> 112
/*      */     //   107: iconst_1
/*      */     //   108: istore_1
/*      */     //   109: goto +13 -> 122
/*      */     //   112: aload 5
/*      */     //   114: getfield 84	oracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry:nextEntrySameProxy	Loracle/jdbc/replay/driver/FailoverManagerImpl$CallHistoryEntry;
/*      */     //   117: astore 5
/*      */     //   119: goto -70 -> 49
/*      */     //   122: getstatic 9	oracle/jdbc/replay/driver/NonTxnReplayableStatement:STMT_REPLAY_LOGGER	Ljava/util/logging/Logger;
/*      */     //   125: getstatic 10	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
/*      */     //   128: ldc 85
/*      */     //   130: iconst_2
/*      */     //   131: anewarray 12	java/lang/Object
/*      */     //   134: dup
/*      */     //   135: iconst_0
/*      */     //   136: aload_0
/*      */     //   137: aastore
/*      */     //   138: dup
/*      */     //   139: iconst_1
/*      */     //   140: iload_1
/*      */     //   141: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
/*      */     //   144: aastore
/*      */     //   145: invokevirtual 13	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   148: iload_1
/*      */     //   149: ireturn
/*      */     //   150: astore_2
/*      */     //   151: getstatic 9	oracle/jdbc/replay/driver/NonTxnReplayableStatement:STMT_REPLAY_LOGGER	Ljava/util/logging/Logger;
/*      */     //   154: getstatic 10	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
/*      */     //   157: ldc 29
/*      */     //   159: iconst_2
/*      */     //   160: anewarray 12	java/lang/Object
/*      */     //   163: dup
/*      */     //   164: iconst_0
/*      */     //   165: aload_0
/*      */     //   166: aastore
/*      */     //   167: dup
/*      */     //   168: iconst_1
/*      */     //   169: aload_2
/*      */     //   170: aastore
/*      */     //   171: invokevirtual 13	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   174: iconst_0
/*      */     //   175: istore_1
/*      */     //   176: getstatic 9	oracle/jdbc/replay/driver/NonTxnReplayableStatement:STMT_REPLAY_LOGGER	Ljava/util/logging/Logger;
/*      */     //   179: getstatic 10	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
/*      */     //   182: ldc 85
/*      */     //   184: iconst_2
/*      */     //   185: anewarray 12	java/lang/Object
/*      */     //   188: dup
/*      */     //   189: iconst_0
/*      */     //   190: aload_0
/*      */     //   191: aastore
/*      */     //   192: dup
/*      */     //   193: iconst_1
/*      */     //   194: iload_1
/*      */     //   195: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
/*      */     //   198: aastore
/*      */     //   199: invokevirtual 13	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   202: iload_1
/*      */     //   203: ireturn
/*      */     //   204: astore 6
/*      */     //   206: getstatic 9	oracle/jdbc/replay/driver/NonTxnReplayableStatement:STMT_REPLAY_LOGGER	Ljava/util/logging/Logger;
/*      */     //   209: getstatic 10	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
/*      */     //   212: ldc 85
/*      */     //   214: iconst_2
/*      */     //   215: anewarray 12	java/lang/Object
/*      */     //   218: dup
/*      */     //   219: iconst_0
/*      */     //   220: aload_0
/*      */     //   221: aastore
/*      */     //   222: dup
/*      */     //   223: iconst_1
/*      */     //   224: iload_1
/*      */     //   225: invokestatic 36	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
/*      */     //   228: aastore
/*      */     //   229: invokevirtual 13	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;[Ljava/lang/Object;)V
/*      */     //   232: iload_1
/*      */     //   233: ireturn
/*      */     // Line number table:
/*      */     //   Java source line #817	-> byte code offset #0
/*      */     //   Java source line #821	-> byte code offset #2
/*      */     //   Java source line #822	-> byte code offset #10
/*      */     //   Java source line #825	-> byte code offset #17
/*      */     //   Java source line #826	-> byte code offset #24
/*      */     //   Java source line #829	-> byte code offset #29
/*      */     //   Java source line #834	-> byte code offset #43
/*      */     //   Java source line #835	-> byte code offset #49
/*      */     //   Java source line #838	-> byte code offset #54
/*      */     //   Java source line #841	-> byte code offset #77
/*      */     //   Java source line #845	-> byte code offset #107
/*      */     //   Java source line #846	-> byte code offset #109
/*      */     //   Java source line #836	-> byte code offset #112
/*      */     //   Java source line #863	-> byte code offset #122
/*      */     //   Java source line #867	-> byte code offset #148
/*      */     //   Java source line #853	-> byte code offset #150
/*      */     //   Java source line #855	-> byte code offset #151
/*      */     //   Java source line #859	-> byte code offset #174
/*      */     //   Java source line #863	-> byte code offset #176
/*      */     //   Java source line #867	-> byte code offset #202
/*      */     //   Java source line #863	-> byte code offset #204
/*      */     //   Java source line #867	-> byte code offset #232
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	234	0	this	NonTxnReplayableStatement
/*      */     //   1	232	1	bool	boolean
/*      */     //   9	2	2	localOracleStatement	oracle.jdbc.internal.OracleStatement
/*      */     //   150	20	2	localSQLException	SQLException
/*      */     //   16	21	3	localSqlKind	OracleStatement.SqlKind
/*      */     //   75	15	4	str	String
/*      */     //   47	71	5	localCallHistoryEntry	FailoverManagerImpl.CallHistoryEntry
/*      */     //   204	1	6	localObject	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   2	122	150	java/sql/SQLException
/*      */     //   2	122	204	finally
/*      */     //   150	176	204	finally
/*      */     //   204	206	204	finally
/*      */   }
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
/*      */   public abstract ResultSet executeQuery(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
/*      */   public abstract ResultSet executeQuery()
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract ResultSetMetaData getMetaData()
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract ParameterMetaData getParameterMetaData()
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Array getArray(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Array getArray(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Blob getBlob(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Blob getBlob(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Clob getClob(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Clob getClob(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract NClob getNClob(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract NClob getNClob(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Ref getRef(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Ref getRef(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract RowId getRowId(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract RowId getRowId(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Struct getStruct(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract Struct getStruct(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract SQLXML getSQLXML(int paramInt)
/*      */     throws SQLException;
/*      */   
/*      */   @ProxyResult(ProxyResultPolicy.CREATE)
/*      */   public abstract SQLXML getSQLXML(String paramString)
/*      */     throws SQLException;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */