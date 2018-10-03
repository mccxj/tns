/*      */ package oracle.sql.converter;
/*      */ 
/*      */ import java.security.AccessController;
/*      */ import java.security.PrivilegedAction;
/*      */ import java.sql.SQLException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Locale;
/*      */ import java.util.StringTokenizer;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CharacterSetMetaData
/*      */ {
/*      */   static final short WIDTH_SIZE = 8;
/*      */   static final short WIDTH_MASK = 255;
/*      */   static final short FLAG_FIXEDWIDTH = 256;
/*      */   public static final int ST_BADCODESET = 0;
/*   66 */   private static final HashMap language = new HashMap(58, 1.0F);
/*   67 */   private static final HashMap territory = new HashMap(134, 1.0F);
/*      */   static InternalCharacterSetMetaData metaDataImpl;
/*      */   private static final short[][] m_maxCharWidth;
/*      */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_;
/*      */   public static final boolean TRACE = false;
/*      */   
/*      */   private static final void getMapProperties() {
/*      */     try {
/*   75 */       String[] arrayOfString = { "oracle.jdbc.languageMap", "oracle.jdbc.territoryMap" };
/*   76 */       HashMap[] arrayOfHashMap = { language, territory };
/*      */       
/*      */ 
/*      */ 
/*   80 */       for (int i = 0; i < arrayOfString.length; i++)
/*      */       {
/*   82 */         String str3 = arrayOfString[i];
/*   83 */         String str4 = (String)AccessController.doPrivileged(new PrivilegedAction()
/*      */         {
/*      */           public String run()
/*      */           {
/*   87 */             return System.getProperty(this.val$fstr, null);
/*      */           }
/*      */         });
/*      */         
/*   91 */         if (str4 != null)
/*      */         {
/*   93 */           StringTokenizer localStringTokenizer = new StringTokenizer(str4, "=;");
/*   94 */           if (localStringTokenizer.countTokens() % 2 == 0)
/*      */           {
/*   96 */             while (localStringTokenizer.hasMoreTokens())
/*      */             {
/*   98 */               String str1 = localStringTokenizer.nextToken();
/*   99 */               String str2 = localStringTokenizer.nextToken();
/*      */               
/*  101 */               arrayOfHashMap[i].put(str1, str2);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (Exception localException) {}
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1178 */     String str = null;
/*      */     
/* 1180 */     str = (String)language.get(paramLocale.getLanguage() + "_" + paramLocale.getCountry());
/*      */     
/* 1182 */     if (str == null)
/*      */     {
/* 1184 */       str = (String)language.get(paramLocale.getLanguage());
/*      */     }
/*      */     
/* 1187 */     return str;
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
/*      */   public static String getNLSTerritory(Locale paramLocale)
/*      */   {
/* 1201 */     String str = null;
/*      */     
/* 1203 */     str = (String)territory.get(paramLocale.getLanguage() + "_" + paramLocale.getCountry());
/*      */     
/* 1205 */     if (str == null)
/*      */     {
/* 1207 */       str = (String)territory.get(paramLocale.getCountry());
/*      */       
/* 1209 */       if (str == null)
/*      */       {
/* 1211 */         str = (String)territory.get(paramLocale.getLanguage());
/*      */       }
/*      */     }
/*      */     
/* 1215 */     return str;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static boolean isFixedWidth(int paramInt)
/*      */     throws SQLException
/*      */   {
/* 1226 */     return metaDataImpl.isFixedWidth(paramInt);
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
/*      */   public static int getRatio(int paramInt1, int paramInt2)
/*      */   {
/* 1260 */     if (paramInt2 == paramInt1)
/*      */     {
/* 1262 */       return 1;
/*      */     }
/*      */     
/* 1265 */     int i = metaDataImpl.getMaxCharLength(paramInt1);
/*      */     
/* 1267 */     if (i == 0)
/* 1268 */       return 0;
/* 1269 */     if (i == 1)
/* 1270 */       return 1;
/* 1271 */     if (paramInt2 == 1) {
/* 1272 */       return i;
/*      */     }
/* 1274 */     if (metaDataImpl.isFixedWidth(paramInt2))
/*      */     {
/*      */ 
/*      */ 
/* 1278 */       return i;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1286 */     int j = metaDataImpl.getMaxCharLength(paramInt2);
/* 1287 */     if (j == 0) {
/* 1288 */       return 0;
/*      */     }
/* 1290 */     int k = i / j;
/*      */     
/* 1292 */     if (i % j != 0)
/*      */     {
/* 1294 */       k++;
/*      */     }
/*      */     
/* 1297 */     return k;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class JdbcCharacterSetMetaData
/*      */     implements InternalCharacterSetMetaData
/*      */   {
/*      */     public boolean isFixedWidth(int paramInt)
/*      */     {
/* 1307 */       if (paramInt == 0) { return false;
/*      */       }
/*      */       
/* 1310 */       int i = -1;
/* 1311 */       int j = 0;
/* 1312 */       int k = CharacterSetMetaData.m_maxCharWidth.length - 1;
/* 1313 */       int m = -1;
/*      */       
/* 1315 */       while (j <= k)
/*      */       {
/* 1317 */         m = (j + k) / 2;
/*      */         
/* 1319 */         if (paramInt < CharacterSetMetaData.m_maxCharWidth[m][0])
/*      */         {
/* 1321 */           k = m - 1;
/*      */         }
/* 1323 */         else if (paramInt > CharacterSetMetaData.m_maxCharWidth[m][0])
/*      */         {
/* 1325 */           j = m + 1;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1330 */           i = m;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1336 */       return (CharacterSetMetaData.m_maxCharWidth[i][1] & 0x100) != 0;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int getMaxCharLength(int paramInt)
/*      */     {
/* 1345 */       int i = -1;
/* 1346 */       int j = 0;
/* 1347 */       int k = CharacterSetMetaData.m_maxCharWidth.length - 1;
/*      */       
/*      */ 
/* 1350 */       while (j <= k)
/*      */       {
/* 1352 */         int m = (j + k) / 2;
/*      */         
/* 1354 */         if (paramInt < CharacterSetMetaData.m_maxCharWidth[m][0])
/*      */         {
/* 1356 */           k = m - 1;
/*      */         }
/* 1358 */         else if (paramInt > CharacterSetMetaData.m_maxCharWidth[m][0])
/*      */         {
/* 1360 */           j = m + 1;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1365 */           i = m;
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1371 */       if (i < 0)
/*      */       {
/* 1373 */         return 0;
/*      */       }
/* 1375 */       return CharacterSetMetaData.m_maxCharWidth[i][1] & 0xFF;
/*      */     }
/*      */   }
/*      */   
/*      */   static
/*      */   {
/*  114 */     language.put("", "AMERICAN");
/*  115 */     language.put("ar_EG", "EGYPTIAN");
/*  116 */     language.put("ar", "ARABIC");
/*  117 */     language.put("as", "ASSAMESE");
/*  118 */     language.put("bg", "BULGARIAN");
/*  119 */     language.put("bn", "BANGLA");
/*  120 */     language.put("ca", "CATALAN");
/*  121 */     language.put("cs", "CZECH");
/*  122 */     language.put("da", "DANISH");
/*  123 */     language.put("de", "GERMAN");
/*  124 */     language.put("el", "GREEK");
/*  125 */     language.put("en", "AMERICAN");
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  146 */     language.put("es_ES", "SPANISH");
/*  147 */     language.put("es_MX", "MEXICAN SPANISH");
/*  148 */     language.put("es", "LATIN AMERICAN SPANISH");
/*  149 */     language.put("et", "ESTONIAN");
/*  150 */     language.put("fi", "FINNISH");
/*  151 */     language.put("fr_CA", "CANADIAN FRENCH");
/*  152 */     language.put("fr", "FRENCH");
/*  153 */     language.put("ga", "IRISH");
/*  154 */     language.put("gu", "GUJARATI");
/*  155 */     language.put("he", "HEBREW");
/*  156 */     language.put("hi", "HINDI");
/*  157 */     language.put("hr", "CROATIAN");
/*  158 */     language.put("hu", "HUNGARIAN");
/*  159 */     language.put("id", "INDONESIAN");
/*  160 */     language.put("in", "INDONESIAN");
/*  161 */     language.put("is", "ICELANDIC");
/*  162 */     language.put("it", "ITALIAN");
/*  163 */     language.put("iw", "HEBREW");
/*  164 */     language.put("ja", "JAPANESE");
/*  165 */     language.put("kn", "KANNADA");
/*  166 */     language.put("ko", "KOREAN");
/*  167 */     language.put("kk", "CYRILLIC KAZAKH");
/*  168 */     language.put("kk_KZ", "CYRILLIC KAZAKH");
/*  169 */     language.put("lt", "LITHUANIAN");
/*  170 */     language.put("lv", "LATVIAN");
/*  171 */     language.put("mk", "MACEDONIAN");
/*  172 */     language.put("ml", "MALAYALAM");
/*  173 */     language.put("mr", "MARATHI");
/*  174 */     language.put("ms", "MALAY");
/*  175 */     language.put("nb", "NORWEGIAN");
/*  176 */     language.put("nl", "DUTCH");
/*  177 */     language.put("no", "NORWEGIAN");
/*  178 */     language.put("or", "ORIYA");
/*  179 */     language.put("pa", "PUNJABI");
/*  180 */     language.put("pl", "POLISH");
/*  181 */     language.put("pt_BR", "BRAZILIAN PORTUGUESE");
/*  182 */     language.put("pt", "PORTUGUESE");
/*  183 */     language.put("ro", "ROMANIAN");
/*  184 */     language.put("ru", "RUSSIAN");
/*  185 */     language.put("sk", "SLOVAK");
/*  186 */     language.put("sq", "ALBANIAN");
/*  187 */     language.put("sl", "SLOVENIAN");
/*  188 */     language.put("sr", "CYRILLIC SERBIAN");
/*  189 */     language.put("sh", "LATIN SERBIAN");
/*  190 */     language.put("sv", "SWEDISH");
/*  191 */     language.put("ta", "TAMIL");
/*  192 */     language.put("te", "TELUGU");
/*  193 */     language.put("th", "THAI");
/*  194 */     language.put("tr", "TURKISH");
/*  195 */     language.put("uk", "UKRAINIAN");
/*  196 */     language.put("vi", "VIETNAMESE");
/*  197 */     language.put("zh_HK", "TRADITIONAL CHINESE");
/*  198 */     language.put("zh_TW", "TRADITIONAL CHINESE");
/*  199 */     language.put("zh", "SIMPLIFIED CHINESE");
/*      */     
/*  201 */     territory.put("AE", "UNITED ARAB EMIRATES");
/*  202 */     territory.put("AL", "ALBANIA");
/*  203 */     territory.put("AT", "AUSTRIA");
/*  204 */     territory.put("AU", "AUSTRALIA");
/*  205 */     territory.put("BD", "BANGLADESH");
/*  206 */     territory.put("BE", "BELGIUM");
/*  207 */     territory.put("BG", "BULGARIA");
/*  208 */     territory.put("BH", "BAHRAIN");
/*  209 */     territory.put("BR", "BRAZIL");
/*  210 */     territory.put("CA", "CANADA");
/*  211 */     territory.put("CH", "SWITZERLAND");
/*  212 */     territory.put("CL", "CHILE");
/*  213 */     territory.put("CN", "CHINA");
/*  214 */     territory.put("CO", "COLOMBIA");
/*  215 */     territory.put("CR", "COSTA RICA");
/*  216 */     territory.put("CY", "CYPRUS");
/*  217 */     territory.put("CZ", "CZECH REPUBLIC");
/*  218 */     territory.put("DE", "GERMANY");
/*  219 */     territory.put("DJ", "DJIBOUTI");
/*  220 */     territory.put("DK", "DENMARK");
/*  221 */     territory.put("DZ", "ALGERIA");
/*  222 */     territory.put("EE", "ESTONIA");
/*  223 */     territory.put("EG", "EGYPT");
/*  224 */     territory.put("ES", "SPAIN");
/*  225 */     territory.put("ca_ES", "CATALONIA");
/*      */     
/*      */ 
/*  228 */     territory.put("FI", "FINLAND");
/*  229 */     territory.put("FR", "FRANCE");
/*  230 */     territory.put("GB", "UNITED KINGDOM");
/*  231 */     territory.put("GR", "GREECE");
/*  232 */     territory.put("GT", "GUATEMALA");
/*  233 */     territory.put("HK", "HONG KONG");
/*  234 */     territory.put("HR", "CROATIA");
/*  235 */     territory.put("HU", "HUNGARY");
/*  236 */     territory.put("ID", "INDONESIA");
/*  237 */     territory.put("IE", "IRELAND");
/*  238 */     territory.put("IL", "ISRAEL");
/*  239 */     territory.put("IN", "INDIA");
/*  240 */     territory.put("IQ", "IRAQ");
/*  241 */     territory.put("IS", "ICELAND");
/*  242 */     territory.put("IT", "ITALY");
/*  243 */     territory.put("JO", "JORDAN");
/*  244 */     territory.put("JP", "JAPAN");
/*  245 */     territory.put("KR", "KOREA");
/*  246 */     territory.put("KW", "KUWAIT");
/*  247 */     territory.put("LB", "LEBANON");
/*  248 */     territory.put("LT", "LITHUANIA");
/*  249 */     territory.put("LU", "LUXEMBOURG");
/*  250 */     territory.put("LV", "LATVIA");
/*  251 */     territory.put("LY", "LIBYA");
/*  252 */     territory.put("MA", "MOROCCO");
/*  253 */     territory.put("MK", "FYR MACEDONIA");
/*  254 */     territory.put("MR", "MAURITANIA");
/*  255 */     territory.put("MX", "MEXICO");
/*  256 */     territory.put("MY", "MALAYSIA");
/*  257 */     territory.put("NI", "NICARAGUA");
/*  258 */     territory.put("NL", "THE NETHERLANDS");
/*  259 */     territory.put("NO", "NORWAY");
/*  260 */     territory.put("NZ", "NEW ZEALAND");
/*  261 */     territory.put("OM", "OMAN");
/*  262 */     territory.put("PA", "PANAMA");
/*  263 */     territory.put("PE", "PERU");
/*  264 */     territory.put("PL", "POLAND");
/*  265 */     territory.put("PR", "PUERTO RICO");
/*  266 */     territory.put("PT", "PORTUGAL");
/*  267 */     territory.put("QA", "QATAR");
/*  268 */     territory.put("RO", "ROMANIA");
/*  269 */     territory.put("RU", "CIS");
/*  270 */     territory.put("SA", "SAUDI ARABIA");
/*  271 */     territory.put("SD", "SUDAN");
/*  272 */     territory.put("SE", "SWEDEN");
/*  273 */     territory.put("SG", "SINGAPORE");
/*  274 */     territory.put("SI", "SLOVENIA");
/*  275 */     territory.put("SK", "SLOVAKIA");
/*  276 */     territory.put("SO", "SOMALIA");
/*  277 */     territory.put("SV", "EL SALVADOR");
/*  278 */     territory.put("SY", "SYRIA");
/*  279 */     territory.put("TH", "THAILAND");
/*  280 */     territory.put("TN", "TUNISIA");
/*  281 */     territory.put("TR", "TURKEY");
/*  282 */     territory.put("TW", "TAIWAN");
/*  283 */     territory.put("UA", "UKRAINE");
/*  284 */     territory.put("US", "AMERICA");
/*  285 */     territory.put("VE", "VENEZUELA");
/*  286 */     territory.put("VN", "VIETNAM");
/*  287 */     territory.put("YE", "YEMEN");
/*  288 */     territory.put("ZA", "SOUTH AFRICA");
/*      */     
/*  290 */     territory.put("ar", "SAUDI ARABIA");
/*  291 */     territory.put("as", "INDIA");
/*  292 */     territory.put("bg", "BULGARIA");
/*  293 */     territory.put("bn", "BANGLADESH");
/*  294 */     territory.put("ca", "CATALONIA");
/*  295 */     territory.put("cs", "CZECH REPUBLIC");
/*  296 */     territory.put("da", "DENMARK");
/*  297 */     territory.put("de", "GERMANY");
/*  298 */     territory.put("el", "GREECE");
/*  299 */     territory.put("en", "AMERICA");
/*  300 */     territory.put("es", "AMERICA");
/*  301 */     territory.put("et", "ESTONIA");
/*  302 */     territory.put("fi", "FINLAND");
/*  303 */     territory.put("fr", "FRANCE");
/*  304 */     territory.put("gu", "INDIA");
/*  305 */     territory.put("he", "ISRAEL");
/*  306 */     territory.put("hi", "INDIA");
/*  307 */     territory.put("hr", "CROATIA");
/*  308 */     territory.put("hu", "HUNGARY");
/*  309 */     territory.put("id", "INDONESIA");
/*  310 */     territory.put("in", "INDONESIA");
/*  311 */     territory.put("is", "ICELAND");
/*  312 */     territory.put("it", "ITALY");
/*  313 */     territory.put("iw", "ISRAEL");
/*  314 */     territory.put("ja", "JAPAN");
/*  315 */     territory.put("kn", "INDIA");
/*  316 */     territory.put("ko", "KOREA");
/*  317 */     territory.put("kk", "KAZAKHSTAN");
/*  318 */     territory.put("kk_KZ", "KAZAKHSTAN");
/*  319 */     territory.put("lt", "LITHUANIA");
/*  320 */     territory.put("lv", "LATVIA");
/*  321 */     territory.put("mk", "FYR MACEDONIA");
/*  322 */     territory.put("ml", "INDIA");
/*  323 */     territory.put("mr", "INDIA");
/*  324 */     territory.put("ms", "MALAYSIA");
/*  325 */     territory.put("nl", "THE NETHERLANDS");
/*  326 */     territory.put("no", "NORWAY");
/*  327 */     territory.put("or", "INDIA");
/*  328 */     territory.put("pa", "INDIA");
/*  329 */     territory.put("pl", "POLAND");
/*  330 */     territory.put("pt", "PORTUGAL");
/*  331 */     territory.put("ro", "ROMANIA");
/*  332 */     territory.put("ru", "CIS");
/*  333 */     territory.put("sk", "SLOVAKIA");
/*  334 */     territory.put("sl", "SLOVENIA");
/*  335 */     territory.put("sq", "ALBANIA");
/*  336 */     territory.put("sr", "SERBIA AND MONTENEGRO");
/*  337 */     territory.put("sh", "SERBIA AND MONTENEGRO");
/*  338 */     territory.put("sv", "SWEDEN");
/*  339 */     territory.put("ta", "INDIA");
/*  340 */     territory.put("te", "INDIA");
/*  341 */     territory.put("th", "THAILAND");
/*  342 */     territory.put("tr", "TURKEY");
/*  343 */     territory.put("uk", "UKRAINE");
/*  344 */     territory.put("vi", "VIETNAM");
/*  345 */     territory.put("zh", "CHINA");
/*      */     
/*      */ 
/*  348 */     metaDataImpl = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  356 */     Orai18nCharacterSetMetaData localOrai18nCharacterSetMetaData = null;
/*      */     
/*      */     try
/*      */     {
/*  360 */       Class.forName("oracle.i18n.text.OraBoot");
/*  361 */       localOrai18nCharacterSetMetaData = new Orai18nCharacterSetMetaData();
/*      */     }
/*      */     catch (ClassNotFoundException localClassNotFoundException) {}
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  369 */     if ((localOrai18nCharacterSetMetaData != null) && (localOrai18nCharacterSetMetaData.oraBoot != null)) {
/*  370 */       metaDataImpl = localOrai18nCharacterSetMetaData;
/*      */     } else {
/*  372 */       metaDataImpl = new JdbcCharacterSetMetaData();
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
/*  385 */     m_maxCharWidth = new short[][] { { 1, 1 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 5, 1 }, { 6, 1 }, { 7, 1 }, { 8, 1 }, { 9, 1 }, { 10, 1 }, { 11, 1 }, { 12, 1 }, { 13, 1 }, { 14, 1 }, { 15, 1 }, { 16, 1 }, { 17, 1 }, { 18, 1 }, { 19, 1 }, { 20, 1 }, { 21, 1 }, { 22, 1 }, { 23, 1 }, { 25, 1 }, { 27, 1 }, { 28, 1 }, { 31, 1 }, { 32, 1 }, { 33, 1 }, { 34, 1 }, { 35, 1 }, { 36, 1 }, { 37, 1 }, { 38, 1 }, { 39, 1 }, { 40, 1 }, { 41, 1 }, { 42, 1 }, { 43, 1 }, { 44, 1 }, { 45, 1 }, { 46, 1 }, { 47, 1 }, { 48, 1 }, { 49, 1 }, { 50, 1 }, { 51, 1 }, { 61, 1 }, { 70, 1 }, { 72, 1 }, { 81, 1 }, { 82, 1 }, { 90, 1 }, { 91, 1 }, { 92, 1 }, { 93, 1 }, { 94, 1 }, { 95, 1 }, { 96, 1 }, { 97, 1 }, { 98, 1 }, { 99, 1 }, { 100, 1 }, { 101, 1 }, { 110, 1 }, { 113, 1 }, { 114, 1 }, { 140, 1 }, { 150, 1 }, { 152, 1 }, { 153, 1 }, { 154, 1 }, { 155, 1 }, { 156, 1 }, { 158, 1 }, { 159, 1 }, { 160, 1 }, { 161, 1 }, { 162, 1 }, { 163, 1 }, { 164, 1 }, { 165, 1 }, { 166, 1 }, { 167, 1 }, { 170, 1 }, { 171, 1 }, { 172, 1 }, { 173, 1 }, { 174, 1 }, { 175, 1 }, { 176, 1 }, { 177, 1 }, { 178, 1 }, { 179, 1 }, { 180, 1 }, { 181, 1 }, { 182, 1 }, { 183, 1 }, { 184, 1 }, { 185, 1 }, { 186, 1 }, { 187, 1 }, { 188, 1 }, { 189, 1 }, { 190, 1 }, { 191, 1 }, { 192, 1 }, { 193, 1 }, { 194, 1 }, { 195, 1 }, { 196, 1 }, { 197, 1 }, { 198, 1 }, { 199, 1 }, { 200, 1 }, { 201, 1 }, { 202, 1 }, { 203, 1 }, { 204, 1 }, { 205, 1 }, { 206, 1 }, { 207, 1 }, { 208, 1 }, { 210, 1 }, { 211, 1 }, { 221, 1 }, { 222, 1 }, { 223, 1 }, { 224, 1 }, { 225, 1 }, { 226, 1 }, { 230, 1 }, { 231, 1 }, { 232, 1 }, { 233, 1 }, { 235, 1 }, { 239, 1 }, { 241, 1 }, { 251, 1 }, { 261, 1 }, { 262, 1 }, { 263, 1 }, { 264, 1 }, { 265, 1 }, { 266, 1 }, { 267, 1 }, { 277, 1 }, { 278, 1 }, { 279, 1 }, { 301, 1 }, { 311, 1 }, { 312, 1 }, { 314, 1 }, { 315, 1 }, { 316, 1 }, { 317, 1 }, { 319, 1 }, { 320, 1 }, { 322, 1 }, { 323, 1 }, { 324, 1 }, { 351, 1 }, { 352, 1 }, { 353, 1 }, { 354, 1 }, { 368, 1 }, { 380, 1 }, { 381, 1 }, { 382, 1 }, { 383, 1 }, { 384, 1 }, { 385, 1 }, { 386, 1 }, { 390, 1 }, { 401, 1 }, { 500, 1 }, { 504, 1 }, { 505, 1 }, { 506, 1 }, { 507, 1 }, { 508, 1 }, { 509, 1 }, { 511, 1 }, { 514, 1 }, { 554, 1 }, { 555, 1 }, { 556, 1 }, { 557, 1 }, { 558, 1 }, { 559, 1 }, { 560, 1 }, { 561, 1 }, { 563, 1 }, { 565, 1 }, { 566, 1 }, { 567, 1 }, { 590, 1 }, { 798, 1 }, { 799, 258 }, { 829, 2 }, { 830, 3 }, { 831, 3 }, { 832, 2 }, { 833, 3 }, { 834, 2 }, { 835, 3 }, { 836, 2 }, { 837, 3 }, { 838, 2 }, { 840, 2 }, { 842, 3 }, { 845, 2 }, { 846, 2 }, { 850, 2 }, { 851, 2 }, { 852, 2 }, { 853, 3 }, { 854, 4 }, { 860, 4 }, { 861, 4 }, { 862, 2 }, { 863, 4 }, { 864, 3 }, { 865, 2 }, { 866, 2 }, { 867, 2 }, { 868, 2 }, { 870, 3 }, { 871, 3 }, { 872, 4 }, { 873, 4 }, { 992, 2 }, { 994, 2 }, { 995, 2 }, { 996, 3 }, { 997, 2 }, { 998, 3 }, { 1001, 258 }, { 1830, 258 }, { 1832, 258 }, { 1833, 258 }, { 1840, 258 }, { 1842, 258 }, { 1850, 258 }, { 1852, 258 }, { 1853, 258 }, { 1860, 258 }, { 1863, 260 }, { 1864, 258 }, { 1865, 258 }, { 2000, 258 }, { 2002, 258 }, { 9996, 3 }, { 9997, 3 }, { 9998, 3 }, { 9999, 3 } };
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1381 */     _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1387 */     getMapProperties();
/*      */   }
/*      */ }
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/sql/converter/CharacterSetMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */