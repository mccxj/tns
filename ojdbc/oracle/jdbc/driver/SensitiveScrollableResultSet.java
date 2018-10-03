package oracle.jdbc.driver;
import java.sql.SQLException;
class SensitiveScrollableResultSet
  extends ScrollableResultSet
{
  int beginLastFetchedIndex;
  int endLastFetchedIndex;
  
  SensitiveScrollableResultSet(ScrollRsetStatement paramScrollRsetStatement, OracleResultSetImpl paramOracleResultSetImpl, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  36 */     super(paramScrollRsetStatement, paramOracleResultSetImpl, paramInt1, paramInt2);
    
/*  38 */     int i = paramOracleResultSetImpl.getValidRows();
    
/*  40 */     if (i > 0)
    {
/*  42 */       this.beginLastFetchedIndex = 1;
/*  43 */       this.endLastFetchedIndex = i;
    }
    else
    {
/*  47 */       this.beginLastFetchedIndex = 0;
/*  48 */       this.endLastFetchedIndex = 0;
    }
  }
  
  public boolean next()
    throws SQLException
  {
/*  64 */     synchronized (this.connection)
    {
/*  66 */       if (super.next())
      {
/*  68 */         handle_refetch();
        
/*  70 */         return true;
      }
      
/*  74 */       return false;
    }
  }
  
  public boolean first()
    throws SQLException
  {
/*  82 */     synchronized (this.connection)
    {
/*  84 */       if (super.first())
      {
/*  86 */         handle_refetch();
        
/*  88 */         return true;
      }
      
/*  92 */       return false;
    }
  }
  
  public boolean last()
    throws SQLException
  {
/* 100 */     synchronized (this.connection)
    {
/* 102 */       if (super.last())
      {
/* 104 */         handle_refetch();
        
/* 106 */         return true;
      }
      
/* 110 */       return false;
    }
  }
  
  public boolean absolute(int paramInt)
    throws SQLException
  {
/* 118 */     synchronized (this.connection)
    {
/* 120 */       if (super.absolute(paramInt))
      {
/* 122 */         handle_refetch();
        
/* 124 */         return true;
      }
      
/* 128 */       return false;
    }
  }
  
  public boolean relative(int paramInt)
    throws SQLException
  {
/* 136 */     synchronized (this.connection)
    {
/* 138 */       if (super.relative(paramInt))
      {
/* 140 */         handle_refetch();
        
/* 142 */         return true;
      }
      
/* 146 */       return false;
    }
  }
  
  public boolean previous()
    throws SQLException
  {
/* 154 */     synchronized (this.connection)
    {
/* 156 */       if (super.previous())
      {
/* 158 */         handle_refetch();
        
/* 160 */         return true;
      }
      
/* 164 */       return false;
    }
  }
  
  public void refreshRow()
    throws SQLException
  {
/* 172 */     synchronized (this.connection)
    {
/* 175 */       if (!isValidRow(this.currentRow))
      {
/* 177 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 11);
/* 178 */         localSQLException1.fillInStackTrace();
/* 179 */         throw localSQLException1;
      }
      
/* 182 */       int i = getFetchDirection();
/* 183 */       int j = 0;
      
      try
      {
/* 187 */         j = refreshRowsInCache(this.currentRow, getFetchSize(), i);
      }
      catch (SQLException localSQLException2)
      {
/* 192 */         SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localSQLException2, 90, "Unsupported syntax for refreshRow()");
/* 193 */         localSQLException3.fillInStackTrace();
/* 194 */         throw localSQLException3;
      }
      
/* 198 */       if (j != 0)
      {
/* 200 */         this.beginLastFetchedIndex = this.currentRow;
        
/* 205 */         this.endLastFetchedIndex = (this.currentRow + j - 1);
      }
    }
  }
  
  int removeRowInCache(int paramInt)
    throws SQLException
  {
/* 221 */     synchronized (this.connection)
    {
/* 223 */       int i = super.removeRowInCache(paramInt);
      
/* 225 */       if (i != 0)
      {
/* 227 */         if ((paramInt >= this.beginLastFetchedIndex) && (paramInt <= this.endLastFetchedIndex) && (this.beginLastFetchedIndex != this.endLastFetchedIndex))
        {
/* 230 */           this.endLastFetchedIndex -= 1;
        }
        else
        {
/* 245 */           this.beginLastFetchedIndex = (this.endLastFetchedIndex = 0);
        }
      }
/* 248 */       return i;
    }
  }
  
  private boolean handle_refetch()
    throws SQLException
  {
/* 259 */     synchronized (this.connection)
    {
/* 263 */       if (((this.currentRow >= this.beginLastFetchedIndex) && (this.currentRow <= this.endLastFetchedIndex)) || ((this.currentRow >= this.endLastFetchedIndex) && (this.currentRow <= this.beginLastFetchedIndex)))
      {
/* 269 */         return false;
      }
      
/* 275 */       refreshRow();
      
/* 277 */       return true;
    }
  }
  
/* 285 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/SensitiveScrollableResultSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */