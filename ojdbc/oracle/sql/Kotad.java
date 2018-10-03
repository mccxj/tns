package oracle.sql;
import java.sql.SQLException;
import oracle.jdbc.driver.DatabaseError;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.oracore.PickleContext;
class Kotad
{
  static final int KOTADSIG = -1365573631;
  static final int KOTPDSIG = -1365442559;
  static final int KOTRDSIG = -1365377023;
  static final int KOTCDSIG = -1365311487;
  static final int KOTODSIG = -1365307391;
  static final int KOTADXSIG = -1365303295;
  static final int KOTADPRV = 1;
  static final int KOTADPUB = 2;
  static final int KOTADCNT = 4;
  static final int KOTADCFM = 248;
  static final int KOTADSUB = 256;
  static final int KOTADPTR = 16384;
  static final int KOTADREF = 32768;
  static final int KOTADCNN = 65536;
  static final int KOTADCFN = 131072;
  static final int KOTADCVN = 262144;
  static final int KOTADTRN = 512;
  static final int KOTADCPT = 4096;
  static final int KOTADIN = 256;
  static final int KOTADOUT = 512;
  static final int KOTADCBR = 1024;
  static final int KOTADREQ = 2048;
  static final int KOTADNCP = 1048576;
  private int kotadkvn;
  private byte[] kotadnam;
  private byte[] kotadtrf;
  private short kotadtvn;
  private short kotadid;
  private byte[] kotadprf;
  private short kotadpvn;
  private int kotadflg;
  private long kotadpre;
  private int kotadcid;
  private byte kotadscl;
  private int kotadcne;
  private byte[] kotaddft;
  private long kotadtyp;
  private byte[] kotadadd;
  
  private Kotad()
    throws SQLException
  {}
  
  private static Kotad unpickleKotad(PickleContext paramPickleContext)
    throws SQLException
  {
/* 139 */     Kotad localKotad = new Kotad();
/* 140 */     paramPickleContext.skipBytes(2);
/* 141 */     long l = paramPickleContext.readLength(true) - 2;
/* 142 */     paramPickleContext.skipBytes(1);
/* 143 */     localKotad.kotadkvn = ((int)paramPickleContext.readUB4());
/* 144 */     localKotad.kotadnam = paramPickleContext.readDataValue();
/* 145 */     localKotad.kotadtrf = paramPickleContext.readDataValue();
/* 146 */     paramPickleContext.skipBytes(1);
/* 147 */     localKotad.kotadtvn = ((short)paramPickleContext.readUB2());
/* 148 */     paramPickleContext.skipBytes(1);
/* 149 */     localKotad.kotadid = ((short)paramPickleContext.readUB2());
/* 150 */     localKotad.kotadprf = paramPickleContext.readDataValue();
/* 151 */     paramPickleContext.skipBytes(1);
/* 152 */     localKotad.kotadpvn = ((short)paramPickleContext.readUB2());
/* 153 */     paramPickleContext.skipBytes(1);
/* 154 */     localKotad.kotadflg = ((int)paramPickleContext.readUB4());
/* 155 */     paramPickleContext.skipBytes(1);
/* 156 */     localKotad.kotadpre = paramPickleContext.readUB4();
/* 157 */     paramPickleContext.skipBytes(1);
/* 158 */     localKotad.kotadcid = paramPickleContext.readUB2();
/* 159 */     paramPickleContext.skipBytes(1);
/* 160 */     localKotad.kotadscl = paramPickleContext.readByte();
/* 161 */     paramPickleContext.skipBytes(1);
/* 162 */     localKotad.kotadcne = ((int)paramPickleContext.readUB4());
/* 163 */     localKotad.kotaddft = paramPickleContext.readDataValue();
/* 164 */     paramPickleContext.skipBytes(1);
/* 165 */     localKotad.kotadtyp = paramPickleContext.readUB4();
/* 166 */     localKotad.kotadadd = paramPickleContext.readDataValue();
/* 167 */     return localKotad;
  }
  
  static final TypeDescriptor unpickleTypeDescriptorImage(PickleContext paramPickleContext)
    throws SQLException
  {
/* 177 */     Kotad localKotad = unpickleKotad(paramPickleContext);
/* 178 */     if (localKotad.kotadkvn != -1365311487)
    {
/* 182 */       localObject = DatabaseError.createSqlException(null, 179);
/* 183 */       ((SQLException)localObject).fillInStackTrace();
/* 184 */       throw ((Throwable)localObject);
    }
    
/* 188 */     Object localObject = constructPredefinedTypeDescriptor(localKotad);
/* 189 */     return (TypeDescriptor)localObject;
  }
  
  static final AttributeDescriptor unpickleAttributeImage(boolean paramBoolean, PickleContext paramPickleContext)
    throws SQLException
  {
/* 200 */     Kotad localKotad = unpickleKotad(paramPickleContext);
/* 201 */     if (localKotad.kotadkvn != -1365573631)
    {
/* 205 */       localObject = DatabaseError.createSqlException(null, 179);
/* 206 */       ((SQLException)localObject).fillInStackTrace();
/* 207 */       throw ((Throwable)localObject);
    }
    
/* 211 */     Object localObject = null;
    
/* 213 */     if (paramBoolean) {
/* 214 */       localObject = constructPredefinedTypeDescriptor(localKotad);
    }
/* 216 */     AttributeDescriptor localAttributeDescriptor = new AttributeDescriptor(new String(localKotad.kotadnam), localKotad.kotadid, localKotad.kotadflg, (TypeDescriptor)localObject);
    
/* 221 */     return localAttributeDescriptor;
  }
  
  private static final TypeDescriptor constructPredefinedTypeDescriptor(Kotad paramKotad)
    throws SQLException
  {
/* 229 */     if (paramKotad.kotadtrf.length != 36)
    {
/* 233 */       SQLException localSQLException = DatabaseError.createSqlException(null, 180);
/* 234 */       localSQLException.fillInStackTrace();
/* 235 */       throw localSQLException;
    }
    
/* 242 */     for (short s = 4; s < 18; s++)
    {
/* 244 */       if (paramKotad.kotadtrf[s] != 0)
      {
/* 248 */         localObject = DatabaseError.createSqlException(null, 180);
/* 249 */         ((SQLException)localObject).fillInStackTrace();
/* 250 */         throw ((Throwable)localObject);
      }
    }
    
/* 254 */     s = TypeDescriptor.OID_TO_TYPECODE[paramKotad.kotadtrf[19]];
/* 255 */     Object localObject = new TypeDescriptor(s);
/* 256 */     ((TypeDescriptor)localObject).setPrecision(paramKotad.kotadpre);
/* 257 */     ((TypeDescriptor)localObject).setScale(paramKotad.kotadscl);
/* 258 */     return (TypeDescriptor)localObject;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 273 */     return null;
  }
  
/* 278 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/Kotad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */