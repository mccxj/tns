package oracle.jdbc.xa;
import java.sql.SQLException;
import javax.transaction.xa.XAException;
import oracle.jdbc.internal.OracleConnection;
public class OracleXAException
  extends XAException
{
/*  29 */   private int xaError = 0;
/*  30 */   private int primary = 0;
/*  31 */   private int secondary = 0;
  
  public OracleXAException() {}
  
  public OracleXAException(int paramInt)
  {
/*  46 */     super(errorConvert(paramInt));
    
/*  50 */     this.xaError = errorConvert(paramInt);
/*  51 */     this.primary = (paramInt & 0xFFFF);
/*  52 */     this.secondary = (paramInt >> 16);
  }
  
  public OracleXAException(int paramInt1, int paramInt2)
  {
/*  59 */     super(errorConvert(paramInt1, paramInt2));
    
/*  63 */     this.xaError = errorConvert(paramInt1, paramInt2);
/*  64 */     this.primary = (paramInt1 & 0xFFFF);
/*  65 */     this.secondary = (paramInt1 >> 16);
  }
  
  public OracleXAException(SQLException paramSQLException, int paramInt)
  {
/*  73 */     this(paramSQLException.getErrorCode(), paramInt);
/*  74 */     initCause(paramSQLException);
  }
  
  public static XAException newXAException(OracleConnection paramOracleConnection, int paramInt)
  {
/*  89 */     OracleXAException localOracleXAException = new OracleXAException(paramInt);
    
/*  91 */     int i = localOracleXAException.getXAError();
/*  92 */     if (i == -7)
    {
/*  94 */       if (paramOracleConnection != null)
      {
/*  96 */         paramOracleConnection.setUsable(false);
      }
    }
    
/* 100 */     return localOracleXAException;
  }
  
  public static XAException newXAException(OracleConnection paramOracleConnection, int paramInt1, int paramInt2)
  {
/* 118 */     OracleXAException localOracleXAException = new OracleXAException(paramInt1, paramInt2);
    
/* 120 */     int i = localOracleXAException.getXAError();
/* 121 */     if (i == -7)
    {
/* 123 */       if (paramOracleConnection != null)
      {
/* 125 */         paramOracleConnection.setUsable(false);
      }
    }
    
/* 129 */     return localOracleXAException;
  }
  
  public static XAException newXAException(OracleConnection paramOracleConnection, SQLException paramSQLException, int paramInt)
  {
/* 147 */     OracleXAException localOracleXAException = new OracleXAException(paramSQLException, paramInt);
    
/* 149 */     int i = localOracleXAException.getXAError();
/* 150 */     if (i == -7)
    {
/* 152 */       if (paramOracleConnection != null)
      {
/* 154 */         paramOracleConnection.setUsable(false);
      }
    }
    
/* 158 */     return localOracleXAException;
  }
  
  public static int errorConvert(int paramInt)
  {
/* 169 */     return errorConvert(paramInt, -3);
  }
  
  public static int errorConvert(int paramInt1, int paramInt2)
  {
/* 177 */     switch (paramInt1 & 0xFFFF)
    {
    case 24756: 
/* 181 */       return -4;
    
    case 25351: 
    case 30006: 
/* 185 */       return 4;
    
    case 24764: 
/* 188 */       return 7;
    
    case 24765: 
/* 191 */       return 6;
    
    case 24766: 
/* 194 */       return 5;
    
    case 24767: 
/* 197 */       return 3;
    
    case 28: 
    case 1031: 
    case 1033: 
    case 1034: 
    case 1041: 
    case 1089: 
    case 1090: 
    case 1092: 
    case 3113: 
    case 3114: 
    case 12571: 
    case 17002: 
    case 17008: 
    case 17410: 
    case 24796: 
    case 25400: 
    case 25401: 
    case 25402: 
    case 25403: 
    case 25404: 
    case 25405: 
    case 25406: 
    case 25407: 
    case 25408: 
    case 25409: 
/* 224 */       return -7;
    
    case 2056: 
    case 17448: 
    case 24763: 
    case 24768: 
    case 24769: 
    case 24770: 
    case 24775: 
    case 24776: 
/* 236 */       return -6;
    
    case 2091: 
    case 2092: 
    case 24761: 
/* 241 */       return 100;
    }
    
/* 244 */     return paramInt2;
  }
  
  public int getXAError()
  {
/* 261 */     return this.xaError;
  }
  
  public int getOracleError()
  {
/* 276 */     return this.primary;
  }
  
  public int getOracleSQLError()
  {
/* 292 */     return this.secondary;
  }
  
  public static String getXAErrorMessage(int paramInt)
  {
/* 301 */     switch (paramInt)
    {
    case 7: 
/* 305 */       return "The transaction branch has been heuristically committed.";
    
    case 8: 
/* 308 */       return "The transaction branch may have been heuristically completed.";
    
    case 5: 
/* 311 */       return "The transaction branch has been heuristically committed and rolled back.";
    
    case 6: 
/* 315 */       return "The transaction branch has been heuristically rolled back.";
    
    case 9: 
/* 318 */       return "Resumption must occur where suspension occured.";
    
    case 100: 
/* 321 */       return "The inclusive lower bound oof the rollback codes.";
    
    case 101: 
/* 324 */       return "Rollback was caused by communication failure.";
    
    case 102: 
/* 327 */       return "A deadlock was detected.";
    
    case 107: 
/* 330 */       return "The inclusive upper bound of the rollback error code.";
    
    case 103: 
/* 333 */       return "A condition that violates the integrity of the resource was detected.";
    
    case 104: 
/* 337 */       return "The resource manager rolled back the transaction branch for a reason not on this list.";
    
    case 105: 
/* 341 */       return "A protocol error occured in the resource manager.";
    
    case 106: 
/* 344 */       return "A transaction branch took too long.";
    
    case 3: 
/* 347 */       return "The transaction branch has been read-only and has been committed.";
    
    case 4: 
/* 350 */       return "Routine returned with no effect and may be reissued.";
    
    case -2: 
/* 353 */       return "Asynchronous operation already outstanding.";
    
    case -8: 
/* 356 */       return "The XID already exists.";
    
    case -5: 
/* 359 */       return "Invalid arguments were given.";
    
    case -4: 
/* 362 */       return "The XID is not valid.";
    
    case -9: 
/* 365 */       return "The resource manager is doing work outside global transaction.";
    
    case -6: 
/* 368 */       return "Routine was invoked in an inproper context.";
    
    case -3: 
/* 371 */       return "A resource manager error has occured in the transaction branch.";
    
    case -7: 
/* 374 */       return "Resource manager is unavailable.";
    }
    
/* 377 */     return "Internal XA Error";
  }
  
/* 383 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/xa/OracleXAException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */