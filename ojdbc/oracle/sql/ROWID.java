package oracle.sql;
import java.io.UnsupportedEncodingException;
import java.sql.RowId;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
public class ROWID
  extends Datum
  implements RowId
{
  static final long serialVersionUID = 5629736369998199486L;
  
  public ROWID() {}
  
  public ROWID(byte[] paramArrayOfByte)
  {
/*  78 */     super(paramArrayOfByte);
  }
  
  public ROWID(String paramString)
    throws SQLException
  {
/*  99 */     this(toAsciiBytes(paramString));
  }
  
  private static byte[] toAsciiBytes(String paramString)
    throws SQLException
  {
    try
    {
/* 107 */       return paramString.getBytes("US-ASCII");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
/* 114 */       SQLException localSQLException = DatabaseError.createSqlException(null, 183);
/* 115 */       localSQLException.fillInStackTrace();
/* 116 */       throw localSQLException;
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 140 */     return this;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 158 */     String str = paramClass.getName();
    
/* 160 */     return str.compareTo("java.lang.String") == 0;
  }
  
  public String stringValue()
  {
/* 177 */     byte[] arrayOfByte = null;
    
/* 179 */     arrayOfByte = getBytes();
    
/* 181 */     return new String(arrayOfByte, 0, 0, arrayOfByte.length);
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 205 */     return new byte[paramInt][];
  }
  
/* 262 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ROWID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */