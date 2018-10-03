package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
class AnyDataFactory
  implements ORADataFactory, OracleDataFactory
{
  public ORAData create(Datum paramDatum, int paramInt)
    throws SQLException
  {
/* 742 */     if (paramDatum == null) { return null;
    }
/* 744 */     if ((paramDatum instanceof OPAQUE)) {
/* 745 */       return new ANYDATA((OPAQUE)paramDatum);
    }
    
/* 748 */     String str = "expected oracle.sql.OPAQUE got: " + paramDatum.getClass().getName();
    
/* 750 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, str);
/* 751 */     localSQLException.fillInStackTrace();
/* 752 */     throw localSQLException;
  }
  
  public OracleData create(Object paramObject, int paramInt)
    throws SQLException
  {
/* 761 */     if (paramObject == null) { return null;
    }
/* 763 */     if ((paramObject instanceof OPAQUE)) {
/* 764 */       return new ANYDATA((OPAQUE)paramObject);
    }
    
/* 767 */     String str = "expected oracle.sql.OPAQUE got: " + paramObject.getClass().getName();
    
/* 769 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, str);
/* 770 */     localSQLException.fillInStackTrace();
/* 771 */     throw localSQLException;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 787 */     return null;
  }
  
/* 792 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/AnyDataFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */