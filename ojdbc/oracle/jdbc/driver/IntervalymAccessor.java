package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.Datum;
import oracle.sql.INTERVALYM;
class IntervalymAccessor
  extends Accessor
{
  static final int maxLength = 5;
  
  IntervalymAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  27 */     init(paramOracleStatement, 182, 182, paramShort, paramBoolean);
/*  28 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  IntervalymAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  37 */     init(paramOracleStatement, 182, 182, paramShort, false);
/*  38 */     initForDescribe(182, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  40 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  48 */     if (paramInt1 != 0) {
/*  49 */       this.externalType = paramInt1;
    }
/*  51 */     this.internalTypeMaxLength = 5;
    
/*  53 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  54 */       this.internalTypeMaxLength = paramInt2;
    }
/*  56 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  77 */     String str = null;
    
/*  79 */     if (this.rowSpaceIndicator == null)
    {
/*  83 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  84 */       localSQLException.fillInStackTrace();
/*  85 */       throw localSQLException;
    }
    
/*  91 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  93 */       int i = this.columnIndex + this.byteLength * paramInt;
/*  94 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
      
/*  96 */       byte[] arrayOfByte = new byte[j];
/*  97 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
      
/*  99 */       str = new INTERVALYM(arrayOfByte).toString();
    }
    
/* 102 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 118 */     return getINTERVALYM(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 141 */     return getINTERVALYM(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 158 */     return getINTERVALYM(paramInt);
  }
  
  INTERVALYM getINTERVALYM(int paramInt)
    throws SQLException
  {
/* 176 */     INTERVALYM localINTERVALYM = null;
    
/* 178 */     if (this.rowSpaceIndicator == null)
    {
/* 182 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 183 */       localSQLException.fillInStackTrace();
/* 184 */       throw localSQLException;
    }
    
/* 190 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 192 */       int i = this.columnIndex + this.byteLength * paramInt;
/* 193 */       int j = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
      
/* 195 */       byte[] arrayOfByte = new byte[j];
      
/* 197 */       System.arraycopy(this.rowSpaceByte, i, arrayOfByte, 0, j);
      
/* 199 */       localINTERVALYM = new INTERVALYM(arrayOfByte);
    }
    
/* 202 */     return localINTERVALYM;
  }
  
/* 207 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/IntervalymAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */