/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLInput;
/*      */ import java.sql.SQLOutput;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.OracleTypeMetaData.Kind;
/*      */ import oracle.jdbc.OracleTypeMetaData.Struct;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.oracore.OracleNamedType;
/*      */ import oracle.jdbc.oracore.OracleType;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StructDescriptor
/*      */   extends TypeDescriptor
/*      */   implements OracleTypeMetaData.Struct, Serializable
/*      */ {
/*      */   static final boolean DEBUG = false;
/*      */   static final long serialVersionUID = 1013921343538311063L;
/*      */   
/*      */   public static StructDescriptor createDescriptor(String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*   72 */     return createDescriptor(paramString, paramConnection, false, false);
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
/*      */   public static StructDescriptor createDescriptor(String paramString, Connection paramConnection, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*   92 */     if ((paramString == null) || (paramString.length() == 0) || (paramConnection == null))
/*      */     {
/*      */ 
/*   95 */       localObject = DatabaseError.createSqlException(null, 60, "Invalid arguments");
/*   96 */       ((SQLException)localObject).fillInStackTrace();
/*   97 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/*  101 */     Object localObject = new SQLName(paramString, (oracle.jdbc.OracleConnection)paramConnection);
/*      */     
/*  103 */     StructDescriptor localStructDescriptor = createDescriptor((SQLName)localObject, paramConnection, paramBoolean1, paramBoolean2);
/*  104 */     return localStructDescriptor;
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
/*      */   public static StructDescriptor createDescriptor(SQLName paramSQLName, Connection paramConnection, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException
/*      */   {
/*  124 */     String str = paramSQLName.getName();
/*  125 */     StructDescriptor localStructDescriptor = null;
/*  126 */     if (!paramBoolean2)
/*      */     {
/*      */ 
/*  129 */       localStructDescriptor = (StructDescriptor)((oracle.jdbc.OracleConnection)paramConnection).getDescriptor(str);
/*      */       
/*      */ 
/*      */ 
/*  133 */       if (localStructDescriptor == null)
/*      */       {
/*  135 */         localStructDescriptor = new StructDescriptor(paramSQLName, paramConnection);
/*  136 */         if (paramBoolean1) localStructDescriptor.initNamesRecursively();
/*  137 */         ((oracle.jdbc.OracleConnection)paramConnection).putDescriptor(str, localStructDescriptor);
/*      */       }
/*      */     }
/*  140 */     return localStructDescriptor;
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
/*      */   public static StructDescriptor createDescriptor(SQLName paramSQLName, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  157 */     return createDescriptor(paramSQLName, paramConnection, false, false);
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
/*      */   public static StructDescriptor createDescriptor(OracleTypeADT paramOracleTypeADT)
/*      */     throws SQLException
/*      */   {
/*  174 */     String str = paramOracleTypeADT.getFullName();
/*  175 */     oracle.jdbc.internal.OracleConnection localOracleConnection = paramOracleTypeADT.getConnection();
/*  176 */     StructDescriptor localStructDescriptor = (StructDescriptor)localOracleConnection.getDescriptor(str);
/*      */     
/*      */ 
/*      */ 
/*  180 */     if (localStructDescriptor == null)
/*      */     {
/*  182 */       SQLName localSQLName = new SQLName(paramOracleTypeADT.getSchemaName(), paramOracleTypeADT.getSimpleName(), paramOracleTypeADT.getConnection());
/*      */       
/*  184 */       localStructDescriptor = new StructDescriptor(localSQLName, paramOracleTypeADT, localOracleConnection);
/*      */       
/*  186 */       localOracleConnection.putDescriptor(str, localStructDescriptor);
/*      */     }
/*  188 */     return localStructDescriptor;
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
/*      */   public static StructDescriptor createDescriptor(SQLName paramSQLName, byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2, oracle.jdbc.internal.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  203 */     OracleTypeADT localOracleTypeADT = new OracleTypeADT(paramSQLName, paramArrayOfByte1, paramInt, paramArrayOfByte2, paramOracleConnection);
/*  204 */     return new StructDescriptor(paramSQLName, localOracleTypeADT, paramOracleConnection);
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
/*      */   public StructDescriptor(OracleTypeADT paramOracleTypeADT, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  221 */     super((short)108, paramOracleTypeADT, paramConnection);
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
/*      */   public StructDescriptor(String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  236 */     super((short)108, paramString, paramConnection);
/*      */     
/*  238 */     initPickler();
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
/*      */   public StructDescriptor(SQLName paramSQLName, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  252 */     super((short)108, paramSQLName, paramConnection);
/*      */     
/*  254 */     initPickler();
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
/*      */   public StructDescriptor(SQLName paramSQLName, OracleTypeADT paramOracleTypeADT, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  269 */     super((short)108, paramSQLName, paramOracleTypeADT, paramConnection);
/*      */     
/*      */ 
/*      */ 
/*  273 */     this.toid = paramOracleTypeADT.getTOID();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   StructDescriptor(byte[] paramArrayOfByte, int paramInt, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  284 */     super((short)108);
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
/*      */   StructDescriptor(AttributeDescriptor[] paramArrayOfAttributeDescriptor, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  300 */     super((short)108);
/*      */     
/*  302 */     this.attributesDescriptor = paramArrayOfAttributeDescriptor;
/*  303 */     setPhysicalConnectionOf(paramConnection);
/*  304 */     this.isTransient = true;
/*      */     
/*      */ 
/*  307 */     initPickler();
/*  308 */     this.isInstanciable = Boolean.TRUE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initPickler()
/*      */     throws SQLException
/*      */   {
/*      */     try
/*      */     {
/*  319 */       if (this.isTransient) {
/*  320 */         this.pickler = new OracleTypeADT(this.attributesDescriptor, this.connection);
/*      */       }
/*      */       else {
/*  323 */         this.pickler = new OracleTypeADT(getName(), this.connection);
/*  324 */         ((OracleTypeADT)this.pickler).init(this.connection);
/*  325 */         this.toid = ((OracleTypeADT)this.pickler).getTOID();
/*      */       }
/*      */       
/*  328 */       this.pickler.setDescriptor(this);
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*  332 */       if ((localException instanceof SQLException)) {
/*  333 */         throw ((SQLException)localException);
/*      */       }
/*      */       
/*      */ 
/*  337 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Unable to resolve type \"" + getName() + "\"");
/*      */       
/*  339 */       localSQLException.fillInStackTrace();
/*  340 */       throw localSQLException;
/*      */     }
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
/*  352 */     return OracleTypeMetaData.Kind.STRUCT;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getTypeCode()
/*      */     throws SQLException
/*      */   {
/*  359 */     int i = getOracleTypeADT().getTypeCode();
/*  360 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */   public int getTypeVersion()
/*      */     throws SQLException
/*      */   {
/*  367 */     int i = getOracleTypeADT().getTypeVersion();
/*  368 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setAttributesDescriptor(AttributeDescriptor[] paramArrayOfAttributeDescriptor)
/*      */   {
/*  375 */     this.attributesDescriptor = paramArrayOfAttributeDescriptor;
/*      */   }
/*      */   
/*      */ 
/*      */   public AttributeDescriptor[] getAttributesDescriptor()
/*      */   {
/*  381 */     return this.attributesDescriptor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   byte[] toBytes(STRUCT paramSTRUCT, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  393 */     Object localObject1 = paramSTRUCT.shareBytes();
/*      */     Object localObject2;
/*  395 */     if (localObject1 == null)
/*      */     {
/*  397 */       if (paramSTRUCT.datumArray != null)
/*      */       {
/*  399 */         localObject1 = this.pickler.linearize(paramSTRUCT);
/*      */         
/*  401 */         if (!paramBoolean) {
/*  402 */           paramSTRUCT.setShareBytes(null);
/*      */         }
/*      */       }
/*  405 */       else if (paramSTRUCT.objectArray != null)
/*      */       {
/*  407 */         paramSTRUCT.datumArray = toOracleArray(paramSTRUCT.objectArray);
/*  408 */         localObject1 = this.pickler.linearize(paramSTRUCT);
/*      */         
/*  410 */         if (!paramBoolean)
/*      */         {
/*  412 */           paramSTRUCT.datumArray = null;
/*      */           
/*  414 */           paramSTRUCT.setShareBytes(null);
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/*  420 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  421 */         ((SQLException)localObject2).fillInStackTrace();
/*  422 */         throw ((Throwable)localObject2);
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*  429 */     else if (paramSTRUCT.imageLength != 0L)
/*      */     {
/*  431 */       if ((paramSTRUCT.imageOffset != 0L) || (paramSTRUCT.imageLength != localObject1.length))
/*      */       {
/*  433 */         localObject2 = new byte[(int)paramSTRUCT.imageLength];
/*      */         
/*  435 */         System.arraycopy(localObject1, (int)paramSTRUCT.imageOffset, localObject2, 0, (int)paramSTRUCT.imageLength);
/*      */         
/*  437 */         paramSTRUCT.setImage((byte[])localObject2, 0L, 0L);
/*      */         
/*  439 */         localObject1 = localObject2;
/*      */       }
/*      */     }
/*      */     
/*  443 */     return (byte[])localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Datum[] toOracleArray(STRUCT paramSTRUCT, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  451 */     Datum[] arrayOfDatum1 = paramSTRUCT.datumArray;
/*  452 */     Datum[] arrayOfDatum2 = null;
/*      */     
/*  454 */     if (arrayOfDatum1 == null)
/*      */     {
/*  456 */       if (paramSTRUCT.objectArray != null)
/*      */       {
/*  458 */         arrayOfDatum1 = toOracleArray(paramSTRUCT.objectArray);
/*      */       }
/*  460 */       else if (paramSTRUCT.shareBytes() != null)
/*      */       {
/*  462 */         if (((paramSTRUCT.shareBytes()[0] & 0x80) <= 0) && (((OracleTypeADT)this.pickler).isEmbeddedADT()))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  467 */           this.pickler = OracleTypeADT.shallowClone((OracleTypeADT)this.pickler);
/*      */         }
/*      */         
/*      */ 
/*  471 */         this.pickler.unlinearize(paramSTRUCT.shareBytes(), paramSTRUCT.imageOffset, paramSTRUCT, 1, null);
/*      */         
/*  473 */         arrayOfDatum1 = paramSTRUCT.datumArray;
/*      */         
/*  475 */         if (!paramBoolean) {
/*  476 */           paramSTRUCT.datumArray = null;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  481 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  482 */         localSQLException.fillInStackTrace();
/*  483 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  489 */     if (paramBoolean)
/*      */     {
/*  491 */       paramSTRUCT.datumArray = arrayOfDatum1;
/*  492 */       arrayOfDatum2 = (Datum[])arrayOfDatum1.clone();
/*      */     }
/*      */     else
/*      */     {
/*  496 */       arrayOfDatum2 = arrayOfDatum1;
/*      */     }
/*  498 */     return arrayOfDatum2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Object[] toArray(STRUCT paramSTRUCT, Map paramMap, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  508 */     Object[] arrayOfObject = null;
/*      */     
/*  510 */     if (paramSTRUCT.objectArray == null)
/*      */     {
/*  512 */       if (paramSTRUCT.datumArray != null)
/*      */       {
/*  514 */         arrayOfObject = new Object[paramSTRUCT.datumArray.length];
/*      */         
/*  516 */         for (int i = 0; i < paramSTRUCT.datumArray.length; i++)
/*      */         {
/*  518 */           if (paramSTRUCT.datumArray[i] != null)
/*      */           {
/*  520 */             if ((paramSTRUCT.datumArray[i] instanceof STRUCT)) {
/*  521 */               arrayOfObject[i] = ((STRUCT)paramSTRUCT.datumArray[i]).toJdbc(paramMap);
/*      */             } else {
/*  523 */               arrayOfObject[i] = paramSTRUCT.datumArray[i].toJdbc();
/*      */             }
/*      */           }
/*      */         }
/*  527 */       } else if (paramSTRUCT.shareBytes() != null)
/*      */       {
/*  529 */         if (((paramSTRUCT.shareBytes()[0] & 0x80) <= 0) && (((OracleTypeADT)this.pickler).isEmbeddedADT()))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  534 */           this.pickler = OracleTypeADT.shallowClone((OracleTypeADT)this.pickler);
/*      */         }
/*      */         
/*  537 */         this.pickler.unlinearize(paramSTRUCT.shareBytes(), paramSTRUCT.imageOffset, paramSTRUCT, 2, paramMap);
/*      */         
/*  539 */         arrayOfObject = paramSTRUCT.objectArray;
/*      */         
/*      */ 
/*  542 */         paramSTRUCT.objectArray = null;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  548 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  549 */         localSQLException.fillInStackTrace();
/*  550 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  557 */       arrayOfObject = (Object[])paramSTRUCT.objectArray.clone();
/*      */     }
/*  559 */     return arrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getLength()
/*      */     throws SQLException
/*      */   {
/*  571 */     return getFieldTypes().length;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeADT getOracleTypeADT()
/*      */     throws SQLException
/*      */   {
/*  582 */     if (this.pickler == null)
/*  583 */       initPickler();
/*  584 */     OracleTypeADT localOracleTypeADT = (OracleTypeADT)this.pickler;
/*  585 */     return localOracleTypeADT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private OracleType[] getFieldTypes()
/*      */     throws SQLException
/*      */   {
/*  596 */     return ((OracleTypeADT)this.pickler).getAttrTypes();
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
/*      */   public SQLInput toJdbc2SQLInput(STRUCT paramSTRUCT, Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  611 */     return new OracleJdbc2SQLInput(toOracleArray(paramSTRUCT, false), paramMap, this.connection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLOutput toJdbc2SQLOutput()
/*      */     throws SQLException
/*      */   {
/*  624 */     return new OracleSQLOutput(this, this.connection);
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
/*      */   public Datum[] toOracleArray(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/*  640 */     Datum[] arrayOfDatum = null;
/*      */     
/*  642 */     if (paramArrayOfObject != null)
/*      */     {
/*  644 */       OracleType[] arrayOfOracleType = getFieldTypes();
/*  645 */       int i = arrayOfOracleType.length;
/*      */       
/*      */ 
/*  648 */       if (paramArrayOfObject.length != i)
/*      */       {
/*      */ 
/*  651 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 49, null);
/*  652 */         ((SQLException)localObject).fillInStackTrace();
/*  653 */         throw ((Throwable)localObject);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  658 */       arrayOfDatum = new Datum[i];
/*      */       
/*  660 */       Object localObject = this.connection;
/*      */       
/*      */ 
/*  663 */       for (int j = 0; j < i; j++) {
/*  664 */         arrayOfDatum[j] = arrayOfOracleType[j].toDatum(paramArrayOfObject[j], (oracle.jdbc.internal.OracleConnection)localObject);
/*      */       }
/*      */     }
/*  667 */     return arrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */   public Datum[] toOracleArray(Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  674 */     Datum[] arrayOfDatum = null;
/*  675 */     int i = 0;
/*      */     
/*  677 */     if (paramMap != null)
/*      */     {
/*  679 */       OracleType[] arrayOfOracleType = getFieldTypes();
/*  680 */       int j = arrayOfOracleType.length;
/*  681 */       int k = paramMap.size();
/*      */       
/*      */ 
/*  684 */       arrayOfDatum = new Datum[j];
/*      */       
/*  686 */       oracle.jdbc.internal.OracleConnection localOracleConnection = this.connection;
/*      */       
/*      */ 
/*  689 */       for (int m = 0; m < j; m++)
/*      */       {
/*  691 */         Object localObject = paramMap.get(((OracleTypeADT)this.pickler).getAttributeName(m + 1));
/*      */         
/*      */ 
/*  694 */         arrayOfDatum[m] = arrayOfOracleType[m].toDatum(localObject, localOracleConnection);
/*      */         
/*  696 */         if ((localObject != null) || (paramMap.containsKey(((OracleTypeADT)this.pickler).getAttributeName(m + 1))))
/*      */         {
/*      */ 
/*  699 */           i++;
/*      */         }
/*      */       }
/*  702 */       if (i < k)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  710 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 68, null);
/*  711 */         localSQLException.fillInStackTrace();
/*  712 */         throw localSQLException;
/*      */       }
/*      */     }
/*      */     
/*  716 */     return arrayOfDatum;
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
/*      */   public ResultSetMetaData getMetaData()
/*      */     throws SQLException
/*      */   {
/*  733 */     return this.connection.newStructMetaData(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isFinalType()
/*      */     throws SQLException
/*      */   {
/*  745 */     return getOracleTypeADT().isFinalType();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSubtype()
/*      */     throws SQLException
/*      */   {
/*  757 */     return getOracleTypeADT().isSubType();
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
/*      */   public boolean isInHierarchyOf(String paramString)
/*      */     throws SQLException
/*      */   {
/*  771 */     StructDescriptor localStructDescriptor = this;
/*  772 */     String str = localStructDescriptor.getName();
/*  773 */     boolean bool = false;
/*      */     
/*  775 */     if (paramString.equals(str)) {
/*  776 */       bool = true;
/*      */     }
/*      */     else {
/*      */       for (;;)
/*      */       {
/*  781 */         str = localStructDescriptor.getSupertypeName();
/*      */         
/*  783 */         if (str == null)
/*      */         {
/*  785 */           bool = false;
/*      */           
/*  787 */           break;
/*      */         }
/*      */         
/*  790 */         if (paramString.equals(str))
/*      */         {
/*  792 */           bool = true;
/*      */           
/*  794 */           break;
/*      */         }
/*      */         
/*  797 */         localStructDescriptor = createDescriptor(str, this.connection);
/*      */       }
/*      */     }
/*      */     
/*  801 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isInstantiable()
/*      */     throws SQLException
/*      */   {
/*  813 */     if (this.isInstanciable == null)
/*  814 */       this.isInstanciable = getOracleTypeADT().isInstanciable();
/*  815 */     return this.isInstanciable.booleanValue();
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
/*      */   public boolean isJavaObject()
/*      */     throws SQLException
/*      */   {
/*  829 */     return getOracleTypeADT().isJavaObject();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getSupertypeName()
/*      */     throws SQLException
/*      */   {
/*  842 */     String str = null;
/*      */     
/*  844 */     if (isSubtype())
/*      */     {
/*  846 */       if (this.supertype == null) {
/*  847 */         this.supertype = getOracleTypeADT().getSuperTypeName();
/*      */       }
/*  849 */       str = this.supertype;
/*      */     }
/*      */     
/*  852 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getLocalAttributeCount()
/*      */     throws SQLException
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*  866 */     if (!isSubtype()) {
/*  867 */       i = getOracleTypeADT().getAttrTypes().length;
/*      */     }
/*      */     else {
/*  870 */       if (this.numLocalAttrs == -1) {
/*  871 */         this.numLocalAttrs = getOracleTypeADT().getNumberOfLocalAttributes();
/*      */       }
/*  873 */       i = this.numLocalAttrs;
/*      */     }
/*  875 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] getSubtypeNames()
/*      */     throws SQLException
/*      */   {
/*  888 */     if (this.subtypes == null)
/*  889 */       this.subtypes = getOracleTypeADT().getSubtypeNames();
/*  890 */     return this.subtypes;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getJavaClassName()
/*      */     throws SQLException
/*      */   {
/*  903 */     String str = null;
/*      */     
/*  905 */     if (isJavaObject()) {
/*  906 */       str = getJavaObjectClassName(this.connection, this);
/*      */     }
/*  908 */     return str;
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
/*      */   public String getAttributeJavaName(int paramInt)
/*      */     throws SQLException
/*      */   {
/*  922 */     String str = null;
/*      */     
/*  924 */     if (isJavaObject())
/*      */     {
/*  926 */       if (this.attrJavaNames == null) {
/*  927 */         initMetaData3();
/*      */       }
/*  929 */       str = this.attrJavaNames[paramInt];
/*      */     }
/*  931 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String[] getAttributeJavaNames()
/*      */     throws SQLException
/*      */   {
/*  944 */     String[] arrayOfString = null;
/*      */     
/*  946 */     if (isJavaObject())
/*      */     {
/*  948 */       if (this.attrJavaNames == null) {
/*  949 */         initMetaData3();
/*      */       }
/*  951 */       arrayOfString = this.attrJavaNames;
/*      */     }
/*      */     else
/*      */     {
/*  955 */       arrayOfString = new String[0];
/*      */     }
/*  957 */     return arrayOfString;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getLanguage()
/*      */     throws SQLException
/*      */   {
/*  968 */     String str = null;
/*      */     
/*  970 */     if (isJavaObject()) {
/*  971 */       str = "JAVA";
/*      */     } else {
/*  973 */       str = "SQL";
/*      */     }
/*  975 */     return str;
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
/*      */   public Class getClass(Map paramMap)
/*      */     throws SQLException
/*      */   {
/*  990 */     String str1 = getName();
/*      */     
/*      */ 
/*  993 */     Class localClass = this.connection.getClassForType(str1, paramMap);
/*      */     
/*  995 */     String str2 = getSchemaName();
/*  996 */     String str3 = getTypeName();
/*      */     
/*  998 */     if (localClass == null)
/*      */     {
/* 1000 */       localClass = (Class)paramMap.get(str3);
/*      */     }
/*      */     
/* 1003 */     if (SQLName.s_parseAllFormat)
/*      */     {
/* 1005 */       if (localClass == null)
/*      */       {
/* 1007 */         if (this.connection.getDefaultSchemaNameForNamedTypes().equals(str2)) {
/* 1008 */           localClass = (Class)paramMap.get("\"" + str3 + "\"");
/*      */         }
/*      */       }
/* 1011 */       if (localClass == null)
/*      */       {
/* 1013 */         localClass = (Class)paramMap.get("\"" + str2 + "\"" + "." + "\"" + str3 + "\"");
/*      */       }
/*      */       
/* 1016 */       if (localClass == null)
/*      */       {
/* 1018 */         localClass = (Class)paramMap.get("\"" + str2 + "\"" + "." + str3);
/*      */       }
/*      */       
/* 1021 */       if (localClass == null)
/*      */       {
/* 1023 */         localClass = (Class)paramMap.get(str2 + "." + "\"" + str3 + "\"");
/*      */       }
/*      */     }
/*      */     
/* 1027 */     return localClass;
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
/*      */   public static String getJavaObjectClassName(Connection paramConnection, StructDescriptor paramStructDescriptor)
/*      */     throws SQLException
/*      */   {
/* 1042 */     return getJavaObjectClassName(paramConnection, paramStructDescriptor.getSchemaName(), paramStructDescriptor.getTypeName());
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
/*      */   public static String getJavaObjectClassName(Connection paramConnection, String paramString1, String paramString2)
/*      */     throws SQLException
/*      */   {
/* 1058 */     PreparedStatement localPreparedStatement = null;
/* 1059 */     ResultSet localResultSet = null;
/*      */     
/* 1061 */     String str = null;
/*      */     
/*      */     try
/*      */     {
/* 1065 */       localPreparedStatement = paramConnection.prepareStatement("select external_name from all_sqlj_types where owner = :1 and type_name = :2");
/*      */       
/*      */ 
/* 1068 */       localPreparedStatement.setString(1, paramString1);
/* 1069 */       localPreparedStatement.setString(2, paramString2);
/*      */       
/* 1071 */       localResultSet = localPreparedStatement.executeQuery();
/*      */       
/* 1073 */       if (localResultSet.next()) {
/* 1074 */         str = localResultSet.getString(1);
/*      */       }
/*      */       else
/*      */       {
/* 1078 */         SQLException localSQLException1 = DatabaseError.createSqlException(null, 100);
/* 1079 */         localSQLException1.fillInStackTrace();
/* 1080 */         throw localSQLException1;
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*      */     catch (SQLException localSQLException2) {}finally
/*      */     {
/*      */ 
/*      */ 
/* 1091 */       if (localResultSet != null) {
/* 1092 */         localResultSet.close();
/*      */       }
/* 1094 */       if (localPreparedStatement != null)
/* 1095 */         localPreparedStatement.close();
/*      */     }
/* 1097 */     return str;
/*      */   }
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
/* 1109 */     StringBuffer localStringBuffer = new StringBuffer();
/*      */     
/* 1111 */     return descType(localStringBuffer, 0);
/*      */   }
/*      */   
/*      */ 
/*      */   String descType(StringBuffer paramStringBuffer, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1118 */     String str1 = "";
/*      */     
/* 1120 */     for (int i = 0; i < paramInt; i++) {
/* 1121 */       str1 = str1 + "  ";
/*      */     }
/* 1123 */     String str2 = str1 + "  ";
/*      */     
/* 1125 */     paramStringBuffer.append(str1);
/* 1126 */     paramStringBuffer.append(getTypeName());
/* 1127 */     paramStringBuffer.append("\n");
/* 1128 */     paramStringBuffer.append(str1);
/* 1129 */     paramStringBuffer.append("Subtype=" + getOracleTypeADT().isSubType());
/* 1130 */     paramStringBuffer.append(" JavaObject=" + getOracleTypeADT().isJavaObject());
/* 1131 */     paramStringBuffer.append(" FinalType=" + getOracleTypeADT().isFinalType());
/* 1132 */     paramStringBuffer.append("\n");
/*      */     
/* 1134 */     ResultSetMetaData localResultSetMetaData = getMetaData();
/* 1135 */     int j = localResultSetMetaData.getColumnCount();
/*      */     
/* 1137 */     for (int k = 0; k < j; k++)
/*      */     {
/* 1139 */       int m = localResultSetMetaData.getColumnType(k + 1);
/*      */       Object localObject;
/* 1141 */       if ((m == 2002) || (m == 2008))
/*      */       {
/*      */ 
/* 1144 */         localObject = createDescriptor(localResultSetMetaData.getColumnTypeName(k + 1), this.connection);
/*      */         
/*      */ 
/*      */ 
/* 1148 */         ((StructDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */       }
/* 1150 */       else if (m == 2003)
/*      */       {
/* 1152 */         localObject = ArrayDescriptor.createDescriptor(localResultSetMetaData.getColumnTypeName(k + 1), this.connection);
/*      */         
/*      */ 
/*      */ 
/* 1156 */         ((ArrayDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */       }
/* 1158 */       else if (m == 2007)
/*      */       {
/* 1160 */         localObject = OpaqueDescriptor.createDescriptor(localResultSetMetaData.getColumnTypeName(k + 1), this.connection);
/*      */         
/*      */ 
/*      */ 
/* 1164 */         ((OpaqueDescriptor)localObject).descType(paramStringBuffer, paramInt + 1);
/*      */       }
/*      */       else
/*      */       {
/* 1168 */         paramStringBuffer.append(str2);
/* 1169 */         paramStringBuffer.append(localResultSetMetaData.getColumnTypeName(k + 1));
/* 1170 */         paramStringBuffer.append("\n");
/*      */       }
/*      */     }
/*      */     
/* 1174 */     return paramStringBuffer.substring(0, paramStringBuffer.length());
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
/*      */   public byte[] toBytes(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 1197 */     Datum[] arrayOfDatum = toOracleArray(paramArrayOfObject);
/*      */     
/* 1199 */     return toBytes(arrayOfDatum);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public byte[] toBytes(Datum[] paramArrayOfDatum)
/*      */     throws SQLException
/*      */   {
/* 1219 */     STRUCT localSTRUCT = new STRUCT(this, (byte[])null, this.connection);
/*      */     
/* 1221 */     localSTRUCT.setDatumArray(paramArrayOfDatum);
/*      */     
/* 1223 */     return this.pickler.linearize(localSTRUCT);
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
/*      */   public Datum[] toArray(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/* 1239 */     return toOracleArray(paramArrayOfObject);
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
/* 1253 */     STRUCT localSTRUCT = new STRUCT(this, paramArrayOfByte, this.connection);
/*      */     
/* 1255 */     return toOracleArray(localSTRUCT, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initMetaData3()
/*      */     throws SQLException
/*      */   {
/* 1265 */     synchronized (this.connection)
/*      */     {
/* 1267 */       if (this.attrJavaNames == null)
/*      */       {
/* 1269 */         String[] arrayOfString = null;
/* 1270 */         PreparedStatement localPreparedStatement = null;
/* 1271 */         ResultSet localResultSet = null;
/*      */         
/*      */         try
/*      */         {
/* 1275 */           localPreparedStatement = this.connection.prepareStatement("select EXTERNAL_ATTR_NAME from all_sqlj_type_attrs where owner = :1 and type_name = :2");
/*      */           
/*      */ 
/* 1278 */           localPreparedStatement.setString(1, getSchemaName());
/* 1279 */           localPreparedStatement.setString(2, getTypeName());
/*      */           
/* 1281 */           localResultSet = localPreparedStatement.executeQuery();
/* 1282 */           arrayOfString = new String[getOracleTypeADT().getAttrTypes().length];
/*      */           
/*      */ 
/* 1285 */           for (int i = 0; localResultSet.next(); i++) {
/* 1286 */             arrayOfString[i] = localResultSet.getString(1);
/*      */           }
/*      */         }
/*      */         finally {
/* 1290 */           if (localResultSet != null) {
/* 1291 */             localResultSet.close();
/*      */           }
/* 1293 */           if (localPreparedStatement != null) {
/* 1294 */             localPreparedStatement.close();
/*      */           }
/*      */         }
/* 1297 */         this.attrJavaNames = arrayOfString;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   String tagName()
/*      */   {
/* 1306 */     return "StructDescriptor";
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
/* 1327 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
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
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/StructDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */