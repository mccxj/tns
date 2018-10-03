package oracle.jdbc.driver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.SQLException;
class T2CInputStream
  extends OracleInputStream
{
  native int t2cGetBytes(long paramLong1, int paramInt1, byte[] paramArrayOfByte1, int paramInt2, Accessor[] paramArrayOfAccessor, byte[] paramArrayOfByte2, int paramInt3, char[] paramArrayOfChar, int paramInt4, short[] paramArrayOfShort, int paramInt5, Object[] paramArrayOfObject1, Object[] paramArrayOfObject2, long paramLong2);
  
  T2CInputStream(OracleStatement paramOracleStatement, int paramInt, Accessor paramAccessor)
  {
/*  65 */     super(paramOracleStatement, paramInt, paramAccessor);
  }
  
  public int getBytes(int paramInt)
    throws IOException
  {
/*  72 */     synchronized (this.statement.connection)
    {
/*  75 */       if (paramInt > this.currentBufferSize)
      {
/*  77 */         this.currentBufferSize = Math.max(paramInt, this.initialBufferSize);
/*  78 */         this.resizableBuffer = new byte[this.currentBufferSize];
      }
/*  80 */       long l = this.statement.connection.useNio ? 1 : 0;
/*  81 */       if (this.statement.connection.useNio)
      {
/*  83 */         if ((this.statement.nioBuffers[3] == null) || (this.statement.nioBuffers[3].capacity() < this.resizableBuffer.length))
        {
/*  86 */           this.statement.nioBuffers[3] = ByteBuffer.allocateDirect(this.resizableBuffer.length);
        }
        else {
/*  89 */           this.statement.nioBuffers[3].rewind();
        }
      }
/*  92 */       int i = t2cGetBytes(this.statement.c_state, this.columnIndex, this.resizableBuffer, this.currentBufferSize, this.statement.accessors, this.statement.defineBytes, this.statement.accessorByteOffset, this.statement.defineChars, this.statement.accessorCharOffset, this.statement.defineIndicators, this.statement.accessorShortOffset, this.statement.nioBuffers, this.statement.lobPrefetchMetaData, l);
      
/* 103 */       int j = 0;
      
      try
      {
/* 113 */         if (i == -1)
        {
/* 115 */           ((T2CConnection)this.statement.connection).checkError(i, this.statement.sqlWarning);
        }
/* 117 */         else if (i == -2)
        {
/* 119 */           j = 1;
/* 120 */           this.accessor.setNull(this.statement.currentRow == -1 ? 0 : this.statement.currentRow, true);
/* 121 */           i = 0;
/* 122 */         } else if ((this.statement.connection.useNio) && (i >= 0))
        {
/* 126 */           this.accessor.setNull(this.statement.currentRow == -1 ? 0 : this.statement.currentRow, false);
        }
      }
      catch (SQLException localSQLException1) {
/* 130 */         throw new IOException(localSQLException1.getMessage());
      }
      
/* 133 */       if (i <= 0) {
/* 134 */         i = -1;
/* 135 */         j = 1;
      }
      
/* 138 */       if (this.statement.connection.useNio)
      {
/* 140 */         ByteBuffer localByteBuffer = this.statement.nioBuffers[3];
/* 141 */         if ((localByteBuffer != null) && (i > 0))
        {
/* 143 */           localByteBuffer.get(this.resizableBuffer, 0, i);
        }
        
/* 147 */         if (j != 0)
        {
          try
          {
/* 151 */             this.statement.extractNioDefineBuffers(this.columnIndex);
          }
          catch (SQLException localSQLException2)
          {
/* 155 */             throw new IOException(localSQLException2.getMessage());
          }
        }
      }
      
/* 160 */       if ((j != 0) && (this.statement.lobPrefetchMetaData != null))
      {
/* 162 */         this.statement.processLobPrefetchMetaData(this.statement.lobPrefetchMetaData);
      }
/* 164 */       return i;
    }
  }
  
/* 170 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T2CInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */