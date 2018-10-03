/*      */ package oracle.jpub.runtime;
/*      */ 
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.Date;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.sql.ARRAY;
/*      */ import oracle.sql.ArrayDescriptor;
/*      */ import oracle.sql.BFILE;
/*      */ import oracle.sql.BINARY_DOUBLE;
/*      */ import oracle.sql.BINARY_FLOAT;
/*      */ import oracle.sql.BLOB;
/*      */ import oracle.sql.CHAR;
/*      */ import oracle.sql.CLOB;
/*      */ import oracle.sql.CustomDatum;
/*      */ import oracle.sql.CustomDatumFactory;
/*      */ import oracle.sql.DATE;
/*      */ import oracle.sql.Datum;
/*      */ import oracle.sql.INTERVALDS;
/*      */ import oracle.sql.INTERVALYM;
/*      */ import oracle.sql.NCLOB;
/*      */ import oracle.sql.NUMBER;
/*      */ import oracle.sql.ORAData;
/*      */ import oracle.sql.ORADataFactory;
/*      */ import oracle.sql.RAW;
/*      */ import oracle.sql.TIMESTAMP;
/*      */ import oracle.sql.TIMESTAMPLTZ;
/*      */ import oracle.sql.TIMESTAMPTZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class MutableArray
/*      */ {
/*      */   int length;
/*      */   Object[] elements;
/*      */   Datum[] datums;
/*      */   ARRAY pickled;
/*      */   boolean pickledCorrect;
/*      */   int sqlType;
/*      */   ORADataFactory factory;
/*      */   CustomDatumFactory old_factory;
/*      */   boolean isNChar;
/*      */   
/*      */   public MutableArray(int paramInt, ARRAY paramARRAY, ORADataFactory paramORADataFactory)
/*      */   {
/*   75 */     this.length = -1;
/*   76 */     this.elements = null;
/*   77 */     this.datums = null;
/*   78 */     this.pickled = paramARRAY;
/*   79 */     this.pickledCorrect = true;
/*   80 */     this.isNChar = false;
/*   81 */     this.sqlType = paramInt;
/*   82 */     this.factory = paramORADataFactory;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, Datum[] paramArrayOfDatum, ORADataFactory paramORADataFactory)
/*      */   {
/*   89 */     this.sqlType = paramInt;
/*   90 */     this.factory = paramORADataFactory;
/*   91 */     this.isNChar = false;
/*      */     
/*   93 */     setDatumArray(paramArrayOfDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, Object[] paramArrayOfObject, ORADataFactory paramORADataFactory)
/*      */   {
/*  100 */     this.sqlType = paramInt;
/*  101 */     this.factory = paramORADataFactory;
/*  102 */     this.isNChar = false;
/*      */     
/*  104 */     setObjectArray(paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, double[] paramArrayOfDouble, ORADataFactory paramORADataFactory)
/*      */   {
/*  111 */     this.sqlType = paramInt;
/*  112 */     this.factory = paramORADataFactory;
/*  113 */     this.isNChar = false;
/*      */     
/*  115 */     setArray(paramArrayOfDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, int[] paramArrayOfInt, ORADataFactory paramORADataFactory)
/*      */   {
/*  122 */     this.sqlType = paramInt;
/*  123 */     this.factory = paramORADataFactory;
/*  124 */     this.isNChar = false;
/*      */     
/*  126 */     setArray(paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, float[] paramArrayOfFloat, ORADataFactory paramORADataFactory)
/*      */   {
/*  133 */     this.sqlType = paramInt;
/*  134 */     this.factory = paramORADataFactory;
/*  135 */     this.isNChar = false;
/*      */     
/*  137 */     setArray(paramArrayOfFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int paramInt, short[] paramArrayOfShort, ORADataFactory paramORADataFactory)
/*      */   {
/*  144 */     this.sqlType = paramInt;
/*  145 */     this.factory = paramORADataFactory;
/*  146 */     this.isNChar = false;
/*      */     
/*  148 */     setArray(paramArrayOfShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(ARRAY paramARRAY, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  155 */     this.length = -1;
/*  156 */     this.elements = null;
/*  157 */     this.datums = null;
/*  158 */     this.pickled = paramARRAY;
/*  159 */     this.pickledCorrect = true;
/*  160 */     this.sqlType = paramInt;
/*  161 */     this.old_factory = paramCustomDatumFactory;
/*  162 */     this.isNChar = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(Datum[] paramArrayOfDatum, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  169 */     this.sqlType = paramInt;
/*  170 */     this.old_factory = paramCustomDatumFactory;
/*  171 */     this.isNChar = false;
/*      */     
/*  173 */     setDatumArray(paramArrayOfDatum);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(Object[] paramArrayOfObject, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  180 */     this.sqlType = paramInt;
/*  181 */     this.old_factory = paramCustomDatumFactory;
/*  182 */     this.isNChar = false;
/*      */     
/*  184 */     setObjectArray(paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(double[] paramArrayOfDouble, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  191 */     this.sqlType = paramInt;
/*  192 */     this.old_factory = paramCustomDatumFactory;
/*  193 */     this.isNChar = false;
/*      */     
/*  195 */     setArray(paramArrayOfDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(int[] paramArrayOfInt, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  202 */     this.sqlType = paramInt;
/*  203 */     this.old_factory = paramCustomDatumFactory;
/*  204 */     this.isNChar = false;
/*      */     
/*  206 */     setArray(paramArrayOfInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(float[] paramArrayOfFloat, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  213 */     this.sqlType = paramInt;
/*  214 */     this.old_factory = paramCustomDatumFactory;
/*  215 */     this.isNChar = false;
/*      */     
/*  217 */     setArray(paramArrayOfFloat);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public MutableArray(short[] paramArrayOfShort, int paramInt, CustomDatumFactory paramCustomDatumFactory)
/*      */   {
/*  224 */     this.sqlType = paramInt;
/*  225 */     this.old_factory = paramCustomDatumFactory;
/*  226 */     this.isNChar = false;
/*      */     
/*  228 */     setArray(paramArrayOfShort);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Datum toDatum(Connection paramConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/*  237 */     if (!this.pickledCorrect)
/*      */     {
/*      */ 
/*      */ 
/*  241 */       this.pickled = new ARRAY(ArrayDescriptor.createDescriptor(paramString, paramConnection), paramConnection, getDatumArray(paramConnection));
/*      */       
/*  243 */       this.pickledCorrect = true;
/*      */     }
/*  245 */     return this.pickled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Datum toDatum(oracle.jdbc.OracleConnection paramOracleConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/*  253 */     return toDatum(paramOracleConnection, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Datum toDatum(oracle.jdbc.driver.OracleConnection paramOracleConnection, String paramString)
/*      */     throws SQLException
/*      */   {
/*  264 */     return toDatum(paramOracleConnection, paramString);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object[] getOracleArray()
/*      */     throws SQLException
/*      */   {
/*  272 */     return getOracleArray(0L, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object[] getOracleArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  283 */     int i = sliceLength(paramLong, paramInt);
/*      */     
/*  285 */     if (i < 0) {
/*  286 */       return null;
/*      */     }
/*  288 */     Object localObject = null;
/*      */     
/*  290 */     switch (this.sqlType)
/*      */     {
/*      */ 
/*      */     case -13: 
/*  294 */       localObject = new BFILE[i];
/*      */       
/*  296 */       break;
/*      */     
/*      */     case 2004: 
/*  299 */       localObject = new BLOB[i];
/*      */       
/*  301 */       break;
/*      */     
/*      */ 
/*      */     case 1: 
/*      */     case 12: 
/*  306 */       localObject = new CHAR[i];
/*      */       
/*  308 */       break;
/*      */     
/*      */     case 2005: 
/*  311 */       localObject = new CLOB[i];
/*      */       
/*  313 */       break;
/*      */     
/*      */     case 91: 
/*  316 */       localObject = new DATE[i];
/*      */       
/*  318 */       break;
/*      */     
/*      */     case 93: 
/*  321 */       localObject = new TIMESTAMP[i];
/*      */       
/*  323 */       break;
/*      */     
/*      */     case -101: 
/*  326 */       localObject = new TIMESTAMPTZ[i];
/*      */       
/*  328 */       break;
/*      */     
/*      */     case -102: 
/*  331 */       localObject = new TIMESTAMPLTZ[i];
/*      */       
/*  333 */       break;
/*      */     
/*      */     case -104: 
/*  336 */       localObject = new INTERVALDS[i];
/*      */       
/*  338 */       break;
/*      */     
/*      */     case -103: 
/*  341 */       localObject = new INTERVALYM[i];
/*      */       
/*  343 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/*  358 */       localObject = new NUMBER[i];
/*      */       
/*  360 */       break;
/*      */     
/*      */     case -2: 
/*  363 */       localObject = new RAW[i];
/*      */       
/*  365 */       break;
/*      */     
/*      */     case 100: 
/*  368 */       localObject = new BINARY_FLOAT[i];
/*      */       
/*  370 */       break;
/*      */     
/*      */     case 101: 
/*  373 */       localObject = new BINARY_DOUBLE[i];
/*      */       
/*  375 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 0: 
/*      */     case 2002: 
/*      */     case 2003: 
/*      */     case 2006: 
/*      */     case 2007: 
/*  386 */       if (this.old_factory == null)
/*      */       {
/*  388 */         localObject = new ORAData[i];
/*      */       }
/*      */       else
/*      */       {
/*  392 */         localObject = new CustomDatum[i];
/*      */       }
/*      */       
/*  395 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case -15: 
/*      */     case -9: 
/*  402 */       setNChar();
/*  403 */       localObject = new CHAR[i];
/*  404 */       break;
/*      */     
/*      */     case 2011: 
/*  407 */       localObject = new NCLOB[i];
/*  408 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     default: 
/*  414 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 48);
/*  415 */       localSQLException.fillInStackTrace();
/*  416 */       throw localSQLException;
/*      */     }
/*      */     
/*      */     
/*  420 */     return getOracleArray(paramLong, (Object[])localObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object[] getOracleArray(long paramLong, Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/*  427 */     if (paramArrayOfObject == null) {
/*  428 */       return null;
/*      */     }
/*  430 */     int i = sliceLength(paramLong, paramArrayOfObject.length);
/*  431 */     int j = (int)paramLong;
/*      */     
/*  433 */     if (i != paramArrayOfObject.length)
/*  434 */       return null;
/*      */     int k;
/*  436 */     if ((this.sqlType == 2002) || (this.sqlType == 2007) || (this.sqlType == 2003) || (this.sqlType == 2006) || (this.sqlType == 0))
/*      */     {
/*      */ 
/*      */ 
/*  440 */       if (this.old_factory == null)
/*      */       {
/*  442 */         for (k = 0; k < i; k++) {
/*  443 */           paramArrayOfObject[k] = this.factory.create(getDatumElement(j++, null), this.sqlType);
/*      */         }
/*      */         
/*      */       } else {
/*  447 */         for (k = 0; k < i; k++) {
/*  448 */           paramArrayOfObject[k] = this.old_factory.create(getDatumElement(j++, null), this.sqlType);
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*  453 */       for (k = 0; k < i; k++)
/*  454 */         paramArrayOfObject[k] = getDatumElement(j++, null);
/*      */     }
/*  456 */     return paramArrayOfObject;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object[] getOracleArray(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/*  463 */     return getOracleArray(0L, paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object[] getObjectArray()
/*      */     throws SQLException
/*      */   {
/*  470 */     return getObjectArray(0L, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object[] getObjectArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  477 */     int i = sliceLength(paramLong, paramInt);
/*      */     
/*  479 */     if (i < 0) {
/*  480 */       return null;
/*      */     }
/*      */     
/*      */     Object localObject;
/*  484 */     switch (this.sqlType)
/*      */     {
/*      */ 
/*      */ 
/*      */     case 1: 
/*      */     case 12: 
/*  490 */       localObject = new String[i];
/*      */       
/*  492 */       break;
/*      */     
/*      */     case 91: 
/*  495 */       localObject = new Date[i];
/*      */       
/*  497 */       break;
/*      */     
/*      */     case 93: 
/*  500 */       localObject = new Timestamp[i];
/*      */       
/*  502 */       break;
/*      */     
/*      */ 
/*      */     case 2: 
/*      */     case 3: 
/*  507 */       localObject = new BigDecimal[i];
/*      */       
/*  509 */       break;
/*      */     
/*      */ 
/*      */     case 6: 
/*      */     case 8: 
/*  514 */       localObject = new Double[i];
/*      */       
/*  516 */       break;
/*      */     
/*      */ 
/*      */     case 4: 
/*      */     case 5: 
/*  521 */       localObject = new Integer[i];
/*      */       
/*  523 */       break;
/*      */     
/*      */     case 7: 
/*  526 */       localObject = new Float[i];
/*      */       
/*  528 */       break;
/*      */     
/*      */     case -2: 
/*  531 */       localObject = new byte[i][];
/*      */       
/*  533 */       break;
/*      */     
/*      */     default: 
/*  536 */       return getOracleArray(paramLong, paramInt);
/*      */     }
/*      */     
/*  539 */     return getObjectArray(paramLong, (Object[])localObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object[] getObjectArray(long paramLong, Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/*  546 */     if (paramArrayOfObject == null) {
/*  547 */       return null;
/*      */     }
/*  549 */     int i = sliceLength(paramLong, paramArrayOfObject.length);
/*  550 */     int j = (int)paramLong;
/*      */     
/*  552 */     if (i != paramArrayOfObject.length) {
/*  553 */       return null;
/*      */     }
/*  555 */     switch (this.sqlType)
/*      */     {
/*      */ 
/*      */ 
/*      */     case -2: 
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     case 4: 
/*      */     case 5: 
/*      */     case 6: 
/*      */     case 7: 
/*      */     case 8: 
/*      */     case 12: 
/*      */     case 91: 
/*      */     case 93: 
/*  571 */       for (int k = 0; k < i; k++) {
/*  572 */         paramArrayOfObject[k] = getObjectElement(j++);
/*      */       }
/*  574 */       return paramArrayOfObject;
/*      */     }
/*      */     
/*      */     
/*  578 */     return getOracleArray(paramLong, paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object[] getObjectArray(Object[] paramArrayOfObject)
/*      */     throws SQLException
/*      */   {
/*  586 */     return getObjectArray(0L, paramArrayOfObject);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getArray()
/*      */     throws SQLException
/*      */   {
/*  593 */     return getArray(0L, Integer.MAX_VALUE);
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getArray(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/*  600 */     int i = sliceLength(paramLong, paramInt);
/*  601 */     int j = (int)paramLong;
/*      */     
/*  603 */     if (i < 0)
/*  604 */       return null;
/*      */     Object localObject;
/*  606 */     int k; switch (this.sqlType)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 6: 
/*      */     case 8: 
/*      */     case 101: 
/*  615 */       localObject = new double[i];
/*      */       
/*  617 */       for (k = 0; k < i; k++) {
/*  618 */         localObject[k] = ((Double)getObjectElement(j++)).doubleValue();
/*      */       }
/*  620 */       return localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 100: 
/*  625 */       localObject = new float[i];
/*      */       
/*  627 */       for (k = 0; k < i; k++) {
/*  628 */         localObject[k] = ((Float)getObjectElement(j++)).floatValue();
/*      */       }
/*  630 */       return localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 4: 
/*  635 */       localObject = new int[i];
/*      */       
/*  637 */       for (k = 0; k < i; k++) {
/*  638 */         localObject[k] = ((Integer)getObjectElement(j++)).intValue();
/*      */       }
/*  640 */       return localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 5: 
/*  645 */       localObject = new short[i];
/*      */       
/*  647 */       for (k = 0; k < i; k++) {
/*  648 */         localObject[k] = ((short)((Integer)getObjectElement(j++)).intValue());
/*      */       }
/*  650 */       return localObject;
/*      */     
/*      */ 
/*      */ 
/*      */     case 7: 
/*  655 */       localObject = new float[i];
/*      */       
/*  657 */       for (k = 0; k < i; k++) {
/*  658 */         localObject[k] = ((Float)getObjectElement(j++)).floatValue();
/*      */       }
/*  660 */       return localObject;
/*      */     }
/*      */     
/*      */     
/*  664 */     return getObjectArray(paramLong, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOracleArray(Object[] paramArrayOfObject)
/*      */   {
/*  672 */     if ((this.factory == null) && (this.old_factory == null)) {
/*  673 */       setDatumArray((Datum[])paramArrayOfObject);
/*      */     } else {
/*  675 */       setObjectArray(paramArrayOfObject);
/*      */     }
/*      */   }
/*      */   
/*      */   public void setOracleArray(Object[] paramArrayOfObject, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  682 */     if ((this.factory == null) && (this.old_factory == null)) {
/*  683 */       setDatumArray((Datum[])paramArrayOfObject, paramLong);
/*      */     } else {
/*  685 */       setObjectArray(paramArrayOfObject, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void setObjectArray(Object[] paramArrayOfObject)
/*      */   {
/*  692 */     if (paramArrayOfObject == null) {
/*  693 */       setNullArray();
/*      */     }
/*      */     else {
/*  696 */       setArrayGeneric(paramArrayOfObject.length);
/*      */       
/*  698 */       this.elements = new Object[this.length];
/*      */       
/*  700 */       for (int i = 0; i < this.length; i++) {
/*  701 */         this.elements[i] = paramArrayOfObject[i];
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setObjectArray(Object[] paramArrayOfObject, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  709 */     if (paramArrayOfObject == null) {
/*  710 */       return;
/*      */     }
/*  712 */     int i = sliceLength(paramLong, paramArrayOfObject.length);
/*  713 */     int j = (int)paramLong;
/*      */     
/*  715 */     for (int k = 0; k < i; k++)
/*      */     {
/*  717 */       setObjectElement(paramArrayOfObject[k], j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArray(double[] paramArrayOfDouble)
/*      */   {
/*  725 */     if (paramArrayOfDouble == null) {
/*  726 */       setNullArray();
/*      */     }
/*      */     else {
/*  729 */       setArrayGeneric(paramArrayOfDouble.length);
/*      */       
/*  731 */       this.elements = new Object[this.length];
/*      */       
/*  733 */       for (int i = 0; i < this.length; i++) {
/*  734 */         this.elements[i] = Double.valueOf(paramArrayOfDouble[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setArray(double[] paramArrayOfDouble, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  742 */     if (paramArrayOfDouble == null) {
/*  743 */       return;
/*      */     }
/*  745 */     int i = sliceLength(paramLong, paramArrayOfDouble.length);
/*  746 */     int j = (int)paramLong;
/*      */     
/*  748 */     for (int k = 0; k < i; k++)
/*      */     {
/*  750 */       setObjectElement(Double.valueOf(paramArrayOfDouble[k]), j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArray(int[] paramArrayOfInt)
/*      */   {
/*  758 */     if (paramArrayOfInt == null) {
/*  759 */       setNullArray();
/*      */     }
/*      */     else {
/*  762 */       setArrayGeneric(paramArrayOfInt.length);
/*      */       
/*  764 */       this.elements = new Object[this.length];
/*      */       
/*  766 */       for (int i = 0; i < this.length; i++) {
/*  767 */         this.elements[i] = Integer.valueOf(paramArrayOfInt[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setArray(int[] paramArrayOfInt, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  775 */     if (paramArrayOfInt == null) {
/*  776 */       return;
/*      */     }
/*  778 */     int i = sliceLength(paramLong, paramArrayOfInt.length);
/*  779 */     int j = (int)paramLong;
/*      */     
/*  781 */     for (int k = 0; k < i; k++)
/*      */     {
/*  783 */       setObjectElement(Integer.valueOf(paramArrayOfInt[k]), j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArray(float[] paramArrayOfFloat)
/*      */   {
/*  791 */     if (paramArrayOfFloat == null) {
/*  792 */       setNullArray();
/*      */     }
/*      */     else {
/*  795 */       setArrayGeneric(paramArrayOfFloat.length);
/*      */       
/*  797 */       this.elements = new Object[this.length];
/*      */       
/*  799 */       for (int i = 0; i < this.length; i++) {
/*  800 */         this.elements[i] = Float.valueOf(paramArrayOfFloat[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setArray(float[] paramArrayOfFloat, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  808 */     if (paramArrayOfFloat == null) {
/*  809 */       return;
/*      */     }
/*  811 */     int i = sliceLength(paramLong, paramArrayOfFloat.length);
/*  812 */     int j = (int)paramLong;
/*      */     
/*  814 */     for (int k = 0; k < i; k++)
/*      */     {
/*  816 */       setObjectElement(Float.valueOf(paramArrayOfFloat[k]), j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setArray(short[] paramArrayOfShort)
/*      */   {
/*  824 */     if (paramArrayOfShort == null) {
/*  825 */       setNullArray();
/*      */     }
/*      */     else {
/*  828 */       setArrayGeneric(paramArrayOfShort.length);
/*      */       
/*  830 */       this.elements = new Object[this.length];
/*      */       
/*  832 */       for (int i = 0; i < this.length; i++) {
/*  833 */         this.elements[i] = Integer.valueOf(paramArrayOfShort[i]);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public void setArray(short[] paramArrayOfShort, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  841 */     if (paramArrayOfShort == null) {
/*  842 */       return;
/*      */     }
/*  844 */     int i = sliceLength(paramLong, paramArrayOfShort.length);
/*  845 */     int j = (int)paramLong;
/*      */     
/*  847 */     for (int k = 0; k < i; k++)
/*      */     {
/*  849 */       setObjectElement(Integer.valueOf(paramArrayOfShort[k]), j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getObjectElement(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  857 */     Object localObject = getLazyArray()[((int)paramLong)];
/*      */     
/*  859 */     if (localObject == null) {
/*      */       Datum localDatum;
/*  861 */       if (this.old_factory == null)
/*      */       {
/*  863 */         localDatum = getLazyOracleArray()[((int)paramLong)];
/*      */         
/*  865 */         localObject = Util.convertToObject(localDatum, this.sqlType, this.factory);
/*  866 */         this.elements[((int)paramLong)] = localObject;
/*      */         
/*  868 */         if (Util.isMutable(localDatum, this.factory)) {
/*  869 */           resetOracleElement(paramLong);
/*      */         }
/*      */       }
/*      */       else {
/*  873 */         localDatum = getLazyOracleArray()[((int)paramLong)];
/*      */         
/*  875 */         localObject = Util.convertToObject(localDatum, this.sqlType, this.old_factory);
/*  876 */         this.elements[((int)paramLong)] = localObject;
/*      */         
/*  878 */         if (Util.isMutable(localDatum, this.old_factory)) {
/*  879 */           resetOracleElement(paramLong);
/*      */         }
/*      */       }
/*      */     }
/*  883 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object getOracleElement(long paramLong)
/*      */     throws SQLException
/*      */   {
/*  890 */     if ((this.factory == null) && (this.old_factory == null))
/*      */     {
/*  892 */       Datum localDatum = getDatumElement(paramLong, null);
/*      */       
/*  894 */       if (Util.isMutable(localDatum, this.factory)) {
/*  895 */         this.pickledCorrect = false;
/*      */       }
/*  897 */       return localDatum;
/*      */     }
/*      */     
/*  900 */     return getObjectElement(paramLong);
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
/*      */   public void setObjectElement(Object paramObject, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  916 */     if (paramObject == null)
/*      */     {
/*  918 */       getLazyOracleArray();
/*      */     }
/*      */     
/*  921 */     resetOracleElement(paramLong);
/*      */     
/*  923 */     getLazyArray()[((int)paramLong)] = paramObject;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setOracleElement(Object paramObject, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  930 */     if ((this.factory == null) && (this.old_factory == null)) {
/*  931 */       setDatumElement((Datum)paramObject, paramLong);
/*      */     } else {
/*  933 */       setObjectElement(paramObject, paramLong);
/*      */     }
/*      */   }
/*      */   
/*      */   public String getBaseTypeName()
/*      */     throws SQLException
/*      */   {
/*  940 */     return this.pickled.getBaseTypeName();
/*      */   }
/*      */   
/*      */ 
/*      */   public int getBaseType()
/*      */     throws SQLException
/*      */   {
/*  947 */     return this.pickled.getBaseType();
/*      */   }
/*      */   
/*      */ 
/*      */   public ArrayDescriptor getDescriptor()
/*      */     throws SQLException
/*      */   {
/*  954 */     return this.pickled.getDescriptor();
/*      */   }
/*      */   
/*      */ 
/*      */   Datum[] getDatumArray(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  961 */     if (this.length < 0) {
/*  962 */       getLazyOracleArray();
/*      */     }
/*  964 */     if (this.datums == null) {
/*  965 */       return null;
/*      */     }
/*  967 */     Datum[] arrayOfDatum = new Datum[this.length];
/*      */     
/*  969 */     for (int i = 0; i < this.length; i++) {
/*  970 */       arrayOfDatum[i] = getDatumElement(i, paramConnection);
/*      */     }
/*  972 */     return arrayOfDatum;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setDatumArray(Datum[] paramArrayOfDatum)
/*      */   {
/*  979 */     if (paramArrayOfDatum == null) {
/*  980 */       setNullArray();
/*      */     }
/*      */     else {
/*  983 */       this.length = paramArrayOfDatum.length;
/*  984 */       this.elements = null;
/*  985 */       this.datums = ((Datum[])paramArrayOfDatum.clone());
/*  986 */       this.pickled = null;
/*  987 */       this.pickledCorrect = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void setDatumArray(Datum[] paramArrayOfDatum, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  995 */     if (paramArrayOfDatum == null) {
/*  996 */       return;
/*      */     }
/*  998 */     int i = sliceLength(paramLong, paramArrayOfDatum.length);
/*  999 */     int j = (int)paramLong;
/*      */     
/* 1001 */     for (int k = 0; k < i; k++)
/*      */     {
/* 1003 */       setDatumElement(paramArrayOfDatum[k], j++);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   Datum getDatumElement(long paramLong, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1011 */     Datum localDatum = getLazyOracleArray()[((int)paramLong)];
/*      */     
/* 1013 */     if (localDatum == null)
/*      */     {
/* 1015 */       Object localObject = getLazyArray()[((int)paramLong)];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1021 */       localDatum = Util.convertToOracle(localObject, paramConnection, this.isNChar);
/* 1022 */       this.datums[((int)paramLong)] = localDatum;
/*      */     }
/* 1024 */     return localDatum;
/*      */   }
/*      */   
/*      */ 
/*      */   void setDatumElement(Datum paramDatum, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1031 */     resetElement(paramLong);
/*      */     
/* 1033 */     getLazyOracleArray()[((int)paramLong)] = paramDatum;
/* 1034 */     this.pickledCorrect = false;
/*      */   }
/*      */   
/*      */ 
/*      */   void resetElement(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1041 */     if (this.elements != null)
/*      */     {
/* 1043 */       this.elements[((int)paramLong)] = null;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setNullArray()
/*      */   {
/* 1051 */     this.length = -1;
/* 1052 */     this.elements = null;
/* 1053 */     this.datums = null;
/* 1054 */     this.pickled = null;
/* 1055 */     this.pickledCorrect = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setArrayGeneric(int paramInt)
/*      */   {
/* 1062 */     this.length = paramInt;
/* 1063 */     this.datums = new Datum[paramInt];
/* 1064 */     this.pickled = null;
/* 1065 */     this.pickledCorrect = false;
/*      */   }
/*      */   
/*      */ 
/*      */   public int length()
/*      */     throws SQLException
/*      */   {
/* 1072 */     if (this.length < 0) {
/* 1073 */       getLazyOracleArray();
/*      */     }
/* 1075 */     return this.length;
/*      */   }
/*      */   
/*      */ 
/*      */   public int sliceLength(long paramLong, int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1082 */     if (this.length < 0) {
/* 1083 */       getLazyOracleArray();
/*      */     }
/* 1085 */     if (paramLong < 0L) {
/* 1086 */       return (int)paramLong;
/*      */     }
/* 1088 */     return Math.min(this.length - (int)paramLong, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */   void resetOracleElement(long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1095 */     if (this.datums != null)
/*      */     {
/* 1097 */       this.datums[((int)paramLong)] = null;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1105 */     this.pickledCorrect = false;
/*      */   }
/*      */   
/*      */ 
/*      */   Object[] getLazyArray()
/*      */     throws SQLException
/*      */   {
/* 1112 */     if (this.length == -1) {
/* 1113 */       getLazyOracleArray();
/*      */     }
/* 1115 */     if (this.elements == null)
/*      */     {
/* 1117 */       this.elements = new Object[this.length];
/*      */     }
/*      */     
/* 1120 */     return this.elements;
/*      */   }
/*      */   
/*      */ 
/*      */   Datum[] getLazyOracleArray()
/*      */     throws SQLException
/*      */   {
/* 1127 */     if (this.datums == null)
/*      */     {
/*      */ 
/*      */ 
/* 1131 */       if (this.pickled != null)
/*      */       {
/*      */ 
/*      */ 
/* 1135 */         this.datums = ((Datum[])this.pickled.getOracleArray());
/* 1136 */         this.length = this.datums.length;
/* 1137 */         this.pickledCorrect = true;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1143 */         if (this.elements != null)
/*      */         {
/* 1145 */           for (int i = 0; i < this.length; i++)
/*      */           {
/* 1147 */             if (this.elements[i] != null)
/*      */             {
/* 1149 */               this.datums[i] = null;
/* 1150 */               this.pickledCorrect = false;
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */       }
/* 1157 */       else if (this.length >= 0) {
/* 1158 */         this.datums = new Datum[this.length];
/*      */       }
/*      */     }
/* 1161 */     return this.datums;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setNChar()
/*      */   {
/* 1168 */     this.isNChar = true;
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1183 */     return null;
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
/* 1213 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jpub/runtime/MutableArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */