package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
abstract class OracleInputStream
  extends OracleBufferedStream
{
  int columnIndex;
  Accessor accessor;
  OracleInputStream nextStream;
/*  37 */   boolean hasBeenOpen = false;
  
  protected OracleInputStream(OracleStatement paramOracleStatement, int paramInt, Accessor paramAccessor)
  {
/*  41 */     super(paramOracleStatement, paramOracleStatement.connection.getDefaultStreamChunkSize());
    
/*  43 */     this.closed = true;
/*  44 */     this.statement = paramOracleStatement;
/*  45 */     this.columnIndex = paramInt;
/*  46 */     this.accessor = paramAccessor;
/*  47 */     this.nextStream = null;
    
/*  57 */     OracleInputStream localOracleInputStream = this.statement.streamList;
    
/*  59 */     if ((localOracleInputStream == null) || (this.columnIndex < localOracleInputStream.columnIndex))
    {
/*  63 */       this.nextStream = this.statement.streamList;
/*  64 */       this.statement.streamList = this;
    }
/*  66 */     else if (this.columnIndex == localOracleInputStream.columnIndex)
    {
/*  71 */       this.nextStream = localOracleInputStream.nextStream;
/*  72 */       localOracleInputStream.nextStream = null;
/*  73 */       this.statement.streamList = this;
    }
    else
    {
/*  78 */       while ((localOracleInputStream.nextStream != null) && (this.columnIndex > localOracleInputStream.nextStream.columnIndex))
      {
/*  80 */         localOracleInputStream = localOracleInputStream.nextStream;
      }
      
/*  83 */       if ((localOracleInputStream.nextStream != null) && (this.columnIndex == localOracleInputStream.nextStream.columnIndex))
      {
/*  87 */         this.nextStream = localOracleInputStream.nextStream.nextStream;
/*  88 */         localOracleInputStream.nextStream.nextStream = null;
/*  89 */         localOracleInputStream.nextStream = this;
      }
      else
      {
/*  95 */         this.nextStream = localOracleInputStream.nextStream;
/*  96 */         localOracleInputStream.nextStream = this;
      }
    }
  }
  
  public String toString()
  {
/* 105 */     return "OIS@" + Integer.toHexString(hashCode()) + "{" + "statement = " + this.statement + ", accessor = " + this.accessor + ", nextStream = " + this.nextStream + ", columnIndex = " + this.columnIndex + ", hasBeenOpen = " + this.hasBeenOpen + "}";
  }
  
  public boolean needBytes(int paramInt)
    throws IOException
  {
/* 116 */     if (this.closed) {
/* 117 */       return false;
    }
/* 119 */     if (this.pos >= this.count)
    {
/* 121 */       if (paramInt > this.currentBufferSize)
      {
/* 123 */         this.currentBufferSize = Math.max(paramInt, this.initialBufferSize);
/* 124 */         this.resizableBuffer = new byte[this.currentBufferSize];
      }
      try
      {
/* 128 */         int i = getBytes(this.currentBufferSize);
        
/* 130 */         this.pos = 0;
/* 131 */         this.count = i;
        
/* 133 */         if (this.count == -1)
        {
/* 137 */           if (this.nextStream == null) {
/* 138 */             this.statement.connection.releaseLine();
          }
/* 140 */           this.closed = true;
          
/* 142 */           this.accessor.fetchNextColumns();
          
/* 144 */           return false;
        }
        
      }
      catch (SQLException localSQLException)
      {
/* 153 */         IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 154 */         localIOException.fillInStackTrace();
/* 155 */         throw localIOException;
      }
    }
    
/* 160 */     return true;
  }
  
  public boolean isNull()
    throws IOException
  {
/* 167 */     boolean bool = false;
    
    try
    {
/* 171 */       bool = this.accessor.isNull(0);
    }
    catch (SQLException localSQLException)
    {
/* 176 */       IOException localIOException = DatabaseError.createIOException(localSQLException);
/* 177 */       localIOException.fillInStackTrace();
/* 178 */       throw localIOException;
    }
    
/* 182 */     return bool;
  }
  
  public boolean isClosed()
  {
/* 189 */     return this.closed;
  }
  
  public void close()
    throws IOException
  {
/* 195 */     synchronized (this.statement.connection)
    {
/* 197 */       if ((!this.closed) && (this.hasBeenOpen))
      {
/* 201 */         while (this.statement.nextStream != this)
        {
/* 203 */           this.statement.nextStream.close();
          
/* 205 */           this.statement.nextStream = this.statement.nextStream.nextStream;
        }
        
/* 208 */         if (!isNull())
        {
/* 211 */           while (needBytes(Math.max(this.initialBufferSize, this.currentBufferSize)))
          {
/* 215 */             this.pos = this.count;
          }
        }
        
/* 219 */         this.closed = true;
/* 220 */         this.resizableBuffer = null;
/* 221 */         this.currentBufferSize = 0;
      }
    }
  }
  
/* 234 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
  
  public abstract int getBytes(int paramInt)
    throws IOException;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleInputStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */