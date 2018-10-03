/*      */ package oracle.jdbc.xa;
/*      */ 
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Statement;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Hashtable;
/*      */ import javax.transaction.xa.XAException;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import javax.transaction.xa.Xid;
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
/*      */ public abstract class OracleXAResource
/*      */   implements XAResource
/*      */ {
/*      */   public static final int XA_OK = 0;
/*      */   public static final short DEFAULT_XA_TIMEOUT = 60;
/*   41 */   protected boolean savedConnectionAutoCommit = false;
/*   42 */   protected boolean savedXAConnectionAutoCommit = false;
/*      */   
/*      */ 
/*      */   public static final int TMNOFLAGS = 0;
/*      */   
/*      */ 
/*      */   public static final int TMNOMIGRATE = 2;
/*      */   
/*      */ 
/*      */   public static final int TMENDRSCAN = 8388608;
/*      */   
/*      */ 
/*      */   public static final int TMFAIL = 536870912;
/*      */   
/*      */ 
/*      */   public static final int TMMIGRATE = 1048576;
/*      */   
/*      */   public static final int TMJOIN = 2097152;
/*      */   
/*      */   public static final int TMONEPHASE = 1073741824;
/*      */   
/*      */   public static final int TMRESUME = 134217728;
/*      */   
/*      */   public static final int TMSTARTRSCAN = 16777216;
/*      */   
/*      */   public static final int TMSUCCESS = 67108864;
/*      */   
/*      */   public static final int TMSUSPEND = 33554432;
/*      */   
/*      */   public static final int ORATMREADONLY = 256;
/*      */   
/*      */   public static final int ORATMREADWRITE = 512;
/*      */   
/*      */   public static final int ORATMSERIALIZABLE = 1024;
/*      */   
/*      */   public static final int ORAISOLATIONMASK = 65280;
/*      */   
/*      */   public static final int ORATRANSLOOSE = 65536;
/*      */   
/*   81 */   protected Connection connection = null;
/*   82 */   protected OracleXAConnection xaconnection = null;
/*   83 */   protected int timeout = 60;
/*   84 */   protected String dblink = null;
/*      */   
/*      */ 
/*   87 */   private Connection logicalConnection = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   95 */   private String synchronizeBeforeRecoverNewCall = "BEGIN sys.dbms_xa.dist_txn_sync \n; END;";
/*      */   
/*      */ 
/*   98 */   private String synchronizeBeforeRecoverOldCall = "BEGIN sys.dbms_system.dist_txn_sync(0) \n; END;";
/*      */   
/*      */ 
/*      */ 
/*  102 */   private String recoverySqlRows = "SELECT formatid, globalid, branchid FROM SYS.DBA_PENDING_TRANSACTIONS";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  108 */   protected boolean canBeMigratablySuspended = false;
/*      */   
/*      */ 
/*  111 */   private boolean isTMRScanStarted = false;
/*      */   
/*  113 */   private static final Xid[] NO_XID = new Xid[0];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleXAResource() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleXAResource(Connection paramConnection, OracleXAConnection paramOracleXAConnection)
/*      */     throws XAException
/*      */   {
/*  135 */     this.connection = paramConnection;
/*  136 */     this.xaconnection = paramOracleXAConnection;
/*      */     
/*  138 */     if (this.connection == null) {
/*  139 */       throw new XAException(-7);
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
/*      */   public synchronized void setConnection(Connection paramConnection)
/*      */     throws XAException
/*      */   {
/*  153 */     this.connection = paramConnection;
/*      */     
/*  155 */     if (this.connection == null) {
/*  156 */       throw new XAException(-7);
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
/*  194 */   Xid lastActiveXid = null;
/*      */   
/*      */ 
/*  197 */   protected Xid activeXid = null;
/*      */   
/*  199 */   protected Hashtable<Xid, XidListEntry> xidHash = new Hashtable(50);
/*      */   
/*      */ 
/*      */   class XidListEntry
/*      */   {
/*      */     Xid xid;
/*      */     boolean isSuspended;
/*      */     
/*      */     XidListEntry(Xid paramXid, boolean paramBoolean)
/*      */     {
/*  209 */       this.xid = paramXid;
/*  210 */       this.isSuspended = paramBoolean;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   final synchronized XidListEntry getMatchingXidListEntry(Xid paramXid)
/*      */   {
/*  218 */     XidListEntry localXidListEntry = (XidListEntry)this.xidHash.get(paramXid);
/*      */     
/*  220 */     return localXidListEntry;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final synchronized boolean removeXidFromList(Xid paramXid)
/*      */   {
/*  228 */     if (isSameXid(this.activeXid, paramXid)) {
/*  229 */       this.activeXid = null;
/*      */     }
/*  231 */     return this.xidHash.remove(paramXid) != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   final boolean isSameXid(Xid paramXid1, Xid paramXid2)
/*      */   {
/*  240 */     return paramXid1 == paramXid2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final boolean isOnStack(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  249 */     return this.xidHash.containsKey(paramXid);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected final synchronized boolean isXidListEmpty()
/*      */   {
/*  258 */     return this.xidHash.isEmpty();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected synchronized void createOrUpdateXid(Xid paramXid, boolean paramBoolean, boolean[] paramArrayOfBoolean)
/*      */   {
/*  280 */     XidListEntry localXidListEntry = getMatchingXidListEntry(paramXid);
/*      */     
/*  282 */     if (localXidListEntry != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  288 */       paramArrayOfBoolean[0] = true;
/*      */       
/*      */ 
/*      */ 
/*  292 */       localXidListEntry.isSuspended = paramBoolean;
/*      */     }
/*      */     else
/*      */     {
/*  296 */       localXidListEntry = new XidListEntry(paramXid, paramBoolean);
/*  297 */       this.xidHash.put(paramXid, localXidListEntry);
/*      */     }
/*      */     
/*  300 */     if (paramBoolean)
/*      */     {
/*      */ 
/*  303 */       this.lastActiveXid = this.activeXid;
/*  304 */       this.activeXid = null;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  309 */       enterGlobalTxnMode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  314 */       if ((this.lastActiveXid != null) && (isSameXid(paramXid, this.lastActiveXid))) {
/*  315 */         this.lastActiveXid = null;
/*      */       }
/*  317 */       this.activeXid = localXidListEntry.xid;
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
/*      */   protected synchronized boolean updateXidList(Xid paramXid, boolean[] paramArrayOfBoolean)
/*      */   {
/*  344 */     boolean bool = false;
/*  345 */     XidListEntry localXidListEntry = getMatchingXidListEntry(paramXid);
/*  346 */     if (localXidListEntry != null)
/*      */     {
/*  348 */       bool = true;
/*  349 */       paramArrayOfBoolean[0] = true;
/*  350 */       paramArrayOfBoolean[1] = localXidListEntry.isSuspended;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  357 */       if (localXidListEntry.isSuspended)
/*      */       {
/*  359 */         enterGlobalTxnMode();
/*      */       }
/*      */       else
/*      */       {
/*  363 */         exitGlobalTxnMode();
/*      */       }
/*      */     }
/*      */     
/*  367 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isXidSuspended(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  382 */     boolean bool = false;
/*  383 */     XidListEntry localXidListEntry = getMatchingXidListEntry(paramXid);
/*      */     
/*  385 */     if (localXidListEntry != null) {
/*  386 */       bool = localXidListEntry.isSuspended;
/*      */     }
/*  388 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Xid suspendStacked(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  414 */     Xid localXid = null;
/*      */     
/*  416 */     if ((this.activeXid != null) && (!isSameXid(this.activeXid, paramXid)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  421 */       localXid = this.activeXid;
/*      */       
/*      */ 
/*      */ 
/*  425 */       if (!isXidSuspended(this.activeXid))
/*      */       {
/*  427 */         end(this.activeXid, 33554432);
/*  428 */         this.lastActiveXid = this.activeXid;
/*  429 */         this.activeXid = null;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  434 */     return localXid;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void resumeStacked(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  454 */     if (paramXid != null)
/*      */     {
/*      */ 
/*      */ 
/*  458 */       start(paramXid, 134217728);
/*  459 */       this.activeXid = paramXid;
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
/*      */   public abstract void start(Xid paramXid, int paramInt)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void end(Xid paramXid, int paramInt)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void commit(Xid paramXid, boolean paramBoolean)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract int prepare(Xid paramXid)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void forget(Xid paramXid)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void rollback(Xid paramXid)
/*      */     throws XAException;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Xid[] recover(int paramInt)
/*      */     throws XAException
/*      */   {
/*  651 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*  654 */       if ((paramInt & 0x1800000) != paramInt)
/*      */       {
/*      */ 
/*      */ 
/*  658 */         throw new XAException(-5);
/*      */       }
/*      */       
/*  661 */       if (paramInt == 16777216) {
/*  662 */         this.isTMRScanStarted = true;
/*  663 */       } else { if ((this.isTMRScanStarted) && (paramInt == 8388608))
/*      */         {
/*  665 */           this.isTMRScanStarted = false;
/*  666 */           return NO_XID;
/*      */         }
/*  668 */         if ((this.isTMRScanStarted) && (paramInt == 0))
/*  669 */           return NO_XID;
/*      */       }
/*  671 */       Statement localStatement = null;
/*  672 */       ResultSet localResultSet = null;
/*  673 */       ArrayList localArrayList = new ArrayList(50);
/*      */       
/*      */       try
/*      */       {
/*  677 */         localStatement = this.connection.createStatement();
/*      */         
/*      */ 
/*      */         try
/*      */         {
/*  682 */           localStatement.execute(this.synchronizeBeforeRecoverNewCall);
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (Exception localException1)
/*      */         {
/*      */ 
/*  689 */           localStatement.execute(this.synchronizeBeforeRecoverOldCall);
/*      */         }
/*      */         
/*      */ 
/*  693 */         localResultSet = localStatement.executeQuery(this.recoverySqlRows);
/*      */         
/*  695 */         while (localResultSet.next())
/*      */         {
/*  697 */           localArrayList.add(new OracleXid(localResultSet.getInt(1), localResultSet.getBytes(2), localResultSet.getBytes(3)));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         try
/*      */         {
/*  715 */           if (localStatement != null) {
/*  716 */             localStatement.close();
/*      */           }
/*  718 */           if (localResultSet != null) {
/*  719 */             localResultSet.close();
/*      */           }
/*      */         }
/*      */         catch (Exception localException2) {}
/*      */         
/*  724 */         i = localArrayList.size();
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  709 */         throw new XAException(-3);
/*      */       }
/*      */       finally
/*      */       {
/*      */         try
/*      */         {
/*  715 */           if (localStatement != null) {
/*  716 */             localStatement.close();
/*      */           }
/*  718 */           if (localResultSet != null) {
/*  719 */             localResultSet.close();
/*      */           }
/*      */         }
/*      */         catch (Exception localException3) {}
/*      */       }
/*      */       int i;
/*  725 */       Xid[] arrayOfXid = new Xid[i];
/*  726 */       System.arraycopy(localArrayList.toArray(), 0, arrayOfXid, 0, i);
/*      */       
/*  728 */       return arrayOfXid;
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
/*      */   protected void restoreAutoCommitModeForGlobalTransaction()
/*      */     throws XAException
/*      */   {
/*  747 */     if ((this.savedConnectionAutoCommit) && (((OracleConnection)this.connection).getTxnMode() != 1))
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/*  753 */         this.connection.setAutoCommit(this.savedConnectionAutoCommit);
/*  754 */         this.xaconnection.setAutoCommit(this.savedXAConnectionAutoCommit);
/*      */       }
/*      */       catch (SQLException localSQLException) {}
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
/*      */   protected void saveAndAlterAutoCommitModeForGlobalTransaction()
/*      */     throws XAException
/*      */   {
/*  776 */     if (((OracleConnection)this.connection).getTxnMode() != 1)
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/*  781 */         this.savedConnectionAutoCommit = this.connection.getAutoCommit();
/*  782 */         this.connection.setAutoCommit(false);
/*  783 */         this.savedXAConnectionAutoCommit = this.xaconnection.getAutoCommit();
/*  784 */         this.xaconnection.setAutoCommit(false);
/*      */       }
/*      */       catch (SQLException localSQLException) {}
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
/*      */   public void resume(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  806 */     start(paramXid, 134217728);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void join(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  823 */     start(paramXid, 2097152);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void suspend(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  839 */     end(paramXid, 33554432);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void join(Xid paramXid, int paramInt)
/*      */     throws XAException
/*      */   {
/*  857 */     this.timeout = paramInt;
/*      */     
/*  859 */     start(paramXid, 2097152);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void resume(Xid paramXid, int paramInt)
/*      */     throws XAException
/*      */   {
/*  877 */     this.timeout = paramInt;
/*      */     
/*  879 */     start(paramXid, 134217728);
/*      */   }
/*      */   
/*      */ 
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
/*      */   {
/*  894 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTransactionTimeout()
/*      */     throws XAException
/*      */   {
/*  913 */     return this.timeout;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSameRM(XAResource paramXAResource)
/*      */     throws XAException
/*      */   {
/*  934 */     Connection localConnection = null;
/*      */     
/*      */ 
/*  937 */     if ((paramXAResource instanceof OracleXAResource)) {
/*  938 */       localConnection = ((OracleXAResource)paramXAResource).getConnection();
/*      */     }
/*      */     else {
/*  941 */       return false;
/*      */     }
/*      */     
/*      */     try
/*      */     {
/*  946 */       if ((this.connection == null) || (((OracleConnection)this.connection).isClosed())) {
/*  947 */         return false;
/*      */       }
/*  949 */       String str1 = ((OracleConnection)this.connection).getURL();
/*  950 */       String str2 = ((OracleConnection)this.connection).getProtocolType();
/*      */       
/*  952 */       if (localConnection != null)
/*      */       {
/*  954 */         return (localConnection.equals(this.connection)) || (((OracleConnection)localConnection).getURL().equals(str1)) || ((((OracleConnection)localConnection).getProtocolType().equals(str2)) && (str2.equals("kprb")));
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  970 */       throw new XAException(-3);
/*      */     }
/*      */     
/*      */ 
/*  974 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean setTransactionTimeout(int paramInt)
/*      */     throws XAException
/*      */   {
/*  997 */     if (paramInt < 0) {
/*  998 */       throw new XAException(-5);
/*      */     }
/* 1000 */     this.timeout = paramInt;
/*      */     
/* 1002 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getDBLink()
/*      */   {
/* 1016 */     return this.dblink;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDBLink(String paramString)
/*      */   {
/* 1031 */     this.dblink = paramString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLogicalConnection(Connection paramConnection)
/*      */   {
/* 1046 */     this.logicalConnection = paramConnection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void allowGlobalTxnModeOnly(int paramInt)
/*      */     throws XAException
/*      */   {
/* 1072 */     if (((OracleConnection)this.connection).getTxnMode() != 1)
/*      */     {
/* 1074 */       throw new XAException(paramInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void exitGlobalTxnMode()
/*      */   {
/* 1086 */     ((OracleConnection)this.connection).setTxnMode(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void enterGlobalTxnMode()
/*      */   {
/* 1098 */     ((OracleConnection)this.connection).setTxnMode(1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkError(int paramInt)
/*      */     throws XAException
/*      */   {
/* 1107 */     if ((paramInt & 0xFFFF) != 0)
/*      */     {
/* 1109 */       XAException localXAException = OracleXAException.newXAException(getConnectionDuringExceptionHandling(), paramInt);
/* 1110 */       localXAException.fillInStackTrace();
/* 1111 */       throw localXAException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkError(int paramInt1, int paramInt2)
/*      */     throws XAException
/*      */   {
/* 1121 */     if ((paramInt1 & 0xFFFF) != 0)
/*      */     {
/* 1123 */       XAException localXAException = OracleXAException.newXAException(getConnectionDuringExceptionHandling(), paramInt1, paramInt2);
/* 1124 */       localXAException.fillInStackTrace();
/* 1125 */       throw localXAException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void checkError(SQLException paramSQLException, int paramInt)
/*      */     throws XAException
/*      */   {
/* 1134 */     XAException localXAException = OracleXAException.newXAException(getConnectionDuringExceptionHandling(), paramSQLException, paramInt);
/* 1135 */     localXAException.fillInStackTrace();
/* 1136 */     throw localXAException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 1152 */     return (OracleConnection)this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1157 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleXAResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */