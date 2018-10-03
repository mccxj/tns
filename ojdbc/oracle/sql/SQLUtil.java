package oracle.sql;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OracleData;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleConnection;
public class SQLUtil
{
  public static Object SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, Class paramClass, Map paramMap)
    throws SQLException
  {
/*  95 */     return oracle.jdbc.driver.SQLUtil.SQLToJava(paramOracleConnection, paramArrayOfByte, paramInt, paramString, paramClass, paramMap);
  }
  
  public static CustomDatum SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, CustomDatumFactory paramCustomDatumFactory)
    throws SQLException
  {
/* 135 */     return oracle.jdbc.driver.SQLUtil.SQLToJava(paramOracleConnection, paramArrayOfByte, paramInt, paramString, paramCustomDatumFactory);
  }
  
  public static ORAData SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, ORADataFactory paramORADataFactory)
    throws SQLException
  {
/* 174 */     return oracle.jdbc.driver.SQLUtil.SQLToJava(paramOracleConnection, paramArrayOfByte, paramInt, paramString, paramORADataFactory);
  }
  
  public static OracleData SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, OracleDataFactory paramOracleDataFactory)
    throws SQLException
  {
/* 213 */     return oracle.jdbc.driver.SQLUtil.SQLToJava(paramOracleConnection, paramArrayOfByte, paramInt, paramString, paramOracleDataFactory);
  }
  
  public static Object SQLToJava(OracleConnection paramOracleConnection, Datum paramDatum, Class paramClass, Map paramMap)
    throws SQLException
  {
/* 255 */     return oracle.jdbc.driver.SQLUtil.SQLToJava(paramOracleConnection, paramDatum, paramClass, paramMap);
  }
  
  public static byte[] JavaToSQL(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
    throws SQLException
  {
/* 294 */     return oracle.jdbc.driver.SQLUtil.JavaToSQL(paramOracleConnection, paramObject, paramInt, paramString);
  }
  
  public static Datum makeDatum(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt1, String paramString, int paramInt2)
    throws SQLException
  {
/* 336 */     return oracle.jdbc.driver.SQLUtil.makeDatum(paramOracleConnection, paramArrayOfByte, paramInt1, paramString, paramInt2);
  }
  
  public static Datum makeDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
    throws SQLException
  {
/* 373 */     return oracle.jdbc.driver.SQLUtil.makeDatum(paramOracleConnection, paramObject, paramInt, paramString);
  }
  
  public static Object getTypeDescriptor(String paramString, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 406 */     return oracle.jdbc.driver.SQLUtil.getTypeDescriptor(paramString, paramOracleConnection);
  }
  
  public static boolean checkDatumType(Datum paramDatum, int paramInt, String paramString)
    throws SQLException
  {
/* 437 */     return oracle.jdbc.driver.SQLUtil.checkDatumType(paramDatum, paramInt, paramString);
  }
  
  public static boolean implementsInterface(Class paramClass1, Class paramClass2)
  {
/* 458 */     return oracle.jdbc.driver.SQLUtil.implementsInterface(paramClass1, paramClass2);
  }
  
  public static Datum makeOracleDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
    throws SQLException
  {
/* 489 */     return oracle.jdbc.driver.SQLUtil.makeOracleDatum(paramOracleConnection, paramObject, paramInt, paramString);
  }
  
  public static int getInternalType(int paramInt)
    throws SQLException
  {
/* 505 */     return oracle.jdbc.driver.SQLUtil.getInternalType(paramInt);
  }
  
  /**
   * @deprecated
   */
  public static int get_internal_type(int paramInt)
    throws SQLException
  {
/* 520 */     return getInternalType(paramInt);
  }
  
/* 526 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/SQLUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */