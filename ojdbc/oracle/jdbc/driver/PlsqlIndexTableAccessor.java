package oracle.jdbc.driver;
import java.math.BigDecimal;
import java.sql.SQLException;
import oracle.sql.CHAR;
import oracle.sql.CharacterSet;
import oracle.sql.Datum;
import oracle.sql.NUMBER;
class PlsqlIndexTableAccessor
  extends Accessor
{
  int elementInternalType;
  int maxNumberOfElements;
  int elementMaxLen;
  int ibtValueIndex;
  int ibtIndicatorIndex;
  int ibtLengthIndex;
  int ibtMetaIndex;
  int ibtByteLength;
  int ibtCharLength;
  
  PlsqlIndexTableAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, boolean paramBoolean)
    throws SQLException
  {
/*  44 */     init(paramOracleStatement, 998, 998, paramShort, paramBoolean);
/*  45 */     this.elementInternalType = paramInt2;
/*  46 */     this.maxNumberOfElements = paramInt4;
    
/*  51 */     this.elementMaxLen = paramInt3;
    
/*  54 */     initForDataAccess(paramInt1, paramInt3, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  62 */     if (paramInt1 != 0)
/*  63 */       this.externalType = paramInt1;
    SQLException localSQLException;
/*  65 */     switch (this.elementInternalType)
    {
    case 1: 
    case 96: 
/*  71 */       this.internalTypeMaxLength = ((OraclePreparedStatement)this.statement).maxIbtVarcharElementLength;
      
/*  74 */       if (paramInt2 > this.internalTypeMaxLength)
      {
/*  76 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 53);
/*  77 */         localSQLException.fillInStackTrace();
/*  78 */         throw localSQLException;
      }
      
/*  84 */       this.elementMaxLen = ((paramInt2 == 0 ? this.internalTypeMaxLength : paramInt2) + 1);
      
/*  89 */       this.ibtCharLength = (this.elementMaxLen * this.maxNumberOfElements);
      
/*  91 */       this.elementInternalType = 9;
      
/*  93 */       break;
    
    case 6: 
/*  96 */       this.internalTypeMaxLength = 21;
/*  97 */       this.elementMaxLen = (this.internalTypeMaxLength + 1);
/*  98 */       this.ibtByteLength = (this.elementMaxLen * this.maxNumberOfElements);
      
/* 100 */       break;
    
    default: 
/* 104 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 97);
/* 105 */       localSQLException.fillInStackTrace();
/* 106 */       throw localSQLException;
    }
    
  }
  
  Object[] getPlsqlIndexTable(int paramInt)
    throws SQLException
  {
/* 124 */     Object localObject = null;
/* 125 */     short[] arrayOfShort = this.statement.ibtBindIndicators;
/* 126 */     int i = ((arrayOfShort[(this.ibtMetaIndex + 4)] & 0xFFFF) << 16) + (arrayOfShort[(this.ibtMetaIndex + 5)] & 0xFFFF);
    
/* 129 */     int j = this.ibtValueIndex;
    
/* 132 */     switch (this.elementInternalType)
    {
    case 9: 
/* 136 */       localObject = new String[i];
/* 137 */       char[] arrayOfChar = this.statement.ibtBindChars;
      
/* 139 */       for (int k = 0; k < i; k++)
      {
/* 141 */         if (arrayOfShort[(this.ibtIndicatorIndex + k)] == -1)
        {
/* 143 */           localObject[k] = null;
        }
        else
        {
/* 147 */           localObject[k] = new String(arrayOfChar, j + 1, arrayOfChar[j] >> '\001');
        }
        
/* 152 */         j += this.elementMaxLen;
      }
      
/* 155 */       break;
    
    case 6: 
/* 158 */       localObject = new BigDecimal[i];
/* 159 */       byte[] arrayOfByte1 = this.statement.ibtBindBytes;
      
/* 161 */       for (int m = 0; m < i; m++)
      {
/* 163 */         if (arrayOfShort[(this.ibtIndicatorIndex + m)] == -1)
        {
/* 165 */           localObject[m] = null;
        }
        else
        {
/* 169 */           int n = arrayOfByte1[j];
/* 170 */           byte[] arrayOfByte2 = new byte[n];
          
/* 172 */           System.arraycopy(arrayOfByte1, j + 1, arrayOfByte2, 0, n);
          
/* 174 */           localObject[m] = NUMBER.toBigDecimal(arrayOfByte2);
        }
        
/* 177 */         j += this.elementMaxLen;
      }
      
/* 180 */       break;
    
    default: 
/* 184 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 97);
/* 185 */       localSQLException.fillInStackTrace();
/* 186 */       throw localSQLException;
    }
    
    
/* 190 */     return (Object[])localObject;
  }
  
  Datum[] getOraclePlsqlIndexTable(int paramInt)
    throws SQLException
  {
/* 206 */     Object localObject = null;
/* 207 */     short[] arrayOfShort = this.statement.ibtBindIndicators;
/* 208 */     int i = ((arrayOfShort[(this.ibtMetaIndex + 4)] & 0xFFFF) << 16) + (arrayOfShort[(this.ibtMetaIndex + 5)] & 0xFFFF);
    
/* 211 */     int j = this.ibtValueIndex;
    
    int m;
/* 214 */     switch (this.elementInternalType)
    {
    case 9: 
/* 218 */       localObject = new CHAR[i];
      
/* 220 */       CharacterSet localCharacterSet = CharacterSet.make(2000);
/* 221 */       char[] arrayOfChar = this.statement.ibtBindChars;
      
/* 223 */       for (int k = 0; k < i; k++)
      {
/* 225 */         if (arrayOfShort[(this.ibtIndicatorIndex + k)] == -1)
        {
/* 227 */           localObject[k] = null;
        }
        else
        {
/* 231 */           m = arrayOfChar[j];
/* 232 */           byte[] arrayOfByte2 = new byte[m];
          
/* 234 */           DBConversion.javaCharsToUcs2Bytes(arrayOfChar, j + 1, arrayOfByte2, 0, m >> 1);
          
/* 237 */           localObject[k] = new CHAR(arrayOfByte2, localCharacterSet);
        }
        
/* 240 */         j += this.elementMaxLen;
      }
      
/* 243 */       break;
    
    case 6: 
/* 246 */       localObject = new NUMBER[i];
/* 247 */       byte[] arrayOfByte1 = this.statement.ibtBindBytes;
      
/* 249 */       for (m = 0; m < i; m++)
      {
/* 251 */         if (arrayOfShort[(this.ibtIndicatorIndex + m)] == -1)
        {
/* 253 */           localObject[m] = null;
        }
        else
        {
/* 257 */           int n = arrayOfByte1[j];
/* 258 */           byte[] arrayOfByte3 = new byte[n];
          
/* 260 */           System.arraycopy(arrayOfByte1, j + 1, arrayOfByte3, 0, n);
          
/* 262 */           localObject[m] = new NUMBER(arrayOfByte3);
        }
        
/* 265 */         j += this.elementMaxLen;
      }
      
/* 268 */       break;
    
    default: 
/* 272 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 97);
/* 273 */       localSQLException.fillInStackTrace();
/* 274 */       throw localSQLException;
    }
    
    
/* 278 */     return (Datum[])localObject;
  }
  
/* 283 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/PlsqlIndexTableAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */