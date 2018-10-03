package oracle.jdbc.rowset;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import javax.sql.RowSet;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.Joinable;
import oracle.jdbc.driver.DatabaseError;
public class OracleJoinRowSet
  extends OracleWebRowSet
  implements JoinRowSet
{
  private static final String MATCH_COLUMN_SUFFIX = "#MATCH_COLUMN";
/*  41 */   private static boolean[] supportedJoins = { false, true, false, false, false };
  
  private int joinType;
  
  private Vector addedRowSets;
  
  private Vector addedRowSetNames;
  
  private Object lockForJoinActions;
  
  public OracleJoinRowSet()
    throws SQLException
  {
/*  60 */     this.joinType = 1;
/*  61 */     this.addedRowSets = new Vector();
/*  62 */     this.addedRowSetNames = new Vector();
  }
  
  public synchronized void addRowSet(Joinable paramJoinable)
    throws SQLException
  {
/*  98 */     if (paramJoinable == null)
    {
/* 100 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 350);
/* 101 */       ((SQLException)localObject).fillInStackTrace();
/* 102 */       throw ((Throwable)localObject);
    }
    
/* 105 */     if (!(paramJoinable instanceof RowSet))
    {
/* 107 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 351);
/* 108 */       ((SQLException)localObject).fillInStackTrace();
/* 109 */       throw ((Throwable)localObject);
    }
    
/* 112 */     Object localObject = checkAndWrapRowSet((RowSet)paramJoinable);
/* 113 */     String str = getMatchColumnTableName((RowSet)paramJoinable);
    
/* 115 */     switch (this.joinType)
    {
    case 1: 
/* 118 */       doInnerJoin((OracleCachedRowSet)localObject);
      
/* 120 */       this.addedRowSets.add(paramJoinable);
/* 121 */       this.addedRowSetNames.add(str);
/* 122 */       break;
    
    case 0: 
    case 2: 
    case 3: 
    case 4: 
    default: 
/* 130 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 352);
/* 131 */       localSQLException.fillInStackTrace();
/* 132 */       throw localSQLException;
    }
    
  }
  
  public synchronized void addRowSet(RowSet paramRowSet, int paramInt)
    throws SQLException
  {
/* 160 */     ((OracleRowSet)paramRowSet).setMatchColumn(paramInt);
/* 161 */     addRowSet((Joinable)paramRowSet);
  }
  
  public synchronized void addRowSet(RowSet paramRowSet, String paramString)
    throws SQLException
  {
/* 187 */     ((OracleRowSet)paramRowSet).setMatchColumn(paramString);
/* 188 */     addRowSet((Joinable)paramRowSet);
  }
  
  public synchronized void addRowSet(RowSet[] paramArrayOfRowSet, int[] paramArrayOfInt)
    throws SQLException
  {
/* 223 */     if (paramArrayOfRowSet.length != paramArrayOfInt.length)
    {
/* 225 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 353);
/* 226 */       localSQLException.fillInStackTrace();
/* 227 */       throw localSQLException;
    }
    
/* 230 */     for (int i = 0; i < paramArrayOfRowSet.length; i++)
    {
/* 232 */       ((OracleRowSet)paramArrayOfRowSet[i]).setMatchColumn(paramArrayOfInt[i]);
/* 233 */       addRowSet((Joinable)paramArrayOfRowSet[i]);
    }
  }
  
  public synchronized void addRowSet(RowSet[] paramArrayOfRowSet, String[] paramArrayOfString)
    throws SQLException
  {
/* 270 */     if (paramArrayOfRowSet.length != paramArrayOfString.length)
    {
/* 272 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 353);
/* 273 */       localSQLException.fillInStackTrace();
/* 274 */       throw localSQLException;
    }
    
/* 277 */     for (int i = 0; i < paramArrayOfRowSet.length; i++)
    {
/* 279 */       ((OracleRowSet)paramArrayOfRowSet[i]).setMatchColumn(paramArrayOfString[i]);
/* 280 */       addRowSet((Joinable)paramArrayOfRowSet[i]);
    }
  }
  
  public Collection getRowSets()
    throws SQLException
  {
/* 302 */     return this.addedRowSets;
  }
  
  public String[] getRowSetNames()
    throws SQLException
  {
/* 320 */     Object[] arrayOfObject = this.addedRowSetNames.toArray();
/* 321 */     String[] arrayOfString = new String[arrayOfObject.length];
/* 322 */     for (int i = 0; i < arrayOfObject.length; i++)
    {
/* 324 */       arrayOfString[i] = ((String)arrayOfObject[i]);
    }
/* 326 */     return arrayOfString;
  }
  
  public CachedRowSet toCachedRowSet()
    throws SQLException
  {
/* 356 */     OracleCachedRowSet localOracleCachedRowSet = (OracleCachedRowSet)createCopy();
    
/* 359 */     localOracleCachedRowSet.setCommand("");
    
/* 361 */     return localOracleCachedRowSet;
  }
  
  public int getJoinType()
  {
/* 379 */     return this.joinType;
  }
  
  public boolean supportsCrossJoin()
  {
/* 392 */     return supportedJoins[0];
  }
  
  public boolean supportsInnerJoin()
  {
/* 405 */     return supportedJoins[1];
  }
  
  public boolean supportsLeftOuterJoin()
  {
/* 418 */     return supportedJoins[2];
  }
  
  public boolean supportsRightOuterJoin()
  {
/* 431 */     return supportedJoins[3];
  }
  
  public boolean supportsFullJoin()
  {
/* 444 */     return supportedJoins[4];
  }
  
  public void setJoinType(int paramInt)
    throws SQLException
  {
/* 462 */     if (paramInt != 1)
    {
/* 464 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 352);
/* 465 */       localSQLException.fillInStackTrace();
/* 466 */       throw localSQLException;
    }
    
/* 469 */     this.joinType = paramInt;
  }
  
  public synchronized String getWhereClause()
    throws SQLException
  {
/* 488 */     if (this.addedRowSets.size() < 2) {
/* 489 */       return "WHERE";
    }
/* 491 */     StringBuffer localStringBuffer = new StringBuffer();
/* 492 */     localStringBuffer.append("WHERE\n");
    
/* 494 */     Object localObject1 = (OracleRowSet)this.addedRowSets.get(0);
/* 495 */     Object localObject2 = ((OracleRowSet)localObject1).getMatchColumnIndexes();
/* 496 */     Object localObject3 = ((OracleRowSet)localObject1).getMetaData();
/* 497 */     Object localObject4 = ((OracleRowSet)localObject1).getTableName();
    
/* 504 */     for (int i = 1; i < this.addedRowSets.size(); i++)
    {
/* 506 */       if (i > 1)
      {
/* 508 */         localStringBuffer.append("\nAND\n");
      }
      
/* 511 */       OracleRowSet localOracleRowSet = (OracleRowSet)this.addedRowSets.get(i);
/* 512 */       int[] arrayOfInt = localOracleRowSet.getMatchColumnIndexes();
/* 513 */       ResultSetMetaData localResultSetMetaData = localOracleRowSet.getMetaData();
/* 514 */       String str = localOracleRowSet.getTableName();
      
/* 516 */       for (int j = 0; j < localObject2.length; j++)
      {
/* 518 */         if (j > 0)
        {
/* 520 */           localStringBuffer.append("\nAND\n");
        }
        
/* 523 */         localStringBuffer.append("(" + (String)localObject4 + "." + ((ResultSetMetaData)localObject3).getColumnName(localObject2[j]) + " = " + str + "." + localResultSetMetaData.getColumnName(arrayOfInt[j]) + ")");
      }
      
/* 530 */       localObject1 = localOracleRowSet;
/* 531 */       localObject2 = arrayOfInt;
/* 532 */       localObject3 = localResultSetMetaData;
/* 533 */       localObject4 = str;
    }
    
/* 536 */     localStringBuffer.append(";");
    
/* 538 */     return localStringBuffer.toString();
  }
  
  private void doInnerJoin(OracleCachedRowSet paramOracleCachedRowSet)
    throws SQLException
  {
/* 564 */     if (this.addedRowSets.isEmpty())
    {
/* 567 */       setMetaData((RowSetMetaData)paramOracleCachedRowSet.getMetaData());
/* 568 */       populate(paramOracleCachedRowSet);
      
/* 570 */       setMatchColumn(paramOracleCachedRowSet.getMatchColumnIndexes());
    }
    else
    {
/* 574 */       Vector localVector = new Vector(100);
/* 575 */       OracleRowSetMetaData localOracleRowSetMetaData = new OracleRowSetMetaData(10);
      
/* 577 */       int[] arrayOfInt1 = getMatchColumnIndexes();
/* 578 */       int[] arrayOfInt2 = paramOracleCachedRowSet.getMatchColumnIndexes();
      
/* 581 */       int i = getMetaData().getColumnCount() + paramOracleCachedRowSet.getMetaData().getColumnCount() - arrayOfInt2.length;
      
/* 584 */       localOracleRowSetMetaData.setColumnCount(i);
      
/* 587 */       String str = getTableName() + "#" + paramOracleCachedRowSet.getTableName();
      
      boolean bool;
/* 590 */       for (int j = 1; j <= this.colCount; j++)
      {
/* 592 */         bool = false;
/* 593 */         for (k = 0; k < arrayOfInt1.length; k++)
        {
/* 595 */           if (j == arrayOfInt1[k])
          {
/* 597 */             bool = true;
/* 598 */             break;
          }
        }
        
/* 602 */         setNewColumnMetaData(j, localOracleRowSetMetaData, j, (RowSetMetaData)this.rowsetMetaData, bool, str);
      }
      
/* 607 */       RowSetMetaData localRowSetMetaData = (RowSetMetaData)paramOracleCachedRowSet.getMetaData();
      
/* 609 */       int k = localRowSetMetaData.getColumnCount();
      
/* 612 */       int m = this.colCount + 1;
/* 613 */       int[] arrayOfInt3 = new int[k];
      
/* 615 */       for (int n = 1; n <= k; n++)
      {
/* 617 */         bool = false;
/* 618 */         for (i1 = 0; i1 < arrayOfInt2.length; i1++)
        {
/* 620 */           if (n == arrayOfInt1[i1])
          {
/* 622 */             bool = true;
/* 623 */             break;
          }
        }
        
/* 627 */         if (!bool)
        {
/* 629 */           setNewColumnMetaData(m, localOracleRowSetMetaData, n, localRowSetMetaData, bool, str);
          
/* 633 */           arrayOfInt3[(n - 1)] = m;
/* 634 */           m++;
        }
        else
        {
/* 639 */           arrayOfInt3[(n - 1)] = -1;
        }
      }
      
/* 643 */       beforeFirst();
      
/* 646 */       n = paramOracleCachedRowSet.size();
/* 647 */       int i1 = 0;
      
/* 649 */       for (int i2 = 1; i2 <= this.rowCount; i2++)
      {
/* 651 */         next();
/* 652 */         paramOracleCachedRowSet.beforeFirst();
        
/* 654 */         for (int i3 = 1; i3 <= n; i3++)
        {
/* 656 */           paramOracleCachedRowSet.next();
          
/* 658 */           i1 = 1;
/* 659 */           for (int i4 = 0; i4 < arrayOfInt1.length; i4++)
          {
/* 661 */             Object localObject1 = getObject(arrayOfInt1[i4]);
/* 662 */             Object localObject2 = paramOracleCachedRowSet.getObject(arrayOfInt2[i4]);
/* 663 */             if (!localObject1.equals(localObject2))
            {
/* 665 */               i1 = 0;
/* 666 */               break;
            }
          }
          
/* 670 */           if (i1 != 0)
          {
/* 672 */             OracleRow localOracleRow = new OracleRow(i, true);
            
/* 675 */             for (int i5 = 1; i5 <= this.colCount; i5++)
            {
/* 677 */               localOracleRow.updateObject(i5, getObject(i5));
            }
            
/* 680 */             for (i5 = 1; i5 <= k; i5++)
            {
/* 682 */               if (arrayOfInt3[(i5 - 1)] != -1)
              {
/* 684 */                 localOracleRow.updateObject(arrayOfInt3[(i5 - 1)], paramOracleCachedRowSet.getObject(i5));
              }
            }
            
/* 689 */             localVector.add(localOracleRow);
          }
        }
      }
      
/* 694 */       this.rows = localVector;
/* 695 */       this.presentRow = 0;
/* 696 */       this.rowCount = this.rows.size();
/* 697 */       setMetaData(localOracleRowSetMetaData);
    }
  }
  
  private void setNewColumnMetaData(int paramInt1, RowSetMetaData paramRowSetMetaData1, int paramInt2, RowSetMetaData paramRowSetMetaData2, boolean paramBoolean, String paramString)
    throws SQLException
  {
/* 733 */     paramRowSetMetaData1.setAutoIncrement(paramInt1, paramRowSetMetaData2.isAutoIncrement(paramInt2));
/* 734 */     paramRowSetMetaData1.setCaseSensitive(paramInt1, paramRowSetMetaData2.isCaseSensitive(paramInt2));
/* 735 */     paramRowSetMetaData1.setCatalogName(paramInt1, paramRowSetMetaData2.getCatalogName(paramInt2));
/* 736 */     paramRowSetMetaData1.setColumnDisplaySize(paramInt1, paramRowSetMetaData2.getColumnDisplaySize(paramInt2));
    
/* 739 */     if (paramBoolean)
    {
/* 741 */       paramRowSetMetaData1.setColumnName(paramInt1, paramRowSetMetaData2.getColumnName(paramInt1) + "#MATCH_COLUMN");
    }
    else
    {
/* 746 */       paramRowSetMetaData1.setColumnName(paramInt1, paramRowSetMetaData2.getColumnName(paramInt2));
    }
    
/* 749 */     paramRowSetMetaData1.setColumnLabel(paramInt1, paramRowSetMetaData1.getColumnName(paramInt2));
    
/* 751 */     paramRowSetMetaData1.setColumnType(paramInt1, paramRowSetMetaData2.getColumnType(paramInt2));
/* 752 */     paramRowSetMetaData1.setColumnTypeName(paramInt1, paramRowSetMetaData2.getColumnTypeName(paramInt2));
/* 753 */     paramRowSetMetaData1.setCurrency(paramInt1, paramRowSetMetaData2.isCurrency(paramInt2));
/* 754 */     paramRowSetMetaData1.setNullable(paramInt1, paramRowSetMetaData2.isNullable(paramInt2));
/* 755 */     paramRowSetMetaData1.setPrecision(paramInt1, paramRowSetMetaData2.getPrecision(paramInt2));
/* 756 */     paramRowSetMetaData1.setScale(paramInt1, paramRowSetMetaData2.getScale(paramInt2));
/* 757 */     paramRowSetMetaData1.setSchemaName(paramInt1, paramRowSetMetaData2.getSchemaName(paramInt2));
/* 758 */     paramRowSetMetaData1.setSearchable(paramInt1, paramRowSetMetaData2.isSearchable(paramInt2));
/* 759 */     paramRowSetMetaData1.setSigned(paramInt1, paramRowSetMetaData2.isSigned(paramInt2));
    
/* 761 */     if (paramBoolean)
    {
/* 763 */       paramRowSetMetaData1.setTableName(paramInt1, paramString);
    }
    else
    {
/* 767 */       paramRowSetMetaData1.setTableName(paramInt1, paramRowSetMetaData2.getTableName(paramInt2));
    }
  }
  
  private OracleCachedRowSet checkAndWrapRowSet(RowSet paramRowSet)
    throws SQLException
  {
/* 790 */     OracleCachedRowSet localOracleCachedRowSet = null;
    
/* 794 */     if ((paramRowSet instanceof OracleCachedRowSet))
    {
/* 796 */       localOracleCachedRowSet = (OracleCachedRowSet)paramRowSet;
    } else { Object localObject;
/* 798 */       if ((paramRowSet instanceof OracleJDBCRowSet))
      {
/* 800 */         localOracleCachedRowSet = new OracleCachedRowSet();
/* 801 */         localOracleCachedRowSet.populate(paramRowSet);
        
/* 803 */         localObject = ((OracleJDBCRowSet)paramRowSet).getMatchColumnIndexes();
/* 804 */         localOracleCachedRowSet.setMatchColumn((int[])localObject);
      }
      else
      {
/* 808 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 354);
/* 809 */         ((SQLException)localObject).fillInStackTrace();
/* 810 */         throw ((Throwable)localObject);
      }
    }
/* 813 */     return localOracleCachedRowSet;
  }
  
  private String getMatchColumnTableName(RowSet paramRowSet)
    throws SQLException
  {
/* 823 */     String str = null;
/* 824 */     if ((paramRowSet instanceof OracleRowSet))
    {
/* 826 */       str = ((OracleRowSet)paramRowSet).getTableName();
    }
    
/* 829 */     return str;
  }
  
/* 834 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleJoinRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */