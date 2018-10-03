package oracle.jdbc.driver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import oracle.jdbc.aq.AQMessageProperties;
import oracle.jdbc.aq.AQMessageProperties.DeliveryMode;
import oracle.jdbc.aq.AQMessageProperties.MessageState;
import oracle.jdbc.aq.AQNotificationEvent;
import oracle.jdbc.aq.AQNotificationEvent.AdditionalEventType;
import oracle.jdbc.aq.AQNotificationEvent.EventType;
import oracle.sql.CharacterSet;
import oracle.sql.TIMESTAMP;
class NTFAQEvent
  extends AQNotificationEvent
{
  private String registrationString;
  private int namespace;
  private byte[] payload;
/*  50 */   private String queueName = null;
/*  51 */   private byte[] messageId = null;
/*  52 */   private String consumerName = null;
  private NTFConnection conn;
  private AQMessagePropertiesI msgProp;
/*  55 */   private AQNotificationEvent.EventType eventType = AQNotificationEvent.EventType.REGULAR;
/*  56 */   private AQNotificationEvent.AdditionalEventType additionalEventType = AQNotificationEvent.AdditionalEventType.NONE;
  private ByteBuffer dataBuffer;
/*  58 */   private boolean isReady = false;
  
  private short databaseVersion;
  
  NTFAQEvent(NTFConnection paramNTFConnection, short paramShort)
    throws IOException, InterruptedException
  {
/*  65 */     super(paramNTFConnection);
    
/*  67 */     this.conn = paramNTFConnection;
/*  68 */     int i = this.conn.readInt();
/*  69 */     byte[] arrayOfByte = new byte[i];
/*  70 */     this.conn.readBuffer(arrayOfByte, 0, i);
/*  71 */     this.dataBuffer = ByteBuffer.wrap(arrayOfByte);
/*  72 */     this.databaseVersion = paramShort;
  }
  
  private void initEvent()
    throws SQLException
  {
/*  79 */     int i = this.dataBuffer.get();
/*  80 */     int j = this.dataBuffer.getInt();
/*  81 */     byte[] arrayOfByte1 = new byte[j];
/*  82 */     this.dataBuffer.get(arrayOfByte1, 0, j);
/*  83 */     this.registrationString = this.conn.charset.toString(arrayOfByte1, 0, j);
    
/*  87 */     int k = this.dataBuffer.get();
/*  88 */     int m = this.dataBuffer.getInt();
/*  89 */     byte[] arrayOfByte2 = new byte[m];
/*  90 */     this.dataBuffer.get(arrayOfByte2, 0, m);
/*  91 */     this.namespace = arrayOfByte2[0];
    
/*  94 */     int n = this.dataBuffer.get();
/*  95 */     int i1 = this.dataBuffer.getInt();
/*  96 */     if (i1 > 0)
    {
/*  98 */       this.payload = new byte[i1];
/*  99 */       this.dataBuffer.get(this.payload, 0, i1);
    }
    else {
/* 102 */       this.payload = null;
    }
/* 104 */     if (this.dataBuffer.hasRemaining())
    {
/* 106 */       int i2 = 0;
/* 107 */       if (this.databaseVersion >= 10200)
      {
/* 110 */         i3 = this.dataBuffer.get();
/* 111 */         i4 = this.dataBuffer.getInt();
/* 112 */         i2 = this.dataBuffer.getInt();
      }
      
/* 116 */       int i3 = this.dataBuffer.get();
/* 117 */       int i4 = this.dataBuffer.getInt();
/* 118 */       byte[] arrayOfByte3 = new byte[i4];
/* 119 */       this.dataBuffer.get(arrayOfByte3, 0, i4);
/* 120 */       this.queueName = this.conn.charset.toString(arrayOfByte3, 0, i4);
      
/* 124 */       int i5 = this.dataBuffer.get();
/* 125 */       int i6 = this.dataBuffer.getInt();
/* 126 */       this.messageId = new byte[i6];
/* 127 */       this.dataBuffer.get(this.messageId, 0, i6);
      
/* 130 */       int i7 = this.dataBuffer.get();
/* 131 */       int i8 = this.dataBuffer.getInt();
/* 132 */       byte[] arrayOfByte4 = new byte[i8];
/* 133 */       this.dataBuffer.get(arrayOfByte4, 0, i8);
/* 134 */       this.consumerName = this.conn.charset.toString(arrayOfByte4, 0, i8);
      
/* 138 */       int i9 = this.dataBuffer.get();
/* 139 */       int i10 = this.dataBuffer.getInt();
/* 140 */       byte[] arrayOfByte5 = new byte[i10];
/* 141 */       this.dataBuffer.get(arrayOfByte5, 0, i10);
      
/* 144 */       int i11 = this.dataBuffer.get();
/* 145 */       int i12 = this.dataBuffer.getInt();
/* 146 */       int i13 = this.dataBuffer.getInt();
/* 147 */       if (arrayOfByte5[0] == 1)
/* 148 */         i13 = -i13;
/* 149 */       int i14 = i13;
      
/* 152 */       int i15 = this.dataBuffer.get();
/* 153 */       int i16 = this.dataBuffer.getInt();
/* 154 */       int i17 = this.dataBuffer.getInt();
      
/* 157 */       int i18 = this.dataBuffer.get();
/* 158 */       int i19 = this.dataBuffer.getInt();
/* 159 */       byte[] arrayOfByte6 = new byte[i19];
/* 160 */       this.dataBuffer.get(arrayOfByte6, 0, i19);
      
/* 163 */       int i20 = this.dataBuffer.get();
/* 164 */       int i21 = this.dataBuffer.getInt();
/* 165 */       int i22 = this.dataBuffer.getInt();
/* 166 */       if (arrayOfByte6[0] == 1)
/* 167 */         i22 = -i22;
/* 168 */       int i23 = i22;
      
/* 171 */       int i24 = this.dataBuffer.get();
/* 172 */       int i25 = this.dataBuffer.getInt();
/* 173 */       int i26 = this.dataBuffer.getInt();
      
/* 176 */       int i27 = this.dataBuffer.get();
/* 177 */       int i28 = this.dataBuffer.getInt();
/* 178 */       byte[] arrayOfByte7 = new byte[i28];
/* 179 */       this.dataBuffer.get(arrayOfByte7, 0, i28);
/* 180 */       TIMESTAMP localTIMESTAMP = new TIMESTAMP(arrayOfByte7);
      
/* 183 */       int i29 = this.dataBuffer.get();
/* 184 */       int i30 = this.dataBuffer.getInt();
/* 185 */       byte[] arrayOfByte8 = new byte[i30];
/* 186 */       this.dataBuffer.get(arrayOfByte8, 0, i30);
/* 187 */       int i31 = arrayOfByte8[0];
      
/* 190 */       int i32 = this.dataBuffer.get();
/* 191 */       int i33 = this.dataBuffer.getInt();
/* 192 */       byte[] arrayOfByte9 = new byte[i33];
/* 193 */       this.dataBuffer.get(arrayOfByte9, 0, i33);
/* 194 */       String str1 = this.conn.charset.toString(arrayOfByte9, 0, i33);
      
/* 198 */       int i34 = this.dataBuffer.get();
/* 199 */       int i35 = this.dataBuffer.getInt();
/* 200 */       byte[] arrayOfByte10 = new byte[i35];
/* 201 */       this.dataBuffer.get(arrayOfByte10, 0, i35);
/* 202 */       String str2 = this.conn.charset.toString(arrayOfByte10, 0, i35);
      
/* 206 */       int i36 = this.dataBuffer.get();
/* 207 */       int i37 = this.dataBuffer.getInt();
/* 208 */       byte[] arrayOfByte11 = null;
/* 209 */       if (i37 > 0)
      {
/* 211 */         arrayOfByte11 = new byte[i37];
/* 212 */         this.dataBuffer.get(arrayOfByte11, 0, i37);
      }
      
/* 216 */       int i38 = this.dataBuffer.get();
/* 217 */       int i39 = this.dataBuffer.getInt();
/* 218 */       byte[] arrayOfByte12 = new byte[i39];
/* 219 */       this.dataBuffer.get(arrayOfByte12, 0, i39);
/* 220 */       String str3 = this.conn.charset.toString(arrayOfByte12, 0, i39);
      
/* 224 */       int i40 = this.dataBuffer.get();
/* 225 */       int i41 = this.dataBuffer.getInt();
/* 226 */       byte[] arrayOfByte13 = new byte[i41];
/* 227 */       this.dataBuffer.get(arrayOfByte13, 0, i41);
/* 228 */       String str4 = this.conn.charset.toString(arrayOfByte13, 0, i41);
      
/* 232 */       int i42 = this.dataBuffer.get();
/* 233 */       int i43 = this.dataBuffer.getInt();
/* 234 */       int i44 = this.dataBuffer.get();
      
/* 239 */       this.msgProp = new AQMessagePropertiesI();
/* 240 */       this.msgProp.setAttempts(i26);
/* 241 */       this.msgProp.setCorrelation(str2);
/* 242 */       this.msgProp.setDelay(i17);
/* 243 */       this.msgProp.setEnqueueTime(localTIMESTAMP.timestampValue());
/* 244 */       this.msgProp.setMessageState(AQMessageProperties.MessageState.getMessageState(i31));
/* 245 */       if (this.databaseVersion >= 10200)
/* 246 */         this.msgProp.setDeliveryMode(AQMessageProperties.DeliveryMode.getDeliveryMode(i2));
/* 247 */       this.msgProp.setPreviousQueueMessageId(arrayOfByte11);
/* 248 */       AQAgentI localAQAgentI = new AQAgentI();
/* 249 */       localAQAgentI.setAddress(str4);
/* 250 */       localAQAgentI.setName(str3);
/* 251 */       localAQAgentI.setProtocol(i44);
/* 252 */       this.msgProp.setSender(localAQAgentI);
      
/* 254 */       this.msgProp.setPriority(i14);
/* 255 */       this.msgProp.setExpiration(i23);
/* 256 */       this.msgProp.setExceptionQueue(str1);
    }
/* 258 */     this.isReady = true;
  }
  
  public AQMessageProperties getMessageProperties()
    throws SQLException
  {
/* 265 */     if (!this.isReady)
/* 266 */       initEvent();
/* 267 */     return this.msgProp;
  }
  
  public String getRegistration()
    throws SQLException
  {
/* 274 */     if (!this.isReady)
/* 275 */       initEvent();
/* 276 */     return this.registrationString;
  }
  
  public AQNotificationEvent.EventType getEventType()
  {
/* 283 */     return this.eventType;
  }
  
  public AQNotificationEvent.AdditionalEventType getAdditionalEventType()
  {
/* 290 */     return this.additionalEventType;
  }
  
  void setEventType(AQNotificationEvent.EventType paramEventType)
    throws IOException
  {
/* 297 */     this.eventType = paramEventType;
  }
  
  void setAdditionalEventType(AQNotificationEvent.AdditionalEventType paramAdditionalEventType)
  {
/* 304 */     this.additionalEventType = paramAdditionalEventType;
  }
  
  public byte[] getPayload()
    throws SQLException
  {
/* 311 */     if (!this.isReady)
/* 312 */       initEvent();
/* 313 */     return this.payload;
  }
  
  public String getQueueName()
    throws SQLException
  {
/* 320 */     if (!this.isReady)
/* 321 */       initEvent();
/* 322 */     return this.queueName;
  }
  
  public byte[] getMessageId()
    throws SQLException
  {
/* 329 */     if (!this.isReady)
/* 330 */       initEvent();
/* 331 */     return this.messageId;
  }
  
  public String getConsumerName()
    throws SQLException
  {
/* 338 */     if (!this.isReady)
/* 339 */       initEvent();
/* 340 */     return this.consumerName;
  }
  
  public String getConnectionInformation()
  {
/* 347 */     return this.conn.connectionDescription;
  }
  
  public String toString()
  {
/* 355 */     if (!this.isReady)
    {
      try
      {
/* 359 */         initEvent();
      }
      catch (SQLException localSQLException)
      {
/* 363 */         return localSQLException.getMessage();
      }
    }
/* 366 */     StringBuffer localStringBuffer = new StringBuffer();
/* 367 */     localStringBuffer.append("Connection information  : " + this.conn.connectionDescription + "\n");
/* 368 */     localStringBuffer.append("Event type              : " + this.eventType + "\n");
/* 369 */     if (this.additionalEventType != AQNotificationEvent.AdditionalEventType.NONE)
/* 370 */       localStringBuffer.append("Additional event type   : " + this.additionalEventType + "\n");
/* 371 */     localStringBuffer.append("Namespace               : " + this.namespace + "\n");
/* 372 */     localStringBuffer.append("Registration            : " + this.registrationString + "\n");
/* 373 */     localStringBuffer.append("Queue name              : " + this.queueName + "\n");
/* 374 */     localStringBuffer.append("Consumer name           : " + this.consumerName + "\n");
/* 375 */     if (this.payload != null)
    {
/* 377 */       localStringBuffer.append("Payload length          : " + this.payload.length + "\n");
/* 378 */       localStringBuffer.append("Payload (first 50 bytes): " + byteBufferToHexString(this.payload, 50) + "\n");
    }
    else {
/* 381 */       localStringBuffer.append("Payload                 : null\n"); }
/* 382 */     localStringBuffer.append("Message ID              : " + byteBufferToHexString(this.messageId, 50) + "\n");
/* 383 */     if (this.msgProp != null)
/* 384 */       localStringBuffer.append(this.msgProp.toString());
/* 385 */     return localStringBuffer.toString();
  }
  
  static final String byteBufferToHexString(byte[] paramArrayOfByte, int paramInt)
  {
/* 391 */     if (paramArrayOfByte == null) {
/* 392 */       return null;
    }
/* 394 */     int i = 0;
/* 395 */     int j = 1;
/* 396 */     StringBuffer localStringBuffer = new StringBuffer();
/* 397 */     while ((i < paramArrayOfByte.length) && (i < paramInt))
    {
/* 399 */       if (j == 0) {
/* 400 */         localStringBuffer.append(' ');
      } else
/* 402 */         j = 0;
/* 403 */       str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
/* 404 */       if (str.length() == 1)
/* 405 */         str = "0" + str;
/* 406 */       localStringBuffer.append(str);
/* 407 */       i++;
    }
/* 409 */     String str = localStringBuffer.toString();
/* 410 */     return str;
  }
  
/* 415 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFAQEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */