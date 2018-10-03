package oracle.jdbc.rowset;
import java.sql.SQLException;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.Predicate;
import oracle.jdbc.driver.DatabaseError;
public class OracleFilteredRowSet
  extends OracleWebRowSet
  implements FilteredRowSet
{
  private Predicate predicate;
  
  public OracleFilteredRowSet()
    throws SQLException
  {}
  
  public void setFilter(Predicate paramPredicate)
    throws SQLException
  {
/*  74 */     this.predicate = paramPredicate;
  }
  
  public Predicate getFilter()
  {
/*  90 */     return this.predicate;
  }
  
  public boolean next()
    throws SQLException
  {
/* 100 */     if (this.rowCount <= 0) {
/* 101 */       return false;
    }
/* 103 */     if (this.presentRow >= this.rowCount) {
/* 104 */       return false;
    }
/* 106 */     int i = 0;
    
    do
    {
/* 110 */       this.presentRow += 1;
      
/* 112 */       if ((this.predicate == null) || ((this.predicate != null) && (this.predicate.evaluate(this))))
      {
/* 115 */         i = 1;
/* 116 */         break;
      }
      
/* 119 */     } while (this.presentRow <= this.rowCount);
    
/* 121 */     if (i != 0)
    {
/* 123 */       notifyCursorMoved();
/* 124 */       return true;
    }
    
/* 128 */     return false;
  }
  
  public boolean previous()
    throws SQLException
  {
/* 140 */     if (this.rowsetType == 1003)
    {
/* 142 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 344);
/* 143 */       localSQLException.fillInStackTrace();
/* 144 */       throw localSQLException;
    }
    
/* 147 */     if (this.rowCount <= 0) {
/* 148 */       return false;
    }
/* 150 */     if (this.presentRow <= 1) {
/* 151 */       return false;
    }
/* 153 */     int i = 0;
    
    do
    {
/* 157 */       this.presentRow -= 1;
      
/* 159 */       if ((this.predicate == null) || ((this.predicate != null) && (this.predicate.evaluate(this))))
      {
/* 162 */         i = 1;
/* 163 */         break;
      }
      
/* 166 */     } while (this.presentRow >= 1);
    
/* 168 */     if (i != 0)
    {
/* 170 */       notifyCursorMoved();
/* 171 */       return true;
    }
    
/* 175 */     return false;
  }
  
  public boolean absolute(int paramInt)
    throws SQLException
  {
/* 188 */     if (this.rowsetType == 1003)
    {
/* 190 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 344);
/* 191 */       localSQLException.fillInStackTrace();
/* 192 */       throw localSQLException;
    }
    
/* 195 */     if ((paramInt == 0) || (Math.abs(paramInt) > this.rowCount)) {
/* 196 */       return false;
    }
    
/* 199 */     int i = paramInt < 0 ? this.rowCount + paramInt + 1 : paramInt;
    
/* 201 */     int j = 0;
/* 202 */     this.presentRow = 0;
    
/* 205 */     while ((j < i) && (this.presentRow <= this.rowCount))
    {
/* 207 */       if (next()) {
/* 208 */         j++;
      } else {
/* 210 */         return false;
      }
    }
/* 213 */     if (j == i)
    {
/* 215 */       notifyCursorMoved();
/* 216 */       return true;
    }
    
/* 219 */     return false;
  }
  
  protected void checkAndFilterObject(int paramInt, Object paramObject)
    throws SQLException
  {
/* 230 */     if ((this.predicate != null) && (!this.predicate.evaluate(paramObject, paramInt)))
    {
/* 232 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 345);
/* 233 */       localSQLException.fillInStackTrace();
/* 234 */       throw localSQLException;
    }
  }
  
/* 241 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleFilteredRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */