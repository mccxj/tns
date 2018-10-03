package oracle.jdbc.driver;
import java.io.IOException;
import oracle.net.ns.BreakNetException;
import oracle.net.ns.NetException;
import oracle.net.ns.NetOutputStream;
class T4CSocketOutputStreamWrapper
  extends NetOutputStream
{
  static final int MAX_BUFFER_SIZE = 2048;
/*  46 */   NetOutputStream os = null;
  
/*  48 */   byte[] buffer = new byte['ࠀ'];
/*  49 */   int bIndex = 0;
  
  T4CSocketOutputStreamWrapper(NetOutputStream paramNetOutputStream)
    throws IOException
  {
/*  54 */     this.os = paramNetOutputStream;
  }
  
  public void write(int paramInt) throws IOException
  {
/*  59 */     if (this.bIndex + 1 >= 2048) {
/*  60 */       flush();
    }
/*  62 */     this.buffer[(this.bIndex++)] = ((byte)(paramInt & 0xFF));
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/*  68 */     if (paramInt2 > 2048)
    {
/*  74 */       flush();
      
/*  77 */       this.os.write(paramArrayOfByte, paramInt1, paramInt2);
/*  78 */     } else if (this.bIndex + paramInt2 < 2048)
    {
/*  80 */       System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.bIndex, paramInt2);
/*  81 */       this.bIndex += paramInt2;
    }
    else {
/*  84 */       flush();
/*  85 */       System.arraycopy(paramArrayOfByte, paramInt1, this.buffer, this.bIndex, paramInt2);
/*  86 */       this.bIndex += paramInt2;
    }
  }
  
  public void flush() throws IOException
  {
/*  92 */     flush(false);
  }
  
  public void flush(boolean paramBoolean) throws IOException
  {
/*  97 */     if (this.bIndex > 0)
    {
/*  99 */       this.os.write(this.buffer, 0, this.bIndex);
/* 100 */       this.bIndex = 0;
    }
/* 102 */     if (paramBoolean) {
/* 103 */       this.os.flush();
    }
  }
  
  public void close() throws IOException {
/* 108 */     this.os.close();
/* 109 */     super.close();
  }
  
  public void writeZeroCopyIO(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, NetException, BreakNetException
  {
/* 115 */     flush(true);
/* 116 */     this.os.writeZeroCopyIO(paramArrayOfByte, paramInt1, paramInt2);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CSocketOutputStreamWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */