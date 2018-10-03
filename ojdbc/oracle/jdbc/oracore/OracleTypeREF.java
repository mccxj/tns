package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.StructDescriptor;
public class OracleTypeREF
  extends OracleNamedType
  implements Serializable
{
  static final long serialVersionUID = 3186448715463064573L;
  
  protected OracleTypeREF() {}
  
  public OracleTypeREF(String paramString, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  41 */     super(paramString, paramOracleConnection);
  }
  
  public OracleTypeREF(OracleTypeADT paramOracleTypeADT, int paramInt, OracleConnection paramOracleConnection)
  {
/*  48 */     super(paramOracleTypeADT, paramInt, paramOracleConnection);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  63 */     REF localREF = null;
    
/*  65 */     if (paramObject != null)
    {
/*  67 */       if ((paramObject instanceof REF)) {
/*  68 */         localREF = (REF)paramObject;
      }
      else {
/*  71 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  72 */         localSQLException.fillInStackTrace();
/*  73 */         throw localSQLException;
      }
    }
    
/*  77 */     return localREF;
  }
  
  public int getTypeCode()
  {
/*  91 */     return 2006;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 112 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 113 */       return null;
    }
/* 115 */     if ((paramInt == 1) || (paramInt == 2))
    {
/* 117 */       localObject = createStructDescriptor();
      
/* 119 */       return new REF((StructDescriptor)localObject, this.connection, paramArrayOfByte);
    }
/* 121 */     if (paramInt == 3)
    {
/* 123 */       return paramArrayOfByte;
    }
    
/* 127 */     Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 128 */     ((SQLException)localObject).fillInStackTrace();
/* 129 */     throw ((Throwable)localObject);
  }
  
  StructDescriptor createStructDescriptor()
    throws SQLException
  {
/* 138 */     if (this.descriptor == null)
    {
/* 140 */       if ((this.sqlName == null) && (getFullName(false) == null))
      {
/* 142 */         OracleTypeADT localOracleTypeADT = new OracleTypeADT(getParent(), getOrder(), this.connection);
        
/* 144 */         this.descriptor = new StructDescriptor(localOracleTypeADT, this.connection);
      }
      else
      {
/* 148 */         this.descriptor = StructDescriptor.createDescriptor(this.sqlName, this.connection);
      }
    }
/* 151 */     return (StructDescriptor)this.descriptor;
  }
  
/* 208 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeREF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */