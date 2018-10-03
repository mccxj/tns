/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import javax.sql.XAConnection;
/*      */ import javax.transaction.xa.XAException;
/*      */ import javax.transaction.xa.XAResource;
/*      */ import javax.transaction.xa.Xid;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.xa.OracleXAConnection;
/*      */ import oracle.jdbc.xa.OracleXAException;
/*      */ import oracle.jdbc.xa.OracleXid;
/*      */ import oracle.jdbc.xa.client.OracleXADataSource;
/*      */ import oracle.jdbc.xa.client.OracleXAResource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CXAResource
/*      */   extends OracleXAResource
/*      */ {
/*      */   T4CConnection physicalConn;
/*   37 */   int[] applicationValueArr = new int[1];
/*   38 */   boolean isTransLoose = false;
/*      */   
/*      */   byte[] context;
/*      */   
/*      */   int errorNumber;
/*      */   private String password;
/*      */   
/*      */   T4CXAResource(T4CConnection paramT4CConnection, OracleXAConnection paramOracleXAConnection, boolean paramBoolean)
/*      */     throws XAException
/*      */   {
/*   48 */     super(paramT4CConnection, paramOracleXAConnection);
/*      */     
/*   50 */     this.physicalConn = paramT4CConnection;
/*   51 */     this.isTransLoose = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int doStart(Xid paramXid, int paramInt)
/*      */     throws XAException
/*      */   {
/*   59 */     synchronized (this.physicalConn)
/*      */     {
/*   61 */       int i = -1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   85 */       if (this.isTransLoose) {
/*   86 */         paramInt |= 0x10000;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   93 */       int j = paramInt & 0x8200000;
/*      */       
/*      */ 
/*   96 */       if ((j == 134217728) && (OracleXid.isLocalTransaction(paramXid))) {
/*   97 */         return 0;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  136 */       this.applicationValueArr[0] = 0;
/*      */       
/*      */       try
/*      */       {
/*      */         try
/*      */         {
/*  142 */           T4CTTIOtxse localT4CTTIOtxse = this.physicalConn.otxse;
/*  143 */           localObject1 = null;
/*  144 */           byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/*  145 */           byte[] arrayOfByte2 = paramXid.getBranchQualifier();
/*      */           
/*  147 */           int k = 0;
/*  148 */           int m = 0;
/*      */           
/*  150 */           if ((arrayOfByte1 != null) && (arrayOfByte2 != null))
/*      */           {
/*  152 */             k = Math.min(arrayOfByte1.length, 64);
/*  153 */             m = Math.min(arrayOfByte2.length, 64);
/*  154 */             localObject1 = new byte[''];
/*      */             
/*  156 */             System.arraycopy(arrayOfByte1, 0, localObject1, 0, k);
/*  157 */             System.arraycopy(arrayOfByte2, 0, localObject1, k, m);
/*      */           }
/*      */           
/*  160 */           int n = 0;
/*      */           
/*      */ 
/*  163 */           if (((paramInt & 0x200000) != 0) || ((paramInt & 0x8000000) != 0)) {
/*  164 */             n |= 0x4;
/*      */           } else {
/*  166 */             n |= 0x1;
/*      */           }
/*  168 */           if ((paramInt & 0x100) != 0) {
/*  169 */             n |= 0x100;
/*      */           }
/*  171 */           if ((paramInt & 0x200) != 0) {
/*  172 */             n |= 0x200;
/*      */           }
/*  174 */           if ((paramInt & 0x400) != 0) {
/*  175 */             n |= 0x400;
/*      */           }
/*  177 */           if ((paramInt & 0x10000) != 0) {
/*  178 */             n |= 0x10000;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  184 */           this.physicalConn.needLine();
/*  185 */           this.physicalConn.sendPiggyBackedMessages();
/*  186 */           localT4CTTIOtxse.doOTXSE(1, null, (byte[])localObject1, paramXid.getFormatId(), k, m, this.timeout, n, this.applicationValueArr);
/*      */           
/*      */ 
/*      */ 
/*  190 */           this.applicationValueArr[0] = localT4CTTIOtxse.getApplicationValue();
/*  191 */           byte[] arrayOfByte3 = localT4CTTIOtxse.getContext();
/*      */           
/*      */ 
/*      */ 
/*  195 */           if (arrayOfByte3 != null) {
/*  196 */             this.context = arrayOfByte3;
/*      */           }
/*  198 */           i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/*      */ 
/*      */ 
/*  207 */           Object localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  208 */           ((SQLException)localObject1).fillInStackTrace();
/*  209 */           throw ((Throwable)localObject1);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*      */ 
/*  217 */         i = localSQLException.getErrorCode();
/*      */         
/*      */ 
/*      */ 
/*  221 */         if (i == 0) {
/*  222 */           throw new XAException(-6);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  262 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int doEnd(Xid paramXid, int paramInt, boolean paramBoolean)
/*      */     throws XAException
/*      */   {
/*  271 */     synchronized (this.physicalConn)
/*      */     {
/*  273 */       int i = -1;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       try
/*      */       {
/*      */         try
/*      */         {
/*  296 */           T4CTTIOtxse localT4CTTIOtxse = this.physicalConn.otxse;
/*  297 */           localObject1 = null;
/*  298 */           byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/*  299 */           byte[] arrayOfByte2 = paramXid.getBranchQualifier();
/*      */           
/*  301 */           int j = 0;
/*  302 */           int k = 0;
/*      */           
/*  304 */           if ((arrayOfByte1 != null) && (arrayOfByte2 != null))
/*      */           {
/*  306 */             j = Math.min(arrayOfByte1.length, 64);
/*  307 */             k = Math.min(arrayOfByte2.length, 64);
/*  308 */             localObject1 = new byte[''];
/*      */             
/*  310 */             System.arraycopy(arrayOfByte1, 0, localObject1, 0, j);
/*  311 */             System.arraycopy(arrayOfByte2, 0, localObject1, j, k);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  316 */           if (this.context == null)
/*      */           {
/*  318 */             i = doStart(paramXid, 134217728);
/*      */             
/*  320 */             if (i != 0) {
/*  321 */               return i;
/*      */             }
/*      */           }
/*  324 */           byte[] arrayOfByte3 = this.context;
/*  325 */           int m = 0;
/*  326 */           if ((paramInt & 0x2) == 2)
/*      */           {
/*  328 */             m = 1048576;
/*  329 */           } else if (((paramInt & 0x2000000) == 33554432) && ((paramInt & 0x100000) != 1048576))
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  342 */             m = 1048576;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  348 */           this.applicationValueArr[0] >>= 16;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  356 */           this.physicalConn.needLine();
/*  357 */           this.physicalConn.sendPiggyBackedMessages();
/*  358 */           localT4CTTIOtxse.doOTXSE(2, arrayOfByte3, (byte[])localObject1, paramXid.getFormatId(), j, k, this.timeout, m, this.applicationValueArr);
/*      */           
/*      */ 
/*      */ 
/*  362 */           this.applicationValueArr[0] = localT4CTTIOtxse.getApplicationValue();
/*  363 */           byte[] arrayOfByte4 = localT4CTTIOtxse.getContext();
/*      */           
/*      */ 
/*  366 */           if (arrayOfByte4 != null) {
/*  367 */             this.context = arrayOfByte4;
/*      */           }
/*  369 */           i = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (IOException localIOException)
/*      */         {
/*      */ 
/*      */ 
/*  378 */           Object localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  379 */           ((SQLException)localObject1).fillInStackTrace();
/*  380 */           throw ((Throwable)localObject1);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*      */ 
/*  388 */         i = localSQLException.getErrorCode();
/*      */         
/*      */ 
/*      */ 
/*  392 */         if (i == 0) {
/*  393 */           throw new XAException(-6);
/*      */         }
/*      */       }
/*  396 */       return i;
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
/*      */   protected void doCommit(Xid paramXid, boolean paramBoolean)
/*      */     throws SQLException, XAException
/*      */   {
/*  415 */     synchronized (this.physicalConn)
/*      */     {
/*      */ 
/*  418 */       int i = paramBoolean ? 4 : 2;
/*      */       
/*      */       try
/*      */       {
/*  422 */         int j = doTransaction(paramXid, 1, i);
/*      */         
/*      */ 
/*  425 */         if ((!paramBoolean) || ((j != 2) && (j != 4)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  431 */           if ((paramBoolean) || (j != 5))
/*      */           {
/*      */ 
/*      */ 
/*  435 */             if (j == 8) {
/*  436 */               throw new XAException(106);
/*      */             }
/*  438 */             throw new XAException(-6);
/*      */           }
/*      */         }
/*      */       } catch (SQLException localSQLException1) {
/*  442 */         int k = localSQLException1.getErrorCode();
/*  443 */         if (k == 24756)
/*      */         {
/*      */ 
/*      */ 
/*  447 */           kputxrec(paramXid, 1, this.timeout + 120, localSQLException1);
/*      */         } else {
/*  449 */           if (k == 24780)
/*      */           {
/*      */ 
/*      */ 
/*  453 */             OracleXADataSource localOracleXADataSource = null;
/*  454 */             XAConnection localXAConnection = null;
/*      */             
/*      */             try
/*      */             {
/*  458 */               localOracleXADataSource = new OracleXADataSource();
/*      */               
/*  460 */               localOracleXADataSource.setURL(this.physicalConn.url);
/*  461 */               localOracleXADataSource.setUser(this.physicalConn.userName);
/*  462 */               this.physicalConn.getPasswordInternal(this);
/*  463 */               localOracleXADataSource.setPassword(this.password);
/*      */               
/*  465 */               localXAConnection = localOracleXADataSource.getXAConnection();
/*      */               
/*  467 */               XAResource localXAResource = localXAConnection.getXAResource();
/*      */               
/*  469 */               localXAResource.commit(paramXid, paramBoolean);
/*      */ 
/*      */             }
/*      */             catch (SQLException localSQLException2)
/*      */             {
/*      */ 
/*  475 */               XAException localXAException = new XAException(-6);
/*  476 */               localXAException.initCause(localSQLException2);
/*  477 */               throw localXAException;
/*      */             }
/*      */             finally
/*      */             {
/*      */               try
/*      */               {
/*  483 */                 if (localXAConnection != null) {
/*  484 */                   localXAConnection.close();
/*      */                 }
/*  486 */                 if (localOracleXADataSource != null) {
/*  487 */                   localOracleXADataSource.close();
/*      */                 }
/*      */               }
/*      */               catch (Exception localException2) {}
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*  495 */           throw localSQLException1;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int doPrepare(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  506 */     synchronized (this.physicalConn)
/*      */     {
/*  508 */       int i = -1;
/*      */       try {
/*  510 */         int j = doTransaction(paramXid, 3, 0);
/*      */         
/*      */ 
/*  513 */         if (j == 8)
/*      */         {
/*      */ 
/*  516 */           throw new XAException(106);
/*      */         }
/*  518 */         if (j == 4)
/*      */         {
/*      */ 
/*  521 */           i = 3;
/*      */         }
/*  523 */         else if (j == 1)
/*      */         {
/*      */ 
/*  526 */           i = 0;
/*      */         } else {
/*  528 */           if (j == 3)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*  533 */             throw new XAException(100);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  538 */           throw new XAException(-6);
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  543 */         int k = localSQLException.getErrorCode();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  549 */         if (k == 25351)
/*      */         {
/*      */ 
/*  552 */           XAException localXAException = new XAException(-6);
/*  553 */           localXAException.initCause(localSQLException);
/*  554 */           throw localXAException;
/*      */         }
/*  556 */         throw localSQLException;
/*      */       }
/*  558 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected int doForget(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  567 */     synchronized (this.physicalConn)
/*      */     {
/*  569 */       int i = 0;
/*      */       
/*  571 */       if (OracleXid.isLocalTransaction(paramXid)) {
/*  572 */         return 24771;
/*      */       }
/*      */       
/*      */ 
/*  576 */       int j = doStart(paramXid, 134217728);
/*      */       
/*  578 */       if (j != 24756)
/*      */       {
/*      */ 
/*      */ 
/*  582 */         if (j == 0)
/*      */         {
/*      */ 
/*      */           try
/*      */           {
/*      */ 
/*  588 */             doEnd(paramXid, 0, false);
/*      */           }
/*      */           catch (Exception localException) {}
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  596 */         if ((j == 0) || (j == 2079) || (j == 24754) || (j == 24761) || (j == 24774) || (j == 24776) || (j == 25351))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  603 */           return 24769; }
/*  604 */         if (j == 24752) {
/*  605 */           return 24771;
/*      */         }
/*  607 */         return j;
/*      */       }
/*      */       
/*  610 */       kputxrec(paramXid, 4, 1, null);
/*      */       
/*  612 */       return i;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   protected void doRollback(Xid paramXid)
/*      */     throws XAException, SQLException
/*      */   {
/*  620 */     synchronized (this.physicalConn)
/*      */     {
/*      */       try
/*      */       {
/*  624 */         int i = doTransaction(paramXid, 2, 3);
/*      */         
/*      */ 
/*  627 */         if (i == 8)
/*  628 */           throw new XAException(106);
/*  629 */         if (i != 3)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  634 */           throw new XAException(-6);
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException1) {
/*  638 */         int j = localSQLException1.getErrorCode();
/*      */         
/*      */ 
/*  641 */         if (j == 24756)
/*      */         {
/*      */ 
/*      */ 
/*  645 */           kputxrec(paramXid, 2, this.timeout + 120, localSQLException1);
/*      */         } else {
/*  647 */           if (j == 24780)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*  652 */             OracleXADataSource localOracleXADataSource = null;
/*  653 */             XAConnection localXAConnection = null;
/*      */             
/*      */             try
/*      */             {
/*  657 */               localOracleXADataSource = new OracleXADataSource();
/*      */               
/*  659 */               localOracleXADataSource.setURL(this.physicalConn.url);
/*  660 */               localOracleXADataSource.setUser(this.physicalConn.userName);
/*  661 */               this.physicalConn.getPasswordInternal(this);
/*  662 */               localOracleXADataSource.setPassword(this.password);
/*      */               
/*  664 */               localXAConnection = localOracleXADataSource.getXAConnection();
/*      */               
/*  666 */               XAResource localXAResource = localXAConnection.getXAResource();
/*      */               
/*  668 */               localXAResource.rollback(paramXid);
/*      */ 
/*      */             }
/*      */             catch (SQLException localSQLException2)
/*      */             {
/*      */ 
/*  674 */               XAException localXAException = new XAException(-6);
/*  675 */               localXAException.initCause(localSQLException2);
/*  676 */               throw localXAException;
/*      */             }
/*      */             finally
/*      */             {
/*      */               try
/*      */               {
/*  682 */                 if (localXAConnection != null) {
/*  683 */                   localXAConnection.close();
/*      */                 }
/*  685 */                 if (localOracleXADataSource != null) {
/*  686 */                   localOracleXADataSource.close();
/*      */                 }
/*      */               } catch (Exception localException2) {}
/*      */             }
/*      */           }
/*  691 */           if (j != 25402)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  697 */             throw localSQLException1;
/*      */           }
/*      */         }
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
/*      */   int doTransaction(Xid paramXid, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  718 */     int i = -1;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  724 */       T4CTTIOtxen localT4CTTIOtxen = this.physicalConn.otxen;
/*  725 */       localObject = null;
/*  726 */       byte[] arrayOfByte1 = paramXid.getGlobalTransactionId();
/*  727 */       byte[] arrayOfByte2 = paramXid.getBranchQualifier();
/*      */       
/*  729 */       int j = 0;
/*  730 */       int k = 0;
/*      */       
/*  732 */       if ((arrayOfByte1 != null) && (arrayOfByte2 != null))
/*      */       {
/*  734 */         j = Math.min(arrayOfByte1.length, 64);
/*  735 */         k = Math.min(arrayOfByte2.length, 64);
/*  736 */         localObject = new byte[''];
/*      */         
/*  738 */         System.arraycopy(arrayOfByte1, 0, localObject, 0, j);
/*  739 */         System.arraycopy(arrayOfByte2, 0, localObject, j, k);
/*      */       }
/*      */       
/*  742 */       byte[] arrayOfByte3 = this.context;
/*      */       
/*  744 */       this.physicalConn.needLine();
/*  745 */       this.physicalConn.sendPiggyBackedMessages();
/*  746 */       localT4CTTIOtxen.doOTXEN(paramInt1, arrayOfByte3, (byte[])localObject, paramXid.getFormatId(), j, k, this.timeout, paramInt2, 0);
/*      */       
/*  748 */       i = localT4CTTIOtxen.getOutStateFromServer();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  761 */       this.physicalConn.handleIOException(localIOException);
/*      */       
/*  763 */       Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  764 */       ((SQLException)localObject).fillInStackTrace();
/*  765 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  769 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void kputxrec(Xid paramXid, int paramInt1, int paramInt2, SQLException paramSQLException)
/*      */     throws XAException, SQLException
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  786 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */     case 1: 
/*  790 */       i = 3;
/*      */       
/*  792 */       break;
/*      */     
/*      */     case 4: 
/*  795 */       i = 2;
/*      */       
/*  797 */       break;
/*      */     
/*      */     default: 
/*  800 */       i = 0;
/*      */     }
/*      */     
/*      */     
/*  804 */     int j = 0;
/*      */     
/*      */ 
/*  807 */     while (paramInt2-- > 0)
/*      */     {
/*  809 */       j = doTransaction(paramXid, 5, i);
/*      */       
/*  811 */       if (j != 7) {
/*      */         break;
/*      */       }
/*      */       try
/*      */       {
/*  816 */         Thread.sleep(1000L);
/*      */       }
/*      */       catch (Exception localException) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  828 */     if (j == 7)
/*      */     {
/*      */ 
/*      */ 
/*  832 */       throw new XAException(-6);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  841 */     int m = -1;
/*      */     
/*  843 */     switch (j)
/*      */     {
/*      */ 
/*      */     case 3: 
/*  847 */       if (paramInt1 == 1) {
/*  848 */         k = 7;
/*      */       }
/*      */       else
/*      */       {
/*  852 */         k = 8;
/*  853 */         m = -3;
/*      */       }
/*      */       
/*  856 */       break;
/*      */     
/*      */     case 0: 
/*  859 */       if (paramInt1 == 4)
/*      */       {
/*  861 */         k = 8;
/*  862 */         m = -3;
/*      */       }
/*      */       else
/*      */       {
/*  866 */         k = 7;
/*  867 */         if (paramInt1 == 1) {
/*  868 */           m = -4;
/*      */         }
/*      */       }
/*      */       
/*      */       break;
/*      */     case 2: 
/*  874 */       if (paramInt1 == 4)
/*      */       {
/*  876 */         k = 8;
/*      */         
/*      */ 
/*  879 */         m = -6; }
/*  880 */       break;
/*      */     
/*      */     case 5: 
/*  883 */       if (paramInt1 == 4)
/*      */       {
/*  885 */         k = 7;
/*      */       }
/*      */       else
/*      */       {
/*  889 */         m = 7;
/*  890 */         k = 8;
/*      */       }
/*  892 */       break;
/*      */     
/*      */     case 4: 
/*  895 */       if (paramInt1 == 4)
/*      */       {
/*  897 */         k = 7;
/*      */       }
/*      */       else
/*      */       {
/*  901 */         m = 6;
/*  902 */         k = 8;
/*      */       }
/*  904 */       break;
/*      */     
/*      */     case 6: 
/*  907 */       if (paramInt1 == 4)
/*      */       {
/*  909 */         k = 7;
/*      */       }
/*      */       else
/*      */       {
/*  913 */         m = 5;
/*  914 */         k = 8;
/*      */       }
/*      */       
/*  917 */       break;
/*      */     }
/*      */     
/*  920 */     m = -3;
/*  921 */     int k = 8;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  926 */     T4CTTIk2rpc localT4CTTIk2rpc = this.physicalConn.k2rpc;
/*      */     
/*      */     try
/*      */     {
/*  930 */       localT4CTTIk2rpc.doOK2RPC(3, k);
/*      */ 
/*      */     }
/*      */     catch (IOException localIOException)
/*      */     {
/*  935 */       localXAException = new XAException(-7);
/*  936 */       localXAException.initCause(localIOException);
/*  937 */       throw localXAException;
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  942 */       XAException localXAException = new XAException(-6);
/*  943 */       localXAException.initCause(localSQLException);
/*  944 */       throw localXAException;
/*      */     }
/*      */     
/*  947 */     if (m != -1)
/*      */     {
/*      */ 
/*      */ 
/*  951 */       OracleXAException localOracleXAException = null;
/*  952 */       if (paramSQLException != null)
/*      */       {
/*  954 */         localOracleXAException = new OracleXAException(paramSQLException.getErrorCode(), m);
/*  955 */         localOracleXAException.initCause(paramSQLException);
/*      */       }
/*      */       else {
/*  958 */         localOracleXAException = new OracleXAException(0, m);
/*      */       }
/*  960 */       throw localOracleXAException;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   final void setPasswordInternal(String paramString)
/*      */   {
/*  969 */     this.password = paramString;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/*  983 */     return this.physicalConn;
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
/* 1060 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CXAResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */