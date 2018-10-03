package oracle.jdbc.driver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLNonTransientException;
import java.sql.SQLRecoverableException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.SQLTransactionRollbackException;
import java.sql.SQLTransientConnectionException;
import java.sql.SQLTransientException;
import java.util.ArrayList;
import java.util.List;
class SQLStateMapping
{
  public static final int SQLEXCEPTION = 0;
  public static final int SQLNONTRANSIENTEXCEPTION = 1;
  public static final int SQLTRANSIENTEXCEPTION = 2;
  public static final int SQLDATAEXCEPTION = 3;
  public static final int SQLFEATURENOTSUPPORTEDEXCEPTION = 4;
  public static final int SQLINTEGRITYCONSTRAINTVIOLATIONEXCEPTION = 5;
  public static final int SQLINVALIDAUTHORIZATIONSPECEXCEPTION = 6;
  public static final int SQLNONTRANSIENTCONNECTIONEXCEPTION = 7;
  public static final int SQLSYNTAXERROREXCEPTION = 8;
  public static final int SQLTIMEOUTEXCEPTION = 9;
  public static final int SQLTRANSACTIONROLLBACKEXCEPTION = 10;
  public static final int SQLTRANSIENTCONNECTIONEXCEPTION = 11;
  public static final int SQLCLIENTINFOEXCEPTION = 12;
  public static final int SQLRECOVERABLEEXCEPTION = 13;
  int low;
  int high;
  public String sqlState;
  public int exception;
  static final String mappingResource = "errorMap.xml";
  static SQLStateMapping[] all;
  private static final int NUMEBER_OF_MAPPINGS_IN_ERRORMAP_XML = 128;
  
  public SQLStateMapping(int paramInt1, int paramInt2, String paramString, int paramInt3)
  {
/*  54 */     this.low = paramInt1;
/*  55 */     this.sqlState = paramString;
/*  56 */     this.exception = paramInt3;
/*  57 */     this.high = paramInt2;
  }
  
  public boolean isIncluded(int paramInt)
  {
/*  63 */     return (this.low <= paramInt) && (paramInt <= this.high);
  }
  
  public SQLException newSQLException(String paramString, int paramInt)
  {
/*  69 */     switch (this.exception) {
    case 0: 
/*  71 */       return new SQLException(paramString, this.sqlState, paramInt);
    
    case 1: 
/*  76 */       return new SQLNonTransientException(paramString, this.sqlState, paramInt);
    case 2: 
/*  78 */       return new SQLTransientException(paramString, this.sqlState, paramInt);
    case 3: 
/*  80 */       return new SQLDataException(paramString, this.sqlState, paramInt);
    case 4: 
/*  82 */       return new SQLFeatureNotSupportedException(paramString, this.sqlState, paramInt);
    case 5: 
/*  84 */       return new SQLIntegrityConstraintViolationException(paramString, this.sqlState, paramInt);
    case 6: 
/*  86 */       return new SQLInvalidAuthorizationSpecException(paramString, this.sqlState, paramInt);
    case 7: 
/*  88 */       return new SQLNonTransientConnectionException(paramString, this.sqlState, paramInt);
    case 8: 
/*  90 */       return new SQLSyntaxErrorException(paramString, this.sqlState, paramInt);
    case 9: 
/*  92 */       return new SQLTimeoutException(paramString, this.sqlState, paramInt);
    case 10: 
/*  94 */       return new SQLTransactionRollbackException(paramString, this.sqlState, paramInt);
    case 11: 
/*  96 */       return new SQLTransientConnectionException(paramString, this.sqlState, paramInt);
    case 12: 
/*  98 */       return new SQLClientInfoException(paramString, this.sqlState, paramInt, null);
    case 13: 
/* 100 */       return new SQLRecoverableException(paramString, this.sqlState, paramInt);
    }
/* 102 */     return new SQLException(paramString, this.sqlState, paramInt);
  }
  
  boolean lessThan(SQLStateMapping paramSQLStateMapping)
  {
/* 108 */     if (this.low < paramSQLStateMapping.low) {
/* 109 */       return this.high < paramSQLStateMapping.high;
    }
    
/* 112 */     return this.high <= paramSQLStateMapping.high;
  }
  
  public String toString()
  {
/* 117 */     return super.toString() + "(" + this.low + ", " + this.high + ", " + this.sqlState + ", " + this.exception + ")";
  }
  
  public static void main(String[] paramArrayOfString)
    throws IOException
  {
/* 124 */     SQLStateMapping[] arrayOfSQLStateMapping = doGetMappings();
/* 125 */     System.out.println("a\t" + arrayOfSQLStateMapping);
/* 126 */     for (int i = 0; i < arrayOfSQLStateMapping.length; i++) {
/* 127 */       System.out.println("low:\t" + arrayOfSQLStateMapping[i].low + "\thigh:\t" + arrayOfSQLStateMapping[i].high + "\tsqlState:\t" + arrayOfSQLStateMapping[i].sqlState + "\tsqlException:\t" + arrayOfSQLStateMapping[i].exception);
    }
  }
  
  static SQLStateMapping[] getMappings()
  {
/* 137 */     if (all == null) {
      try {
/* 139 */         all = doGetMappings();
      }
      catch (Throwable localThrowable)
      {
/* 143 */         all = new SQLStateMapping[0];
      }
    }
/* 146 */     return all;
  }
  
  static SQLStateMapping[] doGetMappings()
    throws IOException
  {
/* 154 */     InputStream localInputStream = SQLStateMapping.class.getResourceAsStream("errorMap.xml");
/* 155 */     ArrayList localArrayList = new ArrayList(128);
/* 156 */     load(localInputStream, localArrayList);
/* 157 */     return (SQLStateMapping[])localArrayList.toArray(new SQLStateMapping[0]);
  }
  
  static void load(InputStream paramInputStream, List paramList)
    throws IOException
  {
/* 179 */     BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramInputStream));
/* 180 */     Tokenizer localTokenizer = new Tokenizer(localBufferedReader);
/* 181 */     int i = -1;
/* 182 */     int j = -1;
/* 183 */     Object localObject1 = null;
/* 184 */     int k = -1;
/* 185 */     Object localObject2 = null;
/* 186 */     int m = 0;
    String str;
/* 188 */     while ((str = localTokenizer.next()) != null) {
/* 189 */       switch (m) {
      case 0: 
/* 191 */         if (str.equals("<")) m = 1;
        break;
      case 1: 
/* 194 */         if (str.equals("!")) { m = 2;
/* 195 */         } else if (str.equals("oraErrorSqlStateSqlExceptionMapping")) m = 6; else {
/* 196 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"oraErrorSqlStateSqlExceptionMapping\".");
        }
        break;
      case 2: 
/* 200 */         if (str.equals("-")) m = 3; else {
/* 201 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"-\".");
        }
        break;
      case 3: 
/* 205 */         if (str.equals("-")) m = 4;
        break;
      case 4: 
/* 208 */         if (str.equals("-")) m = 5; else
/* 209 */           m = 3;
/* 210 */         break;
      case 5: 
/* 212 */         if (str.equals(">")) m = 1; else {
/* 213 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \">\".");
        }
        break;
      case 6: 
/* 217 */         if (str.equals(">")) m = 7; else {
/* 218 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \">\".");
        }
        break;
      case 7: 
/* 222 */         if (str.equals("<")) m = 8; else {
/* 223 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"<\".");
        }
        break;
      case 8: 
/* 227 */         if (str.equals("!")) { m = 9;
/* 228 */         } else if (str.equals("error")) { m = 14;
/* 229 */         } else if (str.equals("/")) m = 16; else {
/* 230 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected one of \"!--\", \"error\", \"/\".");
        }
        break;
      case 9: 
/* 234 */         if (str.equals("-")) m = 10; else {
/* 235 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"-\".");
        }
        break;
      case 10: 
/* 239 */         if (str.equals("-")) m = 11; else {
/* 240 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"-\".");
        }
        break;
      case 11: 
/* 244 */         if (str.equals("-")) m = 12;
        break;
      case 12: 
/* 247 */         if (str.equals("-")) m = 13; else
/* 248 */           m = 11;
/* 249 */         break;
      case 13: 
/* 251 */         if (str.equals(">")) m = 7; else {
/* 252 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \">\".");
        }
        break;
      case 14: 
/* 256 */         if (str.equals("/")) { m = 15;
/* 257 */         } else if (str.equals("oraErrorFrom")) { m = 19;
/* 258 */         } else if (str.equals("oraErrorTo")) { m = 21;
/* 259 */         } else if (str.equals("sqlState")) { m = 23;
/* 260 */         } else if (str.equals("sqlException")) { m = 25;
/* 261 */         } else if (str.equals("comment")) m = 27; else {
/* 262 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected one of " + "\"oraErrorFrom\", \"oraErrorTo\", \"sqlState\", " + "\"sqlException\", \"comment\", \"/\".");
        }
        
        break;
      case 15: 
/* 268 */         if (str.equals(">")) {
          try {
/* 270 */             createOne(paramList, i, j, (String)localObject1, k, (String)localObject2);
          }
          catch (IOException localIOException) {
/* 273 */             throw new IOException("Invalid error element at line " + localTokenizer.lineno + " of errorMap.xml. " + localIOException.getMessage());
          }
          
/* 276 */           i = -1;
/* 277 */           j = -1;
/* 278 */           localObject1 = null;
/* 279 */           k = -1;
/* 280 */           localObject2 = null;
/* 281 */           m = 7;
        } else {
/* 283 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \">\".");
        }
        break;
      case 16: 
/* 287 */         if (str.equals("oraErrorSqlStateSqlExceptionMapping")) m = 17; else {
/* 288 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"oraErrorSqlStateSqlExceptionMapping\".");
        }
        break;
      case 17: 
/* 292 */         if (str.equals(">")) m = 18; else {
/* 293 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \">\".");
        }
        break;
      case 18: 
        break;
      case 19: 
/* 299 */         if (str.equals("=")) m = 20; else {
/* 300 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"=\".");
        }
        break;
      case 20: 
        try {
/* 305 */           i = Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException1) {
/* 308 */           throw new IOException("Unexpected value \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected an integer.");
        }
        
/* 311 */         m = 14;
/* 312 */         break;
      case 21: 
/* 314 */         if (str.equals("=")) m = 22; else {
/* 315 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"=\".");
        }
        break;
      case 22: 
        try {
/* 320 */           j = Integer.parseInt(str);
        }
        catch (NumberFormatException localNumberFormatException2) {
/* 323 */           throw new IOException("Unexpected value \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected an integer.");
        }
        
/* 326 */         m = 14;
/* 327 */         break;
      case 23: 
/* 329 */         if (str.equals("=")) m = 24; else {
/* 330 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"=\".");
        }
        break;
      case 24: 
/* 334 */         localObject1 = str;
/* 335 */         m = 14;
/* 336 */         break;
      case 25: 
/* 338 */         if (str.equals("=")) m = 26; else {
/* 339 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"=\".");
        }
        break;
      case 26: 
        try {
/* 344 */           k = valueOf(str);
        }
        catch (Exception localException) {
/* 347 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected SQLException" + " subclass name.");
        }
        
/* 351 */         m = 14;
/* 352 */         break;
      case 27: 
/* 354 */         if (str.equals("=")) m = 28; else {
/* 355 */           throw new IOException("Unexpected token \"" + str + "\" at line " + localTokenizer.lineno + " of errorMap.xml. Expected \"=\".");
        }
        break;
      case 28: 
/* 359 */         localObject2 = str;
/* 360 */         m = 14;
/* 361 */         break;
      default: 
/* 363 */         throw new IOException("Unknown parser state " + m + " at line " + localTokenizer.lineno + " of errorMap.xml.");
      }
      
    }
  }
  
  private static final class Tokenizer
  {
/* 381 */     int lineno = 1;
    Reader r;
    int c;
    
    Tokenizer(Reader paramReader) throws IOException {
/* 386 */       this.r = paramReader;
/* 387 */       this.c = paramReader.read();
    }
    
    String next() throws IOException {
/* 391 */       StringBuffer localStringBuffer = new StringBuffer(16);
/* 392 */       int i = 1;
      
/* 394 */       while (this.c != -1) {
/* 395 */         if (this.c == 10) this.lineno += 1;
/* 396 */         if ((this.c <= 32) && (i != 0)) {
/* 397 */           this.c = this.r.read();
        }
/* 400 */         else if ((this.c <= 32) && (i == 0)) {
/* 401 */           this.c = this.r.read();
        }
/* 404 */         else if (this.c == 34) {
/* 405 */           while ((this.c = this.r.read()) != 34) localStringBuffer.append((char)this.c);
/* 406 */           this.c = this.r.read();
        }
/* 409 */         else if (((48 <= this.c) && (this.c <= 57)) || ((65 <= this.c) && (this.c <= 90)) || ((97 <= this.c) && (this.c <= 122)) || (this.c == 95))
        {
          do
          {
/* 414 */             localStringBuffer.append((char)this.c);
          }
/* 418 */           while (((48 <= (this.c = this.r.read())) && (this.c <= 57)) || ((65 <= this.c) && (this.c <= 90)) || ((97 <= this.c) && (this.c <= 122)) || (this.c == 95));
        }
        else {
/* 421 */           localStringBuffer.append((char)this.c);
/* 422 */           this.c = this.r.read();
        }
      }
/* 425 */       if (localStringBuffer.length() > 0) return localStringBuffer.toString();
/* 426 */       return null;
    }
  }
  
  private static void createOne(List paramList, int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2) throws IOException
  {
/* 432 */     if (paramInt1 == -1) throw new IOException("oraErrorFrom is a required attribute");
/* 433 */     if (paramInt2 == -1) paramInt2 = paramInt1;
/* 434 */     if ((paramString1 == null) || (paramString1.length() == 0)) throw new IOException("sqlState is a required attribute");
/* 435 */     if (paramInt3 == -1) throw new IOException("sqlException is a required attribute");
/* 436 */     if ((paramString2 == null) || (paramString2.length() < 8)) throw new IOException("a lengthy comment in required");
/* 437 */     SQLStateMapping localSQLStateMapping = new SQLStateMapping(paramInt1, paramInt2, paramString1, paramInt3);
/* 438 */     add(paramList, localSQLStateMapping);
  }
  
  static void add(List paramList, SQLStateMapping paramSQLStateMapping) {
/* 442 */     for (int i = paramList.size(); 
/* 443 */         i > 0; i--) {
/* 444 */       if (((SQLStateMapping)paramList.get(i - 1)).lessThan(paramSQLStateMapping)) {
        break;
      }
    }
/* 448 */     paramList.add(i, paramSQLStateMapping);
  }
  
  static int valueOf(String paramString) throws Exception
  {
/* 453 */     if (paramString.equalsIgnoreCase("SQLEXCEPTION")) return 0;
/* 454 */     if (paramString.equalsIgnoreCase("SQLNONTRANSIENTEXCEPTION")) return 1;
/* 455 */     if (paramString.equalsIgnoreCase("SQLTRANSIENTEXCEPTION")) return 2;
/* 456 */     if (paramString.equalsIgnoreCase("SQLDATAEXCEPTION")) return 3;
/* 457 */     if (paramString.equalsIgnoreCase("SQLFEATURENOTSUPPORTEDEXCEPTION")) return 4;
/* 458 */     if (paramString.equalsIgnoreCase("SQLINTEGRITYCONSTRAINTVIOLATIONEXCEPTION")) return 5;
/* 459 */     if (paramString.equalsIgnoreCase("SQLINVALIDAUTHORIZATIONSPECEXCEPTION")) return 6;
/* 460 */     if (paramString.equalsIgnoreCase("SQLNONTRANSIENTCONNECTIONEXCEPTION")) return 7;
/* 461 */     if (paramString.equalsIgnoreCase("SQLSYNTAXERROREXCEPTION")) return 8;
/* 462 */     if (paramString.equalsIgnoreCase("SQLTIMEOUTEXCEPTION")) return 9;
/* 463 */     if (paramString.equalsIgnoreCase("SQLTRANSACTIONROLLBACKEXCEPTION")) return 10;
/* 464 */     if (paramString.equalsIgnoreCase("SQLTRANSIENTCONNECTIONEXCEPTION")) return 11;
/* 465 */     if (paramString.equalsIgnoreCase("SQLCLIENTINFOEXCEPTION")) return 12;
/* 466 */     if (paramString.equalsIgnoreCase("SQLRECOVERABLEEXCEPTION")) return 13;
/* 467 */     throw new Exception("unexpected exception name: " + paramString);
  }
  
/* 471 */   private static final String _Copyright_2007_Oracle_All_Rights_Reserved_ = null;
  public static final boolean TRACE = false;
}
/* Location:              /home/caixj/下载/ojdbc6.jar!/oracle/jdbc/driver/SQLStateMapping.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */