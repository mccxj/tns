package oracle.jdbc.driver;
import java.io.IOException;
import java.nio.ByteBuffer;
import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeEvent.AdditionalEventType;
import oracle.jdbc.dcn.DatabaseChangeEvent.EventType;
import oracle.jdbc.dcn.QueryChangeDescription;
import oracle.jdbc.dcn.TableChangeDescription;
import oracle.sql.CharacterSet;
class NTFDCNEvent
  extends DatabaseChangeEvent
{
/*  50 */   private int notifVersion = 0;
/*  51 */   private int notifRegid = 0;
  private DatabaseChangeEvent.EventType eventType;
/*  53 */   private DatabaseChangeEvent.AdditionalEventType additionalEventType = DatabaseChangeEvent.AdditionalEventType.NONE;
/*  54 */   private String databaseName = null;
/*  55 */   private byte[] notifXid = new byte[8];
/*  56 */   private int notifScn1 = 0;
/*  57 */   private int notifScn2 = 0;
  
/*  59 */   private int numberOfTables = 0;
/*  60 */   private NTFDCNTableChanges[] tcdesc = null;
  
/*  63 */   private int numberOfQueries = 0;
/*  64 */   private NTFDCNQueryChanges[] qdesc = null;
  
  private long registrationId;
  private NTFConnection conn;
  private int csid;
/*  69 */   private boolean isReady = false;
  
  private ByteBuffer dataBuffer;
/*  72 */   private boolean isDeregistrationEvent = false;
  
  private short databaseVersion;
  
  NTFDCNEvent(NTFConnection paramNTFConnection, short paramShort)
    throws IOException, InterruptedException
  {
/*  79 */     super(paramNTFConnection);
    
/*  81 */     this.conn = paramNTFConnection;
/*  82 */     this.csid = this.conn.charset.getOracleId();
    
/*  85 */     int i = this.conn.readInt();
/*  86 */     byte[] arrayOfByte = new byte[i];
/*  87 */     this.conn.readBuffer(arrayOfByte, 0, i);
/*  88 */     this.dataBuffer = ByteBuffer.wrap(arrayOfByte);
/*  89 */     this.databaseVersion = paramShort;
  }
  
  private void initEvent()
  {
/*  97 */     int i = this.dataBuffer.get();
/*  98 */     int j = this.dataBuffer.getInt();
/*  99 */     byte[] arrayOfByte1 = new byte[j];
/* 100 */     this.dataBuffer.get(arrayOfByte1, 0, j);
    
/* 104 */     String str = null;
    try {
/* 106 */       str = new String(arrayOfByte1, "UTF-8");
    }
    catch (Exception localException1) {}
/* 109 */     str = str.replaceFirst("CHNF", "");
/* 110 */     this.registrationId = Long.parseLong(str);
    
/* 113 */     int k = this.dataBuffer.get();
/* 114 */     int m = this.dataBuffer.getInt();
/* 115 */     byte[] arrayOfByte2 = new byte[m];
/* 116 */     this.dataBuffer.get(arrayOfByte2, 0, m);
    
/* 119 */     int n = this.dataBuffer.get();
/* 120 */     int i1 = this.dataBuffer.getInt();
/* 121 */     if (this.dataBuffer.hasRemaining())
    {
/* 125 */       this.notifVersion = this.dataBuffer.getShort();
/* 126 */       this.notifRegid = this.dataBuffer.getInt();
/* 127 */       this.eventType = DatabaseChangeEvent.EventType.getEventType(this.dataBuffer.getInt());
/* 128 */       int i2 = this.dataBuffer.getShort();
/* 129 */       byte[] arrayOfByte3 = new byte[i2];
/* 130 */       this.dataBuffer.get(arrayOfByte3, 0, i2);
      try {
/* 132 */         this.databaseName = new String(arrayOfByte3, "UTF-8");
      }
      catch (Exception localException2) {}
      
/* 141 */       this.dataBuffer.get(this.notifXid);
      
/* 143 */       this.notifScn1 = this.dataBuffer.getInt();
/* 144 */       this.notifScn2 = this.dataBuffer.getShort();
      int i3;
/* 146 */       if (this.eventType == DatabaseChangeEvent.EventType.OBJCHANGE)
      {
/* 148 */         this.numberOfTables = this.dataBuffer.getShort();
/* 149 */         this.tcdesc = new NTFDCNTableChanges[this.numberOfTables];
/* 150 */         for (i3 = 0; i3 < this.tcdesc.length; i3++) {
/* 151 */           this.tcdesc[i3] = new NTFDCNTableChanges(this.dataBuffer, this.csid);
        }
/* 153 */       } else if (this.eventType == DatabaseChangeEvent.EventType.QUERYCHANGE)
      {
/* 155 */         this.numberOfQueries = this.dataBuffer.getShort();
/* 156 */         this.qdesc = new NTFDCNQueryChanges[this.numberOfQueries];
        
/* 165 */         for (i3 = 0; i3 < this.numberOfQueries; i3++)
        {
/* 167 */           this.qdesc[i3] = new NTFDCNQueryChanges(this.dataBuffer, this.csid);
        }
      }
    }
/* 171 */     this.isReady = true;
  }
  
  public String getDatabaseName()
  {
/* 179 */     if (!this.isReady)
/* 180 */       initEvent();
/* 181 */     return this.databaseName;
  }
  
  public TableChangeDescription[] getTableChangeDescription()
  {
/* 188 */     if (!this.isReady)
/* 189 */       initEvent();
/* 190 */     if (this.eventType == DatabaseChangeEvent.EventType.OBJCHANGE)
    {
/* 192 */       return this.tcdesc;
    }
    
/* 195 */     return null;
  }
  
  public QueryChangeDescription[] getQueryChangeDescription()
  {
/* 202 */     if (!this.isReady)
/* 203 */       initEvent();
/* 204 */     if (this.eventType == DatabaseChangeEvent.EventType.QUERYCHANGE)
    {
/* 206 */       return this.qdesc;
    }
    
/* 209 */     return null;
  }
  
  public byte[] getTransactionId()
  {
/* 216 */     if (!this.isReady)
/* 217 */       initEvent();
/* 218 */     return this.notifXid;
  }
  
  public String getTransactionId(boolean paramBoolean)
  {
/* 226 */     if (!this.isReady)
/* 227 */       initEvent();
    int i;
    int j;
/* 230 */     long l; if (!paramBoolean)
    {
/* 235 */       i = (this.notifXid[0] & 0xFF) << 8 | this.notifXid[1] & 0xFF;
      
/* 238 */       j = (this.notifXid[2] & 0xFF) << 8 | this.notifXid[3] & 0xFF;
      
/* 241 */       l = ((this.notifXid[4] & 0xFF) << 24 | (this.notifXid[5] & 0xFF) << 16 | (this.notifXid[6] & 0xFF) << 8 | this.notifXid[7] & 0xFF) & 0xFFFFFFFF;
    }
    else
    {
/* 250 */       i = (this.notifXid[1] & 0xFF) << 8 | this.notifXid[0] & 0xFF;
      
/* 252 */       j = (this.notifXid[3] & 0xFF) << 8 | this.notifXid[2] & 0xFF;
      
/* 254 */       l = ((this.notifXid[7] & 0xFF) << 24 | (this.notifXid[6] & 0xFF) << 16 | (this.notifXid[5] & 0xFF) << 8 | this.notifXid[4] & 0xFF) & 0xFFFFFFFF;
    }
    
/* 261 */     String str = "" + i + "." + j + "." + l;
/* 262 */     return str;
  }
  
  void setEventType(DatabaseChangeEvent.EventType paramEventType)
    throws IOException
  {
/* 270 */     if (!this.isReady)
/* 271 */       initEvent();
/* 272 */     this.eventType = paramEventType;
/* 273 */     if (this.eventType == DatabaseChangeEvent.EventType.DEREG) {
/* 274 */       this.isDeregistrationEvent = true;
    }
  }
  
  void setAdditionalEventType(DatabaseChangeEvent.AdditionalEventType paramAdditionalEventType)
  {
/* 281 */     this.additionalEventType = paramAdditionalEventType;
  }
  
  public DatabaseChangeEvent.EventType getEventType()
  {
/* 288 */     if (!this.isReady)
/* 289 */       initEvent();
/* 290 */     return this.eventType;
  }
  
  public DatabaseChangeEvent.AdditionalEventType getAdditionalEventType()
  {
/* 297 */     return this.additionalEventType;
  }
  
  boolean isDeregistrationEvent()
  {
/* 306 */     return this.isDeregistrationEvent;
  }
  
  public String getConnectionInformation()
  {
/* 313 */     return this.conn.connectionDescription;
  }
  
  public int getRegistrationId()
  {
/* 320 */     if (!this.isReady)
/* 321 */       initEvent();
/* 322 */     return (int)this.registrationId;
  }
  
  public long getRegId()
  {
/* 328 */     if (!this.isReady)
/* 329 */       initEvent();
/* 330 */     return this.registrationId;
  }
  
  public String toString()
  {
/* 337 */     if (!this.isReady)
/* 338 */       initEvent();
/* 339 */     StringBuffer localStringBuffer = new StringBuffer();
/* 340 */     localStringBuffer.append("Connection information  : " + this.conn.connectionDescription + "\n");
/* 341 */     localStringBuffer.append("Registration ID         : " + this.registrationId + "\n");
/* 342 */     localStringBuffer.append("Notification version    : " + this.notifVersion + "\n");
/* 343 */     localStringBuffer.append("Event type              : " + this.eventType + "\n");
/* 344 */     if (this.additionalEventType != DatabaseChangeEvent.AdditionalEventType.NONE)
/* 345 */       localStringBuffer.append("Additional event type   : " + this.additionalEventType + "\n");
/* 346 */     if (this.databaseName != null) {
/* 347 */       localStringBuffer.append("Database name           : " + this.databaseName + "\n");
    }
    
/* 351 */     TableChangeDescription[] arrayOfTableChangeDescription = getTableChangeDescription();
/* 352 */     if (arrayOfTableChangeDescription != null)
    {
/* 354 */       localStringBuffer.append("Table Change Description (length=" + this.numberOfTables + ")\n");
/* 355 */       for (int i = 0; i < arrayOfTableChangeDescription.length; i++)
/* 356 */         localStringBuffer.append(arrayOfTableChangeDescription[i].toString());
    }
/* 358 */     QueryChangeDescription[] arrayOfQueryChangeDescription = getQueryChangeDescription();
/* 359 */     if (arrayOfQueryChangeDescription != null)
    {
/* 361 */       localStringBuffer.append("Query Change Description (length=" + this.numberOfQueries + ")\n");
/* 362 */       for (int j = 0; j < arrayOfQueryChangeDescription.length; j++) {
/* 363 */         localStringBuffer.append(arrayOfQueryChangeDescription[j].toString());
      }
    }
/* 366 */     return localStringBuffer.toString();
  }
  
/* 370 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/NTFDCNEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */