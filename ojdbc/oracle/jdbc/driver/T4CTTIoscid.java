package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
final class T4CTTIoscid
  extends T4CTTIfun
{
  static final int KPDUSR_CID_RESET = 1;
  static final int KPDUSR_PROXY_RESET = 2;
  static final int KPDUSR_PROXY_TKTSENT = 4;
  static final int KPDUSR_MODULE_RESET = 8;
  static final int KPDUSR_ACTION_RESET = 16;
  static final int KPDUSR_EXECID_RESET = 32;
  static final int KPDUSR_EXECSQ_RESET = 64;
  static final int KPDUSR_COLLCT_RESET = 128;
  static final int KPDUSR_CLINFO_RESET = 256;
  
  T4CTTIoscid(T4CConnection paramT4CConnection)
  {
/* 115 */     super(paramT4CConnection, (byte)17);
/* 116 */     setFunCode((short)135);
  }
  
/* 119 */   private byte[] cidcid = null;
/* 120 */   private byte[] cidmod = null;
/* 121 */   private byte[] cidact = null;
/* 122 */   private byte[] cideci = null;
  
/* 124 */   private boolean[] endToEndHasChanged = null;
/* 125 */   private String[] endToEndValues = null;
  
  private int endToEndECIDSequenceNumber;
  
  void doOSCID(boolean[] paramArrayOfBoolean, String[] paramArrayOfString, int paramInt)
    throws IOException, SQLException
  {
/* 132 */     this.endToEndHasChanged = paramArrayOfBoolean;
/* 133 */     this.endToEndValues = paramArrayOfString;
/* 134 */     this.endToEndECIDSequenceNumber = paramInt;
    
/* 136 */     if (this.endToEndValues[1] != null) {
/* 137 */       this.cidcid = this.meg.conv.StringToCharBytes(this.endToEndValues[1]);
    }
    else {
/* 140 */       this.cidcid = null;
    }
/* 142 */     if (this.endToEndValues[3] != null) {
/* 143 */       this.cidmod = this.meg.conv.StringToCharBytes(this.endToEndValues[3]);
    }
    else {
/* 146 */       this.cidmod = null;
    }
/* 148 */     if (this.endToEndValues[0] != null) {
/* 149 */       this.cidact = this.meg.conv.StringToCharBytes(this.endToEndValues[0]);
    }
    else {
/* 152 */       this.cidact = null;
    }
/* 154 */     if (this.endToEndValues[2] != null) {
/* 155 */       this.cideci = this.meg.conv.StringToCharBytes(this.endToEndValues[2]);
    }
    else
/* 158 */       this.cideci = null;
/* 159 */     doPigRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 167 */     int i = 64;
    
/* 169 */     if (this.endToEndHasChanged[0] != 0) {
/* 170 */       i |= 0x10;
    }
/* 172 */     if (this.endToEndHasChanged[1] != 0) {
/* 173 */       i |= 0x1;
    }
/* 175 */     if (this.endToEndHasChanged[2] != 0) {
/* 176 */       i |= 0x20;
    }
/* 178 */     if (this.endToEndHasChanged[3] != 0) {
/* 179 */       i |= 0x8;
    }
    
/* 183 */     this.meg.marshalNULLPTR();
/* 184 */     this.meg.marshalNULLPTR();
/* 185 */     this.meg.marshalUB4(i);
    
/* 187 */     int j = 0;int k = 0;
/* 188 */     int m = 0;int n = 0;
    
/* 190 */     if (this.endToEndHasChanged[1] != 0)
    {
/* 192 */       this.meg.marshalPTR();
      
/* 194 */       if (this.cidcid != null) {
/* 195 */         this.meg.marshalUB4(this.cidcid.length);
      } else
/* 197 */         this.meg.marshalUB4(0L);
/* 198 */       j = 1;
    }
    else
    {
/* 202 */       this.meg.marshalNULLPTR();
/* 203 */       this.meg.marshalUB4(0L);
    }
    
/* 207 */     if (this.endToEndHasChanged[3] != 0)
    {
/* 209 */       this.meg.marshalPTR();
      
/* 211 */       if (this.cidmod != null) {
/* 212 */         this.meg.marshalUB4(this.cidmod.length);
      } else
/* 214 */         this.meg.marshalUB4(0L);
/* 215 */       k = 1;
    }
    else
    {
/* 219 */       this.meg.marshalNULLPTR();
/* 220 */       this.meg.marshalUB4(0L);
    }
    
/* 223 */     if (this.endToEndHasChanged[0] != 0)
    {
/* 225 */       this.meg.marshalPTR();
/* 226 */       if (this.cidact != null) {
/* 227 */         this.meg.marshalUB4(this.cidact.length);
      } else
/* 229 */         this.meg.marshalUB4(0L);
/* 230 */       m = 1;
    }
    else
    {
/* 234 */       this.meg.marshalNULLPTR();
/* 235 */       this.meg.marshalUB4(0L);
    }
    
/* 238 */     if (this.endToEndHasChanged[2] != 0)
    {
/* 240 */       this.meg.marshalPTR();
      
/* 242 */       if (this.cideci != null) {
/* 243 */         this.meg.marshalUB4(this.cideci.length);
      } else
/* 245 */         this.meg.marshalUB4(0L);
/* 246 */       n = 1;
    }
    else
    {
/* 250 */       this.meg.marshalNULLPTR();
/* 251 */       this.meg.marshalUB4(0L);
    }
    
/* 254 */     this.meg.marshalUB2(0);
/* 255 */     this.meg.marshalUB2(this.endToEndECIDSequenceNumber);
/* 256 */     this.meg.marshalNULLPTR();
/* 257 */     this.meg.marshalUB4(0L);
/* 258 */     this.meg.marshalNULLPTR();
/* 259 */     this.meg.marshalUB4(0L);
/* 260 */     this.meg.marshalNULLPTR();
/* 261 */     this.meg.marshalUB4(0L);
    
/* 264 */     if ((j != 0) && (this.cidcid != null)) {
/* 265 */       this.meg.marshalCHR(this.cidcid);
    }
/* 267 */     if ((k != 0) && (this.cidmod != null)) {
/* 268 */       this.meg.marshalCHR(this.cidmod);
    }
/* 270 */     if ((m != 0) && (this.cidact != null)) {
/* 271 */       this.meg.marshalCHR(this.cidact);
    }
/* 273 */     if ((n != 0) && (this.cideci != null)) {
/* 274 */       this.meg.marshalCHR(this.cideci);
    }
  }
  
/* 278 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoscid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */