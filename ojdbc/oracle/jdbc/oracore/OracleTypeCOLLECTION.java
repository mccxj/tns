/*      */ package oracle.jdbc.oracore;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.sql.CallableStatement;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleCallableStatement;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.ArrayDescriptor;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.SQLName;
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
/*      */ public class OracleTypeCOLLECTION
/*      */   extends OracleTypeADT
/*      */   implements Serializable
/*      */ {
/*      */   static final long serialVersionUID = -7279638692691669378L;
/*      */   public static final int TYPE_PLSQL_INDEX_TABLE = 1;
/*      */   public static final int TYPE_NESTED_TABLE = 2;
/*      */   public static final int TYPE_VARRAY = 3;
/*   59 */   int userCode = 0;
/*   60 */   long maxSize = 0L;
/*   61 */   OracleType elementType = null;
/*      */   static final int CURRENT_USER_OBJECT = 0;
/*      */   static final int CURRENT_USER_SYNONYM = 1;
/*      */   static final int CURRENT_USER_SYNONYM_10g = 2;
/*      */   static final int CURRENT_USER_PUBLIC_SYNONYM = 3;
/*      */   static final int CURRENT_USER_PUBLIC_SYNONYM_10g = 4;
/*      */   static final int POSSIBLY_OTHER_USER_OBJECT = 5;
/*      */   
/*      */   public OracleTypeCOLLECTION(String paramString, OracleConnection paramOracleConnection) throws SQLException {
/*   70 */     super(paramString, paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public OracleTypeCOLLECTION(OracleTypeADT paramOracleTypeADT, int paramInt, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*   78 */     super(paramOracleTypeADT, paramInt, paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeCOLLECTION(SQLName paramSQLName, byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*   89 */     super(paramSQLName, paramArrayOfByte1, paramInt, paramArrayOfByte2, paramOracleConnection);
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
/*      */   public Datum toDatum(Object paramObject, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  105 */     if (paramObject != null)
/*      */     {
/*  107 */       if ((paramObject instanceof ARRAY)) {
/*  108 */         return (ARRAY)paramObject;
/*      */       }
/*      */       
/*  111 */       ArrayDescriptor localArrayDescriptor = createArrayDescriptor();
/*      */       
/*  113 */       return new ARRAY(localArrayDescriptor, this.connection, paramObject);
/*      */     }
/*      */     
/*      */ 
/*  117 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTypeCode()
/*      */   {
/*  129 */     return 2003;
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
/*  140 */     if (paramOracleType == null) {
/*  141 */       return false;
/*      */     }
/*  143 */     if (paramOracleType == this) {
/*  144 */       return true;
/*      */     }
/*  146 */     if (paramOracleType.getClass() != getClass()) {
/*  147 */       return false;
/*      */     }
/*  149 */     return paramOracleType.getTypeDescriptor().getName().equals(this.descriptor.getName());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInHierarchyOf(StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/*  158 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isObjectType()
/*      */   {
/*  165 */     return false;
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
/*      */   public void parseTDSrec(TDSReader paramTDSReader)
/*      */     throws SQLException
/*      */   {
/*  179 */     long l = paramTDSReader.readLong();
/*      */     
/*      */ 
/*      */ 
/*  183 */     this.maxSize = paramTDSReader.readLong();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  189 */     this.userCode = paramTDSReader.readByte();
/*      */     
/*      */ 
/*  192 */     paramTDSReader.addSimplePatch(l, this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum unlinearize(byte[] paramArrayOfByte, long paramLong, Datum paramDatum, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  204 */     return unlinearize(paramArrayOfByte, paramLong, paramDatum, 1L, -1, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum unlinearize(byte[] paramArrayOfByte, long paramLong1, Datum paramDatum, long paramLong2, int paramInt1, int paramInt2, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  213 */     OracleConnection localOracleConnection = getConnection();
/*  214 */     Datum localDatum = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  219 */     if (localOracleConnection == null)
/*      */     {
/*  221 */       localDatum = unlinearizeInternal(paramArrayOfByte, paramLong1, paramDatum, paramLong2, paramInt1, paramInt2, paramMap);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  226 */       synchronized (localOracleConnection)
/*      */       {
/*  228 */         localDatum = unlinearizeInternal(paramArrayOfByte, paramLong1, paramDatum, paramLong2, paramInt1, paramInt2, paramMap);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  233 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized Datum unlinearizeInternal(byte[] paramArrayOfByte, long paramLong1, Datum paramDatum, long paramLong2, int paramInt1, int paramInt2, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  245 */     if (paramArrayOfByte == null) {
/*  246 */       return null;
/*      */     }
/*      */     
/*  249 */     PickleContext localPickleContext = new PickleContext(paramArrayOfByte, paramLong1);
/*      */     
/*  251 */     return unpickle81(localPickleContext, (ARRAY)paramDatum, paramLong2, paramInt1, 1, paramInt2, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public synchronized boolean isInlineImage(byte[] paramArrayOfByte, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  262 */     if (paramArrayOfByte == null) {
/*  263 */       return false;
/*      */     }
/*      */     
/*      */ 
/*  267 */     if (PickleContext.isCollectionImage_pctx(paramArrayOfByte[paramInt]))
/*  268 */       return true;
/*  269 */     if (PickleContext.isDegenerateImage_pctx(paramArrayOfByte[paramInt])) {
/*  270 */       return false;
/*      */     }
/*      */     
/*  273 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image is not a collection image");
/*  274 */     localSQLException.fillInStackTrace();
/*  275 */     throw localSQLException;
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
/*      */   protected int pickle81(PickleContext paramPickleContext, Datum paramDatum)
/*      */     throws SQLException
/*      */   {
/*  289 */     ARRAY localARRAY = (ARRAY)paramDatum;
/*      */     
/*  291 */     boolean bool = localARRAY.hasDataSeg();
/*  292 */     int i = 0;
/*  293 */     int j = paramPickleContext.offset() + 2;
/*      */     
/*  295 */     if (bool)
/*      */     {
/*  297 */       if (!this.metaDataInitialized) {
/*  298 */         copy_properties((OracleTypeCOLLECTION)localARRAY.getDescriptor().getPickler());
/*      */       }
/*  300 */       Datum[] arrayOfDatum = localARRAY.getOracleArray();
/*      */       
/*      */ 
/*  303 */       if (this.userCode == 3)
/*      */       {
/*  305 */         if (arrayOfDatum.length > this.maxSize)
/*      */         {
/*  307 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 71, null);
/*  308 */           localSQLException.fillInStackTrace();
/*  309 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */       
/*  313 */       i += paramPickleContext.writeCollImageHeader(arrayOfDatum.length, this.typeVersion);
/*      */       
/*  315 */       for (int k = 0; k < arrayOfDatum.length; k++)
/*      */       {
/*  317 */         if (arrayOfDatum[k] == null) {
/*  318 */           i += paramPickleContext.writeElementNull();
/*      */         } else {
/*  320 */           i += this.elementType.pickle81(paramPickleContext, arrayOfDatum[k]);
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  327 */       i += paramPickleContext.writeCollImageHeader(localARRAY.getLocator());
/*      */     }
/*      */     
/*  330 */     paramPickleContext.patchImageLen(j, i);
/*      */     
/*  332 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ARRAY unpickle81(PickleContext paramPickleContext, ARRAY paramARRAY, int paramInt1, int paramInt2, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  343 */     return unpickle81(paramPickleContext, paramARRAY, 1L, -1, paramInt1, paramInt2, paramMap);
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
/*      */   ARRAY unpickle81(PickleContext paramPickleContext, ARRAY paramARRAY, long paramLong, int paramInt1, int paramInt2, int paramInt3, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  357 */     ARRAY localARRAY = paramARRAY;
/*      */     
/*  359 */     if (localARRAY == null)
/*      */     {
/*  361 */       ArrayDescriptor localArrayDescriptor = createArrayDescriptor();
/*      */       
/*  363 */       localARRAY = new ARRAY(localArrayDescriptor, (byte[])null, this.connection);
/*      */     }
/*      */     
/*  366 */     if (unpickle81ImgHeader(paramPickleContext, localARRAY, paramInt2, paramInt3))
/*      */     {
/*  368 */       if ((paramLong == 1L) && (paramInt1 == -1)) {
/*  369 */         unpickle81ImgBody(paramPickleContext, localARRAY, paramInt3, paramMap);
/*      */       } else {
/*  371 */         unpickle81ImgBody(paramPickleContext, localARRAY, paramLong, paramInt1, paramInt3, paramMap);
/*      */       }
/*      */     }
/*      */     
/*  375 */     return localARRAY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   boolean unpickle81ImgHeader(PickleContext paramPickleContext, ARRAY paramARRAY, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  383 */     int i = 1;
/*      */     
/*  385 */     if (paramInt1 == 3)
/*      */     {
/*  387 */       paramARRAY.setImage(paramPickleContext.image(), paramPickleContext.absoluteOffset(), 0L);
/*      */     }
/*      */     
/*  390 */     byte b = paramPickleContext.readByte();
/*      */     SQLException localSQLException;
/*  392 */     if (!PickleContext.is81format(b))
/*      */     {
/*  394 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image is not in 8.1 format");
/*  395 */       localSQLException.fillInStackTrace();
/*  396 */       throw localSQLException;
/*      */     }
/*      */     
/*  399 */     if (!PickleContext.hasPrefix(b))
/*      */     {
/*  401 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image has no prefix segment");
/*  402 */       localSQLException.fillInStackTrace();
/*  403 */       throw localSQLException;
/*      */     }
/*      */     
/*  406 */     if (PickleContext.isCollectionImage_pctx(b)) {
/*  407 */       i = 1;
/*  408 */     } else if (PickleContext.isDegenerateImage_pctx(b)) {
/*  409 */       i = 0;
/*      */     }
/*      */     else {
/*  412 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Image is not a collection image");
/*  413 */       localSQLException.fillInStackTrace();
/*  414 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  418 */     paramPickleContext.readByte();
/*      */     
/*      */ 
/*  421 */     if (paramInt1 == 9)
/*      */     {
/*  423 */       paramPickleContext.skipBytes(paramPickleContext.readLength(true) - 2);
/*      */       
/*  425 */       return false;
/*      */     }
/*  427 */     if (paramInt1 == 3)
/*      */     {
/*  429 */       long l = paramPickleContext.readLength();
/*      */       
/*  431 */       paramARRAY.setImageLength(l);
/*  432 */       paramPickleContext.skipTo(paramARRAY.getImageOffset() + l);
/*      */       
/*  434 */       return false;
/*      */     }
/*      */     
/*  437 */     paramPickleContext.skipLength();
/*      */     
/*      */ 
/*  440 */     int j = paramPickleContext.readLength();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  445 */     paramARRAY.setPrefixFlag(paramPickleContext.readByte());
/*      */     
/*  447 */     if (paramARRAY.isInline())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  454 */       paramPickleContext.readDataValue(j - 1);
/*      */     }
/*      */     else
/*      */     {
/*  458 */       paramARRAY.setLocator(paramPickleContext.readDataValue(j - 1));
/*      */     }
/*      */     
/*  461 */     return paramARRAY.isInline();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void unpickle81ImgBody(PickleContext paramPickleContext, ARRAY paramARRAY, long paramLong, int paramInt1, int paramInt2, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  473 */     paramPickleContext.readByte();
/*      */     
/*      */ 
/*  476 */     int i = paramPickleContext.readLength();
/*      */     
/*  478 */     paramARRAY.setLength(i);
/*      */     
/*  480 */     if (paramInt2 == 0) {
/*  481 */       return;
/*      */     }
/*      */     
/*      */ 
/*  485 */     int j = (int)getAccessLength(i, paramLong, paramInt1);
/*  486 */     int k = ArrayDescriptor.getCacheStyle(paramARRAY) == 1 ? 1 : 0;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  491 */     if ((paramLong > 1L) && (j > 0))
/*      */     {
/*  493 */       long l1 = paramARRAY.getLastIndex();
/*      */       long l2;
/*  495 */       if (l1 < paramLong)
/*      */       {
/*  497 */         if (l1 > 0L) {
/*  498 */           paramPickleContext.skipTo(paramARRAY.getLastOffset());
/*      */         } else {
/*  500 */           l1 = 1L;
/*      */         }
/*  502 */         if (k != 0)
/*      */         {
/*  504 */           for (l2 = l1; l2 < paramLong; l2 += 1L)
/*      */           {
/*  506 */             paramARRAY.setIndexOffset(l2, paramPickleContext.offset());
/*  507 */             this.elementType.unpickle81rec(paramPickleContext, 9, null);
/*      */           }
/*      */           
/*      */         }
/*      */         else {
/*  512 */           for (l2 = l1; l2 < paramLong; l2 += 1L) {
/*  513 */             this.elementType.unpickle81rec(paramPickleContext, 9, null);
/*      */           }
/*      */         }
/*  516 */       } else if (l1 > paramLong)
/*      */       {
/*  518 */         l2 = paramARRAY.getOffset(paramLong);
/*      */         
/*  520 */         if (l2 != -1L)
/*      */         {
/*  522 */           paramPickleContext.skipTo(l2);
/*      */         }
/*      */         else {
/*      */           int m;
/*  526 */           if (k != 0)
/*      */           {
/*  528 */             for (m = 1; m < paramLong; m++)
/*      */             {
/*  530 */               paramARRAY.setIndexOffset(m, paramPickleContext.offset());
/*  531 */               this.elementType.unpickle81rec(paramPickleContext, 9, null);
/*      */             }
/*      */             
/*      */           }
/*      */           else {
/*  536 */             for (m = 1; m < paramLong; m++) {
/*  537 */               this.elementType.unpickle81rec(paramPickleContext, 9, null);
/*      */             }
/*      */           }
/*      */         }
/*      */       } else {
/*  542 */         paramPickleContext.skipTo(paramARRAY.getLastOffset());
/*      */       }
/*  544 */       paramARRAY.setLastIndexOffset(paramLong, paramPickleContext.offset());
/*      */     }
/*      */     
/*      */ 
/*  548 */     unpickle81ImgBodyElements(paramPickleContext, paramARRAY, (int)paramLong, j, paramInt2, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void unpickle81ImgBody(PickleContext paramPickleContext, ARRAY paramARRAY, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  560 */     paramPickleContext.readByte();
/*      */     
/*      */ 
/*  563 */     int i = paramPickleContext.readLength();
/*      */     
/*  565 */     paramARRAY.setLength(i);
/*      */     
/*  567 */     if (paramInt == 0) {
/*  568 */       return;
/*      */     }
/*      */     
/*      */ 
/*  572 */     unpickle81ImgBodyElements(paramPickleContext, paramARRAY, 1, i, paramInt, paramMap);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void unpickle81ImgBodyElements(PickleContext paramPickleContext, ARRAY paramARRAY, int paramInt1, int paramInt2, int paramInt3, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  583 */     int i = ArrayDescriptor.getCacheStyle(paramARRAY) == 1 ? 1 : 0;
/*      */     
/*      */     Object localObject;
/*      */     int j;
/*  587 */     switch (paramInt3)
/*      */     {
/*      */ 
/*      */ 
/*      */     case 1: 
/*  592 */       localObject = new Datum[paramInt2];
/*      */       
/*  594 */       if (i != 0)
/*      */       {
/*  596 */         for (j = 0; j < paramInt2; j++)
/*      */         {
/*  598 */           paramARRAY.setIndexOffset(paramInt1 + j, paramPickleContext.offset());
/*      */           
/*  600 */           localObject[j] = ((Datum)this.elementType.unpickle81rec(paramPickleContext, paramInt3, paramMap));
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  606 */         for (j = 0; j < paramInt2; j++) {
/*  607 */           localObject[j] = ((Datum)this.elementType.unpickle81rec(paramPickleContext, paramInt3, paramMap));
/*      */         }
/*      */       }
/*      */       
/*  611 */       paramARRAY.setDatumArray((Datum[])localObject);
/*      */       
/*      */ 
/*  614 */       break;
/*      */     
/*      */ 
/*      */     case 2: 
/*  618 */       localObject = ArrayDescriptor.makeJavaArray(paramInt2, this.elementType.getTypeCode());
/*      */       
/*      */ 
/*  621 */       if (i != 0)
/*      */       {
/*  623 */         for (j = 0; j < paramInt2; j++)
/*      */         {
/*  625 */           paramARRAY.setIndexOffset(paramInt1 + j, paramPickleContext.offset());
/*      */           
/*  627 */           localObject[j] = this.elementType.unpickle81rec(paramPickleContext, paramInt3, paramMap);
/*      */         }
/*      */         
/*      */       }
/*      */       else {
/*  632 */         for (j = 0; j < paramInt2; j++) {
/*  633 */           localObject[j] = this.elementType.unpickle81rec(paramPickleContext, paramInt3, paramMap);
/*      */         }
/*      */       }
/*  636 */       paramARRAY.setObjArray(localObject);
/*      */       
/*      */ 
/*  639 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/*  651 */       if (((this.elementType instanceof OracleTypeNUMBER)) || ((this.elementType instanceof OracleTypeFLOAT)))
/*      */       {
/*      */ 
/*  654 */         paramARRAY.setObjArray(OracleTypeNUMBER.unpickle81NativeArray(paramPickleContext, 1L, paramInt2, paramInt3));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  659 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23, "This feature is limited to numeric collection");
/*  660 */         ((SQLException)localObject).fillInStackTrace();
/*  661 */         throw ((Throwable)localObject);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 3: 
/*      */     default: 
/*  669 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, "Invalid conversion type " + this.elementType);
/*  670 */       ((SQLException)localObject).fillInStackTrace();
/*  671 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  676 */     paramARRAY.setLastIndexOffset(paramInt1 + paramInt2, paramPickleContext.offset());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static final int POSSIBLY_OTHER_USER_OBJECT_10g = 6;
/*      */   
/*      */ 
/*      */   static final int OTHER_USER_OBJECT = 7;
/*      */   
/*      */ 
/*      */   static final int OTHER_USER_SYNONYM = 8;
/*      */   
/*      */ 
/*      */   static final int PUBLIC_SYNONYM = 9;
/*      */   
/*      */ 
/*      */   static final int PUBLIC_SYNONYM_10g = 10;
/*      */   
/*      */ 
/*      */   static final int BREAK = 11;
/*      */   
/*      */ 
/*  699 */   static final String[] sqlString = { "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM USER_COLL_TYPES WHERE TYPE_NAME = :1", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM USER_COLL_TYPES WHERE TYPE_NAME in (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :1 CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :1 FROM DUAL) ", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM USER_COLL_TYPES WHERE TYPE_NAME in (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :1 CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :1 FROM DUAL) ", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM USER_COLL_TYPES WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM ALL_SYNONYMS START WITH SYNONYM_NAME = :1 AND  OWNER = 'PUBLIC' CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER UNION SELECT :2  FROM DUAL) ", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM USER_COLL_TYPES WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM ALL_SYNONYMS START WITH SYNONYM_NAME = :1 AND  OWNER = 'PUBLIC' CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER UNION SELECT :2  FROM DUAL) ", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :tname CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :tname FROM DUAL)", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES WHERE TYPE_NAME IN (SELECT TABLE_NAME FROM USER_SYNONYMS START WITH SYNONYM_NAME = :tname CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME UNION SELECT :tname FROM DUAL)", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES WHERE OWNER = :1 AND TYPE_NAME = :2", "SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES WHERE OWNER = (SELECT TABLE_OWNER FROM ALL_SYNONYMS WHERE SYNONYM_NAME=:1) AND TYPE_NAME = (SELECT TABLE_NAME FROM ALL_SYNONYMS WHERE SYNONYM_NAME=:2) ", "DECLARE   the_owner VARCHAR2(100);   the_type  VARCHAR2(100); begin  SELECT TABLE_NAME, TABLE_OWNER INTO THE_TYPE, THE_OWNER  FROM ALL_SYNONYMS  WHERE TABLE_NAME IN (SELECT TYPE_NAME FROM ALL_TYPES)  START WITH SYNONYM_NAME = :1 AND OWNER = 'PUBLIC'  CONNECT BY PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER; OPEN :2 FOR SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES  WHERE TYPE_NAME = THE_TYPE and OWNER = THE_OWNER; END;", "DECLARE   the_owner VARCHAR2(100);   the_type  VARCHAR2(100); begin  SELECT TABLE_NAME, TABLE_OWNER INTO THE_TYPE, THE_OWNER  FROM ALL_SYNONYMS  WHERE TABLE_NAME IN (SELECT TYPE_NAME FROM ALL_TYPES)  START WITH SYNONYM_NAME = :1 AND OWNER = 'PUBLIC'  CONNECT BY NOCYCLE PRIOR TABLE_NAME = SYNONYM_NAME AND TABLE_OWNER = OWNER; OPEN :2 FOR SELECT ELEM_TYPE_NAME, ELEM_TYPE_OWNER FROM ALL_COLL_TYPES  WHERE TYPE_NAME = THE_TYPE and OWNER = THE_OWNER; END;" };
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
/*      */   private void initCollElemTypeName()
/*      */     throws SQLException
/*      */   {
/*  805 */     if (this.connection == null)
/*  806 */       return;
/*  807 */     synchronized (this.connection) {
/*  808 */       if (this.sqlName == null) {
/*  809 */         getFullName();
/*      */       }
/*  811 */       CallableStatement localCallableStatement = null;
/*  812 */       PreparedStatement localPreparedStatement = null;
/*  813 */       ResultSet localResultSet = null;
/*      */       try {
/*  815 */         int i = this.sqlName.getSchema().equalsIgnoreCase(this.connection.getDefaultSchemaNameForNamedTypes()) ? 0 : 7;
/*      */         
/*      */ 
/*  818 */         while (i != 11)
/*      */         {
/*  820 */           switch (i)
/*      */           {
/*      */ 
/*      */           case 0: 
/*  824 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  825 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/*  826 */             localPreparedStatement.setFetchSize(1);
/*  827 */             localResultSet = localPreparedStatement.executeQuery();
/*  828 */             i = 1;
/*  829 */             break;
/*      */           
/*      */           case 1: 
/*  832 */             if (this.connection.getVersionNumber() >= 10000)
/*      */             {
/*  834 */               i = 2;
/*      */             }
/*      */           
/*      */ 
/*      */           case 2: 
/*  839 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  840 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/*  841 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/*  842 */             localPreparedStatement.setFetchSize(1);
/*  843 */             localResultSet = localPreparedStatement.executeQuery();
/*  844 */             i = 3;
/*  845 */             break;
/*      */           
/*      */           case 3: 
/*  848 */             if (this.connection.getVersionNumber() >= 10000)
/*      */             {
/*  850 */               i = 4;
/*      */             }
/*      */           
/*      */ 
/*      */           case 4: 
/*  855 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  856 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/*  857 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/*  858 */             localPreparedStatement.setFetchSize(1);
/*  859 */             localResultSet = localPreparedStatement.executeQuery();
/*  860 */             i = 5;
/*  861 */             break;
/*      */           
/*      */           case 5: 
/*  864 */             if (this.connection.getVersionNumber() >= 10000)
/*      */             {
/*  866 */               i = 6;
/*      */             }
/*      */           
/*      */ 
/*      */           case 6: 
/*  871 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  872 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/*  873 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/*  874 */             localPreparedStatement.setFetchSize(1);
/*  875 */             localResultSet = localPreparedStatement.executeQuery();
/*  876 */             i = 8;
/*  877 */             break;
/*      */           
/*      */ 
/*      */           case 7: 
/*  881 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  882 */             localPreparedStatement.setString(1, this.sqlName.getSchema());
/*  883 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/*  884 */             localPreparedStatement.setFetchSize(1);
/*  885 */             localResultSet = localPreparedStatement.executeQuery();
/*  886 */             i = 8;
/*  887 */             break;
/*      */           
/*      */ 
/*      */           case 8: 
/*  891 */             localPreparedStatement = this.connection.prepareStatement(getSqlHint() + sqlString[i]);
/*  892 */             localPreparedStatement.setString(1, this.sqlName.getSimpleName());
/*  893 */             localPreparedStatement.setString(2, this.sqlName.getSimpleName());
/*  894 */             localPreparedStatement.setFetchSize(1);
/*  895 */             localResultSet = localPreparedStatement.executeQuery();
/*  896 */             i = 9;
/*  897 */             break;
/*      */           
/*      */           case 9: 
/*  900 */             if (this.connection.getVersionNumber() >= 10000)
/*      */             {
/*  902 */               i = 10;
/*      */             }
/*      */           
/*      */ 
/*      */           case 10: 
/*  907 */             localCallableStatement = this.connection.prepareCall(getSqlHint() + sqlString[i]);
/*  908 */             localCallableStatement.setString(1, this.sqlName.getSimpleName());
/*  909 */             localCallableStatement.registerOutParameter(2, -10);
/*  910 */             localCallableStatement.execute();
/*  911 */             localResultSet = ((OracleCallableStatement)localCallableStatement).getCursor(2);
/*  912 */             i = 11;
/*      */           }
/*      */           
/*      */           
/*  916 */           if (localResultSet.next())
/*      */           {
/*  918 */             if (this.attrTypeNames == null) {
/*  919 */               this.attrTypeNames = new String[1];
/*      */             }
/*  921 */             this.attrTypeNames[0] = (localResultSet.getString(2) + "." + localResultSet.getString(1));
/*  922 */             i = 11;
/*  923 */           } else if (i == 11)
/*      */           {
/*  925 */             SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  926 */             localSQLException.fillInStackTrace();
/*  927 */             throw localSQLException;
/*      */           }
/*      */         }
/*  930 */         while (i != 11) {}
/*      */       }
/*      */       finally {
/*  933 */         if (localResultSet != null)
/*  934 */           localResultSet.close();
/*  935 */         if (localPreparedStatement != null)
/*  936 */           localPreparedStatement.close();
/*  937 */         if (localCallableStatement != null) {
/*  938 */           localCallableStatement.close();
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public String getAttributeName(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  948 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  949 */     localSQLException.fillInStackTrace();
/*  950 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String getAttributeName(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  958 */     return getAttributeName(paramInt);
/*      */   }
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
/*  971 */     if (paramInt != 1)
/*      */     {
/*  973 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  974 */       localSQLException.fillInStackTrace();
/*  975 */       throw localSQLException;
/*      */     }
/*      */     
/*  978 */     if (this.sqlName == null) {
/*  979 */       getFullName();
/*      */     }
/*  981 */     if (this.attrTypeNames == null) {
/*  982 */       initCollElemTypeName();
/*      */     }
/*  984 */     return this.attrTypeNames[0];
/*      */   }
/*      */   
/*      */ 
/*      */   public String getAttributeType(int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  991 */     if (paramBoolean) {
/*  992 */       return getAttributeType(paramInt);
/*      */     }
/*      */     
/*  995 */     if (paramInt != 1)
/*      */     {
/*  997 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68);
/*  998 */       localSQLException.fillInStackTrace();
/*  999 */       throw localSQLException;
/*      */     }
/*      */     
/* 1002 */     if ((this.sqlName != null) && (this.attrTypeNames != null)) {
/* 1003 */       return this.attrTypeNames[0];
/*      */     }
/* 1005 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getNumAttrs()
/*      */     throws SQLException
/*      */   {
/* 1013 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleType getAttrTypeAt(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1020 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */   ArrayDescriptor createArrayDescriptor()
/*      */     throws SQLException
/*      */   {
/* 1027 */     return new ArrayDescriptor(this, this.connection);
/*      */   }
/*      */   
/*      */ 
/*      */   ArrayDescriptor createArrayDescriptorWithItsOwnTree()
/*      */     throws SQLException
/*      */   {
/* 1034 */     if (this.descriptor == null)
/*      */     {
/* 1036 */       if ((this.sqlName == null) && (getFullName(false) == null))
/*      */       {
/* 1038 */         this.descriptor = new ArrayDescriptor(this, this.connection);
/*      */       }
/*      */       else
/*      */       {
/* 1042 */         this.descriptor = ArrayDescriptor.createDescriptor(this.sqlName, this.connection);
/*      */       }
/*      */     }
/*      */     
/* 1046 */     return (ArrayDescriptor)this.descriptor;
/*      */   }
/*      */   
/*      */ 
/*      */   public OracleType getElementType()
/*      */     throws SQLException
/*      */   {
/* 1053 */     return this.elementType;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getUserCode()
/*      */     throws SQLException
/*      */   {
/* 1060 */     return this.userCode;
/*      */   }
/*      */   
/*      */ 
/*      */   public long getMaxLength()
/*      */     throws SQLException
/*      */   {
/* 1067 */     return this.maxSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private long getAccessLength(long paramLong1, long paramLong2, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1075 */     if (paramLong2 > paramLong1) {
/* 1076 */       return 0L;
/*      */     }
/* 1078 */     if (paramInt < 0)
/*      */     {
/* 1080 */       return paramLong1 - paramLong2 + 1L;
/*      */     }
/*      */     
/*      */ 
/* 1084 */     return Math.min(paramLong1 - paramLong2 + 1L, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {
/* 1096 */     paramObjectOutputStream.writeInt(this.userCode);
/* 1097 */     paramObjectOutputStream.writeLong(this.maxSize);
/* 1098 */     paramObjectOutputStream.writeObject(this.elementType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1106 */     this.userCode = paramObjectInputStream.readInt();
/* 1107 */     this.maxSize = paramObjectInputStream.readLong();
/* 1108 */     this.elementType = ((OracleType)paramObjectInputStream.readObject());
/*      */   }
/*      */   
/*      */ 
/*      */   public void setConnection(OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 1115 */     this.connection = paramOracleConnection;
/*      */     
/* 1117 */     this.elementType.setConnection(paramOracleConnection);
/*      */   }
/*      */   
/*      */ 
/*      */   public void initMetadataRecursively()
/*      */     throws SQLException
/*      */   {
/* 1124 */     initMetadata(this.connection);
/* 1125 */     if (this.elementType != null) { this.elementType.initMetadataRecursively();
/*      */     }
/*      */   }
/*      */   
/*      */   public void initChildNamesRecursively(Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1132 */     TypeTreeElement localTypeTreeElement = (TypeTreeElement)paramMap.get(this.sqlName);
/*      */     
/* 1134 */     if (this.elementType != null)
/*      */     {
/* 1136 */       this.elementType.setNames(localTypeTreeElement.getChildSchemaName(0), localTypeTreeElement.getChildTypeName(0));
/* 1137 */       this.elementType.initChildNamesRecursively(paramMap);
/* 1138 */       this.elementType.cacheDescriptor();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void cacheDescriptor()
/*      */     throws SQLException
/*      */   {
/* 1146 */     this.descriptor = ArrayDescriptor.createDescriptor(this);
/*      */   }
/*      */   
/*      */ 
/*      */   public void printXML(PrintWriter paramPrintWriter, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1153 */     printXML(paramPrintWriter, paramInt, false);
/*      */   }
/*      */   
/*      */ 
/*      */   public void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1160 */     for (int i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 1161 */     paramPrintWriter.println("<OracleTypeCOLLECTION sqlName=\"" + this.sqlName + "\" " + ">");
/*      */     
/*      */ 
/* 1164 */     if (this.elementType != null)
/* 1165 */       this.elementType.printXML(paramPrintWriter, paramInt + 1, paramBoolean);
/* 1166 */     for (i = 0; i < paramInt; i++) paramPrintWriter.print("  ");
/* 1167 */     paramPrintWriter.println("</OracleTypeCOLLECTION>");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1172 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/OracleTypeCOLLECTION.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */