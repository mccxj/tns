/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OraclePreparedStatement;
/*      */ import oracle.jdbc.OracleResultSet;
/*      */ import oracle.jdbc.OracleTypeMetaData.Array;
/*      */ import oracle.jdbc.OracleTypeMetaData.ArrayStorage;
/*      */ import oracle.jdbc.OracleTypeMetaData.Kind;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.oracore.OracleNamedType;
/*      */ import oracle.jdbc.oracore.OracleType;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ import oracle.jdbc.oracore.OracleTypeCOLLECTION;
/*      */ import oracle.jdbc.oracore.OracleTypeFLOAT;
/*      */ import oracle.jdbc.oracore.OracleTypeNUMBER;
/*      */ import oracle.jdbc.oracore.OracleTypeREF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ArrayDescriptor
/*      */   extends TypeDescriptor
/*      */   implements OracleTypeMetaData.Array, Serializable
/*      */ {
/*      */   public static final int TYPE_VARRAY = 3;
/*      */   public static final int TYPE_NESTED_TABLE = 2;
/*      */   public static final int CACHE_NONE = 0;
/*      */   public static final int CACHE_ALL = 1;
/*      */   public static final int CACHE_LAST = 2;
/*      */   static final long serialVersionUID = 3838105394346513809L;
/*      */   
/*      */   public static ArrayDescriptor createDescriptor(String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*   73 */     return createDescriptor(paramString, paramConnection, false, false);
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
/*      */   public static ArrayDescriptor createDescriptor(String paramString, Connection paramConnection, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*   94 */     if ((paramString == null) || (paramString.length() == 0) || (paramConnection == null))
/*      */     {
/*      */ 
/*   97 */       localObject = DatabaseError.createSqlException(null, 60, "ArrayDescriptor.createDescriptor: Invalid argument,'name' should not be an empty string and 'conn' should not be null.");
/*      */       
/*   99 */       ((SQLException)localObject).fillInStackTrace();
/*  100 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  105 */     Object localObject = new SQLName(paramString, (oracle.jdbc.OracleConnection)paramConnection);
/*  106 */     return createDescriptor((SQLName)localObject, paramConnection);
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
/*      */   public static ArrayDescriptor createDescriptor(SQLName paramSQLName, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  123 */     return createDescriptor(paramSQLName, paramConnection, false, false);
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
/*      */   public static ArrayDescriptor createDescriptor(SQLName paramSQLName, Connection paramConnection, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*  144 */     String str = paramSQLName.getName();
/*      */     
/*      */ 
/*  147 */     ArrayDescriptor localArrayDescriptor = null;
/*  148 */     if (!paramBoolean2) {
/*  149 */       localArrayDescriptor = (ArrayDescriptor)((oracle.jdbc.OracleConnection)paramConnection).getDescriptor(str);
/*      */     }
/*      */     
/*  152 */     if (localArrayDescriptor == null)
/*      */     {
/*  154 */       localArrayDescriptor = new ArrayDescriptor(paramSQLName, paramConnection);
/*  155 */       if (paramBoolean1) localArrayDescriptor.initNamesRecursively();
/*  156 */       ((oracle.jdbc.OracleConnection)paramConnection).putDescriptor(str, localArrayDescriptor);
/*      */     }
/*  158 */     return localArrayDescriptor;
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
/*      */   public static ArrayDescriptor createDescriptor(OracleTypeCOLLECTION paramOracleTypeCOLLECTION)
/*      */     throws SQLException
/*      */   {
/*  173 */     String str = paramOracleTypeCOLLECTION.getFullName();
/*  174 */     oracle.jdbc.internal.OracleConnection localOracleConnection = paramOracleTypeCOLLECTION.getConnection();
/*  175 */     ArrayDescriptor localArrayDescriptor = (ArrayDescriptor)localOracleConnection.getDescriptor(str);
/*      */     
/*      */ 
/*      */ 
/*  179 */     if (localArrayDescriptor == null)
/*      */     {
/*  181 */       SQLName localSQLName = new SQLName(paramOracleTypeCOLLECTION.getSchemaName(), paramOracleTypeCOLLECTION.getSimpleName(), paramOracleTypeCOLLECTION.getConnection());
/*      */       
/*  183 */       localArrayDescriptor = new ArrayDescriptor(localSQLName, paramOracleTypeCOLLECTION, localOracleConnection);
/*  184 */       localOracleConnection.putDescriptor(str, localArrayDescriptor);
/*      */     }
/*  186 */     return localArrayDescriptor;
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
/*      */   public static ArrayDescriptor createDescriptor(SQLName paramSQLName, byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, oracle.jdbc.internal.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  201 */     OracleTypeADT localOracleTypeADT = new OracleTypeADT(paramSQLName, paramArrayOfByte1, paramInt, paramArrayOfByte2, paramOracleConnection);
/*      */     
/*  203 */     localOracleTypeADT.init(paramArrayOfByte2, paramOracleConnection);
/*  204 */     OracleTypeCOLLECTION localOracleTypeCOLLECTION = (OracleTypeCOLLECTION)localOracleTypeADT.cleanup();
/*  205 */     return new ArrayDescriptor(paramSQLName, localOracleTypeCOLLECTION, paramOracleConnection);
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
/*      */   public ArrayDescriptor(String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  224 */     super((short)122, paramString, paramConnection);
/*      */     
/*  226 */     initPickler();
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
/*      */   public ArrayDescriptor(SQLName paramSQLName, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  241 */     super((short)122, paramSQLName, paramConnection);
/*      */     
/*  243 */     initPickler();
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
/*      */   public ArrayDescriptor(SQLName paramSQLName, OracleTypeCOLLECTION paramOracleTypeCOLLECTION, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  257 */     super((short)122, paramSQLName, paramOracleTypeCOLLECTION, paramConnection);
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
/*      */   public ArrayDescriptor(OracleTypeCOLLECTION paramOracleTypeCOLLECTION, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  272 */     super((short)122, paramOracleTypeCOLLECTION, paramConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   ArrayDescriptor(byte[] paramArrayOfByte, int paramInt, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  284 */     super((short)122);
/*      */     
/*  286 */     this.toid = paramArrayOfByte;
/*  287 */     this.toidVersion = paramInt;
/*  288 */     setPhysicalConnectionOf(paramConnection);
/*  289 */     initPickler();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeMetaData.Kind getKind()
/*      */   {
/*  300 */     return OracleTypeMetaData.Kind.ARRAY;
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
/*      */   public int getBaseType()
/*      */     throws SQLException
/*      */   {
/*  317 */     return ((OracleTypeCOLLECTION)this.pickler).getElementType().getTypeCode();
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
/*      */   public String getBaseName()
/*      */     throws SQLException
/*      */   {
/*  331 */     String str = null;
/*      */     OracleNamedType localOracleNamedType;
/*  333 */     switch (getBaseType())
/*      */     {
/*      */ 
/*      */     case 12: 
/*  337 */       str = "VARCHAR";
/*      */       
/*  339 */       break;
/*      */     
/*      */     case 1: 
/*  342 */       str = "CHAR";
/*      */       
/*  344 */       break;
/*      */     
/*      */     case -2: 
/*  347 */       str = "RAW";
/*      */       
/*  349 */       break;
/*      */     
/*      */     case 6: 
/*  352 */       str = "FLOAT";
/*      */       
/*  354 */       break;
/*      */     
/*      */     case 2: 
/*  357 */       str = "NUMBER";
/*      */       
/*  359 */       break;
/*      */     
/*      */     case 8: 
/*  362 */       str = "DOUBLE";
/*      */       
/*  364 */       break;
/*      */     
/*      */     case 3: 
/*  367 */       str = "DECIMAL";
/*      */       
/*  369 */       break;
/*      */     
/*      */     case 91: 
/*  372 */       str = "DATE";
/*      */       
/*  374 */       break;
/*      */     
/*      */     case 93: 
/*  377 */       str = "TIMESTAMP";
/*      */       
/*  379 */       break;
/*      */     
/*      */     case -101: 
/*  382 */       str = "TIMESTAMP WITH TIME ZONE";
/*      */       
/*  384 */       break;
/*      */     
/*      */     case -102: 
/*  387 */       str = "TIMESTAMP WITH LOCAL TIME ZONE";
/*      */       
/*  389 */       break;
/*      */     
/*      */     case 2004: 
/*  392 */       str = "BLOB";
/*      */       
/*  394 */       break;
/*      */     
/*      */     case 2005: 
/*  397 */       str = "CLOB";
/*      */       
/*  399 */       break;
/*      */     
/*      */     case -13: 
/*  402 */       str = "BFILE";
/*      */       
/*  404 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2002: 
/*      */     case 2003: 
/*      */     case 2007: 
/*      */     case 2008: 
/*  414 */       localOracleNamedType = (OracleNamedType)((OracleTypeCOLLECTION)this.pickler).getElementType();
/*      */       
/*      */ 
/*  417 */       str = localOracleNamedType.getFullName();
/*      */       
/*  419 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 2006: 
/*  424 */       localOracleNamedType = (OracleNamedType)((OracleTypeCOLLECTION)this.pickler).getElementType();
/*      */       
/*      */ 
/*  427 */       str = "REF " + ((OracleTypeREF)localOracleNamedType).getFullName();
/*      */       
/*  429 */       break;
/*      */     
/*      */     case 100: 
/*  432 */       str = "BINARY_FLOAT";
/*  433 */       break;
/*      */     case 101: 
/*  435 */       str = "BINARY_DOUBLE";
/*  436 */       break;
/*      */     
/*      */ 
/*      */     case 1111: 
/*      */     default: 
/*  441 */       str = null;
/*      */     }
/*      */     
/*      */     
/*  445 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeCOLLECTION getOracleTypeCOLLECTION()
/*      */   {
/*  456 */     return (OracleTypeCOLLECTION)this.pickler;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeMetaData.ArrayStorage getArrayStorage()
/*      */     throws SQLException
/*      */   {
/*  468 */     return OracleTypeMetaData.ArrayStorage.withCode(getArrayType());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getArrayType()
/*      */     throws SQLException
/*      */   {
/*  480 */     return ((OracleTypeCOLLECTION)this.pickler).getUserCode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long getMaxLength()
/*      */     throws SQLException
/*      */   {
/*  493 */     return getArrayType() == 3 ? ((OracleTypeCOLLECTION)this.pickler).getMaxLength() : 0L;
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
/*      */   public String descType()
/*      */     throws SQLException
/*      */   {
/*  507 */     StringBuffer localStringBuffer = new StringBuffer();
/*  508 */     return descType(localStringBuffer, 0);
/*      */   }
/*      */   
/*      */ 
/*      */   String descType(StringBuffer paramStringBuffer, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  515 */     String str1 = "";
/*      */     
/*  517 */     for (int i = 0; i < paramInt; i++) {
/*  518 */       str1 = str1 + "  ";
/*      */     }
/*  520 */     String str2 = str1 + "  ";
/*      */     
/*  522 */     paramStringBuffer.append(str1);
/*  523 */     paramStringBuffer.append(getTypeName());
/*  524 */     paramStringBuffer.append("\n");
/*      */     
/*  526 */     int j = getBaseType();
/*      */     Object localObject;
/*  528 */     if ((j == 2002) || (j == 2008))
/*      */     {
/*      */ 
/*  531 */       localObject = StructDescriptor.createDescriptor(getBaseName(), this.connection);
/*      */       
/*      */ 
/*  534 */       ((StructDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */     }
/*  536 */     else if (j == 2003)
/*      */     {
/*  538 */       localObject = createDescriptor(getBaseName(), this.connection);
/*      */       
/*      */ 
/*  541 */       ((ArrayDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */     }
/*  543 */     else if (j == 2007)
/*      */     {
/*  545 */       localObject = OpaqueDescriptor.createDescriptor(getBaseName(), this.connection);
/*      */       
/*      */ 
/*  548 */       ((OpaqueDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */     }
/*      */     else
/*      */     {
/*  552 */       paramStringBuffer.append(str2);
/*  553 */       paramStringBuffer.append(getBaseName());
/*  554 */       paramStringBuffer.append("\n");
/*      */     }
/*      */     
/*  557 */     return paramStringBuffer.substring(0, paramStringBuffer.length());
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
/*      */   int toLength(ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/*  576 */     if (paramARRAY.numElems == -1)
/*      */     {
/*  578 */       if (paramARRAY.datumArray != null)
/*      */       {
/*  580 */         paramARRAY.numElems = paramARRAY.datumArray.length;
/*      */       }
/*  582 */       else if (paramARRAY.objArray != null)
/*      */       {
/*  584 */         if ((paramARRAY.objArray instanceof Object[])) {
/*  585 */           paramARRAY.numElems = ((Object[])paramARRAY.objArray).length;
/*  586 */         } else if ((paramARRAY.objArray instanceof int[])) {
/*  587 */           paramARRAY.numElems = ((long[])paramARRAY.objArray).length;
/*  588 */         } else if ((paramARRAY.objArray instanceof long[])) {
/*  589 */           paramARRAY.numElems = ((float[])paramARRAY.objArray).length;
/*  590 */         } else if ((paramARRAY.objArray instanceof float[])) {
/*  591 */           paramARRAY.numElems = ((double[])paramARRAY.objArray).length;
/*  592 */         } else if ((paramARRAY.objArray instanceof double[])) {
/*  593 */           paramARRAY.numElems = ((boolean[])paramARRAY.objArray).length;
/*  594 */         } else if ((paramARRAY.objArray instanceof boolean[])) {
/*  595 */           paramARRAY.numElems = ((int[])paramARRAY.objArray).length;
/*  596 */         } else if ((paramARRAY.objArray instanceof byte[])) {
/*  597 */           paramARRAY.numElems = ((byte[])paramARRAY.objArray).length;
/*  598 */         } else if ((paramARRAY.objArray instanceof short[])) {
/*  599 */           paramARRAY.numElems = ((short[])paramARRAY.objArray).length;
/*  600 */         } else if ((paramARRAY.objArray instanceof char[])) {
/*  601 */           paramARRAY.numElems = ((char[])paramARRAY.objArray).length;
/*      */         }
/*  603 */       } else if (paramARRAY.locator != null)
/*      */       {
/*  605 */         paramARRAY.numElems = toLengthFromLocator(paramARRAY.locator);
/*      */       } else { SQLException localSQLException;
/*  607 */         if (paramARRAY.shareBytes() != null)
/*      */         {
/*  609 */           this.pickler.unlinearize(paramARRAY.shareBytes(), paramARRAY.imageOffset, paramARRAY, 0, null);
/*      */           
/*      */ 
/*  612 */           if (paramARRAY.numElems == -1)
/*      */           {
/*  614 */             if (paramARRAY.locator != null) {
/*  615 */               paramARRAY.numElems = toLengthFromLocator(paramARRAY.locator);
/*      */             }
/*      */             else
/*      */             {
/*  619 */               localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to get array length");
/*  620 */               localSQLException.fillInStackTrace();
/*  621 */               throw localSQLException;
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */         else
/*      */         {
/*  629 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Array is in inconsistent status");
/*  630 */           localSQLException.fillInStackTrace();
/*  631 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  636 */     return paramARRAY.numElems;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] toBytes(ARRAY paramARRAY, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  647 */     byte[] arrayOfByte = paramARRAY.shareBytes();
/*      */     Object localObject;
/*  649 */     if (arrayOfByte == null)
/*      */     {
/*  651 */       if ((paramARRAY.datumArray != null) || (paramARRAY.locator != null))
/*      */       {
/*  653 */         arrayOfByte = this.pickler.linearize(paramARRAY);
/*      */         
/*  655 */         if (!paramBoolean) {
/*  656 */           paramARRAY.setShareBytes(null);
/*      */         }
/*      */       }
/*  659 */       else if (paramARRAY.objArray != null)
/*      */       {
/*  661 */         paramARRAY.datumArray = toOracleArray(paramARRAY.objArray, 1L, -1);
/*  662 */         arrayOfByte = this.pickler.linearize(paramARRAY);
/*      */         
/*  664 */         if (!paramBoolean)
/*      */         {
/*  666 */           paramARRAY.datumArray = null;
/*      */           
/*  668 */           paramARRAY.setShareBytes(null);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  675 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Array is in inconsistent status");
/*  676 */         ((SQLException)localObject).fillInStackTrace();
/*  677 */         throw ((Throwable)localObject);
/*      */       }
/*      */       
/*      */     }
/*  681 */     else if (paramARRAY.imageLength != 0L)
/*      */     {
/*  683 */       if ((paramARRAY.imageOffset != 0L) || (paramARRAY.imageLength != arrayOfByte.length))
/*      */       {
/*  685 */         localObject = new byte[(int)paramARRAY.imageLength];
/*      */         
/*  687 */         System.arraycopy(arrayOfByte, (int)paramARRAY.imageOffset, localObject, 0, (int)paramARRAY.imageLength);
/*      */         
/*  689 */         paramARRAY.setImage((byte[])localObject, 0L, 0L);
/*      */         
/*  691 */         return (byte[])localObject;
/*      */       }
/*      */     }
/*      */     
/*  695 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Datum[] toOracleArray(ARRAY paramARRAY, long paramLong, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  707 */     Datum[] arrayOfDatum1 = paramARRAY.datumArray;
/*      */     
/*  709 */     if (arrayOfDatum1 == null)
/*      */     {
/*  711 */       if (paramARRAY.objArray != null)
/*      */       {
/*  713 */         arrayOfDatum1 = toOracleArray(paramARRAY.objArray, paramLong, paramInt);
/*      */ 
/*      */       }
/*  716 */       else if (paramARRAY.locator != null)
/*      */       {
/*  718 */         arrayOfDatum1 = toOracleArrayFromLocator(paramARRAY.locator, paramLong, paramInt, null);
/*      */ 
/*      */       }
/*  721 */       else if (paramARRAY.shareBytes() != null)
/*      */       {
/*  723 */         this.pickler.unlinearize(paramARRAY.shareBytes(), paramARRAY.imageOffset, paramARRAY, paramLong, paramInt, 1, null);
/*      */         
/*      */ 
/*  726 */         if (paramARRAY.locator != null)
/*      */         {
/*      */ 
/*  729 */           arrayOfDatum1 = toOracleArrayFromLocator(paramARRAY.locator, paramLong, paramInt, null);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  735 */           arrayOfDatum1 = paramARRAY.datumArray;
/*      */         }
/*      */         
/*  738 */         if (!paramBoolean) {
/*  739 */           paramARRAY.datumArray = null;
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  745 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Array is in inconsistent status.");
/*  746 */         localSQLException.fillInStackTrace();
/*  747 */         throw localSQLException;
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/*  753 */       if (paramLong > arrayOfDatum1.length)
/*      */       {
/*      */ 
/*  756 */         return new Datum[0];
/*      */       }
/*      */       
/*  759 */       int i = (int)(paramInt == -1 ? arrayOfDatum1.length - paramLong + 1L : Math.min(arrayOfDatum1.length - paramLong + 1L, paramInt));
/*      */       
/*      */ 
/*  762 */       arrayOfDatum1 = new Datum[i];
/*      */       
/*  764 */       System.arraycopy(paramARRAY.datumArray, (int)paramLong - 1, arrayOfDatum1, 0, i);
/*      */     }
/*      */     
/*  767 */     Datum[] arrayOfDatum2 = null;
/*      */     
/*  769 */     if (paramBoolean)
/*      */     {
/*  771 */       paramARRAY.datumArray = arrayOfDatum1;
/*  772 */       arrayOfDatum2 = (Datum[])arrayOfDatum1.clone();
/*      */     }
/*      */     else
/*      */     {
/*  776 */       arrayOfDatum2 = arrayOfDatum1;
/*      */     }
/*      */     
/*  779 */     return arrayOfDatum2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Object[] toJavaArray(ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  791 */     Object[] arrayOfObject1 = null;
/*      */     
/*  793 */     if (paramARRAY.objArray != null)
/*      */     {
/*  795 */       arrayOfObject1 = (Object[])((Object[])paramARRAY.objArray).clone();
/*      */       
/*  797 */       int i = arrayOfObject1.length;
/*  798 */       int j = (int)(paramInt == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt));
/*      */       
/*      */ 
/*  801 */       if (j <= 0)
/*      */       {
/*  803 */         Object[] arrayOfObject2 = (Object[])makeJavaArray(j, getBaseType());
/*      */         
/*  805 */         return arrayOfObject2;
/*      */       }
/*      */       
/*      */ 
/*  809 */       arrayOfObject1 = (Object[])makeJavaArray(j, getBaseType());
/*      */       
/*  811 */       System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, arrayOfObject1, 0, j);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  816 */       if (paramARRAY.datumArray != null)
/*      */       {
/*  818 */         arrayOfObject1 = (Object[])toJavaArray(paramARRAY.datumArray, paramLong, paramInt, paramMap);
/*      */       }
/*  820 */       else if (paramARRAY.locator != null)
/*      */       {
/*  822 */         arrayOfObject1 = toArrayFromLocator(paramARRAY.locator, paramLong, paramInt, paramMap);
/*      */       }
/*  824 */       else if (paramARRAY.shareBytes() != null)
/*      */       {
/*  826 */         this.pickler.unlinearize(paramARRAY.shareBytes(), paramARRAY.imageOffset, paramARRAY, paramLong, paramInt, 2, paramMap);
/*      */         
/*      */ 
/*  829 */         if (paramARRAY.locator != null) {
/*  830 */           arrayOfObject1 = toArrayFromLocator(paramARRAY.locator, paramLong, paramInt, paramMap);
/*      */         } else {
/*  832 */           arrayOfObject1 = (Object[])paramARRAY.objArray;
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  838 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Array is in inconsistent status");
/*  839 */         localSQLException.fillInStackTrace();
/*  840 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*  844 */       if ((paramBoolean) && (getBaseType() != 2002) && (getBaseType() != 2008) && (arrayOfObject1 != null))
/*      */       {
/*  846 */         paramARRAY.objArray = arrayOfObject1.clone();
/*      */       } else {
/*  848 */         paramARRAY.objArray = null;
/*      */       }
/*      */     }
/*  851 */     return arrayOfObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Datum[] toOracleArrayFromLocator(byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  860 */     int i = toLengthFromLocator(paramArrayOfByte);
/*  861 */     int j = (int)(paramInt == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt));
/*      */     
/*      */ 
/*  864 */     Datum[] arrayOfDatum = null;
/*      */     
/*  866 */     if (j <= 0)
/*      */     {
/*  868 */       arrayOfDatum = new Datum[0];
/*      */     }
/*      */     else
/*      */     {
/*  872 */       arrayOfDatum = new Datum[j];
/*      */       
/*  874 */       ResultSet localResultSet = toResultSetFromLocator(paramArrayOfByte, paramLong, paramInt, paramMap);
/*      */       
/*      */ 
/*  877 */       for (int k = 0; localResultSet.next(); k++) {
/*  878 */         arrayOfDatum[k] = ((OracleResultSet)localResultSet).getOracleObject(2);
/*      */       }
/*  880 */       localResultSet.close();
/*      */     }
/*      */     
/*  883 */     return arrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Object[] toArrayFromLocator(byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  893 */     int i = toLengthFromLocator(paramArrayOfByte);
/*  894 */     int j = (int)(paramInt == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt));
/*      */     
/*  896 */     Object[] arrayOfObject = null;
/*      */     
/*  898 */     if (j <= 0)
/*      */     {
/*      */ 
/*  901 */       arrayOfObject = (Object[])makeJavaArray(0, getBaseType());
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  906 */       arrayOfObject = (Object[])makeJavaArray(j, getBaseType());
/*      */       
/*  908 */       ResultSet localResultSet = toResultSetFromLocator(paramArrayOfByte, paramLong, paramInt, paramMap);
/*      */       
/*      */ 
/*  911 */       for (int k = 0; localResultSet.next(); k++) {
/*  912 */         arrayOfObject[k] = ((OracleResultSet)localResultSet).getObject(2, paramMap);
/*      */       }
/*  914 */       localResultSet.close();
/*      */     }
/*      */     
/*  917 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ResultSet toResultSet(ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  926 */     ResultSet localResultSet = null;
/*      */     SQLException localSQLException;
/*  928 */     if (paramARRAY.datumArray != null)
/*      */     {
/*  930 */       localResultSet = toResultSet(paramARRAY.datumArray, paramLong, paramInt, paramMap);
/*      */     }
/*  932 */     else if (paramARRAY.locator != null)
/*      */     {
/*  934 */       localResultSet = toResultSetFromLocator(paramARRAY.locator, paramLong, paramInt, paramMap);
/*      */     }
/*  936 */     else if (paramARRAY.objArray != null)
/*      */     {
/*  938 */       localResultSet = toResultSet(toOracleArray(paramARRAY.objArray, paramLong, paramInt), 1L, -1, paramMap);
/*      */ 
/*      */     }
/*  941 */     else if (paramARRAY.shareBytes() != null)
/*      */     {
/*      */ 
/*      */ 
/*  945 */       if (((OracleTypeCOLLECTION)this.pickler).isInlineImage(paramARRAY.shareBytes(), (int)paramARRAY.imageOffset))
/*      */       {
/*      */ 
/*  948 */         localResultSet = toResultSetFromImage(paramARRAY, paramLong, paramInt, paramMap);
/*      */       }
/*      */       else
/*      */       {
/*  952 */         this.pickler.unlinearize(paramARRAY.shareBytes(), paramARRAY.imageOffset, paramARRAY, 1, null);
/*      */         
/*      */ 
/*  955 */         if (paramARRAY.locator != null) {
/*  956 */           localResultSet = toResultSetFromLocator(paramARRAY.locator, paramLong, paramInt, paramMap);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  961 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Array is in inconsistent status");
/*  962 */           localSQLException.fillInStackTrace();
/*  963 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  969 */     if (localResultSet == null)
/*      */     {
/*      */ 
/*      */ 
/*  973 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Unable to create array ResultSet");
/*  974 */       localSQLException.fillInStackTrace();
/*  975 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  979 */     return localResultSet;
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
/*      */   public ResultSet toResultSet(Datum[] paramArrayOfDatum, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  997 */     ResultSet localResultSet = null;
/*      */     
/*  999 */     if (paramInt == -1) {
/* 1000 */       localResultSet = this.connection.newArrayDataResultSet(paramArrayOfDatum, paramLong, paramArrayOfDatum.length, paramMap);
/*      */     } else
/* 1002 */       localResultSet = this.connection.newArrayDataResultSet(paramArrayOfDatum, paramLong, paramInt, paramMap);
/* 1003 */     return localResultSet;
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
/*      */   public ResultSet toResultSetFromLocator(byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1021 */     ResultSet localResultSet = this.connection.newArrayLocatorResultSet(this, paramArrayOfByte, paramLong, paramInt, paramMap);
/*      */     
/*      */ 
/* 1024 */     return localResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public ResultSet toResultSetFromImage(ARRAY paramARRAY, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1032 */     ResultSet localResultSet = this.connection.newArrayDataResultSet(paramARRAY, paramLong, paramInt, paramMap);
/*      */     
/* 1034 */     return localResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static Object[] makeJavaArray(int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1047 */     Object localObject = null;
/*      */     
/* 1049 */     switch (paramInt2)
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
/*      */     case -7: 
/*      */     case -6: 
/*      */     case -5: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/* 1069 */       localObject = new BigDecimal[paramInt1];
/*      */       
/* 1071 */       break;
/*      */     
/*      */ 
/*      */     case 1: 
/*      */     case 12: 
/* 1076 */       localObject = new String[paramInt1];
/*      */       
/* 1078 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -102: 
/*      */     case -101: 
/*      */     case 91: 
/*      */     case 92: 
/*      */     case 93: 
/* 1089 */       localObject = new Timestamp[paramInt1];
/*      */       
/* 1091 */       break;
/*      */     
/*      */ 
/*      */     case 2002: 
/*      */     case 2008: 
/* 1096 */       localObject = new Object[paramInt1];
/*      */       
/* 1098 */       break;
/*      */     
/*      */     case -13: 
/* 1101 */       localObject = new BFILE[paramInt1];
/*      */       
/* 1103 */       break;
/*      */     
/*      */     case 2004: 
/* 1106 */       localObject = new BLOB[paramInt1];
/*      */       
/* 1108 */       break;
/*      */     
/*      */     case 2005: 
/* 1111 */       localObject = new CLOB[paramInt1];
/*      */       
/* 1113 */       break;
/*      */     
/*      */ 
/*      */     case -3: 
/*      */     case -2: 
/* 1118 */       localObject = new byte[paramInt1][];
/*      */       
/* 1120 */       break;
/*      */     
/*      */     case 2006: 
/* 1123 */       localObject = new REF[paramInt1];
/*      */       
/* 1125 */       break;
/*      */     
/*      */     case 2003: 
/* 1128 */       localObject = new Object[paramInt1];
/*      */       
/* 1130 */       break;
/*      */     
/*      */     case 100: 
/* 1133 */       localObject = new Float[paramInt1];
/* 1134 */       break;
/*      */     case 101: 
/* 1136 */       localObject = new Double[paramInt1];
/* 1137 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/* 1142 */       SQLException localSQLException = DatabaseError.createSqlException(null, 1, "makeJavaArray doesn't support type " + paramInt2);
/*      */       
/* 1144 */       localSQLException.fillInStackTrace();
/* 1145 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*      */ 
/* 1150 */     return (Object[])localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int toLengthFromLocator(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1163 */     ARRAY localARRAY = new ARRAY(this, this.connection, (byte[])null);
/*      */     
/* 1165 */     localARRAY.setLocator(paramArrayOfByte);
/*      */     
/* 1167 */     int i = 0;
/*      */     
/*      */ 
/* 1170 */     OraclePreparedStatement localOraclePreparedStatement = null;
/* 1171 */     OracleResultSet localOracleResultSet = null;
/*      */     
/* 1173 */     localOraclePreparedStatement = (OraclePreparedStatement)this.connection.prepareStatement("SELECT count(*) FROM TABLE( CAST(:1 AS " + getName() + ") )");
/*      */     
/*      */ 
/*      */ 
/* 1177 */     localOraclePreparedStatement.setArray(1, localARRAY);
/*      */     
/* 1179 */     localOracleResultSet = (OracleResultSet)localOraclePreparedStatement.executeQuery();
/*      */     
/* 1181 */     if (localOracleResultSet.next()) {
/* 1182 */       i = localOracleResultSet.getInt(1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1187 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1, "Fail to access array storage table");
/* 1188 */       localSQLException.fillInStackTrace();
/* 1189 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/* 1193 */     localOracleResultSet.close();
/* 1194 */     localOraclePreparedStatement.close();
/*      */     
/* 1196 */     return i;
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
/*      */   Datum[] toOracleArray(Object paramObject, long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1212 */     Datum[] arrayOfDatum = null;
/*      */     
/* 1214 */     if (paramObject != null)
/*      */     {
/* 1216 */       OracleType localOracleType = getElementType();
/*      */       
/* 1218 */       arrayOfDatum = localOracleType.toDatumArray(paramObject, this.connection, paramLong, paramInt);
/*      */     }
/*      */     
/* 1221 */     return arrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private Object toJavaArray(Datum[] paramArrayOfDatum, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1229 */     int i = (int)(paramInt == -1 ? paramArrayOfDatum.length - paramLong + 1L : Math.min(paramArrayOfDatum.length - paramLong + 1L, paramInt));
/*      */     
/*      */ 
/* 1232 */     if (i < 0) {
/* 1233 */       i = 0;
/*      */     }
/* 1235 */     Object[] arrayOfObject = (Object[])makeJavaArray(i, getBaseType());
/*      */     Object localObject;
/* 1237 */     int j; if (getBaseType() == 2002)
/*      */     {
/* 1239 */       localObject = null;
/*      */       
/* 1241 */       for (j = 0; j < i; j++)
/*      */       {
/* 1243 */         localObject = (STRUCT)paramArrayOfDatum[((int)paramLong + j - 1)];
/* 1244 */         arrayOfObject[j] = (localObject != null ? ((STRUCT)localObject).toJdbc(paramMap) : null);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1249 */       localObject = null;
/*      */       
/* 1251 */       for (j = 0; j < i; j++)
/*      */       {
/* 1253 */         localObject = paramArrayOfDatum[((int)paramLong + j - 1)];
/* 1254 */         arrayOfObject[j] = (localObject != null ? ((Datum)localObject).toJdbc() : null);
/*      */       }
/*      */     }
/*      */     
/* 1258 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private Object toNumericArray(Datum[] paramArrayOfDatum, long paramLong, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1266 */     Object localObject1 = null;
/*      */     
/* 1268 */     int i = (int)(paramInt1 == -1 ? paramArrayOfDatum.length - paramLong + 1L : Math.min(paramArrayOfDatum.length - paramLong + 1L, paramInt1));
/*      */     
/*      */ 
/* 1271 */     if (i < 0)
/* 1272 */       i = 0;
/*      */     Object localObject2;
/* 1274 */     int j; Datum localDatum; switch (paramInt2)
/*      */     {
/*      */ 
/*      */     case 4: 
/* 1278 */       localObject2 = new int[i];
/*      */       
/* 1280 */       for (j = 0; j < i; j++)
/*      */       {
/* 1282 */         localDatum = paramArrayOfDatum[((int)paramLong + j - 1)];
/*      */         
/* 1284 */         if (localDatum != null) {
/* 1285 */           localObject2[j] = localDatum.intValue();
/*      */         }
/*      */       }
/* 1288 */       localObject1 = localObject2;
/* 1289 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 5: 
/* 1294 */       localObject2 = new double[i];
/*      */       
/* 1296 */       for (j = 0; j < i; j++)
/*      */       {
/* 1298 */         localDatum = paramArrayOfDatum[((int)paramLong + j - 1)];
/*      */         
/* 1300 */         if (localDatum != null) {
/* 1301 */           localObject2[j] = localDatum.doubleValue();
/*      */         }
/*      */       }
/* 1304 */       localObject1 = localObject2;
/* 1305 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 6: 
/* 1310 */       localObject2 = new float[i];
/*      */       
/* 1312 */       for (j = 0; j < i; j++)
/*      */       {
/* 1314 */         localDatum = paramArrayOfDatum[((int)paramLong + j - 1)];
/*      */         
/* 1316 */         if (localDatum != null) {
/* 1317 */           localObject2[j] = localDatum.floatValue();
/*      */         }
/*      */       }
/* 1320 */       localObject1 = localObject2;
/* 1321 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 7: 
/* 1326 */       localObject2 = new long[i];
/*      */       
/* 1328 */       for (j = 0; j < i; j++)
/*      */       {
/* 1330 */         localDatum = paramArrayOfDatum[((int)paramLong + j - 1)];
/*      */         
/* 1332 */         if (localDatum != null) {
/* 1333 */           localObject2[j] = localDatum.longValue();
/*      */         }
/*      */       }
/* 1336 */       localObject1 = localObject2;
/* 1337 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 8: 
/* 1342 */       localObject2 = new short[i];
/*      */       
/* 1344 */       for (j = 0; j < i; j++)
/*      */       {
/* 1346 */         localDatum = paramArrayOfDatum[((int)paramLong + j - 1)];
/*      */         
/* 1348 */         if (localDatum != null) {
/* 1349 */           localObject2[j] = ((NUMBER)localDatum).shortValue();
/*      */         }
/*      */       }
/* 1352 */       localObject1 = localObject2;
/* 1353 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/* 1360 */       localObject2 = DatabaseError.createUnsupportedFeatureSqlException();
/* 1361 */       ((SQLException)localObject2).fillInStackTrace();
/* 1362 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/*      */     
/* 1366 */     return localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Object toNumericArrayFromLocator(byte[] paramArrayOfByte, long paramLong, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1375 */     Object localObject1 = null;
/*      */     
/* 1377 */     int i = toLengthFromLocator(paramArrayOfByte);
/*      */     
/* 1379 */     ResultSet localResultSet = toResultSetFromLocator(paramArrayOfByte, paramLong, paramInt1, null);
/*      */     
/* 1381 */     int j = 0;
/*      */     Object localObject2;
/* 1383 */     switch (paramInt2)
/*      */     {
/*      */ 
/*      */     case 4: 
/* 1387 */       localObject2 = new int[i];
/*      */       
/* 1389 */       while ((localResultSet.next()) && (j < i)) {
/* 1390 */         localObject2[(j++)] = ((OracleResultSet)localResultSet).getInt(2);
/*      */       }
/* 1392 */       localResultSet.close();
/*      */       
/* 1394 */       localObject1 = localObject2;
/* 1395 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 5: 
/* 1400 */       localObject2 = new double[i];
/*      */       
/* 1402 */       while ((localResultSet.next()) && (j < i)) {
/* 1403 */         localObject2[(j++)] = ((OracleResultSet)localResultSet).getDouble(2);
/*      */       }
/* 1405 */       localResultSet.close();
/*      */       
/* 1407 */       localObject1 = localObject2;
/* 1408 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 6: 
/* 1413 */       localObject2 = new float[i];
/*      */       
/* 1415 */       while ((localResultSet.next()) && (j < i)) {
/* 1416 */         localObject2[(j++)] = ((OracleResultSet)localResultSet).getFloat(2);
/*      */       }
/* 1418 */       localResultSet.close();
/*      */       
/* 1420 */       localObject1 = localObject2;
/* 1421 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 7: 
/* 1426 */       localObject2 = new long[i];
/*      */       
/* 1428 */       while ((localResultSet.next()) && (j < i)) {
/* 1429 */         localObject2[(j++)] = ((OracleResultSet)localResultSet).getLong(2);
/*      */       }
/* 1431 */       localResultSet.close();
/*      */       
/* 1433 */       localObject1 = localObject2;
/* 1434 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 8: 
/* 1439 */       localObject2 = new short[i];
/*      */       
/* 1441 */       while ((localResultSet.next()) && (j < i)) {
/* 1442 */         localObject2[(j++)] = ((OracleResultSet)localResultSet).getShort(2);
/*      */       }
/* 1444 */       localResultSet.close();
/*      */       
/* 1446 */       localObject1 = localObject2;
/* 1447 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/* 1453 */       localObject2 = DatabaseError.createUnsupportedFeatureSqlException();
/* 1454 */       ((SQLException)localObject2).fillInStackTrace();
/* 1455 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/* 1458 */     return localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Object toNumericArray(ARRAY paramARRAY, long paramLong, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1467 */     OracleType localOracleType = getElementType();
/* 1468 */     if ((!(localOracleType instanceof OracleTypeNUMBER)) && (!(localOracleType instanceof OracleTypeFLOAT)))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1473 */       localObject1 = DatabaseError.createUnsupportedFeatureSqlException();
/* 1474 */       ((SQLException)localObject1).fillInStackTrace();
/* 1475 */       throw ((Throwable)localObject1);
/*      */     }
/*      */     
/*      */ 
/* 1479 */     Object localObject1 = null;
/*      */     
/*      */ 
/* 1482 */     if (paramARRAY.objArray != null) { int i;
/*      */       Object localObject2;
/* 1484 */       if ((paramInt2 == 4) && ((paramARRAY.objArray instanceof int[])))
/*      */       {
/* 1486 */         i = ((int[])paramARRAY.objArray).length;
/*      */         
/* 1488 */         if (paramLong > i) {
/* 1489 */           return new int[0];
/*      */         }
/* 1491 */         i = (int)(paramInt1 == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt1));
/*      */         
/*      */ 
/* 1494 */         localObject2 = new int[i];
/*      */         
/* 1496 */         System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, localObject2, 0, i);
/*      */         
/*      */ 
/* 1499 */         localObject1 = localObject2;
/*      */       }
/* 1501 */       else if ((paramInt2 == 5) && ((paramARRAY.objArray instanceof double[])))
/*      */       {
/* 1503 */         i = ((double[])paramARRAY.objArray).length;
/*      */         
/* 1505 */         if (paramLong > i) {
/* 1506 */           return new double[0];
/*      */         }
/* 1508 */         i = (int)(paramInt1 == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt1));
/*      */         
/*      */ 
/* 1511 */         localObject2 = new double[i];
/*      */         
/* 1513 */         System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, localObject2, 0, i);
/*      */         
/*      */ 
/* 1516 */         localObject1 = localObject2;
/*      */       }
/* 1518 */       else if ((paramInt2 == 6) && ((paramARRAY.objArray instanceof float[])))
/*      */       {
/* 1520 */         i = ((float[])paramARRAY.objArray).length;
/*      */         
/* 1522 */         if (paramLong > i) {
/* 1523 */           return new float[0];
/*      */         }
/* 1525 */         i = (int)(paramInt1 == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt1));
/*      */         
/*      */ 
/* 1528 */         localObject2 = new float[i];
/*      */         
/* 1530 */         System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, localObject2, 0, i);
/*      */         
/*      */ 
/* 1533 */         localObject1 = localObject2;
/*      */       }
/* 1535 */       else if ((paramInt2 == 7) && ((paramARRAY.objArray instanceof long[])))
/*      */       {
/* 1537 */         i = ((long[])paramARRAY.objArray).length;
/*      */         
/* 1539 */         if (paramLong > i) {
/* 1540 */           return new long[0];
/*      */         }
/* 1542 */         i = (int)(paramInt1 == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt1));
/*      */         
/*      */ 
/* 1545 */         localObject2 = new long[i];
/*      */         
/* 1547 */         System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, localObject2, 0, i);
/*      */         
/*      */ 
/* 1550 */         localObject1 = localObject2;
/*      */       }
/* 1552 */       else if ((paramInt2 == 8) && ((paramARRAY.objArray instanceof short[])))
/*      */       {
/* 1554 */         i = ((short[])paramARRAY.objArray).length;
/*      */         
/* 1556 */         if (paramLong > i) {
/* 1557 */           return new short[0];
/*      */         }
/* 1559 */         i = (int)(paramInt1 == -1 ? i - paramLong + 1L : Math.min(i - paramLong + 1L, paramInt1));
/*      */         
/*      */ 
/* 1562 */         localObject2 = new short[i];
/*      */         
/* 1564 */         System.arraycopy(paramARRAY.objArray, (int)paramLong - 1, localObject2, 0, i);
/*      */         
/*      */ 
/* 1567 */         localObject1 = localObject2;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1574 */       if (paramARRAY.datumArray != null)
/*      */       {
/* 1576 */         localObject1 = toNumericArray(paramARRAY.datumArray, paramLong, paramInt1, paramInt2);
/*      */ 
/*      */       }
/* 1579 */       else if (paramARRAY.locator != null)
/*      */       {
/* 1581 */         localObject1 = toNumericArrayFromLocator(paramARRAY.locator, paramLong, paramInt1, paramInt2);
/*      */ 
/*      */ 
/*      */       }
/* 1585 */       else if (paramARRAY.shareBytes() != null)
/*      */       {
/* 1587 */         this.pickler.unlinearize(paramARRAY.shareBytes(), paramARRAY.imageOffset, paramARRAY, paramLong, paramInt1, paramInt2, null);
/*      */         
/*      */ 
/* 1590 */         if (paramARRAY.locator != null)
/*      */         {
/*      */ 
/* 1593 */           localObject1 = toNumericArrayFromLocator(paramARRAY.locator, paramLong, paramInt1, paramInt2);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1599 */           localObject1 = paramARRAY.objArray;
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/* 1607 */         SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 1608 */         localSQLException.fillInStackTrace();
/* 1609 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/* 1613 */       if (!paramBoolean) {
/* 1614 */         paramARRAY.objArray = null;
/*      */       }
/*      */     }
/* 1617 */     return localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */   private void initPickler()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/* 1626 */       OracleTypeADT localOracleTypeADT = new OracleTypeADT(getName(), this.connection);
/* 1627 */       localOracleTypeADT.init(this.connection);
/* 1628 */       this.pickler = ((OracleTypeCOLLECTION)localOracleTypeADT.cleanup());
/* 1629 */       this.toid = ((OracleTypeADT)this.pickler).getTOID();
/* 1630 */       this.pickler.setDescriptor(this);
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1635 */       if ((localException instanceof SQLException))
/*      */       {
/* 1637 */         throw ((SQLException)localException);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1642 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Unable to resolve type: \"" + getName() + "\"");
/*      */       
/* 1644 */       localSQLException.fillInStackTrace();
/* 1645 */       throw localSQLException;
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
/*      */   private OracleType getElementType()
/*      */     throws SQLException
/*      */   {
/* 1661 */     OracleType localOracleType = ((OracleTypeCOLLECTION)this.pickler).getElementType();
/*      */     
/* 1663 */     return localOracleType;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getTypeCode()
/*      */     throws SQLException
/*      */   {
/* 1670 */     int i = 2003;
/* 1671 */     return i;
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
/*      */   public byte[] toBytes(Datum[] paramArrayOfDatum)
/*      */     throws SQLException
/*      */   {
/* 1694 */     ARRAY localARRAY = new ARRAY(this, this.connection, paramArrayOfDatum);
/*      */     
/* 1696 */     return this.pickler.linearize(localARRAY);
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
/*      */   public byte[] toBytes(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 1715 */     Datum[] arrayOfDatum = toArray(paramArrayOfObject);
/* 1716 */     byte[] arrayOfByte = toBytes(arrayOfDatum);
/* 1717 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int length(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1730 */     ARRAY localARRAY = new ARRAY(this, this.connection, paramArrayOfByte);
/* 1731 */     int i = toLength(localARRAY);
/*      */     
/* 1733 */     return i;
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
/*      */   public Datum[] toArray(byte[] paramArrayOfByte)
/*      */     throws SQLException
/*      */   {
/* 1747 */     Datum[] arrayOfDatum = null;
/* 1748 */     if (paramArrayOfByte != null)
/*      */     {
/* 1750 */       ARRAY localARRAY = new ARRAY(this, this.connection, paramArrayOfByte);
/*      */       
/* 1752 */       arrayOfDatum = toOracleArray(localARRAY, 1L, -1, false);
/*      */     }
/* 1754 */     return arrayOfDatum;
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
/*      */   public Datum[] toArray(Object paramObject)
/*      */     throws SQLException
/*      */   {
/* 1770 */     Datum[] arrayOfDatum = toOracleArray(paramObject, 1L, -1);
/* 1771 */     return arrayOfDatum;
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
/*      */   public ResultSet toResultSet(byte[] paramArrayOfByte, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1786 */     ResultSet localResultSet = null;
/* 1787 */     if (paramArrayOfByte != null)
/*      */     {
/* 1789 */       ARRAY localARRAY = (ARRAY)this.pickler.unlinearize(paramArrayOfByte, 0L, null, 1, null);
/*      */       
/*      */ 
/* 1792 */       localResultSet = toResultSet(localARRAY, 1L, -1, paramMap, false);
/*      */     }
/* 1794 */     return localResultSet;
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
/*      */   public ResultSet toResultSet(byte[] paramArrayOfByte, long paramLong, int paramInt, Map paramMap)
/*      */     throws SQLException
/*      */   {
/* 1811 */     ResultSet localResultSet = null;
/*      */     
/* 1813 */     if (paramArrayOfByte != null)
/*      */     {
/* 1815 */       ARRAY localARRAY = (ARRAY)this.pickler.unlinearize(paramArrayOfByte, 0L, (ARRAY)null, 1, null);
/*      */       
/*      */ 
/* 1818 */       localResultSet = toResultSet(localARRAY, paramLong, paramInt, paramMap, false);
/*      */     }
/*      */     
/* 1821 */     return localResultSet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   String tagName()
/*      */   {
/* 1828 */     return "ArrayDescriptor";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static int getCacheStyle(ARRAY paramARRAY)
/*      */     throws SQLException
/*      */   {
/* 1839 */     int i = 2;
/*      */     
/* 1841 */     if ((paramARRAY.getAutoIndexing()) && ((paramARRAY.getAccessDirection() == 2) || (paramARRAY.getAccessDirection() == 3)))
/*      */     {
/*      */ 
/* 1844 */       i = 1;
/*      */     }
/*      */     
/* 1847 */     return i;
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
/* 1875 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   private void writeObject(ObjectOutputStream paramObjectOutputStream)
/*      */     throws IOException
/*      */   {}
/*      */   
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {}
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/ArrayDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */