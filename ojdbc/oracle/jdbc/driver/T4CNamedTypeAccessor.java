package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CNamedTypeAccessor
  extends NamedTypeAccessor
{
  static final int maxLength = Integer.MAX_VALUE;
/*  24 */   final int[] meta = new int[1];
  
  T4CMAREngine mare;
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  42 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  49 */       this.mare.unmarshalUB2();
/*  50 */       this.mare.unmarshalUB2();
    }
/*  52 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  56 */       this.mare.unmarshalSB2();
      
/*  58 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/*  59 */         this.mare.unmarshalSB2();
      }
/*  61 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/*  63 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  77 */     String str = super.getString(paramInt);
    
/*  79 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/*  81 */       str = str.substring(0, this.definedColumnSize);
    }
/*  83 */     return str;
  }
  
  T4CNamedTypeAccessor(OracleStatement paramOracleStatement, String paramString, short paramShort, int paramInt, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  93 */     super(paramOracleStatement, paramString, paramShort, paramInt, paramBoolean);
    
/*  95 */     this.mare = paramT4CMAREngine;
  }
  
  T4CNamedTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/* 105 */     super(paramOracleStatement, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/* 108 */     this.mare = paramT4CMAREngine;
/* 109 */     this.definedColumnType = paramInt7;
/* 110 */     this.definedColumnSize = paramInt8;
  }
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 126 */     if (this.isUseLess)
    {
/* 128 */       this.lastRowProcessed += 1;
      
/* 130 */       return false;
    }
    
/* 133 */     byte[] arrayOfByte1 = this.mare.unmarshalDALC();
    
/* 135 */     byte[] arrayOfByte2 = this.mare.unmarshalDALC();
    
/* 137 */     byte[] arrayOfByte3 = this.mare.unmarshalDALC();
    
/* 139 */     int i = this.mare.unmarshalUB2();
/* 140 */     long l = this.mare.unmarshalUB4();
/* 141 */     int j = this.mare.unmarshalUB2();
    
/* 143 */     byte[] arrayOfByte4 = null;
    
/* 145 */     if (l > 0L) {
/* 146 */       arrayOfByte4 = this.mare.unmarshalCLR((int)l, this.meta);
    } else {
/* 148 */       arrayOfByte4 = new byte[0];
    }
/* 150 */     this.pickledBytes[this.lastRowProcessed] = arrayOfByte4;
    
/* 152 */     processIndicator(this.meta[0]);
    
/* 155 */     int k = this.indicatorIndex + this.lastRowProcessed;
/* 156 */     int m = this.lengthIndex + this.lastRowProcessed;
    
/* 162 */     if (this.rowSpaceIndicator != null)
    {
/* 164 */       if (this.meta[0] == 0)
      {
/* 168 */         this.rowSpaceIndicator[k] = -1;
/* 169 */         this.rowSpaceIndicator[m] = 0;
      }
      else
      {
/* 173 */         this.rowSpaceIndicator[m] = ((short)this.meta[0]);
        
/* 178 */         this.rowSpaceIndicator[k] = 0;
      }
    }
    
/* 182 */     this.lastRowProcessed += 1;
/* 183 */     return false;
  }
  
/* 188 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CNamedTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */