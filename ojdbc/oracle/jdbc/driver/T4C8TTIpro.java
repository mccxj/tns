package oracle.jdbc.driver;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
class T4C8TTIpro
  extends T4CTTIMsg
{
  short svrCharSet;
  short svrCharSetElem;
  byte svrFlags;
  byte[] proSvrStr;
  short proSvrVer;
/*  67 */   short oVersion = -1;
  
/*  69 */   boolean svrInfoAvailable = false;
  
/*  72 */   byte[] proCliVerTTC8 = { 6, 5, 4, 3, 2, 1, 0 };
  
/*  78 */   byte[] proCliStrTTC8 = { 74, 97, 118, 97, 95, 84, 84, 67, 45, 56, 46, 50, 46, 48, 0 };
  
/*  85 */   short NCHAR_CHARSET = 0;
  
/*  87 */   byte[] runtimeCapabilities = null;
  
  T4C8TTIpro(T4CConnection paramT4CConnection)
    throws SQLException, IOException
  {
/*  98 */     super(paramT4CConnection, (byte)1);
  }
  
  byte[] receive()
    throws SQLException, IOException
  {
    SQLException localSQLException;
    
/* 129 */     if (this.meg.unmarshalUB1() != 1)
    {
/* 131 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 132 */       localSQLException.fillInStackTrace();
/* 133 */       throw localSQLException;
    }
    
/* 136 */     this.proSvrVer = this.meg.unmarshalUB1();
/* 137 */     this.meg.proSvrVer = this.proSvrVer;
    
/* 142 */     switch (this.proSvrVer)
    {
    case 4: 
/* 146 */       this.oVersion = 7230;
      
/* 148 */       break;
    
    case 5: 
/* 152 */       this.oVersion = 8030;
      
/* 154 */       break;
    
    case 6: 
/* 157 */       this.oVersion = 8100;
      
/* 159 */       break;
    
    default: 
/* 163 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 444);
/* 164 */       localSQLException.fillInStackTrace();
/* 165 */       throw localSQLException;
    }
    
    
/* 169 */     this.meg.unmarshalUB1();
    
/* 171 */     this.proSvrStr = this.meg.unmarshalTEXT(50);
/* 172 */     this.oVersion = getOracleVersion();
    
/* 175 */     this.svrCharSet = ((short)this.meg.unmarshalUB2());
/* 176 */     this.svrFlags = ((byte)this.meg.unmarshalUB1());
    
/* 179 */     if ((this.svrCharSetElem = (short)this.meg.unmarshalUB2()) > 0)
    {
/* 185 */       this.meg.unmarshalNBytes(this.svrCharSetElem * 5);
    }
    
/* 188 */     this.svrInfoAvailable = true;
    
/* 191 */     if (this.proSvrVer < 5) {
/* 192 */       return null;
    }
    
/* 198 */     byte b = this.meg.types.getRep((byte)1);
/* 199 */     this.meg.types.setRep((byte)1, (byte)0);
/* 200 */     int i = this.meg.unmarshalUB2();
    
/* 202 */     this.meg.types.setRep((byte)1, b);
    
/* 204 */     byte[] arrayOfByte1 = this.meg.unmarshalNBytes(i);
    
/* 207 */     int j = 6 + (arrayOfByte1[5] & 0xFF) + (arrayOfByte1[6] & 0xFF);
    
/* 209 */     this.NCHAR_CHARSET = ((short)((arrayOfByte1[(j + 3)] & 0xFF) << 8));
/* 210 */     this.NCHAR_CHARSET = ((short)(this.NCHAR_CHARSET | (short)(arrayOfByte1[(j + 4)] & 0xFF)));
    
/* 212 */     if (this.proSvrVer < 6) {
/* 213 */       return null;
    }
    
/* 217 */     int k = this.meg.unmarshalUB1();
/* 218 */     byte[] arrayOfByte2 = new byte[k];
/* 219 */     for (int m = 0; m < k; m++) {
/* 220 */       arrayOfByte2[m] = ((byte)this.meg.unmarshalUB1());
    }
    
/* 223 */     k = this.meg.unmarshalUB1();
/* 224 */     if (k > 0)
    {
/* 226 */       this.runtimeCapabilities = new byte[k];
/* 227 */       for (m = 0; m < k; m++) {
/* 228 */         this.runtimeCapabilities[m] = ((byte)this.meg.unmarshalUB1());
      }
    }
/* 231 */     return arrayOfByte2;
  }
  
  short getOracleVersion()
  {
/* 243 */     return this.oVersion;
  }
  
  byte[] getServerRuntimeCapabilities()
  {
/* 250 */     return this.runtimeCapabilities;
  }
  
  short getCharacterSet()
  {
/* 262 */     return this.svrCharSet;
  }
  
  short getncharCHARSET()
  {
/* 269 */     return this.NCHAR_CHARSET;
  }
  
  byte getFlags()
  {
/* 281 */     return this.svrFlags;
  }
  
  void marshal()
    throws SQLException, IOException
  {
/* 288 */     marshalTTCcode();
    
/* 290 */     this.meg.marshalB1Array(this.proCliVerTTC8);
/* 291 */     this.meg.marshalB1Array(this.proCliStrTTC8);
  }
  
  void printServerInfo()
  {
/* 305 */     if (this.svrInfoAvailable)
    {
/* 307 */       int i = 0;
      
/* 312 */       StringWriter localStringWriter = new StringWriter();
      
/* 314 */       localStringWriter.write("Protocol string  =");
      
/* 316 */       while (i < this.proSvrStr.length) {
/* 317 */         localStringWriter.write((char)this.proSvrStr[(i++)]);
      }
    }
  }
  
/* 334 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIpro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */