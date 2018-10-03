package oracle.jdbc.driver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLXML;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import oracle.sql.DatumWithConnection;
import oracle.sql.OPAQUE;
import oracle.sql.OpaqueDescriptor;
import oracle.xdb.XMLType;
import oracle.xml.parser.v2.XMLDocument;
import oracle.xml.parser.v2.XMLSAXSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
final class OracleSQLXML
  extends DatumWithConnection
  implements SQLXML, Opaqueable
{
  private XMLType xdb;
/*  90 */   private boolean isReadable = false;
/*  91 */   private boolean isWriteable = false;
  
/*  93 */   private DOMResult domResult = null;
/*  94 */   private XMLSAXSerializer serializer = null;
/*  95 */   private ByteArrayOutputStream oStream = null;
  static final int INITIAL_BUFFER_SIZE = 16384;
  
  OracleSQLXML(Connection paramConnection) throws SQLException {
/*  99 */     setPhysicalConnectionOf(paramConnection);
/* 100 */     this.isReadable = false;
/* 101 */     this.isWriteable = true;
  }
  
  OracleSQLXML(Connection paramConnection, OPAQUE paramOPAQUE)
    throws SQLException
  {
/* 107 */     setPhysicalConnectionOf(paramConnection);
/* 108 */     this.isReadable = true;
/* 109 */     this.isWriteable = false;
/* 110 */     if ((paramOPAQUE instanceof XMLType)) {
/* 111 */       this.xdb = ((XMLType)paramOPAQUE);
    } else {
/* 113 */       this.xdb = new XMLType(paramOPAQUE.getDescriptor(), getInternalConnection(), paramOPAQUE.getBytesValue());
    }
  }
  
  OracleSQLXML(OpaqueDescriptor paramOpaqueDescriptor, Connection paramConnection, byte[] paramArrayOfByte) throws SQLException {
/* 118 */     this(paramConnection, new OPAQUE(paramOpaqueDescriptor, paramArrayOfByte, paramConnection));
  }
  
  OracleSQLXML(Connection paramConnection, InputStream paramInputStream) throws SQLException
  {
/* 123 */     setPhysicalConnectionOf(paramConnection);
/* 124 */     this.isReadable = true;
/* 125 */     this.isWriteable = false;
/* 126 */     this.xdb = new XMLType(getInternalConnection(), paramInputStream);
  }
  
  OracleSQLXML(Connection paramConnection, XMLType paramXMLType)
    throws SQLException
  {
/* 132 */     setPhysicalConnectionOf(paramConnection);
/* 133 */     this.isReadable = true;
/* 134 */     this.isWriteable = false;
/* 135 */     this.xdb = paramXMLType;
  }
  
  public OPAQUE toOpaque()
    throws SQLException
  {
/* 142 */     return getXMLTypeInternal();
  }
  
  XMLType getXMLTypeInternal()
    throws SQLException
  {
/* 148 */     if (this.isWriteable) {
/* 149 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 260);
/* 150 */       localSQLException.fillInStackTrace();
/* 151 */       throw localSQLException; }
    Object localObject2;
/* 153 */     if (this.serializer != null)
      try {
/* 155 */         this.serializer.flush();
      }
      catch (IOException localIOException1)
      {
/* 159 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException1);
/* 160 */         ((SQLException)localObject2).fillInStackTrace();
/* 161 */         throw ((Throwable)localObject2);
      }
      finally
      {
/* 165 */         this.serializer = null;
      }
    Object localObject1;
/* 168 */     if (this.oStream != null) {
      try {
/* 170 */         this.oStream.close();
      }
      catch (IOException localIOException2)
      {
/* 174 */         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException2);
/* 175 */         ((SQLException)localObject2).fillInStackTrace();
/* 176 */         throw ((Throwable)localObject2);
      }
      
/* 179 */       this.xdb = XMLType.createXML(getInternalConnection(), new ByteArrayInputStream(this.oStream.toByteArray()));
/* 180 */       this.oStream = null;
    }
/* 182 */     else if (this.domResult != null) {
/* 183 */       localObject1 = this.domResult.getNode();
/* 184 */       localObject2 = null;
/* 185 */       if ((localObject1 instanceof Document)) { localObject2 = (Document)localObject1;
      } else {
/* 187 */         localObject2 = new XMLDocument();
/* 188 */         localObject1 = ((Document)localObject2).importNode((Node)localObject1, true);
/* 189 */         ((Document)localObject2).insertBefore((Node)localObject1, null);
      }
/* 191 */       this.xdb = XMLType.createXML(getInternalConnection(), (Document)localObject2);
/* 192 */       this.domResult = null;
    }
/* 194 */     if (this.xdb == null)
    {
/* 196 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 260);
/* 197 */       ((SQLException)localObject1).fillInStackTrace();
/* 198 */       throw ((Throwable)localObject1);
    }
/* 200 */     return this.xdb;
  }
  
  public byte[] getBytes()
  {
/* 206 */     return null;
  }
  
  public boolean isConvertibleTo(Class paramClass)
  {
/* 212 */     return false;
  }
  
  public Object toJdbc()
    throws SQLException
  {
/* 220 */     return this;
  }
  
  public Object makeJdbcArray(int paramInt)
  {
/* 226 */     return null;
  }
  
  public void free()
    throws SQLException
  {
/* 232 */     this.isReadable = false;
/* 233 */     this.isWriteable = false;
/* 234 */     this.oStream = null;
/* 235 */     this.domResult = null;
/* 236 */     if (this.xdb != null) this.xdb.close();
/* 237 */     this.xdb = null;
  }
  
  InputStream getInputStream()
    throws SQLException
  {
/* 244 */     return new ByteArrayInputStream(this.xdb.getStringVal().getBytes());
  }
  
  public InputStream getBinaryStream()
    throws SQLException
  {
/* 250 */     if (!this.isReadable) {
/* 251 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 261);
/* 252 */       localSQLException.fillInStackTrace();
/* 253 */       throw localSQLException;
    }
/* 255 */     this.isReadable = false;
/* 256 */     return getInputStream();
  }
  
  public Reader getCharacterStream()
    throws SQLException
  {
/* 262 */     if (!this.isReadable) {
/* 263 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 261);
/* 264 */       localSQLException.fillInStackTrace();
/* 265 */       throw localSQLException;
    }
/* 267 */     this.isReadable = false;
/* 268 */     return new StringReader(this.xdb.getStringVal());
  }
  
  public <T extends Source> T getSource(Class<T> paramClass)
    throws SQLException
  {
    Object localObject1;
/* 275 */     if (!this.isReadable) {
/* 276 */       localObject1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 261);
/* 277 */       ((SQLException)localObject1).fillInStackTrace();
/* 278 */       throw ((Throwable)localObject1);
    }
/* 280 */     this.isReadable = false;
/* 281 */     if (paramClass == DOMSource.class) {
/* 282 */       localObject1 = this.xdb.getDocument();
/* 283 */       return new DOMSource((Node)localObject1);
    }
/* 285 */     if (paramClass == SAXSource.class) {
/* 286 */       localObject1 = new InputSource(getInputStream());
/* 287 */       return new SAXSource((InputSource)localObject1);
    }
/* 289 */     if (paramClass == StAXSource.class) {
      try {
/* 291 */         localObject1 = XMLInputFactory.newInstance();
/* 292 */         localObject2 = ((XMLInputFactory)localObject1).createXMLStreamReader(getInputStream());
/* 293 */         return new StAXSource((XMLStreamReader)localObject2);
      }
      catch (XMLStreamException localXMLStreamException)
      {
/* 297 */         Object localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localXMLStreamException);
/* 298 */         ((SQLException)localObject2).fillInStackTrace();
/* 299 */         throw ((Throwable)localObject2);
      }
    }
    
/* 303 */     if (paramClass == StreamSource.class) {
/* 304 */       return new StreamSource(getInputStream());
    }
    
/* 307 */     this.isReadable = true;
    
/* 309 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 264);
/* 310 */     localSQLException.fillInStackTrace();
/* 311 */     throw localSQLException;
  }
  
  public String getString()
    throws SQLException
  {
/* 319 */     if (!this.isReadable) {
/* 320 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 261);
/* 321 */       localSQLException.fillInStackTrace();
/* 322 */       throw localSQLException;
    }
/* 324 */     this.isReadable = false;
/* 325 */     return this.xdb.getStringVal();
  }
  
  protected OutputStream getOutputStream()
    throws SQLException
  {
/* 333 */     if (this.oStream != null) throw new SQLException("Internal Error");
/* 334 */     this.oStream = new ByteArrayOutputStream(16384);
/* 335 */     return this.oStream;
  }
  
  public OutputStream setBinaryStream()
    throws SQLException
  {
/* 341 */     if (!this.isWriteable) {
/* 342 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 262);
/* 343 */       localSQLException.fillInStackTrace();
/* 344 */       throw localSQLException;
    }
/* 346 */     this.isWriteable = false;
/* 347 */     return getOutputStream();
  }
  
  public Writer setCharacterStream()
    throws SQLException
  {
/* 353 */     if (!this.isWriteable) {
/* 354 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 262);
/* 355 */       localSQLException.fillInStackTrace();
/* 356 */       throw localSQLException;
    }
/* 358 */     this.isWriteable = false;
/* 359 */     return new OutputStreamWriter(getOutputStream());
  }
  
  public <T extends Result> T setResult(Class<T> paramClass)
    throws SQLException
  {
    Object localObject;
/* 366 */     if (!this.isWriteable) {
/* 367 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 262);
/* 368 */       ((SQLException)localObject).fillInStackTrace();
/* 369 */       throw ((Throwable)localObject);
    }
/* 371 */     this.isWriteable = false;
/* 372 */     if (paramClass == DOMResult.class) {
/* 373 */       this.domResult = new DOMResult();
/* 374 */       return this.domResult;
    }
/* 376 */     if (paramClass == SAXResult.class) {
/* 377 */       this.serializer = new XMLSAXSerializer(getOutputStream());
/* 378 */       return new SAXResult(this.serializer);
    }
/* 380 */     if (paramClass == StAXResult.class) {
      try {
/* 382 */         localObject = XMLOutputFactory.newInstance();
/* 383 */         return new StAXResult(((XMLOutputFactory)localObject).createXMLStreamWriter(getOutputStream()));
      }
      catch (XMLStreamException localXMLStreamException)
      {
/* 387 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localXMLStreamException);
/* 388 */         localSQLException2.fillInStackTrace();
/* 389 */         throw localSQLException2;
      }
    }
    
/* 393 */     if (paramClass == StreamResult.class) {
/* 394 */       return new StreamResult(getOutputStream());
    }
    
/* 397 */     this.isWriteable = true;
    
/* 399 */     SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 263);
/* 400 */     localSQLException1.fillInStackTrace();
/* 401 */     throw localSQLException1;
  }
  
  public void setString(String paramString)
    throws SQLException
  {
/* 409 */     if (!this.isWriteable) {
/* 410 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 262);
/* 411 */       localSQLException.fillInStackTrace();
/* 412 */       throw localSQLException;
    }
/* 414 */     this.isWriteable = false;
/* 415 */     this.xdb = new XMLType(getInternalConnection(), paramString);
  }
  
/* 420 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleSQLXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */