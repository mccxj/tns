package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.Datum;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
public abstract class OracleType
  implements Serializable
{
  static final long serialVersionUID = -4124152314660261528L;
  public static final int STYLE_ARRAY_LENGTH = 0;
  public static final int STYLE_DATUM = 1;
  public static final int STYLE_JAVA = 2;
  public static final int STYLE_RAWBYTE = 3;
  public static final int STYLE_INT = 4;
  public static final int STYLE_DOUBLE = 5;
  public static final int STYLE_FLOAT = 6;
  public static final int STYLE_LONG = 7;
  public static final int STYLE_SHORT = 8;
  public static final int STYLE_SKIP = 9;
  static final int FORMAT_ADT_ATTR = 1;
  static final int FORMAT_COLL_ELEM = 2;
  static final int FORMAT_COLL_ELEM_NO_INDICATOR = 3;
  int typeCode;
  int dbTypeCode;
/*  56 */   boolean metaDataInitialized = false;
  
  public OracleType() {}
  
  public OracleType(int paramInt)
  {
/*  66 */     this();
    
/*  68 */     this.typeCode = paramInt;
  }
  
  public boolean isInHierarchyOf(OracleType paramOracleType)
    throws SQLException
  {
/*  78 */     return false;
  }
  
  public boolean isInHierarchyOf(StructDescriptor paramStructDescriptor)
    throws SQLException
  {
/*  86 */     return false;
  }
  
  public boolean isObjectType()
  {
/*  93 */     return false;
  }
  
  public TypeDescriptor getTypeDescriptor()
  {
/* 100 */     return null;
  }
  
  public abstract Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException;
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 124 */     Datum[] arrayOfDatum = null;
    
/* 126 */     if (paramObject != null) {
      Object localObject;
/* 128 */       if ((paramObject instanceof Object[]))
      {
/* 130 */         localObject = (Object[])paramObject;
        
/* 132 */         int i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 135 */         arrayOfDatum = new Datum[i];
        
/* 137 */         for (int j = 0; j < i; j++) {
/* 138 */           arrayOfDatum[j] = toDatum(localObject[((int)paramLong + j - 1)], paramOracleConnection);
        }
      }
      else {
/* 142 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 143 */         ((SQLException)localObject).fillInStackTrace();
/* 144 */         throw ((Throwable)localObject);
      }
    }
/* 147 */     return arrayOfDatum;
  }
  
  public void setTypeCode(int paramInt)
  {
/* 154 */     this.typeCode = paramInt;
  }
  
  public int getTypeCode()
    throws SQLException
  {
/* 162 */     return this.typeCode;
  }
  
  public void setDBTypeCode(int paramInt)
  {
/* 169 */     this.dbTypeCode = paramInt;
  }
  
  public int getDBTypeCode()
    throws SQLException
  {
/* 176 */     return this.dbTypeCode;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {}
  
  protected Object unpickle81rec(PickleContext paramPickleContext, int paramInt, Map paramMap)
    throws SQLException
  {
/* 207 */     if (paramInt == 9)
    {
/* 209 */       paramPickleContext.skipDataValue();
      
/* 211 */       return null;
    }
    
/* 215 */     byte[] arrayOfByte = paramPickleContext.readDataValue();
    
/* 217 */     return toObject(arrayOfByte, paramInt, paramMap);
  }
  
  protected Object unpickle81rec(PickleContext paramPickleContext, byte paramByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 227 */     if (paramInt == 9)
    {
/* 229 */       paramPickleContext.skipDataValue();
      
/* 231 */       return null;
    }
    
/* 235 */     byte[] arrayOfByte = paramPickleContext.readDataValue(paramByte);
    
/* 237 */     return toObject(arrayOfByte, paramInt, paramMap);
  }
  
  protected Datum unpickle81datumAsNull(PickleContext paramPickleContext, byte paramByte1, byte paramByte2)
    throws SQLException
  {
/* 247 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 248 */     localSQLException.fillInStackTrace();
/* 249 */     throw localSQLException;
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 259 */     return null;
  }
  
  protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
    throws SQLException
  {
/* 275 */     int i = paramPickleContext.writeLength((int)paramDatum.getLength());
    
/* 277 */     i += paramPickleContext.writeData(paramDatum.shareBytes());
    
/* 279 */     return i;
  }
  
  void writeSerializedFields(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 286 */     writeObject(paramObjectOutputStream);
  }
  
  void readSerializedFields(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 294 */     readObject(paramObjectInputStream);
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 304 */     paramObjectOutputStream.writeInt(0);
/* 305 */     paramObjectOutputStream.writeInt(0);
/* 306 */     paramObjectOutputStream.writeInt(0);
/* 307 */     paramObjectOutputStream.writeInt(0);
/* 308 */     paramObjectOutputStream.writeInt(this.typeCode);
/* 309 */     paramObjectOutputStream.writeInt(this.dbTypeCode);
/* 310 */     paramObjectOutputStream.writeBoolean(this.metaDataInitialized);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 318 */     int i = paramObjectInputStream.readInt();
/* 319 */     int j = paramObjectInputStream.readInt();
/* 320 */     int k = paramObjectInputStream.readInt();
/* 321 */     int m = paramObjectInputStream.readInt();
/* 322 */     this.typeCode = paramObjectInputStream.readInt();
/* 323 */     this.dbTypeCode = paramObjectInputStream.readInt();
/* 324 */     this.metaDataInitialized = paramObjectInputStream.readBoolean();
  }
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {}
  
  public boolean isNCHAR()
    throws SQLException
  {
/* 337 */     return false;
  }
  
  public int getPrecision()
    throws SQLException
  {
/* 345 */     return 0;
  }
  
  public int getScale()
    throws SQLException
  {
/* 352 */     return 0;
  }
  
  public void initMetadataRecursively()
    throws SQLException
  {}
  
  public void initNamesRecursively()
    throws SQLException
  {}
  
  public void initChildNamesRecursively(Map paramMap)
    throws SQLException
  {}
  
  public void cacheDescriptor()
    throws SQLException
  {}
  
  public void setNames(String paramString1, String paramString2)
    throws SQLException
  {}
  
  public String toXMLString()
    throws SQLException
  {
/* 395 */     StringWriter localStringWriter = new StringWriter();
/* 396 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 397 */     printXMLHeader(localPrintWriter);
/* 398 */     printXML(localPrintWriter, 0);
/* 399 */     return localStringWriter.getBuffer().substring(0);
  }
  
  public void printXML(PrintStream paramPrintStream)
    throws SQLException
  {
/* 406 */     PrintWriter localPrintWriter = new PrintWriter(paramPrintStream, true);
/* 407 */     printXMLHeader(localPrintWriter);
/* 408 */     printXML(localPrintWriter, 0);
  }
  
  void printXMLHeader(PrintWriter paramPrintWriter)
    throws SQLException
  {
/* 415 */     paramPrintWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
  }
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt)
    throws SQLException
  {
/* 422 */     printXML(paramPrintWriter, paramInt, false);
  }
  
  public void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
    throws SQLException
  {
/* 428 */     for (int i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 429 */     paramPrintWriter.println("<OracleType typecode=\"" + this.typeCode + "\"" + " />");
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 446 */     return null;
  }
  
/* 451 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */