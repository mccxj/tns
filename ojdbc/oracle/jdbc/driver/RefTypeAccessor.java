package oracle.jdbc.driver;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.oracore.OracleType;
import oracle.jdbc.oracore.OracleTypeADT;
import oracle.sql.Datum;
import oracle.sql.REF;
import oracle.sql.StructDescriptor;
import oracle.sql.TypeDescriptor;
class RefTypeAccessor
  extends TypeAccessor
{
  RefTypeAccessor(OracleStatement paramOracleStatement, String paramString, short paramShort, int paramInt, boolean paramBoolean)
    throws SQLException
  {
/*  29 */     init(paramOracleStatement, 111, 111, paramShort, paramBoolean);
/*  30 */     initForDataAccess(paramInt, 0, paramString);
  }
  
  RefTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString)
    throws SQLException
  {
/*  40 */     init(paramOracleStatement, 111, 111, paramShort, false);
/*  41 */     initForDescribe(111, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/*  43 */     initForDataAccess(0, paramInt1, paramString);
  }
  
  RefTypeAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, String paramString, OracleType paramOracleType)
    throws SQLException
  {
/*  53 */     init(paramOracleStatement, 111, 111, paramShort, false);
    
/*  55 */     this.describeOtype = paramOracleType;
    
/*  57 */     initForDescribe(111, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort, paramString);
    
/*  60 */     this.internalOtype = paramOracleType;
    
/*  62 */     initForDataAccess(0, paramInt1, paramString);
  }
  
  OracleType otypeFromName(String paramString)
    throws SQLException
  {
/*  69 */     if (!this.outBind) {
/*  70 */       return TypeDescriptor.getTypeDescriptor(paramString, this.statement.connection).getPickler();
    }
    
/*  73 */     return StructDescriptor.createDescriptor(paramString, this.statement.connection).getOracleTypeADT();
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  82 */     super.initForDataAccess(paramInt1, paramInt2, paramString);
    
/*  84 */     this.byteLength = this.statement.connection.refTypeAccessorByteLen;
  }
  
  REF getREF(int paramInt)
    throws SQLException
  {
/* 100 */     REF localREF = null;
    Object localObject;
/* 102 */     if (this.rowSpaceIndicator == null)
    {
/* 106 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 107 */       ((SQLException)localObject).fillInStackTrace();
/* 108 */       throw ((Throwable)localObject);
    }
    
/* 114 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 116 */       localObject = pickledBytes(paramInt);
/* 117 */       OracleTypeADT localOracleTypeADT = (OracleTypeADT)this.internalOtype;
      
/* 119 */       localREF = new REF(localOracleTypeADT.getFullName(), this.statement.connection, (byte[])localObject);
    }
    
/* 122 */     return localREF;
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 130 */     return getREF(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 138 */     return getREF(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 146 */     return getREF(paramInt);
  }
  
/* 151 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/RefTypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */