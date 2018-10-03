package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.oci.OracleOCIConnection;
class T2CDriverExtension
  extends OracleDriverExtension
{
  static final int T2C_DEFAULT_BATCHSIZE = 1;
  
  Connection getConnection(String paramString, Properties paramProperties)
    throws SQLException
  {
/*  45 */     Object localObject = null;
/*  46 */     if (paramProperties.getProperty("is_connection_pooling") == "true")
    {
/*  49 */       localObject = new OracleOCIConnection(paramString, paramProperties, this);
    }
    else
    {
/*  53 */       localObject = new T2CConnection(paramString, paramProperties, this);
    }
/*  55 */     return (Connection)localObject;
  }
  
  OracleStatement allocateStatement(PhysicalConnection paramPhysicalConnection, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  67 */     return new T2CStatement((T2CConnection)paramPhysicalConnection, 1, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
  }
  
  OraclePreparedStatement allocatePreparedStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  81 */     return new T2CPreparedStatement((T2CConnection)paramPhysicalConnection, paramString, paramPhysicalConnection.defaultExecuteBatch, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
  }
  
  OracleCallableStatement allocateCallableStatement(PhysicalConnection paramPhysicalConnection, String paramString, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  96 */     return new T2CCallableStatement((T2CConnection)paramPhysicalConnection, paramString, paramPhysicalConnection.defaultExecuteBatch, paramPhysicalConnection.defaultRowPrefetch, paramInt1, paramInt2);
  }
  
  OracleInputStream createInputStream(OracleStatement paramOracleStatement, int paramInt, Accessor paramAccessor)
    throws SQLException
  {
/* 111 */     return new T2CInputStream(paramOracleStatement, paramInt, paramAccessor);
  }
  
/* 116 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CDriverExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */