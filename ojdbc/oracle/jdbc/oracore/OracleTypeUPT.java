package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import oracle.sql.Datum;
import oracle.sql.OPAQUE;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
public class OracleTypeUPT
  extends OracleTypeADT
  implements Serializable
{
  static final long serialVersionUID = -1994358478872378695L;
  static final byte KOPU_UPT_ADT = -6;
  static final byte KOPU_UPT_COLL = -5;
  static final byte KOPU_UPT_REFCUR = 102;
  static final byte KOTTCOPQ = 58;
/*  50 */   byte uptCode = 0;
/*  51 */   OracleNamedType realType = null;
  
  protected OracleTypeUPT() {}
  
  public OracleTypeUPT(String paramString, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  67 */     super(paramString, paramOracleConnection);
  }
  
  public OracleTypeUPT(OracleTypeADT paramOracleTypeADT, int paramInt, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  75 */     super(paramOracleTypeADT, paramInt, paramOracleConnection);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  92 */     if (paramObject != null) {
/*  93 */       return this.realType.toDatum(paramObject, paramOracleConnection);
    }
/*  95 */     return null;
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 109 */     if (paramObject != null) {
/* 110 */       return this.realType.toDatumArray(paramObject, paramOracleConnection, paramLong, paramInt);
    }
/* 112 */     return null;
  }
  
  public int getTypeCode()
    throws SQLException
  {
/* 123 */     switch (this.uptCode)
    {
    case -6: 
/* 127 */       return this.realType.getTypeCode();
    
    case -5: 
/* 130 */       return 2003;
    
    case 58: 
/* 133 */       return 2007;
    }
    
    
/* 137 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid type code");
/* 138 */     localSQLException.fillInStackTrace();
/* 139 */     throw localSQLException;
  }
  
  public boolean isInHierarchyOf(OracleType paramOracleType)
    throws SQLException
  {
/* 153 */     return false;
  }
  
  public boolean isInHierarchyOf(StructDescriptor paramStructDescriptor)
    throws SQLException
  {
/* 161 */     return false;
  }
  
  public boolean isObjectType()
  {
/* 168 */     return false;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 180 */     long l = paramTDSReader.readLong();
    
/* 182 */     this.uptCode = paramTDSReader.readByte();
    
/* 184 */     paramTDSReader.addNormalPatch(l, this.uptCode, this);
  }
  
  protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
    throws SQLException
  {
/* 199 */     int i = 0;
    
/* 201 */     if (paramDatum == null)
    {
/* 203 */       i += paramPickleContext.writeElementNull();
    }
    else
    {
/* 207 */       int j = paramPickleContext.offset();
      
/* 209 */       i += paramPickleContext.writeLength(PickleContext.KOPI20_LN_MAXV + 1);
      
/* 211 */       int k = 0;
      
/* 214 */       if ((this.uptCode == -6) && (!((OracleTypeADT)this.realType).isFinalType()))
      {
/* 216 */         if ((paramDatum instanceof STRUCT))
        {
/* 218 */           k = ((STRUCT)paramDatum).getDescriptor().getOracleTypeADT().pickle81(paramPickleContext, paramDatum);
        }
        else
        {
/* 224 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "invalid upt state");
/* 225 */           localSQLException.fillInStackTrace();
/* 226 */           throw localSQLException;
        }
      }
      else {
/* 230 */         k = this.realType.pickle81(paramPickleContext, paramDatum);
      }
/* 232 */       i += k;
      
/* 234 */       paramPickleContext.patchImageLen(j, k);
    }
    
/* 237 */     return i;
  }
  
  protected Object unpickle81rec(PickleContext paramPickleContext, int paramInt, Map paramMap)
    throws SQLException
  {
/* 254 */     byte b = paramPickleContext.readByte();
    
/* 256 */     if (PickleContext.isElementNull(b))
    {
/* 258 */       return null;
    }
/* 260 */     if (paramInt == 9)
    {
/* 262 */       paramPickleContext.skipBytes(paramPickleContext.readRestOfLength(b));
      
/* 264 */       return null;
    }
    
/* 268 */     paramPickleContext.skipRestOfLength(b);
    
/* 270 */     return unpickle81UPT(paramPickleContext, paramInt, paramMap);
  }
  
  protected Object unpickle81rec(PickleContext paramPickleContext, byte paramByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 284 */     long l = paramPickleContext.readRestOfLength(paramByte);
    
/* 286 */     if (paramInt == 9)
    {
/* 288 */       paramPickleContext.skipBytes((int)l);
      
/* 290 */       return null;
    }
    
/* 293 */     return unpickle81UPT(paramPickleContext, paramInt, paramMap);
  }
  
  private Object unpickle81UPT(PickleContext paramPickleContext, int paramInt, Map paramMap)
    throws SQLException
  {
    SQLException localSQLException;
    
/* 302 */     switch (this.uptCode)
    {
    case -6: 
/* 307 */       switch (paramInt)
      {
      case 1: 
/* 311 */         return ((OracleTypeADT)this.realType).unpickle81(paramPickleContext, (STRUCT)null, 3, paramInt, paramMap);
      
      case 2: 
/* 315 */         localObject = ((OracleTypeADT)this.realType).unpickle81(paramPickleContext, (STRUCT)null, 1, paramInt, paramMap);
        
/* 318 */         return localObject == null ? localObject : ((STRUCT)localObject).toJdbc(paramMap);
      
      case 9: 
/* 321 */         return ((OracleTypeADT)this.realType).unpickle81(paramPickleContext, (STRUCT)null, 9, 1, paramMap);
      }
      
      
/* 326 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 327 */       localSQLException.fillInStackTrace();
/* 328 */       throw localSQLException;
    
    case -5: 
/* 335 */       return ((OracleTypeCOLLECTION)this.realType).unpickle81(paramPickleContext, (ARRAY)null, paramInt == 9 ? paramInt : 3, paramInt, paramMap);
    
    case 58: 
/* 341 */       switch (paramInt)
      {
      case 1: 
      case 9: 
/* 346 */         return ((OracleTypeOPAQUE)this.realType).unpickle81(paramPickleContext, (OPAQUE)null, paramInt, paramMap);
      
      case 2: 
/* 350 */         localObject = ((OracleTypeOPAQUE)this.realType).unpickle81(paramPickleContext, (OPAQUE)null, paramInt, paramMap);
        
/* 353 */         return localObject == null ? localObject : ((OPAQUE)localObject).toJdbc(paramMap);
      }
      
      
/* 357 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 358 */       localSQLException.fillInStackTrace();
/* 359 */       throw localSQLException;
    }
    
    
/* 366 */     Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unrecognized UPT code");
/* 367 */     ((SQLException)localObject).fillInStackTrace();
/* 368 */     throw ((Throwable)localObject);
  }
  
  protected Datum unpickle81datumAsNull(PickleContext paramPickleContext, byte paramByte1, byte paramByte2)
    throws SQLException
  {
/* 380 */     return null;
  }
  
  StructDescriptor createStructDescriptor()
    throws SQLException
  {
/* 392 */     StructDescriptor localStructDescriptor = null;
    
/* 394 */     if (this.sqlName == null) {
/* 395 */       localStructDescriptor = new StructDescriptor((OracleTypeADT)this.realType, this.connection);
    } else {
/* 397 */       localStructDescriptor = StructDescriptor.createDescriptor(this.sqlName, this.connection);
    }
/* 399 */     return localStructDescriptor;
  }
  
  ArrayDescriptor createArrayDescriptor()
    throws SQLException
  {
/* 407 */     ArrayDescriptor localArrayDescriptor = null;
    
/* 409 */     if (this.sqlName == null) {
/* 410 */       localArrayDescriptor = new ArrayDescriptor((OracleTypeCOLLECTION)this.realType, this.connection);
    } else {
/* 412 */       localArrayDescriptor = ArrayDescriptor.createDescriptor(this.sqlName, this.connection);
    }
/* 414 */     return localArrayDescriptor;
  }
  
  public OracleType getRealType()
    throws SQLException
  {
/* 421 */     return this.realType;
  }
  
  public int getNumAttrs()
    throws SQLException
  {
/* 428 */     return ((OracleTypeADT)this.realType).getNumAttrs();
  }
  
  public OracleType getAttrTypeAt(int paramInt)
    throws SQLException
  {
/* 435 */     return ((OracleTypeADT)this.realType).getAttrTypeAt(paramInt);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 446 */     paramObjectOutputStream.writeByte(this.uptCode);
/* 447 */     paramObjectOutputStream.writeObject(this.realType);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 455 */     this.uptCode = paramObjectInputStream.readByte();
/* 456 */     this.realType = ((OracleNamedType)paramObjectInputStream.readObject());
  }
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 464 */     this.connection = paramOracleConnection;
    
/* 466 */     this.realType.setConnection(paramOracleConnection);
  }
  
  public void initChildNamesRecursively(Map paramMap)
    throws SQLException
  {
/* 473 */     if (this.realType != null)
    {
/* 475 */       this.realType.setSqlName(this.sqlName);
/* 476 */       this.realType.initChildNamesRecursively(paramMap);
    }
  }
  
  public void initMetadataRecursively()
    throws SQLException
  {
/* 484 */     initMetadata(this.connection);
/* 485 */     if (this.realType != null) { this.realType.initMetadataRecursively();
    }
  }
  
  public void cacheDescriptor()
    throws SQLException
  {}
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 500 */     for (int i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 501 */     paramPrintWriter.println("<OracleTypeUPT sqlName=\"" + this.sqlName + "\" " + " toid=\"" + this.toid + "\" " + ">");
    
/* 505 */     if (this.realType != null)
/* 506 */       this.realType.printXML(paramPrintWriter, paramInt + 1, paramBoolean);
/* 507 */     for (i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 508 */     paramPrintWriter.println("</OracleTypeUPT>");
  }
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt)
    throws SQLException
  {
/* 515 */     printXML(paramPrintWriter, paramInt, false);
  }
  
/* 567 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeUPT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */