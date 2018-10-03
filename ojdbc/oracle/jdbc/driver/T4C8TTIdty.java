/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Date;
/*      */ import java.util.TimeZone;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4C8TTIdty
/*      */   extends T4CTTIMsg
/*      */ {
/*   55 */   short cliRIN = 1; short cliROUT = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   68 */   byte cliFlags = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  241 */   byte[] jdbcThinCompileTimeCapabilities = { 6, 1, 0, 0, 10, 1, 1, 6, 1, 1, 1, 1, 1, 1, 0, 41, -112, 3, 7, 3, 0, 1, 0, 79, 1, 55, 4, 1, 0, 0, 0, 12, 0, 0, 6, 0, 3, -125 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  291 */   static byte KPCCAP_RTB_TTC_ZCPY = 1;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  297 */   byte[] jdbcThinRuntimeCapabilities = { 2, 1, 0, 0, 0, 0, 0 };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   T4C8TTIdty(T4CConnection paramT4CConnection, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, boolean paramBoolean1, boolean paramBoolean2)
/*      */     throws SQLException, IOException
/*      */   {
/*  470 */     super(paramT4CConnection, (byte)2);
/*      */     
/*      */ 
/*  473 */     this.cliRIN = this.meg.conv.getClientCharSet();
/*  474 */     this.cliROUT = this.meg.conv.getClientCharSet();
/*  475 */     this.cliFlags = this.meg.types.getFlags();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  482 */     if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length <= 27) || (paramArrayOfByte1[27] == 0))
/*      */     {
/*      */ 
/*  485 */       this.jdbcThinCompileTimeCapabilities[27] = 0;
/*      */     }
/*      */     
/*  488 */     if (((paramArrayOfByte1 != null) && ((paramArrayOfByte1[4] & 0x8) == 0)) || (paramBoolean1))
/*      */     {
/*      */ 
/*  491 */       this.jdbcThinCompileTimeCapabilities[4] = 0;
/*      */     }
/*      */     
/*  494 */     if (!this.connection.enableDataInLocator) {
/*  495 */       byte[] tmp375_370 = this.jdbcThinCompileTimeCapabilities;tmp375_370[23] = ((byte)(tmp375_370[23] & 0xFFFFFFFB));
/*      */     }
/*      */     
/*  498 */     if (paramBoolean2) {
/*  499 */       byte[] tmp393_388 = this.jdbcThinRuntimeCapabilities;tmp393_388[6] = ((byte)(tmp393_388[6] | KPCCAP_RTB_TTC_ZCPY));
/*      */     }
/*  501 */     if ((paramArrayOfByte2 == null) || (paramArrayOfByte2.length < 1) || ((paramArrayOfByte2[1] & 0x1) != 1))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  506 */       int tmp425_424 = 1; byte[] tmp425_421 = this.jdbcThinRuntimeCapabilities;tmp425_421[tmp425_424] = ((byte)(tmp425_421[tmp425_424] & 0xFFFFFFFE));
/*      */     }
/*      */     
/*  509 */     if ((paramArrayOfByte1 == null) || (paramArrayOfByte1.length <= 37) || ((paramArrayOfByte1[37] & 0x2) != 2))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  514 */       byte[] tmp459_454 = this.jdbcThinCompileTimeCapabilities;tmp459_454[37] = ((byte)(tmp459_454[37] & 0xFFFFFFFD)); int 
/*      */       
/*  516 */         tmp471_470 = 1; byte[] tmp471_467 = this.jdbcThinRuntimeCapabilities;tmp471_467[tmp471_470] = ((byte)(tmp471_467[tmp471_470] & 0xFFFFFFFE));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  529 */     if ((paramArrayOfByte1 != null) && (paramArrayOfByte1.length > 7) && (paramArrayOfByte1[7] == 5))
/*      */     {
/*      */ 
/*      */ 
/*  533 */       this.runtimeTypeAndRep = typeAndRepFor1100;
/*      */     }
/*      */     else
/*      */     {
/*  537 */       this.runtimeTypeAndRep = typeAndRep;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void marshalTypeReps()
/*      */     throws SQLException, IOException
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  558 */     if (this.jdbcThinCompileTimeCapabilities[27] == 0)
/*      */     {
/*      */ 
/*  561 */       for (i = 1; i < this.runtimeTypeAndRep[0]; i++)
/*  562 */         this.meg.marshalUB1((short)(byte)(this.runtimeTypeAndRep[i] & 0xFF));
/*  563 */       this.meg.marshalUB1((short)0);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/*  570 */       i = this.meg.types.getRep((byte)1);
/*  571 */       this.meg.types.setRep((byte)1, (byte)0);
/*  572 */       for (int j = 1; j < this.runtimeTypeAndRep[0]; j++)
/*  573 */         this.meg.marshalUB2(this.runtimeTypeAndRep[j]);
/*  574 */       this.meg.marshalUB2(0);
/*  575 */       this.meg.types.setRep((byte)1, i);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   void doRPC()
/*      */     throws SQLException, IOException
/*      */   {
/*  583 */     marshal();
/*  584 */     receive();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void marshal()
/*      */     throws SQLException, IOException
/*      */   {
/*  599 */     marshalTTCcode();
/*      */     
/*  601 */     this.meg.marshalUB2(this.cliRIN);
/*  602 */     this.meg.marshalUB2(this.cliROUT);
/*  603 */     this.meg.marshalUB1((short)this.cliFlags);
/*      */     
/*  605 */     if (this.meg.proSvrVer >= 6)
/*      */     {
/*      */ 
/*  608 */       this.meg.marshalUB1((short)this.jdbcThinCompileTimeCapabilities.length);
/*  609 */       this.meg.marshalB1Array(this.jdbcThinCompileTimeCapabilities);
/*  610 */       this.meg.marshalUB1((short)this.jdbcThinRuntimeCapabilities.length);
/*  611 */       this.meg.marshalB1Array(this.jdbcThinRuntimeCapabilities);
/*      */       
/*      */ 
/*  614 */       if ((this.jdbcThinRuntimeCapabilities[1] & 0x1) == 1)
/*      */       {
/*      */ 
/*  617 */         this.meg.marshalB1Array(getTZBytes());
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  622 */         if ((this.jdbcThinCompileTimeCapabilities[37] & 0x2) == 2)
/*      */         {
/*  624 */           this.meg.marshalB1Array(new byte[] { 0, 0, 0, 0 });
/*      */         }
/*      */       }
/*      */     }
/*  628 */     marshalTypeReps();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   byte[] getTZBytes()
/*      */   {
/*  635 */     TimeZone localTimeZone = TimeZone.getDefault();
/*  636 */     int i = localTimeZone.getRawOffset();
/*  637 */     int j = i / 3600000;
/*  638 */     int k = i / 60000 % 60;
/*      */     
/*  640 */     if ((localTimeZone.useDaylightTime()) && (localTimeZone.inDaylightTime(new Date()))) {
/*  641 */       j++;
/*      */     }
/*  643 */     byte[] arrayOfByte = { Byte.MIN_VALUE, 0, 0, 0, (byte)(j + 60 & 0xFF), (byte)(k + 60 & 0xFF), Byte.MIN_VALUE, 0, 0, 0, 0 };
/*      */     
/*      */ 
/*      */ 
/*  647 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void receive()
/*      */     throws SQLException, IOException
/*      */   {
/*      */     SQLException localSQLException;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  677 */     if (this.meg.unmarshalUB1() != 2)
/*      */     {
/*  679 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 401);
/*  680 */       localSQLException.fillInStackTrace();
/*  681 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  687 */     if (!validTypeReps())
/*      */     {
/*  689 */       localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 411);
/*  690 */       localSQLException.fillInStackTrace();
/*  691 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  696 */     setBasicTypes(this.meg.types);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   boolean validTypeReps()
/*      */     throws SQLException, IOException
/*      */   {
/*  739 */     int j = 0;
/*  740 */     int k = 0;
/*  741 */     byte b = 0;
/*  742 */     if (this.jdbcThinCompileTimeCapabilities[27] == 1)
/*      */     {
/*  744 */       b = this.meg.types.getRep((byte)1);
/*  745 */       this.meg.types.setRep((byte)1, (byte)0);
/*      */     }
/*      */     
/*  748 */     if (this.jdbcThinRuntimeCapabilities[1] == 1)
/*      */     {
/*  750 */       int m = 11;
/*  751 */       byte[] arrayOfByte1 = this.meg.unmarshalNBytes(m);
/*      */       
/*  753 */       if ((this.jdbcThinCompileTimeCapabilities[37] & 0x2) == 2)
/*      */       {
/*      */ 
/*      */ 
/*  757 */         byte[] arrayOfByte2 = this.meg.unmarshalNBytes(4);
/*  758 */         this.connection.timeZoneVersionNumber = ((arrayOfByte2[0] & 0xFF) << 24 | (arrayOfByte2[1] & 0xFF) << 16 | (arrayOfByte2[2] & 0xFF) << 8 | arrayOfByte2[3] & 0xFF);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     for (;;)
/*      */     {
/*      */       int i;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  773 */       if (this.jdbcThinCompileTimeCapabilities[27] == 1) {
/*  774 */         i = (short)this.meg.unmarshalUB2();
/*      */       } else {
/*  776 */         i = this.meg.unmarshalUB1();
/*      */       }
/*  778 */       if (j == 0)
/*      */       {
/*  780 */         if (i == 0)
/*      */         {
/*  782 */           if (this.jdbcThinCompileTimeCapabilities[27] == 1)
/*  783 */             this.meg.types.setRep((byte)1, b);
/*  784 */           return true;
/*      */         }
/*      */         
/*  787 */         j = 1;
/*      */       }
/*      */       else
/*      */       {
/*  791 */         switch (k)
/*      */         {
/*      */ 
/*      */         case 0: 
/*  795 */           if (i == 0) {
/*  796 */             j = 0;
/*      */           } else {
/*  798 */             k = 1;
/*      */           }
/*  800 */           break;
/*      */         
/*      */         case 1: 
/*  803 */           k = 0;
/*      */         }
/*      */         
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   void setBasicTypes(T4CTypeRep paramT4CTypeRep)
/*      */     throws SQLException
/*      */   {
/*  822 */     paramT4CTypeRep.setRep((byte)0, (byte)0);
/*  823 */     paramT4CTypeRep.setRep((byte)1, (byte)1);
/*  824 */     paramT4CTypeRep.setRep((byte)2, (byte)1);
/*  825 */     paramT4CTypeRep.setRep((byte)3, (byte)1);
/*  826 */     paramT4CTypeRep.setRep((byte)4, (byte)1);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1232 */   static short[] typeAndRep = new short['ु'];
/* 1233 */   static short[] typeAndRepFor1100 = null;
/* 1234 */   short[] runtimeTypeAndRep = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static void typeRep(short paramShort1, short paramShort2, short paramShort3, short paramShort4)
/*      */   {
/* 1244 */     if (typeAndRep.length < typeAndRep[0] + 4)
/*      */     {
/* 1246 */       short[] arrayOfShort = new short[typeAndRep.length * 2];
/* 1247 */       System.arraycopy(typeAndRep, 0, arrayOfShort, 0, typeAndRep[0] + 1);
/* 1248 */       typeAndRep = null;
/* 1249 */       typeAndRep = arrayOfShort;
/*      */     }
/* 1251 */     int i = typeAndRep[0];
/* 1252 */     typeAndRep[i] = paramShort1;
/* 1253 */     typeAndRep[(i + 1)] = paramShort3;
/* 1254 */     if (paramShort3 == 0)
/*      */     {
/* 1256 */       typeAndRep[0] = ((short)(i + 2));
/*      */     }
/*      */     else
/*      */     {
/* 1260 */       typeAndRep[(i + 2)] = paramShort4;
/* 1261 */       typeAndRep[(i + 3)] = 0;
/* 1262 */       typeAndRep[0] = ((short)(i + 4));
/*      */     }
/*      */   }
/*      */   
/*      */   static
/*      */   {
/* 1238 */     typeAndRep[0] = 1;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1270 */     typeRep((short)1, (short)0, (short)1, (short)1);
/* 1271 */     typeRep((short)2, (short)0, (short)2, (short)10);
/* 1272 */     typeRep((short)8, (short)0, (short)8, (short)1);
/* 1273 */     typeRep((short)12, (short)0, (short)12, (short)10);
/* 1274 */     typeRep((short)23, (short)0, (short)23, (short)1);
/* 1275 */     typeRep((short)24, (short)0, (short)24, (short)1);
/*      */     
/*      */ 
/* 1278 */     typeRep((short)25, (short)0, (short)25, (short)1);
/* 1279 */     typeRep((short)26, (short)0, (short)26, (short)1);
/* 1280 */     typeRep((short)27, (short)0, (short)27, (short)1);
/* 1281 */     typeRep((short)28, (short)0, (short)28, (short)1);
/* 1282 */     typeRep((short)29, (short)0, (short)29, (short)1);
/* 1283 */     typeRep((short)30, (short)0, (short)30, (short)1);
/* 1284 */     typeRep((short)31, (short)0, (short)31, (short)1);
/*      */     
/* 1286 */     typeRep((short)32, (short)0, (short)32, (short)1);
/* 1287 */     typeRep((short)33, (short)0, (short)33, (short)1);
/*      */     
/*      */ 
/* 1290 */     typeRep((short)10, (short)1, (short)10, (short)1);
/* 1291 */     typeRep((short)11, (short)1, (short)11, (short)1);
/*      */     
/* 1293 */     typeRep((short)40, (short)1, (short)40, (short)1);
/* 1294 */     typeRep((short)41, (short)1, (short)41, (short)1);
/* 1295 */     typeRep((short)117, (short)1, (short)117, (short)1);
/* 1296 */     typeRep((short)120, (short)1, (short)120, (short)1);
/* 1297 */     typeRep((short)290, (short)1, (short)290, (short)1);
/* 1298 */     typeRep((short)291, (short)1, (short)291, (short)1);
/* 1299 */     typeRep((short)292, (short)1, (short)292, (short)1);
/* 1300 */     typeRep((short)293, (short)1, (short)293, (short)1);
/* 1301 */     typeRep((short)294, (short)1, (short)294, (short)1);
/* 1302 */     typeRep((short)298, (short)1, (short)298, (short)1);
/* 1303 */     typeRep((short)299, (short)1, (short)299, (short)1);
/* 1304 */     typeRep((short)300, (short)1, (short)300, (short)1);
/* 1305 */     typeRep((short)301, (short)1, (short)301, (short)1);
/* 1306 */     typeRep((short)302, (short)1, (short)302, (short)1);
/* 1307 */     typeRep((short)303, (short)1, (short)303, (short)1);
/* 1308 */     typeRep((short)304, (short)1, (short)304, (short)1);
/* 1309 */     typeRep((short)305, (short)1, (short)305, (short)1);
/* 1310 */     typeRep((short)306, (short)1, (short)306, (short)1);
/* 1311 */     typeRep((short)307, (short)1, (short)307, (short)1);
/* 1312 */     typeRep((short)308, (short)1, (short)308, (short)1);
/* 1313 */     typeRep((short)309, (short)1, (short)309, (short)1);
/* 1314 */     typeRep((short)310, (short)1, (short)310, (short)1);
/* 1315 */     typeRep((short)311, (short)1, (short)311, (short)1);
/* 1316 */     typeRep((short)312, (short)1, (short)312, (short)1);
/* 1317 */     typeRep((short)313, (short)1, (short)313, (short)1);
/* 1318 */     typeRep((short)315, (short)1, (short)315, (short)1);
/* 1319 */     typeRep((short)316, (short)1, (short)316, (short)1);
/* 1320 */     typeRep((short)317, (short)1, (short)317, (short)1);
/* 1321 */     typeRep((short)318, (short)1, (short)318, (short)1);
/* 1322 */     typeRep((short)319, (short)1, (short)319, (short)1);
/* 1323 */     typeRep((short)320, (short)1, (short)320, (short)1);
/* 1324 */     typeRep((short)321, (short)1, (short)321, (short)1);
/* 1325 */     typeRep((short)322, (short)1, (short)322, (short)1);
/* 1326 */     typeRep((short)323, (short)1, (short)323, (short)1);
/* 1327 */     typeRep((short)327, (short)1, (short)327, (short)1);
/* 1328 */     typeRep((short)328, (short)1, (short)328, (short)1);
/* 1329 */     typeRep((short)329, (short)1, (short)329, (short)1);
/* 1330 */     typeRep((short)331, (short)1, (short)331, (short)1);
/* 1331 */     typeRep((short)333, (short)1, (short)333, (short)1);
/* 1332 */     typeRep((short)334, (short)1, (short)334, (short)1);
/* 1333 */     typeRep((short)335, (short)1, (short)335, (short)1);
/* 1334 */     typeRep((short)336, (short)1, (short)336, (short)1);
/* 1335 */     typeRep((short)337, (short)1, (short)337, (short)1);
/* 1336 */     typeRep((short)338, (short)1, (short)338, (short)1);
/* 1337 */     typeRep((short)339, (short)1, (short)339, (short)1);
/* 1338 */     typeRep((short)340, (short)1, (short)340, (short)1);
/* 1339 */     typeRep((short)341, (short)1, (short)341, (short)1);
/* 1340 */     typeRep((short)342, (short)1, (short)342, (short)1);
/* 1341 */     typeRep((short)343, (short)1, (short)343, (short)1);
/* 1342 */     typeRep((short)344, (short)1, (short)344, (short)1);
/* 1343 */     typeRep((short)345, (short)1, (short)345, (short)1);
/* 1344 */     typeRep((short)346, (short)1, (short)346, (short)1);
/* 1345 */     typeRep((short)348, (short)1, (short)348, (short)1);
/* 1346 */     typeRep((short)349, (short)1, (short)349, (short)1);
/* 1347 */     typeRep((short)354, (short)1, (short)354, (short)1);
/* 1348 */     typeRep((short)355, (short)1, (short)355, (short)1);
/* 1349 */     typeRep((short)359, (short)1, (short)359, (short)1);
/* 1350 */     typeRep((short)363, (short)1, (short)363, (short)1);
/* 1351 */     typeRep((short)380, (short)1, (short)380, (short)1);
/* 1352 */     typeRep((short)381, (short)1, (short)381, (short)1);
/* 1353 */     typeRep((short)382, (short)1, (short)382, (short)1);
/* 1354 */     typeRep((short)383, (short)1, (short)383, (short)1);
/* 1355 */     typeRep((short)384, (short)1, (short)384, (short)1);
/* 1356 */     typeRep((short)385, (short)1, (short)385, (short)1);
/* 1357 */     typeRep((short)386, (short)1, (short)386, (short)1);
/* 1358 */     typeRep((short)387, (short)1, (short)387, (short)1);
/* 1359 */     typeRep((short)388, (short)1, (short)388, (short)1);
/* 1360 */     typeRep((short)389, (short)1, (short)389, (short)1);
/* 1361 */     typeRep((short)390, (short)1, (short)390, (short)1);
/* 1362 */     typeRep((short)391, (short)1, (short)391, (short)1);
/* 1363 */     typeRep((short)393, (short)1, (short)393, (short)1);
/* 1364 */     typeRep((short)394, (short)1, (short)394, (short)1);
/* 1365 */     typeRep((short)395, (short)1, (short)395, (short)1);
/* 1366 */     typeRep((short)396, (short)1, (short)396, (short)1);
/* 1367 */     typeRep((short)397, (short)1, (short)397, (short)1);
/* 1368 */     typeRep((short)398, (short)1, (short)398, (short)1);
/* 1369 */     typeRep((short)399, (short)1, (short)399, (short)1);
/* 1370 */     typeRep((short)400, (short)1, (short)400, (short)1);
/* 1371 */     typeRep((short)401, (short)1, (short)401, (short)1);
/* 1372 */     typeRep((short)404, (short)1, (short)404, (short)1);
/* 1373 */     typeRep((short)405, (short)1, (short)405, (short)1);
/* 1374 */     typeRep((short)406, (short)1, (short)406, (short)1);
/* 1375 */     typeRep((short)407, (short)1, (short)407, (short)1);
/* 1376 */     typeRep((short)413, (short)1, (short)413, (short)1);
/* 1377 */     typeRep((short)414, (short)1, (short)414, (short)1);
/* 1378 */     typeRep((short)415, (short)1, (short)415, (short)1);
/* 1379 */     typeRep((short)416, (short)1, (short)416, (short)1);
/* 1380 */     typeRep((short)417, (short)1, (short)417, (short)1);
/* 1381 */     typeRep((short)418, (short)1, (short)418, (short)1);
/* 1382 */     typeRep((short)419, (short)1, (short)419, (short)1);
/* 1383 */     typeRep((short)420, (short)1, (short)420, (short)1);
/* 1384 */     typeRep((short)421, (short)1, (short)421, (short)1);
/* 1385 */     typeRep((short)422, (short)1, (short)422, (short)1);
/* 1386 */     typeRep((short)423, (short)1, (short)423, (short)1);
/* 1387 */     typeRep((short)424, (short)1, (short)424, (short)1);
/* 1388 */     typeRep((short)425, (short)1, (short)425, (short)1);
/* 1389 */     typeRep((short)426, (short)1, (short)426, (short)1);
/* 1390 */     typeRep((short)427, (short)1, (short)427, (short)1);
/* 1391 */     typeRep((short)429, (short)1, (short)429, (short)1);
/* 1392 */     typeRep((short)430, (short)1, (short)430, (short)1);
/* 1393 */     typeRep((short)431, (short)1, (short)431, (short)1);
/* 1394 */     typeRep((short)432, (short)1, (short)432, (short)1);
/* 1395 */     typeRep((short)433, (short)1, (short)433, (short)1);
/* 1396 */     typeRep((short)449, (short)1, (short)449, (short)1);
/* 1397 */     typeRep((short)450, (short)1, (short)450, (short)1);
/* 1398 */     typeRep((short)454, (short)1, (short)454, (short)1);
/* 1399 */     typeRep((short)455, (short)1, (short)455, (short)1);
/* 1400 */     typeRep((short)456, (short)1, (short)456, (short)1);
/* 1401 */     typeRep((short)457, (short)1, (short)457, (short)1);
/* 1402 */     typeRep((short)458, (short)1, (short)458, (short)1);
/* 1403 */     typeRep((short)459, (short)1, (short)459, (short)1);
/* 1404 */     typeRep((short)460, (short)1, (short)460, (short)1);
/* 1405 */     typeRep((short)461, (short)1, (short)461, (short)1);
/* 1406 */     typeRep((short)462, (short)1, (short)462, (short)1);
/* 1407 */     typeRep((short)463, (short)1, (short)463, (short)1);
/* 1408 */     typeRep((short)466, (short)1, (short)466, (short)1);
/* 1409 */     typeRep((short)467, (short)1, (short)467, (short)1);
/* 1410 */     typeRep((short)468, (short)1, (short)468, (short)1);
/* 1411 */     typeRep((short)469, (short)1, (short)469, (short)1);
/* 1412 */     typeRep((short)470, (short)1, (short)470, (short)1);
/* 1413 */     typeRep((short)471, (short)1, (short)471, (short)1);
/* 1414 */     typeRep((short)472, (short)1, (short)472, (short)1);
/* 1415 */     typeRep((short)473, (short)1, (short)473, (short)1);
/* 1416 */     typeRep((short)474, (short)1, (short)474, (short)1);
/* 1417 */     typeRep((short)475, (short)1, (short)475, (short)1);
/* 1418 */     typeRep((short)476, (short)1, (short)476, (short)1);
/* 1419 */     typeRep((short)477, (short)1, (short)477, (short)1);
/* 1420 */     typeRep((short)478, (short)1, (short)478, (short)1);
/* 1421 */     typeRep((short)479, (short)1, (short)479, (short)1);
/* 1422 */     typeRep((short)480, (short)1, (short)480, (short)1);
/* 1423 */     typeRep((short)481, (short)1, (short)481, (short)1);
/* 1424 */     typeRep((short)482, (short)1, (short)482, (short)1);
/* 1425 */     typeRep((short)483, (short)1, (short)483, (short)1);
/* 1426 */     typeRep((short)484, (short)1, (short)484, (short)1);
/* 1427 */     typeRep((short)485, (short)1, (short)485, (short)1);
/* 1428 */     typeRep((short)486, (short)1, (short)486, (short)1);
/* 1429 */     typeRep((short)490, (short)1, (short)490, (short)1);
/* 1430 */     typeRep((short)491, (short)1, (short)491, (short)1);
/* 1431 */     typeRep((short)492, (short)1, (short)492, (short)1);
/* 1432 */     typeRep((short)493, (short)1, (short)493, (short)1);
/* 1433 */     typeRep((short)494, (short)1, (short)494, (short)1);
/* 1434 */     typeRep((short)495, (short)1, (short)495, (short)1);
/* 1435 */     typeRep((short)496, (short)1, (short)496, (short)1);
/* 1436 */     typeRep((short)498, (short)1, (short)498, (short)1);
/* 1437 */     typeRep((short)499, (short)1, (short)499, (short)1);
/* 1438 */     typeRep((short)500, (short)1, (short)500, (short)1);
/* 1439 */     typeRep((short)501, (short)1, (short)501, (short)1);
/* 1440 */     typeRep((short)502, (short)1, (short)502, (short)1);
/* 1441 */     typeRep((short)509, (short)1, (short)509, (short)1);
/* 1442 */     typeRep((short)510, (short)1, (short)510, (short)1);
/* 1443 */     typeRep((short)513, (short)1, (short)513, (short)1);
/* 1444 */     typeRep((short)514, (short)1, (short)514, (short)1);
/* 1445 */     typeRep((short)516, (short)1, (short)516, (short)1);
/* 1446 */     typeRep((short)517, (short)1, (short)517, (short)1);
/* 1447 */     typeRep((short)518, (short)1, (short)518, (short)1);
/* 1448 */     typeRep((short)519, (short)1, (short)519, (short)1);
/* 1449 */     typeRep((short)520, (short)1, (short)520, (short)1);
/* 1450 */     typeRep((short)521, (short)1, (short)521, (short)1);
/* 1451 */     typeRep((short)522, (short)1, (short)522, (short)1);
/* 1452 */     typeRep((short)523, (short)1, (short)523, (short)1);
/* 1453 */     typeRep((short)524, (short)1, (short)524, (short)1);
/* 1454 */     typeRep((short)525, (short)1, (short)525, (short)1);
/* 1455 */     typeRep((short)526, (short)1, (short)526, (short)1);
/* 1456 */     typeRep((short)527, (short)1, (short)527, (short)1);
/* 1457 */     typeRep((short)528, (short)1, (short)528, (short)1);
/* 1458 */     typeRep((short)529, (short)1, (short)529, (short)1);
/* 1459 */     typeRep((short)530, (short)1, (short)530, (short)1);
/* 1460 */     typeRep((short)531, (short)1, (short)531, (short)1);
/* 1461 */     typeRep((short)532, (short)1, (short)532, (short)1);
/* 1462 */     typeRep((short)533, (short)1, (short)533, (short)1);
/* 1463 */     typeRep((short)534, (short)1, (short)534, (short)1);
/* 1464 */     typeRep((short)535, (short)1, (short)535, (short)1);
/* 1465 */     typeRep((short)536, (short)1, (short)536, (short)1);
/* 1466 */     typeRep((short)537, (short)1, (short)537, (short)1);
/* 1467 */     typeRep((short)538, (short)1, (short)538, (short)1);
/* 1468 */     typeRep((short)539, (short)1, (short)539, (short)1);
/*      */     
/* 1470 */     typeRep((short)540, (short)1, (short)540, (short)1);
/* 1471 */     typeRep((short)541, (short)1, (short)541, (short)1);
/* 1472 */     typeRep((short)542, (short)1, (short)542, (short)1);
/* 1473 */     typeRep((short)543, (short)1, (short)543, (short)1);
/*      */     
/* 1475 */     typeRep((short)560, (short)1, (short)560, (short)1);
/* 1476 */     typeRep((short)565, (short)1, (short)565, (short)1);
/* 1477 */     typeRep((short)572, (short)1, (short)572, (short)1);
/* 1478 */     typeRep((short)573, (short)1, (short)573, (short)1);
/* 1479 */     typeRep((short)574, (short)1, (short)574, (short)1);
/* 1480 */     typeRep((short)575, (short)1, (short)575, (short)1);
/* 1481 */     typeRep((short)576, (short)1, (short)576, (short)1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1488 */     typeRep((short)578, (short)1, (short)578, (short)1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1494 */     typeRep((short)580, (short)1, (short)580, (short)1);
/* 1495 */     typeRep((short)581, (short)1, (short)581, (short)1);
/* 1496 */     typeRep((short)582, (short)1, (short)582, (short)1);
/* 1497 */     typeRep((short)583, (short)1, (short)583, (short)1);
/* 1498 */     typeRep((short)584, (short)1, (short)584, (short)1);
/* 1499 */     typeRep((short)585, (short)1, (short)585, (short)1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1504 */     typeRep((short)3, (short)0, (short)2, (short)10);
/* 1505 */     typeRep((short)4, (short)0, (short)2, (short)10);
/* 1506 */     typeRep((short)5, (short)0, (short)1, (short)1);
/* 1507 */     typeRep((short)6, (short)0, (short)2, (short)10);
/* 1508 */     typeRep((short)7, (short)0, (short)2, (short)10);
/* 1509 */     typeRep((short)9, (short)0, (short)1, (short)1);
/*      */     
/* 1511 */     typeRep((short)13, (short)0, (short)0, (short)0);
/* 1512 */     typeRep((short)14, (short)0, (short)0, (short)0);
/* 1513 */     typeRep((short)15, (short)0, (short)23, (short)1);
/* 1514 */     typeRep((short)16, (short)0, (short)0, (short)0);
/* 1515 */     typeRep((short)17, (short)0, (short)0, (short)0);
/* 1516 */     typeRep((short)18, (short)0, (short)0, (short)0);
/* 1517 */     typeRep((short)19, (short)0, (short)0, (short)0);
/* 1518 */     typeRep((short)20, (short)0, (short)0, (short)0);
/* 1519 */     typeRep((short)21, (short)0, (short)0, (short)0);
/* 1520 */     typeRep((short)22, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/* 1523 */     typeRep((short)39, (short)0, (short)120, (short)1);
/* 1524 */     typeRep((short)58, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/*      */ 
/* 1528 */     typeRep((short)68, (short)0, (short)2, (short)10);
/* 1529 */     typeRep((short)69, (short)0, (short)0, (short)0);
/*      */     
/* 1531 */     typeRep((short)70, (short)0, (short)0, (short)0);
/* 1532 */     typeRep((short)74, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/*      */ 
/* 1536 */     typeRep((short)76, (short)0, (short)0, (short)0);
/* 1537 */     typeRep((short)91, (short)0, (short)2, (short)10);
/* 1538 */     typeRep((short)94, (short)0, (short)1, (short)1);
/* 1539 */     typeRep((short)95, (short)0, (short)23, (short)1);
/* 1540 */     typeRep((short)96, (short)0, (short)96, (short)1);
/* 1541 */     typeRep((short)97, (short)0, (short)96, (short)1);
/*      */     
/*      */ 
/* 1544 */     typeRep((short)100, (short)0, (short)100, (short)1);
/* 1545 */     typeRep((short)101, (short)0, (short)101, (short)1);
/* 1546 */     typeRep((short)102, (short)0, (short)102, (short)1);
/*      */     
/* 1548 */     typeRep((short)104, (short)0, (short)11, (short)1);
/*      */     
/* 1550 */     typeRep((short)105, (short)0, (short)0, (short)0);
/* 1551 */     typeRep((short)106, (short)0, (short)106, (short)1);
/* 1552 */     typeRep((short)108, (short)0, (short)109, (short)1);
/* 1553 */     typeRep((short)109, (short)0, (short)109, (short)1);
/* 1554 */     typeRep((short)110, (short)0, (short)111, (short)1);
/* 1555 */     typeRep((short)111, (short)0, (short)111, (short)1);
/* 1556 */     typeRep((short)112, (short)0, (short)112, (short)1);
/* 1557 */     typeRep((short)113, (short)0, (short)113, (short)1);
/* 1558 */     typeRep((short)114, (short)0, (short)114, (short)1);
/* 1559 */     typeRep((short)115, (short)0, (short)115, (short)1);
/* 1560 */     typeRep((short)116, (short)0, (short)102, (short)1);
/* 1561 */     typeRep((short)118, (short)0, (short)0, (short)0);
/* 1562 */     typeRep((short)119, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/*      */ 
/* 1566 */     typeRep((short)121, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/* 1569 */     typeRep((short)122, (short)0, (short)0, (short)0);
/*      */     
/* 1571 */     typeRep((short)123, (short)0, (short)0, (short)0);
/* 1572 */     typeRep((short)136, (short)0, (short)0, (short)0);
/* 1573 */     typeRep((short)146, (short)0, (short)146, (short)1);
/*      */     
/*      */ 
/* 1576 */     typeRep((short)147, (short)0, (short)0, (short)0);
/* 1577 */     typeRep((short)152, (short)0, (short)2, (short)10);
/* 1578 */     typeRep((short)153, (short)0, (short)2, (short)10);
/* 1579 */     typeRep((short)154, (short)0, (short)2, (short)10);
/* 1580 */     typeRep((short)155, (short)0, (short)1, (short)1);
/* 1581 */     typeRep((short)156, (short)0, (short)12, (short)10);
/* 1582 */     typeRep((short)172, (short)0, (short)2, (short)10);
/*      */     
/* 1584 */     typeRep((short)178, (short)0, (short)178, (short)1);
/* 1585 */     typeRep((short)179, (short)0, (short)179, (short)1);
/* 1586 */     typeRep((short)180, (short)0, (short)180, (short)1);
/* 1587 */     typeRep((short)181, (short)0, (short)181, (short)1);
/* 1588 */     typeRep((short)182, (short)0, (short)182, (short)1);
/* 1589 */     typeRep((short)183, (short)0, (short)183, (short)1);
/* 1590 */     typeRep((short)184, (short)0, (short)12, (short)10);
/* 1591 */     typeRep((short)185, (short)0, (short)185, (short)1);
/* 1592 */     typeRep((short)186, (short)0, (short)186, (short)1);
/* 1593 */     typeRep((short)187, (short)0, (short)187, (short)1);
/* 1594 */     typeRep((short)188, (short)0, (short)188, (short)1);
/* 1595 */     typeRep((short)189, (short)0, (short)189, (short)1);
/* 1596 */     typeRep((short)190, (short)0, (short)190, (short)1);
/* 1597 */     typeRep((short)191, (short)0, (short)0, (short)0);
/* 1598 */     typeRep((short)192, (short)0, (short)0, (short)0);
/* 1599 */     typeRep((short)195, (short)0, (short)112, (short)1);
/* 1600 */     typeRep((short)196, (short)0, (short)113, (short)1);
/* 1601 */     typeRep((short)197, (short)0, (short)114, (short)1);
/* 1602 */     typeRep((short)208, (short)0, (short)208, (short)1);
/* 1603 */     typeRep((short)209, (short)0, (short)0, (short)0);
/* 1604 */     typeRep((short)231, (short)0, (short)231, (short)1);
/* 1605 */     typeRep((short)232, (short)0, (short)231, (short)1);
/* 1606 */     typeRep((short)233, (short)0, (short)233, (short)1);
/* 1607 */     typeRep((short)241, (short)0, (short)109, (short)1);
/*      */     
/*      */ 
/* 1610 */     typeRep((short)515, (short)0, (short)0, (short)0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1618 */     typeAndRepFor1100 = new short[typeAndRep.length];
/* 1619 */     System.arraycopy(typeAndRep, 0, typeAndRepFor1100, 0, typeAndRep.length);
/*      */     
/*      */ 
/* 1622 */     typeRep((short)590, (short)1, (short)590, (short)1);
/* 1623 */     typeRep((short)591, (short)1, (short)591, (short)1);
/* 1624 */     typeRep((short)592, (short)1, (short)592, (short)1);
/*      */   }
/*      */   
/*      */ 
/* 1628 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   static final int TTCLXMULTI = 1;
/*      */   static final int TTCLXMCONV = 2;
/*      */   static final int TTCLXNOCNV = 4;
/*      */   static final int TTCLXPCEFC = 8;
/*      */   static final int TTCLXSCSID = 16;
/*      */   static final int TTCLXSIGNCNV = 32;
/*      */   static final int TTCLXRSBCF = 64;
/*      */   static final byte TTC_FLD_VSN_820 = 1;
/*      */   static final byte TTC_FLD_VSN_902 = 2;
/*      */   static final byte TTC_FLD_VSN_1000 = 3;
/*      */   static final byte TTC_FLD_VSN_1020 = 4;
/*      */   static final byte TTC_FLD_VSN_1100 = 5;
/*      */   static final byte TTC_FLD_VSN_1120 = 6;
/*      */   static final byte TTC_FLD_VSN_MAX = 6;
/*      */   static final int KPCCAP_TTC_VSN_OFFSET = 7;
/*      */   static final byte KPULMAXL = 6;
/*      */   static final byte KPCCAP_CTB_TTC1_EOCS = 1;
/*      */   static final byte KPCCAP_CTB_TTC1_PBLB = 2;
/*      */   static final byte KPCCAP_CTB_TTC1_FNTY = 4;
/*      */   static final byte KPCCAP_CTB_TTC1_INRC = 8;
/*      */   static final byte KPCCAP_CTB_TTC1_FCSC = 16;
/*      */   static final byte KPCCAP_CTB_TTC1_FBVC = 32;
/*      */   static final byte KPCCAP_CTB_TTC1_NTEC = 64;
/*      */   static final byte KPCCAP_CTB_TTC1_RSHP = -128;
/*      */   static final byte KPCCAP_CTB_OCI1_DTME = 1;
/*      */   static final byte KPCCAP_CTB_OCI1_NOMIP = 2;
/*      */   static final byte KPCCAP_CTB_OCI1_PDFC = 4;
/*      */   static final byte KPCCAP_CTB_OCI1_FEXF = 8;
/*      */   static final byte KPCCAP_CTB_OCI1_FSAP = 16;
/*      */   static final byte KPCCAP_CTB_OCI1_BFLTDBL = 32;
/*      */   static final byte KPCCAP_CTB_OCI1_CPSSDML = 64;
/*      */   static final byte KPCCAP_CTB_OCI1_APCTX = -128;
/*      */   static final byte KPCCAP_CTB_XML_CSXXMLT = 1;
/*      */   static final byte KPCCAP_CTB_XML_LOBSTR_IMG_ONLY = 2;
/*      */   static final byte KOLE_LOB_CAP_UB8_SIZE = 1;
/*      */   static final byte KOLE_LOB_CAP_ENCS = 2;
/*      */   static final byte KOLE_LOB_CAP_DIL = 4;
/*      */   static final byte KOLE_LOB_CAP_TMPLOC_SZ = 8;
/*      */   static final byte KOLE_LOB_CAP_ARRAY = 32;
/*      */   static final byte KOLE_LOB_CAP_PRFCH = 64;
/*      */   static final byte KOLE_LOB_CAP_ALL = 79;
/*      */   static final int KPCCAP_KOLE_LOB_OFFSET = 23;
/*      */   static final int KPCCAP_CT_AQ_OFFSET = 25;
/*      */   static final byte KPCCAP_CT_AQ_PROP_DQA = 1;
/*      */   static final byte KPCCAP_CT_AQ_BUFQ = 2;
/*      */   static final byte KPCCAP_CT_AQ_BPROP_RCV = 4;
/*      */   static final byte KPCCAP_CT_AQ_ENQ_OPT = 8;
/*      */   static final byte KPCCAP_CT_AQ_NTFN_QPT = 16;
/*      */   static final byte KPCCAP_CT_AQ_NTFN_GRP = 32;
/*      */   static final byte KOPT_VNFT = 3;
/*      */   static final byte KPCCAP_CTB_TTC2_ZLNP = 4;
/*      */   static final byte KPCCAP_CTB_TTC3_TZVER = 2;
/*      */   static final byte ZTVOV_KPCLOG_O30L = 0;
/*      */   static final byte ZTVOV_KPCLOG_O3L = 1;
/*      */   static final byte ZTVOV_KPCLOG_O5L_NP = 2;
/*      */   static final byte ZTVOV_KPCLOG_O4L = 4;
/*      */   static final byte ZTVOV_KPCLOG_O5L = 8;
/*      */   static final byte KPCCAP_CTB_TTC3_COLMETADATA = 1;
/*      */   static final byte KPCCAP_CTB_TTC3_KEEP_OUT_ORDER = -128;
/*      */   static final int KPCCAP_CT_UB2DTY = 27;
/*      */   static final byte KPCCAP_CTB_TTC3 = 37;
/*      */   static final byte KPCCAP_CTB_OCI2_SCTAF = 1;
/*      */   static final byte KPCCAP_CTB_OCI2_FTHDR = 2;
/*      */   static final byte KPCCAP_CTB_OCI2_CQC = 4;
/*      */   static final byte KPCCAP_CTB_OCI2_EDITION = 8;
/*      */   static final byte KPCCAP_CTB_OCI2_SRVCP = 16;
/*      */   static final byte KPCCAP_RT_TZ = 1;
/*      */   static final byte KPCCAP_RT_TZ_EX = 1;
/*      */   static final short DTYEXPBASE = 256;
/*      */   static final short DTY0 = 0;
/*      */   static final short DTYCHR = 1;
/*      */   static final short DTYNUM = 2;
/*      */   static final short DTYINT = 3;
/*      */   static final short DTYFLT = 4;
/*      */   static final short DTYSTR = 5;
/*      */   static final short DTYVNU = 6;
/*      */   static final short DTYPDN = 7;
/*      */   static final short DTYLNG = 8;
/*      */   static final short DTYVCS = 9;
/*      */   static final short DTYTI5 = 10;
/*      */   static final short DTYRID = 11;
/*      */   static final short DTYDAT = 12;
/*      */   static final short DTYIDT = 13;
/*      */   static final short DTYIJU = 14;
/*      */   static final short DTYVBI = 15;
/*      */   static final short DTYDIF = 16;
/*      */   static final short DTYDOF = 17;
/*      */   static final short DTYDTZ = 18;
/*      */   static final short DTYDYN = 19;
/*      */   static final short DTYDPC = 20;
/*      */   static final short DTYBFLOAT = 21;
/*      */   static final short DTYBDOUBLE = 22;
/*      */   static final short DTYBIN = 23;
/*      */   static final short DTYUB1 = 23;
/*      */   static final short DTYLBI = 24;
/*      */   static final short DTYUB2 = 25;
/*      */   static final short DTYUB4 = 26;
/*      */   static final short DTYB1 = 27;
/*      */   static final short DTYB2 = 28;
/*      */   static final short DTYB4 = 29;
/*      */   static final short DTYSB4 = 29;
/*      */   static final short DTYWORD = 30;
/*      */   static final short DTYUWORD = 31;
/*      */   static final short DTYIIN = 29;
/*      */   static final short DTYCURID = 25;
/*      */   static final short DTYAMID = 26;
/*      */   static final short DTYDBA = 26;
/*      */   static final short DTYPTN = 26;
/*      */   static final short DTYPB = 32;
/*      */   static final short DTYPW = 33;
/*      */   static final short DTYOER8 = 290;
/*      */   static final short DTYFUN = 291;
/*      */   static final short DTYAUA = 292;
/*      */   static final short DTYRXH7 = 293;
/*      */   static final short DTYNA6 = 294;
/*      */   static final short DTYOAC = 39;
/*      */   static final short DTYAMS = 40;
/*      */   static final short DTYBRN = 41;
/*      */   static final short DTYBRP = 298;
/*      */   static final short DTYBRV = 299;
/*      */   static final short DTYKVA = 300;
/*      */   static final short DTYCLS = 301;
/*      */   static final short DTYCUI = 302;
/*      */   static final short DTYDFN = 303;
/*      */   static final short DTYDQR = 304;
/*      */   static final short DTYDSC = 305;
/*      */   static final short DTYEXE = 306;
/*      */   static final short DTYFCH = 307;
/*      */   static final short DTYGBV = 308;
/*      */   static final short DTYGEM = 309;
/*      */   static final short DTYGIV = 310;
/*      */   static final short DTYOKG = 311;
/*      */   static final short DTYHMI = 312;
/*      */   static final short DTYINO = 313;
/*      */   static final short DTYOPQ = 58;
/*      */   static final short DTYLNF = 315;
/*      */   static final short DTYONT = 316;
/*      */   static final short DTYOPE = 317;
/*      */   static final short DTYOSQ = 318;
/*      */   static final short DTYSFE = 319;
/*      */   static final short DTYSPF = 320;
/*      */   static final short DTYVSN = 321;
/*      */   static final short DTYUD7 = 322;
/*      */   static final short DTYDSA = 323;
/*      */   static final short DTYUIN = 68;
/*      */   static final short DTYBRI = 69;
/*      */   static final short DTY70 = 70;
/*      */   static final short DTYPIN = 327;
/*      */   static final short DTYPFN = 328;
/*      */   static final short DTYPPT = 329;
/*      */   static final short DTYOCU = 74;
/*      */   static final short DTYSTO = 331;
/*      */   static final short DTY76 = 76;
/*      */   static final short DTYARC = 333;
/*      */   static final short DTYMRS = 334;
/*      */   static final short DTYMRT = 335;
/*      */   static final short DTYMRG = 336;
/*      */   static final short DTYMRR = 337;
/*      */   static final short DTYMRC = 338;
/*      */   static final short DTYVER = 339;
/*      */   static final short DTYLON2 = 340;
/*      */   static final short DTYINO2 = 341;
/*      */   static final short DTYALL = 342;
/*      */   static final short DTYUDB = 343;
/*      */   static final short DTYAQI = 344;
/*      */   static final short DTYULB = 345;
/*      */   static final short DTYULD = 346;
/*      */   static final short DTYSLS = 91;
/*      */   static final short DTYSID = 348;
/*      */   static final short DTYNA7 = 349;
/*      */   static final short DTYLVC = 94;
/*      */   static final short DTYLVB = 95;
/*      */   static final short DTYAFC = 96;
/*      */   static final short DTYAVC = 97;
/*      */   static final short DTYAL7 = 354;
/*      */   static final short DTYK2RPC = 355;
/*      */   static final short DTYIBFLOAT = 100;
/*      */   static final short DTYIBDOUBLE = 101;
/*      */   static final short DTYCUR = 102;
/*      */   static final short DTYXDP = 359;
/*      */   static final short DTYRDD = 104;
/*      */   static final short DTYARR = 70;
/*      */   static final short DTYVAR = 76;
/*      */   static final short DTYLAB = 105;
/*      */   static final short DTYOSL = 106;
/*      */   static final short DTYOKO8 = 363;
/*      */   static final short DTYNTY = 108;
/*      */   static final short DTYINTY = 109;
/*      */   static final short DTYREF = 110;
/*      */   static final short DTYIREF = 111;
/*      */   static final short DTYCLOB = 112;
/*      */   static final short DTYBLOB = 113;
/*      */   static final short DTYBFIL = 114;
/*      */   static final short DTYFILE = 114;
/*      */   static final short DTYCFIL = 115;
/*      */   static final short DTYRSET = 116;
/*      */   static final short DTYCWD = 117;
/*      */   static final short DTYSVT = 118;
/*      */   static final short DTYISVT = 119;
/*      */   static final short DTYNAC = 120;
/*      */   static final short DTYADT = 121;
/*      */   static final short DTYNTB = 122;
/*      */   static final short DTYNAR = 123;
/*      */   static final short DTYUDS = 380;
/*      */   static final short DTYAL8 = 381;
/*      */   static final short DTYLFOP = 382;
/*      */   static final short DTYFCRT = 383;
/*      */   static final short DTYDNY = 384;
/*      */   static final short DTYOPR = 385;
/*      */   static final short DTYPLS = 386;
/*      */   static final short DTYXID = 387;
/*      */   static final short DTYTXN = 388;
/*      */   static final short DTYDCB = 389;
/*      */   static final short DTYCCA = 390;
/*      */   static final short DTYWRN = 391;
/*      */   static final short DTYOBJ = 136;
/*      */   static final short DTYTLH = 393;
/*      */   static final short DTYTOH = 394;
/*      */   static final short DTYFOI = 395;
/*      */   static final short DTYSID2 = 396;
/*      */   static final short DTYTCH = 397;
/*      */   static final short DTYPII = 398;
/*      */   static final short DTYPFI = 399;
/*      */   static final short DTYPPU = 400;
/*      */   static final short DTYPTE = 401;
/*      */   static final short DTYCLV = 146;
/*      */   static final short DTYBLV = 147;
/*      */   static final short DTYRXH8 = 404;
/*      */   static final short DTYTNP = 405;
/*      */   static final short DTYAUTH = 406;
/*      */   static final short DTYKVAL = 407;
/*      */   static final short DTYDTR = 152;
/*      */   static final short DTYDUN = 153;
/*      */   static final short DTYDOP = 154;
/*      */   static final short DTYVST = 155;
/*      */   static final short DTYODT = 156;
/*      */   static final short DTYFGI = 413;
/*      */   static final short DTYDSY = 414;
/*      */   static final short DTYDSYR8 = 415;
/*      */   static final short DTYDSYH8 = 416;
/*      */   static final short DTYDSYL = 417;
/*      */   static final short DTYDSYT8 = 418;
/*      */   static final short DTYDSYV8 = 419;
/*      */   static final short DTYDSYP = 420;
/*      */   static final short DTYDSYF = 421;
/*      */   static final short DTYDSYK = 422;
/*      */   static final short DTYDSYY = 423;
/*      */   static final short DTYDSYQ = 424;
/*      */   static final short DTYDSYC = 425;
/*      */   static final short DTYDSYA = 426;
/*      */   static final short DTYOT8 = 427;
/*      */   static final short DTYDOL = 172;
/*      */   static final short DTYDSYTY = 429;
/*      */   static final short DTYAQE = 430;
/*      */   static final short DTYKV = 431;
/*      */   static final short DTYAQD = 432;
/*      */   static final short DTYAQ8 = 433;
/*      */   static final short DTYTIME = 178;
/*      */   static final short DTYTTZ = 179;
/*      */   static final short DTYSTAMP = 180;
/*      */   static final short DTYSTZ = 181;
/*      */   static final short DTYIYM = 182;
/*      */   static final short DTYIDS = 183;
/*      */   static final short DTYEDATE = 184;
/*      */   static final short DTYETIME = 185;
/*      */   static final short DTYETTZ = 186;
/*      */   static final short DTYESTAMP = 187;
/*      */   static final short DTYESTZ = 188;
/*      */   static final short DTYEIYM = 189;
/*      */   static final short DTYEIDS = 190;
/*      */   static final short DTYLDIIF = 191;
/*      */   static final short DTYLDIOF = 192;
/*      */   static final short DTYRFS = 449;
/*      */   static final short DTYRXH10 = 450;
/*      */   static final short DTYDCLOB = 195;
/*      */   static final short DTYDBLOB = 196;
/*      */   static final short DTYDBFIL = 197;
/*      */   static final short DTYKPN = 454;
/*      */   static final short DTYKPDNR = 455;
/*      */   static final short DTYDSYD = 456;
/*      */   static final short DTYDSYS = 457;
/*      */   static final short DTYDSYR = 458;
/*      */   static final short DTYDSYH = 459;
/*      */   static final short DTYDSYT = 460;
/*      */   static final short DTYDSYV = 461;
/*      */   static final short DTYAQM = 462;
/*      */   static final short DTYOER = 463;
/*      */   static final short DTYBURI = 208;
/*      */   static final short DTYPSR = 209;
/*      */   static final short DTYAQL = 466;
/*      */   static final short DTYOTC = 467;
/*      */   static final short DTYKFNO = 468;
/*      */   static final short DTYKFNP = 469;
/*      */   static final short DTYOKGT8 = 470;
/*      */   static final short DTYRASB4 = 471;
/*      */   static final short DTYRAUB2 = 472;
/*      */   static final short DTYRAUB1 = 473;
/*      */   static final short DTYRATXT = 474;
/*      */   static final short DTYRSSB4 = 475;
/*      */   static final short DTYRSUB2 = 476;
/*      */   static final short DTYRSUB1 = 477;
/*      */   static final short DTYRSTXT = 478;
/*      */   static final short DTYRIDL = 479;
/*      */   static final short DTYGLRDD = 480;
/*      */   static final short DTYGLRDG = 481;
/*      */   static final short DTYGLRDC = 482;
/*      */   static final short DTYOKO = 483;
/*      */   static final short DTYDPP = 484;
/*      */   static final short DTYDPLS = 485;
/*      */   static final short DTYDPMOP = 486;
/*      */   static final short DTYSITZ = 231;
/*      */   static final short DTYESITZ = 232;
/*      */   static final short DTYUB8 = 233;
/*      */   static final short DTYSTAT = 490;
/*      */   static final short DTYRFX = 491;
/*      */   static final short DTYFAL = 492;
/*      */   static final short DTYCKV = 493;
/*      */   static final short DTYDRCX = 494;
/*      */   static final short DTYKGH = 495;
/*      */   static final short DTYAQO = 496;
/*      */   static final short DTYPNTY = 241;
/*      */   static final short DTYOKGT = 498;
/*      */   static final short DTYKPFC = 499;
/*      */   static final short DTYFE2 = 500;
/*      */   static final short DTYSPFP = 501;
/*      */   static final short DTYDPULS = 502;
/*      */   static final short DTY_T_VA = 247;
/*      */   static final short DTY_T_TB = 248;
/*      */   static final short DTYNLOB = 249;
/*      */   static final short DTYREC = 250;
/*      */   static final short DTYTAB = 251;
/*      */   static final short DTYBOL = 252;
/*      */   static final short DTYAQA = 509;
/*      */   static final short DTYKPBF = 510;
/*      */   static final short DTYDTY = 255;
/*      */   static final short DTYTSM = 513;
/*      */   static final short DTYMSS = 514;
/*      */   static final short DTYABS = 515;
/*      */   static final short DTYKPC = 516;
/*      */   static final short DTYCRS = 517;
/*      */   static final short DTYKKS = 518;
/*      */   static final short DTYKSP = 519;
/*      */   static final short DTYKSPTOP = 520;
/*      */   static final short DTYKSPVAL = 521;
/*      */   static final short DTYPSS = 522;
/*      */   static final short DTYNLS = 523;
/*      */   static final short DTYALS = 524;
/*      */   static final short DTYKSDEVTVAL = 525;
/*      */   static final short DTYKSDEVTTOP = 526;
/*      */   static final short DTYKPSPP = 527;
/*      */   static final short DTYKOL = 528;
/*      */   static final short DTYLST = 529;
/*      */   static final short DTYACX = 530;
/*      */   static final short DTYSCS = 531;
/*      */   static final short DTYRXH = 532;
/*      */   static final short DTYKPDNS = 533;
/*      */   static final short DTYKPDCN = 534;
/*      */   static final short DTYKPNNS = 535;
/*      */   static final short DTYKPNCN = 536;
/*      */   static final short DTYKPS = 537;
/*      */   static final short DTYAPINF = 538;
/*      */   static final short DTYTEN = 539;
/*      */   static final short DTYXSSCS = 540;
/*      */   static final short DTYXSSRO = 541;
/*      */   static final short DTYXSSPO = 542;
/*      */   static final short DTYKSRPC = 543;
/*      */   static final short DTYKVL = 560;
/*      */   static final short DTYXSSSDEF = 565;
/*      */   static final short DTYKPDQCINV = 572;
/*      */   static final short DTYKPDQIDC = 573;
/*      */   static final short DTYKPDQCSTA = 574;
/*      */   static final short DTYKPRS = 575;
/*      */   static final short DTYKPDQCID = 576;
/*      */   static final short DTYTRCEVT = 577;
/*      */   static final short DTYRTSTRM = 578;
/*      */   static final short DTYSESSRET = 579;
/*      */   static final short DTYSCN = 580;
/*      */   static final short DTYKECPA = 581;
/*      */   static final short DTYKECPP = 582;
/*      */   static final short DTYSXA = 583;
/*      */   static final short DTYKVARR = 584;
/*      */   static final short DTYKPNGN = 585;
/*      */   static final short DTYXSNSOP = 590;
/*      */   static final short DTYXSATTR = 591;
/*      */   static final short DTYXSNS = 592;
/*      */   static final short DTYMAX = 592;
/*      */   static final short SCALAR = 0;
/*      */   static final short RECORD = 1;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4C8TTIdty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */