package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
final class T4C8Odscrarr
  extends T4CTTIfun
{
  private static final byte OPERATIONFLAGS = 7;
  private static final long SQLPARSEVERSION = 2L;
  byte[] sqltext;
  T4CTTIdcb dcb;
/*  46 */   int cursor_id = 0;
  
/*  49 */   int numuds = 0;
  
  private static final boolean UDSARRAYO2U = true;
  
  private static final boolean NUMUDSO2U = true;
  
/*  60 */   OracleStatement statement = null;
  
  private Accessor[] accessors;
  
  T4C8Odscrarr(T4CConnection paramT4CConnection)
  {
/*  71 */     super(paramT4CConnection, (byte)3);
    
/*  74 */     setFunCode((short)98);
/*  75 */     this.dcb = new T4CTTIdcb(paramT4CConnection);
  }
  
  void doODNY(OracleStatement paramOracleStatement, int paramInt, Accessor[] paramArrayOfAccessor, byte[] paramArrayOfByte)
    throws IOException, SQLException
  {
/*  86 */     this.numuds = 0;
/*  87 */     this.cursor_id = paramOracleStatement.cursorId;
/*  88 */     this.statement = paramOracleStatement;
    
/*  90 */     if ((paramArrayOfByte != null) && (paramArrayOfByte.length > 0)) {
/*  91 */       this.sqltext = paramArrayOfByte;
    } else {
/*  93 */       this.sqltext = PhysicalConnection.EMPTY_BYTE_ARRAY;
    }
/*  95 */     this.dcb.init(paramOracleStatement, paramInt);
/*  96 */     this.accessors = paramArrayOfAccessor;
/*  97 */     this.numuds = 0;
/*  98 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 121 */     this.meg.marshalUB1((short)7);
/* 122 */     this.meg.marshalSWORD(this.cursor_id);
    
/* 125 */     if (this.sqltext.length == 0) {
/* 126 */       this.meg.marshalNULLPTR();
    } else {
/* 128 */       this.meg.marshalPTR();
    }
/* 130 */     this.meg.marshalSB4(this.sqltext.length);
    
/* 132 */     this.meg.marshalUB4(2L);
    
/* 134 */     this.meg.marshalO2U(true);
/* 135 */     this.meg.marshalO2U(true);
    
/* 139 */     this.meg.marshalCHR(this.sqltext);
/* 140 */     this.sqltext = PhysicalConnection.EMPTY_BYTE_ARRAY;
  }
  
  Accessor[] getAccessors()
  {
/* 177 */     return this.accessors;
  }
  
  void readRPA()
    throws IOException, SQLException
  {
/* 184 */     this.accessors = this.dcb.receiveCommon(this.accessors, true);
/* 185 */     this.numuds = this.dcb.numuds;
  }
  
/* 190 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8Odscrarr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */