package oracle.jdbc.oracore;
import java.sql.SQLException;
import java.util.Vector;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class TDSReader
{
  static final int KOPT_NONE_FINAL_TYPE = 1;
  static final int KOPT_JAVA_OBJECT = 2;
  long fixedDataSize;
  Vector patches;
  byte[] tds;
  int beginIndex;
  int index;
  
  TDSReader(byte[] paramArrayOfByte, long paramLong)
  {
/*  44 */     this.fixedDataSize = 0L;
/*  45 */     this.patches = null;
    
/*  47 */     this.tds = paramArrayOfByte;
/*  48 */     this.beginIndex = ((int)paramLong);
/*  49 */     this.index = ((int)paramLong);
  }
  
  void skipBytes(int paramInt)
    throws SQLException
  {
/*  63 */     this.index += paramInt;
  }
  
  void checkNextByte(byte paramByte)
    throws SQLException
  {
    try
    {
/*  77 */       if (paramByte != this.tds[this.index])
      {
/*  79 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 47, "parseTDS");
/*  80 */         localSQLException.fillInStackTrace();
/*  81 */         throw localSQLException;
      }
      
    }
    finally
    {
/*  88 */       this.index += 1;
    }
  }
  
  byte readByte()
    throws SQLException
  {
    try
    {
/* 103 */       return this.tds[this.index];
    }
    finally
    {
/* 109 */       this.index += 1;
    }
  }
  
  int readUnsignedByte()
    throws SQLException
  {
    try
    {
/* 124 */       return this.tds[this.index] & 0xFF;
    }
    finally
    {
/* 130 */       this.index += 1;
    }
  }
  
  int readUB2()
    throws SQLException
  {
    try
    {
/* 146 */       return ((this.tds[this.index] & 0xFF) << 8) + (this.tds[(this.index + 1)] & 0xFF);
    }
    finally
    {
/* 152 */       this.index += 2;
    }
  }
  
  long readLong()
    throws SQLException
  {
    try
    {
/* 169 */       return (((this.tds[this.index] & 0xFF) * 256 + (this.tds[(this.index + 1)] & 0xFF)) * 256 + (this.tds[(this.index + 2)] & 0xFF)) * 256 + (this.tds[(this.index + 3)] & 0xFF);
    }
    finally
    {
/* 176 */       this.index += 4;
    }
  }
  
  void addNormalPatch(long paramLong, byte paramByte, OracleType paramOracleType)
    throws SQLException
  {
/* 194 */     addPatch(new TDSPatch(0, paramOracleType, paramLong, paramByte));
  }
  
  void addSimplePatch(long paramLong, OracleType paramOracleType)
    throws SQLException
  {
/* 207 */     addPatch(new TDSPatch(1, paramOracleType, paramLong, 0));
  }
  
  void addPatch(TDSPatch paramTDSPatch)
    throws SQLException
  {
/* 214 */     if (this.patches == null) {
/* 215 */       this.patches = new Vector(5);
    }
/* 217 */     this.patches.addElement(paramTDSPatch);
  }
  
  long moveToPatchPos(TDSPatch paramTDSPatch)
    throws SQLException
  {
/* 224 */     long l = paramTDSPatch.getPosition();
    
/* 226 */     if (this.beginIndex + l > this.tds.length)
    {
/* 228 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 47, "parseTDS");
/* 229 */       localSQLException.fillInStackTrace();
/* 230 */       throw localSQLException;
    }
    
/* 233 */     skip_to(l);
    
/* 235 */     return l;
  }
  
  TDSPatch getNextPatch()
    throws SQLException
  {
/* 245 */     TDSPatch localTDSPatch = null;
    
/* 247 */     if (this.patches != null)
    {
/* 249 */       if (this.patches.size() > 0)
      {
/* 251 */         localTDSPatch = (TDSPatch)this.patches.firstElement();
        
/* 253 */         this.patches.removeElementAt(0);
      }
    }
    
/* 257 */     return localTDSPatch;
  }
  
  void skip_to(long paramLong)
  {
/* 265 */     this.index = (this.beginIndex + (int)paramLong);
  }
  
  long offset()
    throws SQLException
  {
/* 273 */     return this.index - this.beginIndex;
  }
  
  long absoluteOffset()
    throws SQLException
  {
/* 280 */     return this.index;
  }
  
  byte[] tds()
    throws SQLException
  {
/* 287 */     return this.tds;
  }
  
  boolean isJavaObject(int paramInt, byte paramByte)
  {
/* 296 */     return (paramInt >= 3) && ((paramByte & 0x2) != 0);
  }
  
  boolean isFinalType(int paramInt, byte paramByte)
  {
/* 304 */     return (paramInt >= 3) && ((paramByte & 0x1) == 0);
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 320 */     return null;
  }
  
/* 325 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/TDSReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */