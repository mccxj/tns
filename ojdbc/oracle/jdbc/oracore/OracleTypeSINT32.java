package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
public class OracleTypeSINT32
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -5465988397261455848L;
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  56 */     return OracleTypeNUMBER.toNUMBER(paramObject, paramOracleConnection);
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  70 */     return OracleTypeNUMBER.toNUMBERArray(paramObject, paramOracleConnection, paramLong, paramInt);
  }
  
  public int getTypeCode()
  {
/*  80 */     return 2;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 106 */     return OracleTypeNUMBER.toNumericObject(paramArrayOfByte, paramInt, paramMap);
  }
  
/* 153 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeSINT32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */