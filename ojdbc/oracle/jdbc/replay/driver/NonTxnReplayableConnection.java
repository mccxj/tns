package oracle.jdbc.replay.driver;
import java.lang.reflect.Method;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleSavepoint;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection.TransactionState;
import oracle.jdbc.proxy.annotation.GetCreator;
import oracle.jdbc.proxy.annotation.GetDelegate;
import oracle.jdbc.proxy.annotation.Methods;
import oracle.jdbc.proxy.annotation.OnError;
import oracle.jdbc.proxy.annotation.Post;
import oracle.jdbc.proxy.annotation.Pre;
import oracle.jdbc.proxy.annotation.ProxyFor;
import oracle.jdbc.proxy.annotation.ProxyLocale;
import oracle.jdbc.proxy.annotation.ProxyResult;
import oracle.jdbc.proxy.annotation.ProxyResultPolicy;
import oracle.jdbc.proxy.annotation.SetDelegate;
import oracle.jdbc.replay.OracleDataSource;
import oracle.jdbc.replay.internal.ReplayableConnection;
import oracle.sql.ARRAY;
@ProxyFor({Connection.class, oracle.jdbc.OracleConnection.class, oracle.jdbc.internal.OracleConnection.class})
@ProxyLocale
public abstract class NonTxnReplayableConnection
  extends NonTxnReplayableBase
  implements Replayable, ReplayableConnection
{
  private static final String CONN_FEATURE_LOGGER_NAME = "oracle.jdbc.internal.replay.NonTxnReplayableConnection";
/*  77 */   private static Logger CONN_REPLAY_LOGGER = null;
  
  static
  {
/*  81 */     if (CONN_REPLAY_LOGGER == null) {
/*  82 */       CONN_REPLAY_LOGGER = ReplayLoggerFactory.getLogger("oracle.jdbc.internal.replay.NonTxnReplayableConnection");
    }
  }
  
  @Pre
  protected void preForAll(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/*  94 */     super.preForAll(paramMethod, paramObject, paramVarArgs);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="commit", args={}), @oracle.jdbc.proxy.annotation.Signature(name="commit", args={EnumSet.class}), @oracle.jdbc.proxy.annotation.Signature(name="rollback", args={}), @oracle.jdbc.proxy.annotation.Signature(name="rollback", args={Savepoint.class}), @oracle.jdbc.proxy.annotation.Signature(name="setSavepoint", args={}), @oracle.jdbc.proxy.annotation.Signature(name="setSavepoint", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="releaseSavepoint", args={Savepoint.class}), @oracle.jdbc.proxy.annotation.Signature(name="oracleRollback", args={OracleSavepoint.class}), @oracle.jdbc.proxy.annotation.Signature(name="oracleSetSavepoint", args={}), @oracle.jdbc.proxy.annotation.Signature(name="oracleSetSavepoint", args={String.class}), @oracle.jdbc.proxy.annotation.Signature(name="oracleReleaseSavepoint", args={OracleSavepoint.class})})
  protected void preForTxnControl(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/* 115 */     FailoverManagerImpl.ReplayLifecycle localReplayLifecycle = this.failoverMngr.getReplayLifecycle();
    
/* 117 */     if (localReplayLifecycle != FailoverManagerImpl.ReplayLifecycle.ENABLED_NOT_REPLAYING) {
/* 118 */       return;
    }
/* 120 */     String str = paramMethod == null ? "NULL METHOD" : paramMethod.getName();
    
/* 122 */     CONN_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, entering preForTxnControl({1})", new Object[] { this, str });
    
/* 133 */     if (this.failoverMngr != null)
    {
/* 135 */       int i = 0;
      
      try
      {
/* 139 */         oracle.jdbc.internal.OracleConnection localOracleConnection = (oracle.jdbc.internal.OracleConnection)getDelegate();
        
/* 141 */         EnumSet localEnumSet = localOracleConnection.getTransactionState();
        
/* 144 */         CONN_REPLAY_LOGGER.log(Level.FINEST, "On connection {0}, method {1}, transaction state: {2}", new Object[] { this, str, localEnumSet });
        
/* 151 */         if ((localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_STARTED)) && (!localEnumSet.contains(OracleConnection.TransactionState.TRANSACTION_READONLY)))
        {
/* 155 */           i = 1;
        }
      }
      catch (SQLException localSQLException) {
/* 159 */         CONN_REPLAY_LOGGER.log(Level.WARNING, "On connection {0}, could not get transaction state: {1}", new Object[] { this, localSQLException });
        
/* 163 */         i = 1;
      }
      finally
      {
/* 167 */         if (i != 0)
        {
/* 169 */           this.failoverMngr.disableReplayInternal(paramMethod, 371, "Replay disabled because of active transaction", null);
        }
        else
        {
/* 176 */           super.preForAll(paramMethod, paramObject, paramVarArgs);
        }
      }
    } else {
/* 180 */       CONN_REPLAY_LOGGER.log(Level.SEVERE, "On connection {0}, failover manager not set", this);
    }
    
/* 183 */     CONN_REPLAY_LOGGER.log(Level.FINER, "On connection {0}, exiting preForTxnControl()", this);
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="abort", args={}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={int.class}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={java.util.Properties.class})})
  protected void preForClosure(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/* 197 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, preForClosure({1})", new Object[] { this, paramMethod.getName() });
    
/* 202 */     this.isClosedAndNoReplay = true;
  }
  
  @Pre
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="cancel", args={})})
  protected void preForCancel(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
/* 212 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, preForCancel({1})", new Object[] { this, paramMethod.getName() });
  }
  
  @Post
  protected void postForAll(Method paramMethod)
  {
/* 220 */     postForAll(paramMethod, null);
  }
  
  @Post
  protected Object postForAll(Method paramMethod, Object paramObject)
  {
/* 226 */     return super.postForAll(paramMethod, paramObject);
  }
  
  @Post
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="abort", args={}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={int.class}), @oracle.jdbc.proxy.annotation.Signature(name="close", args={java.util.Properties.class})})
  protected void postForClosure(Method paramMethod)
  {
/* 239 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, postForClosure({1})", new Object[] { this, paramMethod.getName() });
  }
  
  @Post
  @Methods(signatures={@oracle.jdbc.proxy.annotation.Signature(name="cancel", args={})})
  protected void postForCancel(Method paramMethod)
  {
/* 251 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, postForCancel({1})", new Object[] { this, paramMethod.getName() });
  }
  
  @OnError(SQLException.class)
  protected void onErrorVoidForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 260 */     super.onErrorVoidForAll(paramMethod, paramSQLException);
  }
  
  @OnError(SQLException.class)
  protected Object onErrorForAll(Method paramMethod, SQLException paramSQLException)
    throws SQLException
  {
/* 267 */     return super.onErrorForAll(paramMethod, paramSQLException);
  }
  
  public void setReplayInitiationTimeout(int paramInt)
    throws SQLException
  {
/* 292 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, entering setReplayInitiationTimeout({1})", new Object[] { this, Integer.valueOf(paramInt) });
    
/* 296 */     this.failoverMngr.setReplayInitiationTimeout(paramInt);
    
/* 298 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, exiting setReplayInitiationTimeout({1})", new Object[] { this, Integer.valueOf(paramInt) });
  }
  
  public void initialize(OracleDataSource paramOracleDataSource)
    throws SQLException
  {
/* 306 */     FailoverManagerImpl localFailoverManagerImpl = (FailoverManagerImpl)FailoverManagerImpl.getFailoverManager(this, paramOracleDataSource);
    
/* 309 */     setFailoverManager(localFailoverManagerImpl);
  }
  
  public void beginRequest()
    throws SQLException
  {
/* 319 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, entering beginRequest()", this);
    
/* 322 */     this.failoverMngr.beginRequest();
    
/* 324 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, exiting beginRequest()", this);
  }
  
  public void endRequest()
    throws SQLException
  {
/* 331 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, entering endRequest()", this);
    
/* 334 */     this.failoverMngr.endRequest();
    
/* 336 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, exiting endRequest()", this);
  }
  
  public void disableReplay()
    throws SQLException
  {
/* 343 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, entering disableReplay()", this);
    
/* 346 */     this.failoverMngr.disableReplay();
    
/* 348 */     CONN_REPLAY_LOGGER.log(Level.FINE, "On connection {0}, exiting disableReplay()", this);
  }
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public ARRAY createARRAY(String paramString, Object paramObject)
    throws SQLException
  {
    Object localObject1;
    
/* 364 */     if ((paramObject != null) && ((paramObject instanceof Object[])))
    {
/* 366 */       localObject2 = (Object[])paramObject;
/* 367 */       if (localObject2.length > 0)
      {
/* 369 */         Object[] arrayOfObject = new Object[localObject2.length];
/* 370 */         int i = 0;
/* 371 */         for (Object localObject4 : localObject2)
        {
/* 373 */           if ((localObject4 instanceof NonTxnReplayableBase))
          {
/* 375 */             arrayOfObject[(i++)] = ((NonTxnReplayableBase)localObject4).getDelegate();
          }
          else
/* 378 */             arrayOfObject[(i++)] = localObject4;
        }
/* 380 */         localObject1 = arrayOfObject;
      }
      else {
/* 383 */         localObject1 = paramObject;
      }
    } else {
/* 386 */       localObject1 = paramObject;
    }
/* 388 */     Object localObject2 = null;
    
    try
    {
/* 392 */       localObject2 = oracle.jdbc.internal.OracleConnection.class.getMethod("createARRAY", new Class[] { String.class, Object.class });
    }
    catch (Exception localException)
    {
/* 398 */       throw DatabaseError.createSqlException(1, "Cannot create ARRAY instance");
    }
    
/* 402 */     preForAll((Method)localObject2, this, new Object[] { paramString, localObject1 });
    
    try
    {
/* 406 */       oracle.jdbc.internal.OracleConnection localOracleConnection = (oracle.jdbc.internal.OracleConnection)getDelegate();
      
/* 408 */       ARRAY localARRAY = localOracleConnection.createARRAY(paramString, localObject1);
      
/* 410 */       return (ARRAY)postForAll((Method)localObject2, localARRAY);
    }
    catch (SQLException localSQLException)
    {
/* 414 */       return (ARRAY)postForAll((Method)localObject2, onErrorForAll((Method)localObject2, localSQLException));
    }
  }
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public Array createOracleArray(String paramString, Object paramObject)
    throws SQLException
  {
/* 422 */     return createARRAY(paramString, paramObject);
  }
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public Array createArrayOf(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    Object[] arrayOfObject1;
    
/* 432 */     if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
/* 434 */       arrayOfObject1 = new Object[paramArrayOfObject.length];
/* 435 */       int i = 0;
/* 436 */       for (Object localObject : paramArrayOfObject)
      {
/* 438 */         if ((localObject instanceof NonTxnReplayableBase))
        {
/* 440 */           arrayOfObject1[(i++)] = ((NonTxnReplayableBase)localObject).getDelegate();
        }
        else {
/* 443 */           arrayOfObject1[(i++)] = localObject;
        }
      }
    } else {
/* 447 */       arrayOfObject1 = paramArrayOfObject;
    }
/* 449 */     Method localMethod = null;
    
    try
    {
/* 453 */       localMethod = Connection.class.getDeclaredMethod("createArrayOf", new Class[] { String.class, Object[].class });
    }
    catch (Exception localException)
    {
/* 459 */       throw DatabaseError.createSqlException(1, "Cannot create Array instance");
    }
    
/* 463 */     preForAll(localMethod, this, new Object[] { paramString, arrayOfObject1 });
    
    try
    {
/* 467 */       Connection localConnection = (Connection)getDelegate();
      
/* 469 */       Array localArray = localConnection.createArrayOf(paramString, arrayOfObject1);
      
/* 471 */       return (Array)postForAll(localMethod, localArray);
    }
    catch (SQLException localSQLException)
    {
/* 475 */       return (Array)postForAll(localMethod, onErrorForAll(localMethod, localSQLException));
    }
  }
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public Struct createStruct(String paramString, Object[] paramArrayOfObject)
    throws SQLException
  {
    Object[] arrayOfObject1;
    
/* 486 */     if ((paramArrayOfObject != null) && (paramArrayOfObject.length > 0))
    {
/* 488 */       arrayOfObject1 = new Object[paramArrayOfObject.length];
/* 489 */       int i = 0;
/* 490 */       for (Object localObject : paramArrayOfObject)
      {
/* 492 */         if ((localObject instanceof NonTxnReplayableBase))
        {
/* 494 */           arrayOfObject1[(i++)] = ((NonTxnReplayableBase)localObject).getDelegate();
        }
        else {
/* 497 */           arrayOfObject1[(i++)] = localObject;
        }
      }
    } else {
/* 501 */       arrayOfObject1 = paramArrayOfObject;
    }
/* 503 */     Method localMethod = null;
    
    try
    {
/* 507 */       localMethod = Connection.class.getDeclaredMethod("createStruct", new Class[] { String.class, Object[].class });
    }
    catch (Exception localException)
    {
/* 513 */       throw DatabaseError.createSqlException(1, "Cannot create Struct instance");
    }
    
/* 517 */     preForAll(localMethod, this, new Object[] { paramString, arrayOfObject1 });
    
    try
    {
/* 521 */       Connection localConnection = (Connection)getDelegate();
      
/* 523 */       Struct localStruct = localConnection.createStruct(paramString, arrayOfObject1);
      
/* 525 */       return (Struct)postForAll(localMethod, localStruct);
    }
    catch (SQLException localSQLException)
    {
/* 529 */       return (Struct)postForAll(localMethod, onErrorForAll(localMethod, localSQLException));
    }
  }
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public <T> T unwrap(Class<T> paramClass)
    throws SQLException
  {
/* 626 */     return (T)getDelegate();
  }
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public oracle.jdbc.OracleConnection unwrap()
  {
/* 649 */     return ((oracle.jdbc.internal.OracleConnection)getDelegate()).unwrap();
  }
  
  @GetDelegate
  protected abstract Object getDelegate();
  
  @SetDelegate
  protected abstract void setDelegate(Object paramObject);
  
  @GetCreator
  protected abstract Object getCreator();
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract Blob createBlob()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract Clob createClob()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract NClob createNClob()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract SQLXML createSQLXML()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract Statement createStatement()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract Statement createStatement(int paramInt1, int paramInt2)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract Statement createStatement(int paramInt1, int paramInt2, int paramInt3)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract CallableStatement prepareCall(String paramString)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract CallableStatement prepareCall(String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString, int paramInt)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString, int[] paramArrayOfInt)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString, int paramInt1, int paramInt2, int paramInt3)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE_CACHE)
  public abstract PreparedStatement prepareStatement(String paramString, String[] paramArrayOfString)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE)
  public abstract Savepoint setSavepoint()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE)
  public abstract Savepoint setSavepoint(String paramString)
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.MANUAL)
  public abstract Connection _getPC();
  
  @ProxyResult(ProxyResultPolicy.CREATE)
  public abstract OracleSavepoint oracleSetSavepoint()
    throws SQLException;
  
  @ProxyResult(ProxyResultPolicy.CREATE)
  public abstract OracleSavepoint oracleSetSavepoint(String paramString)
    throws SQLException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/replay/driver/NonTxnReplayableConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */