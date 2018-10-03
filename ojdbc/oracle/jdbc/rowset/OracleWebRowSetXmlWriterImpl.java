package oracle.jdbc.rowset;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import javax.sql.RowSet;
import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.spi.SyncProvider;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
class OracleWebRowSetXmlWriterImpl
  implements OracleWebRowSetXmlWriter
{
  private Writer xmlWriter;
  private Stack xmlTagStack;
  private static final String WEBROWSET_ELEMENT = "webRowSet";
  private static final String PROPERTIES_ELEMENT = "properties";
  private static final String METADATA_ELEMENT = "metadata";
  private static final String DATA_ELEMENT = "data";
  private static final String PROPERTY_COMMAND = "command";
  private static final String PROPERTY_CONCURRENCY = "concurrency";
  private static final String PROPERTY_DATASOURCE = "datasource";
  private static final String PROPERTY_ESCAPEPROCESSING = "escape-processing";
  private static final String PROPERTY_FETCHDIRECTION = "fetch-direction";
  private static final String PROPERTY_FETCHSIZE = "fetch-size";
  private static final String PROPERTY_ISOLATIONLEVEL = "isolation-level";
  private static final String PROPERTY_KEYCOLUMNS = "key-columns";
  private static final String PROPERTY_MAP = "map";
  private static final String PROPERTY_MAXFIELDSIZE = "max-field-size";
  private static final String PROPERTY_MAXROWS = "max-rows";
  private static final String PROPERTY_QUERYTIMEOUT = "query-timeout";
  private static final String PROPERTY_READONLY = "read-only";
  private static final String PROPERTY_ROWSETTYPE = "rowset-type";
  private static final String PROPERTY_SHOWDELETED = "show-deleted";
  private static final String PROPERTY_TABLENAME = "table-name";
  private static final String PROPERTY_URL = "url";
  private static final String PROPERTY_SYNCPROVIDER = "sync-provider";
  private static final String PROPERTY_NULL = "null";
  private static final String PROPERTY_KC_COLUMN = "column";
  private static final String PROPERTY_MAP_TYPE = "type";
  private static final String PROPERTY_MAP_CLASS = "class";
  private static final String PROPERTY_S_PROVIDERNAME = "sync-provider-name";
  private static final String PROPERTY_S_PROVIDERVENDOR = "sync-provider-vendor";
  private static final String PROPERTY_S_PROVIDERVERSION = "sync-provider-version";
  private static final String PROPERTY_S_PROVIDERGRADE = "sync-provider-grade";
  private static final String PROPERTY_S_DATASOURCELOCK = "data-source-lock";
  private static final String METADATA_COLUMNCOUNT = "column-count";
  private static final String METADATA_COLUMNDEFINITION = "column-definition";
  private static final String METADATA_COLUMNINDEX = "column-index";
  private static final String METADATA_AUTOINCREMENT = "auto-increment";
  private static final String METADATA_CASESENSITIVE = "case-sensitive";
  private static final String METADATA_CURRENCY = "currency";
  private static final String METADATA_NULLABLE = "nullable";
  private static final String METADATA_SIGNED = "signed";
  private static final String METADATA_SEARCHABLE = "searchable";
  private static final String METADATA_COLUMNDISPLAYSIZE = "column-display-size";
  private static final String METADATA_COLUMNLABEL = "column-label";
  private static final String METADATA_COLUMNNAME = "column-name";
  private static final String METADATA_SCHEMANAME = "schema-name";
  private static final String METADATA_COLUMNPRECISION = "column-precision";
  private static final String METADATA_COLUMNSCALE = "column-scale";
  private static final String METADATA_TABLENAME = "table-name";
  private static final String METADATA_CATALOGNAME = "catalog-name";
  private static final String METADATA_COLUMNTYPE = "column-type";
  private static final String METADATA_COLUMNTYPENAME = "column-type-name";
  private static final String METADATA_NULL = "null";
  private static final String DATA_CURRENTROW = "currentRow";
  private static final String DATA_INSERTROW = "insertRow";
  private static final String DATA_DELETEROW = "deleteRow";
  private static final String DATA_MODIFYROW = "modifyRow";
  private static final String DATA_COLUMNVALUE = "columnValue";
  private static final String DATA_UPDATEVALUE = "updateValue";
  private static final String DATA_NULL = "null";
  
  public void writeXML(WebRowSet paramWebRowSet, Writer paramWriter)
    throws SQLException
  {
/* 151 */     if (!(paramWebRowSet instanceof OracleWebRowSet))
    {
/* 153 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 359);
/* 154 */       localSQLException.fillInStackTrace();
/* 155 */       throw localSQLException;
    }
    
/* 158 */     this.xmlTagStack = new Stack();
/* 159 */     this.xmlWriter = paramWriter;
/* 160 */     writeRowSet((OracleWebRowSet)paramWebRowSet);
  }
  
  public boolean writeData(RowSetInternal paramRowSetInternal)
    throws SQLException
  {
/* 166 */     return false;
  }
  
  private void writeRowSet(OracleWebRowSet paramOracleWebRowSet)
    throws SQLException
  {
    try
    {
/* 181 */       writeHeaderAndStartWebRowSetElement();
/* 182 */       writeProperties(paramOracleWebRowSet);
/* 183 */       writeMetaData(paramOracleWebRowSet);
/* 184 */       writeData(paramOracleWebRowSet);
/* 185 */       endWebRowSetElement();
    }
    catch (IOException localIOException)
    {
/* 189 */       throw new SQLException("IOException: " + localIOException.getMessage());
    }
  }
  
  private void writeHeaderAndStartWebRowSetElement()
    throws IOException
  {
/* 196 */     this.xmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
/* 197 */     this.xmlWriter.write("\n");
    
/* 199 */     setCurrentTag("webRowSet");
    
/* 201 */     this.xmlWriter.write("<webRowSet xmlns=\"http://java.sun.com/xml/ns/jdbc\"\n");
/* 202 */     this.xmlWriter.write("           xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
/* 203 */     this.xmlWriter.write("           xsi:schemaLocation=\"http://java.sun.com/xml/ns/jdbc ");
/* 204 */     this.xmlWriter.write("http://java.sun.com/xml/ns/jdbc/webrowset.xsd");
/* 205 */     this.xmlWriter.write("\">\n");
  }
  
  private void endWebRowSetElement()
    throws IOException
  {
/* 211 */     endTag("webRowSet");
  }
  
  private void startElement(String paramString)
    throws IOException
  {
/* 217 */     startTag(paramString);
/* 218 */     this.xmlWriter.write("\n");
  }
  
  private void endElement(String paramString)
    throws IOException
  {
/* 224 */     writeIndent(this.xmlTagStack.size());
/* 225 */     endTag(paramString);
  }
  
  private void endElement()
    throws IOException
  {
/* 231 */     writeIndent(this.xmlTagStack.size());
/* 232 */     String str = getCurrentTag();
/* 233 */     this.xmlWriter.write("</" + str + ">\n");
/* 234 */     this.xmlWriter.flush();
  }
  
  private void startTag(String paramString)
    throws IOException
  {
/* 240 */     setCurrentTag(paramString);
/* 241 */     writeIndent(this.xmlTagStack.size());
/* 242 */     this.xmlWriter.write("<" + paramString + ">");
  }
  
  private void endTag(String paramString)
    throws IOException
  {
/* 248 */     String str = getCurrentTag();
/* 249 */     if (paramString.equals(str))
/* 250 */       this.xmlWriter.write("</" + str + ">\n");
/* 251 */     this.xmlWriter.flush();
  }
  
  private void setCurrentTag(String paramString)
  {
/* 256 */     this.xmlTagStack.push(paramString);
  }
  
  private String getCurrentTag()
  {
/* 261 */     return (String)this.xmlTagStack.pop();
  }
  
  private void writeEmptyElement(String paramString)
    throws IOException
  {
/* 267 */     this.xmlWriter.write("<" + paramString + "/>");
  }
  
  private void writeProperties(OracleWebRowSet paramOracleWebRowSet)
    throws IOException
  {
/* 273 */     startElement("properties");
    
    try
    {
/* 277 */       writeElementString("command", paramOracleWebRowSet.getCommand());
/* 278 */       writeElementInteger("concurrency", paramOracleWebRowSet.getConcurrency());
/* 279 */       writeElementString("datasource", paramOracleWebRowSet.getDataSourceName());
/* 280 */       writeElementBoolean("escape-processing", paramOracleWebRowSet.getEscapeProcessing());
/* 281 */       writeElementInteger("fetch-direction", paramOracleWebRowSet.getFetchDirection());
/* 282 */       writeElementInteger("fetch-size", paramOracleWebRowSet.getFetchSize());
/* 283 */       writeElementInteger("isolation-level", paramOracleWebRowSet.getTransactionIsolation());
      
/* 285 */       startElement("key-columns");
/* 286 */       int[] arrayOfInt = paramOracleWebRowSet.getKeyColumns();
/* 287 */       for (int i = 0; (arrayOfInt != null) && (i < arrayOfInt.length); i++)
/* 288 */         writeElementInteger("column", arrayOfInt[i]);
/* 289 */       endElement("key-columns");
      
/* 291 */       startElement("map");
/* 292 */       Map localMap = paramOracleWebRowSet.getTypeMap();
/* 293 */       if (localMap != null)
      {
/* 296 */         Set localSet = localMap.entrySet();
/* 297 */         for (Map.Entry localEntry : localSet)
        {
/* 299 */           writeElementString("type", (String)localEntry.getKey());
/* 300 */           writeElementString("class", ((Class)localEntry.getValue()).getName());
        }
      }
/* 303 */       endElement("map");
      
/* 305 */       writeElementInteger("max-field-size", paramOracleWebRowSet.getMaxFieldSize());
/* 306 */       writeElementInteger("max-rows", paramOracleWebRowSet.getMaxRows());
/* 307 */       writeElementInteger("query-timeout", paramOracleWebRowSet.getQueryTimeout());
/* 308 */       writeElementBoolean("read-only", paramOracleWebRowSet.isReadOnly());
/* 309 */       writeElementInteger("rowset-type", paramOracleWebRowSet.getType());
/* 310 */       writeElementBoolean("show-deleted", paramOracleWebRowSet.getShowDeleted());
/* 311 */       writeElementString("table-name", paramOracleWebRowSet.getTableName());
/* 312 */       writeElementString("url", paramOracleWebRowSet.getUrl());
      
/* 316 */       startElement("sync-provider");
      
/* 318 */       SyncProvider localSyncProvider = paramOracleWebRowSet.getSyncProvider();
/* 319 */       writeElementString("sync-provider-name", localSyncProvider.getProviderID());
/* 320 */       writeElementString("sync-provider-vendor", localSyncProvider.getVendor());
/* 321 */       writeElementString("sync-provider-version", localSyncProvider.getVersion());
/* 322 */       writeElementInteger("sync-provider-grade", localSyncProvider.getProviderGrade());
/* 323 */       writeElementInteger("data-source-lock", localSyncProvider.getDataSourceLock());
      
/* 325 */       endElement("sync-provider");
    }
    catch (SQLException localSQLException)
    {
/* 329 */       throw new IOException("SQLException: " + localSQLException.getMessage());
    }
    
/* 332 */     endElement("properties");
  }
  
  private void writeMetaData(OracleWebRowSet paramOracleWebRowSet)
    throws IOException
  {
/* 338 */     startElement("metadata");
    
    try
    {
/* 342 */       ResultSetMetaData localResultSetMetaData = paramOracleWebRowSet.getMetaData();
      
/* 344 */       int i = localResultSetMetaData.getColumnCount();
/* 345 */       writeElementInteger("column-count", i);
      
/* 347 */       for (int j = 1; j <= i; j++)
      {
/* 349 */         startElement("column-definition");
        
/* 351 */         writeElementInteger("column-index", j);
/* 352 */         writeElementBoolean("auto-increment", localResultSetMetaData.isAutoIncrement(j));
/* 353 */         writeElementBoolean("case-sensitive", localResultSetMetaData.isCaseSensitive(j));
/* 354 */         writeElementBoolean("currency", localResultSetMetaData.isCurrency(j));
/* 355 */         writeElementInteger("nullable", localResultSetMetaData.isNullable(j));
/* 356 */         writeElementBoolean("signed", localResultSetMetaData.isSigned(j));
/* 357 */         writeElementBoolean("searchable", localResultSetMetaData.isSearchable(j));
/* 358 */         writeElementInteger("column-display-size", localResultSetMetaData.getColumnDisplaySize(j));
/* 359 */         writeElementString("column-label", localResultSetMetaData.getColumnLabel(j));
/* 360 */         writeElementString("column-name", localResultSetMetaData.getColumnName(j));
/* 361 */         writeElementString("schema-name", localResultSetMetaData.getSchemaName(j));
/* 362 */         writeElementInteger("column-precision", localResultSetMetaData.getPrecision(j));
/* 363 */         writeElementInteger("column-scale", localResultSetMetaData.getScale(j));
/* 364 */         writeElementString("table-name", localResultSetMetaData.getTableName(j));
/* 365 */         writeElementString("catalog-name", localResultSetMetaData.getCatalogName(j));
/* 366 */         writeElementInteger("column-type", localResultSetMetaData.getColumnType(j));
/* 367 */         writeElementString("column-type-name", localResultSetMetaData.getColumnTypeName(j));
        
/* 369 */         endElement("column-definition");
      }
    }
    catch (SQLException localSQLException)
    {
/* 374 */       throw new IOException("SQLException: " + localSQLException.getMessage());
    }
    
/* 377 */     endElement("metadata");
  }
  
  private void writeElementBoolean(String paramString, boolean paramBoolean)
    throws IOException
  {
/* 383 */     startTag(paramString);
/* 384 */     writeBoolean(paramBoolean);
/* 385 */     endTag(paramString);
  }
  
  private void writeElementInteger(String paramString, int paramInt)
    throws IOException
  {
/* 391 */     startTag(paramString);
/* 392 */     writeInteger(paramInt);
/* 393 */     endTag(paramString);
  }
  
  private void writeElementString(String paramString1, String paramString2)
    throws IOException
  {
/* 399 */     startTag(paramString1);
/* 400 */     writeString(paramString2);
/* 401 */     endTag(paramString1);
  }
  
  private void writeData(OracleWebRowSet paramOracleWebRowSet)
    throws IOException
  {
    try
    {
/* 409 */       ResultSetMetaData localResultSetMetaData = paramOracleWebRowSet.getMetaData();
/* 410 */       int i = localResultSetMetaData.getColumnCount();
      
/* 412 */       startElement("data");
      
/* 414 */       paramOracleWebRowSet.beforeFirst();
/* 415 */       paramOracleWebRowSet.setShowDeleted(true);
/* 416 */       for (; paramOracleWebRowSet.next(); endElement())
      {
/* 418 */         if ((paramOracleWebRowSet.rowDeleted()) && (paramOracleWebRowSet.rowInserted())) {
/* 419 */           startElement("modifyRow");
/* 420 */         } else if (paramOracleWebRowSet.rowDeleted()) {
/* 421 */           startElement("deleteRow");
/* 422 */         } else if (paramOracleWebRowSet.rowInserted()) {
/* 423 */           startElement("insertRow");
        } else
/* 425 */           startElement("currentRow");
/* 426 */         for (int j = 1; j <= i; j++)
        {
/* 428 */           if (paramOracleWebRowSet.columnUpdated(j))
          {
/* 430 */             ResultSet localResultSet = paramOracleWebRowSet.getOriginalRow();
/* 431 */             localResultSet.next();
            
/* 433 */             startTag("columnValue");
/* 434 */             writeValue(j, (RowSet)localResultSet);
/* 435 */             endTag("columnValue");
            
/* 437 */             startTag("updateValue");
/* 438 */             writeValue(j, paramOracleWebRowSet);
/* 439 */             endTag("updateValue");
          }
          else
          {
/* 443 */             startTag("columnValue");
/* 444 */             writeValue(j, paramOracleWebRowSet);
/* 445 */             endTag("columnValue");
          }
        }
      }
      
/* 450 */       endElement("data");
    }
    catch (SQLException localSQLException)
    {
/* 454 */       throw new IOException("SQLException: " + localSQLException.getMessage());
    }
  }
  
  private void writeBigDecimal(BigDecimal paramBigDecimal)
    throws IOException
  {
/* 461 */     if (paramBigDecimal != null) {
/* 462 */       this.xmlWriter.write(paramBigDecimal.toString());
    } else {
/* 464 */       writeEmptyElement("null");
    }
  }
  
  private void writeBoolean(boolean paramBoolean) throws IOException
  {
/* 470 */     this.xmlWriter.write(Boolean.valueOf(paramBoolean).toString());
  }
  
  private void writeDouble(double paramDouble)
    throws IOException
  {
/* 476 */     this.xmlWriter.write(Double.toString(paramDouble));
  }
  
  private void writeFloat(float paramFloat)
    throws IOException
  {
/* 482 */     this.xmlWriter.write(Float.toString(paramFloat));
  }
  
  private void writeInteger(int paramInt)
    throws IOException
  {
/* 488 */     this.xmlWriter.write(Integer.toString(paramInt));
  }
  
  private void writeLong(long paramLong)
    throws IOException
  {
/* 494 */     this.xmlWriter.write(Long.toString(paramLong));
  }
  
  private void writeNull()
    throws IOException
  {
/* 500 */     writeEmptyElement("null");
  }
  
  private void writeShort(short paramShort)
    throws IOException
  {
/* 506 */     this.xmlWriter.write(Short.toString(paramShort));
  }
  
  private void writeBytes(byte[] paramArrayOfByte)
    throws IOException
  {
/* 512 */     this.xmlWriter.write(new String(paramArrayOfByte));
  }
  
  private void writeString(String paramString)
    throws IOException
  {
/* 523 */     if (paramString != null) {
/* 524 */       this.xmlWriter.write(paramString);
    } else {
/* 526 */       this.xmlWriter.write("");
    }
  }
  
  private void writeIndent(int paramInt) throws IOException
  {
/* 532 */     for (int i = 1; i < paramInt; i++) {
/* 533 */       this.xmlWriter.write("  ");
    }
  }
  
  private void writeValue(int paramInt, RowSet paramRowSet)
    throws IOException
  {
    try
    {
/* 542 */       int i = paramRowSet.getMetaData().getColumnType(paramInt);
/* 543 */       switch (i)
      {
      case -7: 
      case 5: 
/* 547 */         short s = paramRowSet.getShort(paramInt);
/* 548 */         if (paramRowSet.wasNull()) {
/* 549 */           writeNull();
        } else
/* 551 */           writeShort(s);
/* 552 */         break;
      
      case 4: 
/* 555 */         int j = paramRowSet.getInt(paramInt);
/* 556 */         if (paramRowSet.wasNull()) {
/* 557 */           writeNull();
        } else
/* 559 */           writeInteger(j);
/* 560 */         break;
      
      case -5: 
/* 563 */         long l = paramRowSet.getLong(paramInt);
/* 564 */         if (paramRowSet.wasNull()) {
/* 565 */           writeNull();
        } else
/* 567 */           writeLong(l);
/* 568 */         break;
      
      case 6: 
      case 7: 
/* 572 */         float f = paramRowSet.getFloat(paramInt);
/* 573 */         if (paramRowSet.wasNull()) {
/* 574 */           writeNull();
        } else
/* 576 */           writeFloat(f);
/* 577 */         break;
      
      case 8: 
/* 580 */         double d = paramRowSet.getDouble(paramInt);
/* 581 */         if (paramRowSet.wasNull()) {
/* 582 */           writeNull();
        } else
/* 584 */           writeDouble(d);
/* 585 */         break;
      
      case 2: 
      case 3: 
/* 589 */         BigDecimal localBigDecimal = paramRowSet.getBigDecimal(paramInt);
/* 590 */         if (paramRowSet.wasNull()) {
/* 591 */           writeNull();
        } else
/* 593 */           writeBigDecimal(localBigDecimal);
/* 594 */         break;
      
      case 91: 
/* 597 */         Date localDate = paramRowSet.getDate(paramInt);
/* 598 */         if (paramRowSet.wasNull()) {
/* 599 */           writeNull();
        } else
/* 601 */           writeLong(localDate.getTime());
/* 602 */         break;
      
      case 92: 
/* 605 */         Time localTime = paramRowSet.getTime(paramInt);
/* 606 */         if (paramRowSet.wasNull()) {
/* 607 */           writeNull();
        } else
/* 609 */           writeLong(localTime.getTime());
/* 610 */         break;
      
      case 93: 
/* 613 */         Timestamp localTimestamp = paramRowSet.getTimestamp(paramInt);
/* 614 */         if (paramRowSet.wasNull()) {
/* 615 */           writeNull();
        } else
/* 617 */           writeLong(localTimestamp.getTime());
/* 618 */         break;
      
      case -4: 
      case -3: 
      case -2: 
      case 2004: 
/* 624 */         byte[] arrayOfByte = paramRowSet.getBytes(paramInt);
/* 625 */         if (paramRowSet.wasNull()) {
/* 626 */           writeNull();
        } else
/* 628 */           writeBytes(arrayOfByte);
/* 629 */         break;
      
      case -15: 
      case -9: 
      case -1: 
      case 1: 
      case 12: 
      case 2005: 
      case 2011: 
/* 644 */         String str = paramRowSet.getString(paramInt);
/* 645 */         if (paramRowSet.wasNull()) {
/* 646 */           writeNull();
        } else
/* 648 */           writeString(str);
/* 649 */         break;
      
      default: 
/* 652 */         throw new SQLException("The type " + i + " is not supported currently.");
      }
    }
    catch (SQLException localSQLException)
    {
/* 657 */       throw new IOException("Failed to writeValue: " + localSQLException.getMessage());
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 672 */     return null;
  }
  
/* 677 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleWebRowSetXmlWriterImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */