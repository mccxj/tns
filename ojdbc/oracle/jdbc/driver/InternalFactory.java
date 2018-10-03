package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.aq.AQMessageProperties;
public abstract class InternalFactory
{
  public static KeywordValueI createKeywordValue(int paramInt, String paramString, byte[] paramArrayOfByte)
    throws SQLException
  {
/*  50 */     return new KeywordValueI(paramInt, paramString, paramArrayOfByte);
  }
  
  public static KeywordValueLongI createKeywordValueLong(int paramInt, String paramString, byte[] paramArrayOfByte)
    throws SQLException
  {
/*  58 */     return new KeywordValueLongI(paramInt, paramString, paramArrayOfByte);
  }
  
  public static XSAttributeI createXSAttribute()
    throws SQLException
  {
/*  64 */     return new XSAttributeI();
  }
  
  public static XSNamespaceI createXSNamespace()
    throws SQLException
  {
/*  70 */     return new XSNamespaceI();
  }
  
  public static AQMessagePropertiesI createAQMessageProperties()
    throws SQLException
  {
/*  76 */     return new AQMessagePropertiesI();
  }
  
  public static AQAgentI createAQAgent()
    throws SQLException
  {
/*  82 */     return new AQAgentI();
  }
  
  public static AQMessageI createAQMessage(AQMessageProperties paramAQMessageProperties)
    throws SQLException
  {
/*  89 */     return new AQMessageI((AQMessagePropertiesI)paramAQMessageProperties);
  }
  
  public static byte[] urowid2rowid(long[] paramArrayOfLong)
  {
/* 103 */     return T4CRowidAccessor.rowidToString(paramArrayOfLong);
  }
  
  public static long[] rowid2urowid(byte[] paramArrayOfByte, int paramInt1, int paramInt2) throws SQLException {
/* 107 */     return T4CRowidAccessor.stringToRowid(paramArrayOfByte, paramInt1, paramInt2);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/InternalFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */