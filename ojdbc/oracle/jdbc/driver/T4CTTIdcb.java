/*      */ package oracle.jdbc.driver;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.sql.SQLException;
/*      */ import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
/*      */ import oracle.jdbc.oracore.OracleTypeADT;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class T4CTTIdcb
/*      */   extends T4CTTIMsg
/*      */ {
/*      */   static final int DCBRXFR = 1;
/*      */   static final int DCBFIOT = 2;
/*      */   static final int DCBFHAVECOOKIE = 4;
/*      */   static final int DCBFNEWCOOKIE = 8;
/*      */   static final int DCBFREM = 16;
/*      */   int numuds;
/*      */   int colOffset;
/*      */   byte[] ignoreBuff;
/*  112 */   OracleStatement statement = null;
/*      */   
/*      */ 
/*      */   T4CTTIdcb(T4CConnection paramT4CConnection)
/*      */   {
/*  117 */     super(paramT4CConnection, (byte)16);
/*      */     
/*  119 */     this.ignoreBuff = new byte[40];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   void init(OracleStatement paramOracleStatement, int paramInt)
/*      */   {
/*  126 */     this.statement = paramOracleStatement;
/*  127 */     this.colOffset = paramInt;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   Accessor[] receive(Accessor[] paramArrayOfAccessor)
/*      */     throws SQLException, IOException
/*      */   {
/*  137 */     int i = this.meg.unmarshalUB1();
/*      */     
/*  139 */     if (this.ignoreBuff.length < i) {
/*  140 */       this.ignoreBuff = new byte[i];
/*      */     }
/*  142 */     this.meg.unmarshalNBytes(this.ignoreBuff, 0, i);
/*      */     
/*  144 */     int j = (int)this.meg.unmarshalUB4();
/*      */     
/*  146 */     paramArrayOfAccessor = receiveCommon(paramArrayOfAccessor, false);
/*      */     
/*  148 */     return paramArrayOfAccessor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   Accessor[] receiveFromRefCursor(Accessor[] paramArrayOfAccessor)
/*      */     throws SQLException, IOException
/*      */   {
/*  156 */     int i = this.meg.unmarshalUB1();
/*  157 */     int j = (int)this.meg.unmarshalUB4();
/*      */     
/*  159 */     paramArrayOfAccessor = receiveCommon(paramArrayOfAccessor, false);
/*      */     
/*  161 */     return paramArrayOfAccessor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   Accessor[] receiveCommon(Accessor[] paramArrayOfAccessor, boolean paramBoolean)
/*      */     throws SQLException, IOException
/*      */   {
/*  170 */     if (paramBoolean) {
/*  171 */       this.numuds = this.meg.unmarshalUB2();
/*      */     }
/*      */     else {
/*  174 */       this.numuds = ((int)this.meg.unmarshalUB4());
/*  175 */       if (this.numuds > 0)
/*      */       {
/*      */ 
/*      */ 
/*  179 */         int i = this.meg.unmarshalUB1();
/*      */       }
/*      */     }
/*      */     
/*  183 */     if (this.statement.needToPrepareDefineBuffer)
/*      */     {
/*      */ 
/*      */ 
/*  187 */       if ((paramArrayOfAccessor == null) || (paramArrayOfAccessor.length != this.numuds + this.colOffset))
/*      */       {
/*  189 */         localObject = new Accessor[this.numuds + this.colOffset];
/*  190 */         if ((paramArrayOfAccessor != null) && (paramArrayOfAccessor.length == this.colOffset))
/*      */         {
/*  192 */           System.arraycopy(paramArrayOfAccessor, 0, localObject, 0, this.colOffset);
/*      */         }
/*  194 */         paramArrayOfAccessor = (Accessor[])localObject;
/*      */       }
/*      */     }
/*      */     
/*  198 */     Object localObject = new T4C8TTIuds((T4CConnection)this.statement.connection);
/*      */     
/*  200 */     long l = this.statement.checkSum;
/*  201 */     for (int j = 0; j < this.numuds; j++)
/*      */     {
/*  203 */       ((T4C8TTIuds)localObject).unmarshal();
/*  204 */       String str = this.meg.conv.CharBytesToString(((T4C8TTIuds)localObject).getColumName(), ((T4C8TTIuds)localObject).getColumNameByteLength());
/*      */       
/*      */ 
/*  207 */       if (this.statement.needToPrepareDefineBuffer)
/*  208 */         l = fillupAccessors(paramArrayOfAccessor, this.colOffset + j, (T4C8TTIuds)localObject, str, l);
/*  209 */       this.statement.checkSum = l;
/*      */     }
/*      */     
/*  212 */     if (!paramBoolean)
/*      */     {
/*      */ 
/*  215 */       byte[] arrayOfByte1 = this.meg.unmarshalDALC();
/*      */       
/*      */ 
/*  218 */       if (this.connection.getTTCVersion() >= 3)
/*      */       {
/*  220 */         int k = (int)this.meg.unmarshalUB4();
/*  221 */         int m = (int)this.meg.unmarshalUB4();
/*      */         
/*      */ 
/*  224 */         if (this.connection.getTTCVersion() >= 4)
/*      */         {
/*  226 */           int n = (int)this.meg.unmarshalUB4();
/*  227 */           int i1 = (int)this.meg.unmarshalUB4();
/*      */           
/*  229 */           if (this.connection.getTTCVersion() >= 5)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  235 */             byte[] arrayOfByte2 = this.meg.unmarshalDALC();
/*      */           }
/*      */         }
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  260 */     if (this.statement.needToPrepareDefineBuffer)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  266 */       if (!paramBoolean)
/*      */       {
/*  268 */         this.statement.rowPrefetchInLastFetch = -1;
/*  269 */         this.statement.describedWithNames = true;
/*  270 */         this.statement.described = true;
/*  271 */         this.statement.numberOfDefinePositions = this.numuds;
/*  272 */         this.statement.accessors = paramArrayOfAccessor;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  277 */         this.statement.prepareAccessors();
/*      */         
/*      */ 
/*  280 */         this.statement.allocateTmpByteArray();
/*      */       }
/*      */     }
/*      */     
/*  284 */     return paramArrayOfAccessor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   long fillupAccessors(Accessor[] paramArrayOfAccessor, int paramInt, T4C8TTIuds paramT4C8TTIuds, String paramString, long paramLong)
/*      */     throws SQLException
/*      */   {
/*  296 */     int[] arrayOfInt1 = this.statement.definedColumnType;
/*  297 */     int[] arrayOfInt2 = this.statement.definedColumnSize;
/*  298 */     int[] arrayOfInt3 = this.statement.definedColumnFormOfUse;
/*  299 */     int i = this.statement.sqlObject.includeRowid ? 1 : 0;
/*      */     
/*      */ 
/*  302 */     String str1 = null;
/*  303 */     String str2 = null;
/*  304 */     String str3 = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  311 */     int m = 0;
/*  312 */     int n = 0;
/*  313 */     int i1 = 0;
/*      */     
/*      */ 
/*      */     int i2;
/*      */     
/*  318 */     if (paramInt >= i)
/*      */     {
/*  320 */       i2 = paramInt - i;
/*  321 */       if ((arrayOfInt1 != null) && (arrayOfInt1.length > i2) && (arrayOfInt1[i2] != 0))
/*      */       {
/*      */ 
/*  324 */         m = arrayOfInt1[i2];
/*      */       }
/*  326 */       if ((arrayOfInt2 != null) && (arrayOfInt2.length > i2) && (arrayOfInt2[i2] > 0))
/*      */       {
/*      */ 
/*  329 */         n = arrayOfInt2[i2];
/*      */       }
/*  331 */       if ((arrayOfInt3 != null) && (arrayOfInt3.length > i2) && (arrayOfInt3[i2] > 0))
/*      */       {
/*      */ 
/*  334 */         i1 = arrayOfInt3[i2];
/*      */       }
/*      */     }
/*  337 */     int j = paramT4C8TTIuds.udsoac.oacmxl;
/*      */     int k;
/*  339 */     switch (paramT4C8TTIuds.udsoac.oacdty)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     case 96: 
/*  353 */       if ((paramT4C8TTIuds.udsoac.oacmxlc != 0) && (paramT4C8TTIuds.udsoac.oacmxlc < j))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  359 */         j = 2 * paramT4C8TTIuds.udsoac.oacmxlc;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  364 */       k = j;
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  369 */       if (((m == 1) || (m == 12)) && (n > 0) && (n < j))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  374 */         k = n;
/*      */       }
/*  376 */       paramArrayOfAccessor[paramInt] = new T4CCharAccessor(this.statement, k, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, j, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  391 */       if (((paramT4C8TTIuds.udsoac.oacfl2 & 0x1000) == 4096) || (paramT4C8TTIuds.udsoac.oacmxlc != 0))
/*      */       {
/*  393 */         paramArrayOfAccessor[paramInt].setDisplaySize(paramT4C8TTIuds.udsoac.oacmxlc);
/*      */       }
/*      */       
/*      */ 
/*      */       break;
/*      */     case 2: 
/*  399 */       paramArrayOfAccessor[paramInt] = new T4CNumberAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  413 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     case 1: 
/*  418 */       if ((paramT4C8TTIuds.udsoac.oacmxlc != 0) && (paramT4C8TTIuds.udsoac.oacmxlc < j)) {
/*  419 */         j = 2 * paramT4C8TTIuds.udsoac.oacmxlc;
/*      */       }
/*  421 */       k = j;
/*      */       
/*  423 */       if (((m == 1) || (m == 12)) && (n > 0) && (n < j))
/*      */       {
/*      */ 
/*      */ 
/*  427 */         k = n;
/*      */       }
/*  429 */       paramArrayOfAccessor[paramInt] = new T4CVarcharAccessor(this.statement, k, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, j, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  444 */       if (((paramT4C8TTIuds.udsoac.oacfl2 & 0x1000) == 4096) || (paramT4C8TTIuds.udsoac.oacmxlc != 0))
/*      */       {
/*  446 */         paramArrayOfAccessor[paramInt].setDisplaySize(paramT4C8TTIuds.udsoac.oacmxlc);
/*      */       }
/*      */       
/*      */       break;
/*      */     case 8: 
/*  451 */       if (((m == 1) || (m == 12)) && (this.connection.versionNumber >= 9000) && (n < 4001))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  465 */         if (n > 0) {
/*  466 */           k = n;
/*      */         }
/*      */         else
/*      */         {
/*  470 */           k = 4000;
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  476 */         j = -1;
/*  477 */         paramArrayOfAccessor[paramInt] = new T4CVarcharAccessor(this.statement, k, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, j, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  491 */         paramArrayOfAccessor[paramInt].describeType = 8;
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*  498 */         j = 0;
/*      */         
/*  500 */         paramArrayOfAccessor[paramInt] = new T4CLongAccessor(this.statement, paramInt + 1, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  516 */       break;
/*      */     
/*      */     case 6: 
/*  519 */       paramArrayOfAccessor[paramInt] = new T4CVarnumAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  533 */       break;
/*      */     
/*      */     case 100: 
/*  536 */       paramArrayOfAccessor[paramInt] = new T4CBinaryFloatAccessor(this.statement, 4, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  555 */       break;
/*      */     
/*      */     case 101: 
/*  558 */       paramArrayOfAccessor[paramInt] = new T4CBinaryDoubleAccessor(this.statement, 8, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  573 */       break;
/*      */     
/*      */     case 23: 
/*  576 */       paramArrayOfAccessor[paramInt] = new T4CRawAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  590 */       break;
/*      */     
/*      */ 
/*      */     case 24: 
/*  594 */       if ((m == -2) && (n < 2001) && (this.connection.versionNumber >= 9000))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  601 */         j = -1;
/*  602 */         paramArrayOfAccessor[paramInt] = new T4CRawAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  615 */         paramArrayOfAccessor[paramInt].describeType = 24;
/*      */       }
/*      */       else {
/*  618 */         paramArrayOfAccessor[paramInt] = new T4CLongRawAccessor(this.statement, paramInt + 1, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  633 */       break;
/*      */     
/*      */     case 11: 
/*      */     case 104: 
/*      */     case 208: 
/*  638 */       paramArrayOfAccessor[paramInt] = new T4CRowidAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  652 */       if (paramT4C8TTIuds.udsoac.oacdty == 208) {
/*  653 */         paramArrayOfAccessor[paramInt].describeType = 208;
/*      */       }
/*      */       
/*      */       break;
/*      */     case 102: 
/*  658 */       paramArrayOfAccessor[paramInt] = new T4CResultSetAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  672 */       break;
/*      */     
/*      */     case 12: 
/*  675 */       paramArrayOfAccessor[paramInt] = new T4CDateAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  689 */       break;
/*      */     
/*      */     case 113: 
/*  692 */       if ((m == -4) && (this.connection.versionNumber >= 9000))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  697 */         paramArrayOfAccessor[paramInt] = new T4CLongRawAccessor(this.statement, paramInt + 1, Integer.MAX_VALUE, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  712 */         paramArrayOfAccessor[paramInt].describeType = 113;
/*      */       }
/*  714 */       else if ((m == -3) && (this.connection.versionNumber >= 9000))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  719 */         paramArrayOfAccessor[paramInt] = new T4CRawAccessor(this.statement, 4000, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  732 */         paramArrayOfAccessor[paramInt].describeType = 113;
/*      */       }
/*      */       else
/*      */       {
/*  736 */         paramArrayOfAccessor[paramInt] = new T4CBlobAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  750 */         if ((this.connection.useLobPrefetch) && (m == 2004))
/*      */         {
/*  752 */           paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = n;
/*      */         }
/*      */         else
/*      */         {
/*  756 */           paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = -1; }
/*      */       }
/*  758 */       break;
/*      */     
/*      */     case 112: 
/*  761 */       i2 = 1;
/*  762 */       if (i1 != 0) {
/*  763 */         i2 = (short)i1;
/*      */       }
/*  765 */       if (((m == -1) || (m == -16)) && (this.connection.versionNumber >= 9000))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  774 */         j = 0;
/*  775 */         paramArrayOfAccessor[paramInt] = new T4CLongAccessor(this.statement, paramInt + 1, Integer.MAX_VALUE, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, i2, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  791 */         paramArrayOfAccessor[paramInt].describeType = 112;
/*      */       }
/*  793 */       else if (((m == 12) || (m == 1) || (m == -15) || (m == -9)) && (this.connection.versionNumber >= 9000))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  804 */         k = 4000;
/*  805 */         if ((n > 0) && (n < k))
/*      */         {
/*  807 */           k = n;
/*      */         }
/*  809 */         paramArrayOfAccessor[paramInt] = new T4CVarcharAccessor(this.statement, k, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, i2, 4000, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  824 */         paramArrayOfAccessor[paramInt].describeType = 112;
/*      */       }
/*      */       else {
/*  827 */         paramArrayOfAccessor[paramInt] = new T4CClobAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  840 */         if ((this.connection.useLobPrefetch) && ((m == 2005) || (m == 2011)))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  849 */           paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = n;
/*      */         } else
/*  851 */           paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = -1;
/*      */       }
/*  853 */       break;
/*      */     
/*      */     case 114: 
/*  856 */       paramArrayOfAccessor[paramInt] = new T4CBfileAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  869 */       if ((this.connection.useLobPrefetch) && (m == -13))
/*      */       {
/*      */ 
/*  872 */         paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = n;
/*      */       } else
/*  874 */         paramArrayOfAccessor[paramInt].lobPrefetchSizeForThisColumn = -1;
/*  875 */       break;
/*      */     
/*      */     case 109: 
/*  878 */       str1 = this.meg.conv.CharBytesToString(paramT4C8TTIuds.getTypeName(), paramT4C8TTIuds.getTypeCharLength());
/*      */       
/*      */ 
/*      */ 
/*  882 */       str2 = this.meg.conv.CharBytesToString(paramT4C8TTIuds.getSchemaName(), paramT4C8TTIuds.getSchemaCharLength());
/*      */       
/*      */ 
/*  885 */       str3 = str2 + "." + str1;
/*      */       
/*  887 */       paramArrayOfAccessor[paramInt] = new T4CNamedTypeAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, str3, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  902 */       break;
/*      */     
/*      */     case 111: 
/*  905 */       str1 = this.meg.conv.CharBytesToString(paramT4C8TTIuds.getTypeName(), paramT4C8TTIuds.getTypeCharLength());
/*      */       
/*      */ 
/*      */ 
/*  909 */       str2 = this.meg.conv.CharBytesToString(paramT4C8TTIuds.getSchemaName(), paramT4C8TTIuds.getSchemaCharLength());
/*      */       
/*      */ 
/*  912 */       str3 = str2 + "." + str1;
/*      */       
/*  914 */       paramArrayOfAccessor[paramInt] = new T4CRefTypeAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, str3, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  929 */       break;
/*      */     
/*      */     case 180: 
/*  932 */       paramArrayOfAccessor[paramInt] = new T4CTimestampAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  946 */       break;
/*      */     
/*      */     case 181: 
/*  949 */       paramArrayOfAccessor[paramInt] = new T4CTimestamptzAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  963 */       break;
/*      */     
/*      */     case 231: 
/*  966 */       paramArrayOfAccessor[paramInt] = new T4CTimestampltzAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  980 */       break;
/*      */     
/*      */     case 182: 
/*  983 */       paramArrayOfAccessor[paramInt] = new T4CIntervalymAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  997 */       break;
/*      */     
/*      */     case 183: 
/* 1000 */       paramArrayOfAccessor[paramInt] = new T4CIntervaldsAccessor(this.statement, j, paramT4C8TTIuds.udsnull, paramT4C8TTIuds.udsoac.oacflg, paramT4C8TTIuds.udsoac.oacpre, paramT4C8TTIuds.udsoac.oacscl, paramT4C8TTIuds.udsoac.oacfl2, paramT4C8TTIuds.udsoac.oacmal, paramT4C8TTIuds.udsoac.oaccsfrm, m, n, this.meg);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1014 */       break;
/*      */     
/*      */ 
/*      */ 
/*      */     default: 
/* 1019 */       paramArrayOfAccessor[paramInt] = null;
/*      */     }
/*      */     
/*      */     
/*      */ 
/*      */ 
/* 1025 */     if (paramT4C8TTIuds.udsoac.oactoid.length > 0)
/*      */     {
/* 1027 */       paramArrayOfAccessor[paramInt].internalOtype = new OracleTypeADT(paramT4C8TTIuds.udsoac.oactoid, paramT4C8TTIuds.udsoac.oacvsn, paramT4C8TTIuds.udsoac.oaccsi, paramT4C8TTIuds.udsoac.oaccsfrm, str2 + "." + str1);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1032 */       paramArrayOfAccessor[paramInt].internalOtype = null;
/*      */     }
/*      */     
/* 1035 */     paramArrayOfAccessor[paramInt].columnName = paramString;
/*      */     
/*      */ 
/* 1038 */     paramArrayOfAccessor[paramInt].securityAttribute = OracleResultSetMetaData.SecurityAttribute.NONE;
/*      */     
/* 1040 */     if ((paramT4C8TTIuds.udsflg & 0x1) != 0) {
/* 1041 */       paramArrayOfAccessor[paramInt].securityAttribute = OracleResultSetMetaData.SecurityAttribute.ENABLED;
/*      */     }
/* 1043 */     else if ((paramT4C8TTIuds.udsflg & 0x2) != 0) {
/* 1044 */       paramArrayOfAccessor[paramInt].securityAttribute = OracleResultSetMetaData.SecurityAttribute.UNKNOWN;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1049 */     if (paramT4C8TTIuds.udsoac.oacmxl == 0) {
/* 1050 */       paramArrayOfAccessor[paramInt].isNullByDescribe = true;
/*      */     }
/* 1052 */     paramArrayOfAccessor[paramInt].udskpos = paramT4C8TTIuds.getKernelPosition();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1069 */     if (this.connection.calculateChecksum)
/*      */     {
/* 1071 */       paramLong = CRC64.updateChecksum(paramLong, paramT4C8TTIuds.udsoac.oacdty);
/*      */       
/* 1073 */       paramLong = CRC64.updateChecksum(paramLong, paramT4C8TTIuds.udsoac.oacmxl);
/*      */       
/* 1075 */       paramLong = CRC64.updateChecksum(paramLong, paramT4C8TTIuds.udsoac.oacpre);
/*      */       
/* 1077 */       paramLong = CRC64.updateChecksum(paramLong, paramT4C8TTIuds.udsoac.oacscl);
/*      */       
/* 1079 */       paramLong = CRC64.updateChecksum(paramLong, paramT4C8TTIuds.udsoac.oaccsfrm);
/*      */       
/* 1081 */       if (str1 != null)
/*      */       {
/* 1083 */         paramLong = CRC64.updateChecksum(paramLong, str2 + "." + str1);
/*      */       }
/*      */       
/*      */ 
/* 1087 */       paramLong = CRC64.updateChecksum(paramLong, paramString);
/*      */     }
/*      */     
/* 1090 */     return paramLong;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1097 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIdcb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */