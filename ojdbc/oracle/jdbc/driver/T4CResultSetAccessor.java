package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleStatement.SqlKind;
class T4CResultSetAccessor
  extends ResultSetAccessor
{
  T4CMAREngine mare;
/*  24 */   OracleStatement[] newstmt = new OracleStatement[10];
/*  25 */   byte[] empty = { 0 };
  
/*  43 */   boolean underlyingLongRaw = false;
  
  T4CResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, short paramShort, int paramInt2, boolean paramBoolean, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  49 */     super(paramOracleStatement, paramInt1, paramShort, paramInt2, paramBoolean);
    
/*  51 */     this.mare = paramT4CMAREngine;
  }
  
  T4CResultSetAccessor(OracleStatement paramOracleStatement, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, short paramShort, int paramInt7, int paramInt8, T4CMAREngine paramT4CMAREngine)
    throws SQLException
  {
/*  60 */     super(paramOracleStatement, paramInt1 == -1 ? paramInt8 : paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramShort);
    
/*  63 */     this.mare = paramT4CMAREngine;
    
/*  65 */     if ((paramOracleStatement != null) && (paramOracleStatement.implicitDefineForLobPrefetchDone))
    {
/*  67 */       this.definedColumnType = 0;
/*  68 */       this.definedColumnSize = 0;
    }
    else
    {
/*  72 */       this.definedColumnType = paramInt7;
/*  73 */       this.definedColumnSize = paramInt8;
    }
    
/*  76 */     if (paramInt1 == -1) {
/*  77 */       this.underlyingLongRaw = true;
    }
  }
  
  void processIndicator(int paramInt)
    throws IOException, SQLException
  {
/*  84 */     if (((this.internalType == 1) && (this.describeType == 112)) || ((this.internalType == 23) && (this.describeType == 113)))
    {
/*  91 */       this.mare.unmarshalUB2();
/*  92 */       this.mare.unmarshalUB2();
    }
/*  94 */     else if (this.statement.connection.versionNumber < 9200)
    {
/*  98 */       this.mare.unmarshalSB2();
      
/* 100 */       if (!this.statement.sqlKind.isPlsqlOrCall()) {
/* 101 */         this.mare.unmarshalSB2();
      }
/* 103 */     } else if ((this.statement.sqlKind.isPlsqlOrCall()) || (this.isDMLReturnedParam))
    {
/* 105 */       this.mare.processIndicator(paramInt <= 0, paramInt);
    }
  }
  
  String getString(int paramInt)
    throws SQLException
  {
/* 119 */     String str = super.getString(paramInt);
    
/* 121 */     if ((str != null) && (this.definedColumnSize > 0) && (str.length() > this.definedColumnSize))
    {
/* 123 */       str = str.substring(0, this.definedColumnSize);
    }
/* 125 */     return str;
  }
  
/* 133 */   final int[] meta = new int[1];
  
  boolean unmarshalOneRow()
    throws SQLException, IOException
  {
/* 148 */     if (this.isUseLess)
    {
/* 150 */       this.lastRowProcessed += 1;
      
/* 152 */       return false;
    }
    
/* 157 */     if (this.rowSpaceIndicator == null)
    {
/* 161 */       byte[] arrayOfByte = new byte['㺀'];
      
/* 163 */       this.mare.unmarshalCLR(arrayOfByte, 0, this.meta);
/* 164 */       processIndicator(this.meta[0]);
      
/* 166 */       this.lastRowProcessed += 1;
      
/* 168 */       return false;
    }
    
/* 172 */     int i = this.indicatorIndex + this.lastRowProcessed;
/* 173 */     int j = this.lengthIndex + this.lastRowProcessed;
    
/* 175 */     if (this.isNullByDescribe)
    {
/* 177 */       this.rowSpaceIndicator[i] = -1;
/* 178 */       this.rowSpaceIndicator[j] = 0;
/* 179 */       this.lastRowProcessed += 1;
      
/* 181 */       processIndicator(0);
      
/* 183 */       return false;
    }
    
/* 186 */     int k = this.columnIndex + this.lastRowProcessed * this.byteLength;
    
/* 188 */     if (this.newstmt.length <= this.lastRowProcessed)
    {
/* 190 */       localObject = new OracleStatement[this.newstmt.length * 4];
      
/* 192 */       System.arraycopy(this.newstmt, 0, localObject, 0, this.newstmt.length);
      
/* 194 */       this.newstmt = ((OracleStatement[])localObject);
    }
    
/* 197 */     this.newstmt[this.lastRowProcessed] = this.statement.connection.RefCursorBytesToStatement(this.empty, this.statement);
    
/* 199 */     this.newstmt[this.lastRowProcessed].needToSendOalToFetch = true;
    
/* 201 */     Object localObject = new T4CTTIdcb((T4CConnection)this.statement.connection);
    
/* 203 */     ((T4CTTIdcb)localObject).init(this.newstmt[this.lastRowProcessed], 0);
    
/* 205 */     this.newstmt[this.lastRowProcessed].accessors = ((T4CTTIdcb)localObject).receiveFromRefCursor(this.newstmt[this.lastRowProcessed].accessors);
    
/* 207 */     this.newstmt[this.lastRowProcessed].numberOfDefinePositions = this.newstmt[this.lastRowProcessed].accessors.length;
    
/* 209 */     this.newstmt[this.lastRowProcessed].describedWithNames = true;
/* 210 */     this.newstmt[this.lastRowProcessed].described = true;
    
/* 212 */     int m = (int)this.mare.unmarshalUB4();
    
/* 214 */     this.newstmt[this.lastRowProcessed].setCursorId(m);
    
/* 216 */     if (m > 0)
    {
/* 218 */       this.rowSpaceByte[k] = 1;
/* 219 */       this.rowSpaceByte[(k + 1)] = ((byte)m);
      
/* 223 */       this.meta[0] = 2;
    }
    else
    {
/* 227 */       this.newstmt[this.lastRowProcessed].close();
      
/* 229 */       this.newstmt[this.lastRowProcessed] = null;
/* 230 */       this.meta[0] = 0;
    }
    
/* 235 */     processIndicator(this.meta[0]);
    
/* 237 */     if (this.meta[0] == 0)
    {
/* 241 */       this.rowSpaceIndicator[i] = -1;
/* 242 */       this.rowSpaceIndicator[j] = 0;
    }
    else
    {
/* 246 */       this.rowSpaceIndicator[j] = ((short)this.meta[0]);
/* 247 */       this.rowSpaceIndicator[i] = 0;
    }
    
/* 250 */     this.lastRowProcessed += 1;
    
/* 252 */     return false;
  }
  
  void copyRow()
    throws SQLException, IOException
  {
    int i;
    
/* 262 */     if (this.lastRowProcessed == 0) {
/* 263 */       i = this.statement.rowPrefetchInLastFetch - 1;
    } else {
/* 265 */       i = this.lastRowProcessed - 1;
    }
    
/* 268 */     int j = this.columnIndex + this.lastRowProcessed * this.byteLength;
/* 269 */     int k = this.columnIndex + i * this.byteLength;
/* 270 */     int m = this.indicatorIndex + this.lastRowProcessed;
/* 271 */     int n = this.indicatorIndex + i;
/* 272 */     int i1 = this.lengthIndex + this.lastRowProcessed;
/* 273 */     int i2 = this.lengthIndex + i;
/* 274 */     int i3 = this.rowSpaceIndicator[i2];
/* 275 */     int i4 = this.metaDataIndex + this.lastRowProcessed * 1;
    
/* 277 */     int i5 = this.metaDataIndex + i * 1;
    
/* 281 */     this.rowSpaceIndicator[i1] = ((short)i3);
/* 282 */     this.rowSpaceIndicator[m] = this.rowSpaceIndicator[n];
    
/* 286 */     System.arraycopy(this.rowSpaceByte, k, this.rowSpaceByte, j, i3);
    
/* 290 */     System.arraycopy(this.rowSpaceMetaData, i5, this.rowSpaceMetaData, i4, 1);
    
/* 294 */     this.lastRowProcessed += 1;
  }
  
  void saveDataFromOldDefineBuffers(byte[] paramArrayOfByte, char[] paramArrayOfChar, short[] paramArrayOfShort, int paramInt1, int paramInt2)
    throws SQLException
  {
/* 307 */     int i = this.columnIndex + (paramInt2 - 1) * this.byteLength;
    
/* 309 */     int j = this.columnIndexLastRow + (paramInt1 - 1) * this.byteLength;
    
/* 311 */     int k = this.indicatorIndex + paramInt2 - 1;
/* 312 */     int m = this.indicatorIndexLastRow + paramInt1 - 1;
/* 313 */     int n = this.lengthIndex + paramInt2 - 1;
/* 314 */     int i1 = this.lengthIndexLastRow + paramInt1 - 1;
/* 315 */     int i2 = paramArrayOfShort[i1];
    
/* 317 */     this.rowSpaceIndicator[n] = ((short)i2);
/* 318 */     this.rowSpaceIndicator[k] = paramArrayOfShort[m];
    
/* 321 */     if (i2 != 0)
    {
/* 323 */       System.arraycopy(paramArrayOfByte, j, this.rowSpaceByte, i, i2);
    }
  }
  
  ResultSet getCursor(int paramInt)
    throws SQLException
  {
/* 334 */     Object localObject = null;
    
/* 336 */     if (this.newstmt[paramInt] != null)
    {
/* 338 */       for (int i = 0; i < this.newstmt[paramInt].numberOfDefinePositions; i++) {
/* 339 */         this.newstmt[paramInt].accessors[i].initMetadata();
      }
/* 341 */       this.newstmt[paramInt].prepareAccessors();
      
/* 343 */       this.newstmt[paramInt].setPrefetchInternal(this.statement.getFetchSize(), false, false);
      
/* 345 */       OracleResultSetImpl localOracleResultSetImpl = new OracleResultSetImpl(this.newstmt[paramInt].connection, this.newstmt[paramInt]);
      
/* 349 */       localOracleResultSetImpl.close_statement_on_close = true;
/* 350 */       this.newstmt[paramInt].currentResultSet = localOracleResultSetImpl;
/* 351 */       localObject = localOracleResultSetImpl;
    }
    
/* 354 */     return (ResultSet)localObject;
  }
  
/* 360 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CResultSetAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */