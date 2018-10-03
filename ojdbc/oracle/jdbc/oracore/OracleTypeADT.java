/*      */ package oracle.jdbc.oracore;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringWriter;
/*      */ import java.sql.Blob;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLData;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Map;
/*      */ import java.util.Vector;
/*      */ import oracle.jdbc.OracleCallableStatement;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.ObjectData;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.sql.AttributeDescriptor;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.JAVA_STRUCT;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.SQLName;
/*      */ import oracle.sql.STRUCT;
/*      */ import oracle.sql.StructDescriptor;
/*      */ import oracle.sql.TypeDescriptor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleTypeADT
/*      */   extends OracleNamedType
/*      */   implements Serializable
/*      */ {
/*      */   static final long serialVersionUID = 3031304012507165702L;
/*      */   static final int S_TOP = 1;
/*      */   static final int S_EMBEDDED = 2;
/*      */   static final int S_UPT_ADT = 4;
/*      */   static final int S_JAVA_OBJECT = 16;
/*      */   static final int S_FINAL_TYPE = 32;
/*      */   static final int S_SUB_TYPE = 64;
/*      */   static final int S_ATTR_TDS = 128;
/*      */   static final int S_HAS_METADATA = 256;
/*      */   static final int S_TDS_PARSED = 512;
/*   62 */   private int statusBits = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   69 */   int tdsVersion = 55537;
/*      */   
/*      */   static final int KOPT_V80 = 1;
/*      */   
/*      */   static final int KOPT_V81 = 2;
/*      */   
/*      */   static final int KOPT_VNFT = 3;
/*      */   
/*      */   static final int KOPT_VERSION = 3;
/*      */   
/*   79 */   boolean endOfAdt = false;
/*      */   
/*   81 */   int typeVersion = 1;
/*      */   
/*      */ 
/*   84 */   long fixedDataSize = -1L;
/*   85 */   int alignmentRequirement = -1;
/*      */   
/*      */ 
/*   88 */   OracleType[] attrTypes = null;
/*      */   
/*      */   String[] attrNames;
/*      */   String[] attrTypeNames;
/*   92 */   public long tdoCState = 0L;
/*      */   
/*   94 */   byte[] toid = null;
/*      */   
/*      */   int charSetId;
/*      */   
/*      */   int charSetForm;
/*      */   
/*      */   int flattenedAttrNum;
/*      */   
/*      */   transient int opcode;
/*  103 */   transient int idx = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  110 */   boolean isTransient = false;
/*      */   
/*      */   static final int CURRENT_USER_OBJECT = 0;
/*      */   static final int CURRENT_USER_SYNONYM = 1;
/*      */   static final int CURRENT_USER_SYNONYM_10g = 2;
/*      */   static final int CURRENT_USER_PUBLIC_SYNONYM = 3;
/*      */   static final int CURRENT_USER_PUBLIC_SYNONYM_10g = 4;
/*      */   static final int POSSIBLY_OTHER_USER_OBJECT = 5;
/*      */   static final int POSSIBLY_OTHER_USER_OBJECT_10g = 6;
/*      */   static final int OTHER_USER_OBJECT = 7;
/*      */   static final int OTHER_USER_SYNONYM = 8;
/*      */   static final int PUBLIC_SYNONYM = 9;
/*      */   static final int PUBLIC_SYNONYM_10g = 10;
/*      */   static final int BREAK = 11;
/*      */   
/*      */   protected OracleTypeADT() {}
/*      */   
/*      */   public OracleTypeADT(byte[] paramArrayOfByte, int paramInt1, int paramInt2, short paramShort, String paramString)
/*      */     throws SQLException
/*      */   {
/*  130 */     this(paramString, (OracleConnection)null);
/*      */     
/*  132 */     this.toid = paramArrayOfByte;
/*  133 */     this.typeVersion = paramInt1;
/*  134 */     this.charSetId = paramInt2;
/*  135 */     this.charSetForm = paramShort;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeADT(String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  144 */     super(paramString, (OracleConnection)paramConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeADT(OracleTypeADT paramOracleTypeADT, int paramInt, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  155 */     super(paramOracleTypeADT, paramInt, (OracleConnection)paramConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeADT(SQLName paramSQLName, byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  169 */     this.sqlName = paramSQLName;
/*  170 */     init(paramArrayOfByte2, paramOracleConnection);
/*  171 */     this.toid = paramArrayOfByte1;
/*  172 */     this.typeVersion = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeADT(AttributeDescriptor[] paramArrayOfAttributeDescriptor, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  192 */     setConnectionInternal(paramOracleConnection);
/*  193 */     this.isTransient = true;
/*  194 */     this.flattenedAttrNum = paramArrayOfAttributeDescriptor.length;
/*  195 */     this.attrTypes = new OracleType[this.flattenedAttrNum];
/*  196 */     this.attrNames = new String[this.flattenedAttrNum];
/*  197 */     for (int i = 0; i < this.flattenedAttrNum; i++)
/*  198 */       this.attrNames[i] = paramArrayOfAttributeDescriptor[i].getAttributeName();
/*  199 */     this.statusBits |= 0x100;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  205 */     for (i = 0; i < this.flattenedAttrNum; i++)
/*      */     {
/*  207 */       TypeDescriptor localTypeDescriptor = paramArrayOfAttributeDescriptor[i].getTypeDescriptor();
/*  208 */       switch (localTypeDescriptor.getInternalTypeCode())
/*      */       {
/*      */       case 12: 
/*  211 */         this.attrTypes[i] = new OracleTypeDATE();
/*  212 */         break;
/*      */       case 9: 
/*  214 */         this.attrTypes[i] = new OracleTypeCHAR(this.connection, 12);
/*  215 */         ((OracleTypeCHAR)this.attrTypes[i]).length = ((int)localTypeDescriptor.getPrecision());
/*  216 */         ((OracleTypeCHAR)this.attrTypes[i]).form = 1;
/*  217 */         break;
/*      */       case 96: 
/*  219 */         this.attrTypes[i] = new OracleTypeCHAR(this.connection, 1);
/*  220 */         ((OracleTypeCHAR)this.attrTypes[i]).length = ((int)localTypeDescriptor.getPrecision());
/*  221 */         ((OracleTypeCHAR)this.attrTypes[i]).form = 1;
/*  222 */         break;
/*      */       case 108: 
/*  224 */         this.attrTypes[i] = ((OracleTypeADT)localTypeDescriptor.getPickler());
/*  225 */         ((OracleTypeADT)this.attrTypes[i]).setEmbeddedADT();
/*  226 */         break;
/*      */       case 2: 
/*  228 */         this.attrTypes[i] = new OracleTypeNUMBER(2);
/*  229 */         ((OracleTypeNUMBER)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  230 */         ((OracleTypeNUMBER)this.attrTypes[i]).scale = localTypeDescriptor.getScale();
/*  231 */         break;
/*      */       case 7: 
/*  233 */         this.attrTypes[i] = new OracleTypeNUMBER(3);
/*  234 */         ((OracleTypeNUMBER)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  235 */         ((OracleTypeNUMBER)this.attrTypes[i]).scale = localTypeDescriptor.getScale();
/*  236 */         break;
/*      */       case 22: 
/*  238 */         this.attrTypes[i] = new OracleTypeNUMBER(8);
/*  239 */         ((OracleTypeNUMBER)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  240 */         ((OracleTypeNUMBER)this.attrTypes[i]).scale = localTypeDescriptor.getScale();
/*  241 */         break;
/*      */       case 4: 
/*  243 */         this.attrTypes[i] = new OracleTypeFLOAT();
/*  244 */         ((OracleTypeFLOAT)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  245 */         break;
/*      */       case 100: 
/*  247 */         this.attrTypes[i] = new OracleTypeBINARY_FLOAT();
/*  248 */         break;
/*      */       case 101: 
/*  250 */         this.attrTypes[i] = new OracleTypeBINARY_DOUBLE();
/*  251 */         break;
/*      */       
/*      */       case 29: 
/*  254 */         this.attrTypes[i] = new OracleTypeSINT32();
/*  255 */         break;
/*      */       
/*      */       case 110: 
/*  258 */         this.attrTypes[i] = new OracleTypeREF(this, i, this.connection);
/*  259 */         break;
/*      */       case 114: 
/*  261 */         this.attrTypes[i] = new OracleTypeBFILE(this.connection);
/*  262 */         break;
/*      */       case 95: 
/*  264 */         this.attrTypes[i] = new OracleTypeRAW();
/*  265 */         break;
/*      */       case 112: 
/*  267 */         this.attrTypes[i] = new OracleTypeCLOB(this.connection);
/*  268 */         break;
/*      */       case 113: 
/*  270 */         this.attrTypes[i] = new OracleTypeBLOB(this.connection);
/*  271 */         break;
/*      */       case 187: 
/*  273 */         this.attrTypes[i] = new OracleTypeTIMESTAMP(this.connection);
/*  274 */         ((OracleTypeTIMESTAMP)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  275 */         break;
/*      */       case 188: 
/*  277 */         this.attrTypes[i] = new OracleTypeTIMESTAMPTZ(this.connection);
/*  278 */         ((OracleTypeTIMESTAMPTZ)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  279 */         break;
/*      */       case 232: 
/*  281 */         this.attrTypes[i] = new OracleTypeTIMESTAMPLTZ(this.connection);
/*  282 */         ((OracleTypeTIMESTAMPLTZ)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  283 */         break;
/*      */       case 189: 
/*  285 */         this.attrTypes[i] = new OracleTypeINTERVAL(this.connection);
/*  286 */         ((OracleTypeINTERVAL)this.attrTypes[i]).typeId = 7;
/*  287 */         ((OracleTypeINTERVAL)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  288 */         ((OracleTypeINTERVAL)this.attrTypes[i]).scale = localTypeDescriptor.getScale();
/*  289 */         break;
/*      */       case 190: 
/*  291 */         this.attrTypes[i] = new OracleTypeINTERVAL(this.connection);
/*  292 */         ((OracleTypeINTERVAL)this.attrTypes[i]).typeId = 10;
/*  293 */         ((OracleTypeINTERVAL)this.attrTypes[i]).precision = ((int)localTypeDescriptor.getPrecision());
/*  294 */         ((OracleTypeINTERVAL)this.attrTypes[i]).scale = localTypeDescriptor.getScale();
/*  295 */         break;
/*      */       case 122: 
/*  297 */         this.attrTypes[i] = new OracleTypeCOLLECTION(this, i, this.connection);
/*  298 */         break;
/*      */       
/*      */       default: 
/*  301 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 48, "type: " + localTypeDescriptor.getInternalTypeCode());
/*  302 */         localSQLException.fillInStackTrace();
/*  303 */         throw localSQLException;
/*      */       }
/*      */       
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  321 */     if (paramObject != null)
/*      */     {
/*  323 */       if ((paramObject instanceof STRUCT))
/*      */       {
/*  325 */         return (STRUCT)paramObject;
/*      */       }
/*  327 */       if (((paramObject instanceof SQLData)) || ((paramObject instanceof ObjectData)))
/*      */       {
/*      */ 
/*  330 */         return STRUCT.toSTRUCT(paramObject, paramOracleConnection);
/*      */       }
/*  332 */       if ((paramObject instanceof Object[]))
/*      */       {
/*  334 */         localObject = createStructDescriptor();
/*  335 */         STRUCT localSTRUCT = createObjSTRUCT((StructDescriptor)localObject, (Object[])paramObject);
/*  336 */         return localSTRUCT;
/*      */       }
/*      */       
/*      */ 
/*  340 */       Object localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  341 */       ((SQLException)localObject).fillInStackTrace();
/*  342 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  346 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum[] toDatumArray(Object paramObject, OracleConnection paramOracleConnection, long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  360 */     Datum[] arrayOfDatum = null;
/*      */     
/*  362 */     if (paramObject != null) {
/*      */       Object localObject;
/*  364 */       if ((paramObject instanceof Object[]))
/*      */       {
/*  366 */         localObject = (Object[])paramObject;
/*      */         
/*  368 */         int i = (int)(paramInt == -1 ? localObject.length : Math.min(localObject.length - paramLong + 1L, paramInt));
/*      */         
/*      */ 
/*  371 */         arrayOfDatum = new Datum[i];
/*      */         
/*  373 */         for (int j = 0; j < i; j++) {
/*  374 */           arrayOfDatum[j] = toDatum(localObject[((int)paramLong + j - 1)], paramOracleConnection);
/*      */         }
/*      */       }
/*      */       else {
/*  378 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 59, paramObject);
/*  379 */         ((SQLException)localObject).fillInStackTrace();
/*  380 */         throw ((Throwable)localObject);
/*      */       }
/*      */     }
/*      */     
/*  384 */     return arrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTypeCode()
/*      */     throws SQLException
/*      */   {
/*  394 */     if ((getStatus() & 0x10) != 0) {
/*  395 */       return 2008;
/*      */     }
/*  397 */     return 2002;
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleType[] getAttrTypes()
/*      */     throws SQLException
/*      */   {
/*  404 */     if (this.attrTypes == null) {
/*  405 */       init(this.connection);
/*      */     }
/*  407 */     return this.attrTypes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInHierarchyOf(OracleType paramOracleType)
/*      */     throws SQLException
/*      */   {
/*  418 */     if (paramOracleType == null) {
/*  419 */       return false;
/*      */     }
/*  421 */     if (!paramOracleType.isObjectType()) {
/*  422 */       return false;
/*      */     }
/*  424 */     StructDescriptor localStructDescriptor = (StructDescriptor)paramOracleType.getTypeDescriptor();
/*      */     
/*      */ 
/*  427 */     return this.descriptor.isInHierarchyOf(localStructDescriptor.getName());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInHierarchyOf(StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/*  436 */     if (paramStructDescriptor == null) {
/*  437 */       return false;
/*      */     }
/*  439 */     return this.descriptor.isInHierarchyOf(paramStructDescriptor.getName());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isObjectType()
/*      */   {
/*  446 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public TypeDescriptor getTypeDescriptor()
/*      */   {
/*  453 */     return this.descriptor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void init(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  469 */     synchronized (paramOracleConnection)
/*      */     {
/*  471 */       byte[] arrayOfByte = initMetadata(paramOracleConnection);
/*  472 */       init(arrayOfByte, paramOracleConnection);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void init(byte[] paramArrayOfByte, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  486 */     synchronized (paramOracleConnection)
/*      */     {
/*  488 */       this.statusBits = 1;
/*  489 */       this.connection = paramOracleConnection;
/*      */       
/*  491 */       if (paramArrayOfByte != null) parseTDS(paramArrayOfByte, 0L);
/*  492 */       setStatusBits(256);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] initMetadata(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  510 */     synchronized (this.connection)
/*      */     {
/*  512 */       byte[] arrayOfByte = null;
/*  513 */       if ((this.statusBits & 0x100) != 0) { return null;
/*      */       }
/*  515 */       if (this.sqlName == null) { getFullName();
/*      */       }
/*      */       
/*  518 */       if ((this.statusBits & 0x100) == 0)
/*      */       {
/*  520 */         CallableStatement localCallableStatement = null;
/*      */         try
/*      */         {
/*  523 */           if (this.tdoCState == 0L) { this.tdoCState = this.connection.getTdoCState(this.sqlName.getSchema(), this.sqlName.getSimpleName());
/*      */           }
/*      */           
/*      */ 
/*  527 */           String str = "begin :1 := dbms_pickler.get_type_shape(:2,:3,:4,:5,:6,:7); end;";
/*      */           
/*      */ 
/*      */ 
/*  531 */           int i = 0;
/*  532 */           localCallableStatement = this.connection.prepareCall(str);
/*      */           
/*  534 */           localCallableStatement.registerOutParameter(1, 2);
/*  535 */           localCallableStatement.registerOutParameter(4, -3, 16);
/*  536 */           localCallableStatement.registerOutParameter(5, 4);
/*  537 */           localCallableStatement.registerOutParameter(6, -4);
/*  538 */           localCallableStatement.registerOutParameter(7, -4);
/*  539 */           localCallableStatement.setString(2, this.sqlName.getSchema());
/*  540 */           localCallableStatement.setString(3, this.sqlName.getSimpleName());
/*      */           
/*      */ 
/*      */ 
/*  544 */           localCallableStatement.execute();
/*      */           
/*      */ 
/*  547 */           int j = localCallableStatement.getInt(1);
/*  548 */           Object localObject1; if (j != 0)
/*      */           {
/*      */ 
/*  551 */             if (j != 24331)
/*      */             {
/*  553 */               localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74, this.sqlName.toString());
/*  554 */               ((SQLException)localObject1).fillInStackTrace();
/*  555 */               throw ((Throwable)localObject1);
/*      */             }
/*  557 */             if (j == 24331)
/*      */             {
/*  559 */               i = 1;
/*  560 */               localCallableStatement.registerOutParameter(6, 2004);
/*      */               
/*      */ 
/*  563 */               localCallableStatement.execute();
/*      */               
/*      */ 
/*  566 */               j = localCallableStatement.getInt(1);
/*  567 */               if (j != 0)
/*      */               {
/*      */ 
/*  570 */                 localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 74, this.sqlName.toString());
/*  571 */                 ((SQLException)localObject1).fillInStackTrace();
/*  572 */                 throw ((Throwable)localObject1);
/*      */               }
/*      */             }
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  579 */           this.toid = localCallableStatement.getBytes(4);
/*  580 */           this.typeVersion = NUMBER.toInt(localCallableStatement.getBytes(5));
/*  581 */           if (i == 0)
/*      */           {
/*  583 */             arrayOfByte = localCallableStatement.getBytes(6);
/*      */           }
/*      */           else
/*      */           {
/*      */             try
/*      */             {
/*      */ 
/*  590 */               Blob localBlob = ((OracleCallableStatement)localCallableStatement).getBlob(6);
/*  591 */               localObject1 = localBlob.getBinaryStream();
/*  592 */               arrayOfByte = new byte[(int)localBlob.length()];
/*  593 */               ((InputStream)localObject1).read(arrayOfByte);
/*  594 */               ((InputStream)localObject1).close();
/*  595 */               ((BLOB)localBlob).freeTemporary();
/*      */             }
/*      */             catch (IOException localIOException)
/*      */             {
/*  599 */               SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/*  600 */               localSQLException.fillInStackTrace();
/*  601 */               throw localSQLException;
/*      */             }
/*      */           }
/*      */           
/*  605 */           this.metaDataInitialized = true;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  611 */           this.flattenedAttrNum = (Util.getUnsignedByte(arrayOfByte[8]) * 256 + Util.getUnsignedByte(arrayOfByte[9]));
/*      */           
/*  613 */           localCallableStatement.getBytes(7);
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         finally
/*      */         {
/*      */ 
/*      */ 
/*  622 */           if (localCallableStatement != null) localCallableStatement.close();
/*      */         }
/*      */       }
/*  625 */       setStatusBits(256);
/*  626 */       return arrayOfByte;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   TDSReader parseTDS(byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  635 */     if (this.attrTypes != null) {
/*  636 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  641 */     TDSReader localTDSReader = new TDSReader(paramArrayOfByte, paramLong);
/*      */     
/*      */ 
/*  644 */     long l1 = localTDSReader.readLong() + localTDSReader.offset();
/*      */     
/*      */ 
/*      */ 
/*  648 */     localTDSReader.checkNextByte((byte)38);
/*      */     
/*      */ 
/*  651 */     this.tdsVersion = localTDSReader.readByte();
/*      */     
/*      */ 
/*  654 */     localTDSReader.skipBytes(2);
/*      */     
/*      */ 
/*  657 */     this.flattenedAttrNum = localTDSReader.readUB2();
/*      */     
/*      */ 
/*  660 */     if ((localTDSReader.readByte() & 0xFF) == 255) {
/*  661 */       setStatusBits(128);
/*      */     }
/*      */     
/*      */ 
/*  665 */     long l2 = localTDSReader.offset();
/*      */     
/*      */ 
/*  668 */     localTDSReader.checkNextByte((byte)41);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  673 */     if (localTDSReader.readUB2() != 0)
/*      */     {
/*  675 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 47, "parseTDS");
/*  676 */       localSQLException.fillInStackTrace();
/*  677 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  682 */     long l3 = localTDSReader.readLong();
/*      */     
/*      */ 
/*  685 */     parseTDSrec(localTDSReader);
/*      */     
/*      */ 
/*  688 */     if (this.tdsVersion >= 3)
/*      */     {
/*      */ 
/*      */ 
/*  692 */       localTDSReader.skip_to(l2 + l3 + 2L);
/*      */       
/*      */ 
/*  695 */       localTDSReader.skipBytes(2 * this.flattenedAttrNum);
/*      */       
/*      */ 
/*  698 */       byte b = localTDSReader.readByte();
/*      */       
/*      */ 
/*  701 */       if (localTDSReader.isJavaObject(this.tdsVersion, b)) {
/*  702 */         setStatusBits(16);
/*      */       }
/*      */       
/*      */ 
/*  706 */       if (localTDSReader.isFinalType(this.tdsVersion, b)) {
/*  707 */         setStatusBits(32);
/*      */       }
/*      */       
/*      */ 
/*  711 */       if (localTDSReader.readByte() != 1) {
/*  712 */         setStatusBits(64);
/*      */       }
/*      */     }
/*      */     else {
/*  716 */       setStatusBits(32);
/*      */     }
/*      */     
/*  719 */     localTDSReader.skip_to(l1);
/*  720 */     return localTDSReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void parseTDSrec(TDSReader paramTDSReader)
/*      */     throws SQLException
/*      */   {
/*  728 */     Vector localVector = new Vector(5);
/*  729 */     OracleType localOracleType = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  739 */     this.idx = 1;
/*      */     
/*  741 */     while ((localOracleType = getNextTypeObject(paramTDSReader)) != null)
/*      */     {
/*  743 */       localVector.addElement(localOracleType);
/*      */     }
/*      */     
/*      */ 
/*  747 */     if (this.opcode == 42)
/*      */     {
/*  749 */       this.endOfAdt = true;
/*      */       
/*  751 */       applyTDSpatches(paramTDSReader);
/*      */     }
/*      */     
/*  754 */     this.attrTypes = new OracleType[localVector.size()];
/*      */     
/*  756 */     localVector.copyInto(this.attrTypes);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void applyTDSpatches(TDSReader paramTDSReader)
/*      */     throws SQLException
/*      */   {
/*  766 */     TDSPatch localTDSPatch = paramTDSReader.getNextPatch();
/*      */     
/*  768 */     while (localTDSPatch != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  773 */       paramTDSReader.moveToPatchPos(localTDSPatch);
/*      */       
/*  775 */       int i = localTDSPatch.getType();
/*      */       
/*  777 */       if (i == 0)
/*      */       {
/*      */ 
/*      */ 
/*  781 */         paramTDSReader.readByte();
/*      */         
/*  783 */         int j = localTDSPatch.getUptTypeCode();
/*      */         Object localObject2;
/*  785 */         Object localObject3; switch (j)
/*      */         {
/*      */ 
/*      */ 
/*      */         case -6: 
/*  790 */           paramTDSReader.readLong();
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         case -5: 
/*  797 */           localObject2 = localTDSPatch.getOwner();
/*  798 */           localObject3 = null;
/*      */           
/*  800 */           if (((OracleNamedType)localObject2).hasName())
/*      */           {
/*  802 */             localObject3 = new OracleTypeADT(((OracleNamedType)localObject2).getFullName(), this.connection);
/*      */           }
/*      */           else
/*      */           {
/*  806 */             localObject3 = new OracleTypeADT(((OracleNamedType)localObject2).getParent(), ((OracleNamedType)localObject2).getOrder(), this.connection);
/*      */           }
/*      */           
/*      */ 
/*  810 */           ((OracleTypeADT)localObject3).setUptADT();
/*  811 */           TDSReader localTDSReader = ((OracleTypeADT)localObject3).parseTDS(paramTDSReader.tds(), paramTDSReader.absoluteOffset());
/*      */           
/*  813 */           paramTDSReader.skipBytes((int)localTDSReader.offset());
/*  814 */           localTDSPatch.apply(((OracleTypeADT)localObject3).cleanup());
/*      */           
/*      */ 
/*  817 */           break;
/*      */         
/*      */ 
/*      */         case 58: 
/*  821 */           localObject2 = localTDSPatch.getOwner();
/*  822 */           localObject3 = null;
/*      */           
/*  824 */           if (((OracleNamedType)localObject2).hasName())
/*      */           {
/*  826 */             localObject3 = new OracleTypeOPAQUE(((OracleNamedType)localObject2).getFullName(), this.connection);
/*      */           }
/*      */           else
/*      */           {
/*  830 */             localObject3 = new OracleTypeOPAQUE(((OracleNamedType)localObject2).getParent(), ((OracleNamedType)localObject2).getOrder(), this.connection);
/*      */           }
/*      */           
/*      */ 
/*  834 */           ((OracleTypeOPAQUE)localObject3).parseTDSrec(paramTDSReader);
/*  835 */           localTDSPatch.apply((OracleType)localObject3);
/*      */           
/*      */ 
/*  838 */           break;
/*      */         
/*      */ 
/*      */ 
/*      */         default: 
/*  843 */           localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  844 */           ((SQLException)localObject2).fillInStackTrace();
/*  845 */           throw ((Throwable)localObject2);
/*      */         }
/*      */         
/*      */       } else {
/*      */         Object localObject1;
/*  850 */         if (i == 1)
/*      */         {
/*      */ 
/*      */ 
/*  854 */           localObject1 = getNextTypeObject(paramTDSReader);
/*      */           
/*      */ 
/*      */ 
/*  858 */           localTDSPatch.apply((OracleType)localObject1, this.opcode);
/*      */         }
/*      */         else
/*      */         {
/*  862 */           localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 47, "parseTDS");
/*  863 */           ((SQLException)localObject1).fillInStackTrace();
/*  864 */           throw ((Throwable)localObject1);
/*      */         }
/*      */       }
/*  867 */       localTDSPatch = paramTDSReader.getNextPatch();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleNamedType cleanup()
/*      */   {
/*  878 */     synchronized (this.connection) {
/*      */       Object localObject1;
/*  880 */       if ((this.attrTypes.length == 1) && ((this.attrTypes[0] instanceof OracleTypeCOLLECTION)))
/*      */       {
/*      */ 
/*      */ 
/*  884 */         localObject1 = (OracleTypeCOLLECTION)this.attrTypes[0];
/*      */         
/*  886 */         ((OracleTypeCOLLECTION)localObject1).copy_properties(this);
/*      */         
/*  888 */         return (OracleNamedType)localObject1;
/*      */       }
/*  890 */       if ((this.attrTypes.length == 1) && ((this.statusBits & 0x80) != 0) && ((this.attrTypes[0] instanceof OracleTypeUPT)) && ((((OracleTypeUPT)this.attrTypes[0]).realType instanceof OracleTypeOPAQUE)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  895 */         localObject1 = (OracleTypeOPAQUE)((OracleTypeUPT)this.attrTypes[0]).realType;
/*      */         
/*      */ 
/*  898 */         ((OracleTypeOPAQUE)localObject1).copy_properties(this);
/*      */         
/*  900 */         return (OracleNamedType)localObject1;
/*      */       }
/*      */       
/*  903 */       return this;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void copy_properties(OracleTypeADT paramOracleTypeADT)
/*      */   {
/*  911 */     this.sqlName = paramOracleTypeADT.sqlName;
/*  912 */     this.parent = paramOracleTypeADT.parent;
/*  913 */     this.idx = paramOracleTypeADT.idx;
/*  914 */     this.connection = paramOracleTypeADT.connection;
/*  915 */     this.toid = paramOracleTypeADT.toid;
/*  916 */     this.tdsVersion = paramOracleTypeADT.tdsVersion;
/*  917 */     this.typeVersion = paramOracleTypeADT.typeVersion;
/*  918 */     this.tdoCState = paramOracleTypeADT.tdoCState;
/*  919 */     this.endOfAdt = paramOracleTypeADT.endOfAdt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleType getNextTypeObject(TDSReader paramTDSReader)
/*      */     throws SQLException
/*      */   {
/*      */     for (;;)
/*      */     {
/*  934 */       this.opcode = paramTDSReader.readByte();
/*      */       
/*  936 */       if (this.opcode != 43)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  942 */         if (this.opcode != 44) {
/*      */           break;
/*      */         }
/*      */         
/*  946 */         byte b = paramTDSReader.readByte();
/*      */         
/*  948 */         if (paramTDSReader.isJavaObject(3, b)) {
/*  949 */           setStatusBits(16);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  961 */     switch (this.opcode)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */     case 40: 
/*      */     case 42: 
/*  968 */       return null;
/*      */     
/*      */ 
/*      */ 
/*      */     case 2: 
/*  973 */       localObject = new OracleTypeDATE();
/*      */       
/*  975 */       ((OracleTypeDATE)localObject).parseTDSrec(paramTDSReader);
/*      */       
/*  977 */       this.idx += 1;
/*      */       
/*  979 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 7: 
/*  984 */       localObject = new OracleTypeCHAR(this.connection, 12);
/*      */       
/*      */ 
/*  987 */       ((OracleTypeCHAR)localObject).parseTDSrec(paramTDSReader);
/*      */       
/*  989 */       this.idx += 1;
/*      */       
/*  991 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/*  996 */       localObject = new OracleTypeCHAR(this.connection, 1);
/*      */       
/*  998 */       ((OracleTypeCHAR)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1000 */       this.idx += 1;
/*      */       
/* 1002 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 39: 
/* 1007 */       localObject = new OracleTypeADT(this, this.idx, this.connection);
/*      */       
/* 1009 */       ((OracleTypeADT)localObject).setEmbeddedADT();
/* 1010 */       ((OracleTypeADT)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1012 */       this.idx += 1;
/*      */       
/* 1014 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 6: 
/* 1019 */       localObject = new OracleTypeNUMBER(2);
/*      */       
/* 1021 */       ((OracleTypeNUMBER)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1023 */       this.idx += 1;
/*      */       
/* 1025 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 3: 
/* 1030 */       localObject = new OracleTypeNUMBER(3);
/*      */       
/* 1032 */       ((OracleTypeNUMBER)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1034 */       this.idx += 1;
/*      */       
/* 1036 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 4: 
/* 1041 */       localObject = new OracleTypeNUMBER(8);
/*      */       
/* 1043 */       ((OracleTypeNUMBER)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1045 */       this.idx += 1;
/*      */       
/* 1047 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 5: 
/* 1052 */       localObject = new OracleTypeFLOAT();
/*      */       
/* 1054 */       ((OracleTypeFLOAT)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1056 */       this.idx += 1;
/*      */       
/* 1058 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 37: 
/* 1063 */       localObject = new OracleTypeBINARY_FLOAT();
/*      */       
/* 1065 */       ((OracleTypeBINARY_FLOAT)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1067 */       this.idx += 1;
/*      */       
/* 1069 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 45: 
/* 1074 */       localObject = new OracleTypeBINARY_DOUBLE();
/*      */       
/* 1076 */       ((OracleTypeBINARY_DOUBLE)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1078 */       this.idx += 1;
/*      */       
/* 1080 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 8: 
/* 1086 */       localObject = new OracleTypeSINT32();
/*      */       
/* 1088 */       ((OracleTypeSINT32)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1090 */       this.idx += 1;
/*      */       
/* 1092 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 9: 
/* 1098 */       localObject = new OracleTypeREF(this, this.idx, this.connection);
/*      */       
/* 1100 */       ((OracleTypeREF)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1102 */       this.idx += 1;
/*      */       
/* 1104 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 31: 
/* 1109 */       localObject = new OracleTypeBFILE(this.connection);
/*      */       
/* 1111 */       ((OracleTypeBFILE)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1113 */       this.idx += 1;
/*      */       
/* 1115 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 19: 
/* 1120 */       localObject = new OracleTypeRAW();
/*      */       
/* 1122 */       ((OracleTypeRAW)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1124 */       this.idx += 1;
/*      */       
/* 1126 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 29: 
/* 1131 */       localObject = new OracleTypeCLOB(this.connection);
/*      */       
/* 1133 */       ((OracleTypeCLOB)localObject).parseTDSrec(paramTDSReader);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1148 */       if ((this.sqlName != null) && (!this.endOfAdt)) {
/* 1149 */         this.connection.getForm(this, (OracleTypeCLOB)localObject, this.idx);
/*      */       }
/* 1151 */       this.idx += 1;
/*      */       
/* 1153 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 30: 
/* 1158 */       localObject = new OracleTypeBLOB(this.connection);
/*      */       
/* 1160 */       ((OracleTypeBLOB)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1162 */       this.idx += 1;
/*      */       
/* 1164 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 21: 
/* 1169 */       localObject = new OracleTypeTIMESTAMP(this.connection);
/*      */       
/* 1171 */       ((OracleTypeTIMESTAMP)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1173 */       this.idx += 1;
/*      */       
/* 1175 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 23: 
/* 1180 */       localObject = new OracleTypeTIMESTAMPTZ(this.connection);
/*      */       
/* 1182 */       ((OracleTypeTIMESTAMPTZ)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1184 */       this.idx += 1;
/*      */       
/* 1186 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 33: 
/* 1191 */       localObject = new OracleTypeTIMESTAMPLTZ(this.connection);
/*      */       
/* 1193 */       ((OracleTypeTIMESTAMPLTZ)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1195 */       this.idx += 1;
/*      */       
/* 1197 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 24: 
/* 1202 */       localObject = new OracleTypeINTERVAL(this.connection);
/*      */       
/* 1204 */       ((OracleTypeINTERVAL)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1206 */       this.idx += 1;
/*      */       
/* 1208 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 28: 
/* 1213 */       localObject = new OracleTypeCOLLECTION(this, this.idx, this.connection);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1218 */       ((OracleTypeCOLLECTION)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1220 */       this.idx += 1;
/*      */       
/* 1222 */       return (OracleType)localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 27: 
/* 1227 */       localObject = new OracleTypeUPT(this, this.idx, this.connection);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1232 */       ((OracleTypeUPT)localObject).parseTDSrec(paramTDSReader);
/*      */       
/* 1234 */       this.idx += 1;
/*      */       
/* 1236 */       return (OracleType)localObject;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1254 */     Object localObject = null;
/*      */     
/*      */ 
/* 1257 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 48, "get_next_type: " + this.opcode);
/* 1258 */     localSQLException.fillInStackTrace();
/* 1259 */     throw localSQLException;
/*      */   }
/*      */   
/*      */   /* Error */
/*      */   public byte[] linearize(Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: getfield 42	oracle/jdbc/oracore/OracleTypeADT:connection	Loracle/jdbc/internal/OracleConnection;
/*      */     //   4: dup
/*      */     //   5: astore_2
/*      */     //   6: monitorenter
/*      */     //   7: aload_0
/*      */     //   8: aload_1
/*      */     //   9: invokevirtual 217	oracle/jdbc/oracore/OracleTypeADT:pickle81	(Loracle/sql/Datum;)[B
/*      */     //   12: aload_2
/*      */     //   13: monitorexit
/*      */     //   14: areturn
/*      */     //   15: astore_3
/*      */     //   16: aload_2
/*      */     //   17: monitorexit
/*      */     //   18: aload_3
/*      */     //   19: athrow
/*      */     // Line number table:
/*      */     //   Java source line #1278	-> byte code offset #0
/*      */     //   Java source line #1280	-> byte code offset #7
/*      */     //   Java source line #1282	-> byte code offset #15
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	signature
/*      */     //   0	20	0	this	OracleTypeADT
/*      */     //   0	20	1	paramDatum	Datum
/*      */     //   5	12	2	Ljava/lang/Object;	Object
/*      */     //   15	4	3	localObject1	Object
/*      */     // Exception table:
/*      */     //   from	to	target	type
/*      */     //   7	14	15	finally
/*      */     //   15	18	15	finally
/*      */   }
/*      */   
/*      */   public Datum unlinearize(byte[] paramArrayOfByte, long paramLong, Datum paramDatum, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1298 */     OracleConnection localOracleConnection = getConnection();
/* 1299 */     Datum localDatum = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1304 */     if (localOracleConnection == null)
/*      */     {
/* 1306 */       localDatum = _unlinearize(paramArrayOfByte, paramLong, paramDatum, paramInt, paramMap);
/*      */     }
/*      */     else
/*      */     {
/* 1310 */       synchronized (localOracleConnection) {
/* 1311 */         localDatum = _unlinearize(paramArrayOfByte, paramLong, paramDatum, paramInt, paramMap);
/*      */       }
/*      */     }
/*      */     
/* 1315 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum _unlinearize(byte[] paramArrayOfByte, long paramLong, Datum paramDatum, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1326 */     synchronized (this.connection)
/*      */     {
/* 1328 */       if (paramArrayOfByte == null) {
/* 1329 */         return null;
/*      */       }
/*      */       
/* 1332 */       PickleContext localPickleContext = new PickleContext(paramArrayOfByte, paramLong);
/*      */       
/* 1334 */       return unpickle81(localPickleContext, (STRUCT)paramDatum, 1, paramInt, paramMap);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected STRUCT unpickle81(PickleContext paramPickleContext, STRUCT paramSTRUCT, int paramInt1, int paramInt2, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1356 */     STRUCT localSTRUCT = paramSTRUCT;
/* 1357 */     long l1 = paramPickleContext.offset();
/*      */     
/*      */ 
/* 1360 */     byte b = paramPickleContext.readByte();
/*      */     SQLException localSQLException;
/* 1362 */     if (!PickleContext.is81format(b))
/*      */     {
/* 1364 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image is not in 8.1 format");
/* 1365 */       localSQLException.fillInStackTrace();
/* 1366 */       throw localSQLException;
/*      */     }
/*      */     
/* 1369 */     if (PickleContext.isCollectionImage_pctx(b))
/*      */     {
/* 1371 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image is a collection image,expecting ADT");
/* 1372 */       localSQLException.fillInStackTrace();
/* 1373 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1378 */     if (!paramPickleContext.readAndCheckVersion())
/*      */     {
/* 1380 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image version is not recognized");
/* 1381 */       localSQLException.fillInStackTrace();
/* 1382 */       throw localSQLException;
/*      */     }
/*      */     
/* 1385 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */ 
/*      */     case 9: 
/* 1390 */       paramPickleContext.skipBytes(paramPickleContext.readLength(true) - 2);
/*      */       
/*      */ 
/* 1393 */       break;
/*      */     
/*      */ 
/*      */     case 3: 
/* 1397 */       long l2 = paramPickleContext.readLength();
/*      */       
/* 1399 */       localSTRUCT = unpickle81Prefix(paramPickleContext, localSTRUCT, b);
/*      */       
/* 1401 */       if (localSTRUCT == null)
/*      */       {
/* 1403 */         StructDescriptor localStructDescriptor = createStructDescriptor();
/*      */         
/* 1405 */         localSTRUCT = createByteSTRUCT(localStructDescriptor, (byte[])null);
/*      */       }
/*      */       
/* 1408 */       localSTRUCT.setImage(paramPickleContext.image(), l1, 0L);
/* 1409 */       localSTRUCT.setImageLength(l2);
/* 1410 */       paramPickleContext.skipTo(l1 + l2);
/*      */       
/*      */ 
/* 1413 */       break;
/*      */     
/*      */ 
/*      */     default: 
/* 1417 */       paramPickleContext.skipLength();
/*      */       
/*      */ 
/* 1420 */       localSTRUCT = unpickle81Prefix(paramPickleContext, localSTRUCT, b);
/*      */       
/* 1422 */       if (localSTRUCT == null)
/*      */       {
/* 1424 */         localObject1 = createStructDescriptor();
/*      */         
/* 1426 */         localSTRUCT = createByteSTRUCT((StructDescriptor)localObject1, (byte[])null);
/*      */       }
/*      */       
/*      */ 
/* 1430 */       Object localObject1 = localSTRUCT.getDescriptor().getOracleTypeADT().getAttrTypes();
/*      */       Object localObject2;
/*      */       int i;
/* 1433 */       switch (paramInt2)
/*      */       {
/*      */ 
/*      */ 
/*      */       case 1: 
/* 1438 */         localObject2 = new Datum[localObject1.length];
/*      */         
/* 1440 */         for (i = 0; i < localObject1.length; i++)
/*      */         {
/* 1442 */           localObject2[i] = ((Datum)localObject1[i].unpickle81rec(paramPickleContext, paramInt2, paramMap));
/*      */         }
/*      */         
/*      */ 
/* 1446 */         localSTRUCT.setDatumArray((Datum[])localObject2);
/*      */         
/*      */ 
/* 1449 */         break;
/*      */       
/*      */ 
/*      */       case 2: 
/* 1453 */         localObject2 = new Object[localObject1.length];
/*      */         
/* 1455 */         for (i = 0; i < localObject1.length; i++)
/*      */         {
/* 1457 */           localObject2[i] = localObject1[i].unpickle81rec(paramPickleContext, paramInt2, paramMap);
/*      */         }
/*      */         
/* 1460 */         localSTRUCT.setObjArray((Object[])localObject2);
/*      */         
/*      */ 
/* 1463 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */       default: 
/* 1468 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 1469 */         ((SQLException)localObject2).fillInStackTrace();
/* 1470 */         throw ((Throwable)localObject2);
/*      */       }
/*      */       
/*      */       
/*      */       break;
/*      */     }
/*      */     
/* 1477 */     return localSTRUCT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected STRUCT unpickle81Prefix(PickleContext paramPickleContext, STRUCT paramSTRUCT, byte paramByte)
/*      */     throws SQLException
/*      */   {
/* 1485 */     STRUCT localSTRUCT = paramSTRUCT;
/*      */     
/* 1487 */     if (PickleContext.hasPrefix(paramByte))
/*      */     {
/* 1489 */       long l = paramPickleContext.readLength() + paramPickleContext.absoluteOffset();
/*      */       
/* 1491 */       int i = paramPickleContext.readByte();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1498 */       int j = (byte)(i & 0xC);
/* 1499 */       int k = j == 0 ? 1 : 0;
/*      */       
/* 1501 */       int m = j == 4 ? 1 : 0;
/*      */       
/* 1503 */       int n = j == 8 ? 1 : 0;
/*      */       
/* 1505 */       int i1 = j == 12 ? 1 : 0;
/*      */       
/*      */ 
/* 1508 */       int i2 = (i & 0x10) != 0 ? 1 : 0;
/*      */       
/*      */       Object localObject;
/* 1511 */       if (m != 0)
/*      */       {
/* 1513 */         localObject = paramPickleContext.readBytes(16);
/* 1514 */         String str = toid2typename(this.connection, (byte[])localObject);
/*      */         
/* 1516 */         StructDescriptor localStructDescriptor = (StructDescriptor)TypeDescriptor.getTypeDescriptor(str, this.connection);
/*      */         
/*      */ 
/*      */ 
/* 1520 */         if (localSTRUCT == null) {
/* 1521 */           localSTRUCT = createByteSTRUCT(localStructDescriptor, (byte[])null);
/*      */         } else {
/* 1523 */           localSTRUCT.setDescriptor(localStructDescriptor);
/*      */         }
/*      */       }
/* 1526 */       if (i2 != 0)
/*      */       {
/* 1528 */         paramPickleContext.readLength();
/*      */       }
/*      */       
/* 1531 */       if ((n | i1) != 0)
/*      */       {
/* 1533 */         localObject = DatabaseError.createUnsupportedFeatureSqlException();
/* 1534 */         ((SQLException)localObject).fillInStackTrace();
/* 1535 */         throw ((Throwable)localObject);
/*      */       }
/*      */       
/* 1538 */       paramPickleContext.skipTo(l);
/*      */     }
/*      */     
/* 1541 */     return localSTRUCT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object unpickle81rec(PickleContext paramPickleContext, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1554 */     byte b1 = paramPickleContext.readByte();
/* 1555 */     byte b2 = 0;
/*      */     
/* 1557 */     if (PickleContext.isAtomicNull(b1))
/* 1558 */       return null;
/* 1559 */     if (PickleContext.isImmediatelyEmbeddedNull(b1)) {
/* 1560 */       b2 = paramPickleContext.readByte();
/*      */     }
/* 1562 */     STRUCT localSTRUCT = unpickle81datum(paramPickleContext, b1, b2);
/*      */     
/* 1564 */     return toObject(localSTRUCT, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object unpickle81rec(PickleContext paramPickleContext, byte paramByte, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1573 */     STRUCT localSTRUCT = unpickle81datum(paramPickleContext, paramByte, (byte)0);
/*      */     
/* 1575 */     return toObject(localSTRUCT, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private STRUCT unpickle81datum(PickleContext paramPickleContext, byte paramByte1, byte paramByte2)
/*      */     throws SQLException
/*      */   {
/* 1583 */     int i = getNumAttrs();
/*      */     
/*      */ 
/* 1586 */     StructDescriptor localStructDescriptor = createStructDescriptor();
/* 1587 */     STRUCT localSTRUCT = createByteSTRUCT(localStructDescriptor, (byte[])null);
/* 1588 */     OracleType localOracleType = getAttrTypeAt(0);
/* 1589 */     Object localObject = null;
/*      */     
/*      */ 
/*      */ 
/* 1593 */     if ((PickleContext.isImmediatelyEmbeddedNull(paramByte1)) && (paramByte2 == 1)) {
/* 1594 */       localObject = null;
/* 1595 */     } else if (PickleContext.isImmediatelyEmbeddedNull(paramByte1)) {
/* 1596 */       localObject = ((OracleTypeADT)localOracleType).unpickle81datum(paramPickleContext, paramByte1, (byte)(paramByte2 - 1));
/*      */     }
/* 1598 */     else if (PickleContext.isElementNull(paramByte1))
/*      */     {
/* 1600 */       if ((localOracleType.getTypeCode() == 2002) || (localOracleType.getTypeCode() == 2008))
/*      */       {
/* 1602 */         localObject = localOracleType.unpickle81datumAsNull(paramPickleContext, paramByte1, paramByte2);
/*      */       } else {
/* 1604 */         localObject = null;
/*      */       }
/*      */     } else {
/* 1607 */       localObject = localOracleType.unpickle81rec(paramPickleContext, paramByte1, 1, null);
/*      */     }
/*      */     
/* 1610 */     Datum[] arrayOfDatum = new Datum[i];
/*      */     
/* 1612 */     arrayOfDatum[0] = ((Datum)localObject);
/*      */     
/* 1614 */     for (int j = 1; j < i; j++)
/*      */     {
/* 1616 */       localOracleType = getAttrTypeAt(j);
/* 1617 */       arrayOfDatum[j] = ((Datum)localOracleType.unpickle81rec(paramPickleContext, 1, null));
/*      */     }
/*      */     
/* 1620 */     localSTRUCT.setDatumArray(arrayOfDatum);
/*      */     
/* 1622 */     return localSTRUCT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected Datum unpickle81datumAsNull(PickleContext paramPickleContext, byte paramByte1, byte paramByte2)
/*      */     throws SQLException
/*      */   {
/* 1630 */     int i = getNumAttrs();
/*      */     
/*      */ 
/* 1633 */     StructDescriptor localStructDescriptor = createStructDescriptor();
/* 1634 */     STRUCT localSTRUCT = createByteSTRUCT(localStructDescriptor, (byte[])null);
/* 1635 */     Datum[] arrayOfDatum = new Datum[i];
/* 1636 */     int j = 0;
/* 1637 */     OracleType localOracleType = getAttrTypeAt(j);
/*      */     
/*      */ 
/* 1640 */     if ((localOracleType.getTypeCode() == 2002) || (localOracleType.getTypeCode() == 2008))
/*      */     {
/* 1642 */       arrayOfDatum[(j++)] = localOracleType.unpickle81datumAsNull(paramPickleContext, paramByte1, paramByte2);
/*      */     } else {
/* 1644 */       arrayOfDatum[(j++)] = ((Datum)null);
/*      */     }
/* 1646 */     for (; j < i; j++)
/*      */     {
/* 1648 */       localOracleType = getAttrTypeAt(j);
/* 1649 */       if ((localOracleType.getTypeCode() == 2002) || (localOracleType.getTypeCode() == 2008))
/*      */       {
/* 1651 */         arrayOfDatum[j] = ((Datum)localOracleType.unpickle81rec(paramPickleContext, 1, null));
/*      */       }
/*      */       else
/* 1654 */         arrayOfDatum[j] = ((Datum)localOracleType.unpickle81rec(paramPickleContext, 1, null));
/*      */     }
/* 1656 */     localSTRUCT.setDatumArray(arrayOfDatum);
/* 1657 */     return localSTRUCT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] pickle81(Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1674 */     PickleContext localPickleContext = new PickleContext();
/*      */     
/* 1676 */     localPickleContext.initStream();
/* 1677 */     pickle81(localPickleContext, paramDatum);
/*      */     
/* 1679 */     byte[] arrayOfByte = localPickleContext.stream2Bytes();
/*      */     
/*      */ 
/* 1682 */     paramDatum.setShareBytes(arrayOfByte);
/*      */     
/* 1684 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */   protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/* 1691 */     int i = paramPickleContext.offset() + 2;
/* 1692 */     int j = 0;
/*      */     
/*      */ 
/* 1695 */     j += paramPickleContext.writeImageHeader(shouldHavePrefix());
/*      */     
/* 1697 */     j += pickle81Prefix(paramPickleContext);
/* 1698 */     j += pickle81rec(paramPickleContext, paramDatum, 0);
/*      */     
/* 1700 */     paramPickleContext.patchImageLen(i, j);
/*      */     
/* 1702 */     return j;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean hasVersion()
/*      */   {
/* 1709 */     return this.typeVersion > 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean needsToid()
/*      */   {
/* 1716 */     if (this.isTransient)
/* 1717 */       return false;
/* 1718 */     return ((this.statusBits & 0x40) != 0) || ((this.statusBits & 0x20) == 0) || (hasVersion());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean shouldHavePrefix()
/*      */   {
/* 1726 */     if (this.isTransient) return false;
/* 1727 */     return (hasVersion()) || (needsToid());
/*      */   }
/*      */   
/*      */ 
/*      */   protected int pickle81Prefix(PickleContext paramPickleContext)
/*      */     throws SQLException
/*      */   {
/* 1734 */     if (shouldHavePrefix())
/*      */     {
/* 1736 */       int i = 0;
/* 1737 */       int j = 1;
/* 1738 */       int k = 1;
/*      */       
/* 1740 */       if (needsToid())
/*      */       {
/* 1742 */         k += getTOID().length;
/* 1743 */         j |= 0x4;
/*      */       }
/*      */       
/* 1746 */       if (hasVersion())
/*      */       {
/* 1748 */         j |= 0x10;
/*      */         
/* 1750 */         if (this.typeVersion > PickleContext.KOPI20_LN_MAXV) {
/* 1751 */           k += 5;
/*      */         } else {
/* 1753 */           k += 2;
/*      */         }
/*      */       }
/* 1756 */       i = paramPickleContext.writeLength(k);
/*      */       
/* 1758 */       i += paramPickleContext.writeData((byte)j);
/*      */       
/* 1760 */       if (needsToid()) {
/* 1761 */         i += paramPickleContext.writeData(this.toid);
/*      */       }
/* 1763 */       if (hasVersion())
/*      */       {
/* 1765 */         if (this.typeVersion > PickleContext.KOPI20_LN_MAXV) {
/* 1766 */           i += paramPickleContext.writeLength(this.typeVersion);
/*      */         } else {
/* 1768 */           i += paramPickleContext.writeSB2(this.typeVersion);
/*      */         }
/*      */       }
/* 1771 */       return i;
/*      */     }
/*      */     
/* 1774 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int pickle81rec(PickleContext paramPickleContext, Datum paramDatum, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1784 */     int i = 0;
/*      */     
/* 1786 */     if (!this.metaDataInitialized) {
/* 1787 */       copy_properties((OracleTypeADT)((STRUCT)paramDatum).getDescriptor().getPickler());
/*      */     }
/* 1789 */     Datum[] arrayOfDatum = ((STRUCT)paramDatum).getOracleAttributes();
/* 1790 */     int j = arrayOfDatum.length;
/* 1791 */     int k = 0;
/* 1792 */     OracleType localOracleType = getAttrTypeAt(0);
/*      */     
/* 1794 */     if (((localOracleType instanceof OracleTypeADT)) && (!(localOracleType instanceof OracleTypeCOLLECTION)) && (!(localOracleType instanceof OracleTypeUPT)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1801 */       k = 1;
/*      */       
/* 1803 */       if (arrayOfDatum[0] == null)
/*      */       {
/* 1805 */         if (paramInt > 0) {
/* 1806 */           i += paramPickleContext.writeImmediatelyEmbeddedElementNull((byte)paramInt);
/*      */         } else {
/* 1808 */           i += paramPickleContext.writeAtomicNull();
/*      */         }
/*      */       }
/*      */       else {
/* 1812 */         i += ((OracleTypeADT)localOracleType).pickle81rec(paramPickleContext, arrayOfDatum[0], paramInt + 1);
/*      */       }
/*      */     }
/* 1817 */     for (; 
/*      */         
/* 1817 */         k < j; k++)
/*      */     {
/* 1819 */       localOracleType = getAttrTypeAt(k);
/*      */       
/*      */ 
/* 1822 */       if (arrayOfDatum[k] == null)
/*      */       {
/* 1824 */         if (((localOracleType instanceof OracleTypeADT)) && (!(localOracleType instanceof OracleTypeCOLLECTION)) && (!(localOracleType instanceof OracleTypeUPT)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1830 */           i += paramPickleContext.writeAtomicNull();
/*      */         }
/*      */         else
/*      */         {
/* 1834 */           i += paramPickleContext.writeElementNull();
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 1839 */       else if (((localOracleType instanceof OracleTypeADT)) && (!(localOracleType instanceof OracleTypeCOLLECTION)) && (!(localOracleType instanceof OracleTypeUPT)))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1845 */         i += ((OracleTypeADT)localOracleType).pickle81rec(paramPickleContext, arrayOfDatum[k], 1);
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1852 */         i += localOracleType.pickle81(paramPickleContext, arrayOfDatum[k]);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1857 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Object toObject(STRUCT paramSTRUCT, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1869 */     switch (paramInt)
/*      */     {
/*      */     case 1: 
/* 1872 */       return paramSTRUCT;
/*      */     
/*      */     case 2: 
/* 1875 */       if (paramSTRUCT != null) { return paramSTRUCT.toJdbc(paramMap);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     default: 
/* 1882 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 1883 */       localSQLException.fillInStackTrace();
/* 1884 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1889 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getAttributeType(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1906 */     return getAttributeType(paramInt, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getAttributeType(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1918 */     if (paramBoolean) {
/* 1919 */       if (this.sqlName == null) { getFullName();
/*      */       }
/* 1921 */       if (this.attrNames == null)
/*      */       {
/* 1923 */         initADTAttrNames();
/*      */       }
/*      */     }
/* 1926 */     if ((paramInt < 1) || ((this.attrTypeNames != null) && (paramInt > this.attrTypeNames.length)))
/*      */     {
/* 1928 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid index");
/* 1929 */       localSQLException.fillInStackTrace();
/* 1930 */       throw localSQLException;
/*      */     }
/*      */     
/* 1933 */     if (this.attrTypeNames != null) {
/* 1934 */       return this.attrTypeNames[(paramInt - 1)];
/*      */     }
/*      */     
/* 1937 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getAttributeName(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1951 */     if (this.attrNames == null) { initADTAttrNames();
/*      */     }
/* 1953 */     String str = null;
/* 1954 */     if (this.attrNames != null) {
/* 1955 */       synchronized (this.connection) {
/* 1956 */         if ((paramInt < 1) || (paramInt > this.attrNames.length))
/*      */         {
/* 1958 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid index");
/* 1959 */           localSQLException.fillInStackTrace();
/* 1960 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */       
/* 1964 */       str = this.attrNames[(paramInt - 1)];
/*      */     }
/* 1966 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getAttributeName(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1978 */     if ((paramBoolean) && (this.connection != null)) {
/* 1979 */       return getAttributeName(paramInt);
/*      */     }
/*      */     
/* 1982 */     if ((paramInt < 1) || ((this.attrNames != null) && (paramInt > this.attrNames.length)))
/*      */     {
/* 1984 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Invalid index");
/* 1985 */       localSQLException.fillInStackTrace();
/* 1986 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1990 */     if (this.attrNames != null) {
/* 1991 */       return this.attrNames[(paramInt - 1)];
/*      */     }
/*      */     
/* 1994 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2015 */   static final String[] sqlString = { "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS WHERE TYPE_NAME = :1 ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS WHERE TYPE_NAME in (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :1 CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :1 FROM DUAL) ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS WHERE TYPE_NAME in (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :1 CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :1 FROM DUAL) ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM ALL_SYNONYMS START WITH SYNONYM_NAME = :1 AND  OWNER = 'PUBLIC' CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER UNION SELECT :2  FROM DUAL) ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM ALL_SYNONYMS START WITH SYNONYM_NAME = :1 AND  OWNER = 'PUBLIC' CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER UNION SELECT :2  FROM DUAL) ORDER BY ATTR_NO", "DECLARE CURSOR usyn_cur IS SELECT table_name, table_owner from user_synonyms; TYPE table_name_type IS TABLE OF usyn_cur%ROWTYPE; table_names table_name_type; lastrow BINARY_INTEGER := null; l_syntname user_synonyms.table_name%TYPE; l_syntown  user_synonyms.table_owner%TYPE; BEGIN SELECT TABLE_NAME, TABLE_OWNER BULK COLLECT INTO table_names FROM USER_SYNONYMS START WITH SYNONYM_NAME = ? CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME; IF table_names.LAST IS NOT NULL THEN   lastrow := table_names.LAST;   l_syntname := table_names(lastrow).table_name;   l_syntown :=  table_names(lastrow).table_owner; END IF; OPEN ? FOR SELECT  ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER   FROM ALL_TYPE_ATTRS  A   WHERE (TYPE_NAME = l_syntname OR TYPE_NAME = ?)  AND  A.OWNER = l_syntown   ORDER BY ATTR_NO; END;", "DECLARE CURSOR usyn_cur IS SELECT table_name, table_owner from user_synonyms; TYPE table_name_type IS TABLE OF usyn_cur%ROWTYPE; table_names table_name_type; lastrow BINARY_INTEGER := null; l_syntname user_synonyms.table_name%TYPE; l_syntown  user_synonyms.table_owner%TYPE; BEGIN SELECT TABLE_NAME, TABLE_OWNER BULK COLLECT INTO table_names FROM USER_SYNONYMS START WITH SYNONYM_NAME = ? CONNECT BY NOCYCLEPRIOR TABLE_NAME = SYNONYM_NAME; IF table_names.LAST IS NOT NULL THEN   lastrow := table_names.LAST;   l_syntname := table_names(lastrow).table_name;   l_syntown :=  table_names(lastrow).table_owner; END IF; OPEN ? FOR SELECT  ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER   FROM ALL_TYPE_ATTRS  A   WHERE (TYPE_NAME = l_syntname OR TYPE_NAME = ?)  AND  A.OWNER = l_syntown   ORDER BY ATTR_NO; END;", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM ALL_TYPE_ATTRS WHERE OWNER = :1 AND TYPE_NAME = :2 ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM ALL_TYPE_ATTRS WHERE OWNER = (SELECT TABLE_OWNER FROM ALL_SYNONYMS WHERE SYNONYM_NAME=:1) AND TYPE_NAME = (SELECT TABLE_NAME FROM ALL_SYNONYMS WHERE SYNONYM_NAME=:2) ORDER BY ATTR_NO", "DECLARE   the_owner VARCHAR2(100);   the_type  VARCHAR2(100); begin  SELECT TABLE_NAME, TABLE_OWNER INTO THE_TYPE, THE_OWNER  FROM ALL_SYNONYMS  WHERE TABLE_NAME IN (SELECT TYPE_NAME FROM ALL_TYPES)  START WITH SYNONYM_NAME = :1 AND OWNER = 'PUBLIC'  CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER; OPEN :2 FOR SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME,  ATTR_TYPE_OWNER FROM ALL_TYPE_ATTRS  WHERE TYPE_NAME = THE_TYPE and OWNER = THE_OWNER; END;", "DECLARE   the_owner VARCHAR2(100);   the_type  VARCHAR2(100); begin  SELECT TABLE_NAME, TABLE_OWNER INTO THE_TYPE, THE_OWNER  FROM ALL_SYNONYMS  WHERE TABLE_NAME IN (SELECT TYPE_NAME FROM ALL_TYPES)  START WITH SYNONYM_NAME = :1 AND OWNER = 'PUBLIC'  CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER; OPEN :2 FOR SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME,  ATTR_TYPE_OWNER FROM ALL_TYPE_ATTRS  WHERE TYPE_NAME = THE_TYPE and OWNER = THE_OWNER; END;" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int SEARCH_USER_TYPES = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static final int SEARCH_ALL_TYPES = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initADTAttrNames()
/*      */     throws SQLException
/*      */   {
/* 2176 */     if (this.connection == null) return;
/* 2177 */     if (this.sqlName == null) { getFullName();
/*      */     }
/* 2179 */     if (this.toid != null) {
/* 2180 */       initADTAttrNamesUsingTOID();
/* 2181 */       return;
/*      */     }
/*      */     
/* 2184 */     synchronized (this.connection) {
/* 2185 */       Object localObject1 = null;
/* 2186 */       PreparedStatement localPreparedStatement = null;
/* 2187 */       ResultSet localResultSet = null;
/* 2188 */       String[] arrayOfString1 = new String[this.attrTypes.length];
/* 2189 */       String[] arrayOfString2 = new String[this.attrTypes.length];
/* 2190 */       int i = 0;
/* 2191 */       int j = 0;
/* 2192 */       if (this.attrNames == null)
/*      */       {
/* 2194 */         i = this.sqlName.getSchema().equals(this.connection.getDefaultSchemaNameForNamedTypes()) ? 0 : 7;
/*      */         
/*      */ 
/*      */ 
/* 2198 */         while (i != 11) {
/* 2199 */           switch (i)
/*      */           {
/*      */           case 0: 
/* 2202 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/* 2203 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 2204 */             localPreparedStatement.setFetchSize(this.idx);
/* 2205 */             localResultSet = localPreparedStatement.executeQuery();
/* 2206 */             i = 1;
/* 2207 */             break;
/*      */           
/*      */           case 1: 
/* 2210 */             if (this.connection.getVersionNumber() >= 10000) {
/* 2211 */               i = 2;
/*      */             }
/*      */           
/*      */ 
/*      */           case 2: 
/* 2216 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/* 2217 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 2218 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/* 2219 */             localPreparedStatement.setFetchSize(this.idx);
/* 2220 */             localResultSet = localPreparedStatement.executeQuery();
/* 2221 */             i = 3;
/* 2222 */             break;
/*      */           
/*      */           case 3: 
/* 2225 */             if (this.connection.getVersionNumber() >= 10000) {
/* 2226 */               i = 4;
/*      */             }
/*      */           
/*      */ 
/*      */           case 4: 
/* 2231 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/* 2232 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 2233 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/* 2234 */             localPreparedStatement.setFetchSize(this.idx);
/* 2235 */             localResultSet = localPreparedStatement.executeQuery();
/* 2236 */             i = 5;
/* 2237 */             break;
/*      */           
/*      */           case 5: 
/* 2240 */             if (this.connection.getVersionNumber() >= 10000) {
/* 2241 */               i = 6;
/*      */             }
/*      */           
/*      */ 
/*      */           case 6: 
/* 2246 */             localObject1 = (OracleCallableStatement)this.connection.prepareCall(getSqlHint() + sqlString[i]);
/*      */             
/*      */ 
/* 2249 */             ((CallableStatement)localObject1).setString(1, this.sqlName.getSimpleName());
/* 2250 */             ((CallableStatement)localObject1).setString(3, this.sqlName.getSimpleName());
/* 2251 */             ((CallableStatement)localObject1).registerOutParameter(2, -10);
/* 2252 */             ((CallableStatement)localObject1).execute();
/* 2253 */             localResultSet = ((OracleCallableStatement)localObject1).getCursor(2);
/* 2254 */             localResultSet.setFetchSize(1);
/* 2255 */             i = 8;
/* 2256 */             break;
/*      */           
/*      */ 
/*      */           case 7: 
/* 2260 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/* 2261 */             localPreparedStatement.setString(1, this.sqlName.getSchema());
/* 2262 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/* 2263 */             localPreparedStatement.setFetchSize(this.idx);
/* 2264 */             localResultSet = localPreparedStatement.executeQuery();
/* 2265 */             i = 8;
/* 2266 */             break;
/*      */           
/*      */ 
/*      */           case 8: 
/* 2270 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/* 2271 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 2272 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/* 2273 */             localPreparedStatement.setFetchSize(this.idx);
/* 2274 */             localResultSet = localPreparedStatement.executeQuery();
/* 2275 */             i = 9;
/* 2276 */             break;
/*      */           
/*      */           case 9: 
/* 2279 */             if (this.connection.getVersionNumber() >= 10000) {
/* 2280 */               i = 10;
/*      */             }
/*      */           
/*      */ 
/*      */           case 10: 
/* 2285 */             localObject1 = this.connection.prepareCall(getSqlHint() + sqlString[i]);
/* 2286 */             ((CallableStatement)localObject1).setString(1, this.sqlName.getSimpleName());
/* 2287 */             ((CallableStatement)localObject1).registerOutParameter(2, -10);
/* 2288 */             ((CallableStatement)localObject1).execute();
/* 2289 */             localResultSet = ((OracleCallableStatement)localObject1).getCursor(2);
/* 2290 */             i = 11;
/*      */           }
/*      */           
/*      */           try
/*      */           {
/* 2295 */             for (j = 0; 
/* 2296 */                 (j < this.attrTypes.length) && (localResultSet.next()); 
/* 2297 */                 j++) {
/* 2298 */               if (localResultSet.getInt(1) != j + 1)
/*      */               {
/* 2300 */                 localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "inconsistent ADT attribute");
/* 2301 */                 ((SQLException)localObject2).fillInStackTrace();
/* 2302 */                 throw ((Throwable)localObject2);
/*      */               }
/*      */               
/*      */ 
/*      */ 
/* 2307 */               arrayOfString1[j] = localResultSet.getString(2);
/*      */               
/*      */ 
/* 2310 */               Object localObject2 = localResultSet.getString(4);
/* 2311 */               arrayOfString2[j] = "";
/* 2312 */               if (localObject2 != null) {
/* 2313 */                 arrayOfString2[j] = ((String)localObject2 + ".");
/*      */               }
/* 2315 */               int tmp1003_1001 = j; String[] tmp1003_999 = arrayOfString2;tmp1003_999[tmp1003_1001] = (tmp1003_999[tmp1003_1001] + localResultSet.getString(3));
/*      */             }
/*      */             
/* 2318 */             if (j != 0) {
/* 2319 */               this.attrTypeNames = arrayOfString2;
/*      */               
/* 2321 */               this.attrNames = arrayOfString1;
/* 2322 */               i = 11;
/*      */             }
/*      */             else {
/* 2325 */               if (localResultSet != null) localResultSet.close();
/* 2326 */               if (localPreparedStatement != null) localPreparedStatement.close();
/* 2327 */               if (localObject1 != null) ((CallableStatement)localObject1).close();
/*      */             }
/*      */           }
/*      */           finally {
/* 2331 */             if (localResultSet != null) localResultSet.close();
/* 2332 */             if (localPreparedStatement != null) localPreparedStatement.close();
/* 2333 */             if (localObject1 != null) { ((CallableStatement)localObject1).close();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2343 */   static final String[] sqlStringTOID = { "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM USER_TYPE_ATTRS a, USER_TYPES b WHERE b.TYPE_OID = :1 AND a.TYPE_NAME = b.TYPE_NAME ORDER BY ATTR_NO", "SELECT ATTR_NO, ATTR_NAME, ATTR_TYPE_NAME, ATTR_TYPE_OWNER FROM ALL_TYPE_ATTRS a, ALL_TYPES b WHERE b.TYPE_OID = :1 AND a.TYPE_NAME = b.TYPE_NAME AND a.OWNER = b.OWNER ORDER BY ATTR_NO" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Boolean isInstanciable;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   String superTypeName;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initADTAttrNamesUsingTOID()
/*      */     throws SQLException
/*      */   {
/* 2365 */     synchronized (this.connection) {
/* 2366 */       Object localObject1 = null;
/* 2367 */       PreparedStatement localPreparedStatement = null;
/* 2368 */       ResultSet localResultSet = null;
/* 2369 */       String[] arrayOfString1 = new String[this.attrTypes.length];
/* 2370 */       String[] arrayOfString2 = new String[this.attrTypes.length];
/* 2371 */       int i = 0;
/* 2372 */       int j = 0;
/* 2373 */       if (this.attrNames == null)
/*      */       {
/* 2375 */         i = this.sqlName.getSchema().equals(this.connection.getDefaultSchemaNameForNamedTypes()) ? 0 : 1;
/*      */         
/*      */ 
/*      */ 
/* 2379 */         while (i != 11) {
/* 2380 */           switch (i)
/*      */           {
/*      */           case 0: 
/* 2383 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlStringTOID[i]);
/*      */             
/* 2385 */             localPreparedStatement.setBytes(1, this.toid);
/* 2386 */             localPreparedStatement.setFetchSize(this.idx);
/* 2387 */             localResultSet = localPreparedStatement.executeQuery();
/* 2388 */             i = 1;
/* 2389 */             break;
/*      */           
/*      */ 
/*      */           case 1: 
/* 2393 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlStringTOID[i]);
/*      */             
/* 2395 */             localPreparedStatement.setBytes(1, this.toid);
/* 2396 */             localPreparedStatement.setFetchSize(this.idx);
/* 2397 */             localResultSet = localPreparedStatement.executeQuery();
/* 2398 */             i = 11;
/*      */           }
/*      */           
/*      */           try
/*      */           {
/* 2403 */             for (j = 0; 
/* 2404 */                 (j < this.attrTypes.length) && (localResultSet.next()); 
/* 2405 */                 j++) {
/* 2406 */               if ((localResultSet.getInt(1) != j + 1) && (i == 1))
/*      */               {
/*      */ 
/*      */ 
/* 2410 */                 localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "inconsistent ADT attribute");
/* 2411 */                 ((SQLException)localObject2).fillInStackTrace();
/* 2412 */                 throw ((Throwable)localObject2);
/*      */               }
/*      */               
/*      */ 
/*      */ 
/* 2417 */               arrayOfString1[j] = localResultSet.getString(2);
/*      */               
/*      */ 
/* 2420 */               Object localObject2 = localResultSet.getString(4);
/* 2421 */               arrayOfString2[j] = "";
/* 2422 */               if (localObject2 != null) arrayOfString2[j] = ((String)localObject2 + ".");
/* 2423 */               int tmp391_389 = j; String[] tmp391_387 = arrayOfString2;tmp391_387[tmp391_389] = (tmp391_387[tmp391_389] + localResultSet.getString(3));
/*      */             }
/*      */             
/* 2426 */             if (j != 0) {
/* 2427 */               this.attrTypeNames = arrayOfString2;
/*      */               
/* 2429 */               this.attrNames = arrayOfString1;
/* 2430 */               i = 11;
/*      */             }
/*      */             else {
/* 2433 */               if (localResultSet != null) localResultSet.close();
/* 2434 */               if (localPreparedStatement != null) localPreparedStatement.close();
/*      */             }
/*      */           }
/*      */           finally {
/* 2438 */             if (localResultSet != null) localResultSet.close();
/* 2439 */             if (localPreparedStatement != null) localPreparedStatement.close();
/* 2440 */             if (localObject1 != null) { ((CallableStatement)localObject1).close();
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   StructDescriptor createStructDescriptor()
/*      */     throws SQLException
/*      */   {
/* 2461 */     StructDescriptor localStructDescriptor = (StructDescriptor)this.descriptor;
/*      */     
/* 2463 */     if (localStructDescriptor == null) {
/* 2464 */       localStructDescriptor = new StructDescriptor(this, this.connection);
/*      */     }
/* 2466 */     return localStructDescriptor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   STRUCT createObjSTRUCT(StructDescriptor paramStructDescriptor, Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 2474 */     if ((this.statusBits & 0x10) != 0) {
/* 2475 */       return new JAVA_STRUCT(paramStructDescriptor, this.connection, paramArrayOfObject);
/*      */     }
/* 2477 */     return new STRUCT(paramStructDescriptor, this.connection, paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   STRUCT createByteSTRUCT(StructDescriptor paramStructDescriptor, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2485 */     if ((this.statusBits & 0x10) != 0) {
/* 2486 */       return new JAVA_STRUCT(paramStructDescriptor, paramArrayOfByte, this.connection);
/*      */     }
/* 2488 */     return new STRUCT(paramStructDescriptor, paramArrayOfByte, this.connection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String getSubtypeName(Connection paramConnection, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 2500 */     PickleContext localPickleContext = new PickleContext(paramArrayOfByte, paramLong);
/* 2501 */     byte b = localPickleContext.readByte();
/*      */     
/* 2503 */     if ((!PickleContext.is81format(b)) || (PickleContext.isCollectionImage_pctx(b)) || (!PickleContext.hasPrefix(b)))
/*      */     {
/* 2505 */       return null;
/*      */     }
/*      */     
/*      */     Object localObject;
/* 2509 */     if (!localPickleContext.readAndCheckVersion())
/*      */     {
/* 2511 */       localObject = DatabaseError.createSqlException(null, 1, "Image version is not recognized");
/* 2512 */       ((SQLException)localObject).fillInStackTrace();
/* 2513 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2518 */     localPickleContext.skipLength();
/*      */     
/*      */ 
/* 2521 */     localPickleContext.skipLength();
/*      */     
/* 2523 */     b = localPickleContext.readByte();
/*      */     
/*      */ 
/* 2526 */     if ((b & 0x4) != 0)
/*      */     {
/* 2528 */       localObject = localPickleContext.readBytes(16);
/*      */       
/* 2530 */       return toid2typename(paramConnection, (byte[])localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2539 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static String toid2typename(Connection paramConnection, byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 2549 */     String str = (String)((OracleConnection)paramConnection).getDescriptor(paramArrayOfByte);
/*      */     
/* 2551 */     if (str == null)
/*      */     {
/* 2553 */       PreparedStatement localPreparedStatement = null;
/* 2554 */       ResultSet localResultSet = null;
/*      */       
/*      */       try
/*      */       {
/* 2558 */         localPreparedStatement = paramConnection.prepareStatement("select owner, type_name from all_types where type_oid = :1");
/*      */         
/*      */ 
/* 2561 */         localPreparedStatement.setBytes(1, paramArrayOfByte);
/*      */         
/* 2563 */         localResultSet = localPreparedStatement.executeQuery();
/*      */         
/* 2565 */         if (localResultSet.next())
/*      */         {
/* 2567 */           str = localResultSet.getString(1) + "." + localResultSet.getString(2);
/*      */           
/* 2569 */           ((OracleConnection)paramConnection).putDescriptor(paramArrayOfByte, str);
/*      */         }
/*      */         else
/*      */         {
/* 2573 */           SQLException localSQLException = DatabaseError.createSqlException(null, 1, "Invalid type oid");
/* 2574 */           localSQLException.fillInStackTrace();
/* 2575 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */       finally
/*      */       {
/* 2580 */         if (localResultSet != null) {
/* 2581 */           localResultSet.close();
/*      */         }
/* 2583 */         if (localPreparedStatement != null) {
/* 2584 */           localPreparedStatement.close();
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2589 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getTdsVersion()
/*      */   {
/* 2596 */     return this.tdsVersion;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void printDebug() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private String debugText()
/*      */   {
/* 2615 */     StringWriter localStringWriter = new StringWriter();
/* 2616 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/*      */     
/* 2618 */     localPrintWriter.println("OracleTypeADT = " + this);
/* 2619 */     localPrintWriter.println("sqlName = " + this.sqlName);
/*      */     
/* 2621 */     localPrintWriter.println("OracleType[] : ");
/*      */     
/* 2623 */     if (this.attrTypes != null)
/*      */     {
/* 2625 */       for (int i = 0; i < this.attrTypes.length; i++) {
/* 2626 */         localPrintWriter.println("[" + i + "] = " + this.attrTypes[i]);
/*      */       }
/*      */     } else {
/* 2629 */       localPrintWriter.println("null");
/*      */     }
/* 2631 */     localPrintWriter.println("toid : ");
/*      */     
/* 2633 */     if (this.toid != null) {
/* 2634 */       printUnsignedByteArray(this.toid, localPrintWriter);
/*      */     } else {
/* 2636 */       localPrintWriter.println("null");
/*      */     }
/*      */     
/* 2639 */     localPrintWriter.println("tds version : " + this.tdsVersion);
/* 2640 */     localPrintWriter.println("type version : " + this.typeVersion);
/* 2641 */     localPrintWriter.println("type version : " + this.typeVersion);
/* 2642 */     localPrintWriter.println("opcode : " + this.opcode);
/*      */     
/* 2644 */     localPrintWriter.println("tdoCState : " + this.tdoCState);
/*      */     
/* 2646 */     return localStringWriter.getBuffer().substring(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] getTOID()
/*      */   {
/*      */     try
/*      */     {
/* 2662 */       if (this.toid == null)
/*      */       {
/*      */ 
/* 2665 */         initMetadata(this.connection);
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/* 2670 */     return this.toid;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getImageFormatVersion()
/*      */   {
/* 2677 */     return PickleContext.KOPI20_VERSION;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getTypeVersion()
/*      */   {
/*      */     try
/*      */     {
/* 2686 */       if (this.typeVersion == -1)
/*      */       {
/* 2688 */         initMetadata(this.connection);
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/* 2692 */     return this.typeVersion;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getCharSet()
/*      */   {
/* 2699 */     return this.charSetId;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getCharSetForm()
/*      */   {
/* 2706 */     return this.charSetForm;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getTdoCState()
/*      */   {
/* 2716 */     synchronized (this.connection)
/*      */     {
/*      */       try
/*      */       {
/* 2720 */         if (this.tdoCState == 0L)
/*      */         {
/* 2722 */           getFullName();
/* 2723 */           this.tdoCState = this.connection.getTdoCState(this.sqlName.getSchema(), this.sqlName.getSimpleName());
/*      */         }
/*      */       }
/*      */       catch (SQLException localSQLException) {}
/*      */       
/* 2728 */       return this.tdoCState;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public long getFIXED_DATA_SIZE()
/*      */   {
/*      */     try
/*      */     {
/* 2738 */       return getFixedDataSize();
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/* 2742 */     return 0L;
/*      */   }
/*      */   
/*      */ 
/*      */   public long getFixedDataSize()
/*      */     throws SQLException
/*      */   {
/* 2749 */     return this.fixedDataSize;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getAlignmentReq()
/*      */     throws SQLException
/*      */   {
/* 2756 */     return this.alignmentRequirement;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getNumAttrs()
/*      */     throws SQLException
/*      */   {
/* 2763 */     if ((this.attrTypes == null) && (this.connection != null)) {
/* 2764 */       init(this.connection);
/*      */     }
/* 2766 */     return this.attrTypes.length;
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleType getAttrTypeAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2773 */     if ((this.attrTypes == null) && (this.connection != null)) {
/* 2774 */       init(this.connection);
/*      */     }
/* 2776 */     return this.attrTypes[paramInt];
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isEmbeddedADT()
/*      */     throws SQLException
/*      */   {
/* 2783 */     return (this.statusBits & 0x2) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isUptADT()
/*      */     throws SQLException
/*      */   {
/* 2790 */     return (this.statusBits & 0x4) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isTopADT()
/*      */     throws SQLException
/*      */   {
/* 2797 */     return (this.statusBits & 0x1) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setStatus(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 2804 */     this.statusBits = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */   void setEmbeddedADT()
/*      */     throws SQLException
/*      */   {
/* 2811 */     maskAndSetStatusBits(-16, 2);
/*      */   }
/*      */   
/*      */ 
/*      */   void setUptADT()
/*      */     throws SQLException
/*      */   {
/* 2818 */     maskAndSetStatusBits(-16, 4);
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isSubType()
/*      */     throws SQLException
/*      */   {
/* 2825 */     return (this.statusBits & 0x40) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isFinalType()
/*      */     throws SQLException
/*      */   {
/* 2834 */     return ((this.statusBits & 0x20) != 0 ? 1 : 0) | ((this.statusBits & 0x2) != 0 ? 1 : 0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isJavaObject()
/*      */     throws SQLException
/*      */   {
/* 2842 */     return (this.statusBits & 0x10) != 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getStatus()
/*      */     throws SQLException
/*      */   {
/* 2851 */     if (((this.statusBits & 0x1) != 0) && ((this.statusBits & 0x100) == 0))
/* 2852 */       init(this.connection);
/* 2853 */     return this.statusBits;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static OracleTypeADT shallowClone(OracleTypeADT paramOracleTypeADT)
/*      */     throws SQLException
/*      */   {
/* 2861 */     OracleTypeADT localOracleTypeADT = new OracleTypeADT();
/* 2862 */     shallowCopy(paramOracleTypeADT, localOracleTypeADT);
/* 2863 */     return localOracleTypeADT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static void shallowCopy(OracleTypeADT paramOracleTypeADT1, OracleTypeADT paramOracleTypeADT2)
/*      */     throws SQLException
/*      */   {
/* 2871 */     paramOracleTypeADT2.connection = paramOracleTypeADT1.connection;
/* 2872 */     paramOracleTypeADT2.sqlName = paramOracleTypeADT1.sqlName;
/* 2873 */     paramOracleTypeADT2.parent = paramOracleTypeADT1.parent;
/* 2874 */     paramOracleTypeADT2.idx = paramOracleTypeADT1.idx;
/* 2875 */     paramOracleTypeADT2.descriptor = paramOracleTypeADT1.descriptor;
/* 2876 */     paramOracleTypeADT2.statusBits = paramOracleTypeADT1.statusBits;
/*      */     
/* 2878 */     paramOracleTypeADT2.typeCode = paramOracleTypeADT1.typeCode;
/* 2879 */     paramOracleTypeADT2.dbTypeCode = paramOracleTypeADT1.dbTypeCode;
/* 2880 */     paramOracleTypeADT2.tdsVersion = paramOracleTypeADT1.tdsVersion;
/* 2881 */     paramOracleTypeADT2.typeVersion = paramOracleTypeADT1.typeVersion;
/* 2882 */     paramOracleTypeADT2.fixedDataSize = paramOracleTypeADT1.fixedDataSize;
/* 2883 */     paramOracleTypeADT2.alignmentRequirement = paramOracleTypeADT1.alignmentRequirement;
/* 2884 */     paramOracleTypeADT2.attrTypes = paramOracleTypeADT1.attrTypes;
/* 2885 */     paramOracleTypeADT2.sqlName = paramOracleTypeADT1.sqlName;
/* 2886 */     paramOracleTypeADT2.tdoCState = paramOracleTypeADT1.tdoCState;
/* 2887 */     paramOracleTypeADT2.toid = paramOracleTypeADT1.toid;
/* 2888 */     paramOracleTypeADT2.charSetId = paramOracleTypeADT1.charSetId;
/* 2889 */     paramOracleTypeADT2.charSetForm = paramOracleTypeADT1.charSetForm;
/* 2890 */     paramOracleTypeADT2.flattenedAttrNum = paramOracleTypeADT1.flattenedAttrNum;
/* 2891 */     paramOracleTypeADT2.statusBits = paramOracleTypeADT1.statusBits;
/* 2892 */     paramOracleTypeADT2.attrNames = paramOracleTypeADT1.attrNames;
/* 2893 */     paramOracleTypeADT2.attrTypeNames = paramOracleTypeADT1.attrTypeNames;
/* 2894 */     paramOracleTypeADT2.opcode = paramOracleTypeADT1.opcode;
/* 2895 */     paramOracleTypeADT2.idx = paramOracleTypeADT1.idx;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/* 2906 */     paramObjectOutputStream.writeInt(this.statusBits);
/* 2907 */     paramObjectOutputStream.writeInt(this.tdsVersion);
/* 2908 */     paramObjectOutputStream.writeInt(this.typeVersion);
/* 2909 */     paramObjectOutputStream.writeObject(null);
/* 2910 */     paramObjectOutputStream.writeObject(null);
/* 2911 */     paramObjectOutputStream.writeLong(this.fixedDataSize);
/* 2912 */     paramObjectOutputStream.writeInt(this.alignmentRequirement);
/* 2913 */     paramObjectOutputStream.writeObject(this.attrTypes);
/* 2914 */     paramObjectOutputStream.writeObject(this.attrNames);
/* 2915 */     paramObjectOutputStream.writeObject(this.attrTypeNames);
/* 2916 */     paramObjectOutputStream.writeLong(this.tdoCState);
/* 2917 */     paramObjectOutputStream.writeObject(this.toid);
/* 2918 */     paramObjectOutputStream.writeObject(null);
/* 2919 */     paramObjectOutputStream.writeInt(this.charSetId);
/* 2920 */     paramObjectOutputStream.writeInt(this.charSetForm);
/* 2921 */     paramObjectOutputStream.writeBoolean(true);
/* 2922 */     paramObjectOutputStream.writeInt(this.flattenedAttrNum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 2930 */     this.statusBits = paramObjectInputStream.readInt();
/* 2931 */     this.tdsVersion = paramObjectInputStream.readInt();
/* 2932 */     this.typeVersion = paramObjectInputStream.readInt();
/* 2933 */     paramObjectInputStream.readObject();
/* 2934 */     paramObjectInputStream.readObject();
/* 2935 */     paramObjectInputStream.readLong();
/* 2936 */     paramObjectInputStream.readInt();
/* 2937 */     this.attrTypes = ((OracleType[])paramObjectInputStream.readObject());
/* 2938 */     this.attrNames = ((String[])paramObjectInputStream.readObject());
/* 2939 */     this.attrTypeNames = ((String[])paramObjectInputStream.readObject());
/* 2940 */     paramObjectInputStream.readLong();
/* 2941 */     this.toid = ((byte[])paramObjectInputStream.readObject());
/* 2942 */     paramObjectInputStream.readObject();
/* 2943 */     this.charSetId = paramObjectInputStream.readInt();
/* 2944 */     this.charSetForm = paramObjectInputStream.readInt();
/* 2945 */     paramObjectInputStream.readBoolean();
/* 2946 */     this.flattenedAttrNum = paramObjectInputStream.readInt();
/*      */   }
/*      */   
/*      */   public void setConnection(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 2952 */     synchronized (paramOracleConnection)
/*      */     {
/* 2954 */       this.connection = paramOracleConnection;
/* 2955 */       for (int i = 0; i < this.attrTypes.length; i++) {
/* 2956 */         this.attrTypes[i].setConnection(this.connection);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   private void setStatusBits(int paramInt)
/*      */   {
/* 2963 */     synchronized (this.connection)
/*      */     {
/* 2965 */       this.statusBits |= paramInt;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void maskAndSetStatusBits(int paramInt1, int paramInt2)
/*      */   {
/* 2972 */     synchronized (this.connection)
/*      */     {
/* 2974 */       this.statusBits &= paramInt1;
/* 2975 */       this.statusBits |= paramInt2;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void printUnsignedByteArray(byte[] paramArrayOfByte, PrintWriter paramPrintWriter)
/*      */   {
/* 2984 */     int i = paramArrayOfByte.length;
/*      */     
/*      */ 
/* 2987 */     int[] arrayOfInt = Util.toJavaUnsignedBytes(paramArrayOfByte);
/*      */     
/* 2989 */     for (int j = 0; j < i; j++)
/*      */     {
/* 2991 */       paramPrintWriter.print("0x" + Integer.toHexString(arrayOfInt[j]) + " ");
/*      */     }
/*      */     
/* 2994 */     paramPrintWriter.println();
/*      */     
/* 2996 */     for (j = 0; j < i; j++)
/*      */     {
/* 2998 */       paramPrintWriter.print(arrayOfInt[j] + " ");
/*      */     }
/*      */     
/* 3001 */     paramPrintWriter.println();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void initChildNamesRecursively(Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 3009 */     TypeTreeElement localTypeTreeElement = (TypeTreeElement)paramMap.get(this.sqlName);
/*      */     
/* 3011 */     if ((this.attrTypes != null) && (this.attrTypes.length > 0))
/*      */     {
/* 3013 */       for (int i = 0; i < this.attrTypes.length; i++)
/*      */       {
/* 3015 */         OracleType localOracleType = this.attrTypes[i];
/* 3016 */         localOracleType.setNames(localTypeTreeElement.getChildSchemaName(i + 1), localTypeTreeElement.getChildTypeName(i + 1));
/* 3017 */         localOracleType.initChildNamesRecursively(paramMap);
/* 3018 */         localOracleType.cacheDescriptor();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void cacheDescriptor()
/*      */     throws SQLException
/*      */   {
/* 3027 */     this.descriptor = StructDescriptor.createDescriptor(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initMetaData1()
/*      */     throws SQLException
/*      */   {
/* 3039 */     int i = this.connection.getVersionNumber();
/*      */     
/* 3041 */     if (i >= 9000) {
/* 3042 */       initMetaData1_9_0();
/*      */     } else {
/* 3044 */       initMetaData1_pre_9_0();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 3050 */   int numberOfLocalAttributes = -1;
/*      */   String[] subTypeNames;
/*      */   
/*      */   public Boolean isInstanciable() throws SQLException
/*      */   {
/* 3055 */     if (this.isInstanciable == null) initMetaData1();
/* 3056 */     return this.isInstanciable;
/*      */   }
/*      */   
/*      */ 
/*      */   public String getSuperTypeName()
/*      */     throws SQLException
/*      */   {
/* 3063 */     if (this.superTypeName == null) initMetaData1();
/* 3064 */     return this.superTypeName;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getNumberOfLocalAttributes()
/*      */     throws SQLException
/*      */   {
/* 3071 */     if (this.numberOfLocalAttributes == -1) initMetaData1();
/* 3072 */     return this.numberOfLocalAttributes;
/*      */   }
/*      */   
/*      */ 
/*      */   public String[] getSubtypeNames()
/*      */     throws SQLException
/*      */   {
/* 3079 */     if (this.subTypeNames == null) initMetaData1();
/* 3080 */     return this.subTypeNames;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3088 */   final int LOCAL_TYPE = 0;
/* 3089 */   final int LOOK_FOR_USER_SYNONYM = 1;
/* 3090 */   final int LOOK_FOR_PUBLIC_SYNONYM = 2;
/* 3091 */   final String[] initMetaData1_9_0_SQL = { "SELECT INSTANTIABLE, supertype_owner, supertype_name, LOCAL_ATTRIBUTES FROM all_types WHERE type_name = :1 AND owner = :2 ", "DECLARE \n bind_synonym_name user_synonyms.synonym_name%type := :1; \n the_table_owner  user_synonyms.table_owner%type; \n the_table_name   user_synonyms.table_name%type; \n the_db_link      user_synonyms.db_link%type; \n sql_string       VARCHAR2(1000); \nBEGIN \n   SELECT TABLE_NAME, TABLE_OWNER, DB_LINK INTO  \n         the_table_name, the_table_owner, the_db_link \n         FROM USER_SYNONYMS WHERE \n         SYNONYM_NAME = bind_synonym_name; \n \n   sql_string := 'SELECT INSTANTIABLE, SUPERTYPE_OWNER,      SUPERTYPE_NAME, LOCAL_ATTRIBUTES FROM ALL_TYPES'; \n \n   IF the_db_link IS NOT NULL  \n   THEN \n     sql_string := sql_string || '@' || the_db_link; \n   END IF; \n   sql_string := sql_string       || ' WHERE TYPE_NAME = '''       || the_table_name   || ''' AND OWNER = '''       || the_table_owner  || ''''; \n   OPEN :2 FOR sql_string; \nEND;", "DECLARE \n bind_synonym_name user_synonyms.synonym_name%type := :1; \n the_table_owner  user_synonyms.table_owner%type; \n the_table_name   user_synonyms.table_name%type; \n the_db_link      user_synonyms.db_link%type; \n sql_string       VARCHAR2(1000); \nBEGIN \n   SELECT TABLE_NAME, TABLE_OWNER, DB_LINK INTO  \n         the_table_name, the_table_owner, the_db_link \n         FROM ALL_SYNONYMS WHERE \n         OWNER = 'PUBLIC' AND \n         SYNONYM_NAME = bind_synonym_name; \n \n   sql_string := 'SELECT INSTANTIABLE, SUPERTYPE_OWNER,      SUPERTYPE_NAME, LOCAL_ATTRIBUTES FROM ALL_TYPES'; \n \n   IF the_db_link IS NOT NULL  \n   THEN \n     sql_string := sql_string || '@' || the_db_link; \n   END IF; \n   sql_string := sql_string       || ' WHERE TYPE_NAME = '''       || the_table_name   || ''' AND OWNER = '''       || the_table_owner  || ''''; \n   OPEN :2 FOR sql_string; \nEND;" };
/*      */   
/*      */   static final int TDS_SIZE = 4;
/*      */   
/*      */   static final int TDS_NUMBER = 1;
/*      */   
/*      */   static final int KOPM_OTS_SQL_CHAR = 1;
/*      */   
/*      */   static final int KOPM_OTS_DATE = 2;
/*      */   
/*      */   static final int KOPM_OTS_DECIMAL = 3;
/*      */   
/*      */   static final int KOPM_OTS_DOUBLE = 4;
/*      */   
/*      */   static final int KOPM_OTS_FLOAT = 5;
/*      */   
/*      */   static final int KOPM_OTS_NUMBER = 6;
/*      */   
/*      */   static final int KOPM_OTS_SQL_VARCHAR2 = 7;
/*      */   
/*      */   static final int KOPM_OTS_SINT32 = 8;
/*      */   
/*      */   static final int KOPM_OTS_REF = 9;
/*      */   
/*      */   static final int KOPM_OTS_VARRAY = 10;
/*      */   
/*      */   static final int KOPM_OTS_UINT8 = 11;
/*      */   
/*      */   static final int KOPM_OTS_SINT8 = 12;
/*      */   
/*      */   static final int KOPM_OTS_UINT16 = 13;
/*      */   
/*      */   static final int KOPM_OTS_UINT32 = 14;
/*      */   
/*      */   static final int KOPM_OTS_LOB = 15;
/*      */   static final int KOPM_OTS_CANONICAL = 17;
/*      */   static final int KOPM_OTS_OCTET = 18;
/*      */   static final int KOPM_OTS_RAW = 19;
/*      */   static final int KOPM_OTS_ROWID = 20;
/*      */   static final int KOPM_OTS_STAMP = 21;
/*      */   static final int KOPM_OTS_TZSTAMP = 23;
/*      */   static final int KOPM_OTS_INTERVAL = 24;
/*      */   static final int KOPM_OTS_PTR = 25;
/*      */   static final int KOPM_OTS_SINT16 = 26;
/*      */   static final int KOPM_OTS_UPT = 27;
/*      */   static final int KOPM_OTS_COLLECTION = 28;
/*      */   static final int KOPM_OTS_CLOB = 29;
/*      */   static final int KOPM_OTS_BLOB = 30;
/*      */   static final int KOPM_OTS_BFILE = 31;
/*      */   static final int KOPM_OTS_BINARY_INTEGE = 32;
/*      */   static final int KOPM_OTS_IMPTZSTAMP = 33;
/*      */   static final int KOPM_OTS_BFLOAT = 37;
/*      */   static final int KOPM_OTS_BDOUBLE = 45;
/*      */   static final int KOTTCOPQ = 58;
/*      */   static final int KOPT_OP_STARTEMBADT = 39;
/*      */   static final int KOPT_OP_ENDEMBADT = 40;
/*      */   static final int KOPT_OP_STARTADT = 41;
/*      */   static final int KOPT_OP_ENDADT = 42;
/*      */   static final int KOPT_OP_SUBTYPE_MARKER = 43;
/*      */   static final int KOPT_OP_EMBADT_INFO = 44;
/*      */   static final int KOPT_OPCODE_START = 38;
/*      */   static final int KOPT_OP_VERSION = 38;
/*      */   static final int REGULAR_PATCH = 0;
/*      */   static final int SIMPLE_PATCH = 1;
/*      */   
/*      */   private void initMetaData1_9_0()
/*      */     throws SQLException
/*      */   {
/* 3159 */     if (getTOID() != null) {
/* 3160 */       initMetaData1_9_0UseToid();
/* 3161 */       return;
/*      */     }
/*      */     
/* 3164 */     int i = 0;
/*      */     
/*      */ 
/* 3167 */     if (this.sqlName == null) {
/* 3168 */       getFullName();
/*      */     }
/* 3170 */     synchronized (this.connection)
/*      */     {
/* 3172 */       synchronized (this)
/*      */       {
/* 3174 */         if (this.numberOfLocalAttributes == -1)
/*      */         {
/* 3176 */           PreparedStatement localPreparedStatement = null;
/* 3177 */           OracleCallableStatement localOracleCallableStatement = null;
/* 3178 */           ResultSet localResultSet = null;
/* 3179 */           int j = -1;
/*      */           
/*      */ 
/*      */           try
/*      */           {
/*      */             for (;;)
/*      */             {
/* 3186 */               switch (i)
/*      */               {
/*      */               case 0: 
/* 3189 */                 localPreparedStatement = this.connection.prepareStatement(this.initMetaData1_9_0_SQL[i]);
/*      */                 
/*      */ 
/* 3192 */                 localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 3193 */                 localPreparedStatement.setString(2, this.sqlName.getSchema());
/*      */                 
/* 3195 */                 localPreparedStatement.setFetchSize(1);
/* 3196 */                 localResultSet = localPreparedStatement.executeQuery();
/*      */                 
/* 3198 */                 break;
/*      */               
/*      */               case 1: 
/*      */               case 2: 
/*      */                 try
/*      */                 {
/* 3204 */                   localOracleCallableStatement = (OracleCallableStatement)this.connection.prepareCall(this.initMetaData1_9_0_SQL[i]);
/*      */                   
/*      */ 
/* 3207 */                   localOracleCallableStatement.setString(1, this.sqlName.getSimpleName());
/* 3208 */                   localOracleCallableStatement.registerOutParameter(2, -10);
/*      */                   
/*      */ 
/* 3211 */                   localOracleCallableStatement.execute();
/*      */                   
/* 3213 */                   localResultSet = localOracleCallableStatement.getCursor(2);
/* 3214 */                   localResultSet.setFetchSize(1);
/*      */                 }
/*      */                 catch (SQLException localSQLException1) {
/* 3217 */                   if (localSQLException1.getErrorCode() == 1403)
/*      */                   {
/* 3219 */                     if (i == 1)
/*      */                     {
/* 3221 */                       localOracleCallableStatement.close();
/* 3222 */                       i++;
/*      */ 
/*      */                     }
/*      */                     else
/*      */                     {
/*      */ 
/* 3228 */                       SQLException localSQLException3 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Inconsistent catalog view");
/* 3229 */                       localSQLException3.fillInStackTrace();
/* 3230 */                       throw localSQLException3;
/*      */                     }
/*      */                     
/*      */                   }
/*      */                   else {
/* 3235 */                     throw localSQLException1;
/*      */                   }
/*      */                 }
/*      */               
/*      */ 
/*      */ 
/*      */               default: 
/* 3242 */                 if (localResultSet.next())
/*      */                 {
/* 3244 */                   this.isInstanciable = new Boolean(localResultSet.getString(1).equals("YES"));
/* 3245 */                   this.superTypeName = (localResultSet.getString(2) + "." + localResultSet.getString(3));
/* 3246 */                   j = localResultSet.getInt(4);
/*      */                   
/*      */ 
/*      */                   break label423;
/*      */                 }
/*      */                 
/*      */ 
/* 3253 */                 if (i == 2)
/*      */                 {
/*      */ 
/*      */ 
/* 3257 */                   SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Inconsistent catalog view");
/* 3258 */                   localSQLException2.fillInStackTrace();
/* 3259 */                   throw localSQLException2;
/*      */                 }
/*      */                 
/*      */ 
/* 3263 */                 localResultSet.close();
/* 3264 */                 if (localOracleCallableStatement != null)
/* 3265 */                   localOracleCallableStatement.close();
/* 3266 */                 i++;
/*      */               }
/*      */             }
/*      */           }
/*      */           finally
/*      */           {
/*      */             label423:
/* 3273 */             if (localResultSet != null) {
/* 3274 */               localResultSet.close();
/*      */             }
/* 3276 */             if (localPreparedStatement != null) {
/* 3277 */               localPreparedStatement.close();
/*      */             }
/* 3279 */             if (localOracleCallableStatement != null) {
/* 3280 */               localOracleCallableStatement.close();
/*      */             }
/*      */           }
/* 3283 */           this.numberOfLocalAttributes = j;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void initMetaData1_9_0UseToid()
/*      */     throws SQLException
/*      */   {
/* 3294 */     String str = "SELECT INSTANTIABLE, supertype_owner, supertype_name, LOCAL_ATTRIBUTES, cursor(select owner, type_name from all_types WHERE supertype_name = t.type_name and supertype_owner = t.owner)  FROM all_types t WHERE TYPE_OID = :3";
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3299 */     PreparedStatement localPreparedStatement = null;
/* 3300 */     ResultSet localResultSet1 = null;
/* 3301 */     ResultSet localResultSet2 = null;
/*      */     
/*      */ 
/* 3304 */     if (this.sqlName == null) {
/* 3305 */       getFullName();
/*      */     }
/*      */     try
/*      */     {
/* 3309 */       localPreparedStatement = this.connection.prepareStatement(str);
/* 3310 */       localPreparedStatement.setBytes(1, getTOID());
/*      */       
/* 3312 */       localPreparedStatement.setFetchSize(1);
/* 3313 */       localResultSet1 = localPreparedStatement.executeQuery();
/* 3314 */       Object localObject1; if (localResultSet1.next())
/*      */       {
/* 3316 */         this.isInstanciable = new Boolean(localResultSet1.getString(1).equals("YES"));
/* 3317 */         this.superTypeName = (localResultSet1.getString(2) + "." + localResultSet1.getString(3));
/* 3318 */         this.numberOfLocalAttributes = localResultSet1.getInt(4);
/* 3319 */         localResultSet2 = (ResultSet)localResultSet1.getObject(5);
/*      */         
/*      */ 
/* 3322 */         localObject1 = new ArrayList(5);
/* 3323 */         while (localResultSet2.next()) {
/* 3324 */           ((ArrayList)localObject1).add(localResultSet2.getString(1) + "." + localResultSet2.getString(2));
/*      */         }
/* 3326 */         this.subTypeNames = new String[((ArrayList)localObject1).size()];
/* 3327 */         for (int i = 0; i < this.subTypeNames.length; i++) {
/* 3328 */           this.subTypeNames[i] = ((String)((ArrayList)localObject1).get(i));
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 3333 */         localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Inconsistent catalog view");
/* 3334 */         ((SQLException)localObject1).fillInStackTrace();
/* 3335 */         throw ((Throwable)localObject1);
/*      */       }
/*      */     }
/*      */     finally {
/* 3339 */       if (localResultSet2 != null) localResultSet2.close();
/* 3340 */       if (localResultSet1 != null) localResultSet1.close();
/* 3341 */       if (localPreparedStatement != null) { localPreparedStatement.close();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private synchronized void initMetaData1_pre_9_0()
/*      */     throws SQLException
/*      */   {
/* 3353 */     this.isInstanciable = new Boolean(true);
/* 3354 */     this.superTypeName = "";
/* 3355 */     this.numberOfLocalAttributes = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initMetaData2()
/*      */     throws SQLException
/*      */   {
/* 3367 */     int i = this.connection.getVersionNumber();
/*      */     
/* 3369 */     if (i >= 9000) {
/* 3370 */       initMetaData2_9_0();
/*      */     }
/*      */     else {
/* 3373 */       initMetaData2_pre_9_0();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initMetaData2_9_0()
/*      */     throws SQLException
/*      */   {
/* 3385 */     if (getTOID() != null) {
/* 3386 */       initMetaData1_9_0UseToid();
/* 3387 */       return;
/*      */     }
/*      */     
/*      */ 
/* 3391 */     if (this.sqlName == null) {
/* 3392 */       getFullName();
/*      */     }
/* 3394 */     synchronized (this.connection)
/*      */     {
/* 3396 */       synchronized (this)
/*      */       {
/* 3398 */         if (this.subTypeNames == null)
/*      */         {
/* 3400 */           PreparedStatement localPreparedStatement = null;
/* 3401 */           ResultSet localResultSet = null;
/* 3402 */           String[] arrayOfString = null;
/*      */           
/*      */           try
/*      */           {
/* 3406 */             localPreparedStatement = this.connection.prepareStatement("select owner, type_name from all_types where supertype_name = :1 and supertype_owner = :2");
/*      */             
/*      */ 
/* 3409 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/* 3410 */             localPreparedStatement.setString(2, this.sqlName.getSchema());
/*      */             
/* 3412 */             localResultSet = localPreparedStatement.executeQuery();
/*      */             
/* 3414 */             Vector localVector = new Vector();
/*      */             
/* 3416 */             while (localResultSet.next()) {
/* 3417 */               localVector.addElement(localResultSet.getString(1) + "." + localResultSet.getString(2));
/*      */             }
/* 3419 */             arrayOfString = new String[localVector.size()];
/*      */             
/* 3421 */             for (int i = 0; i < arrayOfString.length; i++) {
/* 3422 */               arrayOfString[i] = ((String)localVector.elementAt(i));
/*      */             }
/* 3424 */             localVector.removeAllElements();
/*      */             
/* 3426 */             localVector = null;
/*      */           }
/*      */           finally
/*      */           {
/* 3430 */             if (localResultSet != null) {
/* 3431 */               localResultSet.close();
/*      */             }
/* 3433 */             if (localPreparedStatement != null) {
/* 3434 */               localPreparedStatement.close();
/*      */             }
/*      */           }
/* 3437 */           this.subTypeNames = arrayOfString;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initMetaData2_pre_9_0()
/*      */     throws SQLException
/*      */   {
/* 3455 */     this.subTypeNames = new String[0];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void printXML(PrintWriter paramPrintWriter, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 3466 */     printXML(paramPrintWriter, paramInt, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 3476 */     for (int i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 3477 */     paramPrintWriter.print("<OracleTypeADT sqlName=\"" + this.sqlName + "\" ");
/*      */     
/* 3479 */     paramPrintWriter.print(" typecode=\"" + this.typeCode + "\"");
/* 3480 */     if (this.tdsVersion != 55537)
/* 3481 */       paramPrintWriter.print(" tds_version=\"" + this.tdsVersion + "\"");
/* 3482 */     paramPrintWriter.println();
/* 3483 */     for (i = 0; i < paramInt + 4; i++) paramPrintWriter.print("  ");
/* 3484 */     paramPrintWriter.println(" is_embedded=\"" + isEmbeddedADT() + "\"" + " is_top_level=\"" + isTopADT() + "\"" + " is_upt=\"" + isUptADT() + "\"" + " finalType=\"" + isFinalType() + "\"" + " subtype=\"" + isSubType() + "\">");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3490 */     if ((this.attrTypes != null) && (this.attrTypes.length > 0))
/*      */     {
/* 3492 */       for (i = 0; i < paramInt + 1; i++) paramPrintWriter.print("  ");
/* 3493 */       paramPrintWriter.println("<attributes>");
/* 3494 */       for (i = 0; i < this.attrTypes.length; i++)
/*      */       {
/* 3496 */         for (int j = 0; j < paramInt + 2; j++) { paramPrintWriter.print("  ");
/*      */         }
/*      */         
/* 3499 */         paramPrintWriter.println("<attribute name=\"" + getAttributeName(i + 1, paramBoolean) + "\" " + " type=\"" + getAttributeType(i + 1, false) + "\" >");
/*      */         
/*      */ 
/* 3502 */         this.attrTypes[i].printXML(paramPrintWriter, paramInt + 3, paramBoolean);
/* 3503 */         for (j = 0; j < paramInt + 2; j++) paramPrintWriter.print("  ");
/* 3504 */         paramPrintWriter.println("</attribute> ");
/*      */       }
/* 3506 */       for (i = 0; i < paramInt + 1; i++) paramPrintWriter.print("  ");
/* 3507 */       paramPrintWriter.println("</attributes>");
/*      */     }
/* 3509 */     for (i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 3510 */     paramPrintWriter.println("</OracleTypeADT>");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3569 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeADT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */