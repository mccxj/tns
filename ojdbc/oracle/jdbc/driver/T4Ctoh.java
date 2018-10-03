package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
class T4Ctoh
{
  static final int TOPLVL_KPCTOH = 1;
  static final int SERBEG_KPCTOH = 2;
  static final int SEREND_KPCTOH = 4;
  static final int SERONE_KPCTOH = 8;
  static final int NEW_KPCTOH = 16;
  static final int UPDATE_KPCTOH = 32;
  static final int DELETE_KPCTOH = 64;
  static final int LAST_KPCTOH = 128;
  static final int NOOBJ_KPCTOH = 256;
  static final int NNO_KPCTOH = 512;
  static final int RAWSTR_KPCTOH = 1024;
/*  73 */   static final byte[] EOID_KOTTD = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1 };
  
  static final byte KORFPFNNL = 2;
  
  static final byte EXTENT_OID = 8;
  
  static final int DONE_KPCTOC = 0;
  
  static final int MORE_KPCTOC = -1;
  
  static final int IGNORE_KPCTOC = -2;
  
  static final int KOLRUG_ENABLE = 1;
  
/*  93 */   static final byte[] ANYDATA_TOID = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 17 };
  
  static final int KOIDFLEN = 16;
  
  static final int KOIDSLEN = 8;
  
/* 101 */   byte[] toid = null;
/* 102 */   byte[] oid = null;
/* 103 */   byte[] snapshot = null;
/* 104 */   int versionNumber = 0;
/* 105 */   int imageLength = 0;
/* 106 */   int flags = 0;
  
/* 108 */   int[] intArr = new int[1];
  
  void init(byte[] paramArrayOfByte, int paramInt)
  {
/* 120 */     if ((this.toid == null) || (this.toid.length != 36)) {
/* 121 */       this.toid = new byte[36];
    }
/* 123 */     this.toid[0] = 0;
/* 124 */     this.toid[1] = 36;
    
/* 126 */     this.toid[2] = 2;
/* 127 */     this.toid[3] = 8;
    
/* 129 */     System.arraycopy(paramArrayOfByte, 0, this.toid, 4, 16);
    
/* 131 */     System.arraycopy(EOID_KOTTD, 0, this.toid, 20, 16);
/* 132 */     this.imageLength = paramInt;
    
/* 134 */     this.oid = null;
/* 135 */     this.snapshot = null;
/* 136 */     this.versionNumber = 0;
/* 137 */     this.flags = 1;
  }
  
  void marshal(T4CMAREngine paramT4CMAREngine)
    throws IOException
  {
/* 146 */     if (this.toid == null) {
/* 147 */       paramT4CMAREngine.marshalUB4(0L);
    }
    else {
/* 150 */       paramT4CMAREngine.marshalUB4(this.toid.length);
/* 151 */       paramT4CMAREngine.marshalCLR(this.toid, 0, this.toid.length);
    }
/* 153 */     if (this.oid == null) {
/* 154 */       paramT4CMAREngine.marshalUB4(0L);
    }
    else {
/* 157 */       paramT4CMAREngine.marshalUB4(this.oid.length);
/* 158 */       paramT4CMAREngine.marshalCLR(this.oid, 0, this.oid.length);
    }
/* 160 */     if (this.snapshot == null) {
/* 161 */       paramT4CMAREngine.marshalUB4(0L);
    }
    else {
/* 164 */       paramT4CMAREngine.marshalUB4(this.snapshot.length);
/* 165 */       paramT4CMAREngine.marshalCLR(this.snapshot, 0, this.snapshot.length);
    }
/* 167 */     paramT4CMAREngine.marshalUB2(this.versionNumber);
/* 168 */     paramT4CMAREngine.marshalUB4(this.imageLength);
/* 169 */     paramT4CMAREngine.marshalUB2(this.flags);
  }
  
  void unmarshal(T4CMAREngine paramT4CMAREngine)
    throws SQLException, IOException
  {
/* 178 */     int i = (int)paramT4CMAREngine.unmarshalUB4();
/* 179 */     if ((this.toid == null) || (this.toid.length != i))
/* 180 */       this.toid = new byte[i];
/* 181 */     if (i > 0) {
/* 182 */       paramT4CMAREngine.unmarshalCLR(this.toid, 0, this.intArr, i);
    }
/* 184 */     int j = (int)paramT4CMAREngine.unmarshalUB4();
/* 185 */     this.oid = new byte[j];
/* 186 */     if (j > 0) {
/* 187 */       paramT4CMAREngine.unmarshalCLR(this.oid, 0, this.intArr, j);
    }
/* 189 */     int k = (int)paramT4CMAREngine.unmarshalUB4();
/* 190 */     this.snapshot = new byte[k];
/* 191 */     if (k > 0) {
/* 192 */       paramT4CMAREngine.unmarshalCLR(this.snapshot, 0, this.intArr, k);
    }
/* 194 */     this.versionNumber = paramT4CMAREngine.unmarshalUB2();
/* 195 */     this.imageLength = ((int)paramT4CMAREngine.unmarshalUB4());
/* 196 */     this.flags = paramT4CMAREngine.unmarshalUB2();
  }
  
/* 200 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4Ctoh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */