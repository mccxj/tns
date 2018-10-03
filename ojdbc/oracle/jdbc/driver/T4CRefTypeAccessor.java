package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CRefTypeAccessor
  extends RefTypeAccessor
{
  static final int maxLength = 4000;
  T4CMAREngine mare;
  
  T4CRefTypeAccessor(OracleStatement paramOracleStatement, String paramString, short paramShort, int paramInt, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  47 */     super(paramOracleStatement, paramString, paramShort, paramInt, paramBoolean);
    
/*  49 */     this.mare = paramT4CMAREngine;
/*  50 */     this.byteLength = 4000;
  }
  
  T4CRefTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  69 */     super(paramOracleStatement, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/*  80 */     this.mare = paramT4CMAREngine;
/*  81 */     this.definedColumnType = paramInt7;
/*  82 */     this.definedColumnSize = paramInt8;
/*  83 */     this.byteLength = 4000;
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  91 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  98 */       this.mare.unmarshalUB2();
/*  99 */       this.mare.unmarshalUB2();
    }
/* 101 */     else if (this.statement.connection.versionNumber < 9200)
    {
/* 105 */       this.mare.unmarshalSB2();
      
/* 107 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 108 */         this.mare.unmarshalSB2();
      }
/* 110 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 112 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 126 */     String str = super.getString(paramInt);
    
/* 128 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 130 */       str = str.substring(0, this.definedColumnSize);
    }
/* 132 */     return str;
  }
  
/* 140 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 155 */     if (this.isUseLess)
    {
/* 157 */       this.lastRowProcessed += 1;
      
/* 159 */       return false;
    }
    
/* 164 */     byte[] arrayOfByte = this.mare.unmarshalCLRforREFS();
    
/* 166 */     if (arrayOfByte == null) {
/* 167 */       arrayOfByte = new byte[0];
    }
/* 169 */     this.pickledBytes[this.lastRowProcessed] = arrayOfByte;
/* 170 */     this.meta[0] = arrayOfByte.length;
    
/* 172 */     processIndicator(this.meta[0]);
    
/* 175 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 176 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 178 */     if (this.meta[0] == 0)
    {
/* 182 */       this.rowSpaceIndicator[i] = -1;
/* 183 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 187 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
      
/* 192 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 195 */     this.lastRowProcessed += 1;
    
/* 197 */     return false;
  }
  
/* 202 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CRefTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */