package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
public class OracleTypeFLOAT
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = 4088841548269771109L;
  int precision;
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  50 */     return OracleTypeNUMBER.toNUMBER(paramObject, paramOracleConnection);
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  64 */     return OracleTypeNUMBER.toNUMBERArray(paramObject, paramOracleConnection, paramLong, paramInt);
  }
  
  public int getTypeCode()
  {
/*  74 */     return 6;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/*  89 */     this.precision = paramTDSReader.readUnsignedByte();
  }
  
  public int getScale()
  {
/* 102 */     return -127;
  }
  
  public int getPrecision()
  {
/* 109 */     return this.precision;
  }
  
  protected static Object unpickle81NativeArray(PickleContext paramPickleContext, long paramLong, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 123 */     return OracleTypeNUMBER.unpickle81NativeArray(paramPickleContext, paramLong, paramInt1, paramInt2);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 136 */     return OracleTypeNUMBER.toNumericObject(paramArrayOfByte, paramInt, paramMap);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 148 */     paramObjectOutputStream.writeInt(this.precision);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 157 */     this.precision = paramObjectInputStream.readInt();
  }
  
/* 190 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeFLOAT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */