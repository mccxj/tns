package oracle.jdbc.rowset;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.WebRowSet;
import oracle.jdbc.driver.DatabaseError;
public class OracleWebRowSet
  extends OracleCachedRowSet
  implements WebRowSet
{
  static final long serialVersionUID = 617253792409477080L;
  private transient OracleWebRowSetXmlReader xmlReader;
  private transient OracleWebRowSetXmlWriter xmlWriter;
  
  public OracleWebRowSet()
    throws SQLException
  {
/*  50 */     this.xmlReader = new OracleWebRowSetXmlReaderImpl();
/*  51 */     this.xmlWriter = new OracleWebRowSetXmlWriterImpl();
    
/*  54 */     setReadOnly(false);
  }
  
  public void readXml(Reader paramReader)
    throws SQLException
  {
/*  71 */     if (this.xmlReader != null)
    {
/*  73 */       this.xmlReader.readXML(this, paramReader);
    }
    else
    {
/*  78 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 355);
/*  79 */       localSQLException.fillInStackTrace();
/*  80 */       throw localSQLException;
    }
  }
  
  public void writeXml(Writer paramWriter)
    throws SQLException
  {
/* 101 */     if (this.xmlWriter != null)
    {
/* 103 */       this.xmlWriter.writeXML(this, paramWriter);
    }
    else
    {
/* 108 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 356);
/* 109 */       localSQLException.fillInStackTrace();
/* 110 */       throw localSQLException;
    }
  }
  
  public void writeXml(ResultSet paramResultSet, Writer paramWriter)
    throws SQLException
  {
/* 130 */     populate(paramResultSet);
/* 131 */     writeXml(paramWriter);
  }
  
  public void readXml(InputStream paramInputStream)
    throws SQLException
  {
/* 148 */     readXml(new InputStreamReader(paramInputStream));
  }
  
  public void writeXml(OutputStream paramOutputStream)
    throws SQLException
  {
/* 167 */     writeXml(new OutputStreamWriter(paramOutputStream));
  }
  
  public void writeXml(ResultSet paramResultSet, OutputStream paramOutputStream)
    throws SQLException
  {
/* 185 */     writeXml(paramResultSet, new OutputStreamWriter(paramOutputStream));
  }
  
/* 191 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/rowset/OracleWebRowSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */