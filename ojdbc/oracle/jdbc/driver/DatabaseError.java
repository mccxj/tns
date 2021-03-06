/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.BatchUpdateException;
/*      */ import java.sql.ClientInfoStatus;
/*      */ import java.sql.SQLClientInfoException;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLWarning;
/*      */ import java.util.Map;
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
/*      */ public class DatabaseError
/*      */ {
/*      */   private static final int ORAERROR_END_OF_FILE_ON_COM_CHANNEL = 3113;
/*      */   private static final int ORAERROR_NOT_CONNECTED_TO_ORACLE = 3114;
/*      */   private static final int ORAERROR_INIT_SHUTDOWN_IN_PROGRESS = 1033;
/*      */   private static final int ORAERROR_ORACLE_NOT_AVAILABLE = 1034;
/*      */   private static final int ORAERROR_IMMEDIATE_SHUTDOWN_IN_PROGRESS = 1089;
/*      */   private static final int ORAERROR_SHUTDOWN_IN_PROGRESS_NO_CONN = 1090;
/*      */   private static final int ORAERROR_NET_IO_EXCEPTION = 17002;
/*      */   private static final int ORAERROR_NO_MORE_DATA_TO_READ = 17410;
/*      */   private static final int ORAERROR_OALL8_INCONSISTENT_STATE = 17447;
/*      */   private static final int ORAERROR_PROTOCOL_VOILATION = 17401;
/*      */   
/*      */   public static SQLException newSQLException(OracleConnection paramOracleConnection, String paramString, int paramInt)
/*      */   {
/*   88 */     return newSQLException(paramOracleConnection, paramString, paramInt, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static SQLException newSQLException(OracleConnection paramOracleConnection, String paramString, int paramInt, Throwable paramThrowable)
/*      */   {
/*   98 */     SQLStateMapping localSQLStateMapping = findSQLStateMapping(paramInt);
/*      */     
/*      */ 
/*  101 */     if (paramOracleConnection != null)
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*      */ 
/*  107 */         switch (paramInt)
/*      */         {
/*      */ 
/*      */ 
/*      */         case 1033: 
/*      */         case 1034: 
/*      */         case 1089: 
/*      */         case 1090: 
/*      */         case 3113: 
/*      */         case 3114: 
/*      */         case 17002: 
/*      */         case 17401: 
/*      */         case 17410: 
/*      */         case 17447: 
/*  121 */           paramOracleConnection.setUsable(false);
/*  122 */           break;
/*      */         
/*      */         default: 
/*  125 */           if (paramOracleConnection.isClosed()) {
/*  126 */             paramOracleConnection.setUsable(false);
/*      */           }
/*      */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       catch (SQLException localSQLException1)
/*      */       {
/*  134 */         paramOracleConnection.setUsable(false);
/*      */       }
/*      */       
/*  137 */       if ((paramInt >= 401) && (paramInt <= 452)) {
/*      */         try {
/*  139 */           ((PhysicalConnection)paramOracleConnection).internalClose();
/*      */         }
/*      */         catch (SQLException localSQLException2) {}
/*      */       }
/*      */     }
/*  144 */     SQLException localSQLException3 = localSQLStateMapping.newSQLException(paramString, paramInt);
/*  145 */     if (paramThrowable != null) {
/*  146 */       localSQLException3.initCause(paramThrowable);
/*      */     }
/*  148 */     return localSQLException3;
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
/*      */   public static SQLException newSQLException(OracleConnection paramOracleConnection, int paramInt)
/*      */   {
/*  166 */     int i = getVendorCode(paramInt);
/*  167 */     String str = findMessage(paramInt, null);
/*  168 */     return newSQLException(paramOracleConnection, str, i);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, String paramString, int paramInt, Throwable paramThrowable)
/*      */   {
/*  191 */     return newSQLException(paramOracleConnection, paramString, paramInt, paramThrowable);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, String paramString, int paramInt)
/*      */   {
/*  200 */     return newSQLException(paramOracleConnection, paramString, paramInt, null);
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
/*      */   public static SQLException createSqlException(int paramInt, Object paramObject)
/*      */   {
/*  224 */     return createSqlException(null, paramInt, paramObject);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, int paramInt, Object paramObject, Throwable paramThrowable)
/*      */   {
/*  250 */     if ((paramInt == 0) || (paramInt == 13))
/*      */     {
/*  252 */       return null;
/*      */     }
/*      */     
/*  255 */     String str = findMessage(paramInt, paramObject);
/*  256 */     int i = getVendorCode(paramInt);
/*      */     
/*  258 */     return createSqlException(paramOracleConnection, str, i, paramThrowable);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, int paramInt, Object paramObject)
/*      */   {
/*  266 */     return createSqlException(paramOracleConnection, paramInt, paramObject, null);
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
/*      */   public static SQLException createSqlException(OracleSQLException paramOracleSQLException)
/*      */   {
/*  291 */     return createSqlException(null, paramOracleSQLException);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, OracleSQLException paramOracleSQLException)
/*      */   {
/*  320 */     int i = paramOracleSQLException.getErrorCode();
/*  321 */     if ((i == 0) || (i == 13))
/*      */     {
/*  323 */       return null;
/*      */     }
/*  325 */     String str = paramOracleSQLException.getMessage();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  331 */     int j = getVendorCode(i);
/*  332 */     SQLException localSQLException = newSQLException(paramOracleConnection, str, j);
/*  333 */     localSQLException.initCause(paramOracleSQLException);
/*  334 */     return localSQLException;
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, SQLException paramSQLException, int paramInt, Object paramObject)
/*      */   {
/*  364 */     if ((paramInt == 0) || (paramInt == 13))
/*      */     {
/*  366 */       return null;
/*      */     }
/*      */     
/*  369 */     String str = findMessage(paramInt, paramObject);
/*  370 */     int i = getVendorCode(paramInt);
/*      */     
/*  372 */     SQLException localSQLException = newSQLException(paramOracleConnection, str, i);
/*      */     
/*  374 */     localSQLException.setNextException(paramSQLException);
/*  375 */     return localSQLException;
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
/*      */   public static SQLException createSqlException(int paramInt)
/*      */   {
/*  393 */     return createSqlException(null, paramInt);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, int paramInt)
/*      */   {
/*  413 */     return createSqlException(paramOracleConnection, paramInt, null);
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
/*      */   public static SQLException createSqlException(IOException paramIOException)
/*      */   {
/*  428 */     return createSqlException(null, paramIOException);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, IOException paramIOException)
/*      */   {
/*  447 */     String str = paramIOException.getMessage();
/*      */     
/*  449 */     int j = 0;
/*      */     
/*      */ 
/*      */     int i;
/*      */     
/*  454 */     if ((str != null) && ((i = str.indexOf("ORA-")) != -1))
/*      */     {
/*      */ 
/*  457 */       i += 4;
/*      */       
/*  459 */       int k = str.length() - 1;
/*  460 */       int m = str.indexOf(":", i);
/*  461 */       int n = str.indexOf(",", i);
/*      */       
/*  463 */       if (m != -1) {
/*  464 */         k = m;
/*      */       }
/*  466 */       if (n != -1) {
/*  467 */         k = Math.min(n, k);
/*      */       }
/*      */       try
/*      */       {
/*  471 */         j = Integer.parseInt(str.substring(i, k));
/*      */       }
/*      */       catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
/*  474 */         return createSqlException(paramOracleConnection, 2, str, paramIOException);
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException) {
/*  477 */         return createSqlException(paramOracleConnection, 2, str, paramIOException);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  482 */       return createSqlException(paramOracleConnection, 2, str, paramIOException);
/*      */     }
/*  484 */     return createSqlException(paramOracleConnection, str, j, paramIOException);
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
/*      */   public static SQLException createSqlException(Exception paramException)
/*      */   {
/*  501 */     return createSqlException(null, paramException);
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
/*      */   public static SQLException createSqlException(OracleConnection paramOracleConnection, Exception paramException)
/*      */   {
/*  520 */     return createSqlException(paramOracleConnection, paramException.getMessage(), 1, paramException);
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
/*      */   public static void addSqlException(SQLException paramSQLException, String paramString, int paramInt)
/*      */   {
/*  537 */     SQLException localSQLException = newSQLException(null, paramString, paramInt);
/*  538 */     paramSQLException.setNextException(localSQLException);
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
/*      */   public static BatchUpdateException createBatchUpdateException(SQLException paramSQLException, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  564 */     int i = 0;
/*  565 */     int[] arrayOfInt = null;
/*      */     
/*  567 */     if (paramInt < 0)
/*      */     {
/*  569 */       paramInt = 0;
/*      */     }
/*      */     
/*  572 */     if (paramArrayOfInt == null)
/*      */     {
/*  574 */       arrayOfInt = new int[0];
/*      */     }
/*  576 */     else if (paramInt >= paramArrayOfInt.length)
/*      */     {
/*  578 */       arrayOfInt = paramArrayOfInt;
/*      */     }
/*      */     else
/*      */     {
/*  582 */       arrayOfInt = new int[paramInt];
/*      */       
/*  584 */       for (i = 0; i < paramInt; i++)
/*      */       {
/*  586 */         arrayOfInt[i] = paramArrayOfInt[i];
/*      */       }
/*      */     }
/*  589 */     BatchUpdateException localBatchUpdateException = new BatchUpdateException(paramSQLException.getMessage(), paramSQLException.getSQLState(), paramSQLException.getErrorCode(), arrayOfInt);
/*      */     
/*  591 */     localBatchUpdateException.setNextException(paramSQLException);
/*  592 */     return localBatchUpdateException;
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
/*      */   public static BatchUpdateException createBatchUpdateException(String paramString1, String paramString2, int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*      */   {
/*  621 */     int i = 0;
/*  622 */     int[] arrayOfInt = null;
/*      */     
/*  624 */     if (paramInt2 < 0)
/*      */     {
/*  626 */       paramInt2 = 0;
/*      */     }
/*      */     
/*  629 */     if (paramArrayOfInt == null)
/*      */     {
/*  631 */       arrayOfInt = new int[0];
/*      */     }
/*  633 */     else if (paramInt2 >= paramArrayOfInt.length)
/*      */     {
/*  635 */       arrayOfInt = paramArrayOfInt;
/*      */     }
/*      */     else
/*      */     {
/*  639 */       arrayOfInt = new int[paramInt2];
/*      */       
/*  641 */       for (i = 0; i < paramInt2; i++)
/*      */       {
/*  643 */         arrayOfInt[i] = paramArrayOfInt[i];
/*      */       }
/*      */     }
/*  646 */     return new BatchUpdateException(paramString1, paramString2, paramInt1, arrayOfInt);
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
/*      */   public static BatchUpdateException createBatchUpdateException(int paramInt1, Object paramObject, int paramInt2, int[] paramArrayOfInt)
/*      */   {
/*  675 */     if (paramInt1 == 0)
/*      */     {
/*  677 */       return null;
/*      */     }
/*      */     
/*  680 */     String str = findMessage(paramInt1, paramObject);
/*  681 */     int i = getVendorCode(paramInt1);
/*      */     
/*  683 */     return createBatchUpdateException(str, null, i, paramInt2, paramArrayOfInt);
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
/*      */   public static BatchUpdateException createBatchUpdateException(int paramInt1, int paramInt2, int[] paramArrayOfInt)
/*      */   {
/*  708 */     return createBatchUpdateException(paramInt1, null, paramInt2, paramArrayOfInt);
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
/*      */   public static SQLException createUnsupportedFeatureSqlException()
/*      */   {
/*  721 */     return createSqlException(null, 23);
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
/*      */   public static SQLException createUnsupportedFeatureSqlException(String paramString)
/*      */   {
/*  734 */     return createSqlException(null, 23, paramString);
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
/*      */   public static SQLClientInfoException createSQLClientInfoException(int paramInt, Map<String, ClientInfoStatus> paramMap, Throwable paramThrowable)
/*      */   {
/*  755 */     if ((paramInt == 0) || (paramInt == 13))
/*      */     {
/*  757 */       return null;
/*      */     }
/*      */     
/*  760 */     int i = getVendorCode(paramInt);
/*  761 */     String str1 = findMessage(paramInt, null);
/*  762 */     SQLStateMapping localSQLStateMapping = findSQLStateMapping(i);
/*  763 */     String str2 = localSQLStateMapping.sqlState;
/*  764 */     return new SQLClientInfoException(str1, str2, i, paramMap, paramThrowable);
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
/*      */   public static IOException createIOException(SQLException paramSQLException)
/*      */   {
/*  786 */     return new IOException(paramSQLException.getMessage(), paramSQLException);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  793 */   private static boolean loadedMessages = false;
/*  794 */   private static Message message = null;
/*  795 */   private static String msgClassName = "oracle.jdbc.driver.Message11";
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static String findMessage(int paramInt, Object paramObject)
/*      */   {
/*  809 */     String str = getMsgKey(paramInt);
/*  810 */     return findMessage(str, paramObject);
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
/*      */   static String findMessage(String paramString, Object paramObject)
/*      */   {
/*  827 */     String str = null;
/*  828 */     if (!loadedMessages)
/*      */     {
/*      */       try
/*      */       {
/*  832 */         message = (Message)Class.forName(msgClassName).newInstance();
/*  833 */         loadedMessages = true;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  840 */         if (loadedMessages) {}
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  848 */         if (message != null) {
/*      */           break label137;
/*      */         }
/*      */       }
/*      */       catch (ClassNotFoundException localClassNotFoundException) {}catch (IllegalAccessException localIllegalAccessException) {}catch (InstantiationException localInstantiationException) {}finally
/*      */       {
/*  840 */         if (loadedMessages) {}
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  850 */     if (paramObject == null)
/*      */     {
/*  852 */       str = paramString + ": (no message for error)";
/*      */     }
/*      */     else
/*      */     {
/*  856 */       str = paramString + ": (no message for error) " + paramObject; return str;
/*      */       
/*      */ 
/*      */       label137:
/*      */       
/*  861 */       str = message.msg(paramString, paramObject);
/*      */     }
/*  863 */     return str;
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
/*      */   public static SQLWarning newSqlWarning(String paramString, int paramInt)
/*      */   {
/*  889 */     return addSqlWarning(null, paramString, paramInt);
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
/*      */   public static SQLWarning newSqlWarning(int paramInt, Object paramObject)
/*      */   {
/*  909 */     return addSqlWarning(null, paramInt, paramObject);
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
/*      */   public static SQLWarning newSqlWarning(int paramInt)
/*      */   {
/*  923 */     return addSqlWarning(null, paramInt);
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
/*      */   public static SQLWarning addSqlWarning(SQLWarning paramSQLWarning, String paramString, int paramInt)
/*      */   {
/*  944 */     String str = ErrorToSQLState(paramInt);
/*  945 */     SQLWarning localSQLWarning = new SQLWarning(paramString, str, paramInt);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  950 */     return addSqlWarning(paramSQLWarning, localSQLWarning);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static SQLWarning addSqlWarning(SQLWarning paramSQLWarning1, SQLWarning paramSQLWarning2)
/*      */   {
/*  961 */     if (paramSQLWarning1 == null)
/*  962 */       return paramSQLWarning2;
/*  963 */     paramSQLWarning1.setNextWarning(paramSQLWarning2);
/*      */     
/*  965 */     return paramSQLWarning1;
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
/*      */   public static SQLWarning addSqlWarning(SQLWarning paramSQLWarning, int paramInt, Object paramObject)
/*      */   {
/*  985 */     if ((paramInt == 0) || (paramInt == 13))
/*      */     {
/*  987 */       return paramSQLWarning;
/*      */     }
/*      */     
/*  990 */     String str = findMessage(paramInt, paramObject);
/*  991 */     int i = getVendorCode(paramInt);
/*      */     
/*  993 */     return addSqlWarning(paramSQLWarning, "Warning: " + str, i);
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
/*      */   public static SQLWarning addSqlWarning(SQLWarning paramSQLWarning, int paramInt)
/*      */   {
/* 1008 */     return addSqlWarning(paramSQLWarning, paramInt, null);
/*      */   }
/*      */   
/*      */ 
/* 1012 */   static final SQLStateMapping DEFAULT_SQLSTATE = new SQLStateMapping(Integer.MIN_VALUE, Integer.MAX_VALUE, "99999", 0);
/*      */   
/*      */ 
/* 1015 */   static final SQLStateMapping[] mappings = SQLStateMapping.getMappings();
/*      */   
/*      */   public static final int JDBC_ERROR_BASE = 17000;
/*      */   
/*      */   public static final int JDBC_MAX_ERRORS = 500;
/*      */   public static final int EOJ_SUCCESS = 0;
/*      */   public static final int EOJ_ERROR = 1;
/*      */   public static final int EOJ_IOEXCEPTION = 2;
/*      */   
/* 1024 */   public static String ErrorToSQLState(int paramInt) { return findSQLStateMapping(paramInt).sqlState; }
/*      */   
/*      */   public static final int EOJ_INVALID_COLUMN_INDEX = 3;
/*      */   public static final int EOJ_INVALID_COLUMN_TYPE = 4;
/*      */   public static final int EOJ_UNSUPPORTED_COLUMN_TYPE = 5;
/*      */   
/* 1030 */   static SQLStateMapping findSQLStateMapping(int paramInt) { for (int i = 0; i < mappings.length; i++)
/* 1031 */       if (mappings[i].isIncluded(paramInt))
/* 1032 */         return mappings[i];
/* 1033 */     return DEFAULT_SQLSTATE;
/*      */   }
/*      */   
/*      */   public static final int EOJ_INVALID_COLUMN_NAME = 6;
/*      */   public static final int EOJ_INVALID_DYNAMIC_COLUMN = 7;
/*      */   public static final int EOJ_CLOSED_CONNECTION = 8;
/*      */   public static final int EOJ_CLOSED_STATEMENT = 9;
/*      */   public static final int EOJ_CLOSED_RESULTSET = 10;
/*      */   public static final int EOJ_EXHAUSTED_RESULTSET = 11;
/*      */   public static final int EOJ_TYPE_CONFLICT = 12;
/*      */   public static final int EOJ_WAS_NULL = 13;
/*      */   public static final int EOJ_RESULTSET_BEFORE_FIRST_ROW = 14;
/*      */   public static final int EOJ_STATEMENT_WAS_CANCELLED = 15;
/*      */   public static final int EOJ_STATEMENT_TIMED_OUT = 16;
/*      */   public static final int EOJ_CURSOR_ALREADY_INITIALIZED = 17;
/*      */   public static final int EOJ_INVALID_CURSOR = 18;
/*      */   public static final int EOJ_CAN_ONLY_DESCRIBE_A_QUERY = 19;
/*      */   public static final int EOJ_INVALID_ROW_PREFETCH = 20;
/*      */   public static final int EOJ_MISSING_DEFINES = 21;
/*      */   
/*      */   public static int getVendorCode(int paramInt)
/*      */   {
/* 1055 */     if (paramInt >= 500) {}
/*      */     
/*      */ 
/*      */ 
/* 1059 */     return 17000 + paramInt;
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
/*      */   static String getMsgKey(int paramInt)
/*      */   {
/* 1074 */     int i = getVendorCode(paramInt);
/* 1075 */     String str = "ORA-" + Integer.toString(i);
/* 1076 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */   public static final int EOJ_MISSING_DEFINES_AT_INDEX = 22;
/*      */   
/*      */   public static final int EOJ_UNSUPPORTED_FEATURE = 23;
/*      */   
/*      */   public static final int EOJ_NO_DATA_READ = 24;
/*      */   
/*      */   public static final int EOJ_IS_DEFINES_NULL_ERROR = 25;
/*      */   
/*      */   public static final int EOJ_NUMERIC_OVERFLOW = 26;
/*      */   
/*      */   public static final int EOJ_STREAM_CLOSED = 27;
/*      */   
/*      */   public static final int EOJ_NO_NEW_DEFINE_IF_RESULT_SET_NOT_CLOSED = 28;
/*      */   
/*      */   public static final int EOJ_READ_ONLY = 29;
/*      */   
/*      */   public static final int EOJ_INVALID_TRANSLEVEL = 30;
/*      */   
/*      */   public static final int EOJ_AUTO_CLOSE_ONLY = 31;
/*      */   
/*      */   public static final int EOJ_ROW_PREFETCH_NOT_ZERO = 32;
/*      */   
/*      */   public static final int EOJ_MALFORMED_SQL92 = 33;
/*      */   
/*      */   public static final int EOJ_NON_SUPPORTED_SQL92_TOKEN = 34;
/*      */   
/*      */   public static final int EOJ_NON_SUPPORTED_CHAR_SET = 35;
/*      */   
/*      */   public static final int EOJ_ORACLE_NUMBER_EXCEPTION = 36;
/*      */   
/*      */   public static final int EOJ_FAIL_CONVERSION_UTF8_TO_UCS2 = 37;
/*      */   
/*      */   public static final int EOJ_CONVERSION_BYTE_ARRAY_ERROR = 38;
/*      */   
/*      */   public static final int EOJ_CONVERSION_CHAR_ARRAY_ERROR = 39;
/*      */   
/*      */   public static final int EOJ_SUB_SUB_PROTOCOL_ERROR = 40;
/*      */   
/*      */   public static final int EOJ_INVALID_IN_OUT_BINDS = 41;
/*      */   
/*      */   public static final int EOJ_INVALID_BATCH_VALUE = 42;
/*      */   
/*      */   public static final int EOJ_INVALID_STREAM_SIZE = 43;
/*      */   
/*      */   public static final int EOJ_DATASET_ITEMS_NOT_ALLOCATED = 44;
/*      */   
/*      */   public static final int EOJ_BEYOND_BINDS_BATCH = 45;
/*      */   
/*      */   public static final int EOJ_INVALID_RANK = 46;
/*      */   
/*      */   public static final int EOJ_TDS_FORMAT_ERROR = 47;
/*      */   
/*      */   public static final int EOJ_UNDEFINED_TYPE = 48;
/*      */   
/*      */   public static final int EOJ_INCONSISTENT_ADT = 49;
/*      */   
/*      */   public static final int EOJ_NOSUCHELEMENT = 50;
/*      */   
/*      */   public static final int EOJ_NOT_AN_OBJECT_TYPE = 51;
/*      */   
/*      */   public static final int EOJ_INVALID_REF = 52;
/*      */   
/*      */   public static final int EOJ_INVALID_SIZE = 53;
/*      */   
/*      */   public static final int EOJ_INVALID_LOB_LOCATOR = 54;
/*      */   
/*      */   public static final int EOJ_FAIL_CONVERSION_CHARACTER = 55;
/*      */   
/*      */   public static final int EOJ_UNSUPPORTED_CHARSET = 56;
/*      */   
/*      */   public static final int EOJ_CLOSED_LOB = 57;
/*      */   
/*      */   public static final int EOJ_INVALID_NLS_RATIO = 58;
/*      */   
/*      */   public static final int EOJ_CONVERSION_JAVA_ERROR = 59;
/*      */   
/*      */   public static final int EOJ_FAIL_CREATE_DESC = 60;
/*      */   
/*      */   public static final int EOJ_NO_DESCRIPTOR = 61;
/*      */   
/*      */   public static final int EOJ_INVALID_REF_CURSOR = 62;
/*      */   
/*      */   public static final int EOJ_NOT_IN_A_TRANSACTION = 63;
/*      */   
/*      */   public static final int EOJ_DATABASE_IS_NULL = 64;
/*      */   
/*      */   public static final int EOJ_CONV_WAS_NULL = 65;
/*      */   
/*      */   public static final int EOJ_ACCESS_SPECIFIC_IMPL = 66;
/*      */   
/*      */   public static final int EOJ_INVALID_URL = 67;
/*      */   
/*      */   public static final int EOJ_INVALID_ARGUMENTS = 68;
/*      */   
/*      */   public static final int EOJ_USE_XA_EXPLICIT = 69;
/*      */   
/*      */   public static final int EOJ_INVALID_DATASIZE_LENGTH = 70;
/*      */   
/*      */   public static final int EOJ_EXCEEDED_VARRAY_LENGTH = 71;
/*      */   
/*      */   public static final int EOJ_VALUE_TOO_BIG = 72;
/*      */   
/*      */   public static final int EOJ_INVALID_NAME_PATTERN = 74;
/*      */   
/*      */   public static final int EOJ_INVALID_FORWARD_RSET_OP = 75;
/*      */   
/*      */   public static final int EOJ_INVALID_READONLY_RSET_OP = 76;
/*      */   
/*      */   public static final int EOJ_FAIL_REF_SETVALUE = 77;
/*      */   
/*      */   public static final int EOJ_CONNECTIONS_ALREADY_EXIST = 78;
/*      */   
/*      */   public static final int EOJ_USER_CREDENTIALS_FAIL = 79;
/*      */   
/*      */   public static final int EOJ_INVALID_BATCH_COMMAND = 80;
/*      */   
/*      */   public static final int EOJ_BATCH_ERROR = 81;
/*      */   
/*      */   public static final int EOJ_NO_CURRENT_ROW = 82;
/*      */   
/*      */   public static final int EOJ_NOT_ON_INSERT_ROW = 83;
/*      */   
/*      */   public static final int EOJ_ON_INSERT_ROW = 84;
/*      */   
/*      */   public static final int EOJ_UPDATE_CONFLICTS = 85;
/*      */   
/*      */   public static final int EOJ_NULL_INSERET_ROW_VALUE = 86;
/*      */   
/*      */   public static final int WARN_IGNORE_FETCH_DIRECTION = 87;
/*      */   
/*      */   public static final int EOJ_UNSUPPORTED_SYNTAX = 88;
/*      */   
/*      */   public static final int EOJ_INTERNAL_ERROR = 89;
/*      */   
/*      */   public static final int EOJ_OPER_NOT_ALLOWED = 90;
/*      */   
/*      */   public static final int WARN_ALTERNATE_RSET_TYPE = 91;
/*      */   
/*      */   public static final int EOJ_NO_JDBC_AT_END_OF_CALL = 92;
/*      */   
/*      */   public static final int EOJ_WARN_SUCCESS_WITH_INFO = 93;
/*      */   
/*      */   public static final int EOJ_VERSION_MISMATCH = 94;
/*      */   
/*      */   public static final int EOJ_NO_STMT_CACHE_SIZE = 95;
/*      */   
/*      */   public static final int EOJ_INVALID_ELEMENT_TYPE = 97;
/*      */   
/*      */   public static final int EOJ_INVALID_EMPTYLOB_OP = 98;
/*      */   
/*      */   public static final int EOJ_INVALID_INDEXTABLE_ARRAY_LENGTH = 99;
/*      */   
/*      */   public static final int EOJ_INVALID_JAVA_OBJECT = 100;
/*      */   
/*      */   public static final int EOJ_CONNECTIONPOOL_INVALID_PROPERTIES = 101;
/*      */   
/*      */   public static final int EOJ_BFILE_IS_READONLY = 102;
/*      */   
/*      */   public static final int EOJ_WRONG_CONNECTION_TYPE_FOR_METHOD = 103;
/*      */   
/*      */   public static final int EOJ_NULL_SQL_STRING = 104;
/*      */   
/*      */   public static final int EOJ_SESSION_TZ_NOT_SET = 105;
/*      */   
/*      */   public static final int EOJ_CONNECTIONPOOL_INVALID_CONFIG = 106;
/*      */   
/*      */   public static final int EOJ_CONNECTIONPOOL_INVALID_PROXY_TYPE = 107;
/*      */   
/*      */   public static final int WARN_DEFINE_COLUMN_TYPE = 108;
/*      */   
/*      */   public static final int EOJ_STANDARD_ENCODING_NOT_FOUND = 109;
/*      */   
/*      */   public static final int EOJ_THIN_WARNING = 110;
/*      */   
/*      */   public static final int EOJ_WARN_CONN_CACHE_TIMEOUT = 111;
/*      */   
/*      */   public static final int EOJ_WARN_THREAD_TIMEOUT_INTERVAL = 112;
/*      */   
/*      */   public static final int EOJ_WARN_THREAD_INTERVAL_TOO_BIG = 113;
/*      */   
/*      */   public static final int EOJ_LOCAL_COMMIT_IN_GLOBAL_TXN = 114;
/*      */   
/*      */   public static final int EOJ_LOCAL_ROLLBACK_IN_GLOBAL_TXN = 115;
/*      */   
/*      */   public static final int EOJ_AUTOCOMMIT_IN_GLOBAL_TXN = 116;
/*      */   public static final int EOJ_SETSVPT_IN_GLOBAL_TXN = 117;
/*      */   public static final int EOJ_GETID_FOR_NAMED_SVPT = 118;
/*      */   public static final int EOJ_GETNAME_FOR_UNNAMED_SVPT = 119;
/*      */   public static final int EOJ_SETSVPT_WITH_AUTOCOMMIT = 120;
/*      */   public static final int EOJ_ROLLBACK_WITH_AUTOCOMMIT = 121;
/*      */   public static final int EOJ_ROLLBACK_TO_SVPT_IN_GLOBAL_TXN = 122;
/*      */   public static final int EOJ_INVALID_STMT_CACHE_SIZE = 123;
/*      */   public static final int EOJ_WARN_CACHE_INACTIVITY_TIMEOUT = 124;
/*      */   public static final int EOJ_IMPROPER_STATEMENT_TYPE = 125;
/*      */   public static final int EOJ_FIXED_WAIT_TIMEOUT = 126;
/*      */   public static final int EOJ_WARN_CACHE_FIXEDWAIT_TIMEOUT = 127;
/*      */   public static final int EOJ_INVALID_QUERY_STRING = 128;
/*      */   public static final int EOJ_INVALID_DML_STRING = 129;
/*      */   public static final int EOJ_QUERY_TIMEOUT_CLASS_NOT_FOUND = 130;
/*      */   public static final int EOJ_QUERY_TIMEOUT_INVALID_STATE = 131;
/*      */   public static final int EOJ_INVALID_OBJECT_TO_CONVERT = 132;
/*      */   public static final int EOJ_PARAMETER_NAME_TOO_LONG = 134;
/*      */   public static final int EOJ_PARAMETER_NAME_APPEARS_MORE_THAN_ONCE = 135;
/*      */   public static final int EOJ_MALFORMED_DLNK_URL = 136;
/*      */   public static final int EOJ_INVALID_CACHE_ENABLED_DATASOURCE = 137;
/*      */   public static final int EOJ_INVALID_CONNECTION_CACHE_NAME = 138;
/*      */   public static final int EOJ_INVALID_CONNECTION_CACHE_PROPERTIES = 139;
/*      */   public static final int EOJ_CONNECTION_CACHE_ALREADY_EXISTS = 140;
/*      */   public static final int EOJ_CONNECTION_CACHE_DOESNOT_EXIST = 141;
/*      */   public static final int EOJ_CONNECTION_CACHE_DISABLED = 142;
/*      */   public static final int EOJ_INVALID_CACHED_CONNECTION = 143;
/*      */   public static final int EOJ_STMT_NOT_EXECUTED = 144;
/*      */   public static final int EOJ_INVALID_ONS_EVENT = 145;
/*      */   public static final int EOJ_INVALID_ONS_EVENT_VERSION = 146;
/*      */   public static final int EOJ_UNKNOWN_PARAMETER_NAME = 147;
/*      */   public static final int EOJ_T4C_ONLY = 148;
/*      */   public static final int EOJ_ALREADY_PROXY = 149;
/*      */   public static final int EOJ_PROXY_WRONG_ARG = 150;
/*      */   public static final int EOJ_CLOB_TOO_LARGE = 151;
/*      */   public static final int EOJ_METHOD_FOR_LOGICAL_CONNECTION_ONLY = 152;
/*      */   public static final int EOJ_METHOD_FOR_PHYSICAL_CONNECTION_ONLY = 153;
/*      */   public static final int EOJ_EX_MAP_ORACLE_TO_UCS = 154;
/*      */   public static final int EOJ_EX_MAP_UCS_TO_ORACLE = 155;
/*      */   public static final int EOJ_E2E_METRIC_ARRAY_SIZE = 156;
/*      */   public static final int EOJ_SETSTRING_LIMIT = 157;
/*      */   public static final int EOJ_INVALID_DURATION = 158;
/*      */   public static final int EOJ_E2E_METRIC_TOO_LONG = 159;
/*      */   public static final int EOJ_E2E_SEQUENCE_NUMBER_OUT_OF_RANGE = 160;
/*      */   public static final int EOJ_INVALID_TXN_MODE = 161;
/*      */   public static final int EOJ_UNSUPPORTED_HOLDABILITY = 162;
/*      */   public static final int EOJ_GETXACONN_WHEN_CACHE_ENABLED = 163;
/*      */   public static final int EOJ_GETXARESOURCE_FROM_PHYSICAL_CONN = 164;
/*      */   public static final int EOJ_PRIVATE_JDBC_NOT_PRESENT = 165;
/*      */   public static final int EOJ_NO_FETCH_ON_PLSQL = 166;
/*      */   public static final int EOJ_ORACLEPKI_JAR_NOT_FOUND = 167;
/*      */   public static final int EOJ_PKI_WALLET_ERROR = 168;
/*      */   public static final int EOJ_NO_STREAM_BIND_ALLOWED = 169;
/*      */   public static final int EOJ_APP_CTXT_NULL_NAMESPACE = 170;
/*      */   public static final int EOJ_APP_CTXT_ATTR_TOO_LONG = 171;
/*      */   public static final int EOJ_APP_CTXT_VAL_TOO_LONG = 172;
/*      */   public static final int EOJ_DML_RETURNING_PARAM_NOT_REGISTERED = 173;
/*      */   public static final int EOJ_APP_CTXT_INVALID_NAMESPACE = 174;
/*      */   public static final int EOJ_REMOTE_ONS_CONFIG_ERROR = 175;
/*      */   public static final int EOJ_UNKNOWN_LOCALE = 176;
/*      */   public static final int EOJ_DOES_NOT_WRAP_INTERFACE = 177;
/*      */   public static final int EOJ_ANYTYPE_PICKLER = 178;
/*      */   public static final int EOJ_KOTAD_MAGIC_NUMBER_ERROR = 179;
/*      */   public static final int EOJ_KOTAD_FORMAT_ERROR = 180;
/*      */   public static final int EOJ_CHARACTER_CONVERTER_GENERAL_ERROR = 181;
/*      */   public static final int EOJ_CHARACTER_CONVERTER_OVERRUN_ERROR = 182;
/*      */   public static final int EOJ_CHARACTER_CONVERTER_IMPOSSIBLE_ERROR = 183;
/*      */   public static final int EOJ_INCORRECT_FORM_OF_USE = 184;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_DEFAULT_MISSING = 185;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_ACCESSMODE_MISSING = 186;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_INVALID_TYPE = 187;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_REFLECTION_ILLEGAL_ACCESS = 188;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_MISSING_INSTANCE_VARIABLE = 189;
/*      */   public static final int EOJ_CONNECTION_PROPERTIES_FORMAT_ERROR = 190;
/*      */   public static final int EOJ_INVALID_COMMIT_OPTIONS = 191;
/*      */   public static final int EOJ_FREED_LOB = 192;
/*      */   public static final int EOJ_INVALID_AQ_MESSAGE_FORMAT = 193;
/*      */   public static final int EOJ_MARK_RESET_NOT_SUPPORTED = 194;
/*      */   public static final int EOJ_INVALID_MARK = 195;
/*      */   public static final int EOJ_READAHEAD_LIMIT_EXCEEDED = 196;
/*      */   public static final int EOJ_INCORRECT_PARAMETER_USAGE = 197;
/*      */   public static final int EOJ_SESSION_TIME_ZONE_NOT_SET = 198;
/*      */   public static final int EOJ_SESSION_TIME_ZONE_NOT_SUPPORTED = 199;
/*      */   public static final int EOJ_HETEROXA_GET_UTF_OPENSTR = 200;
/*      */   public static final int EOJ_HETEROXA_GET_UTF_CLOSESTR = 201;
/*      */   public static final int EOJ_HETEROXA_GET_UTF_RMNAME = 202;
/*      */   public static final int EOJ_HETEROXA_JHANDLE_SIZE = 203;
/*      */   public static final int EOJ_HETEROXA_ARRAY_TOO_SHORT = 204;
/*      */   public static final int EOJ_HETEROXA_SVCCTX_HANDLE = 205;
/*      */   public static final int EOJ_HETEROXA_ENV_HANDLE = 206;
/*      */   public static final int EOJ_HETEROXA_NULL_TNSENTRY = 207;
/*      */   public static final int EOJ_HETEROXA_OPEN_RMERR = 213;
/*      */   public static final int EOJ_HETEROXA_OPEN_INVAL = 215;
/*      */   public static final int EOJ_HETEROXA_OPEN_PROTO = 216;
/*      */   public static final int EOJ_HETEROXA_CLOSE_RMERR = 233;
/*      */   public static final int EOJ_HETEROXA_CLOSE_INVAL = 235;
/*      */   public static final int EOJ_HETEROXA_CLOSE_PROTO = 236;
/*      */   static final int NEXT_ERROR = 240;
/*      */   public static final int EOJ_NTF_UNKNOWN_LOCALHOST = 240;
/*      */   public static final int EOJ_NTF_SECURITY_MANAGER = 241;
/*      */   public static final int EOJ_NTF_TCP_OPTION = 242;
/*      */   public static final int EOJ_NTF_TIMEOUT_OPTION = 243;
/*      */   public static final int EOJ_DCN_CHANGELAG_OPTION = 244;
/*      */   public static final int EOJ_NTF_DELETE_REG_WRONG_INSTANCE = 245;
/*      */   public static final int EOJ_NTF_NULL_LISTENER = 246;
/*      */   public static final int EOJ_NTF_PLSQL_LISTENER = 247;
/*      */   public static final int EOJ_NTF_DUP_LISTENER = 248;
/*      */   public static final int EOJ_NTF_RM_MISSING_LISTENER = 249;
/*      */   public static final int EOJ_NTF_TCPPORT_ALREADY_USED = 250;
/*      */   public static final int EOJ_NTF_CLOSED_REGISTRATION = 251;
/*      */   public static final int EOJ_AQ_UNDEFINED_PAYLOAD_TYPE = 252;
/*      */   public static final int EOJ_INVALID_NAME_FOR_CLIENTINFO = 253;
/*      */   public static final int EOJ_OUT_OF_MEMORY_ERROR = 254;
/*      */   public static final int EOJ_CANNOT_DISABLE_FCF = 255;
/*      */   public static final int EOJ_INSTANCE_PROP_NOT_AVAILABLE = 256;
/*      */   public static final int EOJ_INVALID_DRIVER_NAME_ATTR = 257;
/*      */   public static final int EOJ_SYNONYM_LOOP_DETECTED = 258;
/*      */   public static final int EOJ_MISSING_JAR_FOR_XML = 259;
/*      */   public static final int EOJ_READ_EMPTY_SQLXML = 260;
/*      */   public static final int EOJ_NOT_READABLE_SQLXML = 261;
/*      */   public static final int EOJ_NOT_WRITEABLE_SQLXML = 262;
/*      */   public static final int EOJ_UNSUPPORTED_SQLXML_RESULT_TYPE = 263;
/*      */   public static final int EOJ_UNSUPPORTED_SQLXML_SOURCE_TYPE = 264;
/*      */   public static final int EOJ_INVALID_TIMEZONE_NAME = 265;
/*      */   public static final int EOJ_USER_STREAM_BIND_EXCEPTION = 266;
/*      */   public static final int EOJ_INVALID_LOB_PREFETCH_SIZE = 267;
/*      */   public static final int EOJ_INVALID_DATE_YEAR = 268;
/*      */   public static final int EOJ_OCI_FATAL_ERROR = 269;
/*      */   public static final int EOJ_DUPLICATE_STREAM_PARAMETER = 270;
/*      */   public static final int EOJ_SETPLSQLINDEXTABLE_NULL_ARRAY = 271;
/*      */   public static final int EOJ_SETPLSQLINDEXTABLE_ZERO_ARRAY = 272;
/*      */   public static final int EOJ_RESULTSET_MAXROWS_LIMIT_REACHED = 275;
/*      */   private static final int EOJ_ROWSET_ERROR_BASE = 300;
/*      */   public static final int EOJ_JRS_UNABLE_CONNECT_VIA_DS = 300;
/*      */   public static final int EOJ_JRS_AUTH_PROPS_NOT_SET = 301;
/*      */   public static final int EOJ_JRS_CONN_NOT_OPEN = 302;
/*      */   public static final int EOJ_JRS_DEL_ROWS_INVISIBLE = 303;
/*      */   public static final int EOJ_JRS_SYNC_PROVIDER = 304;
/*      */   public static final int EOJ_JRS_RESULTSET_NOT_OPEN = 305;
/*      */   public static final int EOJ_JRS_FETCH_DIR_FOR_SCROLL = 306;
/*      */   public static final int EOJ_JRS_FETCH_REV_FOR_FWD_TYPE = 307;
/*      */   public static final int EOJ_JRS_INVALID_FETCH_DIR = 308;
/*      */   public static final int EOJ_JRS_ROWSET_NOT_WRITABLE = 309;
/*      */   public static final int EOJ_JRS_INVALID_PARAM_INDEX = 310;
/*      */   public static final int EOJ_JRS_CNVT_COL_TO_STREAM = 311;
/*      */   public static final int EOJ_JRS_CANT_COVT_COL_TO_STRM = 312;
/*      */   public static final int EOJ_JRS_CALL_NEXT_PREV_FIRST = 313;
/*      */   public static final int EOJ_JRS_INVALID_OP_FWD_TYPE = 314;
/*      */   public static final int EOJ_JRS_NO_ROWS_CHANGED = 315;
/*      */   public static final int EOJ_JRS_MAP_IN_TOCOLLECTION = 316;
/*      */   public static final int EOJ_JRS_ROW_NOT_INSERTED = 317;
/*      */   public static final int EOJ_JRS_ROW_NOT_DELETED = 318;
/*      */   public static final int EOJ_JRS_ROW_NOT_UPDATED = 319;
/*      */   public static final int EOJ_JRS_NOT_ALL_COLS_SET = 320;
/*      */   public static final int EOJ_JRS_CONV_READER_TO_STR = 321;
/*      */   public static final int EOJ_JRS_STREAM_READ = 322;
/*      */   public static final int EOJ_JRS_INVALID_PARAM_TYPE = 323;
/*      */   public static final int EOJ_JRS_INVALID_NUM_KEY_COLS = 324;
/*      */   public static final int EOJ_JRS_INVALID_PAGE_SIZE = 325;
/*      */   public static final int EOJ_JRS_MARK_INS_ROW_ORIG = 326;
/*      */   public static final int EOJ_JRS_INVALID_OP_BEFORE_INS = 327;
/*      */   public static final int EOJ_JRS_INVALID_OP_ON_RESULTSET = 328;
/*      */   public static final int EOJ_JRS_INVALID_OP_BEFORE_PAGING = 329;
/*      */   public static final int EOJ_JRS_INVALID_ROW_NUM_PARAM = 330;
/*      */   public static final int EOJ_JRS_NEGATIVE_START_POS = 331;
/*      */   public static final int EOJ_JRS_NULL_RESULTSET_TO_POPULATE = 332;
/*      */   public static final int EOJ_JRS_FEW_ROWS_START_POPULATE = 333;
/*      */   public static final int EOJ_JRS_NO_MATCH_COL_INDEXES_SET = 334;
/*      */   public static final int EOJ_JRS_NO_MATCH_COL_NAMES_SET = 335;
/*      */   public static final int EOJ_JRS_INVALID_MATCH_COL_INDEX = 336;
/*      */   public static final int EOJ_JRS_INVALID_MATCH_COL_NAME = 337;
/*      */   public static final int EOJ_JRS_CANT_SET_MATCH_COL_INDEX = 338;
/*      */   public static final int EOJ_JRS_CANT_SET_MATCH_COL_NAME = 339;
/*      */   public static final int EOJ_JRS_COL_INDEX_NOT_BEEN_SET = 340;
/*      */   public static final int EOJ_JRS_COL_NAME_NOT_BEEN_SET = 341;
/*      */   public static final int EOJ_JRS_CANT_OBTAIN_CONNECTION = 342;
/*      */   public static final int EOJ_JRS_TABLE_NAME_IN_SQL = 343;
/*      */   public static final int EOJ_JRS_WRONG_ROWSET_SCROLL_TYPE = 344;
/*      */   public static final int EOJ_JRS_OBJ_NOT_SATISFY_FILTER = 345;
/*      */   public static final int EOJ_JRS_SERIAL_BLOB_CONSTRUCTOR = 346;
/*      */   public static final int EOJ_JRS_SERIAL_CLOB_CONSTRUCTOR = 347;
/*      */   public static final int EOJ_JRS_CANT_CREATE_OBJ_COPY = 348;
/*      */   public static final int EOJ_JRS_ERROR_CREATE_OBJ_COPY = 349;
/*      */   public static final int EOJ_JRS_EMPTY_ROWSET_PARAM = 350;
/*      */   public static final int EOJ_JRS_PARAM_NOT_A_ROWSET = 351;
/*      */   public static final int EOJ_JRS_UNSUPPORTED_JOIN_TYPE = 352;
/*      */   public static final int EOJ_JRS_ROWSET_MATCH_COL_NUM_DIFF = 353;
/*      */   public static final int EOJ_JRS_UNSUPPORTED_3RD_PARTY = 354;
/*      */   public static final int EOJ_JRS_INVALID_XMLREADER = 355;
/*      */   public static final int EOJ_JRS_INVALID_XMLWRITER = 356;
/*      */   public static final int EOJ_JRS_NON_NULLABLE_PROPERTY = 357;
/*      */   public static final int EOJ_JRS_NON_NULLABLE_METADATA = 358;
/*      */   public static final int EOJ_JRS_INVALID_WEBROWSET_ARG = 359;
/*      */   public static final int EOJ_NO_REPLAY = 370;
/*      */   public static final int NO_REPLAY_ACTIVE_TXN = 371;
/*      */   public static final int NO_REPLAY_NONREPLAYABLE_CALL = 372;
/*      */   public static final int NO_REPLAY_LAST_CALL_PLSQL = 373;
/*      */   public static final int NO_REPLAY_TXN_MONITORING_FAILED = 374;
/*      */   public static final int NO_REPLAY_BEGIN_REPLAY_FAILED = 375;
/*      */   public static final int NO_REPLAY_END_REPLAY_FAILED = 376;
/*      */   public static final int NO_REPLAY_INITIATION_TIMEOUT_EXCEEDED = 377;
/*      */   public static final int NO_REPLAY_RETRIES_EXCEEDED = 378;
/*      */   public static final int NO_REPLAY_INIT_CALLBACK_FAILURE = 379;
/*      */   public static final int NO_REPLAY_ACTIVE_TXN_IN_INIT_CALLBACK = 380;
/*      */   public static final int NO_REPLAY_AFTER_ENDREQUEST = 381;
/*      */   public static final int NO_REPLAY_RECONNECT_RETRIES_EXCEEDED = 382;
/*      */   public static final int EOJ_REPLAY_ERROR = 385;
/*      */   public static final int REPLAY_ERROR_CHECKSUM_MISMATCH = 386;
/*      */   public static final int REPLAY_ERROR_ERRCODES_MESG_MISMATCH = 387;
/*      */   public static final int REPLAY_ERROR_ACTIVE_TXN = 388;
/*      */   public static final int BEGIN_REQUEST_CALLED_DURING_REQUEST = 390;
/*      */   public static final int BEGIN_REQUEST_CALLED_WITH_OPEN_TXN = 391;
/*      */   public static final int END_REQUEST_CALLED_WITH_OPEN_TXN = 392;
/*      */   public static final int SVR_TOO_OLD_TO_SUPPORT_REPLAY = 393;
/*      */   public static final int REPLAY_SUPPORT_UNAVAILABLE_IN_SVR = 394;
/*      */   public static final int TTC_ERR_BASE = 400;
/*      */   public static final int TTC0000 = 401;
/*      */   public static final int TTC0001 = 402;
/*      */   public static final int TTC0002 = 403;
/*      */   public static final int TTC0003 = 404;
/*      */   public static final int TTC0004 = 405;
/*      */   public static final int TTC0005 = 406;
/*      */   public static final int TTC0100 = 407;
/*      */   public static final int TTC0101 = 408;
/*      */   public static final int TTC0102 = 409;
/*      */   public static final int TTC0103 = 410;
/*      */   public static final int TTC0104 = 411;
/*      */   public static final int TTC0105 = 412;
/*      */   public static final int TTC0106 = 413;
/*      */   public static final int TTC0107 = 414;
/*      */   public static final int TTC0108 = 415;
/*      */   public static final int TTC0109 = 416;
/*      */   public static final int TTC0110 = 417;
/*      */   public static final int TTC0111 = 418;
/*      */   public static final int TTC0112 = 419;
/*      */   public static final int TTC0113 = 420;
/*      */   public static final int TTC0114 = 421;
/*      */   public static final int TTC0115 = 422;
/*      */   public static final int TTC0116 = 423;
/*      */   public static final int TTC0117 = 424;
/*      */   public static final int TTC0118 = 425;
/*      */   public static final int TTC0119 = 426;
/*      */   public static final int TTC0120 = 427;
/*      */   public static final int TTC0200 = 428;
/*      */   public static final int TTC0201 = 429;
/*      */   public static final int TTC0202 = 430;
/*      */   public static final int TTC0203 = 431;
/*      */   public static final int TTC0204 = 432;
/*      */   public static final int TTC0205 = 433;
/*      */   public static final int TTC0206 = 434;
/*      */   public static final int TTC0207 = 435;
/*      */   public static final int TTC0208 = 436;
/*      */   public static final int TTC0209 = 437;
/*      */   public static final int TTC0210 = 438;
/*      */   public static final int TTC0211 = 439;
/*      */   public static final int TTC0212 = 440;
/*      */   public static final int TTC0213 = 441;
/*      */   public static final int TTC0214 = 442;
/*      */   public static final int TTC0217 = 444;
/*      */   public static final int TTC0218 = 445;
/*      */   public static final int TTC0219 = 446;
/*      */   public static final int TTC0220 = 447;
/*      */   public static final int TTC0222 = 449;
/*      */   public static final int TTC0223 = 450;
/*      */   public static final int TTC0224 = 451;
/*      */   public static final int TTC0225 = 452;
/*      */   public static final int TTC0226 = 453;
/*      */   public static void test()
/*      */   {
/*      */     try
/*      */     {
/* 1535 */       throw createSqlException(null, "exception_message_1", 25);
/*      */     }
/*      */     catch (SQLException localSQLException1)
/*      */     {
/* 1539 */       printSqlException(localSQLException1);
/*      */       
/*      */ 
/*      */       try
/*      */       {
/* 1544 */         throw createSqlException(null, 412, "object_string");
/*      */       }
/*      */       catch (SQLException localSQLException2)
/*      */       {
/* 1548 */         printSqlException(localSQLException2);
/*      */         
/*      */ 
/*      */         try
/*      */         {
/* 1553 */           throw createSqlException(null, 6);
/*      */         }
/*      */         catch (SQLException localSQLException3)
/*      */         {
/* 1557 */           printSqlException(localSQLException3);
/*      */           
/*      */ 
/*      */           try
/*      */           {
/* 1562 */             throw createSqlException(null, 999);
/*      */           }
/*      */           catch (SQLException localSQLException4)
/*      */           {
/* 1566 */             printSqlException(localSQLException4);
/*      */             
/*      */ 
/*      */             try
/*      */             {
/* 1571 */               throw createSqlException(null, 13);
/*      */             }
/*      */             catch (SQLException localSQLException5)
/*      */             {
/* 1575 */               printSqlException(localSQLException5);
/*      */               
/*      */ 
/*      */               try
/*      */               {
/* 1580 */                 IOException localIOException1 = new IOException("ORA-00601: cleanup lock conflict");
/*      */                 
/* 1582 */                 throw createSqlException(null, localIOException1);
/*      */               }
/*      */               catch (SQLException localSQLException6)
/*      */               {
/* 1586 */                 printSqlException(localSQLException6);
/*      */                 
/*      */ 
/*      */                 try
/*      */                 {
/* 1591 */                   IOException localIOException2 = new IOException("some unknown io exception");
/*      */                   
/* 1593 */                   throw createSqlException(null, localIOException2);
/*      */                 }
/*      */                 catch (SQLException localSQLException7)
/*      */                 {
/* 1597 */                   printSqlException(localSQLException7);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1609 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   public static void printSqlException(SQLException paramSQLException) {}
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/DatabaseError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */