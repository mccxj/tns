package oracle.jdbc.driver;
import java.sql.SQLException;
import oracle.jdbc.oracore.OracleType;
abstract class TypeAccessor
  extends Accessor
{
  byte[][] pickledBytes;
  
  abstract OracleType otypeFromName(String paramString)
    throws SQLException;
  
  void initForDescribe(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, short paramShort, String paramString)
    throws SQLException
  {
/*  32 */     this.describeTypeName = paramString;
    
/*  34 */     initForDescribe(paramInt1, paramInt2, paramBoolean, paramInt4, paramInt5, paramInt3, paramInt6, paramInt7, paramShort);
  }
  
  void setOffsets(int paramInt)
  {
/*  42 */     if (!this.outBind)
    {
/*  44 */       this.columnIndex = this.statement.defineByteSubRange;
/*  45 */       this.statement.defineByteSubRange = (this.columnIndex + paramInt * this.byteLength);
    }
    
/*  48 */     if ((this.pickledBytes == null) || (this.pickledBytes.length < paramInt)) {
/*  49 */       this.pickledBytes = new byte[paramInt][];
    }
  }
  
  byte[] pickledBytes(int paramInt)
  {
/*  57 */     return this.pickledBytes[paramInt];
  }
  
  void initForDataAccess(int paramInt1, int paramInt2, String paramString)
    throws SQLException
  {
/*  65 */     if (paramInt1 != 0) {
/*  66 */       this.externalType = paramInt1;
    }
/*  68 */     this.internalTypeMaxLength = 0;
/*  69 */     this.internalTypeName = paramString;
  }
  
  void initMetadata()
    throws SQLException
  {
/*  86 */     if ((this.describeOtype == null) && (this.describeTypeName != null)) {
/*  87 */       this.describeOtype = otypeFromName(this.describeTypeName);
    }
/*  89 */     if ((this.internalOtype == null) && (this.internalTypeName != null)) {
/*  90 */       this.internalOtype = otypeFromName(this.internalTypeName);
    }
  }
  
  byte[] getBytes(int paramInt)
    throws SQLException
  {
/*  97 */     byte[] arrayOfByte = null;
    Object localObject;
/*  99 */     if (this.rowSpaceIndicator == null)
    {
/* 103 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 21);
/* 104 */       ((SQLException)localObject).fillInStackTrace();
/* 105 */       throw ((Throwable)localObject);
    }
    
/* 111 */     if (this.rowSpaceIndicator[(this.indicatorIndex + paramInt)] != -1)
    {
/* 113 */       localObject = pickledBytes(paramInt);
/* 114 */       int i = localObject.length;
      
/* 116 */       arrayOfByte = new byte[i];
      
/* 118 */       System.arraycopy(localObject, 0, arrayOfByte, 0, i);
    }
    
/* 122 */     return arrayOfByte;
  }
  
  long updateChecksum(long paramLong, int paramInt)
    throws SQLException
  {
/* 129 */     byte[] arrayOfByte = pickledBytes(paramInt);
/* 130 */     if ((arrayOfByte == null) || (arrayOfByte.length == 0))
    {
/* 132 */       paramLong = CRC64.updateChecksum(paramLong, NULL_DATA_BYTES, 0, NULL_DATA_BYTES.length);
    }
    else
    {
/* 138 */       paramLong = CRC64.updateChecksum(paramLong, arrayOfByte, 0, arrayOfByte.length);
    }
    
/* 144 */     return paramLong;
  }
  
/* 149 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/TypeAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */