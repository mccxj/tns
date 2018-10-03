/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.io.PrintWriter;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringWriter;
/*      */ import java.lang.reflect.Field;
/*      */ import java.sql.Connection;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.OracleData;
/*      */ import oracle.jdbc.OracleTypeMetaData;
/*      */ import oracle.jdbc.OracleTypeMetaData.Kind;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.oracore.OracleNamedType;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ import oracle.jdbc.oracore.OracleTypeCOLLECTION;
/*      */ import oracle.jdbc.oracore.OracleTypeOPAQUE;
/*      */ import oracle.jdbc.oracore.PickleContext;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TypeDescriptor
/*      */   implements OracleTypeMetaData, Serializable, ORAData, OracleData
/*      */ {
/*   57 */   public static boolean DEBUG_SERIALIZATION = false;
/*      */   
/*      */   static final long serialVersionUID = 2022598722047823723L;
/*      */   
/*      */   static final int KOIDFLEN = 16;
/*      */   
/*      */   static final short KOTA_TRN = 1;
/*      */   
/*      */   static final short KOTA_PDF = 2;
/*      */   
/*      */   static final short KOTA_ITOID = 4;
/*      */   
/*      */   static final short KOTA_LOB = 8;
/*      */   
/*      */   static final short KOTA_AD = 16;
/*      */   
/*      */   static final short KOTA_NMHSH = 32;
/*      */   
/*      */   static final short KOTA_TEV = 64;
/*      */   
/*      */   static final short KOTA_INH = 128;
/*      */   
/*      */   static final short KOTA_10I = 256;
/*      */   
/*      */   static final short KOTA_RBF = 512;
/*      */   
/*      */   static final short KOTA_HBF = 1024;
/*      */   
/*      */   static final int ANYTYPE_IMAGE_SIZE_TOID = 23;
/*      */   
/*      */   static final int ANYTYPE_IMAGE_SIZE_NO_TOID = 5;
/*      */   
/*      */   static final byte KOTTDOID = 1;
/*      */   
/*      */   static final byte KOTTBOID = 2;
/*      */   
/*      */   static final byte KOTADOID = 3;
/*      */   
/*      */   static final byte KOTREFOID = 4;
/*      */   
/*      */   static final byte KOTMDOID = 5;
/*      */   
/*      */   static final byte KOTMIOID = 6;
/*      */   
/*      */   static final byte KOTEXOID = 7;
/*      */   
/*      */   static final byte KOTDATOID = 8;
/*      */   
/*      */   static final byte KOTBYTOID = 9;
/*      */   
/*      */   static final byte KOTSHOOID = 10;
/*      */   
/*      */   static final byte KOTLONOID = 11;
/*      */   
/*      */   static final byte KOTREAOID = 12;
/*      */   
/*      */   static final byte KOTDOUOID = 13;
/*      */   
/*      */   static final byte KOTFLOOID = 14;
/*      */   
/*      */   static final byte KOTNUMOID = 15;
/*      */   
/*      */   static final byte KOTDECOID = 16;
/*      */   
/*      */   static final byte KOTUBYOID = 17;
/*      */   
/*      */   static final byte KOTUSHOID = 18;
/*      */   
/*      */   static final byte KOTULOOID = 19;
/*      */   
/*      */   static final byte KOTOCTOID = 20;
/*      */   
/*      */   static final byte KOTSMLOID = 21;
/*      */   
/*      */   static final byte KOTINTOID = 22;
/*      */   
/*      */   static final byte KOTRAWOID = 23;
/*      */   
/*      */   static final byte KOTPTROID = 24;
/*      */   
/*      */   static final byte KOTVSIOID = 25;
/*      */   
/*      */   static final byte KOTFSIOID = 26;
/*      */   
/*      */   static final byte KOTVSOOID = 27;
/*      */   
/*      */   static final byte KOTMLSOID = 28;
/*      */   
/*      */   static final byte KOTVAROID = 29;
/*      */   
/*      */   static final byte KOTMSTOID = 30;
/*      */   
/*      */   static final byte KOTNATOID = 31;
/*      */   
/*      */   static final byte KOTDOMOID = 32;
/*      */   
/*      */   static final byte KOTUND1OID = 33;
/*      */   
/*      */   static final byte KOTCLBOID = 34;
/*      */   
/*      */   static final byte KOTBLBOID = 35;
/*      */   
/*      */   static final byte KOTCFLOID = 36;
/*      */   
/*      */   static final byte KOTBFLOID = 37;
/*      */   
/*      */   static final byte KOTOIDOID = 38;
/*      */   
/*      */   static final byte KOTCAROID = 39;
/*      */   
/*      */   static final byte KOTCANOID = 40;
/*      */   
/*      */   static final byte KOTLPTOID = 41;
/*      */   
/*      */   static final byte KOTBRIOID = 42;
/*      */   
/*      */   static final byte KOTUCOOID = 43;
/*      */   
/*      */   static final byte KOTRECOID = 44;
/*      */   
/*      */   static final byte KOTRCUOID = 45;
/*      */   
/*      */   static final byte KOTBOOOID = 46;
/*      */   
/*      */   static final byte KOTRIDOID = 47;
/*      */   
/*      */   static final byte KOTPLOOID = 48;
/*      */   
/*      */   static final byte KOTPLROID = 49;
/*      */   static final byte KOTPBIOID = 50;
/*      */   static final byte KOTPINOID = 51;
/*      */   static final byte KOTPNAOID = 52;
/*      */   static final byte KOTPNNOID = 53;
/*      */   static final byte KOTPPOOID = 54;
/*      */   static final byte KOTPPNOID = 55;
/*      */   static final byte KOTPSTOID = 56;
/*      */   static final byte KOTEX1OID = 57;
/*      */   static final byte KOTOPQOID = 58;
/*      */   static final byte KOTTMOID = 59;
/*      */   static final byte KOTTMTZOID = 60;
/*      */   static final byte KOTTSOID = 61;
/*      */   static final byte KOTTSTZOID = 62;
/*      */   static final byte KOTIYMOID = 63;
/*      */   static final byte KOTIDSOID = 64;
/*      */   static final byte KOTTSIMPTZOID = 65;
/*      */   static final byte KOTTBXOID = 66;
/*      */   static final byte KOTADXOID = 67;
/*      */   static final byte KOTOIDBFLT = 68;
/*      */   static final byte KOTOIDBDBL = 69;
/*      */   static final byte KOTURDOID = 70;
/*      */   static final byte KOTLASTOID = 71;
/*  208 */   static final byte[] KOTTDEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 };
/*      */   
/*      */ 
/*  211 */   static final byte[] KOTTBEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 2 };
/*      */   
/*      */ 
/*  214 */   static final byte[] KOTADEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 3 };
/*      */   
/*      */ 
/*  217 */   static final byte[] KOTMDEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 4 };
/*      */   
/*      */ 
/*  220 */   static final byte[] KOTTBXEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 66 };
/*      */   
/*      */ 
/*  223 */   static final byte[] KOTADXEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 67 };
/*      */   
/*      */ 
/*      */ 
/*  227 */   static final byte[] KOTTDTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
/*      */   
/*  229 */   static final byte[] KOTTBTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 };
/*      */   
/*  231 */   static final byte[] KOTADTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3 };
/*      */   
/*  233 */   static final byte[] KOTMDTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5 };
/*      */   
/*  235 */   static final byte[] KOTMITOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6 };
/*      */   
/*  237 */   static final byte[] KOTEXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7 };
/*      */   
/*  239 */   static final byte[] KOTEX1TOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 57 };
/*      */   
/*  241 */   static final byte[] KOTTBXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 66 };
/*      */   
/*  243 */   static final byte[] KOTADXTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67 };
/*      */   
/*      */ 
/*  246 */   public static final byte[] RAWTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23 };
/*      */   
/*      */ 
/*      */ 
/*  250 */   public static final byte[] ANYTYPETOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 16 };
/*      */   
/*  252 */   public static final byte[] ANYDATATOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17 };
/*      */   
/*  254 */   public static final byte[] ANYDATASETTOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 18 };
/*      */   
/*      */ 
/*  257 */   public static final byte[] XMLTYPETOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0 };
/*      */   
/*      */   static final short SQLT_NONE = 0;
/*      */   
/*      */   static final short SQLT_CHR = 1;
/*      */   
/*      */   static final short SQLT_NUM = 2;
/*      */   
/*      */   static final short SQLT_INT = 3;
/*      */   
/*      */   static final short SQLT_FLT = 4;
/*      */   
/*      */   static final short SQLT_STR = 5;
/*      */   
/*      */   static final short SQLT_VNU = 6;
/*      */   
/*      */   static final short SQLT_PDN = 7;
/*      */   
/*      */   static final short SQLT_LNG = 8;
/*      */   
/*      */   static final short SQLT_VCS = 9;
/*      */   
/*      */   static final short SQLT_NON = 10;
/*      */   
/*      */   static final short SQLT_RID = 11;
/*      */   
/*      */   static final short SQLT_DAT = 12;
/*      */   
/*      */   static final short SQLT_VBI = 15;
/*      */   
/*      */   static final short SQLT_BFLOAT = 21;
/*      */   
/*      */   static final short SQLT_BDOUBLE = 22;
/*      */   
/*      */   static final short SQLT_BIN = 23;
/*      */   
/*      */   static final short SQLT_LBI = 24;
/*      */   
/*      */   static final short SQLT_UIN = 68;
/*      */   
/*      */   static final short SQLT_SLS = 91;
/*      */   
/*      */   static final short SQLT_LVC = 94;
/*      */   
/*      */   static final short SQLT_LVB = 95;
/*      */   
/*      */   static final short SQLT_AFC = 96;
/*      */   
/*      */   static final short SQLT_AVC = 97;
/*      */   
/*      */   static final short SQLT_IBFLOAT = 100;
/*      */   
/*      */   static final short SQLT_IBDOUBLE = 101;
/*      */   
/*      */   static final short SQLT_CUR = 102;
/*      */   
/*      */   static final short SQLT_RDD = 104;
/*      */   
/*      */   static final short SQLT_LAB = 105;
/*      */   
/*      */   static final short SQLT_OSL = 106;
/*      */   
/*      */   static final short SQLT_NTY = 108;
/*      */   
/*      */   static final short SQLT_REF = 110;
/*      */   
/*      */   static final short SQLT_CLOB = 112;
/*      */   
/*      */   static final short SQLT_BLOB = 113;
/*      */   
/*      */   static final short SQLT_BFILEE = 114;
/*      */   
/*      */   static final short SQLT_FILE = 114;
/*      */   
/*      */   static final short SQLT_CFILEE = 115;
/*      */   
/*      */   static final short SQLT_RSET = 116;
/*      */   
/*      */   static final short SQLT_SVT = 118;
/*      */   
/*      */   static final short SQLT_NCO = 122;
/*      */   
/*      */   static final short SQLT_DTR = 152;
/*      */   
/*      */   static final short SQLT_DUN = 153;
/*      */   
/*      */   static final short SQLT_DOP = 154;
/*      */   
/*      */   static final short SQLT_VST = 155;
/*      */   
/*      */   static final short SQLT_ODT = 156;
/*      */   
/*      */   static final short SQLT_DOL = 172;
/*      */   
/*      */   static final short SQLT_DATE = 184;
/*      */   
/*      */   static final short SQLT_TIME = 185;
/*      */   static final short SQLT_TIME_TZ = 186;
/*      */   static final short SQLT_TIMESTAMP = 187;
/*      */   static final short SQLT_TIMESTAMP_TZ = 188;
/*      */   static final short SQLT_INTERVAL_YM = 189;
/*      */   static final short SQLT_INTERVAL_DS = 190;
/*      */   static final short SQLT_TIMESTAMP_LTZ = 232;
/*      */   static final short SQLT_PNTY = 241;
/*      */   static final short SQLT_CFILE = 115;
/*      */   static final short SQLT_BFILE = 114;
/*      */   static final short SQLT_REC = 250;
/*      */   static final short SQLT_TAB = 251;
/*      */   static final short SQLT_BOL = 252;
/*      */   static final short SQLCS_IMPLICIT = 1;
/*      */   static final short SQLCS_NCHAR = 2;
/*      */   static final short SQLCS_EXPLICIT = 3;
/*      */   static final short SQLCS_FLEXIBLE = 4;
/*      */   static final short SQLCS_LIT_NULL = 5;
/*      */   static final short SQLT_XDP = 103;
/*      */   static final short SQLT_OKO = 107;
/*      */   static final short SQLT_INTY = 109;
/*      */   static final short SQLT_IREF = 111;
/*      */   static final short SQLT_DCLOB = 195;
/*      */   public static final short TYPECODE_REF = 110;
/*      */   public static final short TYPECODE_DATE = 12;
/*      */   public static final short TYPECODE_SIGNED8 = 27;
/*      */   public static final short TYPECODE_SIGNED16 = 28;
/*      */   public static final short TYPECODE_SIGNED32 = 29;
/*      */   public static final short TYPECODE_REAL = 21;
/*      */   public static final short TYPECODE_DOUBLE = 22;
/*      */   public static final short TYPECODE_BFLOAT = 100;
/*      */   public static final short TYPECODE_BDOUBLE = 101;
/*      */   public static final short TYPECODE_FLOAT = 4;
/*      */   public static final short TYPECODE_NUMBER = 2;
/*      */   public static final short TYPECODE_DECIMAL = 7;
/*      */   public static final short TYPECODE_UNSIGNED8 = 23;
/*      */   public static final short TYPECODE_UNSIGNED16 = 25;
/*      */   public static final short TYPECODE_UNSIGNED32 = 26;
/*      */   public static final short TYPECODE_OCTET = 245;
/*      */   public static final short TYPECODE_SMALLINT = 246;
/*      */   public static final short TYPECODE_INTEGER = 3;
/*      */   public static final short TYPECODE_RAW = 95;
/*      */   public static final short TYPECODE_PTR = 32;
/*      */   public static final short TYPECODE_VARCHAR2 = 9;
/*      */   public static final short TYPECODE_CHAR = 96;
/*      */   public static final short TYPECODE_VARCHAR = 1;
/*      */   public static final short TYPECODE_MLSLABEL = 105;
/*      */   public static final short TYPECODE_VARRAY = 247;
/*      */   public static final short TYPECODE_TABLE = 248;
/*      */   public static final short TYPECODE_OBJECT = 108;
/*      */   public static final short TYPECODE_OPAQUE = 58;
/*      */   public static final short TYPECODE_NAMEDCOLLECTION = 122;
/*      */   public static final short TYPECODE_BLOB = 113;
/*      */   public static final short TYPECODE_BFILE = 114;
/*      */   public static final short TYPECODE_CLOB = 112;
/*      */   public static final short TYPECODE_CFILE = 115;
/*      */   public static final short TYPECODE_TIME = 185;
/*      */   public static final short TYPECODE_TIME_TZ = 186;
/*      */   public static final short TYPECODE_TIMESTAMP = 187;
/*      */   public static final short TYPECODE_TIMESTAMP_TZ = 188;
/*      */   public static final short TYPECODE_TIMESTAMP_LTZ = 232;
/*      */   public static final short TYPECODE_INTERVAL_YM = 189;
/*      */   public static final short TYPECODE_INTERVAL_DS = 190;
/*      */   public static final short TYPECODE_UROWID = 104;
/*      */   public static final short TYPECODE_OTMFIRST = 228;
/*      */   public static final short TYPECODE_OTMLAST = 320;
/*      */   public static final short TYPECODE_SYSFIRST = 228;
/*      */   public static final short TYPECODE_SYSLAST = 235;
/*      */   public static final short TYPECODE_PLS_INTEGER = 266;
/*      */   public static final short TYPECODE_ITABLE = 251;
/*      */   public static final short TYPECODE_RECORD = 250;
/*      */   public static final short TYPECODE_BOOLEAN = 252;
/*      */   public static final short TYPECODE_NCHAR = 286;
/*      */   public static final short TYPECODE_NVARCHAR2 = 287;
/*      */   public static final short TYPECODE_NCLOB = 288;
/*      */   public static final short TYPECODE_NONE = 0;
/*      */   public static final short TYPECODE_ERRHP = 283;
/*      */   public static final short TYPECODE_JDBC_JOBJECT = 2000;
/*      */   public static final short TYPECODE_JDBC_STRUCT = 2002;
/*      */   public static final short TYPECODE_JDBC_ARRAY = 2003;
/*      */   public static final short TYPECODE_JDBC_JOPAQUE = 2000;
/*      */   public static final short TYPECODE_JDBC_REF = 2006;
/*      */   public static final short TYPECODE_JDBC_JSTRUCT = 2008;
/*      */   public static final short TYPECODE_SQLXML = 2009;
/*      */   private static final short TYPECODE_MAXVALUE = 2009;
/*  438 */   static final short[] OID_TO_TYPECODE = new short[71];
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   SQLName sqlName;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   OracleNamedType pickler;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   transient oracle.jdbc.internal.OracleConnection connection;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   short internalTypeCode;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  521 */   boolean isTransient = false;
/*  522 */   byte[] toid = null;
/*  523 */   int toidVersion = 1;
/*      */   
/*      */   long precision;
/*      */   
/*      */   byte scale;
/*      */   
/*  529 */   byte[] transientImage = null;
/*      */   
/*      */ 
/*      */ 
/*  533 */   AttributeDescriptor[] attributesDescriptor = null;
/*      */   
/*  535 */   transient Boolean isInstanciable = null;
/*  536 */   transient String supertype = null;
/*  537 */   transient int numLocalAttrs = -1;
/*  538 */   transient String[] subtypes = null;
/*  539 */   transient String[] attrJavaNames = null;
/*      */   private static String[] typeCodeTypeNameMap;
/*      */   
/*      */   void copyDescriptor(TypeDescriptor paramTypeDescriptor)
/*      */   {
/*  544 */     this.sqlName = paramTypeDescriptor.sqlName;
/*  545 */     this.pickler = paramTypeDescriptor.pickler;
/*  546 */     this.connection = paramTypeDescriptor.connection;
/*  547 */     this.internalTypeCode = paramTypeDescriptor.internalTypeCode;
/*  548 */     this.isTransient = paramTypeDescriptor.isTransient;
/*  549 */     this.toid = paramTypeDescriptor.toid;
/*  550 */     this.toidVersion = paramTypeDescriptor.toidVersion;
/*  551 */     this.precision = paramTypeDescriptor.precision;
/*  552 */     this.scale = paramTypeDescriptor.scale;
/*  553 */     this.transientImage = paramTypeDescriptor.transientImage;
/*  554 */     this.attributesDescriptor = paramTypeDescriptor.attributesDescriptor;
/*  555 */     this.isInstanciable = paramTypeDescriptor.isInstanciable;
/*  556 */     this.supertype = paramTypeDescriptor.supertype;
/*  557 */     this.numLocalAttrs = paramTypeDescriptor.numLocalAttrs;
/*  558 */     this.subtypes = paramTypeDescriptor.subtypes;
/*  559 */     this.attrJavaNames = paramTypeDescriptor.attrJavaNames;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected TypeDescriptor(short paramShort)
/*      */   {
/*  567 */     this.internalTypeCode = paramShort;
/*      */   }
/*      */   
/*      */ 
/*      */   protected TypeDescriptor(short paramShort, String paramString, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  574 */     this.internalTypeCode = paramShort;
/*      */     
/*  576 */     if ((paramString == null) || (paramConnection == null))
/*      */     {
/*      */ 
/*      */ 
/*  580 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Invalid arguments");
/*  581 */       localSQLException.fillInStackTrace();
/*  582 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  586 */     setPhysicalConnectionOf(paramConnection);
/*      */     
/*  588 */     this.sqlName = new SQLName(paramString, getInternalConnection());
/*      */   }
/*      */   
/*      */ 
/*      */   protected TypeDescriptor(short paramShort, SQLName paramSQLName, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  595 */     this.internalTypeCode = paramShort;
/*  596 */     if ((paramSQLName == null) || (paramConnection == null))
/*      */     {
/*      */ 
/*      */ 
/*  600 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Invalid arguments");
/*  601 */       localSQLException.fillInStackTrace();
/*  602 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  606 */     this.sqlName = paramSQLName;
/*      */     
/*  608 */     setPhysicalConnectionOf(paramConnection);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected TypeDescriptor(short paramShort, SQLName paramSQLName, OracleTypeADT paramOracleTypeADT, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  617 */     this.internalTypeCode = paramShort;
/*  618 */     if ((paramSQLName == null) || (paramOracleTypeADT == null))
/*      */     {
/*      */ 
/*      */ 
/*  622 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Invalid arguments");
/*  623 */       localSQLException.fillInStackTrace();
/*  624 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  628 */     this.sqlName = paramSQLName;
/*  629 */     if (paramConnection != null) {
/*  630 */       setPhysicalConnectionOf(paramConnection);
/*      */     }
/*  632 */     this.pickler = paramOracleTypeADT;
/*      */     
/*  634 */     this.pickler.setDescriptor(this);
/*      */     
/*  636 */     this.toid = paramOracleTypeADT.getTOID();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected TypeDescriptor(short paramShort, OracleTypeADT paramOracleTypeADT, Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/*  646 */     this.internalTypeCode = paramShort;
/*  647 */     if ((paramOracleTypeADT == null) || (paramConnection == null))
/*      */     {
/*      */ 
/*      */ 
/*  651 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 60, "Invalid arguments");
/*  652 */       localSQLException.fillInStackTrace();
/*  653 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  657 */     setPhysicalConnectionOf(paramConnection);
/*      */     
/*  659 */     this.sqlName = null;
/*  660 */     this.pickler = paramOracleTypeADT;
/*  661 */     this.pickler.setDescriptor(this);
/*  662 */     this.toid = paramOracleTypeADT.getTOID();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleTypeMetaData.Kind getKind()
/*      */   {
/*  674 */     return OracleTypeMetaData.Kind.TYPE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getName()
/*      */     throws SQLException
/*      */   {
/*  683 */     synchronized (this.connection)
/*      */     {
/*  685 */       if (this.sqlName == null) {
/*  686 */         initSQLName();
/*      */       }
/*  688 */       String str = null;
/*      */       
/*      */ 
/*  691 */       if (this.sqlName != null)
/*  692 */         str = this.sqlName.getName();
/*  693 */       return str;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SQLName getSQLName()
/*      */     throws SQLException
/*      */   {
/*  704 */     synchronized (this.connection)
/*      */     {
/*  706 */       if (this.sqlName == null) {
/*  707 */         initSQLName();
/*      */       }
/*  709 */       return this.sqlName;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void initSQLName()
/*      */     throws SQLException
/*      */   {
/*  718 */     if (!this.isTransient) {
/*      */       Object localObject;
/*  720 */       if (this.connection == null)
/*      */       {
/*      */ 
/*      */ 
/*  724 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  725 */         ((SQLException)localObject).fillInStackTrace();
/*  726 */         throw ((Throwable)localObject);
/*      */       }
/*      */       
/*      */ 
/*  730 */       if (this.pickler != null) {
/*  731 */         this.sqlName = new SQLName(this.pickler.getFullName(), this.connection);
/*  732 */       } else if (this.toid != null)
/*      */       {
/*      */ 
/*  735 */         localObject = OracleTypeADT.toid2typename(this.connection, this.toid);
/*      */         
/*  737 */         this.sqlName = new SQLName((String)localObject, this.connection);
/*      */         
/*      */ 
/*      */ 
/*  741 */         TypeDescriptor localTypeDescriptor = null;
/*  742 */         String str = this.sqlName.getName();
/*  743 */         localTypeDescriptor = (TypeDescriptor)this.connection.getDescriptor(str);
/*      */         
/*      */ 
/*      */ 
/*  747 */         if (localTypeDescriptor != null) {
/*  748 */           copyDescriptor(localTypeDescriptor);
/*      */         }
/*  750 */       } else if ((this.internalTypeCode == 108) || (this.internalTypeCode == 122))
/*      */       {
/*      */ 
/*      */ 
/*  754 */         localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/*  755 */         ((SQLException)localObject).fillInStackTrace();
/*  756 */         throw ((Throwable)localObject);
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
/*      */   public String getSchemaName()
/*      */     throws SQLException
/*      */   {
/*  774 */     String str = null;
/*  775 */     if (this.sqlName == null)
/*  776 */       initSQLName();
/*  777 */     if (this.sqlName != null)
/*  778 */       str = this.sqlName.getSchema();
/*  779 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getTypeName()
/*      */     throws SQLException
/*      */   {
/*  790 */     String str = null;
/*  791 */     if (this.sqlName == null)
/*  792 */       initSQLName();
/*  793 */     if (this.sqlName != null)
/*  794 */       str = this.sqlName.getSimpleName();
/*  795 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public OracleNamedType getPickler()
/*      */   {
/*  806 */     return this.pickler;
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
/*      */   public oracle.jdbc.internal.OracleConnection getInternalConnection()
/*      */   {
/*  819 */     return this.connection;
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
/*      */   public void setPhysicalConnectionOf(Connection paramConnection)
/*      */   {
/*  832 */     this.connection = ((oracle.jdbc.OracleConnection)paramConnection).physicalConnectionWithin();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getTypeCode()
/*      */     throws SQLException
/*      */   {
/*  844 */     return this.internalTypeCode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getTypeCodeName()
/*      */     throws SQLException
/*      */   {
/*  853 */     return getTypeCodeTypeNameMap()[getTypeCode()];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private static String[] getTypeCodeTypeNameMap()
/*      */     throws SQLException
/*      */   {
/*  861 */     if (typeCodeTypeNameMap == null)
/*      */     {
/*  863 */       String[] arrayOfString = new String['ߚ'];
/*      */       
/*  865 */       Class localClass = null;
/*      */       try
/*      */       {
/*  868 */         localClass = Class.forName("oracle.sql.TypeDescriptor");
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (ClassNotFoundException localClassNotFoundException)
/*      */       {
/*      */ 
/*  875 */         SQLException localSQLException1 = DatabaseError.createSqlException(null, 1, "TypeDescriptor.getTypeCodeName: got a ClassNotFoundException: " + localClassNotFoundException.getMessage());
/*  876 */         localSQLException1.fillInStackTrace();
/*  877 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*  881 */       Field[] arrayOfField = localClass.getFields();
/*  882 */       for (int i = 0; i < arrayOfField.length; i++) {
/*  883 */         if (arrayOfField[i].getName().startsWith("TYPECODE_")) {
/*      */           try
/*      */           {
/*  886 */             arrayOfString[arrayOfField[i].getInt(null)] = arrayOfField[i].getName();
/*      */ 
/*      */           }
/*      */           catch (Exception localException)
/*      */           {
/*  891 */             SQLException localSQLException2 = DatabaseError.createSqlException(null, 1, "TypeDescriptor.getTypeCodeName: " + localException.getMessage());
/*  892 */             localSQLException2.fillInStackTrace();
/*  893 */             throw localSQLException2;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  899 */       typeCodeTypeNameMap = arrayOfString;
/*      */     }
/*  901 */     return typeCodeTypeNameMap;
/*      */   }
/*      */   
/*      */   static
/*      */   {
/*  441 */     OID_TO_TYPECODE[8] = 12;
/*  442 */     OID_TO_TYPECODE[9] = 27;
/*  443 */     OID_TO_TYPECODE[10] = 28;
/*  444 */     OID_TO_TYPECODE[11] = 29;
/*  445 */     OID_TO_TYPECODE[12] = 21;
/*  446 */     OID_TO_TYPECODE[13] = 22;
/*  447 */     OID_TO_TYPECODE[14] = 4;
/*  448 */     OID_TO_TYPECODE[15] = 2;
/*  449 */     OID_TO_TYPECODE[16] = 7;
/*  450 */     OID_TO_TYPECODE[17] = 23;
/*  451 */     OID_TO_TYPECODE[18] = 25;
/*  452 */     OID_TO_TYPECODE[19] = 26;
/*  453 */     OID_TO_TYPECODE[20] = 245;
/*  454 */     OID_TO_TYPECODE[21] = 246;
/*  455 */     OID_TO_TYPECODE[22] = 3;
/*      */     
/*  457 */     OID_TO_TYPECODE[23] = 95;
/*  458 */     OID_TO_TYPECODE[24] = 32;
/*  459 */     OID_TO_TYPECODE[25] = 9;
/*  460 */     OID_TO_TYPECODE[26] = 96;
/*  461 */     OID_TO_TYPECODE[27] = 1;
/*  462 */     OID_TO_TYPECODE[28] = 105;
/*  463 */     OID_TO_TYPECODE[29] = 247;
/*  464 */     OID_TO_TYPECODE[30] = 248;
/*  465 */     OID_TO_TYPECODE[31] = 108;
/*  466 */     OID_TO_TYPECODE[32] = 0;
/*  467 */     OID_TO_TYPECODE[33] = 0;
/*  468 */     OID_TO_TYPECODE[34] = 112;
/*  469 */     OID_TO_TYPECODE[35] = 113;
/*  470 */     OID_TO_TYPECODE[36] = 115;
/*  471 */     OID_TO_TYPECODE[37] = 114;
/*      */     
/*  473 */     OID_TO_TYPECODE[38] = 0;
/*  474 */     OID_TO_TYPECODE[39] = 0;
/*  475 */     OID_TO_TYPECODE[40] = 0;
/*  476 */     OID_TO_TYPECODE[41] = 0;
/*  477 */     OID_TO_TYPECODE[42] = 0;
/*  478 */     OID_TO_TYPECODE[43] = 0;
/*  479 */     OID_TO_TYPECODE[44] = 0;
/*  480 */     OID_TO_TYPECODE[45] = 0;
/*      */     
/*  482 */     OID_TO_TYPECODE[46] = 0;
/*  483 */     OID_TO_TYPECODE[47] = 0;
/*  484 */     OID_TO_TYPECODE[48] = 0;
/*  485 */     OID_TO_TYPECODE[49] = 0;
/*  486 */     OID_TO_TYPECODE[50] = 0;
/*  487 */     OID_TO_TYPECODE[51] = 0;
/*  488 */     OID_TO_TYPECODE[52] = 0;
/*  489 */     OID_TO_TYPECODE[53] = 0;
/*  490 */     OID_TO_TYPECODE[54] = 0;
/*  491 */     OID_TO_TYPECODE[55] = 0;
/*  492 */     OID_TO_TYPECODE[56] = 0;
/*      */     
/*  494 */     OID_TO_TYPECODE[57] = 0;
/*      */     
/*  496 */     OID_TO_TYPECODE[58] = 58;
/*      */     
/*  498 */     OID_TO_TYPECODE[59] = 185;
/*  499 */     OID_TO_TYPECODE[60] = 186;
/*  500 */     OID_TO_TYPECODE[61] = 187;
/*  501 */     OID_TO_TYPECODE[62] = 188;
/*  502 */     OID_TO_TYPECODE[63] = 189;
/*  503 */     OID_TO_TYPECODE[64] = 190;
/*  504 */     OID_TO_TYPECODE[65] = 232;
/*  505 */     OID_TO_TYPECODE[66] = 0;
/*  506 */     OID_TO_TYPECODE[67] = 0;
/*  507 */     OID_TO_TYPECODE[68] = 100;
/*  508 */     OID_TO_TYPECODE[69] = 101;
/*  509 */     OID_TO_TYPECODE[70] = 104;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  856 */     typeCodeTypeNameMap = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  911 */       getTypeCodeTypeNameMap();
/*      */     }
/*      */     catch (Exception localException) {}
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getInternalTypeCode()
/*      */     throws SQLException
/*      */   {
/*  924 */     return this.internalTypeCode;
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
/*      */   public static TypeDescriptor getTypeDescriptor(String paramString, oracle.jdbc.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/*  949 */     Object localObject1 = null;
/*      */     
/*      */ 
/*      */ 
/*      */     try
/*      */     {
/*  955 */       SQLName localSQLName = new SQLName(paramString, paramOracleConnection);
/*  956 */       localObject2 = localSQLName.getName();
/*      */       
/*      */ 
/*  959 */       localObject1 = (TypeDescriptor)paramOracleConnection.getDescriptor((String)localObject2);
/*      */       
/*  961 */       if (localObject1 == null)
/*      */       {
/*      */ 
/*      */ 
/*  965 */         OracleTypeADT localOracleTypeADT = new OracleTypeADT((String)localObject2, paramOracleConnection);
/*  966 */         oracle.jdbc.internal.OracleConnection localOracleConnection = (oracle.jdbc.internal.OracleConnection)paramOracleConnection;
/*      */         
/*      */ 
/*  969 */         localOracleTypeADT.init(localOracleConnection);
/*      */         
/*  971 */         OracleNamedType localOracleNamedType = localOracleTypeADT.cleanup();
/*      */         
/*      */ 
/*  974 */         switch (localOracleNamedType.getTypeCode())
/*      */         {
/*      */ 
/*      */ 
/*      */         case 2002: 
/*      */         case 2008: 
/*  980 */           localObject1 = new StructDescriptor(localSQLName, (OracleTypeADT)localOracleNamedType, paramOracleConnection);
/*      */           
/*      */ 
/*  983 */           break;
/*      */         
/*      */         case 2003: 
/*  986 */           localObject1 = new ArrayDescriptor(localSQLName, (OracleTypeCOLLECTION)localOracleNamedType, paramOracleConnection);
/*      */           
/*      */ 
/*      */ 
/*  990 */           break;
/*      */         
/*      */         case 2007: 
/*  993 */           localObject1 = new OpaqueDescriptor(localSQLName, (OracleTypeOPAQUE)localOracleNamedType, paramOracleConnection);
/*      */           
/*      */ 
/*  996 */           break;
/*      */         case 2004: 
/*      */         case 2005: 
/*      */         case 2006: 
/*      */         default: 
/* 1001 */           SQLException localSQLException = DatabaseError.createSqlException(null, 1);
/* 1002 */           localSQLException.fillInStackTrace();
/* 1003 */           throw localSQLException;
/*      */         }
/*      */         
/*      */         
/*      */ 
/* 1008 */         paramOracleConnection.putDescriptor((String)localObject2, localObject1);
/*      */         
/*      */ 
/* 1011 */         localOracleNamedType.setDescriptor((TypeDescriptor)localObject1);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/* 1018 */       if ((localException instanceof SQLException))
/*      */       {
/* 1020 */         localObject2 = DatabaseError.createSqlException(null, (SQLException)localException, 60, "Unable to resolve type \"" + paramString + "\"");
/*      */         
/* 1022 */         ((SQLException)localObject2).fillInStackTrace();
/* 1023 */         throw ((Throwable)localObject2);
/*      */       }
/*      */       
/*      */ 
/* 1027 */       Object localObject2 = DatabaseError.createSqlException(null, 60, "Unable to resolve type \"" + paramString + "\"");
/*      */       
/* 1029 */       ((SQLException)localObject2).fillInStackTrace();
/* 1030 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     
/* 1033 */     return (TypeDescriptor)localObject1;
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
/*      */   public static TypeDescriptor getTypeDescriptor(String paramString, oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1058 */     Object localObject = null;
/*      */     
/*      */ 
/* 1061 */     String str = getSubtypeName(paramOracleConnection, paramArrayOfByte, paramLong);
/*      */     
/* 1063 */     if (str == null) {
/* 1064 */       str = paramString;
/*      */     }
/*      */     
/*      */ 
/* 1068 */     localObject = (TypeDescriptor)paramOracleConnection.getDescriptor(str);
/*      */     
/* 1070 */     if (localObject == null)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 1075 */       SQLName localSQLName = new SQLName(str, paramOracleConnection);
/*      */       
/*      */ 
/* 1078 */       OracleTypeADT localOracleTypeADT = new OracleTypeADT(str, paramOracleConnection);
/* 1079 */       oracle.jdbc.internal.OracleConnection localOracleConnection = (oracle.jdbc.internal.OracleConnection)paramOracleConnection;
/*      */       
/*      */ 
/* 1082 */       localOracleTypeADT.init(localOracleConnection);
/*      */       
/* 1084 */       OracleNamedType localOracleNamedType = localOracleTypeADT.cleanup();
/*      */       
/*      */ 
/* 1087 */       switch (localOracleNamedType.getTypeCode())
/*      */       {
/*      */ 
/*      */ 
/*      */       case 2002: 
/*      */       case 2008: 
/* 1093 */         localObject = new StructDescriptor(localSQLName, (OracleTypeADT)localOracleNamedType, paramOracleConnection);
/*      */         
/*      */ 
/* 1096 */         break;
/*      */       
/*      */       case 2003: 
/* 1099 */         localObject = new ArrayDescriptor(localSQLName, (OracleTypeCOLLECTION)localOracleNamedType, paramOracleConnection);
/*      */         
/*      */ 
/* 1102 */         break;
/*      */       
/*      */       case 2007: 
/* 1105 */         localObject = new OpaqueDescriptor(localSQLName, (OracleTypeOPAQUE)localOracleNamedType, paramOracleConnection);
/*      */         
/*      */ 
/* 1108 */         break;
/*      */       
/*      */       case 2004: 
/*      */       case 2005: 
/*      */       case 2006: 
/*      */       default: 
/* 1114 */         SQLException localSQLException = DatabaseError.createSqlException(null, 1);
/* 1115 */         localSQLException.fillInStackTrace();
/* 1116 */         throw localSQLException;
/*      */       }
/*      */       
/*      */       
/*      */ 
/* 1121 */       paramOracleConnection.putDescriptor(str, localObject);
/*      */     }
/* 1123 */     return (TypeDescriptor)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */   public Datum toDatum(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1130 */     this.connection = ((oracle.jdbc.internal.OracleConnection)paramConnection);
/* 1131 */     OpaqueDescriptor localOpaqueDescriptor = OpaqueDescriptor.createDescriptor("SYS.ANYTYPE", paramConnection);
/*      */     
/* 1133 */     byte[] arrayOfByte = new byte[getOpaqueImageTypeSize()];
/* 1134 */     pickleOpaqueTypeImage(arrayOfByte, 0, false);
/* 1135 */     OPAQUE localOPAQUE = new OPAQUE(localOpaqueDescriptor, this.connection, arrayOfByte);
/* 1136 */     localOPAQUE.setShareBytes(localOPAQUE.toBytes());
/* 1137 */     return localOPAQUE;
/*      */   }
/*      */   
/*      */ 
/*      */   public Object toJDBCObject(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1144 */     return toDatum(paramConnection);
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
/*      */   static TypeDescriptor unpickleOpaqueTypeImage(PickleContext paramPickleContext, Connection paramConnection, short[] paramArrayOfShort)
/*      */     throws SQLException
/*      */   {
/* 1163 */     byte[] arrayOfByte1 = null;
/*      */     
/* 1165 */     Object localObject1 = null;
/*      */     
/*      */ 
/*      */ 
/* 1169 */     int k = paramPickleContext.offset();
/* 1170 */     byte[] arrayOfByte2 = paramPickleContext.image();
/*      */     
/*      */ 
/* 1173 */     paramPickleContext.skipBytes(1);
/* 1174 */     int i = (short)paramPickleContext.readUB2();
/* 1175 */     paramArrayOfShort[0] = ((short)paramPickleContext.readUB2());
/*      */     Object localObject2;
/* 1177 */     if ((i & 0x20) != 0)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1185 */       localObject2 = DatabaseError.createSqlException(null, 178);
/* 1186 */       ((SQLException)localObject2).fillInStackTrace();
/* 1187 */       throw ((Throwable)localObject2);
/*      */     }
/*      */     Object localObject3;
/*      */     int m;
/*      */     Object localObject4;
/* 1192 */     if ((i & 0x1) == 0)
/*      */     {
/* 1194 */       if (((i & 0x2) == 0) || (paramArrayOfShort[0] == 110) || (paramArrayOfShort[0] == 58))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1200 */         arrayOfByte1 = paramPickleContext.readDataValue(16);
/* 1201 */         int j = paramPickleContext.readUB2();
/*      */         
/*      */ 
/* 1204 */         localObject2 = (String)((oracle.jdbc.internal.OracleConnection)paramConnection).getDescriptor(arrayOfByte1);
/* 1205 */         localObject1 = (TypeDescriptor)((oracle.jdbc.internal.OracleConnection)paramConnection).getDescriptor((String)localObject2);
/* 1206 */         if (localObject1 == null)
/*      */         {
/* 1208 */           if (paramArrayOfShort[0] == 122) {
/* 1209 */             localObject1 = new ArrayDescriptor(arrayOfByte1, j, paramConnection);
/* 1210 */           } else if ((paramArrayOfShort[0] == 108) || (paramArrayOfShort[0] == 110)) {
/* 1211 */             localObject1 = new StructDescriptor(arrayOfByte1, j, paramConnection);
/* 1212 */           } else if (paramArrayOfShort[0] == 58) {
/* 1213 */             localObject1 = new OpaqueDescriptor(arrayOfByte1, j, paramConnection);
/*      */           }
/*      */           else {
/* 1216 */             localObject3 = DatabaseError.createSqlException(null, 178);
/* 1217 */             ((SQLException)localObject3).fillInStackTrace();
/* 1218 */             throw ((Throwable)localObject3);
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1226 */         localObject1 = new TypeDescriptor(paramArrayOfShort[0]);
/*      */       }
/* 1228 */       ((TypeDescriptor)localObject1).setTransient(false);
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*      */ 
/* 1237 */       m = (int)paramPickleContext.readUB4();
/* 1238 */       if (paramArrayOfShort[0] == 108)
/*      */       {
/* 1240 */         localObject3 = null;
/* 1241 */         if (m > 0)
/*      */         {
/* 1243 */           localObject3 = new AttributeDescriptor[m];
/* 1244 */           for (int i1 = 0; i1 < m; i1++)
/*      */           {
/* 1246 */             int i2 = paramPickleContext.readByte();
/* 1247 */             localObject3[i1] = Kotad.unpickleAttributeImage(i2 == 2 ? 1 : false, paramPickleContext);
/* 1248 */             if (i2 != 2)
/*      */             {
/* 1250 */               short[] arrayOfShort = new short[1];
/* 1251 */               localObject3[i1].setTypeDescriptor(unpickleOpaqueTypeImage(paramPickleContext, paramConnection, arrayOfShort));
/*      */             }
/*      */           }
/*      */         }
/*      */         
/* 1256 */         localObject1 = new StructDescriptor((AttributeDescriptor[])localObject3, paramConnection);
/*      */       }
/* 1258 */       else if (m == 1)
/*      */       {
/* 1260 */         int n = paramPickleContext.readByte();
/* 1261 */         localObject1 = Kotad.unpickleTypeDescriptorImage(paramPickleContext);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1266 */         localObject4 = DatabaseError.createSqlException(null, 178);
/* 1267 */         ((SQLException)localObject4).fillInStackTrace();
/* 1268 */         throw ((Throwable)localObject4);
/*      */       }
/*      */       
/* 1271 */       ((TypeDescriptor)localObject1).setTransient(true);
/*      */     }
/*      */     
/* 1274 */     if (((TypeDescriptor)localObject1).isTransient())
/*      */     {
/*      */ 
/* 1277 */       m = paramPickleContext.offset();
/* 1278 */       localObject4 = new byte[m - k];
/* 1279 */       System.arraycopy(arrayOfByte2, k, localObject4, 0, localObject4.length);
/* 1280 */       ((TypeDescriptor)localObject1).setTransientImage((byte[])localObject4);
/*      */     }
/*      */     
/* 1283 */     return (TypeDescriptor)localObject1;
/*      */   }
/*      */   
/*      */ 
/*      */   void setTransientImage(byte[] paramArrayOfByte)
/*      */   {
/* 1289 */     this.transientImage = paramArrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void setTransient(boolean paramBoolean)
/*      */   {
/* 1296 */     this.isTransient = paramBoolean;
/*      */   }
/*      */   
/*      */ 
/*      */   public boolean isTransient()
/*      */   {
/* 1302 */     return this.isTransient;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   int getOpaqueImageTypeSize()
/*      */   {
/* 1309 */     int i = 0;
/* 1310 */     if (this.isTransient) {
/* 1311 */       i = this.transientImage.length;
/*      */     }
/*      */     else {
/* 1314 */       i = 5;
/* 1315 */       if ((this.toid != null) && (this.toid.length == 16))
/* 1316 */         i = 23;
/*      */     }
/* 1318 */     return i;
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
/*      */   int pickleOpaqueTypeImage(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean)
/*      */   {
/* 1332 */     if (this.isTransient)
/*      */     {
/* 1334 */       System.arraycopy(this.transientImage, 0, paramArrayOfByte, paramInt, this.transientImage.length);
/* 1335 */       paramInt += this.transientImage.length;
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1340 */       int i = 0;
/* 1341 */       if ((this.toid != null) && (this.toid.length == 16))
/* 1342 */         i = 1;
/* 1343 */       paramArrayOfByte[(paramInt++)] = 1;
/* 1344 */       int j = this.internalTypeCode;
/* 1345 */       if (paramBoolean)
/* 1346 */         j = 110;
/* 1347 */       int k = 512;
/* 1348 */       if ((j != 108) && (j != 122))
/*      */       {
/* 1350 */         k |= 0x2; }
/* 1351 */       if ((i != 0) && (j != 110))
/* 1352 */         k |= 0x4;
/* 1353 */       paramArrayOfByte[(paramInt++)] = ((byte)((k & 0xFF00) >> 8 & 0xFF));
/* 1354 */       paramArrayOfByte[(paramInt++)] = ((byte)(k & 0xFF));
/* 1355 */       paramArrayOfByte[(paramInt++)] = ((byte)((j & 0xFF00) >> 8 & 0xFF));
/* 1356 */       paramArrayOfByte[(paramInt++)] = ((byte)(j & 0xFF));
/* 1357 */       if (i != 0)
/*      */       {
/* 1359 */         System.arraycopy(this.toid, 0, paramArrayOfByte, paramInt, this.toid.length);
/* 1360 */         paramInt += this.toid.length;
/* 1361 */         paramArrayOfByte[(paramInt++)] = ((byte)((this.toidVersion & 0xFF00) >> 8 & 0xFF));
/* 1362 */         paramArrayOfByte[(paramInt++)] = ((byte)(this.toidVersion & 0xFF));
/*      */       }
/*      */     }
/* 1365 */     return paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void setPrecision(long paramLong)
/*      */   {
/* 1372 */     this.precision = paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */   public long getPrecision()
/*      */   {
/* 1378 */     return this.precision;
/*      */   }
/*      */   
/*      */ 
/*      */   public void setScale(byte paramByte)
/*      */   {
/* 1384 */     this.scale = paramByte;
/*      */   }
/*      */   
/*      */ 
/*      */   public byte getScale()
/*      */   {
/* 1390 */     return this.scale;
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
/*      */   public boolean isInHierarchyOf(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1405 */     return false;
/*      */   }
/*      */   
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
/*      */     try
/*      */     {
/* 1420 */       if (this.sqlName == null) {
/* 1421 */         initSQLName();
/*      */       }
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1426 */       throw new IOException(localSQLException.getMessage());
/*      */     }
/*      */     
/* 1429 */     paramObjectOutputStream.writeObject(this.sqlName);
/* 1430 */     paramObjectOutputStream.writeObject(this.pickler);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream paramObjectInputStream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1438 */     this.sqlName = ((SQLName)paramObjectInputStream.readObject());
/* 1439 */     this.pickler = ((OracleNamedType)paramObjectInputStream.readObject());
/*      */   }
/*      */   
/*      */ 
/*      */   public void setConnection(Connection paramConnection)
/*      */     throws SQLException
/*      */   {
/* 1446 */     setPhysicalConnectionOf(paramConnection);
/* 1447 */     this.pickler.setConnection(getInternalConnection());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public static String getSubtypeName(oracle.jdbc.OracleConnection paramOracleConnection, byte[] paramArrayOfByte, long paramLong)
/*      */     throws SQLException
/*      */   {
/* 1455 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0) || (paramOracleConnection == null))
/*      */     {
/*      */ 
/* 1458 */       localObject = DatabaseError.createSqlException(null, 68, " 'image' should not be empty and 'conn' should not be null. ");
/* 1459 */       ((SQLException)localObject).fillInStackTrace();
/* 1460 */       throw ((Throwable)localObject);
/*      */     }
/*      */     
/*      */ 
/* 1464 */     Object localObject = OracleTypeADT.getSubtypeName(paramOracleConnection, paramArrayOfByte, paramLong);
/*      */     
/* 1466 */     return (String)localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void initMetadataRecursively()
/*      */     throws SQLException
/*      */   {
/* 1474 */     if (this.pickler != null) {
/* 1475 */       this.pickler.initMetadataRecursively();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void initNamesRecursively()
/*      */     throws SQLException
/*      */   {
/* 1483 */     if (this.pickler != null) {
/* 1484 */       this.pickler.initNamesRecursively();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   public void fixupConnection(oracle.jdbc.internal.OracleConnection paramOracleConnection)
/*      */     throws SQLException
/*      */   {
/* 1492 */     if (this.connection == null) this.connection = paramOracleConnection;
/* 1493 */     if (this.pickler != null) { this.pickler.fixupConnection(paramOracleConnection);
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
/*      */   public String toXMLString()
/*      */     throws SQLException
/*      */   {
/* 1512 */     return toXMLString(false);
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
/*      */   public String toXMLString(boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1529 */     StringWriter localStringWriter = new StringWriter();
/* 1530 */     PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
/* 1531 */     printXMLHeader(localPrintWriter);
/* 1532 */     printXML(localPrintWriter, 0, paramBoolean);
/* 1533 */     return localStringWriter.getBuffer().substring(0);
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
/*      */   public void printXML(PrintStream paramPrintStream)
/*      */     throws SQLException
/*      */   {
/* 1547 */     printXML(paramPrintStream, false);
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
/*      */   public void printXML(PrintStream paramPrintStream, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1562 */     PrintWriter localPrintWriter = new PrintWriter(paramPrintStream, true);
/* 1563 */     printXMLHeader(localPrintWriter);
/* 1564 */     printXML(localPrintWriter, 0, paramBoolean);
/*      */   }
/*      */   
/*      */ 
/*      */   void printXML(PrintWriter paramPrintWriter, int paramInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1571 */     String str = tagName();
/* 1572 */     paramPrintWriter.println("<" + str + " sqlName=\"" + this.sqlName + "\" >");
/* 1573 */     if (this.pickler != null) this.pickler.printXML(paramPrintWriter, paramInt + 1, paramBoolean);
/* 1574 */     paramPrintWriter.println("</" + str + ">");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   String tagName()
/*      */   {
/* 1581 */     return "TypeDescriptor";
/*      */   }
/*      */   
/*      */ 
/*      */   void printXMLHeader(PrintWriter paramPrintWriter)
/*      */     throws SQLException
/*      */   {
/* 1588 */     paramPrintWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
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
/* 1603 */     return this.connection;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1608 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/TypeDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */