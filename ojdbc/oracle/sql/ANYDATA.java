package oracle.sql;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleData;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.driver.InternalFactory;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.oracore.PickleContext;
import oracle.xdb.XMLType;
public class ANYDATA
  implements ORAData, OracleData
{
  static final byte KAD_VSN = 1;
  static final byte KAD_VSN2 = 2;
  boolean isNull;
  byte[] data;
  TypeDescriptor type;
/* 135 */   boolean isREF = false;
/* 136 */   short serverCharsetId = 0;
/* 137 */   short serverNCharsetId = 0;
  
  OracleConnection connection;
  
  ANYDATA(TypeDescriptor paramTypeDescriptor, boolean paramBoolean1, byte[] paramArrayOfByte, boolean paramBoolean2)
  {
/* 146 */     this.type = paramTypeDescriptor;
/* 147 */     this.isNull = paramBoolean1;
/* 148 */     this.data = paramArrayOfByte;
/* 149 */     this.isREF = paramBoolean2;
  }
  
  public ANYDATA(OPAQUE paramOPAQUE)
    throws SQLException
  {
/* 159 */     byte[] arrayOfByte = paramOPAQUE.getBytesValue();
/* 160 */     this.connection = paramOPAQUE.getPhysicalConnection();
/* 161 */     if (this.connection != null)
    {
/* 163 */       this.serverCharsetId = this.connection.getDbCsId();
/* 164 */       this.serverNCharsetId = this.connection.getNCharSet();
    }
/* 166 */     unpickle(arrayOfByte, 0);
  }
  
  int unpickle(byte[] paramArrayOfByte, int paramInt)
    throws SQLException
  {
/* 179 */     PickleContext localPickleContext = new PickleContext(paramArrayOfByte, paramInt);
/* 180 */     int i = localPickleContext.readByte();
/* 181 */     if (i == 2) {
/* 182 */       localPickleContext.skipBytes(4);
    }
    
/* 195 */     short[] arrayOfShort = new short[1];
/* 196 */     this.type = TypeDescriptor.unpickleOpaqueTypeImage(localPickleContext, this.connection, arrayOfShort);
/* 197 */     if (localPickleContext.readByte() != 0) {
/* 198 */       this.isNull = true;
    } else
/* 200 */       this.isNull = false;
/* 201 */     if (!this.isNull)
    {
/* 203 */       j = (int)localPickleContext.readUB4();
/* 204 */       this.data = localPickleContext.readDataValue(j);
    }
/* 206 */     if (arrayOfShort[0] == 110) {
/* 207 */       this.isREF = true;
    } else
/* 209 */       this.isREF = false;
/* 210 */     int j = localPickleContext.offset();
/* 211 */     return j;
  }
  
  int pickle(byte[] paramArrayOfByte, int paramInt)
  {
/* 225 */     paramArrayOfByte[(paramInt++)] = 1;
/* 226 */     paramInt = this.type.pickleOpaqueTypeImage(paramArrayOfByte, paramInt, this.isREF);
    
/* 228 */     if (this.isNull) {
/* 229 */       paramArrayOfByte[(paramInt++)] = 1;
    } else
/* 231 */       paramArrayOfByte[(paramInt++)] = 0;
/* 232 */     if (!this.isNull)
    {
/* 234 */       int i = this.data.length;
/* 235 */       paramArrayOfByte[(paramInt++)] = ((byte)((i & 0xFF000000) >> 24 & 0xFF));
/* 236 */       paramArrayOfByte[(paramInt++)] = ((byte)((i & 0xFF0000) >> 16 & 0xFF));
/* 237 */       paramArrayOfByte[(paramInt++)] = ((byte)((i & 0xFF00) >> 8 & 0xFF));
/* 238 */       paramArrayOfByte[(paramInt++)] = ((byte)(i & 0xFF));
/* 239 */       System.arraycopy(this.data, 0, paramArrayOfByte, paramInt, i);
/* 240 */       paramInt += i;
    }
/* 242 */     return paramInt;
  }
  
  int getImageSize()
  {
/* 253 */     int i = this.type.getOpaqueImageTypeSize() + 1 + 1;
    
/* 256 */     if (!this.isNull)
/* 257 */       i += 4 + this.data.length;
/* 258 */     return i;
  }
  
  public Datum toDatum(Connection paramConnection)
    throws SQLException
  {
/* 267 */     this.connection = ((OracleConnection)paramConnection);
/* 268 */     OpaqueDescriptor localOpaqueDescriptor = OpaqueDescriptor.createDescriptor("SYS.ANYDATA", paramConnection);
    
/* 270 */     byte[] arrayOfByte = new byte[getImageSize()];
/* 271 */     pickle(arrayOfByte, 0);
/* 272 */     OPAQUE localOPAQUE = new OPAQUE(localOpaqueDescriptor, this.connection, arrayOfByte);
    
/* 280 */     localOPAQUE.setShareBytes(localOPAQUE.toBytes());
/* 281 */     return localOPAQUE;
  }
  
  public Object toJDBCObject(Connection paramConnection)
    throws SQLException
  {
/* 288 */     return toDatum(paramConnection);
  }
  
  public static ANYDATA convertDatum(Datum paramDatum)
    throws SQLException
  {
/* 325 */     ANYDATA localANYDATA = null;
/* 326 */     if ((paramDatum instanceof STRUCT)) {
/* 327 */       localANYDATA = new ANYDATA(((STRUCT)paramDatum).getDescriptor(), false, ((STRUCT)paramDatum).toBytes(), false);
/* 328 */     } else if ((paramDatum instanceof ARRAY)) {
/* 329 */       localANYDATA = new ANYDATA(((ARRAY)paramDatum).getDescriptor(), false, ((ARRAY)paramDatum).toBytes(), false);
/* 330 */     } else if ((paramDatum instanceof REF)) {
/* 331 */       localANYDATA = new ANYDATA(((REF)paramDatum).getDescriptor(), false, ((REF)paramDatum).getBytes(), true);
/* 332 */     } else if ((paramDatum instanceof OPAQUE)) {
/* 333 */       localANYDATA = new ANYDATA(((OPAQUE)paramDatum).getDescriptor(), false, ((OPAQUE)paramDatum).toBytes(), false);
    }
    else {
/* 336 */       TypeDescriptor localTypeDescriptor = null;
/* 337 */       if ((paramDatum instanceof NUMBER)) {
/* 338 */         localTypeDescriptor = new TypeDescriptor((short)2);
/* 339 */       } else if ((paramDatum instanceof DATE)) {
/* 340 */         localTypeDescriptor = new TypeDescriptor((short)12);
/* 341 */       } else if ((paramDatum instanceof INTERVALDS)) {
/* 342 */         localTypeDescriptor = new TypeDescriptor((short)190);
/* 343 */       } else if ((paramDatum instanceof INTERVALYM)) {
/* 344 */         localTypeDescriptor = new TypeDescriptor((short)189);
/* 345 */       } else if ((paramDatum instanceof TIMESTAMPTZ)) {
/* 346 */         localTypeDescriptor = new TypeDescriptor((short)188);
/* 347 */       } else if ((paramDatum instanceof TIMESTAMPLTZ)) {
/* 348 */         localTypeDescriptor = new TypeDescriptor((short)232);
/* 349 */       } else if ((paramDatum instanceof TIMESTAMP)) {
/* 350 */         localTypeDescriptor = new TypeDescriptor((short)187);
/* 351 */       } else if ((paramDatum instanceof NCLOB))
      {
/* 353 */         localTypeDescriptor = new TypeDescriptor((short)288);
/* 354 */       } else if ((paramDatum instanceof CLOB)) {
/* 355 */         localTypeDescriptor = new TypeDescriptor((short)112);
/* 356 */       } else if ((paramDatum instanceof BLOB)) {
/* 357 */         localTypeDescriptor = new TypeDescriptor((short)113);
/* 358 */       } else if ((paramDatum instanceof BFILE)) {
/* 359 */         localTypeDescriptor = new TypeDescriptor((short)114);
/* 360 */       } else if ((paramDatum instanceof RAW)) {
/* 361 */         localTypeDescriptor = new TypeDescriptor((short)95);
/* 362 */       } else if ((paramDatum instanceof BINARY_DOUBLE)) {
/* 363 */         localTypeDescriptor = new TypeDescriptor((short)101);
/* 364 */       } else if ((paramDatum instanceof BINARY_FLOAT)) {
/* 365 */         localTypeDescriptor = new TypeDescriptor((short)100);
/* 366 */       } else if ((paramDatum instanceof ROWID)) {
/* 367 */         localTypeDescriptor = new TypeDescriptor((short)104);
/* 368 */       } else if ((paramDatum instanceof CHAR)) {
/* 369 */         localTypeDescriptor = new TypeDescriptor((short)96);
      }
/* 371 */       if ((paramDatum instanceof ROWID))
      {
/* 373 */         byte[] arrayOfByte1 = paramDatum.shareBytes();
        
/* 378 */         long[] arrayOfLong = InternalFactory.rowid2urowid(arrayOfByte1, 0, arrayOfByte1.length);
/* 379 */         byte[] arrayOfByte2 = new byte[13];
        
/* 382 */         arrayOfByte2[0] = 1;
/* 383 */         arrayOfByte2[1] = ((byte)(int)((arrayOfLong[0] & 0xFFFFFFFFFF000000) >> 24));
/* 384 */         arrayOfByte2[2] = ((byte)(int)((arrayOfLong[0] & 0xFF0000) >> 16));
/* 385 */         arrayOfByte2[3] = ((byte)(int)((arrayOfLong[0] & 0xFF00) >> 8));
/* 386 */         arrayOfByte2[4] = ((byte)(int)(arrayOfLong[0] & 0xFF));
/* 387 */         arrayOfByte2[5] = ((byte)(int)((arrayOfLong[1] & 0xFF00) >> 8));
/* 388 */         arrayOfByte2[6] = ((byte)(int)(arrayOfLong[1] & 0xFF));
/* 389 */         arrayOfByte2[7] = ((byte)(int)((arrayOfLong[2] & 0xFFFFFFFFFF000000) >> 24));
/* 390 */         arrayOfByte2[8] = ((byte)(int)((arrayOfLong[2] & 0xFF0000) >> 16));
/* 391 */         arrayOfByte2[9] = ((byte)(int)((arrayOfLong[2] & 0xFF00) >> 8));
/* 392 */         arrayOfByte2[10] = ((byte)(int)(arrayOfLong[2] & 0xFF));
/* 393 */         arrayOfByte2[11] = ((byte)(int)((arrayOfLong[3] & 0xFF00) >> 8));
/* 394 */         arrayOfByte2[12] = ((byte)(int)(arrayOfLong[3] & 0xFF));
/* 395 */         localANYDATA = new ANYDATA(localTypeDescriptor, false, arrayOfByte2, false);
      }
      else {
/* 398 */         localANYDATA = new ANYDATA(localTypeDescriptor, false, paramDatum.shareBytes(), false);
      }
    }
    
/* 402 */     if ((paramDatum instanceof DatumWithConnection)) {
/* 403 */       localANYDATA.connection = ((DatumWithConnection)paramDatum).getInternalConnection();
    }
/* 405 */     return localANYDATA;
  }
  
  public TypeDescriptor getTypeDescriptor()
  {
/* 415 */     return this.type;
  }
  
  public boolean isNull()
  {
/* 426 */     return this.isNull;
  }
  
  public byte[] getData()
  {
/* 437 */     return this.data;
  }
  
  public boolean isREF()
  {
/* 446 */     return this.isREF;
  }
  
  public String stringValue()
    throws SQLException
  {
/* 471 */     return stringValue(this.connection);
  }
  
  public String stringValue(Connection paramConnection)
    throws SQLException
  {
/* 502 */     String str1 = null;
    
/* 504 */     str1 = "ANYDATA TypeCode: \"" + getTypeDescriptor().getTypeCodeName();
/* 505 */     if (this.isREF)
/* 506 */       str1 = str1 + "(REF)";
/* 507 */     str1 = str1 + "\" - ANYDATA Value: \"";
    
/* 509 */     Datum localDatum = accessDatum();
/* 510 */     int i = 0;
    try
    {
/* 513 */       str1 = str1 + localDatum.stringValue();
/* 514 */       i = 1;
    }
    catch (SQLException localSQLException) {}
    
/* 518 */     if (i == 0)
    {
/* 520 */       if (((this.type.getInternalTypeCode() == 108) || (this.type.getInternalTypeCode() == 110)) && (!this.type.isTransient()))
      {
/* 523 */         str1 = str1 + ((StructDescriptor)this.type).getName() + "(...)";
/* 524 */       } else if ((this.type.getInternalTypeCode() == 122) && (!this.type.isTransient()))
      {
/* 526 */         str1 = str1 + ((ArrayDescriptor)this.type).getName() + "(...)";
      } else {
        Object localObject1;
/* 529 */         switch (this.type.getInternalTypeCode())
        {
        case 113: 
/* 534 */           localObject1 = ((BLOB)localDatum).getBinaryStream();
          try
          {
/* 537 */             String str3 = "";
/* 538 */             int j; while ((j = ((InputStream)localObject1).read()) != -1)
/* 539 */               str3 = str3 + Integer.toHexString(j);
/* 540 */             str1 = str1 + str3;
          } catch (IOException localIOException2) {}finally {
/* 542 */             try { ((InputStream)localObject1).close();
            }
            catch (IOException localIOException4) {}
          }
        
        case 188: 
/* 550 */           if (paramConnection == null) {
/* 551 */             str1 = str1 + "?";
          } else {
/* 553 */             str1 = str1 + ((TIMESTAMPTZ)localDatum).stringValue(paramConnection);
          }
/* 555 */           break;
        
        case 232: 
/* 561 */           if (paramConnection == null) {
/* 562 */             str1 = str1 + "?";
          } else {
/* 564 */             str1 = str1 + ((TIMESTAMPLTZ)localDatum).stringValue(paramConnection);
          }
/* 566 */           break;
        
        case 114: 
/* 569 */           str1 = str1 + "bfile_dir=" + ((BFILE)localDatum).getDirAlias() + " bfile_name=" + ((BFILE)localDatum).getName();
          
/* 572 */           break;
        case 58: 
/* 574 */           localObject1 = (OPAQUE)localDatum;
/* 575 */           String str2 = ((OPAQUE)localObject1).getSQLTypeName();
/* 576 */           str1 = str1 + "OPAQUE(" + str2 + ")";
/* 577 */           if (str2.compareTo("SYS.XMLTYPE") == 0)
          {
/* 579 */             str1 = str1 + ":";
/* 580 */             XMLType localXMLType = XMLType.createXML((OPAQUE)localObject1);
/* 581 */             str1 = str1 + localXMLType.getStringVal();
          }
          break;
        }
        
      } }
/* 587 */     str1 = str1 + "\"";
    
/* 589 */     return str1;
  }
  
  public Datum accessDatum()
    throws SQLException
  {
/* 601 */     Object localObject = null;
/* 602 */     if (!this.isNull)
    {
/* 604 */       int i = this.type.getInternalTypeCode();
/* 605 */       switch (i)
      {
      case 58: 
/* 608 */         localObject = new OPAQUE((OpaqueDescriptor)this.type, this.data, this.connection);
/* 609 */         break;
      case 108: 
/* 611 */         if ((this.type instanceof OpaqueDescriptor)) {
/* 612 */           localObject = new OPAQUE((OpaqueDescriptor)this.type, this.data, this.connection);
/* 613 */         } else if (!this.isREF) {
/* 614 */           localObject = new STRUCT((StructDescriptor)this.type, this.data, this.connection);
        } else
/* 616 */           localObject = new REF((StructDescriptor)this.type, this.connection, this.data);
/* 617 */         break;
      case 122: 
/* 619 */         localObject = new ARRAY((ArrayDescriptor)this.type, this.data, this.connection);
/* 620 */         break;
      case 110: 
/* 622 */         localObject = new REF((StructDescriptor)this.type, this.connection, this.data);
/* 623 */         break;
      case 2: 
/* 625 */         localObject = new NUMBER(this.data);
/* 626 */         break;
      case 12: 
/* 628 */         localObject = new DATE(this.data);
/* 629 */         break;
      case 190: 
/* 631 */         localObject = new INTERVALDS(this.data);
/* 632 */         break;
      case 189: 
/* 634 */         localObject = new INTERVALYM(this.data);
/* 635 */         break;
      case 188: 
/* 637 */         localObject = new TIMESTAMPTZ(this.data);
/* 638 */         break;
      case 232: 
/* 640 */         localObject = new TIMESTAMPLTZ(this.data);
/* 641 */         break;
      case 187: 
/* 643 */         localObject = new TIMESTAMP(this.data);
/* 644 */         break;
      case 112: 
/* 646 */         localObject = new CLOB(this.connection, this.data);
/* 647 */         break;
      case 288: 
/* 649 */         localObject = new NCLOB(this.connection, this.data);
/* 650 */         break;
      case 113: 
/* 652 */         localObject = new BLOB(this.connection, this.data);
/* 653 */         break;
      case 114: 
/* 655 */         localObject = new BFILE(this.connection, this.data);
/* 656 */         break;
      case 95: 
/* 658 */         localObject = new RAW(this.data);
/* 659 */         break;
      case 101: 
/* 661 */         localObject = new BINARY_DOUBLE(this.data);
/* 662 */         break;
      case 100: 
/* 664 */         localObject = new BINARY_FLOAT(this.data);
/* 665 */         break;
      
      case 104: 
/* 668 */         long l1 = (this.data[1] & 0xFF) << 24 | (this.data[2] & 0xFF) << 16 | (this.data[3] & 0xFF) << 8 | this.data[4] & 0xFF;
        
/* 672 */         long l2 = (this.data[5] & 0xFF) << 8 | this.data[6] & 0xFF;
        
/* 674 */         long l3 = (this.data[7] & 0xFF) << 24 | (this.data[8] & 0xFF) << 16 | (this.data[9] & 0xFF) << 8 | this.data[10] & 0xFF;
        
/* 678 */         long l4 = (this.data[11] & 0xFF) << 8 | this.data[12] & 0xFF;
        
/* 680 */         long[] arrayOfLong = new long[4];
/* 681 */         arrayOfLong[0] = l1;
/* 682 */         arrayOfLong[1] = l2;
/* 683 */         arrayOfLong[2] = l3;
/* 684 */         arrayOfLong[3] = l4;
/* 685 */         byte[] arrayOfByte = InternalFactory.urowid2rowid(arrayOfLong);
/* 686 */         localObject = new ROWID(arrayOfByte);
/* 687 */         break;
      case 1: 
      case 9: 
      case 96: 
/* 691 */         if (this.serverCharsetId != 0) {
/* 692 */           localObject = new CHAR(this.data, CharacterSet.make(this.serverCharsetId));
        } else
/* 694 */           localObject = new CHAR(this.data, null);
/* 695 */         break;
      case 286: 
      case 287: 
/* 698 */         if (this.serverNCharsetId != 0) {
/* 699 */           localObject = new CHAR(this.data, CharacterSet.make(this.serverNCharsetId));
        } else
/* 701 */           localObject = new CHAR(this.data, null);
/* 702 */         break;
      default: 
/* 704 */         String str = "internal typecode: " + i;
        
/* 706 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, str);
/* 707 */         localSQLException.fillInStackTrace();
/* 708 */         throw localSQLException;
      }
      
    }
/* 712 */     return (Datum)localObject;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 726 */     return this.connection;
  }
  
/* 731 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ANYDATA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */