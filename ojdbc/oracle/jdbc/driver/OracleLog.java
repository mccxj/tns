package oracle.jdbc.driver;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
public class OracleLog
{
  private static final int maxPrintBytes = 512;
  public static final boolean TRACE = false;
  
  public static boolean isDebugZip()
  {
/*  71 */     boolean bool = true;
    
/*  74 */     bool = false;
/*  75 */     return bool;
  }
  
  public static boolean isPrivateLogAvailable()
  {
/*  90 */     boolean bool = false;
    
/*  94 */     return bool;
  }
  
  public static boolean isEnabled()
  {
/* 104 */     return false;
  }
  
  public static boolean registerClassNameAndGetCurrentTraceSetting(Class paramClass)
  {
/* 115 */     return false;
  }
  
  public static void setupFromSystemProperties()
  {
/* 150 */     boolean bool = false;
/* 151 */     securityExceptionWhileGettingSystemProperties = false;
    try
    {
/* 154 */       String str = null;
/* 155 */       str = getSystemProperty("oracle.jdbc.Trace", null);
/* 156 */       if ((str != null) && (str.compareTo("true") == 0)) {
/* 157 */         bool = true;
      }
    }
    catch (SecurityException localSecurityException) {
/* 161 */       securityExceptionWhileGettingSystemProperties = true;
    }
/* 163 */     setTrace(bool);
  }
  
  private static String getSystemProperty(String paramString)
  {
/* 180 */     return getSystemProperty(paramString, null);
  }
  
  private static String getSystemProperty(String paramString1, String paramString2)
  {
/* 188 */     if (paramString1 != null)
    {
/* 190 */       final String str1 = paramString1;
/* 191 */       final String str2 = paramString2;
/* 192 */       String[] arrayOfString = { paramString2 };
/* 193 */       AccessController.doPrivileged(new PrivilegedAction()
      {
        public Object run()
        {
/* 197 */           this.val$retStr[0] = System.getProperty(str1, str2);
/* 198 */           return null;
        }
/* 200 */       });
/* 201 */       return arrayOfString[0];
    }
/* 203 */     return paramString2;
  }
  
  public static String argument()
  {
/* 213 */     return "";
  }
  
  public static String argument(boolean paramBoolean) {
/* 217 */     return Boolean.toString(paramBoolean);
  }
  
  public static String argument(byte paramByte) {
/* 221 */     return Byte.toString(paramByte);
  }
  
  public static String argument(short paramShort) {
/* 225 */     return Short.toString(paramShort);
  }
  
  public static String argument(int paramInt) {
/* 229 */     return Integer.toString(paramInt);
  }
  
  public static String argument(long paramLong) {
/* 233 */     return Long.toString(paramLong);
  }
  
  public static String argument(float paramFloat) {
/* 237 */     return Float.toString(paramFloat);
  }
  
  public static String argument(double paramDouble) {
/* 241 */     return Double.toString(paramDouble);
  }
  
  public static String argument(Object paramObject) {
/* 245 */     if (paramObject == null) return "null";
/* 246 */     if ((paramObject instanceof String)) return "\"" + (String)paramObject + "\"";
/* 247 */     return paramObject.toString();
  }
  
  /**
   * @deprecated
   */
  public static String byteToHexString(byte paramByte)
  {
/* 269 */     StringBuffer localStringBuffer = new StringBuffer("");
/* 270 */     int i = 0xFF & paramByte;
    
/* 272 */     if (i <= 15) {
/* 273 */       localStringBuffer.append("0x0");
    } else {
/* 275 */       localStringBuffer.append("0x");
    }
/* 277 */     localStringBuffer.append(Integer.toHexString(i));
    
/* 279 */     return localStringBuffer.toString();
  }
  
  /**
   * @deprecated
   */
  public static String bytesToPrintableForm(String paramString, byte[] paramArrayOfByte)
  {
/* 300 */     int i = paramArrayOfByte == null ? 0 : paramArrayOfByte.length;
    
/* 302 */     return bytesToPrintableForm(paramString, paramArrayOfByte, i);
  }
  
  /**
   * @deprecated
   */
  public static String bytesToPrintableForm(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
/* 326 */     String str = null;
    
/* 328 */     if (paramArrayOfByte == null) {
/* 329 */       str = paramString + ": null";
    } else {
/* 331 */       str = paramString + " (" + paramArrayOfByte.length + " bytes):\n" + bytesToFormattedStr(paramArrayOfByte, paramInt, "  ");
    }
    
/* 334 */     return str;
  }
  
  /**
   * @deprecated
   */
  public static String bytesToFormattedStr(byte[] paramArrayOfByte, int paramInt, String paramString)
  {
/* 361 */     StringBuffer localStringBuffer = new StringBuffer("");
    
/* 363 */     if (paramString == null) {
/* 364 */       paramString = new String("");
    }
/* 366 */     localStringBuffer.append(paramString);
    
/* 368 */     if (paramArrayOfByte == null)
    {
/* 370 */       localStringBuffer.append("byte [] is null");
      
/* 372 */       return localStringBuffer.toString();
    }
    
/* 375 */     for (int i = 0; i < paramInt; i++)
    {
/* 377 */       if (i >= 512)
      {
/* 379 */         localStringBuffer.append("\n" + paramString + "... last " + (paramInt - 512) + " bytes were not printed to limit the output size");
        
/* 382 */         break;
      }
      
/* 385 */       if ((i > 0) && (i % 20 == 0)) {
/* 386 */         localStringBuffer.append("\n" + paramString);
      }
/* 388 */       if (i % 20 == 10) {
/* 389 */         localStringBuffer.append(" ");
      }
/* 391 */       int j = 0xFF & paramArrayOfByte[i];
      
/* 393 */       if (j <= 15) {
/* 394 */         localStringBuffer.append("0");
      }
/* 396 */       localStringBuffer.append(Integer.toHexString(j) + " ");
    }
    
/* 399 */     return localStringBuffer.toString();
  }
  
  /**
   * @deprecated
   */
  public static byte[] strToUcs2Bytes(String paramString)
  {
/* 416 */     if (paramString == null) {
/* 417 */       return null;
    }
/* 419 */     return charsToUcs2Bytes(paramString.toCharArray());
  }
  
  /**
   * @deprecated
   */
  public static byte[] charsToUcs2Bytes(char[] paramArrayOfChar)
  {
/* 436 */     if (paramArrayOfChar == null) {
/* 437 */       return null;
    }
/* 439 */     return charsToUcs2Bytes(paramArrayOfChar, paramArrayOfChar.length);
  }
  
  /**
   * @deprecated
   */
  public static byte[] charsToUcs2Bytes(char[] paramArrayOfChar, int paramInt)
  {
/* 458 */     if (paramArrayOfChar == null) {
/* 459 */       return null;
    }
/* 461 */     if (paramInt < 0) {
/* 462 */       return null;
    }
/* 464 */     return charsToUcs2Bytes(paramArrayOfChar, paramInt, 0);
  }
  
  /**
   * @deprecated
   */
  public static byte[] charsToUcs2Bytes(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
/* 476 */     if (paramArrayOfChar == null) {
/* 477 */       return null;
    }
/* 479 */     if (paramInt1 > paramArrayOfChar.length - paramInt2) {
/* 480 */       paramInt1 = paramArrayOfChar.length - paramInt2;
    }
/* 482 */     if (paramInt1 < 0) {
/* 483 */       return null;
    }
/* 485 */     byte[] arrayOfByte = new byte[2 * paramInt1];
    
/* 487 */     int j = paramInt2; for (int i = 0; j < paramInt1; j++)
    {
/* 489 */       arrayOfByte[(i++)] = ((byte)(paramArrayOfChar[j] >> '\b' & 0xFF));
/* 490 */       arrayOfByte[(i++)] = ((byte)(paramArrayOfChar[j] & 0xFF));
    }
    
/* 493 */     return arrayOfByte;
  }
  
  /**
   * @deprecated
   */
  public static String toPrintableStr(String paramString, int paramInt)
  {
/* 511 */     if (paramString == null)
    {
/* 513 */       return "null";
    }
    
/* 516 */     if (paramString.length() > paramInt)
    {
/* 518 */       return paramString.substring(0, paramInt - 1) + "\n ... the actual length was " + paramString.length();
    }
    
/* 522 */     return paramString;
  }
  
/* 534 */   public static final Level INTERNAL_ERROR = OracleLevel.INTERNAL_ERROR;
/* 535 */   public static final Level TRACE_1 = OracleLevel.TRACE_1;
/* 536 */   public static final Level TRACE_10 = OracleLevel.TRACE_10;
/* 537 */   public static final Level TRACE_16 = OracleLevel.TRACE_16;
/* 538 */   public static final Level TRACE_20 = OracleLevel.TRACE_20;
/* 539 */   public static final Level TRACE_30 = OracleLevel.TRACE_30;
/* 540 */   public static final Level TRACE_32 = OracleLevel.TRACE_32;
  
  static boolean securityExceptionWhileGettingSystemProperties;
  
  static
  {
/* 549 */     initialize();
  }
  
  public static String toHex(long paramLong, int paramInt)
  {
    String str;
    
/* 558 */     switch (paramInt)
    {
    case 1: 
/* 561 */       str = "00" + Long.toString(paramLong & 0xFF, 16);
/* 562 */       break;
    case 2: 
/* 564 */       str = "0000" + Long.toString(paramLong & 0xFFFF, 16);
/* 565 */       break;
    case 3: 
/* 567 */       str = "000000" + Long.toString(paramLong & 0xFFFFFF, 16);
/* 568 */       break;
    case 4: 
/* 570 */       str = "00000000" + Long.toString(paramLong & 0xFFFFFFFF, 16);
/* 571 */       break;
    case 5: 
/* 573 */       str = "0000000000" + Long.toString(paramLong & 0xFFFFFFFFFF, 16);
/* 574 */       break;
    case 6: 
/* 576 */       str = "000000000000" + Long.toString(paramLong & 0xFFFFFFFFFFFF, 16);
/* 577 */       break;
    case 7: 
/* 579 */       str = "00000000000000" + Long.toString(paramLong & 0xFFFFFFFFFFFFFF, 16);
      
/* 581 */       break;
    case 8: 
/* 583 */       return toHex(paramLong >> 32, 4) + toHex(paramLong, 4).substring(2);
    
    default: 
/* 586 */       return "more than 8 bytes";
    }
/* 588 */     return "0x" + str.substring(str.length() - 2 * paramInt);
  }
  
  public static String toHex(byte paramByte)
  {
/* 593 */     String str = "00" + Integer.toHexString(paramByte & 0xFF);
/* 594 */     return "0x" + str.substring(str.length() - 2);
  }
  
  public static String toHex(short paramShort)
  {
/* 599 */     return toHex(paramShort, 2);
  }
  
  public static String toHex(int paramInt)
  {
/* 604 */     return toHex(paramInt, 4);
  }
  
  public static String toHex(byte[] paramArrayOfByte, int paramInt)
  {
/* 609 */     if (paramArrayOfByte == null)
/* 610 */       return "null";
/* 611 */     if (paramInt > paramArrayOfByte.length)
/* 612 */       return "byte array not long enough";
/* 613 */     String str = "[";
/* 614 */     int i = Math.min(64, paramInt);
/* 615 */     for (int j = 0; j < i; j++)
    {
/* 617 */       str = str + toHex(paramArrayOfByte[j]) + " ";
    }
/* 619 */     if (i < paramInt)
/* 620 */       str = str + "...";
/* 621 */     return str + "]";
  }
  
  public static String toHex(byte[] paramArrayOfByte)
  {
/* 626 */     if (paramArrayOfByte == null)
/* 627 */       return "null";
/* 628 */     return toHex(paramArrayOfByte, paramArrayOfByte.length);
  }
  
  public static void setTrace(boolean paramBoolean) {}
  
  private static void initialize() {}
  
  private static class OracleLevel extends Level {
/* 636 */     static final OracleLevel INTERNAL_ERROR = new OracleLevel("INTERNAL_ERROR", 1100);
/* 637 */     static final OracleLevel TRACE_1 = new OracleLevel("TRACE_1", Level.FINE.intValue());
/* 638 */     static final OracleLevel TRACE_10 = new OracleLevel("TRACE_10", 446);
/* 639 */     static final OracleLevel TRACE_16 = new OracleLevel("TRACE_16", Level.FINER.intValue());
/* 640 */     static final OracleLevel TRACE_20 = new OracleLevel("TRACE_20", 376);
/* 641 */     static final OracleLevel TRACE_30 = new OracleLevel("TRACE_30", 316);
/* 642 */     static final OracleLevel TRACE_32 = new OracleLevel("TRACE_32", Level.FINEST.intValue());
    
    OracleLevel(String paramString, int paramInt) {
/* 645 */       super(paramInt);
    }
  }
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/OracleLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */