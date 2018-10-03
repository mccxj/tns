package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.aq.AQEnqueueOptions;
import oracle.jdbc.aq.AQEnqueueOptions.DeliveryMode;
import oracle.jdbc.aq.AQEnqueueOptions.SequenceDeviationOption;
import oracle.jdbc.aq.AQEnqueueOptions.VisibilityOption;
import oracle.jdbc.internal.OracleConnection;
final class T4Caqe
  extends T4CTTIfun
{
  static final int KPD_AQ_BUFMSG = 2;
  static final int KPD_AQ_EITHER = 16;
  static final int OCI_COMMIT_ON_SUCCESS = 32;
  static final int ATTR_TRANSFORMATION = 196;
  T4CTTIaqm aqm;
  T4Ctoh toh;
  
  T4Caqe(T4CConnection paramT4CConnection)
  {
/* 231 */     super(paramT4CConnection, (byte)3);
    
/* 233 */     setFunCode((short)121);
/* 234 */     this.toh = new T4Ctoh();
/* 235 */     this.aqm = new T4CTTIaqm(this.connection, this.toh);
  }
  
/* 238 */   private byte[] queueNameBytes = null;
/* 239 */   private AQEnqueueOptions enqueueOptions = null;
/* 240 */   private AQMessagePropertiesI messageProperties = null;
/* 241 */   private byte[] messageData = null;
/* 242 */   private byte[] messageOid = null;
/* 243 */   private boolean isRawQueue = false;
/* 244 */   private int nbExtensions = 0;
/* 245 */   private byte[][] extensionTextValues = (byte[][])null;
/* 246 */   private byte[][] extensionBinaryValues = (byte[][])null;
/* 247 */   private int[] extensionKeywords = null;
/* 248 */   private AQAgentI[] attrRecipientList = null;
/* 249 */   private byte[][] recipientTextValues = (byte[][])null;
/* 250 */   private byte[][] recipientBinaryValues = (byte[][])null;
/* 251 */   private int[] recipientKeywords = null;
  private byte[] aqmcorBytes;
  private byte[] aqmeqnBytes;
/* 254 */   private boolean retrieveMessageId = false;
/* 255 */   private byte[] outMsgid = null;
/* 256 */   private byte[] senderAgentName = null;
/* 257 */   private byte[] senderAgentAddress = null;
/* 258 */   private byte senderAgentProtocol = 0;
  
  void doOAQEQ(String paramString, AQEnqueueOptions paramAQEnqueueOptions, AQMessagePropertiesI paramAQMessagePropertiesI, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean)
    throws SQLException, IOException
  {
/* 272 */     this.enqueueOptions = paramAQEnqueueOptions;
/* 273 */     this.messageProperties = paramAQMessagePropertiesI;
    
/* 275 */     String str1 = this.messageProperties.getCorrelation();
/* 276 */     if ((str1 != null) && (str1.length() != 0)) {
/* 277 */       this.aqmcorBytes = this.meg.conv.StringToCharBytes(str1);
    } else
/* 279 */       this.aqmcorBytes = null;
/* 280 */     String str2 = this.messageProperties.getExceptionQueue();
/* 281 */     if ((str2 != null) && (str2.length() != 0)) {
/* 282 */       this.aqmeqnBytes = this.meg.conv.StringToCharBytes(str2);
    } else {
/* 284 */       this.aqmeqnBytes = null;
    }
/* 286 */     AQAgentI localAQAgentI = (AQAgentI)this.messageProperties.getSender();
/* 287 */     if (localAQAgentI != null)
    {
/* 289 */       if (localAQAgentI.getName() != null) {
/* 290 */         this.senderAgentName = this.meg.conv.StringToCharBytes(localAQAgentI.getName());
      }
      else
/* 293 */         this.senderAgentName = null;
/* 294 */       if (localAQAgentI.getAddress() != null) {
/* 295 */         this.senderAgentAddress = this.meg.conv.StringToCharBytes(localAQAgentI.getAddress());
      }
      else
/* 298 */         this.senderAgentAddress = null;
/* 299 */       this.senderAgentProtocol = ((byte)localAQAgentI.getProtocol());
    }
    else
    {
/* 303 */       this.senderAgentName = null;
/* 304 */       this.senderAgentAddress = null;
/* 305 */       this.senderAgentProtocol = 0;
    }
    
/* 308 */     this.messageData = paramArrayOfByte1;
/* 309 */     this.messageOid = paramArrayOfByte2;
/* 310 */     this.isRawQueue = paramBoolean;
/* 311 */     if ((paramString != null) && (paramString.length() != 0)) {
/* 312 */       this.queueNameBytes = this.meg.conv.StringToCharBytes(paramString);
    } else {
/* 314 */       this.queueNameBytes = null;
    }
/* 316 */     this.attrRecipientList = ((AQAgentI[])this.messageProperties.getRecipientList());
    
/* 318 */     if ((this.attrRecipientList != null) && (this.attrRecipientList.length > 0))
    {
/* 320 */       this.recipientTextValues = new byte[this.attrRecipientList.length * 3][];
/* 321 */       this.recipientBinaryValues = new byte[this.attrRecipientList.length * 3][];
/* 322 */       this.recipientKeywords = new int[this.attrRecipientList.length * 3];
/* 323 */       for (int i = 0; i < this.attrRecipientList.length; i++)
      {
/* 325 */         if (this.attrRecipientList[i].getName() != null) {
/* 326 */           this.recipientTextValues[(3 * i)] = this.meg.conv.StringToCharBytes(this.attrRecipientList[i].getName());
        }
        
/* 329 */         if (this.attrRecipientList[i].getAddress() != null) {
/* 330 */           this.recipientTextValues[(3 * i + 1)] = this.meg.conv.StringToCharBytes(this.attrRecipientList[i].getAddress());
        }
        
/* 333 */         this.recipientBinaryValues[(3 * i + 2)] = new byte[1];
/* 334 */         this.recipientBinaryValues[(3 * i + 2)][0] = ((byte)this.attrRecipientList[i].getProtocol());
/* 335 */         this.recipientKeywords[(3 * i)] = (3 * i);
/* 336 */         this.recipientKeywords[(3 * i + 1)] = (3 * i + 1);
/* 337 */         this.recipientKeywords[(3 * i + 2)] = (3 * i + 2);
      }
    }
    
/* 341 */     String str3 = this.enqueueOptions.getTransformation();
/* 342 */     if ((str3 != null) && (str3.length() > 0))
    {
/* 344 */       this.nbExtensions = 1;
/* 345 */       this.extensionTextValues = new byte[this.nbExtensions][];
/* 346 */       this.extensionBinaryValues = new byte[this.nbExtensions][];
/* 347 */       this.extensionKeywords = new int[this.nbExtensions];
/* 348 */       this.extensionTextValues[0] = this.meg.conv.StringToCharBytes(str3);
/* 349 */       this.extensionBinaryValues[0] = null;
/* 350 */       this.extensionKeywords[0] = 196;
    }
    else {
/* 353 */       this.nbExtensions = 0; }
/* 354 */     this.outMsgid = null;
/* 355 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 366 */     if ((this.queueNameBytes != null) && (this.queueNameBytes.length != 0))
    {
/* 368 */       this.meg.marshalPTR();
/* 369 */       this.meg.marshalSWORD(this.queueNameBytes.length);
    }
    else
    {
/* 373 */       this.meg.marshalNULLPTR();
/* 374 */       this.meg.marshalSWORD(0);
    }
    
/* 377 */     this.aqm.initToDefaultValues();
/* 378 */     this.aqm.aqmpri = this.messageProperties.getPriority();
/* 379 */     this.aqm.aqmdel = this.messageProperties.getDelay();
/* 380 */     this.aqm.aqmexp = this.messageProperties.getExpiration();
/* 381 */     this.aqm.aqmcorBytes = this.aqmcorBytes;
/* 382 */     this.aqm.aqmeqnBytes = this.aqmeqnBytes;
/* 383 */     this.aqm.senderAgentName = this.senderAgentName;
/* 384 */     this.aqm.senderAgentAddress = this.senderAgentAddress;
/* 385 */     this.aqm.senderAgentProtocol = this.senderAgentProtocol;
/* 386 */     this.aqm.originalMsgId = this.messageProperties.getPreviousQueueMessageId();
/* 387 */     this.aqm.marshal();
    
/* 389 */     AQAgentI[] arrayOfAQAgentI = (AQAgentI[])this.messageProperties.getRecipientList();
    
/* 393 */     if ((arrayOfAQAgentI != null) && (arrayOfAQAgentI.length > 0))
    {
/* 395 */       this.meg.marshalPTR();
/* 396 */       this.meg.marshalSWORD(arrayOfAQAgentI.length * 3);
    }
    else
    {
/* 400 */       this.meg.marshalNULLPTR();
/* 401 */       this.meg.marshalSWORD(0);
    }
    
/* 405 */     this.meg.marshalSB4(this.enqueueOptions.getVisibility().getCode());
    
/* 408 */     int i = 0;
/* 409 */     if ((this.enqueueOptions.getRelativeMessageId() != null) && (this.enqueueOptions.getRelativeMessageId().length > 0))
    {
/* 412 */       i = 1;
/* 413 */       this.meg.marshalPTR();
/* 414 */       this.meg.marshalSWORD(this.enqueueOptions.getRelativeMessageId().length);
    }
    else
    {
/* 418 */       this.meg.marshalNULLPTR();
/* 419 */       this.meg.marshalSWORD(0);
    }
    
/* 422 */     this.meg.marshalSWORD(this.enqueueOptions.getSequenceDeviation().getCode());
    
/* 425 */     this.meg.marshalPTR();
/* 426 */     this.meg.marshalSWORD(16);
    
/* 428 */     this.meg.marshalUB2(1);
/* 429 */     if (!this.isRawQueue)
    {
/* 432 */       this.meg.marshalPTR();
      
/* 434 */       this.meg.marshalNULLPTR();
      
/* 436 */       this.meg.marshalUB4(0L);
    }
    else
    {
/* 441 */       this.meg.marshalNULLPTR();
      
/* 443 */       this.meg.marshalPTR();
      
/* 445 */       this.meg.marshalUB4(this.messageData.length);
    }
/* 447 */     if (this.enqueueOptions.getRetrieveMessageId())
    {
/* 449 */       this.retrieveMessageId = true;
      
/* 451 */       this.meg.marshalPTR();
      
/* 453 */       this.meg.marshalSWORD(16);
    }
    else
    {
/* 457 */       this.retrieveMessageId = false;
      
/* 459 */       this.meg.marshalNULLPTR();
      
/* 461 */       this.meg.marshalSWORD(0);
    }
    
/* 465 */     int j = 0;
/* 466 */     if (this.connection.autocommit)
/* 467 */       j = 32;
/* 468 */     if (this.enqueueOptions.getDeliveryMode() == AQEnqueueOptions.DeliveryMode.BUFFERED)
/* 469 */       j |= 0x2;
/* 470 */     this.meg.marshalUB4(j);
    
/* 472 */     this.meg.marshalNULLPTR();
    
/* 474 */     this.meg.marshalNULLPTR();
    
/* 478 */     if (this.nbExtensions > 0)
    {
/* 480 */       this.meg.marshalPTR();
/* 481 */       this.meg.marshalSWORD(this.nbExtensions);
    }
    else
    {
/* 486 */       this.meg.marshalNULLPTR();
      
/* 488 */       this.meg.marshalSWORD(0);
    }
    
/* 492 */     this.meg.marshalNULLPTR();
    
/* 494 */     this.meg.marshalSWORD(0);
    
/* 496 */     this.meg.marshalNULLPTR();
    
/* 498 */     this.meg.marshalSWORD(0);
    
/* 500 */     this.meg.marshalNULLPTR();
    
/* 502 */     if (this.connection.getTTCVersion() >= 4)
    {
/* 507 */       this.meg.marshalNULLPTR();
      
/* 509 */       this.meg.marshalSWORD(0);
      
/* 511 */       this.meg.marshalNULLPTR();
      
/* 513 */       this.meg.marshalSWORD(0);
      
/* 515 */       this.meg.marshalNULLPTR();
      
/* 517 */       this.meg.marshalSWORD(0);
      
/* 519 */       this.meg.marshalNULLPTR();
      
/* 521 */       this.meg.marshalNULLPTR();
    }
    
/* 526 */     if ((this.queueNameBytes != null) && (this.queueNameBytes.length != 0)) {
/* 527 */       this.meg.marshalCHR(this.queueNameBytes);
    }
/* 529 */     if ((arrayOfAQAgentI != null) && (arrayOfAQAgentI.length > 0))
    {
/* 531 */       this.meg.marshalKPDKV(this.recipientTextValues, this.recipientBinaryValues, this.recipientKeywords);
    }
    
/* 537 */     if (i != 0) {
/* 538 */       this.meg.marshalB1Array(this.enqueueOptions.getRelativeMessageId());
    }
    
/* 541 */     this.meg.marshalB1Array(this.messageOid);
    
/* 544 */     if (!this.isRawQueue)
    {
/* 546 */       this.toh.init(this.messageOid, this.messageData.length);
/* 547 */       this.toh.marshal(this.meg);
/* 548 */       this.meg.marshalCLR(this.messageData, 0, this.messageData.length);
    }
    else {
/* 551 */       this.meg.marshalB1Array(this.messageData);
    }
/* 553 */     if (this.nbExtensions > 0) {
/* 554 */       this.meg.marshalKPDKV(this.extensionTextValues, this.extensionBinaryValues, this.extensionKeywords);
    }
  }
  
  byte[] getMessageId()
  {
/* 562 */     return this.outMsgid;
  }
  
  void readRPA()
    throws SQLException, IOException
  {
/* 569 */     if (this.retrieveMessageId)
    {
/* 571 */       this.outMsgid = new byte[16];
/* 572 */       this.meg.unmarshalBuffer(this.outMsgid, 0, 16);
    }
/* 574 */     int i = this.meg.unmarshalUB2();
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 591 */     return null;
  }
  
/* 596 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4Caqe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */