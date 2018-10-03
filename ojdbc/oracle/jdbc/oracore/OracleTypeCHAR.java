package oracle.jdbc.oracore;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.CHAR;
import oracle.sql.CharacterSet;
import oracle.sql.Datum;
import oracle.sql.converter.CharacterSetMetaData;
public class OracleTypeCHAR
  extends OracleType
  implements Serializable
{
  static final long serialVersionUID = -6899444518695804629L;
  int form;
  int charset;
  int length;
  int characterSemantic;
  private transient OracleConnection connection;
  private short pickleCharaterSetId;
  private transient CharacterSet pickleCharacterSet;
  private short pickleNcharCharacterSet;
  static final int SQLCS_IMPLICIT = 1;
  static final int SQLCS_NCHAR = 2;
  static final int SQLCS_EXPLICIT = 3;
  static final int SQLCS_FLEXIBLE = 4;
  static final int SQLCS_LIT_NULL = 5;
  
  protected OracleTypeCHAR() {}
  
  public OracleTypeCHAR(OracleConnection paramOracleConnection)
  {
/*  71 */     this.form = 0;
/*  72 */     this.charset = 0;
/*  73 */     this.length = 0;
/*  74 */     this.connection = paramOracleConnection;
/*  75 */     this.pickleCharaterSetId = 0;
/*  76 */     this.pickleNcharCharacterSet = 0;
/*  77 */     this.pickleCharacterSet = null;
    
    try
    {
/*  81 */       this.pickleCharaterSetId = this.connection.getStructAttrCsId();
    }
    catch (SQLException localSQLException)
    {
/*  86 */       this.pickleCharaterSetId = -1;
    }
    
/*  89 */     this.pickleCharacterSet = CharacterSet.make(this.pickleCharaterSetId);
  }
  
  protected OracleTypeCHAR(OracleConnection paramOracleConnection, int paramInt)
  {
/*  95 */     super(paramInt);
    
/*  97 */     this.form = 0;
/*  98 */     this.charset = 0;
/*  99 */     this.length = 0;
/* 100 */     this.connection = paramOracleConnection;
/* 101 */     this.pickleCharaterSetId = 0;
/* 102 */     this.pickleNcharCharacterSet = 0;
/* 103 */     this.pickleCharacterSet = null;
    
    try
    {
/* 107 */       this.pickleCharaterSetId = this.connection.getStructAttrCsId();
    }
    catch (SQLException localSQLException)
    {
/* 113 */       this.pickleCharaterSetId = -1;
    }
    
/* 116 */     this.pickleCharacterSet = CharacterSet.make(this.pickleCharaterSetId);
  }
  
  public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 142 */     if (paramObject == null) {
/* 143 */       return null;
    }
/* 145 */     if ((paramObject instanceof CHAR)) {
/* 146 */       return (CHAR)paramObject;
    }
    
    CHAR localCHAR;
    
/* 151 */     if ((this.typeCode == 1) && ((paramObject instanceof String)))
    {
/* 153 */       if (this.characterSemantic != 0)
      {
/* 155 */         int i = CharacterSetMetaData.getRatio(this.pickleCharaterSetId, 1);
        
/* 157 */         String str = (String)paramObject;
/* 158 */         for (int j = str.length(); j < this.length / i; j++) str = str + " ";
/* 159 */         paramObject = str;
/* 160 */         localCHAR = new CHAR(paramObject, this.pickleCharacterSet);
      }
      else
      {
/* 164 */         localCHAR = new CHAR((String)paramObject, this.pickleCharacterSet, this.length);
      }
    } else
/* 167 */       localCHAR = new CHAR(paramObject, this.pickleCharacterSet);
/* 168 */     return localCHAR;
  }
  
  public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 191 */     Datum[] arrayOfDatum = null;
    
/* 193 */     if (paramObject != null)
    {
/* 195 */       if (((paramObject instanceof Object[])) && (!(paramObject instanceof char[][]))) {
/* 196 */         return super.toDatumArray(paramObject, paramOracleConnection, paramLong, paramInt);
      }
/* 198 */       arrayOfDatum = cArrayToDatumArray(paramObject, paramOracleConnection, paramLong, paramInt);
    }
    
/* 201 */     return arrayOfDatum;
  }
  
  public void parseTDSrec(TDSReader paramTDSReader)
    throws SQLException
  {
/* 216 */     super.parseTDSrec(paramTDSReader);
    
    try
    {
/* 221 */       this.length = paramTDSReader.readUB2();
/* 222 */       this.form = paramTDSReader.readByte();
/* 223 */       this.characterSemantic = (this.form & 0x80);
/* 224 */       this.form &= 0x7F;
/* 225 */       this.charset = paramTDSReader.readUB2();
    }
    catch (SQLException localSQLException1)
    {
/* 230 */       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 47, "parseTDS");
/* 231 */       localSQLException3.fillInStackTrace();
/* 232 */       throw localSQLException3;
    }
    
/* 236 */     if ((this.form != 2) || (this.pickleNcharCharacterSet != 0)) {
/* 237 */       return;
    }
    
    try
    {
/* 245 */       this.pickleNcharCharacterSet = this.connection.getStructAttrNCsId();
    }
    catch (SQLException localSQLException2)
    {
/* 251 */       this.pickleNcharCharacterSet = 2000;
    }
    
/* 254 */     this.pickleCharaterSetId = this.pickleNcharCharacterSet;
/* 255 */     this.pickleCharacterSet = CharacterSet.make(this.pickleCharaterSetId);
  }
  
  protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
    throws SQLException
  {
/* 281 */     CHAR localCHAR = getDbCHAR(paramDatum);
    SQLException localSQLException;
/* 283 */     if ((this.characterSemantic != 0) && (this.form != 2))
    {
/* 287 */       if (localCHAR.getStringWithReplacement().length() > this.length)
      {
/* 289 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 72, "\"" + localCHAR.getStringWithReplacement() + "\"");
/* 290 */         localSQLException.fillInStackTrace();
/* 291 */         throw localSQLException;
      }
      
    }
/* 298 */     else if (localCHAR.getLength() > this.length)
    {
/* 300 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 72, "\"" + localCHAR.getStringWithReplacement() + "\"");
/* 301 */       localSQLException.fillInStackTrace();
/* 302 */       throw localSQLException;
    }
    
/* 306 */     return super.pickle81(paramPickleContext, localCHAR);
  }
  
  protected Object toObject(byte[] paramArrayOfByte, int paramInt, Map paramMap)
    throws SQLException
  {
/* 318 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 319 */       return null;
    }
    
/* 323 */     CHAR localCHAR = null;
    
/* 325 */     switch (this.form)
    {
    case 1: 
    case 2: 
/* 333 */       localCHAR = new CHAR(paramArrayOfByte, this.pickleCharacterSet);
      
/* 335 */       break;
    
    case 3: 
    case 4: 
    case 5: 
/* 342 */       localCHAR = new CHAR(paramArrayOfByte, null);
    }
    
    
/* 347 */     if (paramInt == 1)
/* 348 */       return localCHAR;
/* 349 */     if (paramInt == 2)
/* 350 */       return localCHAR.stringValue();
/* 351 */     if (paramInt == 3) {
/* 352 */       return paramArrayOfByte;
    }
    
/* 355 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramArrayOfByte);
/* 356 */     localSQLException.fillInStackTrace();
/* 357 */     throw localSQLException;
  }
  
  private CHAR getDbCHAR(Datum paramDatum)
  {
/* 371 */     CHAR localCHAR1 = (CHAR)paramDatum;
/* 372 */     CHAR localCHAR2 = null;
    
/* 374 */     if (localCHAR1.getCharacterSet().getOracleId() == this.pickleCharaterSetId)
    {
/* 376 */       localCHAR2 = localCHAR1;
    }
    else
    {
      try
      {
/* 382 */         localCHAR2 = new CHAR(localCHAR1.toString(), this.pickleCharacterSet);
      }
      catch (SQLException localSQLException)
      {
/* 388 */         localCHAR2 = localCHAR1;
      }
    }
/* 391 */     return localCHAR2;
  }
  
  private Datum[] cArrayToDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
    throws SQLException
  {
/* 420 */     Datum[] arrayOfDatum = null;
    
/* 422 */     if (paramObject != null) { Object localObject;
      int i;
/* 424 */       int j; if ((paramObject instanceof char[][]))
      {
/* 426 */         localObject = (char[][])paramObject;
/* 427 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 430 */         arrayOfDatum = new Datum[i];
        
/* 432 */         for (j = 0; j < i; j++) {
/* 433 */           arrayOfDatum[j] = new CHAR(new String(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
      }
/* 436 */       else if ((paramObject instanceof boolean[]))
      {
/* 438 */         localObject = (boolean[])paramObject;
/* 439 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 442 */         arrayOfDatum = new Datum[i];
        
/* 444 */         for (j = 0; j < i; j++) {
/* 445 */           arrayOfDatum[j] = new CHAR(Boolean.valueOf(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
      }
/* 448 */       else if ((paramObject instanceof short[]))
      {
/* 450 */         localObject = (short[])paramObject;
/* 451 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 454 */         arrayOfDatum = new Datum[i];
        
/* 458 */         for (j = 0; j < i; j++) {
/* 459 */           arrayOfDatum[j] = new CHAR(Integer.valueOf(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
        
      }
/* 463 */       else if ((paramObject instanceof int[]))
      {
/* 465 */         localObject = (int[])paramObject;
/* 466 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 469 */         arrayOfDatum = new Datum[i];
        
/* 471 */         for (j = 0; j < i; j++) {
/* 472 */           arrayOfDatum[j] = new CHAR(Integer.valueOf(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
      }
/* 475 */       else if ((paramObject instanceof long[]))
      {
/* 477 */         localObject = (long[])paramObject;
/* 478 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 481 */         arrayOfDatum = new Datum[i];
        
/* 483 */         for (j = 0; j < i; j++) {
/* 484 */           arrayOfDatum[j] = new CHAR(new Long(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
      }
/* 487 */       else if ((paramObject instanceof float[]))
      {
/* 489 */         localObject = (float[])paramObject;
/* 490 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 493 */         arrayOfDatum = new Datum[i];
        
/* 495 */         for (j = 0; j < i; j++) {
/* 496 */           arrayOfDatum[j] = new CHAR(new Float(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
      }
/* 499 */       else if ((paramObject instanceof double[]))
      {
/* 501 */         localObject = (double[])paramObject;
/* 502 */         i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
        
/* 505 */         arrayOfDatum = new Datum[i];
        
/* 507 */         for (j = 0; j < i; j++) {
/* 508 */           arrayOfDatum[j] = new CHAR(new Double(localObject[((int)paramLong + j - 1)]), this.pickleCharacterSet);
        }
        
      }
      else
      {
/* 514 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/* 515 */         ((SQLException)localObject).fillInStackTrace();
/* 516 */         throw ((Throwable)localObject);
      }
    }
    
/* 521 */     return arrayOfDatum;
  }
  
  public int getLength()
  {
/* 528 */     return this.length;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
/* 539 */     paramObjectOutputStream.writeInt(this.form);
/* 540 */     paramObjectOutputStream.writeInt(this.charset);
/* 541 */     paramObjectOutputStream.writeInt(this.length);
/* 542 */     paramObjectOutputStream.writeInt(this.characterSemantic);
/* 543 */     paramObjectOutputStream.writeShort(this.pickleCharaterSetId);
/* 544 */     paramObjectOutputStream.writeShort(this.pickleNcharCharacterSet);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
/* 552 */     this.form = paramObjectInputStream.readInt();
/* 553 */     this.charset = paramObjectInputStream.readInt();
/* 554 */     this.length = paramObjectInputStream.readInt();
/* 555 */     this.characterSemantic = paramObjectInputStream.readInt();
/* 556 */     this.pickleCharaterSetId = paramObjectInputStream.readShort();
/* 557 */     this.pickleNcharCharacterSet = paramObjectInputStream.readShort();
    
/* 559 */     if (this.pickleNcharCharacterSet != 0) {
/* 560 */       this.pickleCharacterSet = CharacterSet.make(this.pickleNcharCharacterSet);
    } else {
/* 562 */       this.pickleCharacterSet = CharacterSet.make(this.pickleCharaterSetId);
    }
  }
  
  public void setConnection(OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 569 */     this.connection = paramOracleConnection;
  }
  
  public boolean isNCHAR()
    throws SQLException
  {
/* 583 */     return this.form == 2;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 598 */     return this.connection;
  }
  
/* 645 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeCHAR.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */