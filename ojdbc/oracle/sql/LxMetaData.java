/*      */ package oracle.sql;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LxMetaData
/*      */ {
/*      */   private static final String DEFAULT_ES_ORA_LANGUAGE = "LATIN AMERICAN SPANISH";
/*      */   private static final int WIDTH_SIZE = 8;
/*      */   private static final short WIDTH_MASK = 255;
/*      */   public static final int ST_BADCODESET = 0;
/*      */   
/*      */   static Locale getJavaLocale(String paramString1, String paramString2)
/*      */   {
/*   49 */     if (paramString1 == null)
/*      */     {
/*   51 */       return null;
/*      */     }
/*      */     
/*   54 */     String str1 = paramString1;
/*      */     
/*   56 */     String str2 = EN_LOCALE.getLanguage();
/*   57 */     if (!"".equals(str1))
/*      */     {
/*   59 */       if (ORACLE_LANG_2_ISO_A2_LANG == null)
/*   60 */         ORACLE_LANG_2_ISO_A2_LANG = getLang2IsoLangMap();
/*   61 */       str2 = (String)ORACLE_LANG_2_ISO_A2_LANG.get(str1.toUpperCase(Locale.US));
/*      */       
/*   63 */       if (str2 == null)
/*      */       {
/*   65 */         return null;
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*   70 */       str1 = "AMERICAN";
/*      */     }
/*      */     
/*   73 */     String str3 = null;
/*   74 */     if ((paramString2 == null) || ((str3 = paramString2.toUpperCase(Locale.US)) == null) || ("".equals(str3)))
/*      */     {
/*      */ 
/*      */ 
/*   78 */       if (ORACLE_LANG_2_TERR == null)
/*   79 */         ORACLE_LANG_2_TERR = getLang2Terr();
/*   80 */       str3 = (String)ORACLE_LANG_2_TERR.get(str1);
/*      */     }
/*      */     
/*   83 */     if (ORACLE_TERR_2_ISO_A2_TERR == null)
/*   84 */       ORACLE_TERR_2_ISO_A2_TERR = getTerr2IsoTerrMap();
/*   85 */     String str4 = (String)ORACLE_TERR_2_ISO_A2_TERR.get(str3);
/*   86 */     if (str4 == null)
/*      */     {
/*   88 */       return null;
/*      */     }
/*      */     
/*   91 */     return new Locale(str2, str4);
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
/*      */   public static String getNLSLanguage(Locale paramLocale)
/*      */   {
/*  115 */     String str = getOraLocale(paramLocale);
/*  116 */     if (str == null)
/*  117 */       return null;
/*  118 */     int i = str.indexOf('_');
/*  119 */     return i < 0 ? str : str.substring(0, i);
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
/*      */   public static String getNLSTerritory(Locale paramLocale)
/*      */   {
/*  135 */     String str = getOraLocale(paramLocale);
/*  136 */     if (str == null)
/*  137 */       return null;
/*  138 */     int i = str.indexOf('_');
/*  139 */     return i < 0 ? null : str.substring(i + 1);
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
/*      */   private static String getOraLocale(Locale paramLocale)
/*      */   {
/*  154 */     if (paramLocale == null)
/*      */     {
/*  156 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  160 */     String str1 = paramLocale.getLanguage().equals("") ? EN_LOCALE.getLanguage() : paramLocale.getLanguage();
/*      */     
/*      */ 
/*  163 */     if (ISO_A2_LANG_2_ORACLE_LANG == null) {
/*  164 */       ISO_A2_LANG_2_ORACLE_LANG = getIsoLangToOracleMap();
/*      */     }
/*      */     
/*  167 */     String str2 = (String)ISO_A2_LANG_2_ORACLE_LANG.get(str1);
/*  168 */     if (str2 == null) {
/*  169 */       return null;
/*      */     }
/*      */     
/*  172 */     String str3 = paramLocale.getCountry();
/*      */     
/*  174 */     if (str3.equals(""))
/*      */     {
/*  176 */       if (ISO_LANGUAGE_DEFAULT_TERRITORY == null) {
/*  177 */         ISO_LANGUAGE_DEFAULT_TERRITORY = getIsoLangDefaultTerrMap();
/*      */       }
/*  179 */       str3 = (String)ISO_LANGUAGE_DEFAULT_TERRITORY.get(str1);
/*      */       
/*  181 */       if (str3 == null) {
/*  182 */         return null;
/*      */       }
/*      */     }
/*  185 */     Locale localLocale = new Locale(str1, str3);
/*      */     
/*      */ 
/*  188 */     if (ISO_LOCALE_2_ORACLE_LOCALE == null) {
/*  189 */       ISO_LOCALE_2_ORACLE_LOCALE = getIsoLocToOracleMap();
/*      */     }
/*  191 */     String str4 = (String)ISO_LOCALE_2_ORACLE_LOCALE.get(localLocale.toString());
/*      */     
/*      */ 
/*  194 */     if (str4 != null)
/*      */     {
/*  196 */       return str4;
/*      */     }
/*      */     
/*  199 */     if ("es".equals(str1))
/*      */     {
/*      */ 
/*  202 */       str2 = "LATIN AMERICAN SPANISH";
/*      */     }
/*      */     
/*      */ 
/*  206 */     if (ISO_A2_TERR_2_ORACLE_TERR == null) {
/*  207 */       ISO_A2_TERR_2_ORACLE_TERR = getIsoTerrToOracleMap();
/*      */     }
/*  209 */     String str5 = (String)ISO_A2_TERR_2_ORACLE_TERR.get(str3);
/*  210 */     return str5 != null ? str2 + "_" + str5 : null;
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
/*      */   public static int getRatio(int paramInt1, int paramInt2)
/*      */   {
/*  251 */     if (paramInt2 == paramInt1)
/*      */     {
/*  253 */       return 1;
/*      */     }
/*      */     
/*  256 */     if (CHARSET_RATIO == null)
/*      */     {
/*  258 */       CHARSET_RATIO = getCharsetRatio();
/*      */     }
/*      */     
/*  261 */     Object localObject = CHARSET_RATIO.get(Integer.toString(paramInt1));
/*  262 */     if (localObject == null) {
/*  263 */       return 0;
/*      */     }
/*  265 */     int i = Integer.parseInt((String)localObject);
/*      */     
/*      */ 
/*  268 */     localObject = CHARSET_RATIO.get(Integer.toString(paramInt2));
/*  269 */     if (localObject == null) {
/*  270 */       return 0;
/*      */     }
/*  272 */     int j = Integer.parseInt((String)localObject);
/*      */     
/*  274 */     int k = i & 0xFF;
/*      */     
/*  276 */     if (k == 1)
/*      */     {
/*  278 */       return 1;
/*      */     }
/*      */     
/*  281 */     if (j >>> 8 == 0)
/*      */     {
/*      */ 
/*  284 */       return k;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  291 */     int m = j & 0xFF;
/*  292 */     int n = k / m;
/*      */     
/*  294 */     if (k % m != 0)
/*      */     {
/*  296 */       n++;
/*      */     }
/*      */     
/*  299 */     return n;
/*      */   }
/*      */   
/*  302 */   private static final Locale EN_LOCALE = new Locale("en", "US");
/*      */   
/*  304 */   private static Map ORACLE_LANG_2_ISO_A2_LANG = null;
/*      */   
/*      */   private static synchronized Map getLang2IsoLangMap() {
/*  307 */     HashMap localHashMap = new HashMap();
/*      */     
/*  309 */     localHashMap.put("ALBANIAN", "sq");
/*  310 */     localHashMap.put("AMERICAN", "en");
/*  311 */     localHashMap.put("ARABIC", "ar");
/*  312 */     localHashMap.put("ASSAMESE", "as");
/*  313 */     localHashMap.put("AZERBAIJANI", "az");
/*  314 */     localHashMap.put("BANGLA", "bn");
/*  315 */     localHashMap.put("BELARUSIAN", "be");
/*  316 */     localHashMap.put("BENGALI", "bn");
/*  317 */     localHashMap.put("BRAZILIAN PORTUGUESE", "pt");
/*  318 */     localHashMap.put("BULGARIAN", "bg");
/*  319 */     localHashMap.put("CANADIAN FRENCH", "fr");
/*  320 */     localHashMap.put("CATALAN", "ca");
/*  321 */     localHashMap.put("CROATIAN", "hr");
/*  322 */     localHashMap.put("CYRILLIC KAZAKH", "kk");
/*  323 */     localHashMap.put("CYRILLIC SERBIAN", "sr");
/*  324 */     localHashMap.put("CYRILLIC UZBEK", "uz");
/*  325 */     localHashMap.put("CZECH", "cs");
/*  326 */     localHashMap.put("DANISH", "da");
/*  327 */     localHashMap.put("DUTCH", "nl");
/*  328 */     localHashMap.put("EGYPTIAN", "ar");
/*  329 */     localHashMap.put("ENGLISH", "en");
/*  330 */     localHashMap.put("ESTONIAN", "et");
/*  331 */     localHashMap.put("FINNISH", "fi");
/*  332 */     localHashMap.put("FRENCH", "fr");
/*  333 */     localHashMap.put("GERMAN", "de");
/*  334 */     localHashMap.put("GERMAN DIN", "de");
/*  335 */     localHashMap.put("GREEK", "el");
/*  336 */     localHashMap.put("GUJARATI", "gu");
/*  337 */     localHashMap.put("HEBREW", "iw");
/*  338 */     localHashMap.put("HINDI", "hi");
/*  339 */     localHashMap.put("HUNGARIAN", "hu");
/*  340 */     localHashMap.put("ICELANDIC", "is");
/*  341 */     localHashMap.put("INDONESIAN", "in");
/*  342 */     localHashMap.put("IRISH", "ga");
/*  343 */     localHashMap.put("ITALIAN", "it");
/*  344 */     localHashMap.put("JAPANESE", "ja");
/*  345 */     localHashMap.put("KANNADA", "kn");
/*  346 */     localHashMap.put("KOREAN", "ko");
/*  347 */     localHashMap.put("LATIN AMERICAN SPANISH", "es");
/*  348 */     localHashMap.put("LATIN SERBIAN", "sr");
/*  349 */     localHashMap.put("LATIN UZBEK", "uz");
/*  350 */     localHashMap.put("LATVIAN", "lv");
/*  351 */     localHashMap.put("LITHUANIAN", "lt");
/*  352 */     localHashMap.put("MACEDONIAN", "mk");
/*  353 */     localHashMap.put("MALAY", "ms");
/*  354 */     localHashMap.put("MALAYALAM", "ml");
/*  355 */     localHashMap.put("MARATHI", "mr");
/*  356 */     localHashMap.put("MEXICAN SPANISH", "es");
/*  357 */     localHashMap.put("NORWEGIAN", "no");
/*  358 */     localHashMap.put("NUMERIC DATE LANGUAGE", "en");
/*  359 */     localHashMap.put("ORIYA", "or");
/*  360 */     localHashMap.put("POLISH", "pl");
/*  361 */     localHashMap.put("PORTUGUESE", "pt");
/*  362 */     localHashMap.put("PUNJABI", "pa");
/*  363 */     localHashMap.put("ROMANIAN", "ro");
/*  364 */     localHashMap.put("RUSSIAN", "ru");
/*  365 */     localHashMap.put("SIMPLIFIED CHINESE", "zh");
/*  366 */     localHashMap.put("SLOVAK", "sk");
/*  367 */     localHashMap.put("SLOVENIAN", "sl");
/*  368 */     localHashMap.put("SPANISH", "es");
/*  369 */     localHashMap.put("SWEDISH", "sv");
/*  370 */     localHashMap.put("TAMIL", "ta");
/*  371 */     localHashMap.put("TELUGU", "te");
/*  372 */     localHashMap.put("THAI", "th");
/*  373 */     localHashMap.put("TRADITIONAL CHINESE", "zh");
/*  374 */     localHashMap.put("TURKISH", "tr");
/*  375 */     localHashMap.put("UKRAINIAN", "uk");
/*  376 */     localHashMap.put("VIETNAMESE", "vi");
/*      */     
/*  378 */     return localHashMap;
/*      */   }
/*      */   
/*  381 */   private static Map ORACLE_TERR_2_ISO_A2_TERR = null;
/*      */   
/*      */   private static synchronized Map getTerr2IsoTerrMap() {
/*  384 */     HashMap localHashMap = new HashMap();
/*      */     
/*  386 */     localHashMap.put("ALBANIA", "AL");
/*  387 */     localHashMap.put("ALGERIA", "DZ");
/*  388 */     localHashMap.put("AMERICA", "US");
/*  389 */     localHashMap.put("ARGENTINA", "AR");
/*  390 */     localHashMap.put("AUSTRALIA", "AU");
/*  391 */     localHashMap.put("AUSTRIA", "AT");
/*  392 */     localHashMap.put("AZERBAIJAN", "AZ");
/*  393 */     localHashMap.put("BAHRAIN", "BH");
/*  394 */     localHashMap.put("BANGLADESH", "BD");
/*  395 */     localHashMap.put("BELARUS", "BY");
/*  396 */     localHashMap.put("BELGIUM", "BE");
/*  397 */     localHashMap.put("BRAZIL", "BR");
/*  398 */     localHashMap.put("BULGARIA", "BG");
/*  399 */     localHashMap.put("CANADA", "CA");
/*  400 */     localHashMap.put("CATALONIA", "ES");
/*  401 */     localHashMap.put("CHILE", "CL");
/*  402 */     localHashMap.put("CHINA", "CN");
/*  403 */     localHashMap.put("CIS", "RU");
/*  404 */     localHashMap.put("COLOMBIA", "CO");
/*  405 */     localHashMap.put("COSTA RICA", "CR");
/*  406 */     localHashMap.put("CROATIA", "HR");
/*  407 */     localHashMap.put("CYPRUS", "CY");
/*  408 */     localHashMap.put("CZECH REPUBLIC", "CZ");
/*  409 */     localHashMap.put("CZECHOSLOVAKIA", "CZ");
/*  410 */     localHashMap.put("DENMARK", "DK");
/*  411 */     localHashMap.put("DJIBOUTI", "DJ");
/*  412 */     localHashMap.put("ECUADOR", "EC");
/*  413 */     localHashMap.put("EGYPT", "EG");
/*  414 */     localHashMap.put("EL SALVADOR", "SV");
/*  415 */     localHashMap.put("ESTONIA", "EE");
/*  416 */     localHashMap.put("FINLAND", "FI");
/*  417 */     localHashMap.put("FRANCE", "FR");
/*  418 */     localHashMap.put("FYR MACEDONIA", "MK");
/*  419 */     localHashMap.put("GERMANY", "DE");
/*  420 */     localHashMap.put("GREECE", "GR");
/*  421 */     localHashMap.put("GUATEMALA", "GT");
/*  422 */     localHashMap.put("HONG KONG", "HK");
/*  423 */     localHashMap.put("HUNGARY", "HU");
/*  424 */     localHashMap.put("ICELAND", "IS");
/*  425 */     localHashMap.put("INDIA", "IN");
/*  426 */     localHashMap.put("INDONESIA", "ID");
/*  427 */     localHashMap.put("IRAQ", "IQ");
/*  428 */     localHashMap.put("IRELAND", "IE");
/*  429 */     localHashMap.put("ISRAEL", "IL");
/*  430 */     localHashMap.put("ITALY", "IT");
/*  431 */     localHashMap.put("JAPAN", "JP");
/*  432 */     localHashMap.put("JORDAN", "JO");
/*  433 */     localHashMap.put("KAZAKHSTAN", "KZ");
/*  434 */     localHashMap.put("KOREA", "KR");
/*  435 */     localHashMap.put("KUWAIT", "KW");
/*  436 */     localHashMap.put("LATVIA", "LV");
/*  437 */     localHashMap.put("LEBANON", "LB");
/*  438 */     localHashMap.put("LIBYA", "LY");
/*  439 */     localHashMap.put("LITHUANIA", "LT");
/*  440 */     localHashMap.put("LUXEMBOURG", "LU");
/*  441 */     localHashMap.put("MACEDONIA", "MK");
/*  442 */     localHashMap.put("MALAYSIA", "MY");
/*  443 */     localHashMap.put("MAURITANIA", "MR");
/*  444 */     localHashMap.put("MEXICO", "MX");
/*  445 */     localHashMap.put("MOROCCO", "MA");
/*  446 */     localHashMap.put("NEW ZEALAND", "NZ");
/*  447 */     localHashMap.put("NICARAGUA", "NI");
/*  448 */     localHashMap.put("NORWAY", "NO");
/*  449 */     localHashMap.put("OMAN", "OM");
/*  450 */     localHashMap.put("PANAMA", "PA");
/*  451 */     localHashMap.put("PERU", "PE");
/*  452 */     localHashMap.put("PHILIPPINES", "PH");
/*  453 */     localHashMap.put("POLAND", "PL");
/*  454 */     localHashMap.put("PORTUGAL", "PT");
/*  455 */     localHashMap.put("PUERTO RICO", "PR");
/*  456 */     localHashMap.put("QATAR", "QA");
/*  457 */     localHashMap.put("ROMANIA", "RO");
/*  458 */     localHashMap.put("RUSSIA", "RU");
/*  459 */     localHashMap.put("SAUDI ARABIA", "SA");
/*  460 */     localHashMap.put("SERBIA AND MONTENEGRO", "CS");
/*  461 */     localHashMap.put("SINGAPORE", "SG");
/*  462 */     localHashMap.put("SLOVAKIA", "SK");
/*  463 */     localHashMap.put("SLOVENIA", "SI");
/*  464 */     localHashMap.put("SOMALIA", "SO");
/*  465 */     localHashMap.put("SOUTH AFRICA", "ZA");
/*  466 */     localHashMap.put("SPAIN", "ES");
/*  467 */     localHashMap.put("SUDAN", "SD");
/*  468 */     localHashMap.put("SWEDEN", "SE");
/*  469 */     localHashMap.put("SWITZERLAND", "CH");
/*  470 */     localHashMap.put("SYRIA", "SY");
/*  471 */     localHashMap.put("TAIWAN", "TW");
/*  472 */     localHashMap.put("THAILAND", "TH");
/*  473 */     localHashMap.put("THE NETHERLANDS", "NL");
/*  474 */     localHashMap.put("TUNISIA", "TN");
/*  475 */     localHashMap.put("TURKEY", "TR");
/*  476 */     localHashMap.put("UKRAINE", "UA");
/*  477 */     localHashMap.put("UNITED ARAB EMIRATES", "AE");
/*  478 */     localHashMap.put("UNITED KINGDOM", "GB");
/*  479 */     localHashMap.put("UZBEKISTAN", "UZ");
/*  480 */     localHashMap.put("VENEZUELA", "VE");
/*  481 */     localHashMap.put("VIETNAM", "VN");
/*  482 */     localHashMap.put("YEMEN", "YE");
/*  483 */     localHashMap.put("YUGOSLAVIA", "YU");
/*      */     
/*  485 */     return localHashMap;
/*      */   }
/*      */   
/*  488 */   private static Map ORACLE_LANG_2_TERR = null;
/*      */   
/*      */   private static synchronized Map getLang2Terr() {
/*  491 */     HashMap localHashMap = new HashMap();
/*      */     
/*  493 */     localHashMap.put("ALBANIAN", "ALBANIA");
/*  494 */     localHashMap.put("AMERICAN", "AMERICA");
/*  495 */     localHashMap.put("ARABIC", "UNITED ARAB EMIRATES");
/*  496 */     localHashMap.put("ASSAMESE", "INDIA");
/*  497 */     localHashMap.put("AZERBAIJANI", "AZERBAIJAN");
/*  498 */     localHashMap.put("BANGLA", "INDIA");
/*  499 */     localHashMap.put("BELARUSIAN", "BELARUS");
/*  500 */     localHashMap.put("BRAZILIAN PORTUGUESE", "BRAZIL");
/*  501 */     localHashMap.put("BULGARIAN", "BULGARIA");
/*  502 */     localHashMap.put("CANADIAN FRENCH", "CANADA");
/*  503 */     localHashMap.put("CATALAN", "CATALONIA");
/*  504 */     localHashMap.put("CROATIAN", "CROATIA");
/*  505 */     localHashMap.put("CYRILLIC KAZAKH", "KAZAKHSTAN");
/*  506 */     localHashMap.put("CYRILLIC SERBIAN", "SERBIA AND MONTENEGRO");
/*  507 */     localHashMap.put("CYRILLIC UZBEK", "UZBEKISTAN");
/*  508 */     localHashMap.put("CZECH", "CZECH REPUBLIC");
/*  509 */     localHashMap.put("DANISH", "DENMARK");
/*  510 */     localHashMap.put("DUTCH", "THE NETHERLANDS");
/*  511 */     localHashMap.put("EGYPTIAN", "EGYPT");
/*  512 */     localHashMap.put("ENGLISH", "UNITED KINGDOM");
/*  513 */     localHashMap.put("ESTONIAN", "ESTONIA");
/*  514 */     localHashMap.put("FINNISH", "FINLAND");
/*  515 */     localHashMap.put("FRENCH", "FRANCE");
/*  516 */     localHashMap.put("GERMAN", "GERMANY");
/*  517 */     localHashMap.put("GERMAN DIN", "GERMANY");
/*  518 */     localHashMap.put("GREEK", "GREECE");
/*  519 */     localHashMap.put("GUJARATI", "INDIA");
/*  520 */     localHashMap.put("HEBREW", "ISRAEL");
/*  521 */     localHashMap.put("HINDI", "INDIA");
/*  522 */     localHashMap.put("HUNGARIAN", "HUNGARY");
/*  523 */     localHashMap.put("ICELANDIC", "ICELAND");
/*  524 */     localHashMap.put("INDONESIAN", "INDONESIA");
/*  525 */     localHashMap.put("IRISH", "IRELAND");
/*  526 */     localHashMap.put("ITALIAN", "ITALY");
/*  527 */     localHashMap.put("JAPANESE", "JAPAN");
/*  528 */     localHashMap.put("KANNADA", "INDIA");
/*  529 */     localHashMap.put("KOREAN", "KOREA");
/*  530 */     localHashMap.put("LATIN AMERICAN SPANISH", "AMERICA");
/*  531 */     localHashMap.put("LATIN SERBIAN", "SERBIA AND MONTENEGRO");
/*  532 */     localHashMap.put("LATIN UZBEK", "UZBEKISTAN");
/*  533 */     localHashMap.put("LATVIAN", "LATVIA");
/*  534 */     localHashMap.put("LITHUANIAN", "LITHUANIA");
/*  535 */     localHashMap.put("MACEDONIAN", "FYR MACEDONIA");
/*  536 */     localHashMap.put("MALAY", "MALAYSIA");
/*  537 */     localHashMap.put("MALAYALAM", "INDIA");
/*  538 */     localHashMap.put("MARATHI", "INDIA");
/*  539 */     localHashMap.put("MEXICAN SPANISH", "MEXICO");
/*  540 */     localHashMap.put("NORWEGIAN", "NORWAY");
/*  541 */     localHashMap.put("ORIYA", "INDIA");
/*  542 */     localHashMap.put("POLISH", "POLAND");
/*  543 */     localHashMap.put("PORTUGUESE", "PORTUGAL");
/*  544 */     localHashMap.put("PUNJABI", "INDIA");
/*  545 */     localHashMap.put("ROMANIAN", "ROMANIA");
/*  546 */     localHashMap.put("RUSSIAN", "RUSSIA");
/*  547 */     localHashMap.put("SIMPLIFIED CHINESE", "CHINA");
/*  548 */     localHashMap.put("SLOVAK", "SLOVAKIA");
/*  549 */     localHashMap.put("SLOVENIAN", "SLOVENIA");
/*  550 */     localHashMap.put("SPANISH", "SPAIN");
/*  551 */     localHashMap.put("SWEDISH", "SWEDEN");
/*  552 */     localHashMap.put("TAMIL", "INDIA");
/*  553 */     localHashMap.put("TELUGU", "INDIA");
/*  554 */     localHashMap.put("THAI", "THAILAND");
/*  555 */     localHashMap.put("TRADITIONAL CHINESE", "TAIWAN");
/*  556 */     localHashMap.put("TURKISH", "TURKEY");
/*  557 */     localHashMap.put("UKRAINIAN", "UKRAINE");
/*  558 */     localHashMap.put("VIETNAMESE", "VIETNAM");
/*      */     
/*  560 */     return localHashMap;
/*      */   }
/*      */   
/*  563 */   private static Map ISO_A2_LANG_2_ORACLE_LANG = null;
/*      */   
/*      */   private static synchronized Map getIsoLangToOracleMap() {
/*  566 */     HashMap localHashMap = new HashMap();
/*      */     
/*  568 */     localHashMap.put("ar", "ARABIC");
/*  569 */     localHashMap.put("as", "ASSAMESE");
/*  570 */     localHashMap.put("az", "AZERBAIJANI");
/*  571 */     localHashMap.put("be", "BELARUSIAN");
/*  572 */     localHashMap.put("bg", "BULGARIAN");
/*  573 */     localHashMap.put("bn", "BANGLA");
/*  574 */     localHashMap.put("ca", "CATALAN");
/*  575 */     localHashMap.put("cs", "CZECH");
/*  576 */     localHashMap.put("da", "DANISH");
/*  577 */     localHashMap.put("de", "GERMAN");
/*  578 */     localHashMap.put("el", "GREEK");
/*  579 */     localHashMap.put("en", "ENGLISH");
/*  580 */     localHashMap.put("es", "SPANISH");
/*  581 */     localHashMap.put("et", "ESTONIAN");
/*  582 */     localHashMap.put("fi", "FINNISH");
/*  583 */     localHashMap.put("fr", "FRENCH");
/*  584 */     localHashMap.put("ga", "IRISH");
/*  585 */     localHashMap.put("gu", "GUJARATI");
/*  586 */     localHashMap.put("he", "HEBREW");
/*  587 */     localHashMap.put("hi", "HINDI");
/*  588 */     localHashMap.put("hr", "CROATIAN");
/*  589 */     localHashMap.put("hu", "HUNGARIAN");
/*  590 */     localHashMap.put("id", "INDONESIAN");
/*  591 */     localHashMap.put("in", "INDONESIAN");
/*  592 */     localHashMap.put("is", "ICELANDIC");
/*  593 */     localHashMap.put("it", "ITALIAN");
/*  594 */     localHashMap.put("iw", "HEBREW");
/*  595 */     localHashMap.put("ja", "JAPANESE");
/*  596 */     localHashMap.put("kk", "CYRILLIC KAZAKH");
/*  597 */     localHashMap.put("kn", "KANNADA");
/*  598 */     localHashMap.put("ko", "KOREAN");
/*  599 */     localHashMap.put("lt", "LITHUANIAN");
/*  600 */     localHashMap.put("lv", "LATVIAN");
/*  601 */     localHashMap.put("mk", "MACEDONIAN");
/*  602 */     localHashMap.put("ml", "MALAYALAM");
/*  603 */     localHashMap.put("mr", "MARATHI");
/*  604 */     localHashMap.put("ms", "MALAY");
/*  605 */     localHashMap.put("nb", "NORWEGIAN");
/*  606 */     localHashMap.put("nl", "DUTCH");
/*  607 */     localHashMap.put("nn", "NORWEGIAN");
/*  608 */     localHashMap.put("no", "NORWEGIAN");
/*  609 */     localHashMap.put("or", "ORIYA");
/*  610 */     localHashMap.put("pa", "PUNJABI");
/*  611 */     localHashMap.put("pl", "POLISH");
/*  612 */     localHashMap.put("pt", "PORTUGUESE");
/*  613 */     localHashMap.put("ro", "ROMANIAN");
/*  614 */     localHashMap.put("ru", "RUSSIAN");
/*  615 */     localHashMap.put("sk", "SLOVAK");
/*  616 */     localHashMap.put("sl", "SLOVENIAN");
/*  617 */     localHashMap.put("sq", "ALBANIAN");
/*  618 */     localHashMap.put("sr", "CYRILLIC SERBIAN");
/*  619 */     localHashMap.put("sv", "SWEDISH");
/*  620 */     localHashMap.put("ta", "TAMIL");
/*  621 */     localHashMap.put("te", "TELUGU");
/*  622 */     localHashMap.put("th", "THAI");
/*  623 */     localHashMap.put("tr", "TURKISH");
/*  624 */     localHashMap.put("uk", "UKRAINIAN");
/*  625 */     localHashMap.put("uz", "LATIN UZBEK");
/*  626 */     localHashMap.put("vi", "VIETNAMESE");
/*  627 */     localHashMap.put("zh", "SIMPLIFIED CHINESE");
/*      */     
/*  629 */     return localHashMap;
/*      */   }
/*      */   
/*  632 */   private static Map ISO_LANGUAGE_DEFAULT_TERRITORY = null;
/*      */   
/*      */   private static synchronized Map getIsoLangDefaultTerrMap() {
/*  635 */     HashMap localHashMap = new HashMap();
/*      */     
/*  637 */     localHashMap.put("ar", "AE");
/*  638 */     localHashMap.put("as", "IN");
/*  639 */     localHashMap.put("az", "AZ");
/*  640 */     localHashMap.put("be", "BY");
/*  641 */     localHashMap.put("bg", "BG");
/*  642 */     localHashMap.put("bn", "BD");
/*  643 */     localHashMap.put("ca", "ES");
/*  644 */     localHashMap.put("cs", "CZ");
/*  645 */     localHashMap.put("da", "DK");
/*  646 */     localHashMap.put("de", "DE");
/*  647 */     localHashMap.put("el", "GR");
/*  648 */     localHashMap.put("en", "US");
/*  649 */     localHashMap.put("es", "ES");
/*  650 */     localHashMap.put("et", "EE");
/*  651 */     localHashMap.put("fi", "FI");
/*  652 */     localHashMap.put("fr", "FR");
/*  653 */     localHashMap.put("ga", "IE");
/*  654 */     localHashMap.put("gu", "IN");
/*  655 */     localHashMap.put("he", "IL");
/*  656 */     localHashMap.put("hi", "IN");
/*  657 */     localHashMap.put("hr", "HR");
/*  658 */     localHashMap.put("hu", "HU");
/*  659 */     localHashMap.put("id", "ID");
/*  660 */     localHashMap.put("in", "ID");
/*  661 */     localHashMap.put("is", "IS");
/*  662 */     localHashMap.put("it", "IT");
/*  663 */     localHashMap.put("iw", "IL");
/*  664 */     localHashMap.put("ja", "JP");
/*  665 */     localHashMap.put("kk", "KZ");
/*  666 */     localHashMap.put("kn", "IN");
/*  667 */     localHashMap.put("ko", "KR");
/*  668 */     localHashMap.put("lt", "LT");
/*  669 */     localHashMap.put("lv", "LV");
/*  670 */     localHashMap.put("mk", "MK");
/*  671 */     localHashMap.put("ml", "IN");
/*  672 */     localHashMap.put("mr", "IN");
/*  673 */     localHashMap.put("ms", "MY");
/*  674 */     localHashMap.put("nb", "NO");
/*  675 */     localHashMap.put("nl", "NL");
/*  676 */     localHashMap.put("nn", "NO");
/*  677 */     localHashMap.put("no", "NO");
/*  678 */     localHashMap.put("or", "IN");
/*  679 */     localHashMap.put("pa", "IN");
/*  680 */     localHashMap.put("pl", "PL");
/*  681 */     localHashMap.put("pt", "PT");
/*  682 */     localHashMap.put("ro", "RO");
/*  683 */     localHashMap.put("ru", "RU");
/*  684 */     localHashMap.put("sk", "SK");
/*  685 */     localHashMap.put("sl", "SI");
/*  686 */     localHashMap.put("sq", "AL");
/*  687 */     localHashMap.put("sr", "CS");
/*  688 */     localHashMap.put("sv", "SE");
/*  689 */     localHashMap.put("ta", "IN");
/*  690 */     localHashMap.put("te", "IN");
/*  691 */     localHashMap.put("th", "TH");
/*  692 */     localHashMap.put("tr", "TR");
/*  693 */     localHashMap.put("uk", "UA");
/*  694 */     localHashMap.put("uz", "UZ");
/*  695 */     localHashMap.put("vi", "VN");
/*  696 */     localHashMap.put("zh", "CN");
/*      */     
/*  698 */     return localHashMap;
/*      */   }
/*      */   
/*  701 */   private static Map ISO_LOCALE_2_ORACLE_LOCALE = null;
/*      */   
/*      */   private static synchronized Map getIsoLocToOracleMap() {
/*  704 */     HashMap localHashMap = new HashMap();
/*      */     
/*  706 */     localHashMap.put("ar_EG", "EGYPTIAN_EGYPT");
/*  707 */     localHashMap.put("ca_ES", "CATALAN_CATALONIA");
/*  708 */     localHashMap.put("en_US", "AMERICAN_AMERICA");
/*  709 */     localHashMap.put("es_ES", "SPANISH_SPAIN");
/*  710 */     localHashMap.put("es_MX", "MEXICAN SPANISH_MEXICO");
/*  711 */     localHashMap.put("fr_CA", "CANADIAN FRENCH_CANADA");
/*  712 */     localHashMap.put("pt_BR", "BRAZILIAN PORTUGUESE_BRAZIL");
/*  713 */     localHashMap.put("zh_HK", "TRADITIONAL CHINESE_HONG KONG");
/*  714 */     localHashMap.put("zh_TW", "TRADITIONAL CHINESE_TAIWAN");
/*      */     
/*  716 */     return localHashMap;
/*      */   }
/*      */   
/*  719 */   private static Map ISO_A2_TERR_2_ORACLE_TERR = null;
/*      */   
/*      */   private static synchronized Map getIsoTerrToOracleMap() {
/*  722 */     HashMap localHashMap = new HashMap();
/*      */     
/*  724 */     localHashMap.put("AE", "UNITED ARAB EMIRATES");
/*  725 */     localHashMap.put("AL", "ALBANIA");
/*  726 */     localHashMap.put("AR", "ARGENTINA");
/*  727 */     localHashMap.put("AT", "AUSTRIA");
/*  728 */     localHashMap.put("AU", "AUSTRALIA");
/*  729 */     localHashMap.put("AZ", "AZERBAIJAN");
/*  730 */     localHashMap.put("BD", "BANGLADESH");
/*  731 */     localHashMap.put("BE", "BELGIUM");
/*  732 */     localHashMap.put("BG", "BULGARIA");
/*  733 */     localHashMap.put("BH", "BAHRAIN");
/*  734 */     localHashMap.put("BR", "BRAZIL");
/*  735 */     localHashMap.put("BY", "BELARUS");
/*  736 */     localHashMap.put("CA", "CANADA");
/*  737 */     localHashMap.put("CH", "SWITZERLAND");
/*  738 */     localHashMap.put("CL", "CHILE");
/*  739 */     localHashMap.put("CN", "CHINA");
/*  740 */     localHashMap.put("CO", "COLOMBIA");
/*  741 */     localHashMap.put("CR", "COSTA RICA");
/*  742 */     localHashMap.put("CS", "SERBIA AND MONTENEGRO");
/*  743 */     localHashMap.put("CY", "CYPRUS");
/*  744 */     localHashMap.put("CZ", "CZECH REPUBLIC");
/*  745 */     localHashMap.put("DE", "GERMANY");
/*  746 */     localHashMap.put("DJ", "DJIBOUTI");
/*  747 */     localHashMap.put("DK", "DENMARK");
/*  748 */     localHashMap.put("DZ", "ALGERIA");
/*  749 */     localHashMap.put("EC", "ECUADOR");
/*  750 */     localHashMap.put("EE", "ESTONIA");
/*  751 */     localHashMap.put("EG", "EGYPT");
/*  752 */     localHashMap.put("ES", "SPAIN");
/*  753 */     localHashMap.put("FI", "FINLAND");
/*  754 */     localHashMap.put("FR", "FRANCE");
/*  755 */     localHashMap.put("GB", "UNITED KINGDOM");
/*  756 */     localHashMap.put("GR", "GREECE");
/*  757 */     localHashMap.put("GT", "GUATEMALA");
/*  758 */     localHashMap.put("HK", "HONG KONG");
/*  759 */     localHashMap.put("HR", "CROATIA");
/*  760 */     localHashMap.put("HU", "HUNGARY");
/*  761 */     localHashMap.put("ID", "INDONESIA");
/*  762 */     localHashMap.put("IE", "IRELAND");
/*  763 */     localHashMap.put("IL", "ISRAEL");
/*  764 */     localHashMap.put("IN", "INDIA");
/*  765 */     localHashMap.put("IQ", "IRAQ");
/*  766 */     localHashMap.put("IS", "ICELAND");
/*  767 */     localHashMap.put("IT", "ITALY");
/*  768 */     localHashMap.put("JO", "JORDAN");
/*  769 */     localHashMap.put("JP", "JAPAN");
/*  770 */     localHashMap.put("KR", "KOREA");
/*  771 */     localHashMap.put("KW", "KUWAIT");
/*  772 */     localHashMap.put("KZ", "KAZAKHSTAN");
/*  773 */     localHashMap.put("LB", "LEBANON");
/*  774 */     localHashMap.put("LT", "LITHUANIA");
/*  775 */     localHashMap.put("LU", "LUXEMBOURG");
/*  776 */     localHashMap.put("LV", "LATVIA");
/*  777 */     localHashMap.put("LY", "LIBYA");
/*  778 */     localHashMap.put("MA", "MOROCCO");
/*  779 */     localHashMap.put("MK", "FYR MACEDONIA");
/*  780 */     localHashMap.put("MR", "MAURITANIA");
/*  781 */     localHashMap.put("MX", "MEXICO");
/*  782 */     localHashMap.put("MY", "MALAYSIA");
/*  783 */     localHashMap.put("NI", "NICARAGUA");
/*  784 */     localHashMap.put("NL", "THE NETHERLANDS");
/*  785 */     localHashMap.put("NO", "NORWAY");
/*  786 */     localHashMap.put("NZ", "NEW ZEALAND");
/*  787 */     localHashMap.put("OM", "OMAN");
/*  788 */     localHashMap.put("PA", "PANAMA");
/*  789 */     localHashMap.put("PE", "PERU");
/*  790 */     localHashMap.put("PH", "PHILIPPINES");
/*  791 */     localHashMap.put("PL", "POLAND");
/*  792 */     localHashMap.put("PR", "PUERTO RICO");
/*  793 */     localHashMap.put("PT", "PORTUGAL");
/*  794 */     localHashMap.put("QA", "QATAR");
/*  795 */     localHashMap.put("RO", "ROMANIA");
/*  796 */     localHashMap.put("RU", "RUSSIA");
/*  797 */     localHashMap.put("SA", "SAUDI ARABIA");
/*  798 */     localHashMap.put("SD", "SUDAN");
/*  799 */     localHashMap.put("SE", "SWEDEN");
/*  800 */     localHashMap.put("SG", "SINGAPORE");
/*  801 */     localHashMap.put("SI", "SLOVENIA");
/*  802 */     localHashMap.put("SK", "SLOVAKIA");
/*  803 */     localHashMap.put("SO", "SOMALIA");
/*  804 */     localHashMap.put("SV", "EL SALVADOR");
/*  805 */     localHashMap.put("SY", "SYRIA");
/*  806 */     localHashMap.put("TH", "THAILAND");
/*  807 */     localHashMap.put("TN", "TUNISIA");
/*  808 */     localHashMap.put("TR", "TURKEY");
/*  809 */     localHashMap.put("TW", "TAIWAN");
/*  810 */     localHashMap.put("UA", "UKRAINE");
/*  811 */     localHashMap.put("US", "AMERICA");
/*  812 */     localHashMap.put("UZ", "UZBEKISTAN");
/*  813 */     localHashMap.put("VE", "VENEZUELA");
/*  814 */     localHashMap.put("VN", "VIETNAM");
/*  815 */     localHashMap.put("YE", "YEMEN");
/*  816 */     localHashMap.put("YU", "YUGOSLAVIA");
/*  817 */     localHashMap.put("ZA", "SOUTH AFRICA");
/*      */     
/*  819 */     return localHashMap;
/*      */   }
/*      */   
/*  822 */   private static Map CHARSET_RATIO = null;
/*      */   
/*      */   private static synchronized Map getCharsetRatio() {
/*  825 */     HashMap localHashMap = new HashMap();
/*  826 */     localHashMap.put("2000", "258");
/*  827 */     localHashMap.put("873", "4");
/*  828 */     localHashMap.put("557", "1");
/*  829 */     localHashMap.put("558", "1");
/*  830 */     localHashMap.put("559", "1");
/*  831 */     localHashMap.put("565", "1");
/*  832 */     localHashMap.put("566", "1");
/*  833 */     localHashMap.put("500", "1");
/*  834 */     localHashMap.put("320", "1");
/*  835 */     localHashMap.put("70", "1");
/*  836 */     localHashMap.put("36", "1");
/*  837 */     localHashMap.put("560", "1");
/*  838 */     localHashMap.put("556", "1");
/*  839 */     localHashMap.put("554", "1");
/*  840 */     localHashMap.put("561", "1");
/*  841 */     localHashMap.put("563", "1");
/*  842 */     localHashMap.put("555", "1");
/*  843 */     localHashMap.put("52", "1");
/*  844 */     localHashMap.put("173", "1");
/*  845 */     localHashMap.put("140", "1");
/*  846 */     localHashMap.put("191", "1");
/*  847 */     localHashMap.put("194", "1");
/*  848 */     localHashMap.put("314", "1");
/*  849 */     localHashMap.put("47", "1");
/*  850 */     localHashMap.put("179", "1");
/*  851 */     localHashMap.put("197", "1");
/*  852 */     localHashMap.put("43", "1");
/*  853 */     localHashMap.put("390", "1");
/*  854 */     localHashMap.put("233", "1");
/*  855 */     localHashMap.put("48", "1");
/*  856 */     localHashMap.put("19", "1");
/*  857 */     localHashMap.put("235", "1");
/*  858 */     localHashMap.put("185", "1");
/*  859 */     localHashMap.put("322", "1");
/*  860 */     localHashMap.put("323", "1");
/*  861 */     localHashMap.put("317", "1");
/*  862 */     localHashMap.put("188", "1");
/*  863 */     localHashMap.put("325", "1");
/*  864 */     localHashMap.put("326", "1");
/*  865 */     localHashMap.put("35", "1");
/*  866 */     localHashMap.put("49", "1");
/*  867 */     localHashMap.put("196", "1");
/*  868 */     localHashMap.put("51", "1");
/*  869 */     localHashMap.put("158", "1");
/*  870 */     localHashMap.put("159", "1");
/*  871 */     localHashMap.put("171", "1");
/*  872 */     localHashMap.put("11", "1");
/*  873 */     localHashMap.put("207", "1");
/*  874 */     localHashMap.put("222", "1");
/*  875 */     localHashMap.put("189", "1");
/*  876 */     localHashMap.put("180", "1");
/*  877 */     localHashMap.put("204", "1");
/*  878 */     localHashMap.put("225", "1");
/*  879 */     localHashMap.put("198", "1");
/*  880 */     localHashMap.put("182", "1");
/*  881 */     localHashMap.put("14", "1");
/*  882 */     localHashMap.put("202", "1");
/*  883 */     localHashMap.put("224", "1");
/*  884 */     localHashMap.put("232", "1");
/*  885 */     localHashMap.put("184", "1");
/*  886 */     localHashMap.put("301", "1");
/*  887 */     localHashMap.put("316", "1");
/*  888 */     localHashMap.put("32", "1");
/*  889 */     localHashMap.put("262", "1");
/*  890 */     localHashMap.put("162", "1");
/*  891 */     localHashMap.put("263", "1");
/*  892 */     localHashMap.put("163", "1");
/*  893 */     localHashMap.put("170", "1");
/*  894 */     localHashMap.put("150", "1");
/*  895 */     localHashMap.put("110", "1");
/*  896 */     localHashMap.put("113", "1");
/*  897 */     localHashMap.put("81", "1");
/*  898 */     localHashMap.put("327", "1");
/*  899 */     localHashMap.put("381", "1");
/*  900 */     localHashMap.put("324", "1");
/*  901 */     localHashMap.put("211", "1");
/*  902 */     localHashMap.put("37", "1");
/*  903 */     localHashMap.put("266", "1");
/*  904 */     localHashMap.put("166", "1");
/*  905 */     localHashMap.put("174", "1");
/*  906 */     localHashMap.put("380", "1");
/*  907 */     localHashMap.put("382", "1");
/*  908 */     localHashMap.put("386", "1");
/*  909 */     localHashMap.put("385", "1");
/*  910 */     localHashMap.put("172", "1");
/*  911 */     localHashMap.put("12", "1");
/*  912 */     localHashMap.put("201", "1");
/*  913 */     localHashMap.put("223", "1");
/*  914 */     localHashMap.put("208", "1");
/*  915 */     localHashMap.put("186", "1");
/*  916 */     localHashMap.put("401", "1");
/*  917 */     localHashMap.put("368", "1");
/*  918 */     localHashMap.put("17", "1");
/*  919 */     localHashMap.put("206", "1");
/*  920 */     localHashMap.put("200", "1");
/*  921 */     localHashMap.put("181", "1");
/*  922 */     localHashMap.put("25", "1");
/*  923 */     localHashMap.put("265", "1");
/*  924 */     localHashMap.put("165", "1");
/*  925 */     localHashMap.put("161", "1");
/*  926 */     localHashMap.put("23", "1");
/*  927 */     localHashMap.put("187", "1");
/*  928 */     localHashMap.put("92", "1");
/*  929 */     localHashMap.put("315", "1");
/*  930 */     localHashMap.put("38", "1");
/*  931 */     localHashMap.put("267", "1");
/*  932 */     localHashMap.put("167", "1");
/*  933 */     localHashMap.put("175", "1");
/*  934 */     localHashMap.put("154", "1");
/*  935 */     localHashMap.put("833", "2");
/*  936 */     localHashMap.put("835", "2");
/*  937 */     localHashMap.put("830", "3");
/*  938 */     localHashMap.put("837", "3");
/*  939 */     localHashMap.put("831", "3");
/*  940 */     localHashMap.put("836", "2");
/*  941 */     localHashMap.put("832", "2");
/*  942 */     localHashMap.put("838", "2");
/*  943 */     localHashMap.put("834", "2");
/*  944 */     localHashMap.put("829", "2");
/*  945 */     localHashMap.put("842", "2");
/*  946 */     localHashMap.put("840", "2");
/*  947 */     localHashMap.put("845", "2");
/*  948 */     localHashMap.put("846", "2");
/*  949 */     localHashMap.put("590", "1");
/*  950 */     localHashMap.put("114", "1");
/*  951 */     localHashMap.put("176", "1");
/*  952 */     localHashMap.put("383", "1");
/*  953 */     localHashMap.put("384", "1");
/*  954 */     localHashMap.put("192", "1");
/*  955 */     localHashMap.put("193", "1");
/*  956 */     localHashMap.put("195", "1");
/*  957 */     localHashMap.put("205", "1");
/*  958 */     localHashMap.put("190", "1");
/*  959 */     localHashMap.put("16", "1");
/*  960 */     localHashMap.put("40", "1");
/*  961 */     localHashMap.put("34", "1");
/*  962 */     localHashMap.put("18", "1");
/*  963 */     localHashMap.put("153", "1");
/*  964 */     localHashMap.put("155", "1");
/*  965 */     localHashMap.put("152", "1");
/*  966 */     localHashMap.put("13", "1");
/*  967 */     localHashMap.put("203", "1");
/*  968 */     localHashMap.put("226", "1");
/*  969 */     localHashMap.put("199", "1");
/*  970 */     localHashMap.put("183", "1");
/*  971 */     localHashMap.put("33", "1");
/*  972 */     localHashMap.put("15", "1");
/*  973 */     localHashMap.put("21", "1");
/*  974 */     localHashMap.put("353", "1");
/*  975 */     localHashMap.put("354", "1");
/*  976 */     localHashMap.put("41", "1");
/*  977 */     localHashMap.put("42", "1");
/*  978 */     localHashMap.put("319", "1");
/*  979 */     localHashMap.put("22", "1");
/*  980 */     localHashMap.put("82", "1");
/*  981 */     localHashMap.put("93", "1");
/*  982 */     localHashMap.put("312", "1");
/*  983 */     localHashMap.put("264", "1");
/*  984 */     localHashMap.put("164", "1");
/*  985 */     localHashMap.put("177", "1");
/*  986 */     localHashMap.put("156", "1");
/*  987 */     localHashMap.put("1", "1");
/*  988 */     localHashMap.put("221", "1");
/*  989 */     localHashMap.put("277", "1");
/*  990 */     localHashMap.put("4", "1");
/*  991 */     localHashMap.put("871", "3");
/*  992 */     localHashMap.put("872", "4");
/*  993 */     localHashMap.put("45", "1");
/*  994 */     localHashMap.put("44", "1");
/*  995 */     localHashMap.put("231", "1");
/*  996 */     localHashMap.put("230", "1");
/*  997 */     localHashMap.put("239", "1");
/*  998 */     localHashMap.put("2", "1");
/*  999 */     localHashMap.put("241", "1");
/* 1000 */     localHashMap.put("96", "1");
/* 1001 */     localHashMap.put("100", "1");
/* 1002 */     localHashMap.put("7", "1");
/* 1003 */     localHashMap.put("97", "1");
/* 1004 */     localHashMap.put("98", "1");
/* 1005 */     localHashMap.put("9", "1");
/* 1006 */     localHashMap.put("27", "1");
/* 1007 */     localHashMap.put("99", "1");
/* 1008 */     localHashMap.put("95", "1");
/* 1009 */     localHashMap.put("8", "1");
/* 1010 */     localHashMap.put("5", "1");
/* 1011 */     localHashMap.put("90", "1");
/* 1012 */     localHashMap.put("6", "1");
/* 1013 */     localHashMap.put("91", "1");
/* 1014 */     localHashMap.put("94", "1");
/* 1015 */     localHashMap.put("101", "1");
/* 1016 */     localHashMap.put("210", "1");
/* 1017 */     localHashMap.put("3", "1");
/* 1018 */     localHashMap.put("278", "1");
/* 1019 */     localHashMap.put("31", "1");
/* 1020 */     localHashMap.put("46", "1");
/* 1021 */     localHashMap.put("39", "1");
/* 1022 */     localHashMap.put("279", "1");
/* 1023 */     localHashMap.put("351", "1");
/* 1024 */     localHashMap.put("352", "1");
/* 1025 */     localHashMap.put("178", "1");
/* 1026 */     localHashMap.put("251", "1");
/* 1027 */     localHashMap.put("50", "1");
/* 1028 */     localHashMap.put("10", "1");
/* 1029 */     localHashMap.put("28", "1");
/* 1030 */     localHashMap.put("160", "1");
/* 1031 */     localHashMap.put("261", "1");
/* 1032 */     localHashMap.put("20", "1");
/* 1033 */     localHashMap.put("850", "2");
/* 1034 */     localHashMap.put("853", "2");
/* 1035 */     localHashMap.put("852", "2");
/* 1036 */     localHashMap.put("851", "2");
/* 1037 */     localHashMap.put("854", "260");
/* 1038 */     localHashMap.put("865", "2");
/* 1039 */     localHashMap.put("866", "2");
/* 1040 */     localHashMap.put("864", "2");
/* 1041 */     localHashMap.put("862", "1");
/* 1042 */     localHashMap.put("868", "2");
/* 1043 */     localHashMap.put("992", "2");
/* 1044 */     localHashMap.put("867", "2");
/* 1045 */     localHashMap.put("860", "4");
/* 1046 */     localHashMap.put("861", "2");
/* 1047 */     localHashMap.put("863", "4");
/*      */     
/* 1049 */     return localHashMap;
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/LxMetaData.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */