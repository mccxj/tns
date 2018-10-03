package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
import oracle.sql.Datum;
class TimestampAccessor
  extends DateTimeCommonAccessor
{
  TimestampAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean)
    throws SQLException
  {
/*  28 */     init(paramOracleStatement, 180, 180, paramShort, paramBoolean);
/*  29 */     initForDataAccess(paramInt2, paramInt1, null);
  }
  
  TimestampAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort)
    throws SQLException
  {
/*  38 */     init(paramOracleStatement, 180, 180, paramShort, false);
/*  39 */     initForDescribe(180, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, null);
    
/*  41 */     initForDataAccess(0, paramInt1, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  49 */     if (paramInt1 != 0) {
/*  50 */       this.externalType = paramInt1;
    }
/*  52 */     this.internalTypeMaxLength = 11;
    
/*  54 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  55 */       this.internalTypeMaxLength = paramInt2;
    }
/*  57 */     this.byteLength = this.internalTypeMaxLength;
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  65 */     String str = null;
    
/*  67 */     if (this.rowSpaceIndicator == null)
    {
/*  71 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/*  72 */       localSQLException.fillInStackTrace();
/*  73 */       throw localSQLException;
    }
    
/*  79 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/*  81 */       int i = this.rowSpaceIndicator[(this.lengthIndex + paramInt)];
/*  82 */       int j = this.columnIndex + this.byteLength * paramInt;
/*  83 */       int k = oracleYear(j);
/*  84 */       int m = -1;
      
/*  86 */       if (i == 11)
      {
/*  88 */         m = oracleNanos(j);
      }
      
/*  91 */       int n = 0;
/*  92 */       str = toText(k, this.rowSpaceByte[(2 + j)], this.rowSpaceByte[(3 + j)], n = this.rowSpaceByte[(4 + j)] - 1, this.rowSpaceByte[(5 + j)] - 1, this.rowSpaceByte[(6 + j)] - 1, m, n < 12, null);
    }
    
/* 102 */     return str;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 110 */     Object localObject = null;
    SQLException localSQLException;
/* 112 */     if (this.rowSpaceIndicator == null)
    {
/* 116 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 117 */       localSQLException.fillInStackTrace();
/* 118 */       throw localSQLException;
    }
    
/* 123 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 125 */       if (this.externalType == 0)
      {
/* 127 */         if (this.statement.connection.j2ee13Compliant)
        {
/* 130 */           localObject = getTimestamp(paramInt);
        }
        else
        {
/* 134 */           localObject = getTIMESTAMP(paramInt);
        }
      }
      else
      {
/* 139 */         switch (this.externalType)
        {
        case 93: 
/* 142 */           return getTimestamp(paramInt);
        }
        
/* 145 */         localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 4);
        
/* 147 */         localSQLException.fillInStackTrace();
/* 148 */         throw localSQLException;
      }
    }
    
/* 154 */     return localObject;
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 162 */     return getTIMESTAMP(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 170 */     return getObject(paramInt);
  }
  
/* 175 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/TimestampAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */