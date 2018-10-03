package oracle.jdbc.proxy;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
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
import java.util.Map;
import oracle.jdbc.replay.driver.NonTxnReplayableOthers;
public class oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2java$1sql$1SQLOutput$$$Proxy
  extends NonTxnReplayableOthers
  implements SQLOutput, _Proxy_
{
  private SQLOutput delegate;
  private final Object creator;
  private final ProxyFactory proxyFactory;
  private final Map<Object, Object> proxyCache;
  private static Object[] zeroLengthObjectArray = new Object[0];
  private static Method methodObject25887;
  private static Method methodObject25891;
  private static Method methodObject25881;
  private static Method methodObject25874;
  private static Method methodObject25899;
  private static Method methodObject25900;
  private static Method methodObject25883;
  private static Method methodObject25888;
  private static Method methodObject25894;
  private static Method methodObject25893;
  private static Method methodObject25882;
  private static Method methodObject25886;
  private static Method methodObject25890;
  private static Method methodObject25895;
  private static Method methodObject25879;
  private static Method methodObject25876;
  private static Method methodObject25878;
  private static Method methodObject25885;
  private static Method methodObject25897;
  private static Method methodObject25898;
  private static Method methodObject25875;
  private static Method methodObject25892;
  private static Method methodObject25877;
  private static Method methodObject25896;
  private static Method methodObject25889;
  private static Method methodObject25884;
  private static Method methodObject25880;
  
  public void writeBlob(Blob arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25887, this, new Object[] { arg0 });
      this.delegate.writeBlob((arg0 instanceof _Proxy_) ? (Blob)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25887, e);
    }
  }
  
  public void writeNClob(NClob arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25891, this, new Object[] { arg0 });
      this.delegate.writeNClob((arg0 instanceof _Proxy_) ? (NClob)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25891, e);
    }
  }
  
  public void writeDouble(double arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25881, this, new Object[] { Double.valueOf(arg0) });
      this.delegate.writeDouble(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25881, e);
    }
  }
  
  public void writeObject(SQLData arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25874, this, new Object[] { arg0 });
      this.delegate.writeObject((arg0 instanceof _Proxy_) ? (SQLData)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25874, e);
    }
  }
  
  public void writeTimestamp(Timestamp arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25899, this, new Object[] { arg0 });
      this.delegate.writeTimestamp(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25899, e);
    }
  }
  
  public void writeURL(URL arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25900, this, new Object[] { arg0 });
      this.delegate.writeURL(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25900, e);
    }
  }
  
  public void writeArray(Array arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25883, this, new Object[] { arg0 });
      this.delegate.writeArray((arg0 instanceof _Proxy_) ? (Array)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25883, e);
    }
  }
  
  public void writeCharacterStream(Reader arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25888, this, new Object[] { arg0 });
      this.delegate.writeCharacterStream(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25888, e);
    }
  }
  
  public void writeRowId(RowId arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25894, this, new Object[] { arg0 });
      this.delegate.writeRowId((arg0 instanceof _Proxy_) ? (RowId)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25894, e);
    }
  }
  
  public void writeRef(Ref arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25893, this, new Object[] { arg0 });
      this.delegate.writeRef((arg0 instanceof _Proxy_) ? (Ref)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25893, e);
    }
  }
  
  public void writeBoolean(boolean arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25882, this, new Object[] { Boolean.valueOf(arg0) });
      this.delegate.writeBoolean(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25882, e);
    }
  }
  
  public void writeBinaryStream(InputStream arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25886, this, new Object[] { arg0 });
      this.delegate.writeBinaryStream(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25886, e);
    }
  }
  
  public void writeDate(Date arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25890, this, new Object[] { arg0 });
      this.delegate.writeDate(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25890, e);
    }
  }
  
  public void writeSQLXML(SQLXML arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25895, this, new Object[] { arg0 });
      this.delegate.writeSQLXML((arg0 instanceof _Proxy_) ? (SQLXML)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25895, e);
    }
  }
  
  public void writeShort(short arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25879, this, new Object[] { Short.valueOf(arg0) });
      this.delegate.writeShort(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25879, e);
    }
  }
  
  public void writeBytes(byte[] arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25876, this, new Object[] { arg0 });
      this.delegate.writeBytes(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25876, e);
    }
  }
  
  public void writeLong(long arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25878, this, new Object[] { Long.valueOf(arg0) });
      this.delegate.writeLong(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25878, e);
    }
  }
  
  public void writeBigDecimal(BigDecimal arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25885, this, new Object[] { arg0 });
      this.delegate.writeBigDecimal(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25885, e);
    }
  }
  
  public void writeStruct(Struct arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25897, this, new Object[] { arg0 });
      this.delegate.writeStruct((arg0 instanceof _Proxy_) ? (Struct)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25897, e);
    }
  }
  
  public void writeTime(Time arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25898, this, new Object[] { arg0 });
      this.delegate.writeTime(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25898, e);
    }
  }
  
  public void writeInt(int arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25875, this, new Object[] { Integer.valueOf(arg0) });
      this.delegate.writeInt(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25875, e);
    }
  }
  
  public void writeNString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25892, this, new Object[] { arg0 });
      this.delegate.writeNString(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25892, e);
    }
  }
  
  public void writeByte(byte arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25877, this, new Object[] { Byte.valueOf(arg0) });
      this.delegate.writeByte(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25877, e);
    }
  }
  
  public void writeString(String arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25896, this, new Object[] { arg0 });
      this.delegate.writeString(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25896, e);
    }
  }
  
  public void writeClob(Clob arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25889, this, new Object[] { arg0 });
      this.delegate.writeClob((arg0 instanceof _Proxy_) ? (Clob)((_Proxy_)arg0)._getDelegate_() : arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25889, e);
    }
  }
  
  public void writeAsciiStream(InputStream arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25884, this, new Object[] { arg0 });
      this.delegate.writeAsciiStream(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25884, e);
    }
  }
  
  public void writeFloat(float arg0)
    throws SQLException
  {
    try
    {
      super.preForAll(methodObject25880, this, new Object[] { Float.valueOf(arg0) });
      this.delegate.writeFloat(arg0);
      return;
    }
    catch (SQLException e)
    {
      onErrorVoidForAll(methodObject25880, e);
    }
  }
  
  public SQLOutput _getDelegate_()
  {
    return this.delegate;
  }
  
  public Object getDelegate()
  {
    return this.delegate;
  }
  
  public void setDelegate(Object delegate)
  {
    this.proxyFactory.updateDelegate(this, this.delegate, delegate);
    this.delegate = delegate;
  }
  
  public Object getCreator()
  {
    return this.creator;
  }
  
  static
  {
    try
    {
      methodObject25887 = SQLOutput.class.getDeclaredMethod("writeBlob", new Class[] { Blob.class });
      methodObject25891 = SQLOutput.class.getDeclaredMethod("writeNClob", new Class[] { NClob.class });
      methodObject25881 = SQLOutput.class.getDeclaredMethod("writeDouble", new Class[] { Double.TYPE });
      methodObject25874 = SQLOutput.class.getDeclaredMethod("writeObject", new Class[] { SQLData.class });
      methodObject25899 = SQLOutput.class.getDeclaredMethod("writeTimestamp", new Class[] { Timestamp.class });
      methodObject25900 = SQLOutput.class.getDeclaredMethod("writeURL", new Class[] { URL.class });
      methodObject25883 = SQLOutput.class.getDeclaredMethod("writeArray", new Class[] { Array.class });
      methodObject25888 = SQLOutput.class.getDeclaredMethod("writeCharacterStream", new Class[] { Reader.class });
      methodObject25894 = SQLOutput.class.getDeclaredMethod("writeRowId", new Class[] { RowId.class });
      methodObject25893 = SQLOutput.class.getDeclaredMethod("writeRef", new Class[] { Ref.class });
      methodObject25882 = SQLOutput.class.getDeclaredMethod("writeBoolean", new Class[] { Boolean.TYPE });
      methodObject25886 = SQLOutput.class.getDeclaredMethod("writeBinaryStream", new Class[] { InputStream.class });
      methodObject25890 = SQLOutput.class.getDeclaredMethod("writeDate", new Class[] { Date.class });
      methodObject25895 = SQLOutput.class.getDeclaredMethod("writeSQLXML", new Class[] { SQLXML.class });
      methodObject25879 = SQLOutput.class.getDeclaredMethod("writeShort", new Class[] { Short.TYPE });
      methodObject25876 = SQLOutput.class.getDeclaredMethod("writeBytes", new Class[] { byte[].class });
      methodObject25878 = SQLOutput.class.getDeclaredMethod("writeLong", new Class[] { Long.TYPE });
      methodObject25885 = SQLOutput.class.getDeclaredMethod("writeBigDecimal", new Class[] { BigDecimal.class });
      methodObject25897 = SQLOutput.class.getDeclaredMethod("writeStruct", new Class[] { Struct.class });
      methodObject25898 = SQLOutput.class.getDeclaredMethod("writeTime", new Class[] { Time.class });
      methodObject25875 = SQLOutput.class.getDeclaredMethod("writeInt", new Class[] { Integer.TYPE });
      methodObject25892 = SQLOutput.class.getDeclaredMethod("writeNString", new Class[] { String.class });
      methodObject25877 = SQLOutput.class.getDeclaredMethod("writeByte", new Class[] { Byte.TYPE });
      methodObject25896 = SQLOutput.class.getDeclaredMethod("writeString", new Class[] { String.class });
      methodObject25889 = SQLOutput.class.getDeclaredMethod("writeClob", new Class[] { Clob.class });
      methodObject25884 = SQLOutput.class.getDeclaredMethod("writeAsciiStream", new Class[] { InputStream.class });
      methodObject25880 = SQLOutput.class.getDeclaredMethod("writeFloat", new Class[] { Float.TYPE });
    }
    catch (Throwable localThrowable)
    {
      throw new RuntimeException(localThrowable);
    }
  }
  
  public oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2java$1sql$1SQLOutput$$$Proxy(SQLOutput paramSQLOutput, Object paramObject, ProxyFactory paramProxyFactory, Map paramMap)
  {
    this.delegate = paramSQLOutput;
    this.creator = paramObject;
    this.proxyFactory = paramProxyFactory;
    this.proxyCache = paramMap;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/proxy/oracle$1jdbc$1replay$1driver$1NonTxnReplayableOthers$2java$1sql$1SQLOutput$$$Proxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */