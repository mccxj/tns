package oracle.jdbc.driver;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.BitSet;
import java.util.Vector;
import oracle.jdbc.OracleResultSetMetaData.SecurityAttribute;
import oracle.jdbc.internal.OracleConnection;
import oracle.jdbc.internal.OracleStatement.SqlKind;
import oracle.jdbc.oracore.OracleTypeADT;
class T4CTTIrxd
  extends T4CTTIMsg
{
/*  46 */   static final byte[] NO_BYTES = new byte[0];
  
  byte[] buffer;
  
  byte[] bufferCHAR;
  
/*  56 */   BitSet bvcColSent = null;
/*  57 */   int nbOfColumns = 0;
/*  58 */   boolean bvcFound = false;
  
  boolean isFirstCol;
  
  static final byte TTICMD_UNAUTHORIZED = 1;
  
  T4CTTIrxd(T4CConnection paramT4CConnection)
  {
/*  78 */     super(paramT4CConnection, (byte)7);
    
/*  80 */     this.isFirstCol = true;
  }
  
  void init()
  {
/*  87 */     this.isFirstCol = true;
  }
  
  void setNumberOfColumns(int paramInt)
  {
/*  94 */     this.nbOfColumns = paramInt;
/*  95 */     this.bvcFound = false;
    
/*  97 */     if ((this.bvcColSent == null) || (this.bvcColSent.length() < this.nbOfColumns)) {
/*  98 */       this.bvcColSent = new BitSet(this.nbOfColumns);
    } else {
/* 100 */       this.bvcColSent.clear();
    }
  }
  
  void unmarshalBVC(int paramInt)
    throws SQLException, IOException
  {
/* 108 */     int i = 0;
    
/* 111 */     for (int j = 0; j < this.bvcColSent.length(); j++) {
/* 112 */       this.bvcColSent.clear(j);
    }
    
/* 115 */     j = this.nbOfColumns / 8 + (this.nbOfColumns % 8 != 0 ? 1 : 0);
    
/* 118 */     for (int k = 0; k < j; k++)
    {
/* 120 */       int m = (byte)(this.meg.unmarshalUB1() & 0xFF);
      
/* 122 */       for (int n = 0; n < 8; n++)
      {
/* 124 */         if ((m & 1 << n) != 0)
        {
/* 126 */           this.bvcColSent.set(k * 8 + n);
          
/* 128 */           i++;
        }
      }
    }
    
/* 133 */     if (i != paramInt)
    {
/* 136 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), -1, "INTERNAL ERROR: oracle.jdbc.driver.T4CTTIrxd.unmarshalBVC: bits missing in vector");
      
/* 138 */       localSQLException.fillInStackTrace();
/* 139 */       throw localSQLException;
    }
    
/* 143 */     this.bvcFound = true;
  }
  
  void readBitVector(byte[] paramArrayOfByte)
    throws SQLException, IOException
  {
/* 156 */     for (int i = 0; i < this.bvcColSent.length(); i++) {
/* 157 */       this.bvcColSent.clear(i);
    }
/* 159 */     if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0)) {
/* 160 */       this.bvcFound = false;
    }
    else {
/* 163 */       for (i = 0; i < paramArrayOfByte.length; i++) {
/* 164 */         int j = paramArrayOfByte[i];
/* 165 */         for (int k = 0; k < 8; k++) {
/* 166 */           if ((j & 1 << k) != 0)
/* 167 */             this.bvcColSent.set(i * 8 + k);
        }
      }
/* 170 */       this.bvcFound = true;
    }
  }
  
  Vector<IOException> marshal(byte[] paramArrayOfByte1, char[] paramArrayOfChar1, short[] paramArrayOfShort1, int paramInt1, byte[] paramArrayOfByte2, DBConversion paramDBConversion, InputStream[] paramArrayOfInputStream, byte[][] paramArrayOfByte, OracleTypeADT[] paramArrayOfOracleTypeADT, byte[] paramArrayOfByte3, char[] paramArrayOfChar2, short[] paramArrayOfShort2, byte[] paramArrayOfByte4, int paramInt2, int[] paramArrayOfInt1, boolean paramBoolean1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int[][] paramArrayOfInt, boolean paramBoolean2)
    throws IOException
  {
/* 198 */     Vector localVector = null;
    
    try
    {
/* 203 */       marshalTTCcode();
      
/* 206 */       int i = paramArrayOfShort1[(paramInt1 + 0)] & 0xFFFF;
      
/* 228 */       int i10 = 0;
/* 229 */       int i11 = paramArrayOfInt3[0];
/* 230 */       Object localObject1 = paramArrayOfInt[0];
      
/* 232 */       int i12 = 0;
      int i13;
      int i14;
      int i15;
/* 236 */       int j; int i7; int k; int i9; int m; int i8; int i23; int i24; int i4; int i6; int i5; int i1; int i3; int i18; int i20; if (paramBoolean2)
      {
/* 238 */         i13 = 1;
/* 239 */         if ((!$assertionsDisabled) && (i11 <= 0)) throw new AssertionError("No postoned columns in RXD");
      }
      else
      {
/* 243 */         for (i14 = 0; i14 < i; i14++)
        {
/* 245 */           if ((i10 < i11) && (localObject1[i10] == i14))
          {
/* 248 */             i10++;
          }
          else
          {
/* 252 */             i15 = 0;
            
/* 257 */             j = paramInt1 + 5 + 10 * i14;
            
/* 262 */             i7 = paramArrayOfShort1[(j + 0)] & 0xFFFF;
            
/* 267 */             if ((paramArrayOfByte4 != null) && ((paramArrayOfByte4[i14] & 0x20) == 0))
            {
/* 271 */               if (i7 == 998) {
/* 272 */                 i12++;
              }
            }
            else
            {
/* 277 */               k = ((paramArrayOfShort1[(j + 7)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 8)] & 0xFFFF) + paramInt2;
              
/* 281 */               i9 = ((paramArrayOfShort1[(j + 5)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 6)] & 0xFFFF) + paramInt2;
              
/* 286 */               m = paramArrayOfShort1[k] & 0xFFFF;
              
/* 288 */               i8 = paramArrayOfShort1[i9];
              
/* 290 */               if (i7 == 116)
              {
/* 292 */                 this.meg.marshalUB1((short)1);
/* 293 */                 this.meg.marshalUB1((short)0);
              }
              else
              {
/* 297 */                 if (i7 == 994)
                {
/* 299 */                   i8 = -1;
/* 300 */                   int i16 = paramArrayOfInt2[(3 + i14 * 4 + 0)];
                  
/* 304 */                   if (i16 == 109) {
/* 305 */                     i15 = 1;
                  }
/* 307 */                 } else if ((i7 == 8) || (i7 == 24) || ((!paramBoolean1) && (paramArrayOfInt1 != null) && (paramArrayOfInt1.length > i14) && (paramArrayOfInt1[i14] > 4000)))
                {
/* 324 */                   if (i11 >= localObject1.length)
                  {
/* 326 */                     int[] arrayOfInt = new int[localObject1.length << 1];
                    
/* 329 */                     System.arraycopy(localObject1, 0, arrayOfInt, 0, localObject1.length);
                    
/* 333 */                     localObject1 = arrayOfInt;
                  }
                  
/* 336 */                   localObject1[(i11++)] = i14;
                  
/* 340 */                   continue;
                }
                
/* 344 */                 if (i8 == -1)
                {
/* 346 */                   if ((i7 == 109) || (i15 != 0))
                  {
/* 349 */                     this.meg.marshalDALC(NO_BYTES);
/* 350 */                     this.meg.marshalDALC(NO_BYTES);
/* 351 */                     this.meg.marshalDALC(NO_BYTES);
/* 352 */                     this.meg.marshalUB2(0);
/* 353 */                     this.meg.marshalUB4(0L);
/* 354 */                     this.meg.marshalUB2(1);
                    
/* 356 */                     continue;
                  }
/* 358 */                   if (i7 == 998)
                  {
/* 360 */                     i12++;
/* 361 */                     this.meg.marshalUB4(0L);
/* 362 */                     continue;
                  }
/* 364 */                   if ((i7 == 112) || (i7 == 113) || (i7 == 114))
                  {
/* 366 */                     this.meg.marshalUB4(0L);
/* 367 */                     continue;
                  }
/* 369 */                   if ((i7 != 8) && (i7 != 24))
                  {
/* 378 */                     this.meg.marshalUB1((short)0);
                    
/* 380 */                     continue;
                  } }
                int i19;
                int i25;
/* 384 */                 if (i7 == 998)
                {
/* 387 */                   int i17 = (paramArrayOfShort2[(6 + i12 * 8 + 4)] & 0xFFFF) << 16 & 0xFFFF000 | paramArrayOfShort2[(6 + i12 * 8 + 5)] & 0xFFFF;
                  
/* 390 */                   i19 = (paramArrayOfShort2[(6 + i12 * 8 + 6)] & 0xFFFF) << 16 & 0xFFFF000 | paramArrayOfShort2[(6 + i12 * 8 + 7)] & 0xFFFF;
                  
/* 393 */                   int i21 = paramArrayOfShort2[(6 + i12 * 8)] & 0xFFFF;
/* 394 */                   i23 = paramArrayOfShort2[(6 + i12 * 8 + 1)] & 0xFFFF;
                  
/* 396 */                   this.meg.marshalUB4(i17);
                  
/* 398 */                   for (i24 = 0; i24 < i17; i24++)
                  {
/* 400 */                     i25 = i19 + i24 * i23;
                    
/* 402 */                     if (i21 == 9)
                    {
/* 404 */                       i4 = paramArrayOfChar2[i25] / '\002';
/* 405 */                       i6 = 0;
/* 406 */                       i6 = paramDBConversion.javaCharsToCHARBytes(paramArrayOfChar2, i25 + 1, paramArrayOfByte2, 0, i4);
                      
/* 412 */                       this.meg.marshalCLR(paramArrayOfByte2, i6);
                    }
                    else
                    {
/* 416 */                       m = paramArrayOfByte3[i25];
                      
/* 418 */                       if (m < 1) {
/* 419 */                         this.meg.marshalUB1((short)0);
                      } else {
/* 421 */                         this.meg.marshalCLR(paramArrayOfByte3, i25 + 1, m);
                      }
                    }
                  }
/* 425 */                   i12++;
                }
                else
                {
/* 434 */                   int n = paramArrayOfShort1[(j + 1)] & 0xFFFF;
                  
/* 438 */                   if (n != 0)
                  {
/* 444 */                     int i2 = ((paramArrayOfShort1[(j + 3)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 4)] & 0xFFFF) + n * paramInt2;
                    
/* 451 */                     if (i7 == 6)
                    {
/* 453 */                       i2++;
/* 454 */                       m--;
                    }
/* 456 */                     else if (i7 == 9)
                    {
/* 458 */                       i2 += 2;
                      
/* 460 */                       m -= 2;
                    }
/* 462 */                     else if ((i7 == 114) || (i7 == 113) || (i7 == 112))
                    {
/* 465 */                       this.meg.marshalUB4(m);
                    }
                    
                    Object localObject2;
/* 469 */                     if ((i7 == 109) || (i7 == 111))
                    {
/* 471 */                       if (paramArrayOfByte == null)
                      {
/* 474 */                         localObject2 = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), -1, "INTERNAL ERROR: oracle.jdbc.driver.T4CTTIrxd.marshal: parameterDatum is null");
                        
/* 476 */                         ((SQLException)localObject2).fillInStackTrace();
/* 477 */                         throw ((Throwable)localObject2);
                      }
                      
/* 481 */                       localObject2 = paramArrayOfByte[i14];
                      
/* 483 */                       m = localObject2 == null ? 0 : localObject2.length;
                      
/* 485 */                       if (i7 == 109)
                      {
/* 487 */                         this.meg.marshalDALC(NO_BYTES);
/* 488 */                         this.meg.marshalDALC(NO_BYTES);
/* 489 */                         this.meg.marshalDALC(NO_BYTES);
/* 490 */                         this.meg.marshalUB2(0);
                        
/* 492 */                         this.meg.marshalUB4(m);
/* 493 */                         this.meg.marshalUB2(1);
                      }
                      
/* 496 */                       if (m > 0) {
/* 497 */                         this.meg.marshalCLR((byte[])localObject2, 0, m);
                      }
/* 499 */                     } else if (i7 == 104)
                    {
/* 503 */                       i2 += 2;
                      
/* 505 */                       localObject2 = T4CRowidAccessor.stringToRowid(paramArrayOfByte1, i2, 18);
                      
/* 507 */                       i19 = 14;
/* 508 */                       long l1 = localObject2[0];
/* 509 */                       i24 = (int)localObject2[1];
/* 510 */                       i25 = 0;
/* 511 */                       long l2 = localObject2[2];
/* 512 */                       int i26 = (int)localObject2[3];
                      
/* 515 */                       if ((l1 == 0L) && (i24 == 0) && (l2 == 0L) && (i26 == 0))
                      {
/* 521 */                         this.meg.marshalUB1((short)0);
                      }
                      else
                      {
/* 525 */                         this.meg.marshalUB1(i19);
/* 526 */                         this.meg.marshalUB4(l1);
/* 527 */                         this.meg.marshalUB2(i24);
/* 528 */                         this.meg.marshalUB1(i25);
/* 529 */                         this.meg.marshalUB4(l2);
/* 530 */                         this.meg.marshalUB2(i26);
                      }
                    }
/* 533 */                     else if (i7 == 208)
                    {
/* 537 */                       i2 += 2;
/* 538 */                       m -= 2;
/* 539 */                       this.meg.marshalUB4(m);
/* 540 */                       this.meg.marshalCLR(paramArrayOfByte1, i2, m);
                    }
/* 544 */                     else if (m < 1) {
/* 545 */                       this.meg.marshalUB1((short)0);
                    } else {
/* 547 */                       this.meg.marshalCLR(paramArrayOfByte1, i2, m);
                    }
                    
                  }
                  else
                  {
/* 557 */                     i5 = paramArrayOfShort1[(j + 9)] & 0xFFFF;
                    
/* 564 */                     i1 = paramArrayOfShort1[(j + 2)] & 0xFFFF;
                    
/* 567 */                     i3 = ((paramArrayOfShort1[(j + 3)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 4)] & 0xFFFF) + i1 * paramInt2 + 1;
                    
/* 572 */                     if (i7 == 996)
                    {
/* 581 */                       i18 = paramArrayOfChar1[(i3 - 1)];
                      
/* 583 */                       if ((this.bufferCHAR == null) || (this.bufferCHAR.length < i18)) {
/* 584 */                         this.bufferCHAR = new byte[i18];
                      }
/* 586 */                       for (i20 = 0; i20 < i18; i20++)
                      {
/* 588 */                         this.bufferCHAR[i20] = ((byte)((paramArrayOfChar1[(i3 + i20 / 2)] & 0xFF00) >> '\b' & 0xFF));
                        
/* 592 */                         if (i20 < i18 - 1)
                        {
/* 594 */                           this.bufferCHAR[(i20 + 1)] = ((byte)(paramArrayOfChar1[(i3 + i20 / 2)] & 0xFF & 0xFF));
                          
/* 597 */                           i20++;
                        }
                      }
                      
/* 601 */                       this.meg.marshalCLR(this.bufferCHAR, i18);
                      
/* 603 */                       if (this.bufferCHAR.length > 4000) {
/* 604 */                         this.bufferCHAR = null;
                      }
                      
                    }
                    else
                    {
/* 615 */                       if (i7 == 96)
                      {
/* 619 */                         i4 = m / 2;
/* 620 */                         i3--;
                      }
                      else
                      {
/* 624 */                         i4 = (m - 2) / 2;
                      }
                      
/* 628 */                       i6 = 0;
                      
/* 633 */                       if (i5 == 2)
                      {
/* 635 */                         i6 = paramDBConversion.javaCharsToNCHARBytes(paramArrayOfChar1, i3, paramArrayOfByte2, 0, i4);
                      }
                      else
                      {
/* 640 */                         i6 = paramDBConversion.javaCharsToCHARBytes(paramArrayOfChar1, i3, paramArrayOfByte2, 0, i4);
                      }
                      
/* 646 */                       this.meg.marshalCLR(paramArrayOfByte2, i6);
                    }
                  }
                }
              }
            }
          }
        }
        
/* 656 */         i13 = i11;
      }
      
/* 659 */       if (i11 > 0)
      {
/* 661 */         for (i14 = 0; i14 < i13; i14++)
        {
/* 663 */           i15 = localObject1[i14];
          
/* 665 */           j = paramInt1 + 5 + 10 * i15;
          
/* 669 */           i7 = paramArrayOfShort1[(j + 0)] & 0xFFFF;
          
/* 673 */           k = ((paramArrayOfShort1[(j + 7)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 8)] & 0xFFFF) + paramInt2;
          
/* 677 */           i9 = ((paramArrayOfShort1[(j + 5)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 6)] & 0xFFFF) + paramInt2;
          
/* 681 */           i8 = paramArrayOfShort1[i9];
/* 682 */           m = paramArrayOfShort1[k] & 0xFFFF;
          
/* 684 */           i1 = paramArrayOfShort1[(j + 2)] & 0xFFFF;
          
/* 687 */           i3 = ((paramArrayOfShort1[(j + 3)] & 0xFFFF) << 16) + (paramArrayOfShort1[(j + 4)] & 0xFFFF) + i1 * paramInt2 + 1;
          
/* 692 */           if (i8 == -1)
          {
/* 694 */             this.meg.marshalUB1((short)0);
          }
/* 698 */           else if (i7 == 996)
          {
/* 707 */             i18 = paramArrayOfChar1[(i3 - 1)];
            
/* 709 */             if ((this.bufferCHAR == null) || (this.bufferCHAR.length < i18)) {
/* 710 */               this.bufferCHAR = new byte[i18];
            }
/* 712 */             for (i20 = 0; i20 < i18; i20++)
            {
/* 714 */               this.bufferCHAR[i20] = ((byte)((paramArrayOfChar1[(i3 + i20 / 2)] & 0xFF00) >> '\b' & 0xFF));
              
/* 718 */               if (i20 < i18 - 1)
              {
/* 720 */                 this.bufferCHAR[(i20 + 1)] = ((byte)(paramArrayOfChar1[(i3 + i20 / 2)] & 0xFF & 0xFF));
                
/* 722 */                 i20++;
              }
            }
            
/* 726 */             this.meg.marshalCLR(this.bufferCHAR, i18);
            
/* 728 */             if (this.bufferCHAR.length > 4000) {
/* 729 */               this.bufferCHAR = null;
            }
/* 731 */           } else if ((i7 != 8) && (i7 != 24))
          {
/* 738 */             if (i7 == 96)
            {
/* 742 */               i4 = m / 2;
/* 743 */               i3--;
            }
            else
            {
/* 747 */               i4 = (m - 2) / 2;
            }
            
/* 750 */             i5 = paramArrayOfShort1[(j + 9)] & 0xFFFF;
            
/* 755 */             i6 = 0;
            
/* 760 */             if (i5 == 2)
            {
/* 762 */               i6 = paramDBConversion.javaCharsToNCHARBytes(paramArrayOfChar1, i3, paramArrayOfByte2, 0, i4);
            }
            else
            {
/* 767 */               i6 = paramDBConversion.javaCharsToCHARBytes(paramArrayOfChar1, i3, paramArrayOfByte2, 0, i4);
            }
            
/* 773 */             this.meg.marshalCLR(paramArrayOfByte2, i6);
          }
          else
          {
/* 778 */             i18 = i15;
            
/* 781 */             if (paramArrayOfInputStream != null)
            {
/* 783 */               InputStream localInputStream = paramArrayOfInputStream[i18];
              
/* 785 */               if (localInputStream != null)
              {
/* 789 */                 int i22 = 64;
                
/* 791 */                 if (this.buffer == null) {
/* 792 */                   this.buffer = new byte[i22];
                }
/* 794 */                 i23 = 0;
                
/* 797 */                 this.meg.marshalUB1((short)254);
                
/* 799 */                 i24 = 0;
                
/* 801 */                 while ((i24 == 0) && (!this.connection.sentCancel))
                {
                  try {
/* 804 */                     i23 = localInputStream.read(this.buffer, 0, i22);
                  } catch (IOException localIOException2) {
/* 806 */                     i23 = -1;
/* 807 */                     if (localVector == null)
/* 808 */                       localVector = new Vector();
/* 809 */                     localVector.add(localIOException2);
                  }
                  
/* 812 */                   if (i23 == -1) {
/* 813 */                     i24 = 1;
                  }
/* 815 */                   if (i23 > 0)
                  {
/* 819 */                     this.meg.marshalUB1((short)(i23 & 0xFF));
                    
/* 822 */                     this.meg.marshalB1Array(this.buffer, 0, i23);
                  }
                }
                
/* 827 */                 this.meg.marshalUB1((short)0);
              }
            }
          }
        }
      }
      
/* 844 */       paramArrayOfInt3[0] = i11;
/* 845 */       paramArrayOfInt[0] = localObject1;
    }
    catch (SQLException localSQLException)
    {
/* 849 */       IOException localIOException1 = new IOException();
/* 850 */       localIOException1.initCause(localSQLException);
/* 851 */       throw localIOException1;
    }
    
/* 854 */     return localVector;
  }
  
  boolean unmarshal(Accessor[] paramArrayOfAccessor, int paramInt)
    throws SQLException, IOException
  {
/* 865 */     return unmarshal(paramArrayOfAccessor, 0, paramInt);
  }
  
  boolean unmarshal(Accessor[] paramArrayOfAccessor, int paramInt1, int paramInt2)
    throws SQLException, IOException
  {
/* 882 */     if (paramInt1 == 0) {
/* 883 */       this.isFirstCol = true;
    }
/* 885 */     for (int i = paramInt1; (i < paramInt2) && (i < paramArrayOfAccessor.length); i++)
    {
/* 887 */       if (paramArrayOfAccessor[i] != null)
      {
        int j;
        
        int k;
        
/* 904 */         if (paramArrayOfAccessor[i].physicalColumnIndex < 0)
        {
/* 908 */           j = 0;
          
/* 910 */           for (k = 0; (k < paramInt2) && (k < paramArrayOfAccessor.length); k++)
          {
/* 912 */             if (paramArrayOfAccessor[k] != null)
            {
/* 914 */               paramArrayOfAccessor[k].physicalColumnIndex = j;
              
/* 916 */               if (!paramArrayOfAccessor[k].isUseLess) {
/* 917 */                 j++;
              }
            }
          }
        }
/* 922 */         if ((this.bvcFound) && (!paramArrayOfAccessor[i].isUseLess) && (!this.bvcColSent.get(paramArrayOfAccessor[i].physicalColumnIndex)))
        {
/* 926 */           paramArrayOfAccessor[i].copyRow();
        }
        else
        {
/* 930 */           j = 0;
          
/* 934 */           if ((paramArrayOfAccessor[i].statement.statementType != 2) && (!paramArrayOfAccessor[i].statement.sqlKind.isPlsqlOrCall()))
          {
/* 937 */             k = paramArrayOfAccessor[i].metaDataIndex + paramArrayOfAccessor[i].lastRowProcessed * 1;
            
/* 939 */             if (paramArrayOfAccessor[i].securityAttribute == OracleResultSetMetaData.SecurityAttribute.ENABLED) {
/* 940 */               j = (byte)this.meg.unmarshalUB1();
            }
/* 942 */             paramArrayOfAccessor[i].rowSpaceMetaData[k] = j;
          }
          
/* 945 */           if (paramArrayOfAccessor[i].unmarshalOneRow()) {
/* 946 */             return true;
          }
/* 948 */           this.isFirstCol = false;
        }
      }
    }
    
/* 953 */     this.bvcFound = false;
    
/* 955 */     return false;
  }
  
  boolean unmarshal(Accessor[] paramArrayOfAccessor, int paramInt1, int paramInt2, int paramInt3)
    throws SQLException, IOException
  {
/* 964 */     return false;
  }
  
  protected OracleConnection getConnectionDuringExceptionHandling()
  {
/* 979 */     return null;
  }
  
/* 984 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/T4CTTIrxd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */