package oracle.jdbc.driver;
import java.sql.SQLException;
public class OracleSQLException
  extends SQLException
{
  private Object[] m_parameters;
  
  public OracleSQLException()
  {
/*  33 */     this(null, null, 0);
  }
  
  public OracleSQLException(String paramString)
  {
/*  40 */     this(paramString, null, 0);
  }
  
  public OracleSQLException(String paramString1, String paramString2)
  {
/*  47 */     this(paramString1, paramString2, 0);
  }
  
  public OracleSQLException(String paramString1, String paramString2, int paramInt)
  {
/*  54 */     this(paramString1, paramString2, paramInt, null);
  }
  
  public OracleSQLException(String paramString1, String paramString2, int paramInt, Object[] paramArrayOfObject)
  {
/*  62 */     super(paramString1, paramString2, paramInt);
    
/*  64 */     this.m_parameters = paramArrayOfObject;
  }
  
  public Object[] getParameters()
  {
/*  77 */     if (this.m_parameters == null)
/*  78 */       this.m_parameters = new Object[0];
/*  79 */     return this.m_parameters;
  }
  
  public int getNumParameters()
  {
/*  89 */     if (this.m_parameters == null)
/*  90 */       this.m_parameters = new Object[0];
/*  91 */     return this.m_parameters.length;
  }
  
  public void setParameters(Object[] paramArrayOfObject)
  {
/* 101 */     this.m_parameters = paramArrayOfObject;
  }
  
/* 106 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleSQLException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */