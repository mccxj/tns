/*      */ package oracle.sql.converter;
/*      */ 
/*      */ import java.sql.SQLException;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Hashtable;
/*      */ import java.util.Vector;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CharacterConverter1Byte
/*      */   extends CharacterConverterJDBC
/*      */ {
/*      */   static final long serialVersionUID = 200017349723606452L;
/*      */   static final int ORACHARMASK = 255;
/*      */   static final int UCSCHARWIDTH = 16;
/*  118 */   public int m_ucsReplacement = 0;
/*  119 */   public int[] m_ucsChar = null;
/*  120 */   public char[] m_oraCharLevel1 = null;
/*  121 */   public char[] m_oraCharSurrogateLevel = null;
/*  122 */   public char[] m_oraCharLevel2 = null;
/*  123 */   public byte m_oraCharReplacement = 0;
/*  124 */   protected transient boolean noSurrogate = true;
/*  125 */   protected transient boolean strictASCII = true;
/*  126 */   protected transient int m_oraCharLevel2Size = 0;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CharacterConverter1Byte()
/*      */   {
/*  133 */     this.m_groupId = 0;
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
/*      */   int toUnicode(byte paramByte)
/*      */     throws SQLException
/*      */   {
/*  152 */     int i = this.m_ucsChar[(paramByte & 0xFF)];
/*      */     
/*  154 */     if (i == -1)
/*      */     {
/*      */ 
/*  157 */       SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 154);
/*  158 */       localSQLException.fillInStackTrace();
/*  159 */       throw localSQLException;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  164 */     return i;
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
/*      */   int toUnicodeWithReplacement(byte paramByte)
/*      */   {
/*  177 */     int i = this.m_ucsChar[(paramByte & 0xFF)];
/*      */     
/*  179 */     if (i == -1)
/*      */     {
/*  181 */       return this.m_ucsReplacement;
/*      */     }
/*      */     
/*      */ 
/*  185 */     return i;
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
/*      */   byte toOracleCharacter(char paramChar1, char paramChar2)
/*      */     throws SQLException
/*      */   {
/*  203 */     int i = paramChar1 >>> '\b' & 0xFF;
/*  204 */     int j = paramChar1 & 0xFF;
/*  205 */     int k = paramChar2 >>> '\b' & 0xFF;
/*  206 */     int m = paramChar2 & 0xFF;
/*      */     
/*  208 */     if ((this.m_oraCharLevel1[i] != (char)this.m_oraCharLevel2Size) && (this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] != 65535) && (this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] != 65535) && (this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)] != 65535))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  214 */       return (byte)this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)];
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  219 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 155);
/*  220 */     localSQLException.fillInStackTrace();
/*  221 */     throw localSQLException;
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
/*      */   byte toOracleCharacter(char paramChar)
/*      */     throws SQLException
/*      */   {
/*  241 */     int i = paramChar >>> '\b';
/*  242 */     int j = paramChar & 0xFF;
/*      */     
/*      */     int k;
/*  245 */     if ((k = this.m_oraCharLevel2[(this.m_oraCharLevel1[i] + j)]) != 65535)
/*      */     {
/*      */ 
/*  248 */       return (byte)k;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  253 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 155);
/*  254 */     localSQLException.fillInStackTrace();
/*  255 */     throw localSQLException;
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
/*      */   byte toOracleCharacterWithReplacement(char paramChar1, char paramChar2)
/*      */   {
/*  274 */     int i = paramChar1 >>> '\b' & 0xFF;
/*  275 */     int j = paramChar1 & 0xFF;
/*  276 */     int k = paramChar2 >>> '\b' & 0xFF;
/*  277 */     int m = paramChar2 & 0xFF;
/*      */     
/*  279 */     if ((this.m_oraCharLevel1[i] != (char)this.m_oraCharLevel2Size) && (this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] != 65535) && (this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] != 65535) && (this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)] != 65535))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  285 */       return (byte)this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)];
/*      */     }
/*      */     
/*      */ 
/*  289 */     return this.m_oraCharReplacement;
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
/*      */   byte toOracleCharacterWithReplacement(char paramChar)
/*      */   {
/*  305 */     int i = paramChar >>> '\b';
/*  306 */     int j = paramChar & 0xFF;
/*      */     
/*      */     int k;
/*  309 */     if ((k = this.m_oraCharLevel2[(this.m_oraCharLevel1[i] + j)]) != 65535)
/*      */     {
/*      */ 
/*  312 */       return (byte)k;
/*      */     }
/*      */     
/*      */ 
/*  316 */     return this.m_oraCharReplacement;
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
/*      */   public int toUnicodeChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/*  337 */     int i = paramInt1 + paramInt3;
/*      */     
/*  339 */     int j = 0;
/*      */     
/*      */ 
/*  342 */     int m = paramInt2;
/*      */     
/*  344 */     for (int k = paramInt1; (k < i) && (m < paramArrayOfChar.length); k++)
/*      */     {
/*  346 */       j = this.m_ucsChar[(paramArrayOfByte[k] & 0xFF)];
/*      */       
/*  348 */       if (j == this.m_ucsReplacement)
/*      */       {
/*      */ 
/*  351 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 154);
/*  352 */         localSQLException.fillInStackTrace();
/*  353 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  359 */       if ((j & 0xFFFFFFFF) > 65535L)
/*      */       {
/*  361 */         if (m + 2 < paramArrayOfChar.length)
/*      */         {
/*  363 */           k--;
/*  364 */           break;
/*      */         }
/*  366 */         paramArrayOfChar[(m++)] = ((char)(j >>> 16));
/*  367 */         paramArrayOfChar[(m++)] = ((char)(j & 0xFFFF));
/*      */       }
/*      */       else
/*      */       {
/*  371 */         paramArrayOfChar[(m++)] = ((char)j);
/*      */       }
/*      */     }
/*      */     
/*  375 */     return k;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String toUnicodeString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  383 */     int i = paramInt1 + paramInt2;
/*      */     
/*  385 */     StringBuilder localStringBuilder = new StringBuilder(paramInt2);
/*  386 */     int j = 0;
/*      */     
/*      */ 
/*      */ 
/*  390 */     for (int k = paramInt1; k < i; k++)
/*      */     {
/*  392 */       j = this.m_ucsChar[(paramArrayOfByte[k] & 0xFF)];
/*      */       
/*  394 */       if (j == this.m_ucsReplacement)
/*      */       {
/*      */ 
/*  397 */         SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 154);
/*  398 */         localSQLException.fillInStackTrace();
/*  399 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  405 */       if ((j & 0xFFFFFFFF) > 65535L)
/*      */       {
/*  407 */         localStringBuilder.append((char)(j >>> 16));
/*  408 */         localStringBuilder.append((char)(j & 0xFFFF));
/*      */       }
/*      */       else
/*      */       {
/*  412 */         localStringBuilder.append((char)j);
/*      */       }
/*      */     }
/*      */     
/*  416 */     return localStringBuilder.substring(0, localStringBuilder.length());
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
/*      */   public String toUnicodeStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  429 */     int i = paramInt1 + paramInt2;
/*  430 */     StringBuilder localStringBuilder = new StringBuilder(paramInt2);
/*  431 */     int j = 0;
/*      */     
/*      */ 
/*      */ 
/*  435 */     for (int k = paramInt1; k < i; k++)
/*      */     {
/*  437 */       j = this.m_ucsChar[(paramArrayOfByte[k] & 0xFF)];
/*      */       
/*  439 */       if (j == -1) {
/*  440 */         localStringBuilder.append((char)this.m_ucsReplacement);
/*      */       } else {
/*  442 */         localStringBuilder.append((char)j);
/*      */       }
/*      */     }
/*  445 */     return localStringBuilder.substring(0, localStringBuilder.length());
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
/*      */   public byte[] toOracleString(String paramString)
/*      */     throws SQLException
/*      */   {
/*  459 */     int i = paramString.length();
/*      */     
/*  461 */     if (i == 0)
/*      */     {
/*  463 */       return new byte[0];
/*      */     }
/*      */     
/*  466 */     char[] arrayOfChar = new char[i];
/*      */     
/*  468 */     paramString.getChars(0, i, arrayOfChar, 0);
/*      */     
/*  470 */     byte[] arrayOfByte1 = new byte[i * 4];
/*      */     
/*  472 */     int j = 0;
/*      */     
/*  474 */     for (int k = 0; k < i; k++)
/*      */     {
/*  476 */       if ((arrayOfChar[k] >= 55296) && (arrayOfChar[k] < 56320)) {
/*      */         SQLException localSQLException;
/*  478 */         if ((k + 1 < i) && (arrayOfChar[(k + 1)] >= 56320) && (arrayOfChar[(k + 1)] <= 57343))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  483 */           if (this.noSurrogate)
/*      */           {
/*      */ 
/*  486 */             localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 155);
/*  487 */             localSQLException.fillInStackTrace();
/*  488 */             throw localSQLException;
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  493 */           arrayOfByte1[(j++)] = toOracleCharacter(arrayOfChar[k], arrayOfChar[(k + 1)]);
/*      */           
/*      */ 
/*      */ 
/*  497 */           k++;
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  504 */           localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 155);
/*  505 */           localSQLException.fillInStackTrace();
/*  506 */           throw localSQLException;
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */       }
/*  512 */       else if ((arrayOfChar[k] < '') && (this.strictASCII))
/*      */       {
/*  514 */         arrayOfByte1[(j++)] = ((byte)arrayOfChar[k]);
/*      */       }
/*      */       else {
/*  517 */         arrayOfByte1[(j++)] = toOracleCharacter(arrayOfChar[k]);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  522 */     if (j < arrayOfByte1.length)
/*      */     {
/*      */ 
/*      */ 
/*  526 */       byte[] arrayOfByte2 = new byte[j];
/*      */       
/*  528 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, j);
/*      */       
/*  530 */       return arrayOfByte2;
/*      */     }
/*      */     
/*      */ 
/*  534 */     return arrayOfByte1;
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
/*      */   public byte[] toOracleStringWithReplacement(String paramString)
/*      */   {
/*  547 */     int i = paramString.length();
/*      */     
/*  549 */     if (i == 0)
/*      */     {
/*  551 */       return new byte[0];
/*      */     }
/*      */     
/*  554 */     char[] arrayOfChar = new char[i];
/*      */     
/*  556 */     paramString.getChars(0, i, arrayOfChar, 0);
/*      */     
/*  558 */     byte[] arrayOfByte1 = new byte[i * 4];
/*      */     
/*  560 */     int j = 0;
/*      */     
/*  562 */     for (int k = 0; k < i; k++)
/*      */     {
/*  564 */       if ((arrayOfChar[k] >= 55296) && (arrayOfChar[k] < 56320))
/*      */       {
/*  566 */         if ((k + 1 < i) && (arrayOfChar[(k + 1)] >= 56320) && (arrayOfChar[(k + 1)] <= 57343))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*  571 */           if (this.noSurrogate)
/*      */           {
/*  573 */             arrayOfByte1[(j++)] = this.m_oraCharReplacement;
/*      */           }
/*      */           else
/*      */           {
/*  577 */             arrayOfByte1[(j++)] = toOracleCharacterWithReplacement(arrayOfChar[k], arrayOfChar[(k + 1)]);
/*      */           }
/*      */           
/*      */ 
/*  581 */           k++;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/*  587 */           arrayOfByte1[(j++)] = this.m_oraCharReplacement;
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/*  594 */       else if ((arrayOfChar[k] < '') && (this.strictASCII))
/*      */       {
/*  596 */         arrayOfByte1[(j++)] = ((byte)arrayOfChar[k]);
/*      */       }
/*      */       else {
/*  599 */         arrayOfByte1[(j++)] = toOracleCharacterWithReplacement(arrayOfChar[k]);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  604 */     if (j < arrayOfByte1.length)
/*      */     {
/*      */ 
/*      */ 
/*  608 */       byte[] arrayOfByte2 = new byte[j];
/*      */       
/*  610 */       System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, j);
/*      */       
/*  612 */       return arrayOfByte2;
/*      */     }
/*      */     
/*      */ 
/*  616 */     return arrayOfByte1;
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
/*      */   public void buildUnicodeToOracleMapping()
/*      */   {
/*  651 */     this.m_oraCharLevel1 = new char['Ā'];
/*  652 */     this.m_oraCharSurrogateLevel = null;
/*  653 */     this.m_oraCharLevel2 = null;
/*      */     
/*  655 */     Vector localVector = new Vector(45055, 12287);
/*  656 */     Hashtable localHashtable1 = new Hashtable();
/*  657 */     Hashtable localHashtable2 = new Hashtable();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  666 */     int i1 = this.m_ucsChar.length;
/*  667 */     int i2 = 0;
/*  668 */     int i3 = 0;
/*      */     
/*      */ 
/*  671 */     for (int i4 = 0; i4 < 256; i4++)
/*      */     {
/*  673 */       this.m_oraCharLevel1[i4] = 65535;
/*      */     }
/*      */     int n;
/*  676 */     for (i4 = 0; i4 < i1; i4++)
/*      */     {
/*      */ 
/*      */ 
/*  680 */       n = this.m_ucsChar[i4];
/*      */       
/*  682 */       if (n != -1)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  687 */         localObject1 = new int[2];
/*      */         
/*  689 */         localObject1[0] = n;
/*  690 */         localObject1[1] = i4;
/*      */         
/*  692 */         localVector.addElement(localObject1);
/*  693 */         storeMappingRange(n, localHashtable1, localHashtable2);
/*      */       }
/*      */     }
/*  696 */     if (this.extraUnicodeToOracleMapping != null)
/*      */     {
/*  698 */       i1 = this.extraUnicodeToOracleMapping.length;
/*      */       
/*  700 */       for (i4 = 0; i4 < i1; i4++)
/*      */       {
/*      */ 
/*      */ 
/*  704 */         n = this.extraUnicodeToOracleMapping[i4][0];
/*      */         
/*  706 */         storeMappingRange(n, localHashtable1, localHashtable2);
/*      */       }
/*      */     }
/*      */     
/*  710 */     Object localObject1 = localHashtable1.keys();
/*      */     
/*      */ 
/*  713 */     int i5 = 0;
/*  714 */     int i6 = 0;
/*      */     Object localObject2;
/*  716 */     char[] arrayOfChar; while (((Enumeration)localObject1).hasMoreElements())
/*      */     {
/*  718 */       localObject2 = ((Enumeration)localObject1).nextElement();
/*  719 */       arrayOfChar = (char[])localHashtable1.get(localObject2);
/*      */       
/*  721 */       if (arrayOfChar != null)
/*      */       {
/*      */ 
/*      */ 
/*  725 */         i5 += 256;
/*      */       }
/*      */     }
/*      */     
/*  729 */     localObject1 = localHashtable2.keys();
/*      */     
/*  731 */     while (((Enumeration)localObject1).hasMoreElements())
/*      */     {
/*  733 */       localObject2 = ((Enumeration)localObject1).nextElement();
/*  734 */       arrayOfChar = (char[])localHashtable2.get(localObject2);
/*      */       
/*  736 */       if (arrayOfChar != null)
/*      */       {
/*      */ 
/*      */ 
/*  740 */         i6 += 256;
/*      */       }
/*      */     }
/*      */     
/*  744 */     if (i5 != 0)
/*      */     {
/*  746 */       this.m_oraCharSurrogateLevel = new char[i5];
/*      */     }
/*      */     
/*  749 */     if (i6 != 0)
/*      */     {
/*  751 */       this.m_oraCharLevel2 = new char[i6 + 256];
/*      */     }
/*      */     
/*  754 */     for (i4 = 0; i4 < i5; i4++)
/*      */     {
/*  756 */       this.m_oraCharSurrogateLevel[i4] = 65535;
/*      */     }
/*      */     
/*  759 */     for (i4 = 0; i4 < i6 + 256; i4++)
/*      */     {
/*  761 */       this.m_oraCharLevel2[i4] = 65535; }
/*      */     int i;
/*      */     int j;
/*  764 */     int k; int m; for (i4 = 0; i4 < localVector.size(); i4++)
/*      */     {
/*  766 */       int[] arrayOfInt = (int[])localVector.elementAt(i4);
/*      */       
/*  768 */       i = arrayOfInt[0] >> 24 & 0xFF;
/*  769 */       j = arrayOfInt[0] >> 16 & 0xFF;
/*  770 */       k = arrayOfInt[0] >> 8 & 0xFF;
/*  771 */       m = arrayOfInt[0] & 0xFF;
/*      */       
/*  773 */       if ((i >= 216) && (i < 220))
/*      */       {
/*  775 */         if (this.m_oraCharLevel1[i] == 65535)
/*      */         {
/*  777 */           this.m_oraCharLevel1[i] = i3;
/*  778 */           i3 = (char)(i3 + 256);
/*      */         }
/*      */         
/*  781 */         if (this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] == 65535)
/*      */         {
/*      */ 
/*  784 */           this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] = i3;
/*      */           
/*  786 */           i3 = (char)(i3 + 256);
/*      */         }
/*      */         
/*  789 */         if (this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] == 65535)
/*      */         {
/*      */ 
/*  792 */           this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] = i2;
/*      */           
/*  794 */           i2 = (char)(i2 + 256);
/*      */         }
/*      */         
/*  797 */         if (this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)] == 65535)
/*      */         {
/*      */ 
/*  800 */           this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)] = ((char)(arrayOfInt[1] & 0xFFFF));
/*      */ 
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*      */ 
/*      */ 
/*  811 */         if (this.m_oraCharLevel1[k] == 65535)
/*      */         {
/*  813 */           this.m_oraCharLevel1[k] = i2;
/*  814 */           i2 = (char)(i2 + 256);
/*      */         }
/*      */         
/*  817 */         if (this.m_oraCharLevel2[(this.m_oraCharLevel1[k] + m)] == 65535)
/*      */         {
/*      */ 
/*  820 */           this.m_oraCharLevel2[(this.m_oraCharLevel1[k] + m)] = ((char)(arrayOfInt[1] & 0xFFFF));
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
/*  831 */     if (this.extraUnicodeToOracleMapping != null)
/*      */     {
/*  833 */       i1 = this.extraUnicodeToOracleMapping.length;
/*      */       
/*  835 */       for (i4 = 0; i4 < i1; i4++)
/*      */       {
/*      */ 
/*      */ 
/*  839 */         n = this.extraUnicodeToOracleMapping[i4][0];
/*      */         
/*      */ 
/*      */ 
/*  843 */         i = n >>> 24 & 0xFF;
/*  844 */         j = n >>> 16 & 0xFF;
/*  845 */         k = n >>> 8 & 0xFF;
/*  846 */         m = n & 0xFF;
/*      */         
/*  848 */         if ((i >= 216) && (i < 220))
/*      */         {
/*  850 */           if (this.m_oraCharLevel1[i] == 65535)
/*      */           {
/*      */ 
/*      */ 
/*  854 */             this.m_oraCharLevel1[i] = i3;
/*  855 */             i3 = (char)(i3 + 256);
/*      */           }
/*      */           
/*  858 */           if (this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] == 65535)
/*      */           {
/*      */ 
/*  861 */             this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] = i3;
/*      */             
/*  863 */             i3 = (char)(i3 + 256);
/*      */           }
/*      */           
/*  866 */           if (this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] == 65535)
/*      */           {
/*      */ 
/*  869 */             this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] = i2;
/*      */             
/*  871 */             i2 = (char)(i2 + 256);
/*      */           }
/*      */           
/*  874 */           this.m_oraCharLevel2[(this.m_oraCharSurrogateLevel[(this.m_oraCharSurrogateLevel[(this.m_oraCharLevel1[i] + j)] + k)] + m)] = ((char)(this.extraUnicodeToOracleMapping[i4][1] & 0xFF));
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  879 */           if (this.m_oraCharLevel1[k] == 65535)
/*      */           {
/*  881 */             this.m_oraCharLevel1[k] = i2;
/*  882 */             i2 = (char)(i2 + 256);
/*      */           }
/*      */           
/*  885 */           this.m_oraCharLevel2[(this.m_oraCharLevel1[k] + m)] = ((char)(this.extraUnicodeToOracleMapping[i4][1] & 0xFFFF));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  891 */     if (this.m_oraCharSurrogateLevel == null) {
/*  892 */       this.noSurrogate = true;
/*      */     } else {
/*  894 */       this.noSurrogate = false;
/*      */     }
/*  896 */     this.strictASCII = true;
/*      */     
/*  898 */     for (i4 = 0; i4 < 128; i4++)
/*      */     {
/*  900 */       if (this.m_oraCharLevel2[i4] != i4)
/*      */       {
/*  902 */         this.strictASCII = false;
/*      */         
/*  904 */         break;
/*      */       }
/*      */     }
/*      */     
/*  908 */     for (i4 = 0; i4 < 256; i4++)
/*      */     {
/*  910 */       if (this.m_oraCharLevel1[i4] == 65535) {
/*  911 */         this.m_oraCharLevel1[i4] = ((char)i6);
/*      */       }
/*      */     }
/*  914 */     this.m_oraCharLevel2Size = i6;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void extractCodepoints(Vector paramVector)
/*      */   {
/*  924 */     int i = 0;
/*  925 */     int j = 255;
/*      */     
/*      */ 
/*  928 */     for (int k = i; k <= j; k++)
/*      */     {
/*      */       try
/*      */       {
/*  932 */         int[] arrayOfInt = new int[2];
/*  933 */         arrayOfInt[0] = k;
/*  934 */         arrayOfInt[1] = toUnicode((byte)k);
/*      */         
/*      */ 
/*      */ 
/*  938 */         paramVector.addElement(arrayOfInt);
/*      */       }
/*      */       catch (SQLException localSQLException) {}
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
/*      */   public void extractExtraMappings(Vector paramVector)
/*      */   {
/*  955 */     if (this.extraUnicodeToOracleMapping == null)
/*      */     {
/*  957 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  962 */     for (int i = 0; i < this.extraUnicodeToOracleMapping.length; i++)
/*      */     {
/*  964 */       int[] arrayOfInt = new int[2];
/*  965 */       arrayOfInt[0] = this.extraUnicodeToOracleMapping[i][0];
/*  966 */       arrayOfInt[1] = this.extraUnicodeToOracleMapping[i][1];
/*      */       
/*  968 */       paramVector.addElement(arrayOfInt);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean hasExtraMappings()
/*      */   {
/*  976 */     return this.extraUnicodeToOracleMapping != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public char getOraChar1ByteRep()
/*      */   {
/*  983 */     return (char)this.m_oraCharReplacement;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public char getOraChar2ByteRep()
/*      */   {
/*  990 */     return '\000';
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public int getUCS2CharRep()
/*      */   {
/*  997 */     return this.m_ucsReplacement;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 1012 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 1017 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/CharacterConverter1Byte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */