package oracle.jpub.runtime;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import oracle.jdbc.driver.DatabaseError;
import oracle.sql.ARRAY;
import oracle.sql.BFILE;
import oracle.sql.BLOB;
import oracle.sql.CHAR;
import oracle.sql.CLOB;
import oracle.sql.CharacterSet;
import oracle.sql.CustomDatum;
import oracle.sql.CustomDatumFactory;
import oracle.sql.DATE;
import oracle.sql.Datum;
import oracle.sql.NUMBER;
import oracle.sql.OPAQUE;
import oracle.sql.ORAData;
import oracle.sql.ORADataFactory;
import oracle.sql.RAW;
import oracle.sql.REF;
import oracle.sql.STRUCT;
import oracle.sql.TIMESTAMP;
public class Util
{
/*  32 */   static short lastCsId = 870;
/*  33 */   static CharacterSet lastCS = CharacterSet.make(870);
  
  public static Object convertToObject(Datum paramDatum, int paramInt, Object paramObject)
    throws SQLException
  {
/*  41 */     Object localObject = _convertToObject(paramDatum, paramInt, paramObject);
/*  42 */     return localObject;
  }
  
  public static Object _convertToObject(Datum paramDatum, int paramInt, Object paramObject)
    throws SQLException
  {
/*  50 */     if (paramDatum == null) {
/*  51 */       return null;
    }
/*  53 */     if ((paramDatum instanceof STRUCT))
    {
/*  55 */       if ((paramObject instanceof ORADataFactory))
      {
/*  57 */         return ((ORADataFactory)paramObject).create(paramDatum, 2002);
      }
      
/*  61 */       return ((CustomDatumFactory)paramObject).create(paramDatum, 2002);
    }
    
/*  65 */     if ((paramDatum instanceof REF))
    {
/*  67 */       if ((paramObject instanceof ORADataFactory))
      {
/*  69 */         return ((ORADataFactory)paramObject).create(paramDatum, 2006);
      }
      
/*  73 */       return ((CustomDatumFactory)paramObject).create(paramDatum, 2006);
    }
    
/*  77 */     if ((paramDatum instanceof ARRAY))
    {
/*  79 */       if ((paramObject instanceof ORADataFactory))
      {
/*  81 */         return ((ORADataFactory)paramObject).create(paramDatum, 2003);
      }
      
/*  85 */       return ((CustomDatumFactory)paramObject).create(paramDatum, 2003);
    }
    
/*  89 */     if ((paramDatum instanceof OPAQUE))
    {
/*  91 */       if ((paramObject instanceof ORADataFactory))
      {
/*  93 */         return ((ORADataFactory)paramObject).create(paramDatum, 2007);
      }
      
/*  97 */       return ((CustomDatumFactory)paramObject).create(paramDatum, 2007);
    }
    
/* 101 */     if (paramObject != null)
    {
/* 103 */       if ((paramObject instanceof ORADataFactory))
      {
/* 105 */         return ((ORADataFactory)paramObject).create(paramDatum, paramInt);
      }
      
/* 109 */       return ((CustomDatumFactory)paramObject).create(paramDatum, paramInt);
    }
    
/* 113 */     if ((paramDatum instanceof NUMBER))
    {
/* 115 */       if ((paramInt == 2) || (paramInt == 3)) {
/* 116 */         return ((NUMBER)paramDatum).bigDecimalValue();
      }
/* 118 */       if ((paramInt == 8) || (paramInt == 6)) {
/* 119 */         return Double.valueOf(((NUMBER)paramDatum).doubleValue());
      }
/* 121 */       if ((paramInt == 4) || (paramInt == 5)) {
/* 122 */         return Integer.valueOf(((NUMBER)paramDatum).intValue());
      }
/* 124 */       if (paramInt == 7) {
/* 125 */         return Float.valueOf(((NUMBER)paramDatum).floatValue());
      }
/* 127 */       if (paramInt == 16) {
/* 128 */         return Boolean.valueOf(((NUMBER)paramDatum).booleanValue());
      }
      
/* 131 */       SQLException localSQLException = DatabaseError.createSqlException(null, 48, " type: " + paramInt);
/* 132 */       localSQLException.fillInStackTrace();
/* 133 */       throw localSQLException;
    }
    
/* 137 */     return paramDatum.toJdbc();
  }
  
  public static Datum convertToOracle(Object paramObject, Connection paramConnection)
    throws SQLException
  {
/* 145 */     return convertToOracle(paramObject, paramConnection, false);
  }
  
  public static Datum convertToOracle(Object paramObject, Connection paramConnection, boolean paramBoolean)
    throws SQLException
  {
/* 155 */     Datum localDatum = _convertToOracle(paramObject, paramConnection, paramBoolean);
    
/* 157 */     return localDatum;
  }
  
  private static Datum _convertToOracle(Object paramObject, Connection paramConnection, boolean paramBoolean)
    throws SQLException
  {
/* 166 */     if (paramObject == null) {
/* 167 */       return null;
    }
/* 169 */     if ((paramObject instanceof ORAData)) {
/* 170 */       return ((ORAData)paramObject).toDatum(paramConnection);
    }
/* 172 */     if ((paramObject instanceof CustomDatum))
/* 173 */       return ((CustomDatum)paramObject).toDatum((oracle.jdbc.driver.OracleConnection)paramConnection);
    short s;
/* 175 */     if ((paramObject instanceof String))
    {
/* 177 */       s = paramBoolean ? ((oracle.jdbc.internal.OracleConnection)paramConnection).getNCharSet() : (paramConnection == null) || (!(paramConnection instanceof oracle.jdbc.internal.OracleConnection)) ? 870 : ((oracle.jdbc.internal.OracleConnection)paramConnection).getDbCsId();
      
/* 184 */       if (s != lastCsId)
      {
/* 186 */         lastCsId = s;
/* 187 */         lastCS = CharacterSet.make(lastCsId);
      }
      
/* 190 */       return new CHAR((String)paramObject, lastCS);
    }
    
/* 193 */     if ((paramObject instanceof Character))
    {
/* 195 */       s = (paramConnection == null) || (!(paramConnection instanceof oracle.jdbc.internal.OracleConnection)) ? 870 : ((oracle.jdbc.internal.OracleConnection)paramConnection).getDbCsId();
      
/* 200 */       if (s != lastCsId)
      {
/* 202 */         lastCsId = s;
/* 203 */         lastCS = CharacterSet.make(lastCsId);
      }
      
/* 206 */       return new CHAR(((Character)paramObject).toString(), lastCS);
    }
    
/* 209 */     if ((paramObject instanceof BigDecimal)) {
/* 210 */       return new NUMBER((BigDecimal)paramObject);
    }
/* 212 */     if ((paramObject instanceof BigInteger)) {
/* 213 */       return new NUMBER((BigInteger)paramObject);
    }
/* 215 */     if ((paramObject instanceof Double)) {
/* 216 */       return new NUMBER(((Double)paramObject).doubleValue());
    }
/* 218 */     if ((paramObject instanceof Float)) {
/* 219 */       return new NUMBER(((Float)paramObject).floatValue());
    }
/* 221 */     if ((paramObject instanceof Integer)) {
/* 222 */       return new NUMBER(((Integer)paramObject).intValue());
    }
/* 224 */     if ((paramObject instanceof Boolean)) {
/* 225 */       return new NUMBER(((Boolean)paramObject).booleanValue());
    }
/* 227 */     if ((paramObject instanceof Short)) {
/* 228 */       return new NUMBER(((Short)paramObject).shortValue());
    }
/* 230 */     if ((paramObject instanceof Byte)) {
/* 231 */       return new NUMBER(((Byte)paramObject).byteValue());
    }
/* 233 */     if ((paramObject instanceof Long)) {
/* 234 */       return new NUMBER(((Long)paramObject).longValue());
    }
/* 236 */     if ((paramObject instanceof Timestamp)) {
/* 237 */       return new TIMESTAMP((Timestamp)paramObject);
    }
/* 239 */     if ((paramObject instanceof java.sql.Date)) {
/* 240 */       return new DATE((java.sql.Date)paramObject);
    }
/* 242 */     if ((paramObject instanceof java.util.Date)) {
/* 243 */       return new DATE(new java.sql.Date(((java.util.Date)paramObject).getTime()));
    }
/* 245 */     if ((paramObject instanceof byte[])) {
/* 246 */       return new RAW((byte[])paramObject);
    }
/* 248 */     if ((paramObject instanceof Datum)) {
/* 249 */       return (Datum)paramObject;
    }
    
/* 256 */     SQLException localSQLException = DatabaseError.createSqlException(null, 48);
/* 257 */     localSQLException.fillInStackTrace();
/* 258 */     throw localSQLException;
  }
  
  static boolean isMutable(Datum paramDatum, ORADataFactory paramORADataFactory)
  {
/* 266 */     if (paramDatum == null) {
/* 267 */       return false;
    }
    
/* 283 */     return ((paramDatum instanceof BFILE)) || ((paramDatum instanceof BLOB)) || ((paramDatum instanceof CLOB)) || ((paramORADataFactory != null) && (((paramDatum instanceof STRUCT)) || ((paramDatum instanceof OPAQUE)) || ((paramDatum instanceof ARRAY))));
  }
  
  static boolean isMutable(Datum paramDatum, CustomDatumFactory paramCustomDatumFactory)
  {
/* 296 */     if (paramDatum == null) {
/* 297 */       return false;
    }
    
/* 313 */     return ((paramDatum instanceof BFILE)) || ((paramDatum instanceof BLOB)) || ((paramDatum instanceof CLOB)) || ((paramCustomDatumFactory != null) && (((paramDatum instanceof STRUCT)) || ((paramDatum instanceof OPAQUE)) || ((paramDatum instanceof ARRAY))));
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 334 */     return null;
  }
  
/* 370 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jpub/runtime/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */