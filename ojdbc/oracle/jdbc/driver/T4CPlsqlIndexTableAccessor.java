package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CPlsqlIndexTableAccessor
  extends PlsqlIndexTableAccessor
{
  T4CMAREngine mare;
  
  T4CPlsqlIndexTableAccessor(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, int paramInt4, short paramShort, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  43 */     super(paramOracleStatement, paramInt1, paramInt2, paramInt3, paramInt4, paramShort, paramBoolean);
    
/*  46 */     calculateSizeTmpByteArray();
    
/*  48 */     this.mare = paramT4CMAREngine;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  58 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  65 */       this.mare.unmarshalUB2();
/*  66 */       this.mare.unmarshalUB2();
    }
/*  68 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  72 */       this.mare.unmarshalSB2();
      
/*  74 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  75 */         this.mare.unmarshalSB2();
      }
/*  77 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/*  79 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
/*  87 */   final int[] meta = new int[1];
/*  88 */   final int[] tmp = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 110 */     if (this.isUseLess)
    {
/* 112 */       this.lastRowProcessed += 1;
      
/* 114 */       return false;
    }
    
/* 119 */     if (this.rowSpaceIndicator == null)
    {
/* 123 */       byte[] arrayOfByte1 = new byte['㺀'];
      
/* 125 */       this.mare.unmarshalCLR(arrayOfByte1, 0, this.meta);
/* 126 */       processIndicator(this.meta[0]);
      
/* 128 */       this.lastRowProcessed += 1;
      
/* 130 */       return false;
    }
    
/* 133 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 134 */     int j = this.lengthIndex + this.lastRowProcessed;
/* 135 */     byte[] arrayOfByte2 = this.statement.ibtBindBytes;
/* 136 */     char[] arrayOfChar = this.statement.ibtBindChars;
/* 137 */     short[] arrayOfShort = this.statement.ibtBindIndicators;
    
/* 141 */     if (this.isNullByDescribe)
    {
/* 143 */       this.rowSpaceIndicator[i] = -1;
/* 144 */       this.rowSpaceIndicator[j] = 0;
/* 145 */       this.lastRowProcessed += 1;
      
/* 147 */       if (this.statement.connection.versionNumber < 9200)
/* 148 */         processIndicator(0);
/* 149 */       return false;
    }
    
/* 152 */     int k = (int)this.mare.unmarshalUB4();
    
/* 154 */     arrayOfShort[(this.ibtMetaIndex + 4)] = ((short)((k & 0xFFFF0000) >> 16 & 0xFFFF));
    
/* 156 */     arrayOfShort[(this.ibtMetaIndex + 5)] = ((short)(k & 0xFFFF));
    int n;
/* 158 */     if ((this.elementInternalType == 9) || (this.elementInternalType == 96) || (this.elementInternalType == 1))
    {
/* 165 */       byte[] arrayOfByte3 = this.statement.tmpByteArray;
      
/* 167 */       for (n = 0; n < k; n++)
      {
/* 169 */         int i1 = this.ibtValueIndex + this.elementMaxLen * n;
        
/* 171 */         this.mare.unmarshalCLR(arrayOfByte3, 0, this.meta);
        
/* 173 */         this.tmp[0] = this.meta[0];
        
/* 175 */         int i2 = this.statement.connection.conversion.CHARBytesToJavaChars(arrayOfByte3, 0, arrayOfChar, i1 + 1, this.tmp, arrayOfChar.length - i1 - 1);
        
/* 180 */         arrayOfChar[i1] = ((char)(i2 * 2));
        
/* 184 */         processIndicator(this.meta[0]);
        
/* 186 */         if (this.meta[0] == 0)
        {
/* 190 */           arrayOfShort[(this.ibtIndicatorIndex + n)] = -1;
/* 191 */           arrayOfShort[(this.ibtLengthIndex + n)] = 0;
        }
        else
        {
/* 195 */           arrayOfShort[(this.ibtLengthIndex + n)] = ((short)(this.meta[0] * 2));
          
/* 199 */           arrayOfShort[(this.ibtIndicatorIndex + n)] = 0;
        }
        
      }
      
    }
    else
    {
/* 207 */       for (int m = 0; m < k; m++)
      {
/* 209 */         n = this.ibtValueIndex + this.elementMaxLen * m;
        
/* 211 */         this.mare.unmarshalCLR(arrayOfByte2, n + 1, this.meta);
        
/* 213 */         arrayOfByte2[n] = ((byte)this.meta[0]);
        
/* 215 */         processIndicator(this.meta[0]);
        
/* 217 */         if (this.meta[0] == 0)
        {
/* 221 */           arrayOfShort[(this.ibtIndicatorIndex + m)] = -1;
/* 222 */           arrayOfShort[(this.ibtLengthIndex + m)] = 0;
        }
        else
        {
/* 226 */           arrayOfShort[(this.ibtLengthIndex + m)] = ((short)this.meta[0]);
/* 227 */           arrayOfShort[(this.ibtIndicatorIndex + m)] = 0;
        }
      }
    }
    
/* 232 */     this.lastRowProcessed += 1;
    
/* 234 */     return false;
  }
  
  void calculateSizeTmpByteArray()
  {
/* 252 */     if ((this.elementInternalType == 9) || (this.elementInternalType == 96) || (this.elementInternalType == 1))
    {
/* 256 */       int i = this.ibtCharLength * this.statement.connection.conversion.cMaxCharSize / this.maxNumberOfElements;
      
/* 260 */       if (this.statement.sizeTmpByteArray < i) {
/* 261 */         this.statement.sizeTmpByteArray = i;
      }
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 276 */     String str = super.getString(paramInt);
    
/* 278 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 280 */       str = str.substring(0, this.definedColumnSize);
    }
/* 282 */     return str;
  }
  
/* 288 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CPlsqlIndexTableAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */