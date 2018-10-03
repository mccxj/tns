/*      */ package oracle.sql;
/*      */ 
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.math.BigDecimal;
/*      */ import java.net.URL;
/*      */ import java.sql.Array;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.Ref;
/*      */ import java.sql.RowId;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.SQLInput;
/*      */ import java.sql.SQLXML;
/*      */ import java.sql.Struct;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class OracleJdbc2SQLInput
/*      */   implements SQLInput
/*      */ {
/*      */   private int index;
/*      */   private Datum[] attributes;
/*      */   private Map map;
/*      */   private oracle.jdbc.OracleConnection conn;
/*      */   
/*      */   public OracleJdbc2SQLInput(Datum[] paramArrayOfDatum, Map paramMap, oracle.jdbc.OracleConnection paramOracleConnection)
/*      */   {
/*   95 */     this.attributes = paramArrayOfDatum;
/*   96 */     this.map = paramMap;
/*   97 */     this.conn = paramOracleConnection;
/*   98 */     this.index = 0;
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
/*      */   public String readString()
/*      */     throws SQLException
/*      */   {
/*  116 */     String str = null;
/*      */     
/*      */     try
/*      */     {
/*  120 */       if (this.attributes[this.index] != null)
/*      */       {
/*  122 */         str = this.attributes[this.index].stringValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  127 */       this.index += 1;
/*      */     }
/*  129 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean readBoolean()
/*      */     throws SQLException
/*      */   {
/*  142 */     boolean bool = false;
/*      */     
/*      */     try
/*      */     {
/*  146 */       if (this.attributes[this.index] != null)
/*      */       {
/*  148 */         bool = this.attributes[this.index].booleanValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  153 */       this.index += 1;
/*      */     }
/*  155 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte readByte()
/*      */     throws SQLException
/*      */   {
/*  168 */     byte b = 0;
/*      */     
/*      */     try
/*      */     {
/*  172 */       if (this.attributes[this.index] != null)
/*      */       {
/*  174 */         b = this.attributes[this.index].byteValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  179 */       this.index += 1;
/*      */     }
/*  181 */     return b;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short readShort()
/*      */     throws SQLException
/*      */   {
/*  193 */     long l = readLong();
/*      */     
/*  195 */     if ((l > 65537L) || (l < -65538L))
/*      */     {
/*      */ 
/*  198 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 26, "readShort");
/*  199 */       localSQLException.fillInStackTrace();
/*  200 */       throw localSQLException;
/*      */     }
/*      */     
/*  203 */     return (short)(int)l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int readInt()
/*      */     throws SQLException
/*      */   {
/*  215 */     int i = 0;
/*      */     
/*      */     try
/*      */     {
/*  219 */       if (this.attributes[this.index] != null)
/*      */       {
/*  221 */         i = this.attributes[this.index].intValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  226 */       this.index += 1;
/*      */     }
/*  228 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public long readLong()
/*      */     throws SQLException
/*      */   {
/*  240 */     long l = 0L;
/*      */     
/*      */     try
/*      */     {
/*  244 */       if (this.attributes[this.index] != null)
/*      */       {
/*  246 */         l = this.attributes[this.index].longValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  251 */       this.index += 1;
/*      */     }
/*  253 */     return l;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float readFloat()
/*      */     throws SQLException
/*      */   {
/*  265 */     float f = 0.0F;
/*      */     
/*      */     try
/*      */     {
/*  269 */       if (this.attributes[this.index] != null)
/*      */       {
/*  271 */         f = this.attributes[this.index].floatValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  276 */       this.index += 1;
/*      */     }
/*  278 */     return f;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double readDouble()
/*      */     throws SQLException
/*      */   {
/*  290 */     double d = 0.0D;
/*      */     
/*      */     try
/*      */     {
/*  294 */       if (this.attributes[this.index] != null)
/*      */       {
/*  296 */         d = this.attributes[this.index].doubleValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  301 */       this.index += 1;
/*      */     }
/*  303 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BigDecimal readBigDecimal()
/*      */     throws SQLException
/*      */   {
/*  315 */     BigDecimal localBigDecimal = null;
/*      */     
/*      */     try
/*      */     {
/*  319 */       if (this.attributes[this.index] != null)
/*      */       {
/*  321 */         localBigDecimal = this.attributes[this.index].bigDecimalValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  326 */       this.index += 1;
/*      */     }
/*  328 */     return localBigDecimal;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public byte[] readBytes()
/*      */     throws SQLException
/*      */   {
/*  340 */     byte[] arrayOfByte = null;
/*      */     
/*      */     try
/*      */     {
/*  344 */       if (this.attributes[this.index] != null)
/*      */       {
/*  346 */         if ((this.attributes[this.index] instanceof RAW)) {
/*  347 */           arrayOfByte = ((RAW)this.attributes[this.index]).shareBytes();
/*      */         }
/*      */         else
/*      */         {
/*  351 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  352 */           localSQLException.fillInStackTrace();
/*  353 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  360 */       this.index += 1;
/*      */     }
/*  362 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Date readDate()
/*      */     throws SQLException
/*      */   {
/*  374 */     Date localDate = null;
/*      */     
/*      */     try
/*      */     {
/*  378 */       if (this.attributes[this.index] != null)
/*      */       {
/*  380 */         localDate = this.attributes[this.index].dateValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  385 */       this.index += 1;
/*      */     }
/*  387 */     return localDate;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Time readTime()
/*      */     throws SQLException
/*      */   {
/*  399 */     Time localTime = null;
/*      */     
/*      */     try
/*      */     {
/*  403 */       if (this.attributes[this.index] != null)
/*      */       {
/*  405 */         localTime = this.attributes[this.index].timeValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  410 */       this.index += 1;
/*      */     }
/*  412 */     return localTime;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Timestamp readTimestamp()
/*      */     throws SQLException
/*      */   {
/*  424 */     Timestamp localTimestamp = null;
/*      */     
/*      */     try
/*      */     {
/*  428 */       if (this.attributes[this.index] != null)
/*      */       {
/*  430 */         localTimestamp = this.attributes[this.index].timestampValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  435 */       this.index += 1;
/*      */     }
/*  437 */     return localTimestamp;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Reader readCharacterStream()
/*      */     throws SQLException
/*      */   {
/*  449 */     Reader localReader = null;
/*      */     
/*      */     try
/*      */     {
/*  453 */       Datum localDatum = this.attributes[this.index];
/*      */       
/*  455 */       if (localDatum != null)
/*      */       {
/*  457 */         localReader = localDatum.characterStreamValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  462 */       this.index += 1;
/*      */     }
/*  464 */     return localReader;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream readAsciiStream()
/*      */     throws SQLException
/*      */   {
/*  476 */     InputStream localInputStream = null;
/*      */     
/*      */     try
/*      */     {
/*  480 */       Datum localDatum = this.attributes[this.index];
/*      */       
/*  482 */       if (localDatum != null)
/*      */       {
/*  484 */         localInputStream = localDatum.asciiStreamValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  489 */       this.index += 1;
/*      */     }
/*  491 */     return localInputStream;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public InputStream readBinaryStream()
/*      */     throws SQLException
/*      */   {
/*  504 */     InputStream localInputStream = null;
/*      */     
/*      */     try
/*      */     {
/*  508 */       Datum localDatum = this.attributes[this.index];
/*      */       
/*  510 */       if (localDatum != null)
/*      */       {
/*  512 */         localInputStream = localDatum.binaryStreamValue();
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  517 */       this.index += 1;
/*      */     }
/*  519 */     return localInputStream;
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
/*      */   public Object readObject()
/*      */     throws SQLException
/*      */   {
/*  546 */     Datum localDatum = (Datum)readOracleObject();
/*  547 */     Object localObject = null;
/*      */     
/*  549 */     if (localDatum != null)
/*      */     {
/*  551 */       if ((localDatum instanceof STRUCT)) {
/*  552 */         localObject = ((STRUCT)localDatum).toJdbc(this.map);
/*      */       } else {
/*  554 */         localObject = localDatum.toJdbc();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  562 */     return localObject;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Ref readRef()
/*      */     throws SQLException
/*      */   {
/*  574 */     return readREF();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Blob readBlob()
/*      */     throws SQLException
/*      */   {
/*  586 */     return readBLOB();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Clob readClob()
/*      */     throws SQLException
/*      */   {
/*  598 */     return readCLOB();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Array readArray()
/*      */     throws SQLException
/*      */   {
/*  610 */     return readARRAY();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Struct readStruct()
/*      */     throws SQLException
/*      */   {
/*  622 */     return readSTRUCT();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean wasNull()
/*      */     throws SQLException
/*      */   {
/*  635 */     if (this.index == 0)
/*      */     {
/*      */ 
/*  638 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 24);
/*  639 */       localSQLException.fillInStackTrace();
/*  640 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*  644 */     boolean bool = this.attributes[(this.index - 1)] == null;
/*  645 */     return bool;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object readOracleObject()
/*      */     throws SQLException
/*      */   {
/*  657 */     return this.attributes[(this.index++)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NUMBER readNUMBER()
/*      */     throws SQLException
/*      */   {
/*  669 */     NUMBER localNUMBER = null;
/*      */     try
/*      */     {
/*  672 */       if (this.attributes[this.index] != null)
/*      */       {
/*  674 */         if ((this.attributes[this.index] instanceof NUMBER)) {
/*  675 */           localNUMBER = (NUMBER)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  679 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  680 */           localSQLException.fillInStackTrace();
/*  681 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  688 */       this.index += 1;
/*      */     }
/*  690 */     return localNUMBER;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CHAR readCHAR()
/*      */     throws SQLException
/*      */   {
/*  702 */     CHAR localCHAR = null;
/*      */     
/*      */     try
/*      */     {
/*  706 */       if (this.attributes[this.index] != null)
/*      */       {
/*  708 */         if ((this.attributes[this.index] instanceof CHAR)) {
/*  709 */           localCHAR = (CHAR)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  713 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  714 */           localSQLException.fillInStackTrace();
/*  715 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  722 */       this.index += 1;
/*      */     }
/*  724 */     return localCHAR;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DATE readDATE()
/*      */     throws SQLException
/*      */   {
/*  736 */     DATE localDATE = null;
/*      */     
/*      */     try
/*      */     {
/*  740 */       if (this.attributes[this.index] != null)
/*      */       {
/*  742 */         if ((this.attributes[this.index] instanceof DATE)) {
/*  743 */           localDATE = (DATE)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  747 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  748 */           localSQLException.fillInStackTrace();
/*  749 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  756 */       this.index += 1;
/*      */     }
/*  758 */     return localDATE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BFILE readBFILE()
/*      */     throws SQLException
/*      */   {
/*  770 */     BFILE localBFILE = null;
/*      */     
/*      */     try
/*      */     {
/*  774 */       if (this.attributes[this.index] != null)
/*      */       {
/*  776 */         if ((this.attributes[this.index] instanceof BFILE)) {
/*  777 */           localBFILE = (BFILE)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  781 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  782 */           localSQLException.fillInStackTrace();
/*  783 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  790 */       this.index += 1;
/*      */     }
/*  792 */     return localBFILE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BLOB readBLOB()
/*      */     throws SQLException
/*      */   {
/*  804 */     BLOB localBLOB = null;
/*      */     
/*      */     try
/*      */     {
/*  808 */       if (this.attributes[this.index] != null)
/*      */       {
/*  810 */         if ((this.attributes[this.index] instanceof BLOB)) {
/*  811 */           localBLOB = (BLOB)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  815 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  816 */           localSQLException.fillInStackTrace();
/*  817 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  824 */       this.index += 1;
/*      */     }
/*  826 */     return localBLOB;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CLOB readCLOB()
/*      */     throws SQLException
/*      */   {
/*  838 */     CLOB localCLOB = null;
/*      */     
/*      */     try
/*      */     {
/*  842 */       if (this.attributes[this.index] != null)
/*      */       {
/*  844 */         if ((this.attributes[this.index] instanceof CLOB)) {
/*  845 */           localCLOB = (CLOB)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  849 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  850 */           localSQLException.fillInStackTrace();
/*  851 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  858 */       this.index += 1;
/*      */     }
/*  860 */     return localCLOB;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RAW readRAW()
/*      */     throws SQLException
/*      */   {
/*  872 */     RAW localRAW = null;
/*      */     
/*      */     try
/*      */     {
/*  876 */       if (this.attributes[this.index] != null)
/*      */       {
/*  878 */         if ((this.attributes[this.index] instanceof RAW)) {
/*  879 */           localRAW = (RAW)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  883 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  884 */           localSQLException.fillInStackTrace();
/*  885 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  892 */       this.index += 1;
/*      */     }
/*  894 */     return localRAW;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public REF readREF()
/*      */     throws SQLException
/*      */   {
/*  906 */     REF localREF = null;
/*      */     
/*      */     try
/*      */     {
/*  910 */       if (this.attributes[this.index] != null)
/*      */       {
/*  912 */         if ((this.attributes[this.index] instanceof REF)) {
/*  913 */           localREF = (REF)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  917 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  918 */           localSQLException.fillInStackTrace();
/*  919 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  926 */       this.index += 1;
/*      */     }
/*  928 */     return localREF;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ROWID readROWID()
/*      */     throws SQLException
/*      */   {
/*  940 */     ROWID localROWID = null;
/*      */     
/*      */     try
/*      */     {
/*  944 */       if (this.attributes[this.index] != null)
/*      */       {
/*  946 */         if ((this.attributes[this.index] instanceof ROWID)) {
/*  947 */           localROWID = (ROWID)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  951 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  952 */           localSQLException.fillInStackTrace();
/*  953 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  960 */       this.index += 1;
/*      */     }
/*  962 */     return localROWID;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ARRAY readARRAY()
/*      */     throws SQLException
/*      */   {
/*  974 */     ARRAY localARRAY = null;
/*      */     
/*      */     try
/*      */     {
/*  978 */       if (this.attributes[this.index] != null)
/*      */       {
/*  980 */         if ((this.attributes[this.index] instanceof ARRAY)) {
/*  981 */           localARRAY = (ARRAY)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/*  985 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/*  986 */           localSQLException.fillInStackTrace();
/*  987 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/*  994 */       this.index += 1;
/*      */     }
/*  996 */     return localARRAY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public STRUCT readSTRUCT()
/*      */     throws SQLException
/*      */   {
/* 1008 */     STRUCT localSTRUCT = null;
/*      */     
/*      */     try
/*      */     {
/* 1012 */       if (this.attributes[this.index] != null)
/*      */       {
/* 1014 */         if ((this.attributes[this.index] instanceof STRUCT)) {
/* 1015 */           localSTRUCT = (STRUCT)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/* 1019 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/* 1020 */           localSQLException.fillInStackTrace();
/* 1021 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 1028 */       this.index += 1;
/*      */     }
/* 1030 */     return localSTRUCT;
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
/*      */   public URL readURL()
/*      */     throws SQLException
/*      */   {
/* 1053 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 1054 */     localSQLException.fillInStackTrace();
/* 1055 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NClob readNClob()
/*      */     throws SQLException
/*      */   {
/* 1067 */     NCLOB localNCLOB = null;
/*      */     
/*      */     try
/*      */     {
/* 1071 */       if (this.attributes[this.index] != null)
/*      */       {
/* 1073 */         if ((this.attributes[this.index] instanceof NCLOB)) {
/* 1074 */           localNCLOB = (NCLOB)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/* 1078 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/* 1079 */           localSQLException.fillInStackTrace();
/* 1080 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 1087 */       this.index += 1;
/*      */     }
/* 1089 */     return localNCLOB;
/*      */   }
/*      */   
/*      */ 
/*      */   public String readNString()
/*      */     throws SQLException
/*      */   {
/* 1096 */     return readString();
/*      */   }
/*      */   
/*      */ 
/*      */   public SQLXML readSQLXML()
/*      */     throws SQLException
/*      */   {
/* 1103 */     SQLXML localSQLXML = null;
/*      */     
/*      */     try
/*      */     {
/* 1107 */       if (this.attributes[this.index] != null)
/*      */       {
/* 1109 */         if ((this.attributes[this.index] instanceof SQLXML)) {
/* 1110 */           localSQLXML = (SQLXML)this.attributes[this.index];
/*      */         }
/*      */         else
/*      */         {
/* 1114 */           SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4, null);
/* 1115 */           localSQLException.fillInStackTrace();
/* 1116 */           throw localSQLException;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */     finally
/*      */     {
/* 1123 */       this.index += 1;
/*      */     }
/* 1125 */     return localSQLXML;
/*      */   }
/*      */   
/*      */ 
/*      */   public RowId readRowId()
/*      */     throws SQLException
/*      */   {
/* 1132 */     return readROWID();
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
/*      */   protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1149 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1154 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/OracleJdbc2SQLInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */