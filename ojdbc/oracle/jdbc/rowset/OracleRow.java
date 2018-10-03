package oracle.jdbc.rowset;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Vector;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class OracleRow
  implements Serializable, Cloneable
{
  private Object[] column;
  private Object[] changedColumn;
  private boolean[] isOriginalNull;
  private byte[] columnChangeFlag;
/*  73 */   private int noColumn = 0;
  
  private int noColumnsInserted;
  
/*  84 */   private boolean rowDeleted = false;
  
/*  89 */   private boolean rowInserted = false;
  
  private static final byte COLUMN_CHANGED = 17;
  
/*  99 */   private boolean rowUpdated = false;
  
  int[][] columnTypeInfo;
  
  public OracleRow(int paramInt)
  {
/* 115 */     this.noColumn = paramInt;
/* 116 */     this.column = new Object[paramInt];
/* 117 */     this.changedColumn = new Object[paramInt];
/* 118 */     this.columnChangeFlag = new byte[paramInt];
/* 119 */     this.isOriginalNull = new boolean[paramInt];
/* 120 */     this.columnTypeInfo = new int[paramInt][];
/* 121 */     for (int i = 0; i < paramInt; i++) {
/* 122 */       this.columnChangeFlag[i] = 0;
    }
  }
  
  public OracleRow(int paramInt, boolean paramBoolean)
  {
/* 136 */     this(paramInt);
    
/* 140 */     this.rowInserted = paramBoolean;
/* 141 */     this.noColumnsInserted = 0;
  }
  
  public OracleRow(int paramInt, Object[] paramArrayOfObject)
  {
/* 155 */     this(paramInt);
    
/* 159 */     System.arraycopy(paramArrayOfObject, 0, this.column, 0, paramInt);
  }
  
  public void setColumnValue(int paramInt, Object paramObject)
  {
/* 175 */     if (this.rowInserted)
/* 176 */       this.noColumnsInserted += 1;
/* 177 */     this.column[(paramInt - 1)] = paramObject;
  }
  
  void markOriginalNull(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 187 */     this.isOriginalNull[(paramInt - 1)] = paramBoolean;
  }
  
  boolean isOriginalNull(int paramInt)
    throws SQLException
  {
/* 197 */     return this.isOriginalNull[(paramInt - 1)];
  }
  
  public void updateObject(int paramInt, Object paramObject)
  {
/* 212 */     updateObject(paramInt, paramObject, (int[])null);
  }
  
  void updateObject(int paramInt, Object paramObject, int[] paramArrayOfInt)
  {
/* 221 */     if (this.rowInserted)
/* 222 */       this.noColumnsInserted += 1;
/* 223 */     this.columnChangeFlag[(paramInt - 1)] = 17;
/* 224 */     this.changedColumn[(paramInt - 1)] = paramObject;
/* 225 */     this.columnTypeInfo[(paramInt - 1)] = paramArrayOfInt;
  }
  
  public void cancelRowUpdates()
  {
/* 237 */     this.noColumnsInserted = 0;
/* 238 */     for (int i = 0; i < this.noColumn; i++)
/* 239 */       this.columnChangeFlag[i] = 0;
/* 240 */     this.changedColumn = null;
/* 241 */     this.changedColumn = new Object[this.noColumn];
  }
  
  public Object getColumn(int paramInt)
  {
/* 256 */     return this.column[(paramInt - 1)];
  }
  
  public Object getModifiedColumn(int paramInt)
  {
/* 271 */     return this.changedColumn[(paramInt - 1)];
  }
  
  public boolean isColumnChanged(int paramInt)
  {
/* 288 */     return this.columnChangeFlag[(paramInt - 1)] == 17;
  }
  
  public boolean isRowUpdated()
  {
/* 304 */     if ((this.rowInserted) || (this.rowDeleted)) {
/* 305 */       return false;
    }
/* 307 */     for (int i = 0; i < this.noColumn; i++) {
/* 308 */       if (this.columnChangeFlag[i] == 17)
/* 309 */         return true;
    }
/* 311 */     return false;
  }
  
  public void setRowUpdated(boolean paramBoolean)
  {
/* 323 */     this.rowUpdated = paramBoolean;
/* 324 */     if (!paramBoolean) {
/* 325 */       cancelRowUpdates();
    }
  }
  
  public boolean isRowInserted()
  {
/* 340 */     return this.rowInserted;
  }
  
  public void cancelRowDeletion()
  {
/* 351 */     this.rowDeleted = false;
  }
  
  public void setRowDeleted(boolean paramBoolean)
  {
/* 364 */     this.rowDeleted = paramBoolean;
  }
  
  public boolean isRowDeleted()
  {
/* 376 */     return this.rowDeleted;
  }
  
  public Object[] getOriginalRow()
  {
/* 387 */     return this.column;
  }
  
  public boolean isRowFullyPopulated()
  {
/* 399 */     if (!this.rowInserted) {
/* 400 */       return false;
    }
/* 402 */     return this.noColumnsInserted == this.noColumn;
  }
  
  public void setInsertedFlag(boolean paramBoolean)
  {
/* 415 */     this.rowInserted = paramBoolean;
  }
  
  void makeUpdatesOriginal()
  {
/* 429 */     for (int i = 0; i < this.noColumn; i++)
    {
/* 431 */       if (this.columnChangeFlag[i] == 17)
      {
/* 434 */         this.column[i] = this.changedColumn[i];
/* 435 */         this.changedColumn[i] = null;
/* 436 */         this.columnChangeFlag[i] = 0;
      }
    }
    
/* 440 */     this.rowUpdated = false;
  }
  
  public void insertRow()
  {
/* 454 */     this.columnChangeFlag = null;
/* 455 */     this.columnChangeFlag = new byte[this.noColumn];
/* 456 */     System.arraycopy(this.changedColumn, 0, this.column, 0, this.noColumn);
/* 457 */     this.changedColumn = null;
/* 458 */     this.changedColumn = new Object[this.noColumn];
  }
  
  public Collection toCollection()
  {
/* 470 */     Vector localVector = new Vector(this.noColumn);
/* 471 */     for (int i = 1; i <= this.noColumn; i++) {
/* 472 */       localVector.add(isColumnChanged(i) ? getModifiedColumn(i) : getColumn(i));
    }
    
/* 475 */     return localVector;
  }
  
  public OracleRow createCopy()
    throws SQLException
  {
/* 484 */     OracleRow localOracleRow = new OracleRow(this.noColumn);
/* 485 */     for (int i = 0; i < this.noColumn; i++)
    {
/* 487 */       localOracleRow.column[i] = getCopy(this.column[i]);
/* 488 */       localOracleRow.changedColumn[i] = getCopy(this.changedColumn[i]);
    }
    
/* 491 */     System.arraycopy(this.columnChangeFlag, 0, localOracleRow.columnChangeFlag, 0, this.noColumn);
/* 492 */     localOracleRow.noColumnsInserted = this.noColumnsInserted;
/* 493 */     localOracleRow.rowDeleted = this.rowDeleted;
/* 494 */     localOracleRow.rowInserted = this.rowInserted;
/* 495 */     localOracleRow.rowUpdated = this.rowUpdated;
    
/* 497 */     return localOracleRow;
  }
  
  public Object getCopy(Object paramObject)
    throws SQLException
  {
/* 506 */     Object localObject = null;
    try
    {
/* 509 */       if (paramObject == null) {
/* 510 */         return null;
      }
/* 512 */       if ((paramObject instanceof String)) {
/* 513 */         localObject = (String)paramObject;
      }
/* 515 */       else if ((paramObject instanceof Number)) {
/* 516 */         localObject = new BigDecimal(((Number)paramObject).toString());
      }
/* 518 */       else if ((paramObject instanceof Date)) {
/* 519 */         localObject = new Date(((Date)paramObject).getTime());
      }
/* 521 */       else if ((paramObject instanceof Timestamp)) {
/* 522 */         localObject = new Timestamp(((Timestamp)paramObject).getTime());
      }
/* 525 */       else if ((paramObject instanceof InputStream)) {
/* 526 */         localObject = new DataInputStream((InputStream)paramObject);
      }
/* 528 */       else if ((paramObject instanceof OutputStream)) {
/* 529 */         localObject = new DataOutputStream((OutputStream)paramObject);
      }
      else
      {
/* 533 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 348, paramObject.getClass().getName());
/* 534 */         localSQLException1.fillInStackTrace();
/* 535 */         throw localSQLException1;
      }
    }
    catch (Exception localException)
    {
/* 540 */       SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 349, paramObject.getClass().getName() + localException.getMessage());
/* 541 */       localSQLException2.fillInStackTrace();
/* 542 */       throw localSQLException2;
    }
    
/* 546 */     return localObject;
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
    try
    {
/* 557 */       return createCopy();
    }
    catch (SQLException localSQLException) {
/* 560 */       throw new CloneNotSupportedException("Error while cloning\n" + localSQLException.getMessage());
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 577 */     return null;
  }
  
/* 582 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */