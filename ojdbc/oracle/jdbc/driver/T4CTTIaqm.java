package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.sql.TIMESTAMP;
class T4CTTIaqm
{
  static final int ATTR_ORIGINAL_MSGID = 69;
  static final byte ATTR_AGENT_NAME = 64;
  static final byte ATTR_AGENT_ADDRESS = 65;
  static final byte ATTR_AGENT_PROTOCOL = 66;
  static final int AQM_MSG_NO_DELAY = 0;
  static final int AQM_MSG_NO_EXPIRATION = -1;
  static final int AQM_MSGPROP_CORRID_SIZE = 128;
  int aqmpri;
  int aqmdel;
  int aqmexp;
  byte[] aqmcorBytes;
  int aqmcorBytesLength;
  int aqmatt;
  byte[] aqmeqnBytes;
  int aqmeqnBytesLength;
  int aqmsta;
/* 106 */   private byte[] aqmeqtBuffer = new byte[7];
/* 107 */   private int[] retInt = new int[1];
  
  TIMESTAMP aqmeqt;
  
  byte[] aqmetiBytes;
  
/* 114 */   byte[] senderAgentName = null;
/* 115 */   int senderAgentNameLength = 0;
/* 116 */   byte[] senderAgentAddress = null;
/* 117 */   int senderAgentAddressLength = 0;
/* 118 */   byte senderAgentProtocol = 0;
  
  byte[] originalMsgId;
  
  T4Ctoh toh;
  
  int aqmcsn;
  
  int aqmdsn;
  int aqmflg;
  T4CMAREngine mar;
  T4CConnection connection;
  
  T4CTTIaqm(T4CConnection paramT4CConnection, T4Ctoh paramT4Ctoh)
  {
/* 133 */     this.toh = paramT4Ctoh;
/* 134 */     this.connection = paramT4CConnection;
/* 135 */     this.mar = this.connection.mare;
  }
  
  void initToDefaultValues()
  {
/* 142 */     this.aqmpri = 0;
/* 143 */     this.aqmdel = 0;
/* 144 */     this.aqmexp = -1;
/* 145 */     this.aqmcorBytes = null;
/* 146 */     this.aqmcorBytesLength = 0;
/* 147 */     this.aqmatt = 0;
/* 148 */     this.aqmeqnBytes = null;
/* 149 */     this.aqmeqnBytesLength = 0;
/* 150 */     this.aqmsta = 0;
/* 151 */     this.aqmeqt = null;
/* 152 */     this.aqmetiBytes = null;
/* 153 */     this.senderAgentName = null;
/* 154 */     this.senderAgentNameLength = 0;
/* 155 */     this.senderAgentAddress = null;
/* 156 */     this.senderAgentAddressLength = 0;
/* 157 */     this.senderAgentProtocol = 0;
/* 158 */     this.originalMsgId = null;
/* 159 */     this.aqmcsn = 0;
/* 160 */     this.aqmdsn = 0;
/* 161 */     this.aqmflg = 0;
  }
  
  void marshal()
    throws IOException
  {
/* 169 */     this.mar.marshalSB4(this.aqmpri);
/* 170 */     this.mar.marshalSB4(this.aqmdel);
/* 171 */     this.mar.marshalSB4(this.aqmexp);
/* 172 */     if ((this.aqmcorBytes != null) && (this.aqmcorBytes.length != 0))
    {
/* 174 */       this.mar.marshalSWORD(this.aqmcorBytes.length);
/* 175 */       this.mar.marshalCLR(this.aqmcorBytes, 0, this.aqmcorBytes.length);
    }
    else {
/* 178 */       this.mar.marshalSWORD(0);
    }
    
/* 181 */     this.mar.marshalSB4(0);
    
/* 183 */     if ((this.aqmeqnBytes != null) && (this.aqmeqnBytes.length != 0))
    {
/* 185 */       this.mar.marshalSWORD(this.aqmeqnBytes.length);
/* 186 */       this.mar.marshalCLR(this.aqmeqnBytes, 0, this.aqmeqnBytes.length);
    }
    else {
/* 189 */       this.mar.marshalSWORD(0);
    }
    
/* 192 */     this.mar.marshalSB4(this.aqmsta);
    
/* 195 */     this.mar.marshalSWORD(0);
    
/* 197 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 199 */       if ((this.aqmetiBytes != null) && (this.aqmetiBytes.length > 0))
      {
/* 201 */         this.mar.marshalSWORD(this.aqmetiBytes.length);
/* 202 */         this.mar.marshalCLR(this.aqmetiBytes, 0, this.aqmetiBytes.length);
      }
      else {
/* 205 */         this.mar.marshalSWORD(0);
      }
    }
/* 208 */     int i = 4;
/* 209 */     byte[][] arrayOfByte1 = new byte[i][];
/* 210 */     byte[][] arrayOfByte2 = new byte[i][];
/* 211 */     int[] arrayOfInt = new int[i];
/* 212 */     arrayOfByte1[0] = this.senderAgentName;
/* 213 */     arrayOfByte2[0] = null;
/* 214 */     arrayOfInt[0] = 64;
/* 215 */     arrayOfByte1[1] = this.senderAgentAddress;
/* 216 */     arrayOfByte2[1] = null;
/* 217 */     arrayOfInt[1] = 65;
/* 218 */     arrayOfByte1[2] = null;
/* 219 */     arrayOfByte2[2] = new byte[1];
/* 220 */     arrayOfByte2[2][0] = this.senderAgentProtocol;
/* 221 */     arrayOfInt[2] = 66;
    
/* 223 */     arrayOfByte1[3] = null;
/* 224 */     arrayOfByte2[3] = this.originalMsgId;
/* 225 */     arrayOfInt[3] = 69;
    
/* 228 */     this.mar.marshalSWORD(i);
    
/* 230 */     this.mar.marshalUB1((short)14);
    
/* 234 */     this.mar.marshalKPDKV(arrayOfByte1, arrayOfByte2, arrayOfInt);
    
/* 236 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 242 */       this.mar.marshalUB4(1L);
      
/* 244 */       this.toh.init(T4Ctoh.ANYDATA_TOID, 0);
/* 245 */       this.toh.marshal(this.mar);
      
/* 249 */       this.mar.marshalUB4(0L);
      
/* 251 */       this.mar.marshalUB4(0L);
      
/* 253 */       if (this.connection.getTTCVersion() >= 4)
      {
/* 257 */         this.mar.marshalUB4(0L);
      }
    }
  }
  
  void receive()
    throws SQLException, IOException
  {
/* 266 */     this.aqmpri = this.mar.unmarshalSB4();
/* 267 */     this.aqmdel = this.mar.unmarshalSB4();
/* 268 */     this.aqmexp = this.mar.unmarshalSB4();
/* 269 */     int i = this.mar.unmarshalSWORD();
/* 270 */     if (i > 0)
    {
/* 272 */       this.aqmcorBytes = new byte[i];
/* 273 */       int[] arrayOfInt1 = new int[1];
/* 274 */       this.mar.unmarshalCLR(this.aqmcorBytes, 0, arrayOfInt1, this.aqmcorBytes.length);
/* 275 */       this.aqmcorBytesLength = arrayOfInt1[0];
    }
    else {
/* 278 */       this.aqmcorBytes = null;
    }
/* 280 */     this.aqmatt = this.mar.unmarshalSB4();
/* 281 */     int j = this.mar.unmarshalSWORD();
/* 282 */     if (j > 0)
    {
/* 284 */       this.aqmeqnBytes = new byte[j];
/* 285 */       int[] arrayOfInt2 = new int[1];
/* 286 */       this.mar.unmarshalCLR(this.aqmeqnBytes, 0, arrayOfInt2, this.aqmeqnBytes.length);
/* 287 */       this.aqmeqnBytesLength = arrayOfInt2[0];
    }
    else {
/* 290 */       this.aqmeqnBytes = null;
    }
/* 292 */     this.aqmsta = this.mar.unmarshalSB4();
/* 293 */     int k = this.mar.unmarshalSB4();
/* 294 */     if (k > 0)
    {
/* 296 */       this.mar.unmarshalCLR(this.aqmeqtBuffer, 0, this.retInt, 7);
/* 297 */       this.aqmeqt = new TIMESTAMP(this.aqmeqtBuffer);
    }
    Object localObject;
/* 300 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 302 */       m = this.mar.unmarshalSWORD();
/* 303 */       if (m > 0)
      {
/* 305 */         this.aqmetiBytes = new byte[m];
/* 306 */         localObject = new int[1];
/* 307 */         this.mar.unmarshalCLR(this.aqmetiBytes, 0, (int[])localObject, this.aqmetiBytes.length);
      }
      else {
/* 310 */         this.aqmetiBytes = null;
      } }
/* 312 */     int m = this.mar.unmarshalSWORD();
/* 313 */     this.mar.unmarshalUB1();
/* 314 */     if (m > 0)
    {
/* 316 */       localObject = new byte[m][];
/* 317 */       int[] arrayOfInt3 = new int[m];
/* 318 */       byte[][] arrayOfByte = new byte[m][];
/* 319 */       int[] arrayOfInt4 = new int[m];
/* 320 */       this.mar.unmarshalKPDKV((byte[][])localObject, arrayOfInt3, arrayOfByte, arrayOfInt4);
      
/* 325 */       for (int i1 = 0; i1 < m; i1++)
      {
/* 327 */         if ((arrayOfInt4[i1] == 64) && (localObject[i1] != null) && (arrayOfInt3[i1] > 0))
        {
/* 331 */           this.senderAgentName = localObject[i1];
/* 332 */           this.senderAgentNameLength = arrayOfInt3[i1];
        }
        
/* 336 */         if ((arrayOfInt4[i1] == 65) && (localObject[i1] != null) && (arrayOfInt3[i1] > 0))
        {
/* 340 */           this.senderAgentAddress = localObject[i1];
/* 341 */           this.senderAgentAddressLength = arrayOfInt3[i1];
        }
        
/* 346 */         if ((arrayOfInt4[i1] == 66) && (arrayOfByte[i1] != null) && (arrayOfByte[i1].length > 0))
        {
/* 349 */           this.senderAgentProtocol = arrayOfByte[i1][0]; }
/* 350 */         if ((arrayOfInt4[i1] == 69) && (arrayOfByte[i1] != null) && (arrayOfByte[i1].length > 0))
        {
/* 353 */           this.originalMsgId = arrayOfByte[i1];
        }
      }
    }
    
/* 363 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 365 */       int n = this.mar.unmarshalSWORD();
      
/* 367 */       this.aqmcsn = ((int)this.mar.unmarshalUB4());
/* 368 */       this.aqmdsn = ((int)this.mar.unmarshalUB4());
/* 369 */       if (this.connection.getTTCVersion() >= 4)
      {
/* 376 */         this.aqmflg = ((int)this.mar.unmarshalUB4());
      }
    }
  }
  
/* 382 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIaqm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */