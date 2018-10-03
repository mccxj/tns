package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.sql.Datum;
abstract class T4C8TTILob
  extends T4CTTIfun
{
  static final int KPLOB_READ = 2;
  static final int KPLOB_WRITE = 64;
  static final int KPLOB_WRITE_APPEND = 8192;
  static final int KPLOB_PAGE_SIZE = 16384;
  static final int KPLOB_FILE_OPEN = 256;
  static final int KPLOB_FILE_ISOPEN = 1024;
  static final int KPLOB_FILE_EXISTS = 2048;
  static final int KPLOB_FILE_CLOSE = 512;
  static final int KPLOB_OPEN = 32768;
  static final int KPLOB_CLOSE = 65536;
  static final int KPLOB_ISOPEN = 69632;
  static final int KPLOB_TMP_CREATE = 272;
  static final int KPLOB_TMP_FREE = 273;
  static final int KPLOB_GET_LEN = 1;
  static final int KPLOB_TRIM = 32;
  static final int KOKL_ORDONLY = 1;
  static final int KOKL_ORDWR = 2;
  static final int KOLF_ORDONLY = 11;
  static final int DTYCLOB = 112;
  static final int DTYBLOB = 113;
/*  79 */   byte[] sourceLobLocator = null;
/*  80 */   byte[] destinationLobLocator = null;
/*  81 */   long sourceOffset = 0L;
/*  82 */   long destinationOffset = 0L;
/*  83 */   int destinationLength = 0;
/*  84 */   short characterSet = 0;
/*  85 */   long lobamt = 0L;
/*  86 */   boolean lobnull = false;
/*  87 */   long lobops = 0L;
/*  88 */   int[] lobscn = null;
/*  89 */   int lobscnl = 0;
  
/*  94 */   boolean nullO2U = false;
  
/*  99 */   boolean sendLobamt = false;
  
/* 102 */   byte[] inBuffer = null;
  
  long inBufferOffset;
  long inBufferNumBytes;
/* 106 */   byte[] outBuffer = null;
/* 107 */   int offsetInOutBuffer = 0;
/* 108 */   int rowsProcessed = 0;
/* 109 */   long lobBytesRead = 0L;
/* 110 */   boolean littleEndianClob = false;
/* 111 */   T4C8TTILobd lobd = null;
  
  T4C8TTILob(T4CConnection paramT4CConnection)
  {
/* 117 */     super(paramT4CConnection, (byte)3);
    
/* 119 */     setFunCode((short)96);
    
/* 121 */     this.lobd = new T4C8TTILobd(paramT4CConnection);
  }
  
  long read(byte[] paramArrayOfByte1, long paramLong1, long paramLong2, byte[] paramArrayOfByte2, int paramInt)
    throws SQLException, IOException
  {
/* 136 */     initializeLobdef();
    
/* 139 */     this.lobops = 2L;
/* 140 */     this.sourceLobLocator = paramArrayOfByte1;
/* 141 */     this.sourceOffset = paramLong1;
/* 142 */     this.lobamt = paramLong2;
/* 143 */     this.sendLobamt = true;
/* 144 */     this.outBuffer = paramArrayOfByte2;
/* 145 */     this.offsetInOutBuffer = paramInt;
/* 146 */     doRPC();
    
/* 148 */     return this.lobBytesRead;
  }
  
  long write(byte[] paramArrayOfByte1, long paramLong1, byte[] paramArrayOfByte2, long paramLong2, long paramLong3)
    throws SQLException, IOException
  {
/* 163 */     long l = 0L;
    
/* 166 */     initializeLobdef();
    
/* 169 */     this.lobops = 64L;
/* 170 */     this.sourceLobLocator = paramArrayOfByte1;
/* 171 */     this.sourceOffset = paramLong1;
/* 172 */     this.lobamt = paramLong3;
/* 173 */     this.sendLobamt = true;
/* 174 */     this.inBuffer = paramArrayOfByte2;
/* 175 */     this.inBufferOffset = paramLong2;
/* 176 */     this.inBufferNumBytes = paramLong3;
    
/* 178 */     doRPC();
    
/* 181 */     l = this.lobamt;
    
/* 183 */     return l;
  }
  
  long getLength(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 196 */     long l = 0L;
    
/* 199 */     initializeLobdef();
    
/* 202 */     this.lobops = 1L;
/* 203 */     this.sourceLobLocator = paramArrayOfByte;
    
/* 206 */     this.sendLobamt = true;
    
/* 208 */     doRPC();
/* 209 */     l = this.lobamt;
    
/* 211 */     return l;
  }
  
  long getChunkSize(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 224 */     long l = 0L;
    
/* 227 */     initializeLobdef();
    
/* 230 */     this.lobops = 16384L;
/* 231 */     this.sourceLobLocator = paramArrayOfByte;
    
/* 234 */     this.sendLobamt = true;
    
/* 236 */     doRPC();
    
/* 238 */     l = this.lobamt;
    
/* 240 */     return l;
  }
  
  long trim(byte[] paramArrayOfByte, long paramLong)
    throws SQLException, IOException
  {
/* 253 */     long l = 0L;
    
/* 256 */     initializeLobdef();
    
/* 259 */     this.lobops = 32L;
/* 260 */     this.sourceLobLocator = paramArrayOfByte;
/* 261 */     this.lobamt = paramLong;
/* 262 */     this.sendLobamt = true;
    
/* 264 */     doRPC();
    
/* 266 */     l = this.lobamt;
    
/* 269 */     return l;
  }
  
  abstract Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt)
    throws SQLException, IOException;
  
  void freeTemporaryLob(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 291 */     initializeLobdef();
    
/* 294 */     this.lobops = 273L;
/* 295 */     this.sourceLobLocator = paramArrayOfByte;
    
/* 297 */     doRPC();
  }
  
  abstract boolean open(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException;
  
  boolean _open(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws SQLException, IOException
  {
/* 321 */     boolean bool = false;
    
/* 326 */     if (((paramArrayOfByte[7] & 0x1) == 1) || ((paramArrayOfByte[4] & 0x40) == 64))
    {
/* 331 */       if ((paramArrayOfByte[7] & 0x8) == 8)
      {
/* 334 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 445);
/* 335 */         localSQLException.fillInStackTrace();
/* 336 */         throw localSQLException;
      }
      
/* 343 */       byte[] tmp60_57 = paramArrayOfByte;tmp60_57[7] = ((byte)(tmp60_57[7] | 0x8));
      
/* 347 */       if (paramInt1 == 2) {
/* 348 */         byte[] tmp75_72 = paramArrayOfByte;tmp75_72[7] = ((byte)(tmp75_72[7] | 0x10));
      }
/* 350 */       bool = true;
    }
    else
    {
/* 359 */       initializeLobdef();
      
/* 362 */       this.sourceLobLocator = paramArrayOfByte;
/* 363 */       this.lobops = paramInt2;
/* 364 */       this.lobamt = paramInt1;
/* 365 */       this.sendLobamt = true;
      
/* 367 */       doRPC();
      
/* 371 */       if (this.lobamt != 0L) {
/* 372 */         bool = true;
      }
    }
/* 375 */     return bool;
  }
  
  abstract boolean close(byte[] paramArrayOfByte)
    throws SQLException, IOException;
  
  boolean _close(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 394 */     boolean bool = true;
    
/* 400 */     if (((paramArrayOfByte[7] & 0x1) == 1) || ((paramArrayOfByte[4] & 0x40) == 64))
    {
/* 405 */       if ((paramArrayOfByte[7] & 0x8) != 8)
      {
/* 408 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 446);
/* 409 */         localSQLException.fillInStackTrace();
/* 410 */         throw localSQLException;
      }
      
/* 417 */       byte[] tmp59_56 = paramArrayOfByte;tmp59_56[7] = ((byte)(tmp59_56[7] & 0xFFFFFFE7));
    }
    else
    {
/* 426 */       initializeLobdef();
      
/* 429 */       this.sourceLobLocator = paramArrayOfByte;
/* 430 */       this.lobops = paramInt;
      
/* 432 */       doRPC();
    }
    
/* 436 */     return bool;
  }
  
  abstract boolean isOpen(byte[] paramArrayOfByte)
    throws SQLException, IOException;
  
  boolean _isOpen(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 456 */     boolean bool = false;
    
/* 462 */     if (((paramArrayOfByte[7] & 0x1) == 1) || ((paramArrayOfByte[4] & 0x40) == 64))
    {
/* 467 */       if ((paramArrayOfByte[7] & 0x8) == 8) {
/* 468 */         bool = true;
      }
      
    }
    else
    {
/* 476 */       initializeLobdef();
      
/* 479 */       this.sourceLobLocator = paramArrayOfByte;
/* 480 */       this.lobops = paramInt;
/* 481 */       this.nullO2U = true;
      
/* 483 */       doRPC();
      
/* 487 */       bool = this.lobnull;
    }
    
/* 490 */     return bool;
  }
  
  void initializeLobdef()
  {
/* 503 */     this.sourceLobLocator = null;
/* 504 */     this.destinationLobLocator = null;
/* 505 */     this.sourceOffset = 0L;
/* 506 */     this.destinationOffset = 0L;
/* 507 */     this.destinationLength = 0;
/* 508 */     this.characterSet = 0;
/* 509 */     this.lobamt = 0L;
/* 510 */     this.lobnull = false;
/* 511 */     this.lobops = 0L;
/* 512 */     this.lobscn = null;
/* 513 */     this.lobscnl = 0;
/* 514 */     this.inBuffer = null;
/* 515 */     this.outBuffer = null;
/* 516 */     this.nullO2U = false;
/* 517 */     this.sendLobamt = false;
/* 518 */     this.littleEndianClob = false;
/* 519 */     this.lobBytesRead = 0L;
  }
  
  void marshal()
    throws IOException
  {
/* 554 */     int i = 0;
    
/* 559 */     if (this.sourceLobLocator != null)
    {
/* 561 */       i = this.sourceLobLocator.length;
      
/* 563 */       this.meg.marshalPTR();
    }
    else {
/* 566 */       this.meg.marshalNULLPTR();
    }
    
/* 569 */     this.meg.marshalSB4(i);
    
/* 572 */     if (this.destinationLobLocator != null)
    {
/* 574 */       this.destinationLength = this.destinationLobLocator.length;
      
/* 576 */       this.meg.marshalPTR();
    }
    else {
/* 579 */       this.meg.marshalNULLPTR();
    }
    
/* 582 */     this.meg.marshalSB4(this.destinationLength);
    
/* 585 */     if (this.connection.getTTCVersion() >= 3) {
/* 586 */       this.meg.marshalUB4(0L);
    } else {
/* 588 */       this.meg.marshalUB4(this.sourceOffset);
    }
    
/* 591 */     if (this.connection.getTTCVersion() >= 3) {
/* 592 */       this.meg.marshalUB4(0L);
    } else {
/* 594 */       this.meg.marshalUB4(this.destinationOffset);
    }
    
/* 597 */     if (this.characterSet != 0) {
/* 598 */       this.meg.marshalPTR();
    } else {
/* 600 */       this.meg.marshalNULLPTR();
    }
    
/* 604 */     if ((this.sendLobamt == true) && (this.connection.getTTCVersion() < 3)) {
/* 605 */       this.meg.marshalPTR();
    } else {
/* 607 */       this.meg.marshalNULLPTR();
    }
    
/* 615 */     if (this.nullO2U == true) {
/* 616 */       this.meg.marshalPTR();
    } else {
/* 618 */       this.meg.marshalNULLPTR();
    }
    
/* 621 */     this.meg.marshalUB4(this.lobops);
    
/* 624 */     if (this.lobscnl != 0) {
/* 625 */       this.meg.marshalPTR();
    } else {
/* 627 */       this.meg.marshalNULLPTR();
    }
    
/* 630 */     this.meg.marshalSB4(this.lobscnl);
    
/* 632 */     if (this.connection.getTTCVersion() >= 3)
    {
/* 634 */       this.meg.marshalSB8(this.sourceOffset);
/* 635 */       this.meg.marshalSB8(this.destinationOffset);
      
/* 637 */       if (this.sendLobamt == true) {
/* 638 */         this.meg.marshalPTR();
      } else
/* 640 */         this.meg.marshalNULLPTR();
/* 641 */       if (this.connection.getTTCVersion() >= 4)
      {
/* 657 */         this.meg.marshalNULLPTR();
/* 658 */         this.meg.marshalSWORD(0);
/* 659 */         this.meg.marshalNULLPTR();
/* 660 */         this.meg.marshalSWORD(0);
/* 661 */         this.meg.marshalNULLPTR();
/* 662 */         this.meg.marshalSWORD(0);
      }
    }
    
/* 674 */     if (this.sourceLobLocator != null)
    {
/* 676 */       this.meg.marshalB1Array(this.sourceLobLocator);
    }
    
/* 680 */     if (this.destinationLobLocator != null)
    {
/* 682 */       this.meg.marshalB1Array(this.destinationLobLocator);
    }
    
/* 686 */     if (this.characterSet != 0)
    {
/* 688 */       this.meg.marshalUB2(this.characterSet);
    }
    
/* 692 */     if ((this.sendLobamt == true) && (this.connection.getTTCVersion() < 3))
    {
/* 696 */       this.meg.marshalUB4(this.lobamt);
    }
    
/* 701 */     if (this.lobscnl != 0)
    {
/* 703 */       for (int j = 0; j < this.lobscnl; j++)
      {
/* 705 */         this.meg.marshalUB4(this.lobscn[j]);
      }
    }
    
/* 709 */     if ((this.sendLobamt == true) && (this.connection.getTTCVersion() >= 3))
    {
/* 711 */       this.meg.marshalSB8(this.lobamt);
    }
/* 713 */     if (this.lobops == 64L) {
/* 714 */       marshalData();
    }
  }
  
  void marshalData()
    throws IOException
  {
/* 723 */     boolean bool = this.connection.isZeroCopyIOEnabled() & (this.sourceLobLocator[7] & 0xFFFFFF80) != 0;
    
/* 725 */     int i = 0;
/* 726 */     if ((this.sourceLobLocator[6] & 0x80) == 128) {
/* 727 */       i = 1;
    }
    
/* 731 */     if ((this.connection.versionNumber < 10101) && (i != 0)) {
/* 732 */       this.lobd.marshalClobUB2_For9iDB(this.inBuffer, this.inBufferOffset, this.inBufferNumBytes);
    } else {
/* 734 */       this.lobd.marshalLobData(this.inBuffer, this.inBufferOffset, this.inBufferNumBytes, bool);
    }
  }
  
  void readLOBD()
    throws IOException, SQLException
  {
/* 759 */     boolean bool = this.connection.isZeroCopyIOEnabled() & (this.sourceLobLocator[7] & 0xFFFFFF80) != 0;
    
/* 761 */     int i = 0;
/* 762 */     if ((this.sourceLobLocator[6] & 0x80) == 128) {
/* 763 */       i = 1;
    }
    
/* 767 */     if ((this.connection.versionNumber < 10101) && (i != 0)) {
/* 768 */       this.lobBytesRead = this.lobd.unmarshalClobUB2_For9iDB(this.outBuffer, this.offsetInOutBuffer);
    } else {
/* 770 */       this.lobBytesRead = this.lobd.unmarshalLobData(this.outBuffer, this.offsetInOutBuffer, bool);
    }
  }
  
  void processError()
    throws SQLException
  {
/* 783 */     this.rowsProcessed = this.oer.getCurRowNumber();
    
/* 788 */     if (this.oer.getRetCode() != 1403)
    {
/* 792 */       this.oer.processError();
    }
  }
  
  void readRPA()
    throws SQLException, IOException
  {
    int i;
    
/* 815 */     if (this.sourceLobLocator != null)
    {
/* 817 */       i = this.sourceLobLocator.length;
      
/* 819 */       this.meg.getNBytes(this.sourceLobLocator, 0, i);
    }
    
/* 823 */     if (this.destinationLobLocator != null)
    {
/* 825 */       i = this.meg.unmarshalSB2();
      
/* 827 */       this.destinationLobLocator = this.meg.unmarshalNBytes(i);
    }
    
/* 831 */     if (this.characterSet != 0)
    {
/* 833 */       this.characterSet = this.meg.unmarshalSB2();
    }
    
/* 837 */     if (this.sendLobamt == true)
    {
/* 839 */       if (this.connection.getTTCVersion() >= 3) {
/* 840 */         this.lobamt = this.meg.unmarshalSB8();
      } else {
/* 842 */         this.lobamt = this.meg.unmarshalUB4();
      }
    }
    
/* 846 */     if (this.nullO2U == true)
    {
/* 853 */       i = (short)this.meg.unmarshalSB1();
      
/* 856 */       if (i != 0) {
/* 857 */         this.lobnull = true;
      }
    }
  }
  
/* 865 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTILob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */