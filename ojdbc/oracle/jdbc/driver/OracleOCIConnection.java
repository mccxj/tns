package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import oracle.jdbc.pool.OracleOCIConnectionPool;
public abstract class OracleOCIConnection
  extends T2CConnection
{
/*  31 */   OracleOCIConnectionPool ociConnectionPool = null;
/*  32 */   boolean isPool = false;
/*  33 */   boolean aliasing = false;
  
  public OracleOCIConnection(String paramString, Properties paramProperties, Object paramObject)
    throws SQLException
  {
/*  44 */     this(paramString, paramProperties, (OracleDriverExtension)paramObject);
  }
  
  OracleOCIConnection(String paramString, Properties paramProperties, OracleDriverExtension paramOracleDriverExtension)
    throws SQLException
  {
/*  60 */     super(paramString, paramProperties, paramOracleDriverExtension);
  }
  
  public synchronized byte[] getConnectionId()
    throws SQLException
  {
/*  72 */     byte[] arrayOfByte = t2cGetConnectionId(this.m_nativeState);
    
/*  74 */     if (arrayOfByte == null)
    {
/*  76 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 254, "Cannot create a ByteArray for the connectionId");
/*  77 */       localSQLException.fillInStackTrace();
/*  78 */       throw localSQLException;
    }
    
/*  81 */     this.aliasing = true;
    
/*  83 */     return arrayOfByte;
  }
  
  public synchronized void passwordChange(String paramString1, String paramString2, String paramString3)
    throws SQLException, IOException
  {
/* 102 */     ociPasswordChange(paramString1, paramString2, paramString3);
  }
  
  public synchronized void close()
    throws SQLException
  {
/* 115 */     if ((this.lifecycle == 2) || (this.lifecycle == 4) || (this.aliasing)) {
/* 116 */       return;
    }
/* 118 */     super.close();
    
/* 120 */     this.ociConnectionPool.connectionClosed((oracle.jdbc.oci.OracleOCIConnection)this);
  }
  
  public synchronized void setConnectionPool(OracleOCIConnectionPool paramOracleOCIConnectionPool)
  {
/* 130 */     this.ociConnectionPool = paramOracleOCIConnectionPool;
  }
  
  public synchronized void setStmtCacheSize(int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 146 */     super.setStmtCacheSize(paramInt, paramBoolean);
  }
  
/* 151 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleOCIConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */