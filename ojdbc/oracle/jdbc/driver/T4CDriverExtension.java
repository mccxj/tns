package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
class T4CDriverExtension
  extends OracleDriverExtension
{
  Connection getConnection(String paramString, Properties paramProperties)
    throws SQLException
  {
/*  32 */     return new T4CConnection(paramString, paramProperties, this);
  }
  
  OracleStatement allocateStatement(PhysicalConnection paramPhysicalConnection, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  48 */     return new T4CStatement(paramPhysicalConnection, paramInt1, paramInt2);
  }
  
  OraclePreparedStatement allocatePreparedStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  67 */     return new T4CPreparedStatement(paramPhysicalConnection, paramString, paramInt1, paramInt2);
  }
  
  OracleCallableStatement allocateCallableStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  87 */     return new T4CCallableStatement(paramPhysicalConnection, paramString, paramInt1, paramInt2);
  }
  
  OracleInputStream createInputStream(OracleStatement paramOracleStatement, int paramInt, Accessor paramAccessor)
    throws SQLException
  {
/* 105 */     return new T4CInputStream(paramOracleStatement, paramInt, paramAccessor);
  }
  
/* 109 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CDriverExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */