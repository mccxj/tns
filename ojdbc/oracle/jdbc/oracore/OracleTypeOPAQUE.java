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
import oracle.sql.OPAQUE;
import oracle.sql.OpaqueDescriptor;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
public class OracleTypeOPAQUE
  extends OracleTypeADT
  implements Serializable
{
  static final long KOLOFLLB = 1L;
  static final long KOLOFLCL = 2L;
  static final long KOLOFLUB = 4L;
  static final long KOLOFLFX = 8L;
  static final long serialVersionUID = -7279638692691669378L;
  long flagBits;
  long maxLen;
  
  public OracleTypeOPAQUE(byte[] paramArrayOfByte, int paramInt1, int paramInt2, short paramShort, String paramString, long paramLong)
    throws SQLException
  {
/*  51 */     super(paramArrayOfByte, paramInt1, paramInt2, paramShort, paramString);
    
/*  53 */     this.flagBits = paramLong;
/*  54 */     this.flattenedAttrNum = 1;
  }
  
  public OracleTypeOPAQUE(String paramString, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  61 */     super(paramString, paramOracleConnection);
  }
  
  public OracleTypeOPAQUE(OracleTypeADT paramOracleTypeADT, int paramInt, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  69 */     super(paramOracleTypeADT, paramInt, paramOracleConnection);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  84 */     if (paramObject != null)
    {
/*  86 */       if ((paramObject instanceof OPAQUE)) {
/*  87 */         return (OPAQUE)paramObject;
      }
      
/*  90 */       OpaqueDescriptor localOpaqueDescriptor = createOpaqueDescriptor();
      
/*  92 */       return new OPAQUE(localOpaqueDescriptor, this.connection, paramObject);
    }
    
/*  96 */     return null;
  }
  
  public int getTypeCode()
  {
/* 108 */     return 2007;
  }
  
  public boolean isInHierarchyOf(OracleType paramOracleType)
    throws SQLException
  {
/* 119 */     if (paramOracleType == null)
/* 120 */       return false;
/* 121 */     if (!(paramOracleType instanceof OracleTypeOPAQUE)) {
/* 122 */       return false;
    }
/* 124 */     OpaqueDescriptor localOpaqueDescriptor = (OpaqueDescriptor)paramOracleType.getTypeDescriptor();
    
/* 127 */     return this.descriptor.isInHierarchyOf(localOpaqueDescriptor.getName());
  }
  
  public boolean isInHierarchyOf(StructDescriptor paramStructDescriptor)
    throws SQLException
  {
/* 135 */     return false;
  }
  
  public boolean isObjectType()
  {
/* 142 */     return false;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 154 */     paramTDSReader.skipBytes(5);
    
/* 156 */     this.flagBits = paramTDSReader.readLong();
/* 157 */     this.maxLen = paramTDSReader.readLong();
  }
  
  public Datum unlinearize(byte[] paramArrayOfByte, long paramLong, Datum paramDatum, int paramInt, Map paramMap)
    throws SQLException
  {
/* 169 */     if (paramArrayOfByte == null) {
/* 170 */       return null;
    }
/* 172 */     if ((paramArrayOfByte[0] & 0x80) > 0)
    {
/* 174 */       PickleContext localPickleContext = new PickleContext(paramArrayOfByte, paramLong);
      
/* 176 */       return unpickle81(localPickleContext, (OPAQUE)paramDatum, paramInt, paramMap);
    }
    
/* 182 */     return null;
  }
  
  public byte[] linearize(Datum paramDatum)
    throws SQLException
  {
/* 194 */     return pickle81(paramDatum);
  }
  
  protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
    throws SQLException
  {
/* 205 */     OPAQUE localOPAQUE = (OPAQUE)paramDatum;
/* 206 */     byte[] arrayOfByte = localOPAQUE.getBytesValue();
/* 207 */     int i = 0;
    
/* 209 */     i += paramPickleContext.writeOpaqueImageHeader(arrayOfByte.length);
/* 210 */     i += paramPickleContext.writeData(arrayOfByte);
    
/* 212 */     return i;
  }
  
  OPAQUE unpickle81(PickleContext paramPickleContext, OPAQUE paramOPAQUE, int paramInt, Map paramMap)
    throws SQLException
  {
/* 224 */     return unpickle81datum(paramPickleContext, paramOPAQUE, paramInt);
  }
  
  protected Object unpickle81rec(PickleContext paramPickleContext, int paramInt, Map paramMap)
    throws SQLException
  {
/* 232 */     byte b = paramPickleContext.readByte();
/* 233 */     Object localObject = null;
    
/* 235 */     if (PickleContext.isElementNull(b)) {
/* 236 */       return null;
    }
/* 238 */     paramPickleContext.skipRestOfLength(b);
    
/* 240 */     switch (paramInt)
    {
    case 1: 
/* 244 */       localObject = unpickle81datum(paramPickleContext, null);
/* 245 */       break;
    
    case 2: 
/* 248 */       localObject = unpickle81datum(paramPickleContext, null).toJdbc();
/* 249 */       break;
    
    case 3: 
/* 252 */       localObject = new OPAQUE(createOpaqueDescriptor(), paramPickleContext.readDataValue(), this.connection);
      
/* 256 */       break;
    case 9: 
/* 258 */       paramPickleContext.skipDataValue();
/* 259 */       break;
    case 4: case 5: 
    case 6: case 7: 
    case 8: default: 
/* 263 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 264 */       localSQLException.fillInStackTrace();
/* 265 */       throw localSQLException;
    }
    
    
/* 269 */     return localObject;
  }
  
  private OPAQUE unpickle81datum(PickleContext paramPickleContext, OPAQUE paramOPAQUE)
    throws SQLException
  {
/* 282 */     return unpickle81datum(paramPickleContext, paramOPAQUE, 1);
  }
  
  private OPAQUE unpickle81datum(PickleContext paramPickleContext, OPAQUE paramOPAQUE, int paramInt)
    throws SQLException
  {
/* 292 */     paramPickleContext.skipBytes(2);
    
/* 294 */     long l = paramPickleContext.readLength(true) - 2;
    
/* 298 */     if (paramOPAQUE == null)
    {
/* 300 */       if (paramInt == 2)
      {
/* 304 */         return new OPAQUE(createOpaqueDescriptor(), this.connection, paramPickleContext.readBytes((int)l));
      }
      
/* 311 */       return new OPAQUE(createOpaqueDescriptor(), this.connection, paramPickleContext.readBytes((int)l));
    }
    
/* 317 */     paramOPAQUE.setValue(paramPickleContext.readBytes((int)l));
    
/* 319 */     return paramOPAQUE;
  }
  
  OpaqueDescriptor createOpaqueDescriptor()
    throws SQLException
  {
/* 327 */     if (this.sqlName == null) {
/* 328 */       return new OpaqueDescriptor(this, this.connection);
    }
/* 330 */     return OpaqueDescriptor.createDescriptor(this.sqlName, this.connection);
  }
  
  public long getMaxLength()
    throws SQLException
  {
/* 337 */     return this.maxLen;
  }
  
  public boolean isTrustedLibrary()
    throws SQLException
  {
/* 344 */     return (this.flagBits & 1L) != 0L;
  }
  
  public boolean isModeledInC()
    throws SQLException
  {
/* 351 */     return (this.flagBits & 0x2) != 0L;
  }
  
  public boolean isUnboundedSized()
    throws SQLException
  {
/* 358 */     return (this.flagBits & 0x4) != 0L;
  }
  
  public boolean isFixedSized()
    throws SQLException
  {
/* 365 */     return (this.flagBits & 0x8) != 0L;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {}
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {}
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 389 */     this.connection = paramOracleConnection;
  }
  
/* 419 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeOPAQUE.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */