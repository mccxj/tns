package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
class OracleConversionInputStreamInternal
  extends OracleConversionInputStream
{
/* 536 */   boolean needReset = false;
  
  public OracleConversionInputStreamInternal(DBConversion paramDBConversion, InputStream paramInputStream, int paramInt1, int paramInt2)
  {
/* 541 */     super(paramDBConversion, paramInputStream, paramInt1, paramInt2);
  }
  
  public OracleConversionInputStreamInternal(DBConversion paramDBConversion, Reader paramReader, int paramInt1, int paramInt2, short paramShort)
  {
/* 552 */     super(paramDBConversion, paramReader, paramInt1, paramInt2, paramShort);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/* 562 */     if (this.needReset)
    {
/* 564 */       if ((this.istream != null) && (this.istream.markSupported()))
      {
/* 567 */         this.istream.reset();
/* 568 */         this.endOfStream = false;
/* 569 */         this.totalSize = 0;
/* 570 */         this.needReset = false;
      }
/* 572 */       else if ((this.reader != null) && (this.reader.markSupported()))
      {
/* 575 */         this.reader.reset();
/* 576 */         this.endOfStream = false;
/* 577 */         this.totalSize = 0;
/* 578 */         this.needReset = false;
      }
    }
    
/* 582 */     int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
    
/* 584 */     if (i == -1) {
/* 585 */       this.needReset = true;
    }
/* 587 */     return i;
  }
  
/* 592 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleConversionInputStreamInternal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */