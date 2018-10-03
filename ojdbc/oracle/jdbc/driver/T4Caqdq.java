package oracle.jdbc.driver;
import java.io.IOException;
import java.sql.SQLException;
import oracle.jdbc.aq.AQAgent;
import oracle.jdbc.aq.AQDequeueOptions;
import oracle.jdbc.aq.AQDequeueOptions.DeliveryFilter;
import oracle.jdbc.aq.AQDequeueOptions.DequeueMode;
import oracle.jdbc.aq.AQDequeueOptions.NavigationOption;
import oracle.jdbc.aq.AQDequeueOptions.VisibilityOption;
import oracle.jdbc.aq.AQMessageProperties.DeliveryMode;
import oracle.jdbc.aq.AQMessageProperties.MessageState;
import oracle.jdbc.internal.OracleConnection;
import oracle.sql.TIMESTAMP;
final class T4Caqdq
  extends T4CTTIfun
{
  T4CTTIaqm aqm;
  T4Ctoh toh;
  private String queueName;
  
  T4Caqdq(T4CConnection paramT4CConnection)
  {
/*  93 */     super(paramT4CConnection, (byte)3);
    
/*  95 */     setFunCode((short)122);
/*  96 */     this.toh = new T4Ctoh();
/*  97 */     this.aqm = new T4CTTIaqm(this.connection, this.toh);
  }
  
/* 102 */   private AQDequeueOptions dequeueOptions = null;
/* 103 */   private byte[] payloadToid = null;
/* 104 */   private byte[] queueNameBytes = null;
/* 105 */   private byte[] consumerNameBytes = null;
/* 106 */   private byte[] correlationBytes = null;
/* 107 */   private byte[] conditionBytes = null;
/* 108 */   private int nbExtensions = 0;
/* 109 */   private byte[][] extensionTextValues = (byte[][])null;
/* 110 */   private byte[][] extensionBinaryValues = (byte[][])null;
/* 111 */   private int[] extensionKeywords = null;
  
/* 114 */   private byte[] payload = null;
/* 115 */   private boolean hasAMessageBeenDequeued = false;
/* 116 */   private byte[] dequeuedMessageId = null;
/* 117 */   private boolean isRawQueue = false;
/* 118 */   private AQMessagePropertiesI properties = null;
  
  void doOAQDQ(String paramString, AQDequeueOptions paramAQDequeueOptions, byte[] paramArrayOfByte, boolean paramBoolean, AQMessagePropertiesI paramAQMessagePropertiesI)
    throws SQLException, IOException
  {
/* 129 */     this.queueName = paramString;
/* 130 */     this.dequeueOptions = paramAQDequeueOptions;
/* 131 */     this.payloadToid = paramArrayOfByte;
/* 132 */     this.isRawQueue = paramBoolean;
/* 133 */     this.properties = paramAQMessagePropertiesI;
    
/* 136 */     if ((this.queueName != null) && (this.queueName.length() != 0)) {
/* 137 */       this.queueNameBytes = this.meg.conv.StringToCharBytes(this.queueName);
    } else {
/* 139 */       this.queueNameBytes = null;
    }
/* 141 */     String str1 = this.dequeueOptions.getConsumerName();
    
/* 143 */     if ((str1 != null) && (str1.length() > 0))
    {
/* 145 */       this.consumerNameBytes = this.meg.conv.StringToCharBytes(str1);
    }
    else {
/* 148 */       this.consumerNameBytes = null;
    }
/* 150 */     String str2 = this.dequeueOptions.getCorrelation();
/* 151 */     if ((str2 != null) && (str2.length() != 0)) {
/* 152 */       this.correlationBytes = this.meg.conv.StringToCharBytes(str2);
    } else {
/* 154 */       this.correlationBytes = null;
    }
/* 156 */     String str3 = this.dequeueOptions.getCondition();
/* 157 */     if ((str3 != null) && (str3.length() > 0)) {
/* 158 */       this.conditionBytes = this.meg.conv.StringToCharBytes(str3);
    } else {
/* 160 */       this.conditionBytes = null;
    }
/* 162 */     String str4 = this.dequeueOptions.getTransformation();
/* 163 */     if ((str4 != null) && (str4.length() > 0))
    {
/* 165 */       this.nbExtensions = 1;
/* 166 */       this.extensionTextValues = new byte[this.nbExtensions][];
/* 167 */       this.extensionBinaryValues = new byte[this.nbExtensions][];
/* 168 */       this.extensionKeywords = new int[this.nbExtensions];
/* 169 */       this.extensionTextValues[0] = this.meg.conv.StringToCharBytes(str4);
/* 170 */       this.extensionBinaryValues[0] = null;
/* 171 */       this.extensionKeywords[0] = 196;
    }
    else {
/* 174 */       this.nbExtensions = 0;
    }
/* 176 */     this.hasAMessageBeenDequeued = false;
/* 177 */     this.dequeuedMessageId = null;
/* 178 */     this.payload = null;
    
/* 180 */     doRPC();
  }
  
  void marshal()
    throws IOException
  {
/* 191 */     if ((this.queueNameBytes != null) && (this.queueNameBytes.length != 0))
    {
/* 193 */       this.meg.marshalPTR();
/* 194 */       this.meg.marshalSWORD(this.queueNameBytes.length);
    }
    else
    {
/* 198 */       this.meg.marshalNULLPTR();
/* 199 */       this.meg.marshalSWORD(0);
    }
    
/* 204 */     this.meg.marshalPTR();
/* 205 */     this.meg.marshalPTR();
    
/* 209 */     this.meg.marshalPTR();
/* 210 */     this.meg.marshalPTR();
    
/* 214 */     if ((this.consumerNameBytes != null) && (this.consumerNameBytes.length != 0))
    {
/* 216 */       this.meg.marshalPTR();
/* 217 */       this.meg.marshalSWORD(this.consumerNameBytes.length);
    }
    else
    {
/* 221 */       this.meg.marshalNULLPTR();
/* 222 */       this.meg.marshalSWORD(0);
    }
    
/* 226 */     this.meg.marshalSB4(this.dequeueOptions.getDequeueMode().getCode());
    
/* 229 */     this.meg.marshalSB4(this.dequeueOptions.getNavigation().getCode());
    
/* 232 */     this.meg.marshalSB4(this.dequeueOptions.getVisibility().getCode());
    
/* 235 */     this.meg.marshalSB4(this.dequeueOptions.getWait());
    
/* 239 */     byte[] arrayOfByte = this.dequeueOptions.getDequeueMessageId();
/* 240 */     int i = 0;
/* 241 */     if ((arrayOfByte != null) && (arrayOfByte.length > 0))
    {
/* 243 */       this.meg.marshalPTR();
/* 244 */       this.meg.marshalSWORD(arrayOfByte.length);
/* 245 */       i = 1;
    }
    else
    {
/* 249 */       this.meg.marshalNULLPTR();
/* 250 */       this.meg.marshalSWORD(0);
    }
    
/* 255 */     if ((this.correlationBytes != null) && (this.correlationBytes.length != 0))
    {
/* 257 */       this.meg.marshalPTR();
/* 258 */       this.meg.marshalSWORD(this.correlationBytes.length);
    }
    else
    {
/* 262 */       this.meg.marshalNULLPTR();
/* 263 */       this.meg.marshalSWORD(0);
    }
    
/* 268 */     this.meg.marshalPTR();
/* 269 */     this.meg.marshalSWORD(this.payloadToid.length);
    
/* 272 */     this.meg.marshalUB2(1);
    
/* 275 */     this.meg.marshalPTR();
    
/* 280 */     if (this.dequeueOptions.getRetrieveMessageId())
    {
/* 282 */       this.meg.marshalPTR();
/* 283 */       this.meg.marshalSWORD(16);
    }
    else
    {
/* 287 */       this.meg.marshalNULLPTR();
/* 288 */       this.meg.marshalSWORD(0);
    }
    
/* 292 */     int j = 0;
/* 293 */     if (this.connection.autocommit)
/* 294 */       j = 32;
/* 295 */     if (this.dequeueOptions.getDeliveryFilter() == AQDequeueOptions.DeliveryFilter.BUFFERED) {
/* 296 */       j |= 0x2;
/* 297 */     } else if (this.dequeueOptions.getDeliveryFilter() == AQDequeueOptions.DeliveryFilter.PERSISTENT_OR_BUFFERED)
    {
/* 299 */       j |= 0x10; }
/* 300 */     this.meg.marshalUB4(j);
    
/* 304 */     if ((this.conditionBytes != null) && (this.conditionBytes.length > 0))
    {
/* 306 */       this.meg.marshalPTR();
/* 307 */       this.meg.marshalSWORD(this.conditionBytes.length);
    }
    else
    {
/* 311 */       this.meg.marshalNULLPTR();
/* 312 */       this.meg.marshalSWORD(0);
    }
    
/* 317 */     if (this.nbExtensions > 0)
    {
/* 319 */       this.meg.marshalPTR();
/* 320 */       this.meg.marshalSWORD(this.nbExtensions);
    }
    else
    {
/* 324 */       this.meg.marshalNULLPTR();
/* 325 */       this.meg.marshalSWORD(0);
    }
    
/* 330 */     if ((this.queueNameBytes != null) && (this.queueNameBytes.length != 0)) {
/* 331 */       this.meg.marshalCHR(this.queueNameBytes);
    }
    
/* 334 */     if ((this.consumerNameBytes != null) && (this.consumerNameBytes.length != 0)) {
/* 335 */       this.meg.marshalCHR(this.consumerNameBytes);
    }
    
/* 338 */     if (i != 0) {
/* 339 */       this.meg.marshalB1Array(arrayOfByte);
    }
    
/* 342 */     if ((this.correlationBytes != null) && (this.correlationBytes.length != 0)) {
/* 343 */       this.meg.marshalCHR(this.correlationBytes);
    }
    
/* 346 */     this.meg.marshalB1Array(this.payloadToid);
    
/* 348 */     if ((this.conditionBytes != null) && (this.conditionBytes.length > 0)) {
/* 349 */       this.meg.marshalCHR(this.conditionBytes);
    }
/* 351 */     if (this.nbExtensions > 0) {
/* 352 */       this.meg.marshalKPDKV(this.extensionTextValues, this.extensionBinaryValues, this.extensionKeywords);
    }
  }
  
  byte[] getPayload()
  {
/* 360 */     return this.payload;
  }
  
  boolean hasAMessageBeenDequeued()
  {
/* 366 */     return this.hasAMessageBeenDequeued;
  }
  
  byte[] getDequeuedMessageId()
  {
/* 372 */     return this.dequeuedMessageId;
  }
  
  void readRPA()
    throws SQLException, IOException
  {
/* 379 */     this.hasAMessageBeenDequeued = true;
    
/* 381 */     int i = (int)this.meg.unmarshalUB4();
/* 382 */     if (i > 0)
    {
/* 384 */       this.aqm.initToDefaultValues();
/* 385 */       this.aqm.receive();
/* 386 */       this.properties.setPriority(this.aqm.aqmpri);
/* 387 */       this.properties.setDelay(this.aqm.aqmdel);
/* 388 */       this.properties.setExpiration(this.aqm.aqmexp);
/* 389 */       if (this.aqm.aqmcorBytes != null)
      {
/* 391 */         localObject = this.meg.conv.CharBytesToString(this.aqm.aqmcorBytes, this.aqm.aqmcorBytesLength, true);
        
/* 393 */         this.properties.setCorrelation((String)localObject);
      }
/* 395 */       this.properties.setAttempts(this.aqm.aqmatt);
/* 396 */       if (this.aqm.aqmeqnBytes != null)
      {
/* 398 */         localObject = this.meg.conv.CharBytesToString(this.aqm.aqmeqnBytes, this.aqm.aqmeqnBytesLength, true);
        
/* 400 */         this.properties.setExceptionQueue((String)localObject);
      }
/* 402 */       this.properties.setMessageState(AQMessageProperties.MessageState.getMessageState(this.aqm.aqmsta));
/* 403 */       this.properties.setEnqueueTime(this.aqm.aqmeqt.timestampValue());
/* 404 */       Object localObject = new AQAgentI();
/* 405 */       if (this.aqm.senderAgentName != null) {
/* 406 */         ((AQAgentI)localObject).setName(this.meg.conv.CharBytesToString(this.aqm.senderAgentName, this.aqm.senderAgentNameLength, true));
      }
      
/* 410 */       if (this.aqm.senderAgentAddress != null) {
/* 411 */         ((AQAgentI)localObject).setAddress(this.meg.conv.CharBytesToString(this.aqm.senderAgentAddress, this.aqm.senderAgentAddressLength, true));
      }
      
/* 415 */       ((AQAgentI)localObject).setProtocol(this.aqm.senderAgentProtocol);
      
/* 417 */       this.properties.setSender((AQAgent)localObject);
/* 418 */       this.properties.setPreviousQueueMessageId(this.aqm.originalMsgId);
/* 419 */       this.properties.setDeliveryMode(AQMessageProperties.DeliveryMode.getDeliveryMode(this.aqm.aqmflg));
      
/* 422 */       if (this.aqm.aqmetiBytes != null)
      {
/* 424 */         String str = this.meg.conv.CharBytesToString(this.aqm.aqmetiBytes, this.aqm.aqmetiBytes.length, true);
        
/* 426 */         this.properties.setTransactionGroup(str);
      }
    }
    
/* 430 */     int j = (int)this.meg.unmarshalUB4();
    
/* 433 */     this.toh.unmarshal(this.meg);
/* 434 */     int k = this.toh.imageLength;
    
/* 437 */     if (k > 0)
    {
/* 440 */       int m = k;
      byte[] arrayOfByte2;
/* 442 */       int[] arrayOfInt; if (this.isRawQueue)
      {
/* 447 */         if (k > 4) {
/* 448 */           m -= 4;
        }
        
/* 452 */         m = Math.min(m, this.dequeueOptions.getMaximumBufferLength());
        
/* 455 */         arrayOfByte2 = new byte[m];
/* 456 */         arrayOfInt = new int[1];
/* 457 */         if (k > 4) {
/* 458 */           this.meg.unmarshalCLR(arrayOfByte2, 0, arrayOfInt, arrayOfByte2.length, 4);
        } else
/* 460 */           this.meg.unmarshalCLR(arrayOfByte2, 0, arrayOfInt, arrayOfByte2.length);
/* 461 */         this.payload = arrayOfByte2;
      }
      else
      {
/* 466 */         arrayOfByte2 = new byte[m];
/* 467 */         arrayOfInt = new int[1];
/* 468 */         this.meg.unmarshalCLR(arrayOfByte2, 0, arrayOfInt, arrayOfByte2.length);
/* 469 */         this.payload = arrayOfByte2;
      }
    }
    
/* 474 */     if (this.dequeueOptions.getRetrieveMessageId())
    {
/* 476 */       byte[] arrayOfByte1 = new byte[16];
/* 477 */       this.meg.unmarshalBuffer(arrayOfByte1, 0, 16);
/* 478 */       this.dequeuedMessageId = arrayOfByte1;
    }
  }
  
  void processError()
    throws SQLException
  {
/* 486 */     if (this.oer.retCode != 25228)
    {
/* 488 */       this.oer.processError();
    }
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 505 */     return null;
  }
  
/* 510 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4Caqdq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */