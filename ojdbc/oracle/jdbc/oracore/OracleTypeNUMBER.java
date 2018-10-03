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
import oracle.sql.NUMBER;
public class OracleTypeNUMBER
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -7182242886677299812L;
  int precision;
  int scale;
  
  protected OracleTypeNUMBER() {}
  
  protected OracleTypeNUMBER(int paramInt)
  {
/*  43 */     super(paramInt);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/*  59 */     return toNUMBER(paramObject, paramOracleConnection);
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/*  73 */     return toNUMBERArray(paramObject, paramOracleConnection, paramLong, paramInt);
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/*  84 */     this.precision = paramTDSReader.readUnsignedByte();
    
/*  87 */     this.scale = paramTDSReader.readByte();
  }
  
  protected static Object unpickle81NativeArray(PickleContext paramPickleContext, long paramLong, int paramInt1, int paramInt2)
    throws SQLException
  {
/*  99 */     for (int i = 1; (i < paramLong) && (paramInt1 > 0); i++) {
/* 100 */       paramPickleContext.skipDataValue();
    }
/* 102 */     byte[] arrayOfByte = null;
    int j;
/* 104 */     switch (paramInt2)
    {
    case 4: 
/* 113 */       localObject = new int[paramInt1];
      
/* 115 */       for (j = 0; j < paramInt1; j++)
      {
/* 135 */         if ((arrayOfByte = paramPickleContext.readDataValue()) != null) {
/* 136 */           localObject[j] = NUMBER.toInt(arrayOfByte);
        }
      }
      
/* 141 */       return localObject;
    
    case 5: 
/* 146 */       localObject = new double[paramInt1];
      
/* 148 */       for (j = 0; j < paramInt1; j++)
      {
/* 150 */         if ((arrayOfByte = paramPickleContext.readDataValue()) != null) {
/* 151 */           localObject[j] = NUMBER.toDouble(arrayOfByte);
        }
      }
/* 154 */       return localObject;
    
    case 7: 
/* 159 */       localObject = new long[paramInt1];
      
/* 161 */       for (j = 0; j < paramInt1; j++)
      {
/* 163 */         if ((arrayOfByte = paramPickleContext.readDataValue()) != null) {
/* 164 */           localObject[j] = NUMBER.toLong(arrayOfByte);
        }
      }
/* 167 */       return localObject;
    
    case 6: 
/* 172 */       localObject = new float[paramInt1];
      
/* 174 */       for (j = 0; j < paramInt1; j++)
      {
/* 176 */         if ((arrayOfByte = paramPickleContext.readDataValue()) != null) {
/* 177 */           localObject[j] = NUMBER.toFloat(arrayOfByte);
        }
      }
/* 180 */       return localObject;
    
    case 8: 
/* 185 */       localObject = new short[paramInt1];
      
/* 187 */       for (j = 0; j < paramInt1; j++)
      {
/* 189 */         if ((arrayOfByte = paramPickleContext.readDataValue()) != null) {
/* 190 */           localObject[j] = NUMBER.toShort(arrayOfByte);
        }
      }
/* 193 */       return localObject;
    }
    
    
/* 198 */     Object localObject = DatabaseError.createSqlException(null, 23);
/* 199 */     ((SQLException)localObject).fillInStackTrace();
/* 200 */     throw ((Throwable)localObject);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 214 */     return toNumericObject(paramArrayOfByte, paramInt, paramMap);
  }
  
  static Object toNumericObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 222 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 223 */       return null;
    }
/* 225 */     switch (paramInt)
    {
    case 1: 
/* 229 */       return new NUMBER(paramArrayOfByte);
    
    case 2: 
/* 232 */       return NUMBER.toBigDecimal(paramArrayOfByte);
    
    case 3: 
/* 235 */       return paramArrayOfByte;
    }
    
    
/* 239 */     SQLException localSQLException = DatabaseError.createSqlException(null, 23);
/* 240 */     localSQLException.fillInStackTrace();
/* 241 */     throw localSQLException;
  }
  
  public static NUMBER toNUMBER(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 255 */     NUMBER localNUMBER = null;
    
/* 257 */     if (paramObject != null)
    {
      try
      {
/* 261 */         if ((paramObject instanceof NUMBER)) {
/* 262 */           localNUMBER = (NUMBER)paramObject;
        } else {
/* 264 */           localNUMBER = new NUMBER(paramObject);
        }
      }
      catch (SQLException localSQLException1)
      {
/* 269 */         SQLException localSQLException2 = DatabaseError.createSqlException(null, 59, paramObject);
/* 270 */         localSQLException2.fillInStackTrace();
/* 271 */         throw localSQLException2;
      }
    }
    
/* 275 */     return localNUMBER;
  }
  
  public static Datum[] toNUMBERArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 290 */     Datum[] arrayOfDatum = null;
    
/* 292 */     if (paramObject != null)
    {
/* 294 */       if (((paramObject instanceof Object[])) && (!(paramObject instanceof char[][])))
      {
/* 296 */         Object[] arrayOfObject = (Object[])paramObject;
        
/* 298 */         int i = (int)(paramInt == -1 ? arrayOfObject.length : Math.min(arrayOfObject.length - paramLong + 1L, paramInt));
        
/* 301 */         arrayOfDatum = new Datum[i];
        
/* 303 */         for (int j = 0; j < i; j++) {
/* 304 */           arrayOfDatum[j] = toNUMBER(arrayOfObject[((int)paramLong + j - 1)], paramOracleConnection);
        }
      } else {
/* 307 */         arrayOfDatum = cArrayToNUMBERArray(paramObject, paramOracleConnection, paramLong, paramInt);
      } }
/* 309 */     return arrayOfDatum;
  }
  
  static Datum[] cArrayToNUMBERArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 325 */     Datum[] arrayOfDatum = null;
    
/* 327 */     if (paramObject != null) { Object localObject;
      int i;
/* 329 */       int j; if ((paramObject instanceof short[]))
      {
/* 331 */         localObject = (short[])paramObject;
/* 332 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 335 */         arrayOfDatum = new Datum[i];
        
/* 337 */         for (j = 0; j < i; j++) {
/* 338 */           arrayOfDatum[j] = new NUMBER(localObject[((int)paramLong + j - 1)]);
        }
/* 340 */       } else if ((paramObject instanceof int[]))
      {
/* 342 */         localObject = (int[])paramObject;
/* 343 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 346 */         arrayOfDatum = new Datum[i];
        
/* 348 */         for (j = 0; j < i; j++) {
/* 349 */           arrayOfDatum[j] = new NUMBER(localObject[((int)paramLong + j - 1)]);
        }
/* 351 */       } else if ((paramObject instanceof long[]))
      {
/* 353 */         localObject = (long[])paramObject;
/* 354 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 357 */         arrayOfDatum = new Datum[i];
        
/* 359 */         for (j = 0; j < i; j++) {
/* 360 */           arrayOfDatum[j] = new NUMBER(localObject[((int)paramLong + j - 1)]);
        }
/* 362 */       } else if ((paramObject instanceof float[]))
      {
/* 364 */         localObject = (float[])paramObject;
/* 365 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 368 */         arrayOfDatum = new Datum[i];
        
/* 370 */         for (j = 0; j < i; j++) {
/* 371 */           arrayOfDatum[j] = new NUMBER(localObject[((int)paramLong + j - 1)]);
        }
/* 373 */       } else if ((paramObject instanceof double[]))
      {
/* 375 */         localObject = (double[])paramObject;
/* 376 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 379 */         arrayOfDatum = new Datum[i];
        
/* 381 */         for (j = 0; j < i; j++) {
/* 382 */           arrayOfDatum[j] = new NUMBER(localObject[((int)paramLong + j - 1)]);
        }
/* 384 */       } else if ((paramObject instanceof boolean[]))
      {
/* 386 */         localObject = (boolean[])paramObject;
/* 387 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 390 */         arrayOfDatum = new Datum[i];
        
/* 392 */         for (j = 0; j < i; j++) {
/* 393 */           arrayOfDatum[j] = new NUMBER(Boolean.valueOf(localObject[((int)paramLong + j - 1)]));
        }
      }
/* 396 */       else if ((paramObject instanceof char[][]))
      {
/* 398 */         localObject = (char[][])paramObject;
/* 399 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 402 */         arrayOfDatum = new Datum[i];
        
/* 404 */         for (j = 0; j < i; j++) {
/* 405 */           arrayOfDatum[j] = new NUMBER(new String(localObject[((int)paramLong + j - 1)]));
        }
        
      }
      else
      {
/* 411 */         localObject = DatabaseError.createSqlException(null, 59, paramObject);
/* 412 */         ((SQLException)localObject).fillInStackTrace();
/* 413 */         throw ((Throwable)localObject);
      }
    }
    
/* 417 */     return arrayOfDatum;
  }
  
  public int getPrecision()
  {
/* 424 */     return this.precision;
  }
  
  public int getScale()
  {
/* 431 */     return this.scale;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 442 */     paramObjectOutputStream.writeInt(this.scale);
/* 443 */     paramObjectOutputStream.writeInt(this.precision);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 451 */     this.scale = paramObjectInputStream.readInt();
/* 452 */     this.precision = paramObjectInputStream.readInt();
  }
  
/* 491 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeNUMBER.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */