package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.oracore.OracleTypeADT;
class T4CTTIoac
{
  static final short UACFIND = 1;
  static final short UACFALN = 2;
  static final short UACFRCP = 4;
  static final short UACFBBV = 8;
  static final short UACFNCP = 16;
  static final short UACFBLP = 32;
  static final short UACFARR = 64;
  static final short UACFIGN = 128;
  static final int UACFNSCL = 1;
  static final int UACFBUC = 2;
  static final int UACFSKP = 4;
  static final int UACFCHRCNT = 8;
  static final int UACFNOADJ = 16;
  static final int UACFCUS = 4096;
  static final int UACFLSZ = 33554432;
  static final int UACFVFSP = 134217728;
/*  44 */   static final byte[] NO_BYTES = new byte[0];
  
  int oaccsi;
  
  short oaccsfrm;
  
  static int maxBindArrayLength;
  
  T4CMAREngine meg;
  
  T4CConnection connection;
  
  short oacdty;
  
  short oacflg;
  
  short oacpre;
  
  short oacscl;
  
  int oacmxl;
  
  int oacmxlc;
  
  int oacmal;
  
  int oacfl2;
  
  byte[] oactoid;
  
  int oactoidl;
  
  int oacvsn;
  
  T4CTTIoac(T4CConnection paramT4CConnection)
  {
/*  81 */     this.connection = paramT4CConnection;
/*  82 */     this.meg = this.connection.mare;
    
/*  84 */     this.oactoid = NO_BYTES;
  }
  
  void init(short paramShort, int paramInt)
  {
/* 105 */     if ((paramShort == 9) || (paramShort == 1) || (paramShort == 996)) {
/* 106 */       this.oacdty = 1;
/* 107 */     } else if (paramShort == 104) {
/* 108 */       this.oacdty = 11;
/* 109 */     } else if ((paramShort == 6) || (paramShort == 2)) {
/* 110 */       this.oacdty = 2;
/* 111 */     } else if (paramShort == 15) {
/* 112 */       this.oacdty = 23;
/* 113 */     } else if (paramShort == 116) {
/* 114 */       this.oacdty = 102;
    }
    else
    {
/* 118 */       this.oacdty = paramShort;
    }
/* 120 */     if ((this.oacdty == 1) || (this.oacdty == 96))
    {
/* 122 */       this.oacfl2 = 16;
    }
/* 124 */     if (this.oacdty == 102) {
/* 125 */       this.oacmxl = 1;
    } else {
/* 127 */       this.oacmxl = paramInt;
    }
    
/* 130 */     this.oacflg = 3;
  }
  
  boolean isOldSufficient(T4CTTIoac paramT4CTTIoac)
  {
/* 138 */     boolean bool = false;
    
/* 140 */     if ((this.oactoidl != 0) || (paramT4CTTIoac.oactoidl != 0)) {
/* 141 */       return false;
    }
/* 143 */     if ((this.oaccsi == paramT4CTTIoac.oaccsi) && (this.oaccsfrm == paramT4CTTIoac.oaccsfrm) && (this.oacdty == paramT4CTTIoac.oacdty) && (this.oacflg == paramT4CTTIoac.oacflg) && (this.oacpre == paramT4CTTIoac.oacpre) && (this.oacscl <= paramT4CTTIoac.oacscl) && ((this.oacmxl == paramT4CTTIoac.oacmxl) || ((paramT4CTTIoac.oacmxl > this.oacmxl) && (paramT4CTTIoac.oacmxl < 4000))) && (this.oacmxlc == paramT4CTTIoac.oacmxlc) && (this.oacmal == paramT4CTTIoac.oacmal) && (this.oacfl2 == paramT4CTTIoac.oacfl2) && (this.oacvsn == paramT4CTTIoac.oacvsn))
    {
/* 150 */       bool = true;
    }
/* 152 */     return bool;
  }
  
  boolean isNType()
  {
/* 163 */     boolean bool = this.oaccsfrm == 2;
/* 164 */     return bool;
  }
  
  void unmarshal()
    throws IOException, SQLException
  {
/* 177 */     this.oacdty = this.meg.unmarshalUB1();
/* 178 */     this.oacflg = this.meg.unmarshalUB1();
/* 179 */     this.oacpre = this.meg.unmarshalUB1();
    
/* 183 */     this.oacscl = ((short)this.meg.unmarshalSB1());
/* 184 */     this.oacmxl = this.meg.unmarshalSB4();
/* 185 */     this.oacmal = this.meg.unmarshalSB4();
/* 186 */     this.oacfl2 = this.meg.unmarshalSB4();
/* 187 */     this.oactoid = this.meg.unmarshalDALC();
/* 188 */     this.oactoidl = (this.oactoid == null ? 0 : this.oactoid.length);
/* 189 */     this.oacvsn = this.meg.unmarshalUB2();
/* 190 */     this.oaccsi = this.meg.unmarshalUB2();
/* 191 */     this.oaccsfrm = this.meg.unmarshalUB1();
    
/* 193 */     if (this.connection.getTTCVersion() >= 2) {
/* 194 */       this.oacmxlc = ((int)this.meg.unmarshalUB4());
    }
    
/* 213 */     if (this.oacmxl > 0)
    {
/* 215 */       switch (this.oacdty)
      {
      case 2: 
/* 224 */         this.oacmxl = 22;
        
/* 226 */         break;
      
      case 12: 
/* 229 */         this.oacmxl = 7;
        
/* 231 */         break;
      
      case 181: 
/* 234 */         this.oacmxl = 13;
      }
      
    }
  }
  
  void setMal(int paramInt)
  {
/* 254 */     this.oacmal = paramInt;
  }
  
  void addFlg(short paramShort)
  {
/* 261 */     this.oacflg = ((short)(this.oacflg | paramShort));
  }
  
  void addFlg2(int paramInt)
  {
/* 268 */     this.oacfl2 |= paramInt;
  }
  
  void setFormOfUse(short paramShort)
  {
/* 275 */     this.oaccsfrm = paramShort;
  }
  
  void setCharset(int paramInt)
  {
/* 282 */     this.oaccsi = paramInt;
  }
  
  void setMxlc(int paramInt)
  {
/* 289 */     this.oacmxlc = paramInt;
  }
  
  void setPrecision(short paramShort)
  {
/* 296 */     this.oacpre = paramShort;
  }
  
  void setTimestampFractionalSecondsPrecision(short paramShort)
  {
/* 304 */     this.oacscl = paramShort;
  }
  
  void setADT(OracleTypeADT paramOracleTypeADT)
  {
/* 310 */     this.oactoid = paramOracleTypeADT.getTOID();
/* 311 */     this.oacvsn = paramOracleTypeADT.getTypeVersion();
/* 312 */     this.oaccsi = 2;
/* 313 */     this.oaccsfrm = ((short)paramOracleTypeADT.getCharSetForm());
  }
  
  void marshal()
    throws IOException
  {
/* 323 */     this.meg.marshalUB1(this.oacdty);
/* 324 */     this.meg.marshalUB1(this.oacflg);
/* 325 */     this.meg.marshalUB1(this.oacpre);
    
/* 327 */     if ((this.oacdty == 2) || (this.oacdty == 180) || (this.oacdty == 181) || (this.oacdty == 231) || (this.oacdty == 183))
    {
/* 333 */       this.meg.marshalUB2(this.oacscl);
    }
    else
    {
/* 337 */       this.meg.marshalUB1(this.oacscl);
    }
    
/* 340 */     this.meg.marshalUB4(this.oacmxl);
    
/* 342 */     this.meg.marshalSB4(this.oacmal);
/* 343 */     this.meg.marshalSB4(this.oacfl2);
    
/* 345 */     this.meg.marshalDALC(this.oactoid);
    
/* 347 */     this.meg.marshalUB2(this.oacvsn);
/* 348 */     this.meg.marshalUB2(this.oaccsi);
/* 349 */     this.meg.marshalUB1(this.oaccsfrm);
    
/* 351 */     if (this.connection.getTTCVersion() >= 2) {
/* 352 */       this.meg.marshalUB4(this.oacmxlc);
    }
  }
  
/* 372 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */