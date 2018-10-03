package oracle.jdbc.driver;
import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Map;
import oracle.jdbc.util.RepConversion;
import oracle.sql.Datum;
import oracle.sql.RAW;
class RawCommonAccessor
  extends Accessor
{
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, short paramShort, int paramInt4)
    throws SQLException
  {
/*  32 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, false);
/*  33 */     initForDataAccess(paramInt4, paramInt3, null);
  }
  
  void init(OracleStatement paramOracleStatement, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, short paramShort)
    throws SQLException
  {
/*  42 */     init(paramOracleStatement, paramInt1, paramInt2, paramShort, false);
/*  43 */     initForDescribe(paramInt1, paramInt3, paramBoolean, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramShort, null);
    
/*  46 */     int i = paramOracleStatement.maxFieldSize;
    
/*  48 */     if ((i > 0) && ((paramInt3 == 0) || (i < paramInt3))) {
/*  49 */       paramInt3 = i;
    }
/*  51 */     initForDataAccess(0, paramInt3, null);
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  59 */     if (paramInt1 != 0) {
/*  60 */       this.externalType = paramInt1;
    }
/*  62 */     this.internalTypeMaxLength = Integer.MAX_VALUE;
    
/*  64 */     if ((paramInt2 > 0) && (paramInt2 < this.internalTypeMaxLength)) {
/*  65 */       this.internalTypeMaxLength = paramInt2;
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/*  87 */     byte[] arrayOfByte = getBytes(paramInt);
    
/*  89 */     if (arrayOfByte == null) {
/*  90 */       return null;
    }
/*  92 */     int i = arrayOfByte.length;
    
/*  94 */     if (i == 0) {
/*  95 */       return null;
    }
/*  97 */     return RepConversion.bArray2String(arrayOfByte);
  }
  
  InputStream getAsciiStream(int paramInt)
    throws SQLException
  {
/* 111 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 113 */     if (arrayOfByte == null) {
/* 114 */       return null;
    }
/* 116 */     PhysicalConnection localPhysicalConnection = this.statement.connection;
    
/* 118 */     return localPhysicalConnection.conversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 2);
  }
  
  InputStream getUnicodeStream(int paramInt)
    throws SQLException
  {
/* 133 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 135 */     if (arrayOfByte == null) {
/* 136 */       return null;
    }
/* 138 */     PhysicalConnection localPhysicalConnection = this.statement.connection;
    
/* 140 */     return localPhysicalConnection.conversion.ConvertStream(new ByteArrayInputStream(arrayOfByte), 3);
  }
  
  Reader getCharacterStream(int paramInt)
    throws SQLException
  {
/* 155 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 157 */     if (arrayOfByte == null) {
/* 158 */       return null;
    }
/* 160 */     int i = arrayOfByte.length;
    
/* 162 */     char[] arrayOfChar = new char[i << 1];
    
/* 164 */     int j = DBConversion.RAWBytesToHexChars(arrayOfByte, i, arrayOfChar);
    
/* 167 */     return new CharArrayReader(arrayOfChar, 0, j);
  }
  
  InputStream getBinaryStream(int paramInt)
    throws SQLException
  {
/* 181 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 183 */     if (arrayOfByte == null) {
/* 184 */       return null;
    }
/* 186 */     return new ByteArrayInputStream(arrayOfByte);
  }
  
  Object getObject(int paramInt)
    throws SQLException
  {
/* 200 */     return getBytes(paramInt);
  }
  
  Object getObject(int paramInt, Map paramMap)
    throws SQLException
  {
/* 215 */     return getBytes(paramInt);
  }
  
  Datum getOracleObject(int paramInt)
    throws SQLException
  {
/* 229 */     return getRAW(paramInt);
  }
  
  RAW getRAW(int paramInt)
    throws SQLException
  {
/* 243 */     byte[] arrayOfByte = getBytes(paramInt);
    
/* 245 */     if (arrayOfByte == null) {
/* 246 */       return null;
    }
/* 248 */     return new RAW(arrayOfByte);
  }
  
/* 253 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/RawCommonAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */