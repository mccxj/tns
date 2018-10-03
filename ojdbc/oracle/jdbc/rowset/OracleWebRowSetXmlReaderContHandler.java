/*      */ package oracle.jdbc.rowset;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Blob;
/*      */ import java.sql.Clob;
/*      */ import java.sql.Date;
/*      */ import java.sql.NClob;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Time;
/*      */ import java.sql.Timestamp;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Iterator;
/*      */ import java.util.Vector;
/*      */ import javax.sql.RowSet;
/*      */ import javax.sql.RowSetMetaData;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import org.xml.sax.Attributes;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.SAXParseException;
/*      */ import org.xml.sax.helpers.DefaultHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class OracleWebRowSetXmlReaderContHandler
/*      */   extends DefaultHandler
/*      */ {
/*      */   private OracleWebRowSet wrset;
/*      */   private RowSetMetaData rsetMetaData;
/*      */   private Vector updatesToRowSet;
/*      */   private Vector keyCols;
/*      */   private String columnValue;
/*      */   private String propertyValue;
/*      */   private String metadataValue;
/*      */   private boolean isNullValue;
/*      */   private int columnIndex;
/*      */   private Hashtable propertyNameTagMap;
/*      */   private Hashtable metadataNameTagMap;
/*      */   private Hashtable dataNameTagMap;
/*      */   protected static final String WEBROWSET_ELEMENT_NAME = "webRowSet";
/*      */   protected static final String PROPERTIES_ELEMENT_NAME = "properties";
/*      */   protected static final String METADATA_ELEMENT_NAME = "metadata";
/*      */   protected static final String DATA_ELEMENT_NAME = "data";
/*      */   private int state;
/*      */   private static final int INITIAL_STATE = 0;
/*      */   private static final int PROPERTIES_STATE = 1;
/*      */   private static final int METADATA_STATE = 2;
/*      */   private static final int DATA_STATE = 3;
/*      */   private int tag;
/*      */   private static final int NO_TAG = -1;
/*   85 */   private String[] propertyNames = { "command", "concurrency", "datasource", "escape-processing", "fetch-direction", "fetch-size", "isolation-level", "key-columns", "map", "max-field-size", "max-rows", "query-timeout", "read-only", "rowset-type", "show-deleted", "table-name", "url", "sync-provider", "null", "column", "type", "class", "sync-provider-name", "sync-provider-vendor", "sync-provider-version", "sync-provider-grade", "data-source-lock" };
/*      */   
/*      */ 
/*      */   private boolean readReadOnlyValue;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_COMMAND_TAG = 0;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_CONCURRENCY_TAG = 1;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_DATASOURCETAG = 2;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_ESCAPEPROCESSING_TAG = 3;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_FETCHDIRECTION_TAG = 4;
/*      */   
/*      */ 
/*      */   private static final int PROPERTY_FETCHSIZE_TAG = 5;
/*      */   
/*      */   private static final int PROPERTY_ISOLATIONLEVEL_TAG = 6;
/*      */   
/*      */   private static final int PROPERTY_KEYCOLUMNS_TAG = 7;
/*      */   
/*      */   private static final int PROPERTY_MAP_TAG = 8;
/*      */   
/*      */   private static final int PROPERTY_MAXFIELDSIZE_TAG = 9;
/*      */   
/*      */   private static final int PROPERTY_MAXROWS_TAG = 10;
/*      */   
/*      */   private static final int PROPERTY_QUERYTIMEOUT_TAG = 11;
/*      */   
/*      */   private static final int PROPERTY_READONLY_TAG = 12;
/*      */   
/*      */   private static final int PROPERTY_ROWSETTYPE_TAG = 13;
/*      */   
/*      */   private static final int PROPERTY_SHOWDELETED_TAG = 14;
/*      */   
/*      */   private static final int PROPERTY_TABLENAME_TAG = 15;
/*      */   
/*      */   private static final int PROPERTY_URL_TAG = 16;
/*      */   
/*      */   private static final int PROPERTY_SYNCPROVIDER_TAG = 17;
/*      */   
/*      */   private static final int PROPERTY_NULL_TAG = 18;
/*      */   
/*      */   private static final int PROPERTY_COLUMN_TAG = 19;
/*      */   
/*      */   private static final int PROPERTY_TYPE_TAG = 20;
/*      */   
/*      */   private static final int PROPERTY_CLASS_TAG = 21;
/*      */   
/*      */   private static final int PROPERTY_SYNCPROVIDERNAME_TAG = 22;
/*      */   
/*      */   private static final int PROPERTY_SYNCPROVIDERVENDOR_TAG = 23;
/*      */   
/*      */   private static final int PROPERTY_SYNCPROVIDERVERSION_TAG = 24;
/*      */   
/*      */   private static final int PROPERTY_SYNCPROVIDERGRADE_TAG = 25;
/*      */   
/*      */   private static final int PROPERTY_DATASOURCELOCK_TAG = 26;
/*      */   
/*  150 */   private String[] metadataNames = { "column-count", "column-definition", "column-index", "auto-increment", "case-sensitive", "currency", "nullable", "signed", "searchable", "column-display-size", "column-label", "column-name", "schema-name", "column-precision", "column-scale", "table-name", "catalog-name", "column-type", "column-type-name", "null" };
/*      */   
/*      */ 
/*      */   private static final int METADATA_COLUMNCOUNT_TAG = 0;
/*      */   
/*      */ 
/*      */   private static final int METADATA_COLUMNDEFINITION_TAG = 1;
/*      */   
/*      */ 
/*      */   private static final int METADATA_COLUMNINDEX_TAG = 2;
/*      */   
/*      */ 
/*      */   private static final int METADATA_AUTOINCREMENT_TAG = 3;
/*      */   
/*      */ 
/*      */   private static final int METADATA_CASESENSITIVE_TAG = 4;
/*      */   
/*      */ 
/*      */   private static final int METADATA_CURRENCY_TAG = 5;
/*      */   
/*      */   private static final int METADATA_NULLABLE_TAG = 6;
/*      */   
/*      */   private static final int METADATA_SIGNED_TAG = 7;
/*      */   
/*      */   private static final int METADATA_SEARCHABLE_TAG = 8;
/*      */   
/*      */   private static final int METADATA_COLUMNDISPLAYSIZE_TAG = 9;
/*      */   
/*      */   private static final int METADATA_COLUMNLABEL_TAG = 10;
/*      */   
/*      */   private static final int METADATA_COLUMNNAME_TAG = 11;
/*      */   
/*      */   private static final int METADATA_SCHEMANAME_TAG = 12;
/*      */   
/*      */   private static final int METADATA_COLUMNPRECISION_TAG = 13;
/*      */   
/*      */   private static final int METADATA_COLUMNSCALE_TAG = 14;
/*      */   
/*      */   private static final int METADATA_TABLENAME_TAG = 15;
/*      */   
/*      */   private static final int METADATA_CATALOGNAME_TAG = 16;
/*      */   
/*      */   private static final int METADATA_COLUMNTYPE_TAG = 17;
/*      */   
/*      */   private static final int METADATA_COLUMNTYPENAME_TAG = 18;
/*      */   
/*      */   private static final int METADATA_NULL_TAG = 19;
/*      */   
/*  198 */   private String[] dataNames = { "currentRow", "insertRow", "deleteRow", "modifyRow", "columnValue", "updateValue", "null" };
/*      */   
/*      */ 
/*      */   private static final int DATA_CURRENTROW_TAG = 0;
/*      */   
/*      */ 
/*      */   private static final int DATA_INSERTROW_TAG = 1;
/*      */   
/*      */ 
/*      */   private static final int DATA_DELETEROW_TAG = 2;
/*      */   
/*      */ 
/*      */   private static final int DATA_MODIFYROW_TAG = 3;
/*      */   
/*      */ 
/*      */   private static final int DATA_COLUMNVALUE_TAG = 4;
/*      */   
/*      */ 
/*      */   private static final int DATA_UPDATEVALUE_TAG = 5;
/*      */   
/*      */ 
/*      */   private static final int DATA_NULL_TAG = 6;
/*      */   
/*      */ 
/*      */ 
/*      */   OracleWebRowSetXmlReaderContHandler(RowSet paramRowSet)
/*      */   {
/*  225 */     this.wrset = ((OracleWebRowSet)paramRowSet);
/*      */     
/*  227 */     initialize();
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
/*      */   public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
/*      */     throws SAXException
/*      */   {
/*  248 */     String str = new String(paramArrayOfChar, paramInt1, paramInt2);
/*  249 */     processElement(str);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void endDocument()
/*      */     throws SAXException
/*      */   {
/*      */     try
/*      */     {
/*  262 */       if (this.readReadOnlyValue)
/*      */       {
/*  264 */         this.wrset.setReadOnly(this.readReadOnlyValue);
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  270 */       throw new SAXException(localSQLException.getMessage());
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
/*      */ 
/*      */   public void endElement(String paramString1, String paramString2, String paramString3)
/*      */     throws SAXException
/*      */   {
/*  293 */     String str = (paramString2 == null) || (paramString2.equals("")) ? paramString3 : paramString2;
/*      */     
/*  295 */     switch (getState())
/*      */     {
/*      */     case 1: 
/*  298 */       if (str.equals("properties"))
/*      */       {
/*  300 */         this.state = 0;
/*      */       }
/*      */       else
/*      */       {
/*      */         try
/*      */         {
/*  306 */           int i = ((Integer)this.propertyNameTagMap.get(str)).intValue();
/*      */           
/*  308 */           switch (i)
/*      */           {
/*      */           case 7: 
/*  311 */             if (this.keyCols != null)
/*      */             {
/*  313 */               int[] arrayOfInt = new int[this.keyCols.size()];
/*      */               
/*  315 */               for (int k = 0; k < arrayOfInt.length; k++) {
/*  316 */                 arrayOfInt[k] = Integer.parseInt((String)this.keyCols.elementAt(k));
/*      */               }
/*  318 */               this.wrset.setKeyColumns(arrayOfInt);
/*      */             }
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*      */             break;
/*      */           }
/*      */           
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  331 */           setPropertyValue(this.propertyValue);
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException1)
/*      */         {
/*  336 */           throw new SAXException(localSQLException1.getMessage());
/*      */         }
/*      */         
/*      */ 
/*  340 */         this.propertyValue = "";
/*      */         
/*      */ 
/*  343 */         setNullValue(false);
/*      */         
/*      */ 
/*  346 */         setTag(-1);
/*      */       }
/*      */       
/*  349 */       break;
/*      */     
/*      */     case 2: 
/*  352 */       if (str.equals("metadata"))
/*      */       {
/*      */         try
/*      */         {
/*  356 */           this.wrset.setMetaData(this.rsetMetaData);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  361 */           this.state = 0;
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException2)
/*      */         {
/*      */ 
/*  367 */           throw new SAXException("Error setting metadata in WebRowSet: " + localSQLException2.getMessage());
/*      */ 
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*      */         try
/*      */         {
/*      */ 
/*      */ 
/*  384 */           setMetaDataValue(this.metadataValue);
/*      */ 
/*      */ 
/*      */ 
/*      */         }
/*      */         catch (SQLException localSQLException3)
/*      */         {
/*      */ 
/*      */ 
/*  393 */           throw new SAXException("Error setting metadata value: " + localSQLException3.getMessage());
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  399 */         this.metadataValue = "";
/*      */         
/*      */ 
/*  402 */         setNullValue(false);
/*      */         
/*      */ 
/*  405 */         setTag(-1);
/*      */       }
/*      */       
/*  408 */       break;
/*      */     
/*      */     case 3: 
/*  411 */       if (str.equals("data"))
/*      */       {
/*  413 */         this.state = 0;
/*  414 */         return;
/*      */       }
/*      */       
/*      */ 
/*  418 */       int j = ((Integer)this.dataNameTagMap.get(str)).intValue();
/*      */       
/*  420 */       switch (j)
/*      */       {
/*      */       default: 
/*      */         break;
/*      */       
/*      */       case 4: 
/*      */         try
/*      */         {
/*  428 */           this.columnIndex += 1;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  439 */           insertValue(this.columnValue);
/*      */           
/*      */ 
/*      */ 
/*  443 */           this.columnValue = "";
/*      */           
/*      */ 
/*  446 */           setNullValue(false);
/*      */           
/*      */ 
/*  449 */           setTag(-1);
/*      */         }
/*      */         catch (SQLException localSQLException4)
/*      */         {
/*  453 */           throw new SAXException("Error inserting column values: " + localSQLException4.getMessage());
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case 0: 
/*      */         try
/*      */         {
/*  463 */           this.wrset.insertRow();
/*  464 */           this.wrset.moveToCurrentRow();
/*  465 */           this.wrset.next();
/*      */           
/*  467 */           OracleRow localOracleRow1 = this.wrset.getCurrentRow();
/*  468 */           localOracleRow1.setInsertedFlag(false);
/*  469 */           applyUpdates();
/*      */         }
/*      */         catch (SQLException localSQLException5)
/*      */         {
/*  473 */           throw new SAXException("Error constructing current row: " + localSQLException5.getMessage());
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case 2: 
/*      */         try
/*      */         {
/*  483 */           this.wrset.insertRow();
/*  484 */           this.wrset.moveToCurrentRow();
/*  485 */           this.wrset.next();
/*      */           
/*  487 */           OracleRow localOracleRow2 = this.wrset.getCurrentRow();
/*  488 */           localOracleRow2.setInsertedFlag(false);
/*  489 */           localOracleRow2.setRowDeleted(true);
/*  490 */           applyUpdates();
/*      */         }
/*      */         catch (SQLException localSQLException6)
/*      */         {
/*  494 */           throw new SAXException("Error constructing deleted row: " + localSQLException6.getMessage());
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case 1: 
/*      */         try
/*      */         {
/*  504 */           this.wrset.insertRow();
/*  505 */           this.wrset.moveToCurrentRow();
/*  506 */           this.wrset.next();
/*      */           
/*  508 */           applyUpdates();
/*      */         }
/*      */         catch (SQLException localSQLException7)
/*      */         {
/*  512 */           throw new SAXException("Error constructing inserted row: " + localSQLException7.getMessage());
/*      */         }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       case 3: 
/*      */         try
/*      */         {
/*  522 */           this.wrset.insertRow();
/*  523 */           this.wrset.moveToCurrentRow();
/*  524 */           this.wrset.next();
/*      */           
/*  526 */           OracleRow localOracleRow3 = this.wrset.getCurrentRow();
/*  527 */           localOracleRow3.setRowDeleted(true);
/*  528 */           applyUpdates();
/*      */         }
/*      */         catch (SQLException localSQLException8)
/*      */         {
/*  532 */           throw new SAXException("Error constructing modified row: " + localSQLException8.getMessage());
/*      */         }
/*      */       }
/*      */       
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
/*      */     throws SAXException
/*      */   {
/*  551 */     String str = (paramString2 == null) || (paramString2.equals("")) ? paramString3 : paramString2;
/*      */     
/*      */ 
/*  554 */     switch (getState())
/*      */     {
/*      */     case 1: 
/*  557 */       int i = ((Integer)this.propertyNameTagMap.get(str)).intValue();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  562 */       if (i == 18)
/*      */       {
/*      */ 
/*  565 */         setNullValue(true);
/*  566 */         this.propertyValue = null;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  571 */         setTag(i);
/*      */       }
/*      */       
/*  574 */       break;
/*      */     
/*      */     case 2: 
/*  577 */       int j = ((Integer)this.metadataNameTagMap.get(str)).intValue();
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  582 */       if (j == 19)
/*      */       {
/*      */ 
/*  585 */         setNullValue(true);
/*  586 */         this.metadataValue = null;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  591 */         setTag(j);
/*      */       }
/*      */       
/*  594 */       break;
/*      */     
/*      */     case 3: 
/*  597 */       int k = ((Integer)this.dataNameTagMap.get(str)).intValue();
/*      */       
/*      */ 
/*  600 */       if (k == 6)
/*      */       {
/*  602 */         setNullValue(true);
/*  603 */         this.columnValue = null;
/*      */       }
/*      */       else
/*      */       {
/*  607 */         setTag(k);
/*      */         
/*  609 */         if ((k == 0) || (k == 1) || (k == 2) || (k == 3))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  617 */           this.columnIndex = 0;
/*      */           
/*      */           try
/*      */           {
/*  621 */             this.wrset.moveToInsertRow();
/*      */           }
/*      */           catch (SQLException localSQLException) {}
/*      */         }
/*      */       }
/*      */       break;
/*      */     default: 
/*  628 */       setState(str);
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void error(SAXParseException paramSAXParseException)
/*      */     throws SAXParseException
/*      */   {
/*  638 */     throw paramSAXParseException;
/*      */   }
/*      */   
/*      */ 
/*      */   public void warning(SAXParseException paramSAXParseException)
/*      */     throws SAXParseException
/*      */   {
/*  645 */     System.out.println("** Warning, line " + paramSAXParseException.getLineNumber() + ", uri " + paramSAXParseException.getSystemId());
/*      */     
/*  647 */     System.out.println("   " + paramSAXParseException.getMessage());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void initialize()
/*      */   {
/*  659 */     this.propertyNameTagMap = new Hashtable(30);
/*  660 */     int i = this.propertyNames.length;
/*  661 */     for (int j = 0; j < i; j++) {
/*  662 */       this.propertyNameTagMap.put(this.propertyNames[j], Integer.valueOf(j));
/*      */     }
/*  664 */     this.metadataNameTagMap = new Hashtable(30);
/*  665 */     i = this.metadataNames.length;
/*  666 */     for (j = 0; j < i; j++) {
/*  667 */       this.metadataNameTagMap.put(this.metadataNames[j], Integer.valueOf(j));
/*      */     }
/*  669 */     this.dataNameTagMap = new Hashtable(10);
/*  670 */     i = this.dataNames.length;
/*  671 */     for (j = 0; j < i; j++) {
/*  672 */       this.dataNameTagMap.put(this.dataNames[j], Integer.valueOf(j));
/*      */     }
/*  674 */     this.updatesToRowSet = new Vector();
/*  675 */     this.columnValue = "";
/*  676 */     this.propertyValue = "";
/*  677 */     this.metadataValue = "";
/*  678 */     this.isNullValue = false;
/*  679 */     this.columnIndex = 0;
/*      */     
/*  681 */     this.readReadOnlyValue = false;
/*      */   }
/*      */   
/*      */ 
/*      */   protected void processElement(String paramString)
/*      */     throws SAXException
/*      */   {
/*      */     try
/*      */     {
/*  690 */       switch (getState())
/*      */       {
/*      */       case 1: 
/*  693 */         this.propertyValue = paramString;
/*  694 */         break;
/*      */       
/*      */       case 2: 
/*  697 */         this.metadataValue = paramString;
/*  698 */         break;
/*      */       
/*      */       case 3: 
/*  701 */         setDataValue(paramString);
/*      */       
/*      */       }
/*      */       
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/*  708 */       throw new SAXException("processElement: " + localSQLException.getMessage());
/*      */     }
/*      */   }
/*      */   
/*      */   private BigDecimal getBigDecimalValue(String paramString)
/*      */   {
/*  714 */     return new BigDecimal(paramString);
/*      */   }
/*      */   
/*      */   private byte[] getBinaryValue(String paramString)
/*      */   {
/*  719 */     return paramString.getBytes();
/*      */   }
/*      */   
/*      */   private boolean getBooleanValue(String paramString)
/*      */   {
/*  724 */     return Boolean.valueOf(paramString).booleanValue();
/*      */   }
/*      */   
/*      */   private byte getByteValue(String paramString)
/*      */   {
/*  729 */     return Byte.parseByte(paramString);
/*      */   }
/*      */   
/*      */   private Date getDateValue(String paramString)
/*      */   {
/*  734 */     return new Date(getLongValue(paramString));
/*      */   }
/*      */   
/*      */   private double getDoubleValue(String paramString)
/*      */   {
/*  739 */     return Double.parseDouble(paramString);
/*      */   }
/*      */   
/*      */   private float getFloatValue(String paramString)
/*      */   {
/*  744 */     return Float.parseFloat(paramString);
/*      */   }
/*      */   
/*      */   private int getIntegerValue(String paramString)
/*      */   {
/*  749 */     return Integer.parseInt(paramString);
/*      */   }
/*      */   
/*      */   private long getLongValue(String paramString)
/*      */   {
/*  754 */     return Long.parseLong(paramString);
/*      */   }
/*      */   
/*      */   private boolean getNullValue()
/*      */   {
/*  759 */     return this.isNullValue;
/*      */   }
/*      */   
/*      */   private short getShortValue(String paramString)
/*      */   {
/*  764 */     return Short.parseShort(paramString);
/*      */   }
/*      */   
/*      */   private String getStringValue(String paramString)
/*      */   {
/*  769 */     return paramString;
/*      */   }
/*      */   
/*      */   private Time getTimeValue(String paramString)
/*      */   {
/*  774 */     return new Time(getLongValue(paramString));
/*      */   }
/*      */   
/*      */   private Timestamp getTimestampValue(String paramString)
/*      */   {
/*  779 */     return new Timestamp(getLongValue(paramString));
/*      */   }
/*      */   
/*      */   private Blob getBlobValue(String paramString)
/*      */     throws SQLException
/*      */   {
/*  785 */     return new OracleSerialBlob(paramString.getBytes());
/*      */   }
/*      */   
/*      */   private Clob getClobValue(String paramString)
/*      */     throws SQLException
/*      */   {
/*  791 */     return new OracleSerialClob(paramString.toCharArray());
/*      */   }
/*      */   
/*      */   private void applyUpdates()
/*      */     throws SAXException
/*      */   {
/*  797 */     if (this.updatesToRowSet.size() > 0)
/*      */     {
/*      */ 
/*      */       try
/*      */       {
/*  802 */         Iterator localIterator = this.updatesToRowSet.iterator();
/*  803 */         while (localIterator.hasNext())
/*      */         {
/*  805 */           Object[] arrayOfObject = (Object[])localIterator.next();
/*  806 */           this.columnIndex = ((Integer)arrayOfObject[0]).intValue();
/*  807 */           insertValue((String)arrayOfObject[1]);
/*      */         }
/*      */         
/*  810 */         this.wrset.updateRow();
/*      */       }
/*      */       catch (SQLException localSQLException)
/*      */       {
/*  814 */         throw new SAXException("Error updating row: " + localSQLException.getMessage());
/*      */       }
/*      */       
/*      */ 
/*  818 */       this.updatesToRowSet.removeAllElements();
/*      */     }
/*      */   }
/*      */   
/*      */   private void insertValue(String paramString)
/*      */     throws SQLException
/*      */   {
/*  825 */     if ((getNullValue()) || (paramString == null))
/*      */     {
/*  827 */       this.wrset.updateNull(this.columnIndex);
/*  828 */       return;
/*      */     }
/*      */     
/*  831 */     int i = this.wrset.getMetaData().getColumnType(this.columnIndex);
/*      */     
/*  833 */     switch (i)
/*      */     {
/*      */     case -7: 
/*  836 */       this.wrset.updateByte(this.columnIndex, getByteValue(paramString));
/*  837 */       break;
/*      */     
/*      */     case 5: 
/*  840 */       this.wrset.updateShort(this.columnIndex, getShortValue(paramString));
/*  841 */       break;
/*      */     
/*      */     case 4: 
/*  844 */       this.wrset.updateInt(this.columnIndex, getIntegerValue(paramString));
/*  845 */       break;
/*      */     
/*      */     case -5: 
/*  848 */       this.wrset.updateLong(this.columnIndex, getLongValue(paramString));
/*  849 */       break;
/*      */     
/*      */     case 6: 
/*      */     case 7: 
/*  853 */       this.wrset.updateFloat(this.columnIndex, getFloatValue(paramString));
/*  854 */       break;
/*      */     
/*      */     case 8: 
/*  857 */       this.wrset.updateDouble(this.columnIndex, getDoubleValue(paramString));
/*  858 */       break;
/*      */     
/*      */     case 2: 
/*      */     case 3: 
/*  862 */       this.wrset.updateObject(this.columnIndex, getBigDecimalValue(paramString));
/*  863 */       break;
/*      */     
/*      */     case -4: 
/*      */     case -3: 
/*      */     case -2: 
/*  868 */       this.wrset.updateBytes(this.columnIndex, getBinaryValue(paramString));
/*  869 */       break;
/*      */     
/*      */     case 91: 
/*  872 */       this.wrset.updateDate(this.columnIndex, getDateValue(paramString));
/*  873 */       break;
/*      */     
/*      */     case 92: 
/*  876 */       this.wrset.updateTime(this.columnIndex, getTimeValue(paramString));
/*  877 */       break;
/*      */     
/*      */     case 93: 
/*  880 */       this.wrset.updateTimestamp(this.columnIndex, getTimestampValue(paramString));
/*  881 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case -15: 
/*      */     case -9: 
/*      */     case -1: 
/*      */     case 1: 
/*      */     case 12: 
/*  892 */       this.wrset.updateString(this.columnIndex, getStringValue(paramString));
/*  893 */       break;
/*      */     
/*      */     case 2004: 
/*  896 */       this.wrset.updateBlob(this.columnIndex, getBlobValue(paramString));
/*  897 */       break;
/*      */     
/*      */     case 2005: 
/*  900 */       this.wrset.updateClob(this.columnIndex, getClobValue(paramString));
/*  901 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     case 2011: 
/*  907 */       this.wrset.updateNClob(this.columnIndex, (NClob)getClobValue(paramString));
/*  908 */       break;
/*      */     
/*      */ 
/*      */     default: 
/*  912 */       throw new SQLException("The type " + i + " is not supported currently.");
/*      */     }
/*      */   }
/*      */   
/*      */   private void setPropertyValue(String paramString)
/*      */     throws SQLException
/*      */   {
/*  919 */     boolean bool = getNullValue();
/*      */     SQLException localSQLException1;
/*  921 */     SQLException localSQLException2; switch (getTag()) {
/*      */     case 7: 
/*      */     case 8: 
/*      */     case 17: 
/*      */     case 18: 
/*      */     case 20: 
/*      */     case 21: 
/*      */     default: 
/*      */       break;
/*      */     case 0: 
/*  931 */       if (bool)
/*      */       {
/*      */ 
/*  934 */         this.wrset.setCommand(null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  939 */         this.wrset.setCommand(paramString);
/*      */       }
/*      */       
/*  942 */       break;
/*      */     
/*      */     case 1: 
/*  945 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/*  949 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/*  950 */         localSQLException1.fillInStackTrace();
/*  951 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  956 */       this.wrset.setConcurrency(getIntegerValue(paramString));
/*  957 */       break;
/*      */     
/*      */     case 2: 
/*  960 */       if (bool)
/*      */       {
/*      */ 
/*  963 */         this.wrset.setDataSourceName(null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  968 */         this.wrset.setDataSourceName(paramString);
/*      */       }
/*      */       
/*  971 */       break;
/*      */     
/*      */     case 3: 
/*  974 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/*  978 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/*  979 */         localSQLException1.fillInStackTrace();
/*  980 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  985 */       this.wrset.setEscapeProcessing(getBooleanValue(paramString));
/*  986 */       break;
/*      */     
/*      */     case 4: 
/*  989 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/*  993 */         localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/*  994 */         localSQLException1.fillInStackTrace();
/*  995 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1000 */       int i = this.wrset.getType();
/* 1001 */       if (i != 1005)
/*      */       {
/* 1003 */         this.wrset.setFetchDirection(getIntegerValue(paramString));
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     case 5: 
/* 1009 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1013 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1014 */         localSQLException2.fillInStackTrace();
/* 1015 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1020 */       this.wrset.setFetchSize(getIntegerValue(paramString));
/* 1021 */       break;
/*      */     
/*      */     case 6: 
/* 1024 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1028 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1029 */         localSQLException2.fillInStackTrace();
/* 1030 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1035 */       this.wrset.setTransactionIsolation(getIntegerValue(paramString));
/* 1036 */       break;
/*      */     
/*      */     case 19: 
/* 1039 */       if (this.keyCols == null)
/*      */       {
/* 1041 */         this.keyCols = new Vector();
/*      */       }
/*      */       
/* 1044 */       this.keyCols.add(paramString);
/* 1045 */       break;
/*      */     
/*      */     case 9: 
/* 1048 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1052 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1053 */         localSQLException2.fillInStackTrace();
/* 1054 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1059 */       this.wrset.setMaxFieldSize(getIntegerValue(paramString));
/* 1060 */       break;
/*      */     
/*      */     case 10: 
/* 1063 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1067 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1068 */         localSQLException2.fillInStackTrace();
/* 1069 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1074 */       this.wrset.setMaxRows(getIntegerValue(paramString));
/* 1075 */       break;
/*      */     
/*      */     case 11: 
/* 1078 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1082 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1083 */         localSQLException2.fillInStackTrace();
/* 1084 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1089 */       this.wrset.setQueryTimeout(getIntegerValue(paramString));
/* 1090 */       break;
/*      */     
/*      */     case 12: 
/* 1093 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1097 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1098 */         localSQLException2.fillInStackTrace();
/* 1099 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1105 */       this.readReadOnlyValue = getBooleanValue(paramString);
/*      */       
/* 1107 */       break;
/*      */     
/*      */     case 13: 
/* 1110 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1114 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1115 */         localSQLException2.fillInStackTrace();
/* 1116 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1121 */       this.wrset.setType(getIntegerValue(paramString));
/*      */       
/* 1123 */       break;
/*      */     
/*      */     case 14: 
/* 1126 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1130 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 357);
/* 1131 */         localSQLException2.fillInStackTrace();
/* 1132 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1137 */       this.wrset.setShowDeleted(getBooleanValue(paramString));
/* 1138 */       break;
/*      */     
/*      */     case 15: 
/* 1141 */       if (bool)
/*      */       {
/*      */ 
/* 1144 */         this.wrset.setTableName(null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1149 */         this.wrset.setTableName(paramString);
/*      */       }
/*      */       
/* 1152 */       break;
/*      */     
/*      */     case 16: 
/* 1155 */       if (bool)
/*      */       {
/*      */ 
/* 1158 */         this.wrset.setUrl(null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1163 */         this.wrset.setUrl(paramString);
/*      */       }
/*      */       
/* 1166 */       break;
/*      */     
/*      */     case 22: 
/* 1169 */       if (bool)
/*      */       {
/*      */ 
/* 1172 */         this.wrset.setSyncProvider(null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1177 */         this.wrset.setSyncProvider(paramString);
/*      */       }
/*      */       
/*      */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   private void setMetaDataValue(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1188 */     boolean bool = getNullValue();
/*      */     SQLException localSQLException2;
/* 1190 */     switch (getTag())
/*      */     {
/*      */     case 1: 
/*      */     default: 
/*      */       break;
/*      */     
/*      */     case 0: 
/* 1197 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1201 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1202 */         localSQLException1.fillInStackTrace();
/* 1203 */         throw localSQLException1;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1208 */       int i = getIntegerValue(paramString);
/*      */       
/*      */ 
/* 1211 */       this.rsetMetaData = new OracleRowSetMetaData(i);
/*      */       
/*      */ 
/* 1214 */       this.columnIndex = 0;
/*      */       
/* 1216 */       break;
/*      */     
/*      */     case 2: 
/* 1219 */       this.columnIndex += 1;
/* 1220 */       break;
/*      */     
/*      */     case 3: 
/* 1223 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1227 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1228 */         localSQLException2.fillInStackTrace();
/* 1229 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1234 */       this.rsetMetaData.setAutoIncrement(this.columnIndex, getBooleanValue(paramString));
/* 1235 */       break;
/*      */     
/*      */     case 4: 
/* 1238 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1242 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1243 */         localSQLException2.fillInStackTrace();
/* 1244 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1249 */       this.rsetMetaData.setCaseSensitive(this.columnIndex, getBooleanValue(paramString));
/* 1250 */       break;
/*      */     
/*      */     case 5: 
/* 1253 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1257 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1258 */         localSQLException2.fillInStackTrace();
/* 1259 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1264 */       this.rsetMetaData.setCurrency(this.columnIndex, getBooleanValue(paramString));
/* 1265 */       break;
/*      */     
/*      */     case 6: 
/* 1268 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1272 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1273 */         localSQLException2.fillInStackTrace();
/* 1274 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1279 */       this.rsetMetaData.setNullable(this.columnIndex, getIntegerValue(paramString));
/* 1280 */       break;
/*      */     
/*      */     case 7: 
/* 1283 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1287 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1288 */         localSQLException2.fillInStackTrace();
/* 1289 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1294 */       this.rsetMetaData.setSigned(this.columnIndex, getBooleanValue(paramString));
/* 1295 */       break;
/*      */     
/*      */     case 8: 
/* 1298 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1302 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1303 */         localSQLException2.fillInStackTrace();
/* 1304 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1309 */       this.rsetMetaData.setSearchable(this.columnIndex, getBooleanValue(paramString));
/* 1310 */       break;
/*      */     
/*      */     case 9: 
/* 1313 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1317 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1318 */         localSQLException2.fillInStackTrace();
/* 1319 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1324 */       this.rsetMetaData.setColumnDisplaySize(this.columnIndex, getIntegerValue(paramString));
/* 1325 */       break;
/*      */     
/*      */     case 10: 
/* 1328 */       if (bool)
/*      */       {
/*      */ 
/* 1331 */         this.rsetMetaData.setColumnLabel(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1336 */         this.rsetMetaData.setColumnLabel(this.columnIndex, paramString);
/*      */       }
/*      */       
/* 1339 */       break;
/*      */     
/*      */     case 11: 
/* 1342 */       if (bool)
/*      */       {
/*      */ 
/* 1345 */         this.rsetMetaData.setColumnName(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1350 */         this.rsetMetaData.setColumnName(this.columnIndex, paramString);
/*      */       }
/*      */       
/* 1353 */       break;
/*      */     
/*      */     case 12: 
/* 1356 */       if (bool)
/*      */       {
/*      */ 
/* 1359 */         this.rsetMetaData.setSchemaName(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1364 */         this.rsetMetaData.setSchemaName(this.columnIndex, paramString);
/*      */       }
/*      */       
/* 1367 */       break;
/*      */     
/*      */     case 13: 
/* 1370 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1374 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1375 */         localSQLException2.fillInStackTrace();
/* 1376 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1381 */       this.rsetMetaData.setPrecision(this.columnIndex, getIntegerValue(paramString));
/* 1382 */       break;
/*      */     
/*      */     case 14: 
/* 1385 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1389 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1390 */         localSQLException2.fillInStackTrace();
/* 1391 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1396 */       this.rsetMetaData.setScale(this.columnIndex, getIntegerValue(paramString));
/* 1397 */       break;
/*      */     
/*      */     case 15: 
/* 1400 */       if (bool)
/*      */       {
/*      */ 
/* 1403 */         this.rsetMetaData.setTableName(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1408 */         this.rsetMetaData.setTableName(this.columnIndex, paramString);
/*      */       }
/*      */       
/* 1411 */       break;
/*      */     
/*      */     case 16: 
/* 1414 */       if (bool)
/*      */       {
/*      */ 
/* 1417 */         this.rsetMetaData.setCatalogName(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1422 */         this.rsetMetaData.setCatalogName(this.columnIndex, paramString);
/*      */       }
/*      */       
/* 1425 */       break;
/*      */     
/*      */     case 17: 
/* 1428 */       if (bool)
/*      */       {
/*      */ 
/*      */ 
/* 1432 */         localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 358);
/* 1433 */         localSQLException2.fillInStackTrace();
/* 1434 */         throw localSQLException2;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1439 */       this.rsetMetaData.setColumnType(this.columnIndex, getIntegerValue(paramString));
/* 1440 */       break;
/*      */     
/*      */     case 18: 
/* 1443 */       if (bool)
/*      */       {
/*      */ 
/* 1446 */         this.rsetMetaData.setColumnTypeName(this.columnIndex, null);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1451 */         this.rsetMetaData.setColumnTypeName(this.columnIndex, paramString);
/*      */       }
/*      */       
/*      */       break;
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */   private void setDataValue(String paramString)
/*      */     throws SQLException
/*      */   {
/* 1462 */     switch (getTag())
/*      */     {
/*      */     case 1: 
/*      */     case 2: 
/*      */     case 3: 
/*      */     default: 
/*      */       break;
/*      */     
/*      */     case 4: 
/* 1471 */       this.columnValue = paramString;
/* 1472 */       break;
/*      */     
/*      */     case 5: 
/* 1475 */       Object[] arrayOfObject = new Object[2];
/* 1476 */       arrayOfObject[1] = paramString;
/* 1477 */       arrayOfObject[0] = Integer.valueOf(this.columnIndex);
/* 1478 */       this.updatesToRowSet.add(arrayOfObject);
/*      */     }
/*      */     
/*      */   }
/*      */   
/*      */ 
/*      */   protected void setNullValue(boolean paramBoolean)
/*      */   {
/* 1486 */     this.isNullValue = paramBoolean;
/*      */   }
/*      */   
/*      */   private int getState()
/*      */   {
/* 1491 */     return this.state;
/*      */   }
/*      */   
/*      */   private int getTag()
/*      */   {
/* 1496 */     return this.tag;
/*      */   }
/*      */   
/*      */   private void setState(String paramString)
/*      */     throws SAXException
/*      */   {
/* 1502 */     if (paramString.equals("webRowSet")) {
/* 1503 */       this.state = 0;
/*      */     }
/* 1505 */     else if (paramString.equals("properties"))
/*      */     {
/* 1507 */       if (this.state != 1) {
/* 1508 */         this.state = 1;
/*      */       } else {
/* 1510 */         this.state = 0;
/*      */       }
/*      */     }
/* 1513 */     else if (paramString.equals("metadata"))
/*      */     {
/* 1515 */       if (this.state != 2) {
/* 1516 */         this.state = 2;
/*      */       } else {
/* 1518 */         this.state = 0;
/*      */       }
/*      */     }
/* 1521 */     else if (paramString.equals("data")) {
/* 1522 */       if (this.state != 3) {
/* 1523 */         this.state = 3;
/*      */       } else
/* 1525 */         this.state = 0;
/*      */     }
/*      */   }
/*      */   
/*      */   private void setTag(int paramInt) {
/* 1530 */     this.tag = paramInt;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1544 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1549 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleWebRowSetXmlReaderContHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */