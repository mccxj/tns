package oracle.jdbc.oracore;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
public class TDSPatch
{
  static final int S_NORMAL_PATCH = 0;
  static final int S_SIMPLE_PATCH = 1;
  int typeId;
  OracleType owner;
  long position;
  int uptCode;
  
  public TDSPatch(int paramInt1, OracleType paramOracleType, long paramLong, int paramInt2)
    throws SQLException
  {
/*  33 */     this.typeId = paramInt1;
/*  34 */     this.owner = paramOracleType;
/*  35 */     this.position = paramLong;
/*  36 */     this.uptCode = paramInt2;
  }
  
  int getType()
    throws SQLException
  {
/*  43 */     return this.typeId;
  }
  
  OracleNamedType getOwner()
    throws SQLException
  {
/*  50 */     return (OracleNamedType)this.owner;
  }
  
  long getPosition()
    throws SQLException
  {
/*  57 */     return this.position;
  }
  
  byte getUptTypeCode()
    throws SQLException
  {
/*  64 */     return (byte)this.uptCode;
  }
  
  void apply(OracleType paramOracleType)
    throws SQLException
  {
/*  71 */     apply(paramOracleType, -1);
  }
  
  void apply(OracleType paramOracleType, int paramInt) throws SQLException
  {
    Object localObject;
    OracleNamedType localOracleNamedType;
/*  78 */     if (this.typeId == 0)
    {
/*  82 */       localObject = (OracleTypeUPT)this.owner;
      
/*  84 */       ((OracleTypeUPT)localObject).realType = ((OracleTypeADT)paramOracleType);
      
/*  87 */       if ((paramOracleType instanceof OracleNamedType))
      {
/*  89 */         localOracleNamedType = (OracleNamedType)paramOracleType;
        
/*  91 */         localOracleNamedType.setParent(((OracleTypeUPT)localObject).getParent());
/*  92 */         localOracleNamedType.setOrder(((OracleTypeUPT)localObject).getOrder());
      }
    }
/*  95 */     else if (this.typeId == 1)
    {
/*  99 */       localObject = (OracleTypeCOLLECTION)this.owner;
      
/* 101 */       ((OracleTypeCOLLECTION)localObject).opcode = paramInt;
/* 102 */       ((OracleTypeCOLLECTION)localObject).elementType = paramOracleType;
      
/* 105 */       if ((paramOracleType instanceof OracleNamedType))
      {
/* 107 */         localOracleNamedType = (OracleNamedType)paramOracleType;
        
/* 109 */         localOracleNamedType.setParent((OracleTypeADT)localObject);
/* 110 */         localOracleNamedType.setOrder(1);
      }
    }
    else
    {
/* 115 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 1);
/* 116 */       ((SQLException)localObject).fillInStackTrace();
/* 117 */       throw ((Throwable)localObject);
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 133 */     return null;
  }
  
/* 153 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/oracore/TDSPatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */