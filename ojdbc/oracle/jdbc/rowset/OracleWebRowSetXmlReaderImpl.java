package oracle.jdbc.rowset;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.SQLException;
import javax.sql.RowSetInternal;
import javax.sql.rowset.WebRowSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
class OracleWebRowSetXmlReaderImpl
  implements OracleWebRowSetXmlReader
{
  private static final String JAVA_SAXPARSER_PROPERTY = "javax.xml.parsers.SAXParserFactory";
  private static final String JAVA_DOMPARSER_PROPERTY = "javax.xml.parsers.DocumentBuilderFactory";
  private static final String ORACLE_JAXP_SAXPARSER_FACTORY = "oracle.xml.jaxp.JXSAXParserFactory";
  private static final String ORACLE_JAXP_DOMPARSER_FACTORY = "oracle.xml.jaxp.JXDocumentBuilderFactory";
  private static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  private static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
  private static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
  private static final String WEBROWSET_SCHEMA = "http://java.sun.com/xml/ns/jdbc/webrowset.xsd";
  private Document document;
  private String parserStr;
  
  OracleWebRowSetXmlReaderImpl()
  {
/*  84 */     this.document = null;
/*  85 */     this.parserStr = null;
  }
  
  public void readXML(WebRowSet paramWebRowSet, Reader paramReader)
    throws SQLException
  {
/* 114 */     this.parserStr = getSystemProperty("javax.xml.parsers.SAXParserFactory");
/* 115 */     if (this.parserStr != null)
    {
/* 117 */       readXMLSax((OracleWebRowSet)paramWebRowSet, paramReader);
    }
    else
    {
/* 121 */       this.parserStr = getSystemProperty("javax.xml.parsers.DocumentBuilderFactory");
/* 122 */       if (this.parserStr != null)
      {
/* 124 */         readXMLDom((OracleWebRowSet)paramWebRowSet, paramReader);
      }
      else {
/* 127 */         throw new SQLException("No valid JAXP parser property specified");
      }
    }
  }
  
  public void readData(RowSetInternal paramRowSetInternal)
    throws SQLException
  {}
  
  private void readXMLSax(OracleWebRowSet paramOracleWebRowSet, Reader paramReader)
    throws SQLException
  {
    try
    {
/* 148 */       InputSource localInputSource = new InputSource(paramReader);
/* 149 */       OracleWebRowSetXmlReaderContHandler localOracleWebRowSetXmlReaderContHandler = new OracleWebRowSetXmlReaderContHandler(paramOracleWebRowSet);
      
/* 152 */       SAXParserFactory localSAXParserFactory = SAXParserFactory.newInstance();
      
/* 155 */       localSAXParserFactory.setNamespaceAware(true);
/* 156 */       localSAXParserFactory.setValidating(true);
/* 157 */       SAXParser localSAXParser = localSAXParserFactory.newSAXParser();
      
/* 160 */       localSAXParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
/* 161 */       localSAXParser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/ns/jdbc/webrowset.xsd");
      
/* 163 */       XMLReader localXMLReader = localSAXParser.getXMLReader();
/* 164 */       localXMLReader.setContentHandler(localOracleWebRowSetXmlReaderContHandler);
      
/* 178 */       localXMLReader.parse(localInputSource);
    }
    catch (SAXParseException localSAXParseException)
    {
/* 182 */       System.out.println("** Parsing error, line " + localSAXParseException.getLineNumber() + ", uri " + localSAXParseException.getSystemId());
      
/* 185 */       System.out.println("   " + localSAXParseException.getMessage());
/* 186 */       localSAXParseException.printStackTrace();
/* 187 */       throw new SQLException(localSAXParseException.getMessage());
    }
    catch (SAXNotRecognizedException localSAXNotRecognizedException)
    {
/* 192 */       localSAXNotRecognizedException.printStackTrace();
/* 193 */       throw new SQLException("readXMLSax: SAXNotRecognizedException: " + localSAXNotRecognizedException.getMessage());
    }
    catch (SAXException localSAXException)
    {
/* 197 */       localSAXException.printStackTrace();
/* 198 */       throw new SQLException("readXMLSax: SAXException: " + localSAXException.getMessage());
    }
    catch (FactoryConfigurationError localFactoryConfigurationError)
    {
/* 202 */       localFactoryConfigurationError.printStackTrace();
/* 203 */       throw new SQLException("readXMLSax: Parser factory config: " + localFactoryConfigurationError.getMessage());
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
/* 207 */       localParserConfigurationException.printStackTrace();
/* 208 */       throw new SQLException("readXMLSax: Parser config: " + localParserConfigurationException.getMessage());
    }
    catch (Exception localException)
    {
/* 212 */       localException.printStackTrace();
/* 213 */       throw new SQLException("readXMLSax: " + localException.getMessage());
    }
  }
  
  private void readXMLDom(OracleWebRowSet paramOracleWebRowSet, Reader paramReader)
    throws SQLException
  {
    try
    {
/* 223 */       InputSource localInputSource = new InputSource(paramReader);
/* 224 */       OracleWebRowSetXmlReaderDomHandler localOracleWebRowSetXmlReaderDomHandler = new OracleWebRowSetXmlReaderDomHandler(paramOracleWebRowSet);
      
/* 244 */       DocumentBuilderFactory localDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
      
/* 248 */       localDocumentBuilderFactory.setNamespaceAware(true);
/* 249 */       localDocumentBuilderFactory.setValidating(true);
      
/* 252 */       localDocumentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
/* 253 */       localDocumentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", "http://java.sun.com/xml/ns/jdbc/webrowset.xsd");
/* 254 */       DocumentBuilder localDocumentBuilder = localDocumentBuilderFactory.newDocumentBuilder();
      
/* 256 */       this.document = localDocumentBuilder.parse(localInputSource);
      
/* 259 */       localOracleWebRowSetXmlReaderDomHandler.readXMLDocument(this.document);
    }
    catch (SAXException localSAXException)
    {
/* 263 */       localSAXException.printStackTrace();
/* 264 */       throw new SQLException("readXMLDom: SAXException: " + localSAXException.getMessage());
    }
    catch (FactoryConfigurationError localFactoryConfigurationError)
    {
/* 269 */       localFactoryConfigurationError.printStackTrace();
/* 270 */       throw new SQLException("readXMLDom: Parser factory config: " + localFactoryConfigurationError.getMessage());
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
/* 274 */       localParserConfigurationException.printStackTrace();
/* 275 */       throw new SQLException("readXMLDom: Parser config: " + localParserConfigurationException.getMessage());
    }
    catch (Exception localException)
    {
/* 279 */       localException.printStackTrace();
/* 280 */       throw new SQLException("readXMLDom: " + localException.getMessage());
    }
  }
  
  private String getSystemProperty(String paramString)
  {
/* 287 */     String str = null;
    try
    {
/* 290 */       str = System.getProperty(paramString);
    }
    catch (SecurityException localSecurityException)
    {
/* 294 */       str = null;
    }
    
/* 297 */     return str;
  }
  
/* 301 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleWebRowSetXmlReaderImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */