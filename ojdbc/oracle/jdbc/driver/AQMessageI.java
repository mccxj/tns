package oracle.jdbc.driver;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.aq.AQMessage;
import oracle.jdbc.aq.AQMessageProperties;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.sql.ANYDATA;
import oracle.sql.Datum;
import oracle.sql.OPAQUE;
import oracle.sql.OpaqueDescriptor;
import oracle.sql.RAW;
import oracle.sql.STRUCT;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
import oracle.xdb.XMLType;
class AQMessageI
  implements AQMessage
{
/*  64 */   private byte[] id = null;
/*  65 */   private AQMessagePropertiesI properties = null;
/*  66 */   private byte[] toid = null;
  
  private byte[] payload;
  
  private STRUCT payLoadSTRUCT;
  
  private ANYDATA payLoadANYDATA;
  private RAW payLoadRAW;
  private XMLType payLoadXMLType;
  private Connection conn;
  private String typeName;
  private TypeDescriptor sd;
  
  AQMessageI(AQMessagePropertiesI paramAQMessagePropertiesI, Connection paramConnection)
  {
/*  81 */     this.properties = paramAQMessagePropertiesI;
/*  82 */     this.conn = paramConnection;
  }
  
  AQMessageI(AQMessagePropertiesI paramAQMessagePropertiesI)
    throws SQLException
  {
/*  90 */     this.properties = paramAQMessagePropertiesI;
  }
  
  void setTypeName(String paramString)
  {
/*  97 */     this.typeName = paramString;
  }
  
  void setTypeDescriptor(TypeDescriptor paramTypeDescriptor)
  {
/* 104 */     this.sd = paramTypeDescriptor;
  }
  
  public byte[] getMessageId()
  {
/* 111 */     return this.id;
  }
  
  void setMessageId(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 118 */     this.id = paramArrayOfByte;
  }
  
  public AQMessageProperties getMessageProperties()
  {
/* 125 */     return this.properties;
  }
  
  AQMessagePropertiesI getMessagePropertiesI()
  {
/* 132 */     return this.properties;
  }
  
  public void setPayload(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 145 */     this.payload = paramArrayOfByte;
/* 146 */     this.toid = TypeDescriptor.RAWTOID;
  }
  
  public void setPayload(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws SQLException
  {
/* 154 */     this.payload = paramArrayOfByte1;
/* 155 */     this.toid = paramArrayOfByte2;
  }
  
  public void setPayload(STRUCT paramSTRUCT)
    throws SQLException
  {
/* 163 */     this.payload = paramSTRUCT.toBytes();
/* 164 */     this.payLoadSTRUCT = paramSTRUCT;
/* 165 */     this.toid = paramSTRUCT.getDescriptor().getOracleTypeADT().getTOID();
  }
  
  public void setPayload(ANYDATA paramANYDATA)
    throws SQLException
  {
/* 173 */     this.payload = paramANYDATA.toDatum(this.conn).shareBytes();
/* 174 */     this.payLoadANYDATA = paramANYDATA;
/* 175 */     this.toid = TypeDescriptor.ANYDATATOID;
  }
  
  public void setPayload(RAW paramRAW)
    throws SQLException
  {
/* 182 */     this.payload = paramRAW.shareBytes();
/* 183 */     this.payLoadRAW = paramRAW;
/* 184 */     this.toid = TypeDescriptor.RAWTOID;
  }
  
  public void setPayload(XMLType paramXMLType)
    throws SQLException
  {
/* 191 */     this.payload = paramXMLType.toBytes();
/* 192 */     this.payLoadXMLType = paramXMLType;
/* 193 */     this.toid = TypeDescriptor.XMLTYPETOID;
  }
  
  public byte[] getPayload()
  {
/* 200 */     return this.payload;
  }
  
  public RAW getRAWPayload()
    throws SQLException
  {
/* 207 */     RAW localRAW = null;
/* 208 */     if (this.payLoadRAW != null) {
/* 209 */       localRAW = this.payLoadRAW;
/* 210 */     } else if (isRAWPayload())
    {
/* 212 */       this.payLoadRAW = new RAW(this.payload);
/* 213 */       localRAW = this.payLoadRAW;
    }
    else
    {
/* 217 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 193);
/* 218 */       localSQLException.fillInStackTrace();
/* 219 */       throw localSQLException;
    }
/* 221 */     return localRAW;
  }
  
  public boolean isRAWPayload()
    throws SQLException
  {
/* 229 */     if ((this.toid == null) || (this.toid.length != 16))
    {
/* 231 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 252);
/* 232 */       localSQLException.fillInStackTrace();
/* 233 */       throw localSQLException;
    }
    
/* 236 */     if (compareToid(this.toid, TypeDescriptor.RAWTOID)) {
/* 237 */       return true;
    }
/* 239 */     return false;
  }
  
  public STRUCT getSTRUCTPayload()
    throws SQLException
  {
/* 247 */     STRUCT localSTRUCT = null;
    SQLException localSQLException;
/* 249 */     if (!isSTRUCTPayload())
    {
/* 251 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 193);
/* 252 */       localSQLException.fillInStackTrace();
/* 253 */       throw localSQLException;
    }
    
/* 256 */     if (this.payLoadSTRUCT != null) {
/* 257 */       localSTRUCT = this.payLoadSTRUCT;
    }
    else {
/* 260 */       if (this.sd == null)
      {
/* 262 */         this.typeName = OracleTypeADT.toid2typename(this.conn, this.toid);
/* 263 */         this.sd = TypeDescriptor.getTypeDescriptor(this.typeName, (OracleConnection)this.conn);
      }
      
/* 266 */       if ((this.sd instanceof StructDescriptor))
      {
/* 271 */         localSTRUCT = new STRUCT((StructDescriptor)this.sd, this.payload, this.conn);
/* 272 */         this.payLoadSTRUCT = localSTRUCT;
      }
      else
      {
/* 276 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 193);
/* 277 */         localSQLException.fillInStackTrace();
/* 278 */         throw localSQLException;
      }
    }
    
/* 282 */     return localSTRUCT;
  }
  
  public boolean isSTRUCTPayload()
    throws SQLException
  {
/* 289 */     if ((this.toid == null) || (this.toid.length != 16))
    {
/* 291 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 252);
/* 292 */       localSQLException.fillInStackTrace();
/* 293 */       throw localSQLException;
    }
    
/* 296 */     boolean bool = true;
    
/* 298 */     int i = 1;
/* 299 */     for (int j = 0; j < 15; j++) {
/* 300 */       if (this.toid[j] != 0)
      {
/* 302 */         i = 0;
/* 303 */         break;
      }
    }
/* 306 */     if ((i != 0) || (isRAWPayload()) || (isANYDATAPayload())) {
/* 307 */       bool = false;
    }
/* 309 */     return bool;
  }
  
  public ANYDATA getANYDATAPayload()
    throws SQLException
  {
/* 316 */     ANYDATA localANYDATA = null;
    
/* 318 */     if (this.payLoadANYDATA != null) {
/* 319 */       localANYDATA = this.payLoadANYDATA; } else { Object localObject;
/* 320 */       if (isANYDATAPayload())
      {
/* 322 */         localObject = OpaqueDescriptor.createDescriptor("SYS.ANYDATA", this.conn);
        
/* 324 */         OPAQUE localOPAQUE = new OPAQUE((OpaqueDescriptor)localObject, this.payload, this.conn);
/* 325 */         this.payLoadANYDATA = new ANYDATA(localOPAQUE);
/* 326 */         localANYDATA = this.payLoadANYDATA;
      }
      else
      {
/* 330 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 193);
/* 331 */         ((SQLException)localObject).fillInStackTrace();
/* 332 */         throw ((Throwable)localObject);
      } }
/* 334 */     return localANYDATA;
  }
  
  public boolean isANYDATAPayload()
    throws SQLException
  {
/* 341 */     if ((this.toid == null) || (this.toid.length != 16))
    {
/* 343 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 252);
/* 344 */       localSQLException.fillInStackTrace();
/* 345 */       throw localSQLException;
    }
/* 347 */     if (((this.typeName != null) && (this.typeName.equals("SYS.ANYDATA"))) || (compareToid(this.toid, TypeDescriptor.ANYDATATOID)))
    {
/* 349 */       return true;
    }
/* 351 */     return false;
  }
  
  public XMLType getXMLTypePayload()
    throws SQLException
  {
/* 358 */     XMLType localXMLType = null;
    
/* 360 */     if (this.payLoadXMLType != null) {
/* 361 */       localXMLType = this.payLoadXMLType; } else { Object localObject;
/* 362 */       if (isXMLTypePayload())
      {
/* 364 */         localObject = OpaqueDescriptor.createDescriptor("SYS.XMLTYPE", this.conn);
        
/* 366 */         OPAQUE localOPAQUE = new OPAQUE((OpaqueDescriptor)localObject, this.payload, this.conn);
/* 367 */         this.payLoadXMLType = XMLType.createXML(localOPAQUE);
/* 368 */         localXMLType = this.payLoadXMLType;
      }
      else
      {
/* 372 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 193);
/* 373 */         ((SQLException)localObject).fillInStackTrace();
/* 374 */         throw ((Throwable)localObject);
      } }
/* 376 */     return localXMLType;
  }
  
  public boolean isXMLTypePayload()
    throws SQLException
  {
/* 383 */     if ((this.toid == null) || (this.toid.length != 16))
    {
/* 385 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 252);
/* 386 */       localSQLException.fillInStackTrace();
/* 387 */       throw localSQLException;
    }
/* 389 */     if (((this.typeName != null) && (this.typeName.equals("SYS.XMLTYPE"))) || (compareToid(this.toid, TypeDescriptor.XMLTYPETOID)))
    {
/* 391 */       return true;
    }
/* 393 */     return false;
  }
  
  public byte[] getPayloadTOID()
  {
/* 400 */     return this.toid;
  }
  
  static boolean compareToid(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
/* 407 */     boolean bool = false;
    
/* 409 */     if (paramArrayOfByte1 != null)
    {
/* 411 */       if (paramArrayOfByte1 == paramArrayOfByte2) {
/* 412 */         bool = true;
/* 413 */       } else if (paramArrayOfByte1.length == paramArrayOfByte2.length)
      {
/* 415 */         int i = 1;
/* 416 */         for (int j = 0; j < paramArrayOfByte1.length; j++)
/* 417 */           if (paramArrayOfByte1[j] != paramArrayOfByte2[j])
          {
/* 419 */             i = 0;
/* 420 */             break;
          }
/* 422 */         if (i != 0)
/* 423 */           bool = true;
      }
    }
/* 426 */     return bool;
  }
  
  public String toString()
  {
/* 433 */     StringBuffer localStringBuffer = new StringBuffer();
/* 434 */     localStringBuffer.append("Message Properties={");
/* 435 */     localStringBuffer.append(this.properties);
/* 436 */     localStringBuffer.append("} ");
/* 437 */     return localStringBuffer.toString();
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 452 */     return null;
  }
  
/* 457 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/AQMessageI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */