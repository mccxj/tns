package oracle.jdbc.rowset;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.RowSetInternal;
import javax.sql.RowSetReader;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.driver.OracleDriver;
import oracle.jdbc.internal.OracleConnection;
public class OracleCachedRowSetReader
  implements RowSetReader, Serializable
{
  static final long serialVersionUID = -3565405169674271176L;
  static final transient int SETUNICODESTREAM_INTLENGTH = 1;
  static final transient int SETBINARYSTREAM_INTLENGTH = 2;
  static final transient int SETASCIISTREAM_INTLENGTH = 3;
  static final transient int SETCHARACTERSTREAM_INTLENGTH = 4;
  static final transient int SETBINARYSTREAM = 5;
  static final transient int SETBINARYSTREAM_LONGLENGTH = 6;
  static final transient int SETASCIISTREAM = 7;
  static final transient int SETASCIISTREAM_LONGLENGTH = 8;
  static final transient int SETCHARACTERSTREAM = 9;
  static final transient int SETCHARACTERSTREAM_LONGLENGTH = 10;
  static final transient int SETNCHARACTERSTREAM = 11;
  static final transient int SETNCHARACTERSTREAM_LONGLENGTH = 12;
  static final transient int SETBLOB_STREAM = 13;
  static final transient int SETBLOB_STREAM_LONG = 14;
  static final transient int SETCLOB_READER = 15;
  static final transient int SETCLOB_READER_LONG = 16;
  static final transient int SETNCLOB_READER = 17;
  static final transient int SETNCLOB_READER_LONG = 18;
  static final transient int TWO_PARAMETERS = 2;
  static final transient int THREE_PARAMETERS = 3;
/* 170 */   private static transient boolean driverManagerInitialized = false;
  
  Connection getConnection(RowSetInternal paramRowSetInternal)
    throws SQLException
  {
/* 187 */     Object localObject1 = null;
    
/* 194 */     Connection localConnection = paramRowSetInternal.getConnection();
/* 195 */     if ((localConnection != null) && (!localConnection.isClosed()))
    {
/* 197 */       localObject1 = localConnection; } else { Object localObject2;
      String str2;
/* 199 */       Object localObject3; if (((RowSet)paramRowSetInternal).getDataSourceName() != null)
      {
        try
        {
/* 203 */           InitialContext localInitialContext = null;
          
          try
          {
/* 209 */             Properties localProperties = System.getProperties();
/* 210 */             localInitialContext = new InitialContext(localProperties);
          }
          catch (SecurityException localSecurityException) {}
          
/* 214 */           if (localInitialContext == null)
/* 215 */             localInitialContext = new InitialContext();
/* 216 */           localObject2 = (DataSource)localInitialContext.lookup(((RowSet)paramRowSetInternal).getDataSourceName());
          
/* 221 */           str2 = ((RowSet)paramRowSetInternal).getUsername();
/* 222 */           localObject3 = ((RowSet)paramRowSetInternal).getPassword();
/* 223 */           if ((str2 == null) && (localObject3 == null))
          {
/* 225 */             localObject1 = ((DataSource)localObject2).getConnection();
          }
          else
          {
/* 229 */             localObject1 = ((DataSource)localObject2).getConnection(str2, (String)localObject3);
          }
          
        }
        catch (NamingException localNamingException)
        {
/* 235 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 300, localNamingException.getMessage());
/* 236 */           ((SQLException)localObject2).fillInStackTrace();
/* 237 */           throw ((Throwable)localObject2);
        }
        
      }
/* 241 */       else if (((RowSet)paramRowSetInternal).getUrl() != null)
      {
/* 243 */         if (!driverManagerInitialized)
        {
/* 245 */           DriverManager.registerDriver(new OracleDriver());
/* 246 */           driverManagerInitialized = true;
        }
/* 248 */         String str1 = ((RowSet)paramRowSetInternal).getUrl();
/* 249 */         localObject2 = ((RowSet)paramRowSetInternal).getUsername();
/* 250 */         str2 = ((RowSet)paramRowSetInternal).getPassword();
        
/* 254 */         if ((str1.equals("")) || (((String)localObject2).equals("")) || (str2.equals("")))
        {
/* 257 */           localObject3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 301);
/* 258 */           ((SQLException)localObject3).fillInStackTrace();
/* 259 */           throw ((Throwable)localObject3);
        }
        
/* 262 */         localObject1 = DriverManager.getConnection(str1, (String)localObject2, str2);
      }
    }
/* 265 */     return (Connection)localObject1;
  }
  
  private void setParams(Object[] paramArrayOfObject, PreparedStatement paramPreparedStatement)
    throws SQLException
  {
/* 278 */     for (int i = 0; i < paramArrayOfObject.length; i++)
    {
/* 280 */       int j = 0;
      
/* 285 */       if ((paramArrayOfObject[i] instanceof byte[]))
      {
/* 287 */         paramPreparedStatement.setObject(i + 1, paramArrayOfObject[i]);
      }
      else
      {
        try
        {
/* 297 */           j = Array.getLength(paramArrayOfObject[i]);
        }
        catch (IllegalArgumentException localIllegalArgumentException)
        {
/* 301 */           paramPreparedStatement.setObject(i + 1, paramArrayOfObject[i]);
/* 302 */           continue;
        }
        
/* 305 */         Object[] arrayOfObject = (Object[])paramArrayOfObject[i];
        
        SQLException localSQLException;
        
/* 309 */         if (j == 2)
        {
/* 311 */           if (arrayOfObject[0] == null) {
/* 312 */             paramPreparedStatement.setNull(i + 1, ((Integer)arrayOfObject[1]).intValue());
          }
/* 314 */           else if ((arrayOfObject[0] instanceof Date))
          {
/* 316 */             if ((arrayOfObject[1] instanceof Calendar))
            {
/* 318 */               paramPreparedStatement.setDate(i + 1, (Date)arrayOfObject[0], (Calendar)arrayOfObject[1]);
            }
            else
            {
/* 324 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 325 */               localSQLException.fillInStackTrace();
/* 326 */               throw localSQLException;
            }
            
          }
/* 330 */           else if ((arrayOfObject[0] instanceof Time))
          {
/* 332 */             if ((arrayOfObject[1] instanceof Calendar))
            {
/* 334 */               paramPreparedStatement.setTime(i + 1, (Time)arrayOfObject[0], (Calendar)arrayOfObject[1]);
            }
            else
            {
/* 340 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 341 */               localSQLException.fillInStackTrace();
/* 342 */               throw localSQLException;
            }
            
          }
/* 346 */           else if ((arrayOfObject[0] instanceof Timestamp))
          {
/* 348 */             if ((arrayOfObject[1] instanceof Calendar))
            {
/* 350 */               paramPreparedStatement.setTimestamp(i + 1, (Timestamp)arrayOfObject[0], (Calendar)arrayOfObject[1]);
            }
            else
            {
/* 356 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 357 */               localSQLException.fillInStackTrace();
/* 358 */               throw localSQLException;
            }
            
          }
/* 367 */           else if ((arrayOfObject[0] instanceof Reader))
          {
/* 369 */             switch (((Integer)arrayOfObject[1]).intValue())
            {
            case 9: 
/* 372 */               paramPreparedStatement.setCharacterStream(i + 1, (Reader)arrayOfObject[0]);
              
/* 374 */               break;
            
            case 11: 
/* 377 */               paramPreparedStatement.setNCharacterStream(i + 1, (Reader)arrayOfObject[0]);
              
/* 379 */               break;
            
            case 15: 
/* 382 */               paramPreparedStatement.setClob(i + 1, (Reader)arrayOfObject[0]);
/* 383 */               break;
            
            case 17: 
/* 386 */               paramPreparedStatement.setNClob(i + 1, (Reader)arrayOfObject[0]);
/* 387 */               break;
            case 10: case 12: 
            case 13: case 14: 
            case 16: default: 
/* 391 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 392 */               localSQLException.fillInStackTrace();
/* 393 */               throw localSQLException;
            
            }
            
/* 397 */           } else if ((arrayOfObject[0] instanceof InputStream))
          {
/* 399 */             switch (((Integer)arrayOfObject[1]).intValue())
            {
            case 7: 
/* 402 */               paramPreparedStatement.setAsciiStream(i + 1, (InputStream)arrayOfObject[0]);
              
/* 404 */               break;
            
            case 5: 
/* 407 */               paramPreparedStatement.setBinaryStream(i + 1, (InputStream)arrayOfObject[0]);
              
/* 409 */               break;
            
            case 13: 
/* 412 */               paramPreparedStatement.setBlob(i + 1, (InputStream)arrayOfObject[0]);
/* 413 */               break;
            
            default: 
/* 417 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 418 */               localSQLException.fillInStackTrace();
/* 419 */               throw localSQLException;
            
            }
            
/* 425 */           } else if ((arrayOfObject[1] instanceof Integer)) {
/* 426 */             paramPreparedStatement.setObject(i + 1, arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
          }
          
        }
/* 431 */         else if (j == 3)
        {
/* 433 */           if (arrayOfObject[0] == null)
          {
/* 435 */             paramPreparedStatement.setNull(i + 1, ((Integer)arrayOfObject[1]).intValue(), (String)arrayOfObject[2]);
          }
/* 439 */           else if ((arrayOfObject[0] instanceof Reader))
          {
/* 441 */             switch (((Integer)arrayOfObject[2]).intValue())
            {
            case 4: 
/* 444 */               paramPreparedStatement.setCharacterStream(i + 1, (Reader)arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
              
/* 447 */               break;
            
            case 10: 
/* 453 */               paramPreparedStatement.setCharacterStream(i + 1, (Reader)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 456 */               break;
            
            case 12: 
/* 459 */               paramPreparedStatement.setNCharacterStream(i + 1, (Reader)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 462 */               break;
            
            case 16: 
/* 465 */               paramPreparedStatement.setClob(i + 1, (Reader)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 468 */               break;
            
            case 18: 
/* 471 */               paramPreparedStatement.setNClob(i + 1, (Reader)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 474 */               break;
            case 5: case 6: case 7: 
            case 8: case 9: case 11: 
            case 13: case 14: case 15: 
            case 17: default: 
/* 479 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 480 */               localSQLException.fillInStackTrace();
/* 481 */               throw localSQLException;
            }
            
          }
/* 485 */           else if ((arrayOfObject[0] instanceof InputStream)) {
/* 486 */             switch (((Integer)arrayOfObject[2]).intValue())
            {
            case 1: 
/* 489 */               paramPreparedStatement.setUnicodeStream(i + 1, (InputStream)arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
              
/* 492 */               break;
            
            case 2: 
/* 495 */               paramPreparedStatement.setBinaryStream(i + 1, (InputStream)arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
              
/* 498 */               break;
            
            case 3: 
/* 501 */               paramPreparedStatement.setAsciiStream(i + 1, (InputStream)arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue());
              
/* 504 */               break;
            
            case 6: 
/* 510 */               paramPreparedStatement.setBinaryStream(i + 1, (InputStream)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 513 */               break;
            
            case 8: 
/* 516 */               paramPreparedStatement.setAsciiStream(i + 1, (InputStream)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 519 */               break;
            
            case 14: 
/* 522 */               paramPreparedStatement.setBlob(i + 1, (InputStream)arrayOfObject[0], ((Long)arrayOfObject[1]).longValue());
              
/* 525 */               break;
            case 4: case 5: case 7: 
            case 9: case 10: 
            case 11: case 12: 
            case 13: default: 
/* 530 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 531 */               localSQLException.fillInStackTrace();
/* 532 */               throw localSQLException;
            }
          }
/* 535 */           else if (((arrayOfObject[1] instanceof Integer)) && ((arrayOfObject[2] instanceof Integer)))
          {
/* 538 */             paramPreparedStatement.setObject(i + 1, arrayOfObject[0], ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue());
          }
          else
          {
/* 544 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 323);
/* 545 */             localSQLException.fillInStackTrace();
/* 546 */             throw localSQLException;
          }
        }
      }
    }
  }
  
  public synchronized void readData(RowSetInternal paramRowSetInternal)
    throws SQLException
  {
/* 562 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)paramRowSetInternal;
    
/* 564 */     Connection localConnection = getConnection(paramRowSetInternal);
    
/* 568 */     if ((localConnection == null) || (localOracleCachedRowSet.getCommand() == null))
    {
/* 570 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 342);
/* 571 */       localSQLException1.fillInStackTrace();
/* 572 */       throw localSQLException1;
    }
    
    try
    {
/* 577 */       localConnection.setTransactionIsolation(localOracleCachedRowSet.getTransactionIsolation());
    }
    catch (Exception localException1) {}
    
/* 582 */     PreparedStatement localPreparedStatement = localConnection.prepareStatement(localOracleCachedRowSet.getCommand(), localOracleCachedRowSet.getType(), localOracleCachedRowSet.getConcurrency());
    
/* 587 */     setParams(paramRowSetInternal.getParams(), localPreparedStatement);
    try
    {
/* 590 */       localPreparedStatement.setMaxRows(localOracleCachedRowSet.getMaxRows());
/* 591 */       localPreparedStatement.setMaxFieldSize(localOracleCachedRowSet.getMaxFieldSize());
/* 592 */       localPreparedStatement.setEscapeProcessing(localOracleCachedRowSet.getEscapeProcessing());
/* 593 */       localPreparedStatement.setQueryTimeout(localOracleCachedRowSet.getQueryTimeout());
    }
    catch (Exception localException2) {}
/* 596 */     ResultSet localResultSet = localPreparedStatement.executeQuery();
/* 597 */     localOracleCachedRowSet.populate(localResultSet, localOracleCachedRowSet.getCurrentPage() * localOracleCachedRowSet.getPageSize());
/* 598 */     localResultSet.close();
/* 599 */     localPreparedStatement.close();
    try
    {
/* 602 */       localConnection.commit();
    }
    catch (SQLException localSQLException2) {}
    
/* 607 */     if (!localOracleCachedRowSet.isConnectionStayingOpen())
    {
/* 609 */       localConnection.close();
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 626 */     return null;
  }
  
/* 631 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleCachedRowSetReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */