/*      */ package oracle.jdbc.xa.client;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import javax.transaction.xa.XAException;
/*      */ import javax.transaction.xa.Xid;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.oracore.Util;
/*      */ import oracle.jdbc.xa.OracleXAConnection;
/*      */ import oracle.jdbc.xa.OracleXAException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleXAResource
/*      */   extends oracle.jdbc.xa.OracleXAResource
/*      */ {
/*   35 */   private short m_version = 0;
/*      */   
/*      */ 
/*   38 */   private boolean needStackingForCommitRollbackPrepare = false;
/*      */   
/*      */ 
/*   41 */   private static String xa_start_816 = "begin ? := JAVA_XA.xa_start(?,?,?,?); end;";
/*      */   
/*   43 */   private static String xa_start_post_816 = "begin ? := JAVA_XA.xa_start_new(?,?,?,?,?); end;";
/*      */   
/*      */ 
/*      */ 
/*   47 */   private static String xa_end_816 = "begin ? := JAVA_XA.xa_end(?,?); end;";
/*   48 */   private static String xa_end_post_816 = "begin ? := JAVA_XA.xa_end_new(?,?,?,?); end;";
/*      */   
/*      */ 
/*   51 */   private static String xa_commit_816 = "begin ? := JAVA_XA.xa_commit (?,?,?); end;";
/*      */   
/*   53 */   private static String xa_commit_post_816 = "begin ? := JAVA_XA.xa_commit_new (?,?,?,?); end;";
/*      */   
/*      */ 
/*   56 */   private static String xa_prepare_816 = "begin ? := JAVA_XA.xa_prepare (?,?); end;";
/*      */   
/*   58 */   private static String xa_prepare_post_816 = "begin ? := JAVA_XA.xa_prepare_new (?,?,?); end;";
/*      */   
/*      */ 
/*   61 */   private static String xa_rollback_816 = "begin ? := JAVA_XA.xa_rollback (?,?); end;";
/*      */   
/*   63 */   private static String xa_rollback_post_816 = "begin ? := JAVA_XA.xa_rollback_new (?,?,?); end;";
/*      */   
/*      */ 
/*   66 */   private static String xa_forget_816 = "begin ? := JAVA_XA.xa_forget (?,?); end;";
/*      */   
/*   68 */   private static String xa_forget_post_816 = "begin ? := JAVA_XA.xa_forget_new (?,?,?); end;";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   76 */   boolean isTransLoose = false;
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
/*      */   public OracleXAResource(Connection paramConnection, OracleXAConnection paramOracleXAConnection)
/*      */     throws XAException
/*      */   {
/*   92 */     super(paramConnection, paramOracleXAConnection);
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*   98 */       this.m_version = ((OracleConnection)paramConnection).getVersionNumber();
/*   99 */       this.needStackingForCommitRollbackPrepare = (this.m_version < 9000);
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  106 */     if (this.m_version < 8170)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  112 */       throw new XAException(-6);
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
/*      */   public void start(Xid paramXid, int paramInt)
/*      */     throws XAException
/*      */   {
/*  150 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  155 */       int i = -1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*  162 */         if (paramXid == null)
/*      */         {
/*      */ 
/*      */ 
/*  166 */           throw new XAException(-5);
/*      */         }
/*      */         
/*      */ 
/*  170 */         int j = paramInt & 0xFF00;
/*      */         
/*  172 */         paramInt &= 0xFFFF00FF;
/*      */         
/*  174 */         int k = paramInt & 0x10000 | (this.isTransLoose ? 65536 : 0);
/*      */         
/*  176 */         paramInt &= 0xFFFEFFFF;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  183 */         if (((paramInt & 0x8200002) != paramInt) || ((k != 0) && ((k & 0x10000) != 65536)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  189 */           throw new XAException(-5);
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
/*  200 */         if (((j & 0xFF00) != 0) && (j != 256) && (j != 512) && (j != 1024))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  205 */           throw new XAException(-5);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*  210 */         if (((paramInt & 0x8200000) != 0) && (((j & 0xFF00) != 0) || ((k & 0x10000) != 0)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  216 */           throw new XAException(-5);
/*      */         }
/*      */         
/*      */ 
/*  220 */         paramInt |= j | k;
/*      */         
/*  222 */         saveAndAlterAutoCommitModeForGlobalTransaction();
/*      */         
/*      */ 
/*      */ 
/*      */         try
/*      */         {
/*  228 */           i = doStart(paramXid, paramInt);
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException)
/*      */         {
/*  233 */           checkError(localSQLException, -3);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  240 */         checkError(i);
/*      */         
/*      */ 
/*      */ 
/*  244 */         boolean[] arrayOfBoolean = { false };
/*  245 */         super.createOrUpdateXid(paramXid, false, arrayOfBoolean);
/*      */ 
/*      */       }
/*      */       catch (XAException localXAException)
/*      */       {
/*      */ 
/*  251 */         restoreAutoCommitModeForGlobalTransaction();
/*      */         
/*  253 */         throw localXAException;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int doStart(Xid paramXid, int paramInt)
/*      */     throws XAException, SQLException
/*      */   {
/*  265 */     int i = -1;
/*  266 */     CallableStatement localCallableStatement = null;
/*      */     
/*      */     try
/*      */     {
/*  270 */       localCallableStatement = this.connection.prepareCall(xa_start_post_816);
/*      */       
/*  272 */       localCallableStatement.registerOutParameter(1, 2);
/*  273 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  274 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  275 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*  276 */       localCallableStatement.setInt(5, this.timeout);
/*  277 */       localCallableStatement.setInt(6, paramInt);
/*      */       
/*  279 */       localCallableStatement.execute();
/*      */       
/*  281 */       i = localCallableStatement.getInt(1);
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  285 */       i = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  292 */       if (i == 0) {
/*  293 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  296 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  302 */         if (localCallableStatement != null) {
/*  303 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/*  307 */       localCallableStatement = null;
/*      */     }
/*      */     
/*  310 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void end(Xid paramXid, int paramInt)
/*      */     throws XAException
/*      */   {
/*  340 */     synchronized (this.connection)
/*      */     {
/*  342 */       int i = -1;
/*  343 */       int j = 0;
/*  344 */       int k = 0;
/*      */       
/*      */       try
/*      */       {
/*  348 */         if (paramXid == null)
/*      */         {
/*      */ 
/*      */ 
/*  352 */           throw new XAException(-5);
/*      */         }
/*      */         
/*      */ 
/*  356 */         int m = 638582786;
/*  357 */         if ((paramInt & m) != paramInt)
/*      */         {
/*      */ 
/*      */ 
/*  361 */           throw new XAException(-5);
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
/*  372 */         Xid localXid = null;
/*  373 */         j = (paramInt & 0x4000000) != 0 ? 1 : 0;
/*  374 */         k = (paramInt & 0x20000000) != 0 ? 1 : 0;
/*      */         
/*      */ 
/*      */ 
/*  378 */         if ((j != 0) || (k != 0)) {
/*  379 */           localXid = super.suspendStacked(paramXid);
/*      */         }
/*      */         try
/*      */         {
/*  383 */           boolean bool = false;
/*  384 */           int n; if ((j != 0) || (k != 0))
/*      */           {
/*      */ 
/*  387 */             bool = isXidSuspended(paramXid);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  398 */             if (bool) {
/*  399 */               super.resumeStacked(paramXid);
/*      */             }
/*      */             
/*  402 */             removeXidFromList(paramXid);
/*  403 */           } else if (paramInt == 33554432)
/*      */           {
/*  405 */             boolean[] arrayOfBoolean = { false };
/*  406 */             super.createOrUpdateXid(paramXid, true, arrayOfBoolean);
/*      */             
/*      */ 
/*  409 */             n = arrayOfBoolean[0];
/*      */           }
/*      */           
/*      */ 
/*  413 */           i = doEnd(paramXid, paramInt, n);
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException)
/*      */         {
/*  418 */           checkError(localSQLException, -3);
/*      */         }
/*      */         
/*  421 */         if (localXid != null)
/*      */         {
/*      */ 
/*  424 */           super.resumeStacked(localXid);
/*  425 */         } else if (isXidListEmpty())
/*      */         {
/*      */ 
/*      */ 
/*  429 */           exitGlobalTxnMode();
/*  430 */           this.activeXid = null;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  436 */         checkError(i);
/*      */         
/*  438 */         if (((j != 0) && (paramInt != 67108864)) || ((k != 0) && (paramInt != 536870912)))
/*      */         {
/*      */ 
/*  441 */           throw new XAException(-5);
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/*  446 */         restoreAutoCommitModeForGlobalTransaction();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int doEnd(Xid paramXid, int paramInt, boolean paramBoolean)
/*      */     throws XAException, SQLException
/*      */   {
/*  459 */     CallableStatement localCallableStatement = null;
/*  460 */     int i = -1;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  466 */       localCallableStatement = this.connection.prepareCall(xa_end_post_816);
/*      */       
/*  468 */       localCallableStatement.registerOutParameter(1, 2);
/*  469 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  470 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  471 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*  472 */       localCallableStatement.setInt(5, paramInt);
/*  473 */       localCallableStatement.execute();
/*      */       
/*  475 */       i = localCallableStatement.getInt(1);
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  479 */       i = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  486 */       if (i == 0) {
/*  487 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  490 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  496 */         if (localCallableStatement != null) {
/*  497 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/*  501 */       localCallableStatement = null;
/*      */     }
/*      */     
/*  504 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void commit(Xid paramXid, boolean paramBoolean)
/*      */     throws XAException
/*      */   {
/*  530 */     synchronized (this.connection)
/*      */     {
/*  532 */       if (paramXid == null)
/*      */       {
/*      */ 
/*      */ 
/*  536 */         throw new XAException(-5);
/*      */       }
/*      */       
/*      */ 
/*  540 */       Xid localXid = null;
/*  541 */       if (this.needStackingForCommitRollbackPrepare) {
/*  542 */         localXid = super.suspendStacked(paramXid);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  550 */         removeXidFromList(paramXid);
/*      */         
/*  552 */         if (this.activeXid == null) {
/*  553 */           exitGlobalTxnMode();
/*      */         }
/*      */       }
/*      */       try
/*      */       {
/*      */         try
/*      */         {
/*  560 */           doCommit(paramXid, paramBoolean);
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException1)
/*      */         {
/*  565 */           checkError(localSQLException1, -3);
/*      */         }
/*      */       }
/*      */       catch (XAException localXAException)
/*      */       {
/*  570 */         if (localXAException.errorCode == -7)
/*      */         {
/*      */           try
/*      */           {
/*  574 */             this.connection.close();
/*      */ 
/*      */           }
/*      */           catch (SQLException localSQLException2) {}
/*      */         }
/*  579 */         else if (this.needStackingForCommitRollbackPrepare) {
/*  580 */           super.resumeStacked(localXid);
/*      */         }
/*  582 */         throw localXAException;
/*      */       }
/*      */       
/*  585 */       if (this.needStackingForCommitRollbackPrepare) {
/*  586 */         super.resumeStacked(localXid);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doCommit(Xid paramXid, boolean paramBoolean)
/*      */     throws XAException, SQLException
/*      */   {
/*  597 */     CallableStatement localCallableStatement = null;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  603 */       localCallableStatement = this.connection.prepareCall(xa_commit_post_816);
/*      */       
/*  605 */       localCallableStatement.registerOutParameter(1, 2);
/*  606 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  607 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  608 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*  609 */       localCallableStatement.setInt(5, paramBoolean ? 1 : 0);
/*      */       
/*  611 */       localCallableStatement.execute();
/*      */       
/*  613 */       int i = localCallableStatement.getInt(1);
/*  614 */       checkError(i, -7);
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  618 */       int j = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  625 */       if (j == 0) {
/*  626 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  629 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  635 */         if (localCallableStatement != null) {
/*  636 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/*  640 */       localCallableStatement = null;
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
/*      */   public int prepare(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  665 */     synchronized (this.connection)
/*      */     {
/*  667 */       int i = 0;
/*      */       
/*  669 */       if (paramXid == null)
/*      */       {
/*      */ 
/*      */ 
/*  673 */         throw new XAException(-5);
/*      */       }
/*      */       
/*      */ 
/*  677 */       Xid localXid = null;
/*  678 */       if (this.needStackingForCommitRollbackPrepare) {
/*  679 */         localXid = super.suspendStacked(paramXid);
/*      */       }
/*      */       try
/*      */       {
/*      */         try
/*      */         {
/*  685 */           i = doPrepare(paramXid);
/*  686 */           if ((i != 0) && (i != 3))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  694 */             int j = OracleXAException.errorConvert(i);
/*      */             
/*  696 */             if ((j != 0) && (j != 3))
/*      */             {
/*  698 */               XAException localXAException2 = OracleXAException.newXAException(getConnectionDuringExceptionHandling(), i);
/*  699 */               localXAException2.fillInStackTrace();
/*  700 */               throw localXAException2;
/*      */             }
/*      */             
/*  703 */             i = j;
/*      */           }
/*      */           
/*      */         }
/*      */         catch (SQLException localSQLException1)
/*      */         {
/*  709 */           checkError(localSQLException1, -3);
/*      */         }
/*      */       }
/*      */       catch (XAException localXAException1)
/*      */       {
/*  714 */         if (localXAException1.errorCode == -7)
/*      */         {
/*      */           try
/*      */           {
/*  718 */             this.connection.close();
/*      */ 
/*      */           }
/*      */           catch (SQLException localSQLException2) {}
/*      */         }
/*  723 */         else if (this.needStackingForCommitRollbackPrepare) {
/*  724 */           super.resumeStacked(localXid);
/*      */         }
/*  726 */         throw localXAException1;
/*      */       }
/*      */       
/*      */ 
/*  730 */       if (this.needStackingForCommitRollbackPrepare) {
/*  731 */         super.resumeStacked(localXid);
/*      */       }
/*  733 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int doPrepare(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  743 */     int i = 0;
/*  744 */     CallableStatement localCallableStatement = null;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  750 */       localCallableStatement = this.connection.prepareCall(xa_prepare_post_816);
/*      */       
/*  752 */       localCallableStatement.registerOutParameter(1, 2);
/*  753 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  754 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  755 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*      */       
/*  757 */       localCallableStatement.execute();
/*      */       
/*  759 */       i = localCallableStatement.getInt(1);
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*      */ 
/*  765 */       int j = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  772 */       if (j == 0) {
/*  773 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  776 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  782 */         if (localCallableStatement != null) {
/*  783 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/*  787 */       localCallableStatement = null;
/*      */     }
/*  789 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void forget(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  804 */     synchronized (this.connection)
/*      */     {
/*      */ 
/*      */ 
/*  808 */       int i = 0;
/*      */       
/*  810 */       if (paramXid == null)
/*      */       {
/*      */ 
/*      */ 
/*  814 */         throw new XAException(-5);
/*      */       }
/*      */       
/*      */ 
/*  818 */       removeXidFromList(paramXid);
/*      */       
/*      */       try
/*      */       {
/*  822 */         i = doForget(paramXid);
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  827 */         checkError(localSQLException, -3);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  833 */       checkError(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int doForget(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  844 */     int i = 0;
/*  845 */     CallableStatement localCallableStatement = null;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  851 */       localCallableStatement = this.connection.prepareCall(xa_forget_post_816);
/*      */       
/*  853 */       localCallableStatement.registerOutParameter(1, 2);
/*  854 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  855 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  856 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*      */       
/*  858 */       localCallableStatement.execute();
/*      */       
/*  860 */       i = localCallableStatement.getInt(1);
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  864 */       i = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  871 */       if (i == 0) {
/*  872 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  875 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  881 */         if (localCallableStatement != null) {
/*  882 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/*  886 */       localCallableStatement = null;
/*      */     }
/*      */     
/*  889 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void rollback(Xid paramXid)
/*      */     throws XAException
/*      */   {
/*  907 */     synchronized (this.connection)
/*      */     {
/*  909 */       int i = 0;
/*      */       
/*  911 */       if (paramXid == null)
/*      */       {
/*      */ 
/*      */ 
/*  915 */         throw new XAException(-5);
/*      */       }
/*      */       
/*      */ 
/*  919 */       Xid localXid = null;
/*  920 */       if (this.needStackingForCommitRollbackPrepare) {
/*  921 */         localXid = super.suspendStacked(paramXid);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  929 */         removeXidFromList(paramXid);
/*      */         
/*  931 */         if (this.activeXid == null) {
/*  932 */           exitGlobalTxnMode();
/*      */         }
/*      */       }
/*      */       try {
/*  936 */         doRollback(paramXid);
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  941 */         checkError(localSQLException, -3);
/*      */       }
/*      */       
/*      */ 
/*  945 */       if (this.needStackingForCommitRollbackPrepare) {
/*  946 */         super.resumeStacked(localXid);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  951 */       checkError(i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doRollback(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  962 */     CallableStatement localCallableStatement = null;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  968 */       localCallableStatement = this.connection.prepareCall(xa_rollback_post_816);
/*      */       
/*  970 */       localCallableStatement.registerOutParameter(1, 2);
/*  971 */       localCallableStatement.setInt(2, paramXid.getFormatId());
/*  972 */       localCallableStatement.setBytes(3, paramXid.getGlobalTransactionId());
/*  973 */       localCallableStatement.setBytes(4, paramXid.getBranchQualifier());
/*      */       
/*  975 */       localCallableStatement.execute();
/*      */       
/*  977 */       int i = localCallableStatement.getInt(1);
/*      */       
/*  979 */       checkError(i, -7);
/*      */     }
/*      */     catch (SQLException localSQLException2)
/*      */     {
/*  983 */       int j = localSQLException2.getErrorCode();
/*      */       
/*      */ 
/*      */ 
/*  987 */       if (j == 0) {
/*  988 */         throw new XAException(-6);
/*      */       }
/*      */       
/*  991 */       throw localSQLException2;
/*      */     }
/*      */     finally
/*      */     {
/*      */       try
/*      */       {
/*  997 */         if (localCallableStatement != null) {
/*  998 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException3) {}
/* 1002 */       localCallableStatement = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doTwoPhaseAction(int paramInt1, int paramInt2, String[] paramArrayOfString, Xid[] paramArrayOfXid)
/*      */     throws XAException
/*      */   {
/* 1012 */     synchronized (this.connection)
/*      */     {
/* 1014 */       doDoTwoPhaseAction(paramInt1, paramInt2, paramArrayOfString, paramArrayOfXid);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int doDoTwoPhaseAction(int paramInt1, int paramInt2, String[] paramArrayOfString, Xid[] paramArrayOfXid)
/*      */     throws XAException
/*      */   {
/* 1024 */     throw new XAException(-6);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static byte[] getSerializedBytes(Xid paramXid)
/*      */   {
/*      */     try
/*      */     {
/* 1040 */       return Util.serializeObject(paramXid);
/*      */     }
/*      */     catch (IOException localIOException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1047 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1054 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/client/OracleXAResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */