package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import oracle.jdbc.oracore.OracleTypeADT;
class T4CTTIiov
  extends T4CTTIMsg
{
  T4C8TTIrxh rxh;
  T4CTTIrxd rxd;
/*  40 */   short bindtype = 0;
  
  byte[] iovector;
/*  43 */   int bindcnt = 0;
/*  44 */   int inbinds = 0;
/*  45 */   int outbinds = 0;
  
  static final byte BV_IN_V = 32;
  
  static final byte BV_OUT_V = 16;
  
  T4CTTIiov(T4CConnection paramT4CConnection, T4C8TTIrxh paramT4C8TTIrxh, T4CTTIrxd paramT4CTTIrxd)
    throws SQLException, IOException
  {
/*  61 */     super(paramT4CConnection, (byte)0);
    
/*  63 */     this.rxh = paramT4C8TTIrxh;
/*  64 */     this.rxd = paramT4CTTIrxd;
  }
  
  void init()
    throws SQLException, IOException
  {}
  
  Accessor[] processRXD(Accessor[] paramArrayOfAccessor, int paramInt1, byte[] paramArrayOfByte1, char[] paramArrayOfChar1, short[] paramArrayOfShort1, int paramInt2, DBConversion paramDBConversion, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, InputStream[][] paramArrayOfInputStream, byte[][][] paramArrayOfByte, OracleTypeADT[][] paramArrayOfOracleTypeADT, OracleStatement paramOracleStatement, byte[] paramArrayOfByte4, char[] paramArrayOfChar2, short[] paramArrayOfShort2)
    throws SQLException, IOException
  {
/*  99 */     if (paramArrayOfByte3 != null)
    {
/* 101 */       for (int i = 0; i < paramArrayOfByte3.length; i++)
      {
/* 103 */         if (((paramArrayOfByte3[i] & 0x10) != 0) && ((paramArrayOfAccessor == null) || (paramArrayOfAccessor.length <= i) || (paramArrayOfAccessor[i] == null)))
        {
/* 110 */           int j = paramInt2 + 5 + 10 * i;
          
/* 115 */           int k = paramArrayOfShort1[(j + 0)] & 0xFFFF;
          
/* 118 */           int m = k;
          
/* 120 */           if (k == 9) {
/* 121 */             k = 1;
          }
/* 123 */           Accessor localAccessor = paramOracleStatement.allocateAccessor(k, k, i, 0, (short)0, null, false);
          
/* 132 */           localAccessor.rowSpaceIndicator = null;
          
/* 135 */           if ((localAccessor.defineType == 109) || (localAccessor.defineType == 111))
          {
/* 137 */             localAccessor.setOffsets(1);
          }
/* 139 */           if (paramArrayOfAccessor == null)
          {
/* 141 */             paramArrayOfAccessor = new Accessor[i + 1];
/* 142 */             paramArrayOfAccessor[i] = localAccessor;
          }
/* 144 */           else if (paramArrayOfAccessor.length <= i)
          {
/* 146 */             Accessor[] arrayOfAccessor = new Accessor[i + 1];
            
/* 148 */             arrayOfAccessor[i] = localAccessor;
            
/* 150 */             for (int n = 0; n < paramArrayOfAccessor.length; n++)
            {
/* 152 */               if (paramArrayOfAccessor[n] != null) {
/* 153 */                 arrayOfAccessor[n] = paramArrayOfAccessor[n];
              }
            }
/* 156 */             paramArrayOfAccessor = arrayOfAccessor;
          }
          else
          {
/* 162 */             paramArrayOfAccessor[i] = localAccessor;
          }
        }
/* 165 */         else if (((paramArrayOfByte3[i] & 0x10) == 0) && (paramArrayOfAccessor != null) && (i < paramArrayOfAccessor.length) && (paramArrayOfAccessor[i] != null))
        {
/* 171 */           paramArrayOfAccessor[i].isUseLess = true;
        }
      }
    }
    
/* 178 */     return paramArrayOfAccessor;
  }
  
  void unmarshalV10()
    throws IOException, SQLException
  {
/* 187 */     this.rxh.unmarshalV10(this.rxd);
    
/* 189 */     this.bindcnt = this.rxh.numRqsts;
    
/* 199 */     this.iovector = new byte[this.connection.all8.numberOfBindPositions];
    
/* 201 */     for (int i = 0; i < this.iovector.length; i++)
    {
/* 205 */       if ((this.bindtype = this.meg.unmarshalUB1()) == 0)
      {
/* 209 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 210 */         localSQLException.fillInStackTrace();
/* 211 */         throw localSQLException;
      }
      
/* 217 */       if ((this.bindtype & 0x20) > 0)
      {
/* 219 */         int tmp97_96 = i; byte[] tmp97_93 = this.iovector;tmp97_93[tmp97_96] = ((byte)(tmp97_93[tmp97_96] | 0x20));
/* 220 */         this.inbinds += 1;
      }
      
/* 223 */       if ((this.bindtype & 0x10) > 0)
      {
/* 225 */         int tmp129_128 = i; byte[] tmp129_125 = this.iovector;tmp129_125[tmp129_128] = ((byte)(tmp129_125[tmp129_128] | 0x10));
/* 226 */         this.outbinds += 1;
      }
    }
  }
  
  byte[] getIOVector()
  {
/* 244 */     return this.iovector;
  }
  
  boolean isIOVectorEmpty()
  {
/* 255 */     return this.iovector.length == 0;
  }
  
/* 260 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIiov.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */