package oracle.jdbc.oracore;
import java.sql.SQLException;
import java.util.Vector;
public final class UnpickleContext
{
  byte[] image;
  int absoluteOffset;
  int beginOffset;
  int markedOffset;
  Vector patches;
  long[] ldsOffsets;
  boolean[] nullIndicators;
  boolean bigEndian;
  
  public UnpickleContext() {}
  
  public UnpickleContext(byte[] paramArrayOfByte, int paramInt, boolean[] paramArrayOfBoolean, long[] paramArrayOfLong, boolean paramBoolean)
  {
/*  37 */     this.image = paramArrayOfByte;
/*  38 */     this.beginOffset = paramInt;
/*  39 */     this.absoluteOffset = paramInt;
/*  40 */     this.bigEndian = paramBoolean;
/*  41 */     this.nullIndicators = paramArrayOfBoolean;
/*  42 */     this.patches = null;
/*  43 */     this.ldsOffsets = paramArrayOfLong;
  }
  
  public byte readByte()
    throws SQLException
  {
    try
    {
/*  52 */       return this.image[this.absoluteOffset];
    }
    finally
    {
/*  56 */       this.absoluteOffset += 1;
    }
  }
  
  public byte[] readVarNumBytes()
    throws SQLException
  {
/*  65 */     byte[] arrayOfByte = new byte[this.image[this.absoluteOffset] & 0xFF];
    
    try
    {
/*  69 */       System.arraycopy(this.image, this.absoluteOffset + 1, arrayOfByte, 0, arrayOfByte.length);
    }
    finally
    {
/*  74 */       this.absoluteOffset += 22;
    }
    
/*  77 */     return arrayOfByte;
  }
  
  public byte[] readPtrBytes()
    throws SQLException
  {
/*  85 */     byte[] arrayOfByte = new byte[(this.image[this.absoluteOffset] & 0xFF) * 256 + (this.image[(this.absoluteOffset + 1)] & 0xFF) + 2];
    
/*  88 */     System.arraycopy(this.image, this.absoluteOffset, arrayOfByte, 0, arrayOfByte.length);
    
/*  90 */     this.absoluteOffset += arrayOfByte.length;
    
/*  92 */     return arrayOfByte;
  }
  
  public void skipPtrBytes()
    throws SQLException
  {
/* 100 */     this.absoluteOffset += (this.image[this.absoluteOffset] & 0xFF) * 256 + (this.image[(this.absoluteOffset + 1)] & 0xFF) + 2;
  }
  
  public byte[] readBytes(int paramInt)
    throws SQLException
  {
    try
    {
/* 111 */       byte[] arrayOfByte1 = new byte[paramInt];
      
/* 113 */       System.arraycopy(this.image, this.absoluteOffset, arrayOfByte1, 0, paramInt);
      
/* 115 */       return arrayOfByte1;
    }
    finally
    {
/* 119 */       this.absoluteOffset += paramInt;
    }
  }
  
  public long readLong()
    throws SQLException
  {
    try
    {
/* 130 */       return (((this.image[this.absoluteOffset] & 0xFF) * 256 + (this.image[(this.absoluteOffset + 1)] & 0xFF)) * 256 + (this.image[(this.absoluteOffset + 2)] & 0xFF)) * 256 + (this.image[(this.absoluteOffset + 3)] & 0xFF);
    }
    finally
    {
/* 135 */       this.absoluteOffset += 4;
    }
  }
  
  public short readShort()
    throws SQLException
  {
    try
    {
/* 146 */       return (short)((this.image[this.absoluteOffset] & 0xFF) * 256 + (this.image[(this.absoluteOffset + 1)] & 0xFF));
    }
    finally
    {
/* 151 */       this.absoluteOffset += 2;
    }
  }
  
  public byte[] readLengthBytes()
    throws SQLException
  {
/* 160 */     long l = readLong();
    
/* 162 */     return readBytes((int)l);
  }
  
  public void skipLengthBytes()
    throws SQLException
  {
/* 170 */     long l = readLong();
    
/* 172 */     this.absoluteOffset = ((int)(this.absoluteOffset + l));
  }
  
  public void skipTo(long paramLong)
    throws SQLException
  {
/* 182 */     if (paramLong > this.absoluteOffset - this.beginOffset) {
/* 183 */       this.absoluteOffset = (this.beginOffset + (int)paramLong);
    }
  }
  
  public void skipTo(int paramInt)
    throws SQLException
  {
/* 190 */     if (paramInt > this.absoluteOffset - this.beginOffset) {
/* 191 */       this.absoluteOffset = (this.beginOffset + paramInt);
    }
  }
  
  public void mark()
    throws SQLException
  {
/* 199 */     this.markedOffset = this.absoluteOffset;
  }
  
  public void reset()
    throws SQLException
  {
/* 207 */     this.absoluteOffset = this.markedOffset;
  }
  
  public void markAndSkip()
    throws SQLException
  {
/* 215 */     this.markedOffset = (this.absoluteOffset + 4);
/* 216 */     this.absoluteOffset = (this.beginOffset + (int)readLong());
  }
  
  public void markAndSkip(long paramLong)
    throws SQLException
  {
/* 224 */     this.markedOffset = this.absoluteOffset;
/* 225 */     this.absoluteOffset = (this.beginOffset + (int)paramLong);
  }
  
  public void skipBytes(int paramInt)
    throws SQLException
  {
/* 233 */     if (paramInt >= 0) {
/* 234 */       this.absoluteOffset += paramInt;
    }
  }
  
  public boolean isNull(int paramInt)
  {
/* 242 */     return this.nullIndicators[paramInt];
  }
  
  public int absoluteOffset()
    throws SQLException
  {
/* 250 */     return this.absoluteOffset;
  }
  
  public int offset()
    throws SQLException
  {
/* 258 */     return this.absoluteOffset - this.beginOffset;
  }
  
  public byte[] image()
    throws SQLException
  {
/* 266 */     return this.image;
  }
  
/* 290 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/UnpickleContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */