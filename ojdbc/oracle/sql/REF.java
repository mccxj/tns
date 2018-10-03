package oracle.sql;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleRef;
import oracle.jdbc.oracore.OracleTypeADT;
public class REF
  extends DatumWithConnection
  implements OracleRef, Serializable, Cloneable
{
  static final boolean DEBUG = false;
  static final long serialVersionUID = 1328446996944583167L;
  String typename;
  transient StructDescriptor descriptor;
  
  public String getBaseTypeName()
    throws SQLException
  {
/*  62 */     if (this.typename == null)
    {
/*  64 */       if (this.descriptor != null) {
/*  65 */         this.typename = this.descriptor.getName();
      }
      else
      {
/*  69 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 52);
/*  70 */         localSQLException.fillInStackTrace();
/*  71 */         throw localSQLException;
      }
    }
    
/*  76 */     return this.typename;
  }
  
  public REF(String paramString, Connection paramConnection, byte[] paramArrayOfByte)
    throws SQLException
  {
/*  91 */     super(paramArrayOfByte);
    
/*  93 */     if ((paramConnection == null) || (paramString == null))
    {
/*  97 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  98 */       localSQLException.fillInStackTrace();
/*  99 */       throw localSQLException;
    }
    
/* 103 */     this.typename = paramString;
/* 104 */     this.descriptor = null;
    
/* 106 */     setPhysicalConnectionOf(paramConnection);
  }
  
  public REF(StructDescriptor paramStructDescriptor, Connection paramConnection, byte[] paramArrayOfByte)
    throws SQLException
  {
/* 122 */     super(paramArrayOfByte);
    
/* 124 */     if ((paramConnection == null) || (paramStructDescriptor == null))
    {
/* 127 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/* 128 */       localSQLException.fillInStackTrace();
/* 129 */       throw localSQLException;
    }
    
/* 134 */     this.descriptor = paramStructDescriptor;
    
/* 136 */     setPhysicalConnectionOf(paramConnection);
  }
  
  public Object getValue(Map paramMap)
    throws SQLException
  {
/* 152 */     STRUCT localSTRUCT = getSTRUCT();
/* 153 */     Object localObject = localSTRUCT != null ? localSTRUCT.toJdbc(paramMap) : null;
    
/* 155 */     return localObject;
  }
  
  public Object getValue()
    throws SQLException
  {
/* 171 */     STRUCT localSTRUCT = getSTRUCT();
/* 172 */     Object localObject = localSTRUCT != null ? localSTRUCT.toJdbc() : null;
/* 173 */     return localObject;
  }
  
  public STRUCT getSTRUCT()
    throws SQLException
  {
/* 185 */     synchronized (getInternalConnection())
    {
/* 187 */       STRUCT localSTRUCT = null;
      
/* 189 */       OraclePreparedStatement localOraclePreparedStatement = (OraclePreparedStatement)getInternalConnection().prepareStatement("select deref(:1) from dual");
      
/* 192 */       localOraclePreparedStatement.setRowPrefetch(1);
/* 193 */       localOraclePreparedStatement.setREF(1, this);
      
/* 195 */       OracleResultSet localOracleResultSet = (OracleResultSet)localOraclePreparedStatement.executeQuery();
      
      try
      {
/* 199 */         if (localOracleResultSet.next()) {
/* 200 */           localSTRUCT = localOracleResultSet.getSTRUCT(1);
        }
        else
        {
/* 204 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 52);
/* 205 */           localSQLException.fillInStackTrace();
/* 206 */           throw localSQLException;
        }
        
      }
      finally
      {
/* 212 */         localOracleResultSet.close();
        
/* 214 */         localOracleResultSet = null;
        
/* 216 */         localOraclePreparedStatement.close();
        
/* 218 */         localOraclePreparedStatement = null;
      }
/* 220 */       return localSTRUCT;
    }
  }
  
  public void setValue(Object paramObject)
    throws SQLException
  {
/* 235 */     synchronized (getInternalConnection())
    {
/* 238 */       STRUCT localSTRUCT = STRUCT.toSTRUCT(paramObject, getInternalConnection());
      
/* 240 */       if (localSTRUCT.getInternalConnection() != getInternalConnection())
      {
/* 243 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 77, "Incompatible connection object");
/* 244 */         ((SQLException)localObject1).fillInStackTrace();
/* 245 */         throw ((Throwable)localObject1);
      }
      
/* 249 */       if (!getBaseTypeName().equals(localSTRUCT.getSQLTypeName()))
      {
/* 252 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 77, "Incompatible type");
/* 253 */         ((SQLException)localObject1).fillInStackTrace();
/* 254 */         throw ((Throwable)localObject1);
      }
      
/* 259 */       Object localObject1 = localSTRUCT.toBytes();
      
/* 262 */       byte[] arrayOfByte = localSTRUCT.getDescriptor().getOracleTypeADT().getTOID();
      
/* 265 */       CallableStatement localCallableStatement = null;
      
      try
      {
/* 269 */         localCallableStatement = getInternalConnection().prepareCall("begin :1 := dbms_pickler.update_through_ref (:2, :3, :4, :5); end;");
        
/* 272 */         localCallableStatement.registerOutParameter(1, 2);
        
/* 274 */         localCallableStatement.setBytes(2, shareBytes());
/* 275 */         localCallableStatement.setInt(3, 0);
/* 276 */         localCallableStatement.setBytes(4, arrayOfByte);
/* 277 */         localCallableStatement.setBytes(5, (byte[])localObject1);
        
/* 279 */         localCallableStatement.execute();
        
/* 281 */         int i = 0;
        
/* 283 */         if ((i = localCallableStatement.getInt(1)) != 0)
        {
/* 286 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 77, "ORA-" + i);
/* 287 */           localSQLException.fillInStackTrace();
/* 288 */           throw localSQLException;
        }
        
      }
      finally
      {
/* 294 */         if (localCallableStatement != null) {
/* 295 */           localCallableStatement.close();
        }
/* 297 */         localCallableStatement = null;
      }
    }
  }
  
  public OracleTypeMetaData getOracleMetaData()
    throws SQLException
  {
/* 313 */     return getDescriptor();
  }
  
  public StructDescriptor getDescriptor()
    throws SQLException
  {
/* 326 */     if (this.descriptor == null)
    {
/* 328 */       this.descriptor = StructDescriptor.createDescriptor(this.typename, getInternalConnection());
    }
    
/* 331 */     return this.descriptor;
  }
  
  public String getSQLTypeName()
    throws SQLException
  {
/* 344 */     String str = getBaseTypeName();
/* 345 */     return str;
  }
  
  public Object getObject(Map paramMap)
    throws SQLException
  {
/* 359 */     STRUCT localSTRUCT = getSTRUCT();
/* 360 */     Object localObject = localSTRUCT != null ? localSTRUCT.toJdbc(paramMap) : null;
/* 361 */     return localObject;
  }
  
  public Object getObject()
    throws SQLException
  {
/* 389 */     STRUCT localSTRUCT = getSTRUCT();
/* 390 */     Object localObject = localSTRUCT != null ? localSTRUCT.toJdbc() : null;
/* 391 */     return localObject;
  }
  
  public void setObject(Object paramObject)
    throws SQLException
  {
/* 425 */     PreparedStatement localPreparedStatement = null;
    try
    {
/* 428 */       localPreparedStatement = getInternalConnection().prepareStatement("call sys.utl_ref.update_object( :1, :2 )");
      
/* 430 */       localPreparedStatement.setRef(1, this);
/* 431 */       localPreparedStatement.setObject(2, paramObject);
/* 432 */       localPreparedStatement.execute();
/* 433 */     } finally { if (localPreparedStatement != null) { localPreparedStatement.close();
      }
    }
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 459 */     return this;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 477 */     return false;
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 500 */     return new REF[paramInt];
  }
  
  public Object clone()
    throws CloneNotSupportedException
  {
/* 507 */     REF localREF = null;
    
    try
    {
/* 511 */       localREF = new REF(getBaseTypeName(), getInternalConnection(), getBytes());
    }
    catch (SQLException localSQLException)
    {
/* 515 */       throw new CloneNotSupportedException(localSQLException.getMessage());
    }
    
/* 518 */     return localREF;
  }
  
  public boolean equals(Object paramObject)
  {
/* 525 */     boolean bool = false;
    
    try
    {
/* 529 */       bool = ((paramObject instanceof REF)) && (super.equals(paramObject)) && (getBaseTypeName().equals(((REF)paramObject).getSQLTypeName()));
    }
    catch (Exception localException) {}
    
/* 538 */     return bool;
  }
  
  public int hashCode()
  {
/* 569 */     byte[] arrayOfByte = shareBytes();
/* 570 */     int i = 0;
    int j;
/* 572 */     if ((arrayOfByte[2] & 0x5) == 5)
    {
/* 576 */       for (j = 0; j < 4; j++)
      {
/* 578 */         i *= 256;
/* 579 */         i += (arrayOfByte[(8 + j)] & 0xFF);
      }
      
/* 582 */     } else if ((arrayOfByte[2] & 0x3) == 3)
    {
/* 587 */       for (j = 0; (j < 4) && (j < arrayOfByte.length); j++)
      {
/* 589 */         i *= 256;
/* 590 */         i += (arrayOfByte[(6 + j)] & 0xFF);
      }
      
/* 593 */     } else if ((arrayOfByte[2] & 0x2) == 2)
    {
/* 598 */       for (j = 0; j < 4; j++)
      {
/* 600 */         i *= 256;
/* 601 */         i += (arrayOfByte[(8 + j)] & 0xFF);
      }
    }
/* 604 */     return i;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 610 */     paramObjectOutputStream.writeObject(shareBytes());
    
    try
    {
/* 614 */       paramObjectOutputStream.writeUTF(getBaseTypeName());
    }
    catch (SQLException localSQLException)
    {
/* 618 */       throw new IOException(localSQLException.getMessage());
    }
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 628 */     setBytes((byte[])paramObjectInputStream.readObject());
    
/* 630 */     this.typename = paramObjectInputStream.readUTF();
  }
  
  public Connection getJavaSqlConnection()
    throws SQLException
  {
/* 637 */     return super.getJavaSqlConnection();
  }
  
/* 642 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/REF.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */