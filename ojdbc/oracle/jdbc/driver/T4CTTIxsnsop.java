package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleConnection.XSOperationCode;
import oracle.jdbc.internal.XSNamespace;
class T4CTTIxsnsop
  extends T4CTTIfun
{
  private OracleConnection.XSOperationCode operationCode;
  private byte[] sessionId;
  private XSNamespace[] namespaces;
  private XSNamespace[] outNamespaces;
  
  T4CTTIxsnsop(T4CConnection paramT4CConnection)
  {
/*  65 */     super(paramT4CConnection, (byte)3);
    
/*  67 */     setFunCode((short)172);
  }
  
  void doOXSNS(OracleConnection.XSOperationCode paramXSOperationCode, byte[] paramArrayOfByte, XSNamespace[] paramArrayOfXSNamespace, boolean paramBoolean)
    throws IOException, SQLException
  {
/*  85 */     if (paramBoolean) {
/*  86 */       setTTCCode((byte)3);
    } else
/*  88 */       setTTCCode((byte)17);
/*  89 */     this.operationCode = paramXSOperationCode;
/*  90 */     this.sessionId = paramArrayOfByte;
/*  91 */     this.namespaces = paramArrayOfXSNamespace;
    
/*  93 */     if (this.namespaces != null) {
/*  94 */       for (int i = 0; i < this.namespaces.length; i++)
/*  95 */         ((XSNamespaceI)this.namespaces[i]).doCharConversion(this.meg.conv);
    }
/*  97 */     if (paramBoolean) {
/*  98 */       doRPC();
    } else {
/* 100 */       doPigRPC();
    }
  }
  
  void marshal()
    throws IOException
  {
/* 108 */     this.meg.marshalUB4(this.operationCode.getCode());
/* 109 */     int i = 0;
/* 110 */     if ((this.sessionId != null) && (this.sessionId.length > 0))
    {
/* 112 */       i = 1;
/* 113 */       this.meg.marshalPTR();
/* 114 */       this.meg.marshalUB4(this.sessionId.length);
    }
    else
    {
/* 118 */       this.meg.marshalNULLPTR();
/* 119 */       this.meg.marshalUB4(0L);
    }
    
/* 122 */     int j = 0;
/* 123 */     this.meg.marshalPTR();
/* 124 */     if ((this.namespaces != null) && (this.namespaces.length > 0))
    {
/* 126 */       j = 1;
/* 127 */       this.meg.marshalUB4(this.namespaces.length);
    }
    else
    {
/* 131 */       this.meg.marshalUB4(0L);
    }
/* 133 */     this.meg.marshalPTR();
    
/* 135 */     if (i != 0)
/* 136 */       this.meg.marshalB1Array(this.sessionId);
/* 137 */     if (j != 0) {
/* 138 */       for (int k = 0; k < this.namespaces.length; k++) {
/* 139 */         ((XSNamespaceI)this.namespaces[k]).marshal(this.meg);
      }
    }
  }
  
  void readRPA()
    throws SQLException, IOException
  {
/* 148 */     this.outNamespaces = null;
/* 149 */     int i = (int)this.meg.unmarshalUB4();
/* 150 */     if (i > 0)
    {
/* 152 */       this.outNamespaces = new XSNamespace[i];
/* 153 */       for (int j = 0; j < i; j++) {
/* 154 */         this.outNamespaces[j] = XSNamespaceI.unmarshal(this.meg);
      }
    }
  }
  
  XSNamespace[] getNamespaces()
    throws SQLException
  {
/* 162 */     return this.outNamespaces;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 177 */     return this.connection;
  }
  
/* 182 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIxsnsop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */