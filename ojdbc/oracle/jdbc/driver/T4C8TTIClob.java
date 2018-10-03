package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import oracle.jdbc.OracleConnection;
import oracle.sql.CLOB;
import oracle.sql.CharacterSet;
import oracle.sql.Datum;
import oracle.sql.NCLOB;
final class T4C8TTIClob
  extends T4C8TTILob
{
  int[] nBytes;
  
  T4C8TTIClob(T4CConnection paramT4CConnection)
  {
/* 148 */     super(paramT4CConnection);
    
/* 150 */     this.nBytes = new int[1];
  }
  
  long read(byte[] paramArrayOfByte, long paramLong1, long paramLong2, boolean paramBoolean, char[] paramArrayOfChar, int paramInt)
    throws SQLException, IOException
  {
/* 185 */     long l1 = 0L;
/* 186 */     long l2 = paramLong2;
/* 187 */     int i = 0;
    
/* 199 */     byte[] arrayOfByte = null;
    
    try
    {
/* 204 */       initializeLobdef();
      
/* 212 */       if ((paramArrayOfByte[6] & 0x80) == 128) {
/* 213 */         i = 1;
      }
      
/* 220 */       int j = 0;
/* 221 */       if (i == 1) {
/* 222 */         j = (int)paramLong2 * 2;
      } else {
/* 224 */         j = (int)paramLong2 * 3;
      }
      
/* 227 */       arrayOfByte = this.connection.getByteBuffer(j);
      
/* 235 */       if ((paramArrayOfByte[7] & 0x40) > 0) {
/* 236 */         this.littleEndianClob = true;
      }
      
/* 240 */       this.lobops = 2L;
/* 241 */       this.sourceLobLocator = paramArrayOfByte;
/* 242 */       this.sourceOffset = paramLong1;
/* 243 */       this.lobamt = paramLong2;
/* 244 */       this.sendLobamt = true;
/* 245 */       this.outBuffer = arrayOfByte;
      
/* 247 */       doRPC();
      
/* 249 */       l2 = this.lobamt;
      
/* 255 */       long l3 = 0L;
      
/* 260 */       if (i == 1)
      {
/* 262 */         if (this.connection.versionNumber < 10101)
        {
/* 266 */           DBConversion.ucs2BytesToJavaChars(this.outBuffer, (int)this.lobBytesRead, paramArrayOfChar);
        }
/* 269 */         else if (this.littleEndianClob)
        {
/* 271 */           CharacterSet.convertAL16UTF16LEBytesToJavaChars(this.outBuffer, 0, paramArrayOfChar, paramInt, (int)this.lobBytesRead, true);
        }
        else
        {
/* 276 */           CharacterSet.convertAL16UTF16BytesToJavaChars(this.outBuffer, 0, paramArrayOfChar, paramInt, (int)this.lobBytesRead, true);
        }
        
      }
/* 284 */       else if (!paramBoolean)
      {
/* 289 */         this.nBytes[0] = ((int)this.lobBytesRead);
        
/* 291 */         this.meg.conv.CHARBytesToJavaChars(this.outBuffer, 0, paramArrayOfChar, paramInt, this.nBytes, paramArrayOfChar.length);
      }
      else
      {
/* 302 */         this.nBytes[0] = ((int)this.lobBytesRead);
        
/* 304 */         this.meg.conv.NCHARBytesToJavaChars(this.outBuffer, 0, paramArrayOfChar, paramInt, this.nBytes, paramArrayOfChar.length);
      }
      
    }
    finally
    {
/* 311 */       this.outBuffer = null;
/* 312 */       this.connection.cacheBuffer(arrayOfByte);
    }
    
/* 315 */     return l2;
  }
  
  long write(byte[] paramArrayOfByte, long paramLong1, boolean paramBoolean, char[] paramArrayOfChar, long paramLong2, long paramLong3)
    throws SQLException, IOException
  {
/* 357 */     int i = 0;
/* 358 */     if ((paramArrayOfByte[6] & 0x80) == 128) {
/* 359 */       i = 1;
    }
/* 361 */     if ((paramArrayOfByte[7] & 0x40) == 64) {
/* 362 */       this.littleEndianClob = true;
    }
    
/* 366 */     long l1 = 0L;
/* 367 */     byte[] arrayOfByte = null;
    
/* 373 */     if (i == 1)
    {
/* 378 */       arrayOfByte = new byte[(int)paramLong3 * 2];
      
/* 380 */       if (this.connection.versionNumber < 10101)
      {
/* 382 */         DBConversion.javaCharsToUcs2Bytes(paramArrayOfChar, (int)paramLong2, arrayOfByte, 0, (int)paramLong3);
      }
/* 384 */       else if (this.littleEndianClob)
      {
/* 386 */         CharacterSet.convertJavaCharsToAL16UTF16LEBytes(paramArrayOfChar, (int)paramLong2, arrayOfByte, 0, (int)paramLong3);
      }
      else
      {
/* 390 */         CharacterSet.convertJavaCharsToAL16UTF16Bytes(paramArrayOfChar, (int)paramLong2, arrayOfByte, 0, (int)paramLong3);
      }
      
    }
    else
    {
/* 398 */       arrayOfByte = new byte[(int)paramLong3 * 3];
      
/* 400 */       if (!paramBoolean)
      {
/* 405 */         l1 = this.meg.conv.javaCharsToCHARBytes(paramArrayOfChar, (int)paramLong2, arrayOfByte, 0, (int)paramLong3);
      }
      else
      {
/* 413 */         l1 = this.meg.conv.javaCharsToNCHARBytes(paramArrayOfChar, (int)paramLong2, arrayOfByte, 0, (int)paramLong3);
      }
    }
    
/* 420 */     initializeLobdef();
    
/* 423 */     this.lobops = 64L;
/* 424 */     this.sourceLobLocator = paramArrayOfByte;
/* 425 */     this.sourceOffset = paramLong1;
/* 426 */     this.lobamt = paramLong3;
/* 427 */     this.sendLobamt = true;
/* 428 */     this.inBuffer = arrayOfByte;
/* 429 */     this.inBufferOffset = 0L;
    
/* 433 */     if (i == 1)
    {
/* 438 */       if (this.connection.versionNumber < 10101) {
/* 439 */         this.inBufferNumBytes = paramLong3;
      } else {
/* 441 */         this.inBufferNumBytes = (paramLong3 * 2L);
      }
      
    }
    else
    {
/* 448 */       this.inBufferNumBytes = l1;
    }
/* 450 */     doRPC();
    
/* 452 */     long l2 = this.lobamt;
    
/* 454 */     return l2;
  }
  
  Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt)
    throws SQLException, IOException
  {
/* 474 */     return createTemporaryLob(paramConnection, paramBoolean, paramInt, (short)1);
  }
  
  Datum createTemporaryLob(Connection paramConnection, boolean paramBoolean, int paramInt, short paramShort)
    throws SQLException, IOException
  {
/* 489 */     if (paramInt == 12)
    {
/* 491 */       localObject = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 158);
/* 492 */       ((SQLException)localObject).fillInStackTrace();
/* 493 */       throw ((Throwable)localObject);
    }
    
/* 498 */     Object localObject = null;
    
/* 501 */     initializeLobdef();
    
/* 504 */     this.lobops = 272L;
/* 505 */     this.sourceLobLocator = new byte[40];
/* 506 */     this.sourceLobLocator[1] = 84;
    
/* 509 */     if (paramShort == 1) {
/* 510 */       this.sourceOffset = 1L;
    } else {
/* 512 */       this.sourceOffset = 2L;
    }
    
/* 516 */     this.destinationOffset = 112L;
    
/* 525 */     this.destinationLength = paramInt;
    
/* 527 */     this.lobamt = paramInt;
/* 528 */     this.sendLobamt = true;
    
/* 531 */     this.nullO2U = true;
    
/* 536 */     this.characterSet = (paramShort == 2 ? this.meg.conv.getNCharSetId() : this.meg.conv.getServerCharSetId());
    
/* 538 */     if (this.connection.versionNumber >= 9000)
    {
/* 540 */       this.lobscn = new int[1];
/* 541 */       this.lobscn[0] = (paramBoolean ? 1 : 0);
/* 542 */       this.lobscnl = 1;
    }
    
/* 545 */     doRPC();
    
/* 549 */     if (this.sourceLobLocator != null)
    {
/* 551 */       if (paramShort == 1) {
/* 552 */         localObject = new CLOB((OracleConnection)paramConnection, this.sourceLobLocator);
      }
      else
      {
/* 556 */         localObject = new NCLOB((OracleConnection)paramConnection, this.sourceLobLocator);
      }
    }
    
/* 560 */     return (Datum)localObject;
  }
  
  boolean open(byte[] paramArrayOfByte, int paramInt)
    throws SQLException, IOException
  {
/* 579 */     boolean bool = false;
    
/* 583 */     int i = 2;
    
/* 585 */     if (paramInt == 0) {
/* 586 */       i = 1;
    }
/* 588 */     bool = _open(paramArrayOfByte, i, 32768);
    
/* 590 */     return bool;
  }
  
  boolean close(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 608 */     boolean bool = false;
    
/* 610 */     bool = _close(paramArrayOfByte, 65536);
    
/* 612 */     return bool;
  }
  
  boolean isOpen(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 631 */     boolean bool = false;
    
/* 633 */     bool = _isOpen(paramArrayOfByte, 69632);
    
/* 635 */     return bool;
  }
  
/* 640 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIClob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */