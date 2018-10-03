package oracle.sql;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.SQLXML;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import oracle.jdbc.driver.DatabaseError;
public class OracleSQLOutput
  implements SQLOutput
{
  private StructDescriptor descriptor;
  private Object[] attributes;
  private int index;
  private oracle.jdbc.OracleConnection conn;
  
  public OracleSQLOutput(StructDescriptor paramStructDescriptor, oracle.jdbc.OracleConnection paramOracleConnection)
    throws SQLException
  {
/* 111 */     this.descriptor = paramStructDescriptor;
/* 112 */     this.attributes = new Object[paramStructDescriptor.getLength()];
/* 113 */     this.conn = paramOracleConnection;
/* 114 */     this.index = 0;
  }
  
  public STRUCT getSTRUCT()
    throws SQLException
  {
/* 125 */     return new STRUCT(this.descriptor, this.conn, this.attributes);
  }
  
  public void writeString(String paramString)
    throws SQLException
  {
/* 144 */     this.attributes[(this.index++)] = paramString;
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws SQLException
  {
/* 157 */     this.attributes[(this.index++)] = Boolean.valueOf(paramBoolean);
  }
  
  public void writeByte(byte paramByte)
    throws SQLException
  {
/* 171 */     this.attributes[(this.index++)] = Integer.valueOf(paramByte);
  }
  
  public void writeShort(short paramShort)
    throws SQLException
  {
/* 183 */     this.attributes[(this.index++)] = Integer.valueOf(paramShort);
  }
  
  public void writeInt(int paramInt)
    throws SQLException
  {
/* 195 */     this.attributes[(this.index++)] = Integer.valueOf(paramInt);
  }
  
  public void writeLong(long paramLong)
    throws SQLException
  {
/* 207 */     this.attributes[(this.index++)] = new Long(paramLong);
  }
  
  public void writeFloat(float paramFloat)
    throws SQLException
  {
/* 219 */     this.attributes[(this.index++)] = new Float(paramFloat);
  }
  
  public void writeDouble(double paramDouble)
    throws SQLException
  {
/* 231 */     this.attributes[(this.index++)] = new Double(paramDouble);
  }
  
  public void writeBigDecimal(BigDecimal paramBigDecimal)
    throws SQLException
  {
/* 243 */     this.attributes[(this.index++)] = paramBigDecimal;
  }
  
  public void writeBytes(byte[] paramArrayOfByte)
    throws SQLException
  {
/* 255 */     this.attributes[(this.index++)] = paramArrayOfByte;
  }
  
  public void writeDate(Date paramDate)
    throws SQLException
  {
/* 267 */     this.attributes[(this.index++)] = paramDate;
  }
  
  public void writeTime(Time paramTime)
    throws SQLException
  {
/* 279 */     this.attributes[(this.index++)] = paramTime;
  }
  
  public void writeTimestamp(Timestamp paramTimestamp)
    throws SQLException
  {
/* 291 */     this.attributes[(this.index++)] = paramTimestamp;
  }
  
  public void writeCharacterStream(Reader paramReader)
    throws SQLException
  {
/* 304 */     StringBuffer localStringBuffer = new StringBuffer();
    
/* 306 */     char[] arrayOfChar = new char[100];
/* 307 */     int i = 0;
    
    try
    {
/* 313 */       while ((i = paramReader.read(arrayOfChar)) != -1)
      {
/* 315 */         localStringBuffer.append(arrayOfChar, 0, i);
      }
      
    }
    catch (IOException localIOException)
    {
/* 321 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 322 */       localSQLException.fillInStackTrace();
/* 323 */       throw localSQLException;
    }
    
/* 327 */     String str = localStringBuffer.substring(0, localStringBuffer.length());
    
/* 330 */     this.attributes[(this.index++)] = str;
  }
  
  public void writeAsciiStream(InputStream paramInputStream)
    throws SQLException
  {
/* 343 */     StringBuffer localStringBuffer = new StringBuffer();
    
/* 345 */     byte[] arrayOfByte = new byte[100];
/* 346 */     char[] arrayOfChar = new char[100];
/* 347 */     int i = 0;
    
    try
    {
/* 351 */       while ((i = paramInputStream.read(arrayOfByte)) != -1)
      {
/* 353 */         for (int j = 0; j < i; j++) {
/* 354 */           arrayOfChar[j] = ((char)arrayOfByte[j]);
        }
/* 356 */         localStringBuffer.append(arrayOfChar, 0, i);
      }
      
    }
    catch (IOException localIOException)
    {
/* 362 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), localIOException);
/* 363 */       localSQLException.fillInStackTrace();
/* 364 */       throw localSQLException;
    }
    
/* 368 */     String str = localStringBuffer.substring(0, localStringBuffer.length());
    
/* 370 */     this.attributes[(this.index++)] = str;
  }
  
  public void writeBinaryStream(InputStream paramInputStream)
    throws SQLException
  {
/* 384 */     writeAsciiStream(paramInputStream);
  }
  
  public void writeObject(SQLData paramSQLData)
    throws SQLException
  {
/* 409 */     STRUCT localSTRUCT = null;
    
/* 411 */     if (paramSQLData != null)
    {
/* 413 */       StructDescriptor localStructDescriptor = StructDescriptor.createDescriptor(paramSQLData.getSQLTypeName(), this.conn);
      
/* 416 */       SQLOutput localSQLOutput = localStructDescriptor.toJdbc2SQLOutput();
      
/* 418 */       paramSQLData.writeSQL(localSQLOutput);
      
/* 420 */       localSTRUCT = ((OracleSQLOutput)localSQLOutput).getSTRUCT();
    }
    
/* 423 */     writeStruct(localSTRUCT);
  }
  
  public void writeObject(Object paramObject)
    throws SQLException
  {
/* 436 */     if ((paramObject != null) && ((paramObject instanceof SQLData))) {
/* 437 */       writeObject((SQLData)paramObject);
    } else {
/* 439 */       this.attributes[(this.index++)] = paramObject;
    }
  }
  
  public void writeRef(Ref paramRef)
    throws SQLException
  {
/* 452 */     this.attributes[(this.index++)] = paramRef;
  }
  
  public void writeBlob(Blob paramBlob)
    throws SQLException
  {
/* 464 */     this.attributes[(this.index++)] = paramBlob;
  }
  
  public void writeClob(Clob paramClob)
    throws SQLException
  {
/* 476 */     this.attributes[(this.index++)] = paramClob;
  }
  
  public void writeStruct(Struct paramStruct)
    throws SQLException
  {
/* 488 */     this.attributes[(this.index++)] = paramStruct;
  }
  
  public void writeArray(Array paramArray)
    throws SQLException
  {
/* 500 */     this.attributes[(this.index++)] = paramArray;
  }
  
  public void writeOracleObject(Datum paramDatum)
    throws SQLException
  {
/* 514 */     this.attributes[(this.index++)] = paramDatum;
  }
  
  public void writeRef(REF paramREF)
    throws SQLException
  {
/* 528 */     this.attributes[(this.index++)] = paramREF;
  }
  
  public void writeBlob(BLOB paramBLOB)
    throws SQLException
  {
/* 542 */     this.attributes[(this.index++)] = paramBLOB;
  }
  
  public void writeBfile(BFILE paramBFILE)
    throws SQLException
  {
/* 556 */     this.attributes[(this.index++)] = paramBFILE;
  }
  
  public void writeClob(CLOB paramCLOB)
    throws SQLException
  {
/* 570 */     this.attributes[(this.index++)] = paramCLOB;
  }
  
  public void writeStruct(STRUCT paramSTRUCT)
    throws SQLException
  {
/* 584 */     this.attributes[(this.index++)] = paramSTRUCT;
  }
  
  public void writeArray(ARRAY paramARRAY)
    throws SQLException
  {
/* 598 */     this.attributes[(this.index++)] = paramARRAY;
  }
  
  public void writeNUMBER(NUMBER paramNUMBER)
    throws SQLException
  {
/* 612 */     this.attributes[(this.index++)] = paramNUMBER;
  }
  
  public void writeCHAR(CHAR paramCHAR)
    throws SQLException
  {
/* 626 */     this.attributes[(this.index++)] = paramCHAR;
  }
  
  public void writeDATE(DATE paramDATE)
    throws SQLException
  {
/* 640 */     this.attributes[(this.index++)] = paramDATE;
  }
  
  public void writeRAW(RAW paramRAW)
    throws SQLException
  {
/* 654 */     this.attributes[(this.index++)] = paramRAW;
  }
  
  public void writeROWID(ROWID paramROWID)
    throws SQLException
  {
/* 668 */     this.attributes[(this.index++)] = paramROWID;
  }
  
  public void writeURL(URL paramURL)
    throws SQLException
  {
/* 689 */     SQLException localSQLException = DatabaseError.createUnsupportedFeatureSqlException();
/* 690 */     localSQLException.fillInStackTrace();
/* 691 */     throw localSQLException;
  }
  
  public void writeNClob(NClob paramNClob)
    throws SQLException
  {
/* 706 */     writeClob(paramNClob);
  }
  
  public void writeNString(String paramString)
    throws SQLException
  {
/* 713 */     writeString(paramString);
  }
  
  public void writeSQLXML(SQLXML paramSQLXML)
    throws SQLException
  {
/* 720 */     writeObject(paramSQLXML);
  }
  
  public void writeRowId(RowId paramRowId)
    throws SQLException
  {
/* 727 */     writeROWID((ROWID)paramRowId);
  }
  
  protected oracle.jdbc.internal.OracleConnection getConnectionDuringExceptionHandling()
  {
/* 744 */     return null;
  }
  
/* 749 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/OracleSQLOutput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */