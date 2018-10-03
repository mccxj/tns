package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import oracle.jdbc.internal.OracleConnection;
abstract class OracleBufferedStream
  extends InputStream
{
  byte[] resizableBuffer;
  int initialBufferSize;
  int currentBufferSize;
  int pos;
  int count;
/*  30 */   long maxPosition = 2147483647L;
  
  boolean closed;
  OracleStatement statement;
  
  public OracleBufferedStream(int paramInt)
  {
/*  37 */     this.pos = 0;
/*  38 */     this.count = 0;
/*  39 */     this.closed = false;
/*  40 */     this.initialBufferSize = paramInt;
/*  41 */     this.currentBufferSize = 0;
/*  42 */     this.resizableBuffer = null;
  }
  
  public OracleBufferedStream(OracleStatement paramOracleStatement, int paramInt)
  {
/*  48 */     this(paramInt);
    
/*  51 */     this.statement = paramOracleStatement;
  }
  
  public void close()
    throws IOException
  {
/*  60 */     this.closed = true;
/*  61 */     this.resizableBuffer = null;
  }
  
  public boolean needBytes()
    throws IOException
  {
/*  68 */     return needBytes(Math.max(this.initialBufferSize, this.currentBufferSize));
  }
  
  public abstract boolean needBytes(int paramInt)
    throws IOException;
  
  public int flushBytes(int paramInt)
  {
/*  77 */     int i = paramInt > this.count - this.pos ? this.count - this.pos : paramInt;
    
/*  79 */     this.pos += i;
    
/*  81 */     return i;
  }
  
  public int writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
/*  88 */     int i = paramInt2 > this.count - this.pos ? this.count - this.pos : paramInt2;
    
/*  90 */     System.arraycopy(this.resizableBuffer, this.pos, paramArrayOfByte, paramInt1, i);
    
/*  92 */     this.pos += i;
    
/*  94 */     return i;
  }
  
  /* Error */
  public int read()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   4: ifnonnull +19 -> 23
    //   7: aload_0
    //   8: dup
    //   9: astore_1
    //   10: monitorenter
    //   11: aload_0
    //   12: invokespecial 16	oracle/jdbc/driver/OracleBufferedStream:readInternal	()I
    //   15: aload_1
    //   16: monitorexit
    //   17: ireturn
    //   18: astore_2
    //   19: aload_1
    //   20: monitorexit
    //   21: aload_2
    //   22: athrow
    //   23: aload_0
    //   24: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   27: getfield 17	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
    //   30: dup
    //   31: astore_1
    //   32: monitorenter
    //   33: aload_0
    //   34: invokespecial 16	oracle/jdbc/driver/OracleBufferedStream:readInternal	()I
    //   37: aload_1
    //   38: monitorexit
    //   39: ireturn
    //   40: astore_3
    //   41: aload_1
    //   42: monitorexit
    //   43: aload_3
    //   44: athrow
    // Line number table:
    //   Java source line #101	-> byte code offset #0
    //   Java source line #103	-> byte code offset #7
    //   Java source line #104	-> byte code offset #11
    //   Java source line #105	-> byte code offset #18
    //   Java source line #108	-> byte code offset #23
    //   Java source line #109	-> byte code offset #33
    //   Java source line #110	-> byte code offset #40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	OracleBufferedStream
    //   9	33	1	Ljava/lang/Object;	Object
    //   18	4	2	localObject1	Object
    //   40	4	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   11	17	18	finally
    //   18	21	18	finally
    //   33	39	40	finally
    //   40	43	40	finally
  }
  
  private final int readInternal()
    throws IOException
  {
/* 119 */     if ((this.closed) || (isNull())) {
/* 120 */       return -1;
    }
/* 122 */     if (needBytes()) {
/* 123 */       return this.resizableBuffer[(this.pos++)] & 0xFF;
    }
/* 125 */     return -1;
  }
  
  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
/* 132 */     return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  /* Error */
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   4: ifnonnull +27 -> 31
    //   7: aload_0
    //   8: dup
    //   9: astore 4
    //   11: monitorenter
    //   12: aload_0
    //   13: aload_1
    //   14: iload_2
    //   15: iload_3
    //   16: invokespecial 21	oracle/jdbc/driver/OracleBufferedStream:readInternal	([BII)I
    //   19: aload 4
    //   21: monitorexit
    //   22: ireturn
    //   23: astore 5
    //   25: aload 4
    //   27: monitorexit
    //   28: aload 5
    //   30: athrow
    //   31: aload_0
    //   32: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   35: getfield 17	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
    //   38: dup
    //   39: astore 4
    //   41: monitorenter
    //   42: aload_0
    //   43: aload_1
    //   44: iload_2
    //   45: iload_3
    //   46: invokespecial 21	oracle/jdbc/driver/OracleBufferedStream:readInternal	([BII)I
    //   49: aload 4
    //   51: monitorexit
    //   52: ireturn
    //   53: astore 6
    //   55: aload 4
    //   57: monitorexit
    //   58: aload 6
    //   60: athrow
    // Line number table:
    //   Java source line #140	-> byte code offset #0
    //   Java source line #142	-> byte code offset #7
    //   Java source line #143	-> byte code offset #12
    //   Java source line #144	-> byte code offset #23
    //   Java source line #147	-> byte code offset #31
    //   Java source line #148	-> byte code offset #42
    //   Java source line #149	-> byte code offset #53
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	61	0	this	OracleBufferedStream
    //   0	61	1	paramArrayOfByte	byte[]
    //   0	61	2	paramInt1	int
    //   0	61	3	paramInt2	int
    //   9	47	4	Ljava/lang/Object;	Object
    //   23	6	5	localObject1	Object
    //   53	6	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   12	22	23	finally
    //   23	28	23	finally
    //   42	52	53	finally
    //   53	58	53	finally
  }
  
  private final int readInternal(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
/* 159 */     int j = paramInt1;
    
/* 161 */     if ((this.closed) || (isNull()))
/* 162 */       return -1;
    int i;
/* 164 */     if (paramInt2 > paramArrayOfByte.length) {
/* 165 */       i = j + paramArrayOfByte.length;
    } else {
/* 167 */       i = j + paramInt2;
    }
/* 169 */     if (!needBytes(paramInt2)) {
/* 170 */       return -1;
    }
/* 172 */     j += writeBytes(paramArrayOfByte, j, i - j);
    
/* 174 */     while ((j < i) && (needBytes(i - j)))
    {
/* 176 */       j += writeBytes(paramArrayOfByte, j, i - j);
    }
    
/* 179 */     return j - paramInt1;
  }
  
  public int available()
    throws IOException
  {
/* 186 */     if ((this.closed) || (isNull())) {
/* 187 */       return 0;
    }
/* 189 */     return this.count - this.pos;
  }
  
  public boolean isNull()
    throws IOException
  {
/* 196 */     return false;
  }
  
  public void mark(int paramInt) {}
  
  public void reset()
    throws IOException
  {
/* 207 */     synchronized (this.statement.connection)
    {
/* 209 */       throw new IOException(DatabaseError.findMessage(194, null));
    }
  }
  
  public boolean markSupported()
  {
/* 217 */     return false;
  }
  
  /* Error */
  public long skip(int paramInt)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   4: ifnonnull +21 -> 25
    //   7: aload_0
    //   8: dup
    //   9: astore_2
    //   10: monitorenter
    //   11: aload_0
    //   12: iload_1
    //   13: invokespecial 26	oracle/jdbc/driver/OracleBufferedStream:skipInternal	(I)I
    //   16: i2l
    //   17: aload_2
    //   18: monitorexit
    //   19: lreturn
    //   20: astore_3
    //   21: aload_2
    //   22: monitorexit
    //   23: aload_3
    //   24: athrow
    //   25: aload_0
    //   26: getfield 12	oracle/jdbc/driver/OracleBufferedStream:statement	Loracle/jdbc/driver/OracleStatement;
    //   29: getfield 17	oracle/jdbc/driver/OracleStatement:connection	Loracle/jdbc/driver/PhysicalConnection;
    //   32: dup
    //   33: astore_2
    //   34: monitorenter
    //   35: aload_0
    //   36: iload_1
    //   37: invokespecial 26	oracle/jdbc/driver/OracleBufferedStream:skipInternal	(I)I
    //   40: i2l
    //   41: aload_2
    //   42: monitorexit
    //   43: lreturn
    //   44: astore 4
    //   46: aload_2
    //   47: monitorexit
    //   48: aload 4
    //   50: athrow
    // Line number table:
    //   Java source line #230	-> byte code offset #0
    //   Java source line #232	-> byte code offset #7
    //   Java source line #233	-> byte code offset #11
    //   Java source line #234	-> byte code offset #20
    //   Java source line #237	-> byte code offset #25
    //   Java source line #238	-> byte code offset #35
    //   Java source line #239	-> byte code offset #44
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	51	0	this	OracleBufferedStream
    //   0	51	1	paramInt	int
    //   9	38	2	Ljava/lang/Object;	Object
    //   20	4	3	localObject1	Object
    //   44	5	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   11	19	20	finally
    //   20	23	20	finally
    //   35	43	44	finally
    //   44	48	44	finally
  }
  
  private final int skipInternal(int paramInt)
    throws IOException
  {
/* 247 */     int i = 0;
/* 248 */     int j = paramInt;
    
/* 250 */     if ((this.closed) || (isNull())) {
/* 251 */       return -1;
    }
/* 253 */     if (!needBytes()) {
/* 254 */       return -1;
    }
/* 256 */     while ((i < j) && (needBytes()))
    {
/* 258 */       i += flushBytes(j - i);
    }
    
/* 261 */     return i;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 276 */     return this.statement.getConnectionDuringExceptionHandling();
  }
  
/* 282 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleBufferedStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */