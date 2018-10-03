/*      */ package oracle.sql;
/*      */ 
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Modifier;
/*      */ import java.sql.SQLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import oracle.jdbc.driver.DatabaseError;
/*      */ import oracle.jdbc.internal.OracleConnection;
/*      */ import oracle.sql.converter.CharacterConverterFactoryOGS;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class CharacterSet
/*      */ {
/*      */   public static final short DEFAULT_CHARSET = -1;
/*      */   public static final short ASCII_CHARSET = 1;
/*      */   public static final short ISO_LATIN_1_CHARSET = 31;
/*      */   public static final short UNICODE_1_CHARSET = 870;
/*      */   public static final short US7ASCII_CHARSET = 1;
/*      */   public static final short WE8DEC_CHARSET = 2;
/*      */   public static final short WE8HP_CHARSET = 3;
/*      */   public static final short US8PC437_CHARSET = 4;
/*      */   public static final short WE8EBCDIC37_CHARSET = 5;
/*      */   public static final short WE8EBCDIC500_CHARSET = 6;
/*      */   public static final short WE8EBCDIC285_CHARSET = 8;
/*      */   public static final short WE8PC850_CHARSET = 10;
/*      */   public static final short D7DEC_CHARSET = 11;
/*      */   public static final short F7DEC_CHARSET = 12;
/*      */   public static final short S7DEC_CHARSET = 13;
/*      */   public static final short E7DEC_CHARSET = 14;
/*      */   public static final short SF7ASCII_CHARSET = 15;
/*      */   public static final short NDK7DEC_CHARSET = 16;
/*      */   public static final short I7DEC_CHARSET = 17;
/*      */   public static final short NL7DEC_CHARSET = 18;
/*      */   public static final short CH7DEC_CHARSET = 19;
/*      */   public static final short YUG7ASCII_CHARSET = 20;
/*      */   public static final short SF7DEC_CHARSET = 21;
/*      */   public static final short TR7DEC_CHARSET = 22;
/*      */   public static final short IW7IS960_CHARSET = 23;
/*      */   public static final short IN8ISCII_CHARSET = 25;
/*      */   public static final short WE8ISO8859P1_CHARSET = 31;
/*      */   public static final short EE8ISO8859P2_CHARSET = 32;
/*      */   public static final short SE8ISO8859P3_CHARSET = 33;
/*      */   public static final short NEE8ISO8859P4_CHARSET = 34;
/*      */   public static final short CL8ISO8859P5_CHARSET = 35;
/*      */   public static final short AR8ISO8859P6_CHARSET = 36;
/*      */   public static final short EL8ISO8859P7_CHARSET = 37;
/*      */   public static final short IW8ISO8859P8_CHARSET = 38;
/*      */   public static final short WE8ISO8859P9_CHARSET = 39;
/*      */   public static final short NE8ISO8859P10_CHARSET = 40;
/*      */   public static final short TH8TISASCII_CHARSET = 41;
/*      */   public static final short TH8TISEBCDIC_CHARSET = 42;
/*      */   public static final short BN8BSCII_CHARSET = 43;
/*      */   public static final short VN8VN3_CHARSET = 44;
/*      */   public static final short VN8MSWIN1258_CHARSET = 45;
/*      */   public static final short WE8NEXTSTEP_CHARSET = 50;
/*      */   public static final short AR8ASMO708PLUS_CHARSET = 61;
/*      */   public static final short AR8EBCDICX_CHARSET = 70;
/*      */   public static final short AR8XBASIC_CHARSET = 72;
/*      */   public static final short EL8DEC_CHARSET = 81;
/*      */   public static final short TR8DEC_CHARSET = 82;
/*      */   public static final short WE8EBCDIC37C_CHARSET = 90;
/*      */   public static final short WE8EBCDIC500C_CHARSET = 91;
/*      */   public static final short IW8EBCDIC424_CHARSET = 92;
/*      */   public static final short TR8EBCDIC1026_CHARSET = 93;
/*      */   public static final short WE8EBCDIC871_CHARSET = 94;
/*      */   public static final short WE8EBCDIC284_CHARSET = 95;
/*      */   public static final short WE8EBCDIC1047_CHARSET = 96;
/*      */   public static final short EEC8EUROASCI_CHARSET = 110;
/*      */   public static final short EEC8EUROPA3_CHARSET = 113;
/*      */   public static final short LA8PASSPORT_CHARSET = 114;
/*      */   public static final short BG8PC437S_CHARSET = 140;
/*      */   public static final short EE8PC852_CHARSET = 150;
/*      */   public static final short RU8PC866_CHARSET = 152;
/*      */   public static final short RU8BESTA_CHARSET = 153;
/*      */   public static final short IW8PC1507_CHARSET = 154;
/*      */   public static final short RU8PC855_CHARSET = 155;
/*      */   public static final short TR8PC857_CHARSET = 156;
/*      */   public static final short CL8MACCYRILLIC_CHARSET = 158;
/*      */   public static final short CL8MACCYRILLICS_CHARSET = 159;
/*      */   public static final short WE8PC860_CHARSET = 160;
/*      */   public static final short IS8PC861_CHARSET = 161;
/*      */   public static final short EE8MACCES_CHARSET = 162;
/*      */   public static final short EE8MACCROATIANS_CHARSET = 163;
/*      */   public static final short TR8MACTURKISHS_CHARSET = 164;
/*      */   public static final short IS8MACICELANDICS_CHARSET = 165;
/*      */   public static final short EL8MACGREEKS_CHARSET = 166;
/*      */   public static final short IW8MACHEBREWS_CHARSET = 167;
/*      */   public static final short EE8MSWIN1250_CHARSET = 170;
/*      */   public static final short CL8MSWIN1251_CHARSET = 171;
/*      */   public static final short ET8MSWIN923_CHARSET = 172;
/*      */   public static final short BG8MSWIN_CHARSET = 173;
/*      */   public static final short EL8MSWIN1253_CHARSET = 174;
/*      */   public static final short IW8MSWIN1255_CHARSET = 175;
/*      */   public static final short LT8MSWIN921_CHARSET = 176;
/*      */   public static final short TR8MSWIN1254_CHARSET = 177;
/*      */   public static final short WE8MSWIN1252_CHARSET = 178;
/*      */   public static final short BLT8MSWIN1257_CHARSET = 179;
/*      */   public static final short D8EBCDIC273_CHARSET = 180;
/*      */   public static final short I8EBCDIC280_CHARSET = 181;
/*      */   public static final short DK8EBCDIC277_CHARSET = 182;
/*      */   public static final short S8EBCDIC278_CHARSET = 183;
/*      */   public static final short EE8EBCDIC870_CHARSET = 184;
/*      */   public static final short CL8EBCDIC1025_CHARSET = 185;
/*      */   public static final short F8EBCDIC297_CHARSET = 186;
/*      */   public static final short IW8EBCDIC1086_CHARSET = 187;
/*      */   public static final short CL8EBCDIC1025X_CHARSET = 188;
/*      */   public static final short N8PC865_CHARSET = 190;
/*      */   public static final short BLT8CP921_CHARSET = 191;
/*      */   public static final short LV8PC1117_CHARSET = 192;
/*      */   public static final short LV8PC8LR_CHARSET = 193;
/*      */   public static final short BLT8EBCDIC1112_CHARSET = 194;
/*      */   public static final short LV8RST104090_CHARSET = 195;
/*      */   public static final short CL8KOI8R_CHARSET = 196;
/*      */   public static final short BLT8PC775_CHARSET = 197;
/*      */   public static final short F7SIEMENS9780X_CHARSET = 201;
/*      */   public static final short E7SIEMENS9780X_CHARSET = 202;
/*      */   public static final short S7SIEMENS9780X_CHARSET = 203;
/*      */   public static final short DK7SIEMENS9780X_CHARSET = 204;
/*      */   public static final short N7SIEMENS9780X_CHARSET = 205;
/*      */   public static final short I7SIEMENS9780X_CHARSET = 206;
/*      */   public static final short D7SIEMENS9780X_CHARSET = 207;
/*      */   public static final short WE8GCOS7_CHARSET = 210;
/*      */   public static final short EL8GCOS7_CHARSET = 211;
/*      */   public static final short US8BS2000_CHARSET = 221;
/*      */   public static final short D8BS2000_CHARSET = 222;
/*      */   public static final short F8BS2000_CHARSET = 223;
/*      */   public static final short E8BS2000_CHARSET = 224;
/*      */   public static final short DK8BS2000_CHARSET = 225;
/*      */   public static final short S8BS2000_CHARSET = 226;
/*      */   public static final short WE8BS2000_CHARSET = 231;
/*      */   public static final short CL8BS2000_CHARSET = 235;
/*      */   public static final short WE8BS2000L5_CHARSET = 239;
/*      */   public static final short WE8DG_CHARSET = 241;
/*      */   public static final short WE8NCR4970_CHARSET = 251;
/*      */   public static final short WE8ROMAN8_CHARSET = 261;
/*      */   public static final short EE8MACCE_CHARSET = 262;
/*      */   public static final short EE8MACCROATIAN_CHARSET = 263;
/*      */   public static final short TR8MACTURKISH_CHARSET = 264;
/*      */   public static final short IS8MACICELANDIC_CHARSET = 265;
/*      */   public static final short EL8MACGREEK_CHARSET = 266;
/*      */   public static final short IW8MACHEBREW_CHARSET = 267;
/*      */   public static final short US8ICL_CHARSET = 277;
/*      */   public static final short WE8ICL_CHARSET = 278;
/*      */   public static final short WE8ISOICLUK_CHARSET = 279;
/*      */   public static final short WE8MACROMAN8_CHARSET = 351;
/*      */   public static final short WE8MACROMAN8S_CHARSET = 352;
/*      */   public static final short TH8MACTHAI_CHARSET = 353;
/*      */   public static final short TH8MACTHAIS_CHARSET = 354;
/*      */   public static final short HU8CWI2_CHARSET = 368;
/*      */   public static final short EL8PC437S_CHARSET = 380;
/*      */   public static final short EL8EBCDIC875_CHARSET = 381;
/*      */   public static final short EL8PC737_CHARSET = 382;
/*      */   public static final short LT8PC772_CHARSET = 383;
/*      */   public static final short LT8PC774_CHARSET = 384;
/*      */   public static final short EL8PC869_CHARSET = 385;
/*      */   public static final short EL8PC851_CHARSET = 386;
/*      */   public static final short CDN8PC863_CHARSET = 390;
/*      */   public static final short HU8ABMOD_CHARSET = 401;
/*      */   public static final short AR8ASMO8X_CHARSET = 500;
/*      */   public static final short AR8NAFITHA711T_CHARSET = 504;
/*      */   public static final short AR8SAKHR707T_CHARSET = 505;
/*      */   public static final short AR8MUSSAD768T_CHARSET = 506;
/*      */   public static final short AR8ADOS710T_CHARSET = 507;
/*      */   public static final short AR8ADOS720T_CHARSET = 508;
/*      */   public static final short AR8APTEC715T_CHARSET = 509;
/*      */   public static final short AR8NAFITHA721T_CHARSET = 511;
/*      */   public static final short AR8HPARABIC8T_CHARSET = 514;
/*      */   public static final short AR8NAFITHA711_CHARSET = 554;
/*      */   public static final short AR8SAKHR707_CHARSET = 555;
/*      */   public static final short AR8MUSSAD768_CHARSET = 556;
/*      */   public static final short AR8ADOS710_CHARSET = 557;
/*      */   public static final short AR8ADOS720_CHARSET = 558;
/*      */   public static final short AR8APTEC715_CHARSET = 559;
/*      */   public static final short AR8MSAWIN_CHARSET = 560;
/*      */   public static final short AR8NAFITHA721_CHARSET = 561;
/*      */   public static final short AR8SAKHR706_CHARSET = 563;
/*      */   public static final short AR8ARABICMAC_CHARSET = 565;
/*      */   public static final short AR8ARABICMACS_CHARSET = 566;
/*      */   public static final short AR8ARABICMACT_CHARSET = 567;
/*      */   public static final short LA8ISO6937_CHARSET = 590;
/*      */   public static final short US8NOOP_CHARSET = 797;
/*      */   public static final short WE8DECTST_CHARSET = 798;
/*      */   public static final short JA16VMS_CHARSET = 829;
/*      */   public static final short JA16EUC_CHARSET = 830;
/*      */   public static final short JA16EUCYEN_CHARSET = 831;
/*      */   public static final short JA16SJIS_CHARSET = 832;
/*      */   public static final short JA16DBCS_CHARSET = 833;
/*      */   public static final short JA16SJISYEN_CHARSET = 834;
/*      */   public static final short JA16EBCDIC930_CHARSET = 835;
/*      */   public static final short JA16MACSJIS_CHARSET = 836;
/*      */   public static final short JA16EUCTILDE_CHARSET = 837;
/*      */   public static final short JA16SJISTILDE_CHARSET = 838;
/*      */   public static final short KO16KSC5601_CHARSET = 840;
/*      */   public static final short KO16DBCS_CHARSET = 842;
/*      */   public static final short KO16KSCCS_CHARSET = 845;
/*      */   public static final short KO16MSWIN949_CHARSET = 846;
/*      */   public static final short ZHS16CGB231280_CHARSET = 850;
/*      */   public static final short ZHS16MACCGB231280_CHARSET = 851;
/*      */   public static final short ZHS16GBK_CHARSET = 852;
/*      */   public static final short ZHS16DBCS_CHARSET = 853;
/*      */   public static final short ZHS32GB18030_CHARSET = 854;
/*      */   public static final short ZHT32EUC_CHARSET = 860;
/*      */   public static final short ZHT32SOPS_CHARSET = 861;
/*      */   public static final short ZHT16DBT_CHARSET = 862;
/*      */   public static final short ZHT32TRIS_CHARSET = 863;
/*      */   public static final short ZHT16DBCS_CHARSET = 864;
/*      */   public static final short ZHT16BIG5_CHARSET = 865;
/*      */   public static final short ZHT16CCDC_CHARSET = 866;
/*      */   public static final short ZHT16MSWIN950_CHARSET = 867;
/*      */   public static final short AL24UTFFSS_CHARSET = 870;
/*      */   public static final short UTF8_CHARSET = 871;
/*      */   public static final short UTFE_CHARSET = 872;
/*      */   public static final short AL32UTF8_CHARSET = 873;
/*      */   public static final short KO16TSTSET_CHARSET = 996;
/*      */   public static final short JA16TSTSET2_CHARSET = 997;
/*      */   public static final short JA16TSTSET_CHARSET = 998;
/*      */   public static final short US16TSTFIXED_CHARSET = 1001;
/*      */   public static final short AL16UTF16_CHARSET = 2000;
/*      */   public static final short AL16UTF16LE_CHARSET = 2002;
/*      */   public static final short TH8TISEBCDICS_CHARSET = 319;
/*      */   public static final short BLT8EBCDIC1112S_CHARSET = 314;
/*      */   public static final short CE8BS2000_CHARSET = 233;
/*      */   public static final short CL8EBCDIC1025R_CHARSET = 323;
/*      */   public static final short CL8EBCDIC1158R_CHARSET = 326;
/*      */   public static final short D8EBCDIC1141_CHARSET = 189;
/*      */   public static final short DK8EBCDIC1142_CHARSET = 198;
/*      */   public static final short EE8BS2000_CHARSET = 232;
/*      */   public static final short EE8EBCDIC870S_CHARSET = 316;
/*      */   public static final short EL8EBCDIC423R_CHARSET = 327;
/*      */   public static final short EL8EBCDIC875S_CHARSET = 311;
/*      */   public static final short EL8EBCDIC875R_CHARSET = 324;
/*      */   public static final short F8EBCDIC1147_CHARSET = 208;
/*      */   public static final short I8EBCDIC1144_CHARSET = 200;
/*      */   public static final short WE8BS2000E_CHARSET = 230;
/*      */   public static final short WE8EBCDIC1047E_CHARSET = 100;
/*      */   public static final short WE8EBCDIC1140_CHARSET = 7;
/*      */   public static final short WE8EBCDIC1145_CHARSET = 98;
/*      */   public static final short WE8EBCDIC1146_CHARSET = 9;
/*      */   public static final short WE8EBCDIC1148_CHARSET = 27;
/*      */   public static final short AR8EBCDIC420S_CHARSET = 320;
/*      */   public static final short IW8EBCDIC424S_CHARSET = 315;
/*      */   public static final short TR8EBCDIC1026S_CHARSET = 312;
/*      */   public static final short ZHT16HKSCS_CHARSET = 868;
/*      */   public static final short BLT8ISO8859P13_CHARSET = 47;
/*      */   public static final short WE8ISO8859P15_CHARSET = 46;
/*      */   public static final short AR8MSWIN1256_CHARSET = 560;
/*      */   public static final short S8EBCDIC1143_CHARSET = 199;
/*      */   public static final short ZHT16HKSCS31_CHARSET = 992;
/*      */   public static final short AZ8ISO8859P9E_CHARSET = 52;
/*      */   public static final short CEL8ISO8859P14_CHARSET = 48;
/*      */   public static final short CL8ISOIR111_CHARSET = 49;
/*      */   public static final short CL8KOI8U_CHARSET = 51;
/*      */   public static final short WE8PC858_CHARSET = 28;
/*      */   public static final short CL8EBCDIC1025C_CHARSET = 322;
/*      */   public static final short CL8EBCDIC1025S_CHARSET = 317;
/*      */   public static final short CL8EBCDIC1158_CHARSET = 325;
/*      */   public static final short EE8EBCDIC870C_CHARSET = 301;
/*      */   public static final short WE8EBCDIC924_CHARSET = 101;
/*      */   public static final short WE8EBCDIC1140C_CHARSET = 97;
/*      */   public static final short WE8EBCDIC1148C_CHARSET = 99;
/*      */   public static final short UNICODE_2_CHARSET = 871;
/*  420 */   private static CharacterSet asciiCharSet = null;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static
/*      */   {
/*      */     try
/*      */     {
/*  431 */       Class.forName("oracle.i18n.text.converter.CharacterConverterSJIS");
/*      */       
/*  433 */       CharacterSetWithConverter.ccFactory = new CharacterConverterFactoryOGS();
/*      */     }
/*      */     catch (ClassNotFoundException localClassNotFoundException) {}
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
/*  471 */   static CharacterSetFactory factory = new CharacterSetFactoryDefault();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int oracleId;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   int rep;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   CharacterSet(int paramInt)
/*      */   {
/*  495 */     this.oracleId = paramInt;
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
/*      */   public static CharacterSet make(int paramInt)
/*      */   {
/*  515 */     return factory.make(paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  526 */     if (ReadWriteCharacterSetNamesMap.cache == null)
/*  527 */       buildCharacterSetNames();
/*  528 */     return (String)ReadWriteCharacterSetNamesMap.cache.get(new Short((short)this.oracleId));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   synchronized void buildCharacterSetNames()
/*      */   {
/*  538 */     if (ReadWriteCharacterSetNamesMap.cache == null)
/*      */     {
/*  540 */       Class localClass = CharacterSet.class;
/*  541 */       Field[] arrayOfField = localClass.getFields();
/*  542 */       HashMap localHashMap = new HashMap();
/*  543 */       for (int i = 0; i < arrayOfField.length; i++)
/*      */       {
/*      */         try
/*      */         {
/*  547 */           String str1 = arrayOfField[i].getName();
/*  548 */           int j = str1.lastIndexOf("_CHARSET");
/*  549 */           if (j != -1)
/*      */           {
/*  551 */             str1 = str1.substring(0, j);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  556 */             if ((!str1.equals("ASCII")) && (!str1.equals("ISO_LATIN_1")) && (!str1.equals("AR8MSAWIN")) && (!str1.equals("UNICODE_1")) && (!str1.equals("UNICODE_2")))
/*      */             {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  563 */               short s = arrayOfField[i].getShort(CharacterSet.class);
/*  564 */               int k = arrayOfField[i].getModifiers();
/*  565 */               if ((Modifier.isStatic(k)) && (Modifier.isFinal(k)))
/*      */               {
/*  567 */                 String str2 = (String)localHashMap.get(new Short(s));
/*  568 */                 if (str2 != null)
/*  569 */                   throw new RuntimeException("duplicate field name: " + str1 + " for id: " + s);
/*  570 */                 localHashMap.put(new Short(s), str1);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */         catch (Exception localException)
/*      */         {
/*  577 */           throw new RuntimeException("Failed for field: " + arrayOfField[i], localException);
/*      */         }
/*      */       }
/*      */       
/*  581 */       ReadWriteCharacterSetNamesMap.cache = localHashMap;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isUnicode()
/*      */   {
/*  624 */     return false;
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
/*      */   boolean isWellFormed(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*  644 */     return true;
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
/*      */   public int getOracleId()
/*      */   {
/*  657 */     return this.oracleId;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   int getRep()
/*      */   {
/*  665 */     return this.rep;
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
/*      */   public int getRatioTo(CharacterSet paramCharacterSet)
/*      */   {
/*  682 */     throw new Error("oracle.sql.CharacterSet.getRationTo Not Implemented");
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
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  697 */     return ((paramObject instanceof CharacterSet)) && (this.oracleId == ((CharacterSet)paramObject).oracleId);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  709 */     return this.oracleId;
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
/*      */   public String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  748 */     String str = toStringWithReplacement(paramArrayOfByte, paramInt1, paramInt2);
/*  749 */     byte[] arrayOfByte = convert(str);
/*      */     
/*  751 */     if (paramInt2 != arrayOfByte.length)
/*      */     {
/*  753 */       failCharacterConversion(this);
/*      */     }
/*      */     
/*  756 */     for (int i = 0; i < paramInt2; i++)
/*      */     {
/*      */ 
/*  759 */       if (arrayOfByte[i] != paramArrayOfByte[(paramInt1 + i)])
/*      */       {
/*  761 */         failCharacterConversion(this);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  768 */     return null;
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
/*      */   public byte[] convertUnshared(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  838 */     byte[] arrayOfByte = convert(paramCharacterSet, paramArrayOfByte, paramInt1, paramInt2);
/*      */     
/*  840 */     if (arrayOfByte == paramArrayOfByte)
/*      */     {
/*  842 */       arrayOfByte = new byte[paramArrayOfByte.length];
/*      */       
/*  844 */       System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, paramInt2);
/*      */     }
/*      */     
/*  847 */     return arrayOfByte;
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
/*      */   static final void failCharacterConversion(CharacterSet paramCharacterSet)
/*      */     throws SQLException
/*      */   {
/*  881 */     SQLException localSQLException = DatabaseError.createSqlException(null, 55, paramCharacterSet);
/*  882 */     localSQLException.fillInStackTrace();
/*  883 */     throw localSQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   static final byte[] useOrCopy(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/*      */     byte[] arrayOfByte;
/*      */     
/*      */ 
/*  894 */     if ((paramArrayOfByte.length == paramInt2) && (paramInt1 == 0))
/*      */     {
/*  896 */       arrayOfByte = paramArrayOfByte;
/*      */     }
/*      */     else
/*      */     {
/*  900 */       arrayOfByte = new byte[paramInt2];
/*      */       
/*  902 */       System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
/*      */     }
/*      */     
/*  905 */     return arrayOfByte;
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
/*      */   static final void need(CharacterBuffer paramCharacterBuffer, int paramInt)
/*      */   {
/*  920 */     int i = paramCharacterBuffer.bytes.length;
/*  921 */     int j = paramInt + paramCharacterBuffer.next;
/*      */     
/*  923 */     if (j <= i)
/*      */     {
/*      */ 
/*  926 */       return;
/*      */     }
/*      */     
/*  929 */     while (j > i)
/*      */     {
/*  931 */       i = 2 * i;
/*      */     }
/*      */     
/*  934 */     byte[] arrayOfByte = paramCharacterBuffer.bytes;
/*      */     
/*  936 */     paramCharacterBuffer.bytes = new byte[i];
/*      */     
/*  938 */     System.arraycopy(arrayOfByte, 0, paramCharacterBuffer.bytes, 0, paramCharacterBuffer.next);
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
/*      */   public static final String UTFToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/*  967 */     return new String(UTFToJavaChar(paramArrayOfByte, paramInt1, paramInt2, paramBoolean));
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
/*      */   public static final String UTFToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/*  985 */     return UTFToString(paramArrayOfByte, paramInt1, paramInt2, false);
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
/*      */   public static final char[] UTFToJavaChar(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException
/*      */   {
/* 1008 */     return UTFToJavaChar(paramArrayOfByte, paramInt1, paramInt2, false);
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
/*      */   public static final char[] UTFToJavaChar(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1034 */     char[] arrayOfChar1 = null;
/*      */     
/* 1036 */     arrayOfChar1 = new char[paramInt2];
/*      */     
/*      */ 
/*      */ 
/* 1040 */     int[] arrayOfInt = new int[1];
/*      */     
/* 1042 */     arrayOfInt[0] = paramInt2;
/*      */     
/* 1044 */     int i = convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar1, 0, arrayOfInt, paramBoolean);
/*      */     
/* 1046 */     char[] arrayOfChar2 = new char[i];
/*      */     
/* 1048 */     System.arraycopy(arrayOfChar1, 0, arrayOfChar2, 0, i);
/*      */     
/* 1050 */     arrayOfChar1 = null;
/*      */     
/* 1052 */     return arrayOfChar2;
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
/*      */   public static final char[] UTFToJavaCharWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1076 */     char[] arrayOfChar1 = null;
/*      */     
/*      */     try
/*      */     {
/* 1080 */       arrayOfChar1 = new char[paramInt2];
/*      */       
/*      */ 
/*      */ 
/* 1084 */       int[] arrayOfInt = new int[1];
/*      */       
/* 1086 */       arrayOfInt[0] = paramInt2;
/*      */       
/* 1088 */       int i = convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar1, 0, arrayOfInt, true);
/*      */       
/* 1090 */       char[] arrayOfChar2 = new char[i];
/*      */       
/* 1092 */       System.arraycopy(arrayOfChar1, 0, arrayOfChar2, 0, i);
/*      */       
/* 1094 */       arrayOfChar1 = null;
/*      */       
/* 1096 */       return arrayOfChar2;
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1100 */       throw new IllegalStateException(localSQLException.getMessage());
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int convertUTFBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1136 */     return convertUTFBytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramArrayOfInt, paramBoolean, paramArrayOfChar.length - paramInt2);
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
/*      */   public static final int convertUTFBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1159 */     CharacterConverterBehavior localCharacterConverterBehavior = paramBoolean ? CharacterConverterBehavior.REPLACEMENT : CharacterConverterBehavior.REPORT_ERROR;
/*      */     
/*      */ 
/* 1162 */     int i = paramArrayOfInt[0];
/*      */     
/* 1164 */     paramArrayOfInt[0] = 0;
/*      */     
/* 1166 */     int j = paramInt1;
/* 1167 */     int k = paramInt1 + i;
/* 1168 */     int m = paramInt2;
/* 1169 */     int n = paramInt2 + paramInt3;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1176 */     while (j < k)
/*      */     {
/* 1178 */       int i1 = paramArrayOfByte[(j++)];
/* 1179 */       i3 = i1 & 0xF0;
/* 1180 */       int i4; if (i1 >= 0)
/*      */       {
/* 1182 */         if (m < n)
/*      */         {
/* 1184 */           paramArrayOfChar[(m++)] = ((char)(i1 & 0xFFFFFFFF));
/*      */           
/* 1186 */           if ((k <= paramArrayOfByte.length) && (n <= paramArrayOfChar.length) && (j < k) && (m < n) && (j >= 0) && (m >= 0))
/*      */           {
/*      */ 
/*      */             for (;;)
/*      */             {
/*      */ 
/* 1192 */               if ((j >= k) || 
/* 1193 */                 (m >= n)) break label181;
/* 1194 */               i4 = paramArrayOfByte[j];
/* 1195 */               if (i4 < 0) break;
/* 1196 */               paramArrayOfChar[m] = ((char)(i4 & 0xFFFFFFFF));
/* 1197 */               m++;
/* 1198 */               j++;
/*      */             }
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*      */           label181:
/*      */           
/* 1206 */           paramArrayOfInt[0] = (k - j + 2);
/*      */           
/* 1208 */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1214 */         i4 = i1;
/* 1215 */         i4 = (byte)(i4 << 2);
/* 1216 */         char c1; if (i4 >= 0)
/*      */         {
/*      */ 
/* 1219 */           if (j >= k)
/*      */           {
/* 1221 */             paramArrayOfInt[0] = 1;
/*      */             
/* 1223 */             localCharacterConverterBehavior.onFailConversion();
/*      */             
/* 1225 */             break;
/*      */           }
/*      */           
/*      */ 
/* 1229 */           c1 = conv2ByteUTFtoUTF16(i1, paramArrayOfByte[(j++)]);
/*      */           
/* 1231 */           if (m < n) {
/* 1232 */             paramArrayOfChar[(m++)] = c1;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1237 */             paramArrayOfInt[0] = (k - j + 3);
/*      */             
/* 1239 */             break;
/*      */           }
/*      */           
/* 1242 */           localCharacterConverterBehavior.onFailConversion(c1);
/*      */ 
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1249 */           i4 = (byte)(i4 << 1);
/* 1250 */           if (i4 >= 0)
/*      */           {
/*      */ 
/* 1253 */             if (j + 1 >= k)
/*      */             {
/* 1255 */               paramArrayOfInt[0] = (k - j + 1);
/*      */               
/* 1257 */               localCharacterConverterBehavior.onFailConversion();
/*      */               
/* 1259 */               break;
/*      */             }
/*      */             
/* 1262 */             char c2 = conv3ByteUTFtoUTF16(i1, paramArrayOfByte[(j++)], paramArrayOfByte[(j++)]);
/*      */             
/*      */ 
/*      */ 
/*      */ 
/* 1267 */             if ((i3 != 244) && (paramArrayOfByte[(j - 2)] != -65) && (paramArrayOfByte[(j - 1)] != -67))
/*      */             {
/*      */ 
/* 1270 */               localCharacterConverterBehavior.onFailConversion(c2);
/*      */             }
/*      */             
/* 1273 */             if (isHiSurrogate(c2))
/*      */             {
/* 1275 */               if (m > n - 2)
/*      */               {
/*      */ 
/*      */ 
/* 1279 */                 paramArrayOfInt[0] = (k - j + 4);
/*      */                 
/* 1281 */                 break;
/*      */               }
/*      */               
/* 1284 */               if (j < k)
/*      */               {
/* 1286 */                 int i2 = paramArrayOfByte[j];
/*      */                 
/* 1288 */                 if ((byte)(i2 & 0xF0) != -32)
/*      */                 {
/* 1290 */                   paramArrayOfChar[(m++)] = 65533;
/*      */                   
/*      */ 
/* 1293 */                   localCharacterConverterBehavior.onFailConversion();
/*      */ 
/*      */                 }
/*      */                 else
/*      */                 {
/* 1298 */                   j++;
/*      */                   
/* 1300 */                   if (j + 1 >= k)
/*      */                   {
/*      */ 
/*      */ 
/* 1304 */                     paramArrayOfInt[0] = (k - j + 1);
/*      */                     
/* 1306 */                     localCharacterConverterBehavior.onFailConversion();
/*      */                     
/* 1308 */                     break;
/*      */                   }
/*      */                   
/*      */ 
/* 1312 */                   c1 = conv3ByteUTFtoUTF16(i2, paramArrayOfByte[(j++)], paramArrayOfByte[(j++)]);
/*      */                   
/*      */ 
/* 1315 */                   if (isLoSurrogate(c1)) {
/* 1316 */                     paramArrayOfChar[(m++)] = c2;
/*      */ 
/*      */                   }
/*      */                   else
/*      */                   {
/* 1321 */                     paramArrayOfChar[(m++)] = 65533;
/*      */                     
/*      */ 
/* 1324 */                     localCharacterConverterBehavior.onFailConversion();
/*      */                   }
/*      */                   
/* 1327 */                   paramArrayOfChar[(m++)] = c1;
/*      */                 }
/*      */                 
/*      */               }
/*      */               
/*      */ 
/*      */             }
/* 1334 */             else if (m < n) {
/* 1335 */               paramArrayOfChar[(m++)] = c2;
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1340 */               paramArrayOfInt[0] = (k - j + 4);
/*      */               
/* 1342 */               break;
/*      */ 
/*      */             }
/*      */             
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/* 1351 */             if (m < n) {
/* 1352 */               paramArrayOfChar[(m++)] = 65533;
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/*      */ 
/* 1358 */               paramArrayOfInt[0] = (k - j + 2);
/*      */               
/* 1360 */               break;
/*      */             }
/*      */             
/* 1363 */             localCharacterConverterBehavior.onFailConversion();
/*      */           }
/*      */         } } }
/* 1366 */     int i3 = m - paramInt2;
/*      */     
/* 1368 */     return i3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte[] stringToUTF(String paramString)
/*      */   {
/* 1379 */     char[] arrayOfChar = paramString.toCharArray();
/* 1380 */     int i = arrayOfChar.length * 3;
/* 1381 */     byte[] arrayOfByte1 = null;
/* 1382 */     byte[] arrayOfByte2 = null;
/*      */     
/* 1384 */     arrayOfByte1 = new byte[i];
/*      */     
/*      */ 
/* 1387 */     int j = convertJavaCharsToUTFBytes(arrayOfChar, 0, arrayOfByte1, 0, arrayOfChar.length);
/*      */     
/*      */ 
/* 1390 */     arrayOfByte2 = new byte[j];
/*      */     
/* 1392 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, j);
/*      */     
/* 1394 */     arrayOfByte1 = null;
/*      */     
/* 1396 */     return arrayOfByte2;
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
/*      */   public static final int convertJavaCharsToUTFBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */   {
/* 1419 */     int i = paramInt1;
/* 1420 */     int j = paramInt1 + paramInt3;
/*      */     
/* 1422 */     int k = paramInt2;
/*      */     
/*      */ 
/* 1425 */     for (int n = i; n < j; n++)
/*      */     {
/*      */ 
/* 1428 */       m = paramArrayOfChar[n];
/*      */       
/* 1430 */       if ((m >= 0) && (m <= 127))
/*      */       {
/* 1432 */         paramArrayOfByte[(k++)] = ((byte)m);
/*      */       }
/* 1434 */       else if (m > 2047)
/*      */       {
/* 1436 */         paramArrayOfByte[(k++)] = ((byte)(0xE0 | m >>> 12 & 0xF));
/* 1437 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | m >>> 6 & 0x3F));
/* 1438 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | m >>> 0 & 0x3F));
/*      */       }
/*      */       else
/*      */       {
/* 1442 */         paramArrayOfByte[(k++)] = ((byte)(0xC0 | m >>> 6 & 0x1F));
/* 1443 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | m >>> 0 & 0x3F));
/*      */       }
/*      */     }
/*      */     
/* 1447 */     int m = k - paramInt2;
/*      */     
/* 1449 */     return m;
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
/*      */   public static final int UTFStringLength(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1477 */     int i = 0;
/* 1478 */     int j = paramInt1;
/* 1479 */     int k = paramInt1 + paramInt2;
/*      */     
/*      */ 
/* 1482 */     while (j < k)
/*      */     {
/* 1484 */       int m = paramArrayOfByte[j];
/* 1485 */       if (m >= 0)
/*      */       {
/* 1487 */         j++;
/* 1488 */         i++;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1493 */         m = (byte)(m << 2);
/* 1494 */         if (m >= 0)
/*      */         {
/* 1496 */           if (j + 1 >= k)
/*      */           {
/* 1498 */             j = k;
/*      */           }
/*      */           else
/*      */           {
/* 1502 */             i++;
/* 1503 */             j += 2;
/*      */           }
/*      */           
/*      */         }
/*      */         else
/*      */         {
/* 1509 */           m = (byte)(m << 1);
/* 1510 */           if (m >= 0)
/*      */           {
/* 1512 */             if (j + 2 >= k)
/*      */             {
/* 1514 */               j = k;
/*      */             }
/*      */             else
/*      */             {
/* 1518 */               i++;
/* 1519 */               j += 3;
/*      */             }
/*      */             
/*      */           }
/*      */           else
/*      */           {
/* 1525 */             m = (byte)(m << 1);
/* 1526 */             if (m >= 0)
/*      */             {
/* 1528 */               if (j + 3 >= k)
/*      */               {
/* 1530 */                 j = k;
/*      */               }
/*      */               else
/*      */               {
/* 1534 */                 i += 2;
/* 1535 */                 j += 4;
/*      */               }
/*      */               
/*      */             }
/*      */             else
/*      */             {
/* 1541 */               j++;
/* 1542 */               i++;
/*      */             }
/*      */           } } } }
/* 1545 */     return i;
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
/*      */   public static final int stringUTFLength(String paramString)
/*      */   {
/* 1561 */     char[] arrayOfChar = paramString.toCharArray();
/* 1562 */     return charArrayUTF8Length(arrayOfChar);
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
/*      */   static final int charArrayUTF8Length(char[] paramArrayOfChar)
/*      */   {
/* 1577 */     int i = 0;
/* 1578 */     int j = paramArrayOfChar.length;
/*      */     
/*      */ 
/* 1581 */     for (int m = 0; m < j; m++)
/*      */     {
/*      */ 
/* 1584 */       int k = paramArrayOfChar[m];
/*      */       
/* 1586 */       if ((k >= 0) && (k <= 127))
/*      */       {
/* 1588 */         i++;
/*      */       }
/* 1590 */       else if (k > 2047)
/*      */       {
/* 1592 */         i += 3;
/*      */       }
/*      */       else
/*      */       {
/* 1596 */         i += 2;
/*      */       }
/*      */     }
/*      */     
/* 1600 */     return i;
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
/*      */   public static final String AL32UTF8ToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */   {
/* 1622 */     return AL32UTF8ToString(paramArrayOfByte, paramInt1, paramInt2, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final String AL32UTF8ToString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */   {
/* 1631 */     char[] arrayOfChar = null;
/*      */     
/*      */     try
/*      */     {
/* 1635 */       arrayOfChar = AL32UTF8ToJavaChar(paramArrayOfByte, paramInt1, paramInt2, paramBoolean);
/*      */     }
/*      */     catch (SQLException localSQLException) {}
/*      */     
/*      */ 
/* 1640 */     return new String(arrayOfChar);
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
/*      */   public static final char[] AL32UTF8ToJavaChar(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1657 */     char[] arrayOfChar1 = null;
/*      */     
/*      */     try
/*      */     {
/* 1661 */       arrayOfChar1 = new char[paramInt2];
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1666 */       int[] arrayOfInt = new int[1];
/*      */       
/* 1668 */       arrayOfInt[0] = paramInt2;
/*      */       
/* 1670 */       int i = convertAL32UTF8BytesToJavaChars(paramArrayOfByte, paramInt1, arrayOfChar1, 0, arrayOfInt, paramBoolean);
/*      */       
/* 1672 */       char[] arrayOfChar2 = new char[i];
/*      */       
/* 1674 */       System.arraycopy(arrayOfChar1, 0, arrayOfChar2, 0, i);
/*      */       
/* 1676 */       arrayOfChar1 = null;
/*      */       
/* 1678 */       return arrayOfChar2;
/*      */     }
/*      */     catch (SQLException localSQLException)
/*      */     {
/* 1682 */       failUTFConversion();
/*      */     }
/* 1684 */     return new char[0];
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
/*      */   public static final int convertAL32UTF8BytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 1718 */     return convertAL32UTF8BytesToJavaChars(paramArrayOfByte, paramInt1, paramArrayOfChar, paramInt2, paramArrayOfInt, paramBoolean, paramArrayOfChar.length - paramInt2);
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
/*      */   public static final int convertAL32UTF8BytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 1742 */     CharacterConverterBehavior localCharacterConverterBehavior = paramBoolean ? CharacterConverterBehavior.REPLACEMENT : CharacterConverterBehavior.REPORT_ERROR;
/*      */     
/*      */ 
/* 1745 */     int i = paramArrayOfInt[0];
/*      */     
/* 1747 */     paramArrayOfInt[0] = 0;
/*      */     
/* 1749 */     int j = paramInt1;
/* 1750 */     int k = paramInt1 + i;
/* 1751 */     int m = paramInt2;
/* 1752 */     int n = paramInt2 + paramInt3;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1759 */     while (j < k)
/*      */     {
/* 1761 */       byte b = paramArrayOfByte[(j++)];
/*      */       
/*      */       int i2;
/* 1764 */       if (b >= 0)
/*      */       {
/* 1766 */         if (m < n)
/*      */         {
/* 1768 */           paramArrayOfChar[(m++)] = ((char)(b & 0xFFFFFFFF));
/*      */           
/* 1770 */           if ((k <= paramArrayOfByte.length) && (n <= paramArrayOfChar.length) && (j < k) && (m < n) && (j >= 0) && (m >= 0))
/*      */           {
/*      */ 
/*      */             for (;;)
/*      */             {
/*      */ 
/* 1776 */               if ((j >= k) || 
/* 1777 */                 (m >= n)) break label173;
/* 1778 */               i2 = paramArrayOfByte[j];
/* 1779 */               if (i2 < 0) break;
/* 1780 */               paramArrayOfChar[m] = ((char)(i2 & 0xFFFFFFFF));
/* 1781 */               m++;
/* 1782 */               j++;
/*      */             }
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*      */           label173:
/*      */           
/* 1790 */           paramArrayOfInt[0] = (k - j + 2);
/*      */           
/* 1792 */           break;
/*      */         }
/*      */         
/*      */       }
/*      */       else
/*      */       {
/* 1798 */         i2 = b;
/* 1799 */         i2 = (byte)(i2 << 2);
/* 1800 */         char c; if (i2 >= 0)
/*      */         {
/* 1802 */           if (j >= k)
/*      */           {
/* 1804 */             paramArrayOfInt[0] = 1;
/*      */             
/* 1806 */             localCharacterConverterBehavior.onFailConversion();
/*      */             
/* 1808 */             break;
/*      */           }
/*      */           
/*      */ 
/* 1812 */           c = conv2ByteUTFtoUTF16(b, paramArrayOfByte[(j++)]);
/*      */           
/* 1814 */           if (m < n) {
/* 1815 */             paramArrayOfChar[(m++)] = c;
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/* 1820 */             paramArrayOfInt[0] = (k - j + 3);
/*      */             
/* 1822 */             break;
/*      */           }
/*      */           
/* 1825 */           localCharacterConverterBehavior.onFailConversion(c);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 1831 */           i2 = (byte)(i2 << 1);
/* 1832 */           if (i2 >= 0)
/*      */           {
/* 1834 */             if (j + 1 >= k)
/*      */             {
/* 1836 */               paramArrayOfInt[0] = (k - j + 1);
/*      */               
/* 1838 */               localCharacterConverterBehavior.onFailConversion();
/*      */               
/* 1840 */               break;
/*      */             }
/*      */             
/*      */ 
/* 1844 */             c = conv3ByteAL32UTF8toUTF16(b, paramArrayOfByte[(j++)], paramArrayOfByte[(j++)]);
/*      */             
/*      */ 
/* 1847 */             if (m < n) {
/* 1848 */               paramArrayOfChar[(m++)] = c;
/*      */ 
/*      */             }
/*      */             else
/*      */             {
/* 1853 */               paramArrayOfInt[0] = (k - j + 4);
/*      */               
/* 1855 */               break;
/*      */             }
/*      */             
/* 1858 */             localCharacterConverterBehavior.onFailConversion(c);
/*      */ 
/*      */ 
/*      */           }
/*      */           else
/*      */           {
/*      */ 
/*      */ 
/* 1866 */             i2 = (byte)(i2 << 1);
/* 1867 */             if (i2 >= 0)
/*      */             {
/* 1869 */               if (j + 2 >= k)
/*      */               {
/* 1871 */                 paramArrayOfInt[0] = (k - j + 1);
/*      */                 
/* 1873 */                 localCharacterConverterBehavior.onFailConversion();
/*      */                 
/* 1875 */                 break;
/*      */               }
/*      */               
/* 1878 */               if (m > n - 2)
/*      */               {
/*      */ 
/*      */ 
/* 1882 */                 paramArrayOfInt[0] = (k - j + 2);
/*      */                 
/* 1884 */                 break;
/*      */               }
/*      */               
/* 1887 */               i1 = conv4ByteAL32UTF8toUTF16(b, paramArrayOfByte[(j++)], paramArrayOfByte[(j++)], paramArrayOfByte[(j++)], paramArrayOfChar, m);
/*      */               
/*      */ 
/*      */ 
/* 1891 */               if (i1 == 1)
/*      */               {
/* 1893 */                 localCharacterConverterBehavior.onFailConversion();
/*      */                 
/* 1895 */                 m++;
/*      */               }
/*      */               else {
/* 1898 */                 m += 2;
/*      */               }
/*      */             }
/*      */             else
/*      */             {
/* 1903 */               if (m < n) {
/* 1904 */                 paramArrayOfChar[(m++)] = 65533;
/*      */ 
/*      */               }
/*      */               else
/*      */               {
/*      */ 
/* 1910 */                 paramArrayOfInt[0] = (k - j + 2);
/*      */                 
/* 1912 */                 break;
/*      */               }
/*      */               
/* 1915 */               localCharacterConverterBehavior.onFailConversion();
/*      */             }
/*      */           } } } }
/* 1918 */     int i1 = m - paramInt2;
/*      */     
/* 1920 */     return i1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final byte[] stringToAL32UTF8(String paramString)
/*      */   {
/* 1929 */     char[] arrayOfChar = paramString.toCharArray();
/* 1930 */     int i = arrayOfChar.length * 3;
/* 1931 */     byte[] arrayOfByte1 = null;
/* 1932 */     byte[] arrayOfByte2 = null;
/*      */     
/* 1934 */     arrayOfByte1 = new byte[i];
/*      */     
/* 1936 */     int j = convertJavaCharsToAL32UTF8Bytes(arrayOfChar, 0, arrayOfByte1, 0, arrayOfChar.length);
/*      */     
/*      */ 
/* 1939 */     arrayOfByte2 = new byte[j];
/*      */     
/* 1941 */     System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, j);
/*      */     
/* 1943 */     arrayOfByte1 = null;
/*      */     
/* 1945 */     return arrayOfByte2;
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
/*      */   public static final int convertJavaCharsToAL32UTF8Bytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */   {
/* 1966 */     int i = paramInt1;
/* 1967 */     int j = paramInt1 + paramInt3;
/* 1968 */     int k = paramInt2;
/*      */     
/*      */ 
/* 1971 */     for (int m = i; m < j; m++)
/*      */     {
/*      */ 
/* 1974 */       int n = paramArrayOfChar[m];
/* 1975 */       int i1 = 0;
/*      */       
/* 1977 */       if ((n >= 0) && (n <= 127))
/*      */       {
/* 1979 */         paramArrayOfByte[(k++)] = ((byte)n);
/*      */       }
/* 1981 */       else if (isHiSurrogate((char)n))
/*      */       {
/*      */ 
/*      */ 
/* 1985 */         if ((m + 1 < j) && (isLoSurrogate((char)(i1 = paramArrayOfChar[(m + 1)]))))
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/* 1990 */           i2 = (n >>> 6 & 0xF) + 1;
/* 1991 */           paramArrayOfByte[(k++)] = ((byte)(i2 >>> 2 | 0xF0));
/* 1992 */           paramArrayOfByte[(k++)] = ((byte)((i2 & 0x3) << 4 | n >>> 2 & 0xF | 0x80));
/*      */           
/* 1994 */           paramArrayOfByte[(k++)] = ((byte)((n & 0x3) << 4 | i1 >>> 6 & 0xF | 0x80));
/*      */           
/* 1996 */           paramArrayOfByte[(k++)] = ((byte)(i1 & 0x3F | 0x80));
/* 1997 */           m++;
/*      */         }
/*      */         else
/*      */         {
/* 2001 */           paramArrayOfByte[(k++)] = -17;
/* 2002 */           paramArrayOfByte[(k++)] = -65;
/* 2003 */           paramArrayOfByte[(k++)] = -67;
/*      */         }
/*      */       }
/* 2006 */       else if (n > 2047)
/*      */       {
/* 2008 */         paramArrayOfByte[(k++)] = ((byte)(0xE0 | n >>> 12 & 0xF));
/* 2009 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | n >>> 6 & 0x3F));
/* 2010 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | n >>> 0 & 0x3F));
/*      */       }
/*      */       else
/*      */       {
/* 2014 */         paramArrayOfByte[(k++)] = ((byte)(0xC0 | n >>> 6 & 0x1F));
/* 2015 */         paramArrayOfByte[(k++)] = ((byte)(0x80 | n >>> 0 & 0x3F));
/*      */       }
/*      */     }
/*      */     
/* 2019 */     int i2 = k - paramInt2;
/*      */     
/* 2021 */     return i2;
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
/*      */   public static final int string32UTF8Length(String paramString)
/*      */   {
/* 2041 */     return charArray32UTF8Length(paramString.toCharArray());
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
/*      */   static final int charArray32UTF8Length(char[] paramArrayOfChar)
/*      */   {
/* 2055 */     int i = 0;
/* 2056 */     int j = paramArrayOfChar.length;
/*      */     
/* 2058 */     for (int k = 0; k < j; k++)
/*      */     {
/*      */ 
/* 2061 */       int m = paramArrayOfChar[k];
/*      */       
/* 2063 */       if ((m >= 0) && (m <= 127))
/*      */       {
/* 2065 */         i++;
/*      */       }
/* 2067 */       else if (m > 2047)
/*      */       {
/*      */ 
/* 2070 */         if (isHiSurrogate((char)m))
/*      */         {
/*      */ 
/* 2073 */           if (k + 1 < j)
/*      */           {
/* 2075 */             i += 4;
/* 2076 */             k++;
/*      */           }
/*      */           
/*      */         }
/*      */         else {
/* 2081 */           i += 3;
/*      */         }
/*      */         
/*      */       }
/*      */       else {
/* 2086 */         i += 2;
/*      */       }
/*      */     }
/*      */     
/* 2090 */     return i;
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
/*      */   public static final String AL16UTF16BytesToString(byte[] paramArrayOfByte, int paramInt)
/*      */   {
/* 2111 */     char[] arrayOfChar = new char[paramInt >>> 1];
/*      */     
/* 2113 */     AL16UTF16BytesToJavaChars(paramArrayOfByte, paramInt, arrayOfChar);
/*      */     
/* 2115 */     return new String(arrayOfChar);
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
/*      */   public static final int AL16UTF16BytesToJavaChars(byte[] paramArrayOfByte, int paramInt, char[] paramArrayOfChar)
/*      */   {
/* 2136 */     int k = paramInt >>> 1;
/*      */     
/*      */ 
/* 2139 */     int i = 0; for (int j = 0; i < k; i++)
/*      */     {
/* 2141 */       int m = paramArrayOfByte[j] << 8;
/* 2142 */       paramArrayOfChar[i] = ((char)(m | paramArrayOfByte[(j + 1)] & 0xFF));j += 2;
/*      */     }
/*      */     
/*      */ 
/* 2145 */     return i;
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
/*      */   public static final int convertAL16UTF16BytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2168 */     CharacterConverterBehavior localCharacterConverterBehavior = paramBoolean ? CharacterConverterBehavior.REPLACEMENT : CharacterConverterBehavior.REPORT_ERROR;
/*      */     
/*      */ 
/* 2171 */     int i = paramInt2;
/* 2172 */     int j = paramInt1;
/* 2173 */     int k = paramInt1 + paramInt3;
/* 2178 */     for (; 
/*      */         
/*      */ 
/*      */ 
/* 2178 */         j + 1 < k; j += 2)
/*      */     {
/* 2180 */       int n = paramArrayOfByte[j] << 8;
/* 2181 */       int m = (char)(n | paramArrayOfByte[(j + 1)] & 0xFF);
/*      */       
/* 2183 */       paramArrayOfChar[(i++)] = m;
/*      */     }
/*      */     
/* 2186 */     j = i - paramInt2;
/*      */     
/* 2188 */     return j;
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
/*      */   public static final int convertAL16UTF16LEBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2211 */     CharacterConverterBehavior localCharacterConverterBehavior = paramBoolean ? CharacterConverterBehavior.REPLACEMENT : CharacterConverterBehavior.REPORT_ERROR;
/*      */     
/*      */ 
/* 2214 */     int i = paramInt2;
/* 2215 */     int j = paramInt1;
/* 2216 */     int k = paramInt1 + paramInt3;
/* 2221 */     for (; 
/*      */         
/*      */ 
/*      */ 
/* 2221 */         j + 1 < k; j += 2)
/*      */     {
/* 2223 */       int m = paramArrayOfByte[(j + 1)] << 8;
/* 2224 */       char c1 = (char)(m | paramArrayOfByte[j] & 0xFF);
/*      */       
/* 2226 */       if (isHiSurrogate(c1))
/*      */       {
/* 2228 */         j += 2;
/*      */         
/* 2230 */         if (j + 1 < k)
/*      */         {
/* 2232 */           char c2 = (char)((paramArrayOfByte[(j + 1)] << 8) + (paramArrayOfByte[j] & 0xFF));
/*      */           
/* 2234 */           if (isLoSurrogate(c2))
/*      */           {
/* 2236 */             paramArrayOfChar[(i++)] = c1;
/*      */           }
/*      */           else
/*      */           {
/* 2240 */             paramArrayOfChar[(i++)] = 65533;
/*      */           }
/*      */           
/*      */ 
/* 2244 */           paramArrayOfChar[(i++)] = c2;
/*      */ 
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
/* 2256 */         paramArrayOfChar[(i++)] = c1;
/*      */       }
/*      */     }
/*      */     
/* 2260 */     j = i - paramInt2;
/*      */     
/* 2262 */     return j;
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
/*      */   public static final byte[] stringToAL16UTF16Bytes(String paramString)
/*      */   {
/* 2277 */     char[] arrayOfChar = paramString.toCharArray();
/* 2278 */     int i = arrayOfChar.length;
/* 2279 */     byte[] arrayOfByte = new byte[i * 2];
/*      */     
/* 2281 */     javaCharsToAL16UTF16Bytes(arrayOfChar, i, arrayOfByte);
/*      */     
/* 2283 */     return arrayOfByte;
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
/*      */   public static final int javaCharsToAL16UTF16Bytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */   {
/* 2301 */     int i = Math.min(paramInt, paramArrayOfByte.length >>> 1);
/*      */     
/* 2303 */     return convertJavaCharsToAL16UTF16Bytes(paramArrayOfChar, 0, paramArrayOfByte, 0, i);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int convertJavaCharsToAL16UTF16Bytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */   {
/* 2312 */     int i = paramInt1;
/* 2313 */     int j = paramInt2;
/* 2314 */     int k = paramInt1 + paramInt3;
/* 2316 */     for (; 
/* 2316 */         i < k; j += 2)
/*      */     {
/* 2318 */       paramArrayOfByte[j] = ((byte)(paramArrayOfChar[i] >>> '\b' & 0xFF));
/* 2319 */       paramArrayOfByte[(j + 1)] = ((byte)(paramArrayOfChar[i] & 0xFF));i++;
/*      */     }
/*      */     
/*      */ 
/* 2322 */     return j - paramInt2;
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
/*      */   public static final byte[] stringToAL16UTF16LEBytes(String paramString)
/*      */   {
/* 2338 */     char[] arrayOfChar = paramString.toCharArray();
/* 2339 */     byte[] arrayOfByte = new byte[arrayOfChar.length * 2];
/*      */     
/* 2341 */     javaCharsToAL16UTF16LEBytes(arrayOfChar, arrayOfChar.length, arrayOfByte);
/*      */     
/* 2343 */     return arrayOfByte;
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
/*      */   public static final int javaCharsToAL16UTF16LEBytes(char[] paramArrayOfChar, int paramInt, byte[] paramArrayOfByte)
/*      */   {
/* 2361 */     return convertJavaCharsToAL16UTF16LEBytes(paramArrayOfChar, 0, paramArrayOfByte, 0, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int convertJavaCharsToAL16UTF16LEBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */   {
/* 2370 */     int i = paramInt1;
/* 2371 */     int j = paramInt2;
/* 2372 */     int k = paramInt1 + paramInt3;
/* 2374 */     for (; 
/* 2374 */         i < k; j += 2)
/*      */     {
/* 2376 */       paramArrayOfByte[j] = ((byte)(paramArrayOfChar[i] & 0xFF));
/* 2377 */       paramArrayOfByte[(j + 1)] = ((byte)(paramArrayOfChar[i] >>> '\b'));i++;
/*      */     }
/*      */     
/*      */ 
/* 2380 */     return j - paramInt2;
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
/*      */   public static final int convertASCIIBytesToJavaChars(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2413 */     int k = paramInt2 + paramInt3;
/*      */     
/* 2415 */     int i = paramInt2; for (int j = paramInt1; i < k; j++)
/*      */     {
/* 2417 */       paramArrayOfChar[i] = ((char)(0xFF & paramArrayOfByte[j]));i++;
/*      */     }
/*      */     
/* 2420 */     return paramInt3;
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
/*      */   public static final int convertJavaCharsToASCIIBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2445 */     convertJavaCharsToASCIIBytes(paramArrayOfChar, paramInt1, paramArrayOfByte, paramInt2, paramInt3, false);
/*      */     
/* 2447 */     return paramInt3;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int convertJavaCharsToASCIIBytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean paramBoolean)
/*      */     throws SQLException
/*      */   {
/* 2457 */     if (paramBoolean)
/*      */     {
/* 2459 */       if (asciiCharSet == null) {
/* 2460 */         asciiCharSet = make(1);
/*      */       }
/* 2462 */       byte[] arrayOfByte = asciiCharSet.convertWithReplacement(new String(paramArrayOfChar, paramInt1, paramInt3));
/* 2463 */       System.arraycopy(arrayOfByte, 0, paramArrayOfByte, paramInt2, arrayOfByte.length);
/*      */       
/* 2465 */       return arrayOfByte.length;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2471 */     for (int i = 0; i < paramInt3; i++)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2476 */       paramArrayOfByte[(paramInt2 + i)] = ((byte)paramArrayOfChar[(paramInt1 + i)]);
/*      */     }
/*      */     
/* 2479 */     return paramInt3;
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
/*      */   public static final int convertJavaCharsToISOLATIN1Bytes(char[] paramArrayOfChar, int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2493 */     for (int i = 0; i < paramInt3; i++)
/*      */     {
/* 2495 */       int j = paramArrayOfChar[(paramInt1 + i)];
/*      */       
/* 2497 */       if (j > 255)
/*      */       {
/*      */ 
/*      */ 
/* 2501 */         paramArrayOfByte[(paramInt2 + i)] = -65;
/*      */       } else {
/* 2503 */         paramArrayOfByte[(paramInt2 + i)] = ((byte)j);
/*      */       }
/*      */     }
/* 2506 */     return paramInt3;
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
/*      */   public static final byte[] stringToASCII(String paramString)
/*      */   {
/* 2522 */     byte[] arrayOfByte = new byte[paramString.length()];
/*      */     
/* 2524 */     arrayOfByte = paramString.getBytes();
/*      */     
/* 2526 */     return arrayOfByte;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final long convertUTF32toUTF16(long paramLong)
/*      */   {
/* 2537 */     if (paramLong > 65535L)
/*      */     {
/* 2539 */       long l = 0xD8 | paramLong - 65536L >> 18 & 0xFF;
/* 2540 */       l = paramLong - 65536L >> 10 & 0xFF | l << 8;
/* 2541 */       l = 0xDC | (paramLong & 0x3FF) >> 8 & 0xFF | l << 8;
/* 2542 */       l = paramLong & 0xFF | l << 8;
/*      */       
/* 2544 */       return l;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2549 */     return paramLong;
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
/*      */   static final boolean isHiSurrogate(char paramChar)
/*      */   {
/* 2573 */     return (char)(paramChar & 0xFC00) == 55296;
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
/*      */   static final boolean isLoSurrogate(char paramChar)
/*      */   {
/* 2591 */     return (char)(paramChar & 0xFC00) == 56320;
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
/*      */   static final boolean check80toBF(byte paramByte)
/*      */   {
/* 2608 */     return (paramByte & 0xFFFFFFC0) == Byte.MIN_VALUE;
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
/*      */   static final boolean check80to8F(byte paramByte)
/*      */   {
/* 2625 */     return (paramByte & 0xFFFFFFF0) == Byte.MIN_VALUE;
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
/*      */   static final boolean check80to9F(byte paramByte)
/*      */   {
/* 2640 */     return (paramByte & 0xFFFFFFE0) == Byte.MIN_VALUE;
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
/*      */   static final boolean checkA0toBF(byte paramByte)
/*      */   {
/* 2655 */     return (paramByte & 0xFFFFFFE0) == -96;
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
/*      */   static final boolean check90toBF(byte paramByte)
/*      */   {
/* 2670 */     return ((paramByte & 0xFFFFFFC0) == Byte.MIN_VALUE) && ((paramByte & 0x30) != 0);
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
/*      */   static final char conv2ByteUTFtoUTF16(byte paramByte1, byte paramByte2)
/*      */   {
/* 2688 */     if ((paramByte1 < -62) || (paramByte1 > -33) || (!check80toBF(paramByte2)))
/*      */     {
/*      */ 
/* 2691 */       return 65533;
/*      */     }
/*      */     
/* 2694 */     return (char)((paramByte1 & 0x1F) << 6 | paramByte2 & 0x3F);
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
/*      */   static final char conv3ByteUTFtoUTF16(byte paramByte1, byte paramByte2, byte paramByte3)
/*      */   {
/* 2715 */     if (((paramByte1 != -32) || (!checkA0toBF(paramByte2)) || (!check80toBF(paramByte3))) && ((paramByte1 < -31) || (paramByte1 > -17) || (!check80toBF(paramByte2)) || (!check80toBF(paramByte3))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2720 */       return 65533;
/*      */     }
/*      */     
/* 2723 */     return (char)((paramByte1 & 0xF) << 12 | (paramByte2 & 0x3F) << 6 | paramByte3 & 0x3F);
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
/*      */   static final char conv3ByteAL32UTF8toUTF16(byte paramByte1, byte paramByte2, byte paramByte3)
/*      */   {
/* 2747 */     if (((paramByte1 != -32) || (!checkA0toBF(paramByte2)) || (!check80toBF(paramByte3))) && ((paramByte1 < -31) || (paramByte1 > -20) || (!check80toBF(paramByte2)) || (!check80toBF(paramByte3))) && ((paramByte1 != -19) || (!check80to9F(paramByte2)) || (!check80toBF(paramByte3))) && ((paramByte1 < -18) || (paramByte1 > -17) || (!check80toBF(paramByte2)) || (!check80toBF(paramByte3))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2754 */       return 65533;
/*      */     }
/*      */     
/* 2757 */     return (char)((paramByte1 & 0xF) << 12 | (paramByte2 & 0x3F) << 6 | paramByte3 & 0x3F);
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
/*      */   static final int conv4ByteAL32UTF8toUTF16(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, char[] paramArrayOfChar, int paramInt)
/*      */   {
/* 2779 */     int i = 0;
/*      */     
/* 2781 */     if (((paramByte1 != -16) || (!check90toBF(paramByte2)) || (!check80toBF(paramByte3)) || (!check80toBF(paramByte4))) && ((paramByte1 < -15) || (paramByte1 > -13) || (!check80toBF(paramByte2)) || (!check80toBF(paramByte3)) || (!check80toBF(paramByte4))) && ((paramByte1 != -12) || (!check80to8F(paramByte2)) || (!check80toBF(paramByte3)) || (!check80toBF(paramByte4))))
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 2786 */       paramArrayOfChar[paramInt] = 65533;
/*      */       
/* 2788 */       return 1;
/*      */     }
/*      */     
/* 2791 */     paramArrayOfChar[paramInt] = ((char)((((paramByte1 & 0x7) << 2 | paramByte2 >>> 4 & 0x3) - 1 & 0xF) << 6 | (paramByte2 & 0xF) << 2 | paramByte3 >>> 4 & 0x3 | 0xD800));
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2796 */     paramArrayOfChar[(paramInt + 1)] = ((char)((paramByte3 & 0xF) << 6 | paramByte4 & 0x3F | 0xDC00));
/*      */     
/* 2798 */     return 2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static abstract class CharacterConverterBehavior
/*      */   {
/* 2809 */     public static final char[] NULL_CHARS = new char[1];
/*      */     public static final char UTF16_REPLACEMENT_CHAR = '�';
/* 2811 */     public static final CharacterConverterBehavior REPORT_ERROR = new CharacterConverterBehavior("Report Error")
/*      */     {
/*      */ 
/*      */       public void onFailConversion()
/*      */         throws SQLException
/*      */       {
/* 2817 */         SQLException localSQLException = DatabaseError.createSqlException(null, 55);
/* 2818 */         localSQLException.fillInStackTrace();
/* 2819 */         throw localSQLException;
/*      */       }
/*      */       
/*      */ 
/*      */       public void onFailConversion(char paramAnonymousChar)
/*      */         throws SQLException
/*      */       {
/* 2826 */         if (paramAnonymousChar == 65533)
/*      */         {
/*      */ 
/* 2829 */           SQLException localSQLException = DatabaseError.createSqlException(null, 55);
/* 2830 */           localSQLException.fillInStackTrace();
/* 2831 */           throw localSQLException;
/*      */         }
/*      */       }
/*      */     };
/*      */     
/*      */ 
/*      */ 
/* 2838 */     public static final CharacterConverterBehavior REPLACEMENT = new CharacterConverterBehavior("Replacement")
/*      */     {
/*      */       public void onFailConversion()
/*      */         throws SQLException
/*      */       {}
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */       public void onFailConversion(char paramAnonymousChar)
/*      */         throws SQLException
/*      */       {}
/*      */     };
/*      */     
/*      */ 
/*      */     private final String m_name;
/*      */     
/*      */ 
/*      */ 
/*      */     public CharacterConverterBehavior(String paramString)
/*      */     {
/* 2859 */       this.m_name = paramString;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public abstract void onFailConversion(char paramChar)
/*      */       throws SQLException;
/*      */     
/*      */ 
/*      */ 
/*      */     public abstract void onFailConversion()
/*      */       throws SQLException;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static void failUTFConversion()
/*      */     throws SQLException
/*      */   {
/* 2878 */     SQLException localSQLException = DatabaseError.createSqlException(null, 55);
/* 2879 */     localSQLException.fillInStackTrace();
/* 2880 */     throw localSQLException;
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
/*      */   public int encodedByteLength(String paramString)
/*      */   {
/* 2898 */     if ((paramString == null) || (paramString.length() == 0)) return 0;
/* 2899 */     return convertWithReplacement(paramString).length;
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
/*      */   public int encodedByteLength(char[] paramArrayOfChar)
/*      */   {
/* 2916 */     if ((paramArrayOfChar == null) || (paramArrayOfChar.length == 0)) return 0;
/* 2917 */     return convertWithReplacement(new String(paramArrayOfChar)).length;
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
/*      */   public int toCharWithReplacement(byte[] paramArrayOfByte, int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
/*      */     throws SQLException
/*      */   {
/* 2942 */     SQLException localSQLException = DatabaseError.createSqlException(getConnectionDuringExceptionHandling(), 23);
/* 2943 */     localSQLException.fillInStackTrace();
/* 2944 */     throw localSQLException;
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
/*      */   protected OracleConnection getConnectionDuringExceptionHandling()
/*      */   {
/* 2961 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public boolean isUnknown()
/*      */   {
/* 2968 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/* 2973 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   public abstract boolean isLossyFrom(CharacterSet paramCharacterSet);
/*      */   
/*      */   public abstract boolean isConvertibleFrom(CharacterSet paramCharacterSet);
/*      */   
/*      */   public abstract String toStringWithReplacement(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
/*      */   
/*      */   public abstract byte[] convert(String paramString)
/*      */     throws SQLException;
/*      */   
/*      */   public abstract byte[] convertWithReplacement(String paramString);
/*      */   
/*      */   public abstract byte[] convert(CharacterSet paramCharacterSet, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
/*      */     throws SQLException;
/*      */   
/*      */   abstract int decode(CharacterWalker paramCharacterWalker)
/*      */     throws SQLException;
/*      */   
/*      */   abstract void encode(CharacterBuffer paramCharacterBuffer, int paramInt)
/*      */     throws SQLException;
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/CharacterSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */