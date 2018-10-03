package oracle.jdbc.driver;
import java.io.IOException;
import oracle.net.ns.BreakNetException;
import oracle.net.ns.NetException;
import oracle.net.ns.NetInputStream;
class T4CSocketInputStreamWrapper
  extends NetInputStream
{
  static final int MAX_BUFFER_SIZE = 2048;
/*  51 */   NetInputStream is = null;
/*  52 */   T4CSocketOutputStreamWrapper os = null;
/*  53 */   boolean eof = false;
  
/*  55 */   byte[] buffer = new byte['ࠀ'];
  
/*  57 */   int bIndex = 0;
  
  int bytesAvailable;
  
  T4CSocketInputStreamWrapper(NetInputStream paramNetInputStream, T4CSocketOutputStreamWrapper paramT4CSocketOutputStreamWrapper)
    throws IOException
  {
/*  65 */     this.is = paramNetInputStream;
/*  66 */     this.os = paramT4CSocketOutputStreamWrapper;
  }
  
  public final int read()
    throws IOException
  {
/*  75 */     if (this.eof) {
/*  76 */       return -1;
    }
/*  78 */     if (this.bytesAvailable < 1)
    {
/*  80 */       readNextPacket();
/*  81 */       if (this.eof)
/*  82 */         return -1;
    }
/*  84 */     this.bytesAvailable -= 1;
/*  85 */     return this.buffer[(this.bIndex++)] & 0xFF;
  }
  
  public final int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/*  91 */     if (this.eof) {
/*  92 */       return 0;
    }
/*  94 */     if (this.bytesAvailable < paramInt2)
    {
/*  96 */       int i = this.bytesAvailable;
      
/*  99 */       System.arraycopy(this.buffer, this.bIndex, paramArrayOfByte, paramInt1, i);
/* 100 */       paramInt1 += i;
/* 101 */       this.bIndex += i;
/* 102 */       this.bytesAvailable -= i;
      
/* 105 */       this.is.read(paramArrayOfByte, paramInt1, paramInt2 - i);
    }
    else {
/* 108 */       System.arraycopy(this.buffer, this.bIndex, paramArrayOfByte, paramInt1, paramInt2);
/* 109 */       this.bIndex += paramInt2;
/* 110 */       this.bytesAvailable -= paramInt2;
    }
    
/* 113 */     return paramInt2;
  }
  
  void readNextPacket()
    throws IOException
  {
/* 120 */     this.os.flush();
    
/* 124 */     int i = this.is.read();
    
/* 127 */     if (i == -1)
    {
/* 129 */       this.eof = true;
/* 130 */       return;
    }
    
/* 133 */     this.buffer[0] = ((byte)i);
    
/* 135 */     this.bytesAvailable = (this.is.available() + 1);
    
/* 139 */     this.bytesAvailable = (this.bytesAvailable > 2048 ? 2048 : this.bytesAvailable);
    
/* 142 */     if (this.bytesAvailable > 1) {
/* 143 */       this.is.read(this.buffer, 1, this.bytesAvailable - 1);
    }
    
/* 146 */     this.bIndex = 0;
  }
  
  public int readB1() throws IOException
  {
/* 151 */     return read();
  }
  
  public long readLongLSB(int paramInt) throws IOException
  {
/* 156 */     long l = 0L;
/* 157 */     int i = 0;
    
/* 159 */     if ((paramInt & 0x80) > 0)
    {
/* 161 */       paramInt &= 0x7F;
/* 162 */       i = 1;
    }
    
/* 167 */     int j = paramInt; for (int k = 0; j > 0; k++)
    {
/* 169 */       if (this.bytesAvailable < 1)
      {
/* 171 */         readNextPacket();
      }
/* 173 */       l |= (this.buffer[(this.bIndex++)] & 0xFF) << 8 * k;
/* 174 */       this.bytesAvailable -= 1;j--;
    }
    
/* 178 */     return (i != 0 ? -1 : 1) * l;
  }
  
  public long readLongMSB(int paramInt) throws IOException
  {
/* 183 */     long l = 0L;
/* 184 */     int i = 0;
    
/* 187 */     if ((paramInt & 0x80) > 0)
    {
/* 189 */       paramInt &= 0x7F;
/* 190 */       i = 1;
    }
    
/* 195 */     for (int j = paramInt; j > 0; j--)
    {
/* 197 */       if (this.bytesAvailable < 1)
      {
/* 199 */         readNextPacket();
      }
/* 201 */       l |= (this.buffer[(this.bIndex++)] & 0xFF) << 8 * (j - 1);
      
/* 203 */       this.bytesAvailable -= 1;
    }
    
/* 207 */     return (i != 0 ? -1 : 1) * l;
  }
  
  public boolean readZeroCopyIO(byte[] paramArrayOfByte, int paramInt, int[] paramArrayOfInt)
    throws IOException, NetException, BreakNetException
  {
/* 213 */     this.os.flush();
/* 214 */     return this.is.readZeroCopyIO(paramArrayOfByte, paramInt, paramArrayOfInt);
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CSocketInputStreamWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */