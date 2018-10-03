/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Date;
/*      */ import java.sql.SQLData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleData;
/*      */ import oracle.jdbc.OracleDataFactory;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.jdbc.oracore.OracleNamedType;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ import oracle.jdbc.oracore.OracleTypeCOLLECTION;
/*      */ import oracle.jdbc.oracore.OracleTypeOPAQUE;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.ArrayDescriptor;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CHAR;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.CharacterSet;
/*      */ import oracle.sql.CustomDatum;
/*      */ import oracle.sql.CustomDatumFactory;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.JAVA_STRUCT;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.OPAQUE;
/*      */ import oracle.sql.ORAData;
/*      */ import oracle.sql.ORADataFactory;
/*      */ import oracle.sql.OpaqueDescriptor;
/*      */ import oracle.sql.RAW;
/*      */ import oracle.sql.REF;
/*      */ import oracle.sql.ROWID;
/*      */ import oracle.sql.SQLName;
/*      */ import oracle.sql.STRUCT;
/*      */ import oracle.sql.StructDescriptor;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ import oracle.sql.TypeDescriptor;
/*      */ import oracle.sql.converter.CharacterSetMetaData;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SQLUtil
/*      */ {
/*      */   public static Object SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, Class paramClass, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*   87 */     Datum localDatum = makeDatum(paramOracleConnection, paramArrayOfByte, paramInt, paramString, 0);
/*   88 */     Object localObject = SQLToJava(paramOracleConnection, localDatum, paramClass, paramMap);
/*      */     
/*   90 */     return localObject;
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
/*      */   public static CustomDatum SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, CustomDatumFactory paramCustomDatumFactory)
/*      */     throws SQLException
/*      */   {
/*  128 */     Datum localDatum = makeDatum(paramOracleConnection, paramArrayOfByte, paramInt, paramString, 0);
/*  129 */     CustomDatum localCustomDatum = paramCustomDatumFactory.create(localDatum, paramInt);
/*      */     
/*  131 */     return localCustomDatum;
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
/*      */   public static ORAData SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, ORADataFactory paramORADataFactory)
/*      */     throws SQLException
/*      */   {
/*  169 */     Datum localDatum = makeDatum(paramOracleConnection, paramArrayOfByte, paramInt, paramString, 0);
/*  170 */     ORAData localORAData = paramORADataFactory.create(localDatum, paramInt);
/*      */     
/*  172 */     return localORAData;
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
/*      */   public static OracleData SQLToJava(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt, String paramString, OracleDataFactory paramOracleDataFactory)
/*      */     throws SQLException
/*      */   {
/*  210 */     Datum localDatum = makeDatum(paramOracleConnection, paramArrayOfByte, paramInt, paramString, 0);
/*  211 */     OracleData localOracleData = paramOracleDataFactory.create(localDatum.toJdbc(), paramInt);
/*      */     
/*  213 */     return localOracleData;
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
/*      */   public static Object SQLToJava(OracleConnection paramOracleConnection, Datum paramDatum, Class paramClass, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  253 */     Object localObject = null;
/*      */     
/*  255 */     if ((paramDatum instanceof STRUCT))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  261 */       if (paramClass == null)
/*      */       {
/*  263 */         localObject = paramMap != null ? ((STRUCT)paramDatum).toJdbc(paramMap) : paramDatum.toJdbc();
/*      */       }
/*      */       else
/*      */       {
/*  267 */         localObject = paramMap != null ? ((STRUCT)paramDatum).toClass(paramClass, paramMap) : ((STRUCT)paramDatum).toClass(paramClass);
/*      */       }
/*      */       
/*      */     }
/*  271 */     else if (paramClass == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  277 */       localObject = paramDatum.toJdbc();
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  285 */       int i = classNumber(paramClass);
/*      */       
/*  287 */       switch (i)
/*      */       {
/*      */ 
/*      */       case 0: 
/*  291 */         localObject = paramDatum.stringValue();
/*      */         
/*  293 */         break;
/*      */       
/*      */       case 1: 
/*  296 */         localObject = Boolean.valueOf(paramDatum.longValue() != 0L);
/*      */         
/*  298 */         break;
/*      */       
/*      */       case 2: 
/*  301 */         localObject = Integer.valueOf((int)paramDatum.longValue());
/*      */         
/*  303 */         break;
/*      */       
/*      */       case 3: 
/*  306 */         localObject = Long.valueOf(paramDatum.longValue());
/*      */         
/*  308 */         break;
/*      */       
/*      */       case 4: 
/*  311 */         localObject = Float.valueOf(paramDatum.bigDecimalValue().floatValue());
/*      */         
/*  313 */         break;
/*      */       
/*      */       case 5: 
/*  316 */         localObject = Double.valueOf(paramDatum.bigDecimalValue().doubleValue());
/*      */         
/*  318 */         break;
/*      */       
/*      */       case 6: 
/*  321 */         localObject = paramDatum.bigDecimalValue();
/*      */         
/*  323 */         break;
/*      */       
/*      */       case 7: 
/*  326 */         localObject = paramDatum.dateValue();
/*      */         
/*  328 */         break;
/*      */       
/*      */       case 8: 
/*  331 */         localObject = paramDatum.timeValue();
/*      */         
/*  333 */         break;
/*      */       
/*      */       case 9: 
/*  336 */         localObject = paramDatum.timestampValue();
/*      */         
/*  338 */         break;
/*      */       
/*      */ 
/*      */       case -1: 
/*      */       default: 
/*  343 */         localObject = paramDatum.toJdbc();
/*      */         
/*  345 */         if (!paramClass.isInstance(localObject))
/*      */         {
/*      */ 
/*      */ 
/*  349 */           SQLException localSQLException = DatabaseError.createSqlException(null, 59, "invalid data conversion");
/*  350 */           localSQLException.fillInStackTrace();
/*  351 */           throw localSQLException;
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*  359 */     return localObject;
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
/*      */   public static byte[] JavaToSQL(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  397 */     if (paramObject == null)
/*      */     {
/*      */ 
/*  400 */       return null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  408 */     Object localObject = null;
/*      */     
/*  410 */     if ((paramObject instanceof Datum))
/*      */     {
/*  412 */       localObject = (Datum)paramObject;
/*      */     }
/*  414 */     else if ((paramObject instanceof ORAData))
/*      */     {
/*  416 */       localObject = ((ORAData)paramObject).toDatum(paramOracleConnection);
/*      */     }
/*  418 */     else if ((paramObject instanceof CustomDatum))
/*      */     {
/*  420 */       localObject = paramOracleConnection.toDatum((CustomDatum)paramObject);
/*      */     }
/*  422 */     else if ((paramObject instanceof SQLData))
/*      */     {
/*  424 */       localObject = STRUCT.toSTRUCT(paramObject, paramOracleConnection);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  431 */     if (localObject != null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  441 */       if (!checkDatumType((Datum)localObject, paramInt, paramString))
/*      */       {
/*  443 */         localObject = null;
/*      */ 
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/*  456 */       localObject = makeDatum(paramOracleConnection, paramObject, paramInt, paramString);
/*      */     }
/*      */     
/*  459 */     byte[] arrayOfByte = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  464 */     if (localObject != null)
/*      */     {
/*  466 */       if ((localObject instanceof STRUCT)) {
/*  467 */         arrayOfByte = ((STRUCT)localObject).toBytes();
/*  468 */       } else if ((localObject instanceof ARRAY)) {
/*  469 */         arrayOfByte = ((ARRAY)localObject).toBytes();
/*  470 */       } else if ((localObject instanceof OPAQUE)) {
/*  471 */         arrayOfByte = ((OPAQUE)localObject).toBytes();
/*      */       } else {
/*  473 */         arrayOfByte = ((Datum)localObject).shareBytes();
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  479 */       SQLException localSQLException = DatabaseError.createSqlException(null, 1, "attempt to convert a Datum to incompatible SQL type");
/*  480 */       localSQLException.fillInStackTrace();
/*  481 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  485 */     return arrayOfByte;
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
/*      */   public static Datum makeDatum(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt1, String paramString, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  527 */     Object localObject1 = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  542 */     int i = paramOracleConnection.getDbCsId();
/*  543 */     int j = paramOracleConnection.getJdbcCsId();
/*  544 */     int k = CharacterSetMetaData.getRatio(j, i);
/*      */     Object localObject2;
/*  546 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 96: 
/*  555 */       if ((paramInt2 != 0) && (paramInt2 < paramArrayOfByte.length) && (k == 1))
/*      */       {
/*  557 */         localObject1 = new CHAR(paramArrayOfByte, 0, paramInt2, CharacterSet.make(paramOracleConnection.getJdbcCsId()));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  562 */         localObject1 = new CHAR(paramArrayOfByte, CharacterSet.make(paramOracleConnection.getJdbcCsId()));
/*      */       }
/*      */       
/*  565 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 8: 
/*  572 */       localObject1 = new CHAR(paramArrayOfByte, CharacterSet.make(paramOracleConnection.getJdbcCsId()));
/*      */       
/*      */ 
/*  575 */       break;
/*      */     
/*      */ 
/*      */     case 2: 
/*      */     case 6: 
/*  580 */       localObject1 = new NUMBER(paramArrayOfByte);
/*      */       
/*  582 */       break;
/*      */     
/*      */     case 100: 
/*  585 */       localObject1 = new BINARY_FLOAT(paramArrayOfByte);
/*      */       
/*  587 */       break;
/*      */     
/*      */     case 101: 
/*  590 */       localObject1 = new BINARY_DOUBLE(paramArrayOfByte);
/*      */       
/*  592 */       break;
/*      */     
/*      */ 
/*      */     case 23: 
/*      */     case 24: 
/*  597 */       localObject1 = new RAW(paramArrayOfByte);
/*      */       
/*  599 */       break;
/*      */     
/*      */     case 104: 
/*  602 */       localObject1 = new ROWID(paramArrayOfByte);
/*      */       
/*  604 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 102: 
/*  612 */       localObject2 = DatabaseError.createSqlException(null, 1, "need resolution: do we want to handle ResultSet?");
/*  613 */       ((SQLException)localObject2).fillInStackTrace();
/*  614 */       throw ((Throwable)localObject2);
/*      */     
/*      */ 
/*      */     case 12: 
/*  618 */       localObject1 = new DATE(paramArrayOfByte);
/*      */       
/*  620 */       break;
/*      */     
/*      */     case 182: 
/*  623 */       localObject1 = new INTERVALYM(paramArrayOfByte);
/*      */       
/*  625 */       break;
/*      */     
/*      */     case 183: 
/*  628 */       localObject1 = new INTERVALDS(paramArrayOfByte);
/*      */       
/*  630 */       break;
/*      */     
/*      */     case 180: 
/*  633 */       localObject1 = new TIMESTAMP(paramArrayOfByte);
/*      */       
/*  635 */       break;
/*      */     
/*      */     case 181: 
/*  638 */       localObject1 = new TIMESTAMPTZ(paramArrayOfByte);
/*      */       
/*  640 */       break;
/*      */     
/*      */     case 231: 
/*  643 */       localObject1 = new TIMESTAMPLTZ(paramArrayOfByte);
/*      */       
/*  645 */       break;
/*      */     
/*      */     case 113: 
/*  648 */       localObject1 = paramOracleConnection.createBlob(paramArrayOfByte);
/*      */       
/*  650 */       break;
/*      */     
/*      */     case 112: 
/*  653 */       localObject1 = paramOracleConnection.createClob(paramArrayOfByte);
/*      */       
/*  655 */       break;
/*      */     
/*      */     case 114: 
/*  658 */       localObject1 = paramOracleConnection.createBfile(paramArrayOfByte);
/*      */       
/*  660 */       break;
/*      */     
/*      */ 
/*      */     case 109: 
/*  664 */       localObject2 = TypeDescriptor.getTypeDescriptor(paramString, paramOracleConnection, paramArrayOfByte, 0L);
/*      */       
/*      */ 
/*  667 */       switch (((TypeDescriptor)localObject2).getTypeCode())
/*      */       {
/*      */       case 2002: 
/*  670 */         localObject1 = new STRUCT((StructDescriptor)localObject2, paramArrayOfByte, paramOracleConnection);
/*      */         
/*  672 */         break;
/*      */       
/*      */       case 2008: 
/*  675 */         localObject1 = new JAVA_STRUCT((StructDescriptor)localObject2, paramArrayOfByte, paramOracleConnection);
/*      */         
/*  677 */         break;
/*      */       
/*      */       case 2003: 
/*  680 */         localObject1 = new ARRAY((ArrayDescriptor)localObject2, paramArrayOfByte, paramOracleConnection);
/*      */         
/*  682 */         break;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2009: 
/*  688 */         localObject1 = ClassRef.XMLTYPE.createXML(new OPAQUE((OpaqueDescriptor)localObject2, paramArrayOfByte, paramOracleConnection));
/*      */         
/*  690 */         break;
/*      */       
/*      */ 
/*      */       case 2007: 
/*  694 */         localObject1 = new OPAQUE((OpaqueDescriptor)localObject2, paramArrayOfByte, paramOracleConnection);
/*      */       }
/*      */       
/*      */       
/*      */ 
/*      */ 
/*  700 */       break;
/*      */     
/*      */ 
/*      */     case 111: 
/*  704 */       localObject2 = getTypeDescriptor(paramString, paramOracleConnection);
/*      */       
/*  706 */       if ((localObject2 instanceof StructDescriptor))
/*      */       {
/*  708 */         localObject1 = new REF((StructDescriptor)localObject2, paramOracleConnection, paramArrayOfByte);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  714 */         SQLException localSQLException = DatabaseError.createSqlException(null, 1, "program error: REF points to a non-STRUCT");
/*  715 */         localSQLException.fillInStackTrace();
/*  716 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  721 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/*  726 */       localObject2 = DatabaseError.createSqlException(null, 1, "program error: invalid SQL type code");
/*  727 */       ((SQLException)localObject2).fillInStackTrace();
/*  728 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  733 */     return (Datum)localObject1;
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
/*      */   public static Datum makeNDatum(OracleConnection paramOracleConnection, byte[] paramArrayOfByte, int paramInt1, String paramString, short paramShort, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  749 */     Object localObject = null;
/*      */     
/*  751 */     switch (paramInt1)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 96: 
/*  759 */       int i = paramInt2 * CharacterSetMetaData.getRatio(paramOracleConnection.getNCharSet(), 1);
/*      */       
/*      */ 
/*      */ 
/*  763 */       if ((paramInt2 != 0) && (i < paramArrayOfByte.length)) {
/*  764 */         localObject = new CHAR(paramArrayOfByte, 0, paramInt2, CharacterSet.make(paramOracleConnection.getNCharSet()));
/*      */       }
/*      */       else {
/*  767 */         localObject = new CHAR(paramArrayOfByte, CharacterSet.make(paramOracleConnection.getNCharSet()));
/*      */       }
/*      */       
/*  770 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 8: 
/*  777 */       localObject = new CHAR(paramArrayOfByte, CharacterSet.make(paramOracleConnection.getNCharSet()));
/*      */       
/*      */ 
/*  780 */       break;
/*      */     
/*      */     case 112: 
/*  783 */       localObject = paramOracleConnection.createClob(paramArrayOfByte, paramShort);
/*      */       
/*  785 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/*  790 */       SQLException localSQLException = DatabaseError.createSqlException(null, 1, "program error: invalid SQL type code");
/*  791 */       localSQLException.fillInStackTrace();
/*  792 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*  797 */     return (Datum)localObject;
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
/*      */   public static Datum makeDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/*  831 */     return makeDatum(paramOracleConnection, paramObject, paramInt, paramString, false);
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
/*      */   public static Datum makeDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  845 */     Object localObject1 = null;
/*      */     Object localObject2;
/*  847 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 8: 
/*      */     case 96: 
/*  857 */       localObject1 = new CHAR(paramObject, CharacterSet.make(paramBoolean ? paramOracleConnection.getNCharSet() : paramOracleConnection.getJdbcCsId()));
/*      */       
/*      */ 
/*  860 */       break;
/*      */     
/*      */ 
/*      */     case 2: 
/*      */     case 6: 
/*  865 */       localObject1 = new NUMBER(paramObject);
/*      */       
/*  867 */       break;
/*      */     
/*      */     case 100: 
/*  870 */       if ((paramObject instanceof String)) {
/*  871 */         localObject1 = new BINARY_FLOAT((String)paramObject);
/*  872 */       } else if ((paramObject instanceof Boolean)) {
/*  873 */         localObject1 = new BINARY_FLOAT((Boolean)paramObject);
/*      */       } else {
/*  875 */         localObject1 = new BINARY_FLOAT((Float)paramObject);
/*      */       }
/*  877 */       break;
/*      */     
/*      */     case 101: 
/*  880 */       if ((paramObject instanceof String)) {
/*  881 */         localObject1 = new BINARY_DOUBLE((String)paramObject);
/*  882 */       } else if ((paramObject instanceof Boolean)) {
/*  883 */         localObject1 = new BINARY_DOUBLE((Boolean)paramObject);
/*      */       } else {
/*  885 */         localObject1 = new BINARY_DOUBLE((Double)paramObject);
/*      */       }
/*  887 */       break;
/*      */     
/*      */ 
/*      */     case 23: 
/*      */     case 24: 
/*  892 */       localObject1 = new RAW(paramObject);
/*      */       
/*  894 */       break;
/*      */     
/*      */     case 104: 
/*  897 */       if ((paramObject instanceof String)) {
/*  898 */         localObject1 = new ROWID((String)paramObject);
/*  899 */       } else if ((paramObject instanceof byte[])) {
/*  900 */         localObject1 = new ROWID((byte[])paramObject);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 102: 
/*  910 */       localObject2 = DatabaseError.createSqlException(null, 1, "need resolution: do we want to handle ResultSet");
/*  911 */       ((SQLException)localObject2).fillInStackTrace();
/*  912 */       throw ((Throwable)localObject2);
/*      */     
/*      */ 
/*      */     case 12: 
/*  916 */       localObject1 = new DATE(paramObject);
/*      */       
/*  918 */       break;
/*      */     
/*      */     case 180: 
/*  921 */       if ((paramObject instanceof TIMESTAMP))
/*      */       {
/*  923 */         localObject1 = (Datum)paramObject;
/*      */       }
/*  925 */       else if ((paramObject instanceof Timestamp)) {
/*  926 */         localObject1 = new TIMESTAMP((Timestamp)paramObject);
/*  927 */       } else if ((paramObject instanceof Date)) {
/*  928 */         localObject1 = new TIMESTAMP((Date)paramObject);
/*  929 */       } else if ((paramObject instanceof Time)) {
/*  930 */         localObject1 = new TIMESTAMP((Time)paramObject);
/*  931 */       } else if ((paramObject instanceof DATE)) {
/*  932 */         localObject1 = new TIMESTAMP((DATE)paramObject);
/*  933 */       } else if ((paramObject instanceof String)) {
/*  934 */         localObject1 = new TIMESTAMP((String)paramObject);
/*  935 */       } else if ((paramObject instanceof byte[])) {
/*  936 */         localObject1 = new TIMESTAMP((byte[])paramObject);
/*      */       }
/*      */       
/*      */       break;
/*      */     case 181: 
/*  941 */       if ((paramObject instanceof TIMESTAMPTZ))
/*      */       {
/*  943 */         localObject1 = (Datum)paramObject;
/*      */       }
/*  945 */       else if ((paramObject instanceof Timestamp)) {
/*  946 */         localObject1 = new TIMESTAMPTZ(paramOracleConnection, (Timestamp)paramObject);
/*  947 */       } else if ((paramObject instanceof Date)) {
/*  948 */         localObject1 = new TIMESTAMPTZ(paramOracleConnection, (Date)paramObject);
/*  949 */       } else if ((paramObject instanceof Time)) {
/*  950 */         localObject1 = new TIMESTAMPTZ(paramOracleConnection, (Time)paramObject);
/*  951 */       } else if ((paramObject instanceof DATE)) {
/*  952 */         localObject1 = new TIMESTAMPTZ(paramOracleConnection, (DATE)paramObject);
/*  953 */       } else if ((paramObject instanceof String)) {
/*  954 */         localObject1 = new TIMESTAMPTZ(paramOracleConnection, (String)paramObject);
/*  955 */       } else if ((paramObject instanceof byte[])) {
/*  956 */         localObject1 = new TIMESTAMPTZ((byte[])paramObject);
/*      */       }
/*      */       
/*      */       break;
/*      */     case 231: 
/*  961 */       if ((paramObject instanceof TIMESTAMPLTZ))
/*      */       {
/*  963 */         localObject1 = (Datum)paramObject;
/*      */       }
/*  965 */       else if ((paramObject instanceof Timestamp)) {
/*  966 */         localObject1 = new TIMESTAMPLTZ(paramOracleConnection, (Timestamp)paramObject);
/*  967 */       } else if ((paramObject instanceof Date)) {
/*  968 */         localObject1 = new TIMESTAMPLTZ(paramOracleConnection, (Date)paramObject);
/*  969 */       } else if ((paramObject instanceof Time)) {
/*  970 */         localObject1 = new TIMESTAMPLTZ(paramOracleConnection, (Time)paramObject);
/*  971 */       } else if ((paramObject instanceof DATE)) {
/*  972 */         localObject1 = new TIMESTAMPLTZ(paramOracleConnection, (DATE)paramObject);
/*  973 */       } else if ((paramObject instanceof String)) {
/*  974 */         localObject1 = new TIMESTAMPLTZ(paramOracleConnection, (String)paramObject);
/*  975 */       } else if ((paramObject instanceof byte[])) {
/*  976 */         localObject1 = new TIMESTAMPLTZ((byte[])paramObject);
/*      */       }
/*      */       
/*      */       break;
/*      */     case 113: 
/*  981 */       if ((paramObject instanceof BLOB))
/*      */       {
/*  983 */         localObject1 = (Datum)paramObject;
/*      */       }
/*  985 */       if ((paramObject instanceof byte[]))
/*      */       {
/*  987 */         localObject1 = new RAW((byte[])paramObject);
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     case 112: 
/*  993 */       if ((paramObject instanceof CLOB))
/*      */       {
/*  995 */         localObject1 = (Datum)paramObject;
/*      */       }
/*      */       
/*  998 */       if ((paramObject instanceof String))
/*      */       {
/* 1000 */         localObject2 = CharacterSet.make(paramBoolean ? paramOracleConnection.getNCharSet() : paramOracleConnection.getJdbcCsId());
/* 1001 */         localObject1 = new CHAR((String)paramObject, (CharacterSet)localObject2); }
/* 1002 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 114: 
/* 1007 */       if ((paramObject instanceof BFILE))
/*      */       {
/* 1009 */         localObject1 = (Datum)paramObject;
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     case 109: 
/* 1015 */       if (((paramObject instanceof STRUCT)) || ((paramObject instanceof ARRAY)) || ((paramObject instanceof OPAQUE)))
/*      */       {
/*      */ 
/* 1018 */         localObject1 = (Datum)paramObject;
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     case 111: 
/* 1024 */       if ((paramObject instanceof REF))
/*      */       {
/* 1026 */         localObject1 = (Datum)paramObject;
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1035 */     if (localObject1 == null)
/*      */     {
/*      */ 
/*      */ 
/* 1039 */       localObject2 = DatabaseError.createSqlException(null, 1, "Unable to construct a Datum from the specified input");
/* 1040 */       ((SQLException)localObject2).fillInStackTrace();
/* 1041 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */ 
/* 1045 */     return (Datum)localObject1;
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
/*      */   private static int classNumber(Class paramClass)
/*      */   {
/* 1062 */     int i = -1;
/* 1063 */     Integer localInteger = (Integer)classTable.get(paramClass);
/*      */     
/* 1065 */     if (localInteger != null)
/*      */     {
/* 1067 */       i = localInteger.intValue();
/*      */     }
/*      */     
/* 1070 */     return i;
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
/*      */   public static Object getTypeDescriptor(String paramString, OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 1104 */     Object localObject = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1109 */     SQLName localSQLName = new SQLName(paramString, paramOracleConnection);
/* 1110 */     String str = localSQLName.getName();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1115 */     localObject = paramOracleConnection.getDescriptor(str);
/*      */     
/* 1117 */     if (localObject != null)
/*      */     {
/*      */ 
/* 1120 */       return localObject;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1126 */     OracleTypeADT localOracleTypeADT = new OracleTypeADT(str, paramOracleConnection);
/* 1127 */     localOracleTypeADT.init(paramOracleConnection);
/*      */     
/* 1129 */     OracleNamedType localOracleNamedType = localOracleTypeADT.cleanup();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1134 */     switch (localOracleNamedType.getTypeCode())
/*      */     {
/*      */ 
/*      */ 
/*      */     case 2003: 
/* 1139 */       localObject = new ArrayDescriptor(localSQLName, (OracleTypeCOLLECTION)localOracleNamedType, paramOracleConnection);
/*      */       
/*      */ 
/*      */ 
/* 1143 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 2002: 
/*      */     case 2008: 
/* 1149 */       localObject = new StructDescriptor(localSQLName, (OracleTypeADT)localOracleNamedType, paramOracleConnection);
/*      */       
/*      */ 
/*      */ 
/* 1153 */       break;
/*      */     
/*      */ 
/*      */     case 2007: 
/* 1157 */       localObject = new OpaqueDescriptor(localSQLName, (OracleTypeOPAQUE)localOracleNamedType, paramOracleConnection);
/*      */       
/*      */ 
/*      */ 
/* 1161 */       break;
/*      */     case 2004: 
/*      */     case 2005: 
/*      */     case 2006: 
/*      */     default: 
/* 1166 */       SQLException localSQLException = DatabaseError.createSqlException(null, 1, "Unrecognized type code");
/* 1167 */       localSQLException.fillInStackTrace();
/* 1168 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1175 */     paramOracleConnection.putDescriptor(str, localObject);
/*      */     
/* 1177 */     return localObject;
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
/*      */   public static boolean checkDatumType(Datum paramDatum, int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1207 */     boolean bool = false;
/*      */     
/* 1209 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 8: 
/*      */     case 96: 
/* 1217 */       bool = paramDatum instanceof CHAR;
/*      */       
/* 1219 */       break;
/*      */     
/*      */ 
/*      */     case 2: 
/*      */     case 6: 
/* 1224 */       bool = paramDatum instanceof NUMBER;
/*      */       
/* 1226 */       break;
/*      */     
/*      */     case 100: 
/* 1229 */       bool = paramDatum instanceof BINARY_FLOAT;
/*      */       
/* 1231 */       break;
/*      */     
/*      */     case 101: 
/* 1234 */       bool = paramDatum instanceof BINARY_DOUBLE;
/*      */       
/* 1236 */       break;
/*      */     
/*      */ 
/*      */     case 23: 
/*      */     case 24: 
/* 1241 */       bool = paramDatum instanceof RAW;
/*      */       
/* 1243 */       break;
/*      */     
/*      */     case 104: 
/* 1246 */       bool = paramDatum instanceof ROWID;
/*      */       
/* 1248 */       break;
/*      */     
/*      */     case 12: 
/* 1251 */       bool = paramDatum instanceof DATE;
/*      */       
/* 1253 */       break;
/*      */     
/*      */     case 180: 
/* 1256 */       bool = paramDatum instanceof TIMESTAMP;
/*      */       
/* 1258 */       break;
/*      */     
/*      */     case 181: 
/* 1261 */       bool = paramDatum instanceof TIMESTAMPTZ;
/*      */       
/* 1263 */       break;
/*      */     
/*      */     case 231: 
/* 1266 */       bool = paramDatum instanceof TIMESTAMPLTZ;
/*      */       
/* 1268 */       break;
/*      */     
/*      */     case 113: 
/* 1271 */       bool = paramDatum instanceof BLOB;
/*      */       
/* 1273 */       break;
/*      */     
/*      */     case 112: 
/* 1276 */       bool = paramDatum instanceof CLOB;
/*      */       
/* 1278 */       break;
/*      */     
/*      */     case 114: 
/* 1281 */       bool = paramDatum instanceof BFILE;
/*      */       
/* 1283 */       break;
/*      */     
/*      */     case 111: 
/* 1286 */       bool = ((paramDatum instanceof REF)) && (((REF)paramDatum).getBaseTypeName().equals(paramString));
/*      */       
/*      */ 
/* 1289 */       break;
/*      */     
/*      */     case 109: 
/* 1292 */       if ((paramDatum instanceof STRUCT))
/*      */       {
/* 1294 */         bool = ((STRUCT)paramDatum).isInHierarchyOf(paramString);
/*      */       }
/* 1296 */       else if ((paramDatum instanceof ARRAY))
/*      */       {
/* 1298 */         bool = ((ARRAY)paramDatum).getSQLTypeName().equals(paramString);
/*      */       }
/* 1300 */       else if ((paramDatum instanceof OPAQUE))
/*      */       {
/* 1302 */         bool = ((OPAQUE)paramDatum).getSQLTypeName().equals(paramString);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */       break;
/*      */     case 102: 
/*      */     default: 
/* 1310 */       bool = false;
/*      */     }
/*      */     
/* 1313 */     return bool;
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
/*      */   public static boolean implementsInterface(Class paramClass1, Class paramClass2)
/*      */   {
/* 1333 */     if (paramClass1 == null)
/*      */     {
/* 1335 */       return false;
/*      */     }
/*      */     
/* 1338 */     if (paramClass1 == paramClass2)
/*      */     {
/* 1340 */       return true;
/*      */     }
/*      */     
/* 1343 */     Class[] arrayOfClass = paramClass1.getInterfaces();
/*      */     
/* 1345 */     for (int i = 0; i < arrayOfClass.length; i++)
/*      */     {
/* 1347 */       if (implementsInterface(arrayOfClass[i], paramClass2))
/*      */       {
/* 1349 */         return true;
/*      */       }
/*      */     }
/*      */     
/* 1353 */     return implementsInterface(paramClass1.getSuperclass(), paramClass2);
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
/*      */   public static Datum makeOracleDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString)
/*      */     throws SQLException
/*      */   {
/* 1384 */     return makeOracleDatum(paramOracleConnection, paramObject, paramInt, paramString, false);
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
/*      */   public static Datum makeOracleDatum(OracleConnection paramOracleConnection, Object paramObject, int paramInt, String paramString, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1399 */     Datum localDatum = makeDatum(paramOracleConnection, paramObject, getInternalType(paramInt), paramString, paramBoolean);
/*      */     
/*      */ 
/* 1402 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static int getInternalType(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1410 */     int i = 0;
/*      */     
/* 1412 */     switch (paramInt)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -7: 
/*      */     case -6: 
/*      */     case -5: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/* 1434 */       i = 6;
/*      */       
/* 1436 */       break;
/*      */     
/*      */     case 100: 
/* 1439 */       i = 100;
/*      */       
/* 1441 */       break;
/*      */     
/*      */     case 101: 
/* 1444 */       i = 101;
/*      */       
/* 1446 */       break;
/*      */     
/*      */     case 999: 
/* 1449 */       i = 999;
/*      */       
/* 1451 */       break;
/*      */     
/*      */     case 1: 
/* 1454 */       i = 96;
/*      */       
/* 1456 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case -15: 
/* 1462 */       i = 96;
/* 1463 */       break;
/*      */     
/*      */ 
/*      */     case 12: 
/* 1467 */       i = 1;
/*      */       
/* 1469 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case -9: 
/* 1475 */       i = 1;
/* 1476 */       break;
/*      */     
/*      */ 
/*      */     case -1: 
/* 1480 */       i = 8;
/*      */       
/* 1482 */       break;
/*      */     
/*      */ 
/*      */     case 91: 
/*      */     case 92: 
/* 1487 */       i = 12;
/*      */       
/* 1489 */       break;
/*      */     
/*      */ 
/*      */     case -100: 
/*      */     case 93: 
/* 1494 */       i = 180;
/*      */       
/* 1496 */       break;
/*      */     
/*      */     case -101: 
/* 1499 */       i = 181;
/*      */       
/* 1501 */       break;
/*      */     
/*      */     case -102: 
/* 1504 */       i = 231;
/*      */       
/* 1506 */       break;
/*      */     
/*      */     case -104: 
/* 1509 */       i = 183;
/*      */       
/* 1511 */       break;
/*      */     
/*      */     case -103: 
/* 1514 */       i = 182;
/*      */       
/* 1516 */       break;
/*      */     
/*      */ 
/*      */     case -3: 
/*      */     case -2: 
/* 1521 */       i = 23;
/*      */       
/* 1523 */       break;
/*      */     
/*      */     case -4: 
/* 1526 */       i = 24;
/*      */       
/* 1528 */       break;
/*      */     
/*      */     case -8: 
/* 1531 */       i = 104;
/*      */       
/* 1533 */       break;
/*      */     
/*      */     case 2004: 
/* 1536 */       i = 113;
/*      */       
/* 1538 */       break;
/*      */     
/*      */     case 2005: 
/* 1541 */       i = 112;
/*      */       
/* 1543 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2011: 
/* 1549 */       i = 112;
/*      */       
/* 1551 */       break;
/*      */     
/*      */     case -13: 
/* 1554 */       i = 114;
/*      */       
/* 1556 */       break;
/*      */     
/*      */     case -10: 
/* 1559 */       i = 102;
/*      */       
/* 1561 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2002: 
/*      */     case 2003: 
/*      */     case 2007: 
/*      */     case 2008: 
/* 1570 */       i = 109;
/*      */       
/* 1572 */       break;
/*      */     
/*      */     case 2006: 
/* 1575 */       i = 111;
/*      */       
/* 1577 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/* 1582 */       SQLException localSQLException = DatabaseError.createSqlException(null, 4, "get_internal_type");
/* 1583 */       localSQLException.fillInStackTrace();
/* 1584 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/* 1588 */     return i;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1603 */     return null;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1783 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   private static final int CLASS_NOT_FOUND = -1;
/*      */   
/*      */   private static final int CLASS_STRING = 0;
/*      */   
/*      */   private static final int CLASS_BOOLEAN = 1;
/*      */   
/*      */   private static final int CLASS_INTEGER = 2;
/*      */   
/*      */   private static final int CLASS_LONG = 3;
/*      */   
/*      */   private static final int CLASS_FLOAT = 4;
/*      */   
/*      */   private static final int CLASS_DOUBLE = 5;
/*      */   private static final int CLASS_BIGDECIMAL = 6;
/*      */   private static final int CLASS_DATE = 7;
/*      */   private static final int CLASS_TIME = 8;
/*      */   private static final int CLASS_TIMESTAMP = 9;
/*      */   private static final int TOTAL_CLASSES = 10;
/* 1805 */   private static Hashtable classTable = new Hashtable(10);
/*      */   
/*      */   static {
/*      */     try {
/* 1809 */       classTable.put(Class.forName("java.lang.String"), Integer.valueOf(0));
/*      */       
/* 1811 */       classTable.put(Class.forName("java.lang.Boolean"), Integer.valueOf(1));
/*      */       
/* 1813 */       classTable.put(Class.forName("java.lang.Integer"), Integer.valueOf(2));
/*      */       
/* 1815 */       classTable.put(Class.forName("java.lang.Long"), Integer.valueOf(3));
/* 1816 */       classTable.put(Class.forName("java.lang.Float"), Integer.valueOf(4));
/*      */       
/* 1818 */       classTable.put(Class.forName("java.lang.Double"), Integer.valueOf(5));
/*      */       
/* 1820 */       classTable.put(Class.forName("java.math.BigDecimal"), Integer.valueOf(6));
/*      */       
/* 1822 */       classTable.put(Class.forName("java.sql.Date"), Integer.valueOf(7));
/* 1823 */       classTable.put(Class.forName("java.sql.Time"), Integer.valueOf(8));
/* 1824 */       classTable.put(Class.forName("java.sql.Timestamp"), Integer.valueOf(9));
/*      */     }
/*      */     catch (ClassNotFoundException localClassNotFoundException) {}
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/SQLUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */