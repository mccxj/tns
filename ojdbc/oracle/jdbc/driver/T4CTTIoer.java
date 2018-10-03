package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.internal.OracleConnection;
class T4CTTIoer
  extends T4CTTIMsg
{
/* 164 */   final int MAXERRBUF = 512;
  
  long curRowNumber;
  
  int retCode;
  
  int arrayElemWError;
  int arrayElemErrno;
  int currCursorID;
  short errorPosition;
  short sqlType;
  byte oerFatal;
  short flags;
  short userCursorOpt;
  short upiParam;
  short warningFlag;
  int osError;
  short stmtNumber;
  short callNumber;
  int pad1;
  long successIters;
  int partitionId;
  int tableId;
  int slotNumber;
  long rba;
  long blockNumber;
/* 190 */   int warnLength = 0;
/* 191 */   int warnFlag = 0;
  
/* 195 */   int[] errorLength = new int[1];
  
  byte[] errorMsg;
  
  static final int ORA1403 = 1403;
  
  T4CTTIoer(T4CConnection paramT4CConnection)
  {
/* 209 */     super(paramT4CConnection, (byte)4);
  }
  
  void init()
  {
/* 217 */     this.retCode = 0;
/* 218 */     this.errorMsg = null;
  }
  
  int unmarshal()
    throws IOException, SQLException
  {
/* 233 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 235 */       short s = (short)this.meg.unmarshalUB2();
      
/* 237 */       this.connection.endToEndECIDSequenceNumber = s;
    }
    
/* 240 */     this.curRowNumber = this.meg.unmarshalUB4();
/* 241 */     this.retCode = this.meg.unmarshalUB2();
/* 242 */     this.arrayElemWError = this.meg.unmarshalUB2();
/* 243 */     this.arrayElemErrno = this.meg.unmarshalUB2();
/* 244 */     this.currCursorID = this.meg.unmarshalUB2();
/* 245 */     this.errorPosition = this.meg.unmarshalSB2();
/* 246 */     this.sqlType = this.meg.unmarshalUB1();
/* 247 */     this.oerFatal = this.meg.unmarshalSB1();
/* 248 */     this.flags = ((short)this.meg.unmarshalSB1());
/* 249 */     this.userCursorOpt = ((short)this.meg.unmarshalSB1());
/* 250 */     this.upiParam = this.meg.unmarshalUB1();
/* 251 */     this.warningFlag = this.meg.unmarshalUB1();
    
/* 254 */     this.rba = this.meg.unmarshalUB4();
/* 255 */     this.partitionId = this.meg.unmarshalUB2();
/* 256 */     this.tableId = this.meg.unmarshalUB1();
/* 257 */     this.blockNumber = this.meg.unmarshalUB4();
/* 258 */     this.slotNumber = this.meg.unmarshalUB2();
    
/* 260 */     this.osError = this.meg.unmarshalSWORD();
/* 261 */     this.stmtNumber = this.meg.unmarshalUB1();
/* 262 */     this.callNumber = this.meg.unmarshalUB1();
/* 263 */     this.pad1 = this.meg.unmarshalUB2();
/* 264 */     this.successIters = this.meg.unmarshalUB4();
    
/* 275 */     byte[] arrayOfByte = this.meg.unmarshalDALC();
    
/* 290 */     int i = this.meg.unmarshalUB2();
/* 291 */     for (int j = 0; j < i; j++)
/* 292 */       this.meg.unmarshalUB2();
/* 293 */     j = (int)this.meg.unmarshalUB4();
/* 294 */     for (int k = 0; k < j; k++)
/* 295 */       this.meg.unmarshalUB4();
/* 296 */     k = this.meg.unmarshalUB2();
    
/* 299 */     if (this.retCode != 0)
    {
/* 301 */       this.errorMsg = this.meg.unmarshalCLRforREFS();
/* 302 */       this.errorLength[0] = this.errorMsg.length;
    }
    
/* 306 */     return this.currCursorID;
  }
  
  void unmarshalWarning()
    throws IOException, SQLException
  {
/* 314 */     this.retCode = this.meg.unmarshalUB2();
/* 315 */     this.warnLength = this.meg.unmarshalUB2();
/* 316 */     this.warnFlag = this.meg.unmarshalUB2();
    
/* 319 */     if ((this.retCode != 0) && (this.warnLength > 0))
    {
/* 321 */       this.errorMsg = this.meg.unmarshalCHR(this.warnLength);
/* 322 */       this.errorLength[0] = this.warnLength;
    }
  }
  
  void print()
    throws SQLException
  {
/* 366 */     if (this.retCode == 0)
    {
/* 371 */       if (this.warnFlag == 0) {}
    }
  }
  
  void processError()
    throws SQLException
  {
/* 382 */     processError(true);
  }
  
  void processError(boolean paramBoolean)
    throws SQLException
  {
/* 389 */     processError(paramBoolean, null);
  }
  
  void processError(OracleStatement paramOracleStatement)
    throws SQLException
  {
/* 396 */     processError(true, paramOracleStatement);
  }
  
  void processError(boolean paramBoolean, OracleStatement paramOracleStatement)
    throws SQLException
  {
/* 410 */     if (paramOracleStatement != null) {
/* 411 */       paramOracleStatement.numberOfExecutedElementsInBatch = ((int)this.successIters);
    }
/* 413 */     if (this.retCode != 0)
    {
/* 418 */       switch (this.retCode)
      {
      case 28: 
      case 600: 
      case 1012: 
      case 1041: 
      case 3113: 
      case 3114: 
/* 433 */         this.connection.internalClose();
/* 434 */         break;
      
      case 902: 
/* 438 */         this.connection.removeAllDescriptor();
      }
      
      
/* 442 */       if (paramBoolean)
      {
/* 446 */         SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), this.meg.conv.CharBytesToString(this.errorMsg, this.errorLength[0], true), this.retCode);
/* 447 */         localSQLException1.fillInStackTrace();
/* 448 */         throw localSQLException1;
      }
      
/* 451 */       return;
    }
    
/* 457 */     if (!paramBoolean) {
/* 458 */       return;
    }
    
/* 467 */     if ((this.warningFlag & 0x1) == 1)
    {
/* 469 */       int i = this.warningFlag & 0xFFFFFFFE;
      
/* 472 */       if (((i & 0x20) == 32) || ((i & 0x4) == 4))
      {
/* 474 */         SQLException localSQLException2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 110);
/* 475 */         localSQLException2.fillInStackTrace();
/* 476 */         throw localSQLException2;
      }
    }
    
/* 481 */     if ((this.connection != null) && (this.connection.plsqlCompilerWarnings))
    {
/* 483 */       if ((this.flags & 0x4) == 4) {
/* 484 */         paramOracleStatement.foundPlsqlCompilerWarning();
      }
    }
  }
  
  void processWarning()
    throws SQLException
  {
/* 500 */     if (this.retCode != 0)
    {
/* 504 */       throw DatabaseError.newSqlWarning(this.meg.conv.CharBytesToString(this.errorMsg, this.errorLength[0], true), this.retCode);
    }
  }
  
  int getCurRowNumber()
    throws SQLException
  {
/* 517 */     return (int)this.curRowNumber;
  }
  
  int getRetCode()
  {
/* 528 */     return this.retCode;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 543 */     return this.connection;
  }
  
  long updateChecksum(long paramLong)
    throws SQLException
  {
/* 550 */     paramLong = CRC64.updateChecksum(paramLong, this.retCode);
/* 551 */     paramLong = CRC64.updateChecksum(paramLong, this.curRowNumber);
/* 552 */     paramLong = CRC64.updateChecksum(paramLong, this.errorPosition);
/* 553 */     paramLong = CRC64.updateChecksum(paramLong, this.sqlType);
/* 554 */     paramLong = CRC64.updateChecksum(paramLong, this.oerFatal);
/* 555 */     paramLong = CRC64.updateChecksum(paramLong, this.flags);
/* 556 */     paramLong = CRC64.updateChecksum(paramLong, this.userCursorOpt);
/* 557 */     paramLong = CRC64.updateChecksum(paramLong, this.upiParam);
/* 558 */     paramLong = CRC64.updateChecksum(paramLong, this.warningFlag);
/* 559 */     paramLong = CRC64.updateChecksum(paramLong, this.osError);
/* 560 */     paramLong = CRC64.updateChecksum(paramLong, this.successIters);
/* 561 */     paramLong = CRC64.updateChecksum(paramLong, this.errorMsg, 0, this.errorMsg.length);
/* 562 */     return paramLong;
  }
  
/* 567 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIoer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */