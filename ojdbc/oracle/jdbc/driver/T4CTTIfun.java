package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.LinkedList;
import oracle.jdbc.internal.OracleConnection;
import oracle.net.ns.BreakNetException;
abstract class T4CTTIfun
  extends T4CTTIMsg
{
  static final short OOPEN = 2;
  static final short OFETCH = 5;
  static final short OCLOSE = 8;
  static final short OLOGOFF = 9;
  static final short OCOMON = 12;
  static final short OCOMOFF = 13;
  static final short OCOMMIT = 14;
  static final short OROLLBACK = 15;
  static final short OCANCEL = 20;
  static final short ODSCRARR = 43;
  static final short OVERSION = 59;
  static final short OK2RPC = 67;
  static final short OALL7 = 71;
  static final short OSQL7 = 74;
  static final short O3LOGON = 81;
  static final short O3LOGA = 82;
  static final short OKOD = 92;
  static final short OALL8 = 94;
  static final short OLOBOPS = 96;
  static final short ODNY = 98;
  static final short OTXSE = 103;
  static final short OTXEN = 104;
  static final short OCCA = 105;
  static final short O80SES = 107;
  static final short OAUTH = 115;
  static final short OSESSKEY = 118;
  static final short OCANA = 120;
  static final short OKPN = 125;
  static final short OOTCM = 127;
  static final short OSCID = 135;
  static final short OSPFPPUT = 138;
  static final short OKPFC = 139;
  static final short OPING = 147;
  static final short OKEYVAL = 154;
  static final short OXSSCS = 155;
  static final short OXSSRO = 156;
  static final short OXSSPO = 157;
  static final short OAQEQ = 121;
  static final short OAQDQ = 122;
  static final short OAQGPS = 132;
  static final short OAQLS = 126;
  static final short OAQXQ = 145;
  static final short OXSNS = 172;
  private short funCode;
  protected final T4CTTIoer oer;
  
  T4CTTIfun(T4CConnection paramT4CConnection, byte paramByte)
  {
/* 132 */     super(paramT4CConnection, paramByte);
    
/* 134 */     this.oer = paramT4CConnection.getT4CTTIoer();
  }
  
  final void setFunCode(short paramShort)
  {
/* 141 */     this.funCode = paramShort;
  }
  
  final short getFunCode()
  {
/* 147 */     return this.funCode;
  }
  
  private final void marshalFunHeader()
    throws IOException
  {
/* 159 */     marshalTTCcode();
/* 160 */     this.meg.marshalUB1(this.funCode);
/* 161 */     this.meg.marshalUB1((short)this.connection.getNextSeqNumber());
  }
  
  abstract void marshal()
    throws IOException;
  
  final void doRPC()
    throws IOException, SQLException
  {
/* 178 */     if (getTTCCode() == 17)
    {
/* 181 */       SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 182 */       localSQLException1.fillInStackTrace();
/* 183 */       throw localSQLException1;
    }
    
/* 218 */     for (int i = 0; i < 5; i++)
    {
/* 220 */       init();
/* 221 */       marshalFunHeader();
      try
      {
/* 224 */         this.connection.pipeState = 1;
/* 225 */         marshal();
/* 226 */         this.connection.pipeState = 2;
/* 227 */         receive();
      }
      catch (SQLException localSQLException2)
      {
/* 231 */         synchronized (this.connection.cancelInProgressLockForThin)
        {
/* 234 */           if ((localSQLException2.getErrorCode() == 1013) || ((this.connection.cancelInProgressFlag) && (localSQLException2.getMessage() != null) && (localSQLException2.getMessage().contains("ORA-01013"))))
          {
/* 238 */             this.connection.cancelInProgressFlag = false;
/* 239 */             this.connection.redoCursorClose();
            
/* 242 */             if ((this.funCode == 15) || (this.funCode == 12) || (this.funCode == 13) || (this.funCode == 14) || (this.funCode == 59))
            {
/* 244 */               if ((this.oer.callNumber != this.connection.currentTTCSeqNumber) || (this.connection.statementCancel))
              {
/* 253 */                 this.connection.pipeState = -1; continue;
              }
            }
          }
        }
/* 249 */         throw localSQLException2;
      }
      finally
      {
/* 253 */         this.connection.pipeState = -1;
      }
/* 255 */       break;
    }
  }
  
  final void doPigRPC()
    throws IOException
  {
/* 271 */     init();
/* 272 */     marshalFunHeader();
/* 273 */     marshal();
  }
  
/* 286 */   int receiveState = 0;
  
  static final int IDLE_RECEIVE_STATE = 0;
  
  static final int ACTIVE_RECEIVE_STATE = 1;
  
  static final int READROW_RECEIVE_STATE = 2;
  
  static final int STREAM_RECEIVE_STATE = 3;
  
/* 298 */   boolean rpaProcessed = false;
/* 299 */   boolean rxhProcessed = false;
/* 300 */   boolean iovProcessed = false;
  
  private void init()
  {
/* 310 */     this.rpaProcessed = false;
/* 311 */     this.rxhProcessed = false;
/* 312 */     this.iovProcessed = false;
/* 313 */     this.ttilist.clear();
  }
  
  void resumeReceive()
    throws SQLException, IOException
  {
/* 325 */     receive();
  }
  
/* 329 */   private LinkedList<Short> ttilist = new LinkedList();
  
  private void receive()
    throws SQLException, IOException
  {
/* 341 */     this.receiveState = 1;
    
/* 343 */     Object localObject1 = null;
    
    for (;;)
    {
      try
      {
/* 350 */         short s1 = this.meg.unmarshalUB1();
/* 351 */         this.ttilist.add(new Short(s1));
        
/* 353 */         switch (s1)
        {
        case 8: 
/* 358 */           if (this.rpaProcessed)
          {
/* 360 */             SQLException localSQLException1 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 361 */             localSQLException1.fillInStackTrace();
/* 362 */             throw localSQLException1;
          }
/* 364 */           readRPA();
          try
          {
/* 367 */             processRPA();
          }
          catch (SQLException localSQLException2)
          {
/* 373 */             localObject1 = localSQLException2;
          }
/* 375 */           this.rpaProcessed = true;
/* 376 */           break;
        
        case 21: 
/* 381 */           readBVC();
/* 382 */           break;
        
        case 11: 
/* 385 */           readIOV();
/* 386 */           this.iovProcessed = true;
/* 387 */           break;
        
        case 6: 
/* 391 */           readRXH();
/* 392 */           this.rxhProcessed = true;
/* 393 */           break;
        case 12: 
/* 395 */           processSLG();
/* 396 */           break;
        
        case 7: 
/* 400 */           this.receiveState = 2;
          
/* 403 */           if (readRXD())
          {
/* 408 */             this.receiveState = 3; return;
          }
          
/* 415 */           this.receiveState = 1;
          
/* 417 */           break;
        
        case 16: 
/* 421 */           readDCB();
/* 422 */           break;
        case 14: 
/* 424 */           readLOBD();
/* 425 */           break;
        
        case 23: 
/* 429 */           int i = (byte)this.meg.unmarshalUB1();
/* 430 */           int j = this.meg.unmarshalUB2();
/* 431 */           int k = (byte)this.meg.unmarshalUB1();
          int m;
/* 433 */           if (i == 1)
          {
/* 445 */             m = 0; if (m < j)
            {
/* 447 */               T4CTTIidc localT4CTTIidc = new T4CTTIidc(this.connection);
/* 448 */               localT4CTTIidc.unmarshal();m++; continue;
            }
            
          }
/* 451 */           else if (i == 2)
          {
/* 454 */             m = 0; if (m < j)
            {
/* 456 */               int i1 = this.meg.unmarshalUB1();m++; continue;
            }
          }
/* 459 */           else if (i != 3)
          {
/* 461 */             if (i != 4)
            {
/* 463 */               if (i == 5)
              {
/* 470 */                 T4CTTIkvarr localT4CTTIkvarr = new T4CTTIkvarr(this.connection);
/* 471 */                 localT4CTTIkvarr.unmarshal();
/* 472 */               } else if (i == 6)
              {
/* 474 */                 int n = 0; if (n < j)
                {
/* 476 */                   NTFXSEvent localNTFXSEvent = new NTFXSEvent(this.connection);
/* 477 */                   this.connection.notify(localNTFXSEvent);n++; continue;
                }
              } }
          }
          break;
        case 19: 
/* 482 */           this.meg.marshalUB1((short)19);
/* 483 */           break;
        
        case 15: 
/* 487 */           this.oer.init();
/* 488 */           this.oer.unmarshalWarning();
          
          try
          {
/* 492 */             this.oer.processWarning();
          }
          catch (SQLWarning localSQLWarning)
          {
/* 496 */             this.connection.setWarnings(DatabaseError.addSqlWarning(this.connection.getWarnings(), localSQLWarning));
          }
        
        case 9: 
/* 500 */           processEOCS();
/* 501 */           if (this.connection.getTTCVersion() >= 3)
          {
/* 503 */             short s2 = (short)this.meg.unmarshalUB2();
/* 504 */             this.connection.endToEndECIDSequenceNumber = s2;
          }
          
/* 536 */           this.connection.sentCancel = false; break;
        case 4: 
/* 508 */           processEOCS();
/* 509 */           this.oer.init();
/* 510 */           this.oer.unmarshal();
          try
          {
/* 513 */             processError();
          }
          catch (SQLException localSQLException3)
          {
/* 519 */             localObject1 = localSQLException3;
          }
          
/* 536 */           this.connection.sentCancel = false; break;
        case 5: 
        case 10: 
        case 13: 
        case 17: 
        case 18: 
        case 20: 
        case 22: 
        default: 
/* 526 */           SQLException localSQLException4 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401, this.ttilist.toString());
/* 527 */           localSQLException4.fillInStackTrace();
/* 528 */           throw localSQLException4;
        
        }
        
      }
      catch (BreakNetException localBreakNetException) {}finally
      {
/* 536 */         this.connection.sentCancel = false;
      }
    }
    
/* 542 */     this.receiveState = 0;
    
/* 544 */     if (localObject1 != null) {
/* 545 */       throw ((Throwable)localObject1);
    }
  }
  
  private final void processEOCS()
    throws SQLException, IOException
  {
/* 557 */     if (this.connection.hasServerCompileTimeCapability(15, 1))
    {
/* 559 */       int i = (int)this.meg.unmarshalUB4();
/* 560 */       this.connection.eocs = i;
/* 561 */       if ((i & 0x8) != 0)
      {
/* 565 */         long l = this.meg.unmarshalSB8();
      }
    }
  }
  
  void processRPA()
    throws SQLException
  {}
  
  void readRPA()
    throws IOException, SQLException
  {}
  
  void readBVC()
    throws IOException, SQLException
  {
/* 597 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 598 */     localSQLException.fillInStackTrace();
/* 599 */     throw localSQLException;
  }
  
  void readLOBD()
    throws IOException, SQLException
  {
/* 608 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 609 */     localSQLException.fillInStackTrace();
/* 610 */     throw localSQLException;
  }
  
  void readIOV()
    throws IOException, SQLException
  {
/* 619 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 620 */     localSQLException.fillInStackTrace();
/* 621 */     throw localSQLException;
  }
  
  void readRXH()
    throws IOException, SQLException
  {
/* 630 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 631 */     localSQLException.fillInStackTrace();
/* 632 */     throw localSQLException;
  }
  
  boolean readRXD()
    throws IOException, SQLException
  {
/* 641 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 642 */     localSQLException.fillInStackTrace();
/* 643 */     throw localSQLException;
  }
  
  void readDCB()
    throws IOException, SQLException
  {
/* 652 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 653 */     localSQLException.fillInStackTrace();
/* 654 */     throw localSQLException;
  }
  
  void processSLG()
    throws IOException, SQLException
  {
/* 663 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/* 664 */     localSQLException.fillInStackTrace();
/* 665 */     throw localSQLException;
  }
  
  void processError()
    throws SQLException
  {
/* 675 */     this.oer.processError();
  }
  
  final int getErrorCode()
    throws SQLException
  {
/* 682 */     return this.oer.retCode;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 697 */     return null;
  }
  
/* 702 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIfun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */