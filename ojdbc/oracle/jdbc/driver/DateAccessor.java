package oracle.jdbc.driver;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import oracle.sql.Datum;
class DateAccessor
  extends DateTimeCommonAccessor
{
  static final int maxLength = 7;
  
  DateAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  31 */     init(paramOracleStatement, 12, 12, paramShort, paramBoolean);
/*  32 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  DateAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  41 */     init(paramOracleStatement, 12, 12, paramShort, false);
/*  42 */     initForDescribe(12, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  44 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  52 */     if (paramInt1 != 0) {
/*  53 */       this.externalType = paramInt1;
    }
/*  55 */     this.internalTypeMaxLength = 7;
    
/*  57 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  58 */       this.internalTypeMaxLength = paramInt2;
    }
/*  60 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  68 */     String str = null;
    
/*  70 */     if (this.rowSpaceIndicator == null)
    {
/*  74 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  75 */       localSQLException.fillInStackTrace();
/*  76 */       throw localSQLException;
    }
    
/*  82 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  84 */       if (this.externalType == 0)
      {
/*  87 */         if (this.statement.connection.mapDateToTimestamp)
        {
/*  89 */           str = getTimestamp(paramInt).toString();
        }
        else
        {
/*  93 */           str = getDate(paramInt).toString();
        }
      }
      else
      {
/*  98 */         int i = this.columnIndex + this.byteLength * paramInt;
        
/* 100 */         int j = oracleYear(i);
/* 101 */         int k = 0;
/* 102 */         str = toText(j, this.rowSpaceByte[(2 + i)], this.rowSpaceByte[(3 + i)], k = this.rowSpaceByte[(4 + i)] - 1, this.rowSpaceByte[(5 + i)] - 1, this.rowSpaceByte[(6 + i)] - 1, -1, k < 12, null);
      }
    }
    
/* 114 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 122 */     Object localObject = null;
    SQLException localSQLException;
/* 124 */     if (this.rowSpaceIndicator == null)
    {
/* 128 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 129 */       localSQLException.fillInStackTrace();
/* 130 */       throw localSQLException;
    }
    
/* 135 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 137 */       if (this.externalType == 0)
      {
/* 140 */         if (this.statement.connection.mapDateToTimestamp) {
/* 141 */           localObject = getTimestamp(paramInt);
        } else {
/* 143 */           localObject = getDate(paramInt);
        }
      }
      else {
/* 147 */         switch (this.externalType)
        {
        case 91: 
/* 150 */           return getDate(paramInt);
        case 92: 
/* 152 */           return getTime(paramInt);
        case 93: 
/* 154 */           return getTimestamp(paramInt);
        }
        
/* 157 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
        
/* 159 */         localSQLException.fillInStackTrace();
/* 160 */         throw localSQLException;
      }
    }
    
/* 166 */     return localObject;
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 174 */     return getDATE(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 182 */     return getObject(paramInt);
  }
  
  static String toStr(int paramInt)
  {
/* 189 */     return paramInt < 10 ? "0" + paramInt : Integer.toString(paramInt);
  }
  
/* 194 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/DateAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */