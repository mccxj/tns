package oracle.sql;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
public abstract class Datum
  implements Serializable
{
  private byte[] data;
  static final long serialVersionUID = 4645732484621936751L;
  
  public Datum() {}
  
  public Datum(byte[] paramArrayOfByte)
  {
/*  53 */     this.data = paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
/*  67 */     if (this == paramObject)
/*  68 */       return true;
/*  69 */     if ((paramObject == null) || (!(paramObject instanceof Datum)))
/*  70 */       return false;
/*  71 */     if (getClass() == paramObject.getClass())
    {
/*  73 */       Datum localDatum = (Datum)paramObject;
      
/*  77 */       if ((this.data == null) && (localDatum.data == null))
/*  78 */         return true;
/*  79 */       if (((this.data == null) && (localDatum.data != null)) || ((this.data != null) && (localDatum.data == null)))
      {
/*  81 */         return false;
      }
/*  83 */       if (this.data.length != localDatum.data.length) {
/*  84 */         return false;
      }
/*  86 */       for (int i = 0; i < this.data.length; i++)
      {
/*  88 */         if (this.data[i] != localDatum.data[i]) {
/*  89 */           return false;
        }
      }
/*  92 */       return true;
    }
    
/*  95 */     return false;
  }
  
  public byte[] shareBytes()
  {
/* 108 */     return this.data;
  }
  
  public long getLength()
  {
/* 118 */     if (null == this.data) {
/* 119 */       return 0L;
    }
/* 121 */     return this.data.length;
  }
  
  public void setBytes(byte[] paramArrayOfByte)
  {
/* 131 */     int i = paramArrayOfByte.length;
/* 132 */     this.data = new byte[i];
/* 133 */     System.arraycopy(paramArrayOfByte, 0, this.data, 0, i);
  }
  
  public void setShareBytes(byte[] paramArrayOfByte)
  {
/* 143 */     this.data = paramArrayOfByte;
  }
  
  public byte[] getBytes()
  {
/* 166 */     if (this.data == null) {
/* 167 */       return new byte[0];
    }
/* 169 */     byte[] arrayOfByte = new byte[this.data.length];
/* 170 */     System.arraycopy(this.data, 0, arrayOfByte, 0, this.data.length);
/* 171 */     return arrayOfByte;
  }
  
  public InputStream getStream()
  {
/* 183 */     return new ByteArrayInputStream(this.data);
  }
  
  public String stringValue()
    throws SQLException
  {
/* 194 */     throw new SQLException("Conversion to String failed");
  }
  
  public String stringValue(Connection paramConnection)
    throws SQLException
  {
/* 205 */     return stringValue();
  }
  
  public boolean booleanValue()
    throws SQLException
  {
/* 216 */     throw new SQLException("Conversion to boolean failed");
  }
  
  public int intValue()
    throws SQLException
  {
/* 227 */     throw new SQLException("Conversion to integer failed");
  }
  
  public long longValue()
    throws SQLException
  {
/* 238 */     throw new SQLException("Conversion to long failed");
  }
  
  public float floatValue()
    throws SQLException
  {
/* 249 */     throw new SQLException("Conversion to float failed");
  }
  
  public double doubleValue()
    throws SQLException
  {
/* 260 */     throw new SQLException("Conversion to double failed");
  }
  
  public byte byteValue()
    throws SQLException
  {
/* 271 */     throw new SQLException("Conversion to byte failed");
  }
  
  public BigDecimal bigDecimalValue()
    throws SQLException
  {
/* 282 */     throw new SQLException("Conversion to BigDecimal failed");
  }
  
  public Date dateValue()
    throws SQLException
  {
/* 293 */     throw new SQLException("Conversion to Date failed");
  }
  
  public Time timeValue()
    throws SQLException
  {
/* 304 */     throw new SQLException("Conversion to Time failed");
  }
  
  public Time timeValue(Calendar paramCalendar) throws SQLException {
/* 308 */     throw new SQLException("Conversion to Time failed");
  }
  
  public Timestamp timestampValue()
    throws SQLException
  {
/* 318 */     throw new SQLException("Conversion to Timestamp failed");
  }
  
  public Timestamp timestampValue(Calendar paramCalendar) throws SQLException {
/* 322 */     throw new SQLException("Conversion to Timestamp failed");
  }
  
  public Reader characterStreamValue()
    throws SQLException
  {
/* 333 */     throw new SQLException("Conversion to character stream failed");
  }
  
  public InputStream asciiStreamValue()
    throws SQLException
  {
/* 344 */     throw new SQLException("Conversion to ascii stream failed");
  }
  
  public InputStream binaryStreamValue()
    throws SQLException
  {
/* 355 */     throw new SQLException("Conversion to binary stream failed");
  }
  
  public abstract boolean isConvertibleTo(Class paramClass);
  
  public abstract Object toJdbc()
    throws SQLException;
  
  public abstract Object makeJdbcArray(int paramInt);
  
  protected static int compareBytes(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
/* 393 */     int i = paramArrayOfByte1.length;
/* 394 */     int j = paramArrayOfByte2.length;
/* 395 */     int k = 0;
/* 396 */     int m = Math.min(i, j);
/* 397 */     int n = 0;
/* 398 */     int i1 = 0;
    
/* 400 */     while (k < m)
    {
/* 403 */       n = paramArrayOfByte1[k] & 0xFF;
/* 404 */       i1 = paramArrayOfByte2[k] & 0xFF;
      
/* 406 */       if (n != i1)
      {
/* 408 */         if (n < i1) {
/* 409 */           return -1;
        }
/* 411 */         return 1;
      }
      
/* 414 */       k++;
    }
    
/* 419 */     if (i == j)
/* 420 */       return 0;
/* 421 */     if (i > j) {
/* 422 */       return 1;
    }
/* 424 */     return -1;
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/Datum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */